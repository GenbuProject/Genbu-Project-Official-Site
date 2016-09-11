package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zze
  implements Parcelable.Creator<DataSet>
{
  static void zza(DataSet paramDataSet, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramDataSet.getDataSource(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramDataSet.getVersionCode());
    zzb.zza(paramParcel, 2, paramDataSet.getDataType(), paramInt, false);
    zzb.zzd(paramParcel, 3, paramDataSet.zzuk(), false);
    zzb.zzc(paramParcel, 4, paramDataSet.zzul(), false);
    zzb.zza(paramParcel, 5, paramDataSet.zzuc());
    zzb.zzI(paramParcel, i);
  }
  
  public DataSet zzcR(Parcel paramParcel)
  {
    boolean bool = false;
    ArrayList localArrayList1 = null;
    int j = zza.zzau(paramParcel);
    ArrayList localArrayList2 = new ArrayList();
    DataType localDataType = null;
    DataSource localDataSource = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localDataSource = (DataSource)zza.zza(paramParcel, k, DataSource.CREATOR);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localDataType = (DataType)zza.zza(paramParcel, k, DataType.CREATOR);
        break;
      case 3: 
        zza.zza(paramParcel, k, localArrayList2, getClass().getClassLoader());
        break;
      case 4: 
        localArrayList1 = zza.zzc(paramParcel, k, DataSource.CREATOR);
        break;
      case 5: 
        bool = zza.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new DataSet(i, localDataSource, localDataType, localArrayList2, localArrayList1, bool);
  }
  
  public DataSet[] zzeQ(int paramInt)
  {
    return new DataSet[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\data\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */