package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.zze;
import java.util.concurrent.Callable;

@zzhb
public class zzbs
{
  private final Object zzpV = new Object();
  private boolean zzqA = false;
  private SharedPreferences zzvx = null;
  
  public void initialize(Context paramContext)
  {
    synchronized (this.zzpV)
    {
      if (this.zzqA) {
        return;
      }
      paramContext = zze.getRemoteContext(paramContext);
      if (paramContext == null) {
        return;
      }
    }
    this.zzvx = zzr.zzbJ().zzw(paramContext);
    this.zzqA = true;
  }
  
  public <T> T zzd(final zzbp<T> paramzzbp)
  {
    synchronized (this.zzpV)
    {
      if (!this.zzqA)
      {
        paramzzbp = paramzzbp.zzdq();
        return paramzzbp;
      }
      (T)zzjb.zzb(new Callable()
      {
        public T call()
        {
          return (T)paramzzbp.zza(zzbs.zza(zzbs.this));
        }
      });
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzbs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */