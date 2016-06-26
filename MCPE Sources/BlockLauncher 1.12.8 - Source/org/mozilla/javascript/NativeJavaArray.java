package org.mozilla.javascript;

import java.lang.reflect.Array;

public class NativeJavaArray
  extends NativeJavaObject
{
  static final long serialVersionUID = -924022554283675333L;
  Object array;
  Class<?> cls;
  int length;
  
  public NativeJavaArray(Scriptable paramScriptable, Object paramObject)
  {
    super(paramScriptable, null, ScriptRuntime.ObjectClass);
    paramScriptable = paramObject.getClass();
    if (!paramScriptable.isArray()) {
      throw new RuntimeException("Array expected");
    }
    this.array = paramObject;
    this.length = Array.getLength(paramObject);
    this.cls = paramScriptable.getComponentType();
  }
  
  public static NativeJavaArray wrap(Scriptable paramScriptable, Object paramObject)
  {
    return new NativeJavaArray(paramScriptable, paramObject);
  }
  
  public Object get(int paramInt, Scriptable paramScriptable)
  {
    if ((paramInt >= 0) && (paramInt < this.length))
    {
      paramScriptable = Context.getContext();
      Object localObject = Array.get(this.array, paramInt);
      return paramScriptable.getWrapFactory().wrap(paramScriptable, this, localObject, this.cls);
    }
    return Undefined.instance;
  }
  
  public Object get(String paramString, Scriptable paramScriptable)
  {
    if (paramString.equals("length")) {
      paramScriptable = Integer.valueOf(this.length);
    }
    do
    {
      Object localObject;
      do
      {
        return paramScriptable;
        localObject = super.get(paramString, paramScriptable);
        paramScriptable = (Scriptable)localObject;
      } while (localObject != NOT_FOUND);
      paramScriptable = (Scriptable)localObject;
    } while (ScriptableObject.hasProperty(getPrototype(), paramString));
    throw Context.reportRuntimeError2("msg.java.member.not.found", this.array.getClass().getName(), paramString);
  }
  
  public String getClassName()
  {
    return "JavaArray";
  }
  
  public Object getDefaultValue(Class<?> paramClass)
  {
    Object localObject;
    if ((paramClass == null) || (paramClass == ScriptRuntime.StringClass)) {
      localObject = this.array.toString();
    }
    do
    {
      return localObject;
      if (paramClass == ScriptRuntime.BooleanClass) {
        return Boolean.TRUE;
      }
      localObject = this;
    } while (paramClass != ScriptRuntime.NumberClass);
    return ScriptRuntime.NaNobj;
  }
  
  public Object[] getIds()
  {
    Object[] arrayOfObject = new Object[this.length];
    int i = this.length;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      arrayOfObject[i] = Integer.valueOf(i);
    }
    return arrayOfObject;
  }
  
  public Scriptable getPrototype()
  {
    if (this.prototype == null) {
      this.prototype = ScriptableObject.getArrayPrototype(getParentScope());
    }
    return this.prototype;
  }
  
  public boolean has(int paramInt, Scriptable paramScriptable)
  {
    return (paramInt >= 0) && (paramInt < this.length);
  }
  
  public boolean has(String paramString, Scriptable paramScriptable)
  {
    return (paramString.equals("length")) || (super.has(paramString, paramScriptable));
  }
  
  public boolean hasInstance(Scriptable paramScriptable)
  {
    if (!(paramScriptable instanceof Wrapper)) {
      return false;
    }
    paramScriptable = ((Wrapper)paramScriptable).unwrap();
    return this.cls.isInstance(paramScriptable);
  }
  
  public void put(int paramInt, Scriptable paramScriptable, Object paramObject)
  {
    if ((paramInt >= 0) && (paramInt < this.length))
    {
      Array.set(this.array, paramInt, Context.jsToJava(paramObject, this.cls));
      return;
    }
    throw Context.reportRuntimeError2("msg.java.array.index.out.of.bounds", String.valueOf(paramInt), String.valueOf(this.length - 1));
  }
  
  public void put(String paramString, Scriptable paramScriptable, Object paramObject)
  {
    if (!paramString.equals("length")) {
      throw Context.reportRuntimeError1("msg.java.array.member.not.found", paramString);
    }
  }
  
  public Object unwrap()
  {
    return this.array;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeJavaArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */