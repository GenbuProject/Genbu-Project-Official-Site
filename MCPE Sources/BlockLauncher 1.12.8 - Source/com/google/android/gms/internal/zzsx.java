package com.google.android.gms.internal;

import java.io.IOException;

public final class zzsx
{
  public static final boolean[] zzbuA = new boolean[0];
  public static final String[] zzbuB = new String[0];
  public static final byte[][] zzbuC = new byte[0][];
  public static final byte[] zzbuD = new byte[0];
  public static final int[] zzbuw = new int[0];
  public static final long[] zzbux = new long[0];
  public static final float[] zzbuy = new float[0];
  public static final double[] zzbuz = new double[0];
  
  static int zzF(int paramInt1, int paramInt2)
  {
    return paramInt1 << 3 | paramInt2;
  }
  
  public static boolean zzb(zzsm paramzzsm, int paramInt)
    throws IOException
  {
    return paramzzsm.zzmo(paramInt);
  }
  
  public static final int zzc(zzsm paramzzsm, int paramInt)
    throws IOException
  {
    int i = 1;
    int j = paramzzsm.getPosition();
    paramzzsm.zzmo(paramInt);
    while (paramzzsm.zzIX() == paramInt)
    {
      paramzzsm.zzmo(paramInt);
      i += 1;
    }
    paramzzsm.zzms(j);
    return i;
  }
  
  static int zzmI(int paramInt)
  {
    return paramInt & 0x7;
  }
  
  public static int zzmJ(int paramInt)
  {
    return paramInt >>> 3;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzsx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */