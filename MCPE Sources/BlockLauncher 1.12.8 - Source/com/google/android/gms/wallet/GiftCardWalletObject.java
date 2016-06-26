package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.gms.wallet.wobs.CommonWalletObject.zza;

public final class GiftCardWalletObject
  implements SafeParcelable
{
  public static final Parcelable.Creator<GiftCardWalletObject> CREATOR = new zzg();
  final int mVersionCode;
  String pin;
  CommonWalletObject zzboB = CommonWalletObject.zzIr().zzIs();
  String zzboC;
  String zzboD;
  long zzboE;
  String zzboF;
  long zzboG;
  String zzboH;
  
  GiftCardWalletObject()
  {
    this.mVersionCode = 1;
  }
  
  GiftCardWalletObject(int paramInt, CommonWalletObject paramCommonWalletObject, String paramString1, String paramString2, String paramString3, long paramLong1, String paramString4, long paramLong2, String paramString5)
  {
    this.mVersionCode = paramInt;
    this.zzboB = paramCommonWalletObject;
    this.zzboC = paramString1;
    this.pin = paramString2;
    this.zzboE = paramLong1;
    this.zzboF = paramString4;
    this.zzboG = paramLong2;
    this.zzboH = paramString5;
    this.zzboD = paramString3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getId()
  {
    return this.zzboB.getId();
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wallet\GiftCardWalletObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */