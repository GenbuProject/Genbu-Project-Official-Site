package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class PlayerStatsEntityCreator
  implements Parcelable.Creator<PlayerStatsEntity>
{
  static void zza(PlayerStatsEntity paramPlayerStatsEntity, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramPlayerStatsEntity.getAverageSessionLength());
    zzb.zzc(paramParcel, 1000, paramPlayerStatsEntity.getVersionCode());
    zzb.zza(paramParcel, 2, paramPlayerStatsEntity.getChurnProbability());
    zzb.zzc(paramParcel, 3, paramPlayerStatsEntity.getDaysSinceLastPlayed());
    zzb.zzc(paramParcel, 4, paramPlayerStatsEntity.getNumberOfPurchases());
    zzb.zzc(paramParcel, 5, paramPlayerStatsEntity.getNumberOfSessions());
    zzb.zza(paramParcel, 6, paramPlayerStatsEntity.getSessionPercentile());
    zzb.zza(paramParcel, 7, paramPlayerStatsEntity.getSpendPercentile());
    zzb.zza(paramParcel, 8, paramPlayerStatsEntity.zzxV(), false);
    zzb.zza(paramParcel, 9, paramPlayerStatsEntity.getSpendProbability());
    zzb.zzI(paramParcel, paramInt);
  }
  
  public PlayerStatsEntity zzeE(Parcel paramParcel)
  {
    int i = 0;
    float f1 = 0.0F;
    int n = zza.zzau(paramParcel);
    Bundle localBundle = null;
    float f2 = 0.0F;
    float f3 = 0.0F;
    int j = 0;
    int k = 0;
    float f4 = 0.0F;
    float f5 = 0.0F;
    int m = 0;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = zza.zzat(paramParcel);
      switch (zza.zzca(i1))
      {
      default: 
        zza.zzb(paramParcel, i1);
        break;
      case 1: 
        f5 = zza.zzl(paramParcel, i1);
        break;
      case 1000: 
        m = zza.zzg(paramParcel, i1);
        break;
      case 2: 
        f4 = zza.zzl(paramParcel, i1);
        break;
      case 3: 
        k = zza.zzg(paramParcel, i1);
        break;
      case 4: 
        j = zza.zzg(paramParcel, i1);
        break;
      case 5: 
        i = zza.zzg(paramParcel, i1);
        break;
      case 6: 
        f3 = zza.zzl(paramParcel, i1);
        break;
      case 7: 
        f2 = zza.zzl(paramParcel, i1);
        break;
      case 8: 
        localBundle = zza.zzr(paramParcel, i1);
        break;
      case 9: 
        f1 = zza.zzl(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new zza.zza("Overread allowed size end=" + n, paramParcel);
    }
    return new PlayerStatsEntity(m, f5, f4, k, j, i, f3, f2, localBundle, f1);
  }
  
  public PlayerStatsEntity[] zzgW(int paramInt)
  {
    return new PlayerStatsEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\stats\PlayerStatsEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */