package com.google.android.gms.nearby.messages.devices;

import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;

public class zzb
{
  private final zzc zzbbZ;
  
  public zzb(byte[] paramArrayOfByte)
  {
    this.zzbbZ = new zzc(zzu(paramArrayOfByte));
  }
  
  private static byte[] zzu(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length == 16) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "Bytes must be a namespace plus instance (16 bytes).");
      return paramArrayOfByte;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzb)) {
      return false;
    }
    paramObject = (zzb)paramObject;
    return zzw.equal(this.zzbbZ, ((zzb)paramObject).zzbbZ);
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzbbZ });
  }
  
  public String toString()
  {
    return "EddystoneUid{id=" + zzEt() + '}';
  }
  
  public String zzEt()
  {
    return this.zzbbZ.zzEt();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\devices\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */