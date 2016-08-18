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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\XLEThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */