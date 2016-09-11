package org.mozilla.javascript;

class SpecialRef
  extends Ref
{
  private static final int SPECIAL_NONE = 0;
  private static final int SPECIAL_PARENT = 2;
  private static final int SPECIAL_PROTO = 1;
  static final long serialVersionUID = -7521596632456797847L;
  private String name;
  private Scriptable target;
  private int type;
  
  private SpecialRef(Scriptable paramScriptable, int paramInt, String paramString)
  {
    this.target = paramScriptable;
    this.type = paramInt;
    this.name = paramString;
  }
  
  static Ref createSpecial(Context paramContext, Scriptable paramScriptable, Object paramObject, String paramString)
  {
    paramScriptable = ScriptRuntime.toObjectOrNull(paramContext, paramObject, paramScriptable);
    if (paramScriptable == null) {
      throw ScriptRuntime.undefReadError(paramObject, paramString);
    }
    if (paramString.equals("__proto__")) {}
    for (int i = 1;; i = 2)
    {
      if (!paramContext.hasFeature(5)) {
        i = 0;
      }
      return new SpecialRef(paramScriptable, i, paramString);
      if (!paramString.equals("__parent__")) {
        break;
      }
    }
    throw new IllegalArgumentException(paramString);
  }
  
  public boolean delete(Context paramContext)
  {
    if (this.type == 0) {
      return ScriptRuntime.deleteObjectElem(this.target, this.name, paramContext);
    }
    return false;
  }
  
  public Object get(Context paramContext)
  {
    switch (this.type)
    {
    default: 
      throw Kit.codeBug();
    case 0: 
      return ScriptRuntime.getObjectProp(this.target, this.name, paramContext);
    case 1: 
      return this.target.getPrototype();
    }
    return this.target.getParentScope();
  }
  
  public boolean has(Context paramContext)
  {
    if (this.type == 0) {
      return ScriptRuntime.hasObjectElem(this.target, this.name, paramContext);
    }
    return true;
  }
  
  @Deprecated
  public Object set(Context paramContext, Object paramObject)
  {
    throw new IllegalStateException();
  }
  
  public Object set(Context paramContext, Scriptable paramScriptable, Object paramObject)
  {
    switch (this.type)
    {
    default: 
      throw Kit.codeBug();
    case 0: 
      return ScriptRuntime.setObjectProp(this.target, this.name, paramObject, paramContext);
    }
    paramObject = ScriptRuntime.toObjectOrNull(paramContext, paramObject, paramScriptable);
    if (paramObject != null)
    {
      paramScriptable = (Scriptable)paramObject;
      if (paramScriptable == this.target) {
        throw Context.reportRuntimeError1("msg.cyclic.value", this.name);
      }
      if (this.type != 1) {
        break label122;
      }
    }
    label122:
    for (paramContext = paramScriptable.getPrototype();; paramContext = paramScriptable.getParentScope())
    {
      paramScriptable = paramContext;
      if (paramContext != null) {
        break;
      }
      if (this.type != 1) {
        break label132;
      }
      this.target.setPrototype((Scriptable)paramObject);
      return paramObject;
    }
    label132:
    this.target.setParentScope((Scriptable)paramObject);
    return paramObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\SpecialRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */