package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveId;

public class zzj
  implements Parcelable.Creator<ControlProgressRequest>
{
  static void zza(ControlProgressRequest paramControlProgressRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramControlProgressRequest.mVersionCode);
    zzb.zzc(paramParcel, 2, paramControlProgressRequest.zzaqq);
    zzb.zzc(paramParcel, 3, paramControlProgressRequest.zzaqr);
    zzb.zza(paramParcel, 4, paramControlProgressRequest.zzaoz, paramInt, false);
    zzb.zza(paramParcel, 5, paramControlProgressRequest.zzaqs, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public ControlProgressRequest zzbh(Parcel paramParcel)
  {
    ParcelableTransferPreferences localParcelableTransferPreferences = null;
    int i = 0;
    int m = zza.zzau(paramParcel);
    DriveId localDriveId = null;
    int j = 0;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zza.zzat(paramParcel);
      switch (zza.zzca(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        k = zza.zzg(paramParcel, n);
        break;
      case 2: 
        j = zza.zzg(paramParcel, n);
        break;
      case 3: 
        i = zza.zzg(paramParcel, n);
        break;
      case 4: 
        localDriveId = (DriveId)zza.zza(paramParcel, n, DriveId.CREATOR);
        break;
      case 5: 
        localParcelableTransferPreferences = (ParcelableTransferPreferences)zza.zza(paramParcel, n, ParcelableTransferPreferences.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new ControlProgressRequest(k, j, i, localDriveId, localParcelableTransferPreferences);
  }
  
  public ControlProgressRequest[] zzcX(int paramInt)
  {
    return new ControlProgressRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */