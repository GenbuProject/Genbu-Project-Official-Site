package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.dynamic.zze;

@zzhb
public class zzhu
  extends zzim
  implements zzhv, zzhy
{
  private final Context mContext;
  private final String zzCd;
  private final zzif.zza zzGd;
  private int zzGu = 3;
  private final zzia zzKB;
  private final zzhy zzKC;
  private final String zzKD;
  private final String zzKE;
  private int zzKF = 0;
  private final Object zzpV;
  private final String zzrG;
  
  public zzhu(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, zzif.zza paramzza, zzia paramzzia, zzhy paramzzhy)
  {
    this.mContext = paramContext;
    this.zzCd = paramString1;
    this.zzrG = paramString2;
    this.zzKD = paramString3;
    this.zzKE = paramString4;
    this.zzGd = paramzza;
    this.zzKB = paramzzia;
    this.zzpV = new Object();
    this.zzKC = paramzzhy;
  }
  
  private void zza(AdRequestParcel paramAdRequestParcel, zzey paramzzey)
  {
    try
    {
      if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzCd))
      {
        paramzzey.zza(paramAdRequestParcel, this.zzKD, this.zzKE);
        return;
      }
      paramzzey.zzb(paramAdRequestParcel, this.zzKD);
      return;
    }
    catch (RemoteException paramAdRequestParcel)
    {
      zzin.zzd("Fail to load ad from adapter.", paramAdRequestParcel);
      zza(this.zzCd, 0);
    }
  }
  
  private void zzk(long paramLong)
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzKF != 0) {
          return;
        }
        if (!zzf(paramLong)) {
          return;
        }
      }
    }
  }
  
  public void onStop() {}
  
  public void zzN(int paramInt)
  {
    zza(this.zzCd, 0);
  }
  
  public void zza(String arg1, int paramInt)
  {
    synchronized (this.zzpV)
    {
      this.zzKF = 2;
      this.zzGu = paramInt;
      this.zzpV.notify();
      return;
    }
  }
  
  public void zzax(String arg1)
  {
    synchronized (this.zzpV)
    {
      this.zzKF = 1;
      this.zzpV.notify();
      return;
    }
  }
  
  public void zzbr()
  {
    if ((this.zzKB == null) || (this.zzKB.zzgQ() == null) || (this.zzKB.zzgP() == null)) {
      return;
    }
    final zzhx localzzhx = this.zzKB.zzgQ();
    localzzhx.zza(this);
    localzzhx.zza(this);
    final AdRequestParcel localAdRequestParcel = this.zzGd.zzLd.zzHt;
    final zzey localzzey = this.zzKB.zzgP();
    try
    {
      if (localzzey.isInitialized()) {
        zza.zzMS.post(new Runnable()
        {
          public void run()
          {
            zzhu.zza(zzhu.this, localAdRequestParcel, localzzey);
          }
        });
      }
      for (;;)
      {
        zzk(zzr.zzbG().elapsedRealtime());
        localzzhx.zza(null);
        localzzhx.zza(null);
        if (this.zzKF != 1) {
          break;
        }
        this.zzKC.zzax(this.zzCd);
        return;
        zza.zzMS.post(new Runnable()
        {
          public void run()
          {
            try
            {
              localzzey.zza(zze.zzC(zzhu.zza(zzhu.this)), localAdRequestParcel, zzhu.zzb(zzhu.this), localzzhx, zzhu.zzc(zzhu.this));
              return;
            }
            catch (RemoteException localRemoteException)
            {
              zzin.zzd("Fail to initialize adapter " + zzhu.zzd(zzhu.this), localRemoteException);
              zzhu.this.zza(zzhu.zzd(zzhu.this), 0);
            }
          }
        });
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzin.zzd("Fail to check if adapter is initialized.", localRemoteException);
        zza(this.zzCd, 0);
      }
      this.zzKC.zza(this.zzCd, this.zzGu);
    }
  }
  
  protected boolean zzf(long paramLong)
  {
    paramLong = 20000L - (zzr.zzbG().elapsedRealtime() - paramLong);
    if (paramLong <= 0L) {
      return false;
    }
    try
    {
      this.zzpV.wait(paramLong);
      return true;
    }
    catch (InterruptedException localInterruptedException) {}
    return false;
  }
  
  public void zzgN()
  {
    zza(this.zzGd.zzLd.zzHt, this.zzKB.zzgP());
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzhu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */