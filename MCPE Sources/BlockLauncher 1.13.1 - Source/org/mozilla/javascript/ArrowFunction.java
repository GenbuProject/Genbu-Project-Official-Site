package org.mozilla.javascript;

public class ArrowFunction
  extends BaseFunction
{
  static final long serialVersionUID = -7377989503697220633L;
  private final Scriptable boundThis;
  private final Callable targetFunction;
  
  public ArrowFunction(Context paramContext, Scriptable paramScriptable1, Callable paramCallable, Scriptable paramScriptable2)
  {
    this.targetFunction = paramCallable;
    this.boundThis = paramScriptable2;
    ScriptRuntime.setFunctionProtoAndParent(this, paramScriptable1);
    paramScriptable1 = ScriptRuntime.typeErrorThrower();
    paramCallable = new NativeObject();
    paramCallable.put("get", paramCallable, paramScriptable1);
    paramCallable.put("set", paramCallable, paramScriptable1);
    paramCallable.put("enumerable", paramCallable, Boolean.valueOf(false));
    paramCallable.put("configurable", paramCallable, Boolean.valueOf(false));
    paramCallable.preventExtensions();
    defineOwnProperty(paramContext, "caller", paramCallable, false);
    defineOwnProperty(paramContext, "arguments", paramCallable, false);
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (this.boundThis != null) {}
    for (paramScriptable2 = this.boundThis;; paramScriptable2 = ScriptRuntime.getTopCallScope(paramContext)) {
      return this.targetFunction.call(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
  }
  
  public Scriptable construct(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    throw ScriptRuntime.typeError1("msg.not.ctor", decompile(0, 0));
  }
  
  String decompile(int paramInt1, int paramInt2)
  {
    if ((this.targetFunction instanceof BaseFunction)) {
      return ((BaseFunction)this.targetFunction).decompile(paramInt1, paramInt2);
    }
    return super.decompile(paramInt1, paramInt2);
  }
  
  public int getLength()
  {
    if ((this.targetFunction instanceof BaseFunction)) {
      return ((BaseFunction)this.targetFunction).getLength();
    }
    return 0;
  }
  
  public boolean hasInstance(Scriptable paramScriptable)
  {
    if ((this.targetFunction instanceof Function)) {
      return ((Function)this.targetFunction).hasInstance(paramScriptable);
    }
    throw ScriptRuntime.typeError0("msg.not.ctor");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ArrowFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */