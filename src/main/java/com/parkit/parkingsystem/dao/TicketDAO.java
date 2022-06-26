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
import com.parkit.parkingsystem.model.Ticket;

// mc 20/06/2022d : 5%-discount for recurring users (storie)
public class TicketDAO {

  private static final Logger logger = LogManager.getLogger("TicketDAO");

  public DataBaseConfig dataBaseConfig = new DataBaseConfig();

  public boolean saveTicket(Ticket ticket) {
    try (Connection con = dataBaseConfig.getConnection()) {
      try (PreparedStatement ps = con.prepareStatement(DBConstants.SAVE_TICKET)) {
        // ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
        ps.setInt(1, ticket.getParkingSpot().getId());
        ps.setString(2, ticket.getVehicleRegNumber());
        ps.setDouble(3, ticket.getPrice());
        ps.setTimestamp(4, ConversionDAO.dateToTimestampWithoutMilliseconds(ticket.getInTime()));
        ps.setTimestamp(5, ConversionDAO.dateToTimestampWithoutMilliseconds(ticket.getOutTime()));
        return (ps.executeUpdate() == 1);
      }
    } catch (Exception ex) {
      logger.error("Error fetching next available slot", ex);
    }
    return false;
  }

  public Ticket getTicket(String vehicleRegNumber) {
    Ticket ticket = null;
    try (Connection con = dataBaseConfig.getConnection()) {
      try (PreparedStatement ps = con.prepareStatement(DBConstants.GET_TICKET)) {
        // ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
        ps.setString(1, vehicleRegNumber);
        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            ticket = new Ticket();
            ParkingSpot parkingSpot =
                new ParkingSpot(rs.getInt(1), ParkingType.valueOf(rs.getString(6)), false);
            ticket.setParkingSpot(parkingSpot);
            ticket.setId(rs.getInt(2));
            ticket.setVehicleRegNumber(vehicleRegNumber);
            ticket.setPrice(rs.getDouble(3));
            ticket.setInTime(rs.getTimestamp(4));
            ticket.setOutTime(rs.getTimestamp(5));
          }
        }
      }
    } catch (Exception ex) {
      logger.error("Error fetching next available slot", ex);
    }
    return ticket;
  }

  public boolean updateTicket(Ticket ticket) {
    try (Connection con = dataBaseConfig.getConnection()) {
      try (PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_TICKET)) {
        ps.setDouble(1, ticket.getPrice());
        ps.setTimestamp(2, ConversionDAO.dateToTimestampWithoutMilliseconds(ticket.getOutTime()));
        ps.setInt(3, ticket.getId());
        return (ps.executeUpdate() == 1);
      }
    } catch (Exception ex) {
      logger.error("Error saving ticket info", ex);
    }
    return false;
  }

  // => mc 20/06/2022d : 5%-discount for recurring users (storie)
  public int getVehicleRecurrenceNumberOutput(String vehicleRegNumber) {
    int numberOfRecurrence = 0;
    try (Connection con = dataBaseConfig.getConnection()) {
      try (PreparedStatement ps =
          con.prepareStatement(DBConstants.GET_TICKET_WITH_SAME_VEHICLE_NUMBER)) {
        ps.setString(1, vehicleRegNumber);
        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            numberOfRecurrence = rs.getInt(1);
          }
        }
      }
    } catch (Exception ex) {
      logger.error("Error while retrieving the vehicle recurrence number", ex);
    }
    return numberOfRecurrence;
  }
  // =< mc 20/06/2022d

}
