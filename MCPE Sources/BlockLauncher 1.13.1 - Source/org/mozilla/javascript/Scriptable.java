package org.mozilla.javascript;

public abstract interface Scriptable
{
  public static final Object NOT_FOUND = UniqueTag.NOT_FOUND;
  
  public abstract void delete(int paramInt);
  
  public abstract void delete(String paramString);
  
  public abstract Object get(int paramInt, Scriptable paramScriptable);
  
  public abstract Object get(String paramString, Scriptable paramScriptable);
  
  public abstract String getClassName();
  
  public abstract Object getDefaultValue(Class<?> paramClass);
  
  public abstract Object[] getIds();
  
  public abstract Scriptable getParentScope();
  
  public abstract Scriptable getPrototype();
  
  public abstract boolean has(int paramInt, Scriptable paramScriptable);
  
  public abstract boolean has(String paramString, Scriptable paramScriptable);
  
  public abstract boolean hasInstance(Scriptable paramScriptable);
  
  public abstract void put(int paramInt, Scriptable paramScriptable, Object paramObject);
  
  public abstract void put(String paramString, Scriptable paramScriptable, Object paramObject);
  
  public abstract void setParentScope(Scriptable paramScriptable);
  
  public abstract void setPrototype(Scriptable paramScriptable);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\Scriptable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */