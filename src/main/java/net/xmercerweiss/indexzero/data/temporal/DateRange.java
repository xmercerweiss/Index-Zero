package net.xmercerweiss.indexzero.data.temporal;

import java.time.*;


public class DateRange
{
  // Class Constants
  public static final LocalDate ALWAYS = LocalDate.MIN;
  public static final LocalDate FOREVER = LocalDate.MAX;

  private static final String INV_RANGE_ERR_MSG =
    "DateRange start date must come before end date; check arguments?";

  private static final String INV_INTERSECT_ERR_MSG =
    "DateRange cannot intersect ranges with no overlap; check arguments?";

  private static final String INV_DATE_ERR_MSG =
    "DateRange cannot be compared to null; check arguments?";

  // Class Methods
  public static DateRange ofOneDay(LocalDate date)
  {
    return new DateRange(date, date.plusDays(1));
  }

  public static DateRange unionOf(DateRange a, DateRange b)
  {
    LocalDate earliestStart = a.START.isBefore(b.START) ? a.START : b.START;
    LocalDate latestEnd = a.END.isAfter(b.END) ? a.END : b.END;
    return new DateRange(earliestStart, latestEnd);
  }

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
  public DateRange()
  {
    this(null, null);
  }

  public DateRange(LocalDate start)
  {
    this(start, null);
  }

  public DateRange(LocalDate start, LocalDate end)
  {
    START = start == null ? ALWAYS : start;
    END = end == null ? FOREVER : end;
    if (!START.isBefore(END))
      throw new IllegalArgumentException(INV_RANGE_ERR_MSG);
    LENGTH = START.until(END);
  }

  // Public Interface
  public LocalDate startDate()
  {
    return START;
  }

  public LocalDate endDate()
  {
    return END;
  }

  public Period length()
  {
    return LENGTH;
  }

  public boolean isAfter(LocalDate date)
  {
    if (date == null)
      throw new IllegalArgumentException(INV_DATE_ERR_MSG);
    return date.isBefore(START);
  }

  public boolean contains(LocalDate date)
  {
    if (date == null)
      throw new IllegalArgumentException(INV_DATE_ERR_MSG);
    return !date.isBefore(START) && date.isBefore(END);
  }

  public boolean isBefore(LocalDate date)
  {
    if (date == null)
      throw new IllegalArgumentException(INV_DATE_ERR_MSG);
    return !date.isBefore(END);
  }
}
