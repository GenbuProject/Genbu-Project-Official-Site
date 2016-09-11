package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class UriData
  implements SafeParcelable
{
  public static final Parcelable.Creator<UriData> CREATOR = new zzh();
  String description;
  private final int mVersionCode;
  String zzbdg;
  
  UriData()
  {
    this.mVersionCode = 1;
  }
  
  UriData(int paramInt, String paramString1, String paramString2)
  {
    this.mVersionCode = paramInt;
    this.zzbdg = paramString1;
    this.description = paramString2;
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
    zzh.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\wobs\UriData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */