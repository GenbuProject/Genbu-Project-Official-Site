package org.mozilla.javascript.v8dtoa;

public final class DoubleConversion
{
  private static final int kDenormalExponent = -1074;
  private static final int kExponentBias = 1075;
  private static final long kExponentMask = 9218868437227405312L;
  private static final long kHiddenBit = 4503599627370496L;
  private static final int kPhysicalSignificandSize = 52;
  private static final long kSignMask = Long.MIN_VALUE;
  private static final long kSignificandMask = 4503599627370495L;
  private static final int kSignificandSize = 53;
  
  public static int doubleToInt32(double paramDouble)
  {
    int i = (int)paramDouble;
    if (i == paramDouble) {
      return i;
    }
    long l1 = Double.doubleToLongBits(paramDouble);
    i = exponent(l1);
    if ((i <= -53) || (i > 31)) {
      return 0;
    }
    long l2 = significand(l1);
    int j = sign(l1);
    if (i < 0) {}
    for (l1 = l2 >> -i;; l1 = l2 << i) {
      return (int)l1 * j;
    }
  }
  
  private static int exponent(long paramLong)
  {
    if (isDenormal(paramLong)) {
      return 64462;
    }
    return (int)((0x7FF0000000000000 & paramLong) >> 52) - 1075;
  }
  
  private static boolean isDenormal(long paramLong)
  {
    return (0x7FF0000000000000 & paramLong) == 0L;
  }
  
  private static int sign(long paramLong)
  {
    if ((0x8000000000000000 & paramLong) == 0L) {
      return 1;
    }
    return -1;
  }
  
  private static long significand(long paramLong)
  {
    long l2 = 0xFFFFFFFFFFFFF & paramLong;
    long l1 = l2;
    if (!isDenormal(paramLong)) {
      l1 = l2 + 4503599627370496L;
    }
    return l1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\v8dtoa\DoubleConversion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */