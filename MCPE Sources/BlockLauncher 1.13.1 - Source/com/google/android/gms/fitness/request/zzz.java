package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzz
  implements Parcelable.Creator<SessionRegistrationRequest>
{
  static void zza(SessionRegistrationRequest paramSessionRegistrationRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramSessionRegistrationRequest.getIntent(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramSessionRegistrationRequest.getVersionCode());
    zzb.zza(paramParcel, 2, paramSessionRegistrationRequest.getCallbackBinder(), false);
    zzb.zzc(paramParcel, 4, paramSessionRegistrationRequest.zzvf());
    zzb.zzI(paramParcel, i);
  }
  
  public SessionRegistrationRequest zzdE(Parcel paramParcel)
  {
    Object localObject2 = null;
    int i = 0;
    int k = zza.zzau(paramParcel);
    Object localObject1 = null;
    int j = 0;
    if (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      Object localObject3;
      switch (zza.zzca(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
      for (;;)
      {
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
        break;
        localObject3 = (PendingIntent)zza.zza(paramParcel, m, PendingIntent.CREATOR);
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        j = zza.zzg(paramParcel, m);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = zza.zzq(paramParcel, m);
        localObject2 = localObject1;
        localObject1 = localObject3;
        continue;
        i = zza.zzg(paramParcel, m);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new SessionRegistrationRequest(j, (PendingIntent)localObject1, (IBinder)localObject2, i);
  }
  
  public SessionRegistrationRequest[] zzfF(int paramInt)
  {
    return new SessionRegistrationRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\zzz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */