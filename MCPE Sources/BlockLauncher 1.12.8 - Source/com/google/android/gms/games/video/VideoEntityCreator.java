package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class VideoEntityCreator
  implements Parcelable.Creator<VideoEntity>
{
  static void zza(VideoEntity paramVideoEntity, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramVideoEntity.getDuration());
    zzb.zzc(paramParcel, 1000, paramVideoEntity.getVersionCode());
    zzb.zza(paramParcel, 2, paramVideoEntity.zzxX(), false);
    zzb.zza(paramParcel, 3, paramVideoEntity.getFileSize());
    zzb.zza(paramParcel, 4, paramVideoEntity.getStartTime());
    zzb.zza(paramParcel, 5, paramVideoEntity.getPackageName(), false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public VideoEntity zzeH(Parcel paramParcel)
  {
    long l1 = 0L;
    String str1 = null;
    int i = 0;
    int k = zza.zzau(paramParcel);
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
        i = zza.zzg(paramParcel, m);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        str2 = zza.zzp(paramParcel, m);
        break;
      case 3: 
        l2 = zza.zzi(paramParcel, m);
        break;
      case 4: 
        l1 = zza.zzi(paramParcel, m);
        break;
      case 5: 
        str1 = zza.zzp(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new VideoEntity(j, i, str2, l2, l1, str1);
  }
  
  public VideoEntity[] zzhc(int paramInt)
  {
    return new VideoEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\video\VideoEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */