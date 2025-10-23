package net.xmercerweiss.indexzero.ui.windows;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;


public abstract class AbstractWindow extends BasicWindow
{
  protected static final String TITLE = "Main Menu";

  protected final Panel CONTENT;

  public AbstractWindow(TerminalSize size)
  {
    super(TITLE);
    CONTENT = initContentPanel();
    initContent();
    setComponent(CONTENT);
    if (size != null)
      setFixedSize(size);
  }

  private Panel initContentPanel()
  {
    Panel panel = new Panel();
    panel.setLayoutManager(
      new LinearLayout(Direction.VERTICAL)
    );
    return panel;
  }

  abstract protected void initContent();
}
