package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzr;

@zzhb
public class zziz
{
  private long zzMJ;
  private long zzMK = Long.MIN_VALUE;
  private Object zzpV = new Object();
  
  public zziz(long paramLong)
  {
    this.zzMJ = paramLong;
  }
  
  public boolean tryAcquire()
  {
    synchronized (this.zzpV)
    {
      long l = zzr.zzbG().elapsedRealtime();
      if (this.zzMK + this.zzMJ > l) {
        return false;
      }
      this.zzMK = l;
      return true;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zziz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */