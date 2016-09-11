package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class AppContentActionEntityCreator
  implements Parcelable.Creator<AppContentActionEntity>
{
  static void zza(AppContentActionEntity paramAppContentActionEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramAppContentActionEntity.zzvP(), false);
    zzb.zzc(paramParcel, 1000, paramAppContentActionEntity.getVersionCode());
    zzb.zza(paramParcel, 2, paramAppContentActionEntity.zzvQ(), false);
    zzb.zza(paramParcel, 3, paramAppContentActionEntity.getExtras(), false);
    zzb.zza(paramParcel, 6, paramAppContentActionEntity.getType(), false);
    zzb.zza(paramParcel, 7, paramAppContentActionEntity.getId(), false);
    zzb.zza(paramParcel, 8, paramAppContentActionEntity.zzvO(), paramInt, false);
    zzb.zza(paramParcel, 9, paramAppContentActionEntity.zzvR(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public AppContentActionEntity zzef(Parcel paramParcel)
  {
    String str1 = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    AppContentAnnotationEntity localAppContentAnnotationEntity = null;
    String str2 = null;
    String str3 = null;
    Bundle localBundle = null;
    String str4 = null;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localArrayList = zza.zzc(paramParcel, k, AppContentConditionEntity.CREATOR);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str4 = zza.zzp(paramParcel, k);
        break;
      case 3: 
        localBundle = zza.zzr(paramParcel, k);
        break;
      case 6: 
        str3 = zza.zzp(paramParcel, k);
        break;
      case 7: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 8: 
        localAppContentAnnotationEntity = (AppContentAnnotationEntity)zza.zza(paramParcel, k, AppContentAnnotationEntity.CREATOR);
        break;
      case 9: 
        str1 = zza.zzp(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new AppContentActionEntity(i, localArrayList, str4, localBundle, str3, str2, localAppContentAnnotationEntity, str1);
  }
  
  public AppContentActionEntity[] zzgh(int paramInt)
  {
    return new AppContentActionEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\appcontent\AppContentActionEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */