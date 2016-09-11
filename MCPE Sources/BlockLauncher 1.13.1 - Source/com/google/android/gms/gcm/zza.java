package com.google.android.gms.gcm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;

class zza
{
  static AppMeasurement zzaLx;
  
  private static void zza(Context paramContext, String paramString, Intent paramIntent)
  {
    Bundle localBundle = new Bundle();
    String str1 = paramIntent.getStringExtra("google.c.a.c_id");
    String str2 = paramIntent.getStringExtra("google.c.a.c_l");
    if ((str1 != null) && (str2 != null))
    {
      localBundle.putString("_nmid", str1);
      localBundle.putString("_nmn", str2);
    }
    str1 = paramIntent.getStringExtra("from");
    if ((str1 != null) && (str1.startsWith("/topics/"))) {}
    for (;;)
    {
      if (str1 != null) {
        localBundle.putString("_nt", str1);
      }
      try
      {
        localBundle.putInt("_nmt", Integer.valueOf(paramIntent.getStringExtra("google.c.a.ts")).intValue());
        label101:
        if (!paramIntent.hasExtra("google.c.a.udt")) {}
      }
      catch (NumberFormatException paramIntent)
      {
        try
        {
          localBundle.putInt("_ndt", Integer.valueOf(paramIntent.getStringExtra("google.c.a.udt")).intValue());
          label129:
          if (Log.isLoggable("GcmAnalytics", 3)) {
            Log.d("GcmAnalytics", "Sending event=" + paramString + " params=" + localBundle);
          }
        }
        catch (NumberFormatException paramIntent)
        {
          try
          {
            if (zzaLx == null) {}
            for (paramContext = AppMeasurement.getInstance(paramContext);; paramContext = zzaLx)
            {
              paramContext.zzd("gcm", paramString, localBundle);
              return;
              str1 = null;
              break;
              localNumberFormatException = localNumberFormatException;
              Log.w("GcmAnalytics", "Error while parsing timestamp in GCM event.");
              break label101;
              paramIntent = paramIntent;
              Log.w("GcmAnalytics", "Error while parsing use_device_time in GCM event.");
              break label129;
            }
            return;
          }
          catch (NoClassDefFoundError paramContext)
          {
            Log.w("GcmAnalytics", "Unable to log event, missing measurement library");
          }
        }
      }
    }
  }
  
  public static void zze(Context paramContext, Intent paramIntent)
  {
    zza(paramContext, "_nr", paramIntent);
  }
  
  public static void zzf(Context paramContext, Intent paramIntent)
  {
    zza(paramContext, "_no", paramIntent);
  }
  
  public static void zzg(Context paramContext, Intent paramIntent)
  {
    zza(paramContext, "_nd", paramIntent);
  }
  
  public static void zzh(Context paramContext, Intent paramIntent)
  {
    zza(paramContext, "_nf", paramIntent);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\gcm\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */