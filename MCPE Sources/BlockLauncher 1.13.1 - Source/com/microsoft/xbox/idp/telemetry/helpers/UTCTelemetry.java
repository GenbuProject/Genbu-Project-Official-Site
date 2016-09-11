package com.microsoft.xbox.idp.telemetry.helpers;

import Microsoft.Telemetry.Base;
import com.microsoft.cll.android.AndroidCll;
import com.microsoft.xbox.idp.interop.Interop;
import com.microsoft.xbox.idp.ui.ErrorActivity.ErrorScreen;

public class UTCTelemetry
{
  public static final String UNKNOWNPAGE = "Unknown";
  
  public static void LogEvent(Base paramBase)
  {
    try
    {
      Interop.getCll().log(paramBase);
      return;
    }
    catch (NullPointerException paramBase)
    {
      UTCLog.log("CLL not initialized.  Is null", new Object[0]);
    }
  }
  
  public static String getErrorScreen(ErrorActivity.ErrorScreen paramErrorScreen)
  {
    switch (paramErrorScreen)
    {
    default: 
      return String.format("%sErrorScreen", new Object[] { "Unknown" });
    case ???: 
      return "Banned error view";
    case ???: 
      return "Generic error view";
    case ???: 
      return "Create error view";
    }
    return "Offline error view";
  }
  
  public static enum CallBackSources
  {
    Account,  Ticket;
    
    private CallBackSources() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\telemetry\helpers\UTCTelemetry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */