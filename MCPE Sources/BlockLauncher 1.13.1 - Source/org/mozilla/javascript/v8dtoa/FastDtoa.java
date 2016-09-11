package org.mozilla.javascript.v8dtoa;

public class FastDtoa
{
  static final int kFastDtoaMaximalLength = 17;
  static final int kTen4 = 10000;
  static final int kTen5 = 100000;
  static final int kTen6 = 1000000;
  static final int kTen7 = 10000000;
  static final int kTen8 = 100000000;
  static final int kTen9 = 1000000000;
  static final int maximal_target_exponent = -32;
  static final int minimal_target_exponent = -60;
  
  static
  {
    if (!FastDtoa.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  static long biggestPowerTen(int paramInt1, int paramInt2)
  {
    int j = 1;
    int i = 0;
    switch (paramInt2)
    {
    default: 
      paramInt1 = 0;
      paramInt2 = i;
    }
    for (;;)
    {
      long l = paramInt2;
      return paramInt1 & 0xFFFFFFFF | l << 32;
      if (1000000000 <= paramInt1)
      {
        paramInt2 = 1000000000;
        paramInt1 = 9;
      }
      else if (100000000 <= paramInt1)
      {
        paramInt2 = 100000000;
        paramInt1 = 8;
      }
      else if (10000000 <= paramInt1)
      {
        paramInt2 = 10000000;
        paramInt1 = 7;
      }
      else if (1000000 <= paramInt1)
      {
        paramInt2 = 1000000;
        paramInt1 = 6;
      }
      else if (100000 <= paramInt1)
      {
        paramInt2 = 100000;
        paramInt1 = 5;
      }
      else if (10000 <= paramInt1)
      {
        paramInt2 = 10000;
        paramInt1 = 4;
      }
      else if (1000 <= paramInt1)
      {
        paramInt1 = 3;
        paramInt2 = 1000;
      }
      else if (100 <= paramInt1)
      {
        paramInt1 = 2;
        paramInt2 = 100;
      }
      else if (10 <= paramInt1)
      {
        paramInt2 = 10;
        paramInt1 = j;
      }
      else if (1 <= paramInt1)
      {
        paramInt2 = 1;
        paramInt1 = 0;
      }
      else
      {
        paramInt1 = -1;
        paramInt2 = i;
      }
    }
  }
  
  static boolean digitGen(DiyFp paramDiyFp1, DiyFp paramDiyFp2, DiyFp paramDiyFp3, FastDtoaBuilder paramFastDtoaBuilder, int paramInt)
  {
    assert ((paramDiyFp1.e() == paramDiyFp2.e()) && (paramDiyFp2.e() == paramDiyFp3.e()));
    assert (uint64_lte(paramDiyFp1.f() + 1L, paramDiyFp3.f() - 1L));
    assert ((-60 <= paramDiyFp2.e()) && (paramDiyFp2.e() <= -32));
    long l1 = 1L;
    DiyFp localDiyFp = new DiyFp(paramDiyFp1.f() - 1L, paramDiyFp1.e());
    paramDiyFp1 = new DiyFp(paramDiyFp3.f() + 1L, paramDiyFp3.e());
    paramDiyFp3 = DiyFp.minus(paramDiyFp1, localDiyFp);
    localDiyFp = new DiyFp(1L << -paramDiyFp2.e(), paramDiyFp2.e());
    int k = (int)(paramDiyFp1.f() >>> -localDiyFp.e() & 0xFFFFFFFF);
    long l2 = paramDiyFp1.f();
    l2 = localDiyFp.f() - 1L & l2;
    long l3 = biggestPowerTen(k, 64 - -localDiyFp.e());
    int j = (int)(l3 >>> 32 & 0xFFFFFFFF);
    int i = (int)(l3 & 0xFFFFFFFF) + 1;
    while (i > 0)
    {
      paramFastDtoaBuilder.append((char)(k / j + 48));
      k %= j;
      i -= 1;
      l3 = (k << -localDiyFp.e()) + l2;
      if (l3 < paramDiyFp3.f())
      {
        paramFastDtoaBuilder.point = (i + (paramFastDtoaBuilder.end - paramInt));
        return roundWeed(paramFastDtoaBuilder, DiyFp.minus(paramDiyFp1, paramDiyFp2).f(), paramDiyFp3.f(), l3, j << -localDiyFp.e(), 1L);
      }
      j /= 10;
    }
    long l4;
    do
    {
      l2 = 5L * l2;
      l3 = l1 * 5L;
      paramDiyFp3.setF(paramDiyFp3.f() * 5L);
      paramDiyFp3.setE(paramDiyFp3.e() + 1);
      localDiyFp.setF(localDiyFp.f() >>> 1);
      localDiyFp.setE(localDiyFp.e() + 1);
      paramFastDtoaBuilder.append((char)((int)(l2 >>> -localDiyFp.e() & 0xFFFFFFFF) + 48));
      l4 = localDiyFp.f() - 1L & l2;
      j = i - 1;
      i = j;
      l2 = l4;
      l1 = l3;
    } while (l4 >= paramDiyFp3.f());
    paramFastDtoaBuilder.point = (j + (paramFastDtoaBuilder.end - paramInt));
    return roundWeed(paramFastDtoaBuilder, DiyFp.minus(paramDiyFp1, paramDiyFp2).f() * l3, paramDiyFp3.f(), l4, localDiyFp.f(), l3);
  }
  
  public static boolean dtoa(double paramDouble, FastDtoaBuilder paramFastDtoaBuilder)
  {
    assert (paramDouble > 0.0D);
    assert (!Double.isNaN(paramDouble));
    assert (!Double.isInfinite(paramDouble));
    return grisu3(paramDouble, paramFastDtoaBuilder);
  }
  
  static boolean grisu3(double paramDouble, FastDtoaBuilder paramFastDtoaBuilder)
  {
    long l = Double.doubleToLongBits(paramDouble);
    DiyFp localDiyFp4 = DoubleHelper.asNormalizedDiyFp(l);
    DiyFp localDiyFp1 = new DiyFp();
    DiyFp localDiyFp2 = new DiyFp();
    DoubleHelper.normalizedBoundaries(l, localDiyFp1, localDiyFp2);
    assert (localDiyFp2.e() == localDiyFp4.e());
    DiyFp localDiyFp3 = new DiyFp();
    int i = CachedPowers.getCachedPower(localDiyFp4.e() + 64, -60, -32, localDiyFp3);
    assert ((-60 <= localDiyFp4.e() + localDiyFp3.e() + 64) && (-32 >= localDiyFp4.e() + localDiyFp3.e() + 64));
    localDiyFp4 = DiyFp.times(localDiyFp4, localDiyFp3);
    assert (localDiyFp4.e() == localDiyFp2.e() + localDiyFp3.e() + 64);
    return digitGen(DiyFp.times(localDiyFp1, localDiyFp3), localDiyFp4, DiyFp.times(localDiyFp2, localDiyFp3), paramFastDtoaBuilder, i);
  }
  
  public static String numberToString(double paramDouble)
  {
    FastDtoaBuilder localFastDtoaBuilder = new FastDtoaBuilder();
    if (numberToString(paramDouble, localFastDtoaBuilder)) {
      return localFastDtoaBuilder.format();
    }
    return null;
  }
  
  public static boolean numberToString(double paramDouble, FastDtoaBuilder paramFastDtoaBuilder)
  {
    paramFastDtoaBuilder.reset();
    double d = paramDouble;
    if (paramDouble < 0.0D)
    {
      paramFastDtoaBuilder.append('-');
      d = -paramDouble;
    }
    return dtoa(d, paramFastDtoaBuilder);
  }
  
  static boolean roundWeed(FastDtoaBuilder paramFastDtoaBuilder, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5)
  {
    long l = paramLong1 - paramLong5;
    paramLong1 += paramLong5;
    while ((paramLong3 < l) && (paramLong2 - paramLong3 >= paramLong4) && ((paramLong3 + paramLong4 < l) || (l - paramLong3 >= paramLong3 + paramLong4 - l)))
    {
      paramFastDtoaBuilder.decreaseLast();
      paramLong3 += paramLong4;
    }
    if ((paramLong3 < paramLong1) && (paramLong2 - paramLong3 >= paramLong4) && ((paramLong3 + paramLong4 < paramLong1) || (paramLong1 - paramLong3 > paramLong3 + paramLong4 - paramLong1))) {
      return false;
    }
    return (2L * paramLong5 <= paramLong3) && (paramLong3 <= paramLong2 - 4L * paramLong5);
  }
  
  private static boolean uint64_lte(long paramLong1, long paramLong2)
  {
    boolean bool = false;
    int i;
    int j;
    if (paramLong1 != paramLong2)
    {
      if (paramLong1 >= paramLong2) {
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
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\v8dtoa\FastDtoa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */