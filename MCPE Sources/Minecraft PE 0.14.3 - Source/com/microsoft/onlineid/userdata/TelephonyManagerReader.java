package com.microsoft.onlineid.userdata;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;

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
    String str2 = this._telephonyManager.getSimCountryIso();
    IClientAnalytics localIClientAnalytics = ClientAnalytics.get();
    if (TextUtils.isEmpty(str2)) {}
    for (String str1 = "Does not exist in Telephony Manager";; str1 = "Exists in Telephony Manager")
    {
      localIClientAnalytics.logEvent("User data", "Country code", str1);
      return str2;
    }
  }
  
  public String getPhoneNumber()
  {
    String str2 = this._telephonyManager.getLine1Number();
    IClientAnalytics localIClientAnalytics = ClientAnalytics.get();
    if (TextUtils.isEmpty(str2)) {}
    for (String str1 = "Does not exist in Telephony Manager";; str1 = "Exists in Telephony Manager")
    {
      localIClientAnalytics.logEvent("User data", "Mobile phone number", str1);
      return str2;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\userdata\TelephonyManagerReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */