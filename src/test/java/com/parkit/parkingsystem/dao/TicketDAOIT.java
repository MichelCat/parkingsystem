package com.parkit.parkingsystem.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

// mc 20/06/2022d : 5%-discount for recurring users (storie)
public class TicketDAOIT {

  private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
  private static TicketDAO ticketDAO;
  private static DataBasePrepareService dataBasePrepareService;

  private Ticket ticket;


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
  @DisplayName("Save null ticket, return false")
  public void saveTicket_NullTicket_returnFalse() {
    // GIVEN
    ticket = null;
    // WHEN
    final boolean result = ticketDAO.saveTicket(ticket);
    // THEN
    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("Save ticket, return true")
  public void saveTicket_saveTicket_returnTrue() {
    // GIVEN
    // WHEN
    final boolean result = ticketDAO.saveTicket(ticket);
    // THEN
    assertThat(result).isTrue();
    assertThat(ticket.compare(ticketDAO.getTicket("ABCDEF"))).isTrue();
  }


  // ----------------------------------------------------------------------------------------------------
  // Method getTicket
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Search null vehicle registration number, return null")
  public void getTicket_nullVehicleRegistrationNumber_returnNull() {
    // GIVEN
    String vehicleRegNumber = null;
    // WHEN
    final Ticket result = ticketDAO.getTicket(vehicleRegNumber);
    // THEN
    assertThat(result).isNull();
  }

  @Test
  @DisplayName("Search vehicle registration number, return last ticket")
  public void getTicket_vehicleRegistrationNumber_returnLastTicket() {
    // GIVEN
    final boolean returnStatusSaveTicket = ticketDAO.saveTicket(ticket);
    // WHEN
    final Ticket ticketReadingResult = ticketDAO.getTicket("ABCDEF");
    // THEN
    assertThat(returnStatusSaveTicket).isTrue();
    assertThat(ticket.compare(ticketReadingResult)).isTrue();
  }


  // ----------------------------------------------------------------------------------------------------
  // Method updateTicket
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Update null ticket, return null")
  public void updateTicket_nullUpdateTicket_returnNull() {
    // GIVEN
    ticket = null;
    // WHEN
    final boolean result = ticketDAO.updateTicket(ticket);
    // THEN
    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("Update ticket, return true")
  public void updateTicket_updateTicket_returnTrue() {
    // GIVEN
    final boolean returnStatusSaveTicket = ticketDAO.saveTicket(ticket);
    ticket.setPrice(10);
    Date outTime = new Date(ticket.getInTime().getTime() + (60 * 60 * 1000));
    ticket.setOutTime(outTime);
    // WHEN
    final boolean result = ticketDAO.updateTicket(ticket);
    // THEN
    assertThat(result).isTrue();
    assertThat(ticket.compare(ticketDAO.getTicket("ABCDEF"))).isTrue();
  }


  // => mc 20/06/2022d : 5%-discount for recurring users (storie)
  // ----------------------------------------------------------------------------------------------------
  // Method getVehicleRecurrenceNumberOutput
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Search null vehicle registration number output, return zero")
  public void getVehicleRecurrenceNumberOutput_nullVehicleRegistrationNumber_returnZero() {
    // GIVEN
    String vehicleRegNumber = null;
    // WHEN
    final int result = ticketDAO.getVehicleRecurrenceNumberOutput(vehicleRegNumber);
    // THEN
    assertThat(result).isZero();
  }

  @Test
  @DisplayName("Search vehicle registration number Output, return recurrence number")
  public void getVehicleRecurrenceNumberOutput_vehicleRegistrationNumberExit_returnRecurrenceNumber() {
    // GIVEN
    ticket.setOutTime(new Date());
    final boolean returnStatusSaveTicket = ticketDAO.saveTicket(ticket);
    // WHEN
    final int ticketReadingResult = ticketDAO.getVehicleRecurrenceNumberOutput("ABCDEF");
    // THEN
    assertThat(ticketReadingResult).isEqualTo(1);
  }
  // =< mc 20/06/2022d

}
