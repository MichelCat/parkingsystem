package com.parkit.parkingsystem.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;

// mc 20/06/2022b : Fix integration tests (storie)
@ExtendWith(MockitoExtension.class)
public class ParkingDataBaseIT {

  private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
  private static ParkingSpotDAO parkingSpotDAO;
  private static TicketDAO ticketDAO;
  private static DataBasePrepareService dataBasePrepareService;

  @Mock
  private static InputReaderUtil inputReaderUtil;

  @BeforeAll
  private static void setUp() throws Exception {
    parkingSpotDAO = new ParkingSpotDAO();
    parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
    ticketDAO = new TicketDAO();
    ticketDAO.dataBaseConfig = dataBaseTestConfig;
    dataBasePrepareService = new DataBasePrepareService();
  }

  @BeforeEach
  private void setUpPerTest() throws Exception {
    when(inputReaderUtil.readSelection()).thenReturn(1);
    when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
    dataBasePrepareService.clearDataBaseEntries();
  }

  @AfterAll
  private static void tearDown() {

  }

  @Test
  @DisplayName("Book parking")
  public void testParkingACar() {
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    parkingService.processIncomingVehicle();
    // => mc 20/06/2022b : Fix integration tests (storie)
    // THEN
    final Ticket ticketReadingResult = ticketDAO.getTicket("ABCDEF");
    assertThat(ticketReadingResult.getParkingSpot().getId()).isEqualTo(1);
    assertThat(ticketReadingResult.getVehicleRegNumber()).isEqualTo("ABCDEF");
    assertThat(ticketReadingResult.getPrice()).isZero();
    assertThat(ticketReadingResult.getInTime()).isNotNull();
    assertThat(ticketReadingResult.getOutTime()).isNull();

    final ParkingSpot parkingSpotReadingResult = parkingSpotDAO.getParkingSpot(1);
    assertThat(parkingSpotReadingResult.getId()).isEqualTo(1);
    assertThat(parkingSpotReadingResult.getParkingType()).isEqualTo(ParkingType.CAR);
    assertThat(parkingSpotReadingResult.isAvailable()).isFalse();
    // =< mc 20/06/2022b
  }

  @Test
  @DisplayName("Book and free parking")
  public void testParkingLotExit() {
    // => mc 20/06/2022b : Fix integration tests (storie)
    // testParkingACar();
    // ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO,
    // ticketDAO);
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    parkingService.processIncomingVehicle();
    // =< mc 20/06/2022b
    parkingService.processExitingVehicle();
    // => mc 20/06/2022b : Fix integration tests (storie)
    // THEN
    final Ticket ticketReadingResult = ticketDAO.getTicket("ABCDEF");
    assertThat(ticketReadingResult.getParkingSpot().getId()).isEqualTo(1);
    assertThat(ticketReadingResult.getVehicleRegNumber()).isEqualTo("ABCDEF");
    assertThat(ticketReadingResult.getPrice()).isZero();
    assertThat(ticketReadingResult.getInTime()).isNotNull();
    assertThat(ticketReadingResult.getOutTime()).isNotNull();

    final ParkingSpot parkingSpotReadingResult = parkingSpotDAO.getParkingSpot(1);
    assertThat(parkingSpotReadingResult.getId()).isEqualTo(1);
    assertThat(parkingSpotReadingResult.getParkingType()).isEqualTo(ParkingType.CAR);
    assertThat(parkingSpotReadingResult.isAvailable()).isTrue();
    // =< mc 20/06/2022b
  }

}
