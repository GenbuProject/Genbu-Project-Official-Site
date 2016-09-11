package com.google.android.gms.nearby.bootstrap.request;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzqg;
import com.google.android.gms.internal.zzqg.zza;

public class ContinueConnectRequest
  implements SafeParcelable
{
  public static final zzb CREATOR = new zzb();
  final int versionCode;
  private final zzqg zzbaV;
  private final String zzbaW;
  
  ContinueConnectRequest(int paramInt, String paramString, IBinder paramIBinder)
  {
    this.versionCode = paramInt;
    this.zzbaW = ((String)zzx.zzz(paramString));
    this.zzbaV = zzqg.zza.zzds(paramIBinder);
  }
  
  public int describeContents()
  {
    zzb localzzb = CREATOR;
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzbaV == null) {
      return null;
    }
    return this.zzbaV.asBinder();
  }
  
  public String getToken()
  {
    return this.zzbaW;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb localzzb = CREATOR;
    zzb.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\bootstrap\request\ContinueConnectRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */