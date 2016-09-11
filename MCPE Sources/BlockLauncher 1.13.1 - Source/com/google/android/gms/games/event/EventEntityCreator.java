package com.google.android.gms.games.event;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.PlayerEntity;

public class EventEntityCreator
  implements Parcelable.Creator<EventEntity>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void zza(EventEntity paramEventEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramEventEntity.getEventId(), false);
    zzb.zzc(paramParcel, 1000, paramEventEntity.getVersionCode());
    zzb.zza(paramParcel, 2, paramEventEntity.getName(), false);
    zzb.zza(paramParcel, 3, paramEventEntity.getDescription(), false);
    zzb.zza(paramParcel, 4, paramEventEntity.getIconImageUri(), paramInt, false);
    zzb.zza(paramParcel, 5, paramEventEntity.getIconImageUrl(), false);
    zzb.zza(paramParcel, 6, paramEventEntity.getPlayer(), paramInt, false);
    zzb.zza(paramParcel, 7, paramEventEntity.getValue());
    zzb.zza(paramParcel, 8, paramEventEntity.getFormattedValue(), false);
    zzb.zza(paramParcel, 9, paramEventEntity.isVisible());
    zzb.zzI(paramParcel, i);
  }
  
  public EventEntity createFromParcel(Parcel paramParcel)
  {
    boolean bool = false;
    String str1 = null;
    int j = zza.zzau(paramParcel);
    long l = 0L;
    PlayerEntity localPlayerEntity = null;
    String str2 = null;
    Uri localUri = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        str5 = zza.zzp(paramParcel, k);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str4 = zza.zzp(paramParcel, k);
        break;
      case 3: 
        str3 = zza.zzp(paramParcel, k);
        break;
      case 4: 
        localUri = (Uri)zza.zza(paramParcel, k, Uri.CREATOR);
        break;
      case 5: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 6: 
        localPlayerEntity = (PlayerEntity)zza.zza(paramParcel, k, PlayerEntity.CREATOR);
        break;
      case 7: 
        l = zza.zzi(paramParcel, k);
        break;
      case 8: 
        str1 = zza.zzp(paramParcel, k);
        break;
      case 9: 
        bool = zza.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new EventEntity(i, str5, str4, str3, localUri, str2, localPlayerEntity, l, str1, bool);
  }
  
  public EventEntity[] newArray(int paramInt)
  {
    return new EventEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\event\EventEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */