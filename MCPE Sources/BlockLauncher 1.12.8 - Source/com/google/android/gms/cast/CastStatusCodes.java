package com.google.android.gms.cast;

import com.google.android.gms.common.api.CommonStatusCodes;

public final class CastStatusCodes
{
  public static final int APPLICATION_NOT_FOUND = 2004;
  public static final int APPLICATION_NOT_RUNNING = 2005;
  public static final int AUTHENTICATION_FAILED = 2000;
  public static final int CANCELED = 2002;
  public static final int ERROR_SERVICE_CREATION_FAILED = 2200;
  public static final int ERROR_SERVICE_DISCONNECTED = 2201;
  public static final int FAILED = 2100;
  public static final int INTERNAL_ERROR = 8;
  public static final int INTERRUPTED = 14;
  public static final int INVALID_REQUEST = 2001;
  public static final int MESSAGE_SEND_BUFFER_TOO_FULL = 2007;
  public static final int MESSAGE_TOO_LARGE = 2006;
  public static final int NETWORK_ERROR = 7;
  public static final int NOT_ALLOWED = 2003;
  public static final int REPLACED = 2103;
  public static final int SUCCESS = 0;
  public static final int TIMEOUT = 15;
  public static final int UNKNOWN_ERROR = 13;
  
  public static String getStatusCodeString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return CommonStatusCodes.getStatusCodeString(paramInt);
    case 0: 
      return "SUCCESS";
    case 7: 
      return "NETWORK_ERROR";
    case 15: 
      return "TIMEOUT";
    case 14: 
      return "INTERRUPTED";
    case 2000: 
      return "AUTHENTICATION_FAILED";
    case 2001: 
      return "INVALID_REQUEST";
    case 2002: 
      return "CANCELED";
    case 2003: 
      return "NOT_ALLOWED";
    case 2004: 
      return "APPLICATION_NOT_FOUND";
    case 2005: 
      return "APPLICATION_NOT_RUNNING";
    case 2006: 
      return "MESSAGE_TOO_LARGE";
    case 2007: 
      return "MESSAGE_SEND_BUFFER_TOO_FULL";
    case 2100: 
      return "FAILED";
    case 2103: 
      return "REPLACED";
    case 8: 
      return "INTERNAL_ERROR";
    case 13: 
      return "UNKNOWN_ERROR";
    case 2200: 
      return "ERROR_SERVICE_CREATION_FAILED";
    case 2201: 
      return "ERROR_SERVICE_DISCONNECTED";
    case 2102: 
      return "STATUS_TIMED_OUT";
    }
    return "STATUS_CANCELLED";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\CastStatusCodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */