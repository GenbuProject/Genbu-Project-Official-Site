package org.mozilla.javascript;

final class NativeNumber
  extends IdScriptableObject
{
  private static final int ConstructorId_isFinite = -1;
  private static final int ConstructorId_isInteger = -3;
  private static final int ConstructorId_isNaN = -2;
  private static final int ConstructorId_isSafeInteger = -4;
  private static final int ConstructorId_parseFloat = -5;
  private static final int ConstructorId_parseInt = -6;
  private static final int Id_constructor = 1;
  private static final int Id_toExponential = 7;
  private static final int Id_toFixed = 6;
  private static final int Id_toLocaleString = 3;
  private static final int Id_toPrecision = 8;
  private static final int Id_toSource = 4;
  private static final int Id_toString = 2;
  private static final int Id_valueOf = 5;
  private static final int MAX_PRECISION = 100;
  private static final int MAX_PROTOTYPE_ID = 8;
  private static final double MAX_SAFE_INTEGER = Math.pow(2.0D, 53.0D) - 1.0D;
  private static final double MIN_SAFE_INTEGER = -MAX_SAFE_INTEGER;
  private static final Object NUMBER_TAG = "Number";
  static final long serialVersionUID = 3504516769741512101L;
  private double doubleValue;
  
  NativeNumber(double paramDouble)
  {
    this.doubleValue = paramDouble;
  }
  
  private Double doubleVal(Number paramNumber)
  {
    if ((paramNumber instanceof Double)) {
      return (Double)paramNumber;
    }
    return Double.valueOf(paramNumber.doubleValue());
  }
  
  private Object execConstructorCall(int paramInt, Object[] paramArrayOfObject)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case -1: 
      if ((paramArrayOfObject.length == 0) || (Undefined.instance == paramArrayOfObject[0])) {
        return Boolean.valueOf(false);
      }
      if ((paramArrayOfObject[0] instanceof Number)) {
        return isFinite(paramArrayOfObject[0]);
      }
      return Boolean.valueOf(false);
    case -2: 
      if ((paramArrayOfObject.length == 0) || (Undefined.instance == paramArrayOfObject[0])) {
        return Boolean.valueOf(false);
      }
      if ((paramArrayOfObject[0] instanceof Number)) {
        return isNaN((Number)paramArrayOfObject[0]);
      }
      return Boolean.valueOf(false);
    case -3: 
      if ((paramArrayOfObject.length == 0) || (Undefined.instance == paramArrayOfObject[0])) {
        return Boolean.valueOf(false);
      }
      if ((paramArrayOfObject[0] instanceof Number)) {
        return Boolean.valueOf(isInteger((Number)paramArrayOfObject[0]));
      }
      return Boolean.valueOf(false);
    case -4: 
      if ((paramArrayOfObject.length == 0) || (Undefined.instance == paramArrayOfObject[0])) {
        return Boolean.valueOf(false);
      }
      if ((paramArrayOfObject[0] instanceof Number)) {
        return Boolean.valueOf(isSafeInteger((Number)paramArrayOfObject[0]));
      }
      return Boolean.valueOf(false);
    case -5: 
      return NativeGlobal.js_parseFloat(paramArrayOfObject);
    }
    return NativeGlobal.js_parseInt(paramArrayOfObject);
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    new NativeNumber(0.0D).exportAsJSClass(8, paramScriptable, paramBoolean);
  }
  
  private boolean isDoubleInteger(Double paramDouble)
  {
    return (!paramDouble.isInfinite()) && (!paramDouble.isNaN()) && (Math.floor(paramDouble.doubleValue()) == paramDouble.doubleValue());
  }
  
  private boolean isDoubleNan(Double paramDouble)
  {
    return paramDouble.isNaN();
  }
  
  private boolean isDoubleSafeInteger(Double paramDouble)
  {
    return (isDoubleInteger(paramDouble)) && (paramDouble.doubleValue() <= MAX_SAFE_INTEGER) && (paramDouble.doubleValue() >= MIN_SAFE_INTEGER);
  }
  
  static Object isFinite(Object paramObject)
  {
    paramObject = Double.valueOf(ScriptRuntime.toNumber(paramObject));
    if ((!((Double)paramObject).isInfinite()) && (!((Double)paramObject).isNaN())) {}
    for (boolean bool = true;; bool = false) {
      return ScriptRuntime.wrapBoolean(bool);
    }
  }
  
  private boolean isInteger(Number paramNumber)
  {
    return ScriptRuntime.toBoolean(Boolean.valueOf(isDoubleInteger(doubleVal(paramNumber))));
  }
  
  private Object isNaN(Number paramNumber)
  {
    return Boolean.valueOf(ScriptRuntime.toBoolean(Boolean.valueOf(isDoubleNan(doubleVal(paramNumber)))));
  }
  
  private boolean isSafeInteger(Number paramNumber)
  {
    return ScriptRuntime.toBoolean(Boolean.valueOf(isDoubleSafeInteger(doubleVal(paramNumber))));
  }
  
  private static String num_to(double paramDouble, Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    if (paramArrayOfObject.length == 0) {
      paramInt2 = paramInt1;
    }
    double d;
    for (paramInt1 = i;; paramInt1 = ScriptRuntime.toInt32(d))
    {
      paramArrayOfObject = new StringBuilder();
      DToA.JS_dtostr(paramArrayOfObject, paramInt2, paramInt1 + paramInt4, paramDouble);
      return paramArrayOfObject.toString();
      d = ScriptRuntime.toInteger(paramArrayOfObject[0]);
      if ((d < paramInt3) || (d > 100.0D)) {
        throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage1("msg.bad.precision", ScriptRuntime.toString(paramArrayOfObject[0])));
      }
    }
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(NUMBER_TAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i = paramIdFunctionObject.methodId();
    if (i == 1)
    {
      if (paramArrayOfObject.length >= 1) {}
      for (d = ScriptRuntime.toNumber(paramArrayOfObject[0]); paramScriptable2 == null; d = 0.0D) {
        return new NativeNumber(d);
      }
      return ScriptRuntime.wrapNumber(d);
    }
    if (i < 1) {
      return execConstructorCall(i, paramArrayOfObject);
    }
    if (!(paramScriptable2 instanceof NativeNumber)) {
      throw incompatibleCallError(paramIdFunctionObject);
    }
    double d = ((NativeNumber)paramScriptable2).doubleValue;
    switch (i)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    case 2: 
    case 3: 
      if ((paramArrayOfObject.length == 0) || (paramArrayOfObject[0] == Undefined.instance)) {}
      for (i = 10;; i = ScriptRuntime.toInt32(paramArrayOfObject[0])) {
        return ScriptRuntime.numberToString(d, i);
      }
    case 4: 
      return "(new Number(" + ScriptRuntime.toString(d) + "))";
    case 5: 
      return ScriptRuntime.wrapNumber(d);
    case 6: 
      return num_to(d, paramArrayOfObject, 2, 2, -20, 0);
    case 7: 
      if (Double.isNaN(d)) {
        return "NaN";
      }
      if (Double.isInfinite(d))
      {
        if (d >= 0.0D) {
          return "Infinity";
        }
        return "-Infinity";
      }
      return num_to(d, paramArrayOfObject, 1, 3, 0, 1);
    }
    if ((paramArrayOfObject.length == 0) || (paramArrayOfObject[0] == Undefined.instance)) {
      return ScriptRuntime.numberToString(d, 10);
    }
    if (Double.isNaN(d)) {
      return "NaN";
    }
    if (Double.isInfinite(d))
    {
      if (d >= 0.0D) {
        return "Infinity";
      }
      return "-Infinity";
    }
    return num_to(d, paramArrayOfObject, 0, 4, 1, 0);
  }
  
  protected void fillConstructorProperties(IdFunctionObject paramIdFunctionObject)
  {
    paramIdFunctionObject.defineProperty("NaN", ScriptRuntime.NaNobj, 7);
    paramIdFunctionObject.defineProperty("POSITIVE_INFINITY", ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY), 7);
    paramIdFunctionObject.defineProperty("NEGATIVE_INFINITY", ScriptRuntime.wrapNumber(Double.NEGATIVE_INFINITY), 7);
    paramIdFunctionObject.defineProperty("MAX_VALUE", ScriptRuntime.wrapNumber(Double.MAX_VALUE), 7);
    paramIdFunctionObject.defineProperty("MIN_VALUE", ScriptRuntime.wrapNumber(Double.MIN_VALUE), 7);
    paramIdFunctionObject.defineProperty("MAX_SAFE_INTEGER", ScriptRuntime.wrapNumber(MAX_SAFE_INTEGER), 7);
    paramIdFunctionObject.defineProperty("MIN_SAFE_INTEGER", ScriptRuntime.wrapNumber(MIN_SAFE_INTEGER), 7);
    addIdFunctionProperty(paramIdFunctionObject, NUMBER_TAG, -1, "isFinite", 1);
    addIdFunctionProperty(paramIdFunctionObject, NUMBER_TAG, -2, "isNaN", 1);
    addIdFunctionProperty(paramIdFunctionObject, NUMBER_TAG, -3, "isInteger", 1);
    addIdFunctionProperty(paramIdFunctionObject, NUMBER_TAG, -4, "isSafeInteger", 1);
    addIdFunctionProperty(paramIdFunctionObject, NUMBER_TAG, -5, "parseFloat", 1);
    addIdFunctionProperty(paramIdFunctionObject, NUMBER_TAG, -6, "parseInt", 1);
    super.fillConstructorProperties(paramIdFunctionObject);
  }
  
  protected int findPrototypeId(String paramString)
  {
    String str;
    int i;
    switch (paramString.length())
    {
    case 9: 
    case 10: 
    case 12: 
    default: 
      str = null;
      i = 0;
    }
    for (;;)
    {
      if ((str == null) || (str == paramString) || (str.equals(paramString))) {
        return i;
      }
      return 0;
      i = paramString.charAt(0);
      if (i == 116)
      {
        i = 6;
        str = "toFixed";
      }
      else
      {
        if (i != 118) {
          break;
        }
        i = 5;
        str = "valueOf";
        continue;
        i = paramString.charAt(3);
        if (i == 111)
        {
          i = 4;
          str = "toSource";
        }
        else
        {
          if (i != 116) {
            break;
          }
          i = 2;
          str = "toString";
          continue;
          i = paramString.charAt(0);
          if (i == 99)
          {
            i = 1;
            str = "constructor";
          }
          else
          {
            if (i != 116) {
              break;
            }
            i = 8;
            str = "toPrecision";
            continue;
            i = 7;
            str = "toExponential";
            continue;
            i = 3;
            str = "toLocaleString";
          }
        }
      }
    }
    return i;
  }
  
  public String getClassName()
  {
    return "Number";
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i = 1;
    String str;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      str = "constructor";
    }
    for (;;)
    {
      initPrototypeMethod(NUMBER_TAG, paramInt, str, i);
      return;
      str = "toString";
      continue;
      str = "toLocaleString";
      continue;
      str = "toSource";
      i = 0;
      continue;
      str = "valueOf";
      i = 0;
      continue;
      str = "toFixed";
      continue;
      str = "toExponential";
      continue;
      str = "toPrecision";
    }
  }
  
  public String toString()
  {
    return ScriptRuntime.numberToString(this.doubleValue, 10);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeNumber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */