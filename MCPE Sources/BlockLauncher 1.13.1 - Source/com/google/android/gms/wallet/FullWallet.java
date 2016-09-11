package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class FullWallet
  implements SafeParcelable
{
  public static final Parcelable.Creator<FullWallet> CREATOR = new zze();
  private final int mVersionCode;
  String zzboo;
  String zzbop;
  ProxyCard zzboq;
  String zzbor;
  Address zzbos;
  Address zzbot;
  String[] zzbou;
  UserAddress zzbov;
  UserAddress zzbow;
  InstrumentInfo[] zzbox;
  PaymentMethodToken zzboy;
  
  private FullWallet()
  {
    this.mVersionCode = 1;
  }
  
  FullWallet(int paramInt, String paramString1, String paramString2, ProxyCard paramProxyCard, String paramString3, Address paramAddress1, Address paramAddress2, String[] paramArrayOfString, UserAddress paramUserAddress1, UserAddress paramUserAddress2, InstrumentInfo[] paramArrayOfInstrumentInfo, PaymentMethodToken paramPaymentMethodToken)
  {
    this.mVersionCode = paramInt;
    this.zzboo = paramString1;
    this.zzbop = paramString2;
    this.zzboq = paramProxyCard;
    this.zzbor = paramString3;
    this.zzbos = paramAddress1;
    this.zzbot = paramAddress2;
    this.zzbou = paramArrayOfString;
    this.zzbov = paramUserAddress1;
    this.zzbow = paramUserAddress2;
    this.zzbox = paramArrayOfInstrumentInfo;
    this.zzboy = paramPaymentMethodToken;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  @Deprecated
  public Address getBillingAddress()
  {
    return this.zzbos;
  }
  
  public UserAddress getBuyerBillingAddress()
  {
    return this.zzbov;
  }
  
  public UserAddress getBuyerShippingAddress()
  {
    return this.zzbow;
  }
  
  public String getEmail()
  {
    return this.zzbor;
  }
  
  public String getGoogleTransactionId()
  {
    return this.zzboo;
  }
  
  public InstrumentInfo[] getInstrumentInfos()
  {
    return this.zzbox;
  }
  
  public String getMerchantTransactionId()
  {
    return this.zzbop;
  }
  
  public String[] getPaymentDescriptions()
  {
    return this.zzbou;
  }
  
  public PaymentMethodToken getPaymentMethodToken()
  {
    return this.zzboy;
  }
  
  public ProxyCard getProxyCard()
  {
    return this.zzboq;
  }
  
  @Deprecated
  public Address getShippingAddress()
  {
    return this.zzbot;
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\FullWallet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */