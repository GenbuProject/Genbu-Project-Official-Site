package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class zzh
  implements Parcelable.Creator<CloseContentsAndUpdateMetadataRequest>
{
  static void zza(CloseContentsAndUpdateMetadataRequest paramCloseContentsAndUpdateMetadataRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramCloseContentsAndUpdateMetadataRequest.mVersionCode);
    zzb.zza(paramParcel, 2, paramCloseContentsAndUpdateMetadataRequest.zzaqj, paramInt, false);
    zzb.zza(paramParcel, 3, paramCloseContentsAndUpdateMetadataRequest.zzaqk, paramInt, false);
    zzb.zza(paramParcel, 4, paramCloseContentsAndUpdateMetadataRequest.zzaql, paramInt, false);
    zzb.zza(paramParcel, 5, paramCloseContentsAndUpdateMetadataRequest.zzaoW);
    zzb.zza(paramParcel, 6, paramCloseContentsAndUpdateMetadataRequest.zzaoV, false);
    zzb.zzc(paramParcel, 7, paramCloseContentsAndUpdateMetadataRequest.zzaqm);
    zzb.zzc(paramParcel, 8, paramCloseContentsAndUpdateMetadataRequest.zzaqn);
    zzb.zza(paramParcel, 9, paramCloseContentsAndUpdateMetadataRequest.zzaqo);
    zzb.zza(paramParcel, 10, paramCloseContentsAndUpdateMetadataRequest.zzapa);
    zzb.zzI(paramParcel, i);
  }
  
  public CloseContentsAndUpdateMetadataRequest zzbf(Parcel paramParcel)
  {
    String str = null;
    boolean bool2 = false;
    int m = zza.zzau(paramParcel);
    boolean bool1 = true;
    int i = 0;
    int j = 0;
    boolean bool3 = false;
    Contents localContents = null;
    MetadataBundle localMetadataBundle = null;
    DriveId localDriveId = null;
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
        localDriveId = (DriveId)zza.zza(paramParcel, n, DriveId.CREATOR);
        break;
      case 3: 
        localMetadataBundle = (MetadataBundle)zza.zza(paramParcel, n, MetadataBundle.CREATOR);
        break;
      case 4: 
        localContents = (Contents)zza.zza(paramParcel, n, Contents.CREATOR);
        break;
      case 5: 
        bool3 = zza.zzc(paramParcel, n);
        break;
      case 6: 
        str = zza.zzp(paramParcel, n);
        break;
      case 7: 
        j = zza.zzg(paramParcel, n);
        break;
      case 8: 
        i = zza.zzg(paramParcel, n);
        break;
      case 9: 
        bool2 = zza.zzc(paramParcel, n);
        break;
      case 10: 
        bool1 = zza.zzc(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new CloseContentsAndUpdateMetadataRequest(k, localDriveId, localMetadataBundle, localContents, bool3, str, j, i, bool2, bool1);
  }
  
  public CloseContentsAndUpdateMetadataRequest[] zzcV(int paramInt)
  {
    return new CloseContentsAndUpdateMetadataRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */