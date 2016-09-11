package com.google.android.gms.playlog.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzsu;
import java.util.ArrayList;
import java.util.Iterator;

public class zzf
  extends zzj<zza>
{
  private final String zzTJ;
  private final zzd zzbdT;
  private final zzb zzbdU;
  private boolean zzbdV;
  private final Object zzpV;
  
  public zzf(Context paramContext, Looper paramLooper, zzd paramzzd, com.google.android.gms.common.internal.zzf paramzzf)
  {
    super(paramContext, paramLooper, 24, paramzzf, paramzzd, paramzzd);
    this.zzTJ = paramContext.getPackageName();
    this.zzbdT = ((zzd)zzx.zzz(paramzzd));
    this.zzbdT.zza(this);
    this.zzbdU = new zzb();
    this.zzpV = new Object();
    this.zzbdV = true;
  }
  
  private void zzEW()
  {
    boolean bool;
    if (!this.zzbdV)
    {
      bool = true;
      com.google.android.gms.common.internal.zzb.zzab(bool);
      if (!this.zzbdU.isEmpty()) {
        Object localObject = null;
      }
    }
    label122:
    label195:
    label228:
    for (;;)
    {
      ArrayList localArrayList;
      zzb.zza localzza;
      try
      {
        localArrayList = new ArrayList();
        Iterator localIterator = this.zzbdU.zzEU().iterator();
        if (!localIterator.hasNext()) {
          break label195;
        }
        localzza = (zzb.zza)localIterator.next();
        if (localzza.zzbdI == null) {
          break label122;
        }
        ((zza)zzqJ()).zza(this.zzTJ, localzza.zzbdG, zzsu.toByteArray(localzza.zzbdI));
        continue;
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("PlayLoggerImpl", "Couldn't send cached log events to AndroidLog service.  Retaining in memory cache.");
      }
      bool = false;
      break;
      if (localzza.zzbdG.equals(localRemoteException))
      {
        localArrayList.add(localzza.zzbdH);
      }
      else
      {
        if (!localArrayList.isEmpty())
        {
          ((zza)zzqJ()).zza(this.zzTJ, localRemoteException, localArrayList);
          localArrayList.clear();
        }
        PlayLoggerContext localPlayLoggerContext = localzza.zzbdG;
        localArrayList.add(localzza.zzbdH);
        break label228;
        if (!localArrayList.isEmpty()) {
          ((zza)zzqJ()).zza(this.zzTJ, localPlayLoggerContext, localArrayList);
        }
        this.zzbdU.clear();
        return;
      }
    }
  }
  
  private void zzc(PlayLoggerContext paramPlayLoggerContext, LogEvent paramLogEvent)
  {
    this.zzbdU.zza(paramPlayLoggerContext, paramLogEvent);
  }
  
  private void zzd(PlayLoggerContext paramPlayLoggerContext, LogEvent paramLogEvent)
  {
    try
    {
      zzEW();
      ((zza)zzqJ()).zza(this.zzTJ, paramPlayLoggerContext, paramLogEvent);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("PlayLoggerImpl", "Couldn't send log event.  Will try caching.");
      zzc(paramPlayLoggerContext, paramLogEvent);
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      Log.e("PlayLoggerImpl", "Service was disconnected.  Will try caching.");
      zzc(paramPlayLoggerContext, paramLogEvent);
    }
  }
  
  public void start()
  {
    synchronized (this.zzpV)
    {
      if ((isConnecting()) || (isConnected())) {
        return;
      }
      this.zzbdT.zzat(true);
      zzqG();
      return;
    }
  }
  
  public void stop()
  {
    synchronized (this.zzpV)
    {
      this.zzbdT.zzat(false);
      disconnect();
      return;
    }
  }
  
  void zzau(boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzbdV;
      this.zzbdV = paramBoolean;
      if ((bool) && (!this.zzbdV)) {
        zzEW();
      }
      return;
    }
  }
  
  public void zzb(PlayLoggerContext paramPlayLoggerContext, LogEvent paramLogEvent)
  {
    synchronized (this.zzpV)
    {
      if (this.zzbdV)
      {
        zzc(paramPlayLoggerContext, paramLogEvent);
        return;
      }
      zzd(paramPlayLoggerContext, paramLogEvent);
    }
  }
  
  protected zza zzdO(IBinder paramIBinder)
  {
    return zza.zza.zzdN(paramIBinder);
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.playlog.service.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.playlog.internal.IPlayLogService";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\playlog\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */