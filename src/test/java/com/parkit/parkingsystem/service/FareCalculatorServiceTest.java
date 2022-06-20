package com.parkit.parkingsystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Date;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

// mc 20/06/2022c : Free 30-min parking (stories STORY#1)
@ExtendWith(MockitoExtension.class)
public class FareCalculatorServiceTest {

  private static FareCalculatorService fareCalculatorService;
  private Ticket ticket;
  private long currentTime;
  private Date outTime;
  private Date inTime;

  private static Stream<Arguments> listOfParkingTypeParametersToTest() {
    // Elements (enum type of vehicle)
    return Stream.of(Arguments.of(ParkingType.CAR, Fare.CAR_RATE_PER_HOUR),
        Arguments.of(ParkingType.BIKE, Fare.BIKE_RATE_PER_HOUR));
  }


  @BeforeAll
  private static void setUp() {
    fareCalculatorService = new FareCalculatorService();
  }

  @BeforeEach
  private void setUpPerTest() {
    currentTime = System.currentTimeMillis();
    outTime = new Date();
    outTime.setTime(currentTime);
    inTime = new Date();

    ticket = new Ticket();
  }


  // ----------------------------------------------------------------------------------------------------
  // Method calculateFare
  // ----------------------------------------------------------------------------------------------------
  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  public void calculateFareCarBike(ParkingType arg1, double arg2) {
    // GIVEN
    inTime.setTime(currentTime - (60 * 60 * 1000));
    ticket.setInTime(inTime);
    ticket.setOutTime(outTime);
    ParkingSpot parkingSpot = new ParkingSpot(1, arg1, false);
    ticket.setParkingSpot(parkingSpot);
    // WHEN
    fareCalculatorService.calculateFare(ticket);
    // THEN
    assertEquals(ticket.getPrice(), arg2);
  }

  @Test
  public void calculateFareUnkownType() {
    // GIVEN
    inTime.setTime(currentTime - (60 * 60 * 1000));
    ticket.setInTime(inTime);
    ticket.setOutTime(outTime);
    ParkingSpot parkingSpot = new ParkingSpot(1, null, false);
    ticket.setParkingSpot(parkingSpot);
    // WHEN
    assertThrows(NullPointerException.class, () -> fareCalculatorService.calculateFare(ticket));
    // THEN
  }

  @Test
  public void calculateFareBikeWithFutureInTime() {
    // GIVEN
    inTime.setTime(currentTime + (60 * 60 * 1000));
    ticket.setInTime(inTime);
    ticket.setOutTime(outTime);
    ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.BIKE, false);
    ticket.setParkingSpot(parkingSpot);
    // WHEN
    assertThrows(IllegalArgumentException.class, () -> fareCalculatorService.calculateFare(ticket));
    // THEN
  }

  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  public void calculateFareWithLessThanOneHourParkingTime(ParkingType arg1, double arg2) {
    // GIVEN
    inTime.setTime(currentTime - (45 * 60 * 1000));// 45 minutes parking time should
                                                   // give 3/4th parking fare
    ticket.setInTime(inTime);
    ticket.setOutTime(outTime);
    ParkingSpot parkingSpot = new ParkingSpot(1, arg1, false);
    ticket.setParkingSpot(parkingSpot);
    // WHEN
    fareCalculatorService.calculateFare(ticket);
    // THEN
    assertEquals((0.75 * arg2), ticket.getPrice());
  }

  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  public void calculateFareCarWithMoreThanADayParkingTime(ParkingType arg1, double arg2) {
    // GIVEN
    inTime.setTime(currentTime - (24 * 60 * 60 * 1000));// 24 hours parking time
    // should give 24 * parking
    // fare per hour
    ticket.setInTime(inTime);
    ticket.setOutTime(outTime);
    ParkingSpot parkingSpot = new ParkingSpot(1, arg1, false);
    ticket.setParkingSpot(parkingSpot);
    // WHEN
    fareCalculatorService.calculateFare(ticket);
    // THEN
    assertEquals((24 * arg2), ticket.getPrice());
  }


  // => mc 20/06/2022c : Free 30-min parking (stories STORY#1)
  // ----------------------------------------------------------------------------------------------------
  // Method calculateFare
  // Park for free when the user stays less than 30 minutes
  // ----------------------------------------------------------------------------------------------------
  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  public void calculateFare_when30Minutes00secondesCarBike(ParkingType arg1, double arg2) {
    // GIVEN
    inTime.setTime(currentTime - (30 * 60 * 1000));
    ticket.setInTime(inTime);
    ticket.setOutTime(outTime);
    ParkingSpot parkingSpot = new ParkingSpot(1, arg1, false);
    ticket.setParkingSpot(parkingSpot);
    // WHEN
    fareCalculatorService.calculateFare(ticket);
    // THEN
    assertThat(ticket.getPrice() > 0);
  }

  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  public void calculateFare_when29Minutes59secondesCarBike(ParkingType arg1, double arg2) {
    // GIVEN
    inTime.setTime(currentTime - (30 * 60 - 1) * 1000);
    ticket.setInTime(inTime);
    ticket.setOutTime(outTime);
    ParkingSpot parkingSpot = new ParkingSpot(1, arg1, false);
    ticket.setParkingSpot(parkingSpot);
    // WHEN
    fareCalculatorService.calculateFare(ticket);
    // THEN
    assertThat(ticket.getPrice() == 0);
  }

  @ParameterizedTest(name = "{0} + {1}")
  @MethodSource("listOfParkingTypeParametersToTest")
  public void calculateFare_when30Minutes01secondesCarBike(ParkingType arg1, double arg2) {
    // GIVEN
    inTime.setTime(currentTime - (30 * 60 + 1) * 1000);
    ticket.setInTime(inTime);
    ticket.setOutTime(outTime);
    ParkingSpot parkingSpot = new ParkingSpot(1, arg1, false);
    ticket.setParkingSpot(parkingSpot);
    // WHEN
    fareCalculatorService.calculateFare(ticket);
    // THEN
    assertThat(ticket.getPrice() > 0);
  }
  // =< mc 20/06/2022c

}
