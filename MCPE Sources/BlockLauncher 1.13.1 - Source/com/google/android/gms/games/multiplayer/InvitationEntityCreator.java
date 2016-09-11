package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

public class InvitationEntityCreator
  implements Parcelable.Creator<InvitationEntity>
{
  static void zza(InvitationEntity paramInvitationEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramInvitationEntity.getGame(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramInvitationEntity.getVersionCode());
    zzb.zza(paramParcel, 2, paramInvitationEntity.getInvitationId(), false);
    zzb.zza(paramParcel, 3, paramInvitationEntity.getCreationTimestamp());
    zzb.zzc(paramParcel, 4, paramInvitationEntity.getInvitationType());
    zzb.zza(paramParcel, 5, paramInvitationEntity.getInviter(), paramInt, false);
    zzb.zzc(paramParcel, 6, paramInvitationEntity.getParticipants(), false);
    zzb.zzc(paramParcel, 7, paramInvitationEntity.getVariant());
    zzb.zzc(paramParcel, 8, paramInvitationEntity.getAvailableAutoMatchSlots());
    zzb.zzI(paramParcel, i);
  }
  
  public InvitationEntity zzer(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int i = 0;
    int n = zza.zzau(paramParcel);
    long l = 0L;
    int j = 0;
    ParticipantEntity localParticipantEntity = null;
    int k = 0;
    String str = null;
    GameEntity localGameEntity = null;
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
        localGameEntity = (GameEntity)zza.zza(paramParcel, i1, GameEntity.CREATOR);
        break;
      case 1000: 
        m = zza.zzg(paramParcel, i1);
        break;
      case 2: 
        str = zza.zzp(paramParcel, i1);
        break;
      case 3: 
        l = zza.zzi(paramParcel, i1);
        break;
      case 4: 
        k = zza.zzg(paramParcel, i1);
        break;
      case 5: 
        localParticipantEntity = (ParticipantEntity)zza.zza(paramParcel, i1, ParticipantEntity.CREATOR);
        break;
      case 6: 
        localArrayList = zza.zzc(paramParcel, i1, ParticipantEntity.CREATOR);
        break;
      case 7: 
        j = zza.zzg(paramParcel, i1);
        break;
      case 8: 
        i = zza.zzg(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new zza.zza("Overread allowed size end=" + n, paramParcel);
    }
    return new InvitationEntity(m, localGameEntity, str, l, k, localParticipantEntity, localArrayList, j, i);
  }
  
  public InvitationEntity[] zzgH(int paramInt)
  {
    return new InvitationEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\multiplayer\InvitationEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */