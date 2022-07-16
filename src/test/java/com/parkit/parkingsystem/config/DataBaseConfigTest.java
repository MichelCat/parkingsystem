package com.parkit.parkingsystem.config;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DataBaseConfigTest {

  @Mock
  private static DriverManager driverManager;

  @Mock
  private static Connection connection;

  @Mock
  private static PreparedStatement preparedStatement;

  @Mock
  private static ResultSet resultSet;

  private static DataBaseConfig dataBaseConfig;

  @BeforeEach
  private void setUpPerTest() {
    dataBaseConfig = new DataBaseConfig();
  }


  // -----------------------------------------------------------------------------------------------
  // Method getConnection
  // -----------------------------------------------------------------------------------------------


  // -----------------------------------------------------------------------------------------------
  // Method closeConnection
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Closing a database connection")
  public void closeConnection_shouldCloseConnection() throws SQLException {
    // GIVEN
    // WHEN
    dataBaseConfig.closeConnection(connection);
    // THEN
    verify(connection, Mockito.times(1)).close();
  }

  @Test
  @DisplayName("Closing null connection")
  public void closeConnection_closingNullConnection() throws SQLException {
    // GIVEN
    // WHEN
    dataBaseConfig.closeConnection(null);
    // THEN
    verify(connection, Mockito.times(0)).close();
  }

  @Test
  @DisplayName("SQLException error closing connection")
  public void closeConnection_throwSQLExceptionClosingConnection() throws SQLException {
    // GIVEN
    doThrow(new SQLException()).when(connection).close();
    // WHEN
    dataBaseConfig.closeConnection(connection);
    // THEN
    verify(connection, Mockito.times(1)).close();
  }


  // -----------------------------------------------------------------------------------------------
  // Method closePreparedStatement
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Closing PreparedStatement")
  public void closePreparedStatement_shouldClosePreparedStatement() throws SQLException {
    // GIVEN
    // WHEN
    dataBaseConfig.closePreparedStatement(preparedStatement);
    // THEN
    verify(preparedStatement, Mockito.times(1)).close();
  }

  @Test
  @DisplayName("Closing null PreparedStatement")
  public void closePreparedStatement_closingNullPreparedStatement() throws SQLException {
    // GIVEN
    // WHEN
    dataBaseConfig.closePreparedStatement(null);
    // THEN
    verify(preparedStatement, Mockito.times(0)).close();
  }

  @Test
  @DisplayName("SQLException error closing PreparedStatement")
  public void closePreparedStatement_throwSQLExceptionClosingPreparedStatement()
      throws SQLException {
    // GIVEN
    doThrow(new SQLException()).when(preparedStatement).close();
    // WHEN
    dataBaseConfig.closePreparedStatement(preparedStatement);
    // THEN
    verify(preparedStatement, Mockito.times(1)).close();
  }


  // -----------------------------------------------------------------------------------------------
  // Method closeResultSet
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Closing ResultSet")
  public void closeResultSet_shouldCloseResultSet() throws SQLException {
    // GIVEN
    // WHEN
    dataBaseConfig.closeResultSet(resultSet);
    // THEN
    verify(resultSet, Mockito.times(1)).close();
  }

  @Test
  @DisplayName("Closing null ResultSet")
  public void closeResultSet_closingdNullResultSet() throws SQLException {
    // GIVEN
    // WHEN
    dataBaseConfig.closeResultSet(null);
    // THEN
    verify(resultSet, Mockito.times(0)).close();
  }

  @Test
  @DisplayName("SQLException error closing ResultSet")
  public void closeResultSet_throwSQLExceptionClosingResultSet() throws SQLException {
    // GIVEN
    doThrow(new SQLException()).when(resultSet).close();
    // WHEN
    dataBaseConfig.closeResultSet(resultSet);
    // THEN
    verify(resultSet, Mockito.times(1)).close();
  }
}
