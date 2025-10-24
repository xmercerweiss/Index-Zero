package net.xmercerweiss.indexzero.ui.windows;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;

import net.xmercerweiss.indexzero.events.*;


public class MainMenuWindow extends AbstractWindow
{
  protected static final String TITLE = "Main Menu";

  public MainMenuWindow()
  {
    super(null);
  }

  public MainMenuWindow(TerminalSize size)
  {
    super(size);
  }

  @Override
  protected void initContent()
  {
    CONTENT.addComponent(
      new Label("This is the main menu of Index Zero.")
    );
    CONTENT.addComponent(
      new Button("Exit", EventManager::closeApp)
    );
  }
}
