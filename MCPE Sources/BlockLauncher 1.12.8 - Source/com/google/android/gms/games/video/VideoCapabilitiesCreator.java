package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class VideoCapabilitiesCreator
  implements Parcelable.Creator<VideoCapabilities>
{
  static void zza(VideoCapabilities paramVideoCapabilities, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramVideoCapabilities.zzxZ());
    zzb.zzc(paramParcel, 1000, paramVideoCapabilities.getVersionCode());
    zzb.zza(paramParcel, 2, paramVideoCapabilities.zzxY());
    zzb.zza(paramParcel, 3, paramVideoCapabilities.zzya());
    zzb.zza(paramParcel, 4, paramVideoCapabilities.zzyb(), false);
    zzb.zza(paramParcel, 5, paramVideoCapabilities.zzyc(), false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public VideoCapabilities zzeF(Parcel paramParcel)
  {
    boolean[] arrayOfBoolean1 = null;
    boolean bool1 = false;
    int j = zza.zzau(paramParcel);
    boolean[] arrayOfBoolean2 = null;
    boolean bool2 = false;
    boolean bool3 = false;
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
        bool3 = zza.zzc(paramParcel, k);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        bool2 = zza.zzc(paramParcel, k);
        break;
      case 3: 
        bool1 = zza.zzc(paramParcel, k);
        break;
      case 4: 
        arrayOfBoolean2 = zza.zzu(paramParcel, k);
        break;
      case 5: 
        arrayOfBoolean1 = zza.zzu(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new VideoCapabilities(i, bool3, bool2, bool1, arrayOfBoolean2, arrayOfBoolean1);
  }
  
  public VideoCapabilities[] zzgY(int paramInt)
  {
    return new VideoCapabilities[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\video\VideoCapabilitiesCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */