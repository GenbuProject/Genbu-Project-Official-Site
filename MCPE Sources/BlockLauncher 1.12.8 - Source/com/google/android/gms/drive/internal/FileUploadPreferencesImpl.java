package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.FileUploadPreferences;

public final class FileUploadPreferencesImpl
  implements SafeParcelable, FileUploadPreferences
{
  public static final Parcelable.Creator<FileUploadPreferencesImpl> CREATOR = new zzag();
  final int mVersionCode;
  int zzarG;
  int zzarH;
  boolean zzarI;
  
  FileUploadPreferencesImpl(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    this.mVersionCode = paramInt1;
    this.zzarG = paramInt2;
    this.zzarH = paramInt3;
    this.zzarI = paramBoolean;
  }
  
  public static boolean zzdj(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static boolean zzdk(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getBatteryUsagePreference()
  {
    if (!zzdk(this.zzarH)) {
      return 0;
    }
    return this.zzarH;
  }
  
  public int getNetworkTypePreference()
  {
    if (!zzdj(this.zzarG)) {
      return 0;
    }
    return this.zzarG;
  }
  
  public boolean isRoamingAllowed()
  {
    return this.zzarI;
  }
  
  public void setBatteryUsagePreference(int paramInt)
  {
    if (!zzdk(paramInt)) {
      throw new IllegalArgumentException("Invalid battery usage preference value.");
    }
    this.zzarH = paramInt;
  }
  
  public void setNetworkTypePreference(int paramInt)
  {
    if (!zzdj(paramInt)) {
      throw new IllegalArgumentException("Invalid data connection preference value.");
    }
    this.zzarG = paramInt;
  }
  
  public void setRoamingAllowed(boolean paramBoolean)
  {
    this.zzarI = paramBoolean;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzag.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\FileUploadPreferencesImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */