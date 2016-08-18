package com.microsoft.onlineid.userdata;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.internal.log.Logger;

public class TelephonyManagerReader
  implements IPhoneNumberReader
{
  private final TelephonyManager _telephonyManager;
  
  public TelephonyManagerReader(Context paramContext)
  {
    this((TelephonyManager)paramContext.getSystemService("phone"));
  }
  
  TelephonyManagerReader(TelephonyManager paramTelephonyManager)
  {
    this._telephonyManager = paramTelephonyManager;
  }
  
  public String getIsoCountryCode()
  {
    Object localObject = null;
    try
    {
      str1 = this._telephonyManager.getSimCountryIso();
      localObject = str1;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        String str1;
        IClientAnalytics localIClientAnalytics;
        Logger.warning("Could not obtain country code via getSimCountryIso(): " + localSecurityException.getMessage());
        continue;
        String str2 = "Exists in Telephony Manager";
      }
    }
    localIClientAnalytics = ClientAnalytics.get();
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      str1 = "Does not exist in Telephony Manager";
      localIClientAnalytics.logEvent("User data", "Country code", str1);
      return (String)localObject;
    }
  }
  
  public String getPhoneNumber()
  {
    Object localObject = null;
    try
    {
      str1 = this._telephonyManager.getLine1Number();
      localObject = str1;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        String str1;
        IClientAnalytics localIClientAnalytics;
        Logger.warning("Could not obtain phone number via getLine1Number(): " + localSecurityException.getMessage());
        continue;
        String str2 = "Exists in Telephony Manager";
      }
    }
    localIClientAnalytics = ClientAnalytics.get();
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      str1 = "Does not exist in Telephony Manager";
      localIClientAnalytics.logEvent("User data", "Mobile phone number", str1);
      return (String)localObject;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\userdata\TelephonyManagerReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */