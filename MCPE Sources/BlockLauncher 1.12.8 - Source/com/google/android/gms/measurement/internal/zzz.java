package com.google.android.gms.measurement.internal;

abstract class zzz
  extends zzy
{
  private boolean zzQk;
  private boolean zzQl;
  private boolean zzaYC;
  
  zzz(zzw paramzzw)
  {
    super(paramzzw);
    this.zzaTV.zzb(this);
  }
  
  boolean isInitialized()
  {
    return (this.zzQk) && (!this.zzQl);
  }
  
  boolean zzDi()
  {
    return this.zzaYC;
  }
  
  public final void zza()
  {
    if (this.zzQk) {
      throw new IllegalStateException("Can't initialize twice");
    }
    zziJ();
    this.zzaTV.zzDg();
    this.zzQk = true;
  }
  
  protected abstract void zziJ();
  
  protected void zzjv()
  {
    if (!isInitialized()) {
      throw new IllegalStateException("Not initialized");
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */