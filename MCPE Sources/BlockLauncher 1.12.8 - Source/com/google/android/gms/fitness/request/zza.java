package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.data.BleDevice;
import java.util.HashMap;
import java.util.Map;

public class zza
  extends zzq.zza
{
  private final BleScanCallback zzaAy;
  
  private zza(BleScanCallback paramBleScanCallback)
  {
    this.zzaAy = ((BleScanCallback)zzx.zzz(paramBleScanCallback));
  }
  
  public void onDeviceFound(BleDevice paramBleDevice)
    throws RemoteException
  {
    this.zzaAy.onDeviceFound(paramBleDevice);
  }
  
  public void onScanStopped()
    throws RemoteException
  {
    this.zzaAy.onScanStopped();
  }
  
  public static class zza
  {
    private static final zza zzaAz = new zza();
    private final Map<BleScanCallback, zza> zzaAA = new HashMap();
    
    public static zza zzuJ()
    {
      return zzaAz;
    }
    
    public zza zza(BleScanCallback paramBleScanCallback)
    {
      synchronized (this.zzaAA)
      {
        zza localzza2 = (zza)this.zzaAA.get(paramBleScanCallback);
        zza localzza1 = localzza2;
        if (localzza2 == null)
        {
          localzza1 = new zza(paramBleScanCallback, null);
          this.zzaAA.put(paramBleScanCallback, localzza1);
        }
        return localzza1;
      }
    }
    
    public zza zzb(BleScanCallback paramBleScanCallback)
    {
      synchronized (this.zzaAA)
      {
        zza localzza = (zza)this.zzaAA.get(paramBleScanCallback);
        if (localzza != null) {
          return localzza;
        }
        paramBleScanCallback = new zza(paramBleScanCallback, null);
        return paramBleScanCallback;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */