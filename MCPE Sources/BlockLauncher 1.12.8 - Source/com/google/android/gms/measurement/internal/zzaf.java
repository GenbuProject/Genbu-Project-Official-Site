package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;

class zzaf
{
  private long zzCv;
  private final zzmq zzqW;
  
  public zzaf(zzmq paramzzmq)
  {
    zzx.zzz(paramzzmq);
    this.zzqW = paramzzmq;
  }
  
  public void clear()
  {
    this.zzCv = 0L;
  }
  
  public void start()
  {
    this.zzCv = this.zzqW.elapsedRealtime();
  }
  
  public boolean zzv(long paramLong)
  {
    if (this.zzCv == 0L) {}
    while (this.zzqW.elapsedRealtime() - this.zzCv >= paramLong) {
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */