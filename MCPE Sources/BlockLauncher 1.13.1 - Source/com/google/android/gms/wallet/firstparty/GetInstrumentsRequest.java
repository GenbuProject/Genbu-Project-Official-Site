package com.google.android.gms.wallet.firstparty;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GetInstrumentsRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<GetInstrumentsRequest> CREATOR = new zzd();
  private final int mVersionCode;
  int[] zzbpS;
  
  GetInstrumentsRequest()
  {
    this(1, null);
  }
  
  GetInstrumentsRequest(int paramInt, int[] paramArrayOfInt)
  {
    this.mVersionCode = paramInt;
    this.zzbpS = paramArrayOfInt;
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
    zzd.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\firstparty\GetInstrumentsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */