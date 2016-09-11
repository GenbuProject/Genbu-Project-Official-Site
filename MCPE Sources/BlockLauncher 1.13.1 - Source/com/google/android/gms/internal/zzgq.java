package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.request.AdResponseParcel;

@zzhb
public abstract class zzgq
  extends zzim
{
  protected final Context mContext;
  protected final zzgr.zza zzGc;
  protected final zzif.zza zzGd;
  protected AdResponseParcel zzGe;
  protected final Object zzGg = new Object();
  protected final Object zzpV = new Object();
  
  protected zzgq(Context paramContext, zzif.zza paramzza, zzgr.zza paramzza1)
  {
    super(true);
    this.mContext = paramContext;
    this.zzGd = paramzza;
    this.zzGe = paramzza.zzLe;
    this.zzGc = paramzza1;
  }
  
  public void onStop() {}
  
  protected abstract zzif zzD(int paramInt);
  
  public void zzbr()
  {
    for (;;)
    {
      int i;
      synchronized (this.zzpV)
      {
        zzin.zzaI("AdRendererBackgroundTask started.");
        i = this.zzGd.errorCode;
        try
        {
          zzh(SystemClock.elapsedRealtime());
          final zzif localzzif = zzD(i);
          zzir.zzMc.post(new Runnable()
          {
            public void run()
            {
              synchronized (zzgq.this.zzpV)
              {
                zzgq.this.zzm(localzzif);
                return;
              }
            }
          });
          return;
        }
        catch (zza localzza)
        {
          i = localzza.getErrorCode();
          if (i == 3) {
            continue;
          }
        }
        if (i == -1)
        {
          zzin.zzaJ(localzza.getMessage());
          if (this.zzGe == null)
          {
            this.zzGe = new AdResponseParcel(i);
            zzir.zzMc.post(new Runnable()
            {
              public void run()
              {
                zzgq.this.onStop();
              }
            });
          }
        }
        else
        {
          zzin.zzaK(localzza.getMessage());
        }
      }
      this.zzGe = new AdResponseParcel(i, this.zzGe.zzBU);
    }
  }
  
  protected abstract void zzh(long paramLong)
    throws zzgq.zza;
  
  protected void zzm(zzif paramzzif)
  {
    this.zzGc.zzb(paramzzif);
  }
  
  protected static final class zza
    extends Exception
  {
    private final int zzGu;
    
    public zza(String paramString, int paramInt)
    {
      super();
      this.zzGu = paramInt;
    }
    
    public int getErrorCode()
    {
      return this.zzGu;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzgq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */