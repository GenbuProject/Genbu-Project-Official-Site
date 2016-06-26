package org.mozilla.javascript;

public class BoundFunction
  extends BaseFunction
{
  static final long serialVersionUID = 2118137342826470729L;
  private final Object[] boundArgs;
  private final Scriptable boundThis;
  private final int length;
  private final Callable targetFunction;
  
  public BoundFunction(Context paramContext, Scriptable paramScriptable1, Callable paramCallable, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    this.targetFunction = paramCallable;
    this.boundThis = paramScriptable2;
    this.boundArgs = paramArrayOfObject;
    if ((paramCallable instanceof BaseFunction)) {}
    for (this.length = Math.max(0, ((BaseFunction)paramCallable).getLength() - paramArrayOfObject.length);; this.length = 0)
    {
      ScriptRuntime.setFunctionProtoAndParent(this, paramScriptable1);
      paramScriptable1 = ScriptRuntime.typeErrorThrower(paramContext);
      paramCallable = new NativeObject();
      paramCallable.put("get", paramCallable, paramScriptable1);
      paramCallable.put("set", paramCallable, paramScriptable1);
      paramCallable.put("enumerable", paramCallable, Boolean.valueOf(false));
      paramCallable.put("configurable", paramCallable, Boolean.valueOf(false));
      paramCallable.preventExtensions();
      defineOwnProperty(paramContext, "caller", paramCallable, false);
      defineOwnProperty(paramContext, "arguments", paramCallable, false);
      return;
    }
  }
  
  private Object[] concat(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    Object[] arrayOfObject = new Object[paramArrayOfObject1.length + paramArrayOfObject2.length];
    System.arraycopy(paramArrayOfObject1, 0, arrayOfObject, 0, paramArrayOfObject1.length);
    System.arraycopy(paramArrayOfObject2, 0, arrayOfObject, paramArrayOfObject1.length, paramArrayOfObject2.length);
    return arrayOfObject;
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (this.boundThis != null) {}
    for (paramScriptable2 = this.boundThis;; paramScriptable2 = ScriptRuntime.getTopCallScope(paramContext)) {
      return this.targetFunction.call(paramContext, paramScriptable1, paramScriptable2, concat(this.boundArgs, paramArrayOfObject));
    }
  }
  
  public Scriptable construct(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    if ((this.targetFunction instanceof Function)) {
      return ((Function)this.targetFunction).construct(paramContext, paramScriptable, concat(this.boundArgs, paramArrayOfObject));
    }
    throw ScriptRuntime.typeError0("msg.not.ctor");
  }
  
  public int getLength()
  {
    return this.length;
  }
  
  public boolean hasInstance(Scriptable paramScriptable)
  {
    if ((this.targetFunction instanceof Function)) {
      return ((Function)this.targetFunction).hasInstance(paramScriptable);
    }
    throw ScriptRuntime.typeError0("msg.not.ctor");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\BoundFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */