package org.mozilla.javascript;

import java.io.Serializable;
import org.mozilla.javascript.xml.XMLLib;

public class NativeGlobal
  implements Serializable, IdFunctionCall
{
  private static final Object FTAG = "Global";
  private static final int INVALID_UTF8 = Integer.MAX_VALUE;
  private static final int Id_decodeURI = 1;
  private static final int Id_decodeURIComponent = 2;
  private static final int Id_encodeURI = 3;
  private static final int Id_encodeURIComponent = 4;
  private static final int Id_escape = 5;
  private static final int Id_eval = 6;
  private static final int Id_isFinite = 7;
  private static final int Id_isNaN = 8;
  private static final int Id_isXMLName = 9;
  private static final int Id_new_CommonError = 14;
  private static final int Id_parseFloat = 10;
  private static final int Id_parseInt = 11;
  private static final int Id_unescape = 12;
  private static final int Id_uneval = 13;
  private static final int LAST_SCOPE_FUNCTION_ID = 13;
  private static final String URI_DECODE_RESERVED = ";/?:@&=+$,#";
  static final long serialVersionUID = 6080442165748707530L;
  
  @Deprecated
  public static EcmaError constructError(Context paramContext, String paramString1, String paramString2, Scriptable paramScriptable)
  {
    return ScriptRuntime.constructError(paramString1, paramString2);
  }
  
  @Deprecated
  public static EcmaError constructError(Context paramContext, String paramString1, String paramString2, Scriptable paramScriptable, String paramString3, int paramInt1, int paramInt2, String paramString4)
  {
    return ScriptRuntime.constructError(paramString1, paramString2, paramString3, paramInt1, paramString4, paramInt2);
  }
  
  private static String decode(String paramString, boolean paramBoolean)
  {
    Object localObject1 = null;
    int i6 = paramString.length();
    int i1 = 0;
    int k = 0;
    int i;
    int m;
    if (i1 != i6)
    {
      i = paramString.charAt(i1);
      if (i != 37)
      {
        if (localObject1 == null) {
          break label669;
        }
        m = k + 1;
        localObject1[k] = i;
      }
    }
    for (;;)
    {
      int n = i1 + 1;
      Object localObject2 = localObject1;
      k = m;
      localObject1 = localObject2;
      i1 = n;
      break;
      if (localObject1 == null)
      {
        localObject1 = new char[i6];
        paramString.getChars(0, i1, (char[])localObject1, 0);
      }
      for (int i2 = i1;; i2 = k)
      {
        if (i1 + 3 > i6) {
          throw uriError();
        }
        k = unHex(paramString.charAt(i1 + 1), paramString.charAt(i1 + 2));
        if (k < 0) {
          throw uriError();
        }
        int i3 = i1 + 3;
        if ((k & 0x80) == 0)
        {
          i = (char)k;
          k = i3;
        }
        for (;;)
        {
          if ((!paramBoolean) || (";/?:@&=+$,#".indexOf(i) < 0)) {
            break label619;
          }
          m = i2;
          i2 = i1;
          i1 = m;
          for (;;)
          {
            n = k;
            m = i1;
            localObject2 = localObject1;
            if (i2 == k) {
              break;
            }
            localObject1[i1] = paramString.charAt(i2);
            i2 += 1;
            i1 += 1;
          }
          if ((k & 0xC0) == 128) {
            throw uriError();
          }
          if ((k & 0x20) == 0)
          {
            k &= 0x1F;
            m = 128;
            n = 1;
          }
          while (n * 3 + i3 > i6)
          {
            throw uriError();
            if ((k & 0x10) == 0)
            {
              k &= 0xF;
              m = 2048;
              n = 2;
            }
            else if ((k & 0x8) == 0)
            {
              k &= 0x7;
              m = 65536;
              n = 3;
            }
            else if ((k & 0x4) == 0)
            {
              k &= 0x3;
              m = 2097152;
              n = 4;
            }
            else if ((k & 0x2) == 0)
            {
              k &= 0x1;
              m = 67108864;
              n = 5;
            }
            else
            {
              throw uriError();
            }
          }
          int i5 = k;
          int i4 = 0;
          k = i3;
          for (i3 = i5; i4 != n; i3 = i5 & 0x3F | i3 << 6)
          {
            if (paramString.charAt(k) != '%') {
              throw uriError();
            }
            i5 = unHex(paramString.charAt(k + 1), paramString.charAt(k + 2));
            if ((i5 < 0) || ((i5 & 0xC0) != 128)) {
              throw uriError();
            }
            k += 3;
            i4 += 1;
          }
          if ((i3 < m) || ((i3 >= 55296) && (i3 <= 57343))) {
            m = Integer.MAX_VALUE;
          }
          for (;;)
          {
            if (m >= 65536)
            {
              m -= 65536;
              if (m > 1048575)
              {
                throw uriError();
                if (i3 != 65534)
                {
                  m = i3;
                  if (i3 != 65535) {}
                }
                else
                {
                  m = 65533;
                }
              }
              else
              {
                int j = (char)((m >>> 10) + 55296);
                i = (char)((m & 0x3FF) + 56320);
                localObject1[i2] = j;
                i2 += 1;
                break;
              }
            }
          }
          i = (char)m;
        }
        label619:
        m = i2 + 1;
        localObject1[i2] = i;
        n = k;
        localObject2 = localObject1;
        break;
        if (localObject1 == null) {
          return paramString;
        }
        return new String((char[])localObject1, 0, k);
      }
      label669:
      m = k;
    }
  }
  
  private static String encode(String paramString, boolean paramBoolean)
  {
    Object localObject1 = null;
    int n = paramString.length();
    int j = 0;
    Object localObject2 = null;
    int i;
    int m;
    Object localObject5;
    Object localObject3;
    if (j != n)
    {
      i = paramString.charAt(j);
      if (encodeUnescaped(i, paramBoolean))
      {
        m = j;
        localObject5 = localObject1;
        localObject3 = localObject2;
        if (localObject1 == null) {
          break label298;
        }
        ((StringBuilder)localObject1).append(i);
      }
    }
    for (;;)
    {
      j += 1;
      break;
      Object localObject4 = localObject1;
      if (localObject1 == null)
      {
        localObject4 = new StringBuilder(n + 3);
        ((StringBuilder)localObject4).append(paramString);
        ((StringBuilder)localObject4).setLength(j);
        localObject2 = new byte[6];
      }
      if ((56320 <= i) && (i <= 57343)) {
        throw uriError();
      }
      m = i;
      int k = j;
      if (i >= 55296)
      {
        if (56319 >= i) {
          break label226;
        }
        k = j;
      }
      for (m = i;; m = (i - 55296 << 10) + (j - 56320) + 65536)
      {
        int i1 = oneUcs4ToUtf8Char((byte[])localObject2, m);
        j = 0;
        for (;;)
        {
          m = k;
          localObject5 = localObject4;
          localObject3 = localObject2;
          if (j >= i1) {
            break;
          }
          m = localObject2[j] & 0xFF;
          ((StringBuilder)localObject4).append('%');
          ((StringBuilder)localObject4).append(toHexChar(m >>> 4));
          ((StringBuilder)localObject4).append(toHexChar(m & 0xF));
          j += 1;
        }
        label226:
        k = j + 1;
        if (k == n) {
          throw uriError();
        }
        j = paramString.charAt(k);
        if ((56320 > j) || (j > 57343)) {
          throw uriError();
        }
      }
      if (localObject1 == null) {
        return paramString;
      }
      return ((StringBuilder)localObject1).toString();
      label298:
      localObject1 = localObject5;
      localObject2 = localObject3;
      j = m;
    }
  }
  
  private static boolean encodeUnescaped(char paramChar, boolean paramBoolean)
  {
    if ((('A' <= paramChar) && (paramChar <= 'Z')) || (('a' <= paramChar) && (paramChar <= 'z')) || (('0' <= paramChar) && (paramChar <= '9'))) {}
    do
    {
      do
      {
        return true;
      } while ("-_.!~*'()".indexOf(paramChar) >= 0);
      if (!paramBoolean) {
        break;
      }
    } while (";/?:@&=+$,#".indexOf(paramChar) >= 0);
    return false;
    return false;
  }
  
  public static void init(Context paramContext, Scriptable paramScriptable, boolean paramBoolean)
  {
    NativeGlobal localNativeGlobal = new NativeGlobal();
    int j = 1;
    if (j <= 13)
    {
      switch (j)
      {
      default: 
        throw Kit.codeBug();
      case 1: 
        localObject1 = "decodeURI";
        i = 1;
      }
      for (;;)
      {
        localObject1 = new IdFunctionObject(localNativeGlobal, FTAG, j, (String)localObject1, i, paramScriptable);
        if (paramBoolean) {
          ((IdFunctionObject)localObject1).sealObject();
        }
        ((IdFunctionObject)localObject1).exportAsScopeProperty();
        j += 1;
        break;
        localObject1 = "decodeURIComponent";
        i = 1;
        continue;
        localObject1 = "encodeURI";
        i = 1;
        continue;
        localObject1 = "encodeURIComponent";
        i = 1;
        continue;
        localObject1 = "escape";
        i = 1;
        continue;
        localObject1 = "eval";
        i = 1;
        continue;
        localObject1 = "isFinite";
        i = 1;
        continue;
        localObject1 = "isNaN";
        i = 1;
        continue;
        localObject1 = "isXMLName";
        i = 1;
        continue;
        localObject1 = "parseFloat";
        i = 1;
        continue;
        localObject1 = "parseInt";
        i = 2;
        continue;
        localObject1 = "unescape";
        i = 1;
        continue;
        localObject1 = "uneval";
        i = 1;
      }
    }
    ScriptableObject.defineProperty(paramScriptable, "NaN", ScriptRuntime.NaNobj, 7);
    ScriptableObject.defineProperty(paramScriptable, "Infinity", ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY), 7);
    ScriptableObject.defineProperty(paramScriptable, "undefined", Undefined.instance, 7);
    Object localObject1 = TopLevel.NativeErrors.values();
    j = localObject1.length;
    int i = 0;
    if (i < j)
    {
      ScriptableObject localScriptableObject = localObject1[i];
      if (localScriptableObject == TopLevel.NativeErrors.Error) {}
      for (;;)
      {
        i += 1;
        break;
        Object localObject2 = localScriptableObject.name();
        localScriptableObject = (ScriptableObject)ScriptRuntime.newBuiltinObject(paramContext, paramScriptable, TopLevel.Builtins.Error, ScriptRuntime.emptyArgs);
        localScriptableObject.put("name", localScriptableObject, localObject2);
        localScriptableObject.put("message", localScriptableObject, "");
        localObject2 = new IdFunctionObject(localNativeGlobal, FTAG, 14, (String)localObject2, 1, paramScriptable);
        ((IdFunctionObject)localObject2).markAsConstructor(localScriptableObject);
        localScriptableObject.put("constructor", localScriptableObject, localObject2);
        localScriptableObject.setAttributes("constructor", 2);
        if (paramBoolean)
        {
          localScriptableObject.sealObject();
          ((IdFunctionObject)localObject2).sealObject();
        }
        ((IdFunctionObject)localObject2).exportAsScopeProperty();
      }
    }
  }
  
  static boolean isEvalFunction(Object paramObject)
  {
    if ((paramObject instanceof IdFunctionObject))
    {
      paramObject = (IdFunctionObject)paramObject;
      if ((((IdFunctionObject)paramObject).hasTag(FTAG)) && (((IdFunctionObject)paramObject).methodId() == 6)) {
        return true;
      }
    }
    return false;
  }
  
  private Object js_escape(Object[] paramArrayOfObject)
  {
    String str = ScriptRuntime.toString(paramArrayOfObject, 0);
    int i = 7;
    if (paramArrayOfObject.length > 1)
    {
      double d = ScriptRuntime.toNumber(paramArrayOfObject[1]);
      if (d == d)
      {
        j = (int)d;
        if (j == d)
        {
          i = j;
          if ((j & 0xFFFFFFF8) == 0) {
            break label61;
          }
        }
      }
      throw Context.reportRuntimeError0("msg.bad.esc.mask");
    }
    label61:
    int n = str.length();
    int j = 0;
    paramArrayOfObject = null;
    int i1;
    Object[] arrayOfObject;
    if (j != n)
    {
      i1 = str.charAt(j);
      if ((i != 0) && (((i1 >= 48) && (i1 <= 57)) || ((i1 >= 65) && (i1 <= 90)) || ((i1 >= 97) && (i1 <= 122)) || (i1 == 64) || (i1 == 42) || (i1 == 95) || (i1 == 45) || (i1 == 46) || (((i & 0x4) != 0) && ((i1 == 47) || (i1 == 43)))))
      {
        arrayOfObject = paramArrayOfObject;
        if (paramArrayOfObject != null)
        {
          paramArrayOfObject.append((char)i1);
          arrayOfObject = paramArrayOfObject;
        }
      }
    }
    for (;;)
    {
      j += 1;
      paramArrayOfObject = arrayOfObject;
      break;
      if (paramArrayOfObject == null)
      {
        paramArrayOfObject = new StringBuilder(n + 3);
        paramArrayOfObject.append(str);
        paramArrayOfObject.setLength(j);
      }
      for (;;)
      {
        int k;
        label303:
        int m;
        if (i1 < 256)
        {
          if ((i1 == 32) && (i == 2))
          {
            paramArrayOfObject.append('+');
            arrayOfObject = paramArrayOfObject;
            break;
          }
          paramArrayOfObject.append('%');
          k = 2;
          k = (k - 1) * 4;
          if (k < 0) {
            break label394;
          }
          m = i1 >> k & 0xF;
          if (m >= 10) {
            break label369;
          }
          m += 48;
        }
        for (;;)
        {
          paramArrayOfObject.append((char)m);
          k -= 4;
          break label303;
          paramArrayOfObject.append('%');
          paramArrayOfObject.append('u');
          k = 4;
          break;
          label369:
          m += 55;
        }
        if (paramArrayOfObject == null) {
          return str;
        }
        return paramArrayOfObject.toString();
      }
      label394:
      arrayOfObject = paramArrayOfObject;
    }
  }
  
  private Object js_eval(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    paramScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    return ScriptRuntime.evalSpecial(paramContext, paramScriptable, paramScriptable, paramArrayOfObject, "eval code", 1);
  }
  
  static Object js_parseFloat(Object[] paramArrayOfObject)
  {
    int m = 0;
    if (paramArrayOfObject.length < 1) {
      return ScriptRuntime.NaNobj;
    }
    paramArrayOfObject = ScriptRuntime.toString(paramArrayOfObject[0]);
    int i4 = paramArrayOfObject.length();
    int j = 0;
    int i;
    for (;;)
    {
      if (j == i4) {
        return ScriptRuntime.NaNobj;
      }
      k = paramArrayOfObject.charAt(j);
      if (!ScriptRuntime.isStrWhiteSpaceChar(k))
      {
        if ((k != 43) && (k != 45)) {
          break label492;
        }
        i = j + 1;
        if (i != i4) {
          break;
        }
        return ScriptRuntime.NaNobj;
      }
      j += 1;
    }
    int k = paramArrayOfObject.charAt(i);
    for (;;)
    {
      if (k == 73)
      {
        if ((i + 8 <= i4) && (paramArrayOfObject.regionMatches(i, "Infinity", 0, 8)))
        {
          if (paramArrayOfObject.charAt(j) == '-') {}
          for (double d = Double.NEGATIVE_INFINITY;; d = Double.POSITIVE_INFINITY) {
            return ScriptRuntime.wrapNumber(d);
          }
        }
        return ScriptRuntime.NaNobj;
      }
      k = -1;
      int n = -1;
      if (i < i4) {
        switch (paramArrayOfObject.charAt(i))
        {
        }
      }
      for (;;)
      {
        label308:
        if ((k != -1) && (m == 0)) {}
        for (;;)
        {
          paramArrayOfObject = paramArrayOfObject.substring(j, k);
          try
          {
            paramArrayOfObject = Double.valueOf(paramArrayOfObject);
            return paramArrayOfObject;
          }
          catch (NumberFormatException paramArrayOfObject)
          {
            int i3;
            int i2;
            int i1;
            return ScriptRuntime.NaNobj;
          }
          if (n != -1) {
            break label308;
          }
          i3 = i;
          i2 = k;
          i1 = m;
          for (;;)
          {
            i += 1;
            m = i1;
            k = i2;
            n = i3;
            break;
            if ((k != -1) || (i == i4 - 1)) {
              break label308;
            }
            i2 = i;
            i1 = m;
            i3 = n;
            continue;
            if (k != i - 1) {
              break label308;
            }
            i1 = m;
            i2 = k;
            i3 = n;
            if (i == i4 - 1)
            {
              i -= 1;
              break label308;
              i1 = m;
              i2 = k;
              i3 = n;
              if (k != -1)
              {
                i1 = 1;
                i2 = k;
                i3 = n;
              }
            }
          }
          k = i;
        }
      }
      label492:
      i = j;
    }
  }
  
  static Object js_parseInt(Object[] paramArrayOfObject)
  {
    int n = 0;
    int k = 0;
    String str = ScriptRuntime.toString(paramArrayOfObject, 0);
    int m = ScriptRuntime.toInt32(paramArrayOfObject, 1);
    int i1 = str.length();
    if (i1 == 0) {
      return ScriptRuntime.NaNobj;
    }
    int j = 0;
    int i2 = str.charAt(j);
    label56:
    int i;
    if (!ScriptRuntime.isStrWhiteSpaceChar(i2))
    {
      i = n;
      if (i2 != 43)
      {
        i = k;
        if (i2 == 45) {
          i = 1;
        }
        if (i == 0) {
          break label376;
        }
      }
      k = j + 1;
    }
    for (n = i;; n = i)
    {
      if (m == 0) {
        j = -1;
      }
      for (;;)
      {
        label104:
        m = k;
        i = j;
        if (j == -1)
        {
          j = 10;
          m = k;
          i = j;
          if (i1 - k > 1)
          {
            m = k;
            i = j;
            if (str.charAt(k) == '0')
            {
              i1 = str.charAt(k + 1);
              if ((i1 != 120) && (i1 != 88)) {
                break label326;
              }
              m = k + 2;
              i = 16;
            }
          }
        }
        for (;;)
        {
          double d2 = ScriptRuntime.stringToNumber(str, m, i);
          double d1 = d2;
          if (n != 0) {
            d1 = -d2;
          }
          return ScriptRuntime.wrapNumber(d1);
          i = j + 1;
          j = i;
          if (i < i1) {
            break;
          }
          j = i;
          break label56;
          if ((m < 2) || (m > 36)) {
            return ScriptRuntime.NaNobj;
          }
          if ((m != 16) || (i1 - k <= 1) || (str.charAt(k) != '0')) {
            break label369;
          }
          i = str.charAt(k + 1);
          if ((i != 120) && (i != 88)) {
            break label369;
          }
          k += 2;
          j = m;
          break label104;
          label326:
          m = k;
          i = j;
          if (48 <= i1)
          {
            m = k;
            i = j;
            if (i1 <= 57)
            {
              i = 8;
              m = k + 1;
            }
          }
        }
        label369:
        j = m;
      }
      label376:
      k = j;
    }
  }
  
  private Object js_unescape(Object[] paramArrayOfObject)
  {
    String str = ScriptRuntime.toString(paramArrayOfObject, 0);
    int j = str.indexOf('%');
    paramArrayOfObject = str;
    int m;
    int i;
    int n;
    if (j >= 0)
    {
      int i2 = str.length();
      paramArrayOfObject = str.toCharArray();
      m = j;
      if (j != i2)
      {
        i = paramArrayOfObject[j];
        n = j + 1;
        if ((i != 37) || (n == i2)) {
          break label170;
        }
        int k;
        if (paramArrayOfObject[n] == 'u')
        {
          k = n + 1;
          j = n + 5;
        }
        while (j <= i2)
        {
          int i1 = 0;
          for (;;)
          {
            if (k != j)
            {
              i1 = Kit.xDigitToInt(paramArrayOfObject[k], i1);
              k += 1;
              continue;
              j = n + 2;
              k = n;
              break;
            }
          }
          if (i1 >= 0) {
            i = (char)i1;
          }
        }
      }
    }
    for (;;)
    {
      paramArrayOfObject[m] = i;
      m += 1;
      break;
      paramArrayOfObject = new String(paramArrayOfObject, 0, m);
      return paramArrayOfObject;
      label170:
      j = n;
    }
  }
  
  private static int oneUcs4ToUtf8Char(byte[] paramArrayOfByte, int paramInt)
  {
    if ((paramInt & 0xFFFFFF80) == 0)
    {
      paramArrayOfByte[0] = ((byte)paramInt);
      return 1;
    }
    int i = 2;
    int j = paramInt >>> 11;
    while (j != 0)
    {
      j >>>= 5;
      i += 1;
    }
    j = i;
    for (;;)
    {
      j -= 1;
      if (j <= 0) {
        break;
      }
      paramArrayOfByte[j] = ((byte)(paramInt & 0x3F | 0x80));
      paramInt >>>= 6;
    }
    paramArrayOfByte[0] = ((byte)(256 - (1 << 8 - i) + paramInt));
    return i;
  }
  
  private static char toHexChar(int paramInt)
  {
    if (paramInt >> 4 != 0) {
      Kit.codeBug();
    }
    if (paramInt < 10) {
      paramInt += 48;
    }
    for (;;)
    {
      return (char)paramInt;
      paramInt = paramInt - 10 + 65;
    }
  }
  
  private static int unHex(char paramChar)
  {
    if (('A' <= paramChar) && (paramChar <= 'F')) {
      return paramChar - 'A' + 10;
    }
    if (('a' <= paramChar) && (paramChar <= 'f')) {
      return paramChar - 'a' + 10;
    }
    if (('0' <= paramChar) && (paramChar <= '9')) {
      return paramChar - '0';
    }
    return -1;
  }
  
  private static int unHex(char paramChar1, char paramChar2)
  {
    int i = unHex(paramChar1);
    int j = unHex(paramChar2);
    if ((i >= 0) && (j >= 0)) {
      return i << 4 | j;
    }
    return -1;
  }
  
  private static EcmaError uriError()
  {
    return ScriptRuntime.constructError("URIError", ScriptRuntime.getMessage0("msg.bad.uri"));
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    boolean bool2 = true;
    boolean bool3 = true;
    boolean bool1 = true;
    int i;
    if (paramIdFunctionObject.hasTag(FTAG)) {
      i = paramIdFunctionObject.methodId();
    }
    switch (i)
    {
    default: 
      throw paramIdFunctionObject.unknown();
    case 1: 
    case 2: 
      paramIdFunctionObject = ScriptRuntime.toString(paramArrayOfObject, 0);
      if (i == 1) {}
      for (;;)
      {
        return decode(paramIdFunctionObject, bool1);
        bool1 = false;
      }
    case 3: 
    case 4: 
      paramIdFunctionObject = ScriptRuntime.toString(paramArrayOfObject, 0);
      if (i == 3) {}
      for (bool1 = bool2;; bool1 = false) {
        return encode(paramIdFunctionObject, bool1);
      }
    case 5: 
      return js_escape(paramArrayOfObject);
    case 6: 
      return js_eval(paramContext, paramScriptable1, paramArrayOfObject);
    case 7: 
      if (paramArrayOfObject.length < 1) {
        return Boolean.FALSE;
      }
      return NativeNumber.isFinite(paramArrayOfObject[0]);
    case 8: 
      if (paramArrayOfObject.length < 1) {
        bool1 = bool3;
      }
      for (;;)
      {
        return ScriptRuntime.wrapBoolean(bool1);
        double d = ScriptRuntime.toNumber(paramArrayOfObject[0]);
        bool1 = bool3;
        if (d == d) {
          bool1 = false;
        }
      }
    case 9: 
      if (paramArrayOfObject.length == 0) {}
      for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[0]) {
        return ScriptRuntime.wrapBoolean(XMLLib.extractFromScope(paramScriptable1).isXMLName(paramContext, paramIdFunctionObject));
      }
    case 10: 
      return js_parseFloat(paramArrayOfObject);
    case 11: 
      return js_parseInt(paramArrayOfObject);
    case 12: 
      return js_unescape(paramArrayOfObject);
    case 13: 
      if (paramArrayOfObject.length != 0) {}
      for (paramIdFunctionObject = paramArrayOfObject[0];; paramIdFunctionObject = Undefined.instance) {
        return ScriptRuntime.uneval(paramContext, paramScriptable1, paramIdFunctionObject);
      }
    }
    return NativeError.make(paramContext, paramScriptable1, paramIdFunctionObject, paramArrayOfObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeGlobal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */