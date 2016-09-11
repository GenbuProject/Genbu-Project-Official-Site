package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzc;

public final class LocationSettingsStates
  implements SafeParcelable
{
  public static final Parcelable.Creator<LocationSettingsStates> CREATOR = new zzh();
  private final int mVersionCode;
  private final boolean zzaOj;
  private final boolean zzaOk;
  private final boolean zzaOl;
  private final boolean zzaOm;
  private final boolean zzaOn;
  private final boolean zzaOo;
  
  LocationSettingsStates(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6)
  {
    this.mVersionCode = paramInt;
    this.zzaOj = paramBoolean1;
    this.zzaOk = paramBoolean2;
    this.zzaOl = paramBoolean3;
    this.zzaOm = paramBoolean4;
    this.zzaOn = paramBoolean5;
    this.zzaOo = paramBoolean6;
  }
  
  public static LocationSettingsStates fromIntent(Intent paramIntent)
  {
    return (LocationSettingsStates)zzc.zza(paramIntent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public boolean isBlePresent()
  {
    return this.zzaOo;
  }
  
  public boolean isBleUsable()
  {
    return this.zzaOl;
  }
  
  public boolean isGpsPresent()
  {
    return this.zzaOm;
  }
  
  public boolean isGpsUsable()
  {
    return this.zzaOj;
  }
  
  public boolean isLocationPresent()
  {
    return (this.zzaOm) || (this.zzaOn);
  }
  
  public boolean isLocationUsable()
  {
    return (this.zzaOj) || (this.zzaOk);
  }
  
  public boolean isNetworkLocationPresent()
  {
    return this.zzaOn;
  }
  
  public boolean isNetworkLocationUsable()
  {
    return this.zzaOk;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\LocationSettingsStates.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */