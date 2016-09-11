package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;

public final class MaskedWalletRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<MaskedWalletRequest> CREATOR = new zzm();
  private final int mVersionCode;
  String zzboi;
  String zzbop;
  Cart zzboz;
  boolean zzbpl;
  boolean zzbpm;
  boolean zzbpn;
  String zzbpo;
  String zzbpp;
  boolean zzbpq;
  boolean zzbpr;
  CountrySpecification[] zzbps;
  boolean zzbpt;
  boolean zzbpu;
  ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> zzbpv;
  PaymentMethodTokenizationParameters zzbpw;
  ArrayList<Integer> zzbpx;
  
  MaskedWalletRequest()
  {
    this.mVersionCode = 3;
    this.zzbpt = true;
    this.zzbpu = true;
  }
  
  MaskedWalletRequest(int paramInt, String paramString1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2, String paramString3, String paramString4, Cart paramCart, boolean paramBoolean4, boolean paramBoolean5, CountrySpecification[] paramArrayOfCountrySpecification, boolean paramBoolean6, boolean paramBoolean7, ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> paramArrayList, PaymentMethodTokenizationParameters paramPaymentMethodTokenizationParameters, ArrayList<Integer> paramArrayList1)
  {
    this.mVersionCode = paramInt;
    this.zzbop = paramString1;
    this.zzbpl = paramBoolean1;
    this.zzbpm = paramBoolean2;
    this.zzbpn = paramBoolean3;
    this.zzbpo = paramString2;
    this.zzboi = paramString3;
    this.zzbpp = paramString4;
    this.zzboz = paramCart;
    this.zzbpq = paramBoolean4;
    this.zzbpr = paramBoolean5;
    this.zzbps = paramArrayOfCountrySpecification;
    this.zzbpt = paramBoolean6;
    this.zzbpu = paramBoolean7;
    this.zzbpv = paramArrayList;
    this.zzbpw = paramPaymentMethodTokenizationParameters;
    this.zzbpx = paramArrayList1;
  }
  
  public static Builder newBuilder()
  {
    MaskedWalletRequest localMaskedWalletRequest = new MaskedWalletRequest();
    localMaskedWalletRequest.getClass();
    return new Builder(null);
  }
  
  public boolean allowDebitCard()
  {
    return this.zzbpu;
  }
  
  public boolean allowPrepaidCard()
  {
    return this.zzbpt;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public ArrayList<Integer> getAllowedCardNetworks()
  {
    return this.zzbpx;
  }
  
  public ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> getAllowedCountrySpecificationsForShipping()
  {
    return this.zzbpv;
  }
  
  public CountrySpecification[] getAllowedShippingCountrySpecifications()
  {
    return this.zzbps;
  }
  
  public Cart getCart()
  {
    return this.zzboz;
  }
  
  public String getCurrencyCode()
  {
    return this.zzboi;
  }
  
  public String getEstimatedTotalPrice()
  {
    return this.zzbpo;
  }
  
  public String getMerchantName()
  {
    return this.zzbpp;
  }
  
  public String getMerchantTransactionId()
  {
    return this.zzbop;
  }
  
  public PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters()
  {
    return this.zzbpw;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  @Deprecated
  public boolean isBillingAgreement()
  {
    return this.zzbpr;
  }
  
  public boolean isPhoneNumberRequired()
  {
    return this.zzbpl;
  }
  
  public boolean isShippingAddressRequired()
  {
    return this.zzbpm;
  }
  
  @Deprecated
  public boolean useMinimalBillingAddress()
  {
    return this.zzbpn;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzm.zza(this, paramParcel, paramInt);
  }
  
  public final class Builder
  {
    private Builder() {}
    
    public Builder addAllowedCardNetwork(int paramInt)
    {
      if (MaskedWalletRequest.this.zzbpx == null) {
        MaskedWalletRequest.this.zzbpx = new ArrayList();
      }
      MaskedWalletRequest.this.zzbpx.add(Integer.valueOf(paramInt));
      return this;
    }
    
    public Builder addAllowedCardNetworks(Collection<Integer> paramCollection)
    {
      if (paramCollection != null)
      {
        if (MaskedWalletRequest.this.zzbpx == null) {
          MaskedWalletRequest.this.zzbpx = new ArrayList();
        }
        MaskedWalletRequest.this.zzbpx.addAll(paramCollection);
      }
      return this;
    }
    
    public Builder addAllowedCountrySpecificationForShipping(com.google.android.gms.identity.intents.model.CountrySpecification paramCountrySpecification)
    {
      if (MaskedWalletRequest.this.zzbpv == null) {
        MaskedWalletRequest.this.zzbpv = new ArrayList();
      }
      MaskedWalletRequest.this.zzbpv.add(paramCountrySpecification);
      return this;
    }
    
    public Builder addAllowedCountrySpecificationsForShipping(Collection<com.google.android.gms.identity.intents.model.CountrySpecification> paramCollection)
    {
      if (paramCollection != null)
      {
        if (MaskedWalletRequest.this.zzbpv == null) {
          MaskedWalletRequest.this.zzbpv = new ArrayList();
        }
        MaskedWalletRequest.this.zzbpv.addAll(paramCollection);
      }
      return this;
    }
    
    public MaskedWalletRequest build()
    {
      return MaskedWalletRequest.this;
    }
    
    public Builder setAllowDebitCard(boolean paramBoolean)
    {
      MaskedWalletRequest.this.zzbpu = paramBoolean;
      return this;
    }
    
    public Builder setAllowPrepaidCard(boolean paramBoolean)
    {
      MaskedWalletRequest.this.zzbpt = paramBoolean;
      return this;
    }
    
    public Builder setCart(Cart paramCart)
    {
      MaskedWalletRequest.this.zzboz = paramCart;
      return this;
    }
    
    public Builder setCurrencyCode(String paramString)
    {
      MaskedWalletRequest.this.zzboi = paramString;
      return this;
    }
    
    public Builder setEstimatedTotalPrice(String paramString)
    {
      MaskedWalletRequest.this.zzbpo = paramString;
      return this;
    }
    
    @Deprecated
    public Builder setIsBillingAgreement(boolean paramBoolean)
    {
      MaskedWalletRequest.this.zzbpr = paramBoolean;
      return this;
    }
    
    public Builder setMerchantName(String paramString)
    {
      MaskedWalletRequest.this.zzbpp = paramString;
      return this;
    }
    
    public Builder setMerchantTransactionId(String paramString)
    {
      MaskedWalletRequest.this.zzbop = paramString;
      return this;
    }
    
    public Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters paramPaymentMethodTokenizationParameters)
    {
      MaskedWalletRequest.this.zzbpw = paramPaymentMethodTokenizationParameters;
      return this;
    }
    
    public Builder setPhoneNumberRequired(boolean paramBoolean)
    {
      MaskedWalletRequest.this.zzbpl = paramBoolean;
      return this;
    }
    
    public Builder setShippingAddressRequired(boolean paramBoolean)
    {
      MaskedWalletRequest.this.zzbpm = paramBoolean;
      return this;
    }
    
    @Deprecated
    public Builder setUseMinimalBillingAddress(boolean paramBoolean)
    {
      MaskedWalletRequest.this.zzbpn = paramBoolean;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\MaskedWalletRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */