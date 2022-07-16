package com.parkit.parkingsystem.constants;

// mc 20/06/2022b : Fix integration tests (storie)
// mc 20/06/2022d : 5%-discount for recurring users (storie)
public class DBConstants {

  public static final String GET_NEXT_PARKING_SPOT =
      "select min(PARKING_NUMBER) from parking where AVAILABLE = true and TYPE = ?";
  public static final String UPDATE_PARKING_SPOT =
      "update parking set available = ? where PARKING_NUMBER = ?";
  // => mc 20/06/2022b : Fix integration tests (storie)
  public static final String GET_PARKING_SPOT =
      "select PARKING_NUMBER, AVAILABLE, TYPE from parking where PARKING_NUMBER = ?";
  // =< mc 20/06/2022b

  public static final String SAVE_TICKET =
      "insert into ticket(PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)"
          + " values(?,?,?,?,?)";
  public static final String UPDATE_TICKET = "update ticket set PRICE=?, OUT_TIME=? where ID=?";
  // => mc 20/06/2022b : Fix integration tests (storie)
  public static final String GET_TICKET =
      "select t.PARKING_NUMBER, t.ID, t.PRICE, t.IN_TIME, t.OUT_TIME, p.TYPE"
          + " from ticket t,parking p"
          + " where p.parking_number = t.parking_number and t.VEHICLE_REG_NUMBER=?"
          + " order by t.IN_TIME desc  limit 1";
  // =< mc 20/06/2022b
  // => mc 20/06/2022d : 5%-discount for recurring users (storie)
  public static final String GET_TICKET_WITH_SAME_VEHICLE_NUMBER =
      "select count(VEHICLE_REG_NUMBER) from ticket"
          + " where VEHICLE_REG_NUMBER=? and OUT_TIME is not null";
  // =< mc 20/06/2022d
}
