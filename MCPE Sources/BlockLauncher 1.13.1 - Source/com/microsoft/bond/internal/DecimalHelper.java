package com.microsoft.bond.internal;

public final class DecimalHelper
{
  public static final int SIZEOF_DOUBLE = 8;
  public static final int SIZEOF_FLOAT = 4;
  
  public static double decodeDouble(byte[] paramArrayOfByte)
  {
    return Double.longBitsToDouble(paramArrayOfByte[0] & 0xFF | (paramArrayOfByte[1] & 0xFF) << 8 | (paramArrayOfByte[2] & 0xFF) << 16 | (paramArrayOfByte[3] & 0xFF) << 24 | (paramArrayOfByte[4] & 0xFF) << 32 | (paramArrayOfByte[5] & 0xFF) << 40 | (paramArrayOfByte[6] & 0xFF) << 48 | (paramArrayOfByte[7] & 0xFF) << 56);
  }
  
  public static float decodeFloat(byte[] paramArrayOfByte)
  {
    return Float.intBitsToFloat(paramArrayOfByte[0] & 0xFF | (paramArrayOfByte[1] & 0xFF) << 8 | (paramArrayOfByte[2] & 0xFF) << 16 | (paramArrayOfByte[3] & 0xFF) << 24);
  }
  
  public static void encodeDouble(double paramDouble, byte[] paramArrayOfByte)
  {
    long l = Double.doubleToRawLongBits(paramDouble);
    paramArrayOfByte[0] = ((byte)(int)l);
    paramArrayOfByte[1] = ((byte)(int)(l >> 8));
    paramArrayOfByte[2] = ((byte)(int)(l >> 16));
    paramArrayOfByte[3] = ((byte)(int)(l >> 24));
    paramArrayOfByte[4] = ((byte)(int)(l >> 32));
    paramArrayOfByte[5] = ((byte)(int)(l >> 40));
    paramArrayOfByte[6] = ((byte)(int)(l >> 48));
    paramArrayOfByte[7] = ((byte)(int)(l >> 56));
  }
  
  public static void encodeFloat(float paramFloat, byte[] paramArrayOfByte)
  {
    int i = Float.floatToRawIntBits(paramFloat);
    paramArrayOfByte[0] = ((byte)i);
    paramArrayOfByte[1] = ((byte)(i >> 8));
    paramArrayOfByte[2] = ((byte)(i >> 16));
    paramArrayOfByte[3] = ((byte)(i >> 24));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\internal\DecimalHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */