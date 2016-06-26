package com.google.android.gms.nearby.messages.devices;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class NearbyDeviceId
  implements SafeParcelable
{
  public static final Parcelable.Creator<NearbyDeviceId> CREATOR = new zzh();
  public static final NearbyDeviceId zzbcl = new NearbyDeviceId();
  final int mVersionCode;
  private final int zzabB;
  final byte[] zzbbY;
  private final zzb zzbcm;
  private final zzd zzbcn;
  
  private NearbyDeviceId()
  {
    this(1, 1, null);
  }
  
  NearbyDeviceId(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
  {
    this.mVersionCode = paramInt1;
    this.zzabB = paramInt2;
    this.zzbbY = paramArrayOfByte;
    if (paramInt2 == 2) {}
    for (Object localObject1 = new zzb(paramArrayOfByte);; localObject1 = null)
    {
      this.zzbcm = ((zzb)localObject1);
      localObject1 = localObject2;
      if (paramInt2 == 3) {
        localObject1 = new zzd(paramArrayOfByte);
      }
      this.zzbcn = ((zzd)localObject1);
      return;
    }
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
      if (!(paramObject instanceof NearbyDeviceId)) {
        return false;
      }
      paramObject = (NearbyDeviceId)paramObject;
    } while ((zzw.equal(Integer.valueOf(this.zzabB), Integer.valueOf(((NearbyDeviceId)paramObject).zzabB))) && (zzw.equal(this.zzbbY, ((NearbyDeviceId)paramObject).zzbbY)));
    return false;
  }
  
  public int getType()
  {
    return this.zzabB;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.zzabB), this.zzbbY });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("NearbyDeviceId{");
    switch (this.zzabB)
    {
    }
    for (;;)
    {
      localStringBuilder.append("}");
      return localStringBuilder.toString();
      localStringBuilder.append("eddystoneUid=").append(this.zzbcm);
      continue;
      localStringBuilder.append("iBeaconId=").append(this.zzbcn);
      continue;
      localStringBuilder.append("UNKNOWN");
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\devices\NearbyDeviceId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */