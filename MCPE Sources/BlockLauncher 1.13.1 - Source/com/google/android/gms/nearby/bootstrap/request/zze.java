package com.google.android.gms.nearby.bootstrap.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze
  implements Parcelable.Creator<EnableTargetRequest>
{
  static void zza(EnableTargetRequest paramEnableTargetRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramEnableTargetRequest.getName(), false);
    zzb.zzc(paramParcel, 1000, paramEnableTargetRequest.versionCode);
    zzb.zza(paramParcel, 2, paramEnableTargetRequest.getDescription(), false);
    zzb.zza(paramParcel, 3, paramEnableTargetRequest.zzEg(), false);
    zzb.zza(paramParcel, 4, paramEnableTargetRequest.zzEh(), false);
    zzb.zza(paramParcel, 5, paramEnableTargetRequest.getCallbackBinder(), false);
    zzb.zza(paramParcel, 6, paramEnableTargetRequest.zzEb());
    zzb.zzI(paramParcel, paramInt);
  }
  
  public EnableTargetRequest zzfU(Parcel paramParcel)
  {
    byte b = 0;
    IBinder localIBinder1 = null;
    int j = zza.zzau(paramParcel);
    IBinder localIBinder2 = null;
    IBinder localIBinder3 = null;
    String str1 = null;
    String str2 = null;
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
        str2 = zza.zzp(paramParcel, k);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str1 = zza.zzp(paramParcel, k);
        break;
      case 3: 
        localIBinder3 = zza.zzq(paramParcel, k);
        break;
      case 4: 
        localIBinder2 = zza.zzq(paramParcel, k);
        break;
      case 5: 
        localIBinder1 = zza.zzq(paramParcel, k);
        break;
      case 6: 
        b = zza.zze(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new EnableTargetRequest(i, str2, str1, b, localIBinder3, localIBinder2, localIBinder1);
  }
  
  public EnableTargetRequest[] zziQ(int paramInt)
  {
    return new EnableTargetRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\bootstrap\request\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */