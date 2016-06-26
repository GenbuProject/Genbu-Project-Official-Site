package org.mozilla.javascript;

final class NativeBoolean
  extends IdScriptableObject
{
  private static final Object BOOLEAN_TAG = "Boolean";
  private static final int Id_constructor = 1;
  private static final int Id_toSource = 3;
  private static final int Id_toString = 2;
  private static final int Id_valueOf = 4;
  private static final int MAX_PROTOTYPE_ID = 4;
  static final long serialVersionUID = -3716996899943880933L;
  private boolean booleanValue;
  
  NativeBoolean(boolean paramBoolean)
  {
    this.booleanValue = paramBoolean;
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    new NativeBoolean(false).exportAsJSClass(4, paramScriptable, paramBoolean);
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(BOOLEAN_TAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i = paramIdFunctionObject.methodId();
    if (i == 1)
    {
      if (paramArrayOfObject.length == 0)
      {
        bool = false;
        if (paramScriptable2 == null) {
          return new NativeBoolean(bool);
        }
      }
      else
      {
        if (((paramArrayOfObject[0] instanceof ScriptableObject)) && (((ScriptableObject)paramArrayOfObject[0]).avoidObjectDetection())) {}
        for (bool = true;; bool = ScriptRuntime.toBoolean(paramArrayOfObject[0])) {
          break;
        }
      }
      return ScriptRuntime.wrapBoolean(bool);
    }
    if (!(paramScriptable2 instanceof NativeBoolean)) {
      throw incompatibleCallError(paramIdFunctionObject);
    }
    boolean bool = ((NativeBoolean)paramScriptable2).booleanValue;
    switch (i)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    case 2: 
      if (bool) {
        return "true";
      }
      return "false";
    case 3: 
      if (bool) {
        return "(new Boolean(true))";
      }
      return "(new Boolean(false))";
    }
    return ScriptRuntime.wrapBoolean(bool);
  }
  
  protected int findPrototypeId(String paramString)
  {
    int i = paramString.length();
    String str;
    if (i == 7)
    {
      i = 4;
      str = "valueOf";
    }
    for (;;)
    {
      if ((str != null) && (str != paramString) && (!str.equals(paramString)))
      {
        return 0;
        if (i == 8)
        {
          i = paramString.charAt(3);
          if (i == 111)
          {
            i = 3;
            str = "toSource";
            continue;
          }
          if (i == 116)
          {
            i = 2;
            str = "toString";
          }
        }
        else if (i == 11)
        {
          i = 1;
          str = "constructor";
        }
      }
      else
      {
        return i;
      }
      str = null;
      i = 0;
    }
  }
  
  public String getClassName()
  {
    return "Boolean";
  }
  
  public Object getDefaultValue(Class<?> paramClass)
  {
    if (paramClass == ScriptRuntime.BooleanClass) {
      return ScriptRuntime.wrapBoolean(this.booleanValue);
    }
    return super.getDefaultValue(paramClass);
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
      i = 1;
      str = "constructor";
    }
    for (;;)
    {
      initPrototypeMethod(BOOLEAN_TAG, paramInt, str, i);
      return;
      str = "toString";
      continue;
      str = "toSource";
      continue;
      str = "valueOf";
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeBoolean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */