package org.mozilla.javascript;

import java.io.Serializable;

public class NativeWith
  implements Scriptable, IdFunctionCall, Serializable
{
  private static final Object FTAG = "With";
  private static final int Id_constructor = 1;
  private static final long serialVersionUID = 1L;
  protected Scriptable parent;
  protected Scriptable prototype;
  
  private NativeWith() {}
  
  protected NativeWith(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    this.parent = paramScriptable1;
    this.prototype = paramScriptable2;
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    NativeWith localNativeWith = new NativeWith();
    localNativeWith.setParentScope(paramScriptable);
    localNativeWith.setPrototype(ScriptableObject.getObjectPrototype(paramScriptable));
    paramScriptable = new IdFunctionObject(localNativeWith, FTAG, 1, "With", 0, paramScriptable);
    paramScriptable.markAsConstructor(localNativeWith);
    if (paramBoolean) {
      paramScriptable.sealObject();
    }
    paramScriptable.exportAsScopeProperty();
  }
  
  static boolean isWithFunction(Object paramObject)
  {
    if ((paramObject instanceof IdFunctionObject))
    {
      paramObject = (IdFunctionObject)paramObject;
      return (((IdFunctionObject)paramObject).hasTag(FTAG)) && (((IdFunctionObject)paramObject).methodId() == 1);
    }
    return false;
  }
  
  static Object newWithSpecial(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    ScriptRuntime.checkDeprecated(paramContext, "With");
    paramScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    NativeWith localNativeWith = new NativeWith();
    if (paramArrayOfObject.length == 0) {}
    for (paramContext = ScriptableObject.getObjectPrototype(paramScriptable);; paramContext = ScriptRuntime.toObject(paramContext, paramScriptable, paramArrayOfObject[0]))
    {
      localNativeWith.setPrototype(paramContext);
      localNativeWith.setParentScope(paramScriptable);
      return localNativeWith;
    }
  }
  
  public void delete(int paramInt)
  {
    this.prototype.delete(paramInt);
  }
  
  public void delete(String paramString)
  {
    this.prototype.delete(paramString);
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if ((paramIdFunctionObject.hasTag(FTAG)) && (paramIdFunctionObject.methodId() == 1)) {
      throw Context.reportRuntimeError1("msg.cant.call.indirect", "With");
    }
    throw paramIdFunctionObject.unknown();
  }
  
  public Object get(int paramInt, Scriptable paramScriptable)
  {
    Scriptable localScriptable = paramScriptable;
    if (paramScriptable == this) {
      localScriptable = this.prototype;
    }
    return this.prototype.get(paramInt, localScriptable);
  }
  
  public Object get(String paramString, Scriptable paramScriptable)
  {
    Scriptable localScriptable = paramScriptable;
    if (paramScriptable == this) {
      localScriptable = this.prototype;
    }
    return this.prototype.get(paramString, localScriptable);
  }
  
  public String getClassName()
  {
    return "With";
  }
  
  public Object getDefaultValue(Class<?> paramClass)
  {
    return this.prototype.getDefaultValue(paramClass);
  }
  
  public Object[] getIds()
  {
    return this.prototype.getIds();
  }
  
  public Scriptable getParentScope()
  {
    return this.parent;
  }
  
  public Scriptable getPrototype()
  {
    return this.prototype;
  }
  
  public boolean has(int paramInt, Scriptable paramScriptable)
  {
    return this.prototype.has(paramInt, this.prototype);
  }
  
  public boolean has(String paramString, Scriptable paramScriptable)
  {
    return this.prototype.has(paramString, this.prototype);
  }
  
  public boolean hasInstance(Scriptable paramScriptable)
  {
    return this.prototype.hasInstance(paramScriptable);
  }
  
  public void put(int paramInt, Scriptable paramScriptable, Object paramObject)
  {
    Scriptable localScriptable = paramScriptable;
    if (paramScriptable == this) {
      localScriptable = this.prototype;
    }
    this.prototype.put(paramInt, localScriptable, paramObject);
  }
  
  public void put(String paramString, Scriptable paramScriptable, Object paramObject)
  {
    Scriptable localScriptable = paramScriptable;
    if (paramScriptable == this) {
      localScriptable = this.prototype;
    }
    this.prototype.put(paramString, localScriptable, paramObject);
  }
  
  public void setParentScope(Scriptable paramScriptable)
  {
    this.parent = paramScriptable;
  }
  
  public void setPrototype(Scriptable paramScriptable)
  {
    this.prototype = paramScriptable;
  }
  
  protected Object updateDotQuery(boolean paramBoolean)
  {
    throw new IllegalStateException();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeWith.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */