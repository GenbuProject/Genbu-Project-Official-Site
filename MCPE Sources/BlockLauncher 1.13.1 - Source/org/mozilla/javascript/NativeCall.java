package org.mozilla.javascript;

public final class NativeCall
  extends IdScriptableObject
{
  private static final Object CALL_TAG = "Call";
  private static final int Id_constructor = 1;
  private static final int MAX_PROTOTYPE_ID = 1;
  static final long serialVersionUID = -7471457301304454454L;
  private Arguments arguments;
  NativeFunction function;
  boolean isStrict;
  Object[] originalArgs;
  transient NativeCall parentActivationCall;
  
  NativeCall() {}
  
  NativeCall(NativeFunction paramNativeFunction, Scriptable paramScriptable, Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.function = paramNativeFunction;
    setParentScope(paramScriptable);
    int k;
    int j;
    int i;
    label53:
    String str;
    if (paramArrayOfObject == null)
    {
      paramScriptable = ScriptRuntime.emptyArgs;
      this.originalArgs = paramScriptable;
      this.isStrict = paramBoolean2;
      k = paramNativeFunction.getParamAndVarCount();
      j = paramNativeFunction.getParamCount();
      if (k == 0) {
        break label109;
      }
      i = 0;
      if (i >= j) {
        break label109;
      }
      str = paramNativeFunction.getParamOrVarName(i);
      if (i >= paramArrayOfObject.length) {
        break label102;
      }
    }
    label102:
    for (paramScriptable = paramArrayOfObject[i];; paramScriptable = Undefined.instance)
    {
      defineProperty(str, paramScriptable, 4);
      i += 1;
      break label53;
      paramScriptable = paramArrayOfObject;
      break;
    }
    label109:
    if ((!super.has("arguments", this)) && (!paramBoolean1))
    {
      this.arguments = new Arguments(this);
      defineProperty("arguments", this.arguments, 4);
    }
    if (k != 0)
    {
      i = j;
      if (i < k)
      {
        paramScriptable = paramNativeFunction.getParamOrVarName(i);
        if (!super.has(paramScriptable, this))
        {
          if (!paramNativeFunction.getParamOrVarConst(i)) {
            break label207;
          }
          defineProperty(paramScriptable, Undefined.instance, 13);
        }
        for (;;)
        {
          i += 1;
          break;
          label207:
          defineProperty(paramScriptable, Undefined.instance, 4);
        }
      }
    }
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    new NativeCall().exportAsJSClass(1, paramScriptable, paramBoolean);
  }
  
  public void defineAttributesForArguments()
  {
    if (this.arguments != null) {
      this.arguments.defineAttributesForStrictMode();
    }
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(CALL_TAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i = paramIdFunctionObject.methodId();
    if (i == 1)
    {
      if (paramScriptable2 != null) {
        throw Context.reportRuntimeError1("msg.only.from.new", "Call");
      }
      ScriptRuntime.checkDeprecated(paramContext, "Call");
      paramIdFunctionObject = new NativeCall();
      paramIdFunctionObject.setPrototype(getObjectPrototype(paramScriptable1));
      return paramIdFunctionObject;
    }
    throw new IllegalArgumentException(String.valueOf(i));
  }
  
  protected int findPrototypeId(String paramString)
  {
    if (paramString.equals("constructor")) {
      return 1;
    }
    return 0;
  }
  
  public String getClassName()
  {
    return "Call";
  }
  
  protected void initPrototypeId(int paramInt)
  {
    if (paramInt == 1)
    {
      initPrototypeMethod(CALL_TAG, paramInt, "constructor", 1);
      return;
    }
    throw new IllegalArgumentException(String.valueOf(paramInt));
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */