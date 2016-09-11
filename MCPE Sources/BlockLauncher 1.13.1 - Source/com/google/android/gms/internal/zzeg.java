package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzx;
import java.util.Map;

@zzhb
public class zzeg
{
  private final Context mContext;
  private final String zzAY;
  private zzb<zzed> zzAZ;
  private zzb<zzed> zzBa;
  private zze zzBb;
  private int zzBc = 1;
  private final VersionInfoParcel zzpT;
  private final Object zzpV = new Object();
  
  public zzeg(Context paramContext, VersionInfoParcel paramVersionInfoParcel, String paramString)
  {
    this.zzAY = paramString;
    this.mContext = paramContext.getApplicationContext();
    this.zzpT = paramVersionInfoParcel;
    this.zzAZ = new zzc();
    this.zzBa = new zzc();
  }
  
  public zzeg(Context paramContext, VersionInfoParcel paramVersionInfoParcel, String paramString, zzb<zzed> paramzzb1, zzb<zzed> paramzzb2)
  {
    this(paramContext, paramVersionInfoParcel, paramString);
    this.zzAZ = paramzzb1;
    this.zzBa = paramzzb2;
  }
  
  private zze zzep()
  {
    final zze localzze = new zze(this.zzBa);
    zzir.runOnUiThread(new Runnable()
    {
      public void run()
      {
        final zzed localzzed = zzeg.this.zza(zzeg.zza(zzeg.this), zzeg.zzb(zzeg.this));
        localzzed.zza(new zzed.zza()
        {
          public void zzeo()
          {
            zzir.zzMc.postDelayed(new Runnable()
            {
              public void run()
              {
                synchronized (zzeg.zzc(zzeg.this))
                {
                  if ((zzeg.1.this.zzBd.getStatus() == -1) || (zzeg.1.this.zzBd.getStatus() == 1)) {
                    return;
                  }
                  zzeg.1.this.zzBd.reject();
                  zzir.runOnUiThread(new Runnable()
                  {
                    public void run()
                    {
                      zzeg.1.1.this.zzBf.destroy();
                    }
                  });
                  zzin.v("Could not receive loaded message in a timely manner. Rejecting.");
                  return;
                }
              }
            }, zzeg.zza.zzBn);
          }
        });
        localzzed.zza("/jsLoaded", new zzdf()
        {
          public void zza(zzjp arg1, Map<String, String> paramAnonymous2Map)
          {
            synchronized (zzeg.zzc(zzeg.this))
            {
              if ((zzeg.1.this.zzBd.getStatus() == -1) || (zzeg.1.this.zzBd.getStatus() == 1)) {
                return;
              }
              zzeg.zza(zzeg.this, 0);
              zzeg.zzd(zzeg.this).zze(localzzed);
              zzeg.1.this.zzBd.zzh(localzzed);
              zzeg.zza(zzeg.this, zzeg.1.this.zzBd);
              zzin.v("Successfully loaded JS Engine.");
              return;
            }
          }
        });
        final zzja localzzja = new zzja();
        zzdf local3 = new zzdf()
        {
          public void zza(zzjp arg1, Map<String, String> paramAnonymous2Map)
          {
            synchronized (zzeg.zzc(zzeg.this))
            {
              zzin.zzaJ("JS Engine is requesting an update");
              if (zzeg.zze(zzeg.this) == 0)
              {
                zzin.zzaJ("Starting reload.");
                zzeg.zza(zzeg.this, 2);
                zzeg.this.zzeq();
              }
              localzzed.zzb("/requestReload", (zzdf)localzzja.get());
              return;
            }
          }
        };
        localzzja.set(local3);
        localzzed.zza("/requestReload", local3);
        if (zzeg.zzf(zzeg.this).endsWith(".js")) {
          localzzed.zzZ(zzeg.zzf(zzeg.this));
        }
        for (;;)
        {
          zzir.zzMc.postDelayed(new Runnable()
          {
            public void run()
            {
              synchronized (zzeg.zzc(zzeg.this))
              {
                if ((zzeg.1.this.zzBd.getStatus() == -1) || (zzeg.1.this.zzBd.getStatus() == 1)) {
                  return;
                }
                zzeg.1.this.zzBd.reject();
                zzir.runOnUiThread(new Runnable()
                {
                  public void run()
                  {
                    zzeg.1.4.this.zzBf.destroy();
                  }
                });
                zzin.v("Could not receive loaded message in a timely manner. Rejecting.");
                return;
              }
            }
          }, zzeg.zza.zzBm);
          return;
          if (zzeg.zzf(zzeg.this).startsWith("<html>")) {
            localzzed.zzab(zzeg.zzf(zzeg.this));
          } else {
            localzzed.zzaa(zzeg.zzf(zzeg.this));
          }
        }
      }
    });
    return localzze;
  }
  
  protected zzed zza(Context paramContext, VersionInfoParcel paramVersionInfoParcel)
  {
    return new zzef(paramContext, paramVersionInfoParcel, null);
  }
  
  protected zze zzeq()
  {
    final zze localzze = zzep();
    localzze.zza(new zzji.zzc()new zzji.zza
    {
      public void zza(zzed arg1)
      {
        synchronized (zzeg.zzc(zzeg.this))
        {
          zzeg.zza(zzeg.this, 0);
          if ((zzeg.zzg(zzeg.this) != null) && (localzze != zzeg.zzg(zzeg.this)))
          {
            zzin.v("New JS engine is loaded, marking previous one as destroyable.");
            zzeg.zzg(zzeg.this).zzeu();
          }
          zzeg.zza(zzeg.this, localzze);
          return;
        }
      }
    }, new zzji.zza()
    {
      public void run()
      {
        synchronized (zzeg.zzc(zzeg.this))
        {
          zzeg.zza(zzeg.this, 1);
          zzin.v("Failed loading new engine. Marking new engine destroyable.");
          localzze.zzeu();
          return;
        }
      }
    });
    return localzze;
  }
  
  public zzd zzer()
  {
    synchronized (this.zzpV)
    {
      zzd localzzd1;
      if ((this.zzBb == null) || (this.zzBb.getStatus() == -1))
      {
        this.zzBc = 2;
        this.zzBb = zzeq();
        localzzd1 = this.zzBb.zzes();
        return localzzd1;
      }
      if (this.zzBc == 0)
      {
        localzzd1 = this.zzBb.zzes();
        return localzzd1;
      }
    }
    if (this.zzBc == 1)
    {
      this.zzBc = 2;
      zzeq();
      localzzd2 = this.zzBb.zzes();
      return localzzd2;
    }
    if (this.zzBc == 2)
    {
      localzzd2 = this.zzBb.zzes();
      return localzzd2;
    }
    zzd localzzd2 = this.zzBb.zzes();
    return localzzd2;
  }
  
  static class zza
  {
    static int zzBm = 60000;
    static int zzBn = 10000;
  }
  
  public static abstract interface zzb<T>
  {
    public abstract void zze(T paramT);
  }
  
  public static class zzc<T>
    implements zzeg.zzb<T>
  {
    public void zze(T paramT) {}
  }
  
  public static class zzd
    extends zzjj<zzeh>
  {
    private final zzeg.zze zzBo;
    private boolean zzBp;
    private final Object zzpV = new Object();
    
    public zzd(zzeg.zze paramzze)
    {
      this.zzBo = paramzze;
    }
    
    public void release()
    {
      synchronized (this.zzpV)
      {
        if (this.zzBp) {
          return;
        }
        this.zzBp = true;
        zza(new zzji.zzc()new zzji.zzb
        {
          public void zzd(zzeh paramAnonymouszzeh)
          {
            zzin.v("Ending javascript session.");
            ((zzei)paramAnonymouszzeh).zzew();
          }
        }, new zzji.zzb());
        zza(new zzji.zzc()new zzji.zza
        {
          public void zzd(zzeh paramAnonymouszzeh)
          {
            zzin.v("Releasing engine reference.");
            zzeg.zzd.zza(zzeg.zzd.this).zzet();
          }
        }, new zzji.zza()
        {
          public void run()
          {
            zzeg.zzd.zza(zzeg.zzd.this).zzet();
          }
        });
        return;
      }
    }
  }
  
  public static class zze
    extends zzjj<zzed>
  {
    private zzeg.zzb<zzed> zzBa;
    private boolean zzBr;
    private int zzBs;
    private final Object zzpV = new Object();
    
    public zze(zzeg.zzb<zzed> paramzzb)
    {
      this.zzBa = paramzzb;
      this.zzBr = false;
      this.zzBs = 0;
    }
    
    public zzeg.zzd zzes()
    {
      final zzeg.zzd localzzd = new zzeg.zzd(this);
      for (;;)
      {
        synchronized (this.zzpV)
        {
          zza(new zzji.zzc()new zzji.zza
          {
            public void zza(zzed paramAnonymouszzed)
            {
              zzin.v("Getting a new session for JS Engine.");
              localzzd.zzh(paramAnonymouszzed.zzen());
            }
          }, new zzji.zza()
          {
            public void run()
            {
              zzin.v("Rejecting reference for JS Engine.");
              localzzd.reject();
            }
          });
          if (this.zzBs >= 0)
          {
            bool = true;
            zzx.zzab(bool);
            this.zzBs += 1;
            return localzzd;
          }
        }
        boolean bool = false;
      }
    }
    
    protected void zzet()
    {
      for (boolean bool = true;; bool = false) {
        synchronized (this.zzpV)
        {
          if (this.zzBs >= 1)
          {
            zzx.zzab(bool);
            zzin.v("Releasing 1 reference for JS Engine");
            this.zzBs -= 1;
            zzev();
            return;
          }
        }
      }
    }
    
    public void zzeu()
    {
      for (boolean bool = true;; bool = false) {
        synchronized (this.zzpV)
        {
          if (this.zzBs >= 0)
          {
            zzx.zzab(bool);
            zzin.v("Releasing root reference. JS Engine will be destroyed once other references are released.");
            this.zzBr = true;
            zzev();
            return;
          }
        }
      }
    }
    
    protected void zzev()
    {
      for (;;)
      {
        synchronized (this.zzpV)
        {
          if (this.zzBs >= 0)
          {
            bool = true;
            zzx.zzab(bool);
            if ((this.zzBr) && (this.zzBs == 0))
            {
              zzin.v("No reference is left (including root). Cleaning up engine.");
              zza(new zzji.zzc()new zzji.zzb
              {
                public void zza(final zzed paramAnonymouszzed)
                {
                  zzir.runOnUiThread(new Runnable()
                  {
                    public void run()
                    {
                      zzeg.zze.zza(zzeg.zze.this).zze(paramAnonymouszzed);
                      paramAnonymouszzed.destroy();
                    }
                  });
                }
              }, new zzji.zzb());
              return;
            }
            zzin.v("There are still references to the engine. Not destroying.");
          }
        }
        boolean bool = false;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzeg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */