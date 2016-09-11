package com.google.android.gms.internal;

import java.util.concurrent.Future;

@zzhb
public abstract class zzim
  implements zzit<Future>
{
  private volatile Thread zzLM;
  private boolean zzLN;
  private final Runnable zzx = new Runnable()
  {
    public final void run()
    {
      zzim.zza(zzim.this, Thread.currentThread());
      zzim.this.zzbr();
    }
  };
  
  public zzim()
  {
    this.zzLN = false;
  }
  
  public zzim(boolean paramBoolean)
  {
    this.zzLN = paramBoolean;
  }
  
  public final void cancel()
  {
    onStop();
    if (this.zzLM != null) {
      this.zzLM.interrupt();
    }
  }
  
  public abstract void onStop();
  
  public abstract void zzbr();
  
  public final Future zzhn()
  {
    if (this.zzLN) {
      return zziq.zza(1, this.zzx);
    }
    return zziq.zza(this.zzx);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzim.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */