package org.mozilla.javascript.v8dtoa;

public class DoubleHelper
{
  private static final int kDenormalExponent = -1074;
  private static final int kExponentBias = 1075;
  static final long kExponentMask = 9218868437227405312L;
  static final long kHiddenBit = 4503599627370496L;
  static final long kSignMask = Long.MIN_VALUE;
  static final long kSignificandMask = 4503599627370495L;
  private static final int kSignificandSize = 52;
  
  static
  {
    if (!DoubleHelper.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  static DiyFp asDiyFp(long paramLong)
  {
    assert (!isSpecial(paramLong));
    return new DiyFp(significand(paramLong), exponent(paramLong));
  }
  
  static DiyFp asNormalizedDiyFp(long paramLong)
  {
    long l = significand(paramLong);
    int j = exponent(paramLong);
    int i = j;
    paramLong = l;
    if (!$assertionsDisabled)
    {
      i = j;
      paramLong = l;
      if (l == 0L) {
        throw new AssertionError();
      }
    }
    while ((0x10000000000000 & paramLong) == 0L)
    {
      paramLong <<= 1;
      i -= 1;
    }
    return new DiyFp(paramLong << 11, i - 11);
  }
  
  static int exponent(long paramLong)
  {
    if (isDenormal(paramLong)) {
      return 64462;
    }
    return (int)((0x7FF0000000000000 & paramLong) >>> 52 & 0xFFFFFFFF) - 1075;
  }
  
  static boolean isDenormal(long paramLong)
  {
    return (0x7FF0000000000000 & paramLong) == 0L;
  }
  
  static boolean isInfinite(long paramLong)
  {
    return ((paramLong & 0x7FF0000000000000) == 9218868437227405312L) && ((0xFFFFFFFFFFFFF & paramLong) == 0L);
  }
  
  static boolean isNan(long paramLong)
  {
    return ((paramLong & 0x7FF0000000000000) == 9218868437227405312L) && ((0xFFFFFFFFFFFFF & paramLong) != 0L);
  }
  
  static boolean isSpecial(long paramLong)
  {
    return (paramLong & 0x7FF0000000000000) == 9218868437227405312L;
  }
  
  static void normalizedBoundaries(long paramLong, DiyFp paramDiyFp1, DiyFp paramDiyFp2)
  {
    DiyFp localDiyFp = asDiyFp(paramLong);
    int i;
    if (localDiyFp.f() == 4503599627370496L)
    {
      i = 1;
      paramDiyFp2.setF((localDiyFp.f() << 1) + 1L);
      paramDiyFp2.setE(localDiyFp.e() - 1);
      paramDiyFp2.normalize();
      if ((i == 0) || (localDiyFp.e() == 64462)) {
        break label122;
      }
      paramDiyFp1.setF((localDiyFp.f() << 2) - 1L);
      paramDiyFp1.setE(localDiyFp.e() - 2);
    }
    for (;;)
    {
      paramDiyFp1.setF(paramDiyFp1.f() << paramDiyFp1.e() - paramDiyFp2.e());
      paramDiyFp1.setE(paramDiyFp2.e());
      return;
      i = 0;
      break;
      label122:
      paramDiyFp1.setF((localDiyFp.f() << 1) - 1L);
      paramDiyFp1.setE(localDiyFp.e() - 1);
    }
  }
  
  static int sign(long paramLong)
  {
    if ((0x8000000000000000 & paramLong) == 0L) {
      return 1;
    }
    return -1;
  }
  
  static long significand(long paramLong)
  {
    long l2 = 0xFFFFFFFFFFFFF & paramLong;
    long l1 = l2;
    if (!isDenormal(paramLong)) {
      l1 = l2 + 4503599627370496L;
    }
    return l1;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\v8dtoa\DoubleHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */