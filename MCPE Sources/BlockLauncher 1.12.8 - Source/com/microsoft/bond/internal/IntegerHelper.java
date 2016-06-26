package com.microsoft.bond.internal;

import com.microsoft.bond.io.BondInputStream;
import java.io.IOException;

public final class IntegerHelper
{
  public static final int MAX_BYTES_VARINT16 = 3;
  public static final int MAX_BYTES_VARINT32 = 5;
  public static final int MAX_BYTES_VARINT64 = 10;
  public static final int MAX_VARINT_SIZE_BYTES = 10;
  public static final int SIZEOF_BYTE = 1;
  public static final int SIZEOF_INT = 4;
  public static final int SIZEOF_LONG = 8;
  public static final int SIZEOF_SHORT = 2;
  
  public static short decodeVarInt16(BondInputStream paramBondInputStream)
    throws IOException
  {
    return (short)(int)decodeVarInt64(paramBondInputStream);
  }
  
  public static int decodeVarInt32(BondInputStream paramBondInputStream)
    throws IOException
  {
    return (int)decodeVarInt64(paramBondInputStream);
  }
  
  public static long decodeVarInt64(BondInputStream paramBondInputStream)
    throws IOException
  {
    long l1 = 0L;
    int i = 0;
    for (;;)
    {
      long l2 = l1;
      if (i < 64)
      {
        int j = paramBondInputStream.read();
        l1 |= (j & 0x7F) << i;
        if ((j & 0x80) == 0) {
          l2 = l1;
        }
      }
      else
      {
        return l2;
      }
      i += 7;
    }
  }
  
  public static short decodeZigzag16(short paramShort)
  {
    return (0xFFFF & paramShort) >>> 1 ^ -(paramShort & 0x1);
  }
  
  public static int decodeZigzag32(int paramInt)
  {
    return paramInt >>> 1 ^ -(paramInt & 0x1);
  }
  
  public static long decodeZigzag64(long paramLong)
  {
    return paramLong >>> 1 ^ -(1L & paramLong);
  }
  
  public static int encodeVarUInt16(short paramShort, byte[] paramArrayOfByte, int paramInt)
  {
    int i = paramShort;
    int j = paramInt;
    if ((0xFF80 & paramShort) != 0)
    {
      j = paramInt + 1;
      paramArrayOfByte[paramInt] = ((byte)(paramShort & 0x7F | 0x80));
      i = paramShort >>> 7;
      if ((0xFF80 & i) == 0) {
        break label81;
      }
      paramShort = j + 1;
      paramArrayOfByte[j] = ((byte)(i & 0x7F | 0x80));
      i >>>= 7;
      j = paramShort;
    }
    label81:
    for (;;)
    {
      paramArrayOfByte[j] = ((byte)(i & 0x7F));
      return j + 1;
    }
  }
  
  public static int encodeVarUInt16(byte[] paramArrayOfByte, short paramShort)
  {
    return encodeVarUInt16(paramShort, paramArrayOfByte, 0);
  }
  
  public static int encodeVarUInt32(int paramInt, byte[] paramArrayOfByte)
  {
    return encodeVarUInt32(paramInt, paramArrayOfByte, 0);
  }
  
  public static int encodeVarUInt32(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    int i = paramInt1;
    int j = paramInt2;
    if ((paramInt1 & 0xFFFFFF80) != 0)
    {
      j = paramInt2 + 1;
      paramArrayOfByte[paramInt2] = ((byte)(paramInt1 & 0x7F | 0x80));
      paramInt2 = paramInt1 >>> 7;
      paramInt1 = j;
      i = paramInt2;
      if ((paramInt2 & 0xFFFFFF80) == 0) {
        break label161;
      }
      paramInt1 = j + 1;
      paramArrayOfByte[j] = ((byte)(paramInt2 & 0x7F | 0x80));
      int k = paramInt2 >>> 7;
      i = k;
      j = paramInt1;
      if ((k & 0xFFFFFF80) != 0)
      {
        paramInt2 = paramInt1 + 1;
        paramArrayOfByte[paramInt1] = ((byte)(k & 0x7F | 0x80));
        j = k >>> 7;
        paramInt1 = paramInt2;
        i = j;
        if ((j & 0xFFFFFF80) == 0) {
          break label161;
        }
        paramInt1 = paramInt2 + 1;
        paramArrayOfByte[paramInt2] = ((byte)(j & 0x7F | 0x80));
        i = j >>> 7;
      }
    }
    label161:
    for (j = paramInt1;; j = paramInt1)
    {
      paramArrayOfByte[j] = ((byte)(i & 0x7F));
      return j + 1;
    }
  }
  
  public static int encodeVarUInt64(long paramLong, byte[] paramArrayOfByte)
  {
    return encodeVarUInt64(paramLong, paramArrayOfByte, 0);
  }
  
  public static int encodeVarUInt64(long paramLong, byte[] paramArrayOfByte, int paramInt)
  {
    long l = paramLong;
    int i = paramInt;
    int j;
    if ((0xFFFFFFFFFFFFFF80 & paramLong) != 0L)
    {
      i = paramInt + 1;
      paramArrayOfByte[paramInt] = ((byte)(int)(0x80 | 0x7F & paramLong));
      paramLong >>>= 7;
      paramInt = i;
      l = paramLong;
      if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L) {
        break label385;
      }
      paramInt = i + 1;
      paramArrayOfByte[i] = ((byte)(int)(0x80 | 0x7F & paramLong));
      paramLong >>>= 7;
      l = paramLong;
      i = paramInt;
      if ((0xFFFFFFFFFFFFFF80 & paramLong) != 0L)
      {
        i = paramInt + 1;
        paramArrayOfByte[paramInt] = ((byte)(int)(0x80 | 0x7F & paramLong));
        paramLong >>>= 7;
        paramInt = i;
        l = paramLong;
        if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L) {
          break label385;
        }
        paramInt = i + 1;
        paramArrayOfByte[i] = ((byte)(int)(0x80 | 0x7F & paramLong));
        paramLong >>>= 7;
        l = paramLong;
        i = paramInt;
        if ((0xFFFFFFFFFFFFFF80 & paramLong) != 0L)
        {
          i = paramInt + 1;
          paramArrayOfByte[paramInt] = ((byte)(int)(0x80 | 0x7F & paramLong));
          paramLong >>>= 7;
          paramInt = i;
          l = paramLong;
          if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L) {
            break label385;
          }
          paramInt = i + 1;
          paramArrayOfByte[i] = ((byte)(int)(0x80 | 0x7F & paramLong));
          paramLong >>>= 7;
          l = paramLong;
          i = paramInt;
          if ((0xFFFFFFFFFFFFFF80 & paramLong) != 0L)
          {
            j = paramInt + 1;
            paramArrayOfByte[paramInt] = ((byte)(int)(0x80 | 0x7F & paramLong));
            l = paramLong >>> 7;
            if ((0xFFFFFFFFFFFFFF80 & l) == 0L) {
              break label391;
            }
            i = j + 1;
            paramArrayOfByte[j] = ((byte)(int)(0x80 | 0x7F & l));
            paramLong = l >>> 7;
            paramInt = i;
            l = paramLong;
            if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L) {
              break label385;
            }
            paramInt = i + 1;
            paramArrayOfByte[i] = ((byte)(int)(0x80 | 0x7F & paramLong));
            l = paramLong >>> 7;
            i = paramInt;
          }
        }
      }
    }
    for (;;)
    {
      paramArrayOfByte[i] = ((byte)(int)(0x7F & l));
      return i + 1;
      label385:
      i = paramInt;
      continue;
      label391:
      i = j;
    }
  }
  
  public static short encodeZigzag16(short paramShort)
  {
    return paramShort << 1 ^ paramShort >> 15;
  }
  
  public static int encodeZigzag32(int paramInt)
  {
    return paramInt << 1 ^ paramInt >> 31;
  }
  
  public static long encodeZigzag64(long paramLong)
  {
    return paramLong << 1 ^ paramLong >> 63;
  }
  
  public static int getVarUInt16Size(short paramShort)
  {
    if ((0xFF80 & paramShort) != 0)
    {
      if ((0xC000 & paramShort) != 0) {
        return 3;
      }
      return 2;
    }
    return 1;
  }
  
  public static int getVarUInt32Size(int paramInt)
  {
    if ((0xFFE00000 & paramInt) != 0)
    {
      if ((0xF0000000 & paramInt) != 0) {
        return 5;
      }
      return 4;
    }
    if ((paramInt & 0xFFFFFF80) != 0)
    {
      if ((paramInt & 0xC000) != 0) {
        return 3;
      }
      return 2;
    }
    return 1;
  }
  
  public static int getVarUInt64Size(long paramLong)
  {
    if (0L != (0xFFFFFFF800000000 & paramLong))
    {
      if (0L != (0xFFFE000000000000 & paramLong))
      {
        if (0L != (0xFF00000000000000 & paramLong))
        {
          if (0L != (0x8000000000000000 & paramLong)) {
            return 10;
          }
          return 9;
        }
        return 8;
      }
      if (0L != (0xFFFFFC0000000000 & paramLong)) {
        return 7;
      }
      return 6;
    }
    if (0L != (0xFFFFFFFFFFE00000 & paramLong))
    {
      if (0L != (0xFFFFFFFFF0000000 & paramLong)) {
        return 5;
      }
      return 4;
    }
    if (0L != (0xFFFFFFFFFFFFFF80 & paramLong))
    {
      if (0L != (0xFFFFFFFFFFFFC000 & paramLong)) {
        return 3;
      }
      return 2;
    }
    return 1;
  }
  
  public static int readBigEndianInt32(byte[] paramArrayOfByte)
  {
    return paramArrayOfByte[3] & 0xFF | (paramArrayOfByte[2] & 0xFF) << 8 | (paramArrayOfByte[1] & 0xFF) << 16 | (paramArrayOfByte[0] & 0xFF) << 24;
  }
  
  public static void writeBigEndianInt32(int paramInt, byte[] paramArrayOfByte)
  {
    paramArrayOfByte[3] = ((byte)paramInt);
    paramArrayOfByte[2] = ((byte)(paramInt >> 8));
    paramArrayOfByte[1] = ((byte)(paramInt >> 16));
    paramArrayOfByte[0] = ((byte)(paramInt >> 24));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\internal\IntegerHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */