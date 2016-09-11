package com.google.android.gms.internal;

public final class zzsq
  implements Cloneable
{
  private static final zzsr zzbum = new zzsr();
  private int mSize;
  private boolean zzbun = false;
  private int[] zzbuo;
  private zzsr[] zzbup;
  
  zzsq()
  {
    this(10);
  }
  
  zzsq(int paramInt)
  {
    paramInt = idealIntArraySize(paramInt);
    this.zzbuo = new int[paramInt];
    this.zzbup = new zzsr[paramInt];
    this.mSize = 0;
  }
  
  private void gc()
  {
    int m = this.mSize;
    int[] arrayOfInt = this.zzbuo;
    zzsr[] arrayOfzzsr = this.zzbup;
    int i = 0;
    int k;
    for (int j = 0; i < m; j = k)
    {
      zzsr localzzsr = arrayOfzzsr[i];
      k = j;
      if (localzzsr != zzbum)
      {
        if (i != j)
        {
          arrayOfInt[j] = arrayOfInt[i];
          arrayOfzzsr[j] = localzzsr;
          arrayOfzzsr[i] = null;
        }
        k = j + 1;
      }
      i += 1;
    }
    this.zzbun = false;
    this.mSize = j;
  }
  
  private int idealByteArraySize(int paramInt)
  {
    int i = 4;
    for (;;)
    {
      int j = paramInt;
      if (i < 32)
      {
        if (paramInt <= (1 << i) - 12) {
          j = (1 << i) - 12;
        }
      }
      else {
        return j;
      }
      i += 1;
    }
  }
  
  private int idealIntArraySize(int paramInt)
  {
    return idealByteArraySize(paramInt * 4) / 4;
  }
  
  private boolean zza(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      if (paramArrayOfInt1[i] != paramArrayOfInt2[i]) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private boolean zza(zzsr[] paramArrayOfzzsr1, zzsr[] paramArrayOfzzsr2, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      if (!paramArrayOfzzsr1[i].equals(paramArrayOfzzsr2[i])) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private int zzmH(int paramInt)
  {
    int i = 0;
    int j = this.mSize - 1;
    while (i <= j)
    {
      int k = i + j >>> 1;
      int m = this.zzbuo[k];
      if (m < paramInt) {
        i = k + 1;
      } else if (m > paramInt) {
        j = k - 1;
      } else {
        return k;
      }
    }
    return i ^ 0xFFFFFFFF;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzsq)) {
        return false;
      }
      paramObject = (zzsq)paramObject;
      if (size() != ((zzsq)paramObject).size()) {
        return false;
      }
    } while ((zza(this.zzbuo, ((zzsq)paramObject).zzbuo, this.mSize)) && (zza(this.zzbup, ((zzsq)paramObject).zzbup, this.mSize)));
    return false;
  }
  
  public int hashCode()
  {
    if (this.zzbun) {
      gc();
    }
    int j = 17;
    int i = 0;
    while (i < this.mSize)
    {
      j = (j * 31 + this.zzbuo[i]) * 31 + this.zzbup[i].hashCode();
      i += 1;
    }
    return j;
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  int size()
  {
    if (this.zzbun) {
      gc();
    }
    return this.mSize;
  }
  
  public final zzsq zzJq()
  {
    int i = 0;
    int j = size();
    zzsq localzzsq = new zzsq(j);
    System.arraycopy(this.zzbuo, 0, localzzsq.zzbuo, 0, j);
    while (i < j)
    {
      if (this.zzbup[i] != null) {
        localzzsq.zzbup[i] = this.zzbup[i].zzJr();
      }
      i += 1;
    }
    localzzsq.mSize = j;
    return localzzsq;
  }
  
  void zza(int paramInt, zzsr paramzzsr)
  {
    int i = zzmH(paramInt);
    if (i >= 0)
    {
      this.zzbup[i] = paramzzsr;
      return;
    }
    int j = i ^ 0xFFFFFFFF;
    if ((j < this.mSize) && (this.zzbup[j] == zzbum))
    {
      this.zzbuo[j] = paramInt;
      this.zzbup[j] = paramzzsr;
      return;
    }
    i = j;
    if (this.zzbun)
    {
      i = j;
      if (this.mSize >= this.zzbuo.length)
      {
        gc();
        i = zzmH(paramInt) ^ 0xFFFFFFFF;
      }
    }
    if (this.mSize >= this.zzbuo.length)
    {
      j = idealIntArraySize(this.mSize + 1);
      int[] arrayOfInt = new int[j];
      zzsr[] arrayOfzzsr = new zzsr[j];
      System.arraycopy(this.zzbuo, 0, arrayOfInt, 0, this.zzbuo.length);
      System.arraycopy(this.zzbup, 0, arrayOfzzsr, 0, this.zzbup.length);
      this.zzbuo = arrayOfInt;
      this.zzbup = arrayOfzzsr;
    }
    if (this.mSize - i != 0)
    {
      System.arraycopy(this.zzbuo, i, this.zzbuo, i + 1, this.mSize - i);
      System.arraycopy(this.zzbup, i, this.zzbup, i + 1, this.mSize - i);
    }
    this.zzbuo[i] = paramInt;
    this.zzbup[i] = paramzzsr;
    this.mSize += 1;
  }
  
  zzsr zzmF(int paramInt)
  {
    paramInt = zzmH(paramInt);
    if ((paramInt < 0) || (this.zzbup[paramInt] == zzbum)) {
      return null;
    }
    return this.zzbup[paramInt];
  }
  
  zzsr zzmG(int paramInt)
  {
    if (this.zzbun) {
      gc();
    }
    return this.zzbup[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzsq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */