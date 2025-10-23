package net.xmercerweiss.indexzero.events;

import java.util.LinkedList;
import java.util.HashMap;


public class EventManager
{
  private static final HashMap<Event,LinkedList<EventListener>>
    SUBSCRIBERS = new HashMap<>();

  static {
    for (Event e : Event.values())
      SUBSCRIBERS.put(e, new LinkedList<>());
  }

  public static void addSubscriber(EventListener listener, Event e)
  {
    SUBSCRIBERS.get(e)
      .add(listener);
  }

  public static void publish(Event e)
  {
    for (EventListener subscriber : SUBSCRIBERS.get(e))
      subscriber.signal(e);
  }

  public static void launchApp()
  {
    publish(Event.APP_LAUNCH);
  }

  public static void closeApp()
  {
    publish(Event.APP_CLOSE);
  }
}
