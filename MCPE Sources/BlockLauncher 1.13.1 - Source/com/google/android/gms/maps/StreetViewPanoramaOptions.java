package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions
  implements SafeParcelable
{
  public static final zzb CREATOR = new zzb();
  private final int mVersionCode;
  private Boolean zzaRQ;
  private Boolean zzaRW = Boolean.valueOf(true);
  private StreetViewPanoramaCamera zzaSD;
  private String zzaSE;
  private LatLng zzaSF;
  private Integer zzaSG;
  private Boolean zzaSH = Boolean.valueOf(true);
  private Boolean zzaSI = Boolean.valueOf(true);
  private Boolean zzaSJ = Boolean.valueOf(true);
  
  public StreetViewPanoramaOptions()
  {
    this.mVersionCode = 1;
  }
  
  StreetViewPanoramaOptions(int paramInt, StreetViewPanoramaCamera paramStreetViewPanoramaCamera, String paramString, LatLng paramLatLng, Integer paramInteger, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5)
  {
    this.mVersionCode = paramInt;
    this.zzaSD = paramStreetViewPanoramaCamera;
    this.zzaSF = paramLatLng;
    this.zzaSG = paramInteger;
    this.zzaSE = paramString;
    this.zzaSH = zza.zza(paramByte1);
    this.zzaRW = zza.zza(paramByte2);
    this.zzaSI = zza.zza(paramByte3);
    this.zzaSJ = zza.zza(paramByte4);
    this.zzaRQ = zza.zza(paramByte5);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Boolean getPanningGesturesEnabled()
  {
    return this.zzaSI;
  }
  
  public String getPanoramaId()
  {
    return this.zzaSE;
  }
  
  public LatLng getPosition()
  {
    return this.zzaSF;
  }
  
  public Integer getRadius()
  {
    return this.zzaSG;
  }
  
  public Boolean getStreetNamesEnabled()
  {
    return this.zzaSJ;
  }
  
  public StreetViewPanoramaCamera getStreetViewPanoramaCamera()
  {
    return this.zzaSD;
  }
  
  public Boolean getUseViewLifecycleInFragment()
  {
    return this.zzaRQ;
  }
  
  public Boolean getUserNavigationEnabled()
  {
    return this.zzaSH;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public Boolean getZoomGesturesEnabled()
  {
    return this.zzaRW;
  }
  
  public StreetViewPanoramaOptions panningGesturesEnabled(boolean paramBoolean)
  {
    this.zzaSI = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera paramStreetViewPanoramaCamera)
  {
    this.zzaSD = paramStreetViewPanoramaCamera;
    return this;
  }
  
  public StreetViewPanoramaOptions panoramaId(String paramString)
  {
    this.zzaSE = paramString;
    return this;
  }
  
  public StreetViewPanoramaOptions position(LatLng paramLatLng)
  {
    this.zzaSF = paramLatLng;
    return this;
  }
  
  public StreetViewPanoramaOptions position(LatLng paramLatLng, Integer paramInteger)
  {
    this.zzaSF = paramLatLng;
    this.zzaSG = paramInteger;
    return this;
  }
  
  public StreetViewPanoramaOptions streetNamesEnabled(boolean paramBoolean)
  {
    this.zzaSJ = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public StreetViewPanoramaOptions useViewLifecycleInFragment(boolean paramBoolean)
  {
    this.zzaRQ = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public StreetViewPanoramaOptions userNavigationEnabled(boolean paramBoolean)
  {
    this.zzaSH = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public StreetViewPanoramaOptions zoomGesturesEnabled(boolean paramBoolean)
  {
    this.zzaRW = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  byte zzAa()
  {
    return zza.zze(this.zzaSH);
  }
  
  byte zzAb()
  {
    return zza.zze(this.zzaSI);
  }
  
  byte zzAc()
  {
    return zza.zze(this.zzaSJ);
  }
  
  byte zzzL()
  {
    return zza.zze(this.zzaRQ);
  }
  
  byte zzzP()
  {
    return zza.zze(this.zzaRW);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\maps\StreetViewPanoramaOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */