package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

public class zzk
  implements Parcelable.Creator<DataUpdateListenerRegistrationRequest>
{
  static void zza(DataUpdateListenerRegistrationRequest paramDataUpdateListenerRegistrationRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramDataUpdateListenerRegistrationRequest.getDataSource(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramDataUpdateListenerRegistrationRequest.getVersionCode());
    zzb.zza(paramParcel, 2, paramDataUpdateListenerRegistrationRequest.getDataType(), paramInt, false);
    zzb.zza(paramParcel, 3, paramDataUpdateListenerRegistrationRequest.getIntent(), paramInt, false);
    zzb.zza(paramParcel, 4, paramDataUpdateListenerRegistrationRequest.getCallbackBinder(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public DataUpdateListenerRegistrationRequest zzdq(Parcel paramParcel)
  {
    IBinder localIBinder = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    PendingIntent localPendingIntent = null;
    DataType localDataType = null;
    DataSource localDataSource = null;
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
        localPendingIntent = (PendingIntent)zza.zza(paramParcel, k, PendingIntent.CREATOR);
        break;
      case 4: 
        localIBinder = zza.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new DataUpdateListenerRegistrationRequest(i, localDataSource, localDataType, localPendingIntent, localIBinder);
  }
  
  public DataUpdateListenerRegistrationRequest[] zzfq(int paramInt)
  {
    return new DataUpdateListenerRegistrationRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */