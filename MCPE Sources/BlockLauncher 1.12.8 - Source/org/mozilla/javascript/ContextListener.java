package org.mozilla.javascript;

@Deprecated
public abstract interface ContextListener
  extends ContextFactory.Listener
{
  @Deprecated
  public abstract void contextEntered(Context paramContext);
  
  @Deprecated
  public abstract void contextExited(Context paramContext);
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ContextListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */