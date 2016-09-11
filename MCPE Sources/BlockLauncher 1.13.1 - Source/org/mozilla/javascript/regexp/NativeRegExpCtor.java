package org.mozilla.javascript.regexp;

import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel.Builtins;
import org.mozilla.javascript.Undefined;

class NativeRegExpCtor
  extends BaseFunction
{
  private static final int DOLLAR_ID_BASE = 12;
  private static final int Id_AMPERSAND = 6;
  private static final int Id_BACK_QUOTE = 10;
  private static final int Id_DOLLAR_1 = 13;
  private static final int Id_DOLLAR_2 = 14;
  private static final int Id_DOLLAR_3 = 15;
  private static final int Id_DOLLAR_4 = 16;
  private static final int Id_DOLLAR_5 = 17;
  private static final int Id_DOLLAR_6 = 18;
  private static final int Id_DOLLAR_7 = 19;
  private static final int Id_DOLLAR_8 = 20;
  private static final int Id_DOLLAR_9 = 21;
  private static final int Id_PLUS = 8;
  private static final int Id_QUOTE = 12;
  private static final int Id_STAR = 2;
  private static final int Id_UNDERSCORE = 4;
  private static final int Id_input = 3;
  private static final int Id_lastMatch = 5;
  private static final int Id_lastParen = 7;
  private static final int Id_leftContext = 9;
  private static final int Id_multiline = 1;
  private static final int Id_rightContext = 11;
  private static final int MAX_INSTANCE_ID = 21;
  static final long serialVersionUID = -5733330028285400526L;
  private int inputAttr = 4;
  private int multilineAttr = 4;
  private int starAttr = 4;
  private int underscoreAttr = 4;
  
  private static RegExpImpl getImpl()
  {
    return (RegExpImpl)ScriptRuntime.getRegExpProxy(Context.getCurrentContext());
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof NativeRegExp)) && ((paramArrayOfObject.length == 1) || (paramArrayOfObject[1] == Undefined.instance))) {
      return paramArrayOfObject[0];
    }
    return construct(paramContext, paramScriptable1, paramArrayOfObject);
  }
  
  public Scriptable construct(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    NativeRegExp localNativeRegExp = new NativeRegExp();
    localNativeRegExp.compile(paramContext, paramScriptable, paramArrayOfObject);
    ScriptRuntime.setBuiltinProtoAndParent(localNativeRegExp, paramScriptable, TopLevel.Builtins.RegExp);
    return localNativeRegExp;
  }
  
  protected int findInstanceIdInfo(String paramString)
  {
    int k = 5;
    int i = 4;
    String str = null;
    int j;
    switch (paramString.length())
    {
    case 3: 
    case 4: 
    case 6: 
    case 7: 
    case 8: 
    case 10: 
    default: 
      j = 0;
    }
    for (;;)
    {
      i = j;
      if (str != null)
      {
        i = j;
        if (str != paramString)
        {
          i = j;
          if (!str.equals(paramString)) {
            i = 0;
          }
        }
      }
      for (;;)
      {
        if (i != 0) {
          break label570;
        }
        return super.findInstanceIdInfo(paramString);
        switch (paramString.charAt(1))
        {
        default: 
          j = 0;
          break;
        case '&': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 6;
          break;
        case '\'': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 12;
          break;
        case '*': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 2;
          break;
        case '+': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 8;
          break;
        case '1': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 13;
          break;
        case '2': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 14;
          break;
        case '3': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 15;
          break;
        case '4': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 16;
          break;
        case '5': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 17;
          break;
        case '6': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 18;
          break;
        case '7': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 19;
          break;
        case '8': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 20;
          break;
        case '9': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 21;
          break;
        case '_': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          break;
        case '`': 
          if (paramString.charAt(0) != '$') {
            break;
          }
          i = 10;
        }
      }
      j = 3;
      str = "input";
      continue;
      i = paramString.charAt(4);
      if (i == 77)
      {
        str = "lastMatch";
        j = 5;
      }
      else if (i == 80)
      {
        j = 7;
        str = "lastParen";
      }
      else
      {
        if (i != 105) {
          break;
        }
        str = "multiline";
        j = 1;
        continue;
        j = 9;
        str = "leftContext";
        continue;
        j = 11;
        str = "rightContext";
      }
    }
    switch (i)
    {
    default: 
      label570:
      j = k;
    }
    for (;;)
    {
      return instanceIdInfo(j, i + super.getMaxInstanceId());
      j = this.multilineAttr;
      continue;
      j = this.starAttr;
      continue;
      j = this.inputAttr;
      continue;
      j = this.underscoreAttr;
    }
  }
  
  public int getArity()
  {
    return 2;
  }
  
  public String getFunctionName()
  {
    return "RegExp";
  }
  
  protected String getInstanceIdName(int paramInt)
  {
    int i = paramInt - super.getMaxInstanceId();
    if ((1 <= i) && (i <= 21))
    {
      switch (i)
      {
      default: 
        return new String(new char[] { '$', (char)(i - 12 - 1 + 49) });
      case 1: 
        return "multiline";
      case 2: 
        return "$*";
      case 3: 
        return "input";
      case 4: 
        return "$_";
      case 5: 
        return "lastMatch";
      case 6: 
        return "$&";
      case 7: 
        return "lastParen";
      case 8: 
        return "$+";
      case 9: 
        return "leftContext";
      case 10: 
        return "$`";
      case 11: 
        return "rightContext";
      }
      return "$'";
    }
    return super.getInstanceIdName(paramInt);
  }
  
  protected Object getInstanceIdValue(int paramInt)
  {
    int i = paramInt - super.getMaxInstanceId();
    if ((1 <= i) && (i <= 21))
    {
      Object localObject = getImpl();
      switch (i)
      {
      default: 
        localObject = ((RegExpImpl)localObject).getParenSubString(i - 12 - 1);
      }
      while (localObject == null)
      {
        return "";
        return ScriptRuntime.wrapBoolean(((RegExpImpl)localObject).multiline);
        localObject = ((RegExpImpl)localObject).input;
        continue;
        localObject = ((RegExpImpl)localObject).lastMatch;
        continue;
        localObject = ((RegExpImpl)localObject).lastParen;
        continue;
        localObject = ((RegExpImpl)localObject).leftContext;
        continue;
        localObject = ((RegExpImpl)localObject).rightContext;
      }
      return localObject.toString();
    }
    return super.getInstanceIdValue(paramInt);
  }
  
  public int getLength()
  {
    return 2;
  }
  
  protected int getMaxInstanceId()
  {
    return super.getMaxInstanceId() + 21;
  }
  
  protected void setInstanceIdAttributes(int paramInt1, int paramInt2)
  {
    int i = paramInt1 - super.getMaxInstanceId();
    switch (i)
    {
    default: 
      i = i - 12 - 1;
      if ((i < 0) || (i > 8)) {
        break;
      }
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
      return;
    case 1: 
      this.multilineAttr = paramInt2;
      return;
    case 2: 
      this.starAttr = paramInt2;
      return;
    case 3: 
      this.inputAttr = paramInt2;
      return;
    case 4: 
      this.underscoreAttr = paramInt2;
      return;
    }
    super.setInstanceIdAttributes(paramInt1, paramInt2);
  }
  
  protected void setInstanceIdValue(int paramInt, Object paramObject)
  {
    int i = paramInt - super.getMaxInstanceId();
    switch (i)
    {
    default: 
      i = i - 12 - 1;
      if ((i < 0) || (i > 8)) {
        break;
      }
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
      return;
    case 1: 
    case 2: 
      getImpl().multiline = ScriptRuntime.toBoolean(paramObject);
      return;
    case 3: 
    case 4: 
      getImpl().input = ScriptRuntime.toString(paramObject);
      return;
    }
    super.setInstanceIdValue(paramInt, paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\regexp\NativeRegExpCtor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */