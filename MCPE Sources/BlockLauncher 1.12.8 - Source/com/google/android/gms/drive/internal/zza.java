package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.ChangesAvailableOptions;
import com.google.android.gms.drive.events.TransferProgressOptions;
import com.google.android.gms.drive.events.TransferStateOptions;

public class zza
  implements Parcelable.Creator<AddEventListenerRequest>
{
  static void zza(AddEventListenerRequest paramAddEventListenerRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramAddEventListenerRequest.mVersionCode);
    zzb.zza(paramParcel, 2, paramAddEventListenerRequest.zzaoz, paramInt, false);
    zzb.zzc(paramParcel, 3, paramAddEventListenerRequest.zzanf);
    zzb.zza(paramParcel, 4, paramAddEventListenerRequest.zzapy, paramInt, false);
    zzb.zza(paramParcel, 5, paramAddEventListenerRequest.zzapY, paramInt, false);
    zzb.zza(paramParcel, 6, paramAddEventListenerRequest.zzapZ, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public AddEventListenerRequest zzaZ(Parcel paramParcel)
  {
    int i = 0;
    TransferProgressOptions localTransferProgressOptions = null;
    int k = com.google.android.gms.common.internal.safeparcel.zza.zzau(paramParcel);
    TransferStateOptions localTransferStateOptions = null;
    ChangesAvailableOptions localChangesAvailableOptions = null;
    DriveId localDriveId = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = com.google.android.gms.common.internal.safeparcel.zza.zzat(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, m);
        break;
      case 1: 
        j = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 2: 
        localDriveId = (DriveId)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, DriveId.CREATOR);
        break;
      case 3: 
        i = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 4: 
        localChangesAvailableOptions = (ChangesAvailableOptions)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ChangesAvailableOptions.CREATOR);
        break;
      case 5: 
        localTransferStateOptions = (TransferStateOptions)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, TransferStateOptions.CREATOR);
        break;
      case 6: 
        localTransferProgressOptions = (TransferProgressOptions)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, TransferProgressOptions.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new AddEventListenerRequest(j, localDriveId, i, localChangesAvailableOptions, localTransferStateOptions, localTransferProgressOptions);
  }
  
  public AddEventListenerRequest[] zzcP(int paramInt)
  {
    return new AddEventListenerRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */