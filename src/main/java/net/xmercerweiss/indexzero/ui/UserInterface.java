package net.xmercerweiss.indexzero.ui;

import net.xmercerweiss.indexzero.events.*;

import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.terminal.*;

import java.io.IOException;

public class UserInterface implements EventListener
{
  private WindowBasedTextGUI gui = null;

  public UserInterface()
  {
    try
    {
      Screen screen = new DefaultTerminalFactory().createScreen();
      gui = new MultiWindowTextGUI(screen);
      screen.startScreen();
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void listen(Event e)
  {
    switch (e)
    {
      case LAUNCH_APP:
        launch();
        break;
    }
  }

  private void launch()
  {
    if (gui != null)
    {
      MessageDialog window = new MessageDialogBuilder()
        .setTitle("Index Zero")
        .setText("Lets do some money-ing!")
        .build();
      gui.addWindow(window);
      try
      {
        gui.updateScreen();
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}
