package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class VideoConfigurationCreator
  implements Parcelable.Creator<VideoConfiguration>
{
  static void zza(VideoConfiguration paramVideoConfiguration, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramVideoConfiguration.zzyd());
    zzb.zzc(paramParcel, 1000, paramVideoConfiguration.getVersionCode());
    zzb.zzc(paramParcel, 2, paramVideoConfiguration.zzye());
    zzb.zza(paramParcel, 3, paramVideoConfiguration.getStreamUrl(), false);
    zzb.zza(paramParcel, 4, paramVideoConfiguration.zzyf(), false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public VideoConfiguration zzeG(Parcel paramParcel)
  {
    String str1 = null;
    int i = 0;
    int m = zza.zzau(paramParcel);
    String str2 = null;
    int j = 0;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zza.zzat(paramParcel);
      switch (zza.zzca(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        j = zza.zzg(paramParcel, n);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, n);
        break;
      case 2: 
        i = zza.zzg(paramParcel, n);
        break;
      case 3: 
        str2 = zza.zzp(paramParcel, n);
        break;
      case 4: 
        str1 = zza.zzp(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new VideoConfiguration(k, j, i, str2, str1);
  }
  
  public VideoConfiguration[] zzhb(int paramInt)
  {
    return new VideoConfiguration[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\video\VideoConfigurationCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */