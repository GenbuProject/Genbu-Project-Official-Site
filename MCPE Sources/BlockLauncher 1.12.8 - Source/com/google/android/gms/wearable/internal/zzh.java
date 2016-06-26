package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh
  implements Parcelable.Creator<AncsNotificationParcelable>
{
  static void zza(AncsNotificationParcelable paramAncsNotificationParcelable, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramAncsNotificationParcelable.mVersionCode);
    zzb.zzc(paramParcel, 2, paramAncsNotificationParcelable.getId());
    zzb.zza(paramParcel, 3, paramAncsNotificationParcelable.zzwK(), false);
    zzb.zza(paramParcel, 4, paramAncsNotificationParcelable.zzIB(), false);
    zzb.zza(paramParcel, 5, paramAncsNotificationParcelable.zzIC(), false);
    zzb.zza(paramParcel, 6, paramAncsNotificationParcelable.getTitle(), false);
    zzb.zza(paramParcel, 7, paramAncsNotificationParcelable.zzwc(), false);
    zzb.zza(paramParcel, 8, paramAncsNotificationParcelable.getDisplayName(), false);
    zzb.zza(paramParcel, 9, paramAncsNotificationParcelable.zzID());
    zzb.zza(paramParcel, 10, paramAncsNotificationParcelable.zzIE());
    zzb.zza(paramParcel, 11, paramAncsNotificationParcelable.zzIF());
    zzb.zza(paramParcel, 12, paramAncsNotificationParcelable.zzIG());
    zzb.zza(paramParcel, 13, paramAncsNotificationParcelable.getPackageName(), false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public AncsNotificationParcelable zzid(Parcel paramParcel)
  {
    int k = zza.zzau(paramParcel);
    int j = 0;
    int i = 0;
    String str7 = null;
    String str6 = null;
    String str5 = null;
    String str4 = null;
    String str3 = null;
    String str2 = null;
    byte b4 = 0;
    byte b3 = 0;
    byte b2 = 0;
    byte b1 = 0;
    String str1 = null;
    while (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      switch (zza.zzca(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        i = zza.zzg(paramParcel, m);
        break;
      case 3: 
        str7 = zza.zzp(paramParcel, m);
        break;
      case 4: 
        str6 = zza.zzp(paramParcel, m);
        break;
      case 5: 
        str5 = zza.zzp(paramParcel, m);
        break;
      case 6: 
        str4 = zza.zzp(paramParcel, m);
        break;
      case 7: 
        str3 = zza.zzp(paramParcel, m);
        break;
      case 8: 
        str2 = zza.zzp(paramParcel, m);
        break;
      case 9: 
        b4 = zza.zze(paramParcel, m);
        break;
      case 10: 
        b3 = zza.zze(paramParcel, m);
        break;
      case 11: 
        b2 = zza.zze(paramParcel, m);
        break;
      case 12: 
        b1 = zza.zze(paramParcel, m);
        break;
      case 13: 
        str1 = zza.zzp(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new AncsNotificationParcelable(j, i, str7, str6, str5, str4, str3, str2, b4, b3, b2, b1, str1);
  }
  
  public AncsNotificationParcelable[] zzlE(int paramInt)
  {
    return new AncsNotificationParcelable[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */