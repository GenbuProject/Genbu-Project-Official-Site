package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;
import java.util.ArrayList;

public class zzf
  implements Parcelable.Creator<DataReadRequest>
{
  static void zza(DataReadRequest paramDataReadRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramDataReadRequest.getDataTypes(), false);
    zzb.zzc(paramParcel, 2, paramDataReadRequest.getDataSources(), false);
    zzb.zza(paramParcel, 3, paramDataReadRequest.zzlO());
    zzb.zza(paramParcel, 4, paramDataReadRequest.zzud());
    zzb.zzc(paramParcel, 5, paramDataReadRequest.getAggregatedDataTypes(), false);
    zzb.zzc(paramParcel, 6, paramDataReadRequest.getAggregatedDataSources(), false);
    zzb.zzc(paramParcel, 7, paramDataReadRequest.getBucketType());
    zzb.zza(paramParcel, 8, paramDataReadRequest.zzuR());
    zzb.zza(paramParcel, 9, paramDataReadRequest.getActivityDataSource(), paramInt, false);
    zzb.zzc(paramParcel, 10, paramDataReadRequest.getLimit());
    zzb.zza(paramParcel, 12, paramDataReadRequest.zzuQ());
    zzb.zza(paramParcel, 13, paramDataReadRequest.zzuP());
    zzb.zza(paramParcel, 14, paramDataReadRequest.getCallbackBinder(), false);
    zzb.zzc(paramParcel, 16, paramDataReadRequest.zzuS(), false);
    zzb.zzc(paramParcel, 1000, paramDataReadRequest.getVersionCode());
    zzb.zzI(paramParcel, i);
  }
  
  public DataReadRequest zzdl(Parcel paramParcel)
  {
    int m = zza.zzau(paramParcel);
    int k = 0;
    ArrayList localArrayList5 = null;
    ArrayList localArrayList4 = null;
    long l3 = 0L;
    long l2 = 0L;
    ArrayList localArrayList3 = null;
    ArrayList localArrayList2 = null;
    int j = 0;
    long l1 = 0L;
    DataSource localDataSource = null;
    int i = 0;
    boolean bool2 = false;
    boolean bool1 = false;
    IBinder localIBinder = null;
    ArrayList localArrayList1 = null;
    while (paramParcel.dataPosition() < m)
    {
      int n = zza.zzat(paramParcel);
      switch (zza.zzca(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        localArrayList5 = zza.zzc(paramParcel, n, DataType.CREATOR);
        break;
      case 2: 
        localArrayList4 = zza.zzc(paramParcel, n, DataSource.CREATOR);
        break;
      case 3: 
        l3 = zza.zzi(paramParcel, n);
        break;
      case 4: 
        l2 = zza.zzi(paramParcel, n);
        break;
      case 5: 
        localArrayList3 = zza.zzc(paramParcel, n, DataType.CREATOR);
        break;
      case 6: 
        localArrayList2 = zza.zzc(paramParcel, n, DataSource.CREATOR);
        break;
      case 7: 
        j = zza.zzg(paramParcel, n);
        break;
      case 8: 
        l1 = zza.zzi(paramParcel, n);
        break;
      case 9: 
        localDataSource = (DataSource)zza.zza(paramParcel, n, DataSource.CREATOR);
        break;
      case 10: 
        i = zza.zzg(paramParcel, n);
        break;
      case 12: 
        bool2 = zza.zzc(paramParcel, n);
        break;
      case 13: 
        bool1 = zza.zzc(paramParcel, n);
        break;
      case 14: 
        localIBinder = zza.zzq(paramParcel, n);
        break;
      case 16: 
        localArrayList1 = zza.zzc(paramParcel, n, Device.CREATOR);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new DataReadRequest(k, localArrayList5, localArrayList4, l3, l2, localArrayList3, localArrayList2, j, l1, localDataSource, i, bool2, bool1, localIBinder, localArrayList1);
  }
  
  public DataReadRequest[] zzfl(int paramInt)
  {
    return new DataReadRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */