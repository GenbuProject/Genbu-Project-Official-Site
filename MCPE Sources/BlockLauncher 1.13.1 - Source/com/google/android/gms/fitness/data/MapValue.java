package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public class MapValue
  implements SafeParcelable
{
  public static final Parcelable.Creator<MapValue> CREATOR = new zzm();
  private final int mVersionCode;
  private final int zzawZ;
  private final float zzaxe;
  
  public MapValue(int paramInt, float paramFloat)
  {
    this(1, paramInt, paramFloat);
  }
  
  MapValue(int paramInt1, int paramInt2, float paramFloat)
  {
    this.mVersionCode = paramInt1;
    this.zzawZ = paramInt2;
    this.zzaxe = paramFloat;
  }
  
  private boolean zza(MapValue paramMapValue)
  {
    if (this.zzawZ == paramMapValue.zzawZ)
    {
      switch (this.zzawZ)
      {
      default: 
        if (this.zzaxe != paramMapValue.zzaxe) {
          break;
        }
      case 2: 
        do
        {
          return true;
        } while (asFloat() == paramMapValue.asFloat());
        return false;
      }
      return false;
    }
    return false;
  }
  
  public static MapValue zzc(float paramFloat)
  {
    return new MapValue(2, paramFloat);
  }
  
  public float asFloat()
  {
    if (this.zzawZ == 2) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Value is not in float format");
      return this.zzaxe;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof MapValue)) && (zza((MapValue)paramObject)));
  }
  
  int getFormat()
  {
    return this.zzawZ;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return (int)this.zzaxe;
  }
  
  public String toString()
  {
    switch (this.zzawZ)
    {
    default: 
      return "unknown";
    }
    return Float.toString(asFloat());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzm.zza(this, paramParcel, paramInt);
  }
  
  float zzuv()
  {
    return this.zzaxe;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\data\MapValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */