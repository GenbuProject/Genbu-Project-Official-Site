package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CircleOptions
  implements SafeParcelable
{
  public static final zzb CREATOR = new zzb();
  private final int mVersionCode;
  private LatLng zzaTc = null;
  private double zzaTd = 0.0D;
  private float zzaTe = 10.0F;
  private int zzaTf = -16777216;
  private int zzaTg = 0;
  private float zzaTh = 0.0F;
  private boolean zzaTi = true;
  
  public CircleOptions()
  {
    this.mVersionCode = 1;
  }
  
  CircleOptions(int paramInt1, LatLng paramLatLng, double paramDouble, float paramFloat1, int paramInt2, int paramInt3, float paramFloat2, boolean paramBoolean)
  {
    this.mVersionCode = paramInt1;
    this.zzaTc = paramLatLng;
    this.zzaTd = paramDouble;
    this.zzaTe = paramFloat1;
    this.zzaTf = paramInt2;
    this.zzaTg = paramInt3;
    this.zzaTh = paramFloat2;
    this.zzaTi = paramBoolean;
  }
  
  public CircleOptions center(LatLng paramLatLng)
  {
    this.zzaTc = paramLatLng;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public CircleOptions fillColor(int paramInt)
  {
    this.zzaTg = paramInt;
    return this;
  }
  
  public LatLng getCenter()
  {
    return this.zzaTc;
  }
  
  public int getFillColor()
  {
    return this.zzaTg;
  }
  
  public double getRadius()
  {
    return this.zzaTd;
  }
  
  public int getStrokeColor()
  {
    return this.zzaTf;
  }
  
  public float getStrokeWidth()
  {
    return this.zzaTe;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public float getZIndex()
  {
    return this.zzaTh;
  }
  
  public boolean isVisible()
  {
    return this.zzaTi;
  }
  
  public CircleOptions radius(double paramDouble)
  {
    this.zzaTd = paramDouble;
    return this;
  }
  
  public CircleOptions strokeColor(int paramInt)
  {
    this.zzaTf = paramInt;
    return this;
  }
  
  public CircleOptions strokeWidth(float paramFloat)
  {
    this.zzaTe = paramFloat;
    return this;
  }
  
  public CircleOptions visible(boolean paramBoolean)
  {
    this.zzaTi = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public CircleOptions zIndex(float paramFloat)
  {
    this.zzaTh = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\maps\model\CircleOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */