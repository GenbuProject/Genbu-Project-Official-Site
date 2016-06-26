package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzah
  implements Parcelable.Creator<GetAllCapabilitiesResponse>
{
  static void zza(GetAllCapabilitiesResponse paramGetAllCapabilitiesResponse, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramGetAllCapabilitiesResponse.versionCode);
    zzb.zzc(paramParcel, 2, paramGetAllCapabilitiesResponse.statusCode);
    zzb.zzc(paramParcel, 3, paramGetAllCapabilitiesResponse.zzbsA, false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public GetAllCapabilitiesResponse zzin(Parcel paramParcel)
  {
    int j = 0;
    int k = zza.zzau(paramParcel);
    ArrayList localArrayList = null;
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
        i = zza.zzg(paramParcel, m);
        break;
      case 2: 
        j = zza.zzg(paramParcel, m);
        break;
      case 3: 
        localArrayList = zza.zzc(paramParcel, m, CapabilityInfoParcelable.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new GetAllCapabilitiesResponse(i, j, localArrayList);
  }
  
  public GetAllCapabilitiesResponse[] zzlR(int paramInt)
  {
    return new GetAllCapabilitiesResponse[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */