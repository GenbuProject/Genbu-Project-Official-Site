package com.microsoft.xbox.toolkit;

public class XLEThread
  extends Thread
{
  public XLEThread(Runnable paramRunnable, String paramString)
  {
    super(paramRunnable, paramString);
    setUncaughtExceptionHandler(XLEUnhandledExceptionHandler.Instance);
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\XLEThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */