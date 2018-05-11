package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore prim;
  private TorpedoStore sec;

  @Before
  public void init()
  {
    prim = mock(TorpedoStore.class);
    sec = mock(TorpedoStore.class);
    this.ship = new GT4500(prim, sec);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(prim.isEmpty()).thenReturn(false);
    when(sec.isEmpty()).thenReturn(false);
    when(prim.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(prim, times(1)).isEmpty();
    verify(sec, times(0)).isEmpty();
    verify(prim, times(1)).fire(1);
    verify(sec, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_Only_prim(){
    // Arrange
    when(prim.isEmpty()).thenReturn(false);
    when(sec.isEmpty()).thenReturn(true);
    when(prim.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(prim, times(1)).isEmpty();
    verify(sec, times(0)).isEmpty();
    verify(prim, times(1)).fire(1);
    verify(sec, times(0)).fire(1);

  }

  @Test
  public void fireTorpedo_Single_Success_Only_second(){
    // Arrange
    when(prim.isEmpty()).thenReturn(true);
    when(sec.isEmpty()).thenReturn(false);
    when(prim.fire(1)).thenReturn(false);
    when(sec.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(prim, times(1)).isEmpty();
    verify(sec, times(1)).isEmpty();
    verify(prim, times(0)).fire(1);
    verify(sec, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(prim.isEmpty()).thenReturn(false);
    when(sec.isEmpty()).thenReturn(false);
    when(prim.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(prim, times(0)).fire(1);
    verify(sec, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success_Only_prim(){
    // Arrange
    when(prim.isEmpty()).thenReturn(false);
    when(sec.isEmpty()).thenReturn(true);
    when(prim.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(prim, times(1)).fire(1);
    verify(sec, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_fire_twice_first_fire(){
    // Arrange
    when(prim.isEmpty()).thenReturn(false);
    when(sec.isEmpty()).thenReturn(false);
    when(prim.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);;

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_fire_twice_second_fire(){
    // Arrange
    when(prim.isEmpty()).thenReturn(false);
    when(sec.isEmpty()).thenReturn(true);
    when(prim.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);;

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success_Only_sec(){
    // Arrange
    when(prim.isEmpty()).thenReturn(true);
    when(sec.isEmpty()).thenReturn(false);
    when(prim.fire(1)).thenReturn(false);
    when(sec.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

}
