package com.parkit.parkingsystem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataBaseConfig {

  private static final Logger logger = LogManager.getLogger("DataBaseConfig");

  private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/prod";
  private static final String DB_LOGIN = "root";
  private static final String DB_PASSWORD = "MySqlOracle1.";

  public Connection getConnection() throws ClassNotFoundException, SQLException {
    logger.info("Create DB connection");
    Class.forName(DB_DRIVER);
    return DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
  }

  public void closeConnection(Connection con) {
    if (con != null) {
      try {
        con.close();
        logger.info("Closing DB connection");
      } catch (SQLException e) {
        logger.error("Error while closing connection", e);
      }
    }
  }

  public void closePreparedStatement(PreparedStatement ps) {
    if (ps != null) {
      try {
        ps.close();
        logger.info("Closing Prepared Statement");
      } catch (SQLException e) {
        logger.error("Error while closing prepared statement", e);
      }
    }
  }

  public void closeResultSet(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
        logger.info("Closing Result Set");
      } catch (SQLException e) {
        logger.error("Error while closing result set", e);
      }
    }
  }
}
