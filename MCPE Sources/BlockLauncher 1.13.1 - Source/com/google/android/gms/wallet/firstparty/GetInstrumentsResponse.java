package com.google.android.gms.wallet.firstparty;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GetInstrumentsResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<GetInstrumentsResponse> CREATOR = new zze();
  private final int mVersionCode;
  String[] zzbpT;
  byte[][] zzbpU;
  
  GetInstrumentsResponse()
  {
    this(1, new String[0], new byte[0][]);
  }
  
  GetInstrumentsResponse(int paramInt, String[] paramArrayOfString, byte[][] paramArrayOfByte)
  {
    this.mVersionCode = paramInt;
    this.zzbpT = paramArrayOfString;
    this.zzbpU = paramArrayOfByte;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\firstparty\GetInstrumentsResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */