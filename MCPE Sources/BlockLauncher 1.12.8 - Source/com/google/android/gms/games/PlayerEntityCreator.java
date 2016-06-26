package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;

public class PlayerEntityCreator
  implements Parcelable.Creator<PlayerEntity>
{
  static void zza(PlayerEntity paramPlayerEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramPlayerEntity.getPlayerId(), false);
    zzb.zza(paramParcel, 2, paramPlayerEntity.getDisplayName(), false);
    zzb.zza(paramParcel, 3, paramPlayerEntity.getIconImageUri(), paramInt, false);
    zzb.zza(paramParcel, 4, paramPlayerEntity.getHiResImageUri(), paramInt, false);
    zzb.zza(paramParcel, 5, paramPlayerEntity.getRetrievedTimestamp());
    zzb.zzc(paramParcel, 6, paramPlayerEntity.zzvG());
    zzb.zza(paramParcel, 7, paramPlayerEntity.getLastPlayedWithTimestamp());
    zzb.zza(paramParcel, 8, paramPlayerEntity.getIconImageUrl(), false);
    zzb.zza(paramParcel, 9, paramPlayerEntity.getHiResImageUrl(), false);
    zzb.zza(paramParcel, 14, paramPlayerEntity.getTitle(), false);
    zzb.zza(paramParcel, 15, paramPlayerEntity.zzvI(), paramInt, false);
    zzb.zza(paramParcel, 16, paramPlayerEntity.getLevelInfo(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramPlayerEntity.getVersionCode());
    zzb.zza(paramParcel, 19, paramPlayerEntity.zzvF());
    zzb.zza(paramParcel, 18, paramPlayerEntity.zzvH());
    zzb.zza(paramParcel, 21, paramPlayerEntity.getName(), false);
    zzb.zza(paramParcel, 20, paramPlayerEntity.zzvE(), false);
    zzb.zza(paramParcel, 23, paramPlayerEntity.getBannerImageLandscapeUrl(), false);
    zzb.zza(paramParcel, 22, paramPlayerEntity.getBannerImageLandscapeUri(), paramInt, false);
    zzb.zza(paramParcel, 25, paramPlayerEntity.getBannerImagePortraitUrl(), false);
    zzb.zza(paramParcel, 24, paramPlayerEntity.getBannerImagePortraitUri(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public PlayerEntity zzeb(Parcel paramParcel)
  {
    int k = zza.zzau(paramParcel);
    int j = 0;
    String str9 = null;
    String str8 = null;
    Uri localUri4 = null;
    Uri localUri3 = null;
    long l2 = 0L;
    int i = 0;
    long l1 = 0L;
    String str7 = null;
    String str6 = null;
    String str5 = null;
    MostRecentGameInfoEntity localMostRecentGameInfoEntity = null;
    PlayerLevelInfo localPlayerLevelInfo = null;
    boolean bool2 = false;
    boolean bool1 = false;
    String str4 = null;
    String str3 = null;
    Uri localUri2 = null;
    String str2 = null;
    Uri localUri1 = null;
    String str1 = null;
    while (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      switch (zza.zzca(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        str9 = zza.zzp(paramParcel, m);
        break;
      case 2: 
        str8 = zza.zzp(paramParcel, m);
        break;
      case 3: 
        localUri4 = (Uri)zza.zza(paramParcel, m, Uri.CREATOR);
        break;
      case 4: 
        localUri3 = (Uri)zza.zza(paramParcel, m, Uri.CREATOR);
        break;
      case 5: 
        l2 = zza.zzi(paramParcel, m);
        break;
      case 6: 
        i = zza.zzg(paramParcel, m);
        break;
      case 7: 
        l1 = zza.zzi(paramParcel, m);
        break;
      case 8: 
        str7 = zza.zzp(paramParcel, m);
        break;
      case 9: 
        str6 = zza.zzp(paramParcel, m);
        break;
      case 14: 
        str5 = zza.zzp(paramParcel, m);
        break;
      case 15: 
        localMostRecentGameInfoEntity = (MostRecentGameInfoEntity)zza.zza(paramParcel, m, MostRecentGameInfoEntity.CREATOR);
        break;
      case 16: 
        localPlayerLevelInfo = (PlayerLevelInfo)zza.zza(paramParcel, m, PlayerLevelInfo.CREATOR);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, m);
        break;
      case 19: 
        bool1 = zza.zzc(paramParcel, m);
        break;
      case 18: 
        bool2 = zza.zzc(paramParcel, m);
        break;
      case 21: 
        str3 = zza.zzp(paramParcel, m);
        break;
      case 20: 
        str4 = zza.zzp(paramParcel, m);
        break;
      case 23: 
        str2 = zza.zzp(paramParcel, m);
        break;
      case 22: 
        localUri2 = (Uri)zza.zza(paramParcel, m, Uri.CREATOR);
        break;
      case 25: 
        str1 = zza.zzp(paramParcel, m);
        break;
      case 24: 
        localUri1 = (Uri)zza.zza(paramParcel, m, Uri.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new PlayerEntity(j, str9, str8, localUri4, localUri3, l2, i, l1, str7, str6, str5, localMostRecentGameInfoEntity, localPlayerLevelInfo, bool2, bool1, str4, str3, localUri2, str2, localUri1, str1);
  }
  
  public PlayerEntity[] zzgd(int paramInt)
  {
    return new PlayerEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\PlayerEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */