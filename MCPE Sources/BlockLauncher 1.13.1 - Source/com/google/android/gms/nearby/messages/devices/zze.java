package com.google.android.gms.nearby.messages.devices;

import com.google.android.gms.common.internal.zzx;
import java.nio.ByteBuffer;
import java.util.UUID;

class zze
  extends zza
{
  public zze(byte[] paramArrayOfByte)
  {
    super(zzu(paramArrayOfByte));
  }
  
  private static byte[] zzu(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte.length == 16) || (paramArrayOfByte.length == 18) || (paramArrayOfByte.length == 20)) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "Prefix must be a UUID, a UUID and a major, or a UUID, a major, and a minor.");
      return paramArrayOfByte;
    }
  }
  
  public String toString()
  {
    return "IBeaconIdPrefix{proximityUuid=" + zzEu() + ", major=" + zzEx() + ", minor=" + zzEy() + '}';
  }
  
  public UUID zzEu()
  {
    ByteBuffer localByteBuffer = ByteBuffer.wrap(getBytes());
    return new UUID(localByteBuffer.getLong(), localByteBuffer.getLong());
  }
  
  public Short zzEx()
  {
    byte[] arrayOfByte = getBytes();
    if (arrayOfByte.length >= 18) {
      return Short.valueOf(ByteBuffer.wrap(arrayOfByte).getShort(16));
    }
    return null;
  }
  
  public Short zzEy()
  {
    byte[] arrayOfByte = getBytes();
    if (arrayOfByte.length == 20) {
      return Short.valueOf(ByteBuffer.wrap(arrayOfByte).getShort(18));
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\devices\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */