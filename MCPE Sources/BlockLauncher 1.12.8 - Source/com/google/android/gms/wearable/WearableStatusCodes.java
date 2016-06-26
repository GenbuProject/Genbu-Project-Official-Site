package com.google.android.gms.wearable;

import com.google.android.gms.common.api.CommonStatusCodes;

public final class WearableStatusCodes
  extends CommonStatusCodes
{
  public static final int ASSET_UNAVAILABLE = 4005;
  public static final int DATA_ITEM_TOO_LARGE = 4003;
  public static final int DUPLICATE_CAPABILITY = 4006;
  public static final int DUPLICATE_LISTENER = 4001;
  public static final int INVALID_TARGET_NODE = 4004;
  public static final int TARGET_NODE_NOT_CONNECTED = 4000;
  public static final int UNKNOWN_CAPABILITY = 4007;
  public static final int UNKNOWN_LISTENER = 4002;
  public static final int WIFI_CREDENTIAL_SYNC_NO_CREDENTIAL_FETCHED = 4008;
  
  public static String getStatusCodeString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return CommonStatusCodes.getStatusCodeString(paramInt);
    case 4000: 
      return "TARGET_NODE_NOT_CONNECTED";
    case 4001: 
      return "DUPLICATE_LISTENER";
    case 4002: 
      return "UNKNOWN_LISTENER";
    case 4003: 
      return "DATA_ITEM_TOO_LARGE";
    case 4004: 
      return "INVALID_TARGET_NODE";
    }
    return "ASSET_UNAVAILABLE";
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\WearableStatusCodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */