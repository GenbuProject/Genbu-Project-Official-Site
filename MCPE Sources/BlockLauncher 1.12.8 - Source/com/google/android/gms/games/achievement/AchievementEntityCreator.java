package com.google.android.gms.games.achievement;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.PlayerEntity;

public class AchievementEntityCreator
  implements Parcelable.Creator<AchievementEntity>
{
  static void zza(AchievementEntity paramAchievementEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramAchievementEntity.getAchievementId(), false);
    zzb.zzc(paramParcel, 2, paramAchievementEntity.getType());
    zzb.zza(paramParcel, 3, paramAchievementEntity.getName(), false);
    zzb.zza(paramParcel, 4, paramAchievementEntity.getDescription(), false);
    zzb.zza(paramParcel, 5, paramAchievementEntity.getUnlockedImageUri(), paramInt, false);
    zzb.zza(paramParcel, 6, paramAchievementEntity.getUnlockedImageUrl(), false);
    zzb.zza(paramParcel, 7, paramAchievementEntity.getRevealedImageUri(), paramInt, false);
    zzb.zza(paramParcel, 8, paramAchievementEntity.getRevealedImageUrl(), false);
    zzb.zzc(paramParcel, 9, paramAchievementEntity.zzvK());
    zzb.zza(paramParcel, 10, paramAchievementEntity.zzvL(), false);
    zzb.zza(paramParcel, 11, paramAchievementEntity.getPlayer(), paramInt, false);
    zzb.zzc(paramParcel, 12, paramAchievementEntity.getState());
    zzb.zzc(paramParcel, 13, paramAchievementEntity.zzvM());
    zzb.zza(paramParcel, 14, paramAchievementEntity.zzvN(), false);
    zzb.zza(paramParcel, 15, paramAchievementEntity.getLastUpdatedTimestamp());
    zzb.zza(paramParcel, 16, paramAchievementEntity.getXpValue());
    zzb.zzc(paramParcel, 1000, paramAchievementEntity.getVersionCode());
    zzb.zzI(paramParcel, i);
  }
  
  public AchievementEntity zzee(Parcel paramParcel)
  {
    int i1 = zza.zzau(paramParcel);
    int n = 0;
    String str7 = null;
    int m = 0;
    String str6 = null;
    String str5 = null;
    Uri localUri2 = null;
    String str4 = null;
    Uri localUri1 = null;
    String str3 = null;
    int k = 0;
    String str2 = null;
    PlayerEntity localPlayerEntity = null;
    int j = 0;
    int i = 0;
    String str1 = null;
    long l2 = 0L;
    long l1 = 0L;
    while (paramParcel.dataPosition() < i1)
    {
      int i2 = zza.zzat(paramParcel);
      switch (zza.zzca(i2))
      {
      default: 
        zza.zzb(paramParcel, i2);
        break;
      case 1: 
        str7 = zza.zzp(paramParcel, i2);
        break;
      case 2: 
        m = zza.zzg(paramParcel, i2);
        break;
      case 3: 
        str6 = zza.zzp(paramParcel, i2);
        break;
      case 4: 
        str5 = zza.zzp(paramParcel, i2);
        break;
      case 5: 
        localUri2 = (Uri)zza.zza(paramParcel, i2, Uri.CREATOR);
        break;
      case 6: 
        str4 = zza.zzp(paramParcel, i2);
        break;
      case 7: 
        localUri1 = (Uri)zza.zza(paramParcel, i2, Uri.CREATOR);
        break;
      case 8: 
        str3 = zza.zzp(paramParcel, i2);
        break;
      case 9: 
        k = zza.zzg(paramParcel, i2);
        break;
      case 10: 
        str2 = zza.zzp(paramParcel, i2);
        break;
      case 11: 
        localPlayerEntity = (PlayerEntity)zza.zza(paramParcel, i2, PlayerEntity.CREATOR);
        break;
      case 12: 
        j = zza.zzg(paramParcel, i2);
        break;
      case 13: 
        i = zza.zzg(paramParcel, i2);
        break;
      case 14: 
        str1 = zza.zzp(paramParcel, i2);
        break;
      case 15: 
        l2 = zza.zzi(paramParcel, i2);
        break;
      case 16: 
        l1 = zza.zzi(paramParcel, i2);
        break;
      case 1000: 
        n = zza.zzg(paramParcel, i2);
      }
    }
    if (paramParcel.dataPosition() != i1) {
      throw new zza.zza("Overread allowed size end=" + i1, paramParcel);
    }
    return new AchievementEntity(n, str7, m, str6, str5, localUri2, str4, localUri1, str3, k, str2, localPlayerEntity, j, i, str1, l2, l1);
  }
  
  public AchievementEntity[] zzgg(int paramInt)
  {
    return new AchievementEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\achievement\AchievementEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */