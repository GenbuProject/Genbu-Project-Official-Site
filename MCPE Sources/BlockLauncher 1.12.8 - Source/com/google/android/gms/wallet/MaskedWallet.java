package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class MaskedWallet
  implements SafeParcelable
{
  public static final Parcelable.Creator<MaskedWallet> CREATOR = new zzl();
  private final int mVersionCode;
  String zzboo;
  String zzbop;
  String zzbor;
  Address zzbos;
  Address zzbot;
  String[] zzbou;
  UserAddress zzbov;
  UserAddress zzbow;
  InstrumentInfo[] zzbox;
  LoyaltyWalletObject[] zzbpi;
  OfferWalletObject[] zzbpj;
  
  private MaskedWallet()
  {
    this.mVersionCode = 2;
  }
  
  MaskedWallet(int paramInt, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3, Address paramAddress1, Address paramAddress2, LoyaltyWalletObject[] paramArrayOfLoyaltyWalletObject, OfferWalletObject[] paramArrayOfOfferWalletObject, UserAddress paramUserAddress1, UserAddress paramUserAddress2, InstrumentInfo[] paramArrayOfInstrumentInfo)
  {
    this.mVersionCode = paramInt;
    this.zzboo = paramString1;
    this.zzbop = paramString2;
    this.zzbou = paramArrayOfString;
    this.zzbor = paramString3;
    this.zzbos = paramAddress1;
    this.zzbot = paramAddress2;
    this.zzbpi = paramArrayOfLoyaltyWalletObject;
    this.zzbpj = paramArrayOfOfferWalletObject;
    this.zzbov = paramUserAddress1;
    this.zzbow = paramUserAddress2;
    this.zzbox = paramArrayOfInstrumentInfo;
  }
  
  public static Builder newBuilderFrom(MaskedWallet paramMaskedWallet)
  {
    zzx.zzz(paramMaskedWallet);
    return zzIl().setGoogleTransactionId(paramMaskedWallet.getGoogleTransactionId()).setMerchantTransactionId(paramMaskedWallet.getMerchantTransactionId()).setPaymentDescriptions(paramMaskedWallet.getPaymentDescriptions()).setInstrumentInfos(paramMaskedWallet.getInstrumentInfos()).setEmail(paramMaskedWallet.getEmail()).zza(paramMaskedWallet.zzIm()).zza(paramMaskedWallet.zzIn()).setBuyerBillingAddress(paramMaskedWallet.getBuyerBillingAddress()).setBuyerShippingAddress(paramMaskedWallet.getBuyerShippingAddress());
  }
  
  public static Builder zzIl()
  {
    MaskedWallet localMaskedWallet = new MaskedWallet();
    localMaskedWallet.getClass();
    return new Builder(null);
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
    zzl.zza(this, paramParcel, paramInt);
  }
  
  @Deprecated
  public LoyaltyWalletObject[] zzIm()
  {
    return this.zzbpi;
  }
  
  @Deprecated
  public OfferWalletObject[] zzIn()
  {
    return this.zzbpj;
  }
  
  public final class Builder
  {
    private Builder() {}
    
    public MaskedWallet build()
    {
      return MaskedWallet.this;
    }
    
    public Builder setBillingAddress(Address paramAddress)
    {
      MaskedWallet.this.zzbos = paramAddress;
      return this;
    }
    
    public Builder setBuyerBillingAddress(UserAddress paramUserAddress)
    {
      MaskedWallet.this.zzbov = paramUserAddress;
      return this;
    }
    
    public Builder setBuyerShippingAddress(UserAddress paramUserAddress)
    {
      MaskedWallet.this.zzbow = paramUserAddress;
      return this;
    }
    
    public Builder setEmail(String paramString)
    {
      MaskedWallet.this.zzbor = paramString;
      return this;
    }
    
    public Builder setGoogleTransactionId(String paramString)
    {
      MaskedWallet.this.zzboo = paramString;
      return this;
    }
    
    public Builder setInstrumentInfos(InstrumentInfo[] paramArrayOfInstrumentInfo)
    {
      MaskedWallet.this.zzbox = paramArrayOfInstrumentInfo;
      return this;
    }
    
    public Builder setMerchantTransactionId(String paramString)
    {
      MaskedWallet.this.zzbop = paramString;
      return this;
    }
    
    public Builder setPaymentDescriptions(String[] paramArrayOfString)
    {
      MaskedWallet.this.zzbou = paramArrayOfString;
      return this;
    }
    
    public Builder setShippingAddress(Address paramAddress)
    {
      MaskedWallet.this.zzbot = paramAddress;
      return this;
    }
    
    @Deprecated
    public Builder zza(LoyaltyWalletObject[] paramArrayOfLoyaltyWalletObject)
    {
      MaskedWallet.this.zzbpi = paramArrayOfLoyaltyWalletObject;
      return this;
    }
    
    @Deprecated
    public Builder zza(OfferWalletObject[] paramArrayOfOfferWalletObject)
    {
      MaskedWallet.this.zzbpj = paramArrayOfOfferWalletObject;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wallet\MaskedWallet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */