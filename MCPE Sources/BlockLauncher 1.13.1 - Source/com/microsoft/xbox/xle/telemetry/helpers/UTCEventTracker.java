package com.microsoft.xbox.xle.telemetry.helpers;

import com.microsoft.xbox.idp.telemetry.helpers.UTCLog;

public class UTCEventTracker
{
  public static String callStringTrackWrapper(UTCStringEventDelegate paramUTCStringEventDelegate)
  {
    try
    {
      paramUTCStringEventDelegate = paramUTCStringEventDelegate.call();
      return paramUTCStringEventDelegate;
    }
    catch (Exception paramUTCStringEventDelegate)
    {
      UTCLog.log(paramUTCStringEventDelegate.getMessage(), new Object[0]);
    }
    return null;
  }
  
  public static void callTrackWrapper(UTCEventDelegate paramUTCEventDelegate)
  {
    try
    {
      paramUTCEventDelegate.call();
      return;
    }
    catch (Exception paramUTCEventDelegate)
    {
      UTCLog.log(paramUTCEventDelegate.getMessage(), new Object[0]);
    }
  }
  
  public static abstract interface UTCEventDelegate
  {
    public abstract void call();
  }
  
  public static abstract interface UTCStringEventDelegate
  {
    public abstract String call();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\telemetry\helpers\UTCEventTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */