package com.google.android.gms.nearby.bootstrap.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.nearby.bootstrap.Device;

public class zza
  implements Parcelable.Creator<ConnectRequest>
{
  static void zza(ConnectRequest paramConnectRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramConnectRequest.zzEd(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramConnectRequest.versionCode);
    zzb.zza(paramParcel, 2, paramConnectRequest.getName(), false);
    zzb.zza(paramParcel, 3, paramConnectRequest.getDescription(), false);
    zzb.zza(paramParcel, 4, paramConnectRequest.zzEg(), false);
    zzb.zza(paramParcel, 5, paramConnectRequest.zzEh(), false);
    zzb.zza(paramParcel, 6, paramConnectRequest.getCallbackBinder(), false);
    zzb.zza(paramParcel, 7, paramConnectRequest.zzEb());
    zzb.zza(paramParcel, 8, paramConnectRequest.zzEe());
    zzb.zza(paramParcel, 9, paramConnectRequest.getToken(), false);
    zzb.zza(paramParcel, 10, paramConnectRequest.zzEf());
    zzb.zzI(paramParcel, i);
  }
  
  public ConnectRequest zzfQ(Parcel paramParcel)
  {
    int j = com.google.android.gms.common.internal.safeparcel.zza.zzau(paramParcel);
    int i = 0;
    Device localDevice = null;
    String str3 = null;
    String str2 = null;
    byte b2 = 0;
    long l = 0L;
    String str1 = null;
    byte b1 = 0;
    IBinder localIBinder3 = null;
    IBinder localIBinder2 = null;
    IBinder localIBinder1 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = com.google.android.gms.common.internal.safeparcel.zza.zzat(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, k);
        break;
      case 1: 
        localDevice = (Device)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, k, Device.CREATOR);
        break;
      case 1000: 
        i = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, k);
        break;
      case 2: 
        str3 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, k);
        break;
      case 3: 
        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, k);
        break;
      case 4: 
        localIBinder3 = com.google.android.gms.common.internal.safeparcel.zza.zzq(paramParcel, k);
        break;
      case 5: 
        localIBinder2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(paramParcel, k);
        break;
      case 6: 
        localIBinder1 = com.google.android.gms.common.internal.safeparcel.zza.zzq(paramParcel, k);
        break;
      case 7: 
        b2 = com.google.android.gms.common.internal.safeparcel.zza.zze(paramParcel, k);
        break;
      case 8: 
        l = com.google.android.gms.common.internal.safeparcel.zza.zzi(paramParcel, k);
        break;
      case 9: 
        str1 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, k);
        break;
      case 10: 
        b1 = com.google.android.gms.common.internal.safeparcel.zza.zze(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new ConnectRequest(i, localDevice, str3, str2, b2, l, str1, b1, localIBinder3, localIBinder2, localIBinder1);
  }
  
  public ConnectRequest[] zziM(int paramInt)
  {
    return new ConnectRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\bootstrap\request\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */