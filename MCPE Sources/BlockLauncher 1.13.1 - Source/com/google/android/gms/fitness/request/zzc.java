package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.DataType;

public class zzc
  implements Parcelable.Creator<DailyTotalRequest>
{
  static void zza(DailyTotalRequest paramDailyTotalRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramDailyTotalRequest.getCallbackBinder(), false);
    zzb.zzc(paramParcel, 1000, paramDailyTotalRequest.getVersionCode());
    zzb.zza(paramParcel, 2, paramDailyTotalRequest.getDataType(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public DailyTotalRequest zzdi(Parcel paramParcel)
  {
    DataType localDataType = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    IBinder localIBinder = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localIBinder = zza.zzq(paramParcel, k);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localDataType = (DataType)zza.zza(paramParcel, k, DataType.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new DailyTotalRequest(i, localIBinder, localDataType);
  }
  
  public DailyTotalRequest[] zzfi(int paramInt)
  {
    return new DailyTotalRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */