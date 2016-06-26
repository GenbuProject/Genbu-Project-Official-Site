package org.mozilla.javascript;

public class IdFunctionObject
  extends BaseFunction
{
  static final long serialVersionUID = -5332312783643935019L;
  private int arity;
  private String functionName;
  private final IdFunctionCall idcall;
  private final int methodId;
  private final Object tag;
  private boolean useCallAsConstructor;
  
  public IdFunctionObject(IdFunctionCall paramIdFunctionCall, Object paramObject, int paramInt1, int paramInt2)
  {
    if (paramInt2 < 0) {
      throw new IllegalArgumentException();
    }
    this.idcall = paramIdFunctionCall;
    this.tag = paramObject;
    this.methodId = paramInt1;
    this.arity = paramInt2;
    if (paramInt2 < 0) {
      throw new IllegalArgumentException();
    }
  }
  
  public IdFunctionObject(IdFunctionCall paramIdFunctionCall, Object paramObject, int paramInt1, String paramString, int paramInt2, Scriptable paramScriptable)
  {
    super(paramScriptable, null);
    if (paramInt2 < 0) {
      throw new IllegalArgumentException();
    }
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    this.idcall = paramIdFunctionCall;
    this.tag = paramObject;
    this.methodId = paramInt1;
    this.arity = paramInt2;
    this.functionName = paramString;
  }
  
  public final void addAsProperty(Scriptable paramScriptable)
  {
    ScriptableObject.defineProperty(paramScriptable, this.functionName, this, 2);
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    return this.idcall.execIdCall(this, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
  }
  
  public Scriptable createObject(Context paramContext, Scriptable paramScriptable)
  {
    if (this.useCallAsConstructor) {
      return null;
    }
    throw ScriptRuntime.typeError1("msg.not.ctor", this.functionName);
  }
  
  String decompile(int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramInt2 & 0x1) != 0)
    {
      paramInt1 = 1;
      if (paramInt1 == 0)
      {
        localStringBuilder.append("function ");
        localStringBuilder.append(getFunctionName());
        localStringBuilder.append("() { ");
      }
      localStringBuilder.append("[native code for ");
      if ((this.idcall instanceof Scriptable))
      {
        localStringBuilder.append(((Scriptable)this.idcall).getClassName());
        localStringBuilder.append('.');
      }
      localStringBuilder.append(getFunctionName());
      localStringBuilder.append(", arity=");
      localStringBuilder.append(getArity());
      if (paramInt1 == 0) {
        break label144;
      }
    }
    label144:
    for (String str = "]\n";; str = "] }\n")
    {
      localStringBuilder.append(str);
      return localStringBuilder.toString();
      paramInt1 = 0;
      break;
    }
  }
  
  public void exportAsScopeProperty()
  {
    addAsProperty(getParentScope());
  }
  
  public int getArity()
  {
    return this.arity;
  }
  
  public String getFunctionName()
  {
    if (this.functionName == null) {
      return "";
    }
    return this.functionName;
  }
  
  public int getLength()
  {
    return getArity();
  }
  
  public Scriptable getPrototype()
  {
    Scriptable localScriptable2 = super.getPrototype();
    Scriptable localScriptable1 = localScriptable2;
    if (localScriptable2 == null)
    {
      localScriptable1 = getFunctionPrototype(getParentScope());
      setPrototype(localScriptable1);
    }
    return localScriptable1;
  }
  
  public Object getTag()
  {
    return this.tag;
  }
  
  public final boolean hasTag(Object paramObject)
  {
    if (paramObject == null) {
      return this.tag == null;
    }
    return paramObject.equals(this.tag);
  }
  
  public void initFunction(String paramString, Scriptable paramScriptable)
  {
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    if (paramScriptable == null) {
      throw new IllegalArgumentException();
    }
    this.functionName = paramString;
    setParentScope(paramScriptable);
  }
  
  public final void markAsConstructor(Scriptable paramScriptable)
  {
    this.useCallAsConstructor = true;
    setImmunePrototypeProperty(paramScriptable);
  }
  
  public final int methodId()
  {
    return this.methodId;
  }
  
  public final RuntimeException unknown()
  {
    return new IllegalArgumentException("BAD FUNCTION ID=" + this.methodId + " MASTER=" + this.idcall);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\IdFunctionObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */