package com.google.android.gms.nearby.bootstrap.request;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzqe;
import com.google.android.gms.internal.zzqe.zza;
import com.google.android.gms.internal.zzqf;
import com.google.android.gms.internal.zzqf.zza;
import com.google.android.gms.internal.zzqg;
import com.google.android.gms.internal.zzqg.zza;

public class EnableTargetRequest
  implements SafeParcelable
{
  public static final zze CREATOR = new zze();
  private final String description;
  private final String name;
  final int versionCode;
  private final byte zzbaR;
  private final zzqe zzbaT;
  private final zzqf zzbaU;
  private final zzqg zzbaV;
  
  EnableTargetRequest(int paramInt, String paramString1, String paramString2, byte paramByte, IBinder paramIBinder1, IBinder paramIBinder2, IBinder paramIBinder3)
  {
    this.versionCode = paramInt;
    this.name = zzx.zzcM(paramString1);
    this.description = ((String)zzx.zzz(paramString2));
    this.zzbaR = paramByte;
    zzx.zzz(paramIBinder1);
    this.zzbaT = zzqe.zza.zzdq(paramIBinder1);
    zzx.zzz(paramIBinder2);
    this.zzbaU = zzqf.zza.zzdr(paramIBinder2);
    zzx.zzz(paramIBinder3);
    this.zzbaV = zzqg.zza.zzds(paramIBinder3);
  }
  
  public int describeContents()
  {
    zze localzze = CREATOR;
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzbaV == null) {
      return null;
    }
    return this.zzbaV.asBinder();
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze localzze = CREATOR;
    zze.zza(this, paramParcel, paramInt);
  }
  
  public byte zzEb()
  {
    return this.zzbaR;
  }
  
  public IBinder zzEg()
  {
    if (this.zzbaT == null) {
      return null;
    }
    return this.zzbaT.asBinder();
  }
  
  public IBinder zzEh()
  {
    if (this.zzbaU == null) {
      return null;
    }
    return this.zzbaU.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\bootstrap\request\EnableTargetRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */