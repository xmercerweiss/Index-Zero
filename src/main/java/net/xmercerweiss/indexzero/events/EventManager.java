package net.xmercerweiss.indexzero.events;

import java.util.LinkedList;


public class EventManager
{
  private static EventManager instance = null;

  private static final LinkedList<EventListener> SUBSCRIBERS =
    new LinkedList<>();

  public static EventManager getInstance()
  {
    if (instance == null)
      instance = new EventManager();
    return instance;
  }

  private EventManager()
  {
    instance = this;
  }

  public void addSubscriber(EventListener listener)
  {
    SUBSCRIBERS.add(listener);
  }

  public void publish(Event e)
  {
    for (EventListener subscriber : SUBSCRIBERS)
      subscriber.listen(e);
  }
}
