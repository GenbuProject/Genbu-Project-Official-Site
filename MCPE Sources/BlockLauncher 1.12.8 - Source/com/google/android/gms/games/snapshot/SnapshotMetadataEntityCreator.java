package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;

public class SnapshotMetadataEntityCreator
  implements Parcelable.Creator<SnapshotMetadataEntity>
{
  static void zza(SnapshotMetadataEntity paramSnapshotMetadataEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramSnapshotMetadataEntity.getGame(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramSnapshotMetadataEntity.getVersionCode());
    zzb.zza(paramParcel, 2, paramSnapshotMetadataEntity.getOwner(), paramInt, false);
    zzb.zza(paramParcel, 3, paramSnapshotMetadataEntity.getSnapshotId(), false);
    zzb.zza(paramParcel, 5, paramSnapshotMetadataEntity.getCoverImageUri(), paramInt, false);
    zzb.zza(paramParcel, 6, paramSnapshotMetadataEntity.getCoverImageUrl(), false);
    zzb.zza(paramParcel, 7, paramSnapshotMetadataEntity.getTitle(), false);
    zzb.zza(paramParcel, 8, paramSnapshotMetadataEntity.getDescription(), false);
    zzb.zza(paramParcel, 9, paramSnapshotMetadataEntity.getLastModifiedTimestamp());
    zzb.zza(paramParcel, 10, paramSnapshotMetadataEntity.getPlayedTime());
    zzb.zza(paramParcel, 11, paramSnapshotMetadataEntity.getCoverImageAspectRatio());
    zzb.zza(paramParcel, 12, paramSnapshotMetadataEntity.getUniqueName(), false);
    zzb.zza(paramParcel, 13, paramSnapshotMetadataEntity.hasChangePending());
    zzb.zza(paramParcel, 14, paramSnapshotMetadataEntity.getProgressValue());
    zzb.zza(paramParcel, 15, paramSnapshotMetadataEntity.getDeviceName(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public SnapshotMetadataEntity zzeD(Parcel paramParcel)
  {
    int j = zza.zzau(paramParcel);
    int i = 0;
    GameEntity localGameEntity = null;
    PlayerEntity localPlayerEntity = null;
    String str6 = null;
    Uri localUri = null;
    String str5 = null;
    String str4 = null;
    String str3 = null;
    long l3 = 0L;
    long l2 = 0L;
    float f = 0.0F;
    String str2 = null;
    boolean bool = false;
    long l1 = 0L;
    String str1 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localGameEntity = (GameEntity)zza.zza(paramParcel, k, GameEntity.CREATOR);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localPlayerEntity = (PlayerEntity)zza.zza(paramParcel, k, PlayerEntity.CREATOR);
        break;
      case 3: 
        str6 = zza.zzp(paramParcel, k);
        break;
      case 5: 
        localUri = (Uri)zza.zza(paramParcel, k, Uri.CREATOR);
        break;
      case 6: 
        str5 = zza.zzp(paramParcel, k);
        break;
      case 7: 
        str4 = zza.zzp(paramParcel, k);
        break;
      case 8: 
        str3 = zza.zzp(paramParcel, k);
        break;
      case 9: 
        l3 = zza.zzi(paramParcel, k);
        break;
      case 10: 
        l2 = zza.zzi(paramParcel, k);
        break;
      case 11: 
        f = zza.zzl(paramParcel, k);
        break;
      case 12: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 13: 
        bool = zza.zzc(paramParcel, k);
        break;
      case 14: 
        l1 = zza.zzi(paramParcel, k);
        break;
      case 15: 
        str1 = zza.zzp(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new SnapshotMetadataEntity(i, localGameEntity, localPlayerEntity, str6, localUri, str5, str4, str3, l3, l2, f, str2, bool, l1, str1);
  }
  
  public SnapshotMetadataEntity[] zzgU(int paramInt)
  {
    return new SnapshotMetadataEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\snapshot\SnapshotMetadataEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */