package org.mozilla.javascript;

public class NativeCallSite
  extends IdScriptableObject
{
  private static final String CALLSITE_TAG = "CallSite";
  private static final int Id_constructor = 1;
  private static final int Id_getColumnNumber = 9;
  private static final int Id_getEvalOrigin = 10;
  private static final int Id_getFileName = 7;
  private static final int Id_getFunction = 4;
  private static final int Id_getFunctionName = 5;
  private static final int Id_getLineNumber = 8;
  private static final int Id_getMethodName = 6;
  private static final int Id_getThis = 2;
  private static final int Id_getTypeName = 3;
  private static final int Id_isConstructor = 14;
  private static final int Id_isEval = 12;
  private static final int Id_isNative = 13;
  private static final int Id_isToplevel = 11;
  private static final int Id_toString = 15;
  private static final int MAX_PROTOTYPE_ID = 15;
  private ScriptStackElement element;
  
  private Object getFalse()
  {
    return Boolean.FALSE;
  }
  
  private Object getFileName(Scriptable paramScriptable)
  {
    while ((paramScriptable != null) && (!(paramScriptable instanceof NativeCallSite))) {
      paramScriptable = paramScriptable.getPrototype();
    }
    if (paramScriptable == null) {
      return NOT_FOUND;
    }
    paramScriptable = (NativeCallSite)paramScriptable;
    if (paramScriptable.element == null) {
      return null;
    }
    return paramScriptable.element.fileName;
  }
  
  private Object getFunctionName(Scriptable paramScriptable)
  {
    while ((paramScriptable != null) && (!(paramScriptable instanceof NativeCallSite))) {
      paramScriptable = paramScriptable.getPrototype();
    }
    if (paramScriptable == null) {
      return NOT_FOUND;
    }
    paramScriptable = (NativeCallSite)paramScriptable;
    if (paramScriptable.element == null) {
      return null;
    }
    return paramScriptable.element.functionName;
  }
  
  private Object getLineNumber(Scriptable paramScriptable)
  {
    while ((paramScriptable != null) && (!(paramScriptable instanceof NativeCallSite))) {
      paramScriptable = paramScriptable.getPrototype();
    }
    if (paramScriptable == null) {
      return NOT_FOUND;
    }
    paramScriptable = (NativeCallSite)paramScriptable;
    if ((paramScriptable.element == null) || (paramScriptable.element.lineNumber < 0)) {
      return Undefined.instance;
    }
    return Integer.valueOf(paramScriptable.element.lineNumber);
  }
  
  private Object getNull()
  {
    return null;
  }
  
  private Object getUndefined()
  {
    return Undefined.instance;
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    new NativeCallSite().exportAsJSClass(15, paramScriptable, paramBoolean);
  }
  
  private Object js_toString(Scriptable paramScriptable)
  {
    while ((paramScriptable != null) && (!(paramScriptable instanceof NativeCallSite))) {
      paramScriptable = paramScriptable.getPrototype();
    }
    if (paramScriptable == null) {
      return NOT_FOUND;
    }
    paramScriptable = (NativeCallSite)paramScriptable;
    StringBuilder localStringBuilder = new StringBuilder();
    paramScriptable.element.renderJavaStyle(localStringBuilder);
    return localStringBuilder.toString();
  }
  
  static NativeCallSite make(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    NativeCallSite localNativeCallSite = new NativeCallSite();
    paramScriptable2 = (Scriptable)paramScriptable2.get("prototype", paramScriptable2);
    localNativeCallSite.setParentScope(paramScriptable1);
    localNativeCallSite.setPrototype(paramScriptable2);
    return localNativeCallSite;
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag("CallSite")) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i = paramIdFunctionObject.methodId();
    switch (i)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    case 1: 
      return make(paramScriptable1, paramIdFunctionObject);
    case 5: 
      return getFunctionName(paramScriptable2);
    case 7: 
      return getFileName(paramScriptable2);
    case 8: 
      return getLineNumber(paramScriptable2);
    case 2: 
    case 3: 
    case 4: 
    case 9: 
      return getUndefined();
    case 6: 
      return getNull();
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
      return getFalse();
    }
    return js_toString(paramScriptable2);
  }
  
  protected int findPrototypeId(String paramString)
  {
    label60:
    String str;
    int i;
    switch (paramString.length())
    {
    case 9: 
    case 12: 
    case 14: 
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
      i = 12;
      str = "isEval";
      continue;
      i = 2;
      str = "getThis";
      continue;
      i = paramString.charAt(0);
      if (i == 105)
      {
        i = 13;
        str = "isNative";
      }
      else
      {
        if (i != 116) {
          break;
        }
        i = 15;
        str = "toString";
        continue;
        i = 11;
        str = "isToplevel";
        continue;
        switch (paramString.charAt(4))
        {
        default: 
          str = null;
          i = 0;
          break;
        case 'i': 
          i = 7;
          str = "getFileName";
          break;
        case 't': 
          i = 1;
          str = "constructor";
          break;
        case 'u': 
          i = 4;
          str = "getFunction";
          break;
        case 'y': 
          str = "getTypeName";
          i = 3;
          continue;
          switch (paramString.charAt(3))
          {
          default: 
            str = null;
            i = 0;
            break;
          case 'E': 
            i = 10;
            str = "getEvalOrigin";
            break;
          case 'L': 
            i = 8;
            str = "getLineNumber";
            break;
          case 'M': 
            i = 6;
            str = "getMethodName";
            break;
          case 'o': 
            i = 14;
            str = "isConstructor";
            continue;
            i = paramString.charAt(3);
            if (i == 67)
            {
              i = 9;
              str = "getColumnNumber";
            }
            else
            {
              if (i != 70) {
                break label60;
              }
              i = 5;
              str = "getFunctionName";
            }
            break;
          }
          break;
        }
      }
    }
    return i;
  }
  
  public String getClassName()
  {
    return "CallSite";
  }
  
  protected void initPrototypeId(int paramInt)
  {
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
      initPrototypeMethod("CallSite", paramInt, str, 0);
      return;
      str = "getThis";
      continue;
      str = "getTypeName";
      continue;
      str = "getFunction";
      continue;
      str = "getFunctionName";
      continue;
      str = "getMethodName";
      continue;
      str = "getFileName";
      continue;
      str = "getLineNumber";
      continue;
      str = "getColumnNumber";
      continue;
      str = "getEvalOrigin";
      continue;
      str = "isToplevel";
      continue;
      str = "isEval";
      continue;
      str = "isNative";
      continue;
      str = "isConstructor";
      continue;
      str = "toString";
    }
  }
  
  void setElement(ScriptStackElement paramScriptStackElement)
  {
    this.element = paramScriptStackElement;
  }
  
  public String toString()
  {
    if (this.element == null) {
      return "";
    }
    return this.element.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeCallSite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */