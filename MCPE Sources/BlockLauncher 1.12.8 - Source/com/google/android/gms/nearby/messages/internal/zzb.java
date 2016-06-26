package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;

public class zzb
  implements Parcelable.Creator<GetPermissionStatusRequest>
{
  static void zza(GetPermissionStatusRequest paramGetPermissionStatusRequest, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzb.zzav(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramGetPermissionStatusRequest.mVersionCode);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 2, paramGetPermissionStatusRequest.zzED(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 3, paramGetPermissionStatusRequest.zzbbF, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 4, paramGetPermissionStatusRequest.zzbcs, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, i);
  }
  
  public GetPermissionStatusRequest zzgh(Parcel paramParcel)
  {
    ClientAppContext localClientAppContext = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    String str = null;
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
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localIBinder = zza.zzq(paramParcel, k);
        break;
      case 3: 
        str = zza.zzp(paramParcel, k);
        break;
      case 4: 
        localClientAppContext = (ClientAppContext)zza.zza(paramParcel, k, ClientAppContext.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new GetPermissionStatusRequest(i, localIBinder, str, localClientAppContext);
  }
  
  public GetPermissionStatusRequest[] zzjm(int paramInt)
  {
    return new GetPermissionStatusRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */