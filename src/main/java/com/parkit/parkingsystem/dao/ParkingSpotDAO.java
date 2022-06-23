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
  private static final Logger logger = LogManager.getLogger("ParkingSpotDAO");

  public DataBaseConfig dataBaseConfig = new DataBaseConfig();

  public int getNextAvailableSlot(ParkingType parkingType) {
    int result = -1;
    try (Connection con = dataBaseConfig.getConnection()) {
      try (PreparedStatement ps = con.prepareStatement(DBConstants.GET_NEXT_PARKING_SPOT)) {
        ps.setString(1, parkingType.toString());
        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            result = rs.getInt(1);
          }
        }
      }
    } catch (Exception ex) {
      logger.error("Error fetching next available slot", ex);
    }
    return result;
  }

  public boolean updateParking(ParkingSpot parkingSpot) {
    // update the availability fo that parking slot
    try (Connection con = dataBaseConfig.getConnection()) {
      try (PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_PARKING_SPOT)) {
        ps.setBoolean(1, parkingSpot.isAvailable());
        ps.setInt(2, parkingSpot.getId());
        int updateRowCount = ps.executeUpdate();
        return (updateRowCount == 1);
      }
    } catch (Exception ex) {
      logger.error("Error updating parking info", ex);
      return false;
    }
  }

  // => mc 20/06/2022b : Fix integration tests (storie)
  public ParkingSpot getParkingSpot(int parkingNumber) {
    ParkingSpot parkingSpot = null;
    try (Connection con = dataBaseConfig.getConnection()) {
      try (PreparedStatement ps = con.prepareStatement(DBConstants.GET_PARKING_SPOT)) {
        // PARKING_NUMBER, AVAILABLE, TYPE
        ps.setInt(1, parkingNumber);
        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            parkingSpot = new ParkingSpot(rs.getInt(1), ParkingType.valueOf(rs.getString(3)),
                rs.getBoolean(2));
          }
        }
      }
    } catch (Exception ex) {
      logger.error("Error fetching next available slot", ex);
    }
    return parkingSpot;
  }
  // =< mc 20/06/2022b

}
