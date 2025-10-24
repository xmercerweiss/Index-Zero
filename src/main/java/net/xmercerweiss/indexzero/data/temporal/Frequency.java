package net.xmercerweiss.indexzero.data.temporal;

import java.time.*;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

import net.xmercerweiss.indexzero.util.*;


public class Frequency
{
  private static final String INV_WINDOW_ERR_MSG =
    "Cannot count occurrences within infinite range (start=ALWAYS or end=FOREVER)";

  private final DateRange LIFESPAN;
  private final CyclicIterator<Period> PERIODS;

  public Frequency(DateRange lifespan, Period... periods)
  {
    LIFESPAN = lifespan;
    PERIODS = new CyclicIterator<>(
      Arrays.asList(periods)
    );
  }

  public int timesOccurringWithin(DateRange range)
  {
    DateRange window = DateRange.intersectionOf(LIFESPAN, range);
    validateRangeOfOccurrences(window);
    LocalDate date = firstDayWithin(window);
    int out = 0;
    while (date != null && window.contains(date))
    {
      out++;
      date = date.plus(PERIODS.next());
    }
    return out;
  }

  public List<LocalDate> occurrencesWithin(DateRange range)
  {
    DateRange window = DateRange.intersectionOf(LIFESPAN, range);
    validateRangeOfOccurrences(window);
    LocalDate date = firstDayWithin(window);
    LinkedList<LocalDate> out = new LinkedList<>();
    while (date != null && window.contains(date))
    {
      out.add(date);
      date = date.plus(PERIODS.next());
    }
    return List.copyOf(out);
  }

  public LocalDate firstDayWithin(DateRange range)
  {
    LocalDate date = LIFESPAN.startDate();
    PERIODS.reset();
    while (range.isAfter(date))
    {
      date = date.plus(PERIODS.next());
      if (range.contains(date))
        return date;
    }
    return null;
  }

  private void validateRangeOfOccurrences(DateRange range)
  {
    if (range.startDate() == DateRange.ALWAYS
      || range.endDate() == DateRange.FOREVER)
      throw new IllegalArgumentException(INV_WINDOW_ERR_MSG);
  }
}
