package com.google.android.gms.auth.api.proxy;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ProxyGrpcRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<ProxyGrpcRequest> CREATOR = new zza();
  public final byte[] body;
  public final String hostname;
  public final String method;
  public final int port;
  public final long timeoutMillis;
  final int versionCode;
  
  ProxyGrpcRequest(int paramInt1, String paramString1, int paramInt2, long paramLong, byte[] paramArrayOfByte, String paramString2)
  {
    this.versionCode = paramInt1;
    this.hostname = paramString1;
    this.port = paramInt2;
    this.timeoutMillis = paramLong;
    this.body = paramArrayOfByte;
    this.method = paramString2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\api\proxy\ProxyGrpcRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */