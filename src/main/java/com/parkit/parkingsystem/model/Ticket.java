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
    if (this.vehicleRegNumber != that.getVehicleRegNumber())
      return false;
    if (this.price != that.getPrice())
      return false;

    // We test not test milliseconds
    if ((this.getInTime() == null) && (that.getInTime() == null))
      return true;
    if ((this.getInTime() == null) || (that.getInTime() == null))
      return false;
    if (this.getInTime().getTime() / 1000 != that.getInTime().getTime() / 1000)
      return false;

    // We test not test milliseconds
    if ((this.getOutTime() == null) && (that.getOutTime() == null))
      return true;
    if ((this.getOutTime() == null) || (that.getOutTime() == null))
      return false;
    if (this.getOutTime().getTime() / 1000 != that.getOutTime().getTime() / 1000)
      return false;
    return true;
  }
  // =< mc 20/06/2022a
}
