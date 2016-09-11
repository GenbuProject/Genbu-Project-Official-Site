package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

public class NativeJavaObject
  implements Scriptable, Wrapper, Serializable
{
  private static final Object COERCED_INTERFACE_KEY = "Coerced Interface";
  static final byte CONVERSION_NONE = 99;
  static final byte CONVERSION_NONTRIVIAL = 0;
  static final byte CONVERSION_TRIVIAL = 1;
  private static final int JSTYPE_BOOLEAN = 2;
  private static final int JSTYPE_JAVA_ARRAY = 7;
  private static final int JSTYPE_JAVA_CLASS = 5;
  private static final int JSTYPE_JAVA_OBJECT = 6;
  private static final int JSTYPE_NULL = 1;
  private static final int JSTYPE_NUMBER = 3;
  private static final int JSTYPE_OBJECT = 8;
  private static final int JSTYPE_STRING = 4;
  private static final int JSTYPE_UNDEFINED = 0;
  private static Method adapter_readAdapterObject;
  private static Method adapter_writeAdapterObject;
  static final long serialVersionUID = -6948590651130498591L;
  private transient Map<String, FieldAndMethods> fieldAndMethods;
  protected transient boolean isAdapter;
  protected transient Object javaObject;
  protected transient JavaMembers members;
  protected Scriptable parent;
  protected Scriptable prototype;
  protected transient Class<?> staticType;
  
  static
  {
    Class[] arrayOfClass = new Class[2];
    Class localClass = Kit.classOrNull("org.mozilla.javascript.JavaAdapter");
    if (localClass != null) {}
    try
    {
      arrayOfClass[0] = ScriptRuntime.ObjectClass;
      arrayOfClass[1] = Kit.classOrNull("java.io.ObjectOutputStream");
      adapter_writeAdapterObject = localClass.getMethod("writeAdapterObject", arrayOfClass);
      arrayOfClass[0] = ScriptRuntime.ScriptableClass;
      arrayOfClass[1] = Kit.classOrNull("java.io.ObjectInputStream");
      adapter_readAdapterObject = localClass.getMethod("readAdapterObject", arrayOfClass);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      adapter_writeAdapterObject = null;
      adapter_readAdapterObject = null;
    }
  }
  
  public NativeJavaObject() {}
  
  public NativeJavaObject(Scriptable paramScriptable, Object paramObject, Class<?> paramClass)
  {
    this(paramScriptable, paramObject, paramClass, false);
  }
  
  public NativeJavaObject(Scriptable paramScriptable, Object paramObject, Class<?> paramClass, boolean paramBoolean)
  {
    this.parent = paramScriptable;
    this.javaObject = paramObject;
    this.staticType = paramClass;
    this.isAdapter = paramBoolean;
    initMembers();
  }
  
  public static boolean canConvert(Object paramObject, Class<?> paramClass)
  {
    return getConversionWeight(paramObject, paramClass) < 99;
  }
  
  private static Object coerceToNumber(Class<?> paramClass, Object paramObject)
  {
    double d1 = 0.0D;
    Class localClass = paramObject.getClass();
    if ((paramClass == Character.TYPE) || (paramClass == ScriptRuntime.CharacterClass)) {
      if (localClass != ScriptRuntime.CharacterClass) {}
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return paramObject;
                return Character.valueOf((char)(int)toInteger(paramObject, ScriptRuntime.CharacterClass, 0.0D, 65535.0D));
                if ((paramClass != ScriptRuntime.ObjectClass) && (paramClass != ScriptRuntime.DoubleClass) && (paramClass != Double.TYPE)) {
                  break;
                }
              } while (localClass == ScriptRuntime.DoubleClass);
              return new Double(toDouble(paramObject));
              if ((paramClass != ScriptRuntime.FloatClass) && (paramClass != Float.TYPE)) {
                break;
              }
            } while (localClass == ScriptRuntime.FloatClass);
            d2 = toDouble(paramObject);
            if ((Double.isInfinite(d2)) || (Double.isNaN(d2)) || (d2 == 0.0D)) {
              return new Float((float)d2);
            }
            double d3 = Math.abs(d2);
            if (d3 < 1.401298464324817E-45D)
            {
              if (d2 > 0.0D) {}
              for (;;)
              {
                return new Float(d1);
                d1 = 0.0D;
              }
            }
            if (d3 > 3.4028234663852886E38D)
            {
              if (d2 > 0.0D) {}
              for (float f = Float.POSITIVE_INFINITY;; f = Float.NEGATIVE_INFINITY) {
                return new Float(f);
              }
            }
            return new Float((float)d2);
            if ((paramClass != ScriptRuntime.IntegerClass) && (paramClass != Integer.TYPE)) {
              break;
            }
          } while (localClass == ScriptRuntime.IntegerClass);
          return Integer.valueOf((int)toInteger(paramObject, ScriptRuntime.IntegerClass, -2.147483648E9D, 2.147483647E9D));
          if ((paramClass != ScriptRuntime.LongClass) && (paramClass != Long.TYPE)) {
            break;
          }
        } while (localClass == ScriptRuntime.LongClass);
        d1 = Double.longBitsToDouble(4890909195324358655L);
        double d2 = Double.longBitsToDouble(-4332462841530417152L);
        return Long.valueOf(toInteger(paramObject, ScriptRuntime.LongClass, d2, d1));
        if ((paramClass != ScriptRuntime.ShortClass) && (paramClass != Short.TYPE)) {
          break;
        }
      } while (localClass == ScriptRuntime.ShortClass);
      return Short.valueOf((short)(int)toInteger(paramObject, ScriptRuntime.ShortClass, -32768.0D, 32767.0D));
      if ((paramClass != ScriptRuntime.ByteClass) && (paramClass != Byte.TYPE)) {
        break;
      }
    } while (localClass == ScriptRuntime.ByteClass);
    return Byte.valueOf((byte)(int)toInteger(paramObject, ScriptRuntime.ByteClass, -128.0D, 127.0D));
    return new Double(toDouble(paramObject));
  }
  
  @Deprecated
  public static Object coerceType(Class<?> paramClass, Object paramObject)
  {
    return coerceTypeImpl(paramClass, paramObject);
  }
  
  static Object coerceTypeImpl(Class<?> paramClass, Object paramObject)
  {
    int i = 0;
    Object localObject1;
    if ((paramObject != null) && (paramObject.getClass() == paramClass)) {
      localObject1 = paramObject;
    }
    do
    {
      Object localObject2;
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return localObject1;
                    switch (getJSTypeCode(paramObject))
                    {
                    default: 
                      return paramObject;
                    case 0: 
                      if ((paramClass == ScriptRuntime.StringClass) || (paramClass == ScriptRuntime.ObjectClass)) {
                        return "undefined";
                      }
                    case 1: 
                      if (paramClass.isPrimitive()) {
                        reportConversionError(paramObject, paramClass);
                      }
                      return null;
                      reportConversionError("undefined", paramClass);
                      return paramObject;
                    case 2: 
                      localObject1 = paramObject;
                    }
                  } while (paramClass == Boolean.TYPE);
                  localObject1 = paramObject;
                } while (paramClass == ScriptRuntime.BooleanClass);
                localObject1 = paramObject;
              } while (paramClass == ScriptRuntime.ObjectClass);
              if (paramClass == ScriptRuntime.StringClass) {
                return paramObject.toString();
              }
              reportConversionError(paramObject, paramClass);
              return paramObject;
              if (paramClass == ScriptRuntime.StringClass) {
                return ScriptRuntime.toString(paramObject);
              }
              if (paramClass == ScriptRuntime.ObjectClass) {
                return coerceToNumber(Double.TYPE, paramObject);
              }
              if (((paramClass.isPrimitive()) && (paramClass != Boolean.TYPE)) || (ScriptRuntime.NumberClass.isAssignableFrom(paramClass))) {
                return coerceToNumber(paramClass, paramObject);
              }
              reportConversionError(paramObject, paramClass);
              return paramObject;
              if ((paramClass == ScriptRuntime.StringClass) || (paramClass.isInstance(paramObject))) {
                return paramObject.toString();
              }
              if ((paramClass == Character.TYPE) || (paramClass == ScriptRuntime.CharacterClass))
              {
                if (((CharSequence)paramObject).length() == 1) {
                  return Character.valueOf(((CharSequence)paramObject).charAt(0));
                }
                return coerceToNumber(paramClass, paramObject);
              }
              if (((paramClass.isPrimitive()) && (paramClass != Boolean.TYPE)) || (ScriptRuntime.NumberClass.isAssignableFrom(paramClass))) {
                return coerceToNumber(paramClass, paramObject);
              }
              reportConversionError(paramObject, paramClass);
              return paramObject;
              localObject2 = paramObject;
              if ((paramObject instanceof Wrapper)) {
                localObject2 = ((Wrapper)paramObject).unwrap();
              }
              localObject1 = localObject2;
            } while (paramClass == ScriptRuntime.ClassClass);
            localObject1 = localObject2;
          } while (paramClass == ScriptRuntime.ObjectClass);
          if (paramClass == ScriptRuntime.StringClass) {
            return localObject2.toString();
          }
          reportConversionError(localObject2, paramClass);
          return localObject2;
          localObject2 = paramObject;
          if ((paramObject instanceof Wrapper)) {
            localObject2 = ((Wrapper)paramObject).unwrap();
          }
          if (paramClass.isPrimitive())
          {
            if (paramClass == Boolean.TYPE) {
              reportConversionError(localObject2, paramClass);
            }
            return coerceToNumber(paramClass, localObject2);
          }
          if (paramClass == ScriptRuntime.StringClass) {
            return localObject2.toString();
          }
          localObject1 = localObject2;
        } while (paramClass.isInstance(localObject2));
        reportConversionError(localObject2, paramClass);
        return localObject2;
        if (paramClass == ScriptRuntime.StringClass) {
          return ScriptRuntime.toString(paramObject);
        }
        if (paramClass.isPrimitive())
        {
          if (paramClass == Boolean.TYPE) {
            reportConversionError(paramObject, paramClass);
          }
          return coerceToNumber(paramClass, paramObject);
        }
        localObject1 = paramObject;
      } while (paramClass.isInstance(paramObject));
      if ((paramClass == ScriptRuntime.DateClass) && ((paramObject instanceof NativeDate))) {
        return new Date(((NativeDate)paramObject).getJSTimeValue());
      }
      if ((paramClass.isArray()) && ((paramObject instanceof NativeArray)))
      {
        localObject1 = (NativeArray)paramObject;
        long l = ((NativeArray)localObject1).getLength();
        localObject2 = paramClass.getComponentType();
        Object localObject3 = Array.newInstance((Class)localObject2, (int)l);
        for (;;)
        {
          if (i < l) {
            try
            {
              Array.set(localObject3, i, coerceTypeImpl((Class)localObject2, ((NativeArray)localObject1).get(i, (Scriptable)localObject1)));
              i += 1;
            }
            catch (EvaluatorException localEvaluatorException)
            {
              for (;;)
              {
                reportConversionError(paramObject, paramClass);
              }
            }
          }
        }
        return localObject3;
      }
      if (!(paramObject instanceof Wrapper)) {
        break;
      }
      paramObject = ((Wrapper)paramObject).unwrap();
      localObject1 = paramObject;
    } while (paramClass.isInstance(paramObject));
    reportConversionError(paramObject, paramClass);
    return paramObject;
    if ((paramClass.isInterface()) && (((paramObject instanceof NativeObject)) || ((paramObject instanceof NativeFunction)))) {
      return createInterfaceAdapter(paramClass, (ScriptableObject)paramObject);
    }
    reportConversionError(paramObject, paramClass);
    return paramObject;
  }
  
  protected static Object createInterfaceAdapter(Class<?> paramClass, ScriptableObject paramScriptableObject)
  {
    Object localObject1 = Kit.makeHashKeyFromPair(COERCED_INTERFACE_KEY, paramClass);
    Object localObject2 = paramScriptableObject.getAssociatedValue(localObject1);
    if (localObject2 != null) {
      return localObject2;
    }
    return paramScriptableObject.associateValue(localObject1, InterfaceAdapter.create(Context.getContext(), paramClass, paramScriptableObject));
  }
  
  static int getConversionWeight(Object paramObject, Class<?> paramClass)
  {
    int i = getJSTypeCode(paramObject);
    switch (i)
    {
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            return 99;
                          } while ((paramClass != ScriptRuntime.StringClass) && (paramClass != ScriptRuntime.ObjectClass));
                          return 1;
                        } while (paramClass.isPrimitive());
                        return 1;
                        if (paramClass == Boolean.TYPE) {
                          return 1;
                        }
                        if (paramClass == ScriptRuntime.BooleanClass) {
                          return 2;
                        }
                        if (paramClass == ScriptRuntime.ObjectClass) {
                          return 3;
                        }
                      } while (paramClass != ScriptRuntime.StringClass);
                      return 4;
                      if (!paramClass.isPrimitive()) {
                        break;
                      }
                      if (paramClass == Double.TYPE) {
                        return 1;
                      }
                    } while (paramClass == Boolean.TYPE);
                    return getSizeRank(paramClass) + 1;
                    if (paramClass == ScriptRuntime.StringClass) {
                      return 9;
                    }
                    if (paramClass == ScriptRuntime.ObjectClass) {
                      return 10;
                    }
                  } while (!ScriptRuntime.NumberClass.isAssignableFrom(paramClass));
                  return 2;
                  if (paramClass == ScriptRuntime.StringClass) {
                    return 1;
                  }
                  if (paramClass.isInstance(paramObject)) {
                    return 2;
                  }
                } while (!paramClass.isPrimitive());
                if (paramClass == Character.TYPE) {
                  return 3;
                }
              } while (paramClass == Boolean.TYPE);
              return 4;
              if (paramClass == ScriptRuntime.ClassClass) {
                return 1;
              }
              if (paramClass == ScriptRuntime.ObjectClass) {
                return 3;
              }
            } while (paramClass != ScriptRuntime.StringClass);
            return 4;
            Object localObject = paramObject;
            if ((paramObject instanceof Wrapper)) {
              localObject = ((Wrapper)paramObject).unwrap();
            }
            if (paramClass.isInstance(localObject)) {
              return 0;
            }
            if (paramClass == ScriptRuntime.StringClass) {
              return 2;
            }
          } while ((!paramClass.isPrimitive()) || (paramClass == Boolean.TYPE) || (i == 7));
          return getSizeRank(paramClass) + 2;
          if ((paramClass != ScriptRuntime.ObjectClass) && (paramClass.isInstance(paramObject))) {
            return 1;
          }
          if (!paramClass.isArray()) {
            break;
          }
        } while (!(paramObject instanceof NativeArray));
        return 2;
        if (paramClass == ScriptRuntime.ObjectClass) {
          return 3;
        }
        if (paramClass == ScriptRuntime.StringClass) {
          return 4;
        }
        if (paramClass != ScriptRuntime.DateClass) {
          break;
        }
      } while (!(paramObject instanceof NativeDate));
      return 1;
      if (paramClass.isInterface())
      {
        if ((paramObject instanceof NativeFunction)) {
          return 1;
        }
        if ((paramObject instanceof NativeObject)) {
          return 2;
        }
        return 12;
      }
    } while ((!paramClass.isPrimitive()) || (paramClass == Boolean.TYPE));
    return getSizeRank(paramClass) + 4;
  }
  
  private static int getJSTypeCode(Object paramObject)
  {
    int i = 5;
    if (paramObject == null) {
      i = 1;
    }
    do
    {
      do
      {
        return i;
        if (paramObject == Undefined.instance) {
          return 0;
        }
        if ((paramObject instanceof CharSequence)) {
          return 4;
        }
        if ((paramObject instanceof Number)) {
          return 3;
        }
        if ((paramObject instanceof Boolean)) {
          return 2;
        }
        if (!(paramObject instanceof Scriptable)) {
          break;
        }
      } while ((paramObject instanceof NativeJavaClass));
      if ((paramObject instanceof NativeJavaArray)) {
        return 7;
      }
      if ((paramObject instanceof Wrapper)) {
        return 6;
      }
      return 8;
    } while ((paramObject instanceof Class));
    if (paramObject.getClass().isArray()) {
      return 7;
    }
    return 6;
  }
  
  static int getSizeRank(Class<?> paramClass)
  {
    if (paramClass == Double.TYPE) {
      return 1;
    }
    if (paramClass == Float.TYPE) {
      return 2;
    }
    if (paramClass == Long.TYPE) {
      return 3;
    }
    if (paramClass == Integer.TYPE) {
      return 4;
    }
    if (paramClass == Short.TYPE) {
      return 5;
    }
    if (paramClass == Character.TYPE) {
      return 6;
    }
    if (paramClass == Byte.TYPE) {
      return 7;
    }
    if (paramClass == Boolean.TYPE) {
      return 99;
    }
    return 8;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    this.isAdapter = paramObjectInputStream.readBoolean();
    if (this.isAdapter) {
      if (adapter_readAdapterObject == null) {
        throw new ClassNotFoundException();
      }
    }
    for (;;)
    {
      try
      {
        this.javaObject = adapter_readAdapterObject.invoke(null, new Object[] { this, paramObjectInputStream });
        paramObjectInputStream = (String)paramObjectInputStream.readObject();
        if (paramObjectInputStream == null) {
          break label101;
        }
        this.staticType = Class.forName(paramObjectInputStream);
        initMembers();
        return;
      }
      catch (Exception paramObjectInputStream)
      {
        throw new IOException();
      }
      this.javaObject = paramObjectInputStream.readObject();
      continue;
      label101:
      this.staticType = null;
    }
  }
  
  static void reportConversionError(Object paramObject, Class<?> paramClass)
  {
    throw Context.reportRuntimeError2("msg.conversion.not.allowed", String.valueOf(paramObject), JavaMembers.javaSignature(paramClass));
  }
  
  private static double toDouble(Object paramObject)
  {
    Object localObject = null;
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).doubleValue();
    }
    if ((paramObject instanceof String)) {
      return ScriptRuntime.toNumber((String)paramObject);
    }
    if ((paramObject instanceof Scriptable))
    {
      if ((paramObject instanceof Wrapper)) {
        return toDouble(((Wrapper)paramObject).unwrap());
      }
      return ScriptRuntime.toNumber(paramObject);
    }
    try
    {
      Method localMethod = paramObject.getClass().getMethod("doubleValue", (Class[])null);
      localObject = localMethod;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
    if (localObject != null) {}
    try
    {
      double d = ((Number)((Method)localObject).invoke(paramObject, (Object[])null)).doubleValue();
      return d;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      reportConversionError(paramObject, Double.TYPE);
      return ScriptRuntime.toNumber(paramObject.toString());
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        reportConversionError(paramObject, Double.TYPE);
      }
    }
  }
  
  private static long toInteger(Object paramObject, Class<?> paramClass, double paramDouble1, double paramDouble2)
  {
    double d = toDouble(paramObject);
    if ((Double.isInfinite(d)) || (Double.isNaN(d))) {
      reportConversionError(ScriptRuntime.toString(paramObject), paramClass);
    }
    if (d > 0.0D) {}
    for (d = Math.floor(d);; d = Math.ceil(d))
    {
      if ((d < paramDouble1) || (d > paramDouble2)) {
        reportConversionError(ScriptRuntime.toString(paramObject), paramClass);
      }
      return d;
    }
  }
  
  @Deprecated
  public static Object wrap(Scriptable paramScriptable, Object paramObject, Class<?> paramClass)
  {
    Context localContext = Context.getContext();
    return localContext.getWrapFactory().wrap(localContext, paramScriptable, paramObject, paramClass);
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    paramObjectOutputStream.writeBoolean(this.isAdapter);
    Object localObject;
    if (this.isAdapter)
    {
      if (adapter_writeAdapterObject == null) {
        throw new IOException();
      }
      localObject = this.javaObject;
    }
    for (;;)
    {
      try
      {
        adapter_writeAdapterObject.invoke(null, new Object[] { localObject, paramObjectOutputStream });
        if (this.staticType == null) {
          break;
        }
        paramObjectOutputStream.writeObject(this.staticType.getClass().getName());
        return;
      }
      catch (Exception paramObjectOutputStream)
      {
        throw new IOException();
      }
      paramObjectOutputStream.writeObject(this.javaObject);
    }
    paramObjectOutputStream.writeObject(null);
  }
  
  public void delete(int paramInt) {}
  
  public void delete(String paramString) {}
  
  public Object get(int paramInt, Scriptable paramScriptable)
  {
    throw this.members.reportMemberNotFound(Integer.toString(paramInt));
  }
  
  public Object get(String paramString, Scriptable paramScriptable)
  {
    if (this.fieldAndMethods != null)
    {
      paramScriptable = this.fieldAndMethods.get(paramString);
      if (paramScriptable != null) {
        return paramScriptable;
      }
    }
    return this.members.get(this, paramString, this.javaObject, false);
  }
  
  public String getClassName()
  {
    return "JavaObject";
  }
  
  public Object getDefaultValue(Class<?> paramClass)
  {
    Object localObject = paramClass;
    if (paramClass == null)
    {
      localObject = paramClass;
      if ((this.javaObject instanceof Boolean)) {
        localObject = ScriptRuntime.BooleanClass;
      }
    }
    if ((localObject == null) || (localObject == ScriptRuntime.StringClass)) {
      return this.javaObject.toString();
    }
    if (localObject == ScriptRuntime.BooleanClass) {}
    for (paramClass = "booleanValue";; paramClass = "doubleValue")
    {
      paramClass = get(paramClass, this);
      if (!(paramClass instanceof Function)) {
        break label119;
      }
      paramClass = (Function)paramClass;
      return paramClass.call(Context.getContext(), paramClass.getParentScope(), this, ScriptRuntime.emptyArgs);
      if (localObject != ScriptRuntime.NumberClass) {
        break;
      }
    }
    throw Context.reportRuntimeError0("msg.default.value");
    label119:
    if ((localObject == ScriptRuntime.NumberClass) && ((this.javaObject instanceof Boolean)))
    {
      if (((Boolean)this.javaObject).booleanValue()) {}
      for (double d = 1.0D;; d = 0.0D) {
        return ScriptRuntime.wrapNumber(d);
      }
    }
    return this.javaObject.toString();
  }
  
  public Object[] getIds()
  {
    return this.members.getIds(false);
  }
  
  public Scriptable getParentScope()
  {
    return this.parent;
  }
  
  public Scriptable getPrototype()
  {
    if ((this.prototype == null) && ((this.javaObject instanceof String))) {
      return TopLevel.getBuiltinPrototype(ScriptableObject.getTopLevelScope(this.parent), TopLevel.Builtins.String);
    }
    return this.prototype;
  }
  
  public boolean has(int paramInt, Scriptable paramScriptable)
  {
    return false;
  }
  
  public boolean has(String paramString, Scriptable paramScriptable)
  {
    return this.members.has(paramString, false);
  }
  
  public boolean hasInstance(Scriptable paramScriptable)
  {
    return false;
  }
  
  protected void initMembers()
  {
    if (this.javaObject != null) {}
    for (Class localClass = this.javaObject.getClass();; localClass = this.staticType)
    {
      this.members = JavaMembers.lookupClass(this.parent, localClass, this.staticType, this.isAdapter);
      this.fieldAndMethods = this.members.getFieldAndMethodsObjects(this, this.javaObject, false);
      return;
    }
  }
  
  public void put(int paramInt, Scriptable paramScriptable, Object paramObject)
  {
    throw this.members.reportMemberNotFound(Integer.toString(paramInt));
  }
  
  public void put(String paramString, Scriptable paramScriptable, Object paramObject)
  {
    if ((this.prototype == null) || (this.members.has(paramString, false)))
    {
      this.members.put(this, paramString, this.javaObject, paramObject, false);
      return;
    }
    this.prototype.put(paramString, this.prototype, paramObject);
  }
  
  public void setParentScope(Scriptable paramScriptable)
  {
    this.parent = paramScriptable;
  }
  
  public void setPrototype(Scriptable paramScriptable)
  {
    this.prototype = paramScriptable;
  }
  
  public Object unwrap()
  {
    return this.javaObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeJavaObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */