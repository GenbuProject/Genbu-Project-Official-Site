package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.zzmq;

public class zzad
{
  private final long zzSP;
  private final int zzSQ;
  private double zzSR;
  private long zzSS;
  private final Object zzST = new Object();
  private final String zzSU;
  private final zzmq zzqW;
  
  public zzad(int paramInt, long paramLong, String paramString, zzmq paramzzmq)
  {
    this.zzSQ = paramInt;
    this.zzSR = this.zzSQ;
    this.zzSP = paramLong;
    this.zzSU = paramString;
    this.zzqW = paramzzmq;
  }
  
  public zzad(String paramString, zzmq paramzzmq)
  {
    this(60, 2000L, paramString, paramzzmq);
  }
  
  public boolean zzlw()
  {
    synchronized (this.zzST)
    {
      long l = this.zzqW.currentTimeMillis();
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
      zzae.zzaK("Excessive " + this.zzSU + " detected; call ignored.");
      return false;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */