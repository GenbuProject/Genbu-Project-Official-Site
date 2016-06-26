package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf.zzj;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class zzcm
  implements zzp.zze
{
  private boolean mClosed;
  private final Context mContext;
  private final String zzbhM;
  private String zzbij;
  private zzbf<zzaf.zzj> zzbkg;
  private zzs zzbkh;
  private final ScheduledExecutorService zzbkj;
  private final zza zzbkk;
  private ScheduledFuture<?> zzbkl;
  
  public zzcm(Context paramContext, String paramString, zzs paramzzs)
  {
    this(paramContext, paramString, paramzzs, null, null);
  }
  
  zzcm(Context paramContext, String paramString, zzs paramzzs, zzb paramzzb, zza paramzza)
  {
    this.zzbkh = paramzzs;
    this.mContext = paramContext;
    this.zzbhM = paramString;
    paramContext = paramzzb;
    if (paramzzb == null) {
      paramContext = new zzb()
      {
        public ScheduledExecutorService zzHb()
        {
          return Executors.newSingleThreadScheduledExecutor();
        }
      };
    }
    this.zzbkj = paramContext.zzHb();
    if (paramzza == null)
    {
      this.zzbkk = new zza()
      {
        public zzcl zza(zzs paramAnonymouszzs)
        {
          return new zzcl(zzcm.zza(zzcm.this), zzcm.zzb(zzcm.this), paramAnonymouszzs);
        }
      };
      return;
    }
    this.zzbkk = paramzza;
  }
  
  private void zzHa()
  {
    try
    {
      if (this.mClosed) {
        throw new IllegalStateException("called method after closed");
      }
    }
    finally {}
  }
  
  private zzcl zzgm(String paramString)
  {
    zzcl localzzcl = this.zzbkk.zza(this.zzbkh);
    localzzcl.zza(this.zzbkg);
    localzzcl.zzfW(this.zzbij);
    localzzcl.zzgl(paramString);
    return localzzcl;
  }
  
  public void release()
  {
    try
    {
      zzHa();
      if (this.zzbkl != null) {
        this.zzbkl.cancel(false);
      }
      this.zzbkj.shutdown();
      this.mClosed = true;
      return;
    }
    finally {}
  }
  
  public void zza(zzbf<zzaf.zzj> paramzzbf)
  {
    try
    {
      zzHa();
      this.zzbkg = paramzzbf;
      return;
    }
    finally
    {
      paramzzbf = finally;
      throw paramzzbf;
    }
  }
  
  public void zzf(long paramLong, String paramString)
  {
    try
    {
      zzbg.v("loadAfterDelay: containerId=" + this.zzbhM + " delay=" + paramLong);
      zzHa();
      if (this.zzbkg == null) {
        throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
      }
    }
    finally {}
    if (this.zzbkl != null) {
      this.zzbkl.cancel(false);
    }
    this.zzbkl = this.zzbkj.schedule(zzgm(paramString), paramLong, TimeUnit.MILLISECONDS);
  }
  
  public void zzfW(String paramString)
  {
    try
    {
      zzHa();
      this.zzbij = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  static abstract interface zza
  {
    public abstract zzcl zza(zzs paramzzs);
  }
  
  static abstract interface zzb
  {
    public abstract ScheduledExecutorService zzHb();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzcm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */