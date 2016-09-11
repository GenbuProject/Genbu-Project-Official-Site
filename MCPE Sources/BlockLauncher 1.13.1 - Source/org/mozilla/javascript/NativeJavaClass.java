package org.mozilla.javascript;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Map;

public class NativeJavaClass
  extends NativeJavaObject
  implements Function
{
  static final String javaClassPropertyName = "__javaObject__";
  static final long serialVersionUID = -6460763940409461664L;
  private Map<String, FieldAndMethods> staticFieldAndMethods;
  
  public NativeJavaClass() {}
  
  public NativeJavaClass(Scriptable paramScriptable, Class<?> paramClass)
  {
    this(paramScriptable, paramClass, false);
  }
  
  public NativeJavaClass(Scriptable paramScriptable, Class<?> paramClass, boolean paramBoolean)
  {
    super(paramScriptable, paramClass, null, paramBoolean);
  }
  
  static Object constructInternal(Object[] paramArrayOfObject, MemberBox paramMemberBox)
  {
    int i = 0;
    int j = 0;
    Class[] arrayOfClass = paramMemberBox.argTypes;
    Object localObject2;
    Class localClass;
    Object localObject3;
    if (paramMemberBox.vararg)
    {
      localObject2 = new Object[arrayOfClass.length];
      i = 0;
      while (i < arrayOfClass.length - 1)
      {
        localObject2[i] = Context.jsToJava(paramArrayOfObject[i], arrayOfClass[i]);
        i += 1;
      }
      if ((paramArrayOfObject.length == arrayOfClass.length) && ((paramArrayOfObject[(paramArrayOfObject.length - 1)] == null) || ((paramArrayOfObject[(paramArrayOfObject.length - 1)] instanceof NativeArray)) || ((paramArrayOfObject[(paramArrayOfObject.length - 1)] instanceof NativeJavaArray))))
      {
        localObject1 = Context.jsToJava(paramArrayOfObject[(paramArrayOfObject.length - 1)], arrayOfClass[(arrayOfClass.length - 1)]);
        localObject2[(arrayOfClass.length - 1)] = localObject1;
        return paramMemberBox.newInstance((Object[])localObject2);
      }
      localClass = arrayOfClass[(arrayOfClass.length - 1)].getComponentType();
      localObject3 = Array.newInstance(localClass, paramArrayOfObject.length - arrayOfClass.length + 1);
      i = j;
      for (;;)
      {
        localObject1 = localObject3;
        if (i >= Array.getLength(localObject3)) {
          break;
        }
        Array.set(localObject3, i, Context.jsToJava(paramArrayOfObject[(arrayOfClass.length - 1 + i)], localClass));
        i += 1;
      }
    }
    for (Object localObject1 = paramArrayOfObject;; localObject1 = localObject2)
    {
      localObject2 = localObject1;
      if (i >= localObject1.length) {
        break;
      }
      localClass = localObject1[i];
      localObject3 = Context.jsToJava(localClass, arrayOfClass[i]);
      localObject2 = localObject1;
      if (localObject3 != localClass)
      {
        localObject2 = localObject1;
        if (localObject1 == paramArrayOfObject) {
          localObject2 = (Object[])paramArrayOfObject.clone();
        }
        localObject2[i] = localObject3;
      }
      i += 1;
    }
  }
  
  static Scriptable constructSpecific(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject, MemberBox paramMemberBox)
  {
    paramArrayOfObject = constructInternal(paramArrayOfObject, paramMemberBox);
    paramScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    return paramContext.getWrapFactory().wrapNewObject(paramContext, paramScriptable, paramArrayOfObject);
  }
  
  private static Class<?> findNestedClass(Class<?> paramClass, String paramString)
  {
    paramString = paramClass.getName() + '$' + paramString;
    paramClass = paramClass.getClassLoader();
    if (paramClass == null) {
      return Kit.classOrNull(paramString);
    }
    return Kit.classOrNull(paramClass, paramString);
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject.length == 1) && ((paramArrayOfObject[0] instanceof Scriptable)))
    {
      Class localClass = getClassObject();
      paramScriptable2 = (Scriptable)paramArrayOfObject[0];
      Scriptable localScriptable;
      do
      {
        if (((paramScriptable2 instanceof Wrapper)) && (localClass.isInstance(((Wrapper)paramScriptable2).unwrap()))) {
          return paramScriptable2;
        }
        localScriptable = paramScriptable2.getPrototype();
        paramScriptable2 = localScriptable;
      } while (localScriptable != null);
    }
    return construct(paramContext, paramScriptable1, paramArrayOfObject);
  }
  
  public Scriptable construct(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    Class localClass = getClassObject();
    int i = localClass.getModifiers();
    if ((!Modifier.isInterface(i)) && (!Modifier.isAbstract(i)))
    {
      localObject = this.members.ctors;
      i = ((NativeJavaMethod)localObject).findCachedFunction(paramContext, paramArrayOfObject);
      if (i < 0)
      {
        paramContext = NativeJavaMethod.scriptSignature(paramArrayOfObject);
        throw Context.reportRuntimeError2("msg.no.java.ctor", localClass.getName(), paramContext);
      }
      return constructSpecific(paramContext, paramScriptable, paramArrayOfObject, localObject.methods[i]);
    }
    if (paramArrayOfObject.length == 0) {
      throw Context.reportRuntimeError0("msg.adapter.zero.args");
    }
    Object localObject = ScriptableObject.getTopLevelScope(this);
    try
    {
      if (("Dalvik".equals(System.getProperty("java.vm.name"))) && (localClass.isInterface()))
      {
        paramArrayOfObject = createInterfaceAdapter(localClass, ScriptableObject.ensureScriptableObject(paramArrayOfObject[0]));
        return paramContext.getWrapFactory().wrapAsJavaObject(paramContext, paramScriptable, paramArrayOfObject, null);
      }
      paramScriptable = ((Scriptable)localObject).get("JavaAdapter", (Scriptable)localObject);
      if (paramScriptable != NOT_FOUND)
      {
        paramContext = ((Function)paramScriptable).construct(paramContext, (Scriptable)localObject, new Object[] { this, paramArrayOfObject[0] });
        return paramContext;
      }
      paramContext = "";
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramScriptable = paramContext.getMessage();
        paramContext = paramScriptable;
        if (paramScriptable == null) {
          paramContext = "";
        }
      }
    }
    throw Context.reportRuntimeError2("msg.cant.instantiate", paramContext, localClass.getName());
  }
  
  public Object get(String paramString, Scriptable paramScriptable)
  {
    if (paramString.equals("prototype")) {
      localObject1 = null;
    }
    do
    {
      return localObject1;
      if (this.staticFieldAndMethods == null) {
        break;
      }
      localObject2 = this.staticFieldAndMethods.get(paramString);
      localObject1 = localObject2;
    } while (localObject2 != null);
    if (this.members.has(paramString, true)) {
      return this.members.get(this, paramString, this.javaObject, true);
    }
    Object localObject1 = Context.getContext();
    paramScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    Object localObject2 = ((Context)localObject1).getWrapFactory();
    if ("__javaObject__".equals(paramString)) {
      return ((WrapFactory)localObject2).wrap((Context)localObject1, paramScriptable, this.javaObject, ScriptRuntime.ClassClass);
    }
    Class localClass = findNestedClass(getClassObject(), paramString);
    if (localClass != null)
    {
      paramString = ((WrapFactory)localObject2).wrapJavaClass((Context)localObject1, paramScriptable, localClass);
      paramString.setParentScope(this);
      return paramString;
    }
    throw this.members.reportMemberNotFound(paramString);
  }
  
  public String getClassName()
  {
    return "JavaClass";
  }
  
  public Class<?> getClassObject()
  {
    return (Class)super.unwrap();
  }
  
  public Object getDefaultValue(Class<?> paramClass)
  {
    Object localObject;
    if ((paramClass == null) || (paramClass == ScriptRuntime.StringClass)) {
      localObject = toString();
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
    return this.members.getIds(true);
  }
  
  public boolean has(String paramString, Scriptable paramScriptable)
  {
    return (this.members.has(paramString, true)) || ("__javaObject__".equals(paramString));
  }
  
  public boolean hasInstance(Scriptable paramScriptable)
  {
    if (((paramScriptable instanceof Wrapper)) && (!(paramScriptable instanceof NativeJavaClass)))
    {
      paramScriptable = ((Wrapper)paramScriptable).unwrap();
      return getClassObject().isInstance(paramScriptable);
    }
    return false;
  }
  
  protected void initMembers()
  {
    Class localClass = (Class)this.javaObject;
    this.members = JavaMembers.lookupClass(this.parent, localClass, localClass, this.isAdapter);
    this.staticFieldAndMethods = this.members.getFieldAndMethodsObjects(this, localClass, true);
  }
  
  public void put(String paramString, Scriptable paramScriptable, Object paramObject)
  {
    this.members.put(this, paramString, this.javaObject, paramObject, true);
  }
  
  public String toString()
  {
    return "[JavaClass " + getClassObject().getName() + "]";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeJavaClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */