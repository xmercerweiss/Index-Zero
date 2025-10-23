package net.xmercerweiss.indexzero.tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import net.xmercerweiss.indexzero.data.monetary.Cents;


public class CentsTest
{
  private static final long[] LONG_INPUTS = {
          0,
          1,
         25,
       1_00,
      10_00,
     500_00,
    -500_00
  };

  private static final double[][] DOUBLE_DATA = {
    { 0,            0},
    { 0.01,         1},
    { 0.012,        1},
    { 0.015,        1},
    { 0.019,        1},
    { 0.05,         5},
    { 0.059,        5},
    { 0.25,        25},
    { 1.00,       100},
    { 1.001,      100},
    { 1.01,       101},
    { 1.25,       125},
    { 1.5,        150},
    {11.1111111, 1111},
    {50.0,       5000},
    {50.001,     5000},
    {50.01,      5001},
    {50.1,       5010},
    {51.0,       5100},
  };

  @Test
  void longToCents_equals_Long()
  {
    for (long input : LONG_INPUTS)
    {
      Cents cents = new Cents(input);
      // Input matches expected value
      assertEquals(input, cents.value());
    }
  }

  @Test
  void doubleToCents_equals_doubleToLong()
  {
    for (double[] datum : DOUBLE_DATA)
    {
      double input = datum[0];
      long expected = (long) datum[1];
      Cents cents = new Cents(input);
      assertEquals(expected, cents.value());
    }
  }
}
