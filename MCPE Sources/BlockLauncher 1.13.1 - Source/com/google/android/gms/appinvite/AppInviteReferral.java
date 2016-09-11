package com.google.android.gms.appinvite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class AppInviteReferral
{
  @Deprecated
  public static Intent addPlayStoreReferrerToIntent(Intent paramIntent1, Intent paramIntent2)
  {
    paramIntent1 = zzh(paramIntent1);
    if ((paramIntent1 != null) && (paramIntent2 != null)) {
      paramIntent2.putExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE", paramIntent1);
    }
    return paramIntent2;
  }
  
  @Deprecated
  public static Intent addReferralDataToIntent(String paramString1, String paramString2, Intent paramIntent)
  {
    if (paramIntent == null) {
      return null;
    }
    return paramIntent.putExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE", zza(paramString1, paramString2, false));
  }
  
  public static String getDeepLink(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      paramIntent = paramIntent.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE");
      if (paramIntent != null) {
        return paramIntent.getString("com.google.android.gms.appinvite.DEEP_LINK");
      }
    }
    return null;
  }
  
  public static String getInvitationId(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      paramIntent = paramIntent.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE");
      if (paramIntent != null) {
        return paramIntent.getString("com.google.android.gms.appinvite.INVITATION_ID");
      }
    }
    return null;
  }
  
  public static boolean hasReferral(Intent paramIntent)
  {
    return (paramIntent != null) && (paramIntent.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE") != null);
  }
  
  public static boolean isOpenedFromPlayStore(Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (hasReferral(paramIntent))
    {
      bool1 = bool2;
      if (paramIntent.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE").getBoolean("com.google.android.gms.appinvite.OPENED_FROM_PLAY_STORE", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static Bundle zza(String paramString1, String paramString2, boolean paramBoolean)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("com.google.android.gms.appinvite.INVITATION_ID", paramString1);
    localBundle.putString("com.google.android.gms.appinvite.DEEP_LINK", paramString2);
    localBundle.putBoolean("com.google.android.gms.appinvite.OPENED_FROM_PLAY_STORE", paramBoolean);
    return localBundle;
  }
  
  private static Bundle zzh(Intent paramIntent)
  {
    if ((paramIntent == null) || (!paramIntent.getAction().equals("com.android.vending.INSTALL_REFERRER")) || (paramIntent.getStringExtra("referrer") == null)) {
      return null;
    }
    String str = paramIntent.getStringExtra("referrer");
    Object localObject;
    try
    {
      paramIntent = URLDecoder.decode(str, "UTF-8");
      localObject = Uri.parse("s://a.b.c?" + paramIntent);
      str = ((Uri)localObject).getQueryParameter("invitation_id");
      localObject = ((Uri)localObject).getQueryParameter("deep_link_id");
      if ((str == null) && (localObject == null))
      {
        Log.w("AppInviteRef", "Missing  Referrer query params: " + paramIntent);
        return null;
      }
    }
    catch (UnsupportedEncodingException paramIntent)
    {
      Log.e("AppInviteRef", "Error parsing Play Store referrer URL: " + str);
      return null;
    }
    return zza(str, (String)localObject, true);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\appinvite\AppInviteReferral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */