package com.microsoft.onlineid.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.System;

public class NetworkConnectivity
{
  private static NetworkInfo getActiveNetworkInfo(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
  }
  
  public static String getNetworkTypeDescription(Context paramContext)
  {
    paramContext = getActiveNetworkInfo(paramContext);
    if ((paramContext == null) || (!paramContext.isConnected())) {
      return "Not connected";
    }
    switch (paramContext.getType())
    {
    case 8: 
    default: 
      return "Unknown";
    case 7: 
      return "Bluetooth";
    case 9: 
      return "Ethernet";
    case 0: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
      return "Mobile";
    case 1: 
      return "WiFi";
    }
    return "WiMAX";
  }
  
  public static boolean hasInternetConnectivity(Context paramContext)
  {
    paramContext = getActiveNetworkInfo(paramContext);
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  @TargetApi(17)
  public static boolean isAirplaneModeOn(Context paramContext)
  {
    boolean bool = false;
    if (Build.VERSION.SDK_INT < 17) {
      if (Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) == 0) {}
    }
    while (Settings.Global.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) != 0)
    {
      bool = true;
      return bool;
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\NetworkConnectivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */