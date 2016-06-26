package com.google.android.gms.games.quest;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class MilestoneEntityCreator
  implements Parcelable.Creator<MilestoneEntity>
{
  static void zza(MilestoneEntity paramMilestoneEntity, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramMilestoneEntity.getMilestoneId(), false);
    zzb.zzc(paramParcel, 1000, paramMilestoneEntity.getVersionCode());
    zzb.zza(paramParcel, 2, paramMilestoneEntity.getCurrentProgress());
    zzb.zza(paramParcel, 3, paramMilestoneEntity.getTargetProgress());
    zzb.zza(paramParcel, 4, paramMilestoneEntity.getCompletionRewardData(), false);
    zzb.zzc(paramParcel, 5, paramMilestoneEntity.getState());
    zzb.zza(paramParcel, 6, paramMilestoneEntity.getEventId(), false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public MilestoneEntity zzex(Parcel paramParcel)
  {
    long l1 = 0L;
    int i = 0;
    String str1 = null;
    int k = zza.zzau(paramParcel);
    byte[] arrayOfByte = null;
    long l2 = 0L;
    String str2 = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      switch (zza.zzca(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        str2 = zza.zzp(paramParcel, m);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        l2 = zza.zzi(paramParcel, m);
        break;
      case 3: 
        l1 = zza.zzi(paramParcel, m);
        break;
      case 4: 
        arrayOfByte = zza.zzs(paramParcel, m);
        break;
      case 5: 
        i = zza.zzg(paramParcel, m);
        break;
      case 6: 
        str1 = zza.zzp(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new MilestoneEntity(j, str2, l2, l1, arrayOfByte, i, str1);
  }
  
  public MilestoneEntity[] zzgN(int paramInt)
  {
    return new MilestoneEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\quest\MilestoneEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */