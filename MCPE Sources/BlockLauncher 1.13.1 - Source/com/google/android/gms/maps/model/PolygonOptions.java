package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolygonOptions
  implements SafeParcelable
{
  public static final zzh CREATOR = new zzh();
  private final int mVersionCode;
  private final List<LatLng> zzaTJ;
  private final List<List<LatLng>> zzaTK;
  private boolean zzaTL = false;
  private float zzaTe = 10.0F;
  private int zzaTf = -16777216;
  private int zzaTg = 0;
  private float zzaTh = 0.0F;
  private boolean zzaTi = true;
  private boolean zzaTr = false;
  
  public PolygonOptions()
  {
    this.mVersionCode = 1;
    this.zzaTJ = new ArrayList();
    this.zzaTK = new ArrayList();
  }
  
  PolygonOptions(int paramInt1, List<LatLng> paramList, List paramList1, float paramFloat1, int paramInt2, int paramInt3, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.mVersionCode = paramInt1;
    this.zzaTJ = paramList;
    this.zzaTK = paramList1;
    this.zzaTe = paramFloat1;
    this.zzaTf = paramInt2;
    this.zzaTg = paramInt3;
    this.zzaTh = paramFloat2;
    this.zzaTi = paramBoolean1;
    this.zzaTL = paramBoolean2;
    this.zzaTr = paramBoolean3;
  }
  
  public PolygonOptions add(LatLng paramLatLng)
  {
    this.zzaTJ.add(paramLatLng);
    return this;
  }
  
  public PolygonOptions add(LatLng... paramVarArgs)
  {
    this.zzaTJ.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public PolygonOptions addAll(Iterable<LatLng> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      LatLng localLatLng = (LatLng)paramIterable.next();
      this.zzaTJ.add(localLatLng);
    }
    return this;
  }
  
  public PolygonOptions addHole(Iterable<LatLng> paramIterable)
  {
    ArrayList localArrayList = new ArrayList();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      localArrayList.add((LatLng)paramIterable.next());
    }
    this.zzaTK.add(localArrayList);
    return this;
  }
  
  public PolygonOptions clickable(boolean paramBoolean)
  {
    this.zzaTr = paramBoolean;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public PolygonOptions fillColor(int paramInt)
  {
    this.zzaTg = paramInt;
    return this;
  }
  
  public PolygonOptions geodesic(boolean paramBoolean)
  {
    this.zzaTL = paramBoolean;
    return this;
  }
  
  public int getFillColor()
  {
    return this.zzaTg;
  }
  
  public List<List<LatLng>> getHoles()
  {
    return this.zzaTK;
  }
  
  public List<LatLng> getPoints()
  {
    return this.zzaTJ;
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
  
  public PolygonOptions strokeColor(int paramInt)
  {
    this.zzaTf = paramInt;
    return this;
  }
  
  public PolygonOptions strokeWidth(float paramFloat)
  {
    this.zzaTe = paramFloat;
    return this;
  }
  
  public PolygonOptions visible(boolean paramBoolean)
  {
    this.zzaTi = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
  
  public PolygonOptions zIndex(float paramFloat)
  {
    this.zzaTh = paramFloat;
    return this;
  }
  
  List zzAl()
  {
    return this.zzaTK;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\maps\model\PolygonOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */