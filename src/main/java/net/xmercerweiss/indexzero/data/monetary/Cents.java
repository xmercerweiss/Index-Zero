package net.xmercerweiss.indexzero.data.monetary;


/**
 * An arbitrary signed number of U.S. Cents
 * @version v0.1 2025-10-23
 * @author Xavier Mercerweiss
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
  /**
   * Constructs an object representing the given number of U.S. Cents
   * @param cents A signed 64-bit integer
   */
  public Cents(long cents)
  {
    CENTS = cents;
  }

  // Override Methods
  /**
   * Indicates whether the given object is of type {@code Cents},
   * and if so, whether its equal to this object
   * @param obj An object reference
   * @return A boolean value
   */
  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof Cents that)
      return this.compareTo(that) == 0;
    return false;
  }

  /**
   * Compares the value of this {@code Cents} object with another
   * @param that A {@code Cents} object
   * @return 1 if this > that, -1 if this < that, 0 otherwise
   */
  @Override
  public int compareTo(Cents that)
  {
    if (this.value() == that.value())
      return 0;
    return this.value() > that.value() ? 1 : -1;
  }

  /**
   * Returns the string representation of this {@code Cents} object
   * @return A {@code String}
   */
  @Override
  public String toString()
  {
    return this.asCents();
  }

  // Public Methods
  /**
   * Returns the representation of this {@code Cents} object in U.S. Cents
   * @return A {@code String} in the form {@code ¢%,d}
   */
  public String asCents()
  {
    String repr = CENT_FMT.formatted(Math.abs(CENTS));
    String fmt = isNegative() ? NEG_FMT : POS_FMT;
    return fmt.formatted(repr);
  }

  /**
   * Returns the representation of this {@code Cents} object in U.S. Dollars
   * @return A {@code String} in the form {@code $%,d.%02d}
   */
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

  /**
   * Returns the number of U.S. Cents this object represents
   * @return A signed 64-bit integer
   */
  public long value()
  {
    return CENTS;
  }

  /**
   * Indicates whether this object represents a negative number of U.S. Cents
   * @return A boolean value
   */
  public boolean isNegative()
  {
    return value() < 0;
  }

  /**
   * Indicates whether this object represents exactly zero U.S. Cents
   * @return A boolean value
   */
  public boolean isZero()
  {
    return value() == 0;
  }

  /**
   * Indicates whether this object represents a positive number of U.S. Cents
   * @return A boolean value
   */
  public boolean isPositive()
  {
    return value() > 0;
  }

  /**
   * Constructs a new {@code Cents} object of this object's value plus {@code n}
   * @param n A signed 64-bit integer
   * @return A new {@code Cents} object
   */
  public Cents plus(long n)
  {
    return new Cents(value() + n);
  }

  /**
   * Constructs a new {@code Cents} object of this object's value plus the given number of Cents
   * @param other A {@code Cents} object
   * @return A new {@code Cents} object
   */
  public Cents plus(Cents other)
  {
    return plus(other.value());
  }

  /**
   * Constructs a new {@code Cents} object of this object's value minus {@code n}
   * @param n A signed 64-bit integer
   * @return A new {@code Cents} object
   */
  public Cents minus(long n)
  {
    return new Cents(value() - n);
  }

  /**
   * Constructs a new {@code Cents} object of this object's value minus the given number of Cents
   * @param other A {@code Cents} object
   * @return A new {@code Cents} object
   */
  public Cents minus(Cents other)
  {
    return minus(other.value());
  }

  /**
   * Constructs a new {@code Cents} object of this object's value times {@code n}
   * @param n A signed 64-bit integer
   * @return A new {@code Cents} object
   */
  public Cents times(long n)
  {
    return new Cents(value() * n);
  }

  /**
   * Constructs a new {@code Cents} object of this object's value
   * divided by {@code n} and rounded down
   * @param n A signed 64-bit integer
   * @return A new {@code Cents} object
   */
  public Cents dividedBy(long n)
  {
    return new Cents(value() / n);
  }
}
