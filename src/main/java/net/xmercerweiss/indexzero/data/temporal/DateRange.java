package net.xmercerweiss.indexzero.data.temporal;

import java.time.*;


/**
 * An interval between two arbitrary dates
 * @version v0.1 2025-10-23
 * @author Xavier Mercerweiss
 */
public class DateRange
{
  // Class Constants
  public static final LocalDate ALWAYS = LocalDate.MIN;
  public static final LocalDate FOREVER = LocalDate.MAX;

  private static final String INV_RANGE_ERR_MSG =
    "DateRange start date must come before end date";

  private static final String INV_INTERSECT_ERR_MSG =
    "DateRange cannot intersect ranges with no overlap";

  private static final String INV_DATE_ERR_MSG =
    "DateRange cannot be compared to null";

  // Class Methods
  /**
   * Constructs a new {@code DateRange} object consisting of a single day
   * @param date A LocalDate object
   * @return A new {@code DateRange} object
   */
  public static DateRange ofOneDay(LocalDate date)
  {
    return new DateRange(date, date.plusDays(1));
  }

  /**
   * Constructs a new {@code DateRange} object consisting of the
   * earlier starting date of the two given ranges, and the later ending date
   * @param a A {@code DateRange} object
   * @param b Another {@code DateRange} object
   * @return A new {@code DateRange} object
   */
  public static DateRange unionOf(DateRange a, DateRange b)
  {
    LocalDate earliestStart = a.START.isBefore(b.START) ? a.START : b.START;
    LocalDate latestEnd = a.END.isAfter(b.END) ? a.END : b.END;
    return new DateRange(earliestStart, latestEnd);
  }

  /**
   * Constructs a new {@code DateRange} object consisting of the
   * overlap between the two given ranges, if such an overlap exists;
   * an exception is thrown if the ranges do not overlap
   * @param a A {@code DateRange} object
   * @param b An overlapping {@code DateRange} object
   * @return A new {@code DateRange} object
   */
  public static DateRange intersectionOf(DateRange a, DateRange b)
  {
    LocalDate latestStart = a.START.isBefore(b.START) ? b.START : a.START;
    LocalDate earliestEnd = a.END.isAfter(b.END) ? b.END : a.END;
    if (!latestStart.isBefore(earliestEnd))
      throw new IllegalArgumentException(INV_INTERSECT_ERR_MSG);
    return new DateRange(latestStart, earliestEnd);
  }

  // Instance Fields
  private final LocalDate START;
  private final LocalDate END;
  private final Period LENGTH;

  // Constructors
  /**
   * Constructs a new {@code DateRange} object which covers every
   * possible date; the start is {@code ALWAYS} and the end is {@code FOREVER}
   */
  public DateRange()
  {
    this(null, null);
  }

  /**
   * Constructs a new {@code DateRange} object which covers the
   * interval {@code [start, FOREVER)}
   * @param start A {@code LocalDate} object
   */
  public DateRange(LocalDate start)
  {
    this(start, null);
  }

  /**
   * Constructs a new {@code DateRange} object which covers the
   * interval {@code [start, end)}
   * @param start A {@code LocalDate} object
   * @param end A {@code LocalDate} after {@code start}
   */
  public DateRange(LocalDate start, LocalDate end)
  {
    START = start == null ? ALWAYS : start;
    END = end == null ? FOREVER : end;
    if (!START.isBefore(END))
      throw new IllegalArgumentException(INV_RANGE_ERR_MSG);
    LENGTH = START.until(END);
  }

  // Public Methods

  /**
   * Returns the starting date of this {@code DateRange}
   * @return A {@code LocalDate} object
   */
  public LocalDate startDate()
  {
    return START;
  }

  /**
   * Returns the ending date of this {@code DateRange},
   * which is excluded from the range
   * @return A {@code LocalDate} object
   */
  public LocalDate endDate()
  {
    return END;
  }

  /**
   * Returns the length of this {@code DateRange}
   * @return A {@code Period} object
   */
  public Period length()
  {
    return LENGTH;
  }

  /**
   * Indicates whether this {@code DateRange} begins after the given date
   * @param date A {@code LocalDate} object
   * @return A boolean value
   */
  public boolean isAfter(LocalDate date)
  {
    validateDate(date);
    return date.isBefore(START);
  }

  /**
   * Indicates whether this {@code DateRange} contains the given date
   * @param date A {@code LocalDate} object
   * @return A boolean value
   */
  public boolean contains(LocalDate date)
  {
    validateDate(date);
    return !date.isBefore(START) && date.isBefore(END);
  }

  /**
   * Indicates whether this {@code DateRange} ends before the given date
   * @param date A {@code LocalDate} object
   * @return A boolean value
   */
  public boolean isBefore(LocalDate date)
  {
    validateDate(date);
    return !date.isBefore(END);
  }

  // Private Methods
  private void validateDate(LocalDate date)
  {
    if (date == null)
      throw new IllegalArgumentException(INV_DATE_ERR_MSG);
  }
}
