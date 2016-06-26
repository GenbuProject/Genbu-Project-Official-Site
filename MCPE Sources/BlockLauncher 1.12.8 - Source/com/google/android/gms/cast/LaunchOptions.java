package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.cast.internal.zzf;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.Locale;

public class LaunchOptions
  implements SafeParcelable
{
  public static final Parcelable.Creator<LaunchOptions> CREATOR = new zzd();
  private final int mVersionCode;
  private boolean zzaaK;
  private String zzaaL;
  
  public LaunchOptions()
  {
    this(1, false, zzf.zzb(Locale.getDefault()));
  }
  
  LaunchOptions(int paramInt, boolean paramBoolean, String paramString)
  {
    this.mVersionCode = paramInt;
    this.zzaaK = paramBoolean;
    this.zzaaL = paramString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof LaunchOptions)) {
        return false;
      }
      paramObject = (LaunchOptions)paramObject;
    } while ((this.zzaaK == ((LaunchOptions)paramObject).zzaaK) && (zzf.zza(this.zzaaL, ((LaunchOptions)paramObject).zzaaL)));
    return false;
  }
  
  public String getLanguage()
  {
    return this.zzaaL;
  }
  
  public boolean getRelaunchIfRunning()
  {
    return this.zzaaK;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Boolean.valueOf(this.zzaaK), this.zzaaL });
  }
  
  public void setLanguage(String paramString)
  {
    this.zzaaL = paramString;
  }
  
  public void setRelaunchIfRunning(boolean paramBoolean)
  {
    this.zzaaK = paramBoolean;
  }
  
  public String toString()
  {
    return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", new Object[] { Boolean.valueOf(this.zzaaK), this.zzaaL });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private LaunchOptions zzaaM = new LaunchOptions();
    
    public LaunchOptions build()
    {
      return this.zzaaM;
    }
    
    public Builder setLocale(Locale paramLocale)
    {
      this.zzaaM.setLanguage(zzf.zzb(paramLocale));
      return this;
    }
    
    public Builder setRelaunchIfRunning(boolean paramBoolean)
    {
      this.zzaaM.setRelaunchIfRunning(paramBoolean);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\LaunchOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */