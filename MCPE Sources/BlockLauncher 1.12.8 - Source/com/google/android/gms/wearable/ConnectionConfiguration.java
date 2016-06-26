package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class ConnectionConfiguration
  implements SafeParcelable
{
  public static final Parcelable.Creator<ConnectionConfiguration> CREATOR = new zzg();
  private final String mName;
  final int mVersionCode;
  private boolean zzTb;
  private final int zzabB;
  private final int zzapo;
  private final String zzawc;
  private final boolean zzbqY;
  private String zzbqZ;
  private boolean zzbra;
  private String zzbrb;
  
  ConnectionConfiguration(int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, String paramString3, boolean paramBoolean3, String paramString4)
  {
    this.mVersionCode = paramInt1;
    this.mName = paramString1;
    this.zzawc = paramString2;
    this.zzabB = paramInt2;
    this.zzapo = paramInt3;
    this.zzbqY = paramBoolean1;
    this.zzTb = paramBoolean2;
    this.zzbqZ = paramString3;
    this.zzbra = paramBoolean3;
    this.zzbrb = paramString4;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ConnectionConfiguration)) {}
    do
    {
      return false;
      paramObject = (ConnectionConfiguration)paramObject;
    } while ((!zzw.equal(Integer.valueOf(this.mVersionCode), Integer.valueOf(((ConnectionConfiguration)paramObject).mVersionCode))) || (!zzw.equal(this.mName, ((ConnectionConfiguration)paramObject).mName)) || (!zzw.equal(this.zzawc, ((ConnectionConfiguration)paramObject).zzawc)) || (!zzw.equal(Integer.valueOf(this.zzabB), Integer.valueOf(((ConnectionConfiguration)paramObject).zzabB))) || (!zzw.equal(Integer.valueOf(this.zzapo), Integer.valueOf(((ConnectionConfiguration)paramObject).zzapo))) || (!zzw.equal(Boolean.valueOf(this.zzbqY), Boolean.valueOf(((ConnectionConfiguration)paramObject).zzbqY))) || (!zzw.equal(Boolean.valueOf(this.zzbra), Boolean.valueOf(((ConnectionConfiguration)paramObject).zzbra))));
    return true;
  }
  
  public String getAddress()
  {
    return this.zzawc;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getNodeId()
  {
    return this.zzbrb;
  }
  
  public int getRole()
  {
    return this.zzapo;
  }
  
  public int getType()
  {
    return this.zzabB;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.mVersionCode), this.mName, this.zzawc, Integer.valueOf(this.zzabB), Integer.valueOf(this.zzapo), Boolean.valueOf(this.zzbqY), Boolean.valueOf(this.zzbra) });
  }
  
  public boolean isConnected()
  {
    return this.zzTb;
  }
  
  public boolean isEnabled()
  {
    return this.zzbqY;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("ConnectionConfiguration[ ");
    localStringBuilder.append("mName=" + this.mName);
    localStringBuilder.append(", mAddress=" + this.zzawc);
    localStringBuilder.append(", mType=" + this.zzabB);
    localStringBuilder.append(", mRole=" + this.zzapo);
    localStringBuilder.append(", mEnabled=" + this.zzbqY);
    localStringBuilder.append(", mIsConnected=" + this.zzTb);
    localStringBuilder.append(", mPeerNodeId=" + this.zzbqZ);
    localStringBuilder.append(", mBtlePriority=" + this.zzbra);
    localStringBuilder.append(", mNodeId=" + this.zzbrb);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
  
  public String zzIt()
  {
    return this.zzbqZ;
  }
  
  public boolean zzIu()
  {
    return this.zzbra;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\ConnectionConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */