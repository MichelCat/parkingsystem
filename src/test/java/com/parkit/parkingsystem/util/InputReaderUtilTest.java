package com.parkit.parkingsystem.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class InputReaderUtilTest {

  private static InputReaderUtil inputReaderUtil;

  // private static Scanner scan = new Scanner(System.in);

  // @Rule
  // public final TextFromStandardInputStream systemInMock =
  // TextFromStandardInputStream.emptyStandardInputStream();

  @BeforeEach
  private void setUpPerTest() {
    inputReaderUtil = new InputReaderUtil();
  }


  // ----------------------------------------------------------------------------------------------------
  // Method readSelection
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void readSelection() throws Exception {
    // // GIVEN
    // when(scan.nextLine()).thenReturn("1");
    // // WHEN
    // final int result = inputReaderUtil.readSelection();
    // // THEN
    // verify(scan, times(1)).nextLine();
    // assertThat(result).isEqualTo(1);


    // Scanner mockScanner = mock(Scanner.class);
    // when(mockScanner.nextLine()).thenReturn("1");
    // final int result = inputReaderUtil.readSelection();
    // verify(mockScanner).nextLine();


    // systemInMock.provideLines("1");
    // final int result = inputReaderUtil.readSelection();
    // assertThat(result).isEqualTo(1);


    // InputStream systemIn = System.in;
    // try {
    // String userInput = String.format("1\r\n");
    // System.setIn(new ByteArrayInputStream(userInput.getBytes()));
    // final int result = inputReaderUtil.readSelection();
    // assertThat(result).isEqualTo(1);
    // System.setIn(systemIn);
    // } finally {
    // System.setIn(systemIn);
    // }
  }



  // ----------------------------------------------------------------------------------------------------
  // Method readVehicleRegistrationNumber
  // ----------------------------------------------------------------------------------------------------
  // @Test
  // public void readVehicleRegistrationNumber_shouldThrowException_ofVehicleRegNumber()
  // throws Exception {
  // // GIVEN
  // when(integer.parseInt(any(String.class))).thenReturn(integer).thenReturn("ABCDEF");
  // // WHEN
  // final String result = inputReaderUtil.readVehicleRegistrationNumber();
  // // THEN
  // verify(scan, times(1)).nextLine();
  // assertThat(result).isEqualTo("ABCDEF");
  // }



  // @Test
  // public void readVehicleRegistrationNumber_shouldThrowException_ofVehicleRegNumber()
  // throws Exception {
  // // GIVEN
  // when(scan.nextLine()).thenReturn(null);
  // // WHEN
  // // final String result = inputReaderUtil.readVehicleRegistrationNumber();
  // assertThatThrownBy(() -> {
  // inputReaderUtil.readVehicleRegistrationNumber();
  // }).hasMessage("Invalid input provided");
  //
  // // THEN
  // verify(scan, times(1)).nextLine();
  // }


}
