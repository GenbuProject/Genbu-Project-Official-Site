package org.mozilla.javascript;

class NativeScript
  extends BaseFunction
{
  private static final int Id_compile = 3;
  private static final int Id_constructor = 1;
  private static final int Id_exec = 4;
  private static final int Id_toString = 2;
  private static final int MAX_PROTOTYPE_ID = 4;
  private static final Object SCRIPT_TAG = "Script";
  static final long serialVersionUID = -6795101161980121700L;
  private Script script;
  
  private NativeScript(Script paramScript)
  {
    this.script = paramScript;
  }
  
  private static Script compile(Context paramContext, String paramString)
  {
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 0;
    String str2 = Context.getSourcePositionFromStack(arrayOfInt);
    String str1 = str2;
    if (str2 == null)
    {
      str1 = "<Script object>";
      arrayOfInt[0] = 1;
    }
    return paramContext.compileString(paramString, null, DefaultErrorReporter.forEval(paramContext.getErrorReporter()), str1, arrayOfInt[0], null);
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    new NativeScript(null).exportAsJSClass(4, paramScriptable, paramBoolean);
  }
  
  private static NativeScript realThis(Scriptable paramScriptable, IdFunctionObject paramIdFunctionObject)
  {
    if (!(paramScriptable instanceof NativeScript)) {
      throw incompatibleCallError(paramIdFunctionObject);
    }
    return (NativeScript)paramScriptable;
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (this.script != null) {
      return this.script.exec(paramContext, paramScriptable1);
    }
    return Undefined.instance;
  }
  
  public Scriptable construct(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    throw Context.reportRuntimeError0("msg.script.is.not.constructor");
  }
  
  String decompile(int paramInt1, int paramInt2)
  {
    if ((this.script instanceof NativeFunction)) {
      return ((NativeFunction)this.script).decompile(paramInt1, paramInt2);
    }
    return super.decompile(paramInt1, paramInt2);
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(SCRIPT_TAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i = paramIdFunctionObject.methodId();
    switch (i)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    case 1: 
      if (paramArrayOfObject.length == 0) {}
      for (paramIdFunctionObject = "";; paramIdFunctionObject = ScriptRuntime.toString(paramArrayOfObject[0]))
      {
        paramIdFunctionObject = new NativeScript(compile(paramContext, paramIdFunctionObject));
        ScriptRuntime.setObjectProtoAndParent(paramIdFunctionObject, paramScriptable1);
        return paramIdFunctionObject;
      }
    case 2: 
      paramIdFunctionObject = realThis(paramScriptable2, paramIdFunctionObject).script;
      if (paramIdFunctionObject == null) {
        return "";
      }
      return paramContext.decompileScript(paramIdFunctionObject, 0);
    case 4: 
      throw Context.reportRuntimeError1("msg.cant.call.indirect", "exec");
    }
    paramIdFunctionObject = realThis(paramScriptable2, paramIdFunctionObject);
    paramIdFunctionObject.script = compile(paramContext, ScriptRuntime.toString(paramArrayOfObject, 0));
    return paramIdFunctionObject;
  }
  
  protected int findPrototypeId(String paramString)
  {
    String str;
    int i;
    switch (paramString.length())
    {
    case 5: 
    case 6: 
    case 9: 
    case 10: 
    default: 
      str = null;
      i = 0;
    }
    while ((str != null) && (str != paramString) && (!str.equals(paramString)))
    {
      return 0;
      i = 4;
      str = "exec";
      continue;
      i = 3;
      str = "compile";
      continue;
      i = 2;
      str = "toString";
      continue;
      i = 1;
      str = "constructor";
    }
    return i;
  }
  
  public int getArity()
  {
    return 0;
  }
  
  public String getClassName()
  {
    return "Script";
  }
  
  public int getLength()
  {
    return 0;
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
      initPrototypeMethod(SCRIPT_TAG, paramInt, str, i);
      return;
      str = "toString";
      i = 0;
      continue;
      str = "exec";
      i = 0;
      continue;
      str = "compile";
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeScript.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */