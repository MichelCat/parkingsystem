package com.parkit.parkingsystem.model;

import static org.assertj.core.api.Assertions.assertThat;
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
    assertThat(parkingSpot.getId()).isEqualTo(1);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setId
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setId_writeNumber() {
    parkingSpot.setId(2);
    assertThat(parkingSpot.getId()).isEqualTo(2);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method getParkingType
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void getParkingType_shouldReturnsParkingType() {
    assertThat(parkingSpot.getParkingType()).isEqualTo(ParkingType.CAR);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setParkingType
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setParkingType_writeParkingType() {
    parkingSpot.setParkingType(ParkingType.BIKE);
    assertThat(parkingSpot.getParkingType()).isEqualTo(ParkingType.BIKE);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method isAvailable
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void isAvailable_shouldReturnsIsAvailable() {
    assertThat(parkingSpot.isAvailable()).isFalse();
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setAvailable
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void setAvailable_writeIsAvailable() {
    parkingSpot.setAvailable(true);
    assertThat(parkingSpot.isAvailable()).isTrue();
  }

  // ----------------------------------------------------------------------------------------------------
  // Method equals
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void equals_shouldReturnsIsAvailable_identicalParkingSpot() {
    assertThat(parkingSpot.equals(parkingSpot)).isTrue();
  }

  @Test
  public void equals_shouldReturnsIsAvailable_equalParkingSpot() {
    ParkingSpot parkingSpotToTest = new ParkingSpot(1, ParkingType.CAR, false);
    assertThat(parkingSpot.equals(parkingSpotToTest)).isTrue();
  }

  @Test
  public void equals_shouldReturnsIsAvailable_notEqualParkingSpot() {
    ParkingSpot parkingSpotToTest = new ParkingSpot(2, ParkingType.CAR, false);
    assertThat(parkingSpot.equals(parkingSpotToTest)).isFalse();
  }

  @Test
  public void equals_shouldReturnsIsAvailable_nullParkingSpot() {
    ParkingSpot parkingSpotToTest = null;
    assertThat(parkingSpot.equals(parkingSpotToTest)).isFalse();

  }

  @Test
  public void equals_shouldReturnsIsAvailable_differentParkingSpot() {
    Ticket ticket = new Ticket();
    assertThat(parkingSpot.equals(ticket)).isFalse();
  }

  // ----------------------------------------------------------------------------------------------------
  // Method hashCode
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void hashCode_shouldReturnsNumber() {
    assertThat(parkingSpot.hashCode()).isEqualTo(1);
  }


}
