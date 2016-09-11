package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class MostRecentGameInfoEntityCreator
  implements Parcelable.Creator<MostRecentGameInfoEntity>
{
  static void zza(MostRecentGameInfoEntity paramMostRecentGameInfoEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramMostRecentGameInfoEntity.zzxy(), false);
    zzb.zzc(paramParcel, 1000, paramMostRecentGameInfoEntity.getVersionCode());
    zzb.zza(paramParcel, 2, paramMostRecentGameInfoEntity.zzxz(), false);
    zzb.zza(paramParcel, 3, paramMostRecentGameInfoEntity.zzxA());
    zzb.zza(paramParcel, 4, paramMostRecentGameInfoEntity.zzxB(), paramInt, false);
    zzb.zza(paramParcel, 5, paramMostRecentGameInfoEntity.zzxC(), paramInt, false);
    zzb.zza(paramParcel, 6, paramMostRecentGameInfoEntity.zzxD(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public MostRecentGameInfoEntity zzep(Parcel paramParcel)
  {
    Uri localUri1 = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    long l = 0L;
    Uri localUri2 = null;
    Uri localUri3 = null;
    String str1 = null;
    String str2 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str1 = zza.zzp(paramParcel, k);
        break;
      case 3: 
        l = zza.zzi(paramParcel, k);
        break;
      case 4: 
        localUri3 = (Uri)zza.zza(paramParcel, k, Uri.CREATOR);
        break;
      case 5: 
        localUri2 = (Uri)zza.zza(paramParcel, k, Uri.CREATOR);
        break;
      case 6: 
        localUri1 = (Uri)zza.zza(paramParcel, k, Uri.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new MostRecentGameInfoEntity(i, str2, str1, l, localUri3, localUri2, localUri1);
  }
  
  public MostRecentGameInfoEntity[] zzgE(int paramInt)
  {
    return new MostRecentGameInfoEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\internal\player\MostRecentGameInfoEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */