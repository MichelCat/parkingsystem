package com.parkit.parkingsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.parkit.parkingsystem.constants.ParkingType;

class TicketTest {

  private static Ticket ticket;
  private static ParkingSpot parkingSpot;
  private static Date currentTime = new Date(System.currentTimeMillis());

  @BeforeEach
  private void setUpPerTest() {
    parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);

    ticket = new Ticket();
    ticket.setId(1);
    ticket.setParkingSpot(parkingSpot);
    ticket.setVehicleRegNumber("ABCDEF");
    ticket.setPrice(0);
    ticket.setInTime(currentTime);
    ticket.setOutTime(currentTime);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getId
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read identity ticket")
  public void getId_returnIdentityTicket() {
    assertEquals(ticket.getId(), 1);
  }

  // -----------------------------------------------------------------------------------------------
  // Method setId
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write identity ticket")
  public void setId_writeIdentityTicket() {
    ticket.setId(2);
    assertEquals(ticket.getId(), 2);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getParkingSpot
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read ParkingSpot")
  public void getParkingSpot_returnParkingSpot() {
    assertEquals(ticket.getParkingSpot(), parkingSpot);
  }

  // -----------------------------------------------------------------------------------------------
  // Method setParkingSpot
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write ParkingSpot")
  public void setParkingSpot_writeParkingSpot() {
    ParkingSpot parkingSpotToTest = new ParkingSpot(2, ParkingType.CAR, false);
    ticket.setParkingSpot(parkingSpotToTest);
    assertEquals(ticket.getParkingSpot(), parkingSpotToTest);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getVehicleRegNumber
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read vehicle registration number")
  public void getVehicleRegNumber_returnVehicleRegistrationNumber() {
    assertEquals(ticket.getVehicleRegNumber(), "ABCDEF");
  }

  // -----------------------------------------------------------------------------------------------
  // Method setVehicleRegNumber
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write vehicle registration number")
  public void setVehicleRegNumber_writeVehicleRegistrationNumber() {
    ticket.setVehicleRegNumber("123456");
    assertEquals(ticket.getVehicleRegNumber(), "123456");
  }

  // -----------------------------------------------------------------------------------------------
  // Method getPrice
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read price")
  public void getPrice_returnPrice() {
    assertEquals(ticket.getPrice(), 0);
  }

  // -----------------------------------------------------------------------------------------------
  // Method setPrice
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write price")
  public void setPrice_writePrice() {
    ticket.setPrice(89.0);
    assertEquals(ticket.getPrice(), 89.0);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getInTime
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read input time")
  public void getInTime_returnInputTime() {
    assertEquals(ticket.getInTime(), currentTime);
  }

  // -----------------------------------------------------------------------------------------------
  // Method setInTime
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write input time")
  public void setInTime_writeInputTime() {
    Date currentTimeTest = new Date(System.currentTimeMillis());
    ticket.setInTime(currentTimeTest);
    assertEquals(ticket.getInTime(), currentTimeTest);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getOutTime
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read output time")
  public void getOutTime_returnOutputTime() {
    assertEquals(ticket.getOutTime(), currentTime);
  }

  // -----------------------------------------------------------------------------------------------
  // Method setOutTime
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write output time")
  public void setOutTime_writeOutputTime() {
    Date currentTimeTest = new Date(System.currentTimeMillis());
    ticket.setOutTime(currentTimeTest);
    assertEquals(ticket.getOutTime(), currentTimeTest);
  }

}
