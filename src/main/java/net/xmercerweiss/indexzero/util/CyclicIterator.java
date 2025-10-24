package net.xmercerweiss.indexzero.util;

import java.util.Iterator;

public class CyclicIterator<T> implements Iterator<T>
{
  private static final String INV_ITER_ERR_MSG =
    "Cannot build CycleIterator using empty Iterable";

  private final Iterable<T> ITER;
  private Iterator<T> cycle;

  public CyclicIterator(Iterable<T> iterable)
  {
    ITER = iterable;
    cycle = ITER.iterator();
    if (!cycle.hasNext())
      throw new IllegalArgumentException(INV_ITER_ERR_MSG);
  }

  @Override
  public boolean hasNext()
  {
    return true;
  }

  @Override
  public T next()
  {
    if (!cycle.hasNext())
      reset();
    return cycle.next();
  }

  public void reset()
  {
    cycle = ITER.iterator();
  }
}
