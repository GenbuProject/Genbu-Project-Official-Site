package com.google.android.gms.nearby.messages.devices;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzg
  implements Parcelable.Creator<NearbyDeviceFilter>
{
  static void zza(NearbyDeviceFilter paramNearbyDeviceFilter, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramNearbyDeviceFilter.zzbci);
    zzb.zzc(paramParcel, 1000, paramNearbyDeviceFilter.mVersionCode);
    zzb.zza(paramParcel, 2, paramNearbyDeviceFilter.zzbcj, false);
    zzb.zza(paramParcel, 3, paramNearbyDeviceFilter.zzbck);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public NearbyDeviceFilter zzge(Parcel paramParcel)
  {
    boolean bool = false;
    int k = zza.zzau(paramParcel);
    byte[] arrayOfByte = null;
    int j = 0;
    int i = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      switch (zza.zzca(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        j = zza.zzg(paramParcel, m);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, m);
        break;
      case 2: 
        arrayOfByte = zza.zzs(paramParcel, m);
        break;
      case 3: 
        bool = zza.zzc(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new NearbyDeviceFilter(i, j, arrayOfByte, bool);
  }
  
  public NearbyDeviceFilter[] zzjj(int paramInt)
  {
    return new NearbyDeviceFilter[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\devices\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */