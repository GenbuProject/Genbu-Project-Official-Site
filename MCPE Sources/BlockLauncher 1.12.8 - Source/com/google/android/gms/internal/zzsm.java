package com.google.android.gms.internal;

import java.io.IOException;

public final class zzsm
{
  private final byte[] buffer;
  private int zzbtZ;
  private int zzbua;
  private int zzbub;
  private int zzbuc;
  private int zzbud;
  private int zzbue = Integer.MAX_VALUE;
  private int zzbuf;
  private int zzbug = 64;
  private int zzbuh = 67108864;
  
  private zzsm(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.buffer = paramArrayOfByte;
    this.zzbtZ = paramInt1;
    this.zzbua = (paramInt1 + paramInt2);
    this.zzbuc = paramInt1;
  }
  
  public static zzsm zzD(byte[] paramArrayOfByte)
  {
    return zza(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  private void zzJj()
  {
    this.zzbua += this.zzbub;
    int i = this.zzbua;
    if (i > this.zzbue)
    {
      this.zzbub = (i - this.zzbue);
      this.zzbua -= this.zzbub;
      return;
    }
    this.zzbub = 0;
  }
  
  public static zzsm zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new zzsm(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public static long zzan(long paramLong)
  {
    return paramLong >>> 1 ^ -(1L & paramLong);
  }
  
  public static int zzmp(int paramInt)
  {
    return paramInt >>> 1 ^ -(paramInt & 0x1);
  }
  
  public int getPosition()
  {
    return this.zzbuc - this.zzbtZ;
  }
  
  public byte[] readBytes()
    throws IOException
  {
    int i = zzJf();
    if ((i <= this.zzbua - this.zzbuc) && (i > 0))
    {
      byte[] arrayOfByte = new byte[i];
      System.arraycopy(this.buffer, this.zzbuc, arrayOfByte, 0, i);
      this.zzbuc = (i + this.zzbuc);
      return arrayOfByte;
    }
    if (i == 0) {
      return zzsx.zzbuD;
    }
    return zzmt(i);
  }
  
  public double readDouble()
    throws IOException
  {
    return Double.longBitsToDouble(zzJi());
  }
  
  public float readFloat()
    throws IOException
  {
    return Float.intBitsToFloat(zzJh());
  }
  
  public String readString()
    throws IOException
  {
    int i = zzJf();
    if ((i <= this.zzbua - this.zzbuc) && (i > 0))
    {
      String str = new String(this.buffer, this.zzbuc, i, "UTF-8");
      this.zzbuc = (i + this.zzbuc);
      return str;
    }
    return new String(zzmt(i), "UTF-8");
  }
  
  public int zzIX()
    throws IOException
  {
    if (zzJl())
    {
      this.zzbud = 0;
      return 0;
    }
    this.zzbud = zzJf();
    if (this.zzbud == 0) {
      throw zzst.zzJv();
    }
    return this.zzbud;
  }
  
  public void zzIY()
    throws IOException
  {
    int i;
    do
    {
      i = zzIX();
    } while ((i != 0) && (zzmo(i)));
  }
  
  public long zzIZ()
    throws IOException
  {
    return zzJg();
  }
  
  public long zzJa()
    throws IOException
  {
    return zzJg();
  }
  
  public int zzJb()
    throws IOException
  {
    return zzJf();
  }
  
  public boolean zzJc()
    throws IOException
  {
    return zzJf() != 0;
  }
  
  public int zzJd()
    throws IOException
  {
    return zzmp(zzJf());
  }
  
  public long zzJe()
    throws IOException
  {
    return zzan(zzJg());
  }
  
  public int zzJf()
    throws IOException
  {
    int i = zzJm();
    if (i >= 0) {}
    int k;
    do
    {
      return i;
      i &= 0x7F;
      j = zzJm();
      if (j >= 0) {
        return i | j << 7;
      }
      i |= (j & 0x7F) << 7;
      j = zzJm();
      if (j >= 0) {
        return i | j << 14;
      }
      i |= (j & 0x7F) << 14;
      k = zzJm();
      if (k >= 0) {
        return i | k << 21;
      }
      j = zzJm();
      k = i | (k & 0x7F) << 21 | j << 28;
      i = k;
    } while (j >= 0);
    int j = 0;
    for (;;)
    {
      if (j >= 5) {
        break label133;
      }
      i = k;
      if (zzJm() >= 0) {
        break;
      }
      j += 1;
    }
    label133:
    throw zzst.zzJu();
  }
  
  public long zzJg()
    throws IOException
  {
    int i = 0;
    long l = 0L;
    while (i < 64)
    {
      int j = zzJm();
      l |= (j & 0x7F) << i;
      if ((j & 0x80) == 0) {
        return l;
      }
      i += 7;
    }
    throw zzst.zzJu();
  }
  
  public int zzJh()
    throws IOException
  {
    return zzJm() & 0xFF | (zzJm() & 0xFF) << 8 | (zzJm() & 0xFF) << 16 | (zzJm() & 0xFF) << 24;
  }
  
  public long zzJi()
    throws IOException
  {
    int i = zzJm();
    int j = zzJm();
    int k = zzJm();
    int m = zzJm();
    int n = zzJm();
    int i1 = zzJm();
    int i2 = zzJm();
    int i3 = zzJm();
    long l = i;
    return (j & 0xFF) << 8 | l & 0xFF | (k & 0xFF) << 16 | (m & 0xFF) << 24 | (n & 0xFF) << 32 | (i1 & 0xFF) << 40 | (i2 & 0xFF) << 48 | (i3 & 0xFF) << 56;
  }
  
  public int zzJk()
  {
    if (this.zzbue == Integer.MAX_VALUE) {
      return -1;
    }
    int i = this.zzbuc;
    return this.zzbue - i;
  }
  
  public boolean zzJl()
  {
    return this.zzbuc == this.zzbua;
  }
  
  public byte zzJm()
    throws IOException
  {
    if (this.zzbuc == this.zzbua) {
      throw zzst.zzJs();
    }
    byte[] arrayOfByte = this.buffer;
    int i = this.zzbuc;
    this.zzbuc = (i + 1);
    return arrayOfByte[i];
  }
  
  public void zza(zzsu paramzzsu)
    throws IOException
  {
    int i = zzJf();
    if (this.zzbuf >= this.zzbug) {
      throw zzst.zzJy();
    }
    i = zzmq(i);
    this.zzbuf += 1;
    paramzzsu.mergeFrom(this);
    zzmn(0);
    this.zzbuf -= 1;
    zzmr(i);
  }
  
  public void zza(zzsu paramzzsu, int paramInt)
    throws IOException
  {
    if (this.zzbuf >= this.zzbug) {
      throw zzst.zzJy();
    }
    this.zzbuf += 1;
    paramzzsu.mergeFrom(this);
    zzmn(zzsx.zzF(paramInt, 4));
    this.zzbuf -= 1;
  }
  
  public void zzmn(int paramInt)
    throws zzst
  {
    if (this.zzbud != paramInt) {
      throw zzst.zzJw();
    }
  }
  
  public boolean zzmo(int paramInt)
    throws IOException
  {
    switch (zzsx.zzmI(paramInt))
    {
    default: 
      throw zzst.zzJx();
    case 0: 
      zzJb();
      return true;
    case 1: 
      zzJi();
      return true;
    case 2: 
      zzmu(zzJf());
      return true;
    case 3: 
      zzIY();
      zzmn(zzsx.zzF(zzsx.zzmJ(paramInt), 4));
      return true;
    case 4: 
      return false;
    }
    zzJh();
    return true;
  }
  
  public int zzmq(int paramInt)
    throws zzst
  {
    if (paramInt < 0) {
      throw zzst.zzJt();
    }
    paramInt = this.zzbuc + paramInt;
    int i = this.zzbue;
    if (paramInt > i) {
      throw zzst.zzJs();
    }
    this.zzbue = paramInt;
    zzJj();
    return i;
  }
  
  public void zzmr(int paramInt)
  {
    this.zzbue = paramInt;
    zzJj();
  }
  
  public void zzms(int paramInt)
  {
    if (paramInt > this.zzbuc - this.zzbtZ) {
      throw new IllegalArgumentException("Position " + paramInt + " is beyond current " + (this.zzbuc - this.zzbtZ));
    }
    if (paramInt < 0) {
      throw new IllegalArgumentException("Bad position " + paramInt);
    }
    this.zzbuc = (this.zzbtZ + paramInt);
  }
  
  public byte[] zzmt(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw zzst.zzJt();
    }
    if (this.zzbuc + paramInt > this.zzbue)
    {
      zzmu(this.zzbue - this.zzbuc);
      throw zzst.zzJs();
    }
    if (paramInt <= this.zzbua - this.zzbuc)
    {
      byte[] arrayOfByte = new byte[paramInt];
      System.arraycopy(this.buffer, this.zzbuc, arrayOfByte, 0, paramInt);
      this.zzbuc += paramInt;
      return arrayOfByte;
    }
    throw zzst.zzJs();
  }
  
  public void zzmu(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw zzst.zzJt();
    }
    if (this.zzbuc + paramInt > this.zzbue)
    {
      zzmu(this.zzbue - this.zzbuc);
      throw zzst.zzJs();
    }
    if (paramInt <= this.zzbua - this.zzbuc)
    {
      this.zzbuc += paramInt;
      return;
    }
    throw zzst.zzJs();
  }
  
  public byte[] zzz(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return zzsx.zzbuD;
    }
    byte[] arrayOfByte = new byte[paramInt2];
    int i = this.zzbtZ;
    System.arraycopy(this.buffer, i + paramInt1, arrayOfByte, 0, paramInt2);
    return arrayOfByte;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzsm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */