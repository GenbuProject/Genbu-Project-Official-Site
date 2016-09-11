package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public final class GroundOverlayOptions
  implements SafeParcelable
{
  public static final zzc CREATOR = new zzc();
  public static final float NO_DIMENSION = -1.0F;
  private final int mVersionCode;
  private LatLngBounds zzaRk;
  private float zzaTa;
  private float zzaTh;
  private boolean zzaTi = true;
  private BitmapDescriptor zzaTk;
  private LatLng zzaTl;
  private float zzaTm;
  private float zzaTn;
  private float zzaTo = 0.0F;
  private float zzaTp = 0.5F;
  private float zzaTq = 0.5F;
  private boolean zzaTr = false;
  
  public GroundOverlayOptions()
  {
    this.mVersionCode = 1;
  }
  
  GroundOverlayOptions(int paramInt, IBinder paramIBinder, LatLng paramLatLng, float paramFloat1, float paramFloat2, LatLngBounds paramLatLngBounds, float paramFloat3, float paramFloat4, boolean paramBoolean1, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean2)
  {
    this.mVersionCode = paramInt;
    this.zzaTk = new BitmapDescriptor(zzd.zza.zzbs(paramIBinder));
    this.zzaTl = paramLatLng;
    this.zzaTm = paramFloat1;
    this.zzaTn = paramFloat2;
    this.zzaRk = paramLatLngBounds;
    this.zzaTa = paramFloat3;
    this.zzaTh = paramFloat4;
    this.zzaTi = paramBoolean1;
    this.zzaTo = paramFloat5;
    this.zzaTp = paramFloat6;
    this.zzaTq = paramFloat7;
    this.zzaTr = paramBoolean2;
  }
  
  private GroundOverlayOptions zza(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    this.zzaTl = paramLatLng;
    this.zzaTm = paramFloat1;
    this.zzaTn = paramFloat2;
    return this;
  }
  
  public GroundOverlayOptions anchor(float paramFloat1, float paramFloat2)
  {
    this.zzaTp = paramFloat1;
    this.zzaTq = paramFloat2;
    return this;
  }
  
  public GroundOverlayOptions bearing(float paramFloat)
  {
    this.zzaTa = ((paramFloat % 360.0F + 360.0F) % 360.0F);
    return this;
  }
  
  public GroundOverlayOptions clickable(boolean paramBoolean)
  {
    this.zzaTr = paramBoolean;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public float getAnchorU()
  {
    return this.zzaTp;
  }
  
  public float getAnchorV()
  {
    return this.zzaTq;
  }
  
  public float getBearing()
  {
    return this.zzaTa;
  }
  
  public LatLngBounds getBounds()
  {
    return this.zzaRk;
  }
  
  public float getHeight()
  {
    return this.zzaTn;
  }
  
  public BitmapDescriptor getImage()
  {
    return this.zzaTk;
  }
  
  public LatLng getLocation()
  {
    return this.zzaTl;
  }
  
  public float getTransparency()
  {
    return this.zzaTo;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public float getWidth()
  {
    return this.zzaTm;
  }
  
  public float getZIndex()
  {
    return this.zzaTh;
  }
  
  public GroundOverlayOptions image(BitmapDescriptor paramBitmapDescriptor)
  {
    this.zzaTk = paramBitmapDescriptor;
    return this;
  }
  
  public boolean isClickable()
  {
    return this.zzaTr;
  }
  
  public boolean isVisible()
  {
    return this.zzaTi;
  }
  
  public GroundOverlayOptions position(LatLng paramLatLng, float paramFloat)
  {
    boolean bool2 = true;
    if (this.zzaRk == null)
    {
      bool1 = true;
      zzx.zza(bool1, "Position has already been set using positionFromBounds");
      if (paramLatLng == null) {
        break label59;
      }
      bool1 = true;
      label24:
      zzx.zzb(bool1, "Location must be specified");
      if (paramFloat < 0.0F) {
        break label64;
      }
    }
    label59:
    label64:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzx.zzb(bool1, "Width must be non-negative");
      return zza(paramLatLng, paramFloat, -1.0F);
      bool1 = false;
      break;
      bool1 = false;
      break label24;
    }
  }
  
  public GroundOverlayOptions position(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    boolean bool2 = true;
    if (this.zzaRk == null)
    {
      bool1 = true;
      zzx.zza(bool1, "Position has already been set using positionFromBounds");
      if (paramLatLng == null) {
        break label81;
      }
      bool1 = true;
      label27:
      zzx.zzb(bool1, "Location must be specified");
      if (paramFloat1 < 0.0F) {
        break label87;
      }
      bool1 = true;
      label43:
      zzx.zzb(bool1, "Width must be non-negative");
      if (paramFloat2 < 0.0F) {
        break label93;
      }
    }
    label81:
    label87:
    label93:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzx.zzb(bool1, "Height must be non-negative");
      return zza(paramLatLng, paramFloat1, paramFloat2);
      bool1 = false;
      break;
      bool1 = false;
      break label27;
      bool1 = false;
      break label43;
    }
  }
  
  public GroundOverlayOptions positionFromBounds(LatLngBounds paramLatLngBounds)
  {
    if (this.zzaTl == null) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Position has already been set using position: " + this.zzaTl);
      this.zzaRk = paramLatLngBounds;
      return this;
    }
  }
  
  public GroundOverlayOptions transparency(float paramFloat)
  {
    if ((paramFloat >= 0.0F) && (paramFloat <= 1.0F)) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "Transparency must be in the range [0..1]");
      this.zzaTo = paramFloat;
      return this;
    }
  }
  
  public GroundOverlayOptions visible(boolean paramBoolean)
  {
    this.zzaTi = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public GroundOverlayOptions zIndex(float paramFloat)
  {
    this.zzaTh = paramFloat;
    return this;
  }
  
  IBinder zzAj()
  {
    return this.zzaTk.zzzH().asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\maps\model\GroundOverlayOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */