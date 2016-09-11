package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.AdRequestParcel;

@zzhb
public class zzii
{
  long zzLE = -1L;
  long zzLF = -1L;
  int zzLG = -1;
  int zzLH = 0;
  int zzLI = 0;
  final String zzLq;
  private final Object zzpV = new Object();
  
  public zzii(String paramString)
  {
    this.zzLq = paramString;
  }
  
  public static boolean zzH(Context paramContext)
  {
    int i = paramContext.getResources().getIdentifier("Theme.Translucent", "style", "android");
    if (i == 0)
    {
      zzin.zzaJ("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
      return false;
    }
    ComponentName localComponentName = new ComponentName(paramContext.getPackageName(), "com.google.android.gms.ads.AdActivity");
    try
    {
      if (i == paramContext.getPackageManager().getActivityInfo(localComponentName, 0).theme) {
        return true;
      }
      zzin.zzaJ("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      zzin.zzaK("Fail to fetch AdActivity theme");
      zzin.zzaJ("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
    }
    return false;
  }
  
  public void zzb(AdRequestParcel paramAdRequestParcel, long paramLong)
  {
    synchronized (this.zzpV)
    {
      if (this.zzLF == -1L)
      {
        this.zzLF = paramLong;
        this.zzLE = this.zzLF;
        if ((paramAdRequestParcel.extras == null) || (paramAdRequestParcel.extras.getInt("gw", 2) != 1)) {}
      }
      else
      {
        this.zzLE = paramLong;
      }
    }
    this.zzLG += 1;
  }
  
  public Bundle zzc(Context paramContext, String paramString)
  {
    synchronized (this.zzpV)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("session_id", this.zzLq);
      localBundle.putLong("basets", this.zzLF);
      localBundle.putLong("currts", this.zzLE);
      localBundle.putString("seq_num", paramString);
      localBundle.putInt("preqs", this.zzLG);
      localBundle.putInt("pclick", this.zzLH);
      localBundle.putInt("pimp", this.zzLI);
      localBundle.putBoolean("support_transparent_background", zzH(paramContext));
      return localBundle;
    }
  }
  
  public void zzgS()
  {
    synchronized (this.zzpV)
    {
      this.zzLI += 1;
      return;
    }
  }
  
  public void zzgT()
  {
    synchronized (this.zzpV)
    {
      this.zzLH += 1;
      return;
    }
  }
  
  public long zzhl()
  {
    return this.zzLF;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzii.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */