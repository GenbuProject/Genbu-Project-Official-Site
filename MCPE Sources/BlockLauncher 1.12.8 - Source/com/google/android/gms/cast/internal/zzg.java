package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzg
  implements Parcelable.Creator<DeviceStatus>
{
  static void zza(DeviceStatus paramDeviceStatus, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramDeviceStatus.getVersionCode());
    zzb.zza(paramParcel, 2, paramDeviceStatus.zzok());
    zzb.zza(paramParcel, 3, paramDeviceStatus.zzot());
    zzb.zzc(paramParcel, 4, paramDeviceStatus.zzol());
    zzb.zza(paramParcel, 5, paramDeviceStatus.getApplicationMetadata(), paramInt, false);
    zzb.zzc(paramParcel, 6, paramDeviceStatus.zzom());
    zzb.zzI(paramParcel, i);
  }
  
  public DeviceStatus zzae(Parcel paramParcel)
  {
    int i = 0;
    int m = zza.zzau(paramParcel);
    double d = 0.0D;
    ApplicationMetadata localApplicationMetadata = null;
    int j = 0;
    boolean bool = false;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zza.zzat(paramParcel);
      switch (zza.zzca(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        k = zza.zzg(paramParcel, n);
        break;
      case 2: 
        d = zza.zzn(paramParcel, n);
        break;
      case 3: 
        bool = zza.zzc(paramParcel, n);
        break;
      case 4: 
        j = zza.zzg(paramParcel, n);
        break;
      case 5: 
        localApplicationMetadata = (ApplicationMetadata)zza.zza(paramParcel, n, ApplicationMetadata.CREATOR);
        break;
      case 6: 
        i = zza.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new DeviceStatus(k, d, bool, j, localApplicationMetadata, i);
  }
  
  public DeviceStatus[] zzbo(int paramInt)
  {
    return new DeviceStatus[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\internal\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */