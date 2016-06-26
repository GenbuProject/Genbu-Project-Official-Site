package com.google.android.gms.cast.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

public abstract class zzc
  extends zzd
{
  protected final Handler mHandler = new Handler(Looper.getMainLooper());
  protected final long zzadq;
  protected final Runnable zzadr = new zza(null);
  protected boolean zzads;
  
  public zzc(String paramString1, String paramString2, String paramString3)
  {
    this(paramString1, paramString2, paramString3, 1000L);
  }
  
  public zzc(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    super(paramString1, paramString2, paramString3);
    this.zzadq = paramLong;
    zzW(false);
  }
  
  protected final void zzW(boolean paramBoolean)
  {
    if (this.zzads != paramBoolean)
    {
      this.zzads = paramBoolean;
      if (paramBoolean) {
        this.mHandler.postDelayed(this.zzadr, this.zzadq);
      }
    }
    else
    {
      return;
    }
    this.mHandler.removeCallbacks(this.zzadr);
  }
  
  public void zzof()
  {
    zzW(false);
  }
  
  protected abstract boolean zzz(long paramLong);
  
  private class zza
    implements Runnable
  {
    private zza() {}
    
    public void run()
    {
      zzc.this.zzads = false;
      long l = SystemClock.elapsedRealtime();
      boolean bool = zzc.this.zzz(l);
      zzc.this.zzW(bool);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\internal\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */