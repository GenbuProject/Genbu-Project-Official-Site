package org.mozilla.javascript;

import org.mozilla.javascript.typedarrays.Conversions;

final class NativeMath
  extends IdScriptableObject
{
  private static final int Id_E = 30;
  private static final int Id_LN10 = 32;
  private static final int Id_LN2 = 33;
  private static final int Id_LOG10E = 35;
  private static final int Id_LOG2E = 34;
  private static final int Id_PI = 31;
  private static final int Id_SQRT1_2 = 36;
  private static final int Id_SQRT2 = 37;
  private static final int Id_abs = 2;
  private static final int Id_acos = 3;
  private static final int Id_asin = 4;
  private static final int Id_atan = 5;
  private static final int Id_atan2 = 6;
  private static final int Id_cbrt = 20;
  private static final int Id_ceil = 7;
  private static final int Id_cos = 8;
  private static final int Id_cosh = 21;
  private static final int Id_exp = 9;
  private static final int Id_expm1 = 22;
  private static final int Id_floor = 10;
  private static final int Id_hypot = 23;
  private static final int Id_imul = 28;
  private static final int Id_log = 11;
  private static final int Id_log10 = 25;
  private static final int Id_log1p = 24;
  private static final int Id_max = 12;
  private static final int Id_min = 13;
  private static final int Id_pow = 14;
  private static final int Id_random = 15;
  private static final int Id_round = 16;
  private static final int Id_sin = 17;
  private static final int Id_sinh = 26;
  private static final int Id_sqrt = 18;
  private static final int Id_tan = 19;
  private static final int Id_tanh = 27;
  private static final int Id_toSource = 1;
  private static final int Id_trunc = 29;
  private static final int LAST_METHOD_ID = 29;
  private static final Object MATH_TAG = "Math";
  private static final int MAX_ID = 37;
  static final long serialVersionUID = -8838847185801131569L;
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    NativeMath localNativeMath = new NativeMath();
    localNativeMath.activatePrototypeMap(37);
    localNativeMath.setPrototype(getObjectPrototype(paramScriptable));
    localNativeMath.setParentScope(paramScriptable);
    if (paramBoolean) {
      localNativeMath.sealObject();
    }
    ScriptableObject.defineProperty(paramScriptable, "Math", localNativeMath, 2);
  }
  
  private double js_hypot(Object[] paramArrayOfObject)
  {
    double d1 = 0.0D;
    if (paramArrayOfObject == null) {
      return d1;
    }
    int j = paramArrayOfObject.length;
    int i = 0;
    double d2 = 0.0D;
    for (;;)
    {
      if (i >= j) {
        break label87;
      }
      double d3 = ScriptRuntime.toNumber(paramArrayOfObject[i]);
      d1 = d3;
      if (d3 == ScriptRuntime.NaN) {
        break;
      }
      if ((d3 == Double.POSITIVE_INFINITY) || (d3 == Double.NEGATIVE_INFINITY)) {
        return Double.POSITIVE_INFINITY;
      }
      d2 += d3 * d3;
      i += 1;
    }
    label87:
    return Math.sqrt(d2);
  }
  
  private Object js_imul(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length < 2)) {
      return ScriptRuntime.wrapNumber(ScriptRuntime.NaN);
    }
    long l2 = Conversions.toUint32(paramArrayOfObject[0]) * Conversions.toUint32(paramArrayOfObject[1]) % 4294967296L;
    long l1 = l2;
    if (l2 >= 2147483648L) {
      l1 = l2 - 4294967296L;
    }
    return Double.valueOf(ScriptRuntime.toNumber(Long.valueOf(l1)));
  }
  
  private double js_pow(double paramDouble1, double paramDouble2)
  {
    double d1;
    if (paramDouble2 != paramDouble2) {
      d1 = paramDouble2;
    }
    do
    {
      double d2;
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return d1;
                if (paramDouble2 == 0.0D) {
                  return 1.0D;
                }
                if (paramDouble1 == 0.0D)
                {
                  if (1.0D / paramDouble1 > 0.0D)
                  {
                    if (paramDouble2 > 0.0D) {
                      return 0.0D;
                    }
                    return Double.POSITIVE_INFINITY;
                  }
                  l = paramDouble2;
                  if ((l == paramDouble2) && ((l & 1L) != 0L))
                  {
                    if (paramDouble2 > 0.0D) {
                      return 0.0D;
                    }
                    return Double.NEGATIVE_INFINITY;
                  }
                  if (paramDouble2 > 0.0D) {
                    return 0.0D;
                  }
                  return Double.POSITIVE_INFINITY;
                }
                d2 = Math.pow(paramDouble1, paramDouble2);
                d1 = d2;
              } while (d2 == d2);
              if (paramDouble2 != Double.POSITIVE_INFINITY) {
                break;
              }
              if ((paramDouble1 < -1.0D) || (1.0D < paramDouble1)) {
                return Double.POSITIVE_INFINITY;
              }
              d1 = d2;
            } while (-1.0D >= paramDouble1);
            d1 = d2;
          } while (paramDouble1 >= 1.0D);
          return 0.0D;
          if (paramDouble2 != Double.NEGATIVE_INFINITY) {
            break;
          }
          if ((paramDouble1 < -1.0D) || (1.0D < paramDouble1)) {
            return 0.0D;
          }
          d1 = d2;
        } while (-1.0D >= paramDouble1);
        d1 = d2;
      } while (paramDouble1 >= 1.0D);
      return Double.POSITIVE_INFINITY;
      if (paramDouble1 == Double.POSITIVE_INFINITY)
      {
        if (paramDouble2 > 0.0D) {
          return Double.POSITIVE_INFINITY;
        }
        return 0.0D;
      }
      d1 = d2;
    } while (paramDouble1 != Double.NEGATIVE_INFINITY);
    long l = paramDouble2;
    if ((l == paramDouble2) && ((l & 1L) != 0L))
    {
      if (paramDouble2 > 0.0D) {
        return Double.NEGATIVE_INFINITY;
      }
      return 0.0D;
    }
    if (paramDouble2 > 0.0D) {
      return Double.POSITIVE_INFINITY;
    }
    return 0.0D;
  }
  
  private double js_trunc(double paramDouble)
  {
    if (paramDouble < 0.0D) {
      return Math.ceil(paramDouble);
    }
    return Math.floor(paramDouble);
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    double d3 = NaN.0D;
    double d2 = 0.0D;
    if (!paramIdFunctionObject.hasTag(MATH_TAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int j = paramIdFunctionObject.methodId();
    double d1;
    switch (j)
    {
    default: 
      throw new IllegalStateException(String.valueOf(j));
    case 1: 
      return "Math";
    case 2: 
      d1 = ScriptRuntime.toNumber(paramArrayOfObject, 0);
      if (d1 == 0.0D) {
        d1 = d2;
      }
      break;
    }
    for (;;)
    {
      return ScriptRuntime.wrapNumber(d1);
      if (d1 < 0.0D)
      {
        d1 = -d1;
      }
      else
      {
        continue;
        d2 = ScriptRuntime.toNumber(paramArrayOfObject, 0);
        d1 = d3;
        if (d2 == d2)
        {
          d1 = d3;
          if (-1.0D <= d2)
          {
            d1 = d3;
            if (d2 <= 1.0D)
            {
              if (j == 3) {}
              for (d1 = Math.acos(d2);; d1 = Math.asin(d2)) {
                break;
              }
              d1 = Math.atan(ScriptRuntime.toNumber(paramArrayOfObject, 0));
              continue;
              d1 = Math.atan2(ScriptRuntime.toNumber(paramArrayOfObject, 0), ScriptRuntime.toNumber(paramArrayOfObject, 1));
              continue;
              d1 = Math.cbrt(ScriptRuntime.toNumber(paramArrayOfObject, 0));
              continue;
              d1 = Math.ceil(ScriptRuntime.toNumber(paramArrayOfObject, 0));
              continue;
              d1 = ScriptRuntime.toNumber(paramArrayOfObject, 0);
              if ((d1 == Double.POSITIVE_INFINITY) || (d1 == Double.NEGATIVE_INFINITY)) {}
              for (d1 = NaN.0D;; d1 = Math.cos(d1)) {
                break;
              }
              d1 = Math.cosh(ScriptRuntime.toNumber(paramArrayOfObject, 0));
              continue;
              d1 = js_hypot(paramArrayOfObject);
              continue;
              d3 = ScriptRuntime.toNumber(paramArrayOfObject, 0);
              if (d3 == Double.POSITIVE_INFINITY) {
                d1 = d3;
              }
              for (;;)
              {
                break;
                d1 = d2;
                if (d3 != Double.NEGATIVE_INFINITY) {
                  d1 = Math.exp(d3);
                }
              }
              d1 = Math.expm1(ScriptRuntime.toNumber(paramArrayOfObject, 0));
              continue;
              d1 = Math.floor(ScriptRuntime.toNumber(paramArrayOfObject, 0));
              continue;
              return js_imul(paramArrayOfObject);
              d2 = ScriptRuntime.toNumber(paramArrayOfObject, 0);
              d1 = d3;
              if (d2 >= 0.0D)
              {
                d1 = Math.log(d2);
                continue;
                d1 = Math.log1p(ScriptRuntime.toNumber(paramArrayOfObject, 0));
                continue;
                d1 = Math.log10(ScriptRuntime.toNumber(paramArrayOfObject, 0));
                continue;
                if (j == 12) {}
                int i;
                for (d1 = Double.NEGATIVE_INFINITY;; d1 = Double.POSITIVE_INFINITY)
                {
                  i = 0;
                  d2 = d1;
                  d1 = d2;
                  if (i == paramArrayOfObject.length) {
                    break;
                  }
                  d1 = ScriptRuntime.toNumber(paramArrayOfObject[i]);
                  if (d1 == d1) {
                    break label627;
                  }
                  break;
                }
                label627:
                if (j == 12) {}
                for (d2 = Math.max(d2, d1);; d2 = Math.min(d2, d1))
                {
                  i += 1;
                  break;
                }
                d1 = js_pow(ScriptRuntime.toNumber(paramArrayOfObject, 0), ScriptRuntime.toNumber(paramArrayOfObject, 1));
                continue;
                d1 = Math.random();
                continue;
                d3 = ScriptRuntime.toNumber(paramArrayOfObject, 0);
                d1 = d3;
                if (d3 == d3)
                {
                  d1 = d3;
                  if (d3 != Double.POSITIVE_INFINITY)
                  {
                    d1 = d3;
                    if (d3 != Double.NEGATIVE_INFINITY)
                    {
                      long l = Math.round(d3);
                      if (l != 0L) {
                        d1 = l;
                      }
                      for (;;)
                      {
                        break;
                        if (d3 < 0.0D)
                        {
                          d1 = ScriptRuntime.negativeZero;
                        }
                        else
                        {
                          d1 = d2;
                          if (d3 == 0.0D) {
                            d1 = d3;
                          }
                        }
                      }
                      d2 = ScriptRuntime.toNumber(paramArrayOfObject, 0);
                      d1 = d3;
                      if (d2 != Double.POSITIVE_INFINITY)
                      {
                        d1 = d3;
                        if (d2 != Double.NEGATIVE_INFINITY)
                        {
                          d1 = Math.sin(d2);
                          continue;
                          d1 = Math.sinh(ScriptRuntime.toNumber(paramArrayOfObject, 0));
                          continue;
                          d1 = Math.sqrt(ScriptRuntime.toNumber(paramArrayOfObject, 0));
                          continue;
                          d1 = Math.tan(ScriptRuntime.toNumber(paramArrayOfObject, 0));
                          continue;
                          d1 = Math.tanh(ScriptRuntime.toNumber(paramArrayOfObject, 0));
                          continue;
                          d1 = js_trunc(ScriptRuntime.toNumber(paramArrayOfObject, 0));
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  protected int findPrototypeId(String paramString)
  {
    String str = null;
    int i;
    switch (paramString.length())
    {
    default: 
      i = 0;
    }
    for (;;)
    {
      int j = i;
      if (str != null)
      {
        j = i;
        if (str != paramString)
        {
          j = i;
          if (!str.equals(paramString)) {
            j = 0;
          }
        }
      }
      return j;
      if (paramString.charAt(0) != 'E') {
        break;
      }
      return 30;
      if ((paramString.charAt(0) != 'P') || (paramString.charAt(1) != 'I')) {
        break;
      }
      return 31;
      switch (paramString.charAt(0))
      {
      default: 
        i = 0;
        break;
      case 'L': 
        if ((paramString.charAt(2) != '2') || (paramString.charAt(1) != 'N')) {
          break;
        }
        return 33;
      case 'a': 
        if ((paramString.charAt(2) != 's') || (paramString.charAt(1) != 'b')) {
          break;
        }
        return 2;
      case 'c': 
        if ((paramString.charAt(2) != 's') || (paramString.charAt(1) != 'o')) {
          break;
        }
        return 8;
      case 'e': 
        if ((paramString.charAt(2) != 'p') || (paramString.charAt(1) != 'x')) {
          break;
        }
        return 9;
      case 'l': 
        if ((paramString.charAt(2) != 'g') || (paramString.charAt(1) != 'o')) {
          break;
        }
        return 11;
      case 'm': 
        i = paramString.charAt(2);
        if (i == 110)
        {
          if (paramString.charAt(1) != 'i') {
            break;
          }
          return 13;
        }
        if ((i != 120) || (paramString.charAt(1) != 'a')) {
          break;
        }
        return 12;
      case 'p': 
        if ((paramString.charAt(2) != 'w') || (paramString.charAt(1) != 'o')) {
          break;
        }
        return 14;
      case 's': 
        if ((paramString.charAt(2) != 'n') || (paramString.charAt(1) != 'i')) {
          break;
        }
        return 17;
      case 't': 
        if ((paramString.charAt(2) != 'n') || (paramString.charAt(1) != 'a')) {
          break;
        }
        return 19;
        switch (paramString.charAt(1))
        {
        default: 
          i = 0;
          break;
        case 'N': 
          i = 32;
          str = "LN10";
          break;
        case 'a': 
          i = 27;
          str = "tanh";
          break;
        case 'b': 
          i = 20;
          str = "cbrt";
          break;
        case 'c': 
          i = 3;
          str = "acos";
          break;
        case 'e': 
          i = 7;
          str = "ceil";
          break;
        case 'i': 
          i = 26;
          str = "sinh";
          break;
        case 'm': 
          i = 28;
          str = "imul";
          break;
        case 'o': 
          i = 21;
          str = "cosh";
          break;
        case 'q': 
          i = 18;
          str = "sqrt";
          break;
        case 's': 
          i = 4;
          str = "asin";
          break;
        case 't': 
          i = 5;
          str = "atan";
          continue;
          switch (paramString.charAt(0))
          {
          default: 
            i = 0;
            break;
          case 'L': 
            i = 34;
            str = "LOG2E";
            break;
          case 'S': 
            i = 37;
            str = "SQRT2";
            break;
          case 'a': 
            i = 6;
            str = "atan2";
            break;
          case 'e': 
            i = 22;
            str = "expm1";
            break;
          case 'f': 
            i = 10;
            str = "floor";
            break;
          case 'h': 
            i = 23;
            str = "hypot";
            break;
          case 'l': 
            i = paramString.charAt(4);
            if (i == 48)
            {
              i = 25;
              str = "log10";
            }
            else
            {
              if (i != 112) {
                break;
              }
              i = 24;
              str = "log1p";
            }
            break;
          case 'r': 
            i = 16;
            str = "round";
            break;
          case 't': 
            i = 29;
            str = "trunc";
            continue;
            i = paramString.charAt(0);
            if (i == 76)
            {
              i = 35;
              str = "LOG10E";
            }
            else
            {
              if (i != 114) {
                break;
              }
              i = 15;
              str = "random";
              continue;
              i = 36;
              str = "SQRT1_2";
              continue;
              str = "toSource";
              i = 1;
            }
            break;
          }
          break;
        }
        break;
      }
    }
  }
  
  public String getClassName()
  {
    return "Math";
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i = 0;
    String str;
    if (paramInt <= 29)
    {
      switch (paramInt)
      {
      default: 
        throw new IllegalStateException(String.valueOf(paramInt));
      case 1: 
        str = "toSource";
      }
      for (;;)
      {
        initPrototypeMethod(MATH_TAG, paramInt, str, i);
        return;
        str = "abs";
        i = 1;
        continue;
        str = "acos";
        i = 1;
        continue;
        str = "asin";
        i = 1;
        continue;
        str = "atan";
        i = 1;
        continue;
        str = "atan2";
        i = 2;
        continue;
        str = "cbrt";
        i = 1;
        continue;
        str = "ceil";
        i = 1;
        continue;
        str = "cos";
        i = 1;
        continue;
        str = "cosh";
        i = 1;
        continue;
        str = "exp";
        i = 1;
        continue;
        str = "expm1";
        i = 1;
        continue;
        str = "floor";
        i = 1;
        continue;
        str = "hypot";
        i = 2;
        continue;
        str = "imul";
        i = 2;
        continue;
        str = "log";
        i = 1;
        continue;
        str = "log1p";
        i = 1;
        continue;
        str = "log10";
        i = 1;
        continue;
        str = "max";
        i = 2;
        continue;
        str = "min";
        i = 2;
        continue;
        str = "pow";
        i = 2;
        continue;
        str = "random";
        continue;
        str = "round";
        i = 1;
        continue;
        str = "sin";
        i = 1;
        continue;
        str = "sinh";
        i = 1;
        continue;
        str = "sqrt";
        i = 1;
        continue;
        str = "tan";
        i = 1;
        continue;
        str = "tanh";
        i = 1;
        continue;
        str = "trunc";
        i = 1;
      }
    }
    double d;
    switch (paramInt)
    {
    default: 
      throw new IllegalStateException(String.valueOf(paramInt));
    case 30: 
      d = 2.718281828459045D;
      str = "E";
    }
    for (;;)
    {
      initPrototypeValue(paramInt, str, ScriptRuntime.wrapNumber(d), 7);
      return;
      d = 3.141592653589793D;
      str = "PI";
      continue;
      d = 2.302585092994046D;
      str = "LN10";
      continue;
      d = 0.6931471805599453D;
      str = "LN2";
      continue;
      d = 1.4426950408889634D;
      str = "LOG2E";
      continue;
      d = 0.4342944819032518D;
      str = "LOG10E";
      continue;
      d = 0.7071067811865476D;
      str = "SQRT1_2";
      continue;
      d = 1.4142135623730951D;
      str = "SQRT2";
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeMath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */