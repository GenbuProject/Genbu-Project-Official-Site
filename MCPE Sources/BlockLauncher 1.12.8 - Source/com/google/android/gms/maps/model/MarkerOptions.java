package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public final class MarkerOptions
  implements SafeParcelable
{
  public static final zzf CREATOR = new zzf();
  private float mAlpha = 1.0F;
  private final int mVersionCode;
  private LatLng zzaSF;
  private BitmapDescriptor zzaTA;
  private boolean zzaTB;
  private boolean zzaTC = false;
  private float zzaTD = 0.0F;
  private float zzaTE = 0.5F;
  private float zzaTF = 0.0F;
  private boolean zzaTi = true;
  private float zzaTp = 0.5F;
  private float zzaTq = 1.0F;
  private String zzaTz;
  private String zzapg;
  
  public MarkerOptions()
  {
    this.mVersionCode = 1;
  }
  
  MarkerOptions(int paramInt, LatLng paramLatLng, String paramString1, String paramString2, IBinder paramIBinder, float paramFloat1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    this.mVersionCode = paramInt;
    this.zzaSF = paramLatLng;
    this.zzapg = paramString1;
    this.zzaTz = paramString2;
    if (paramIBinder == null) {}
    for (paramLatLng = null;; paramLatLng = new BitmapDescriptor(zzd.zza.zzbs(paramIBinder)))
    {
      this.zzaTA = paramLatLng;
      this.zzaTp = paramFloat1;
      this.zzaTq = paramFloat2;
      this.zzaTB = paramBoolean1;
      this.zzaTi = paramBoolean2;
      this.zzaTC = paramBoolean3;
      this.zzaTD = paramFloat3;
      this.zzaTE = paramFloat4;
      this.zzaTF = paramFloat5;
      this.mAlpha = paramFloat6;
      return;
    }
  }
  
  public MarkerOptions alpha(float paramFloat)
  {
    this.mAlpha = paramFloat;
    return this;
  }
  
  public MarkerOptions anchor(float paramFloat1, float paramFloat2)
  {
    this.zzaTp = paramFloat1;
    this.zzaTq = paramFloat2;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public MarkerOptions draggable(boolean paramBoolean)
  {
    this.zzaTB = paramBoolean;
    return this;
  }
  
  public MarkerOptions flat(boolean paramBoolean)
  {
    this.zzaTC = paramBoolean;
    return this;
  }
  
  public float getAlpha()
  {
    return this.mAlpha;
  }
  
  public float getAnchorU()
  {
    return this.zzaTp;
  }
  
  public float getAnchorV()
  {
    return this.zzaTq;
  }
  
  public BitmapDescriptor getIcon()
  {
    return this.zzaTA;
  }
  
  public float getInfoWindowAnchorU()
  {
    return this.zzaTE;
  }
  
  public float getInfoWindowAnchorV()
  {
    return this.zzaTF;
  }
  
  public LatLng getPosition()
  {
    return this.zzaSF;
  }
  
  public float getRotation()
  {
    return this.zzaTD;
  }
  
  public String getSnippet()
  {
    return this.zzaTz;
  }
  
  public String getTitle()
  {
    return this.zzapg;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public MarkerOptions icon(BitmapDescriptor paramBitmapDescriptor)
  {
    this.zzaTA = paramBitmapDescriptor;
    return this;
  }
  
  public MarkerOptions infoWindowAnchor(float paramFloat1, float paramFloat2)
  {
    this.zzaTE = paramFloat1;
    this.zzaTF = paramFloat2;
    return this;
  }
  
  public boolean isDraggable()
  {
    return this.zzaTB;
  }
  
  public boolean isFlat()
  {
    return this.zzaTC;
  }
  
  public boolean isVisible()
  {
    return this.zzaTi;
  }
  
  public MarkerOptions position(LatLng paramLatLng)
  {
    this.zzaSF = paramLatLng;
    return this;
  }
  
  public MarkerOptions rotation(float paramFloat)
  {
    this.zzaTD = paramFloat;
    return this;
  }
  
  public MarkerOptions snippet(String paramString)
  {
    this.zzaTz = paramString;
    return this;
  }
  
  public MarkerOptions title(String paramString)
  {
    this.zzapg = paramString;
    return this;
  }
  
  public MarkerOptions visible(boolean paramBoolean)
  {
    this.zzaTi = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzf.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzAk()
  {
    if (this.zzaTA == null) {
      return null;
    }
    return this.zzaTA.zzzH().asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\maps\model\MarkerOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */