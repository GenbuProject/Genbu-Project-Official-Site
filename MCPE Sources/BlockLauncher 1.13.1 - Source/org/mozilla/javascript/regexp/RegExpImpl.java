package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.RegExpProxy;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

public class RegExpImpl
  implements RegExpProxy
{
  protected String input;
  protected SubString lastMatch;
  protected SubString lastParen;
  protected SubString leftContext;
  protected boolean multiline;
  protected SubString[] parens;
  protected SubString rightContext;
  
  private static NativeRegExp createRegExp(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject, int paramInt, boolean paramBoolean)
  {
    Scriptable localScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    if ((paramArrayOfObject.length == 0) || (paramArrayOfObject[0] == Undefined.instance)) {
      return new NativeRegExp(localScriptable, NativeRegExp.compileRE(paramContext, "", "", false));
    }
    if ((paramArrayOfObject[0] instanceof NativeRegExp)) {
      return (NativeRegExp)paramArrayOfObject[0];
    }
    String str = ScriptRuntime.toString(paramArrayOfObject[0]);
    if (paramInt < paramArrayOfObject.length) {
      paramArrayOfObject[0] = str;
    }
    for (paramScriptable = ScriptRuntime.toString(paramArrayOfObject[paramInt]);; paramScriptable = null) {
      return new NativeRegExp(localScriptable, NativeRegExp.compileRE(paramContext, str, paramScriptable, paramBoolean));
    }
  }
  
  private static void do_replace(GlobData paramGlobData, Context paramContext, RegExpImpl paramRegExpImpl)
  {
    StringBuilder localStringBuilder = paramGlobData.charBuf;
    String str = paramGlobData.repstr;
    int j = paramGlobData.dollar;
    int i;
    if (j != -1)
    {
      paramGlobData = new int[1];
      int k = 0;
      int m;
      do
      {
        localStringBuilder.append(str.substring(k, j));
        SubString localSubString = interpretDollar(paramContext, paramRegExpImpl, str, j, paramGlobData);
        if (localSubString == null) {
          break;
        }
        i = localSubString.length;
        if (i > 0) {
          localStringBuilder.append(localSubString.str, localSubString.index, localSubString.index + i);
        }
        i = paramGlobData[0] + j;
        j += paramGlobData[0];
        m = str.indexOf('$', j);
        j = m;
        k = i;
      } while (m >= 0);
    }
    for (;;)
    {
      j = str.length();
      if (j > i) {
        localStringBuilder.append(str.substring(i, j));
      }
      return;
      i = j;
      j += 1;
      break;
      i = 0;
    }
  }
  
  private static int find_split(Context paramContext, Scriptable paramScriptable1, String paramString1, String paramString2, int paramInt, RegExpProxy paramRegExpProxy, Scriptable paramScriptable2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean, String[][] paramArrayOfString)
  {
    int j = paramArrayOfInt1[0];
    int k = paramString1.length();
    int i;
    if ((paramInt == 120) && (paramScriptable2 == null) && (paramString2.length() == 1) && (paramString2.charAt(0) == ' '))
    {
      i = j;
      if (j == 0)
      {
        i = j;
        while ((i < k) && (Character.isWhitespace(paramString1.charAt(i)))) {
          i += 1;
        }
        paramArrayOfInt1[0] = i;
      }
      paramInt = i;
      if (i == k) {
        paramInt = -1;
      }
    }
    do
    {
      return paramInt;
      while ((paramInt < k) && (!Character.isWhitespace(paramString1.charAt(paramInt)))) {
        paramInt += 1;
      }
      i = paramInt;
      while ((i < k) && (Character.isWhitespace(paramString1.charAt(i)))) {
        i += 1;
      }
      paramArrayOfInt2[0] = (i - paramInt);
      return paramInt;
      if (j > k) {
        return -1;
      }
      if (paramScriptable2 != null) {
        return paramRegExpProxy.find_split(paramContext, paramScriptable1, paramString1, paramString2, paramScriptable2, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfBoolean, paramArrayOfString);
      }
      if ((paramInt != 0) && (paramInt < 130) && (k == 0)) {
        return -1;
      }
      if (paramString2.length() == 0)
      {
        if (paramInt == 120)
        {
          if (j == k)
          {
            paramArrayOfInt2[0] = 1;
            return j;
          }
          return j + 1;
        }
        if (j == k) {
          return -1;
        }
        return j + 1;
      }
      if (paramArrayOfInt1[0] >= k) {
        return k;
      }
      i = paramString1.indexOf(paramString2, paramArrayOfInt1[0]);
      paramInt = i;
    } while (i != -1);
    return k;
  }
  
  private static SubString interpretDollar(Context paramContext, RegExpImpl paramRegExpImpl, String paramString, int paramInt, int[] paramArrayOfInt)
  {
    if (paramString.charAt(paramInt) != '$') {
      Kit.codeBug();
    }
    int i = paramContext.getLanguageVersion();
    if ((i != 0) && (i <= 140) && (paramInt > 0) && (paramString.charAt(paramInt - 1) == '\\')) {
      return null;
    }
    int n = paramString.length();
    if (paramInt + 1 >= n) {
      return null;
    }
    char c = paramString.charAt(paramInt + 1);
    int j;
    int k;
    int m;
    if (NativeRegExp.isDigit(c))
    {
      if ((i != 0) && (i <= 140))
      {
        if (c == '0') {
          return null;
        }
        j = paramInt;
        for (i = 0;; i = k)
        {
          j += 1;
          k = j;
          m = i;
          if (j < n)
          {
            c = paramString.charAt(j);
            k = j;
            m = i;
            if (NativeRegExp.isDigit(c))
            {
              k = c - '0' + i * 10;
              if (k >= i) {
                continue;
              }
              m = i;
              k = j;
            }
          }
          paramArrayOfInt[0] = (k - paramInt);
          return paramRegExpImpl.getParenSubString(m - 1);
        }
      }
      if (paramRegExpImpl.parens == null) {}
      for (i = 0;; i = paramRegExpImpl.parens.length)
      {
        k = c - '0';
        if (k <= i) {
          break;
        }
        return null;
      }
      j = paramInt + 2;
      if (paramInt + 2 >= n) {
        break label437;
      }
      c = paramString.charAt(paramInt + 2);
      if (!NativeRegExp.isDigit(c)) {
        break label437;
      }
      m = c - '0' + k * 10;
      if (m > i) {
        break label437;
      }
      j += 1;
    }
    label437:
    for (i = m;; i = k)
    {
      k = j;
      m = i;
      if (i != 0) {
        break;
      }
      return null;
      paramArrayOfInt[0] = 2;
      switch (c)
      {
      default: 
        return null;
      case '$': 
        return new SubString("$");
      case '&': 
        return paramRegExpImpl.lastMatch;
      case '+': 
        return paramRegExpImpl.lastParen;
      case '`': 
        if (i == 120)
        {
          paramRegExpImpl.leftContext.index = 0;
          paramRegExpImpl.leftContext.length = paramRegExpImpl.lastMatch.index;
        }
        return paramRegExpImpl.leftContext;
      }
      return paramRegExpImpl.rightContext;
    }
  }
  
  private static Object matchOrReplace(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject, RegExpImpl paramRegExpImpl, GlobData paramGlobData, NativeRegExp paramNativeRegExp)
  {
    String str = paramGlobData.str;
    boolean bool;
    int[] arrayOfInt;
    if ((paramNativeRegExp.getFlags() & 0x1) != 0)
    {
      bool = true;
      paramGlobData.global = bool;
      arrayOfInt = new int[1];
      arrayOfInt[0] = 0;
      if (paramGlobData.mode != 3) {
        break label100;
      }
      paramContext = paramNativeRegExp.executeRegExp(paramContext, paramScriptable1, paramRegExpImpl, str, arrayOfInt, 0);
      if ((paramContext == null) || (!paramContext.equals(Boolean.TRUE))) {
        break label95;
      }
      paramArrayOfObject = Integer.valueOf(paramRegExpImpl.leftContext.length);
    }
    label95:
    label100:
    label122:
    do
    {
      do
      {
        return paramArrayOfObject;
        bool = false;
        break;
        return Integer.valueOf(-1);
        if (!paramGlobData.global) {
          break label300;
        }
        paramNativeRegExp.lastIndex = Double.valueOf(0.0D);
        i = 0;
        paramScriptable2 = null;
        if (arrayOfInt[0] > str.length()) {
          return paramScriptable2;
        }
        paramScriptable2 = paramNativeRegExp.executeRegExp(paramContext, paramScriptable1, paramRegExpImpl, str, arrayOfInt, 0);
        paramArrayOfObject = paramScriptable2;
      } while (paramScriptable2 == null);
      paramArrayOfObject = paramScriptable2;
    } while (!paramScriptable2.equals(Boolean.TRUE));
    if (paramGlobData.mode == 1) {
      match_glob(paramGlobData, paramContext, paramScriptable1, i, paramRegExpImpl);
    }
    for (;;)
    {
      if (paramRegExpImpl.lastMatch.length == 0)
      {
        paramArrayOfObject = paramScriptable2;
        if (arrayOfInt[0] == str.length()) {
          break;
        }
        arrayOfInt[0] += 1;
      }
      i += 1;
      break label122;
      if (paramGlobData.mode != 2) {
        Kit.codeBug();
      }
      paramArrayOfObject = paramRegExpImpl.lastMatch;
      int j = paramGlobData.leftIndex;
      int k = paramArrayOfObject.index;
      int m = paramArrayOfObject.index;
      paramGlobData.leftIndex = (paramArrayOfObject.length + m);
      replace_glob(paramGlobData, paramContext, paramScriptable1, paramRegExpImpl, j, k - j);
    }
    label300:
    if (paramGlobData.mode == 2) {}
    for (int i = 0;; i = 1) {
      return paramNativeRegExp.executeRegExp(paramContext, paramScriptable1, paramRegExpImpl, str, arrayOfInt, i);
    }
    return paramScriptable2;
  }
  
  private static void match_glob(GlobData paramGlobData, Context paramContext, Scriptable paramScriptable, int paramInt, RegExpImpl paramRegExpImpl)
  {
    if (paramGlobData.arrayobj == null) {
      paramGlobData.arrayobj = paramContext.newArray(paramScriptable, 0);
    }
    paramContext = paramRegExpImpl.lastMatch.toString();
    paramGlobData.arrayobj.put(paramInt, paramGlobData.arrayobj, paramContext);
  }
  
  private static void replace_glob(GlobData paramGlobData, Context paramContext, Scriptable paramScriptable, RegExpImpl paramRegExpImpl, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i;
    Object localObject1;
    if (paramGlobData.lambda != null)
    {
      Object localObject2 = paramRegExpImpl.parens;
      if (localObject2 == null)
      {
        i = 0;
        localObject1 = new Object[i + 3];
        localObject1[0] = paramRegExpImpl.lastMatch.toString();
        label44:
        if (j >= i) {
          break label105;
        }
        Object localObject3 = localObject2[j];
        if (localObject3 == null) {
          break label92;
        }
        localObject1[(j + 1)] = ((SubString)localObject3).toString();
      }
      for (;;)
      {
        j += 1;
        break label44;
        i = localObject2.length;
        break;
        label92:
        localObject1[(j + 1)] = Undefined.instance;
      }
      label105:
      localObject1[(i + 1)] = Integer.valueOf(paramRegExpImpl.leftContext.length);
      localObject1[(i + 2)] = paramGlobData.str;
      if (paramRegExpImpl != ScriptRuntime.getRegExpProxy(paramContext)) {
        Kit.codeBug();
      }
      localObject2 = new RegExpImpl();
      ((RegExpImpl)localObject2).multiline = paramRegExpImpl.multiline;
      ((RegExpImpl)localObject2).input = paramRegExpImpl.input;
      ScriptRuntime.setRegExpProxy(paramContext, (RegExpProxy)localObject2);
    }
    for (;;)
    {
      try
      {
        paramScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
        paramScriptable = ScriptRuntime.toString(paramGlobData.lambda.call(paramContext, paramScriptable, paramScriptable, (Object[])localObject1));
        ScriptRuntime.setRegExpProxy(paramContext, paramRegExpImpl);
        i = paramScriptable.length();
        i = paramRegExpImpl.rightContext.length + (paramInt2 + i);
        localObject1 = paramGlobData.charBuf;
        if (localObject1 != null) {
          break label413;
        }
        localObject1 = new StringBuilder(i);
        paramGlobData.charBuf = ((StringBuilder)localObject1);
        ((StringBuilder)localObject1).append(paramRegExpImpl.leftContext.str, paramInt1, paramInt1 + paramInt2);
        if (paramGlobData.lambda == null) {
          break;
        }
        ((StringBuilder)localObject1).append(paramScriptable);
        return;
      }
      finally
      {
        ScriptRuntime.setRegExpProxy(paramContext, paramRegExpImpl);
      }
      j = paramGlobData.repstr.length();
      i = j;
      int k;
      if (paramGlobData.dollar >= 0)
      {
        paramScriptable = new int[1];
        k = paramGlobData.dollar;
        label328:
        localObject1 = interpretDollar(paramContext, paramRegExpImpl, paramGlobData.repstr, k, paramScriptable);
        if (localObject1 == null) {
          break label400;
        }
        i = j + (((SubString)localObject1).length - paramScriptable[0]);
        k += paramScriptable[0];
      }
      for (;;)
      {
        int m = paramGlobData.repstr.indexOf('$', k);
        k = m;
        j = i;
        if (m >= 0) {
          break label328;
        }
        paramScriptable = null;
        break;
        label400:
        k += 1;
        i = j;
      }
      label413:
      ((StringBuilder)localObject1).ensureCapacity(i + paramGlobData.charBuf.length());
    }
    do_replace(paramGlobData, paramContext, paramRegExpImpl);
  }
  
  public Object action(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject, int paramInt)
  {
    GlobData localGlobData = new GlobData();
    localGlobData.mode = paramInt;
    localGlobData.str = ScriptRuntime.toString(paramScriptable2);
    switch (paramInt)
    {
    default: 
      throw Kit.codeBug();
    case 1: 
      paramContext = matchOrReplace(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject, this, localGlobData, createRegExp(paramContext, paramScriptable1, paramArrayOfObject, 1, false));
      if (localGlobData.arrayobj == null) {
        return paramContext;
      }
      return localGlobData.arrayobj;
    case 3: 
      return matchOrReplace(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject, this, localGlobData, createRegExp(paramContext, paramScriptable1, paramArrayOfObject, 1, false));
    }
    Object localObject1;
    Object localObject2;
    label174:
    String str;
    if (((paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof NativeRegExp))) || (paramArrayOfObject.length > 2))
    {
      paramInt = 1;
      NativeRegExp localNativeRegExp = null;
      if (paramInt == 0) {
        break label294;
      }
      localNativeRegExp = createRegExp(paramContext, paramScriptable1, paramArrayOfObject, 2, true);
      localObject1 = null;
      if (paramArrayOfObject.length >= 2) {
        break label325;
      }
      localObject2 = Undefined.instance;
      if (!(localObject2 instanceof Function)) {
        break label334;
      }
      localObject2 = (Function)localObject2;
      str = null;
      label192:
      localGlobData.lambda = ((Function)localObject2);
      localGlobData.repstr = str;
      if (str != null) {
        break label347;
      }
      i = -1;
      label214:
      localGlobData.dollar = i;
      localGlobData.charBuf = null;
      localGlobData.leftIndex = 0;
      if (paramInt == 0) {
        break label359;
      }
      paramScriptable2 = matchOrReplace(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject, this, localGlobData, localNativeRegExp);
    }
    for (;;)
    {
      if (localGlobData.charBuf == null)
      {
        if ((localGlobData.global) || (paramScriptable2 == null) || (!paramScriptable2.equals(Boolean.TRUE)))
        {
          return localGlobData.str;
          paramInt = 0;
          break;
          label294:
          if (paramArrayOfObject.length < 1) {}
          for (localObject1 = Undefined.instance;; localObject1 = paramArrayOfObject[0])
          {
            localObject1 = ScriptRuntime.toString(localObject1);
            break;
          }
          label325:
          localObject2 = paramArrayOfObject[1];
          break label174;
          label334:
          str = ScriptRuntime.toString(localObject2);
          localObject2 = null;
          break label192;
          label347:
          i = str.indexOf('$');
          break label214;
          label359:
          paramScriptable2 = localGlobData.str;
          paramInt = paramScriptable2.indexOf((String)localObject1);
          if (paramInt >= 0)
          {
            i = ((String)localObject1).length();
            this.lastParen = null;
            this.leftContext = new SubString(paramScriptable2, 0, paramInt);
            this.lastMatch = new SubString(paramScriptable2, paramInt, i);
            this.rightContext = new SubString(paramScriptable2, paramInt + i, paramScriptable2.length() - paramInt - i);
            paramScriptable2 = Boolean.TRUE;
            continue;
          }
          paramScriptable2 = Boolean.FALSE;
          continue;
        }
        paramScriptable2 = this.leftContext;
        replace_glob(localGlobData, paramContext, paramScriptable1, this, paramScriptable2.index, paramScriptable2.length);
      }
    }
    paramContext = this.rightContext;
    paramScriptable1 = localGlobData.charBuf;
    paramScriptable2 = paramContext.str;
    paramInt = paramContext.index;
    int i = paramContext.index;
    paramScriptable1.append(paramScriptable2, paramInt, paramContext.length + i);
    return localGlobData.charBuf.toString();
  }
  
  public Object compileRegExp(Context paramContext, String paramString1, String paramString2)
  {
    return NativeRegExp.compileRE(paramContext, paramString1, paramString2, false);
  }
  
  public int find_split(Context paramContext, Scriptable paramScriptable1, String paramString1, String paramString2, Scriptable paramScriptable2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean, String[][] paramArrayOfString)
  {
    int i = paramArrayOfInt1[0];
    int k = paramString1.length();
    int j = paramContext.getLanguageVersion();
    paramString2 = (NativeRegExp)paramScriptable2;
    int m = paramArrayOfInt1[0];
    paramArrayOfInt1[0] = i;
    if (paramString2.executeRegExp(paramContext, paramScriptable1, this, paramString1, paramArrayOfInt1, 0) != Boolean.TRUE)
    {
      paramArrayOfInt1[0] = m;
      paramArrayOfInt2[0] = 1;
      paramArrayOfBoolean[0] = false;
      return k;
    }
    i = paramArrayOfInt1[0];
    paramArrayOfInt1[0] = m;
    paramArrayOfBoolean[0] = true;
    paramArrayOfInt2[0] = this.lastMatch.length;
    if ((paramArrayOfInt2[0] == 0) && (i == paramArrayOfInt1[0])) {
      if (i == k) {
        if (j == 120)
        {
          paramArrayOfInt2[0] = 1;
          label137:
          if (this.parens != null) {
            break label224;
          }
        }
      }
    }
    label224:
    for (j = 0;; j = this.parens.length)
    {
      paramArrayOfString[0] = new String[j];
      m = 0;
      for (;;)
      {
        k = i;
        if (m >= j) {
          break;
        }
        paramContext = getParenSubString(m);
        paramArrayOfString[0][m] = paramContext.toString();
        m += 1;
      }
      i = -1;
      break label137;
      i += 1;
      break;
      i -= paramArrayOfInt2[0];
      break label137;
    }
  }
  
  SubString getParenSubString(int paramInt)
  {
    if ((this.parens != null) && (paramInt < this.parens.length))
    {
      SubString localSubString = this.parens[paramInt];
      if (localSubString != null) {
        return localSubString;
      }
    }
    return SubString.emptySubString;
  }
  
  public boolean isRegExp(Scriptable paramScriptable)
  {
    return paramScriptable instanceof NativeRegExp;
  }
  
  public Object js_split(Context paramContext, Scriptable paramScriptable, String paramString, Object[] paramArrayOfObject)
  {
    Scriptable localScriptable = paramContext.newArray(paramScriptable, 0);
    int j;
    long l1;
    if ((paramArrayOfObject.length > 1) && (paramArrayOfObject[1] != Undefined.instance))
    {
      j = 1;
      l1 = 0L;
      if (j == 0) {
        break label489;
      }
      long l2 = ScriptRuntime.toUint32(paramArrayOfObject[1]);
      l1 = l2;
      if (l2 <= paramString.length()) {
        break label489;
      }
      l1 = paramString.length() + 1;
    }
    label305:
    label318:
    label489:
    for (;;)
    {
      if ((paramArrayOfObject.length < 1) || (paramArrayOfObject[0] == Undefined.instance))
      {
        localScriptable.put(0, localScriptable, paramString);
        return localScriptable;
        j = 0;
        break;
      }
      String str = null;
      int[] arrayOfInt2 = new int[1];
      int[] arrayOfInt1 = null;
      Object localObject1 = null;
      Object localObject2 = arrayOfInt1;
      if ((paramArrayOfObject[0] instanceof Scriptable))
      {
        localObject3 = ScriptRuntime.getRegExpProxy(paramContext);
        localObject1 = localObject3;
        localObject2 = arrayOfInt1;
        if (localObject3 != null)
        {
          localObject4 = (Scriptable)paramArrayOfObject[0];
          localObject1 = localObject3;
          localObject2 = arrayOfInt1;
          if (((RegExpProxy)localObject3).isRegExp((Scriptable)localObject4))
          {
            localObject2 = localObject4;
            localObject1 = localObject3;
          }
        }
      }
      if (localObject2 == null)
      {
        str = ScriptRuntime.toString(paramArrayOfObject[0]);
        arrayOfInt2[0] = str.length();
      }
      arrayOfInt1 = new int[1];
      arrayOfInt1[0] = 0;
      Object localObject3 = new boolean[1];
      localObject3[0] = 0;
      Object localObject4 = new String[1][];
      localObject4[0] = null;
      int n = paramContext.getLanguageVersion();
      int i = 0;
      int i1 = find_split(paramContext, paramScriptable, paramString, str, n, (RegExpProxy)localObject1, (Scriptable)localObject2, arrayOfInt1, arrayOfInt2, (boolean[])localObject3, (String[][])localObject4);
      if ((i1 < 0) || ((j != 0) && (i >= l1)) || (i1 > paramString.length())) {
        return localScriptable;
      }
      int i2;
      int m;
      if (paramString.length() == 0)
      {
        paramArrayOfObject = paramString;
        localScriptable.put(i, localScriptable, paramArrayOfObject);
        int k = i + 1;
        i = k;
        if (localObject2 != null)
        {
          i = k;
          if (localObject3[0] == 1)
          {
            i2 = localObject4[0].length;
            m = 0;
            i = k;
          }
        }
      }
      for (;;)
      {
        if ((m >= i2) || ((j != 0) && (i >= l1)))
        {
          localObject3[0] = 0;
          arrayOfInt2[0] += i1;
          if ((n < 130) && (n != 0) && (j == 0) && (arrayOfInt1[0] == paramString.length())) {
            break label305;
          }
          break;
          paramArrayOfObject = paramString.substring(arrayOfInt1[0], i1);
          break label318;
        }
        localScriptable.put(i, localScriptable, localObject4[0][m]);
        m += 1;
        i += 1;
      }
    }
  }
  
  public Scriptable wrapRegExp(Context paramContext, Scriptable paramScriptable, Object paramObject)
  {
    return new NativeRegExp(paramScriptable, (RECompiled)paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\regexp\RegExpImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */