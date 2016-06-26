package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzbn
  implements Parcelable.Creator<ParcelableTransferPreferences>
{
  static void zza(ParcelableTransferPreferences paramParcelableTransferPreferences, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramParcelableTransferPreferences.mVersionCode);
    zzb.zzc(paramParcel, 2, paramParcelableTransferPreferences.zzarG);
    zzb.zzc(paramParcel, 3, paramParcelableTransferPreferences.zzarH);
    zzb.zza(paramParcel, 4, paramParcelableTransferPreferences.zzasA);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public ParcelableTransferPreferences zzbP(Parcel paramParcel)
  {
    boolean bool = false;
    int m = zza.zzau(paramParcel);
    int k = 0;
    int j = 0;
    int i = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zza.zzat(paramParcel);
      switch (zza.zzca(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        i = zza.zzg(paramParcel, n);
        break;
      case 2: 
        j = zza.zzg(paramParcel, n);
        break;
      case 3: 
        k = zza.zzg(paramParcel, n);
        break;
      case 4: 
        bool = zza.zzc(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new ParcelableTransferPreferences(i, j, k, bool);
  }
  
  public ParcelableTransferPreferences[] zzdK(int paramInt)
  {
    return new ParcelableTransferPreferences[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzbn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */