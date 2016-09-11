package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.Subscription;
import java.util.ArrayList;

public class zzh
  implements Parcelable.Creator<ListSubscriptionsResult>
{
  static void zza(ListSubscriptionsResult paramListSubscriptionsResult, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramListSubscriptionsResult.getSubscriptions(), false);
    zzb.zzc(paramParcel, 1000, paramListSubscriptionsResult.getVersionCode());
    zzb.zza(paramParcel, 2, paramListSubscriptionsResult.getStatus(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public ListSubscriptionsResult zzdU(Parcel paramParcel)
  {
    Status localStatus = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localArrayList = zza.zzc(paramParcel, k, Subscription.CREATOR);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localStatus = (Status)zza.zza(paramParcel, k, Status.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new ListSubscriptionsResult(i, localArrayList, localStatus);
  }
  
  public ListSubscriptionsResult[] zzfV(int paramInt)
  {
    return new ListSubscriptionsResult[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\result\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */