package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd
  implements Parcelable.Creator<Strategy>
{
  static void zza(Strategy paramStrategy, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramStrategy.zzbbL);
    zzb.zzc(paramParcel, 1000, paramStrategy.mVersionCode);
    zzb.zzc(paramParcel, 2, paramStrategy.zzbbM);
    zzb.zzc(paramParcel, 3, paramStrategy.zzbbN);
    zzb.zza(paramParcel, 4, paramStrategy.zzbbO);
    zzb.zzc(paramParcel, 5, paramStrategy.zzEr());
    zzb.zzc(paramParcel, 6, paramStrategy.zzEs());
    zzb.zzI(paramParcel, paramInt);
  }
  
  public Strategy zzgc(Parcel paramParcel)
  {
    int i = 0;
    int i2 = zza.zzau(paramParcel);
    int j = 0;
    boolean bool = false;
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    while (paramParcel.dataPosition() < i2)
    {
      int i3 = zza.zzat(paramParcel);
      switch (zza.zzca(i3))
      {
      default: 
        zza.zzb(paramParcel, i3);
        break;
      case 1: 
        n = zza.zzg(paramParcel, i3);
        break;
      case 1000: 
        i1 = zza.zzg(paramParcel, i3);
        break;
      case 2: 
        m = zza.zzg(paramParcel, i3);
        break;
      case 3: 
        k = zza.zzg(paramParcel, i3);
        break;
      case 4: 
        bool = zza.zzc(paramParcel, i3);
        break;
      case 5: 
        j = zza.zzg(paramParcel, i3);
        break;
      case 6: 
        i = zza.zzg(paramParcel, i3);
      }
    }
    if (paramParcel.dataPosition() != i2) {
      throw new zza.zza("Overread allowed size end=" + i2, paramParcel);
    }
    return new Strategy(i1, n, m, k, bool, j, i);
  }
  
  public Strategy[] zzjh(int paramInt)
  {
    return new Strategy[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */