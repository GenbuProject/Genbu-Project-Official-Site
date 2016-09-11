package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zza
  implements Parcelable.Creator<AutocompletePredictionEntity>
{
  static void zza(AutocompletePredictionEntity paramAutocompletePredictionEntity, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramAutocompletePredictionEntity.zzaQd, false);
    zzb.zzc(paramParcel, 1000, paramAutocompletePredictionEntity.mVersionCode);
    zzb.zza(paramParcel, 2, paramAutocompletePredictionEntity.zzaPH, false);
    zzb.zza(paramParcel, 3, paramAutocompletePredictionEntity.zzaPd, false);
    zzb.zzc(paramParcel, 4, paramAutocompletePredictionEntity.zzaQe, false);
    zzb.zzc(paramParcel, 5, paramAutocompletePredictionEntity.zzaQf);
    zzb.zza(paramParcel, 6, paramAutocompletePredictionEntity.zzaQg, false);
    zzb.zzc(paramParcel, 7, paramAutocompletePredictionEntity.zzaQh, false);
    zzb.zza(paramParcel, 8, paramAutocompletePredictionEntity.zzaQi, false);
    zzb.zzc(paramParcel, 9, paramAutocompletePredictionEntity.zzaQj, false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public AutocompletePredictionEntity zzfk(Parcel paramParcel)
  {
    int i = 0;
    ArrayList localArrayList1 = null;
    int k = com.google.android.gms.common.internal.safeparcel.zza.zzau(paramParcel);
    String str1 = null;
    ArrayList localArrayList2 = null;
    String str2 = null;
    ArrayList localArrayList3 = null;
    String str3 = null;
    ArrayList localArrayList4 = null;
    String str4 = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = com.google.android.gms.common.internal.safeparcel.zza.zzat(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, m);
        break;
      case 1: 
        str3 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 1000: 
        j = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 2: 
        str4 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 3: 
        localArrayList4 = com.google.android.gms.common.internal.safeparcel.zza.zzC(paramParcel, m);
        break;
      case 4: 
        localArrayList3 = com.google.android.gms.common.internal.safeparcel.zza.zzc(paramParcel, m, AutocompletePredictionEntity.SubstringEntity.CREATOR);
        break;
      case 5: 
        i = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 6: 
        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 7: 
        localArrayList2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(paramParcel, m, AutocompletePredictionEntity.SubstringEntity.CREATOR);
        break;
      case 8: 
        str1 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 9: 
        localArrayList1 = com.google.android.gms.common.internal.safeparcel.zza.zzc(paramParcel, m, AutocompletePredictionEntity.SubstringEntity.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new AutocompletePredictionEntity(j, str4, localArrayList4, i, str3, localArrayList3, str2, localArrayList2, str1, localArrayList1);
  }
  
  public AutocompletePredictionEntity[] zzhW(int paramInt)
  {
    return new AutocompletePredictionEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\internal\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */