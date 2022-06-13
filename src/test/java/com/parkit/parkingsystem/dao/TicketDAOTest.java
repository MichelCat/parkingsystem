package com.parkit.parkingsystem.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

@ExtendWith(MockitoExtension.class)
public class TicketDAOTest {

  @Mock
  private static DataBaseConfig dataBaseConfig;

  @Mock
  private static Connection connection;

  @Mock
  private static PreparedStatement preparedStatement;

  private static TicketDAO ticketDAO;
  private static Ticket ticket;

  @BeforeEach
  private void setUpPerTest() {
    // dataBaseConfig = new DataBaseConfig();
    ticketDAO = new TicketDAO();

    ticket = new Ticket();
    ticket.setId(1);
    ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, true);
    ticket.setParkingSpot(parkingSpot);
    ticket.setVehicleRegNumber("ABCDEF");
    ticket.setPrice(0);
    ticket.setInTime(new Date(System.currentTimeMillis()));
    ticket.setOutTime(new Date(System.currentTimeMillis()));
  }


  // ----------------------------------------------------------------------------------------------------
  // Method saveTicket
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void saveTicket_saveTicket() throws Exception {
    // GIVEN
    when(dataBaseConfig.getConnection()).thenReturn(connection);
    when(connection.prepareStatement(DBConstants.SAVE_TICKET)).thenReturn(preparedStatement);
    when(preparedStatement.execute()).thenReturn(true);
    // WHEN
    final boolean result = ticketDAO.saveTicket(ticket);
    // THEN
    assertThat(result).isFalse();
  }


  // ----------------------------------------------------------------------------------------------------
  // Method getTicket
  // ----------------------------------------------------------------------------------------------------


  // ----------------------------------------------------------------------------------------------------
  // Method updateTicket
  // ----------------------------------------------------------------------------------------------------

}
