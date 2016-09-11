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
import com.google.android.gms.nearby.bootstrap.Device;

public class ConnectRequest
  implements SafeParcelable
{
  public static final zza CREATOR = new zza();
  private final String description;
  private final String name;
  private final long timeoutMillis;
  final int versionCode;
  private final byte zzbaR;
  private final Device zzbaS;
  private final zzqe zzbaT;
  private final zzqf zzbaU;
  private final zzqg zzbaV;
  private final String zzbaW;
  private final byte zzbaX;
  
  ConnectRequest(int paramInt, Device paramDevice, String paramString1, String paramString2, byte paramByte1, long paramLong, String paramString3, byte paramByte2, IBinder paramIBinder1, IBinder paramIBinder2, IBinder paramIBinder3)
  {
    this.versionCode = paramInt;
    this.zzbaS = ((Device)zzx.zzz(paramDevice));
    this.name = zzx.zzcM(paramString1);
    this.description = ((String)zzx.zzz(paramString2));
    this.zzbaR = paramByte1;
    this.timeoutMillis = paramLong;
    this.zzbaX = paramByte2;
    this.zzbaW = paramString3;
    zzx.zzz(paramIBinder1);
    this.zzbaT = zzqe.zza.zzdq(paramIBinder1);
    zzx.zzz(paramIBinder2);
    this.zzbaU = zzqf.zza.zzdr(paramIBinder2);
    zzx.zzz(paramIBinder3);
    this.zzbaV = zzqg.zza.zzds(paramIBinder3);
  }
  
  public int describeContents()
  {
    zza localzza = CREATOR;
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
  
  public String getToken()
  {
    return this.zzbaW;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza localzza = CREATOR;
    zza.zza(this, paramParcel, paramInt);
  }
  
  public byte zzEb()
  {
    return this.zzbaR;
  }
  
  public Device zzEd()
  {
    return this.zzbaS;
  }
  
  public long zzEe()
  {
    return this.timeoutMillis;
  }
  
  public byte zzEf()
  {
    return this.zzbaX;
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\bootstrap\request\ConnectRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */