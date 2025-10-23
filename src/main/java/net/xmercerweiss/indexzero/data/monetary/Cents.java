package net.xmercerweiss.indexzero.data.monetary;


/**
 * An arbitrary signed number of U.S. Cents
 */
public class Cents implements Comparable<Cents>
{
  // Class Constants
  public static final long IN_DOLLAR = 100;

  public static final String CENT_SIGN = "¢";
  public static final String DOLLAR_SIGN = "$";

  private static final String CENT_FMT = CENT_SIGN + "%,d";
  private static final String DOLLAR_FMT = DOLLAR_SIGN + "%,d.%02d";
  private static final String NEG_FMT = "(%s)";
  private static final String POS_FMT = "%s";

  // Instance Fields
  private final long CENTS;

  // Constructors
  public Cents(long cents)
  {
    CENTS = cents;
  }

  public Cents(double dollars)
  {
    this((long) Math.floor(dollars * IN_DOLLAR));
  }

  // Override Methods
  @Override
  public boolean equals(Object o)
  {
    if (o instanceof Cents that)
      return this.compareTo(that) == 0;
    return false;
  }

  @Override
  public int compareTo(Cents that)
  {
    if (this.value() == that.value())
      return 0;
    return this.value() > that.value() ? 1 : -1;
  }

  @Override
  public String toString()
  {
    return this.asCents();
  }

  // Interface
  public String asCents()
  {
    String repr = CENT_FMT.formatted(Math.abs(CENTS));
    String fmt = isNegative() ? NEG_FMT : POS_FMT;
    return fmt.formatted(repr);
  }

  public String asDollars()
  {
    long total = Math.abs(CENTS);
    String repr = DOLLAR_FMT.formatted(
      total / IN_DOLLAR,
      total % IN_DOLLAR
    );
    String fmt = isNegative() ? NEG_FMT : POS_FMT;
    return fmt.formatted(repr);
  }

  public long value()
  {
    return CENTS;
  }

  public boolean isNegative()
  {
    return value() < 0;
  }

  public boolean isZero()
  {
    return value() == 0;
  }

  public boolean isPositive()
  {
    return value() > 0;
  }

  public Cents plus(long n)
  {
    return new Cents(value() + n);
  }

  public Cents plus(Cents other)
  {
    return plus(other.value());
  }

  public Cents minus(long n)
  {
    return new Cents(value() - n);
  }

  public Cents minus(Cents other)
  {
    return minus(other.value());
  }

  public Cents times(long n)
  {
    return new Cents(value() * n);
  }

  public Cents dividedBy(long n)
  {
    return new Cents(value() / n);
  }
}
