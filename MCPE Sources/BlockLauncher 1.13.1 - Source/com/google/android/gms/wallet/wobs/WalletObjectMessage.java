package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WalletObjectMessage
  implements SafeParcelable
{
  public static final Parcelable.Creator<WalletObjectMessage> CREATOR = new zzi();
  String body;
  private final int mVersionCode;
  String zzbqO;
  TimeInterval zzbqR;
  UriData zzbqS;
  UriData zzbqT;
  
  WalletObjectMessage()
  {
    this.mVersionCode = 1;
  }
  
  WalletObjectMessage(int paramInt, String paramString1, String paramString2, TimeInterval paramTimeInterval, UriData paramUriData1, UriData paramUriData2)
  {
    this.mVersionCode = paramInt;
    this.zzbqO = paramString1;
    this.body = paramString2;
    this.zzbqR = paramTimeInterval;
    this.zzbqS = paramUriData1;
    this.zzbqT = paramUriData2;
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
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\wobs\WalletObjectMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */