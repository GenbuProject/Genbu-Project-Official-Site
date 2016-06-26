package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class zzay<T>
{
  private final Map<T, zzbq<T>> zzaxd = new HashMap();
  
  public void zza(zzbp paramzzbp, zza.zzb<Status> paramzzb, T paramT)
    throws RemoteException
  {
    synchronized (this.zzaxd)
    {
      zzbq localzzbq = (zzbq)this.zzaxd.remove(paramT);
      if (localzzbq == null)
      {
        paramzzb.zzs(new Status(4002));
        return;
      }
      localzzbq.clear();
      ((zzax)paramzzbp.zzqJ()).zza(new zzb(this.zzaxd, paramT, paramzzb), new RemoveListenerRequest(localzzbq));
      return;
    }
  }
  
  public void zza(zzbp paramzzbp, zza.zzb<Status> paramzzb, T paramT, zzbq<T> paramzzbq)
    throws RemoteException
  {
    synchronized (this.zzaxd)
    {
      if (this.zzaxd.get(paramT) != null)
      {
        paramzzb.zzs(new Status(4001));
        return;
      }
      this.zzaxd.put(paramT, paramzzbq);
    }
  }
  
  public void zzev(IBinder paramIBinder)
  {
    synchronized (this.zzaxd)
    {
      paramIBinder = zzax.zza.zzeu(paramIBinder);
      zzbo.zzo localzzo = new zzbo.zzo();
      Iterator localIterator = this.zzaxd.entrySet().iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          zzbq localzzbq = (zzbq)localEntry.getValue();
          try
          {
            paramIBinder.zza(localzzo, new AddListenerRequest(localzzbq));
            if (Log.isLoggable("WearableClient", 2)) {
              Log.d("WearableClient", "onPostInitHandler: added: " + localEntry.getKey() + "/" + localzzbq);
            }
          }
          catch (RemoteException localRemoteException)
          {
            Log.d("WearableClient", "onPostInitHandler: Didn't add: " + localEntry.getKey() + "/" + localzzbq);
          }
        }
      }
    }
  }
  
  private static class zza<T>
    extends zzbo.zzb<Status>
  {
    private WeakReference<Map<T, zzbq<T>>> zzbsM;
    private WeakReference<T> zzbsN;
    
    zza(Map<T, zzbq<T>> paramMap, T paramT, zza.zzb<Status> paramzzb)
    {
      super();
      this.zzbsM = new WeakReference(paramMap);
      this.zzbsN = new WeakReference(paramT);
    }
    
    public void zza(Status paramStatus)
    {
      Map localMap = (Map)this.zzbsM.get();
      Object localObject = this.zzbsN.get();
      if ((!paramStatus.getStatus().isSuccess()) && (localMap != null) && (localObject != null)) {}
      try
      {
        localObject = (zzbq)localMap.remove(localObject);
        if (localObject != null) {
          ((zzbq)localObject).clear();
        }
        zzX(paramStatus);
        return;
      }
      finally {}
    }
  }
  
  private static class zzb<T>
    extends zzbo.zzb<Status>
  {
    private WeakReference<Map<T, zzbq<T>>> zzbsM;
    private WeakReference<T> zzbsN;
    
    zzb(Map<T, zzbq<T>> paramMap, T paramT, zza.zzb<Status> paramzzb)
    {
      super();
      this.zzbsM = new WeakReference(paramMap);
      this.zzbsN = new WeakReference(paramT);
    }
    
    public void zza(Status paramStatus)
    {
      Map localMap = (Map)this.zzbsM.get();
      Object localObject = this.zzbsN.get();
      if ((paramStatus.getStatus().getStatusCode() == 4002) && (localMap != null) && (localObject != null)) {}
      try
      {
        localObject = (zzbq)localMap.remove(localObject);
        if (localObject != null) {
          ((zzbq)localObject).clear();
        }
        zzX(paramStatus);
        return;
      }
      finally {}
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */