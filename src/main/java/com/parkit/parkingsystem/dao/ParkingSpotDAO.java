package com.parkit.parkingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;

// mc 20/06/2022b : Fix integration tests (storie)
public class ParkingSpotDAO {
  private static final Logger LOGGER = LogManager.getLogger("ParkingSpotDAO");

  public DataBaseConfig dataBaseConfig = new DataBaseConfig();

  public int getNextAvailableSlot(ParkingType parkingType) {
    Connection con = null;
    int result = -1;
    try {
      con = dataBaseConfig.getConnection();
      PreparedStatement ps = con.prepareStatement(DBConstants.GET_NEXT_PARKING_SPOT);
      ps.setString(1, parkingType.toString());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        result = rs.getInt(1);
      }
      dataBaseConfig.closeResultSet(rs);
      dataBaseConfig.closePreparedStatement(ps);
    } catch (Exception ex) {
      LOGGER.error("Error fetching next available slot", ex);
    } finally {
      dataBaseConfig.closeConnection(con);
    }
    return result;
  }

  public boolean updateParking(ParkingSpot parkingSpot) {
    // update the availability fo that parking slot
    Connection con = null;
    try {
      con = dataBaseConfig.getConnection();
      PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_PARKING_SPOT);
      ps.setBoolean(1, parkingSpot.isAvailable());
      ps.setInt(2, parkingSpot.getId());
      int updateRowCount = ps.executeUpdate();
      dataBaseConfig.closePreparedStatement(ps);
      return (updateRowCount == 1);
    } catch (Exception ex) {
      LOGGER.error("Error updating parking info", ex);
      return false;
    } finally {
      dataBaseConfig.closeConnection(con);
    }
  }

  // => mc 20/06/2022b : Fix integration tests (storie)
  public ParkingSpot getParkingSpot(int parkingNumber) {
    ParkingSpot parkingSpot = null;
    Connection con = null;
    try {
      con = dataBaseConfig.getConnection();
      PreparedStatement ps = con.prepareStatement(DBConstants.GET_PARKING_SPOT);
      // PARKING_NUMBER, AVAILABLE, TYPE
      ps.setInt(1, parkingNumber);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        parkingSpot =
            new ParkingSpot(rs.getInt(1), ParkingType.valueOf(rs.getString(3)), rs.getBoolean(2));
      }
      dataBaseConfig.closeResultSet(rs);
      dataBaseConfig.closePreparedStatement(ps);
    } catch (Exception ex) {
      LOGGER.error("Error fetching next available slot", ex);
    } finally {
      dataBaseConfig.closeConnection(con);
    }
    return parkingSpot;
  }
  // =< mc 20/06/2022b

}
