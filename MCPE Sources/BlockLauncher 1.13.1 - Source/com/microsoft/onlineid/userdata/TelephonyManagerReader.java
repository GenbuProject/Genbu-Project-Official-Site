package com.microsoft.onlineid.userdata;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephonyManagerReader
  implements IPhoneNumberReader
{
  public TelephonyManagerReader(Context paramContext) {}
  
  public TelephonyManagerReader(TelephonyManager paramTelephonyManager) {}
  
  public String getIsoCountryCode()
  {
    return null;
  }
  
  public String getPhoneNumber()
  {
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\userdata\TelephonyManagerReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */