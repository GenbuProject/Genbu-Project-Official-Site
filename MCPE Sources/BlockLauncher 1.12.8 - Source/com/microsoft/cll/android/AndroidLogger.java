package com.microsoft.cll.android;

import android.util.Log;

public class AndroidLogger
  implements ILogger
{
  private static AndroidLogger INSTANCE;
  private static Object InstanceLock = new Object();
  private Verbosity verbosity;
  
  private AndroidLogger()
  {
    setVerbosity(Verbosity.NONE);
  }
  
  public static ILogger getInstance()
  {
    if (INSTANCE == null) {}
    synchronized (InstanceLock)
    {
      if (INSTANCE == null) {
        INSTANCE = new AndroidLogger();
      }
      return INSTANCE;
    }
  }
  
  public void error(String paramString1, String paramString2)
  {
    if ((this.verbosity == Verbosity.ERROR) || (this.verbosity == Verbosity.WARN) || (this.verbosity == Verbosity.INFO)) {
      Log.e(paramString1, paramString2);
    }
  }
  
  public void info(String paramString1, String paramString2)
  {
    if (this.verbosity == Verbosity.INFO) {
      Log.i(paramString1, paramString2);
    }
  }
  
  public void setVerbosity(Verbosity paramVerbosity)
  {
    this.verbosity = paramVerbosity;
  }
  
  public void warn(String paramString1, String paramString2)
  {
    if ((this.verbosity == Verbosity.WARN) || (this.verbosity == Verbosity.INFO)) {
      Log.d(paramString1, paramString2);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\cll\android\AndroidLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */