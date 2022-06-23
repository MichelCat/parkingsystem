package com.parkit.parkingsystem.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

// mc 20/06/2022d : 5%-discount for recurring users (storie)
@ExtendWith(MockitoExtension.class)
public class TicketDAOIT {

  private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
  private static TicketDAO ticketDAO;
  private static DataBasePrepareService dataBasePrepareService;

  private static Ticket ticket;


  @BeforeAll
  private static void setUp() throws Exception {
    ticketDAO = new TicketDAO();
    ticketDAO.dataBaseConfig = dataBaseTestConfig;
    dataBasePrepareService = new DataBasePrepareService();
  }


  @BeforeEach
  private void setUpPerTest() {
    dataBasePrepareService.clearDataBaseEntries();

    ticket = new Ticket();
    ticket.setId(1);
    ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
    ticket.setParkingSpot(parkingSpot);
    ticket.setVehicleRegNumber("ABCDEF");
    ticket.setPrice(0);
    ticket.setInTime(new Date());
    ticket.setOutTime(null);
  }


  // ----------------------------------------------------------------------------------------------------
  // Method saveTicket
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void saveTicket_shouldNull_ofTicket() throws Exception {
    // GIVEN
    ticket = null;
    // WHEN
    final boolean result = ticketDAO.saveTicket(ticket);
    // THEN
    assertThat(result).isFalse();
  }

  @Test
  public void saveTicket_shouldCreateTicket() throws Exception {
    // GIVEN
    // WHEN
    final boolean result = ticketDAO.saveTicket(ticket);
    // THEN
    assertThat(result).isFalse();
  }


  // ----------------------------------------------------------------------------------------------------
  // Method getTicket
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void getTicket_shouldNullString_ofVehicleRegNumber() throws Exception {
    // GIVEN
    String vehicleRegNumber = null;
    // WHEN
    final Ticket result = ticketDAO.getTicket(vehicleRegNumber);
    // THEN
    assertThat(result).isNull();
  }

  @Test
  public void getTicket_shouldReadTicket() throws Exception {
    // GIVEN
    final boolean returnStatusSaveTicket = ticketDAO.saveTicket(ticket);
    // WHEN
    final Ticket ticketReadingResult = ticketDAO.getTicket("ABCDEF");
    // THEN
    assertThat(ticket.compare(ticketReadingResult)).isTrue();
  }


  // ----------------------------------------------------------------------------------------------------
  // Method updateTicket
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void updateTicket_shouldNull_ofTicket() throws Exception {
    // GIVEN
    ticket = null;
    // WHEN
    final boolean result = ticketDAO.updateTicket(ticket);
    // THEN
    assertThat(result).isFalse();
  }

  @Test
  public void updateTicket_shouldUpdateTicket() throws Exception {
    // GIVEN
    final boolean returnStatusSaveTicket = ticketDAO.saveTicket(ticket);
    ticket.setPrice(10);
    Date outTime = new Date(ticket.getInTime().getTime() + (60 * 60 * 1000));
    ticket.setOutTime(outTime);
    // WHEN
    final boolean result = ticketDAO.updateTicket(ticket);
    // THEN
    final Ticket ticketReadingResult = ticketDAO.getTicket("ABCDEF");
    assertThat(ticket.compare(ticketReadingResult)).isTrue();
  }


  // => mc 20/06/2022d : 5%-discount for recurring users (storie)
  // ----------------------------------------------------------------------------------------------------
  // Method getVehicleRecurrenceNumber
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void getVehicleRecurrenceNumber_shouldNullString_ofVehicleRegNumber() throws Exception {
    // GIVEN
    String vehicleRegNumber = null;
    // WHEN
    final int result = ticketDAO.getVehicleRecurrenceNumber(vehicleRegNumber);
    // THEN
    assertThat(result).isZero();
  }

  @Test
  public void getVehicleRecurrenceNumber_shouldReadTicket() throws Exception {
    // GIVEN
    ticket.setInTime(new Date());
    ticket.setOutTime(new Date());
    final boolean returnStatusSaveTicket1 = ticketDAO.saveTicket(ticket);

    ticket.setInTime(new Date());
    ticket.setOutTime(null);
    final boolean returnStatusSaveTicket2 = ticketDAO.saveTicket(ticket);
    // WHEN
    final int ticketReadingResult = ticketDAO.getVehicleRecurrenceNumber("ABCDEF");
    // THEN
    assertThat(ticketReadingResult).isEqualTo(1);
  }
  // =< mc 20/06/2022d

}
