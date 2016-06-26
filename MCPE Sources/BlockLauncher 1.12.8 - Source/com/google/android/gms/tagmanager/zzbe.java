package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzmq;

class zzbe
  implements zzcd
{
  private final long zzSP;
  private final int zzSQ;
  private double zzSR;
  private long zzSS;
  private final Object zzST = new Object();
  private final String zzSU;
  private final long zzbjt;
  private final zzmq zzqW;
  
  public zzbe(int paramInt, long paramLong1, long paramLong2, String paramString, zzmq paramzzmq)
  {
    this.zzSQ = paramInt;
    this.zzSR = this.zzSQ;
    this.zzSP = paramLong1;
    this.zzbjt = paramLong2;
    this.zzSU = paramString;
    this.zzqW = paramzzmq;
  }
  
  public boolean zzlw()
  {
    synchronized (this.zzST)
    {
      long l = this.zzqW.currentTimeMillis();
      if (l - this.zzSS < this.zzbjt)
      {
        zzbg.zzaK("Excessive " + this.zzSU + " detected; call ignored.");
        return false;
      }
      if (this.zzSR < this.zzSQ)
      {
        double d = (l - this.zzSS) / this.zzSP;
        if (d > 0.0D) {
          this.zzSR = Math.min(this.zzSQ, d + this.zzSR);
        }
      }
      this.zzSS = l;
      if (this.zzSR >= 1.0D)
      {
        this.zzSR -= 1.0D;
        return true;
      }
    }
    zzbg.zzaK("Excessive " + this.zzSU + " detected; call ignored.");
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzbe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */