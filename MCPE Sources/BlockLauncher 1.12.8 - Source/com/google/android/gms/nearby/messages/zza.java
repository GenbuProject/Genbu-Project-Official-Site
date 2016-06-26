package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.nearby.messages.devices.NearbyDevice;

public class zza
  implements Parcelable.Creator<Message>
{
  static void zza(Message paramMessage, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramMessage.getContent(), false);
    zzb.zzc(paramParcel, 1000, paramMessage.versionCode);
    zzb.zza(paramParcel, 2, paramMessage.getType(), false);
    zzb.zza(paramParcel, 3, paramMessage.getNamespace(), false);
    zzb.zza(paramParcel, 4, paramMessage.zzEn(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public Message zzga(Parcel paramParcel)
  {
    NearbyDevice[] arrayOfNearbyDevice = null;
    int j = com.google.android.gms.common.internal.safeparcel.zza.zzau(paramParcel);
    int i = 0;
    String str1 = null;
    String str2 = null;
    byte[] arrayOfByte = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = com.google.android.gms.common.internal.safeparcel.zza.zzat(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, k);
        break;
      case 1: 
        arrayOfByte = com.google.android.gms.common.internal.safeparcel.zza.zzs(paramParcel, k);
        break;
      case 1000: 
        i = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, k);
        break;
      case 2: 
        str1 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, k);
        break;
      case 3: 
        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, k);
        break;
      case 4: 
        arrayOfNearbyDevice = (NearbyDevice[])com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, k, NearbyDevice.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new Message(i, arrayOfByte, str2, str1, arrayOfNearbyDevice);
  }
  
  public Message[] zzjb(int paramInt)
  {
    return new Message[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */