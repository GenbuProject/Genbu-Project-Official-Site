package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.zzx;

public final class zzt
  extends zzau.zza
{
  private zzm zzbsk;
  private zzu zzbso;
  private final Object zzpV = new Object();
  
  public void zza(zzu paramzzu)
  {
    synchronized (this.zzpV)
    {
      this.zzbso = ((zzu)zzx.zzz(paramzzu));
      zzm localzzm = this.zzbsk;
      if (localzzm != null) {
        paramzzu.zzb(localzzm);
      }
      return;
    }
  }
  
  public void zzy(int paramInt1, int paramInt2)
  {
    synchronized (this.zzpV)
    {
      zzu localzzu = this.zzbso;
      zzm localzzm = new zzm(paramInt1, paramInt2);
      this.zzbsk = localzzm;
      if (localzzu != null) {
        localzzu.zzb(localzzm);
      }
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */