package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh
  implements Parcelable.Creator<DataUpdateNotification>
{
  static void zza(DataUpdateNotification paramDataUpdateNotification, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramDataUpdateNotification.zzup());
    zzb.zzc(paramParcel, 1000, paramDataUpdateNotification.mVersionCode);
    zzb.zza(paramParcel, 2, paramDataUpdateNotification.zzuq());
    zzb.zzc(paramParcel, 3, paramDataUpdateNotification.getOperationType());
    zzb.zza(paramParcel, 4, paramDataUpdateNotification.getDataSource(), paramInt, false);
    zzb.zza(paramParcel, 5, paramDataUpdateNotification.getDataType(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public DataUpdateNotification zzcU(Parcel paramParcel)
  {
    long l1 = 0L;
    DataType localDataType = null;
    int i = 0;
    int k = zza.zzau(paramParcel);
    DataSource localDataSource = null;
    long l2 = 0L;
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
        l2 = zza.zzi(paramParcel, m);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        l1 = zza.zzi(paramParcel, m);
        break;
      case 3: 
        i = zza.zzg(paramParcel, m);
        break;
      case 4: 
        localDataSource = (DataSource)zza.zza(paramParcel, m, DataSource.CREATOR);
        break;
      case 5: 
        localDataType = (DataType)zza.zza(paramParcel, m, DataType.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new DataUpdateNotification(j, l2, l1, i, localDataSource, localDataType);
  }
  
  public DataUpdateNotification[] zzeT(int paramInt)
  {
    return new DataUpdateNotification[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\data\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */