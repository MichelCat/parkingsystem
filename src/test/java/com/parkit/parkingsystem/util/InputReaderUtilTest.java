package com.parkit.parkingsystem.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
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

  // ----------------------------------------------------------------------------------------------------
  // Method readSelection
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void readSelection_shouldReturnInt() {
    // GIVEN
    when(scanner.nextLine()).thenReturn("4");
    // WHEN
    final int result = inputReaderUtil.readSelection();
    // THEN
    assertThat(result).isEqualTo(4);
  }

  @Test
  public void readSelection_shoulThrowdExceptionWithEmptyString() {
    // GIVEN
    when(scanner.nextLine()).thenReturn("");
    // WHEN
    final int result = inputReaderUtil.readSelection();
    // THEN
    assertThat(result).isEqualTo(-1);
  }


  // ----------------------------------------------------------------------------------------------------
  // Method readVehicleRegistrationNumber
  // ----------------------------------------------------------------------------------------------------
  @Test
  public void readVehicleRegistrationNumber_shouldReturnString() throws Exception {
    // GIVEN
    when(scanner.nextLine()).thenReturn("ABCDEF");
    // WHEN
    final String result = inputReaderUtil.readVehicleRegistrationNumber();
    // THEN
    assertThat(result).isEqualTo("ABCDEF");
  }

  @Test
  public void readVehicleRegistrationNumber_shoulThrowdExceptionWithNull() throws Exception {
    // GIVEN
    when(scanner.nextLine()).thenReturn(null);
    // WHEN
    // THEN
    assertThrows(Exception.class, () -> inputReaderUtil.readVehicleRegistrationNumber());
  }

  @Test
  public void readVehicleRegistrationNumber_shouldThrowExceptionWithEmptyString() throws Exception {
    // GIVEN
    when(scanner.nextLine()).thenReturn("");
    // WHEN
    // THEN
    assertThrows(Exception.class, () -> inputReaderUtil.readVehicleRegistrationNumber());
  }

}
