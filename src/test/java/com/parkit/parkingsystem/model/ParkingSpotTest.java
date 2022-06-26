package com.parkit.parkingsystem.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
  @DisplayName("Read parking number")
  public void getId_returnParkingNumber() {
    assertThat(parkingSpot.getId()).isEqualTo(1);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setId
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write parking number")
  public void setId_writeParkingNumber() {
    parkingSpot.setId(2);
    assertThat(parkingSpot.getId()).isEqualTo(2);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method getParkingType
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read parking type")
  public void getParkingType_returnParkingType() {
    assertThat(parkingSpot.getParkingType()).isEqualTo(ParkingType.CAR);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setParkingType
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write parking type")
  public void setParkingType_writeParkingType() {
    parkingSpot.setParkingType(ParkingType.BIKE);
    assertThat(parkingSpot.getParkingType()).isEqualTo(ParkingType.BIKE);
  }

  // ----------------------------------------------------------------------------------------------------
  // Method isAvailable
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read parking is available")
  public void isAvailable_returnParkingIsAvailable() {
    assertThat(parkingSpot.isAvailable()).isFalse();
  }

  // ----------------------------------------------------------------------------------------------------
  // Method setAvailable
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write parking is available")
  public void setAvailable_writeParkingIsAvailable() {
    parkingSpot.setAvailable(true);
    assertThat(parkingSpot.isAvailable()).isTrue();
  }

  // ----------------------------------------------------------------------------------------------------
  // Method equals
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Identical ParkingSpot, return true")
  public void equals_identicalParkingSpot_returnTrue() {
    assertThat(parkingSpot.equals(parkingSpot)).isTrue();
  }

  @Test
  @DisplayName("Equal ParkingSpot, return true")
  public void equals_equalParkingSpot_returnTrue() {
    ParkingSpot parkingSpotToTest = new ParkingSpot(1, ParkingType.CAR, false);
    assertThat(parkingSpot.equals(parkingSpotToTest)).isTrue();
  }

  @Test
  @DisplayName("Not equal parking number, return false")
  public void equals_notEqualParkingNumber_returnFalse() {
    ParkingSpot parkingSpotToTest = new ParkingSpot(2, ParkingType.CAR, false);
    assertThat(parkingSpot.equals(parkingSpotToTest)).isFalse();
  }

  @Test
  @DisplayName("Null ParkingSpot, return false")
  public void equals_nullParkingSpot_returnFalse() {
    ParkingSpot parkingSpotToTest = null;
    assertThat(parkingSpot.equals(parkingSpotToTest)).isFalse();

  }

  @Test
  @DisplayName("Different ParkingSpot, return false")
  public void equals_differentParkingSpot_returnFalse() {
    Ticket ticket = new Ticket();
    assertThat(parkingSpot.equals(ticket)).isFalse();
  }

  // ----------------------------------------------------------------------------------------------------
  // Method hashCode
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Return parking number")
  public void hashCode_returnParkingNumber() {
    assertThat(parkingSpot.hashCode()).isEqualTo(1);
  }

}
