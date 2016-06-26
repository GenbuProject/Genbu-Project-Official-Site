package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.TransferProgressOptions;

public class zzbp
  implements Parcelable.Creator<RemoveEventListenerRequest>
{
  static void zza(RemoveEventListenerRequest paramRemoveEventListenerRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramRemoveEventListenerRequest.mVersionCode);
    zzb.zza(paramParcel, 2, paramRemoveEventListenerRequest.zzaoz, paramInt, false);
    zzb.zzc(paramParcel, 3, paramRemoveEventListenerRequest.zzanf);
    zzb.zza(paramParcel, 4, paramRemoveEventListenerRequest.zzapZ, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public RemoveEventListenerRequest zzbR(Parcel paramParcel)
  {
    TransferProgressOptions localTransferProgressOptions = null;
    int j = 0;
    int m = zza.zzau(paramParcel);
    DriveId localDriveId = null;
    int i = 0;
    if (paramParcel.dataPosition() < m)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        k = j;
        j = i;
        i = k;
      }
      for (;;)
      {
        k = j;
        j = i;
        i = k;
        break;
        k = zza.zzg(paramParcel, k);
        i = j;
        j = k;
        continue;
        localDriveId = (DriveId)zza.zza(paramParcel, k, DriveId.CREATOR);
        k = i;
        i = j;
        j = k;
        continue;
        k = zza.zzg(paramParcel, k);
        j = i;
        i = k;
        continue;
        localTransferProgressOptions = (TransferProgressOptions)zza.zza(paramParcel, k, TransferProgressOptions.CREATOR);
        k = i;
        i = j;
        j = k;
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new RemoveEventListenerRequest(i, localDriveId, j, localTransferProgressOptions);
  }
  
  public RemoveEventListenerRequest[] zzdM(int paramInt)
  {
    return new RemoveEventListenerRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzbp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */