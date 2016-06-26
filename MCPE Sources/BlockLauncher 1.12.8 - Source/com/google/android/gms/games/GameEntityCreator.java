package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class GameEntityCreator
  implements Parcelable.Creator<GameEntity>
{
  static void zza(GameEntity paramGameEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramGameEntity.getApplicationId(), false);
    zzb.zza(paramParcel, 2, paramGameEntity.getDisplayName(), false);
    zzb.zza(paramParcel, 3, paramGameEntity.getPrimaryCategory(), false);
    zzb.zza(paramParcel, 4, paramGameEntity.getSecondaryCategory(), false);
    zzb.zza(paramParcel, 5, paramGameEntity.getDescription(), false);
    zzb.zza(paramParcel, 6, paramGameEntity.getDeveloperName(), false);
    zzb.zza(paramParcel, 7, paramGameEntity.getIconImageUri(), paramInt, false);
    zzb.zza(paramParcel, 8, paramGameEntity.getHiResImageUri(), paramInt, false);
    zzb.zza(paramParcel, 9, paramGameEntity.getFeaturedImageUri(), paramInt, false);
    zzb.zza(paramParcel, 10, paramGameEntity.zzvx());
    zzb.zza(paramParcel, 11, paramGameEntity.zzvz());
    zzb.zza(paramParcel, 12, paramGameEntity.zzvA(), false);
    zzb.zzc(paramParcel, 13, paramGameEntity.zzvB());
    zzb.zzc(paramParcel, 14, paramGameEntity.getAchievementTotalCount());
    zzb.zzc(paramParcel, 15, paramGameEntity.getLeaderboardCount());
    zzb.zza(paramParcel, 17, paramGameEntity.isTurnBasedMultiplayerEnabled());
    zzb.zza(paramParcel, 16, paramGameEntity.isRealTimeMultiplayerEnabled());
    zzb.zzc(paramParcel, 1000, paramGameEntity.getVersionCode());
    zzb.zza(paramParcel, 19, paramGameEntity.getHiResImageUrl(), false);
    zzb.zza(paramParcel, 18, paramGameEntity.getIconImageUrl(), false);
    zzb.zza(paramParcel, 21, paramGameEntity.isMuted());
    zzb.zza(paramParcel, 20, paramGameEntity.getFeaturedImageUrl(), false);
    zzb.zza(paramParcel, 23, paramGameEntity.areSnapshotsEnabled());
    zzb.zza(paramParcel, 22, paramGameEntity.zzvy());
    zzb.zza(paramParcel, 25, paramGameEntity.hasGamepadSupport());
    zzb.zza(paramParcel, 24, paramGameEntity.getThemeColor(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public GameEntity zzea(Parcel paramParcel)
  {
    int n = zza.zzau(paramParcel);
    int m = 0;
    String str11 = null;
    String str10 = null;
    String str9 = null;
    String str8 = null;
    String str7 = null;
    String str6 = null;
    Uri localUri3 = null;
    Uri localUri2 = null;
    Uri localUri1 = null;
    boolean bool8 = false;
    boolean bool7 = false;
    String str5 = null;
    int k = 0;
    int j = 0;
    int i = 0;
    boolean bool6 = false;
    boolean bool5 = false;
    String str4 = null;
    String str3 = null;
    String str2 = null;
    boolean bool4 = false;
    boolean bool3 = false;
    boolean bool2 = false;
    String str1 = null;
    boolean bool1 = false;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = zza.zzat(paramParcel);
      switch (zza.zzca(i1))
      {
      default: 
        zza.zzb(paramParcel, i1);
        break;
      case 1: 
        str11 = zza.zzp(paramParcel, i1);
        break;
      case 2: 
        str10 = zza.zzp(paramParcel, i1);
        break;
      case 3: 
        str9 = zza.zzp(paramParcel, i1);
        break;
      case 4: 
        str8 = zza.zzp(paramParcel, i1);
        break;
      case 5: 
        str7 = zza.zzp(paramParcel, i1);
        break;
      case 6: 
        str6 = zza.zzp(paramParcel, i1);
        break;
      case 7: 
        localUri3 = (Uri)zza.zza(paramParcel, i1, Uri.CREATOR);
        break;
      case 8: 
        localUri2 = (Uri)zza.zza(paramParcel, i1, Uri.CREATOR);
        break;
      case 9: 
        localUri1 = (Uri)zza.zza(paramParcel, i1, Uri.CREATOR);
        break;
      case 10: 
        bool8 = zza.zzc(paramParcel, i1);
        break;
      case 11: 
        bool7 = zza.zzc(paramParcel, i1);
        break;
      case 12: 
        str5 = zza.zzp(paramParcel, i1);
        break;
      case 13: 
        k = zza.zzg(paramParcel, i1);
        break;
      case 14: 
        j = zza.zzg(paramParcel, i1);
        break;
      case 15: 
        i = zza.zzg(paramParcel, i1);
        break;
      case 17: 
        bool5 = zza.zzc(paramParcel, i1);
        break;
      case 16: 
        bool6 = zza.zzc(paramParcel, i1);
        break;
      case 1000: 
        m = zza.zzg(paramParcel, i1);
        break;
      case 19: 
        str3 = zza.zzp(paramParcel, i1);
        break;
      case 18: 
        str4 = zza.zzp(paramParcel, i1);
        break;
      case 21: 
        bool4 = zza.zzc(paramParcel, i1);
        break;
      case 20: 
        str2 = zza.zzp(paramParcel, i1);
        break;
      case 23: 
        bool2 = zza.zzc(paramParcel, i1);
        break;
      case 22: 
        bool3 = zza.zzc(paramParcel, i1);
        break;
      case 25: 
        bool1 = zza.zzc(paramParcel, i1);
        break;
      case 24: 
        str1 = zza.zzp(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new zza.zza("Overread allowed size end=" + n, paramParcel);
    }
    return new GameEntity(m, str11, str10, str9, str8, str7, str6, localUri3, localUri2, localUri1, bool8, bool7, str5, k, j, i, bool6, bool5, str4, str3, str2, bool4, bool3, bool2, str1, bool1);
  }
  
  public GameEntity[] zzgb(int paramInt)
  {
    return new GameEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\GameEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */