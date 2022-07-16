package com.parkit.parkingsystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Date;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.util.InputReaderUtil;


@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

  @Mock
  private static InputReaderUtil inputReaderUtil;
  @Mock
  private static ParkingSpotDAO parkingSpotDAO;
  @Mock
  private static TicketDAO ticketDAO;

  private static Stream<Arguments> listOfParkingTypeParametersToTest() {
    // Elements (Type of vehicle entered, enum type of vehicle)
    return Stream.of(Arguments.of(1, ParkingType.CAR), Arguments.of(2, ParkingType.BIKE));
  }


  // -----------------------------------------------------------------------------------------------
  // Method processIncomingVehicle
  // -----------------------------------------------------------------------------------------------
  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  @DisplayName("Book parking")
  public void processIncomingVehicle_BookParking(int arg1, ParkingType arg2) throws Exception {
    // GIVEN
    when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
    when(inputReaderUtil.readSelection()).thenReturn(arg1);
    when(parkingSpotDAO.getNextAvailableSlot(arg2)).thenReturn(1);
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    // WHEN
    parkingService.processIncomingVehicle();
    // THEN
    verify(ticketDAO, Mockito.times(1)).saveTicket(any(Ticket.class));
  }

  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  @DisplayName("IllegalArgumentException on reading vehicle registration number")
  public void processIncomingVehicle_throwIllegalArgumentException_ofRegistrationNumber(int arg1,
      ParkingType arg2) throws Exception {
    // GIVEN
    when(inputReaderUtil.readVehicleRegistrationNumber()).thenThrow(new IllegalArgumentException());
    when(inputReaderUtil.readSelection()).thenReturn(arg1);
    when(parkingSpotDAO.getNextAvailableSlot(arg2)).thenReturn(1);
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    // WHEN
    parkingService.processIncomingVehicle();
    // THEN
    verify(ticketDAO, Mockito.times(0)).saveTicket(any(Ticket.class));
  }

  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  @DisplayName("Exception on reading vehicle registration number")
  public void processIncomingVehicle_throwException_ofRegistrationNumber(int arg1, ParkingType arg2)
      throws Exception {
    // GIVEN
    when(inputReaderUtil.readVehicleRegistrationNumber()).thenThrow(new Exception());
    when(inputReaderUtil.readSelection()).thenReturn(arg1);
    when(parkingSpotDAO.getNextAvailableSlot(arg2)).thenReturn(1);
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    // WHEN
    parkingService.processIncomingVehicle();
    // THEN
    verify(ticketDAO, Mockito.times(0)).saveTicket(any(Ticket.class));
  }

  @Test
  @DisplayName("IllegalArgumentException on reading zero parking number")
  public void processIncomingVehicle_zeroParkingNumber() {
    // GIVEN
    when(inputReaderUtil.readSelection()).thenReturn(0);
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    // WHEN
    parkingService.processIncomingVehicle();
    // THEN
    verify(ticketDAO, Mockito.times(0)).saveTicket(any(Ticket.class));
  }

  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  @DisplayName("Exception on parking number with value -1")
  public void processIncomingVehicle_incorrectReading_ofParkingNumber(int arg1, ParkingType arg2)
      throws Exception {
    // GIVEN
    when(inputReaderUtil.readSelection()).thenReturn(arg1);
    when(parkingSpotDAO.getNextAvailableSlot(arg2)).thenReturn(1);
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    when(parkingService.getNextParkingNumberIfAvailable().getId()).thenReturn(-1);
    // WHEN
    parkingService.processIncomingVehicle();
    // THEN
    verify(ticketDAO, Mockito.times(0)).saveTicket(any(Ticket.class));
  }


  // -----------------------------------------------------------------------------------------------
  // Method getNextParkingNumberIfAvailable
  // -----------------------------------------------------------------------------------------------
  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  @DisplayName("Read ParkingSpot corresponding to the vehicle number")
  public void getNextParkingNumberIfAvailable_returnParkingSpot(int arg1, ParkingType arg2) {
    // GIVEN
    when(inputReaderUtil.readSelection()).thenReturn(arg1);
    when(parkingSpotDAO.getNextAvailableSlot(arg2)).thenReturn(1);
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    // WHEN
    final ParkingSpot result = parkingService.getNextParkingNumberIfAvailable();
    // THEN
    assertThat(result).isEqualTo(new ParkingSpot(1, arg2, true));
  }

  @Test
  @DisplayName("IllegalArgumentException on reading zero parking number")
  public void getNextParkingNumberIfAvailable_incorrectInputVehicleType() {
    // GIVEN
    when(inputReaderUtil.readSelection()).thenReturn(0);
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    // WHEN
    final ParkingSpot result = parkingService.getNextParkingNumberIfAvailable();
    // THEN
    assertThat(result).isNull();
  }

  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  @DisplayName("Exception on parking number with value -1")
  public void getNextParkingNumberIfAvailable_searchErrorParkingNumber(int arg1, ParkingType arg2) {
    // GIVEN
    when(inputReaderUtil.readSelection()).thenReturn(arg1);
    when(parkingSpotDAO.getNextAvailableSlot(arg2)).thenReturn(-1);
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    // WHEN
    final ParkingSpot result = parkingService.getNextParkingNumberIfAvailable();
    // THEN
    assertThat(result).isNull();
  }


  // -----------------------------------------------------------------------------------------------
  // Method processExitingVehicle
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Free parking")
  public void processExitingVehicle_freeParking() throws Exception {
    // GIVEN
    when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");

    ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
    Ticket ticket = new Ticket();
    ticket.setInTime(new Date(System.currentTimeMillis() - (60 * 60 * 1000)));
    ticket.setParkingSpot(parkingSpot);
    ticket.setVehicleRegNumber("ABCDEF");
    when(ticketDAO.getTicket(anyString())).thenReturn(ticket);
    when(ticketDAO.updateTicket(any(Ticket.class))).thenReturn(true);

    when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);

    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);

    // WHEN
    parkingService.processExitingVehicle();
    // THEN
    verify(parkingSpotDAO, Mockito.times(1)).updateParking(any(ParkingSpot.class));
  }

  @Test
  @DisplayName("IllegalArgumentException on reading vehicle registration number")
  public void processExitingVehicle_throwIllegalArgumentException_ofRegistrationNumber()
      throws Exception {
    // GIVEN
    when(inputReaderUtil.readVehicleRegistrationNumber()).thenThrow(new IllegalArgumentException());
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    // WHEN
    parkingService.processExitingVehicle();
    // THEN
    verify(parkingSpotDAO, Mockito.times(0)).updateParking(any(ParkingSpot.class));
  }

  @Test
  @DisplayName("Exception on reading vehicle registration number")
  public void processExitingVehicle_throwException_ofRegistrationNumber() throws Exception {
    // GIVEN
    when(inputReaderUtil.readVehicleRegistrationNumber()).thenThrow(new Exception());
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    // WHEN
    parkingService.processExitingVehicle();
    // THEN
    verify(parkingSpotDAO, Mockito.times(0)).updateParking(any(ParkingSpot.class));
  }


  @Test
  @DisplayName("Update ticket return false")
  public void processExitingVehicle_updateError_ofTicket() throws Exception {
    // GIVEN
    when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");

    ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
    Ticket ticket = new Ticket();
    ticket.setInTime(new Date(System.currentTimeMillis() - (60 * 60 * 1000)));
    ticket.setParkingSpot(parkingSpot);
    ticket.setVehicleRegNumber("ABCDEF");
    when(ticketDAO.getTicket(anyString())).thenReturn(ticket);
    when(ticketDAO.updateTicket(any(Ticket.class))).thenReturn(false);

    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);

    // WHEN
    parkingService.processExitingVehicle();
    // THEN
    verify(parkingSpotDAO, Mockito.times(0)).updateParking(any(ParkingSpot.class));
  }

}
