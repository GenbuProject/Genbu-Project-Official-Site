package com.google.android.gms.nearby.messages.devices;

import java.util.Arrays;

abstract class zza
{
  private static final char[] zzbbX = "0123456789abcdef".toCharArray();
  private final byte[] zzbbY;
  
  protected zza(byte[] paramArrayOfByte)
  {
    this.zzbbY = paramArrayOfByte;
  }
  
  static String zzt(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      int k = paramArrayOfByte[i];
      localStringBuilder.append(zzbbX[(k >> 4 & 0xF)]).append(zzbbX[(k & 0xF)]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!paramObject.getClass().isAssignableFrom(getClass())) {
      return false;
    }
    paramObject = (zza)paramObject;
    return Arrays.equals(this.zzbbY, ((zza)paramObject).zzbbY);
  }
  
  byte[] getBytes()
  {
    return this.zzbbY;
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(this.zzbbY);
  }
  
  public String toString()
  {
    return zzt(this.zzbbY);
  }
  
  String zzEt()
  {
    return zzt(this.zzbbY);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\devices\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */