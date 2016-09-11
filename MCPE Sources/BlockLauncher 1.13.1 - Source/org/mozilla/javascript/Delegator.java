package org.mozilla.javascript;

public class Delegator
  implements Function
{
  protected Scriptable obj = null;
  
  public Delegator() {}
  
  public Delegator(Scriptable paramScriptable)
  {
    this.obj = paramScriptable;
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    return ((Function)this.obj).call(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
  }
  
  public Scriptable construct(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    if (this.obj == null)
    {
      Delegator localDelegator = newInstance();
      if (paramArrayOfObject.length == 0) {}
      for (paramContext = new NativeObject();; paramContext = ScriptRuntime.toObject(paramContext, paramScriptable, paramArrayOfObject[0]))
      {
        localDelegator.setDelegee(paramContext);
        return localDelegator;
      }
    }
    return ((Function)this.obj).construct(paramContext, paramScriptable, paramArrayOfObject);
  }
  
  public void delete(int paramInt)
  {
    this.obj.delete(paramInt);
  }
  
  public void delete(String paramString)
  {
    this.obj.delete(paramString);
  }
  
  public Object get(int paramInt, Scriptable paramScriptable)
  {
    return this.obj.get(paramInt, paramScriptable);
  }
  
  public Object get(String paramString, Scriptable paramScriptable)
  {
    return this.obj.get(paramString, paramScriptable);
  }
  
  public String getClassName()
  {
    return this.obj.getClassName();
  }
  
  public Object getDefaultValue(Class<?> paramClass)
  {
    if ((paramClass == null) || (paramClass == ScriptRuntime.ScriptableClass) || (paramClass == ScriptRuntime.FunctionClass)) {
      return this;
    }
    return this.obj.getDefaultValue(paramClass);
  }
  
  public Scriptable getDelegee()
  {
    return this.obj;
  }
  
  public Object[] getIds()
  {
    return this.obj.getIds();
  }
  
  public Scriptable getParentScope()
  {
    return this.obj.getParentScope();
  }
  
  public Scriptable getPrototype()
  {
    return this.obj.getPrototype();
  }
  
  public boolean has(int paramInt, Scriptable paramScriptable)
  {
    return this.obj.has(paramInt, paramScriptable);
  }
  
  public boolean has(String paramString, Scriptable paramScriptable)
  {
    return this.obj.has(paramString, paramScriptable);
  }
  
  public boolean hasInstance(Scriptable paramScriptable)
  {
    return this.obj.hasInstance(paramScriptable);
  }
  
  protected Delegator newInstance()
  {
    try
    {
      Delegator localDelegator = (Delegator)getClass().newInstance();
      return localDelegator;
    }
    catch (Exception localException)
    {
      throw Context.throwAsScriptRuntimeEx(localException);
    }
  }
  
  public void put(int paramInt, Scriptable paramScriptable, Object paramObject)
  {
    this.obj.put(paramInt, paramScriptable, paramObject);
  }
  
  public void put(String paramString, Scriptable paramScriptable, Object paramObject)
  {
    this.obj.put(paramString, paramScriptable, paramObject);
  }
  
  public void setDelegee(Scriptable paramScriptable)
  {
    this.obj = paramScriptable;
  }
  
  public void setParentScope(Scriptable paramScriptable)
  {
    this.obj.setParentScope(paramScriptable);
  }
  
  public void setPrototype(Scriptable paramScriptable)
  {
    this.obj.setPrototype(paramScriptable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\Delegator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */