package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzsw
{
  final int tag;
  final byte[] zzbuv;
  
  zzsw(int paramInt, byte[] paramArrayOfByte)
  {
    this.tag = paramInt;
    this.zzbuv = paramArrayOfByte;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzsw)) {
        return false;
      }
      paramObject = (zzsw)paramObject;
    } while ((this.tag == ((zzsw)paramObject).tag) && (Arrays.equals(this.zzbuv, ((zzsw)paramObject).zzbuv)));
    return false;
  }
  
  public int hashCode()
  {
    return (this.tag + 527) * 31 + Arrays.hashCode(this.zzbuv);
  }
  
  void writeTo(zzsn paramzzsn)
    throws IOException
  {
    paramzzsn.zzmB(this.tag);
    paramzzsn.zzH(this.zzbuv);
  }
  
  int zzz()
  {
    return 0 + zzsn.zzmC(this.tag) + this.zzbuv.length;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzsw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */