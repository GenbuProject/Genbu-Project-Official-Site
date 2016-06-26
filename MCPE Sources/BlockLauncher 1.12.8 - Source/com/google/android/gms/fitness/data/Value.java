package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.internal.zzmy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public final class Value
  implements SafeParcelable
{
  public static final Parcelable.Creator<Value> CREATOR = new zzu();
  private final int mVersionCode;
  private final int zzawZ;
  private float zzaxe;
  private boolean zzaxq;
  private String zzaxr;
  private Map<String, MapValue> zzaxs;
  private int[] zzaxt;
  private float[] zzaxu;
  private byte[] zzaxv;
  
  public Value(int paramInt)
  {
    this(3, paramInt, false, 0.0F, null, null, null, null, null);
  }
  
  Value(int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat, String paramString, Bundle paramBundle, int[] paramArrayOfInt, float[] paramArrayOfFloat, byte[] paramArrayOfByte)
  {
    this.mVersionCode = paramInt1;
    this.zzawZ = paramInt2;
    this.zzaxq = paramBoolean;
    this.zzaxe = paramFloat;
    this.zzaxr = paramString;
    this.zzaxs = zzv(paramBundle);
    this.zzaxt = paramArrayOfInt;
    this.zzaxu = paramArrayOfFloat;
    this.zzaxv = paramArrayOfByte;
  }
  
  private boolean zza(Value paramValue)
  {
    if ((this.zzawZ == paramValue.zzawZ) && (this.zzaxq == paramValue.zzaxq))
    {
      switch (this.zzawZ)
      {
      default: 
        if (this.zzaxe != paramValue.zzaxe) {
          break;
        }
      case 1: 
      case 2: 
        do
        {
          do
          {
            return true;
          } while (asInt() == paramValue.asInt());
          return false;
        } while (this.zzaxe == paramValue.zzaxe);
        return false;
      case 3: 
        return zzw.equal(this.zzaxr, paramValue.zzaxr);
      case 4: 
        return zzw.equal(this.zzaxs, paramValue.zzaxs);
      case 5: 
        return Arrays.equals(this.zzaxt, paramValue.zzaxt);
      case 6: 
        return Arrays.equals(this.zzaxu, paramValue.zzaxu);
      case 7: 
        return Arrays.equals(this.zzaxv, paramValue.zzaxv);
      }
      return false;
    }
    return false;
  }
  
  private static Map<String, MapValue> zzv(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return null;
    }
    paramBundle.setClassLoader(MapValue.class.getClassLoader());
    ArrayMap localArrayMap = new ArrayMap(paramBundle.size());
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayMap.put(str, paramBundle.getParcelable(str));
    }
    return localArrayMap;
  }
  
  public String asActivity()
  {
    return FitnessActivities.getName(asInt());
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
  
  public int asInt()
  {
    boolean bool = true;
    if (this.zzawZ == 1) {}
    for (;;)
    {
      zzx.zza(bool, "Value is not in int format");
      return Float.floatToRawIntBits(this.zzaxe);
      bool = false;
    }
  }
  
  public String asString()
  {
    if (this.zzawZ == 3) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Value is not in string format");
      return this.zzaxr;
    }
  }
  
  public void clearKey(String paramString)
  {
    if (this.zzawZ == 4) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
      if (this.zzaxs != null) {
        this.zzaxs.remove(paramString);
      }
      return;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof Value)) && (zza((Value)paramObject)));
  }
  
  public int getFormat()
  {
    return this.zzawZ;
  }
  
  @Nullable
  public Float getKeyValue(String paramString)
  {
    if (this.zzawZ == 4) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Value is not in float map format");
      if ((this.zzaxs == null) || (!this.zzaxs.containsKey(paramString))) {
        break;
      }
      return Float.valueOf(((MapValue)this.zzaxs.get(paramString)).asFloat());
    }
    return null;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Float.valueOf(this.zzaxe), this.zzaxr, this.zzaxs, this.zzaxt, this.zzaxu, this.zzaxv });
  }
  
  public boolean isSet()
  {
    return this.zzaxq;
  }
  
  public void setActivity(String paramString)
  {
    setInt(FitnessActivities.zzdm(paramString));
  }
  
  public void setFloat(float paramFloat)
  {
    if (this.zzawZ == 2) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Attempting to set an float value to a field that is not in FLOAT format.  Please check the data type definition and use the right format.");
      this.zzaxq = true;
      this.zzaxe = paramFloat;
      return;
    }
  }
  
  public void setInt(int paramInt)
  {
    if (this.zzawZ == 1) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Attempting to set an int value to a field that is not in INT32 format.  Please check the data type definition and use the right format.");
      this.zzaxq = true;
      this.zzaxe = Float.intBitsToFloat(paramInt);
      return;
    }
  }
  
  public void setKeyValue(String paramString, float paramFloat)
  {
    if (this.zzawZ == 4) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
      this.zzaxq = true;
      if (this.zzaxs == null) {
        this.zzaxs = new HashMap();
      }
      this.zzaxs.put(paramString, MapValue.zzc(paramFloat));
      return;
    }
  }
  
  public void setString(String paramString)
  {
    if (this.zzawZ == 3) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Attempting to set a string value to a field that is not in STRING format.  Please check the data type definition and use the right format.");
      this.zzaxq = true;
      this.zzaxr = paramString;
      return;
    }
  }
  
  public String toString()
  {
    if (!this.zzaxq) {
      return "unset";
    }
    switch (this.zzawZ)
    {
    default: 
      return "unknown";
    case 1: 
      return Integer.toString(asInt());
    case 2: 
      return Float.toString(this.zzaxe);
    case 3: 
      return this.zzaxr;
    case 4: 
      return new TreeMap(this.zzaxs).toString();
    case 5: 
      return Arrays.toString(this.zzaxt);
    case 6: 
      return Arrays.toString(this.zzaxu);
    }
    return zzmy.zza(this.zzaxv, 0, this.zzaxv.length, false);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzu.zza(this, paramParcel, paramInt);
  }
  
  String zzuA()
  {
    return this.zzaxr;
  }
  
  Bundle zzuB()
  {
    if (this.zzaxs == null) {
      return null;
    }
    Bundle localBundle = new Bundle(this.zzaxs.size());
    Iterator localIterator = this.zzaxs.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localBundle.putParcelable((String)localEntry.getKey(), (Parcelable)localEntry.getValue());
    }
    return localBundle;
  }
  
  int[] zzuC()
  {
    return this.zzaxt;
  }
  
  float[] zzuD()
  {
    return this.zzaxu;
  }
  
  byte[] zzuE()
  {
    return this.zzaxv;
  }
  
  float zzuv()
  {
    return this.zzaxe;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\Value.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */