package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public final class CredentialRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<CredentialRequest> CREATOR = new zzc();
  final int mVersionCode;
  private final boolean zzWd;
  private final String[] zzWe;
  private final CredentialPickerConfig zzWf;
  private final CredentialPickerConfig zzWg;
  
  CredentialRequest(int paramInt, boolean paramBoolean, String[] paramArrayOfString, CredentialPickerConfig paramCredentialPickerConfig1, CredentialPickerConfig paramCredentialPickerConfig2)
  {
    this.mVersionCode = paramInt;
    this.zzWd = paramBoolean;
    this.zzWe = ((String[])zzx.zzz(paramArrayOfString));
    paramArrayOfString = paramCredentialPickerConfig1;
    if (paramCredentialPickerConfig1 == null) {
      paramArrayOfString = new CredentialPickerConfig.Builder().build();
    }
    this.zzWf = paramArrayOfString;
    paramArrayOfString = paramCredentialPickerConfig2;
    if (paramCredentialPickerConfig2 == null) {
      paramArrayOfString = new CredentialPickerConfig.Builder().build();
    }
    this.zzWg = paramArrayOfString;
  }
  
  private CredentialRequest(Builder paramBuilder)
  {
    this(2, Builder.zza(paramBuilder), Builder.zzb(paramBuilder), Builder.zzc(paramBuilder), Builder.zzd(paramBuilder));
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
  public CredentialPickerConfig getCredentialHintPickerConfig()
  {
    return this.zzWg;
  }
  
  @NonNull
  public CredentialPickerConfig getCredentialPickerConfig()
  {
    return this.zzWf;
  }
  
  @Deprecated
  public boolean getSupportsPasswordLogin()
  {
    return isPasswordLoginSupported();
  }
  
  public boolean isPasswordLoginSupported()
  {
    return this.zzWd;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private boolean zzWd;
    private String[] zzWe;
    private CredentialPickerConfig zzWf;
    private CredentialPickerConfig zzWg;
    
    public CredentialRequest build()
    {
      if (this.zzWe == null) {
        this.zzWe = new String[0];
      }
      if ((!this.zzWd) && (this.zzWe.length == 0)) {
        throw new IllegalStateException("At least one authentication method must be specified");
      }
      return new CredentialRequest(this, null);
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
    
    public Builder setCredentialHintPickerConfig(CredentialPickerConfig paramCredentialPickerConfig)
    {
      this.zzWg = paramCredentialPickerConfig;
      return this;
    }
    
    public Builder setCredentialPickerConfig(CredentialPickerConfig paramCredentialPickerConfig)
    {
      this.zzWf = paramCredentialPickerConfig;
      return this;
    }
    
    public Builder setPasswordLoginSupported(boolean paramBoolean)
    {
      this.zzWd = paramBoolean;
      return this;
    }
    
    @Deprecated
    public Builder setSupportsPasswordLogin(boolean paramBoolean)
    {
      return setPasswordLoginSupported(paramBoolean);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\api\credentials\CredentialRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */