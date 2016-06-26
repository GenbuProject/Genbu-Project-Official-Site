package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd
  implements Parcelable.Creator<DataPoint>
{
  static void zza(DataPoint paramDataPoint, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramDataPoint.getDataSource(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramDataPoint.getVersionCode());
    zzb.zza(paramParcel, 3, paramDataPoint.getTimestampNanos());
    zzb.zza(paramParcel, 4, paramDataPoint.zzuj());
    zzb.zza(paramParcel, 5, paramDataPoint.zzuf(), paramInt, false);
    zzb.zza(paramParcel, 6, paramDataPoint.getOriginalDataSource(), paramInt, false);
    zzb.zza(paramParcel, 7, paramDataPoint.zzug());
    zzb.zza(paramParcel, 8, paramDataPoint.zzuh());
    zzb.zzI(paramParcel, i);
  }
  
  public DataPoint zzcQ(Parcel paramParcel)
  {
    int j = zza.zzau(paramParcel);
    int i = 0;
    DataSource localDataSource2 = null;
    long l4 = 0L;
    long l3 = 0L;
    Value[] arrayOfValue = null;
    DataSource localDataSource1 = null;
    long l2 = 0L;
    long l1 = 0L;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localDataSource2 = (DataSource)zza.zza(paramParcel, k, DataSource.CREATOR);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 3: 
        l4 = zza.zzi(paramParcel, k);
        break;
      case 4: 
        l3 = zza.zzi(paramParcel, k);
        break;
      case 5: 
        arrayOfValue = (Value[])zza.zzb(paramParcel, k, Value.CREATOR);
        break;
      case 6: 
        localDataSource1 = (DataSource)zza.zza(paramParcel, k, DataSource.CREATOR);
        break;
      case 7: 
        l2 = zza.zzi(paramParcel, k);
        break;
      case 8: 
        l1 = zza.zzi(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new DataPoint(i, localDataSource2, l4, l3, arrayOfValue, localDataSource1, l2, l1);
  }
  
  public DataPoint[] zzeP(int paramInt)
  {
    return new DataPoint[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */