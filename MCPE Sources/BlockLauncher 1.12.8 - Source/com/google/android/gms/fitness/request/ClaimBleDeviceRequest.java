package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;

public class ClaimBleDeviceRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<ClaimBleDeviceRequest> CREATOR = new zzb();
  private final int mVersionCode;
  private final String zzaAB;
  private final BleDevice zzaAC;
  private final zzow zzaAD;
  
  ClaimBleDeviceRequest(int paramInt, String paramString, BleDevice paramBleDevice, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.zzaAB = paramString;
    this.zzaAC = paramBleDevice;
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
  }
  
  public ClaimBleDeviceRequest(String paramString, BleDevice paramBleDevice, zzow paramzzow)
  {
    this.mVersionCode = 4;
    this.zzaAB = paramString;
    this.zzaAC = paramBleDevice;
    this.zzaAD = paramzzow;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaAD == null) {
      return null;
    }
    return this.zzaAD.asBinder();
  }
  
  public String getDeviceAddress()
  {
    return this.zzaAB;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public String toString()
  {
    return String.format("ClaimBleDeviceRequest{%s %s}", new Object[] { this.zzaAB, this.zzaAC });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public BleDevice zzuK()
  {
    return this.zzaAC;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\ClaimBleDeviceRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */