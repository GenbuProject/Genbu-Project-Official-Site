package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LoyaltyPointsBalance
  implements SafeParcelable
{
  public static final Parcelable.Creator<LoyaltyPointsBalance> CREATOR = new zzd();
  private final int mVersionCode;
  String zzboF;
  int zzbqJ;
  String zzbqK;
  double zzbqL;
  long zzbqM;
  int zzbqN;
  
  LoyaltyPointsBalance()
  {
    this.mVersionCode = 1;
    this.zzbqN = -1;
    this.zzbqJ = -1;
    this.zzbqL = -1.0D;
  }
  
  LoyaltyPointsBalance(int paramInt1, int paramInt2, String paramString1, double paramDouble, String paramString2, long paramLong, int paramInt3)
  {
    this.mVersionCode = paramInt1;
    this.zzbqJ = paramInt2;
    this.zzbqK = paramString1;
    this.zzbqL = paramDouble;
    this.zzboF = paramString2;
    this.zzbqM = paramLong;
    this.zzbqN = paramInt3;
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\wobs\LoyaltyPointsBalance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */