package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;

public class TurnBasedMatchEntityCreator
  implements Parcelable.Creator<TurnBasedMatchEntity>
{
  static void zza(TurnBasedMatchEntity paramTurnBasedMatchEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramTurnBasedMatchEntity.getGame(), paramInt, false);
    zzb.zza(paramParcel, 2, paramTurnBasedMatchEntity.getMatchId(), false);
    zzb.zza(paramParcel, 3, paramTurnBasedMatchEntity.getCreatorId(), false);
    zzb.zza(paramParcel, 4, paramTurnBasedMatchEntity.getCreationTimestamp());
    zzb.zza(paramParcel, 5, paramTurnBasedMatchEntity.getLastUpdaterId(), false);
    zzb.zza(paramParcel, 6, paramTurnBasedMatchEntity.getLastUpdatedTimestamp());
    zzb.zza(paramParcel, 7, paramTurnBasedMatchEntity.getPendingParticipantId(), false);
    zzb.zzc(paramParcel, 8, paramTurnBasedMatchEntity.getStatus());
    zzb.zzc(paramParcel, 10, paramTurnBasedMatchEntity.getVariant());
    zzb.zzc(paramParcel, 11, paramTurnBasedMatchEntity.getVersion());
    zzb.zza(paramParcel, 12, paramTurnBasedMatchEntity.getData(), false);
    zzb.zzc(paramParcel, 13, paramTurnBasedMatchEntity.getParticipants(), false);
    zzb.zza(paramParcel, 14, paramTurnBasedMatchEntity.getRematchId(), false);
    zzb.zza(paramParcel, 15, paramTurnBasedMatchEntity.getPreviousMatchData(), false);
    zzb.zza(paramParcel, 17, paramTurnBasedMatchEntity.getAutoMatchCriteria(), false);
    zzb.zzc(paramParcel, 16, paramTurnBasedMatchEntity.getMatchNumber());
    zzb.zzc(paramParcel, 1000, paramTurnBasedMatchEntity.getVersionCode());
    zzb.zza(paramParcel, 19, paramTurnBasedMatchEntity.isLocallyModified());
    zzb.zzc(paramParcel, 18, paramTurnBasedMatchEntity.getTurnStatus());
    zzb.zza(paramParcel, 21, paramTurnBasedMatchEntity.getDescriptionParticipantId(), false);
    zzb.zza(paramParcel, 20, paramTurnBasedMatchEntity.getDescription(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public TurnBasedMatchEntity zzew(Parcel paramParcel)
  {
    int i2 = zza.zzau(paramParcel);
    int i1 = 0;
    GameEntity localGameEntity = null;
    String str7 = null;
    String str6 = null;
    long l2 = 0L;
    String str5 = null;
    long l1 = 0L;
    String str4 = null;
    int n = 0;
    int m = 0;
    int k = 0;
    byte[] arrayOfByte2 = null;
    ArrayList localArrayList = null;
    String str3 = null;
    byte[] arrayOfByte1 = null;
    int j = 0;
    Bundle localBundle = null;
    int i = 0;
    boolean bool = false;
    String str2 = null;
    String str1 = null;
    while (paramParcel.dataPosition() < i2)
    {
      int i3 = zza.zzat(paramParcel);
      switch (zza.zzca(i3))
      {
      default: 
        zza.zzb(paramParcel, i3);
        break;
      case 1: 
        localGameEntity = (GameEntity)zza.zza(paramParcel, i3, GameEntity.CREATOR);
        break;
      case 2: 
        str7 = zza.zzp(paramParcel, i3);
        break;
      case 3: 
        str6 = zza.zzp(paramParcel, i3);
        break;
      case 4: 
        l2 = zza.zzi(paramParcel, i3);
        break;
      case 5: 
        str5 = zza.zzp(paramParcel, i3);
        break;
      case 6: 
        l1 = zza.zzi(paramParcel, i3);
        break;
      case 7: 
        str4 = zza.zzp(paramParcel, i3);
        break;
      case 8: 
        n = zza.zzg(paramParcel, i3);
        break;
      case 10: 
        m = zza.zzg(paramParcel, i3);
        break;
      case 11: 
        k = zza.zzg(paramParcel, i3);
        break;
      case 12: 
        arrayOfByte2 = zza.zzs(paramParcel, i3);
        break;
      case 13: 
        localArrayList = zza.zzc(paramParcel, i3, ParticipantEntity.CREATOR);
        break;
      case 14: 
        str3 = zza.zzp(paramParcel, i3);
        break;
      case 15: 
        arrayOfByte1 = zza.zzs(paramParcel, i3);
        break;
      case 17: 
        localBundle = zza.zzr(paramParcel, i3);
        break;
      case 16: 
        j = zza.zzg(paramParcel, i3);
        break;
      case 1000: 
        i1 = zza.zzg(paramParcel, i3);
        break;
      case 19: 
        bool = zza.zzc(paramParcel, i3);
        break;
      case 18: 
        i = zza.zzg(paramParcel, i3);
        break;
      case 21: 
        str1 = zza.zzp(paramParcel, i3);
        break;
      case 20: 
        str2 = zza.zzp(paramParcel, i3);
      }
    }
    if (paramParcel.dataPosition() != i2) {
      throw new zza.zza("Overread allowed size end=" + i2, paramParcel);
    }
    return new TurnBasedMatchEntity(i1, localGameEntity, str7, str6, l2, str5, l1, str4, n, m, k, arrayOfByte2, localArrayList, str3, arrayOfByte1, j, localBundle, i, bool, str2, str1);
  }
  
  public TurnBasedMatchEntity[] zzgM(int paramInt)
  {
    return new TurnBasedMatchEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\multiplayer\turnbased\TurnBasedMatchEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */