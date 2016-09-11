package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class IsReadyToPayRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<IsReadyToPayRequest> CREATOR = new zzi();
  final int mVersionCode;
  
  IsReadyToPayRequest()
  {
    this.mVersionCode = 1;
  }
  
  IsReadyToPayRequest(int paramInt)
  {
    this.mVersionCode = paramInt;
  }
  
  public static zza zzIj()
  {
    IsReadyToPayRequest localIsReadyToPayRequest = new IsReadyToPayRequest();
    localIsReadyToPayRequest.getClass();
    return new zza(null);
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
    zzi.zza(this, paramParcel, paramInt);
  }
  
  public final class zza
  {
    private zza() {}
    
    public IsReadyToPayRequest zzIk()
    {
      return IsReadyToPayRequest.this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\IsReadyToPayRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */