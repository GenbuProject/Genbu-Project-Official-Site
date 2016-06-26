package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc
  implements Parcelable.Creator<HandleClientLifecycleEventRequest>
{
  static void zza(HandleClientLifecycleEventRequest paramHandleClientLifecycleEventRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramHandleClientLifecycleEventRequest.versionCode);
    zzb.zza(paramParcel, 2, paramHandleClientLifecycleEventRequest.zzbcs, paramInt, false);
    zzb.zzc(paramParcel, 3, paramHandleClientLifecycleEventRequest.zzbct);
    zzb.zzI(paramParcel, i);
  }
  
  public HandleClientLifecycleEventRequest zzgi(Parcel paramParcel)
  {
    int i = 0;
    int k = zza.zzau(paramParcel);
    ClientAppContext localClientAppContext = null;
    int j = 0;
    if (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      switch (zza.zzca(m))
      {
      default: 
        zza.zzb(paramParcel, m);
      }
      for (;;)
      {
        break;
        j = zza.zzg(paramParcel, m);
        continue;
        localClientAppContext = (ClientAppContext)zza.zza(paramParcel, m, ClientAppContext.CREATOR);
        continue;
        i = zza.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new HandleClientLifecycleEventRequest(j, localClientAppContext, i);
  }
  
  public HandleClientLifecycleEventRequest[] zzjn(int paramInt)
  {
    return new HandleClientLifecycleEventRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\internal\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */