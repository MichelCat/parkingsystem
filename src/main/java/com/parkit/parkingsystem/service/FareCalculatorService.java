package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

// mc 20/06/2022c : Free 30-min parking (storie)
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

    // => MC 06/06/2020a : Correction of parking price calculation
    // int duration = outHour - inHour;
    double duration = (outHour - inHour) / (3600 * 1000);
    // =< MC 06/06/2020a
    // => mc 20/06/2022c : Free 30-min parking (storie)
    // Park for free when the user stays less than 30 minutes
    if (duration < 0.5) {
      duration = 0.0;
    }
    // =< mc 20/06/2022c

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

  public void discountForRecurringUsers(Ticket ticket) {
    ticket.setPrice(ticket.getPrice() * 0.05); // 5% discount
  }
}
