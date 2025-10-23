package net.xmercerweiss.indexzero;

import java.io.IOException;

import net.xmercerweiss.indexzero.events.*;
import net.xmercerweiss.indexzero.ui.UserInterface;


public class Launcher
{
  public static void main(String[] args)
  {
    UserInterface ui = new UserInterface();
    ui.subscribe(
      Event.APP_LAUNCH,
      Event.APP_CLOSE
    );
    EventManager.publish(Event.APP_LAUNCH);
  }
}
