package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.R.string;
import com.google.android.gms.internal.zzmu;

public final class zzg
{
  public static String zzc(Context paramContext, int paramInt, String paramString)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      return paramContext.getString(R.string.common_google_play_services_unknown_issue, new Object[] { paramString });
    case 1: 
      if (zzmu.zzb(paramContext)) {
        return paramContext.getString(R.string.common_google_play_services_install_text_tablet, new Object[] { paramString });
      }
      return paramContext.getString(R.string.common_google_play_services_install_text_phone, new Object[] { paramString });
    case 3: 
      return paramContext.getString(R.string.common_google_play_services_enable_text, new Object[] { paramString });
    case 18: 
      return paramContext.getString(R.string.common_google_play_services_updating_text, new Object[] { paramString });
    case 2: 
      return paramContext.getString(R.string.common_google_play_services_update_text, new Object[] { paramString });
    case 42: 
      return paramContext.getString(R.string.common_google_play_services_wear_update_text);
    case 9: 
      return paramContext.getString(R.string.common_google_play_services_unsupported_text, new Object[] { paramString });
    case 7: 
      return paramContext.getString(R.string.common_google_play_services_network_error_text);
    case 5: 
      return paramContext.getString(R.string.common_google_play_services_invalid_account_text);
    case 16: 
      return paramContext.getString(R.string.common_google_play_services_api_unavailable_text, new Object[] { paramString });
    case 17: 
      return paramContext.getString(R.string.common_google_play_services_sign_in_failed_text);
    }
    return paramContext.getString(R.string.common_google_play_services_restricted_profile_text);
  }
  
  @Nullable
  public static final String zzg(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      Log.e("GoogleApiAvailability", "Unexpected error code " + paramInt);
    case 4: 
    case 6: 
      return null;
    case 1: 
      return paramContext.getString(R.string.common_google_play_services_install_title);
    case 3: 
      return paramContext.getString(R.string.common_google_play_services_enable_title);
    case 18: 
      return paramContext.getString(R.string.common_google_play_services_updating_title);
    case 2: 
    case 42: 
      return paramContext.getString(R.string.common_google_play_services_update_title);
    case 9: 
      Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
      return paramContext.getString(R.string.common_google_play_services_unsupported_title);
    case 7: 
      Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
      return paramContext.getString(R.string.common_google_play_services_network_error_title);
    case 8: 
      Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
      return null;
    case 10: 
      Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
      return null;
    case 5: 
      Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
      return paramContext.getString(R.string.common_google_play_services_invalid_account_title);
    case 11: 
      Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
      return null;
    case 16: 
      Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
      return null;
    case 17: 
      Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
      return paramContext.getString(R.string.common_google_play_services_sign_in_failed_title);
    }
    Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
    return paramContext.getString(R.string.common_google_play_services_restricted_profile_title);
  }
  
  public static String zzh(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      return paramContext.getString(17039370);
    case 1: 
      return paramContext.getString(R.string.common_google_play_services_install_button);
    case 3: 
      return paramContext.getString(R.string.common_google_play_services_enable_button);
    }
    return paramContext.getString(R.string.common_google_play_services_update_button);
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\internal\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */