package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzk
  implements Parcelable.Creator<PlaceRequest>
{
  static void zza(PlaceRequest paramPlaceRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1000, paramPlaceRequest.mVersionCode);
    zzb.zza(paramParcel, 2, paramPlaceRequest.zzyZ(), paramInt, false);
    zzb.zza(paramParcel, 3, paramPlaceRequest.getInterval());
    zzb.zzc(paramParcel, 4, paramPlaceRequest.getPriority());
    zzb.zza(paramParcel, 5, paramPlaceRequest.getExpirationTime());
    zzb.zzI(paramParcel, i);
  }
  
  public PlaceRequest zzfi(Parcel paramParcel)
  {
    int k = zza.zzau(paramParcel);
    int j = 0;
    PlaceFilter localPlaceFilter = null;
    long l2 = PlaceRequest.zzaPJ;
    int i = 102;
    long l1 = Long.MAX_VALUE;
    while (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      switch (zza.zzca(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        localPlaceFilter = (PlaceFilter)zza.zza(paramParcel, m, PlaceFilter.CREATOR);
        break;
      case 3: 
        l2 = zza.zzi(paramParcel, m);
        break;
      case 4: 
        i = zza.zzg(paramParcel, m);
        break;
      case 5: 
        l1 = zza.zzi(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new PlaceRequest(j, localPlaceFilter, l2, i, l1);
  }
  
  public PlaceRequest[] zzhT(int paramInt)
  {
    return new PlaceRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */