package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzu
  implements Parcelable.Creator<UnsubscribeRequest>
{
  static void zza(UnsubscribeRequest paramUnsubscribeRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramUnsubscribeRequest.mVersionCode);
    zzb.zza(paramParcel, 2, paramUnsubscribeRequest.zzEH(), false);
    zzb.zza(paramParcel, 3, paramUnsubscribeRequest.zzED(), false);
    zzb.zza(paramParcel, 4, paramUnsubscribeRequest.zzbda, paramInt, false);
    zzb.zzc(paramParcel, 5, paramUnsubscribeRequest.zzbdb);
    zzb.zza(paramParcel, 6, paramUnsubscribeRequest.zzbbF, false);
    zzb.zza(paramParcel, 7, paramUnsubscribeRequest.zzbco, false);
    zzb.zza(paramParcel, 8, paramUnsubscribeRequest.zzbbH);
    zzb.zza(paramParcel, 9, paramUnsubscribeRequest.zzbcs, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public UnsubscribeRequest zzgp(Parcel paramParcel)
  {
    boolean bool = false;
    ClientAppContext localClientAppContext = null;
    int k = zza.zzau(paramParcel);
    String str1 = null;
    String str2 = null;
    int i = 0;
    PendingIntent localPendingIntent = null;
    IBinder localIBinder1 = null;
    IBinder localIBinder2 = null;
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
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        localIBinder2 = zza.zzq(paramParcel, m);
        break;
      case 3: 
        localIBinder1 = zza.zzq(paramParcel, m);
        break;
      case 4: 
        localPendingIntent = (PendingIntent)zza.zza(paramParcel, m, PendingIntent.CREATOR);
        break;
      case 5: 
        i = zza.zzg(paramParcel, m);
        break;
      case 6: 
        str2 = zza.zzp(paramParcel, m);
        break;
      case 7: 
        str1 = zza.zzp(paramParcel, m);
        break;
      case 8: 
        bool = zza.zzc(paramParcel, m);
        break;
      case 9: 
        localClientAppContext = (ClientAppContext)zza.zza(paramParcel, m, ClientAppContext.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new UnsubscribeRequest(j, localIBinder2, localIBinder1, localPendingIntent, i, str2, str1, bool, localClientAppContext);
  }
  
  public UnsubscribeRequest[] zzjv(int paramInt)
  {
    return new UnsubscribeRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\internal\zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */