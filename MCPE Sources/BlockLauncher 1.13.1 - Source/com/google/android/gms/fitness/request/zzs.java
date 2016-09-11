package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.DataType;

public class zzs
  implements Parcelable.Creator<ListSubscriptionsRequest>
{
  static void zza(ListSubscriptionsRequest paramListSubscriptionsRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramListSubscriptionsRequest.getDataType(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramListSubscriptionsRequest.getVersionCode());
    zzb.zza(paramParcel, 2, paramListSubscriptionsRequest.getCallbackBinder(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public ListSubscriptionsRequest zzdx(Parcel paramParcel)
  {
    IBinder localIBinder = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    DataType localDataType = null;
    if (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
      }
      for (;;)
      {
        break;
        localDataType = (DataType)zza.zza(paramParcel, k, DataType.CREATOR);
        continue;
        i = zza.zzg(paramParcel, k);
        continue;
        localIBinder = zza.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new ListSubscriptionsRequest(i, localDataType, localIBinder);
  }
  
  public ListSubscriptionsRequest[] zzfx(int paramInt)
  {
    return new ListSubscriptionsRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */