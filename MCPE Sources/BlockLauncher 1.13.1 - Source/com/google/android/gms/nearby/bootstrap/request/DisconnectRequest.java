package com.google.android.gms.nearby.bootstrap.request;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzqg;
import com.google.android.gms.internal.zzqg.zza;
import com.google.android.gms.nearby.bootstrap.Device;

public class DisconnectRequest
  implements SafeParcelable
{
  public static final zzd CREATOR = new zzd();
  final int versionCode;
  private final Device zzbaS;
  private final zzqg zzbaV;
  
  DisconnectRequest(int paramInt, Device paramDevice, IBinder paramIBinder)
  {
    this.versionCode = paramInt;
    this.zzbaS = ((Device)zzx.zzz(paramDevice));
    zzx.zzz(paramIBinder);
    this.zzbaV = zzqg.zza.zzds(paramIBinder);
  }
  
  public int describeContents()
  {
    zzd localzzd = CREATOR;
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    return this.zzbaV.asBinder();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd localzzd = CREATOR;
    zzd.zza(this, paramParcel, paramInt);
  }
  
  public Device zzEd()
  {
    return this.zzbaS;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\bootstrap\request\DisconnectRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */