package com.microsoft.xbox.idp.telemetry.helpers;

import android.util.Log;

public class UTCLog
{
  static final String UTCLOGTAG = "UTCLOGGING";
  
  public static void log(String paramString, Object... paramVarArgs)
  {
    try
    {
      String str = Thread.currentThread().getStackTrace()[3].getMethodName();
      Log.d("UTCLOGGING", String.format(String.format("%s: ", new Object[] { str }) + paramString, paramVarArgs));
      return;
    }
    catch (Exception paramString)
    {
      if (paramString.getMessage().equals("Format specifier: s")) {
        Log.e("UTCLOGGING", paramString.getMessage());
      }
      Log.e("UTCLOGGING", paramString.getMessage());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\telemetry\helpers\UTCLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */