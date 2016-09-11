package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzs
  implements Parcelable.Creator<Subscription>
{
  static void zza(Subscription paramSubscription, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramSubscription.getDataSource(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramSubscription.getVersionCode());
    zzb.zza(paramParcel, 2, paramSubscription.getDataType(), paramInt, false);
    zzb.zza(paramParcel, 3, paramSubscription.zzux());
    zzb.zzc(paramParcel, 4, paramSubscription.getAccuracyMode());
    zzb.zzI(paramParcel, i);
  }
  
  public Subscription zzdd(Parcel paramParcel)
  {
    DataType localDataType = null;
    int i = 0;
    int k = zza.zzau(paramParcel);
    long l = 0L;
    DataSource localDataSource = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      switch (zza.zzca(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        localDataSource = (DataSource)zza.zza(paramParcel, m, DataSource.CREATOR);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        localDataType = (DataType)zza.zza(paramParcel, m, DataType.CREATOR);
        break;
      case 3: 
        l = zza.zzi(paramParcel, m);
        break;
      case 4: 
        i = zza.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new Subscription(j, localDataSource, localDataType, l, i);
  }
  
  public Subscription[] zzfd(int paramInt)
  {
    return new Subscription[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\data\zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */