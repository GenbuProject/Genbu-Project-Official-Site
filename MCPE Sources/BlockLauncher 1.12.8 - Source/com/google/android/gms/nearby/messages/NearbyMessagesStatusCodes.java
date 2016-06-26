package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.api.CommonStatusCodes;

public class NearbyMessagesStatusCodes
  extends CommonStatusCodes
{
  public static final int APP_NOT_OPTED_IN = 2802;
  public static final int APP_QUOTA_LIMIT_REACHED = 2804;
  public static final int BLE_ADVERTISING_UNSUPPORTED = 2821;
  public static final int BLE_SCANNING_UNSUPPORTED = 2822;
  public static final int BLUETOOTH_OFF = 2820;
  public static final int DISALLOWED_CALLING_CONTEXT = 2803;
  public static final int TOO_MANY_PENDING_INTENTS = 2801;
  
  public static String getStatusCodeString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return CommonStatusCodes.getStatusCodeString(paramInt);
    case 2801: 
      return "TOO_MANY_PENDING_INTENTS";
    case 2802: 
      return "APP_NOT_OPTED_IN";
    case 2803: 
      return "DISALLOWED_CALLING_CONTEXT";
    case 2820: 
      return "BLUETOOTH_OFF";
    case 2821: 
      return "BLE_ADVERTISING_UNSUPPORTED";
    }
    return "BLE_SCANNING_UNSUPPORTED";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\NearbyMessagesStatusCodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */