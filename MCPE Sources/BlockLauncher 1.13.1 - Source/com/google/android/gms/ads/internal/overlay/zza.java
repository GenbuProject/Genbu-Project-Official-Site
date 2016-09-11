package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;

@zzhb
public class zza
{
  public boolean zza(Context paramContext, Intent paramIntent, zzp paramzzp)
  {
    try
    {
      zzin.v("Launching an intent: " + paramIntent.toURI());
      zzr.zzbC().zzb(paramContext, paramIntent);
      if (paramzzp != null) {
        paramzzp.zzaO();
      }
      return true;
    }
    catch (ActivityNotFoundException paramContext)
    {
      zzin.zzaK(paramContext.getMessage());
    }
    return false;
  }
  
  public boolean zza(Context paramContext, AdLauncherIntentInfoParcel paramAdLauncherIntentInfoParcel, zzp paramzzp)
  {
    int i = 0;
    if (paramAdLauncherIntentInfoParcel == null)
    {
      zzin.zzaK("No intent data for launcher overlay.");
      return false;
    }
    if (paramAdLauncherIntentInfoParcel.intent != null) {
      return zza(paramContext, paramAdLauncherIntentInfoParcel.intent, paramzzp);
    }
    Intent localIntent = new Intent();
    if (TextUtils.isEmpty(paramAdLauncherIntentInfoParcel.url))
    {
      zzin.zzaK("Open GMSG did not contain a URL.");
      return false;
    }
    if (!TextUtils.isEmpty(paramAdLauncherIntentInfoParcel.mimeType)) {
      localIntent.setDataAndType(Uri.parse(paramAdLauncherIntentInfoParcel.url), paramAdLauncherIntentInfoParcel.mimeType);
    }
    String[] arrayOfString;
    for (;;)
    {
      localIntent.setAction("android.intent.action.VIEW");
      if (!TextUtils.isEmpty(paramAdLauncherIntentInfoParcel.packageName)) {
        localIntent.setPackage(paramAdLauncherIntentInfoParcel.packageName);
      }
      if (TextUtils.isEmpty(paramAdLauncherIntentInfoParcel.zzDK)) {
        break label199;
      }
      arrayOfString = paramAdLauncherIntentInfoParcel.zzDK.split("/", 2);
      if (arrayOfString.length >= 2) {
        break;
      }
      zzin.zzaK("Could not parse component name from open GMSG: " + paramAdLauncherIntentInfoParcel.zzDK);
      return false;
      localIntent.setData(Uri.parse(paramAdLauncherIntentInfoParcel.url));
    }
    localIntent.setClassName(arrayOfString[0], arrayOfString[1]);
    label199:
    paramAdLauncherIntentInfoParcel = paramAdLauncherIntentInfoParcel.zzDL;
    if (!TextUtils.isEmpty(paramAdLauncherIntentInfoParcel)) {}
    try
    {
      int j = Integer.parseInt(paramAdLauncherIntentInfoParcel);
      i = j;
    }
    catch (NumberFormatException paramAdLauncherIntentInfoParcel)
    {
      for (;;)
      {
        zzin.zzaK("Could not parse intent flags.");
      }
    }
    localIntent.addFlags(i);
    return zza(paramContext, localIntent, paramzzp);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\overlay\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */