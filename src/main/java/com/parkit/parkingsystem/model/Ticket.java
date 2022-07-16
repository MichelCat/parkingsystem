package com.parkit.parkingsystem.model;

import java.util.Date;
import com.parkit.parkingsystem.dao.ConversionDAO;

// mc 20/06/2022a : Add test compare ticket
public class Ticket {
  private int id;
  private ParkingSpot parkingSpot;
  private String vehicleRegNumber;
  private double price;
  private Date inTime;
  private Date outTime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ParkingSpot getParkingSpot() {
    return parkingSpot;
  }

  public void setParkingSpot(ParkingSpot parkingSpot) {
    this.parkingSpot = parkingSpot;
  }

  public String getVehicleRegNumber() {
    return vehicleRegNumber;
  }

  public void setVehicleRegNumber(String vehicleRegNumber) {
    this.vehicleRegNumber = vehicleRegNumber;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Date getInTime() {
    return inTime;
  }

  public void setInTime(Date inTime) {
    this.inTime = inTime;
  }

  public Date getOutTime() {
    return outTime;
  }

  public void setOutTime(Date outTime) {
    this.outTime = outTime;
  }

  // => mc 20/06/2022a : Add test compare ticket
  public boolean compare(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ticket that = (Ticket) o;
    if (this.id != that.getId()) {
      return false;
    }
    if (!this.parkingSpot.equals(that.getParkingSpot())) {
      return false;
    }
    if (this.price != that.getPrice()) {
      return false;
    }

    if ((this.vehicleRegNumber == null) ^ (that.getVehicleRegNumber() == null)) {
      return false;
    }
    if (this.vehicleRegNumber != null
        && !this.vehicleRegNumber.equals(that.getVehicleRegNumber())) {
      return false;
    }

    // We test not test milliseconds
    if ((this.inTime == null) ^ (that.getInTime() == null)) {
      return false;
    }
    if (this.inTime != null && !ConversionDAO.dateToTimestampWithoutMilliseconds(this.inTime)
        .equals(ConversionDAO.dateToTimestampWithoutMilliseconds(that.getInTime()))) {
      return false;
    }

    // We test not test milliseconds
    if ((this.outTime == null) ^ (that.getOutTime() == null)) {
      return false;
    }
    if (this.outTime != null && !ConversionDAO.dateToTimestampWithoutMilliseconds(this.outTime)
        .equals(ConversionDAO.dateToTimestampWithoutMilliseconds(that.getOutTime()))) {
      return false;
    }

    return true;
  }
  // =< mc 20/06/2022a
}
