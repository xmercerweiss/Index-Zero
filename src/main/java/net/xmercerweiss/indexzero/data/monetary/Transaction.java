package net.xmercerweiss.indexzero.data.monetary;

public class Transaction
{
  private final Cents AMOUNT;

  public Transaction(Cents cents)
  {
    AMOUNT = cents;
  }

  public Transaction(long cents)
  {
    AMOUNT = new Cents(cents);
  }
}
