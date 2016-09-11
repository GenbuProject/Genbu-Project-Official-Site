package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.fitness.BleApi;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.ClaimBleDeviceRequest;
import com.google.android.gms.fitness.request.ListClaimedBleDevicesRequest;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.StopBleScanRequest;
import com.google.android.gms.fitness.request.UnclaimBleDeviceRequest;
import com.google.android.gms.fitness.result.BleDevicesResult;

public class zzpa
  implements BleApi
{
  public PendingResult<Status> claimBleDevice(GoogleApiClient paramGoogleApiClient, final BleDevice paramBleDevice)
  {
    paramGoogleApiClient.zzb(new zznz.zzc(paramGoogleApiClient)
    {
      protected void zza(zznz paramAnonymouszznz)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzok)paramAnonymouszznz.zzqJ()).zza(new ClaimBleDeviceRequest(paramBleDevice.getAddress(), paramBleDevice, localzzph));
      }
    });
  }
  
  public PendingResult<Status> claimBleDevice(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.zzb(new zznz.zzc(paramGoogleApiClient)
    {
      protected void zza(zznz paramAnonymouszznz)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzok)paramAnonymouszznz.zzqJ()).zza(new ClaimBleDeviceRequest(paramString, null, localzzph));
      }
    });
  }
  
  public PendingResult<BleDevicesResult> listClaimedBleDevices(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zza(new zznz.zza(paramGoogleApiClient)
    {
      protected BleDevicesResult zzI(Status paramAnonymousStatus)
      {
        return BleDevicesResult.zzQ(paramAnonymousStatus);
      }
      
      protected void zza(zznz paramAnonymouszznz)
        throws RemoteException
      {
        zzpa.zza localzza = new zzpa.zza(this, null);
        ((zzok)paramAnonymouszznz.zzqJ()).zza(new ListClaimedBleDevicesRequest(localzza));
      }
    });
  }
  
  public PendingResult<Status> startBleScan(GoogleApiClient paramGoogleApiClient, final StartBleScanRequest paramStartBleScanRequest)
  {
    paramGoogleApiClient.zza(new zznz.zzc(paramGoogleApiClient)
    {
      protected void zza(zznz paramAnonymouszznz)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzok)paramAnonymouszznz.zzqJ()).zza(new StartBleScanRequest(paramStartBleScanRequest, localzzph));
      }
    });
  }
  
  public PendingResult<Status> stopBleScan(GoogleApiClient paramGoogleApiClient, final BleScanCallback paramBleScanCallback)
  {
    paramGoogleApiClient.zza(new zznz.zzc(paramGoogleApiClient)
    {
      protected void zza(zznz paramAnonymouszznz)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzok)paramAnonymouszznz.zzqJ()).zza(new StopBleScanRequest(paramBleScanCallback, localzzph));
      }
    });
  }
  
  public PendingResult<Status> unclaimBleDevice(GoogleApiClient paramGoogleApiClient, BleDevice paramBleDevice)
  {
    return unclaimBleDevice(paramGoogleApiClient, paramBleDevice.getAddress());
  }
  
  public PendingResult<Status> unclaimBleDevice(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.zzb(new zznz.zzc(paramGoogleApiClient)
    {
      protected void zza(zznz paramAnonymouszznz)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzok)paramAnonymouszznz.zzqJ()).zza(new UnclaimBleDeviceRequest(paramString, localzzph));
      }
    });
  }
  
  private static class zza
    extends zzpj.zza
  {
    private final zza.zzb<BleDevicesResult> zzamC;
    
    private zza(zza.zzb<BleDevicesResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void zza(BleDevicesResult paramBleDevicesResult)
    {
      this.zzamC.zzs(paramBleDevicesResult);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzpa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */