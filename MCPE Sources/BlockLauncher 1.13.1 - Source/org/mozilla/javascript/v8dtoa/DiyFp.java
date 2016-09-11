package org.mozilla.javascript.v8dtoa;

class DiyFp
{
  static final int kSignificandSize = 64;
  static final long kUint64MSB = Long.MIN_VALUE;
  private int e;
  private long f;
  
  static
  {
    if (!DiyFp.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  DiyFp()
  {
    this.f = 0L;
    this.e = 0;
  }
  
  DiyFp(long paramLong, int paramInt)
  {
    this.f = paramLong;
    this.e = paramInt;
  }
  
  static DiyFp minus(DiyFp paramDiyFp1, DiyFp paramDiyFp2)
  {
    paramDiyFp1 = new DiyFp(paramDiyFp1.f, paramDiyFp1.e);
    paramDiyFp1.subtract(paramDiyFp2);
    return paramDiyFp1;
  }
  
  static DiyFp normalize(DiyFp paramDiyFp)
  {
    paramDiyFp = new DiyFp(paramDiyFp.f, paramDiyFp.e);
    paramDiyFp.normalize();
    return paramDiyFp;
  }
  
  static DiyFp times(DiyFp paramDiyFp1, DiyFp paramDiyFp2)
  {
    paramDiyFp1 = new DiyFp(paramDiyFp1.f, paramDiyFp1.e);
    paramDiyFp1.multiply(paramDiyFp2);
    return paramDiyFp1;
  }
  
  private static boolean uint64_gte(long paramLong1, long paramLong2)
  {
    boolean bool = false;
    int i;
    int j;
    if (paramLong1 != paramLong2)
    {
      if (paramLong1 <= paramLong2) {
        break label53;
      }
      i = 1;
      if (paramLong1 >= 0L) {
        break label59;
      }
      j = 1;
      label27:
      if (paramLong2 >= 0L) {
        break label65;
      }
    }
    label53:
    label59:
    label65:
    for (int k = 1;; k = 0)
    {
      if ((k ^ i ^ j) != 0) {
        bool = true;
      }
      return bool;
      i = 0;
      break;
      j = 0;
      break label27;
    }
  }
  
  int e()
  {
    return this.e;
  }
  
  long f()
  {
    return this.f;
  }
  
  void multiply(DiyFp paramDiyFp)
  {
    long l1 = this.f >>> 32;
    long l2 = this.f & 0xFFFFFFFF;
    long l3 = paramDiyFp.f >>> 32;
    long l4 = paramDiyFp.f & 0xFFFFFFFF;
    long l5 = l3 * l2;
    long l6 = l1 * l4;
    this.e += paramDiyFp.e + 64;
    this.f = ((l6 >>> 32) + l1 * l3 + (l5 >>> 32) + ((l2 * l4 >>> 32) + (l6 & 0xFFFFFFFF) + (l5 & 0xFFFFFFFF) + 2147483648L >>> 32));
  }
  
  void normalize()
  {
    assert (this.f != 0L);
    long l1 = this.f;
    int i = this.e;
    int j;
    long l2;
    for (;;)
    {
      j = i;
      l2 = l1;
      if ((0xFFC0000000000000 & l1) != 0L) {
        break;
      }
      l1 <<= 10;
      i -= 10;
    }
    while ((0x8000000000000000 & l2) == 0L)
    {
      l2 <<= 1;
      j -= 1;
    }
    this.f = l2;
    this.e = j;
  }
  
  void setE(int paramInt)
  {
    this.e = paramInt;
  }
  
  void setF(long paramLong)
  {
    this.f = paramLong;
  }
  
  void subtract(DiyFp paramDiyFp)
  {
    assert (this.e == paramDiyFp.e);
    assert (uint64_gte(this.f, paramDiyFp.f));
    this.f -= paramDiyFp.f;
  }
  
  public String toString()
  {
    return "[DiyFp f:" + this.f + ", e:" + this.e + "]";
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\v8dtoa\DiyFp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */