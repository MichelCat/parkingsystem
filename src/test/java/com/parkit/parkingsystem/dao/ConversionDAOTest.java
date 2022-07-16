package com.parkit.parkingsystem.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConversionDAOTest {

  private static ConversionDAO conversionDAO;

  @BeforeEach
  private void setUpPerTest() {
    conversionDAO = new ConversionDAO();
  }


  // -----------------------------------------------------------------------------------------------
  // Method dateToTimestampWithoutMilliseconds
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Null date to timestamp converter, return null")
  public void dateToTimestampWithoutMilliseconds_nullDate_returnNull() {
    // GIVEN
    // WHEN
    final Timestamp result = conversionDAO.dateToTimestampWithoutMilliseconds(null);
    // THEN
    assertThat(result).isNull();;
  }

  @Test
  @DisplayName("Date to Timestamp converter, return Timestamp")
  public void dateToTimestampWithoutMilliseconds_date_returnTimestamp() throws Exception {
    // GIVEN
    String currentDate = "02/06/2010 12:35:45.123";
    java.util.Date sourceDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(currentDate);
    java.util.Date destinationDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(currentDate);
    // WHEN
    final Timestamp result = conversionDAO.dateToTimestampWithoutMilliseconds(sourceDate);
    // THEN
    assertThat(result).isEqualTo(new Timestamp(destinationDate.getTime()));
  }
}
