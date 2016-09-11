package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel.Builtins;
import org.mozilla.javascript.Undefined;

public class NativeRegExp
  extends IdScriptableObject
  implements Function
{
  private static final int ANCHOR_BOL = -2;
  private static final int INDEX_LEN = 2;
  private static final int Id_compile = 1;
  private static final int Id_exec = 4;
  private static final int Id_global = 3;
  private static final int Id_ignoreCase = 4;
  private static final int Id_lastIndex = 1;
  private static final int Id_multiline = 5;
  private static final int Id_prefix = 6;
  private static final int Id_source = 2;
  private static final int Id_test = 5;
  private static final int Id_toSource = 3;
  private static final int Id_toString = 2;
  public static final int JSREG_FOLD = 2;
  public static final int JSREG_GLOB = 1;
  public static final int JSREG_MULTILINE = 4;
  public static final int MATCH = 1;
  private static final int MAX_INSTANCE_ID = 5;
  private static final int MAX_PROTOTYPE_ID = 6;
  public static final int PREFIX = 2;
  private static final Object REGEXP_TAG;
  private static final byte REOP_ALNUM = 9;
  private static final byte REOP_ALT = 31;
  private static final byte REOP_ALTPREREQ = 53;
  private static final byte REOP_ALTPREREQ2 = 55;
  private static final byte REOP_ALTPREREQi = 54;
  private static final byte REOP_ASSERT = 41;
  private static final byte REOP_ASSERTNOTTEST = 44;
  private static final byte REOP_ASSERTTEST = 43;
  private static final byte REOP_ASSERT_NOT = 42;
  private static final byte REOP_BACKREF = 13;
  private static final byte REOP_BOL = 2;
  private static final byte REOP_CLASS = 22;
  private static final byte REOP_DIGIT = 7;
  private static final byte REOP_DOT = 6;
  private static final byte REOP_EMPTY = 1;
  private static final byte REOP_END = 57;
  private static final byte REOP_ENDCHILD = 49;
  private static final byte REOP_EOL = 3;
  private static final byte REOP_FLAT = 14;
  private static final byte REOP_FLAT1 = 15;
  private static final byte REOP_FLAT1i = 17;
  private static final byte REOP_FLATi = 16;
  private static final byte REOP_JUMP = 32;
  private static final byte REOP_LPAREN = 29;
  private static final byte REOP_MINIMALOPT = 47;
  private static final byte REOP_MINIMALPLUS = 46;
  private static final byte REOP_MINIMALQUANT = 48;
  private static final byte REOP_MINIMALREPEAT = 52;
  private static final byte REOP_MINIMALSTAR = 45;
  private static final byte REOP_NCLASS = 23;
  private static final byte REOP_NONALNUM = 10;
  private static final byte REOP_NONDIGIT = 8;
  private static final byte REOP_NONSPACE = 12;
  private static final byte REOP_OPT = 28;
  private static final byte REOP_PLUS = 27;
  private static final byte REOP_QUANT = 25;
  private static final byte REOP_REPEAT = 51;
  private static final byte REOP_RPAREN = 30;
  private static final byte REOP_SIMPLE_END = 23;
  private static final byte REOP_SIMPLE_START = 1;
  private static final byte REOP_SPACE = 11;
  private static final byte REOP_STAR = 26;
  private static final byte REOP_UCFLAT1 = 18;
  private static final byte REOP_UCFLAT1i = 19;
  private static final byte REOP_WBDRY = 4;
  private static final byte REOP_WNONBDRY = 5;
  public static final int TEST = 0;
  private static final boolean debug = false;
  static final long serialVersionUID = 4965263491464903264L;
  Object lastIndex = Double.valueOf(0.0D);
  private int lastIndexAttr = 6;
  private RECompiled re;
  
  static
  {
    if (!NativeRegExp.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      REGEXP_TAG = new Object();
      return;
    }
  }
  
  NativeRegExp() {}
  
  NativeRegExp(Scriptable paramScriptable, RECompiled paramRECompiled)
  {
    this.re = paramRECompiled;
    this.lastIndex = Double.valueOf(0.0D);
    ScriptRuntime.setBuiltinProtoAndParent(this, paramScriptable, TopLevel.Builtins.RegExp);
  }
  
  private static void addCharacterRangeToCharSet(RECharSet paramRECharSet, char paramChar1, char paramChar2)
  {
    int i = paramChar1 / '\b';
    char c = paramChar2 / '\b';
    if ((paramChar2 >= paramRECharSet.length) || (paramChar1 > paramChar2)) {
      throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
    }
    paramChar1 = (char)(paramChar1 & 0x7);
    paramChar2 = (char)(paramChar2 & 0x7);
    if (i == c)
    {
      paramRECharSet = paramRECharSet.bits;
      paramRECharSet[i] = ((byte)(255 >> 7 - (paramChar2 - paramChar1) << paramChar1 | paramRECharSet[i]));
      return;
    }
    byte[] arrayOfByte = paramRECharSet.bits;
    arrayOfByte[i] = ((byte)('?' << paramChar1 | arrayOfByte[i]));
    paramChar1 = i + 1;
    while (paramChar1 < c)
    {
      paramRECharSet.bits[paramChar1] = -1;
      paramChar1 += '\001';
    }
    paramRECharSet = paramRECharSet.bits;
    paramRECharSet[c] = ((byte)(paramRECharSet[c] | 255 >> '\007' - paramChar2));
  }
  
  private static void addCharacterToCharSet(RECharSet paramRECharSet, char paramChar)
  {
    int i = paramChar / '\b';
    if (paramChar >= paramRECharSet.length) {
      throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
    }
    paramRECharSet = paramRECharSet.bits;
    paramRECharSet[i] = ((byte)(paramRECharSet[i] | '\001' << (paramChar & 0x7)));
  }
  
  private static int addIndex(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramInt2 < 0) {
      throw Kit.codeBug();
    }
    if (paramInt2 > 65535) {
      throw Context.reportRuntimeError("Too complex regexp");
    }
    paramArrayOfByte[paramInt1] = ((byte)(paramInt2 >> 8));
    paramArrayOfByte[(paramInt1 + 1)] = ((byte)paramInt2);
    return paramInt1 + 2;
  }
  
  private static boolean backrefMatcher(REGlobalData paramREGlobalData, int paramInt1, String paramString, int paramInt2)
  {
    boolean bool = true;
    if ((paramREGlobalData.parens == null) || (paramInt1 >= paramREGlobalData.parens.length)) {
      bool = false;
    }
    int i;
    do
    {
      return bool;
      i = paramREGlobalData.parensIndex(paramInt1);
    } while (i == -1);
    int j = paramREGlobalData.parensLength(paramInt1);
    if (paramREGlobalData.cp + j > paramInt2) {
      return false;
    }
    if ((paramREGlobalData.regexp.flags & 0x2) != 0)
    {
      paramInt1 = 0;
      while (paramInt1 < j)
      {
        char c1 = paramString.charAt(i + paramInt1);
        char c2 = paramString.charAt(paramREGlobalData.cp + paramInt1);
        if ((c1 != c2) && (upcase(c1) != upcase(c2))) {
          return false;
        }
        paramInt1 += 1;
      }
    }
    if (!paramString.regionMatches(i, paramString, paramREGlobalData.cp, j)) {
      return false;
    }
    paramREGlobalData.cp += j;
    return true;
  }
  
  private static boolean calculateBitmapSize(CompilerState paramCompilerState, RENode paramRENode, char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int n = 0;
    int k = 0;
    paramRENode.bmsize = 0;
    paramRENode.sense = true;
    if (paramInt1 == paramInt2) {
      return true;
    }
    int i;
    int m;
    if (paramArrayOfChar[paramInt1] == '^')
    {
      i = paramInt1 + 1;
      paramRENode.sense = false;
      m = 0;
    }
    for (;;)
    {
      int j;
      label87:
      int i1;
      label471:
      int i2;
      if (i != paramInt2)
      {
        j = 2;
        switch (paramArrayOfChar[i])
        {
        default: 
          paramInt1 = paramArrayOfChar[i];
          i += 1;
        }
        for (;;)
        {
          if (k != 0) {
            if (m > paramInt1)
            {
              reportError("msg.bad.range", "");
              return false;
              i += 1;
              paramInt1 = i + 1;
              i1 = paramArrayOfChar[i];
              switch (i1)
              {
              default: 
                i = paramInt1;
                paramInt1 = i1;
                break;
              case 98: 
                j = 8;
                i = paramInt1;
                paramInt1 = j;
                break;
              case 102: 
                j = 12;
                i = paramInt1;
                paramInt1 = j;
                break;
              case 110: 
                j = 10;
                i = paramInt1;
                paramInt1 = j;
                break;
              case 114: 
                j = 13;
                i = paramInt1;
                paramInt1 = j;
                break;
              case 116: 
                j = 9;
                i = paramInt1;
                paramInt1 = j;
                break;
              case 118: 
                j = 11;
                i = paramInt1;
                paramInt1 = j;
                break;
              case 99: 
                if ((paramInt1 < paramInt2) && (isControlLetter(paramArrayOfChar[paramInt1])))
                {
                  i = paramInt1 + 1;
                  paramInt1 = (char)(paramArrayOfChar[paramInt1] & 0x1F);
                  paramInt1 = i;
                }
                for (;;)
                {
                  j = 92;
                  i = paramInt1;
                  paramInt1 = j;
                  break;
                  paramInt1 -= 1;
                }
              case 117: 
                j = 4;
              case 120: 
                i = 0;
                i1 = 0;
                if ((i1 >= j) || (paramInt1 >= paramInt2)) {
                  break label839;
                }
                i2 = paramInt1 + 1;
                i = Kit.xDigitToInt(paramArrayOfChar[paramInt1], i);
                if (i < 0)
                {
                  paramInt1 = i2 - (i1 + 1);
                  i = 92;
                }
                break;
              }
            }
          }
        }
      }
      label708:
      label754:
      label826:
      label833:
      label839:
      for (;;)
      {
        j = paramInt1;
        paramInt1 = i;
        i = j;
        break label87;
        i1 += 1;
        paramInt1 = i2;
        break label471;
        if (k != 0)
        {
          reportError("msg.bad.range", "");
          return false;
        }
        j = 57;
        i = paramInt1;
        paramInt1 = j;
        break label87;
        if (k != 0)
        {
          reportError("msg.bad.range", "");
          return false;
        }
        paramRENode.bmsize = 65536;
        return true;
        i1 -= 48;
        i2 = paramArrayOfChar[paramInt1];
        i = i1;
        j = paramInt1;
        if (48 <= i2)
        {
          i = i1;
          j = paramInt1;
          if (i2 <= 55)
          {
            j = paramInt1 + 1;
            paramInt1 = i2 - 48 + i1 * 8;
            i = paramArrayOfChar[j];
            if ((48 > i) || (i > 55)) {
              break label833;
            }
            j += 1;
            i = i - 48 + paramInt1 * 8;
            if (i > 255) {
              break label708;
            }
          }
        }
        for (;;)
        {
          paramInt1 = i;
          i = j;
          break label87;
          j -= 1;
          i = paramInt1;
          continue;
          for (j = 0;; j = k)
          {
            if ((paramCompilerState.flags & 0x2) != 0)
            {
              k = upcase((char)paramInt1);
              paramInt1 = downcase((char)paramInt1);
              if (k >= paramInt1) {
                paramInt1 = k;
              }
            }
            for (;;)
            {
              if (paramInt1 > n) {}
              for (;;)
              {
                k = j;
                n = paramInt1;
                break;
                if ((i >= paramInt2 - 1) || (paramArrayOfChar[i] != '-')) {
                  break label826;
                }
                i += 1;
                k = 1;
                m = (char)paramInt1;
                break;
                break label754;
                paramRENode.bmsize = (n + 1);
                return true;
                paramInt1 = n;
              }
            }
          }
          i = paramInt1;
        }
      }
      m = 0;
      i = paramInt1;
    }
  }
  
  private static boolean classMatcher(REGlobalData paramREGlobalData, RECharSet paramRECharSet, char paramChar)
  {
    int j = 1;
    if (!paramRECharSet.converted) {
      processCharSet(paramREGlobalData, paramRECharSet);
    }
    int i = j;
    if (paramRECharSet.length != 0)
    {
      i = j;
      if (paramChar < paramRECharSet.length) {
        if ((paramRECharSet.bits[(paramChar >> '\003')] & '\001' << (paramChar & 0x7)) != 0) {
          break label64;
        }
      }
    }
    label64:
    for (i = j;; i = 0) {
      return i ^ paramRECharSet.sense;
    }
  }
  
  static RECompiled compileRE(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    RECompiled localRECompiled = new RECompiled(paramString1);
    int m = paramString1.length();
    int i;
    if (paramString2 != null)
    {
      int k = 0;
      int j = 0;
      i = j;
      if (k < paramString2.length())
      {
        char c = paramString2.charAt(k);
        if (c == 'g') {
          i = 1;
        }
        for (;;)
        {
          if ((j & i) != 0) {
            reportError("msg.invalid.re.flag", String.valueOf(c));
          }
          j |= i;
          k += 1;
          break;
          if (c == 'i')
          {
            i = 2;
          }
          else if (c == 'm')
          {
            i = 4;
          }
          else
          {
            reportError("msg.invalid.re.flag", String.valueOf(c));
            i = 0;
          }
        }
      }
    }
    else
    {
      i = 0;
    }
    localRECompiled.flags = i;
    paramString2 = new CompilerState(paramContext, localRECompiled.source, m, i);
    if ((paramBoolean) && (m > 0))
    {
      paramString2.result = new RENode((byte)14);
      paramString2.result.chr = paramString2.cpbegin[0];
      paramString2.result.length = m;
      paramString2.result.flatIndex = 0;
      paramString2.progLength += 5;
      paramString1 = paramString2;
      localRECompiled.program = new byte[paramString1.progLength + 1];
      if (paramString1.classCount != 0)
      {
        localRECompiled.classList = new RECharSet[paramString1.classCount];
        localRECompiled.classCount = paramString1.classCount;
      }
      i = emitREBytecode(paramString1, localRECompiled, 0, paramString1.result);
      localRECompiled.program[i] = 57;
      localRECompiled.parenCount = paramString1.parenCount;
      switch (localRECompiled.program[0])
      {
      }
    }
    for (;;)
    {
      return localRECompiled;
      if (!parseDisjunction(paramString2)) {
        return null;
      }
      paramString1 = paramString2;
      if (paramString2.maxBackReference <= paramString2.parenCount) {
        break;
      }
      paramContext = new CompilerState(paramContext, localRECompiled.source, m, i);
      paramContext.backReferenceLimit = paramContext.parenCount;
      paramString1 = paramContext;
      if (parseDisjunction(paramContext)) {
        break;
      }
      return null;
      localRECompiled.anchorCh = ((char)getIndex(localRECompiled.program, 1));
      continue;
      localRECompiled.anchorCh = ((char)(localRECompiled.program[1] & 0xFF));
      continue;
      i = getIndex(localRECompiled.program, 1);
      localRECompiled.anchorCh = localRECompiled.source[i];
      continue;
      localRECompiled.anchorCh = -2;
      continue;
      paramContext = paramString1.result;
      if ((paramContext.kid.op == 2) && (paramContext.kid2.op == 2)) {
        localRECompiled.anchorCh = -2;
      }
    }
  }
  
  private static void doFlat(CompilerState paramCompilerState, char paramChar)
  {
    paramCompilerState.result = new RENode((byte)14);
    paramCompilerState.result.chr = paramChar;
    paramCompilerState.result.length = 1;
    paramCompilerState.result.flatIndex = -1;
    paramCompilerState.progLength += 3;
  }
  
  private static char downcase(char paramChar)
  {
    char c1;
    if (paramChar < '?')
    {
      c1 = paramChar;
      if ('A' <= paramChar)
      {
        c1 = paramChar;
        if (paramChar <= 'Z') {
          c1 = (char)(paramChar + ' ');
        }
      }
    }
    char c2;
    do
    {
      return c1;
      c2 = Character.toLowerCase(paramChar);
      c1 = paramChar;
    } while (c2 < '?');
    return c2;
  }
  
  private static int emitREBytecode(CompilerState paramCompilerState, RECompiled paramRECompiled, int paramInt, RENode paramRENode)
  {
    byte[] arrayOfByte = paramRECompiled.program;
    int j = paramInt;
    label166:
    int k;
    if (paramRENode != null)
    {
      paramInt = j + 1;
      arrayOfByte[j] = paramRENode.op;
      switch (paramRENode.op)
      {
      default: 
      case 1: 
        for (;;)
        {
          paramRENode = paramRENode.next;
          j = paramInt;
          break;
          paramInt -= 1;
        }
      case 53: 
      case 54: 
      case 55: 
        if (paramRENode.op == 54)
        {
          j = 1;
          if (j == 0) {
            break label320;
          }
          k = upcase(paramRENode.chr);
          label180:
          addIndex(arrayOfByte, paramInt, k);
          k = paramInt + 2;
          if (j == 0) {
            break label329;
          }
          paramInt = upcase((char)paramRENode.index);
          label208:
          addIndex(arrayOfByte, k, paramInt);
          paramInt = k + 2;
        }
        break;
      }
    }
    for (;;)
    {
      RENode localRENode = paramRENode.kid2;
      k = emitREBytecode(paramCompilerState, paramRECompiled, paramInt + 2, paramRENode.kid);
      j = k + 1;
      arrayOfByte[k] = 32;
      k = j + 2;
      resolveForwardJump(arrayOfByte, paramInt, k);
      paramInt = emitREBytecode(paramCompilerState, paramRECompiled, k, localRENode);
      k = paramInt + 1;
      arrayOfByte[paramInt] = 32;
      paramInt = k + 2;
      resolveForwardJump(arrayOfByte, j, paramInt);
      resolveForwardJump(arrayOfByte, k, paramInt);
      break;
      j = 0;
      break label166;
      label320:
      k = paramRENode.chr;
      break label180;
      label329:
      paramInt = paramRENode.index;
      break label208;
      if (paramRENode.flatIndex != -1) {
        while ((paramRENode.next != null) && (paramRENode.next.op == 14) && (paramRENode.flatIndex + paramRENode.length == paramRENode.next.flatIndex))
        {
          paramRENode.length += paramRENode.next.length;
          paramRENode.next = paramRENode.next.next;
        }
      }
      if ((paramRENode.flatIndex != -1) && (paramRENode.length > 1))
      {
        if ((paramCompilerState.flags & 0x2) != 0) {
          arrayOfByte[(paramInt - 1)] = 16;
        }
        for (;;)
        {
          paramInt = addIndex(arrayOfByte, addIndex(arrayOfByte, paramInt, paramRENode.flatIndex), paramRENode.length);
          break;
          arrayOfByte[(paramInt - 1)] = 14;
        }
      }
      if (paramRENode.chr < '?')
      {
        if ((paramCompilerState.flags & 0x2) != 0) {
          arrayOfByte[(paramInt - 1)] = 17;
        }
        for (;;)
        {
          arrayOfByte[paramInt] = ((byte)paramRENode.chr);
          paramInt += 1;
          break;
          arrayOfByte[(paramInt - 1)] = 15;
        }
      }
      if ((paramCompilerState.flags & 0x2) != 0) {
        arrayOfByte[(paramInt - 1)] = 19;
      }
      for (;;)
      {
        paramInt = addIndex(arrayOfByte, paramInt, paramRENode.chr);
        break;
        arrayOfByte[(paramInt - 1)] = 18;
      }
      paramInt = emitREBytecode(paramCompilerState, paramRECompiled, addIndex(arrayOfByte, paramInt, paramRENode.parenIndex), paramRENode.kid);
      arrayOfByte[paramInt] = 30;
      paramInt = addIndex(arrayOfByte, paramInt + 1, paramRENode.parenIndex);
      break;
      paramInt = addIndex(arrayOfByte, paramInt, paramRENode.parenIndex);
      break;
      k = emitREBytecode(paramCompilerState, paramRECompiled, paramInt + 2, paramRENode.kid);
      j = k + 1;
      arrayOfByte[k] = 43;
      resolveForwardJump(arrayOfByte, paramInt, j);
      paramInt = j;
      break;
      k = emitREBytecode(paramCompilerState, paramRECompiled, paramInt + 2, paramRENode.kid);
      j = k + 1;
      arrayOfByte[k] = 44;
      resolveForwardJump(arrayOfByte, paramInt, j);
      paramInt = j;
      break;
      int i;
      if ((paramRENode.min == 0) && (paramRENode.max == -1)) {
        if (paramRENode.greedy)
        {
          i = 26;
          label740:
          arrayOfByte[(paramInt - 1)] = i;
        }
      }
      for (;;)
      {
        j = addIndex(arrayOfByte, addIndex(arrayOfByte, paramInt, paramRENode.parenCount), paramRENode.parenIndex);
        k = emitREBytecode(paramCompilerState, paramRECompiled, j + 2, paramRENode.kid);
        paramInt = k + 1;
        arrayOfByte[k] = 49;
        resolveForwardJump(arrayOfByte, j, paramInt);
        break;
        i = 45;
        break label740;
        if ((paramRENode.min == 0) && (paramRENode.max == 1))
        {
          if (paramRENode.greedy) {}
          for (i = 28;; i = 47)
          {
            arrayOfByte[(paramInt - 1)] = i;
            break;
          }
        }
        if ((paramRENode.min == 1) && (paramRENode.max == -1))
        {
          if (paramRENode.greedy) {}
          for (i = 27;; i = 46)
          {
            arrayOfByte[(paramInt - 1)] = i;
            break;
          }
        }
        if (!paramRENode.greedy) {
          arrayOfByte[(paramInt - 1)] = 48;
        }
        paramInt = addIndex(arrayOfByte, addIndex(arrayOfByte, paramInt, paramRENode.min), paramRENode.max + 1);
      }
      if (!paramRENode.sense) {
        arrayOfByte[(paramInt - 1)] = 23;
      }
      paramInt = addIndex(arrayOfByte, paramInt, paramRENode.index);
      paramRECompiled.classList[paramRENode.index] = new RECharSet(paramRENode.bmsize, paramRENode.startIndex, paramRENode.kidlen, paramRENode.sense);
      break;
      return j;
    }
  }
  
  private static String escapeRegExp(Object paramObject)
  {
    String str = ScriptRuntime.toString(paramObject);
    int i = 0;
    int j = str.indexOf('/');
    paramObject = null;
    if (j > -1)
    {
      if ((j != i) && (str.charAt(j - 1) == '\\')) {
        break label117;
      }
      Object localObject = paramObject;
      if (paramObject == null) {
        localObject = new StringBuilder();
      }
      ((StringBuilder)localObject).append(str, i, j);
      ((StringBuilder)localObject).append("\\/");
      paramObject = localObject;
      i = j + 1;
    }
    label117:
    for (;;)
    {
      j = str.indexOf('/', j + 1);
      break;
      if (paramObject != null)
      {
        ((StringBuilder)paramObject).append(str, i, str.length());
        return ((StringBuilder)paramObject).toString();
      }
      return str;
    }
  }
  
  private Object execSub(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject, int paramInt)
  {
    RegExpImpl localRegExpImpl = getImpl(paramContext);
    Object localObject;
    if (paramArrayOfObject.length == 0)
    {
      localObject = localRegExpImpl.input;
      paramArrayOfObject = (Object[])localObject;
      if (localObject == null) {
        paramArrayOfObject = ScriptRuntime.toString(Undefined.instance);
      }
      if ((this.re.flags & 0x1) == 0) {
        break label165;
      }
    }
    label155:
    label165:
    for (double d = ScriptRuntime.toInteger(this.lastIndex);; d = 0.0D)
    {
      if ((d < 0.0D) || (paramArrayOfObject.length() < d))
      {
        this.lastIndex = Double.valueOf(0.0D);
        return null;
        paramArrayOfObject = ScriptRuntime.toString(paramArrayOfObject[0]);
        break;
      }
      localObject = new int[1];
      localObject[0] = ((int)d);
      paramContext = executeRegExp(paramContext, paramScriptable, localRegExpImpl, paramArrayOfObject, (int[])localObject, paramInt);
      if ((this.re.flags & 0x1) != 0) {
        if ((paramContext != null) && (paramContext != Undefined.instance)) {
          break label155;
        }
      }
      for (d = 0.0D;; d = localObject[0])
      {
        this.lastIndex = Double.valueOf(d);
        return paramContext;
      }
    }
  }
  
  private static boolean executeREBytecode(REGlobalData paramREGlobalData, String paramString, int paramInt)
  {
    byte[] arrayOfByte = paramREGlobalData.regexp.program;
    int i2 = 57;
    int i1 = 0;
    int n = 1;
    int i3 = 1;
    int m = arrayOfByte[0];
    int k = m;
    int j = n;
    if (paramREGlobalData.regexp.anchorCh < 0)
    {
      k = m;
      j = n;
      if (reopIsSimple(m))
      {
        if (paramREGlobalData.cp > paramInt) {
          break label2465;
        }
        k = simpleMatch(paramREGlobalData, paramString, m, arrayOfByte, 1, paramInt, true);
        if (k >= 0)
        {
          n = 1;
          j = k + 1;
          k = arrayOfByte[k];
        }
      }
    }
    for (;;)
    {
      if (n == 0)
      {
        return false;
        paramREGlobalData.skipped += 1;
        paramREGlobalData.cp += 1;
        break;
        m = 1;
        j = k + 1;
        k = arrayOfByte[k];
        label151:
        pushBackTrackState(paramREGlobalData, arrayOfByte[i2], i2 + 1, i3, n, i1);
      }
      for (;;)
      {
        label170:
        if (reopIsSimple(k))
        {
          m = simpleMatch(paramREGlobalData, paramString, k, arrayOfByte, j, paramInt, true);
          if (m >= 0)
          {
            k = 1;
            label201:
            if (k != 0) {
              j = m;
            }
            m = k;
            k = j;
            j = m;
          }
        }
        label222:
        Object localObject;
        int i4;
        int i5;
        for (;;)
        {
          if (j == 0)
          {
            localObject = paramREGlobalData.backTrackStackTop;
            if (localObject != null)
            {
              paramREGlobalData.backTrackStackTop = ((REBackTrackData)localObject).previous;
              paramREGlobalData.parens = ((REBackTrackData)localObject).parens;
              paramREGlobalData.cp = ((REBackTrackData)localObject).cp;
              paramREGlobalData.stateStackTop = ((REBackTrackData)localObject).stateStackTop;
              n = ((REBackTrackData)localObject).continuationOp;
              i1 = ((REBackTrackData)localObject).continuationPc;
              i2 = ((REBackTrackData)localObject).pc;
              k = ((REBackTrackData)localObject).op;
              m = j;
              j = i2;
              break label170;
              k = 0;
              break label201;
              i3 = j;
              i2 = m;
              switch (k)
              {
              case 33: 
              case 34: 
              case 35: 
              case 36: 
              case 37: 
              case 38: 
              case 39: 
              case 40: 
              case 50: 
              case 56: 
              default: 
                throw Kit.codeBug("invalid bytecode");
              case 53: 
              case 54: 
              case 55: 
                i4 = (char)getIndex(arrayOfByte, j);
                j += 2;
                i5 = (char)getIndex(arrayOfByte, j);
                j += 2;
                if (paramREGlobalData.cp == paramInt)
                {
                  m = 0;
                  k = j;
                  j = m;
                }
                else
                {
                  int i = paramString.charAt(paramREGlobalData.cp);
                  if (k == 55)
                  {
                    i3 = j;
                    if (i != i4)
                    {
                      i3 = j;
                      if (!classMatcher(paramREGlobalData, paramREGlobalData.regexp.classList[i5], i))
                      {
                        m = 0;
                        k = j;
                        j = m;
                      }
                    }
                  }
                  else
                  {
                    i2 = i;
                    if (k == 54) {
                      i2 = upcase(i);
                    }
                    i3 = j;
                    if (i2 != i4)
                    {
                      i3 = j;
                      if (i2 != i5)
                      {
                        m = 0;
                        k = j;
                        j = m;
                      }
                    }
                  }
                }
                break;
              case 31: 
                i2 = i3 + getOffset(arrayOfByte, i3);
                k = i3 + 2;
                j = k + 1;
                k = arrayOfByte[k];
                i3 = paramREGlobalData.cp;
                if (!reopIsSimple(k)) {
                  break label2452;
                }
                k = simpleMatch(paramREGlobalData, paramString, k, arrayOfByte, j, paramInt, true);
                if (k >= 0) {
                  break;
                }
                j = i2 + 1;
                k = arrayOfByte[i2];
                break;
              case 32: 
                k = getOffset(arrayOfByte, j) + j;
                j = k + 1;
                k = arrayOfByte[k];
                break;
              case 29: 
                k = getIndex(arrayOfByte, j);
                i2 = j + 2;
                paramREGlobalData.setParens(k, paramREGlobalData.cp, 0);
                j = i2 + 1;
                k = arrayOfByte[i2];
                break;
              case 30: 
                k = getIndex(arrayOfByte, j);
                i2 = j + 2;
                j = paramREGlobalData.parensIndex(k);
                paramREGlobalData.setParens(k, j, paramREGlobalData.cp - j);
                j = i2 + 1;
                k = arrayOfByte[i2];
                break;
              case 41: 
                i3 = getIndex(arrayOfByte, j);
                k = j + 2;
                i2 = k + 1;
                k = arrayOfByte[k];
                if ((reopIsSimple(k)) && (simpleMatch(paramREGlobalData, paramString, k, arrayOfByte, i2, paramInt, false) < 0))
                {
                  j = 0;
                  k = i2;
                }
                else
                {
                  pushProgState(paramREGlobalData, 0, 0, paramREGlobalData.cp, paramREGlobalData.backTrackStackTop, n, i1);
                  pushBackTrackState(paramREGlobalData, (byte)43, j + i3);
                  j = i2;
                }
                break;
              case 42: 
                i3 = getIndex(arrayOfByte, j);
                k = j + 2;
                i2 = k + 1;
                k = arrayOfByte[k];
                if (reopIsSimple(k))
                {
                  i4 = simpleMatch(paramREGlobalData, paramString, k, arrayOfByte, i2, paramInt, false);
                  if ((i4 >= 0) && (arrayOfByte[i4] == 44))
                  {
                    j = 0;
                    k = i2;
                    continue;
                  }
                }
                pushProgState(paramREGlobalData, 0, 0, paramREGlobalData.cp, paramREGlobalData.backTrackStackTop, n, i1);
                pushBackTrackState(paramREGlobalData, (byte)44, j + i3);
                j = i2;
                break;
              case 43: 
              case 44: 
                localObject = popProgState(paramREGlobalData);
                paramREGlobalData.cp = ((REProgState)localObject).index;
                paramREGlobalData.backTrackStackTop = ((REProgState)localObject).backTrack;
                i1 = ((REProgState)localObject).continuationPc;
                n = ((REProgState)localObject).continuationOp;
                if (k != 44) {
                  break label2445;
                }
                if (m == 0) {
                  k = 1;
                }
                break;
              }
            }
          }
        }
        for (;;)
        {
          m = j;
          j = k;
          k = m;
          break label222;
          k = 0;
          continue;
          i2 = 0;
          i5 = 0;
          i3 = 0;
          i4 = 0;
          switch (k)
          {
          default: 
            throw Kit.codeBug();
          case 26: 
            i4 = 1;
          case 45: 
            i2 = 0;
            i3 = -1;
            k = i4;
          }
          for (;;)
          {
            pushProgState(paramREGlobalData, i2, i3, paramREGlobalData.cp, null, n, i1);
            if (k == 0) {
              break label1388;
            }
            pushBackTrackState(paramREGlobalData, (byte)51, j);
            n = 51;
            i2 = j + 6;
            k = arrayOfByte[i2];
            i1 = j;
            j = i2 + 1;
            break;
            i2 = 1;
            i4 = 1;
            i3 = -1;
            k = i2;
            i2 = i4;
            continue;
            i5 = 1;
            i2 = 0;
            i3 = 1;
            k = i5;
            continue;
            i3 = 1;
            i2 = getOffset(arrayOfByte, j);
            j += 2;
            i4 = getOffset(arrayOfByte, j) - 1;
            j += 2;
            k = i3;
            i3 = i4;
          }
          label1388:
          if (i2 != 0)
          {
            n = 52;
            i2 = j + 6;
            k = arrayOfByte[i2];
            i1 = j;
            j = i2 + 1;
            break;
          }
          pushBackTrackState(paramREGlobalData, (byte)52, j);
          popProgState(paramREGlobalData);
          j += 4;
          k = j + getOffset(arrayOfByte, j);
          j = k + 1;
          k = arrayOfByte[k];
          break;
          k = n;
          m = 1;
          j = i1;
          break;
          label1485:
          i2 = k;
          localObject = popProgState(paramREGlobalData);
          if (i2 == 0)
          {
            if (((REProgState)localObject).min == 0) {
              i2 = 1;
            }
            i1 = ((REProgState)localObject).continuationPc;
            n = ((REProgState)localObject).continuationOp;
            k = j + 4;
            m = getOffset(arrayOfByte, k);
            j = i2;
            k += m;
            break label222;
          }
          if ((((REProgState)localObject).min == 0) && (paramREGlobalData.cp == ((REProgState)localObject).index))
          {
            k = 0;
            i1 = ((REProgState)localObject).continuationPc;
            n = ((REProgState)localObject).continuationOp;
            j += 4;
            m = j + getOffset(arrayOfByte, j);
            j = k;
            k = m;
            break label222;
          }
          m = ((REProgState)localObject).min;
          i1 = ((REProgState)localObject).max;
          if (m != 0) {
            m -= 1;
          }
          for (;;)
          {
            if (i1 != -1) {
              i1 -= 1;
            }
            for (;;)
            {
              if (i1 == 0)
              {
                k = 1;
                i1 = ((REProgState)localObject).continuationPc;
                n = ((REProgState)localObject).continuationOp;
                j += 4;
                m = j + getOffset(arrayOfByte, j);
                j = k;
                k = m;
                break label222;
              }
              n = j + 6;
              k = arrayOfByte[n];
              i3 = paramREGlobalData.cp;
              if (reopIsSimple(k))
              {
                n = simpleMatch(paramREGlobalData, paramString, k, arrayOfByte, n + 1, paramInt, true);
                if (n < 0)
                {
                  if (m == 0) {}
                  for (k = 1;; k = 0)
                  {
                    i1 = ((REProgState)localObject).continuationPc;
                    n = ((REProgState)localObject).continuationOp;
                    j += 4;
                    m = j + getOffset(arrayOfByte, j);
                    j = k;
                    k = m;
                    break;
                  }
                }
              }
              for (k = 1;; k = i2)
              {
                pushProgState(paramREGlobalData, m, i1, i3, null, ((REProgState)localObject).continuationOp, ((REProgState)localObject).continuationPc);
                if (m == 0)
                {
                  pushBackTrackState(paramREGlobalData, (byte)51, j, i3, ((REProgState)localObject).continuationOp, ((REProgState)localObject).continuationPc);
                  i1 = getIndex(arrayOfByte, j);
                  i2 = getIndex(arrayOfByte, j + 2);
                  m = 0;
                  while (m < i1)
                  {
                    paramREGlobalData.setParens(i2 + m, -1, 0);
                    m += 1;
                  }
                }
                if (arrayOfByte[n] == 49) {
                  break label1485;
                }
                i3 = arrayOfByte[n];
                m = k;
                i2 = 51;
                n += 1;
                i1 = j;
                k = i3;
                j = n;
                n = i2;
                break;
                localObject = popProgState(paramREGlobalData);
                if (m == 0)
                {
                  if ((((REProgState)localObject).max == -1) || (((REProgState)localObject).max > 0))
                  {
                    pushProgState(paramREGlobalData, ((REProgState)localObject).min, ((REProgState)localObject).max, paramREGlobalData.cp, null, ((REProgState)localObject).continuationOp, ((REProgState)localObject).continuationPc);
                    n = 52;
                    i1 = getIndex(arrayOfByte, j);
                    k = j + 2;
                    i2 = getIndex(arrayOfByte, k);
                    i3 = k + 4;
                    k = 0;
                    while (k < i1)
                    {
                      paramREGlobalData.setParens(i2 + k, -1, 0);
                      k += 1;
                    }
                    k = arrayOfByte[i3];
                    i2 = i3 + 1;
                    i1 = j;
                    j = i2;
                    break;
                  }
                  i1 = ((REProgState)localObject).continuationPc;
                  n = ((REProgState)localObject).continuationOp;
                  k = j;
                  j = m;
                  break label222;
                }
                if ((((REProgState)localObject).min == 0) && (paramREGlobalData.cp == ((REProgState)localObject).index))
                {
                  m = 0;
                  i1 = ((REProgState)localObject).continuationPc;
                  n = ((REProgState)localObject).continuationOp;
                  k = j;
                  j = m;
                  break label222;
                }
                n = ((REProgState)localObject).min;
                i1 = ((REProgState)localObject).max;
                k = n;
                if (n != 0) {
                  k = n - 1;
                }
                n = i1;
                if (i1 != -1) {
                  n = i1 - 1;
                }
                pushProgState(paramREGlobalData, k, n, paramREGlobalData.cp, null, ((REProgState)localObject).continuationOp, ((REProgState)localObject).continuationPc);
                if (k != 0)
                {
                  n = 52;
                  i1 = getIndex(arrayOfByte, j);
                  k = j + 2;
                  i2 = getIndex(arrayOfByte, k);
                  i3 = k + 4;
                  k = 0;
                  while (k < i1)
                  {
                    paramREGlobalData.setParens(i2 + k, -1, 0);
                    k += 1;
                  }
                  k = arrayOfByte[i3];
                  i2 = i3 + 1;
                  i1 = j;
                  j = i2;
                  break;
                }
                i1 = ((REProgState)localObject).continuationPc;
                n = ((REProgState)localObject).continuationOp;
                pushBackTrackState(paramREGlobalData, (byte)52, j);
                popProgState(paramREGlobalData);
                j += 4;
                k = j + getOffset(arrayOfByte, j);
                j = k + 1;
                k = arrayOfByte[k];
                break;
                return true;
                return false;
                i2 = k + 1;
                k = arrayOfByte[k];
                m = j;
                j = i2;
                break;
              }
            }
          }
          label2445:
          k = m;
        }
        label2452:
        break label151;
        m = 0;
        n = i2;
      }
      label2465:
      n = 0;
      k = m;
      j = i3;
    }
  }
  
  private static boolean flatNIMatcher(REGlobalData paramREGlobalData, int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    if (paramREGlobalData.cp + paramInt2 > paramInt3) {
      return false;
    }
    char[] arrayOfChar = paramREGlobalData.regexp.source;
    paramInt3 = 0;
    for (;;)
    {
      if (paramInt3 >= paramInt2) {
        break label82;
      }
      char c1 = arrayOfChar[(paramInt1 + paramInt3)];
      char c2 = paramString.charAt(paramREGlobalData.cp + paramInt3);
      if ((c1 != c2) && (upcase(c1) != upcase(c2))) {
        break;
      }
      paramInt3 += 1;
    }
    label82:
    paramREGlobalData.cp += paramInt2;
    return true;
  }
  
  private static boolean flatNMatcher(REGlobalData paramREGlobalData, int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    if (paramREGlobalData.cp + paramInt2 > paramInt3) {
      return false;
    }
    paramInt3 = 0;
    for (;;)
    {
      if (paramInt3 >= paramInt2) {
        break label57;
      }
      if (paramREGlobalData.regexp.source[(paramInt1 + paramInt3)] != paramString.charAt(paramREGlobalData.cp + paramInt3)) {
        break;
      }
      paramInt3 += 1;
    }
    label57:
    paramREGlobalData.cp += paramInt2;
    return true;
  }
  
  private static int getDecimalValue(char paramChar, CompilerState paramCompilerState, int paramInt, String paramString)
  {
    int i = 0;
    int k = paramCompilerState.cp;
    char[] arrayOfChar = paramCompilerState.cpbegin;
    char c2 = paramChar - '0';
    char c1;
    if (paramCompilerState.cp != paramCompilerState.cpend)
    {
      c1 = arrayOfChar[paramCompilerState.cp];
      if (isDigit(c1)) {}
    }
    else
    {
      if (i != 0) {
        reportError(paramString, String.valueOf(arrayOfChar, k, paramCompilerState.cp - k));
      }
      return c2;
    }
    paramChar = c2;
    int j = i;
    if (i == 0)
    {
      paramChar = c2 * '\n' + (c1 - '0');
      if (paramChar >= paramInt) {
        break label128;
      }
      j = i;
    }
    for (;;)
    {
      paramCompilerState.cp += 1;
      c2 = paramChar;
      i = j;
      break;
      label128:
      j = 1;
      paramChar = paramInt;
    }
  }
  
  private static RegExpImpl getImpl(Context paramContext)
  {
    return (RegExpImpl)ScriptRuntime.getRegExpProxy(paramContext);
  }
  
  private static int getIndex(byte[] paramArrayOfByte, int paramInt)
  {
    return (paramArrayOfByte[paramInt] & 0xFF) << 8 | paramArrayOfByte[(paramInt + 1)] & 0xFF;
  }
  
  private static int getOffset(byte[] paramArrayOfByte, int paramInt)
  {
    return getIndex(paramArrayOfByte, paramInt);
  }
  
  public static void init(Context paramContext, Scriptable paramScriptable, boolean paramBoolean)
  {
    NativeRegExp localNativeRegExp = new NativeRegExp();
    localNativeRegExp.re = compileRE(paramContext, "", null, false);
    localNativeRegExp.activatePrototypeMap(6);
    localNativeRegExp.setParentScope(paramScriptable);
    localNativeRegExp.setPrototype(getObjectPrototype(paramScriptable));
    paramContext = new NativeRegExpCtor();
    localNativeRegExp.defineProperty("constructor", paramContext, 2);
    ScriptRuntime.setFunctionProtoAndParent(paramContext, paramScriptable);
    paramContext.setImmunePrototypeProperty(localNativeRegExp);
    if (paramBoolean)
    {
      localNativeRegExp.sealObject();
      paramContext.sealObject();
    }
    defineProperty(paramScriptable, "RegExp", paramContext, 2);
  }
  
  private static boolean isControlLetter(char paramChar)
  {
    return (('a' <= paramChar) && (paramChar <= 'z')) || (('A' <= paramChar) && (paramChar <= 'Z'));
  }
  
  static boolean isDigit(char paramChar)
  {
    return ('0' <= paramChar) && (paramChar <= '9');
  }
  
  private static boolean isLineTerm(char paramChar)
  {
    return ScriptRuntime.isJSLineTerminator(paramChar);
  }
  
  private static boolean isREWhiteSpace(int paramInt)
  {
    return ScriptRuntime.isJSWhitespaceOrLineTerminator(paramInt);
  }
  
  private static boolean isWord(char paramChar)
  {
    return (('a' <= paramChar) && (paramChar <= 'z')) || (('A' <= paramChar) && (paramChar <= 'Z')) || (isDigit(paramChar)) || (paramChar == '_');
  }
  
  private static boolean matchRegExp(REGlobalData paramREGlobalData, RECompiled paramRECompiled, String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    label44:
    int m;
    if (paramRECompiled.parenCount != 0)
    {
      paramREGlobalData.parens = new long[paramRECompiled.parenCount];
      paramREGlobalData.backTrackStackTop = null;
      paramREGlobalData.stateStackTop = null;
      if ((!paramBoolean) && ((paramRECompiled.flags & 0x4) == 0)) {
        break label100;
      }
      paramBoolean = true;
      paramREGlobalData.multiline = paramBoolean;
      paramREGlobalData.regexp = paramRECompiled;
      m = paramREGlobalData.regexp.anchorCh;
    }
    for (int j = paramInt1;; j = paramREGlobalData.skipped + paramInt1 + 1)
    {
      int k;
      if (j <= paramInt2)
      {
        k = j;
        if (m < 0) {
          break label155;
        }
      }
      for (;;)
      {
        if (j == paramInt2)
        {
          return false;
          paramREGlobalData.parens = null;
          break;
          label100:
          paramBoolean = false;
          break label44;
        }
        int i = paramString.charAt(j);
        k = j;
        if (i != m)
        {
          if (((paramREGlobalData.regexp.flags & 0x2) != 0) && (upcase(i) == upcase((char)m))) {
            k = j;
          }
        }
        else
        {
          label155:
          paramREGlobalData.cp = k;
          paramREGlobalData.skipped = (k - paramInt1);
          j = 0;
          while (j < paramRECompiled.parenCount)
          {
            paramREGlobalData.parens[j] = -1L;
            j += 1;
          }
        }
        j += 1;
      }
      paramBoolean = executeREBytecode(paramREGlobalData, paramString, paramInt2);
      paramREGlobalData.backTrackStackTop = null;
      paramREGlobalData.stateStackTop = null;
      if (paramBoolean) {
        return true;
      }
      if ((m == -2) && (!paramREGlobalData.multiline))
      {
        paramREGlobalData.skipped = paramInt2;
        return false;
      }
    }
  }
  
  private static boolean parseAlternative(CompilerState paramCompilerState)
  {
    Object localObject4 = null;
    char[] arrayOfChar = paramCompilerState.cpbegin;
    Object localObject3 = null;
    if ((paramCompilerState.cp == paramCompilerState.cpend) || (arrayOfChar[paramCompilerState.cp] == '|') || ((paramCompilerState.parenNesting != 0) && (arrayOfChar[paramCompilerState.cp] == ')')))
    {
      if (localObject3 == null) {}
      for (paramCompilerState.result = new RENode((byte)1);; paramCompilerState.result = ((RENode)localObject3)) {
        return true;
      }
    }
    if (!parseTerm(paramCompilerState)) {
      return false;
    }
    Object localObject1;
    Object localObject2;
    if (localObject3 == null)
    {
      localObject1 = paramCompilerState.result;
      localObject2 = localObject1;
    }
    for (;;)
    {
      localObject4 = localObject1;
      localObject3 = localObject2;
      if (((RENode)localObject1).next == null) {
        break;
      }
      localObject1 = ((RENode)localObject1).next;
      continue;
      ((RENode)localObject4).next = paramCompilerState.result;
      localObject1 = localObject4;
      localObject2 = localObject3;
    }
  }
  
  private static boolean parseDisjunction(CompilerState paramCompilerState)
  {
    if (!parseAlternative(paramCompilerState)) {}
    Object localObject;
    do
    {
      return false;
      localObject = paramCompilerState.cpbegin;
      int i = paramCompilerState.cp;
      if ((i == localObject.length) || (localObject[i] != '|')) {
        break;
      }
      paramCompilerState.cp += 1;
      localObject = new RENode((byte)31);
      ((RENode)localObject).kid = paramCompilerState.result;
    } while (!parseDisjunction(paramCompilerState));
    ((RENode)localObject).kid2 = paramCompilerState.result;
    paramCompilerState.result = ((RENode)localObject);
    byte b;
    if ((((RENode)localObject).kid.op == 14) && (((RENode)localObject).kid2.op == 14)) {
      if ((paramCompilerState.flags & 0x2) == 0)
      {
        b = 53;
        ((RENode)localObject).op = b;
        ((RENode)localObject).chr = ((RENode)localObject).kid.chr;
        ((RENode)localObject).index = ((RENode)localObject).kid2.chr;
        paramCompilerState.progLength += 13;
      }
    }
    for (;;)
    {
      return true;
      b = 54;
      break;
      if ((((RENode)localObject).kid.op == 22) && (((RENode)localObject).kid.index < 256) && (((RENode)localObject).kid2.op == 14) && ((paramCompilerState.flags & 0x2) == 0))
      {
        ((RENode)localObject).op = 55;
        ((RENode)localObject).chr = ((RENode)localObject).kid2.chr;
        ((RENode)localObject).index = ((RENode)localObject).kid.index;
        paramCompilerState.progLength += 13;
      }
      else if ((((RENode)localObject).kid.op == 14) && (((RENode)localObject).kid2.op == 22) && (((RENode)localObject).kid2.index < 256) && ((paramCompilerState.flags & 0x2) == 0))
      {
        ((RENode)localObject).op = 55;
        ((RENode)localObject).chr = ((RENode)localObject).kid.chr;
        ((RENode)localObject).index = ((RENode)localObject).kid2.index;
        paramCompilerState.progLength += 13;
      }
      else
      {
        paramCompilerState.progLength += 9;
      }
    }
  }
  
  private static boolean parseTerm(CompilerState paramCompilerState)
  {
    int i = 92;
    char[] arrayOfChar = paramCompilerState.cpbegin;
    int j = paramCompilerState.cp;
    paramCompilerState.cp = (j + 1);
    char c = arrayOfChar[j];
    int i1 = paramCompilerState.parenCount;
    RENode localRENode;
    switch (c)
    {
    default: 
      paramCompilerState.result = new RENode((byte)14);
      paramCompilerState.result.chr = c;
      paramCompilerState.result.length = 1;
      paramCompilerState.result.flatIndex = (paramCompilerState.cp - 1);
      paramCompilerState.progLength += 3;
    case '^': 
    case '$': 
    case '\\': 
      for (;;)
      {
        localRENode = paramCompilerState.result;
        if (paramCompilerState.cp != paramCompilerState.cpend) {
          break label1781;
        }
        return true;
        paramCompilerState.result = new RENode((byte)2);
        paramCompilerState.progLength += 1;
        return true;
        paramCompilerState.result = new RENode((byte)3);
        paramCompilerState.progLength += 1;
        return true;
        if (paramCompilerState.cp >= paramCompilerState.cpend) {
          break;
        }
        j = paramCompilerState.cp;
        paramCompilerState.cp = (j + 1);
        c = arrayOfChar[j];
        switch (c)
        {
        default: 
          paramCompilerState.result = new RENode((byte)14);
          paramCompilerState.result.chr = c;
          paramCompilerState.result.length = 1;
          paramCompilerState.result.flatIndex = (paramCompilerState.cp - 1);
          paramCompilerState.progLength += 3;
          break;
        case 'b': 
          paramCompilerState.result = new RENode((byte)4);
          paramCompilerState.progLength += 1;
          return true;
        case 'B': 
          paramCompilerState.result = new RENode((byte)5);
          paramCompilerState.progLength += 1;
          return true;
        case '0': 
          reportWarning(paramCompilerState.cx, "msg.bad.backref", "");
          for (j = 0; (j < 32) && (paramCompilerState.cp < paramCompilerState.cpend); j = j * 8 + (k - 48))
          {
            k = arrayOfChar[paramCompilerState.cp];
            if ((k < 48) || (k > 55)) {
              break;
            }
            paramCompilerState.cp += 1;
          }
          doFlat(paramCompilerState, (char)j);
          break;
        case '1': 
        case '2': 
        case '3': 
        case '4': 
        case '5': 
        case '6': 
        case '7': 
        case '8': 
        case '9': 
          j = paramCompilerState.cp;
          k = getDecimalValue(c, paramCompilerState, 65535, "msg.overlarge.backref");
          if (k > paramCompilerState.backReferenceLimit) {
            reportWarning(paramCompilerState.cx, "msg.bad.backref", "");
          }
          if (k > paramCompilerState.backReferenceLimit)
          {
            paramCompilerState.cp = (j - 1);
            if (c >= '8')
            {
              doFlat(paramCompilerState, '\\');
            }
            else
            {
              paramCompilerState.cp += 1;
              for (j = c - '0'; (j < 32) && (paramCompilerState.cp < paramCompilerState.cpend); j = j * 8 + (k - 48))
              {
                k = arrayOfChar[paramCompilerState.cp];
                if ((k < 48) || (k > 55)) {
                  break;
                }
                paramCompilerState.cp += 1;
              }
              doFlat(paramCompilerState, (char)j);
            }
          }
          else
          {
            paramCompilerState.result = new RENode((byte)13);
            paramCompilerState.result.parenIndex = (k - 1);
            paramCompilerState.progLength += 3;
            if (paramCompilerState.maxBackReference < k) {
              paramCompilerState.maxBackReference = k;
            }
          }
          break;
        case 'f': 
          doFlat(paramCompilerState, '\f');
          break;
        case 'n': 
          doFlat(paramCompilerState, '\n');
          break;
        case 'r': 
          doFlat(paramCompilerState, '\r');
          break;
        case 't': 
          doFlat(paramCompilerState, '\t');
          break;
        case 'v': 
          doFlat(paramCompilerState, '\013');
        }
      }
      if ((paramCompilerState.cp < paramCompilerState.cpend) && (isControlLetter(arrayOfChar[paramCompilerState.cp])))
      {
        j = paramCompilerState.cp;
        paramCompilerState.cp = (j + 1);
        i = (char)(arrayOfChar[j] & 0x1F);
      }
      for (;;)
      {
        doFlat(paramCompilerState, i);
        break;
        paramCompilerState.cp -= 1;
      }
    }
    for (int k = 4;; k = 2)
    {
      int m = 0;
      j = 0;
      label1012:
      int n;
      if ((m < k) && (paramCompilerState.cp < paramCompilerState.cpend))
      {
        n = paramCompilerState.cp;
        paramCompilerState.cp = (n + 1);
        j = Kit.xDigitToInt(arrayOfChar[n], j);
        if (j < 0)
        {
          paramCompilerState.cp -= m + 2;
          j = paramCompilerState.cp;
          paramCompilerState.cp = (j + 1);
          j = arrayOfChar[j];
        }
      }
      for (;;)
      {
        doFlat(paramCompilerState, (char)j);
        break;
        m += 1;
        break label1012;
        paramCompilerState.result = new RENode((byte)7);
        paramCompilerState.progLength += 1;
        break;
        paramCompilerState.result = new RENode((byte)8);
        paramCompilerState.progLength += 1;
        break;
        paramCompilerState.result = new RENode((byte)11);
        paramCompilerState.progLength += 1;
        break;
        paramCompilerState.result = new RENode((byte)12);
        paramCompilerState.progLength += 1;
        break;
        paramCompilerState.result = new RENode((byte)9);
        paramCompilerState.progLength += 1;
        break;
        paramCompilerState.result = new RENode((byte)10);
        paramCompilerState.progLength += 1;
        break;
        reportError("msg.trail.backslash", "");
        return false;
        localRENode = null;
        j = paramCompilerState.cp;
        if ((paramCompilerState.cp + 1 < paramCompilerState.cpend) && (arrayOfChar[paramCompilerState.cp] == '?'))
        {
          j = arrayOfChar[(paramCompilerState.cp + 1)];
          if ((j == 61) || (j == 33) || (j == 58))
          {
            paramCompilerState.cp += 2;
            if (j == 61)
            {
              localRENode = new RENode((byte)41);
              paramCompilerState.progLength += 4;
            }
          }
        }
        for (;;)
        {
          paramCompilerState.parenNesting += 1;
          if (parseDisjunction(paramCompilerState)) {
            break;
          }
          return false;
          if (j == 33)
          {
            localRENode = new RENode((byte)42);
            paramCompilerState.progLength += 4;
            continue;
            localRENode = new RENode((byte)29);
            paramCompilerState.progLength += 6;
            j = paramCompilerState.parenCount;
            paramCompilerState.parenCount = (j + 1);
            localRENode.parenIndex = j;
          }
        }
        if ((paramCompilerState.cp == paramCompilerState.cpend) || (arrayOfChar[paramCompilerState.cp] != ')'))
        {
          reportError("msg.unterm.paren", "");
          return false;
        }
        paramCompilerState.cp += 1;
        paramCompilerState.parenNesting -= 1;
        if (localRENode == null) {
          break;
        }
        localRENode.kid = paramCompilerState.result;
        paramCompilerState.result = localRENode;
        break;
        reportError("msg.re.unmatched.right.paren", "");
        return false;
        paramCompilerState.result = new RENode((byte)22);
        j = paramCompilerState.cp;
        paramCompilerState.result.startIndex = j;
        if (paramCompilerState.cp == paramCompilerState.cpend)
        {
          reportError("msg.unterm.class", "");
          return false;
        }
        if (arrayOfChar[paramCompilerState.cp] == '\\') {
          paramCompilerState.cp += 1;
        }
        while (arrayOfChar[paramCompilerState.cp] != ']')
        {
          paramCompilerState.cp += 1;
          break;
        }
        paramCompilerState.result.kidlen = (paramCompilerState.cp - j);
        localRENode = paramCompilerState.result;
        k = paramCompilerState.classCount;
        paramCompilerState.classCount = (k + 1);
        localRENode.index = k;
        localRENode = paramCompilerState.result;
        k = paramCompilerState.cp;
        paramCompilerState.cp = (k + 1);
        if (!calculateBitmapSize(paramCompilerState, localRENode, arrayOfChar, j, k)) {
          return false;
        }
        paramCompilerState.progLength += 3;
        break;
        paramCompilerState.result = new RENode((byte)6);
        paramCompilerState.progLength += 1;
        break;
        reportError("msg.bad.quant", String.valueOf(arrayOfChar[(paramCompilerState.cp - 1)]));
        return false;
        label1781:
        switch (arrayOfChar[paramCompilerState.cp])
        {
        default: 
          j = 0;
        }
        int i2;
        for (;;)
        {
          if (j == 0)
          {
            return true;
            paramCompilerState.result = new RENode((byte)25);
            paramCompilerState.result.min = 1;
            paramCompilerState.result.max = -1;
            paramCompilerState.progLength += 8;
            j = 1;
            continue;
            paramCompilerState.result = new RENode((byte)25);
            paramCompilerState.result.min = 0;
            paramCompilerState.result.max = -1;
            paramCompilerState.progLength += 8;
            j = 1;
            continue;
            paramCompilerState.result = new RENode((byte)25);
            paramCompilerState.result.min = 0;
            paramCompilerState.result.max = 1;
            paramCompilerState.progLength += 8;
            j = 1;
            continue;
            i2 = paramCompilerState.cp;
            j = paramCompilerState.cp + 1;
            paramCompilerState.cp = j;
            if (j >= arrayOfChar.length) {
              break label2306;
            }
            i = arrayOfChar[paramCompilerState.cp];
            if (!isDigit(i)) {
              break label2306;
            }
            paramCompilerState.cp += 1;
            m = getDecimalValue(i, paramCompilerState, 65535, "msg.overlarge.min");
            k = arrayOfChar[paramCompilerState.cp];
            if (k == 44)
            {
              j = paramCompilerState.cp + 1;
              paramCompilerState.cp = j;
              i = arrayOfChar[j];
              if (!isDigit(i)) {
                break label2312;
              }
              paramCompilerState.cp += 1;
              n = getDecimalValue(i, paramCompilerState, 65535, "msg.overlarge.max");
              k = arrayOfChar[paramCompilerState.cp];
              j = n;
              if (m <= n) {
                break label2139;
              }
              reportError("msg.max.lt.min", String.valueOf(arrayOfChar[paramCompilerState.cp]));
              return false;
            }
          }
        }
        for (j = m;; j = -1)
        {
          label2139:
          if (k == 125)
          {
            paramCompilerState.result = new RENode((byte)25);
            paramCompilerState.result.min = m;
            paramCompilerState.result.max = j;
            paramCompilerState.progLength += 12;
          }
          label2306:
          for (k = 1;; k = 0)
          {
            j = k;
            if (k != 0) {
              break;
            }
            paramCompilerState.cp = i2;
            j = k;
            break;
            paramCompilerState.cp += 1;
            paramCompilerState.result.kid = localRENode;
            paramCompilerState.result.parenIndex = i1;
            paramCompilerState.result.parenCount = (paramCompilerState.parenCount - i1);
            if ((paramCompilerState.cp < paramCompilerState.cpend) && (arrayOfChar[paramCompilerState.cp] == '?')) {
              paramCompilerState.cp += 1;
            }
            for (paramCompilerState.result.greedy = false;; paramCompilerState.result.greedy = true) {
              return true;
            }
          }
          label2312:
          k = i;
        }
      }
    }
  }
  
  private static REProgState popProgState(REGlobalData paramREGlobalData)
  {
    REProgState localREProgState = paramREGlobalData.stateStackTop;
    paramREGlobalData.stateStackTop = localREProgState.previous;
    return localREProgState;
  }
  
  private static void processCharSet(REGlobalData paramREGlobalData, RECharSet paramRECharSet)
  {
    try
    {
      if (!paramRECharSet.converted)
      {
        processCharSetImpl(paramREGlobalData, paramRECharSet);
        paramRECharSet.converted = true;
      }
      return;
    }
    finally {}
  }
  
  private static void processCharSetImpl(REGlobalData paramREGlobalData, RECharSet paramRECharSet)
  {
    int j = paramRECharSet.startIndex;
    int i2 = j + paramRECharSet.strlength;
    paramRECharSet.bits = new byte[(paramRECharSet.length + 7) / 8];
    if (j == i2) {}
    for (;;)
    {
      return;
      int k;
      char c2;
      if (paramREGlobalData.regexp.source[j] == '^')
      {
        assert (!paramRECharSet.sense);
        k = 0;
        c2 = '\000';
        j += 1;
      }
      while (j != i2)
      {
        int m = 2;
        char c1;
        int i;
        switch (paramREGlobalData.regexp.source[j])
        {
        default: 
          c1 = paramREGlobalData.regexp.source[j];
          i = j + 1;
        }
        label141:
        int n;
        int i1;
        for (;;)
        {
          if (k != 0) {
            if ((paramREGlobalData.regexp.flags & 0x2) != 0) {
              if ((!$assertionsDisabled) && (c2 > c1))
              {
                throw new AssertionError();
                if (($assertionsDisabled) || (paramRECharSet.sense)) {
                  break label1166;
                }
                throw new AssertionError();
                j += 1;
                char[] arrayOfChar = paramREGlobalData.regexp.source;
                i = j + 1;
                c1 = arrayOfChar[j];
                switch (c1)
                {
                default: 
                  break;
                case 'b': 
                  c1 = '\b';
                  break;
                case 'f': 
                  c1 = '\f';
                  break;
                case 'n': 
                  c1 = '\n';
                  break;
                case 'r': 
                  c1 = '\r';
                  break;
                case 't': 
                  c1 = '\t';
                  break;
                case 'v': 
                  c1 = '\013';
                  break;
                case 'c': 
                  if ((i < i2) && (isControlLetter(paramREGlobalData.regexp.source[i])))
                  {
                    arrayOfChar = paramREGlobalData.regexp.source;
                    j = i + 1;
                    c1 = (char)(arrayOfChar[i] & 0x1F);
                    i = j;
                  }
                  else
                  {
                    i -= 1;
                    c1 = '\\';
                  }
                  break;
                case 'u': 
                  m = 4;
                case 'x': 
                  n = 0;
                  i1 = 0;
                  j = i;
                  i = i1;
                  label543:
                  if ((n >= m) || (j >= i2)) {
                    break label1151;
                  }
                  arrayOfChar = paramREGlobalData.regexp.source;
                  i1 = j + 1;
                  j = toASCIIHexDigit(arrayOfChar[j]);
                  if (j < 0)
                  {
                    i = i1 - (n + 1);
                    j = 92;
                  }
                  break;
                }
              }
            }
          }
        }
        for (;;)
        {
          c1 = (char)j;
          break label141;
          n += 1;
          i = j | i << 4;
          j = i1;
          break label543;
          n = c1 - '0';
          i1 = paramREGlobalData.regexp.source[i];
          m = n;
          j = i;
          if (48 <= i1)
          {
            m = n;
            j = i;
            if (i1 <= 55)
            {
              j = i + 1;
              i = i1 - 48 + n * 8;
              m = paramREGlobalData.regexp.source[j];
              if ((48 > m) || (m > 55)) {
                break label1144;
              }
              j += 1;
              m = m - 48 + i * 8;
              if (m > 255) {
                break label760;
              }
            }
          }
          for (;;)
          {
            c1 = (char)m;
            i = j;
            break label141;
            label760:
            j -= 1;
            m = i;
            continue;
            addCharacterRangeToCharSet(paramRECharSet, '0', '9');
            j = i;
            break;
            addCharacterRangeToCharSet(paramRECharSet, '\000', '/');
            addCharacterRangeToCharSet(paramRECharSet, ':', (char)(paramRECharSet.length - 1));
            j = i;
            break;
            j = paramRECharSet.length - 1;
            while (j >= 0)
            {
              if (isREWhiteSpace(j)) {
                addCharacterToCharSet(paramRECharSet, (char)j);
              }
              j -= 1;
              continue;
              j = paramRECharSet.length - 1;
              while (j >= 0)
              {
                if (!isREWhiteSpace(j)) {
                  addCharacterToCharSet(paramRECharSet, (char)j);
                }
                j -= 1;
                continue;
                j = paramRECharSet.length - 1;
                while (j >= 0)
                {
                  if (isWord((char)j)) {
                    addCharacterToCharSet(paramRECharSet, (char)j);
                  }
                  j -= 1;
                  continue;
                  j = paramRECharSet.length - 1;
                  while (j >= 0)
                  {
                    if (!isWord((char)j)) {
                      addCharacterToCharSet(paramRECharSet, (char)j);
                    }
                    j -= 1;
                    continue;
                    char c3 = c2;
                    char c4;
                    do
                    {
                      if (c3 > c1) {
                        break;
                      }
                      addCharacterToCharSet(paramRECharSet, c3);
                      c4 = upcase(c3);
                      char c5 = downcase(c3);
                      if (c3 != c4) {
                        addCharacterToCharSet(paramRECharSet, c4);
                      }
                      if (c3 != c5) {
                        addCharacterToCharSet(paramRECharSet, c5);
                      }
                      c4 = (char)(c3 + '\001');
                      c3 = c4;
                    } while (c4 != 0);
                    for (;;)
                    {
                      k = 0;
                      j = i;
                      break;
                      addCharacterRangeToCharSet(paramRECharSet, c2, c1);
                    }
                    if ((paramREGlobalData.regexp.flags & 0x2) != 0)
                    {
                      addCharacterToCharSet(paramRECharSet, upcase(c1));
                      addCharacterToCharSet(paramRECharSet, downcase(c1));
                    }
                    for (;;)
                    {
                      j = i;
                      if (i >= i2 - 1) {
                        break;
                      }
                      j = i;
                      if (paramREGlobalData.regexp.source[i] != '-') {
                        break;
                      }
                      j = i + 1;
                      k = 1;
                      c2 = c1;
                      break;
                      addCharacterToCharSet(paramRECharSet, c1);
                    }
                  }
                }
              }
            }
            j = i;
            break;
            label1144:
            m = i;
          }
          label1151:
          m = j;
          j = i;
          i = m;
        }
        label1166:
        k = 0;
        c2 = '\000';
      }
    }
  }
  
  private static void pushBackTrackState(REGlobalData paramREGlobalData, byte paramByte, int paramInt)
  {
    REProgState localREProgState = paramREGlobalData.stateStackTop;
    paramREGlobalData.backTrackStackTop = new REBackTrackData(paramREGlobalData, paramByte, paramInt, paramREGlobalData.cp, localREProgState.continuationOp, localREProgState.continuationPc);
  }
  
  private static void pushBackTrackState(REGlobalData paramREGlobalData, byte paramByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramREGlobalData.backTrackStackTop = new REBackTrackData(paramREGlobalData, paramByte, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  private static void pushProgState(REGlobalData paramREGlobalData, int paramInt1, int paramInt2, int paramInt3, REBackTrackData paramREBackTrackData, int paramInt4, int paramInt5)
  {
    paramREGlobalData.stateStackTop = new REProgState(paramREGlobalData.stateStackTop, paramInt1, paramInt2, paramInt3, paramREBackTrackData, paramInt4, paramInt5);
  }
  
  private static NativeRegExp realThis(Scriptable paramScriptable, IdFunctionObject paramIdFunctionObject)
  {
    if (!(paramScriptable instanceof NativeRegExp)) {
      throw incompatibleCallError(paramIdFunctionObject);
    }
    return (NativeRegExp)paramScriptable;
  }
  
  private static boolean reopIsSimple(int paramInt)
  {
    return (paramInt >= 1) && (paramInt <= 23);
  }
  
  private static void reportError(String paramString1, String paramString2)
  {
    throw ScriptRuntime.constructError("SyntaxError", ScriptRuntime.getMessage1(paramString1, paramString2));
  }
  
  private static void reportWarning(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext.hasFeature(11)) {
      Context.reportWarning(ScriptRuntime.getMessage1(paramString1, paramString2));
    }
  }
  
  private static void resolveForwardJump(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramInt1 > paramInt2) {
      throw Kit.codeBug();
    }
    addIndex(paramArrayOfByte, paramInt1, paramInt2 - paramInt1);
  }
  
  private static int simpleMatch(REGlobalData paramREGlobalData, String paramString, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int j = 0;
    int m = 0;
    int n = 0;
    int i = 1;
    int k = paramREGlobalData.cp;
    switch (paramInt1)
    {
    case 20: 
    case 21: 
    default: 
      throw Kit.codeBug();
    case 1: 
      m = 1;
      paramInt1 = paramInt2;
    }
    for (;;)
    {
      if (m != 0)
      {
        if (!paramBoolean) {
          paramREGlobalData.cp = k;
        }
        return paramInt1;
        if (paramREGlobalData.cp != 0)
        {
          paramInt1 = paramInt2;
          if (!paramREGlobalData.multiline) {
            break label1216;
          }
          if (!isLineTerm(paramString.charAt(paramREGlobalData.cp - 1)))
          {
            paramInt1 = paramInt2;
            m = n;
            continue;
          }
        }
        m = 1;
        paramInt1 = paramInt2;
        continue;
        if (paramREGlobalData.cp != paramInt3)
        {
          paramInt1 = paramInt2;
          if (!paramREGlobalData.multiline) {
            break label1216;
          }
          if (!isLineTerm(paramString.charAt(paramREGlobalData.cp)))
          {
            paramInt1 = paramInt2;
            m = n;
            continue;
          }
        }
        m = 1;
        paramInt1 = paramInt2;
        continue;
        if ((paramREGlobalData.cp == 0) || (!isWord(paramString.charAt(paramREGlobalData.cp - 1)))) {}
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          if (paramREGlobalData.cp < paramInt3)
          {
            paramInt3 = j;
            if (isWord(paramString.charAt(paramREGlobalData.cp))) {}
          }
          else
          {
            paramInt3 = 1;
          }
          m = paramInt3 ^ paramInt1;
          paramInt1 = paramInt2;
          break;
        }
        if ((paramREGlobalData.cp == 0) || (!isWord(paramString.charAt(paramREGlobalData.cp - 1))))
        {
          paramInt1 = 1;
          label353:
          if ((paramREGlobalData.cp >= paramInt3) || (!isWord(paramString.charAt(paramREGlobalData.cp)))) {
            break label397;
          }
        }
        label397:
        for (paramInt3 = i;; paramInt3 = 0)
        {
          m = paramInt1 ^ paramInt3;
          paramInt1 = paramInt2;
          break;
          paramInt1 = 0;
          break label353;
        }
        paramInt1 = paramInt2;
        if (paramREGlobalData.cp != paramInt3)
        {
          paramInt1 = paramInt2;
          if (!isLineTerm(paramString.charAt(paramREGlobalData.cp)))
          {
            paramREGlobalData.cp += 1;
            m = 1;
            paramInt1 = paramInt2;
            continue;
            paramInt1 = paramInt2;
            if (paramREGlobalData.cp != paramInt3)
            {
              paramInt1 = paramInt2;
              if (isDigit(paramString.charAt(paramREGlobalData.cp)))
              {
                paramREGlobalData.cp += 1;
                m = 1;
                paramInt1 = paramInt2;
                continue;
                paramInt1 = paramInt2;
                if (paramREGlobalData.cp != paramInt3)
                {
                  paramInt1 = paramInt2;
                  if (!isDigit(paramString.charAt(paramREGlobalData.cp)))
                  {
                    paramREGlobalData.cp += 1;
                    m = 1;
                    paramInt1 = paramInt2;
                    continue;
                    paramInt1 = paramInt2;
                    if (paramREGlobalData.cp != paramInt3)
                    {
                      paramInt1 = paramInt2;
                      if (isWord(paramString.charAt(paramREGlobalData.cp)))
                      {
                        paramREGlobalData.cp += 1;
                        m = 1;
                        paramInt1 = paramInt2;
                        continue;
                        paramInt1 = paramInt2;
                        if (paramREGlobalData.cp != paramInt3)
                        {
                          paramInt1 = paramInt2;
                          if (!isWord(paramString.charAt(paramREGlobalData.cp)))
                          {
                            paramREGlobalData.cp += 1;
                            m = 1;
                            paramInt1 = paramInt2;
                            continue;
                            paramInt1 = paramInt2;
                            if (paramREGlobalData.cp != paramInt3)
                            {
                              paramInt1 = paramInt2;
                              if (isREWhiteSpace(paramString.charAt(paramREGlobalData.cp)))
                              {
                                paramREGlobalData.cp += 1;
                                m = 1;
                                paramInt1 = paramInt2;
                                continue;
                                paramInt1 = paramInt2;
                                if (paramREGlobalData.cp != paramInt3)
                                {
                                  paramInt1 = paramInt2;
                                  if (!isREWhiteSpace(paramString.charAt(paramREGlobalData.cp)))
                                  {
                                    paramREGlobalData.cp += 1;
                                    m = 1;
                                    paramInt1 = paramInt2;
                                    continue;
                                    bool = backrefMatcher(paramREGlobalData, getIndex(paramArrayOfByte, paramInt2), paramString, paramInt3);
                                    paramInt1 = paramInt2 + 2;
                                    continue;
                                    paramInt1 = getIndex(paramArrayOfByte, paramInt2);
                                    paramInt2 += 2;
                                    bool = flatNMatcher(paramREGlobalData, paramInt1, getIndex(paramArrayOfByte, paramInt2), paramString, paramInt3);
                                    paramInt1 = paramInt2 + 2;
                                    continue;
                                    i = paramInt2 + 1;
                                    paramInt2 = (char)(paramArrayOfByte[paramInt2] & 0xFF);
                                    paramInt1 = i;
                                    bool = n;
                                    if (paramREGlobalData.cp == paramInt3) {
                                      continue;
                                    }
                                    paramInt1 = i;
                                    bool = n;
                                    if (paramString.charAt(paramREGlobalData.cp) != paramInt2) {
                                      continue;
                                    }
                                    paramREGlobalData.cp += 1;
                                    bool = true;
                                    paramInt1 = i;
                                    continue;
                                    paramInt1 = getIndex(paramArrayOfByte, paramInt2);
                                    paramInt2 += 2;
                                    bool = flatNIMatcher(paramREGlobalData, paramInt1, getIndex(paramArrayOfByte, paramInt2), paramString, paramInt3);
                                    paramInt1 = paramInt2 + 2;
                                    continue;
                                    i = paramInt2 + 1;
                                    char c1 = (char)(paramArrayOfByte[paramInt2] & 0xFF);
                                    paramInt1 = i;
                                    bool = n;
                                    if (paramREGlobalData.cp == paramInt3) {
                                      continue;
                                    }
                                    char c2 = paramString.charAt(paramREGlobalData.cp);
                                    if (c1 != c2)
                                    {
                                      paramInt1 = i;
                                      bool = n;
                                      if (upcase(c1) != upcase(c2)) {
                                        continue;
                                      }
                                    }
                                    paramREGlobalData.cp += 1;
                                    bool = true;
                                    paramInt1 = i;
                                    continue;
                                    i = (char)getIndex(paramArrayOfByte, paramInt2);
                                    paramInt2 += 2;
                                    paramInt1 = paramInt2;
                                    if (paramREGlobalData.cp != paramInt3)
                                    {
                                      paramInt1 = paramInt2;
                                      if (paramString.charAt(paramREGlobalData.cp) == i)
                                      {
                                        paramREGlobalData.cp += 1;
                                        bool = true;
                                        paramInt1 = paramInt2;
                                        continue;
                                        c1 = (char)getIndex(paramArrayOfByte, paramInt2);
                                        paramInt2 += 2;
                                        paramInt1 = paramInt2;
                                        if (paramREGlobalData.cp != paramInt3)
                                        {
                                          c2 = paramString.charAt(paramREGlobalData.cp);
                                          if ((c1 == c2) || (upcase(c1) == upcase(c2)))
                                          {
                                            paramREGlobalData.cp += 1;
                                            bool = true;
                                          }
                                          paramInt1 = paramInt2;
                                          continue;
                                          i = getIndex(paramArrayOfByte, paramInt2);
                                          paramInt2 += 2;
                                          paramInt1 = paramInt2;
                                          if (paramREGlobalData.cp != paramInt3)
                                          {
                                            paramInt1 = paramInt2;
                                            if (classMatcher(paramREGlobalData, paramREGlobalData.regexp.classList[i], paramString.charAt(paramREGlobalData.cp)))
                                            {
                                              paramREGlobalData.cp += 1;
                                              bool = true;
                                              paramInt1 = paramInt2;
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
                    }
                  }
                }
              }
            }
          }
        }
      }
      else
      {
        paramREGlobalData.cp = k;
        return -1;
      }
      label1216:
      boolean bool = n;
    }
  }
  
  private static int toASCIIHexDigit(int paramInt)
  {
    if (paramInt < 48) {}
    do
    {
      return -1;
      if (paramInt <= 57) {
        return paramInt - 48;
      }
      paramInt |= 0x20;
    } while ((97 > paramInt) || (paramInt > 102));
    return paramInt - 97 + 10;
  }
  
  private static char upcase(char paramChar)
  {
    char c1;
    if (paramChar < '?')
    {
      c1 = paramChar;
      if ('a' <= paramChar)
      {
        c1 = paramChar;
        if (paramChar <= 'z') {
          c1 = (char)(paramChar - ' ');
        }
      }
    }
    char c2;
    do
    {
      return c1;
      c2 = Character.toUpperCase(paramChar);
      c1 = paramChar;
    } while (c2 < '?');
    return c2;
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    return execSub(paramContext, paramScriptable1, paramArrayOfObject, 1);
  }
  
  Scriptable compile(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof NativeRegExp)))
    {
      if ((paramArrayOfObject.length > 1) && (paramArrayOfObject[1] != Undefined.instance)) {
        throw ScriptRuntime.typeError0("msg.bad.regexp.compile");
      }
      paramContext = (NativeRegExp)paramArrayOfObject[0];
      this.re = paramContext.re;
      this.lastIndex = paramContext.lastIndex;
      return this;
    }
    if ((paramArrayOfObject.length == 0) || ((paramArrayOfObject[0] instanceof Undefined)))
    {
      paramScriptable = "";
      if ((paramArrayOfObject.length <= 1) || (paramArrayOfObject[1] == Undefined.instance)) {
        break label132;
      }
    }
    label132:
    for (paramArrayOfObject = ScriptRuntime.toString(paramArrayOfObject[1]);; paramArrayOfObject = null)
    {
      this.re = compileRE(paramContext, paramScriptable, paramArrayOfObject, false);
      this.lastIndex = Double.valueOf(0.0D);
      return this;
      paramScriptable = escapeRegExp(paramArrayOfObject[0]);
      break;
    }
  }
  
  public Scriptable construct(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    return (Scriptable)execSub(paramContext, paramScriptable, paramArrayOfObject, 1);
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(REGEXP_TAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i = paramIdFunctionObject.methodId();
    switch (i)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    case 1: 
      return realThis(paramScriptable2, paramIdFunctionObject).compile(paramContext, paramScriptable1, paramArrayOfObject);
    case 2: 
    case 3: 
      return realThis(paramScriptable2, paramIdFunctionObject).toString();
    case 4: 
      return realThis(paramScriptable2, paramIdFunctionObject).execSub(paramContext, paramScriptable1, paramArrayOfObject, 1);
    case 5: 
      paramIdFunctionObject = realThis(paramScriptable2, paramIdFunctionObject).execSub(paramContext, paramScriptable1, paramArrayOfObject, 0);
      if (Boolean.TRUE.equals(paramIdFunctionObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    return realThis(paramScriptable2, paramIdFunctionObject).execSub(paramContext, paramScriptable1, paramArrayOfObject, 2);
  }
  
  Object executeRegExp(Context paramContext, Scriptable paramScriptable, RegExpImpl paramRegExpImpl, String paramString, int[] paramArrayOfInt, int paramInt)
  {
    REGlobalData localREGlobalData = new REGlobalData();
    int j = paramArrayOfInt[0];
    int k = paramString.length();
    int i = j;
    if (j > k) {
      i = k;
    }
    if (!matchRegExp(localREGlobalData, this.re, paramString, i, k, paramRegExpImpl.multiline))
    {
      if (paramInt != 2) {
        return null;
      }
      return Undefined.instance;
    }
    int m = localREGlobalData.cp;
    paramArrayOfInt[0] = m;
    int n = m - (localREGlobalData.skipped + i);
    int i1 = m - n;
    if (paramInt == 0)
    {
      paramArrayOfInt = Boolean.TRUE;
      paramScriptable = null;
      if (this.re.parenCount != 0) {
        break label352;
      }
      paramRegExpImpl.parens = null;
      paramRegExpImpl.lastParen = SubString.emptySubString;
      label138:
      if (paramInt != 0)
      {
        paramScriptable.put("index", paramScriptable, Integer.valueOf(localREGlobalData.skipped + i));
        paramScriptable.put("input", paramScriptable, paramString);
      }
      if (paramRegExpImpl.lastMatch == null)
      {
        paramRegExpImpl.lastMatch = new SubString();
        paramRegExpImpl.leftContext = new SubString();
        paramRegExpImpl.rightContext = new SubString();
      }
      paramRegExpImpl.lastMatch.str = paramString;
      paramRegExpImpl.lastMatch.index = i1;
      paramRegExpImpl.lastMatch.length = n;
      paramRegExpImpl.leftContext.str = paramString;
      if (paramContext.getLanguageVersion() != 120) {
        break label509;
      }
      paramRegExpImpl.leftContext.index = i;
    }
    for (paramRegExpImpl.leftContext.length = localREGlobalData.skipped;; paramRegExpImpl.leftContext.length = (localREGlobalData.skipped + i))
    {
      paramRegExpImpl.rightContext.str = paramString;
      paramRegExpImpl.rightContext.index = m;
      paramRegExpImpl.rightContext.length = (k - m);
      return paramArrayOfInt;
      paramArrayOfInt = paramContext.newArray(paramScriptable, 0);
      paramScriptable = (Scriptable)paramArrayOfInt;
      paramScriptable.put(0, paramScriptable, paramString.substring(i1, i1 + n));
      break;
      label352:
      paramRegExpImpl.parens = new SubString[this.re.parenCount];
      Object localObject2 = null;
      j = 0;
      if (j < this.re.parenCount)
      {
        int i2 = localREGlobalData.parensIndex(j);
        Object localObject1;
        if (i2 != -1)
        {
          localObject2 = new SubString(paramString, i2, localREGlobalData.parensLength(j));
          paramRegExpImpl.parens[j] = localObject2;
          localObject1 = localObject2;
          if (paramInt != 0)
          {
            paramScriptable.put(j + 1, paramScriptable, ((SubString)localObject2).toString());
            localObject1 = localObject2;
          }
        }
        for (;;)
        {
          j += 1;
          localObject2 = localObject1;
          break;
          localObject1 = localObject2;
          if (paramInt != 0)
          {
            paramScriptable.put(j + 1, paramScriptable, Undefined.instance);
            localObject1 = localObject2;
          }
        }
      }
      paramRegExpImpl.lastParen = ((SubString)localObject2);
      break label138;
      label509:
      paramRegExpImpl.leftContext.index = 0;
    }
  }
  
  protected int findInstanceIdInfo(String paramString)
  {
    int i = paramString.length();
    String str;
    if (i == 6)
    {
      i = paramString.charAt(0);
      if (i == 103)
      {
        i = 3;
        str = "global";
      }
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
      if (j == 0)
      {
        return super.findInstanceIdInfo(paramString);
        if (i == 115)
        {
          i = 2;
          str = "source";
          continue;
          if (i == 9)
          {
            i = paramString.charAt(0);
            if (i == 108)
            {
              i = 1;
              str = "lastIndex";
              continue;
            }
            if (i == 109)
            {
              i = 5;
              str = "multiline";
            }
          }
          else if (i == 10)
          {
            i = 4;
            str = "ignoreCase";
          }
        }
      }
      else
      {
        switch (j)
        {
        default: 
          throw new IllegalStateException();
        }
        for (i = this.lastIndexAttr;; i = 7) {
          return instanceIdInfo(i, j);
        }
      }
      str = null;
      i = 0;
    }
  }
  
  protected int findPrototypeId(String paramString)
  {
    String str;
    int i;
    switch (paramString.length())
    {
    case 5: 
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
      if (i == 101)
      {
        i = 4;
        str = "exec";
      }
      else
      {
        if (i != 116) {
          break;
        }
        i = 5;
        str = "test";
        continue;
        i = 6;
        str = "prefix";
        continue;
        i = 1;
        str = "compile";
        continue;
        i = paramString.charAt(3);
        if (i == 111)
        {
          i = 3;
          str = "toSource";
        }
        else
        {
          if (i != 116) {
            break;
          }
          i = 2;
          str = "toString";
        }
      }
    }
    return i;
  }
  
  public String getClassName()
  {
    return "RegExp";
  }
  
  int getFlags()
  {
    return this.re.flags;
  }
  
  protected String getInstanceIdName(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return super.getInstanceIdName(paramInt);
    case 1: 
      return "lastIndex";
    case 2: 
      return "source";
    case 3: 
      return "global";
    case 4: 
      return "ignoreCase";
    }
    return "multiline";
  }
  
  protected Object getInstanceIdValue(int paramInt)
  {
    boolean bool2 = true;
    boolean bool3 = true;
    boolean bool1 = true;
    switch (paramInt)
    {
    default: 
      return super.getInstanceIdValue(paramInt);
    case 1: 
      return this.lastIndex;
    case 2: 
      return new String(this.re.source);
    case 3: 
      if ((this.re.flags & 0x1) != 0) {}
      for (;;)
      {
        return ScriptRuntime.wrapBoolean(bool1);
        bool1 = false;
      }
    case 4: 
      if ((this.re.flags & 0x2) != 0) {}
      for (bool1 = bool2;; bool1 = false) {
        return ScriptRuntime.wrapBoolean(bool1);
      }
    }
    if ((this.re.flags & 0x4) != 0) {}
    for (bool1 = bool3;; bool1 = false) {
      return ScriptRuntime.wrapBoolean(bool1);
    }
  }
  
  protected int getMaxInstanceId()
  {
    return 5;
  }
  
  public String getTypeOf()
  {
    return "object";
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i = 0;
    String str;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      i = 2;
      str = "compile";
    }
    for (;;)
    {
      initPrototypeMethod(REGEXP_TAG, paramInt, str, i);
      return;
      str = "toString";
      continue;
      str = "toSource";
      continue;
      str = "exec";
      i = 1;
      continue;
      str = "test";
      i = 1;
      continue;
      str = "prefix";
      i = 1;
    }
  }
  
  protected void setInstanceIdAttributes(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      super.setInstanceIdAttributes(paramInt1, paramInt2);
      return;
    }
    this.lastIndexAttr = paramInt2;
  }
  
  protected void setInstanceIdValue(int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default: 
      super.setInstanceIdValue(paramInt, paramObject);
    case 2: 
    case 3: 
    case 4: 
    case 5: 
      return;
    }
    this.lastIndex = paramObject;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('/');
    if (this.re.source.length != 0) {
      localStringBuilder.append(this.re.source);
    }
    for (;;)
    {
      localStringBuilder.append('/');
      if ((this.re.flags & 0x1) != 0) {
        localStringBuilder.append('g');
      }
      if ((this.re.flags & 0x2) != 0) {
        localStringBuilder.append('i');
      }
      if ((this.re.flags & 0x4) != 0) {
        localStringBuilder.append('m');
      }
      return localStringBuilder.toString();
      localStringBuilder.append("(?:)");
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\regexp\NativeRegExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */