package com.google.android.gms.identity.intents.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class UserAddress
  implements SafeParcelable
{
  public static final Parcelable.Creator<UserAddress> CREATOR = new zzb();
  private final int mVersionCode;
  String name;
  String phoneNumber;
  String zzJU;
  String zzaMD;
  String zzaME;
  String zzaMF;
  String zzaMG;
  String zzaMH;
  String zzaMI;
  String zzaMJ;
  String zzaMK;
  String zzaML;
  boolean zzaMM;
  String zzaMN;
  String zzaMO;
  
  UserAddress()
  {
    this.mVersionCode = 1;
  }
  
  UserAddress(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, boolean paramBoolean, String paramString13, String paramString14)
  {
    this.mVersionCode = paramInt;
    this.name = paramString1;
    this.zzaMD = paramString2;
    this.zzaME = paramString3;
    this.zzaMF = paramString4;
    this.zzaMG = paramString5;
    this.zzaMH = paramString6;
    this.zzaMI = paramString7;
    this.zzaMJ = paramString8;
    this.zzJU = paramString9;
    this.zzaMK = paramString10;
    this.zzaML = paramString11;
    this.phoneNumber = paramString12;
    this.zzaMM = paramBoolean;
    this.zzaMN = paramString13;
    this.zzaMO = paramString14;
  }
  
  public static UserAddress fromIntent(Intent paramIntent)
  {
    if ((paramIntent == null) || (!paramIntent.hasExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS"))) {
      return null;
    }
    return (UserAddress)paramIntent.getParcelableExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS");
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
  
  public String getAddress4()
  {
    return this.zzaMG;
  }
  
  public String getAddress5()
  {
    return this.zzaMH;
  }
  
  public String getAdministrativeArea()
  {
    return this.zzaMI;
  }
  
  public String getCompanyName()
  {
    return this.zzaMN;
  }
  
  public String getCountryCode()
  {
    return this.zzJU;
  }
  
  public String getEmailAddress()
  {
    return this.zzaMO;
  }
  
  public String getLocality()
  {
    return this.zzaMJ;
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
  
  public String getSortingCode()
  {
    return this.zzaML;
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
    zzb.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\identity\intents\model\UserAddress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */