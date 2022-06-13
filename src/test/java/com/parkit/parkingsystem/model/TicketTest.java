package com.parkit.parkingsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
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

  // ----------------------------------------------------------------------------------------------------
  // Method getId
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void getId_shouldReturnsId() {
    assertEquals(ticket.getId(), 1);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setId
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setId_writeId() {
    ticket.setId(2);
    assertEquals(ticket.getId(), 2);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method getParkingSpot
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void getParkingSpot_shouldReturnsParkingSpot() {
    assertEquals(ticket.getParkingSpot(), parkingSpot);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setParkingSpot
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setParkingSpot_writeParkingSpot() {
    ParkingSpot parkingSpotToTest = new ParkingSpot(2, ParkingType.CAR, false);
    ticket.setParkingSpot(parkingSpotToTest);
    assertEquals(ticket.getParkingSpot(), parkingSpotToTest);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method getVehicleRegNumber
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void getVehicleRegNumber_shouldReturnsVehicleRegNumber() {
    assertEquals(ticket.getVehicleRegNumber(), "ABCDEF");
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setVehicleRegNumber
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setVehicleRegNumber_writeVehicleRegNumber() {
    ticket.setVehicleRegNumber("123456");
    assertEquals(ticket.getVehicleRegNumber(), "123456");
  }

  // ----------------------------------------------------------------------------------------------------
  // Method getPrice
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void getPrice_shouldReturnsPrice() {
    assertEquals(ticket.getPrice(), 0);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setPrice
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setPrice_writePrice() {
    ticket.setPrice(89.0);
    assertEquals(ticket.getPrice(), 89.0);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method getInTime
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void getInTime_shouldReturnsInTime() {
    assertEquals(ticket.getInTime(), currentTime);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setInTime
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setInTime_writeInTime() {
    Date currentTimeTest = new Date(System.currentTimeMillis() + 1000);
    ticket.setInTime(currentTimeTest);
    assertEquals(ticket.getInTime(), currentTimeTest);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method getOutTime
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void getOutTime_shouldReturnsOutTime() {
    assertEquals(ticket.getOutTime(), currentTime);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setOutTime
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setOutTime_writeOutTime() {
    Date currentTimeTest = new Date(System.currentTimeMillis() + 1000);
    ticket.setOutTime(currentTimeTest);
    assertEquals(ticket.getOutTime(), currentTimeTest);
  }

}
