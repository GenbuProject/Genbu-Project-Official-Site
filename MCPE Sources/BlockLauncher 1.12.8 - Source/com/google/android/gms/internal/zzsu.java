package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public abstract class zzsu
{
  protected volatile int zzbuu = -1;
  
  public static final <T extends zzsu> T mergeFrom(T paramT, byte[] paramArrayOfByte)
    throws zzst
  {
    return mergeFrom(paramT, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static final <T extends zzsu> T mergeFrom(T paramT, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws zzst
  {
    try
    {
      paramArrayOfByte = zzsm.zza(paramArrayOfByte, paramInt1, paramInt2);
      paramT.mergeFrom(paramArrayOfByte);
      paramArrayOfByte.zzmn(0);
      return paramT;
    }
    catch (zzst paramT)
    {
      throw paramT;
    }
    catch (IOException paramT)
    {
      throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
    }
  }
  
  public static final boolean messageNanoEquals(zzsu paramzzsu1, zzsu paramzzsu2)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramzzsu1 == paramzzsu2) {
      bool1 = true;
    }
    int i;
    do
    {
      do
      {
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          } while (paramzzsu1 == null);
          bool1 = bool2;
        } while (paramzzsu2 == null);
        bool1 = bool2;
      } while (paramzzsu1.getClass() != paramzzsu2.getClass());
      i = paramzzsu1.getSerializedSize();
      bool1 = bool2;
    } while (paramzzsu2.getSerializedSize() != i);
    byte[] arrayOfByte1 = new byte[i];
    byte[] arrayOfByte2 = new byte[i];
    toByteArray(paramzzsu1, arrayOfByte1, 0, i);
    toByteArray(paramzzsu2, arrayOfByte2, 0, i);
    return Arrays.equals(arrayOfByte1, arrayOfByte2);
  }
  
  public static final void toByteArray(zzsu paramzzsu, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      paramArrayOfByte = zzsn.zzb(paramArrayOfByte, paramInt1, paramInt2);
      paramzzsu.writeTo(paramArrayOfByte);
      paramArrayOfByte.zzJo();
      return;
    }
    catch (IOException paramzzsu)
    {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", paramzzsu);
    }
  }
  
  public static final byte[] toByteArray(zzsu paramzzsu)
  {
    byte[] arrayOfByte = new byte[paramzzsu.getSerializedSize()];
    toByteArray(paramzzsu, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
  
  public zzsu clone()
    throws CloneNotSupportedException
  {
    return (zzsu)super.clone();
  }
  
  public int getCachedSize()
  {
    if (this.zzbuu < 0) {
      getSerializedSize();
    }
    return this.zzbuu;
  }
  
  public int getSerializedSize()
  {
    int i = zzz();
    this.zzbuu = i;
    return i;
  }
  
  public abstract zzsu mergeFrom(zzsm paramzzsm)
    throws IOException;
  
  public String toString()
  {
    return zzsv.zzf(this);
  }
  
  public void writeTo(zzsn paramzzsn)
    throws IOException
  {}
  
  protected int zzz()
  {
    return 0;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzsu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */