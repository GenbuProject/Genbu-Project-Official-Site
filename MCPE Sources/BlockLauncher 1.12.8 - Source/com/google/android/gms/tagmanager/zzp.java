package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzb;
import com.google.android.gms.internal.zzaf.zzf;
import com.google.android.gms.internal.zzaf.zzj;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzmt;
import com.google.android.gms.internal.zzrq.zza;
import com.google.android.gms.internal.zzrr;
import com.google.android.gms.internal.zzrs.zzc;

public class zzp
  extends zzb<ContainerHolder>
{
  private final Context mContext;
  private final Looper zzagr;
  private final String zzbhM;
  private long zzbhR;
  private final TagManager zzbhY;
  private final zzd zzbib;
  private final zzcd zzbic;
  private final int zzbid;
  private zzf zzbie;
  private zzrr zzbif;
  private volatile zzo zzbig;
  private volatile boolean zzbih;
  private zzaf.zzj zzbii;
  private String zzbij;
  private zze zzbik;
  private zza zzbil;
  private final zzmq zzqW;
  
  zzp(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, zzf paramzzf, zze paramzze, zzrr paramzzrr, zzmq paramzzmq, zzcd paramzzcd) {}
  
  public zzp(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, zzs paramzzs)
  {
    this(paramContext, paramTagManager, paramLooper, paramString, paramInt, new zzcn(paramContext, paramString), new zzcm(paramContext, paramString, paramzzs), new zzrr(paramContext), zzmt.zzsc(), new zzbe(30, 900000L, 5000L, "refreshing", zzmt.zzsc()));
    this.zzbif.zzgB(paramzzs.zzGm());
  }
  
  private boolean zzGj()
  {
    zzcb localzzcb = zzcb.zzGU();
    return ((localzzcb.zzGV() == zzcb.zza.zzbjV) || (localzzcb.zzGV() == zzcb.zza.zzbjW)) && (this.zzbhM.equals(localzzcb.getContainerId()));
  }
  
  private void zza(zzaf.zzj paramzzj)
  {
    try
    {
      if (this.zzbie != null)
      {
        zzrq.zza localzza = new zzrq.zza();
        localzza.zzbmd = this.zzbhR;
        localzza.zzju = new zzaf.zzf();
        localzza.zzbme = paramzzj;
        this.zzbie.zzb(localzza);
      }
      return;
    }
    finally
    {
      paramzzj = finally;
      throw paramzzj;
    }
  }
  
  private void zza(zzaf.zzj paramzzj, long paramLong, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        paramBoolean = this.zzbih;
        if (paramBoolean) {
          return;
        }
        if ((isReady()) && (this.zzbig == null)) {}
        this.zzbii = paramzzj;
        this.zzbhR = paramLong;
        zzak(Math.max(0L, Math.min(43200000L, this.zzbhR + 43200000L - this.zzqW.currentTimeMillis())));
        paramzzj = new Container(this.mContext, this.zzbhY.getDataLayer(), this.zzbhM, paramLong, paramzzj);
        if (this.zzbig == null)
        {
          this.zzbig = new zzo(this.zzbhY, this.zzagr, paramzzj, this.zzbib);
          if ((!isReady()) && (this.zzbil.zzb(paramzzj))) {
            zza(this.zzbig);
          }
        }
        else
        {
          this.zzbig.zza(paramzzj);
        }
      }
      finally {}
    }
  }
  
  /* Error */
  private void zzak(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 87	com/google/android/gms/tagmanager/zzp:zzbik	Lcom/google/android/gms/tagmanager/zzp$zze;
    //   6: ifnonnull +12 -> 18
    //   9: ldc_w 277
    //   12: invokestatic 282	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: aload_0
    //   19: getfield 87	com/google/android/gms/tagmanager/zzp:zzbik	Lcom/google/android/gms/tagmanager/zzp$zze;
    //   22: lload_1
    //   23: aload_0
    //   24: getfield 101	com/google/android/gms/tagmanager/zzp:zzbii	Lcom/google/android/gms/internal/zzaf$zzj;
    //   27: getfield 285	com/google/android/gms/internal/zzaf$zzj:zzjv	Ljava/lang/String;
    //   30: invokeinterface 288 4 0
    //   35: goto -20 -> 15
    //   38: astore_3
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_3
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	zzp
    //   0	43	1	paramLong	long
    //   38	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	15	38	finally
    //   18	35	38	finally
  }
  
  private void zzaw(final boolean paramBoolean)
  {
    this.zzbie.zza(new zzb(null));
    this.zzbik.zza(new zzc(null));
    zzrs.zzc localzzc = this.zzbie.zzke(this.zzbid);
    if (localzzc != null) {
      this.zzbig = new zzo(this.zzbhY, this.zzagr, new Container(this.mContext, this.zzbhY.getDataLayer(), this.zzbhM, 0L, localzzc), this.zzbib);
    }
    this.zzbil = new zza()
    {
      public boolean zzb(Container paramAnonymousContainer)
      {
        if (paramBoolean) {
          if (paramAnonymousContainer.getLastRefreshTime() + 43200000L < zzp.zzc(zzp.this).currentTimeMillis()) {}
        }
        while (!paramAnonymousContainer.isDefault())
        {
          return true;
          return false;
        }
        return false;
      }
    };
    if (zzGj())
    {
      this.zzbik.zzf(0L, "");
      return;
    }
    this.zzbie.zzGl();
  }
  
  String zzGd()
  {
    try
    {
      String str = this.zzbij;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void zzGg()
  {
    Object localObject = this.zzbie.zzke(this.zzbid);
    if (localObject != null)
    {
      localObject = new Container(this.mContext, this.zzbhY.getDataLayer(), this.zzbhM, 0L, (zzrs.zzc)localObject);
      zza(new zzo(this.zzbhY, this.zzagr, (Container)localObject, new zzo.zza()
      {
        public String zzGd()
        {
          return zzp.this.zzGd();
        }
        
        public void zzGf()
        {
          zzbg.zzaK("Refresh ignored: container loaded as default only.");
        }
        
        public void zzfT(String paramAnonymousString)
        {
          zzp.this.zzfT(paramAnonymousString);
        }
      }));
    }
    for (;;)
    {
      this.zzbik = null;
      this.zzbie = null;
      return;
      zzbg.e("Default was requested, but no default container was found");
      zza(zzbn(new Status(10, "Default was requested, but no default container was found", null)));
    }
  }
  
  public void zzGh()
  {
    zzaw(false);
  }
  
  public void zzGi()
  {
    zzaw(true);
  }
  
  protected ContainerHolder zzbn(Status paramStatus)
  {
    if (this.zzbig != null) {
      return this.zzbig;
    }
    if (paramStatus == Status.zzagF) {
      zzbg.e("timer expired: setting result to failure");
    }
    return new zzo(paramStatus);
  }
  
  void zzfT(String paramString)
  {
    try
    {
      this.zzbij = paramString;
      if (this.zzbik != null) {
        this.zzbik.zzfW(paramString);
      }
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
    public abstract boolean zzb(Container paramContainer);
  }
  
  private class zzb
    implements zzbf<zzrq.zza>
  {
    private zzb() {}
    
    public void zzGk() {}
    
    public void zza(zzrq.zza paramzza)
    {
      zzaf.zzj localzzj;
      if (paramzza.zzbme != null) {
        localzzj = paramzza.zzbme;
      }
      for (;;)
      {
        zzp.zza(zzp.this, localzzj, paramzza.zzbmd, true);
        return;
        zzaf.zzf localzzf = paramzza.zzju;
        localzzj = new zzaf.zzj();
        localzzj.zzju = localzzf;
        localzzj.zzjt = null;
        localzzj.zzjv = localzzf.version;
      }
    }
    
    public void zza(zzbf.zza paramzza)
    {
      if (!zzp.zzd(zzp.this)) {
        zzp.zza(zzp.this, 0L);
      }
    }
  }
  
  private class zzc
    implements zzbf<zzaf.zzj>
  {
    private zzc() {}
    
    public void zzGk() {}
    
    public void zza(zzbf.zza arg1)
    {
      synchronized (zzp.this)
      {
        if (!zzp.this.isReady())
        {
          if (zzp.zzb(zzp.this) != null) {
            zzp.this.zza(zzp.zzb(zzp.this));
          }
        }
        else
        {
          zzp.zza(zzp.this, 3600000L);
          return;
        }
        zzp.this.zza(zzp.this.zzbn(Status.zzagF));
      }
    }
    
    public void zzb(zzaf.zzj paramzzj)
    {
      synchronized (zzp.this)
      {
        if (paramzzj.zzju == null)
        {
          if (zzp.zze(zzp.this).zzju == null)
          {
            zzbg.e("Current resource is null; network resource is also null");
            zzp.zza(zzp.this, 3600000L);
            return;
          }
          paramzzj.zzju = zzp.zze(zzp.this).zzju;
        }
        zzp.zza(zzp.this, paramzzj, zzp.zzc(zzp.this).currentTimeMillis(), false);
        zzbg.v("setting refresh time to current time: " + zzp.zzf(zzp.this));
        if (!zzp.zzg(zzp.this)) {
          zzp.zza(zzp.this, paramzzj);
        }
        return;
      }
    }
  }
  
  private class zzd
    implements zzo.zza
  {
    private zzd() {}
    
    public String zzGd()
    {
      return zzp.this.zzGd();
    }
    
    public void zzGf()
    {
      if (zzp.zza(zzp.this).zzlw()) {
        zzp.zza(zzp.this, 0L);
      }
    }
    
    public void zzfT(String paramString)
    {
      zzp.this.zzfT(paramString);
    }
  }
  
  static abstract interface zze
    extends Releasable
  {
    public abstract void zza(zzbf<zzaf.zzj> paramzzbf);
    
    public abstract void zzf(long paramLong, String paramString);
    
    public abstract void zzfW(String paramString);
  }
  
  static abstract interface zzf
    extends Releasable
  {
    public abstract void zzGl();
    
    public abstract void zza(zzbf<zzrq.zza> paramzzbf);
    
    public abstract void zzb(zzrq.zza paramzza);
    
    public abstract zzrs.zzc zzke(int paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */