package net.xmercerweiss.indexzero.events;


public abstract class EventListener
{
  abstract public void signal(Event e);

  public void subscribe(Event... events)
  {
    for (Event e : events)
      EventManager.addSubscriber(this, e);
  }

}
