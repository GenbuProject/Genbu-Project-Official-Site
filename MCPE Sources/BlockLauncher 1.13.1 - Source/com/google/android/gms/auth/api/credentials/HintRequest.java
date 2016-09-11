package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public final class HintRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<HintRequest> CREATOR = new zzd();
  final int mVersionCode;
  private final String[] zzWe;
  private final CredentialPickerConfig zzWh;
  private final boolean zzWi;
  private final boolean zzWj;
  
  HintRequest(int paramInt, CredentialPickerConfig paramCredentialPickerConfig, boolean paramBoolean1, boolean paramBoolean2, String[] paramArrayOfString)
  {
    this.mVersionCode = paramInt;
    this.zzWh = ((CredentialPickerConfig)zzx.zzz(paramCredentialPickerConfig));
    this.zzWi = paramBoolean1;
    this.zzWj = paramBoolean2;
    this.zzWe = ((String[])zzx.zzz(paramArrayOfString));
  }
  
  private HintRequest(Builder paramBuilder)
  {
    this(1, Builder.zza(paramBuilder), Builder.zzb(paramBuilder), Builder.zzc(paramBuilder), Builder.zzd(paramBuilder));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  @NonNull
  public String[] getAccountTypes()
  {
    return this.zzWe;
  }
  
  @NonNull
  public CredentialPickerConfig getHintPickerConfig()
  {
    return this.zzWh;
  }
  
  public boolean isEmailAddressIdentifierSupported()
  {
    return this.zzWi;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
  
  public boolean zzmy()
  {
    return this.zzWj;
  }
  
  public static final class Builder
  {
    private String[] zzWe;
    private CredentialPickerConfig zzWh = new CredentialPickerConfig.Builder().build();
    private boolean zzWi;
    private boolean zzWj;
    
    public HintRequest build()
    {
      if (this.zzWe == null) {
        this.zzWe = new String[0];
      }
      if ((!this.zzWi) && (!this.zzWj) && (this.zzWe.length == 0)) {
        throw new IllegalStateException("At least one authentication method must be specified");
      }
      return new HintRequest(this, null);
    }
    
    public Builder setAccountTypes(String... paramVarArgs)
    {
      String[] arrayOfString = paramVarArgs;
      if (paramVarArgs == null) {
        arrayOfString = new String[0];
      }
      this.zzWe = arrayOfString;
      return this;
    }
    
    public Builder setEmailAddressIdentifierSupported(boolean paramBoolean)
    {
      this.zzWi = paramBoolean;
      return this;
    }
    
    public Builder setHintPickerConfig(@NonNull CredentialPickerConfig paramCredentialPickerConfig)
    {
      this.zzWh = ((CredentialPickerConfig)zzx.zzz(paramCredentialPickerConfig));
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\api\credentials\HintRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */