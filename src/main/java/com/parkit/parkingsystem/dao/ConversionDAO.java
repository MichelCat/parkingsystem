package com.parkit.parkingsystem.dao;

import java.sql.Timestamp;

public class ConversionDAO {

  public static Timestamp dateToTimestampWithoutMilliseconds(java.util.Date sourceDate) {
    return ((sourceDate == null) ? null : new Timestamp((sourceDate.getTime() / 1000) * 1000));
  }

}
