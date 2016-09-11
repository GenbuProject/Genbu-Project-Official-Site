package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.PlayerEntity;

public class ParticipantEntityCreator
  implements Parcelable.Creator<ParticipantEntity>
{
  static void zza(ParticipantEntity paramParticipantEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramParticipantEntity.getParticipantId(), false);
    zzb.zzc(paramParcel, 1000, paramParticipantEntity.getVersionCode());
    zzb.zza(paramParcel, 2, paramParticipantEntity.getDisplayName(), false);
    zzb.zza(paramParcel, 3, paramParticipantEntity.getIconImageUri(), paramInt, false);
    zzb.zza(paramParcel, 4, paramParticipantEntity.getHiResImageUri(), paramInt, false);
    zzb.zzc(paramParcel, 5, paramParticipantEntity.getStatus());
    zzb.zza(paramParcel, 6, paramParticipantEntity.zzwt(), false);
    zzb.zza(paramParcel, 7, paramParticipantEntity.isConnectedToRoom());
    zzb.zza(paramParcel, 8, paramParticipantEntity.getPlayer(), paramInt, false);
    zzb.zzc(paramParcel, 9, paramParticipantEntity.getCapabilities());
    zzb.zza(paramParcel, 10, paramParticipantEntity.getResult(), paramInt, false);
    zzb.zza(paramParcel, 11, paramParticipantEntity.getIconImageUrl(), false);
    zzb.zza(paramParcel, 12, paramParticipantEntity.getHiResImageUrl(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public ParticipantEntity zzes(Parcel paramParcel)
  {
    int m = zza.zzau(paramParcel);
    int k = 0;
    String str5 = null;
    String str4 = null;
    Uri localUri2 = null;
    Uri localUri1 = null;
    int j = 0;
    String str3 = null;
    boolean bool = false;
    PlayerEntity localPlayerEntity = null;
    int i = 0;
    ParticipantResult localParticipantResult = null;
    String str2 = null;
    String str1 = null;
    while (paramParcel.dataPosition() < m)
    {
      int n = zza.zzat(paramParcel);
      switch (zza.zzca(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        str5 = zza.zzp(paramParcel, n);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, n);
        break;
      case 2: 
        str4 = zza.zzp(paramParcel, n);
        break;
      case 3: 
        localUri2 = (Uri)zza.zza(paramParcel, n, Uri.CREATOR);
        break;
      case 4: 
        localUri1 = (Uri)zza.zza(paramParcel, n, Uri.CREATOR);
        break;
      case 5: 
        j = zza.zzg(paramParcel, n);
        break;
      case 6: 
        str3 = zza.zzp(paramParcel, n);
        break;
      case 7: 
        bool = zza.zzc(paramParcel, n);
        break;
      case 8: 
        localPlayerEntity = (PlayerEntity)zza.zza(paramParcel, n, PlayerEntity.CREATOR);
        break;
      case 9: 
        i = zza.zzg(paramParcel, n);
        break;
      case 10: 
        localParticipantResult = (ParticipantResult)zza.zza(paramParcel, n, ParticipantResult.CREATOR);
        break;
      case 11: 
        str2 = zza.zzp(paramParcel, n);
        break;
      case 12: 
        str1 = zza.zzp(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new ParticipantEntity(k, str5, str4, localUri2, localUri1, j, str3, bool, localPlayerEntity, i, localParticipantResult, str2, str1);
  }
  
  public ParticipantEntity[] zzgI(int paramInt)
  {
    return new ParticipantEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\multiplayer\ParticipantEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */