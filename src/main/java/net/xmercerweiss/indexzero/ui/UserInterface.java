package net.xmercerweiss.indexzero.ui;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;
import com.googlecode.lanterna.gui2.*;

import net.xmercerweiss.indexzero.events.*;
import net.xmercerweiss.indexzero.ui.windows.*;


public class UserInterface extends EventListener
{
  private final WindowBasedTextGUI GUI;
  private final Screen SCREEN;

  private Window mainMenu;

  public UserInterface()
  {
    GUI = initGUI();
    SCREEN = GUI.getScreen();
  }

  private WindowBasedTextGUI initGUI()
  {
    WindowBasedTextGUI out;
    DefaultTerminalFactory termFactory = new DefaultTerminalFactory(
      System.out,
      System.in,
      StandardCharsets.UTF_8
    );
    try
    {
      Screen screen = termFactory.createScreen();
      out = new MultiWindowTextGUI(screen);
    }
    catch (Exception exc)
    {
      out = null;
    }
    return out;
  }

  @Override
  public void signal(Event e)
  {
    try
    {
      switch (e)
      {
        case APP_LAUNCH: launch(); break;
        case APP_CLOSE: close(); break;
      }
    }
    catch (IOException ioe)
    {
      throw new RuntimeException(ioe);
    }
  }

  private void launch()
    throws IOException
  {
    if (GUI == null)
     return;
    SCREEN.startScreen();
    mainMenu = new MainMenuWindow(
      SCREEN.getTerminalSize()
        .withRelative(-6, -5)
    );
    GUI.addWindowAndWait(mainMenu);
  }

  private void close()
    throws IOException
  {
    SCREEN.stopScreen();
    System.exit(0);
  }
}
