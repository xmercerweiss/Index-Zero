package net.xmercerweiss.indexzero.ui;

import net.xmercerweiss.indexzero.events.*;

public class UserInterface implements EventListener
{

  public UserInterface()
  {

  }

  @Override
  public void listen(Event e)
  {
    switch (e)
    {
      case LAUNCH_APP:
        System.out.print("Launched UI!");
        break;
    }
  }
}
