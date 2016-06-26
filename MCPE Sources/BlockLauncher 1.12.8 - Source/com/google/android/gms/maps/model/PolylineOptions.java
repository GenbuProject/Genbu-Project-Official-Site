package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolylineOptions
  implements SafeParcelable
{
  public static final zzi CREATOR = new zzi();
  private int mColor = -16777216;
  private final int mVersionCode;
  private final List<LatLng> zzaTJ;
  private boolean zzaTL = false;
  private float zzaTh = 0.0F;
  private boolean zzaTi = true;
  private float zzaTm = 10.0F;
  private boolean zzaTr = false;
  
  public PolylineOptions()
  {
    this.mVersionCode = 1;
    this.zzaTJ = new ArrayList();
  }
  
  PolylineOptions(int paramInt1, List paramList, float paramFloat1, int paramInt2, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.mVersionCode = paramInt1;
    this.zzaTJ = paramList;
    this.zzaTm = paramFloat1;
    this.mColor = paramInt2;
    this.zzaTh = paramFloat2;
    this.zzaTi = paramBoolean1;
    this.zzaTL = paramBoolean2;
    this.zzaTr = paramBoolean3;
  }
  
  public PolylineOptions add(LatLng paramLatLng)
  {
    this.zzaTJ.add(paramLatLng);
    return this;
  }
  
  public PolylineOptions add(LatLng... paramVarArgs)
  {
    this.zzaTJ.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public PolylineOptions addAll(Iterable<LatLng> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      LatLng localLatLng = (LatLng)paramIterable.next();
      this.zzaTJ.add(localLatLng);
    }
    return this;
  }
  
  public PolylineOptions clickable(boolean paramBoolean)
  {
    this.zzaTr = paramBoolean;
    return this;
  }
  
  public PolylineOptions color(int paramInt)
  {
    this.mColor = paramInt;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public PolylineOptions geodesic(boolean paramBoolean)
  {
    this.zzaTL = paramBoolean;
    return this;
  }
  
  public int getColor()
  {
    return this.mColor;
  }
  
  public List<LatLng> getPoints()
  {
    return this.zzaTJ;
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
  
  public boolean isClickable()
  {
    return this.zzaTr;
  }
  
  public boolean isGeodesic()
  {
    return this.zzaTL;
  }
  
  public boolean isVisible()
  {
    return this.zzaTi;
  }
  
  public PolylineOptions visible(boolean paramBoolean)
  {
    this.zzaTi = paramBoolean;
    return this;
  }
  
  public PolylineOptions width(float paramFloat)
  {
    this.zzaTm = paramFloat;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzi.zza(this, paramParcel, paramInt);
  }
  
  public PolylineOptions zIndex(float paramFloat)
  {
    this.zzaTh = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\maps\model\PolylineOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */