package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.Subscription;

public class zzaf
  implements Parcelable.Creator<SubscribeRequest>
{
  static void zza(SubscribeRequest paramSubscribeRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramSubscribeRequest.zzvh(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramSubscribeRequest.getVersionCode());
    zzb.zza(paramParcel, 2, paramSubscribeRequest.zzvi());
    zzb.zza(paramParcel, 3, paramSubscribeRequest.getCallbackBinder(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public SubscribeRequest zzdK(Parcel paramParcel)
  {
    IBinder localIBinder = null;
    boolean bool = false;
    int j = zza.zzau(paramParcel);
    Subscription localSubscription = null;
    int i = 0;
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
        localSubscription = (Subscription)zza.zza(paramParcel, k, Subscription.CREATOR);
        continue;
        i = zza.zzg(paramParcel, k);
        continue;
        bool = zza.zzc(paramParcel, k);
        continue;
        localIBinder = zza.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new SubscribeRequest(i, localSubscription, bool, localIBinder);
  }
  
  public SubscribeRequest[] zzfL(int paramInt)
  {
    return new SubscribeRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\zzaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */