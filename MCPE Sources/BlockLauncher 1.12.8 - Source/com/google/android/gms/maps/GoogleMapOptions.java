package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions
  implements SafeParcelable
{
  public static final zza CREATOR = new zza();
  private final int mVersionCode;
  private Boolean zzaRP;
  private Boolean zzaRQ;
  private int zzaRR = -1;
  private CameraPosition zzaRS;
  private Boolean zzaRT;
  private Boolean zzaRU;
  private Boolean zzaRV;
  private Boolean zzaRW;
  private Boolean zzaRX;
  private Boolean zzaRY;
  private Boolean zzaRZ;
  private Boolean zzaSa;
  private Boolean zzaSb;
  
  public GoogleMapOptions()
  {
    this.mVersionCode = 1;
  }
  
  GoogleMapOptions(int paramInt1, byte paramByte1, byte paramByte2, int paramInt2, CameraPosition paramCameraPosition, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, byte paramByte7, byte paramByte8, byte paramByte9, byte paramByte10, byte paramByte11)
  {
    this.mVersionCode = paramInt1;
    this.zzaRP = com.google.android.gms.maps.internal.zza.zza(paramByte1);
    this.zzaRQ = com.google.android.gms.maps.internal.zza.zza(paramByte2);
    this.zzaRR = paramInt2;
    this.zzaRS = paramCameraPosition;
    this.zzaRT = com.google.android.gms.maps.internal.zza.zza(paramByte3);
    this.zzaRU = com.google.android.gms.maps.internal.zza.zza(paramByte4);
    this.zzaRV = com.google.android.gms.maps.internal.zza.zza(paramByte5);
    this.zzaRW = com.google.android.gms.maps.internal.zza.zza(paramByte6);
    this.zzaRX = com.google.android.gms.maps.internal.zza.zza(paramByte7);
    this.zzaRY = com.google.android.gms.maps.internal.zza.zza(paramByte8);
    this.zzaRZ = com.google.android.gms.maps.internal.zza.zza(paramByte9);
    this.zzaSa = com.google.android.gms.maps.internal.zza.zza(paramByte10);
    this.zzaSb = com.google.android.gms.maps.internal.zza.zza(paramByte11);
  }
  
  public static GoogleMapOptions createFromAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet == null) {
      return null;
    }
    TypedArray localTypedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
    GoogleMapOptions localGoogleMapOptions = new GoogleMapOptions();
    if (localTypedArray.hasValue(R.styleable.MapAttrs_mapType)) {
      localGoogleMapOptions.mapType(localTypedArray.getInt(R.styleable.MapAttrs_mapType, -1));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_zOrderOnTop)) {
      localGoogleMapOptions.zOrderOnTop(localTypedArray.getBoolean(R.styleable.MapAttrs_zOrderOnTop, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_useViewLifecycle)) {
      localGoogleMapOptions.useViewLifecycleInFragment(localTypedArray.getBoolean(R.styleable.MapAttrs_useViewLifecycle, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiCompass)) {
      localGoogleMapOptions.compassEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiCompass, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiRotateGestures)) {
      localGoogleMapOptions.rotateGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiRotateGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiScrollGestures)) {
      localGoogleMapOptions.scrollGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiScrollGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiTiltGestures)) {
      localGoogleMapOptions.tiltGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiTiltGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiZoomGestures)) {
      localGoogleMapOptions.zoomGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiZoomGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiZoomControls)) {
      localGoogleMapOptions.zoomControlsEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiZoomControls, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_liteMode)) {
      localGoogleMapOptions.liteMode(localTypedArray.getBoolean(R.styleable.MapAttrs_liteMode, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiMapToolbar)) {
      localGoogleMapOptions.mapToolbarEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiMapToolbar, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_ambientEnabled)) {
      localGoogleMapOptions.ambientEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_ambientEnabled, false));
    }
    localGoogleMapOptions.camera(CameraPosition.createFromAttributes(paramContext, paramAttributeSet));
    localTypedArray.recycle();
    return localGoogleMapOptions;
  }
  
  public GoogleMapOptions ambientEnabled(boolean paramBoolean)
  {
    this.zzaSb = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions camera(CameraPosition paramCameraPosition)
  {
    this.zzaRS = paramCameraPosition;
    return this;
  }
  
  public GoogleMapOptions compassEnabled(boolean paramBoolean)
  {
    this.zzaRU = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Boolean getAmbientEnabled()
  {
    return this.zzaSb;
  }
  
  public CameraPosition getCamera()
  {
    return this.zzaRS;
  }
  
  public Boolean getCompassEnabled()
  {
    return this.zzaRU;
  }
  
  public Boolean getLiteMode()
  {
    return this.zzaRZ;
  }
  
  public Boolean getMapToolbarEnabled()
  {
    return this.zzaSa;
  }
  
  public int getMapType()
  {
    return this.zzaRR;
  }
  
  public Boolean getRotateGesturesEnabled()
  {
    return this.zzaRY;
  }
  
  public Boolean getScrollGesturesEnabled()
  {
    return this.zzaRV;
  }
  
  public Boolean getTiltGesturesEnabled()
  {
    return this.zzaRX;
  }
  
  public Boolean getUseViewLifecycleInFragment()
  {
    return this.zzaRQ;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public Boolean getZOrderOnTop()
  {
    return this.zzaRP;
  }
  
  public Boolean getZoomControlsEnabled()
  {
    return this.zzaRT;
  }
  
  public Boolean getZoomGesturesEnabled()
  {
    return this.zzaRW;
  }
  
  public GoogleMapOptions liteMode(boolean paramBoolean)
  {
    this.zzaRZ = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions mapToolbarEnabled(boolean paramBoolean)
  {
    this.zzaSa = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions mapType(int paramInt)
  {
    this.zzaRR = paramInt;
    return this;
  }
  
  public GoogleMapOptions rotateGesturesEnabled(boolean paramBoolean)
  {
    this.zzaRY = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions scrollGesturesEnabled(boolean paramBoolean)
  {
    this.zzaRV = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions tiltGesturesEnabled(boolean paramBoolean)
  {
    this.zzaRX = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions useViewLifecycleInFragment(boolean paramBoolean)
  {
    this.zzaRQ = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public GoogleMapOptions zOrderOnTop(boolean paramBoolean)
  {
    this.zzaRP = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions zoomControlsEnabled(boolean paramBoolean)
  {
    this.zzaRT = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions zoomGesturesEnabled(boolean paramBoolean)
  {
    this.zzaRW = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  byte zzzK()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzaRP);
  }
  
  byte zzzL()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzaRQ);
  }
  
  byte zzzM()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzaRT);
  }
  
  byte zzzN()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzaRU);
  }
  
  byte zzzO()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzaRV);
  }
  
  byte zzzP()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzaRW);
  }
  
  byte zzzQ()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzaRX);
  }
  
  byte zzzR()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzaRY);
  }
  
  byte zzzS()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzaRZ);
  }
  
  byte zzzT()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzaSa);
  }
  
  byte zzzU()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzaSb);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\maps\GoogleMapOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */