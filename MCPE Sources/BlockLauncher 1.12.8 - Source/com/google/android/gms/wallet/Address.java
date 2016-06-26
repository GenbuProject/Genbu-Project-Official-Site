package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@Deprecated
public final class Address
  implements SafeParcelable
{
  public static final Parcelable.Creator<Address> CREATOR = new zza();
  private final int mVersionCode;
  String name;
  String phoneNumber;
  String zzJU;
  String zzaMD;
  String zzaME;
  String zzaMF;
  String zzaMK;
  boolean zzaMM;
  String zzaMN;
  String zzbof;
  String zzbog;
  
  Address()
  {
    this.mVersionCode = 1;
  }
  
  Address(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean, String paramString10)
  {
    this.mVersionCode = paramInt;
    this.name = paramString1;
    this.zzaMD = paramString2;
    this.zzaME = paramString3;
    this.zzaMF = paramString4;
    this.zzJU = paramString5;
    this.zzbof = paramString6;
    this.zzbog = paramString7;
    this.zzaMK = paramString8;
    this.phoneNumber = paramString9;
    this.zzaMM = paramBoolean;
    this.zzaMN = paramString10;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAddress1()
  {
    return this.zzaMD;
  }
  
  public String getAddress2()
  {
    return this.zzaME;
  }
  
  public String getAddress3()
  {
    return this.zzaMF;
  }
  
  public String getCity()
  {
    return this.zzbof;
  }
  
  public String getCompanyName()
  {
    return this.zzaMN;
  }
  
  public String getCountryCode()
  {
    return this.zzJU;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }
  
  public String getPostalCode()
  {
    return this.zzaMK;
  }
  
  public String getState()
  {
    return this.zzbog;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public boolean isPostBox()
  {
    return this.zzaMM;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wallet\Address.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */