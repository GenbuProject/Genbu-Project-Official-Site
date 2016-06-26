package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.ads.internal.util.client.zzb;

@zzhb
public final class zzin
  extends zzb
{
  public static void v(String paramString)
  {
    if (zzhp()) {
      Log.v("Ads", paramString);
    }
  }
  
  public static boolean zzho()
  {
    return ((Boolean)zzbt.zzwK.get()).booleanValue();
  }
  
  private static boolean zzhp()
  {
    return (zzQ(2)) && (zzho());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */