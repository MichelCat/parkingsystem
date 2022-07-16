package com.parkit.parkingsystem.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class InputReaderUtilTest {

  private static InputReaderUtil inputReaderUtil;
  private static Scanner scanner;

  static void setFinalStatic(Field field, Object newValue) throws Exception {
    field.setAccessible(true);
    Field modifiersField = Field.class.getDeclaredField("modifiers");
    modifiersField.setAccessible(true);
    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    field.set(null, newValue);
  }

  @BeforeEach
  private void setUpPerTest() throws Exception {
    inputReaderUtil = new InputReaderUtil();
    scanner = mock(Scanner.class);
    setFinalStatic(InputReaderUtil.class.getDeclaredField("scan"), scanner);
  }

  // -----------------------------------------------------------------------------------------------
  // Method readSelection
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read integer 4")
  public void readSelection_readFour_returnFour() {
    // GIVEN
    when(scanner.nextLine()).thenReturn("4");
    // WHEN
    final int result = inputReaderUtil.readSelection();
    // THEN
    assertThat(result).isEqualTo(4);
  }

  @Test
  @DisplayName("Exception on read empty string")
  public void readSelection_throwdExceptionWithEmptyString() {
    // GIVEN
    when(scanner.nextLine()).thenReturn("");
    // WHEN
    final int result = inputReaderUtil.readSelection();
    // THEN
    assertThat(result).isEqualTo(-1);
  }


  // -----------------------------------------------------------------------------------------------
  // Method readVehicleRegistrationNumber
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read string ABCDEF")
  public void readVehicleRegistrationNumber_readString_returnString() throws Exception {
    // GIVEN
    when(scanner.nextLine()).thenReturn("ABCDEF");
    // WHEN
    final String result = inputReaderUtil.readVehicleRegistrationNumber();
    // THEN
    assertThat(result).isEqualTo("ABCDEF");
  }

  @Test
  @DisplayName("Exception on read null string")
  public void readVehicleRegistrationNumber_throwdExceptionWithNull() throws Exception {
    // GIVEN
    when(scanner.nextLine()).thenReturn(null);
    // WHEN
    // THEN
    assertThrows(Exception.class, () -> inputReaderUtil.readVehicleRegistrationNumber());
  }

  @Test
  @DisplayName("Exception on read empty string")
  public void readVehicleRegistrationNumber_throwExceptionWithEmptyString() throws Exception {
    // GIVEN
    when(scanner.nextLine()).thenReturn("");
    // WHEN
    // THEN
    assertThrows(Exception.class, () -> inputReaderUtil.readVehicleRegistrationNumber());
  }

}
