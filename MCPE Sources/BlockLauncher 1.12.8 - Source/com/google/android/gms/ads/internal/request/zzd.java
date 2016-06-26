package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.zzbm;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzhc;
import com.google.android.gms.internal.zzhd;
import com.google.android.gms.internal.zzih;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzit;
import com.google.android.gms.internal.zzix;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzji.zza;
import com.google.android.gms.internal.zzji.zzc;

@zzhb
public abstract class zzd
  implements zzc.zza, zzit<Void>
{
  private final zzji<AdRequestInfoParcel> zzHl;
  private final zzc.zza zzHm;
  private final Object zzpV = new Object();
  
  public zzd(zzji<AdRequestInfoParcel> paramzzji, zzc.zza paramzza)
  {
    this.zzHl = paramzzji;
    this.zzHm = paramzza;
  }
  
  public void cancel()
  {
    zzgr();
  }
  
  boolean zza(zzj paramzzj, AdRequestInfoParcel paramAdRequestInfoParcel)
  {
    try
    {
      paramzzj.zza(paramAdRequestInfoParcel, new zzg(this));
      return true;
    }
    catch (RemoteException paramzzj)
    {
      zzin.zzd("Could not fetch ad response from ad request service.", paramzzj);
      zzr.zzbF().zzb(paramzzj, true);
      this.zzHm.zzb(new AdResponseParcel(0));
      return false;
    }
    catch (NullPointerException paramzzj)
    {
      for (;;)
      {
        zzin.zzd("Could not fetch ad response from ad request service due to an Exception.", paramzzj);
        zzr.zzbF().zzb(paramzzj, true);
      }
    }
    catch (SecurityException paramzzj)
    {
      for (;;)
      {
        zzin.zzd("Could not fetch ad response from ad request service due to an Exception.", paramzzj);
        zzr.zzbF().zzb(paramzzj, true);
      }
    }
    catch (Throwable paramzzj)
    {
      for (;;)
      {
        zzin.zzd("Could not fetch ad response from ad request service due to an Exception.", paramzzj);
        zzr.zzbF().zzb(paramzzj, true);
      }
    }
  }
  
  public void zzb(AdResponseParcel paramAdResponseParcel)
  {
    synchronized (this.zzpV)
    {
      this.zzHm.zzb(paramAdResponseParcel);
      zzgr();
      return;
    }
  }
  
  public Void zzga()
  {
    final zzj localzzj = zzgs();
    if (localzzj == null)
    {
      this.zzHm.zzb(new AdResponseParcel(0));
      zzgr();
      return null;
    }
    this.zzHl.zza(new zzji.zzc()new zzji.zza
    {
      public void zzc(AdRequestInfoParcel paramAnonymousAdRequestInfoParcel)
      {
        if (!zzd.this.zza(localzzj, paramAnonymousAdRequestInfoParcel)) {
          zzd.this.zzgr();
        }
      }
    }, new zzji.zza()
    {
      public void run()
      {
        zzd.this.zzgr();
      }
    });
    return null;
  }
  
  public abstract void zzgr();
  
  public abstract zzj zzgs();
  
  @zzhb
  public static final class zza
    extends zzd
  {
    private final Context mContext;
    
    public zza(Context paramContext, zzji<AdRequestInfoParcel> paramzzji, zzc.zza paramzza)
    {
      super(paramzza);
      this.mContext = paramContext;
    }
    
    public void zzgr() {}
    
    public zzj zzgs()
    {
      zzbm localzzbm = new zzbm((String)zzbt.zzvB.get());
      return zzhd.zza(this.mContext, localzzbm, zzhc.zzgA());
    }
  }
  
  @zzhb
  public static class zzb
    extends zzd
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
  {
    private Context mContext;
    private zzji<AdRequestInfoParcel> zzHl;
    private final zzc.zza zzHm;
    protected zze zzHp;
    private boolean zzHq;
    private VersionInfoParcel zzpT;
    private final Object zzpV = new Object();
    
    public zzb(Context paramContext, VersionInfoParcel paramVersionInfoParcel, zzji<AdRequestInfoParcel> paramzzji, zzc.zza paramzza)
    {
      super(paramzza);
      this.mContext = paramContext;
      this.zzpT = paramVersionInfoParcel;
      this.zzHl = paramzzji;
      this.zzHm = paramzza;
      if (((Boolean)zzbt.zzwa.get()).booleanValue()) {
        this.zzHq = true;
      }
      for (paramVersionInfoParcel = zzr.zzbO().zzhC();; paramVersionInfoParcel = paramContext.getMainLooper())
      {
        this.zzHp = new zze(paramContext, paramVersionInfoParcel, this, this, this.zzpT.zzNa);
        connect();
        return;
      }
    }
    
    protected void connect()
    {
      this.zzHp.zzqG();
    }
    
    public void onConnected(Bundle paramBundle)
    {
      zzga();
    }
    
    public void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
    {
      zzin.zzaI("Cannot connect to remote service, fallback to local instance.");
      zzgt().zzgd();
      paramConnectionResult = new Bundle();
      paramConnectionResult.putString("action", "gms_connection_failed_fallback_to_local");
      zzr.zzbC().zzb(this.mContext, this.zzpT.afmaVersion, "gmob-apps", paramConnectionResult, true);
    }
    
    public void onConnectionSuspended(int paramInt)
    {
      zzin.zzaI("Disconnected from remote ad request service.");
    }
    
    public void zzgr()
    {
      synchronized (this.zzpV)
      {
        if ((this.zzHp.isConnected()) || (this.zzHp.isConnecting())) {
          this.zzHp.disconnect();
        }
        Binder.flushPendingCommands();
        if (this.zzHq)
        {
          zzr.zzbO().zzhD();
          this.zzHq = false;
        }
        return;
      }
    }
    
    /* Error */
    public zzj zzgs()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 37	com/google/android/gms/ads/internal/request/zzd$zzb:zzpV	Ljava/lang/Object;
      //   4: astore_1
      //   5: aload_1
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 90	com/google/android/gms/ads/internal/request/zzd$zzb:zzHp	Lcom/google/android/gms/ads/internal/request/zze;
      //   11: invokevirtual 189	com/google/android/gms/ads/internal/request/zze:zzgw	()Lcom/google/android/gms/ads/internal/request/zzj;
      //   14: astore_2
      //   15: aload_1
      //   16: monitorexit
      //   17: aload_2
      //   18: areturn
      //   19: aload_1
      //   20: monitorexit
      //   21: aconst_null
      //   22: areturn
      //   23: astore_2
      //   24: aload_1
      //   25: monitorexit
      //   26: aload_2
      //   27: athrow
      //   28: astore_2
      //   29: goto -10 -> 19
      //   32: astore_2
      //   33: goto -14 -> 19
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	36	0	this	zzb
      //   14	4	2	localzzj	zzj
      //   23	4	2	localObject2	Object
      //   28	1	2	localDeadObjectException	android.os.DeadObjectException
      //   32	1	2	localIllegalStateException	IllegalStateException
      // Exception table:
      //   from	to	target	type
      //   7	15	23	finally
      //   15	17	23	finally
      //   19	21	23	finally
      //   24	26	23	finally
      //   7	15	28	android/os/DeadObjectException
      //   7	15	32	java/lang/IllegalStateException
    }
    
    zzit zzgt()
    {
      return new zzd.zza(this.mContext, this.zzHl, this.zzHm);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\request\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */