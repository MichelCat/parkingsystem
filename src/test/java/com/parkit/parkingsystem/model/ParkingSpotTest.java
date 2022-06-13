package com.parkit.parkingsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.parkit.parkingsystem.constants.ParkingType;

class ParkingSpotTest {

  private static ParkingSpot parkingSpot;

  @BeforeEach
  private void setUpPerTest() {
    parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method getId
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void getId_shouldReturnsNumber() {
    assertEquals(parkingSpot.getId(), 1);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setId
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setId_writeNumber() {
    parkingSpot.setId(2);
    assertEquals(parkingSpot.getId(), 2);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method getParkingType
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void getParkingType_shouldReturnsParkingType() {
    assertEquals(parkingSpot.getParkingType(), ParkingType.CAR);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setParkingType
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setParkingType_writeParkingType() {
    parkingSpot.setParkingType(ParkingType.BIKE);
    assertEquals(parkingSpot.getParkingType(), ParkingType.BIKE);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method isAvailable
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void isAvailable_shouldReturnsIsAvailable() {
    assertEquals(parkingSpot.isAvailable(), false);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setAvailable
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setAvailable_writeIsAvailable() {
    parkingSpot.setAvailable(true);
    assertEquals(parkingSpot.isAvailable(), true);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method equals
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void equals_shouldReturnsIsAvailable_identicalParkingSpot() {
    assertEquals(parkingSpot.equals(parkingSpot), true);
  }

  @Test
  public void equals_shouldReturnsIsAvailable_equalParkingSpot() {
    ParkingSpot parkingSpotToTest = new ParkingSpot(1, ParkingType.CAR, false);
    assertEquals(parkingSpot.equals(parkingSpotToTest), true);
  }

  @Test
  public void equals_shouldReturnsIsAvailable_notEqualParkingSpot() {
    ParkingSpot parkingSpotToTest = new ParkingSpot(2, ParkingType.CAR, false);
    assertEquals(parkingSpot.equals(parkingSpotToTest), false);
  }

  @Test
  public void equals_shouldReturnsIsAvailable_nullParkingSpot() {
    ParkingSpot parkingSpotToTest = null;
    assertEquals(parkingSpot.equals(parkingSpotToTest), false);
  }

  @Test
  public void equals_shouldReturnsIsAvailable_differentParkingSpot() {
    Ticket ticket = new Ticket();
    assertEquals(parkingSpot.equals(ticket), false);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method hashCode
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void hashCode_shouldReturnsNumber() {
    assertEquals(parkingSpot.hashCode(), 1);
  }


}
