package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.SharedPreferences;

public class zza
{
  private static int zzaeO = -1;
  public static final zza zzaeP = new zza();
  
  public int zzah(Context paramContext)
  {
    if (zzaeO < 0) {
      zzaeO = paramContext.getSharedPreferences("bootCount", 0).getInt("bootCount", 1);
    }
    return zzaeO;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\clearcut\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */