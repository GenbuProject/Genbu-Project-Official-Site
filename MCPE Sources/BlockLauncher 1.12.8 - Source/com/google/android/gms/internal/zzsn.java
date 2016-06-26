package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzsn
{
  private final ByteBuffer zzbui;
  
  private zzsn(ByteBuffer paramByteBuffer)
  {
    this.zzbui = paramByteBuffer;
    this.zzbui.order(ByteOrder.LITTLE_ENDIAN);
  }
  
  private zzsn(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this(ByteBuffer.wrap(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  public static int zzC(int paramInt1, int paramInt2)
  {
    return zzmA(paramInt1) + zzmx(paramInt2);
  }
  
  public static int zzD(int paramInt1, int paramInt2)
  {
    return zzmA(paramInt1) + zzmy(paramInt2);
  }
  
  public static zzsn zzE(byte[] paramArrayOfByte)
  {
    return zzb(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static int zzG(byte[] paramArrayOfByte)
  {
    return zzmC(paramArrayOfByte.length) + paramArrayOfByte.length;
  }
  
  private static int zza(CharSequence paramCharSequence, int paramInt)
  {
    int m = paramCharSequence.length();
    int i = 0;
    if (paramInt < m)
    {
      int n = paramCharSequence.charAt(paramInt);
      int j;
      if (n < 2048)
      {
        i += (127 - n >>> 31);
        j = paramInt;
      }
      for (;;)
      {
        paramInt = j + 1;
        break;
        int k = i + 2;
        j = paramInt;
        i = k;
        if (55296 <= n)
        {
          j = paramInt;
          i = k;
          if (n <= 57343)
          {
            if (Character.codePointAt(paramCharSequence, paramInt) < 65536) {
              throw new IllegalArgumentException("Unpaired surrogate at index " + paramInt);
            }
            j = paramInt + 1;
            i = k;
          }
        }
      }
    }
    return i;
  }
  
  private static int zza(CharSequence paramCharSequence, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int k = paramCharSequence.length();
    int j = 0;
    int m = paramInt1 + paramInt2;
    paramInt2 = j;
    while ((paramInt2 < k) && (paramInt2 + paramInt1 < m))
    {
      j = paramCharSequence.charAt(paramInt2);
      if (j >= 128) {
        break;
      }
      paramArrayOfByte[(paramInt1 + paramInt2)] = ((byte)j);
      paramInt2 += 1;
    }
    if (paramInt2 == k) {
      return paramInt1 + k;
    }
    paramInt1 += paramInt2;
    if (paramInt2 < k)
    {
      int i = paramCharSequence.charAt(paramInt2);
      if ((i < 128) && (paramInt1 < m))
      {
        j = paramInt1 + 1;
        paramArrayOfByte[paramInt1] = ((byte)i);
        paramInt1 = j;
      }
      for (;;)
      {
        paramInt2 += 1;
        break;
        if ((i < 2048) && (paramInt1 <= m - 2))
        {
          j = paramInt1 + 1;
          paramArrayOfByte[paramInt1] = ((byte)(i >>> 6 | 0x3C0));
          paramInt1 = j + 1;
          paramArrayOfByte[j] = ((byte)(i & 0x3F | 0x80));
        }
        else
        {
          int n;
          if (((i < 55296) || (57343 < i)) && (paramInt1 <= m - 3))
          {
            j = paramInt1 + 1;
            paramArrayOfByte[paramInt1] = ((byte)(i >>> 12 | 0x1E0));
            n = j + 1;
            paramArrayOfByte[j] = ((byte)(i >>> 6 & 0x3F | 0x80));
            paramInt1 = n + 1;
            paramArrayOfByte[n] = ((byte)(i & 0x3F | 0x80));
          }
          else
          {
            if (paramInt1 > m - 4) {
              break label442;
            }
            j = paramInt2;
            char c;
            if (paramInt2 + 1 != paramCharSequence.length())
            {
              paramInt2 += 1;
              c = paramCharSequence.charAt(paramInt2);
              if (!Character.isSurrogatePair(i, c)) {
                j = paramInt2;
              }
            }
            else
            {
              throw new IllegalArgumentException("Unpaired surrogate at index " + (j - 1));
            }
            j = Character.toCodePoint(i, c);
            n = paramInt1 + 1;
            paramArrayOfByte[paramInt1] = ((byte)(j >>> 18 | 0xF0));
            paramInt1 = n + 1;
            paramArrayOfByte[n] = ((byte)(j >>> 12 & 0x3F | 0x80));
            n = paramInt1 + 1;
            paramArrayOfByte[paramInt1] = ((byte)(j >>> 6 & 0x3F | 0x80));
            paramInt1 = n + 1;
            paramArrayOfByte[n] = ((byte)(j & 0x3F | 0x80));
          }
        }
      }
      label442:
      if ((55296 <= i) && (i <= 57343) && ((paramInt2 + 1 == paramCharSequence.length()) || (!Character.isSurrogatePair(i, paramCharSequence.charAt(paramInt2 + 1))))) {
        throw new IllegalArgumentException("Unpaired surrogate at index " + paramInt2);
      }
      throw new ArrayIndexOutOfBoundsException("Failed writing " + i + " at index " + paramInt1);
    }
    return paramInt1;
  }
  
  private static void zza(CharSequence paramCharSequence, ByteBuffer paramByteBuffer)
  {
    if (paramByteBuffer.isReadOnly()) {
      throw new ReadOnlyBufferException();
    }
    if (paramByteBuffer.hasArray()) {
      try
      {
        paramByteBuffer.position(zza(paramCharSequence, paramByteBuffer.array(), paramByteBuffer.arrayOffset() + paramByteBuffer.position(), paramByteBuffer.remaining()) - paramByteBuffer.arrayOffset());
        return;
      }
      catch (ArrayIndexOutOfBoundsException paramCharSequence)
      {
        paramByteBuffer = new BufferOverflowException();
        paramByteBuffer.initCause(paramCharSequence);
        throw paramByteBuffer;
      }
    }
    zzb(paramCharSequence, paramByteBuffer);
  }
  
  public static int zzaA(boolean paramBoolean)
  {
    return 1;
  }
  
  public static int zzar(long paramLong)
  {
    return zzav(paramLong);
  }
  
  public static int zzas(long paramLong)
  {
    return zzav(paramLong);
  }
  
  public static int zzat(long paramLong)
  {
    return zzav(zzax(paramLong));
  }
  
  public static int zzav(long paramLong)
  {
    if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L) {
      return 1;
    }
    if ((0xFFFFFFFFFFFFC000 & paramLong) == 0L) {
      return 2;
    }
    if ((0xFFFFFFFFFFE00000 & paramLong) == 0L) {
      return 3;
    }
    if ((0xFFFFFFFFF0000000 & paramLong) == 0L) {
      return 4;
    }
    if ((0xFFFFFFF800000000 & paramLong) == 0L) {
      return 5;
    }
    if ((0xFFFFFC0000000000 & paramLong) == 0L) {
      return 6;
    }
    if ((0xFFFE000000000000 & paramLong) == 0L) {
      return 7;
    }
    if ((0xFF00000000000000 & paramLong) == 0L) {
      return 8;
    }
    if ((0x8000000000000000 & paramLong) == 0L) {
      return 9;
    }
    return 10;
  }
  
  public static long zzax(long paramLong)
  {
    return paramLong << 1 ^ paramLong >> 63;
  }
  
  public static int zzb(int paramInt, double paramDouble)
  {
    return zzmA(paramInt) + zzl(paramDouble);
  }
  
  public static int zzb(int paramInt, zzsu paramzzsu)
  {
    return zzmA(paramInt) * 2 + zzd(paramzzsu);
  }
  
  public static int zzb(int paramInt, byte[] paramArrayOfByte)
  {
    return zzmA(paramInt) + zzG(paramArrayOfByte);
  }
  
  public static zzsn zzb(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new zzsn(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  private static void zzb(CharSequence paramCharSequence, ByteBuffer paramByteBuffer)
  {
    int m = paramCharSequence.length();
    int j = 0;
    if (j < m)
    {
      int i = paramCharSequence.charAt(j);
      if (i < 128) {
        paramByteBuffer.put((byte)i);
      }
      for (;;)
      {
        j += 1;
        break;
        if (i < 2048)
        {
          paramByteBuffer.put((byte)(i >>> 6 | 0x3C0));
          paramByteBuffer.put((byte)(i & 0x3F | 0x80));
        }
        else if ((i < 55296) || (57343 < i))
        {
          paramByteBuffer.put((byte)(i >>> 12 | 0x1E0));
          paramByteBuffer.put((byte)(i >>> 6 & 0x3F | 0x80));
          paramByteBuffer.put((byte)(i & 0x3F | 0x80));
        }
        else
        {
          int k = j;
          char c;
          if (j + 1 != paramCharSequence.length())
          {
            j += 1;
            c = paramCharSequence.charAt(j);
            if (!Character.isSurrogatePair(i, c)) {
              k = j;
            }
          }
          else
          {
            throw new IllegalArgumentException("Unpaired surrogate at index " + (k - 1));
          }
          k = Character.toCodePoint(i, c);
          paramByteBuffer.put((byte)(k >>> 18 | 0xF0));
          paramByteBuffer.put((byte)(k >>> 12 & 0x3F | 0x80));
          paramByteBuffer.put((byte)(k >>> 6 & 0x3F | 0x80));
          paramByteBuffer.put((byte)(k & 0x3F | 0x80));
        }
      }
    }
  }
  
  public static int zzc(int paramInt, float paramFloat)
  {
    return zzmA(paramInt) + zzk(paramFloat);
  }
  
  public static int zzc(int paramInt, zzsu paramzzsu)
  {
    return zzmA(paramInt) + zze(paramzzsu);
  }
  
  private static int zzc(CharSequence paramCharSequence)
  {
    int m = paramCharSequence.length();
    int i = 0;
    while ((i < m) && (paramCharSequence.charAt(i) < '?')) {
      i += 1;
    }
    for (;;)
    {
      int k = i;
      int j;
      if (j < m)
      {
        k = paramCharSequence.charAt(j);
        if (k < 2048)
        {
          j += 1;
          i = (127 - k >>> 31) + i;
        }
        else
        {
          k = i + zza(paramCharSequence, j);
        }
      }
      else
      {
        if (k < m) {
          throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (k + 4294967296L));
        }
        return k;
        j = i;
        i = m;
      }
    }
  }
  
  public static int zzd(int paramInt, long paramLong)
  {
    return zzmA(paramInt) + zzas(paramLong);
  }
  
  public static int zzd(zzsu paramzzsu)
  {
    return paramzzsu.getSerializedSize();
  }
  
  public static int zze(int paramInt, long paramLong)
  {
    return zzmA(paramInt) + zzat(paramLong);
  }
  
  public static int zze(zzsu paramzzsu)
  {
    int i = paramzzsu.getSerializedSize();
    return i + zzmC(i);
  }
  
  public static int zzf(int paramInt, boolean paramBoolean)
  {
    return zzmA(paramInt) + zzaA(paramBoolean);
  }
  
  public static int zzgO(String paramString)
  {
    int i = zzc(paramString);
    return i + zzmC(i);
  }
  
  public static int zzk(float paramFloat)
  {
    return 4;
  }
  
  public static int zzl(double paramDouble)
  {
    return 8;
  }
  
  public static int zzmA(int paramInt)
  {
    return zzmC(zzsx.zzF(paramInt, 0));
  }
  
  public static int zzmC(int paramInt)
  {
    if ((paramInt & 0xFFFFFF80) == 0) {
      return 1;
    }
    if ((paramInt & 0xC000) == 0) {
      return 2;
    }
    if ((0xFFE00000 & paramInt) == 0) {
      return 3;
    }
    if ((0xF0000000 & paramInt) == 0) {
      return 4;
    }
    return 5;
  }
  
  public static int zzmE(int paramInt)
  {
    return paramInt << 1 ^ paramInt >> 31;
  }
  
  public static int zzmx(int paramInt)
  {
    if (paramInt >= 0) {
      return zzmC(paramInt);
    }
    return 10;
  }
  
  public static int zzmy(int paramInt)
  {
    return zzmC(zzmE(paramInt));
  }
  
  public static int zzo(int paramInt, String paramString)
  {
    return zzmA(paramInt) + zzgO(paramString);
  }
  
  public void zzA(int paramInt1, int paramInt2)
    throws IOException
  {
    zzE(paramInt1, 0);
    zzmv(paramInt2);
  }
  
  public void zzB(int paramInt1, int paramInt2)
    throws IOException
  {
    zzE(paramInt1, 0);
    zzmw(paramInt2);
  }
  
  public void zzE(int paramInt1, int paramInt2)
    throws IOException
  {
    zzmB(zzsx.zzF(paramInt1, paramInt2));
  }
  
  public void zzF(byte[] paramArrayOfByte)
    throws IOException
  {
    zzmB(paramArrayOfByte.length);
    zzH(paramArrayOfByte);
  }
  
  public void zzH(byte[] paramArrayOfByte)
    throws IOException
  {
    zzc(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int zzJn()
  {
    return this.zzbui.remaining();
  }
  
  public void zzJo()
  {
    if (zzJn() != 0) {
      throw new IllegalStateException("Did not write as much data as expected.");
    }
  }
  
  public void zza(int paramInt, double paramDouble)
    throws IOException
  {
    zzE(paramInt, 1);
    zzk(paramDouble);
  }
  
  public void zza(int paramInt, long paramLong)
    throws IOException
  {
    zzE(paramInt, 0);
    zzao(paramLong);
  }
  
  public void zza(int paramInt, zzsu paramzzsu)
    throws IOException
  {
    zzE(paramInt, 2);
    zzc(paramzzsu);
  }
  
  public void zza(int paramInt, byte[] paramArrayOfByte)
    throws IOException
  {
    zzE(paramInt, 2);
    zzF(paramArrayOfByte);
  }
  
  public void zzao(long paramLong)
    throws IOException
  {
    zzau(paramLong);
  }
  
  public void zzap(long paramLong)
    throws IOException
  {
    zzau(paramLong);
  }
  
  public void zzaq(long paramLong)
    throws IOException
  {
    zzau(zzax(paramLong));
  }
  
  public void zzau(long paramLong)
    throws IOException
  {
    for (;;)
    {
      if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L)
      {
        zzmz((int)paramLong);
        return;
      }
      zzmz((int)paramLong & 0x7F | 0x80);
      paramLong >>>= 7;
    }
  }
  
  public void zzaw(long paramLong)
    throws IOException
  {
    if (this.zzbui.remaining() < 8) {
      throw new zza(this.zzbui.position(), this.zzbui.limit());
    }
    this.zzbui.putLong(paramLong);
  }
  
  public void zzaz(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      zzmz(i);
      return;
    }
  }
  
  public void zzb(byte paramByte)
    throws IOException
  {
    if (!this.zzbui.hasRemaining()) {
      throw new zza(this.zzbui.position(), this.zzbui.limit());
    }
    this.zzbui.put(paramByte);
  }
  
  public void zzb(int paramInt, float paramFloat)
    throws IOException
  {
    zzE(paramInt, 5);
    zzj(paramFloat);
  }
  
  public void zzb(int paramInt, long paramLong)
    throws IOException
  {
    zzE(paramInt, 0);
    zzap(paramLong);
  }
  
  public void zzb(zzsu paramzzsu)
    throws IOException
  {
    paramzzsu.writeTo(this);
  }
  
  public void zzc(int paramInt, long paramLong)
    throws IOException
  {
    zzE(paramInt, 0);
    zzaq(paramLong);
  }
  
  public void zzc(zzsu paramzzsu)
    throws IOException
  {
    zzmB(paramzzsu.getCachedSize());
    paramzzsu.writeTo(this);
  }
  
  public void zzc(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.zzbui.remaining() >= paramInt2)
    {
      this.zzbui.put(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    throw new zza(this.zzbui.position(), this.zzbui.limit());
  }
  
  public void zze(int paramInt, boolean paramBoolean)
    throws IOException
  {
    zzE(paramInt, 0);
    zzaz(paramBoolean);
  }
  
  public void zzgN(String paramString)
    throws IOException
  {
    int i;
    int j;
    try
    {
      i = zzmC(paramString.length());
      if (i != zzmC(paramString.length() * 3)) {
        break label150;
      }
      j = this.zzbui.position();
      if (this.zzbui.remaining() < i) {
        throw new zza(i + j, this.zzbui.limit());
      }
    }
    catch (BufferOverflowException paramString)
    {
      zza localzza = new zza(this.zzbui.position(), this.zzbui.limit());
      localzza.initCause(paramString);
      throw localzza;
    }
    this.zzbui.position(j + i);
    zza(paramString, this.zzbui);
    int k = this.zzbui.position();
    this.zzbui.position(j);
    zzmB(k - j - i);
    this.zzbui.position(k);
    return;
    label150:
    zzmB(zzc(paramString));
    zza(paramString, this.zzbui);
  }
  
  public void zzj(float paramFloat)
    throws IOException
  {
    zzmD(Float.floatToIntBits(paramFloat));
  }
  
  public void zzk(double paramDouble)
    throws IOException
  {
    zzaw(Double.doubleToLongBits(paramDouble));
  }
  
  public void zzmB(int paramInt)
    throws IOException
  {
    for (;;)
    {
      if ((paramInt & 0xFFFFFF80) == 0)
      {
        zzmz(paramInt);
        return;
      }
      zzmz(paramInt & 0x7F | 0x80);
      paramInt >>>= 7;
    }
  }
  
  public void zzmD(int paramInt)
    throws IOException
  {
    if (this.zzbui.remaining() < 4) {
      throw new zza(this.zzbui.position(), this.zzbui.limit());
    }
    this.zzbui.putInt(paramInt);
  }
  
  public void zzmv(int paramInt)
    throws IOException
  {
    if (paramInt >= 0)
    {
      zzmB(paramInt);
      return;
    }
    zzau(paramInt);
  }
  
  public void zzmw(int paramInt)
    throws IOException
  {
    zzmB(zzmE(paramInt));
  }
  
  public void zzmz(int paramInt)
    throws IOException
  {
    zzb((byte)paramInt);
  }
  
  public void zzn(int paramInt, String paramString)
    throws IOException
  {
    zzE(paramInt, 2);
    zzgN(paramString);
  }
  
  public static class zza
    extends IOException
  {
    zza(int paramInt1, int paramInt2)
    {
      super();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzsn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */