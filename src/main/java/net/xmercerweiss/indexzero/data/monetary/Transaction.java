package net.xmercerweiss.indexzero.data.monetary;

import net.xmercerweiss.indexzero.data.temporal.*;


/**
 * A repeated change of an amount of money
 */
public class Transaction
{
  private final Cents AMOUNT;
  private final Frequency FREQ;

  public Transaction(Cents c, Frequency f)
  {
    AMOUNT = c;
    FREQ = f;
  }

  // TODO Finish Transaction implementation
}
