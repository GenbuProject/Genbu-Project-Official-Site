package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.Node;

public class NodeParcelable
  implements SafeParcelable, Node
{
  public static final Parcelable.Creator<NodeParcelable> CREATOR = new zzbc();
  final int mVersionCode;
  private final String zzWQ;
  private final int zzbsY;
  private final boolean zzbsZ;
  private final String zzyv;
  
  NodeParcelable(int paramInt1, String paramString1, String paramString2, int paramInt2, boolean paramBoolean)
  {
    this.mVersionCode = paramInt1;
    this.zzyv = paramString1;
    this.zzWQ = paramString2;
    this.zzbsY = paramInt2;
    this.zzbsZ = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof NodeParcelable)) {
      return false;
    }
    return ((NodeParcelable)paramObject).zzyv.equals(this.zzyv);
  }
  
  public String getDisplayName()
  {
    return this.zzWQ;
  }
  
  public int getHopCount()
  {
    return this.zzbsY;
  }
  
  public String getId()
  {
    return this.zzyv;
  }
  
  public int hashCode()
  {
    return this.zzyv.hashCode();
  }
  
  public boolean isNearby()
  {
    return this.zzbsZ;
  }
  
  public String toString()
  {
    return "Node{" + this.zzWQ + ", id=" + this.zzyv + ", hops=" + this.zzbsY + ", isNearby=" + this.zzbsZ + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\NodeParcelable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */