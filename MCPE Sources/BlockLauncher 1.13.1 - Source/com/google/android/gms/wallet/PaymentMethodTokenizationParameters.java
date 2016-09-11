package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public final class PaymentMethodTokenizationParameters
  implements SafeParcelable
{
  public static final Parcelable.Creator<PaymentMethodTokenizationParameters> CREATOR = new zzq();
  private final int mVersionCode;
  int zzbpC;
  Bundle zzbpD = new Bundle();
  
  private PaymentMethodTokenizationParameters()
  {
    this.mVersionCode = 1;
  }
  
  PaymentMethodTokenizationParameters(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    this.mVersionCode = paramInt1;
    this.zzbpC = paramInt2;
    this.zzbpD = paramBundle;
  }
  
  public static Builder newBuilder()
  {
    PaymentMethodTokenizationParameters localPaymentMethodTokenizationParameters = new PaymentMethodTokenizationParameters();
    localPaymentMethodTokenizationParameters.getClass();
    return new Builder(null);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Bundle getParameters()
  {
    return new Bundle(this.zzbpD);
  }
  
  public int getPaymentMethodTokenizationType()
  {
    return this.zzbpC;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzq.zza(this, paramParcel, paramInt);
  }
  
  public final class Builder
  {
    private Builder() {}
    
    public Builder addParameter(String paramString1, String paramString2)
    {
      zzx.zzh(paramString1, "Tokenization parameter name must not be empty");
      zzx.zzh(paramString2, "Tokenization parameter value must not be empty");
      PaymentMethodTokenizationParameters.this.zzbpD.putString(paramString1, paramString2);
      return this;
    }
    
    public PaymentMethodTokenizationParameters build()
    {
      return PaymentMethodTokenizationParameters.this;
    }
    
    public Builder setPaymentMethodTokenizationType(int paramInt)
    {
      PaymentMethodTokenizationParameters.this.zzbpC = paramInt;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\PaymentMethodTokenizationParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */