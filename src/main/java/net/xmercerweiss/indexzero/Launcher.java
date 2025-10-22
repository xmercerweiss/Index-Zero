package net.xmercerweiss.indexzero;

import net.xmercerweiss.indexzero.ui.UserInterface;
import net.xmercerweiss.indexzero.events.*;


public class Launcher
{
  public static void main(String[] args)
  {
    UserInterface ui = new UserInterface();
    EventManager manager = EventManager.getInstance();
    manager.addSubscriber(ui);
    manager.publish(Event.LAUNCH_APP);
  }
}
