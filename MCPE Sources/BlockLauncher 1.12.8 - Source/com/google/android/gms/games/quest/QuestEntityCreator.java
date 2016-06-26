package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

public class QuestEntityCreator
  implements Parcelable.Creator<QuestEntity>
{
  static void zza(QuestEntity paramQuestEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramQuestEntity.getGame(), paramInt, false);
    zzb.zza(paramParcel, 2, paramQuestEntity.getQuestId(), false);
    zzb.zza(paramParcel, 3, paramQuestEntity.getAcceptedTimestamp());
    zzb.zza(paramParcel, 4, paramQuestEntity.getBannerImageUri(), paramInt, false);
    zzb.zza(paramParcel, 5, paramQuestEntity.getBannerImageUrl(), false);
    zzb.zza(paramParcel, 6, paramQuestEntity.getDescription(), false);
    zzb.zza(paramParcel, 7, paramQuestEntity.getEndTimestamp());
    zzb.zza(paramParcel, 8, paramQuestEntity.getLastUpdatedTimestamp());
    zzb.zza(paramParcel, 9, paramQuestEntity.getIconImageUri(), paramInt, false);
    zzb.zza(paramParcel, 10, paramQuestEntity.getIconImageUrl(), false);
    zzb.zza(paramParcel, 12, paramQuestEntity.getName(), false);
    zzb.zza(paramParcel, 13, paramQuestEntity.zzxS());
    zzb.zza(paramParcel, 14, paramQuestEntity.getStartTimestamp());
    zzb.zzc(paramParcel, 15, paramQuestEntity.getState());
    zzb.zzc(paramParcel, 17, paramQuestEntity.zzxR(), false);
    zzb.zzc(paramParcel, 16, paramQuestEntity.getType());
    zzb.zzc(paramParcel, 1000, paramQuestEntity.getVersionCode());
    zzb.zzI(paramParcel, i);
  }
  
  public QuestEntity zzey(Parcel paramParcel)
  {
    int m = zza.zzau(paramParcel);
    int k = 0;
    GameEntity localGameEntity = null;
    String str5 = null;
    long l5 = 0L;
    Uri localUri2 = null;
    String str4 = null;
    String str3 = null;
    long l4 = 0L;
    long l3 = 0L;
    Uri localUri1 = null;
    String str2 = null;
    String str1 = null;
    long l2 = 0L;
    long l1 = 0L;
    int j = 0;
    int i = 0;
    ArrayList localArrayList = null;
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
      case 2: 
        str5 = zza.zzp(paramParcel, n);
        break;
      case 3: 
        l5 = zza.zzi(paramParcel, n);
        break;
      case 4: 
        localUri2 = (Uri)zza.zza(paramParcel, n, Uri.CREATOR);
        break;
      case 5: 
        str4 = zza.zzp(paramParcel, n);
        break;
      case 6: 
        str3 = zza.zzp(paramParcel, n);
        break;
      case 7: 
        l4 = zza.zzi(paramParcel, n);
        break;
      case 8: 
        l3 = zza.zzi(paramParcel, n);
        break;
      case 9: 
        localUri1 = (Uri)zza.zza(paramParcel, n, Uri.CREATOR);
        break;
      case 10: 
        str2 = zza.zzp(paramParcel, n);
        break;
      case 12: 
        str1 = zza.zzp(paramParcel, n);
        break;
      case 13: 
        l2 = zza.zzi(paramParcel, n);
        break;
      case 14: 
        l1 = zza.zzi(paramParcel, n);
        break;
      case 15: 
        j = zza.zzg(paramParcel, n);
        break;
      case 17: 
        localArrayList = zza.zzc(paramParcel, n, MilestoneEntity.CREATOR);
        break;
      case 16: 
        i = zza.zzg(paramParcel, n);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new QuestEntity(k, localGameEntity, str5, l5, localUri2, str4, str3, l4, l3, localUri1, str2, str1, l2, l1, j, i, localArrayList);
  }
  
  public QuestEntity[] zzgO(int paramInt)
  {
    return new QuestEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\quest\QuestEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */