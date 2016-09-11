package com.google.android.gms.tagmanager;

class zzcs
  implements zzcd
{
  private final long zzSP;
  private final int zzSQ;
  private double zzSR;
  private final Object zzST = new Object();
  private long zzbkO;
  
  public zzcs()
  {
    this(60, 2000L);
  }
  
  public zzcs(int paramInt, long paramLong)
  {
    this.zzSQ = paramInt;
    this.zzSR = this.zzSQ;
    this.zzSP = paramLong;
  }
  
  public boolean zzlw()
  {
    synchronized (this.zzST)
    {
      long l = System.currentTimeMillis();
      if (this.zzSR < this.zzSQ)
      {
        double d = (l - this.zzbkO) / this.zzSP;
        if (d > 0.0D) {
          this.zzSR = Math.min(this.zzSQ, d + this.zzSR);
        }
      }
      this.zzbkO = l;
      if (this.zzSR >= 1.0D)
      {
        this.zzSR -= 1.0D;
        return true;
      }
      zzbg.zzaK("No more tokens available.");
      return false;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */