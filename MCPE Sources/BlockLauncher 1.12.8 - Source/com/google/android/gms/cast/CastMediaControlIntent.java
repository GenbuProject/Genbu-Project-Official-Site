package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.cast.internal.zzf;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

public final class CastMediaControlIntent
{
  public static final String ACTION_SYNC_STATUS = "com.google.android.gms.cast.ACTION_SYNC_STATUS";
  @Deprecated
  public static final String CATEGORY_CAST = "com.google.android.gms.cast.CATEGORY_CAST";
  public static final String DEFAULT_MEDIA_RECEIVER_APPLICATION_ID = "CC1AD845";
  public static final int ERROR_CODE_REQUEST_FAILED = 1;
  public static final int ERROR_CODE_SESSION_START_FAILED = 2;
  public static final int ERROR_CODE_TEMPORARILY_DISCONNECTED = 3;
  public static final String EXTRA_CAST_APPLICATION_ID = "com.google.android.gms.cast.EXTRA_CAST_APPLICATION_ID";
  public static final String EXTRA_CAST_LANGUAGE_CODE = "com.google.android.gms.cast.EXTRA_CAST_LANGUAGE_CODE";
  public static final String EXTRA_CAST_RELAUNCH_APPLICATION = "com.google.android.gms.cast.EXTRA_CAST_RELAUNCH_APPLICATION";
  public static final String EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS = "com.google.android.gms.cast.EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS";
  public static final String EXTRA_CUSTOM_DATA = "com.google.android.gms.cast.EXTRA_CUSTOM_DATA";
  public static final String EXTRA_DEBUG_LOGGING_ENABLED = "com.google.android.gms.cast.EXTRA_DEBUG_LOGGING_ENABLED";
  public static final String EXTRA_ERROR_CODE = "com.google.android.gms.cast.EXTRA_ERROR_CODE";
  
  public static String categoryForCast(String paramString)
    throws IllegalArgumentException
  {
    if (paramString == null) {
      throw new IllegalArgumentException("applicationId cannot be null");
    }
    return zza("com.google.android.gms.cast.CATEGORY_CAST", paramString, null);
  }
  
  public static String categoryForCast(String paramString, Collection<String> paramCollection)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("applicationId cannot be null");
    }
    if (paramCollection == null) {
      throw new IllegalArgumentException("namespaces cannot be null");
    }
    return zza("com.google.android.gms.cast.CATEGORY_CAST", paramString, paramCollection);
  }
  
  public static String categoryForCast(Collection<String> paramCollection)
    throws IllegalArgumentException
  {
    if (paramCollection == null) {
      throw new IllegalArgumentException("namespaces cannot be null");
    }
    return zza("com.google.android.gms.cast.CATEGORY_CAST", null, paramCollection);
  }
  
  public static String categoryForRemotePlayback()
  {
    return zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", null, null);
  }
  
  public static String categoryForRemotePlayback(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("applicationId cannot be null or empty");
    }
    return zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", paramString, null);
  }
  
  public static String languageTagForLocale(Locale paramLocale)
  {
    return zzf.zzb(paramLocale);
  }
  
  private static String zza(String paramString1, String paramString2, Collection<String> paramCollection)
    throws IllegalArgumentException
  {
    paramString1 = new StringBuilder(paramString1);
    if (paramString2 != null)
    {
      String str = paramString2.toUpperCase();
      if (!str.matches("[A-F0-9]+")) {
        throw new IllegalArgumentException("Invalid application ID: " + paramString2);
      }
      paramString1.append("/").append(str);
    }
    if (paramCollection != null)
    {
      if (paramCollection.isEmpty()) {
        throw new IllegalArgumentException("Must specify at least one namespace");
      }
      if (paramString2 == null) {
        paramString1.append("/");
      }
      paramString1.append("/");
      paramString2 = paramCollection.iterator();
      int i = 1;
      if (paramString2.hasNext())
      {
        paramCollection = (String)paramString2.next();
        zzf.zzch(paramCollection);
        if (i != 0) {
          i = 0;
        }
        for (;;)
        {
          paramString1.append(zzf.zzcj(paramCollection));
          break;
          paramString1.append(",");
        }
      }
    }
    return paramString1.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\CastMediaControlIntent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */