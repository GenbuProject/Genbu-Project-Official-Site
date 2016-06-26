package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Node;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CapabilityInfoParcelable
  implements SafeParcelable, CapabilityInfo
{
  public static final Parcelable.Creator<CapabilityInfoParcelable> CREATOR = new zzk();
  private final String mName;
  final int mVersionCode;
  private Set<Node> zzbrS;
  private final List<NodeParcelable> zzbrV;
  private final Object zzpV = new Object();
  
  CapabilityInfoParcelable(int paramInt, String paramString, List<NodeParcelable> paramList)
  {
    this.mVersionCode = paramInt;
    this.mName = paramString;
    this.zzbrV = paramList;
    this.zzbrS = null;
    zzIH();
  }
  
  private void zzIH()
  {
    zzx.zzz(this.mName);
    zzx.zzz(this.zzbrV);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (CapabilityInfoParcelable)paramObject;
      if (this.mVersionCode != ((CapabilityInfoParcelable)paramObject).mVersionCode) {
        return false;
      }
      if (this.mName != null)
      {
        if (this.mName.equals(((CapabilityInfoParcelable)paramObject).mName)) {}
      }
      else {
        while (((CapabilityInfoParcelable)paramObject).mName != null) {
          return false;
        }
      }
      if (this.zzbrV == null) {
        break;
      }
    } while (this.zzbrV.equals(((CapabilityInfoParcelable)paramObject).zzbrV));
    for (;;)
    {
      return false;
      if (((CapabilityInfoParcelable)paramObject).zzbrV == null) {
        break;
      }
    }
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public Set<Node> getNodes()
  {
    synchronized (this.zzpV)
    {
      if (this.zzbrS == null) {
        this.zzbrS = new HashSet(this.zzbrV);
      }
      Set localSet = this.zzbrS;
      return localSet;
    }
  }
  
  public int hashCode()
  {
    int j = 0;
    int k = this.mVersionCode;
    if (this.mName != null) {}
    for (int i = this.mName.hashCode();; i = 0)
    {
      if (this.zzbrV != null) {
        j = this.zzbrV.hashCode();
      }
      return (i + k * 31) * 31 + j;
    }
  }
  
  public String toString()
  {
    return "CapabilityInfo{" + this.mName + ", " + this.zzbrV + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzk.zza(this, paramParcel, paramInt);
  }
  
  public List<NodeParcelable> zzII()
  {
    return this.zzbrV;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\CapabilityInfoParcelable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */