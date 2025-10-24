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

  @Test
  void longToCents_equals_long()
  {
    for (long input : LONG_INPUTS)
    {
      Cents cents = new Cents(input);
      // Input is also expected value
      assertEquals(input, cents.value());
    }
  }
}
