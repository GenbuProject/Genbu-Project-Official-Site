package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import java.util.ArrayList;

public class GameRequestEntityCreator
  implements Parcelable.Creator<GameRequestEntity>
{
  static void zza(GameRequestEntity paramGameRequestEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramGameRequestEntity.getGame(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramGameRequestEntity.getVersionCode());
    zzb.zza(paramParcel, 2, paramGameRequestEntity.getSender(), paramInt, false);
    zzb.zza(paramParcel, 3, paramGameRequestEntity.getData(), false);
    zzb.zza(paramParcel, 4, paramGameRequestEntity.getRequestId(), false);
    zzb.zzc(paramParcel, 5, paramGameRequestEntity.getRecipients(), false);
    zzb.zzc(paramParcel, 7, paramGameRequestEntity.getType());
    zzb.zza(paramParcel, 9, paramGameRequestEntity.getCreationTimestamp());
    zzb.zza(paramParcel, 10, paramGameRequestEntity.getExpirationTimestamp());
    zzb.zza(paramParcel, 11, paramGameRequestEntity.zzxT(), false);
    zzb.zzc(paramParcel, 12, paramGameRequestEntity.getStatus());
    zzb.zzI(paramParcel, i);
  }
  
  public GameRequestEntity zzez(Parcel paramParcel)
  {
    int m = zza.zzau(paramParcel);
    int k = 0;
    GameEntity localGameEntity = null;
    PlayerEntity localPlayerEntity = null;
    byte[] arrayOfByte = null;
    String str = null;
    ArrayList localArrayList = null;
    int j = 0;
    long l2 = 0L;
    long l1 = 0L;
    Bundle localBundle = null;
    int i = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zza.zzat(paramParcel);
      switch (zza.zzca(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        localGameEntity = (GameEntity)zza.zza(paramParcel, n, GameEntity.CREATOR);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, n);
        break;
      case 2: 
        localPlayerEntity = (PlayerEntity)zza.zza(paramParcel, n, PlayerEntity.CREATOR);
        break;
      case 3: 
        arrayOfByte = zza.zzs(paramParcel, n);
        break;
      case 4: 
        str = zza.zzp(paramParcel, n);
        break;
      case 5: 
        localArrayList = zza.zzc(paramParcel, n, PlayerEntity.CREATOR);
        break;
      case 7: 
        j = zza.zzg(paramParcel, n);
        break;
      case 9: 
        l2 = zza.zzi(paramParcel, n);
        break;
      case 10: 
        l1 = zza.zzi(paramParcel, n);
        break;
      case 11: 
        localBundle = zza.zzr(paramParcel, n);
        break;
      case 12: 
        i = zza.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new GameRequestEntity(k, localGameEntity, localPlayerEntity, arrayOfByte, str, localArrayList, j, l2, l1, localBundle, i);
  }
  
  public GameRequestEntity[] zzgP(int paramInt)
  {
    return new GameRequestEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\request\GameRequestEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */