package com.parkit.parkingsystem.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;

// mc 20/06/2022b : Fix integration tests (storie)
@ExtendWith(MockitoExtension.class)
public class ParkingSpotDAOIT {

  private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
  private static ParkingSpotDAO parkingSpotDAO;
  private static DataBasePrepareService dataBasePrepareService;

  private static ParkingSpot parkingSpot;

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
  public void getNextAvailableSlot_shouldNextAvailableSlot_CAR() throws Exception {
    // GIVEN
    parkingSpot = new ParkingSpot(1, ParkingType.CAR, true);
    final boolean returnStatusUpdateParking = parkingSpotDAO.updateParking(parkingSpot);
    // WHEN
    final int result = parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR);
    // THEN
    assertThat(result).isEqualTo(1);
  }

  @Test
  public void getNextAvailableSlot_shouldNextAvailableSlot_BIKE() throws Exception {
    // GIVEN
    parkingSpot = new ParkingSpot(1, ParkingType.BIKE, true);
    final boolean returnStatusUpdateParking = parkingSpotDAO.updateParking(parkingSpot);
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
  public void updateParking_shouldUpdateParking(ParkingType arg1) throws Exception {
    // GIVEN
    parkingSpot = new ParkingSpot(1, arg1, false);
    // WHEN
    final boolean result = parkingSpotDAO.updateParking(parkingSpot);
    // THEN
    assertThat(result).isTrue();
  }

  @Test
  public void updateParking_shouldNull_ofParkingSpot() throws Exception {
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
  public void getParkingSpot_shouldZero_ofParkingType() {
    // GIVEN
    // WHEN
    final ParkingSpot result = parkingSpotDAO.getParkingSpot(0);
    // THEN
    assertThat(result).isNull();
  }

  @Test
  public void getParkingSpot_shouldParkingType() {
    // GIVEN
    // WHEN
    final ParkingSpot result = parkingSpotDAO.getParkingSpot(1);
    // THEN
    assertThat(result.getId()).isEqualTo(1);
  }
  // =< mc 20/06/2022b

}
