package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import java.util.ArrayList;

public class zzb
  implements Parcelable.Creator<CastDevice>
{
  static void zza(CastDevice paramCastDevice, Parcel paramParcel, int paramInt)
  {
    paramInt = com.google.android.gms.common.internal.safeparcel.zzb.zzav(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramCastDevice.getVersionCode());
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 2, paramCastDevice.zzny(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 3, paramCastDevice.zzZU, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 4, paramCastDevice.getFriendlyName(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 5, paramCastDevice.getModelName(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 6, paramCastDevice.getDeviceVersion(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 7, paramCastDevice.getServicePort());
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 8, paramCastDevice.getIcons(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 9, paramCastDevice.getCapabilities());
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 10, paramCastDevice.getStatus());
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 11, paramCastDevice.zznz(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, paramInt);
  }
  
  public CastDevice[] zzaX(int paramInt)
  {
    return new CastDevice[paramInt];
  }
  
  public CastDevice zzaa(Parcel paramParcel)
  {
    int j = 0;
    String str1 = null;
    int n = zza.zzau(paramParcel);
    int i = -1;
    ArrayList localArrayList = null;
    int k = 0;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    String str6 = null;
    int m = 0;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = zza.zzat(paramParcel);
      switch (zza.zzca(i1))
      {
      default: 
        zza.zzb(paramParcel, i1);
        break;
      case 1: 
        m = zza.zzg(paramParcel, i1);
        break;
      case 2: 
        str6 = zza.zzp(paramParcel, i1);
        break;
      case 3: 
        str5 = zza.zzp(paramParcel, i1);
        break;
      case 4: 
        str4 = zza.zzp(paramParcel, i1);
        break;
      case 5: 
        str3 = zza.zzp(paramParcel, i1);
        break;
      case 6: 
        str2 = zza.zzp(paramParcel, i1);
        break;
      case 7: 
        k = zza.zzg(paramParcel, i1);
        break;
      case 8: 
        localArrayList = zza.zzc(paramParcel, i1, WebImage.CREATOR);
        break;
      case 9: 
        j = zza.zzg(paramParcel, i1);
        break;
      case 10: 
        i = zza.zzg(paramParcel, i1);
        break;
      case 11: 
        str1 = zza.zzp(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new zza.zza("Overread allowed size end=" + n, paramParcel);
    }
    return new CastDevice(m, str6, str5, str4, str3, str2, k, localArrayList, j, i, str1);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */