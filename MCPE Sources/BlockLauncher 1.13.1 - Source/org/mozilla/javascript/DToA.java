package org.mozilla.javascript;

import java.math.BigInteger;

class DToA
{
  private static final int Bias = 1023;
  private static final int Bletch = 16;
  private static final int Bndry_mask = 1048575;
  static final int DTOSTR_EXPONENTIAL = 3;
  static final int DTOSTR_FIXED = 2;
  static final int DTOSTR_PRECISION = 4;
  static final int DTOSTR_STANDARD = 0;
  static final int DTOSTR_STANDARD_EXPONENTIAL = 1;
  private static final int Exp_11 = 1072693248;
  private static final int Exp_mask = 2146435072;
  private static final int Exp_mask_shifted = 2047;
  private static final int Exp_msk1 = 1048576;
  private static final long Exp_msk1L = 4503599627370496L;
  private static final int Exp_shift = 20;
  private static final int Exp_shift1 = 20;
  private static final int Exp_shiftL = 52;
  private static final int Frac_mask = 1048575;
  private static final int Frac_mask1 = 1048575;
  private static final long Frac_maskL = 4503599627370495L;
  private static final int Int_max = 14;
  private static final int Log2P = 1;
  private static final int P = 53;
  private static final int Quick_max = 14;
  private static final int Sign_bit = Integer.MIN_VALUE;
  private static final int Ten_pmax = 22;
  private static final double[] bigtens = { 1.0E16D, 1.0E32D, 1.0E64D, 1.0E128D, 1.0E256D };
  private static final int[] dtoaModes = { 0, 0, 3, 2, 2 };
  private static final int n_bigtens = 5;
  private static final double[] tens = { 1.0D, 10.0D, 100.0D, 1000.0D, 10000.0D, 100000.0D, 1000000.0D, 1.0E7D, 1.0E8D, 1.0E9D, 1.0E10D, 1.0E11D, 1.0E12D, 1.0E13D, 1.0E14D, 1.0E15D, 1.0E16D, 1.0E17D, 1.0E18D, 1.0E19D, 1.0E20D, 1.0E21D, 1.0E22D };
  
  private static char BASEDIGIT(int paramInt)
  {
    if (paramInt >= 10) {
      paramInt += 87;
    }
    for (;;)
    {
      return (char)paramInt;
      paramInt += 48;
    }
  }
  
  static int JS_dtoa(double paramDouble, int paramInt1, boolean paramBoolean, int paramInt2, boolean[] paramArrayOfBoolean, StringBuilder paramStringBuilder)
  {
    Object localObject2 = new int[1];
    Object localObject3 = new int[1];
    if ((word0(paramDouble) & 0x80000000) != 0)
    {
      paramArrayOfBoolean[0] = true;
      paramDouble = setWord0(paramDouble, word0(paramDouble) & 0x7FFFFFFF);
      if ((word0(paramDouble) & 0x7FF00000) != 2146435072) {
        break label97;
      }
      if ((word1(paramDouble) != 0) || ((word0(paramDouble) & 0xFFFFF) != 0)) {
        break label90;
      }
    }
    label90:
    for (paramArrayOfBoolean = "Infinity";; paramArrayOfBoolean = "NaN")
    {
      paramStringBuilder.append(paramArrayOfBoolean);
      return 9999;
      paramArrayOfBoolean[0] = false;
      break;
    }
    label97:
    if (paramDouble == 0.0D)
    {
      paramStringBuilder.setLength(0);
      paramStringBuilder.append('0');
      return 1;
    }
    Object localObject1 = d2b(paramDouble, (int[])localObject2, (int[])localObject3);
    int j = word0(paramDouble) >>> 20 & 0x7FF;
    double d1;
    int i10;
    int m;
    int k;
    int i8;
    if (j != 0)
    {
      d1 = setWord0(paramDouble, word0(paramDouble) & 0xFFFFF | 0x3FF00000);
      i10 = 0;
      m = j - 1023;
      d1 = m * 0.301029995663981D + ((d1 - 1.5D) * 0.289529654602168D + 0.1760912590558D);
      k = (int)d1;
      j = k;
      if (d1 < 0.0D)
      {
        j = k;
        if (d1 != k) {
          j = k - 1;
        }
      }
      if ((j < 0) || (j > 22)) {
        break label3270;
      }
      k = j;
      if (paramDouble < tens[j]) {
        k = j - 1;
      }
      i8 = 0;
    }
    for (int n = k;; n = j)
    {
      int i5 = localObject3[0] - m - 1;
      label292:
      int i2;
      if (i5 >= 0)
      {
        m = 0;
        if (n < 0) {
          break label662;
        }
        j = 0;
        i5 += n;
        i2 = n;
        label311:
        if ((paramInt1 >= 0) && (paramInt1 <= 9)) {
          break label3267;
        }
        paramInt1 = 0;
      }
      label397:
      label662:
      label1610:
      label1624:
      label1733:
      label1795:
      label1851:
      label1884:
      label2198:
      label2365:
      label2530:
      label2601:
      label2629:
      label2757:
      label3084:
      label3088:
      label3091:
      label3119:
      label3139:
      label3160:
      label3227:
      label3233:
      label3245:
      label3267:
      for (;;)
      {
        int i9;
        int i4;
        if (paramInt1 > 5)
        {
          i9 = paramInt1 - 4;
          i4 = 0;
        }
        for (;;)
        {
          paramInt1 = 1;
          k = 1;
          int i3 = 0;
          int i1;
          int i6;
          switch (i9)
          {
          default: 
            i1 = 1;
            paramInt1 = 0;
            i6 = paramInt2;
            k = i3;
          }
          for (;;)
          {
            paramInt2 = n;
            i3 = paramInt1;
            d1 = paramDouble;
            double d2;
            if (paramInt1 >= 0)
            {
              paramInt2 = n;
              i3 = paramInt1;
              d1 = paramDouble;
              if (paramInt1 <= 14)
              {
                paramInt2 = n;
                i3 = paramInt1;
                d1 = paramDouble;
                if (i4 != 0)
                {
                  paramInt2 = 2;
                  if (n > 0)
                  {
                    d1 = tens[(n & 0xF)];
                    i3 = n >> 4;
                    if ((i3 & 0x10) == 0) {
                      break label3233;
                    }
                    d2 = paramDouble / bigtens[4];
                    i3 &= 0xF;
                    i4 = 0;
                    paramInt2 = 3;
                  }
                }
              }
            }
            for (;;)
            {
              double d3;
              int i7;
              long l;
              if (i3 != 0)
              {
                d3 = d1;
                i7 = paramInt2;
                if ((i3 & 0x1) != 0)
                {
                  i7 = paramInt2 + 1;
                  d3 = d1 * bigtens[i4];
                }
                i3 >>= 1;
                i4 += 1;
                d1 = d3;
                paramInt2 = i7;
                continue;
                j = localObject3[0] + localObject2[0] + 1074;
                if (j > 32) {}
                for (l = word0(paramDouble) << 64 - j | word1(paramDouble) >>> j - 32;; l = word1(paramDouble) << 32 - j)
                {
                  d1 = setWord0(l, word0(l) - 32505856);
                  i10 = 1;
                  m = j - 1075;
                  break;
                }
                m = -i5;
                i5 = 0;
                break label292;
                m -= n;
                j = -n;
                i2 = 0;
                break label311;
                k = -1;
                i6 = 0;
                i1 = 1;
                paramInt1 = -1;
                break label397;
                k = 0;
                i3 = paramInt2;
                if (paramInt2 <= 0) {
                  i3 = 1;
                }
                i1 = k;
                k = i3;
                paramInt1 = i3;
                i6 = i3;
                break label397;
                paramInt1 = 0;
                i3 = paramInt2 + n + 1;
                k = i3 - 1;
                if (i3 > 0) {
                  break label3245;
                }
                i1 = paramInt1;
                paramInt1 = i3;
                i6 = paramInt2;
                break label397;
              }
              d1 = d2 / d1;
              for (;;)
              {
                if ((i8 != 0) && (d1 < 1.0D) && (paramInt1 > 0)) {
                  if (k <= 0)
                  {
                    i4 = 1;
                    i3 = paramInt1;
                    i7 = paramInt2;
                    paramInt2 = n;
                  }
                }
                for (;;)
                {
                  d2 = i7 * d1 + 7.0D;
                  d2 = setWord0(d2, word0(d2) - 54525952);
                  if (i3 == 0)
                  {
                    d1 -= 5.0D;
                    if (d1 > d2)
                    {
                      paramStringBuilder.append('1');
                      return paramInt2 + 1 + 1;
                      i3 = -n;
                      if (i3 == 0) {
                        break label3227;
                      }
                      d1 = tens[(i3 & 0xF)];
                      paramInt2 = 2;
                      i4 = i3 >> 4;
                      d1 *= paramDouble;
                      i3 = 0;
                      while (i4 != 0)
                      {
                        i7 = paramInt2;
                        d2 = d1;
                        if ((i4 & 0x1) != 0)
                        {
                          i7 = paramInt2 + 1;
                          d2 = d1 * bigtens[i3];
                        }
                        i4 >>= 1;
                        i3 += 1;
                        paramInt2 = i7;
                        d1 = d2;
                      }
                      d1 *= 10.0D;
                      i3 = k;
                      i7 = paramInt2 + 1;
                      paramInt2 = n - 1;
                      i4 = 0;
                    }
                    else
                    {
                      if (d1 < -d2)
                      {
                        paramStringBuilder.setLength(0);
                        paramStringBuilder.append('0');
                        return 1;
                      }
                      i4 = 1;
                    }
                  }
                  else
                  {
                    for (;;)
                    {
                      if (i4 == 0) {
                        if (i1 != 0)
                        {
                          d3 = 0.5D / tens[(i3 - 1)] - d2;
                          i4 = 0;
                          d2 = d1;
                          d1 = d3;
                          l = d2;
                          d2 -= l;
                          paramStringBuilder.append((char)(int)(l + 48L));
                          if (d2 < d1) {
                            return paramInt2 + 1;
                          }
                          if (1.0D - d2 < d1)
                          {
                            paramInt1 = paramStringBuilder.charAt(paramStringBuilder.length() - 1);
                            paramStringBuilder.setLength(paramStringBuilder.length() - 1);
                            if (paramInt1 != 57) {}
                            for (;;)
                            {
                              paramStringBuilder.append((char)(paramInt1 + 1));
                              return paramInt2 + 1;
                              if (paramStringBuilder.length() != 0) {
                                break;
                              }
                              paramInt2 += 1;
                              paramInt1 = 48;
                            }
                          }
                          i4 += 1;
                          if (i4 >= i3)
                          {
                            i4 = 1;
                            d1 = d2;
                          }
                        }
                      }
                      for (;;)
                      {
                        if (i4 != 0)
                        {
                          paramStringBuilder.setLength(0);
                          d1 = paramDouble;
                          i3 = paramInt1;
                          paramInt2 = n;
                        }
                        for (;;)
                        {
                          if ((localObject2[0] >= 0) && (paramInt2 <= 14))
                          {
                            paramDouble = tens[paramInt2];
                            if ((i6 < 0) && (i3 <= 0))
                            {
                              if ((i3 < 0) || (d1 < 5.0D * paramDouble) || ((!paramBoolean) && (d1 == paramDouble * 5.0D)))
                              {
                                paramStringBuilder.setLength(0);
                                paramStringBuilder.append('0');
                                return 1;
                                d1 *= 10.0D;
                                d2 *= 10.0D;
                                break;
                                d2 *= tens[(i3 - 1)];
                                i4 = 1;
                                for (;;)
                                {
                                  l = d1;
                                  d1 -= l;
                                  paramStringBuilder.append((char)(int)(48L + l));
                                  if (i4 == i3)
                                  {
                                    if (d1 > 0.5D + d2)
                                    {
                                      paramInt1 = paramStringBuilder.charAt(paramStringBuilder.length() - 1);
                                      paramStringBuilder.setLength(paramStringBuilder.length() - 1);
                                      if (paramInt1 != 57) {}
                                      for (;;)
                                      {
                                        paramStringBuilder.append((char)(paramInt1 + 1));
                                        return paramInt2 + 1;
                                        if (paramStringBuilder.length() != 0) {
                                          break;
                                        }
                                        paramInt2 += 1;
                                        paramInt1 = 48;
                                      }
                                    }
                                    if (d1 >= 0.5D - d2) {
                                      break;
                                    }
                                    stripTrailingZeroes(paramStringBuilder);
                                    return paramInt2 + 1;
                                  }
                                  i4 += 1;
                                  d1 = 10.0D * d1;
                                }
                              }
                              paramStringBuilder.append('1');
                              return paramInt2 + 1 + 1;
                            }
                            paramInt1 = 1;
                            for (;;)
                            {
                              l = (d1 / paramDouble);
                              d1 -= l * paramDouble;
                              paramStringBuilder.append((char)(int)(48L + l));
                              if (paramInt1 == i3)
                              {
                                d1 += d1;
                                if (d1 <= paramDouble)
                                {
                                  j = paramInt2;
                                  if (d1 != paramDouble) {
                                    break label1624;
                                  }
                                  if ((1L & l) == 0L)
                                  {
                                    j = paramInt2;
                                    if (!paramBoolean) {
                                      break label1624;
                                    }
                                  }
                                }
                                paramInt1 = paramStringBuilder.charAt(paramStringBuilder.length() - 1);
                                paramStringBuilder.setLength(paramStringBuilder.length() - 1);
                                if (paramInt1 != 57)
                                {
                                  paramStringBuilder.append((char)(paramInt1 + 1));
                                  j = paramInt2;
                                }
                              }
                              do
                              {
                                return j + 1;
                                if (paramStringBuilder.length() != 0) {
                                  break;
                                }
                                paramInt2 += 1;
                                paramInt1 = 48;
                                break label1610;
                                d1 *= 10.0D;
                                j = paramInt2;
                              } while (d1 == 0.0D);
                              paramInt1 += 1;
                            }
                          }
                          paramArrayOfBoolean = null;
                          if (i1 != 0) {
                            if (i9 < 2) {
                              if (i10 != 0)
                              {
                                paramInt1 = localObject2[0] + 1075;
                                i4 = j;
                                i6 = m;
                                i7 = i2;
                                i2 = j;
                                n = paramInt1;
                                paramInt1 = i6;
                                j = i4;
                                i4 = i7;
                                i6 = m + n;
                                paramArrayOfBoolean = BigInteger.valueOf(1L);
                                m = i2;
                                i7 = i5 + n;
                                i5 = j;
                                i2 = i4;
                                i4 = m;
                                n = i6;
                                m = i7;
                              }
                            }
                          }
                          for (;;)
                          {
                            if ((paramInt1 > 0) && (m > 0)) {
                              if (paramInt1 < m)
                              {
                                j = paramInt1;
                                n -= j;
                                m -= j;
                                paramInt1 -= j;
                              }
                            }
                            for (j = m;; j = m)
                            {
                              if (i4 > 0) {
                                if (i1 != 0)
                                {
                                  if (i5 <= 0) {
                                    break label3139;
                                  }
                                  localObject2 = pow5mult(paramArrayOfBoolean, i5);
                                  localObject3 = ((BigInteger)localObject2).multiply((BigInteger)localObject1);
                                  m = i4 - i5;
                                  paramArrayOfBoolean = (boolean[])localObject2;
                                  localObject1 = localObject3;
                                  if (m != 0)
                                  {
                                    localObject1 = pow5mult((BigInteger)localObject3, m);
                                    paramArrayOfBoolean = (boolean[])localObject2;
                                  }
                                }
                              }
                              for (;;)
                              {
                                localObject2 = BigInteger.valueOf(1L);
                                localObject3 = localObject2;
                                if (i2 > 0) {
                                  localObject3 = pow5mult((BigInteger)localObject2, i2);
                                }
                                i4 = 0;
                                if ((i9 < 2) && (word1(d1) == 0) && ((word0(d1) & 0xFFFFF) == 0) && ((word0(d1) & 0x7FE00000) != 0))
                                {
                                  j += 1;
                                  i4 = 1;
                                }
                                for (m = n + 1;; m = n)
                                {
                                  localObject2 = ((BigInteger)localObject3).toByteArray();
                                  n = 0;
                                  i5 = 0;
                                  for (;;)
                                  {
                                    if (i5 < 4)
                                    {
                                      i6 = n << 8;
                                      n = i6;
                                      if (i5 < localObject2.length) {
                                        n = i6 | localObject2[i5] & 0xFF;
                                      }
                                      i5 += 1;
                                      continue;
                                      paramInt1 = 54 - localObject3[0];
                                      break;
                                      paramInt1 = i3 - 1;
                                      if (j >= paramInt1)
                                      {
                                        paramInt1 = j - paramInt1;
                                        i4 = i2;
                                        i2 = j;
                                      }
                                      for (j = paramInt1;; j = 0)
                                      {
                                        if (i3 >= 0) {
                                          break label3160;
                                        }
                                        paramInt1 = m - i3;
                                        n = 0;
                                        break;
                                        paramInt1 -= j;
                                        i4 = i2 + paramInt1;
                                        i2 = j + paramInt1;
                                      }
                                      j = m;
                                      break label1795;
                                      localObject1 = pow5mult((BigInteger)localObject1, i4);
                                      break label1884;
                                    }
                                  }
                                  if (i2 != 0)
                                  {
                                    n = 32 - hi0bits(n);
                                    i2 = n + j & 0x1F;
                                    n = i2;
                                    if (i2 != 0) {
                                      n = 32 - i2;
                                    }
                                    if (n <= 4) {
                                      break label2365;
                                    }
                                    n -= 4;
                                    m += n;
                                    paramInt1 += n;
                                    n = j + n;
                                    j = paramInt1;
                                    paramInt1 = n;
                                  }
                                  for (;;)
                                  {
                                    localObject2 = localObject1;
                                    if (m > 0) {
                                      localObject2 = ((BigInteger)localObject1).shiftLeft(m);
                                    }
                                    if (paramInt1 > 0) {}
                                    for (Object localObject4 = ((BigInteger)localObject3).shiftLeft(paramInt1);; localObject4 = localObject3)
                                    {
                                      if ((i8 != 0) && (((BigInteger)localObject2).compareTo((BigInteger)localObject4) < 0))
                                      {
                                        localObject2 = ((BigInteger)localObject2).multiply(BigInteger.valueOf(10L));
                                        localObject1 = paramArrayOfBoolean;
                                        if (i1 != 0) {
                                          localObject1 = paramArrayOfBoolean.multiply(BigInteger.valueOf(10L));
                                        }
                                        paramInt1 = paramInt2 - 1;
                                        i3 = k;
                                        paramArrayOfBoolean = (boolean[])localObject2;
                                        localObject3 = localObject1;
                                      }
                                      for (;;)
                                      {
                                        if ((i3 <= 0) && (i9 > 2))
                                        {
                                          if (i3 >= 0)
                                          {
                                            paramInt2 = paramArrayOfBoolean.compareTo(((BigInteger)localObject4).multiply(BigInteger.valueOf(5L)));
                                            if ((paramInt2 >= 0) && ((paramInt2 != 0) || (paramBoolean))) {}
                                          }
                                          else
                                          {
                                            paramStringBuilder.setLength(0);
                                            paramStringBuilder.append('0');
                                            return 1;
                                            n = 1;
                                            break;
                                            if (n >= 4) {
                                              break label3119;
                                            }
                                            i2 = n + 28;
                                            m += i2;
                                            n = paramInt1 + i2;
                                            paramInt1 = j + i2;
                                            j = n;
                                            break label2198;
                                          }
                                          paramStringBuilder.append('1');
                                          return paramInt1 + 1 + 1;
                                        }
                                        if (i1 != 0)
                                        {
                                          localObject1 = localObject3;
                                          if (j > 0) {
                                            localObject1 = ((BigInteger)localObject3).shiftLeft(j);
                                          }
                                          if (i4 == 0) {
                                            break label3091;
                                          }
                                        }
                                        for (localObject2 = ((BigInteger)localObject1).shiftLeft(1);; localObject2 = localObject1)
                                        {
                                          paramInt2 = 1;
                                          localObject3 = localObject2;
                                          localObject2 = paramArrayOfBoolean;
                                          paramArrayOfBoolean = (boolean[])localObject3;
                                          localObject3 = localObject2;
                                          localObject3 = ((BigInteger)localObject3).divideAndRemainder((BigInteger)localObject4);
                                          localObject2 = localObject3[1];
                                          int i = (char)(localObject3[0].intValue() + 48);
                                          k = ((BigInteger)localObject2).compareTo((BigInteger)localObject1);
                                          localObject3 = ((BigInteger)localObject4).subtract(paramArrayOfBoolean);
                                          if (((BigInteger)localObject3).signum() <= 0)
                                          {
                                            j = 1;
                                            if ((j != 0) || (i9 != 0) || ((word1(d1) & 0x1) != 0)) {
                                              break label2629;
                                            }
                                            if (i != 57) {
                                              break label2601;
                                            }
                                            paramStringBuilder.append('9');
                                            if (!roundOff(paramStringBuilder)) {
                                              break label3088;
                                            }
                                            paramInt1 += 1;
                                            paramStringBuilder.append('1');
                                          }
                                          for (;;)
                                          {
                                            return paramInt1 + 1;
                                            j = ((BigInteger)localObject2).compareTo((BigInteger)localObject3);
                                            break label2530;
                                            char c = i;
                                            if (k > 0) {
                                              c = (char)(i + 1);
                                            }
                                            paramStringBuilder.append(c);
                                            return paramInt1 + 1;
                                            if ((k < 0) || ((k == 0) && (i9 == 0) && ((word1(d1) & 0x1) == 0)))
                                            {
                                              c = i;
                                              if (j > 0)
                                              {
                                                paramInt2 = ((BigInteger)localObject2).shiftLeft(1).compareTo((BigInteger)localObject4);
                                                if (paramInt2 <= 0)
                                                {
                                                  c = i;
                                                  if (paramInt2 != 0) {
                                                    break label2757;
                                                  }
                                                  if ((i & 0x1) != 1)
                                                  {
                                                    c = i;
                                                    if (!paramBoolean) {
                                                      break label2757;
                                                    }
                                                  }
                                                }
                                                c = (char)(i + 1);
                                                if (i == 57)
                                                {
                                                  paramStringBuilder.append('9');
                                                  paramInt2 = paramInt1;
                                                  if (roundOff(paramStringBuilder))
                                                  {
                                                    paramInt2 = paramInt1 + 1;
                                                    paramStringBuilder.append('1');
                                                  }
                                                  return paramInt2 + 1;
                                                }
                                              }
                                              paramStringBuilder.append(c);
                                              return paramInt1 + 1;
                                            }
                                            if (j > 0)
                                            {
                                              if (i == 57)
                                              {
                                                paramStringBuilder.append('9');
                                                paramInt2 = paramInt1;
                                                if (roundOff(paramStringBuilder))
                                                {
                                                  paramInt2 = paramInt1 + 1;
                                                  paramStringBuilder.append('1');
                                                }
                                                return paramInt2 + 1;
                                              }
                                              paramStringBuilder.append((char)(i + 1));
                                              return paramInt1 + 1;
                                            }
                                            paramStringBuilder.append(i);
                                            if (paramInt2 == i3)
                                            {
                                              paramInt2 = i;
                                              paramArrayOfBoolean = (boolean[])localObject2;
                                              j = paramArrayOfBoolean.shiftLeft(1).compareTo((BigInteger)localObject4);
                                              if ((j > 0) || ((j == 0) && (((paramInt2 & 0x1) == 1) || (paramBoolean))))
                                              {
                                                if (!roundOff(paramStringBuilder)) {
                                                  break label3084;
                                                }
                                                paramStringBuilder.append('1');
                                                return paramInt1 + 1 + 1;
                                              }
                                            }
                                            else
                                            {
                                              localObject3 = ((BigInteger)localObject2).multiply(BigInteger.valueOf(10L));
                                              if (localObject1 == paramArrayOfBoolean) {
                                                localObject1 = paramArrayOfBoolean.multiply(BigInteger.valueOf(10L));
                                              }
                                              for (paramArrayOfBoolean = (boolean[])localObject1;; paramArrayOfBoolean = (boolean[])localObject2)
                                              {
                                                paramInt2 += 1;
                                                localObject2 = localObject1;
                                                localObject1 = paramArrayOfBoolean;
                                                paramArrayOfBoolean = (boolean[])localObject2;
                                                break;
                                                localObject2 = ((BigInteger)localObject1).multiply(BigInteger.valueOf(10L));
                                                localObject1 = paramArrayOfBoolean.multiply(BigInteger.valueOf(10L));
                                              }
                                              paramInt2 = 1;
                                              for (;;)
                                              {
                                                localObject1 = paramArrayOfBoolean.divideAndRemainder((BigInteger)localObject4);
                                                paramArrayOfBoolean = localObject1[1];
                                                i = (char)(localObject1[0].intValue() + 48);
                                                paramStringBuilder.append(i);
                                                if (paramInt2 >= i3)
                                                {
                                                  paramInt2 = i;
                                                  break;
                                                }
                                                paramArrayOfBoolean = paramArrayOfBoolean.multiply(BigInteger.valueOf(10L));
                                                paramInt2 += 1;
                                              }
                                            }
                                            stripTrailingZeroes(paramStringBuilder);
                                            return paramInt1 + 1;
                                          }
                                        }
                                        paramInt1 = paramInt2;
                                        localObject3 = paramArrayOfBoolean;
                                        paramArrayOfBoolean = (boolean[])localObject2;
                                      }
                                    }
                                    n = paramInt1;
                                    paramInt1 = j;
                                    j = n;
                                  }
                                }
                                localObject2 = paramArrayOfBoolean;
                                localObject3 = localObject1;
                                break label1851;
                              }
                            }
                            paramInt1 = m;
                            n = i3;
                            break label1733;
                            paramInt1 = m;
                            i4 = j;
                            n = m;
                            m = i5;
                            i5 = j;
                          }
                        }
                        i4 = 1;
                      }
                    }
                    i4 = 0;
                    i3 = paramInt1;
                    i7 = paramInt2;
                    paramInt2 = n;
                  }
                }
                continue;
                d1 = paramDouble;
              }
              d2 = paramDouble;
              i4 = 0;
              paramInt2 = 2;
            }
            i1 = paramInt1;
            paramInt1 = i3;
            i6 = paramInt2;
          }
          i4 = 1;
          i9 = paramInt1;
        }
      }
      label3270:
      i8 = 1;
    }
  }
  
  static String JS_dtobasestr(int paramInt, double paramDouble)
  {
    if ((2 > paramInt) || (paramInt > 36)) {
      throw new IllegalArgumentException("Bad base: " + paramInt);
    }
    Object localObject2;
    if (Double.isNaN(paramDouble))
    {
      localObject2 = "NaN";
      return (String)localObject2;
    }
    if (Double.isInfinite(paramDouble))
    {
      if (paramDouble > 0.0D) {
        return "Infinity";
      }
      return "-Infinity";
    }
    if (paramDouble == 0.0D) {
      return "0";
    }
    int i;
    label91:
    double d;
    if (paramDouble >= 0.0D)
    {
      i = 0;
      d = Math.floor(paramDouble);
      l = d;
      if (l != d) {
        break label455;
      }
      if (i == 0) {
        break label452;
      }
      l = -l;
    }
    Object localObject1;
    int m;
    int k;
    Object localObject3;
    Object localObject4;
    label268:
    BigInteger localBigInteger1;
    BigInteger localBigInteger2;
    label356:
    label385:
    label406:
    label452:
    for (;;)
    {
      localObject1 = Long.toString(l, paramInt);
      localObject2 = localObject1;
      if (paramDouble == d) {
        break;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject1).append('.');
      l = Double.doubleToLongBits(paramDouble);
      m = (int)(l >> 32);
      k = (int)l;
      localObject3 = new int[1];
      localObject4 = d2b(paramDouble - d, (int[])localObject3, new int[1]);
      j = -(m >>> 20 & 0x7FF);
      i = j;
      if (j == 0) {
        i = -1;
      }
      i += 1076;
      localObject2 = BigInteger.valueOf(1L);
      if ((k != 0) || ((0xFFFFF & m) != 0) || ((0x7FE00000 & m) == 0)) {
        break label714;
      }
      i += 1;
      localObject1 = BigInteger.valueOf(2L);
      localObject4 = ((BigInteger)localObject4).shiftLeft(localObject3[0] + i);
      localBigInteger1 = BigInteger.valueOf(1L).shiftLeft(i);
      localBigInteger2 = BigInteger.valueOf(paramInt);
      i = 0;
      localObject3 = localObject1;
      do
      {
        localObject4 = ((BigInteger)localObject4).multiply(localBigInteger2).divideAndRemainder(localBigInteger1);
        localObject1 = localObject4[1];
        paramInt = (char)localObject4[0].intValue();
        if (localObject2 != localObject3) {
          break;
        }
        localObject3 = ((BigInteger)localObject2).multiply(localBigInteger2);
        localObject2 = localObject3;
        m = ((BigInteger)localObject1).compareTo((BigInteger)localObject2);
        localObject4 = localBigInteger1.subtract((BigInteger)localObject3);
        if (((BigInteger)localObject4).signum() > 0) {
          break label596;
        }
        j = 1;
        if ((j != 0) || ((k & 0x1) != 0)) {
          break label608;
        }
        if (m <= 0) {
          break label711;
        }
        paramInt += 1;
        j = 1;
        i = paramInt;
        paramInt = j;
        localStringBuilder.append(BASEDIGIT(i));
        i = paramInt;
        localObject4 = localObject1;
      } while (paramInt == 0);
      return localStringBuilder.toString();
      i = 1;
      paramDouble = -paramDouble;
      break label91;
    }
    label455:
    long l = Double.doubleToLongBits(d);
    int j = (int)(l >> 52) & 0x7FF;
    if (j == 0)
    {
      l = (l & 0xFFFFFFFFFFFFF) << 1;
      label488:
      if (i == 0) {
        break label721;
      }
      l = -l;
    }
    label596:
    label608:
    label705:
    label711:
    label714:
    label721:
    for (;;)
    {
      i = j - 1075;
      localObject2 = BigInteger.valueOf(l);
      if (i > 0) {
        localObject1 = ((BigInteger)localObject2).shiftLeft(i);
      }
      for (;;)
      {
        localObject1 = ((BigInteger)localObject1).toString(paramInt);
        break;
        l = l & 0xFFFFFFFFFFFFF | 0x10000000000000;
        break label488;
        localObject1 = localObject2;
        if (i < 0) {
          localObject1 = ((BigInteger)localObject2).shiftRight(-i);
        }
      }
      localObject2 = ((BigInteger)localObject2).multiply(localBigInteger2);
      localObject3 = ((BigInteger)localObject3).multiply(localBigInteger2);
      break label356;
      j = ((BigInteger)localObject1).compareTo((BigInteger)localObject4);
      break label385;
      if ((m < 0) || ((m == 0) && ((k & 0x1) == 0)))
      {
        if (j <= 0) {
          break label705;
        }
        localObject4 = ((BigInteger)localObject1).shiftLeft(1);
        localObject1 = localObject4;
        i = paramInt;
        if (((BigInteger)localObject4).compareTo(localBigInteger1) > 0)
        {
          i = paramInt + 1;
          localObject1 = localObject4;
        }
      }
      for (;;)
      {
        paramInt = 1;
        break;
        if (j > 0)
        {
          i = 1;
          j = paramInt + 1;
          paramInt = i;
          i = j;
          break;
        }
        j = paramInt;
        paramInt = i;
        i = j;
        break;
        i = paramInt;
      }
      break label406;
      localObject1 = localObject2;
      break label268;
    }
  }
  
  static void JS_dtostr(StringBuilder paramStringBuilder, int paramInt1, int paramInt2, double paramDouble)
  {
    boolean[] arrayOfBoolean = new boolean[1];
    int j = paramInt1;
    if (paramInt1 == 2) {
      if (paramDouble < 1.0E21D)
      {
        j = paramInt1;
        if (paramDouble > -1.0E21D) {}
      }
      else
      {
        j = 0;
      }
    }
    paramInt1 = dtoaModes[j];
    boolean bool;
    int i;
    int k;
    if (j >= 2)
    {
      bool = true;
      i = JS_dtoa(paramDouble, paramInt1, bool, paramInt2, arrayOfBoolean, paramStringBuilder);
      k = paramStringBuilder.length();
      if (i != 9999)
      {
        paramInt1 = paramInt2;
        switch (j)
        {
        default: 
          paramInt1 = 0;
          paramInt2 = 0;
        }
      }
    }
    for (;;)
    {
      label120:
      if (k < paramInt1) {
        do
        {
          paramStringBuilder.append('0');
        } while (paramStringBuilder.length() != paramInt1);
      }
      for (;;)
      {
        if (paramInt2 != 0)
        {
          if (paramInt1 != 1) {
            paramStringBuilder.insert(1, '.');
          }
          paramStringBuilder.append('e');
          if (i - 1 >= 0) {
            paramStringBuilder.append('+');
          }
          paramStringBuilder.append(i - 1);
        }
        for (;;)
        {
          if ((arrayOfBoolean[0] != 0) && ((word0(paramDouble) != Integer.MIN_VALUE) || (word1(paramDouble) != 0)) && (((word0(paramDouble) & 0x7FF00000) != 2146435072) || ((word1(paramDouble) == 0) && ((word0(paramDouble) & 0xFFFFF) == 0)))) {
            paramStringBuilder.insert(0, '-');
          }
          return;
          bool = false;
          break;
          if ((i < -5) || (i > 21))
          {
            paramInt1 = 0;
            paramInt2 = 1;
            break label120;
          }
          paramInt1 = i;
          paramInt2 = 0;
          break label120;
          if (paramInt2 >= 0)
          {
            paramInt1 = paramInt2 + i;
            paramInt2 = 0;
            break label120;
          }
          paramInt1 = i;
          paramInt2 = 0;
          break label120;
          paramInt1 = 0;
          paramInt2 = 1;
          break label120;
          if ((i >= -5) && (i <= paramInt2)) {
            break label398;
          }
          paramInt1 = paramInt2;
          paramInt2 = 1;
          break label120;
          if (i != paramInt1) {
            if (i > 0)
            {
              paramStringBuilder.insert(i, '.');
            }
            else
            {
              paramInt1 = 0;
              while (paramInt1 < 1 - i)
              {
                paramStringBuilder.insert(0, '0');
                paramInt1 += 1;
              }
              paramStringBuilder.insert(1, '.');
            }
          }
        }
        paramInt1 = k;
      }
      label398:
      paramInt1 = paramInt2;
      paramInt2 = 0;
    }
  }
  
  private static BigInteger d2b(double paramDouble, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int j = 1;
    long l = Double.doubleToLongBits(paramDouble);
    int i = (int)(l >>> 32);
    int i1 = (int)l;
    int k = 0xFFFFF & i;
    int n = (0x7FFFFFFF & i) >>> 20;
    i = k;
    if (n != 0) {
      i = k | 0x100000;
    }
    byte[] arrayOfByte;
    if (i1 != 0)
    {
      arrayOfByte = new byte[8];
      int m = lo0bits(i1);
      k = i1 >>> m;
      if (m != 0)
      {
        stuffBits(arrayOfByte, 4, k | i << 32 - m);
        i >>= m;
        stuffBits(arrayOfByte, 0, i);
        if (i != 0) {
          j = 2;
        }
        k = j;
        j = m;
        label133:
        if (n == 0) {
          break label222;
        }
        paramArrayOfInt1[0] = (n - 1023 - 52 + j);
        paramArrayOfInt2[0] = (53 - j);
      }
    }
    for (;;)
    {
      return new BigInteger(arrayOfByte);
      stuffBits(arrayOfByte, 4, k);
      break;
      arrayOfByte = new byte[4];
      j = lo0bits(i);
      i >>>= j;
      stuffBits(arrayOfByte, 0, i);
      k = 1;
      j += 32;
      break label133;
      label222:
      paramArrayOfInt1[0] = (j + (n - 1023 - 52 + 1));
      paramArrayOfInt2[0] = (k * 32 - hi0bits(i));
    }
  }
  
  private static int hi0bits(int paramInt)
  {
    int k = 0;
    if ((0xFFFF0000 & paramInt) == 0) {
      k = 16;
    }
    for (int i = paramInt << 16;; i = paramInt)
    {
      int j = k;
      paramInt = i;
      if ((0xFF000000 & i) == 0)
      {
        j = k + 8;
        paramInt = i << 8;
      }
      k = j;
      i = paramInt;
      if ((0xF0000000 & paramInt) == 0)
      {
        k = j + 4;
        i = paramInt << 4;
      }
      paramInt = k;
      j = i;
      if ((0xC0000000 & i) == 0)
      {
        paramInt = k + 2;
        j = i << 2;
      }
      i = paramInt;
      if ((0x80000000 & j) == 0)
      {
        i = paramInt + 1;
        if ((j & 0x40000000) == 0) {
          i = 32;
        }
      }
      return i;
    }
  }
  
  private static int lo0bits(int paramInt)
  {
    int i = 0;
    if ((paramInt & 0x7) != 0)
    {
      if ((paramInt & 0x1) != 0) {
        return i;
      }
      if ((paramInt & 0x2) != 0) {
        return 1;
      }
      return 2;
    }
    int k;
    if ((0xFFFF & paramInt) == 0) {
      k = 16;
    }
    for (int j = paramInt >>> 16;; j = paramInt)
    {
      paramInt = j;
      i = k;
      if ((j & 0xFF) == 0)
      {
        i = k + 8;
        paramInt = j >>> 8;
      }
      j = paramInt;
      k = i;
      if ((paramInt & 0xF) == 0)
      {
        k = i + 4;
        j = paramInt >>> 4;
      }
      if ((j & 0x3) == 0)
      {
        paramInt = k + 2;
        j >>>= 2;
      }
      for (;;)
      {
        i = paramInt;
        if ((j & 0x1) != 0) {
          break;
        }
        i = paramInt + 1;
        if ((j >>> 1 & 0x1) != 0) {
          break;
        }
        return 32;
        paramInt = k;
      }
      k = 0;
    }
  }
  
  static BigInteger pow5mult(BigInteger paramBigInteger, int paramInt)
  {
    return paramBigInteger.multiply(BigInteger.valueOf(5L).pow(paramInt));
  }
  
  static boolean roundOff(StringBuilder paramStringBuilder)
  {
    int i = paramStringBuilder.length();
    while (i != 0)
    {
      int j = i - 1;
      int k = paramStringBuilder.charAt(j);
      i = j;
      if (k != 57)
      {
        paramStringBuilder.setCharAt(j, (char)(k + 1));
        paramStringBuilder.setLength(j + 1);
        return false;
      }
    }
    paramStringBuilder.setLength(0);
    return true;
  }
  
  static double setWord0(double paramDouble, int paramInt)
  {
    return Double.longBitsToDouble(Double.doubleToLongBits(paramDouble) & 0xFFFFFFFF | paramInt << 32);
  }
  
  private static void stripTrailingZeroes(StringBuilder paramStringBuilder)
  {
    int j;
    for (int i = paramStringBuilder.length();; i = j)
    {
      j = i - 1;
      if ((i <= 0) || (paramStringBuilder.charAt(j) != '0')) {
        break;
      }
    }
    paramStringBuilder.setLength(j + 1);
  }
  
  private static void stuffBits(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[paramInt1] = ((byte)(paramInt2 >> 24));
    paramArrayOfByte[(paramInt1 + 1)] = ((byte)(paramInt2 >> 16));
    paramArrayOfByte[(paramInt1 + 2)] = ((byte)(paramInt2 >> 8));
    paramArrayOfByte[(paramInt1 + 3)] = ((byte)paramInt2);
  }
  
  static int word0(double paramDouble)
  {
    return (int)(Double.doubleToLongBits(paramDouble) >> 32);
  }
  
  static int word1(double paramDouble)
  {
    return (int)Double.doubleToLongBits(paramDouble);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\DToA.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */