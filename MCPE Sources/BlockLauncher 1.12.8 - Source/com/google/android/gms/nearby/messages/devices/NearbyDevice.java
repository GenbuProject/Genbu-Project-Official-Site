package com.google.android.gms.nearby.messages.devices;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import java.util.Arrays;

public class NearbyDevice
  implements SafeParcelable
{
  public static final Parcelable.Creator<NearbyDevice> CREATOR = new zzf();
  private static final NearbyDeviceId[] zzbcb = new NearbyDeviceId[0];
  private static final String[] zzbcc = new String[0];
  public static final NearbyDevice zzbcd = new NearbyDevice("", zzbcb, zzbcc);
  final int mVersionCode;
  @Deprecated
  @Nullable
  private final String zzF;
  @Deprecated
  private final NearbyDeviceId zzbce;
  private final String zzbcf;
  private final NearbyDeviceId[] zzbcg;
  private final String[] zzbch;
  
  NearbyDevice(int paramInt, @Nullable NearbyDeviceId paramNearbyDeviceId, @Nullable String paramString1, @Nullable String paramString2, @Nullable NearbyDeviceId[] paramArrayOfNearbyDeviceId, @Nullable String[] paramArrayOfString)
  {
    this.mVersionCode = ((Integer)zzx.zzz(Integer.valueOf(paramInt))).intValue();
    paramNearbyDeviceId = paramString2;
    if (paramString2 == null) {
      paramNearbyDeviceId = "";
    }
    this.zzbcf = paramNearbyDeviceId;
    paramNearbyDeviceId = paramArrayOfNearbyDeviceId;
    if (paramArrayOfNearbyDeviceId == null) {
      paramNearbyDeviceId = zzbcb;
    }
    this.zzbcg = paramNearbyDeviceId;
    paramNearbyDeviceId = paramArrayOfString;
    if (paramArrayOfString == null) {
      paramNearbyDeviceId = zzbcc;
    }
    this.zzbch = paramNearbyDeviceId;
    if (this.zzbcg.length == 0)
    {
      paramNearbyDeviceId = NearbyDeviceId.zzbcl;
      this.zzbce = paramNearbyDeviceId;
      if (this.zzbch.length != 0) {
        break label114;
      }
    }
    label114:
    for (paramNearbyDeviceId = null;; paramNearbyDeviceId = this.zzbch[0])
    {
      this.zzF = paramNearbyDeviceId;
      return;
      paramNearbyDeviceId = this.zzbcg[0];
      break;
    }
  }
  
  public NearbyDevice(String paramString, NearbyDeviceId[] paramArrayOfNearbyDeviceId, String[] paramArrayOfString)
  {
    this(1, null, null, paramString, paramArrayOfNearbyDeviceId, paramArrayOfString);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof NearbyDevice)) {
      return false;
    }
    paramObject = (NearbyDevice)paramObject;
    return zzw.equal(this.zzbcf, ((NearbyDevice)paramObject).zzbcf);
  }
  
  @Deprecated
  @Nullable
  public String getUrl()
  {
    if (this.zzbch.length == 0) {
      return null;
    }
    return this.zzbch[0];
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzbcf });
  }
  
  public String toString()
  {
    return "NearbyDevice{deviceHandle=" + this.zzbcf + ", ids=" + Arrays.toString(this.zzbcg) + ", urls=" + Arrays.toString(this.zzbch) + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzf.zza(this, paramParcel, paramInt);
  }
  
  public NearbyDeviceId[] zzEA()
  {
    return this.zzbcg;
  }
  
  public String[] zzEB()
  {
    return this.zzbch;
  }
  
  public String zzEC()
  {
    return this.zzbcf;
  }
  
  @Deprecated
  public NearbyDeviceId zzEz()
  {
    if (this.zzbcg.length == 0) {
      return NearbyDeviceId.zzbcl;
    }
    return this.zzbcg[0];
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\devices\NearbyDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */