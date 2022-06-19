package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {
  // MC 06/06/2020a : Correction of parking price calculation

  public void calculateFare(Ticket ticket) {
    if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
      throw new IllegalArgumentException(
          "Out time provided is incorrect:" + ticket.getOutTime().toString());
    }

    // => MC 06/06/2020a : Correction of parking price calculation
    // int inHour = ticket.getInTime().getHours();
    // int outHour = ticket.getOutTime().getHours();
    double inHour = ticket.getInTime().getTime();
    double outHour = ticket.getOutTime().getTime();
    // =< MC 06/06/2020a

    // TODO: Some tests are failing here. Need to check if this logic is correct
    // => MC 06/06/2020a : Correction of parking price calculation
    // int duration = outHour - inHour;
    double duration = (outHour - inHour) / (3600 * 1000);
    // =< MC 06/06/2020a

    switch (ticket.getParkingSpot().getParkingType()) {
      case CAR: {
        ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
        break;
      }
      case BIKE: {
        ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
        break;
      }
      default:
        throw new IllegalArgumentException("Unkown Parking Type");
    }
  }
}
