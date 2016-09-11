package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.zzpl;
import java.util.concurrent.Callable;

public class zzb
{
  private static SharedPreferences zzaBZ = null;
  
  public static SharedPreferences zzw(Context paramContext)
  {
    try
    {
      if (zzaBZ == null) {
        zzaBZ = (SharedPreferences)zzpl.zzb(new Callable()
        {
          public SharedPreferences zzvw()
          {
            return this.zzxh.getSharedPreferences("google_sdk_flags", 1);
          }
        });
      }
      paramContext = zzaBZ;
      return paramContext;
    }
    finally {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\flags\impl\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */