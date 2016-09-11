package com.google.android.gms.nearby.bootstrap.request;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzqg;
import com.google.android.gms.internal.zzqg.zza;
import com.google.android.gms.internal.zzqi;
import com.google.android.gms.internal.zzqi.zza;

public class StartScanRequest
  implements SafeParcelable
{
  public static final zzg CREATOR = new zzg();
  final int versionCode;
  private final zzqg zzbaV;
  private final zzqi zzbaY;
  
  StartScanRequest(int paramInt, IBinder paramIBinder1, IBinder paramIBinder2)
  {
    this.versionCode = paramInt;
    zzx.zzz(paramIBinder1);
    this.zzbaY = zzqi.zza.zzdu(paramIBinder1);
    zzx.zzz(paramIBinder2);
    this.zzbaV = zzqg.zza.zzds(paramIBinder2);
  }
  
  public int describeContents()
  {
    zzg localzzg = CREATOR;
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzbaV == null) {
      return null;
    }
    return this.zzbaV.asBinder();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg localzzg = CREATOR;
    zzg.zza(this, paramParcel, paramInt);
  }
  
  public IBinder zzEi()
  {
    if (this.zzbaY == null) {
      return null;
    }
    return this.zzbaY.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\bootstrap\request\StartScanRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */