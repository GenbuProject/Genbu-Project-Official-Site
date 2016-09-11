package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GetPermissionStatusRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<GetPermissionStatusRequest> CREATOR = new zzb();
  final int mVersionCode;
  @Deprecated
  public final String zzbbF;
  public final zze zzbcr;
  public final ClientAppContext zzbcs;
  
  GetPermissionStatusRequest(int paramInt, IBinder paramIBinder, String paramString, ClientAppContext paramClientAppContext)
  {
    this.mVersionCode = paramInt;
    this.zzbcr = zze.zza.zzdz(paramIBinder);
    this.zzbbF = paramString;
    if (paramClientAppContext != null) {}
    for (;;)
    {
      this.zzbcs = paramClientAppContext;
      return;
      paramClientAppContext = new ClientAppContext(null, this.zzbbF);
    }
  }
  
  GetPermissionStatusRequest(IBinder paramIBinder, ClientAppContext paramClientAppContext)
  {
    this(1, paramIBinder, null, paramClientAppContext);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzED()
  {
    return this.zzbcr.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\internal\GetPermissionStatusRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */