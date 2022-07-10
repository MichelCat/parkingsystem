package com.parkit.parkingsystem.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;

// mc 20/06/2022b : Fix integration tests (storie)
public class ParkingSpotDAOIT {

  private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
  private static ParkingSpotDAO parkingSpotDAO;
  private static DataBasePrepareService dataBasePrepareService;

  private ParkingSpot parkingSpot;

  private static Stream<Arguments> listOfParkingTypeParametersToTest() {
    // Elements (enum type of vehicle)
    return Stream.of(Arguments.of(ParkingType.CAR), Arguments.of(ParkingType.BIKE));
  }


  @BeforeAll
  private static void setUp() throws Exception {
    parkingSpotDAO = new ParkingSpotDAO();
    parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
    dataBasePrepareService = new DataBasePrepareService();
  }

  @BeforeEach
  private void setUpPerTest() {
    dataBasePrepareService.clearDataBaseEntries();
  }


  // ----------------------------------------------------------------------------------------------------
  // Method getNextAvailableSlot
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Next available car slot, retun one")
  public void getNextAvailableSlot_nextAvailableCarSlot_returnOne() {
    // GIVEN
    // WHEN
    final int result = parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR);
    // THEN
    assertThat(result).isEqualTo(1);
  }

  @Test
  @DisplayName("Next available bike slot, return four")
  public void getNextAvailableSlot_nextAvailableBikeSlot_returnFour() {
    // GIVEN
    // WHEN
    final int result = parkingSpotDAO.getNextAvailableSlot(ParkingType.BIKE);
    // THEN
    assertThat(result).isEqualTo(4);
  }


  // ----------------------------------------------------------------------------------------------------
  // Method updateParking
  // ----------------------------------------------------------------------------------------------------
  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  @DisplayName("Update parking, return true")
  public void updateParking_updateParking_returnTrue(ParkingType arg1) {
    // GIVEN
    parkingSpot = new ParkingSpot(1, arg1, false);
    // WHEN
    final boolean result = parkingSpotDAO.updateParking(parkingSpot);
    // THEN
    assertThat(result).isTrue();
    assertThat(parkingSpotDAO.getParkingSpot(parkingSpot.getId())).isEqualTo(parkingSpot);
  }

  @Test
  @DisplayName("Update null parking, return false")
  public void updateParking_nullParkingSpot_returnFalse() {
    // GIVEN
    parkingSpot = null;
    // WHEN
    final boolean result = parkingSpotDAO.updateParking(parkingSpot);
    // THEN
    assertThat(result).isFalse();
  }


  // => mc 20/06/2022b : Fix integration tests (storie)
  // ----------------------------------------------------------------------------------------------------
  // Method getParkingSpot
  // ----------------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Search parking number zero, return null ParkingSpot")
  public void getParkingSpot_parkingNumberZero_returnNullParkingSpot() {
    // GIVEN
    // WHEN
    final ParkingSpot result = parkingSpotDAO.getParkingSpot(0);
    // THEN
    assertThat(result).isNull();
  }

  @Test
  @DisplayName("Search parking number one, return first ParkingSpot")
  public void getParkingSpot_parkingNumberOne_returnFirstParkingSpot() {
    // GIVEN
    // WHEN
    final ParkingSpot result = parkingSpotDAO.getParkingSpot(1);
    // THEN
    assertThat(result.getId()).isEqualTo(1);
  }
  // =< mc 20/06/2022b

}
