package com.parkit.parkingsystem.model;

import java.util.Date;

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
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Ticket that = (Ticket) o;
    if (this.id != that.getId())
      return false;
    if (!this.parkingSpot.equals(that.getParkingSpot()))
      return false;
    if (this.price != that.getPrice())
      return false;

    if ((this.vehicleRegNumber == null) ^ (that.getVehicleRegNumber() == null))
      return false;
    if (this.vehicleRegNumber != null && that.getVehicleRegNumber() != null
        && !this.vehicleRegNumber.equals(that.getVehicleRegNumber()))
      return false;

    // We test not test milliseconds
    if ((this.inTime == null) ^ (that.getInTime() == null))
      return false;
    if (this.inTime != null && that.getInTime() != null
        && this.inTime.getTime() / 1000 != that.getInTime().getTime() / 1000)
      return false;

    // We test not test milliseconds
    if ((this.outTime == null) ^ (that.getOutTime() == null))
      return false;
    if (this.outTime != null && that.getOutTime() != null
        && this.outTime.getTime() / 1000 != that.getOutTime().getTime() / 1000)
      return false;
    return true;
  }
  // =< mc 20/06/2022a
}
