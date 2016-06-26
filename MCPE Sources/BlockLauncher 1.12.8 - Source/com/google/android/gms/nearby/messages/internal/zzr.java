package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzr
  implements Parcelable.Creator<RegisterStatusCallbackRequest>
{
  static void zza(RegisterStatusCallbackRequest paramRegisterStatusCallbackRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramRegisterStatusCallbackRequest.versionCode);
    zzb.zza(paramParcel, 2, paramRegisterStatusCallbackRequest.zzED(), false);
    zzb.zza(paramParcel, 3, paramRegisterStatusCallbackRequest.zzEG(), false);
    zzb.zza(paramParcel, 4, paramRegisterStatusCallbackRequest.zzbcX);
    zzb.zza(paramParcel, 5, paramRegisterStatusCallbackRequest.zzbbF, false);
    zzb.zza(paramParcel, 6, paramRegisterStatusCallbackRequest.zzbcs, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public RegisterStatusCallbackRequest zzgm(Parcel paramParcel)
  {
    boolean bool = false;
    ClientAppContext localClientAppContext = null;
    int j = zza.zzau(paramParcel);
    String str = null;
    IBinder localIBinder1 = null;
    IBinder localIBinder2 = null;
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
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localIBinder2 = zza.zzq(paramParcel, k);
        break;
      case 3: 
        localIBinder1 = zza.zzq(paramParcel, k);
        break;
      case 4: 
        bool = zza.zzc(paramParcel, k);
        break;
      case 5: 
        str = zza.zzp(paramParcel, k);
        break;
      case 6: 
        localClientAppContext = (ClientAppContext)zza.zza(paramParcel, k, ClientAppContext.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new RegisterStatusCallbackRequest(i, localIBinder2, localIBinder1, bool, str, localClientAppContext);
  }
  
  public RegisterStatusCallbackRequest[] zzjs(int paramInt)
  {
    return new RegisterStatusCallbackRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\internal\zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */