package org.mozilla.javascript;

import java.text.Collator;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import org.mozilla.javascript.regexp.NativeRegExp;

final class NativeString
  extends IdScriptableObject
{
  private static final int ConstructorId_charAt = -5;
  private static final int ConstructorId_charCodeAt = -6;
  private static final int ConstructorId_concat = -14;
  private static final int ConstructorId_equalsIgnoreCase = -30;
  private static final int ConstructorId_fromCharCode = -1;
  private static final int ConstructorId_indexOf = -7;
  private static final int ConstructorId_lastIndexOf = -8;
  private static final int ConstructorId_localeCompare = -34;
  private static final int ConstructorId_match = -31;
  private static final int ConstructorId_replace = -33;
  private static final int ConstructorId_search = -32;
  private static final int ConstructorId_slice = -15;
  private static final int ConstructorId_split = -9;
  private static final int ConstructorId_substr = -13;
  private static final int ConstructorId_substring = -10;
  private static final int ConstructorId_toLocaleLowerCase = -35;
  private static final int ConstructorId_toLowerCase = -11;
  private static final int ConstructorId_toUpperCase = -12;
  private static final int Id_anchor = 28;
  private static final int Id_big = 21;
  private static final int Id_blink = 22;
  private static final int Id_bold = 16;
  private static final int Id_charAt = 5;
  private static final int Id_charCodeAt = 6;
  private static final int Id_codePointAt = 45;
  private static final int Id_concat = 14;
  private static final int Id_constructor = 1;
  private static final int Id_endsWith = 42;
  private static final int Id_equals = 29;
  private static final int Id_equalsIgnoreCase = 30;
  private static final int Id_fixed = 18;
  private static final int Id_fontcolor = 26;
  private static final int Id_fontsize = 25;
  private static final int Id_includes = 40;
  private static final int Id_indexOf = 7;
  private static final int Id_italics = 17;
  private static final int Id_iterator = 46;
  private static final int Id_lastIndexOf = 8;
  private static final int Id_length = 1;
  private static final int Id_link = 27;
  private static final int Id_localeCompare = 34;
  private static final int Id_match = 31;
  private static final int Id_normalize = 43;
  private static final int Id_repeat = 44;
  private static final int Id_replace = 33;
  private static final int Id_search = 32;
  private static final int Id_slice = 15;
  private static final int Id_small = 20;
  private static final int Id_split = 9;
  private static final int Id_startsWith = 41;
  private static final int Id_strike = 19;
  private static final int Id_sub = 24;
  private static final int Id_substr = 13;
  private static final int Id_substring = 10;
  private static final int Id_sup = 23;
  private static final int Id_toLocaleLowerCase = 35;
  private static final int Id_toLocaleUpperCase = 36;
  private static final int Id_toLowerCase = 11;
  private static final int Id_toSource = 3;
  private static final int Id_toString = 2;
  private static final int Id_toUpperCase = 12;
  private static final int Id_trim = 37;
  private static final int Id_trimLeft = 38;
  private static final int Id_trimRight = 39;
  private static final int Id_valueOf = 4;
  private static final int MAX_INSTANCE_ID = 1;
  private static final int MAX_PROTOTYPE_ID = 46;
  private static final Object STRING_TAG = "String";
  static final long serialVersionUID = 920268368584188687L;
  private CharSequence string;
  
  NativeString(CharSequence paramCharSequence)
  {
    this.string = paramCharSequence;
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    new NativeString("").exportAsJSClass(46, paramScriptable, paramBoolean);
  }
  
  private static String js_concat(String paramString, Object[] paramArrayOfObject)
  {
    int k = 0;
    int m = paramArrayOfObject.length;
    if (m == 0) {
      return paramString;
    }
    if (m == 1) {
      return paramString.concat(ScriptRuntime.toString(paramArrayOfObject[0]));
    }
    int j = paramString.length();
    String[] arrayOfString = new String[m];
    int i = 0;
    while (i != m)
    {
      String str = ScriptRuntime.toString(paramArrayOfObject[i]);
      arrayOfString[i] = str;
      j += str.length();
      i += 1;
    }
    paramArrayOfObject = new StringBuilder(j);
    paramArrayOfObject.append(paramString);
    i = k;
    while (i != m)
    {
      paramArrayOfObject.append(arrayOfString[i]);
      i += 1;
    }
    return paramArrayOfObject.toString();
  }
  
  private static int js_indexOf(int paramInt, String paramString, Object[] paramArrayOfObject)
  {
    double d1 = 0.0D;
    int i = 0;
    String str = ScriptRuntime.toString(paramArrayOfObject, 0);
    double d2 = ScriptRuntime.toInteger(paramArrayOfObject, 1);
    if ((d2 > paramString.length()) && (paramInt != 41) && (paramInt != 42)) {
      return -1;
    }
    if (d2 < 0.0D) {}
    for (;;)
    {
      if (42 == paramInt)
      {
        if ((paramArrayOfObject.length != 0) && (paramArrayOfObject.length != 1))
        {
          d2 = d1;
          if (paramArrayOfObject.length == 2)
          {
            d2 = d1;
            if (paramArrayOfObject[1] != Undefined.instance) {}
          }
        }
        else
        {
          d2 = paramString.length();
        }
        if (paramString.substring(0, (int)d2).endsWith(str)) {}
        for (paramInt = 0;; paramInt = -1)
        {
          return paramInt;
          if (d2 > paramString.length())
          {
            d1 = paramString.length();
            break;
          }
          if ((paramInt != 42) || ((d2 == d2) && (d2 <= paramString.length()))) {
            break label214;
          }
          d1 = paramString.length();
          break;
        }
      }
      if (paramInt == 41) {
        if (paramString.startsWith(str, (int)d1)) {
          paramInt = i;
        }
      }
      for (;;)
      {
        return paramInt;
        paramInt = -1;
        continue;
        paramInt = paramString.indexOf(str, (int)d1);
      }
      label214:
      d1 = d2;
    }
  }
  
  private static int js_lastIndexOf(String paramString, Object[] paramArrayOfObject)
  {
    double d1 = 0.0D;
    String str = ScriptRuntime.toString(paramArrayOfObject, 0);
    double d2 = ScriptRuntime.toNumber(paramArrayOfObject, 1);
    if ((d2 != d2) || (d2 > paramString.length())) {
      d1 = paramString.length();
    }
    for (;;)
    {
      return paramString.lastIndexOf(str, (int)d1);
      if (d2 >= 0.0D) {
        d1 = d2;
      }
    }
  }
  
  private static String js_repeat(Context paramContext, Scriptable paramScriptable, IdFunctionObject paramIdFunctionObject, Object[] paramArrayOfObject)
  {
    paramContext = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(paramContext, paramScriptable, paramIdFunctionObject));
    double d = ScriptRuntime.toInteger(paramArrayOfObject, 0);
    if ((d < 0.0D) || (d == Double.POSITIVE_INFINITY)) {
      throw ScriptRuntime.rangeError("Invalid count value");
    }
    if ((d == 0.0D) || (paramContext.length() == 0)) {
      return "";
    }
    long l = paramContext.length() * d;
    if ((d > 2.147483647E9D) || (l > 2147483647L)) {
      throw ScriptRuntime.rangeError("Invalid size or count value");
    }
    paramScriptable = new StringBuilder((int)l);
    paramScriptable.append(paramContext);
    int i = 1;
    int j = (int)d;
    while (i <= j / 2)
    {
      paramScriptable.append(paramScriptable);
      i *= 2;
    }
    if (i < j) {
      paramScriptable.append(paramScriptable.substring(0, (j - i) * paramContext.length()));
    }
    return paramScriptable.toString();
  }
  
  private static CharSequence js_slice(CharSequence paramCharSequence, Object[] paramArrayOfObject)
  {
    double d3 = 0.0D;
    double d2;
    int i;
    double d1;
    if (paramArrayOfObject.length < 1)
    {
      d2 = 0.0D;
      i = paramCharSequence.length();
      if (d2 >= 0.0D) {
        break label90;
      }
      d2 += i;
      d1 = d2;
      if (d2 < 0.0D) {
        d1 = 0.0D;
      }
    }
    for (;;)
    {
      if ((paramArrayOfObject.length >= 2) && (paramArrayOfObject[1] != Undefined.instance)) {
        break label109;
      }
      d3 = i;
      return paramCharSequence.subSequence((int)d1, (int)d3);
      d2 = ScriptRuntime.toInteger(paramArrayOfObject[0]);
      break;
      label90:
      d1 = d2;
      if (d2 > i) {
        d1 = i;
      }
    }
    label109:
    double d4 = ScriptRuntime.toInteger(paramArrayOfObject[1]);
    if (d4 < 0.0D)
    {
      d4 += i;
      d2 = d4;
      if (d4 >= 0.0D) {
        break label185;
      }
      d2 = d3;
    }
    label185:
    for (;;)
    {
      d3 = d2;
      if (d2 >= d1) {
        break;
      }
      d3 = d1;
      break;
      d2 = d4;
      if (d4 > i) {
        d2 = i;
      }
    }
  }
  
  private static CharSequence js_substr(CharSequence paramCharSequence, Object[] paramArrayOfObject)
  {
    double d2 = 0.0D;
    if (paramArrayOfObject.length < 1) {
      return paramCharSequence;
    }
    double d3 = ScriptRuntime.toInteger(paramArrayOfObject[0]);
    int i = paramCharSequence.length();
    double d1;
    if (d3 < 0.0D)
    {
      d3 += i;
      d1 = d3;
      if (d3 < 0.0D) {
        d1 = 0.0D;
      }
    }
    while (paramArrayOfObject.length == 1)
    {
      d2 = i;
      return paramCharSequence.subSequence((int)d1, (int)d2);
      d1 = d3;
      if (d3 > i) {
        d1 = i;
      }
    }
    d3 = ScriptRuntime.toInteger(paramArrayOfObject[1]);
    if (d3 < 0.0D) {}
    for (;;)
    {
      d3 = d2 + d1;
      d2 = d3;
      if (d3 <= i) {
        break;
      }
      d2 = i;
      break;
      d2 = d3;
    }
  }
  
  private static CharSequence js_substring(Context paramContext, CharSequence paramCharSequence, Object[] paramArrayOfObject)
  {
    double d2 = 0.0D;
    int i = paramCharSequence.length();
    double d3 = ScriptRuntime.toInteger(paramArrayOfObject, 0);
    double d1;
    if (d3 < 0.0D) {
      d1 = 0.0D;
    }
    while ((paramArrayOfObject.length <= 1) || (paramArrayOfObject[1] == Undefined.instance))
    {
      d3 = i;
      d2 = d1;
      return paramCharSequence.subSequence((int)d2, (int)d3);
      d1 = d3;
      if (d3 > i) {
        d1 = i;
      }
    }
    d3 = ScriptRuntime.toInteger(paramArrayOfObject[1]);
    if (d3 < 0.0D) {}
    for (;;)
    {
      if (d2 < d1)
      {
        d3 = d1;
        if (paramContext.getLanguageVersion() != 120) {
          break;
        }
        d2 = d1;
        d3 = d1;
        break;
        if (d3 <= i) {
          break label152;
        }
        d2 = i;
        continue;
      }
      d3 = d2;
      d2 = d1;
      break;
      label152:
      d2 = d3;
    }
  }
  
  private static NativeString realThis(Scriptable paramScriptable, IdFunctionObject paramIdFunctionObject)
  {
    if (!(paramScriptable instanceof NativeString)) {
      throw incompatibleCallError(paramIdFunctionObject);
    }
    return (NativeString)paramScriptable;
  }
  
  private static String tagify(Object paramObject, String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    paramObject = ScriptRuntime.toString(paramObject);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('<');
    localStringBuilder.append(paramString1);
    if (paramString2 != null)
    {
      localStringBuilder.append(' ');
      localStringBuilder.append(paramString2);
      localStringBuilder.append("=\"");
      localStringBuilder.append(ScriptRuntime.toString(paramArrayOfObject, 0));
      localStringBuilder.append('"');
    }
    localStringBuilder.append('>');
    localStringBuilder.append((String)paramObject);
    localStringBuilder.append("</");
    localStringBuilder.append(paramString1);
    localStringBuilder.append('>');
    return localStringBuilder.toString();
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(STRING_TAG)) {
      paramIdFunctionObject = super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    do
    {
      do
      {
        return paramIdFunctionObject;
        i = paramIdFunctionObject.methodId();
        switch (i)
        {
        case -29: 
        case -28: 
        case -27: 
        case -26: 
        case -25: 
        case -24: 
        case -23: 
        case -22: 
        case -21: 
        case -20: 
        case -19: 
        case -18: 
        case -17: 
        case -16: 
        case -4: 
        case -3: 
        case -2: 
        case 0: 
        default: 
          throw new IllegalArgumentException("String.prototype has no method: " + paramIdFunctionObject.getFunctionName());
        case -35: 
        case -34: 
        case -33: 
        case -32: 
        case -31: 
        case -30: 
        case -15: 
        case -14: 
        case -13: 
        case -12: 
        case -11: 
        case -10: 
        case -9: 
        case -8: 
        case -7: 
        case -6: 
        case -5: 
          if (paramArrayOfObject.length > 0)
          {
            paramScriptable2 = ScriptRuntime.toObject(paramContext, paramScriptable1, ScriptRuntime.toCharSequence(paramArrayOfObject[0]));
            Object[] arrayOfObject = new Object[paramArrayOfObject.length - 1];
            j = 0;
            while (j < arrayOfObject.length)
            {
              arrayOfObject[j] = paramArrayOfObject[(j + 1)];
              j += 1;
            }
            paramArrayOfObject = arrayOfObject;
          }
          for (;;)
          {
            i = -i;
            break;
            paramScriptable2 = ScriptRuntime.toObject(paramContext, paramScriptable1, ScriptRuntime.toCharSequence(paramScriptable2));
          }
        case -1: 
          j = paramArrayOfObject.length;
          if (j < 1) {
            return "";
          }
          paramIdFunctionObject = new StringBuilder(j);
          i = 0;
          while (i != j)
          {
            paramIdFunctionObject.append(ScriptRuntime.toUint16(paramArrayOfObject[i]));
            i += 1;
          }
          return paramIdFunctionObject.toString();
        case 1: 
          if (paramArrayOfObject.length >= 1) {}
          for (paramContext = ScriptRuntime.toCharSequence(paramArrayOfObject[0]); paramScriptable2 == null; paramContext = "") {
            return new NativeString(paramContext);
          }
          paramIdFunctionObject = paramContext;
        }
      } while ((paramContext instanceof String));
      return paramContext.toString();
      paramContext = realThis(paramScriptable2, paramIdFunctionObject).string;
      paramIdFunctionObject = paramContext;
    } while ((paramContext instanceof String));
    return paramContext.toString();
    paramIdFunctionObject = realThis(paramScriptable2, paramIdFunctionObject).string;
    return "(new String(\"" + ScriptRuntime.escapeString(paramIdFunctionObject.toString()) + "\"))";
    paramIdFunctionObject = ScriptRuntime.toCharSequence(paramScriptable2);
    double d = ScriptRuntime.toInteger(paramArrayOfObject, 0);
    if ((d < 0.0D) || (d >= paramIdFunctionObject.length()))
    {
      if (i == 5) {
        return "";
      }
      return ScriptRuntime.NaNobj;
    }
    char c = paramIdFunctionObject.charAt((int)d);
    if (i == 5) {
      return String.valueOf(c);
    }
    return ScriptRuntime.wrapInt(c);
    return ScriptRuntime.wrapInt(js_indexOf(7, ScriptRuntime.toString(paramScriptable2), paramArrayOfObject));
    paramContext = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(paramContext, paramScriptable2, paramIdFunctionObject));
    if ((paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof NativeRegExp))) {
      throw ScriptRuntime.typeError2("msg.first.arg.not.regexp", String.class.getSimpleName(), paramIdFunctionObject.getFunctionName());
    }
    int j = js_indexOf(i, paramContext, paramArrayOfObject);
    if (i == 40)
    {
      if (j != -1) {}
      for (bool = true;; bool = false) {
        return Boolean.valueOf(bool);
      }
    }
    if (i == 41)
    {
      if (j == 0) {}
      for (bool = true;; bool = false) {
        return Boolean.valueOf(bool);
      }
    }
    if (i == 42)
    {
      if (j != -1) {}
      for (bool = true;; bool = false) {
        return Boolean.valueOf(bool);
      }
    }
    return ScriptRuntime.wrapInt(js_lastIndexOf(ScriptRuntime.toString(paramScriptable2), paramArrayOfObject));
    return ScriptRuntime.checkRegExpProxy(paramContext).js_split(paramContext, paramScriptable1, ScriptRuntime.toString(paramScriptable2), paramArrayOfObject);
    return js_substring(paramContext, ScriptRuntime.toCharSequence(paramScriptable2), paramArrayOfObject);
    return ScriptRuntime.toString(paramScriptable2).toLowerCase(ScriptRuntime.ROOT_LOCALE);
    return ScriptRuntime.toString(paramScriptable2).toUpperCase(ScriptRuntime.ROOT_LOCALE);
    return js_substr(ScriptRuntime.toCharSequence(paramScriptable2), paramArrayOfObject);
    return js_concat(ScriptRuntime.toString(paramScriptable2), paramArrayOfObject);
    return js_slice(ScriptRuntime.toCharSequence(paramScriptable2), paramArrayOfObject);
    return tagify(paramScriptable2, "b", null, null);
    return tagify(paramScriptable2, "i", null, null);
    return tagify(paramScriptable2, "tt", null, null);
    return tagify(paramScriptable2, "strike", null, null);
    return tagify(paramScriptable2, "small", null, null);
    return tagify(paramScriptable2, "big", null, null);
    return tagify(paramScriptable2, "blink", null, null);
    return tagify(paramScriptable2, "sup", null, null);
    return tagify(paramScriptable2, "sub", null, null);
    return tagify(paramScriptable2, "font", "size", paramArrayOfObject);
    return tagify(paramScriptable2, "font", "color", paramArrayOfObject);
    return tagify(paramScriptable2, "a", "href", paramArrayOfObject);
    return tagify(paramScriptable2, "a", "name", paramArrayOfObject);
    paramIdFunctionObject = ScriptRuntime.toString(paramScriptable2);
    paramContext = ScriptRuntime.toString(paramArrayOfObject, 0);
    if (i == 29) {}
    for (boolean bool = paramIdFunctionObject.equals(paramContext);; bool = paramIdFunctionObject.equalsIgnoreCase(paramContext)) {
      return ScriptRuntime.wrapBoolean(bool);
    }
    if (i == 31) {
      i = 1;
    }
    for (;;)
    {
      return ScriptRuntime.checkRegExpProxy(paramContext).action(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject, i);
      if (i == 32) {
        i = 3;
      } else {
        i = 2;
      }
    }
    paramIdFunctionObject = Collator.getInstance(paramContext.getLocale());
    paramIdFunctionObject.setStrength(3);
    paramIdFunctionObject.setDecomposition(1);
    return ScriptRuntime.wrapNumber(paramIdFunctionObject.compare(ScriptRuntime.toString(paramScriptable2), ScriptRuntime.toString(paramArrayOfObject, 0)));
    return ScriptRuntime.toString(paramScriptable2).toLowerCase(paramContext.getLocale());
    return ScriptRuntime.toString(paramScriptable2).toUpperCase(paramContext.getLocale());
    paramIdFunctionObject = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(paramContext, paramScriptable2, paramIdFunctionObject));
    paramContext = paramIdFunctionObject.toCharArray();
    int i = 0;
    while ((i < paramContext.length) && (ScriptRuntime.isJSWhitespaceOrLineTerminator(paramContext[i]))) {
      i += 1;
    }
    j = paramContext.length;
    while ((j > i) && (ScriptRuntime.isJSWhitespaceOrLineTerminator(paramContext[(j - 1)]))) {
      j -= 1;
    }
    return paramIdFunctionObject.substring(i, j);
    paramIdFunctionObject = ScriptRuntime.toString(paramScriptable2);
    paramContext = paramIdFunctionObject.toCharArray();
    i = 0;
    while ((i < paramContext.length) && (ScriptRuntime.isJSWhitespaceOrLineTerminator(paramContext[i]))) {
      i += 1;
    }
    return paramIdFunctionObject.substring(i, paramContext.length);
    paramIdFunctionObject = ScriptRuntime.toString(paramScriptable2);
    paramContext = paramIdFunctionObject.toCharArray();
    i = paramContext.length;
    while ((i > 0) && (ScriptRuntime.isJSWhitespaceOrLineTerminator(paramContext[(i - 1)]))) {
      i -= 1;
    }
    return paramIdFunctionObject.substring(0, i);
    paramScriptable1 = ScriptRuntime.toString(paramArrayOfObject, 0);
    if (Normalizer.Form.NFD.name().equals(paramScriptable1)) {
      paramScriptable1 = Normalizer.Form.NFD;
    }
    for (;;)
    {
      return Normalizer.normalize(ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(paramContext, paramScriptable2, paramIdFunctionObject)), paramScriptable1);
      if (Normalizer.Form.NFKC.name().equals(paramScriptable1))
      {
        paramScriptable1 = Normalizer.Form.NFKC;
      }
      else if (Normalizer.Form.NFKD.name().equals(paramScriptable1))
      {
        paramScriptable1 = Normalizer.Form.NFKD;
      }
      else
      {
        if ((!Normalizer.Form.NFC.name().equals(paramScriptable1)) && (paramArrayOfObject.length != 0)) {
          break;
        }
        paramScriptable1 = Normalizer.Form.NFC;
      }
    }
    throw ScriptRuntime.rangeError("The normalization form should be one of NFC, NFD, NFKC, NFKD");
    return js_repeat(paramContext, paramScriptable2, paramIdFunctionObject, paramArrayOfObject);
    paramIdFunctionObject = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(paramContext, paramScriptable2, paramIdFunctionObject));
    d = ScriptRuntime.toInteger(paramArrayOfObject, 0);
    if ((d < 0.0D) || (d >= paramIdFunctionObject.length())) {
      return Undefined.instance;
    }
    return Integer.valueOf(paramIdFunctionObject.codePointAt((int)d));
    return new NativeStringIterator(paramScriptable1, paramScriptable2);
  }
  
  protected void fillConstructorProperties(IdFunctionObject paramIdFunctionObject)
  {
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -1, "fromCharCode", 1);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -5, "charAt", 2);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -6, "charCodeAt", 2);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -7, "indexOf", 2);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -8, "lastIndexOf", 2);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -9, "split", 3);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -10, "substring", 3);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -11, "toLowerCase", 1);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -12, "toUpperCase", 1);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -13, "substr", 3);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -14, "concat", 2);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -15, "slice", 3);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -30, "equalsIgnoreCase", 2);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -31, "match", 2);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -32, "search", 2);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -33, "replace", 2);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -34, "localeCompare", 2);
    addIdFunctionProperty(paramIdFunctionObject, STRING_TAG, -35, "toLocaleLowerCase", 1);
    super.fillConstructorProperties(paramIdFunctionObject);
  }
  
  protected int findInstanceIdInfo(String paramString)
  {
    if (paramString.equals("length")) {
      return instanceIdInfo(7, 1);
    }
    return super.findInstanceIdInfo(paramString);
  }
  
  protected int findPrototypeId(String paramString)
  {
    label80:
    String str;
    int i;
    switch (paramString.length())
    {
    case 12: 
    case 14: 
    case 15: 
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
      i = paramString.charAt(2);
      if (i == 98)
      {
        if ((paramString.charAt(0) != 's') || (paramString.charAt(1) != 'u')) {
          break;
        }
        return 24;
      }
      if (i == 103)
      {
        if ((paramString.charAt(0) != 'b') || (paramString.charAt(1) != 'i')) {
          break;
        }
        return 21;
      }
      if ((i != 112) || (paramString.charAt(0) != 's') || (paramString.charAt(1) != 'u')) {
        break;
      }
      return 23;
      i = paramString.charAt(0);
      if (i == 98)
      {
        i = 16;
        str = "bold";
      }
      else if (i == 108)
      {
        i = 27;
        str = "link";
      }
      else
      {
        if (i != 116) {
          break;
        }
        i = 37;
        str = "trim";
        continue;
        switch (paramString.charAt(4))
        {
        default: 
          str = null;
          i = 0;
          break;
        case 'd': 
          i = 18;
          str = "fixed";
          break;
        case 'e': 
          i = 15;
          str = "slice";
          break;
        case 'h': 
          i = 31;
          str = "match";
          break;
        case 'k': 
          i = 22;
          str = "blink";
          break;
        case 'l': 
          i = 20;
          str = "small";
          break;
        case 't': 
          i = 9;
          str = "split";
          continue;
          switch (paramString.charAt(1))
          {
          default: 
            str = null;
            i = 0;
            break;
          case 'e': 
            i = paramString.charAt(0);
            if (i == 114)
            {
              i = 44;
              str = "repeat";
            }
            else
            {
              if (i != 115) {
                break;
              }
              i = 32;
              str = "search";
            }
            break;
          case 'h': 
            i = 5;
            str = "charAt";
            break;
          case 'n': 
            i = 28;
            str = "anchor";
            break;
          case 'o': 
            i = 14;
            str = "concat";
            break;
          case 'q': 
            i = 29;
            str = "equals";
            break;
          case 't': 
            i = 19;
            str = "strike";
            break;
          case 'u': 
            i = 13;
            str = "substr";
            continue;
            switch (paramString.charAt(1))
            {
            default: 
              str = null;
              i = 0;
              break;
            case 'a': 
              i = 4;
              str = "valueOf";
              break;
            case 'e': 
              i = 33;
              str = "replace";
              break;
            case 'n': 
              i = 7;
              str = "indexOf";
              break;
            case 't': 
              i = 17;
              str = "italics";
              continue;
              switch (paramString.charAt(6))
              {
              default: 
                str = null;
                i = 0;
                break;
              case 'c': 
                i = 3;
                str = "toSource";
                break;
              case 'e': 
                i = 40;
                str = "includes";
                break;
              case 'f': 
                i = 38;
                str = "trimLeft";
                break;
              case 'n': 
                i = 2;
                str = "toString";
                break;
              case 't': 
                i = 42;
                str = "endsWith";
                break;
              case 'z': 
                i = 25;
                str = "fontsize";
                continue;
                switch (paramString.charAt(0))
                {
                default: 
                  str = null;
                  i = 0;
                  break;
                case 'f': 
                  i = 26;
                  str = "fontcolor";
                  break;
                case 'n': 
                  i = 43;
                  str = "normalize";
                  break;
                case 's': 
                  i = 10;
                  str = "substring";
                  break;
                case 't': 
                  i = 39;
                  str = "trimRight";
                  continue;
                  i = paramString.charAt(0);
                  if (i == 99)
                  {
                    i = 6;
                    str = "charCodeAt";
                  }
                  else if (i == 115)
                  {
                    i = 41;
                    str = "startsWith";
                  }
                  else
                  {
                    if (i != 64) {
                      break label80;
                    }
                    i = 46;
                    str = "@@iterator";
                    continue;
                    switch (paramString.charAt(2))
                    {
                    default: 
                      str = null;
                      i = 0;
                      break;
                    case 'L': 
                      i = 11;
                      str = "toLowerCase";
                      break;
                    case 'U': 
                      i = 12;
                      str = "toUpperCase";
                      break;
                    case 'd': 
                      i = 45;
                      str = "codePointAt";
                      break;
                    case 'n': 
                      str = "constructor";
                      i = 1;
                      break;
                    case 's': 
                      i = 8;
                      str = "lastIndexOf";
                      continue;
                      i = 34;
                      str = "localeCompare";
                      continue;
                      i = 30;
                      str = "equalsIgnoreCase";
                      continue;
                      i = paramString.charAt(8);
                      if (i == 76)
                      {
                        i = 35;
                        str = "toLocaleLowerCase";
                      }
                      else
                      {
                        if (i != 85) {
                          break label80;
                        }
                        i = 36;
                        str = "toLocaleUpperCase";
                      }
                      break;
                    }
                  }
                  break;
                }
                break;
              }
              break;
            }
            break;
          }
          break;
        }
      }
    }
    return i;
  }
  
  public Object get(int paramInt, Scriptable paramScriptable)
  {
    if ((paramInt >= 0) && (paramInt < this.string.length())) {
      return String.valueOf(this.string.charAt(paramInt));
    }
    return super.get(paramInt, paramScriptable);
  }
  
  public Object[] getAllIds()
  {
    Object localObject = Context.getCurrentContext();
    if ((localObject != null) && (((Context)localObject).getLanguageVersion() >= 200))
    {
      localObject = super.getAllIds();
      Object[] arrayOfObject = new Object[localObject.length + this.string.length()];
      int i = 0;
      while (i < this.string.length())
      {
        arrayOfObject[i] = Integer.valueOf(i);
        i += 1;
      }
      System.arraycopy(localObject, 0, arrayOfObject, i, localObject.length);
      return arrayOfObject;
    }
    return super.getAllIds();
  }
  
  public String getClassName()
  {
    return "String";
  }
  
  protected String getInstanceIdName(int paramInt)
  {
    if (paramInt == 1) {
      return "length";
    }
    return super.getInstanceIdName(paramInt);
  }
  
  protected Object getInstanceIdValue(int paramInt)
  {
    if (paramInt == 1) {
      return ScriptRuntime.wrapInt(this.string.length());
    }
    return super.getInstanceIdValue(paramInt);
  }
  
  int getLength()
  {
    return this.string.length();
  }
  
  protected int getMaxInstanceId()
  {
    return 1;
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i = 1;
    String str2 = null;
    String str1;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      str1 = "constructor";
    }
    for (;;)
    {
      initPrototypeMethod(STRING_TAG, paramInt, str1, str2, i);
      return;
      str1 = "toString";
      i = 0;
      continue;
      str1 = "toSource";
      i = 0;
      continue;
      str1 = "valueOf";
      i = 0;
      continue;
      str1 = "charAt";
      continue;
      str1 = "charCodeAt";
      continue;
      str1 = "indexOf";
      continue;
      str1 = "lastIndexOf";
      continue;
      str1 = "split";
      i = 2;
      continue;
      str1 = "substring";
      i = 2;
      continue;
      str1 = "toLowerCase";
      i = 0;
      continue;
      str1 = "toUpperCase";
      i = 0;
      continue;
      str1 = "substr";
      i = 2;
      continue;
      str1 = "concat";
      continue;
      str1 = "slice";
      i = 2;
      continue;
      str1 = "bold";
      i = 0;
      continue;
      str1 = "italics";
      i = 0;
      continue;
      str1 = "fixed";
      i = 0;
      continue;
      str1 = "strike";
      i = 0;
      continue;
      str1 = "small";
      i = 0;
      continue;
      str1 = "big";
      i = 0;
      continue;
      str1 = "blink";
      i = 0;
      continue;
      str1 = "sup";
      i = 0;
      continue;
      str1 = "sub";
      i = 0;
      continue;
      str1 = "fontsize";
      i = 0;
      continue;
      str1 = "fontcolor";
      i = 0;
      continue;
      str1 = "link";
      i = 0;
      continue;
      str1 = "anchor";
      i = 0;
      continue;
      str1 = "equals";
      continue;
      str1 = "equalsIgnoreCase";
      continue;
      str1 = "match";
      continue;
      str1 = "search";
      continue;
      str1 = "replace";
      i = 2;
      continue;
      str1 = "localeCompare";
      continue;
      str1 = "toLocaleLowerCase";
      i = 0;
      continue;
      str1 = "toLocaleUpperCase";
      i = 0;
      continue;
      str1 = "trim";
      i = 0;
      continue;
      str1 = "trimLeft";
      i = 0;
      continue;
      str1 = "trimRight";
      i = 0;
      continue;
      str1 = "includes";
      continue;
      str1 = "startsWith";
      continue;
      str1 = "endsWith";
      continue;
      str1 = "normalize";
      i = 0;
      continue;
      str1 = "repeat";
      continue;
      str1 = "codePointAt";
      continue;
      str1 = "@@iterator";
      str2 = "[Symbol.iterator]";
      i = 0;
    }
  }
  
  public void put(int paramInt, Scriptable paramScriptable, Object paramObject)
  {
    if ((paramInt >= 0) && (paramInt < this.string.length())) {
      return;
    }
    super.put(paramInt, paramScriptable, paramObject);
  }
  
  public CharSequence toCharSequence()
  {
    return this.string;
  }
  
  public String toString()
  {
    if ((this.string instanceof String)) {
      return (String)this.string;
    }
    return this.string.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */