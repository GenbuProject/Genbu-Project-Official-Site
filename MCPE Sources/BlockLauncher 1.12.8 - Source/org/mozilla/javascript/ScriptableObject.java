package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.mozilla.javascript.annotations.JSConstructor;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;
import org.mozilla.javascript.annotations.JSStaticFunction;
import org.mozilla.javascript.debug.DebuggableObject;

public abstract class ScriptableObject
  implements Scriptable, Serializable, DebuggableObject, ConstProperties
{
  public static final int CONST = 13;
  public static final int DONTENUM = 2;
  public static final int EMPTY = 0;
  private static final Method GET_ARRAY_LENGTH;
  private static final int INITIAL_SLOT_SIZE = 4;
  private static final Comparator<Object> KEY_COMPARATOR;
  public static final int PERMANENT = 4;
  public static final int READONLY = 1;
  private static final int SLOT_CONVERT_ACCESSOR_TO_DATA = 5;
  private static final int SLOT_MODIFY = 2;
  private static final int SLOT_MODIFY_CONST = 3;
  private static final int SLOT_MODIFY_GETTER_SETTER = 4;
  private static final int SLOT_QUERY = 1;
  public static final int UNINITIALIZED_CONST = 8;
  static final long serialVersionUID = 2829861078851942586L;
  private volatile Map<Object, Object> associatedValues;
  private int count;
  private transient ExternalArrayData externalData;
  private transient Slot firstAdded;
  private boolean isExtensible = true;
  private transient Slot lastAdded;
  private Scriptable parentScopeObject;
  private Scriptable prototypeObject;
  private transient Slot[] slots;
  
  static
  {
    boolean bool = false;
    if (!ScriptableObject.class.desiredAssertionStatus()) {
      bool = true;
    }
    $assertionsDisabled = bool;
    try
    {
      GET_ARRAY_LENGTH = ScriptableObject.class.getMethod("getExternalArrayLength", new Class[0]);
      KEY_COMPARATOR = new KeyComparator(null);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new RuntimeException(localNoSuchMethodException);
    }
  }
  
  public ScriptableObject() {}
  
  public ScriptableObject(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    if (paramScriptable1 == null) {
      throw new IllegalArgumentException();
    }
    this.parentScopeObject = paramScriptable1;
    this.prototypeObject = paramScriptable2;
  }
  
  private static void addKnownAbsentSlot(Slot[] paramArrayOfSlot, Slot paramSlot, int paramInt)
  {
    if (paramArrayOfSlot[paramInt] == null)
    {
      paramArrayOfSlot[paramInt] = paramSlot;
      return;
    }
    Object localObject = paramArrayOfSlot[paramInt];
    Slot localSlot;
    for (paramArrayOfSlot = ((Slot)localObject).next; paramArrayOfSlot != null; paramArrayOfSlot = localSlot)
    {
      localSlot = paramArrayOfSlot.next;
      localObject = paramArrayOfSlot;
    }
    ((Slot)localObject).next = paramSlot;
  }
  
  static <T extends Scriptable> BaseFunction buildClassCtor(Scriptable paramScriptable, Class<T> paramClass, boolean paramBoolean1, boolean paramBoolean2)
    throws IllegalAccessException, InstantiationException, InvocationTargetException
  {
    Method[] arrayOfMethod = FunctionObject.getMethodList(paramClass);
    int i = 0;
    Object localObject2;
    if (i < arrayOfMethod.length)
    {
      localObject1 = arrayOfMethod[i];
      if (!((Method)localObject1).getName().equals("init")) {}
      do
      {
        i += 1;
        break;
        localObject2 = ((Method)localObject1).getParameterTypes();
        if ((localObject2.length == 3) && (localObject2[0] == ScriptRuntime.ContextClass) && (localObject2[1] == ScriptRuntime.ScriptableClass) && (localObject2[2] == Boolean.TYPE) && (Modifier.isStatic(((Method)localObject1).getModifiers())))
        {
          localObject2 = Context.getContext();
          if (paramBoolean1) {}
          for (paramClass = Boolean.TRUE;; paramClass = Boolean.FALSE)
          {
            ((Method)localObject1).invoke(null, new Object[] { localObject2, paramScriptable, paramClass });
            return null;
          }
        }
      } while ((localObject2.length != 1) || (localObject2[0] != ScriptRuntime.ScriptableClass) || (!Modifier.isStatic(((Method)localObject1).getModifiers())));
      ((Method)localObject1).invoke(null, new Object[] { paramScriptable });
      return null;
    }
    Object localObject4 = paramClass.getConstructors();
    i = 0;
    if (i < localObject4.length) {
      if (localObject4[i].getParameterTypes().length != 0) {}
    }
    for (Object localObject1 = localObject4[i];; localObject1 = null)
    {
      if (localObject1 == null)
      {
        throw Context.reportRuntimeError1("msg.zero.arg.ctor", paramClass.getName());
        i += 1;
        break;
      }
      ((Constructor)localObject1).setAccessible(true);
      Scriptable localScriptable = (Scriptable)((Constructor)localObject1).newInstance(ScriptRuntime.emptyArgs);
      Object localObject5 = localScriptable.getClassName();
      localObject1 = getProperty(getTopLevelScope(paramScriptable), (String)localObject5);
      if ((localObject1 instanceof BaseFunction))
      {
        localObject2 = ((BaseFunction)localObject1).getPrototypeProperty();
        if ((localObject2 != null) && (paramClass.equals(localObject2.getClass()))) {
          return (BaseFunction)localObject1;
        }
      }
      localObject2 = null;
      localObject1 = localObject2;
      if (paramBoolean2)
      {
        localObject3 = paramClass.getSuperclass();
        localObject1 = localObject2;
        if (ScriptRuntime.ScriptableClass.isAssignableFrom((Class)localObject3))
        {
          localObject1 = localObject2;
          if (!Modifier.isAbstract(((Class)localObject3).getModifiers()))
          {
            localObject3 = defineClass(paramScriptable, extendsScriptable((Class)localObject3), paramBoolean1, paramBoolean2);
            localObject1 = localObject2;
            if (localObject3 != null) {
              localObject1 = getClassPrototype(paramScriptable, (String)localObject3);
            }
          }
        }
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = getObjectPrototype(paramScriptable);
      }
      localScriptable.setPrototype((Scriptable)localObject2);
      localObject2 = findAnnotatedMember(arrayOfMethod, JSConstructor.class);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = findAnnotatedMember((AccessibleObject[])localObject4, JSConstructor.class);
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = FunctionObject.findSingleMethod(arrayOfMethod, "jsConstructor");
      }
      Object localObject3 = localObject2;
      if (localObject2 == null)
      {
        if (localObject4.length == 1) {
          localObject1 = localObject4[0];
        }
        for (;;)
        {
          localObject3 = localObject1;
          if (localObject1 != null) {
            break;
          }
          throw Context.reportRuntimeError1("msg.ctor.multiple.parms", paramClass.getName());
          localObject1 = localObject2;
          if (localObject4.length == 2) {
            if (localObject4[0].getParameterTypes().length == 0)
            {
              localObject1 = localObject4[1];
            }
            else
            {
              localObject1 = localObject2;
              if (localObject4[1].getParameterTypes().length == 0) {
                localObject1 = localObject4[0];
              }
            }
          }
        }
      }
      localObject5 = new FunctionObject((String)localObject5, (Member)localObject3, paramScriptable);
      if (((FunctionObject)localObject5).isVarArgsMethod()) {
        throw Context.reportRuntimeError1("msg.varargs.ctor", ((Member)localObject3).getName());
      }
      ((FunctionObject)localObject5).initAsConstructor(paramScriptable, localScriptable);
      paramClass = null;
      HashSet localHashSet1 = new HashSet();
      HashSet localHashSet2 = new HashSet();
      int k = arrayOfMethod.length;
      i = 0;
      Method localMethod;
      String str1;
      if (i < k)
      {
        localMethod = arrayOfMethod[i];
        if (localMethod == localObject3) {
          localObject1 = paramClass;
        }
        for (;;)
        {
          i += 1;
          paramClass = (Class<T>)localObject1;
          break;
          str1 = localMethod.getName();
          if (str1.equals("finishInit"))
          {
            localObject1 = localMethod.getParameterTypes();
            if ((localObject1.length == 3) && (localObject1[0] == ScriptRuntime.ScriptableClass) && (localObject1[1] == FunctionObject.class) && (localObject1[2] == ScriptRuntime.ScriptableClass))
            {
              localObject1 = localMethod;
              if (Modifier.isStatic(localMethod.getModifiers())) {
                continue;
              }
            }
          }
          if (str1.indexOf('$') != -1)
          {
            localObject1 = paramClass;
          }
          else
          {
            if (!str1.equals("jsConstructor")) {
              break label796;
            }
            localObject1 = paramClass;
          }
        }
        label796:
        localObject4 = null;
        if (localMethod.isAnnotationPresent(JSFunction.class)) {
          localObject1 = localMethod.getAnnotation(JSFunction.class);
        }
      }
      for (;;)
      {
        label820:
        localObject2 = localObject4;
        label845:
        int j;
        if (localObject1 == null)
        {
          if (str1.startsWith("jsFunction_")) {
            localObject2 = "jsFunction_";
          }
        }
        else
        {
          if ((!(localObject1 instanceof JSStaticFunction)) && (localObject2 != "jsStaticFunction_")) {
            break label1023;
          }
          j = 1;
          label864:
          if (j == 0) {
            break label1029;
          }
        }
        String str2;
        label1023:
        label1029:
        for (localObject4 = localHashSet1;; localObject4 = localHashSet2)
        {
          str2 = getPropertyName(str1, (String)localObject2, (Annotation)localObject1);
          if (!((HashSet)localObject4).contains(str2)) {
            break label1036;
          }
          throw Context.reportRuntimeError2("duplicate.defineClass.name", str1, str2);
          if (localMethod.isAnnotationPresent(JSStaticFunction.class))
          {
            localObject1 = localMethod.getAnnotation(JSStaticFunction.class);
            break label820;
          }
          if (localMethod.isAnnotationPresent(JSGetter.class))
          {
            localObject1 = localMethod.getAnnotation(JSGetter.class);
            break label820;
          }
          if (!localMethod.isAnnotationPresent(JSSetter.class)) {
            break label1294;
          }
          localObject1 = paramClass;
          break;
          if (str1.startsWith("jsStaticFunction_"))
          {
            localObject2 = "jsStaticFunction_";
            break label845;
          }
          if (str1.startsWith("jsGet_"))
          {
            localObject2 = "jsGet_";
            break label845;
          }
          localObject2 = localObject4;
          if (localObject1 != null) {
            break label845;
          }
          localObject1 = paramClass;
          break;
          j = 0;
          break label864;
        }
        label1036:
        ((HashSet)localObject4).add(str2);
        if (((localObject1 instanceof JSGetter)) || (localObject2 == "jsGet_"))
        {
          if (!(localScriptable instanceof ScriptableObject)) {
            throw Context.reportRuntimeError2("msg.extend.scriptable", localScriptable.getClass().toString(), str2);
          }
          localObject1 = findSetterMethod(arrayOfMethod, str2, "jsSet_");
          if (localObject1 != null) {}
          for (j = 0;; j = 1)
          {
            ((ScriptableObject)localScriptable).defineProperty(str2, null, localMethod, (Method)localObject1, j | 0x6);
            localObject1 = paramClass;
            break;
          }
        }
        if ((j != 0) && (!Modifier.isStatic(localMethod.getModifiers()))) {
          throw Context.reportRuntimeError("jsStaticFunction must be used with static method.");
        }
        localObject2 = new FunctionObject(str2, localMethod, localScriptable);
        if (((FunctionObject)localObject2).isVarArgsConstructor()) {
          throw Context.reportRuntimeError1("msg.varargs.fun", ((Member)localObject3).getName());
        }
        if (j != 0) {}
        for (localObject1 = localObject5;; localObject1 = localScriptable)
        {
          defineProperty((Scriptable)localObject1, str2, localObject2, 2);
          if (paramBoolean1) {
            ((FunctionObject)localObject2).sealObject();
          }
          localObject1 = paramClass;
          break;
        }
        if (paramClass != null) {
          paramClass.invoke(null, new Object[] { paramScriptable, localObject5, localScriptable });
        }
        if (paramBoolean1)
        {
          ((FunctionObject)localObject5).sealObject();
          if ((localScriptable instanceof ScriptableObject)) {
            ((ScriptableObject)localScriptable).sealObject();
          }
        }
        return (BaseFunction)localObject5;
        label1294:
        localObject1 = null;
      }
    }
  }
  
  protected static ScriptableObject buildDataDescriptor(Scriptable paramScriptable, Object paramObject, int paramInt)
  {
    boolean bool2 = true;
    NativeObject localNativeObject = new NativeObject();
    ScriptRuntime.setBuiltinProtoAndParent(localNativeObject, paramScriptable, TopLevel.Builtins.Object);
    localNativeObject.defineProperty("value", paramObject, 0);
    if ((paramInt & 0x1) == 0)
    {
      bool1 = true;
      localNativeObject.defineProperty("writable", Boolean.valueOf(bool1), 0);
      if ((paramInt & 0x2) != 0) {
        break label103;
      }
      bool1 = true;
      label60:
      localNativeObject.defineProperty("enumerable", Boolean.valueOf(bool1), 0);
      if ((paramInt & 0x4) != 0) {
        break label108;
      }
    }
    label103:
    label108:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      localNativeObject.defineProperty("configurable", Boolean.valueOf(bool1), 0);
      return localNativeObject;
      bool1 = false;
      break;
      bool1 = false;
      break label60;
    }
  }
  
  public static Object callMethod(Context paramContext, Scriptable paramScriptable, String paramString, Object[] paramArrayOfObject)
  {
    Object localObject = getProperty(paramScriptable, paramString);
    if (!(localObject instanceof Function)) {
      throw ScriptRuntime.notFunctionError(paramScriptable, paramString);
    }
    paramString = (Function)localObject;
    localObject = getTopLevelScope(paramScriptable);
    if (paramContext != null) {
      return paramString.call(paramContext, (Scriptable)localObject, paramScriptable, paramArrayOfObject);
    }
    return Context.call(null, paramString, (Scriptable)localObject, paramScriptable, paramArrayOfObject);
  }
  
  public static Object callMethod(Scriptable paramScriptable, String paramString, Object[] paramArrayOfObject)
  {
    return callMethod(null, paramScriptable, paramString, paramArrayOfObject);
  }
  
  private void checkNotSealed(String paramString, int paramInt)
  {
    if (!isSealed()) {
      return;
    }
    if (paramString != null) {}
    for (;;)
    {
      throw Context.reportRuntimeError1("msg.modify.sealed", paramString);
      paramString = Integer.toString(paramInt);
    }
  }
  
  static void checkValidAttributes(int paramInt)
  {
    if ((paramInt & 0xFFFFFFF0) != 0) {
      throw new IllegalArgumentException(String.valueOf(paramInt));
    }
  }
  
  private static void copyTable(Slot[] paramArrayOfSlot1, Slot[] paramArrayOfSlot2, int paramInt)
  {
    if (paramInt == 0) {
      throw Kit.codeBug();
    }
    int m = paramArrayOfSlot2.length;
    int j = paramArrayOfSlot1.length;
    int i = paramInt;
    Slot localSlot;
    do
    {
      int k = j - 1;
      localSlot = paramArrayOfSlot1[k];
      paramInt = i;
      j = k;
      i = paramInt;
    } while (localSlot == null);
    i = getSlotIndex(m, localSlot.indexOrHash);
    if (localSlot.next == null) {}
    for (Object localObject = localSlot;; localObject = new RelinkedSlot(localSlot))
    {
      addKnownAbsentSlot(paramArrayOfSlot2, (Slot)localObject, i);
      localSlot = localSlot.next;
      i = paramInt - 1;
      paramInt = i;
      if (i != 0) {
        break;
      }
      return;
    }
  }
  
  private Slot createSlot(String paramString, int paramInt1, int paramInt2)
  {
    for (;;)
    {
      Slot[] arrayOfSlot;
      int i;
      Slot localSlot;
      try
      {
        arrayOfSlot = this.slots;
        if (this.count == 0)
        {
          localObject1 = new Slot[4];
          this.slots = ((Slot[])localObject1);
          i = getSlotIndex(localObject1.length, paramInt1);
          if (paramInt2 != 4) {
            break label407;
          }
          paramString = new GetterSlot(paramString, paramInt1, 0);
          if (paramInt2 == 3) {
            paramString.setAttributes(13);
          }
          this.count += 1;
          if (this.lastAdded != null) {
            this.lastAdded.orderedNext = paramString;
          }
          if (this.firstAdded == null) {
            this.firstAdded = paramString;
          }
          this.lastAdded = paramString;
          addKnownAbsentSlot((Slot[])localObject1, paramString, i);
          return paramString;
        }
        i = getSlotIndex(arrayOfSlot.length, paramInt1);
        localObject2 = arrayOfSlot[i];
        localObject1 = localObject2;
        if ((localObject1 == null) || ((((Slot)localObject1).indexOrHash == paramInt1) && ((((Slot)localObject1).name == paramString) || ((paramString != null) && (paramString.equals(((Slot)localObject1).name))))))
        {
          if (localObject1 == null) {
            break label350;
          }
          localSlot = unwrapSlot((Slot)localObject1);
          if ((paramInt2 != 4) || ((localSlot instanceof GetterSlot))) {
            break label310;
          }
          paramString = new GetterSlot(paramString, paramInt1, localSlot.getAttributes());
          paramString.value = localSlot.value;
          paramString.next = ((Slot)localObject1).next;
          if (this.lastAdded != null) {
            this.lastAdded.orderedNext = paramString;
          }
          if (this.firstAdded == null) {
            this.firstAdded = paramString;
          }
          this.lastAdded = paramString;
          if (localObject2 != localObject1) {
            break label341;
          }
          arrayOfSlot[i] = paramString;
          ((Slot)localObject1).markDeleted();
          continue;
        }
        localSlot = ((Slot)localObject1).next;
      }
      finally {}
      Object localObject2 = localObject1;
      Object localObject1 = localSlot;
      continue;
      label310:
      if ((paramInt2 == 5) && ((localSlot instanceof GetterSlot)))
      {
        paramString = new Slot(paramString, paramInt1, localSlot.getAttributes());
        continue;
        label341:
        ((Slot)localObject2).next = paramString;
        continue;
        label350:
        if ((this.count + 1) * 4 > arrayOfSlot.length * 3)
        {
          localObject1 = new Slot[arrayOfSlot.length * 2];
          copyTable(this.slots, (Slot[])localObject1, this.count);
          this.slots = ((Slot[])localObject1);
          i = getSlotIndex(localObject1.length, paramInt1);
          continue;
          label407:
          paramString = new Slot(paramString, paramInt1, 0);
        }
        else
        {
          localObject1 = arrayOfSlot;
        }
      }
      else if (paramInt2 == 3)
      {
        paramString = null;
      }
      else
      {
        paramString = localSlot;
      }
    }
  }
  
  public static <T extends Scriptable> String defineClass(Scriptable paramScriptable, Class<T> paramClass, boolean paramBoolean1, boolean paramBoolean2)
    throws IllegalAccessException, InstantiationException, InvocationTargetException
  {
    paramClass = buildClassCtor(paramScriptable, paramClass, paramBoolean1, paramBoolean2);
    if (paramClass == null) {
      return null;
    }
    String str = paramClass.getClassPrototype().getClassName();
    defineProperty(paramScriptable, str, paramClass, 2);
    return str;
  }
  
  public static <T extends Scriptable> void defineClass(Scriptable paramScriptable, Class<T> paramClass)
    throws IllegalAccessException, InstantiationException, InvocationTargetException
  {
    defineClass(paramScriptable, paramClass, false, false);
  }
  
  public static <T extends Scriptable> void defineClass(Scriptable paramScriptable, Class<T> paramClass, boolean paramBoolean)
    throws IllegalAccessException, InstantiationException, InvocationTargetException
  {
    defineClass(paramScriptable, paramClass, paramBoolean, false);
  }
  
  public static void defineConstProperty(Scriptable paramScriptable, String paramString)
  {
    if ((paramScriptable instanceof ConstProperties))
    {
      ((ConstProperties)paramScriptable).defineConst(paramString, paramScriptable);
      return;
    }
    defineProperty(paramScriptable, paramString, Undefined.instance, 13);
  }
  
  public static void defineProperty(Scriptable paramScriptable, String paramString, Object paramObject, int paramInt)
  {
    if (!(paramScriptable instanceof ScriptableObject))
    {
      paramScriptable.put(paramString, paramScriptable, paramObject);
      return;
    }
    ((ScriptableObject)paramScriptable).defineProperty(paramString, paramObject, paramInt);
  }
  
  public static boolean deleteProperty(Scriptable paramScriptable, int paramInt)
  {
    Scriptable localScriptable = getBase(paramScriptable, paramInt);
    if (localScriptable == null) {}
    do
    {
      return true;
      localScriptable.delete(paramInt);
    } while (!localScriptable.has(paramInt, paramScriptable));
    return false;
  }
  
  public static boolean deleteProperty(Scriptable paramScriptable, String paramString)
  {
    Scriptable localScriptable = getBase(paramScriptable, paramString);
    if (localScriptable == null) {}
    do
    {
      return true;
      localScriptable.delete(paramString);
    } while (!localScriptable.has(paramString, paramScriptable));
    return false;
  }
  
  protected static Scriptable ensureScriptable(Object paramObject)
  {
    if (!(paramObject instanceof Scriptable)) {
      throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(paramObject));
    }
    return (Scriptable)paramObject;
  }
  
  protected static ScriptableObject ensureScriptableObject(Object paramObject)
  {
    if (!(paramObject instanceof ScriptableObject)) {
      throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(paramObject));
    }
    return (ScriptableObject)paramObject;
  }
  
  private static <T extends Scriptable> Class<T> extendsScriptable(Class<?> paramClass)
  {
    if (ScriptRuntime.ScriptableClass.isAssignableFrom(paramClass)) {
      return paramClass;
    }
    return null;
  }
  
  private static Member findAnnotatedMember(AccessibleObject[] paramArrayOfAccessibleObject, Class<? extends Annotation> paramClass)
  {
    int j = paramArrayOfAccessibleObject.length;
    int i = 0;
    while (i < j)
    {
      AccessibleObject localAccessibleObject = paramArrayOfAccessibleObject[i];
      if (localAccessibleObject.isAnnotationPresent(paramClass)) {
        return (Member)localAccessibleObject;
      }
      i += 1;
    }
    return null;
  }
  
  private Slot findAttributeSlot(String paramString, int paramInt1, int paramInt2)
  {
    Slot localSlot = getSlot(paramString, paramInt1, paramInt2);
    if (localSlot == null)
    {
      if (paramString != null) {}
      for (;;)
      {
        throw Context.reportRuntimeError1("msg.prop.not.found", paramString);
        paramString = Integer.toString(paramInt1);
      }
    }
    return localSlot;
  }
  
  private static Method findSetterMethod(Method[] paramArrayOfMethod, String paramString1, String paramString2)
  {
    String str = "set" + Character.toUpperCase(paramString1.charAt(0)) + paramString1.substring(1);
    int j = paramArrayOfMethod.length;
    int i = 0;
    while (i < j)
    {
      localObject = paramArrayOfMethod[i];
      JSSetter localJSSetter = (JSSetter)((Method)localObject).getAnnotation(JSSetter.class);
      if ((localJSSetter != null) && ((paramString1.equals(localJSSetter.value())) || (("".equals(localJSSetter.value())) && (str.equals(((Method)localObject).getName())))))
      {
        paramString1 = (String)localObject;
        return paramString1;
      }
      i += 1;
    }
    Object localObject = paramString2 + paramString1;
    j = paramArrayOfMethod.length;
    i = 0;
    for (;;)
    {
      if (i >= j) {
        break label184;
      }
      paramString2 = paramArrayOfMethod[i];
      paramString1 = paramString2;
      if (((String)localObject).equals(paramString2.getName())) {
        break;
      }
      i += 1;
    }
    label184:
    return null;
  }
  
  public static Scriptable getArrayPrototype(Scriptable paramScriptable)
  {
    return TopLevel.getBuiltinPrototype(getTopLevelScope(paramScriptable), TopLevel.Builtins.Array);
  }
  
  private static Scriptable getBase(Scriptable paramScriptable, int paramInt)
  {
    Scriptable localScriptable;
    do
    {
      if (paramScriptable.has(paramInt, paramScriptable)) {
        return paramScriptable;
      }
      localScriptable = paramScriptable.getPrototype();
      paramScriptable = localScriptable;
    } while (localScriptable != null);
    return localScriptable;
  }
  
  private static Scriptable getBase(Scriptable paramScriptable, String paramString)
  {
    Scriptable localScriptable;
    do
    {
      if (paramScriptable.has(paramString, paramScriptable)) {
        return paramScriptable;
      }
      localScriptable = paramScriptable.getPrototype();
      paramScriptable = localScriptable;
    } while (localScriptable != null);
    return localScriptable;
  }
  
  public static Scriptable getClassPrototype(Scriptable paramScriptable, String paramString)
  {
    paramScriptable = getProperty(getTopLevelScope(paramScriptable), paramString);
    if ((paramScriptable instanceof BaseFunction)) {
      paramScriptable = ((BaseFunction)paramScriptable).getPrototypeProperty();
    }
    while ((paramScriptable instanceof Scriptable))
    {
      return (Scriptable)paramScriptable;
      if ((paramScriptable instanceof Scriptable))
      {
        paramScriptable = (Scriptable)paramScriptable;
        paramScriptable = paramScriptable.get("prototype", paramScriptable);
      }
      else
      {
        return null;
      }
    }
    return null;
  }
  
  public static Object getDefaultValue(Scriptable paramScriptable, Class<?> paramClass)
  {
    Object localObject2 = null;
    int j = 0;
    if (j < 2)
    {
      int i;
      label23:
      Object localObject3;
      Object localObject1;
      if (paramClass == ScriptRuntime.StringClass) {
        if (j == 0)
        {
          i = 1;
          if (i == 0) {
            break label80;
          }
          localObject3 = ScriptRuntime.emptyArgs;
          localObject1 = "toString";
          localObject1 = getProperty(paramScriptable, (String)localObject1);
          if ((localObject1 instanceof Function)) {
            break label272;
          }
        }
      }
      label80:
      label261:
      label272:
      do
      {
        do
        {
          do
          {
            do
            {
              j += 1;
              break;
              i = 0;
              break label23;
              if (j == 1) {}
              for (i = 1;; i = 0) {
                break;
              }
              localObject3 = new Object[1];
              if (paramClass == null) {
                localObject1 = "undefined";
              }
              for (;;)
              {
                localObject3[0] = localObject1;
                localObject1 = "valueOf";
                break;
                if (paramClass == ScriptRuntime.StringClass)
                {
                  localObject1 = "string";
                }
                else if (paramClass == ScriptRuntime.ScriptableClass)
                {
                  localObject1 = "object";
                }
                else if (paramClass == ScriptRuntime.FunctionClass)
                {
                  localObject1 = "function";
                }
                else if ((paramClass == ScriptRuntime.BooleanClass) || (paramClass == Boolean.TYPE))
                {
                  localObject1 = "boolean";
                }
                else
                {
                  if ((paramClass != ScriptRuntime.NumberClass) && (paramClass != ScriptRuntime.ByteClass) && (paramClass != Byte.TYPE) && (paramClass != ScriptRuntime.ShortClass) && (paramClass != Short.TYPE) && (paramClass != ScriptRuntime.IntegerClass) && (paramClass != Integer.TYPE) && (paramClass != ScriptRuntime.FloatClass) && (paramClass != Float.TYPE) && (paramClass != ScriptRuntime.DoubleClass) && (paramClass != Double.TYPE)) {
                    break label261;
                  }
                  localObject1 = "number";
                }
              }
              throw Context.reportRuntimeError1("msg.invalid.type", paramClass.toString());
              Function localFunction = (Function)localObject1;
              localObject1 = localObject2;
              if (localObject2 == null) {
                localObject1 = Context.getContext();
              }
              localObject3 = localFunction.call((Context)localObject1, localFunction.getParentScope(), paramScriptable, (Object[])localObject3);
              localObject2 = localObject1;
            } while (localObject3 == null);
            if (!(localObject3 instanceof Scriptable)) {}
            while ((paramClass == ScriptRuntime.ScriptableClass) || (paramClass == ScriptRuntime.FunctionClass)) {
              return localObject3;
            }
            localObject2 = localObject1;
          } while (i == 0);
          localObject2 = localObject1;
        } while (!(localObject3 instanceof Wrapper));
        localObject3 = ((Wrapper)localObject3).unwrap();
        localObject2 = localObject1;
      } while (!(localObject3 instanceof String));
      return localObject3;
    }
    if (paramClass == null) {}
    for (paramScriptable = "undefined";; paramScriptable = paramClass.getName()) {
      throw ScriptRuntime.typeError1("msg.default.value", paramScriptable);
    }
  }
  
  public static Scriptable getFunctionPrototype(Scriptable paramScriptable)
  {
    return TopLevel.getBuiltinPrototype(getTopLevelScope(paramScriptable), TopLevel.Builtins.Function);
  }
  
  public static Scriptable getObjectPrototype(Scriptable paramScriptable)
  {
    return TopLevel.getBuiltinPrototype(getTopLevelScope(paramScriptable), TopLevel.Builtins.Object);
  }
  
  public static Object getProperty(Scriptable paramScriptable, int paramInt)
  {
    Object localObject1 = paramScriptable;
    Object localObject2;
    Scriptable localScriptable;
    do
    {
      localObject2 = ((Scriptable)localObject1).get(paramInt, paramScriptable);
      if (localObject2 != Scriptable.NOT_FOUND) {
        return localObject2;
      }
      localScriptable = ((Scriptable)localObject1).getPrototype();
      localObject1 = localScriptable;
    } while (localScriptable != null);
    return localObject2;
  }
  
  public static Object getProperty(Scriptable paramScriptable, String paramString)
  {
    Object localObject1 = paramScriptable;
    Object localObject2;
    Scriptable localScriptable;
    do
    {
      localObject2 = ((Scriptable)localObject1).get(paramString, paramScriptable);
      if (localObject2 != Scriptable.NOT_FOUND) {
        return localObject2;
      }
      localScriptable = ((Scriptable)localObject1).getPrototype();
      localObject1 = localScriptable;
    } while (localScriptable != null);
    return localObject2;
  }
  
  public static Object[] getPropertyIds(Scriptable paramScriptable)
  {
    if (paramScriptable == null)
    {
      paramScriptable = ScriptRuntime.emptyArgs;
      return paramScriptable;
    }
    Object localObject2 = paramScriptable.getIds();
    ObjToIntMap localObjToIntMap = null;
    Object localObject1 = paramScriptable;
    paramScriptable = (Scriptable)localObject2;
    Object[] arrayOfObject;
    for (;;)
    {
      localObject2 = ((Scriptable)localObject1).getPrototype();
      if (localObject2 == null)
      {
        if (localObjToIntMap == null) {
          break;
        }
        return localObjToIntMap.getKeys();
      }
      arrayOfObject = ((Scriptable)localObject2).getIds();
      localObject1 = localObject2;
      if (arrayOfObject.length != 0)
      {
        if (localObjToIntMap != null) {
          break label153;
        }
        if (paramScriptable.length != 0) {
          break label83;
        }
        paramScriptable = arrayOfObject;
        localObject1 = localObject2;
      }
    }
    label83:
    localObjToIntMap = new ObjToIntMap(paramScriptable.length + arrayOfObject.length);
    int i = 0;
    while (i != paramScriptable.length)
    {
      localObjToIntMap.intern(paramScriptable[i]);
      i += 1;
    }
    paramScriptable = null;
    label153:
    for (;;)
    {
      i = 0;
      while (i != arrayOfObject.length)
      {
        localObjToIntMap.intern(arrayOfObject[i]);
        i += 1;
      }
      localObject1 = localObject2;
      break;
    }
  }
  
  private static String getPropertyName(String paramString1, String paramString2, Annotation paramAnnotation)
  {
    if (paramString2 != null)
    {
      paramAnnotation = paramString1.substring(paramString2.length());
      return paramAnnotation;
    }
    paramString2 = null;
    if ((paramAnnotation instanceof JSGetter))
    {
      paramAnnotation = ((JSGetter)paramAnnotation).value();
      if (paramAnnotation != null)
      {
        paramString2 = paramAnnotation;
        if (paramAnnotation.length() != 0) {}
      }
      else
      {
        paramString2 = paramAnnotation;
        if (paramString1.length() > 3)
        {
          paramString2 = paramAnnotation;
          if (paramString1.startsWith("get"))
          {
            paramAnnotation = paramString1.substring(3);
            paramString2 = paramAnnotation;
            if (Character.isUpperCase(paramAnnotation.charAt(0)))
            {
              if (paramAnnotation.length() != 1) {
                break label118;
              }
              paramString2 = paramAnnotation.toLowerCase();
            }
          }
        }
      }
    }
    for (;;)
    {
      paramAnnotation = paramString1;
      if (paramString2 == null) {
        break;
      }
      paramAnnotation = paramString1;
      if (paramString2.length() == 0) {
        break;
      }
      return paramString2;
      label118:
      paramString2 = paramAnnotation;
      if (!Character.isUpperCase(paramAnnotation.charAt(1)))
      {
        paramString2 = Character.toLowerCase(paramAnnotation.charAt(0)) + paramAnnotation.substring(1);
        continue;
        if ((paramAnnotation instanceof JSFunction)) {
          paramString2 = ((JSFunction)paramAnnotation).value();
        } else if ((paramAnnotation instanceof JSStaticFunction)) {
          paramString2 = ((JSStaticFunction)paramAnnotation).value();
        }
      }
    }
  }
  
  private Slot getSlot(String paramString, int paramInt1, int paramInt2)
  {
    Object localObject1 = this.slots;
    Object localObject2;
    if ((localObject1 == null) && (paramInt2 == 1))
    {
      localObject2 = null;
      return (Slot)localObject2;
    }
    if (paramString != null) {
      paramInt1 = paramString.hashCode();
    }
    if (localObject1 != null)
    {
      localObject1 = localObject1[getSlotIndex(localObject1.length, paramInt1)];
      if (localObject1 != null)
      {
        localObject2 = ((Slot)localObject1).name;
        if ((paramInt1 != ((Slot)localObject1).indexOrHash) || ((localObject2 != paramString) && ((paramString == null) || (!paramString.equals(localObject2))))) {
          break label136;
        }
      }
      localObject2 = localObject1;
      switch (paramInt2)
      {
      }
    }
    label136:
    do
    {
      do
      {
        do
        {
          return createSlot(paramString, paramInt1, paramInt2);
          localObject1 = ((Slot)localObject1).next;
          break;
        } while (localObject1 == null);
        return (Slot)localObject1;
        localObject1 = unwrapSlot((Slot)localObject1);
      } while (!(localObject1 instanceof GetterSlot));
      return (Slot)localObject1;
      localObject1 = unwrapSlot((Slot)localObject1);
    } while ((localObject1 instanceof GetterSlot));
    return (Slot)localObject1;
  }
  
  private static int getSlotIndex(int paramInt1, int paramInt2)
  {
    return paramInt1 - 1 & paramInt2;
  }
  
  public static Scriptable getTopLevelScope(Scriptable paramScriptable)
  {
    for (;;)
    {
      Scriptable localScriptable = paramScriptable.getParentScope();
      if (localScriptable == null) {
        return paramScriptable;
      }
      paramScriptable = localScriptable;
    }
  }
  
  public static Object getTopScopeValue(Scriptable paramScriptable, Object paramObject)
  {
    paramScriptable = getTopLevelScope(paramScriptable);
    Object localObject;
    do
    {
      if ((paramScriptable instanceof ScriptableObject))
      {
        localObject = ((ScriptableObject)paramScriptable).getAssociatedValue(paramObject);
        if (localObject != null) {
          return localObject;
        }
      }
      localObject = paramScriptable.getPrototype();
      paramScriptable = (Scriptable)localObject;
    } while (localObject != null);
    return null;
  }
  
  public static <T> T getTypedProperty(Scriptable paramScriptable, int paramInt, Class<T> paramClass)
  {
    Object localObject = getProperty(paramScriptable, paramInt);
    paramScriptable = (Scriptable)localObject;
    if (localObject == Scriptable.NOT_FOUND) {
      paramScriptable = null;
    }
    return (T)paramClass.cast(Context.jsToJava(paramScriptable, paramClass));
  }
  
  public static <T> T getTypedProperty(Scriptable paramScriptable, String paramString, Class<T> paramClass)
  {
    paramString = getProperty(paramScriptable, paramString);
    paramScriptable = paramString;
    if (paramString == Scriptable.NOT_FOUND) {
      paramScriptable = null;
    }
    return (T)paramClass.cast(Context.jsToJava(paramScriptable, paramClass));
  }
  
  public static boolean hasProperty(Scriptable paramScriptable, int paramInt)
  {
    return getBase(paramScriptable, paramInt) != null;
  }
  
  public static boolean hasProperty(Scriptable paramScriptable, String paramString)
  {
    return getBase(paramScriptable, paramString) != null;
  }
  
  protected static boolean isFalse(Object paramObject)
  {
    return !isTrue(paramObject);
  }
  
  protected static boolean isTrue(Object paramObject)
  {
    return (paramObject != NOT_FOUND) && (ScriptRuntime.toBoolean(paramObject));
  }
  
  private boolean putConstImpl(String paramString, int paramInt1, Scriptable paramScriptable, Object paramObject, int paramInt2)
  {
    assert (paramInt2 != 0);
    if ((!this.isExtensible) && (Context.getContext().isStrictMode())) {
      throw ScriptRuntime.typeError0("msg.not.extensible");
    }
    Slot localSlot;
    if (this != paramScriptable)
    {
      localSlot = getSlot(paramString, paramInt1, 1);
      paramString = localSlot;
      if (localSlot == null) {
        return false;
      }
    }
    else if (!isExtensible())
    {
      localSlot = getSlot(paramString, paramInt1, 1);
      paramString = localSlot;
      if (localSlot == null) {
        return true;
      }
    }
    else
    {
      checkNotSealed(paramString, paramInt1);
      paramScriptable = unwrapSlot(getSlot(paramString, paramInt1, 3));
      paramInt1 = paramScriptable.getAttributes();
      if ((paramInt1 & 0x1) == 0) {
        throw Context.reportRuntimeError1("msg.var.redecl", paramString);
      }
      if ((paramInt1 & 0x8) != 0)
      {
        paramScriptable.value = paramObject;
        if (paramInt2 != 8) {
          paramScriptable.setAttributes(paramInt1 & 0xFFFFFFF7);
        }
      }
      return true;
    }
    return paramString.setValue(paramObject, this, paramScriptable);
  }
  
  public static void putConstProperty(Scriptable paramScriptable, String paramString, Object paramObject)
  {
    Scriptable localScriptable2 = getBase(paramScriptable, paramString);
    Scriptable localScriptable1 = localScriptable2;
    if (localScriptable2 == null) {
      localScriptable1 = paramScriptable;
    }
    if ((localScriptable1 instanceof ConstProperties)) {
      ((ConstProperties)localScriptable1).putConst(paramString, paramScriptable, paramObject);
    }
  }
  
  private boolean putImpl(String paramString, int paramInt, Scriptable paramScriptable, Object paramObject)
  {
    if ((!this.isExtensible) && (Context.getContext().isStrictMode())) {
      throw ScriptRuntime.typeError0("msg.not.extensible");
    }
    Slot localSlot;
    if (this != paramScriptable)
    {
      localSlot = getSlot(paramString, paramInt, 1);
      paramString = localSlot;
      if (localSlot == null) {
        return false;
      }
    }
    else if (!this.isExtensible)
    {
      localSlot = getSlot(paramString, paramInt, 1);
      paramString = localSlot;
      if (localSlot == null) {
        return true;
      }
    }
    else
    {
      if (this.count < 0) {
        checkNotSealed(paramString, paramInt);
      }
      paramString = getSlot(paramString, paramInt, 2);
    }
    return paramString.setValue(paramObject, this, paramScriptable);
  }
  
  public static void putProperty(Scriptable paramScriptable, int paramInt, Object paramObject)
  {
    Scriptable localScriptable2 = getBase(paramScriptable, paramInt);
    Scriptable localScriptable1 = localScriptable2;
    if (localScriptable2 == null) {
      localScriptable1 = paramScriptable;
    }
    localScriptable1.put(paramInt, paramScriptable, paramObject);
  }
  
  public static void putProperty(Scriptable paramScriptable, String paramString, Object paramObject)
  {
    Scriptable localScriptable2 = getBase(paramScriptable, paramString);
    Scriptable localScriptable1 = localScriptable2;
    if (localScriptable2 == null) {
      localScriptable1 = paramScriptable;
    }
    localScriptable1.put(paramString, paramScriptable, paramObject);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    int j = paramObjectInputStream.readInt();
    int i;
    if (j != 0)
    {
      i = j;
      if ((j - 1 & j) != 0)
      {
        if (j > 1073741824) {
          throw new RuntimeException("Property table overflow");
        }
        i = 4;
        while (i < j) {
          i <<= 1;
        }
      }
      this.slots = new Slot[i];
      j = this.count;
      if (j >= 0) {
        break label166;
      }
      j ^= 0xFFFFFFFF;
    }
    label166:
    for (;;)
    {
      Slot localSlot = null;
      int k = 0;
      if (k != j)
      {
        this.lastAdded = ((Slot)paramObjectInputStream.readObject());
        if (k == 0) {
          this.firstAdded = this.lastAdded;
        }
        for (;;)
        {
          int m = getSlotIndex(i, this.lastAdded.indexOrHash);
          addKnownAbsentSlot(this.slots, this.lastAdded, m);
          localSlot = this.lastAdded;
          k += 1;
          break;
          localSlot.orderedNext = this.lastAdded;
        }
      }
      return;
    }
  }
  
  public static void redefineProperty(Scriptable paramScriptable, String paramString, boolean paramBoolean)
  {
    paramScriptable = getBase(paramScriptable, paramString);
    if (paramScriptable == null) {}
    do
    {
      return;
      if (((paramScriptable instanceof ConstProperties)) && (((ConstProperties)paramScriptable).isConst(paramString))) {
        throw ScriptRuntime.typeError1("msg.const.redecl", paramString);
      }
    } while (!paramBoolean);
    throw ScriptRuntime.typeError1("msg.var.redecl", paramString);
  }
  
  private void removeSlot(String paramString, int paramInt)
  {
    if (paramString != null) {}
    Slot[] arrayOfSlot;
    int i;
    Object localObject2;
    Object localObject1;
    for (;;)
    {
      try
      {
        paramInt = paramString.hashCode();
        arrayOfSlot = this.slots;
        if (this.count == 0) {
          break label208;
        }
        i = getSlotIndex(arrayOfSlot.length, paramInt);
        localObject2 = arrayOfSlot[i];
        localObject1 = localObject2;
        if ((localObject1 == null) || ((((Slot)localObject1).indexOrHash == paramInt) && ((((Slot)localObject1).name == paramString) || ((paramString != null) && (paramString.equals(((Slot)localObject1).name))))))
        {
          if (localObject1 == null) {
            break label208;
          }
          if ((((Slot)localObject1).getAttributes() & 0x4) == 0) {
            break;
          }
          if (!Context.getContext().isStrictMode()) {
            break label208;
          }
          throw ScriptRuntime.typeError1("msg.delete.property.with.configurable.false", paramString);
        }
      }
      finally {}
      Slot localSlot = ((Slot)localObject1).next;
      localObject2 = localObject1;
      localObject1 = localSlot;
    }
    this.count -= 1;
    if (localObject2 == localObject1)
    {
      arrayOfSlot[i] = ((Slot)localObject1).next;
      localObject2 = unwrapSlot((Slot)localObject1);
      if (localObject2 != this.firstAdded) {
        break label224;
      }
      paramString = null;
      this.firstAdded = ((Slot)localObject2).orderedNext;
    }
    for (;;)
    {
      if (localObject2 == this.lastAdded) {
        this.lastAdded = paramString;
      }
      ((Slot)localObject1).markDeleted();
      label208:
      return;
      ((Slot)localObject2).next = ((Slot)localObject1).next;
      break;
      label224:
      for (paramString = this.firstAdded; paramString.orderedNext != localObject2; paramString = paramString.orderedNext) {}
      paramString.orderedNext = ((Slot)localObject2).orderedNext;
    }
  }
  
  private void setGetterOrSetter(String paramString, int paramInt, Callable paramCallable, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramString != null) && (paramInt != 0)) {
      throw new IllegalArgumentException(paramString);
    }
    if (!paramBoolean2) {
      checkNotSealed(paramString, paramInt);
    }
    if (isExtensible()) {}
    for (Object localObject = (GetterSlot)getSlot(paramString, paramInt, 4); (!paramBoolean2) && ((((GetterSlot)localObject).getAttributes() & 0x1) != 0); localObject = (GetterSlot)localObject)
    {
      throw Context.reportRuntimeError1("msg.modify.readonly", paramString);
      localObject = unwrapSlot(getSlot(paramString, paramInt, 1));
      if (!(localObject instanceof GetterSlot)) {
        return;
      }
    }
    if (paramBoolean1) {
      ((GetterSlot)localObject).setter = paramCallable;
    }
    for (;;)
    {
      ((GetterSlot)localObject).value = Undefined.instance;
      return;
      ((GetterSlot)localObject).getter = paramCallable;
    }
  }
  
  private static Slot unwrapSlot(Slot paramSlot)
  {
    Slot localSlot = paramSlot;
    if ((paramSlot instanceof RelinkedSlot)) {
      localSlot = ((RelinkedSlot)paramSlot).slot;
    }
    return localSlot;
  }
  
  /* Error */
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 869	java/io/ObjectOutputStream:defaultWriteObject	()V
    //   6: aload_0
    //   7: getfield 485	org/mozilla/javascript/ScriptableObject:count	I
    //   10: istore_3
    //   11: iload_3
    //   12: istore_2
    //   13: iload_3
    //   14: ifge +7 -> 21
    //   17: iload_3
    //   18: iconst_m1
    //   19: ixor
    //   20: istore_2
    //   21: iload_2
    //   22: ifne +11 -> 33
    //   25: aload_1
    //   26: iconst_0
    //   27: invokevirtual 872	java/io/ObjectOutputStream:writeInt	(I)V
    //   30: aload_0
    //   31: monitorexit
    //   32: return
    //   33: aload_1
    //   34: aload_0
    //   35: getfield 483	org/mozilla/javascript/ScriptableObject:slots	[Lorg/mozilla/javascript/ScriptableObject$Slot;
    //   38: arraylength
    //   39: invokevirtual 872	java/io/ObjectOutputStream:writeInt	(I)V
    //   42: aload_0
    //   43: getfield 498	org/mozilla/javascript/ScriptableObject:firstAdded	Lorg/mozilla/javascript/ScriptableObject$Slot;
    //   46: astore 4
    //   48: aload 4
    //   50: ifnull +21 -> 71
    //   53: aload 4
    //   55: getfield 875	org/mozilla/javascript/ScriptableObject$Slot:wasDeleted	Z
    //   58: ifeq +13 -> 71
    //   61: aload 4
    //   63: getfield 496	org/mozilla/javascript/ScriptableObject$Slot:orderedNext	Lorg/mozilla/javascript/ScriptableObject$Slot;
    //   66: astore 4
    //   68: goto -20 -> 48
    //   71: aload_0
    //   72: aload 4
    //   74: putfield 498	org/mozilla/javascript/ScriptableObject:firstAdded	Lorg/mozilla/javascript/ScriptableObject$Slot;
    //   77: aload 4
    //   79: astore 5
    //   81: aload 5
    //   83: ifnull -53 -> 30
    //   86: aload_1
    //   87: aload 5
    //   89: invokevirtual 878	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   92: aload 5
    //   94: getfield 496	org/mozilla/javascript/ScriptableObject$Slot:orderedNext	Lorg/mozilla/javascript/ScriptableObject$Slot;
    //   97: astore 4
    //   99: aload 4
    //   101: ifnull +21 -> 122
    //   104: aload 4
    //   106: getfield 875	org/mozilla/javascript/ScriptableObject$Slot:wasDeleted	Z
    //   109: ifeq +13 -> 122
    //   112: aload 4
    //   114: getfield 496	org/mozilla/javascript/ScriptableObject$Slot:orderedNext	Lorg/mozilla/javascript/ScriptableObject$Slot;
    //   117: astore 4
    //   119: goto -20 -> 99
    //   122: aload 5
    //   124: aload 4
    //   126: putfield 496	org/mozilla/javascript/ScriptableObject$Slot:orderedNext	Lorg/mozilla/javascript/ScriptableObject$Slot;
    //   129: aload 4
    //   131: astore 5
    //   133: goto -52 -> 81
    //   136: astore_1
    //   137: aload_0
    //   138: monitorexit
    //   139: aload_1
    //   140: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	this	ScriptableObject
    //   0	141	1	paramObjectOutputStream	ObjectOutputStream
    //   12	10	2	i	int
    //   10	10	3	j	int
    //   46	84	4	localSlot1	Slot
    //   79	53	5	localSlot2	Slot
    // Exception table:
    //   from	to	target	type
    //   2	11	136	finally
    //   25	30	136	finally
    //   33	48	136	finally
    //   53	68	136	finally
    //   71	77	136	finally
    //   86	99	136	finally
    //   104	119	136	finally
    //   122	129	136	finally
  }
  
  void addLazilyInitializedValue(String paramString, int paramInt1, LazilyLoadedCtor paramLazilyLoadedCtor, int paramInt2)
  {
    if ((paramString != null) && (paramInt1 != 0)) {
      throw new IllegalArgumentException(paramString);
    }
    checkNotSealed(paramString, paramInt1);
    paramString = (GetterSlot)getSlot(paramString, paramInt1, 4);
    paramString.setAttributes(paramInt2);
    paramString.getter = null;
    paramString.setter = null;
    paramString.value = paramLazilyLoadedCtor;
  }
  
  protected int applyDescriptorToAttributeBitset(int paramInt, ScriptableObject paramScriptableObject)
  {
    Object localObject = getProperty(paramScriptableObject, "enumerable");
    int i;
    if (localObject != NOT_FOUND) {
      if (ScriptRuntime.toBoolean(localObject)) {
        i = paramInt & 0xFFFFFFFD;
      }
    }
    for (;;)
    {
      localObject = getProperty(paramScriptableObject, "writable");
      paramInt = i;
      if (localObject != NOT_FOUND) {
        if (!ScriptRuntime.toBoolean(localObject)) {
          break label100;
        }
      }
      label100:
      for (paramInt = i & 0xFFFFFFFE;; paramInt = i | 0x1)
      {
        paramScriptableObject = getProperty(paramScriptableObject, "configurable");
        i = paramInt;
        if (paramScriptableObject != NOT_FOUND)
        {
          if (!ScriptRuntime.toBoolean(paramScriptableObject)) {
            break label107;
          }
          i = paramInt & 0xFFFFFFFB;
        }
        return i;
        i = paramInt | 0x2;
        break;
      }
      label107:
      return paramInt | 0x4;
      i = paramInt;
    }
  }
  
  public final Object associateValue(Object paramObject1, Object paramObject2)
  {
    if (paramObject2 == null) {
      try
      {
        throw new IllegalArgumentException();
      }
      finally {}
    }
    Map localMap = this.associatedValues;
    Object localObject = localMap;
    if (localMap == null)
    {
      localObject = new HashMap();
      this.associatedValues = ((Map)localObject);
    }
    paramObject1 = Kit.initHash((Map)localObject, paramObject1, paramObject2);
    return paramObject1;
  }
  
  public boolean avoidObjectDetection()
  {
    return false;
  }
  
  protected void checkPropertyChange(String paramString, ScriptableObject paramScriptableObject1, ScriptableObject paramScriptableObject2)
  {
    boolean bool1;
    boolean bool2;
    if (paramScriptableObject1 == null)
    {
      if (!isExtensible()) {
        throw ScriptRuntime.typeError0("msg.not.extensible");
      }
    }
    else if (isFalse(paramScriptableObject1.get("configurable", paramScriptableObject1)))
    {
      if (isTrue(getProperty(paramScriptableObject2, "configurable"))) {
        throw ScriptRuntime.typeError1("msg.change.configurable.false.to.true", paramString);
      }
      if (isTrue(paramScriptableObject1.get("enumerable", paramScriptableObject1)) != isTrue(getProperty(paramScriptableObject2, "enumerable"))) {
        throw ScriptRuntime.typeError1("msg.change.enumerable.with.configurable.false", paramString);
      }
      bool1 = isDataDescriptor(paramScriptableObject2);
      bool2 = isAccessorDescriptor(paramScriptableObject2);
      if ((bool1) || (bool2)) {
        break label110;
      }
    }
    label110:
    do
    {
      do
      {
        do
        {
          return;
          if ((!bool1) || (!isDataDescriptor(paramScriptableObject1))) {
            break;
          }
        } while (!isFalse(paramScriptableObject1.get("writable", paramScriptableObject1)));
        if (isTrue(getProperty(paramScriptableObject2, "writable"))) {
          throw ScriptRuntime.typeError1("msg.change.writable.false.to.true.with.configurable.false", paramString);
        }
      } while (sameValue(getProperty(paramScriptableObject2, "value"), paramScriptableObject1.get("value", paramScriptableObject1)));
      throw ScriptRuntime.typeError1("msg.change.value.with.writable.false", paramString);
      if ((!bool2) || (!isAccessorDescriptor(paramScriptableObject1))) {
        break;
      }
      if (!sameValue(getProperty(paramScriptableObject2, "set"), paramScriptableObject1.get("set", paramScriptableObject1))) {
        throw ScriptRuntime.typeError1("msg.change.setter.with.configurable.false", paramString);
      }
    } while (sameValue(getProperty(paramScriptableObject2, "get"), paramScriptableObject1.get("get", paramScriptableObject1)));
    throw ScriptRuntime.typeError1("msg.change.getter.with.configurable.false", paramString);
    if (isDataDescriptor(paramScriptableObject1)) {
      throw ScriptRuntime.typeError1("msg.change.property.data.to.accessor.with.configurable.false", paramString);
    }
    throw ScriptRuntime.typeError1("msg.change.property.accessor.to.data.with.configurable.false", paramString);
  }
  
  protected void checkPropertyDefinition(ScriptableObject paramScriptableObject)
  {
    Object localObject = getProperty(paramScriptableObject, "get");
    if ((localObject != NOT_FOUND) && (localObject != Undefined.instance) && (!(localObject instanceof Callable))) {
      throw ScriptRuntime.notFunctionError(localObject);
    }
    localObject = getProperty(paramScriptableObject, "set");
    if ((localObject != NOT_FOUND) && (localObject != Undefined.instance) && (!(localObject instanceof Callable))) {
      throw ScriptRuntime.notFunctionError(localObject);
    }
    if ((isDataDescriptor(paramScriptableObject)) && (isAccessorDescriptor(paramScriptableObject))) {
      throw ScriptRuntime.typeError0("msg.both.data.and.accessor.desc");
    }
  }
  
  public void defineConst(String paramString, Scriptable paramScriptable)
  {
    if (putConstImpl(paramString, 0, paramScriptable, Undefined.instance, 8)) {}
    do
    {
      return;
      if (paramScriptable == this) {
        throw Kit.codeBug();
      }
    } while (!(paramScriptable instanceof ConstProperties));
    ((ConstProperties)paramScriptable).defineConst(paramString, paramScriptable);
  }
  
  public void defineFunctionProperties(String[] paramArrayOfString, Class<?> paramClass, int paramInt)
  {
    Method[] arrayOfMethod = FunctionObject.getMethodList(paramClass);
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      String str = paramArrayOfString[i];
      Method localMethod = FunctionObject.findSingleMethod(arrayOfMethod, str);
      if (localMethod == null) {
        throw Context.reportRuntimeError2("msg.method.not.found", str, paramClass.getName());
      }
      defineProperty(str, new FunctionObject(str, localMethod, this), paramInt);
      i += 1;
    }
  }
  
  public void defineOwnProperties(Context paramContext, ScriptableObject paramScriptableObject)
  {
    int j = 0;
    Object[] arrayOfObject = paramScriptableObject.getIds();
    ScriptableObject[] arrayOfScriptableObject = new ScriptableObject[arrayOfObject.length];
    int k = arrayOfObject.length;
    int i = 0;
    while (i < k)
    {
      ScriptableObject localScriptableObject = ensureScriptableObject(ScriptRuntime.getObjectElem(paramScriptableObject, arrayOfObject[i], paramContext));
      checkPropertyDefinition(localScriptableObject);
      arrayOfScriptableObject[i] = localScriptableObject;
      i += 1;
    }
    k = arrayOfObject.length;
    i = j;
    while (i < k)
    {
      defineOwnProperty(paramContext, arrayOfObject[i], arrayOfScriptableObject[i]);
      i += 1;
    }
  }
  
  public void defineOwnProperty(Context paramContext, Object paramObject, ScriptableObject paramScriptableObject)
  {
    checkPropertyDefinition(paramScriptableObject);
    defineOwnProperty(paramContext, paramObject, paramScriptableObject, true);
  }
  
  protected void defineOwnProperty(Context paramContext, Object paramObject, ScriptableObject paramScriptableObject, boolean paramBoolean)
  {
    Object localObject2 = getSlot(paramContext, paramObject, 1);
    int i;
    Object localObject1;
    label30:
    int j;
    if (localObject2 == null)
    {
      i = 1;
      if (paramBoolean)
      {
        if (localObject2 != null) {
          break label179;
        }
        localObject1 = null;
        checkPropertyChange(ScriptRuntime.toString(paramObject), (ScriptableObject)localObject1, paramScriptableObject);
      }
      paramBoolean = isAccessorDescriptor(paramScriptableObject);
      if (localObject2 != null) {
        break label197;
      }
      if (!paramBoolean) {
        break label191;
      }
      j = 4;
      label61:
      localObject1 = getSlot(paramContext, paramObject, j);
      j = applyDescriptorToAttributeBitset(7, paramScriptableObject);
    }
    for (;;)
    {
      localObject1 = unwrapSlot((Slot)localObject1);
      if (!paramBoolean) {
        break label216;
      }
      localObject2 = localObject1;
      if (!(localObject1 instanceof GetterSlot)) {
        localObject2 = getSlot(paramContext, paramObject, 4);
      }
      paramContext = (GetterSlot)localObject2;
      paramObject = getProperty(paramScriptableObject, "get");
      if (paramObject != NOT_FOUND) {
        paramContext.getter = paramObject;
      }
      paramObject = getProperty(paramScriptableObject, "set");
      if (paramObject != NOT_FOUND) {
        paramContext.setter = paramObject;
      }
      paramContext.value = Undefined.instance;
      paramContext.setAttributes(j);
      return;
      i = 0;
      break;
      label179:
      localObject1 = ((Slot)localObject2).getPropertyDescriptor(paramContext, this);
      break label30;
      label191:
      j = 2;
      break label61;
      label197:
      j = applyDescriptorToAttributeBitset(((Slot)localObject2).getAttributes(), paramScriptableObject);
      localObject1 = localObject2;
    }
    label216:
    localObject2 = localObject1;
    if ((localObject1 instanceof GetterSlot))
    {
      localObject2 = localObject1;
      if (isDataDescriptor(paramScriptableObject)) {
        localObject2 = getSlot(paramContext, paramObject, 5);
      }
    }
    paramContext = getProperty(paramScriptableObject, "value");
    if (paramContext != NOT_FOUND) {
      ((Slot)localObject2).value = paramContext;
    }
    for (;;)
    {
      ((Slot)localObject2).setAttributes(j);
      return;
      if (i != 0) {
        ((Slot)localObject2).value = Undefined.instance;
      }
    }
  }
  
  public void defineProperty(String paramString, Class<?> paramClass, int paramInt)
  {
    int i = paramString.length();
    if (i == 0) {
      throw new IllegalArgumentException();
    }
    Object localObject2 = new char[i + 3];
    paramString.getChars(0, i, (char[])localObject2, 3);
    localObject2[3] = Character.toUpperCase(localObject2[3]);
    localObject2[0] = 103;
    localObject2[1] = 101;
    localObject2[2] = 116;
    Object localObject1 = new String((char[])localObject2);
    localObject2[0] = 115;
    String str = new String((char[])localObject2);
    paramClass = FunctionObject.getMethodList(paramClass);
    localObject2 = FunctionObject.findSingleMethod(paramClass, (String)localObject1);
    localObject1 = FunctionObject.findSingleMethod(paramClass, str);
    if (localObject1 == null) {
      paramInt |= 0x1;
    }
    for (;;)
    {
      paramClass = (Class<?>)localObject1;
      if (localObject1 == null) {
        paramClass = null;
      }
      defineProperty(paramString, null, (Method)localObject2, paramClass, paramInt);
      return;
    }
  }
  
  public void defineProperty(String paramString, Object paramObject, int paramInt)
  {
    checkNotSealed(paramString, 0);
    put(paramString, this, paramObject);
    setAttributes(paramString, paramInt);
  }
  
  public void defineProperty(String paramString, Object paramObject, Method paramMethod1, Method paramMethod2, int paramInt)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    MemberBox localMemberBox;
    int i;
    label43:
    Object localObject1;
    if (paramMethod1 != null)
    {
      localMemberBox = new MemberBox(paramMethod1);
      if (!Modifier.isStatic(paramMethod1.getModifiers())) {
        if (paramObject != null)
        {
          i = 1;
          localMemberBox.delegateTo = paramObject;
          localObject1 = paramMethod1.getParameterTypes();
          if (localObject1.length != 0) {
            break label100;
          }
          if (i == 0) {
            break label368;
          }
          localObject1 = "msg.obj.getter.parms";
        }
      }
      for (;;)
      {
        if (localObject1 == null) {
          break label158;
        }
        throw Context.reportRuntimeError1((String)localObject1, paramMethod1.toString());
        i = 0;
        break;
        localMemberBox.delegateTo = Void.TYPE;
        i = 1;
        break label43;
        label100:
        if (localObject1.length == 1)
        {
          localObject1 = localObject1[0];
          if ((localObject1 != ScriptRuntime.ScriptableClass) && (localObject1 != ScriptRuntime.ScriptableObjectClass))
          {
            localObject1 = "msg.bad.getter.parms";
          }
          else
          {
            if (i != 0) {
              break label368;
            }
            localObject1 = "msg.bad.getter.parms";
          }
        }
        else
        {
          localObject1 = "msg.bad.getter.parms";
        }
      }
    }
    label158:
    for (paramMethod1 = localMemberBox;; paramMethod1 = null)
    {
      localObject1 = localObject3;
      if (paramMethod2 != null)
      {
        if (paramMethod2.getReturnType() != Void.TYPE) {
          throw Context.reportRuntimeError1("msg.setter.return", paramMethod2.toString());
        }
        localObject1 = new MemberBox(paramMethod2);
        if (!Modifier.isStatic(paramMethod2.getModifiers())) {
          if (paramObject != null)
          {
            i = 1;
            ((MemberBox)localObject1).delegateTo = paramObject;
            label228:
            paramObject = paramMethod2.getParameterTypes();
            if (paramObject.length != 1) {
              break label286;
            }
            paramObject = localObject2;
            if (i != 0) {
              paramObject = "msg.setter2.expected";
            }
          }
        }
        for (;;)
        {
          if (paramObject == null) {
            break label339;
          }
          throw Context.reportRuntimeError1((String)paramObject, paramMethod2.toString());
          i = 0;
          break;
          ((MemberBox)localObject1).delegateTo = Void.TYPE;
          i = 1;
          break label228;
          label286:
          if (paramObject.length == 2)
          {
            paramObject = paramObject[0];
            if ((paramObject != ScriptRuntime.ScriptableClass) && (paramObject != ScriptRuntime.ScriptableObjectClass))
            {
              paramObject = "msg.setter2.parms";
            }
            else
            {
              paramObject = localObject2;
              if (i == 0) {
                paramObject = "msg.setter1.parms";
              }
            }
          }
          else
          {
            paramObject = "msg.setter.parms";
          }
        }
      }
      label339:
      paramString = (GetterSlot)getSlot(paramString, 0, 4);
      paramString.setAttributes(paramInt);
      paramString.getter = paramMethod1;
      paramString.setter = localObject1;
      return;
      label368:
      localObject1 = null;
      break;
    }
  }
  
  public void delete(int paramInt)
  {
    checkNotSealed(null, paramInt);
    removeSlot(null, paramInt);
  }
  
  public void delete(String paramString)
  {
    checkNotSealed(paramString, 0);
    removeSlot(paramString, 0);
  }
  
  protected Object equivalentValues(Object paramObject)
  {
    if (this == paramObject) {
      return Boolean.TRUE;
    }
    return Scriptable.NOT_FOUND;
  }
  
  public Object get(int paramInt, Scriptable paramScriptable)
  {
    if (this.externalData != null)
    {
      if (paramInt < this.externalData.getArrayLength()) {
        return this.externalData.getArrayElement(paramInt);
      }
      return Scriptable.NOT_FOUND;
    }
    Slot localSlot = getSlot(null, paramInt, 1);
    if (localSlot == null) {
      return Scriptable.NOT_FOUND;
    }
    return localSlot.getValue(paramScriptable);
  }
  
  public Object get(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      paramObject = get((String)paramObject, this);
    }
    for (;;)
    {
      Object localObject;
      if ((paramObject == Scriptable.NOT_FOUND) || (paramObject == Undefined.instance)) {
        localObject = null;
      }
      do
      {
        return localObject;
        if (!(paramObject instanceof Number)) {
          break label77;
        }
        paramObject = get(((Number)paramObject).intValue(), this);
        break;
        localObject = paramObject;
      } while (!(paramObject instanceof Wrapper));
      return ((Wrapper)paramObject).unwrap();
      label77:
      paramObject = null;
    }
  }
  
  public Object get(String paramString, Scriptable paramScriptable)
  {
    paramString = getSlot(paramString, 0, 1);
    if (paramString == null) {
      return Scriptable.NOT_FOUND;
    }
    return paramString.getValue(paramScriptable);
  }
  
  public Object[] getAllIds()
  {
    return getIds(true);
  }
  
  public final Object getAssociatedValue(Object paramObject)
  {
    Map localMap = this.associatedValues;
    if (localMap == null) {
      return null;
    }
    return localMap.get(paramObject);
  }
  
  public int getAttributes(int paramInt)
  {
    return findAttributeSlot(null, paramInt, 1).getAttributes();
  }
  
  @Deprecated
  public final int getAttributes(int paramInt, Scriptable paramScriptable)
  {
    return getAttributes(paramInt);
  }
  
  public int getAttributes(String paramString)
  {
    return findAttributeSlot(paramString, 0, 1).getAttributes();
  }
  
  @Deprecated
  public final int getAttributes(String paramString, Scriptable paramScriptable)
  {
    return getAttributes(paramString);
  }
  
  public abstract String getClassName();
  
  public Object getDefaultValue(Class<?> paramClass)
  {
    return getDefaultValue(this, paramClass);
  }
  
  public ExternalArrayData getExternalArrayData()
  {
    return this.externalData;
  }
  
  public Object getExternalArrayLength()
  {
    if (this.externalData == null) {}
    for (int i = 0;; i = this.externalData.getArrayLength()) {
      return Integer.valueOf(i);
    }
  }
  
  public Object getGetterOrSetter(String paramString, int paramInt, boolean paramBoolean)
  {
    if ((paramString != null) && (paramInt != 0)) {
      throw new IllegalArgumentException(paramString);
    }
    paramString = unwrapSlot(getSlot(paramString, paramInt, 1));
    Object localObject;
    if (paramString == null)
    {
      localObject = null;
      return localObject;
    }
    if ((paramString instanceof GetterSlot))
    {
      paramString = (GetterSlot)paramString;
      if (paramBoolean) {}
      for (paramString = paramString.setter;; paramString = paramString.getter)
      {
        localObject = paramString;
        if (paramString != null) {
          break;
        }
        return Undefined.instance;
      }
    }
    return Undefined.instance;
  }
  
  public Object[] getIds()
  {
    return getIds(false);
  }
  
  Object[] getIds(boolean paramBoolean)
  {
    Slot[] arrayOfSlot = this.slots;
    if (this.externalData == null) {}
    Object localObject1;
    for (int i = 0; i == 0; i = this.externalData.getArrayLength())
    {
      localObject1 = ScriptRuntime.emptyArgs;
      if (arrayOfSlot != null) {
        break label81;
      }
      localObject2 = localObject1;
      return (Object[])localObject2;
    }
    Object localObject2 = new Object[i];
    int j = 0;
    for (;;)
    {
      localObject1 = localObject2;
      if (j >= i) {
        break;
      }
      localObject2[j] = Integer.valueOf(j);
      j += 1;
    }
    label81:
    localObject2 = this.firstAdded;
    label110:
    Object localObject3;
    while ((localObject2 != null) && (((Slot)localObject2).wasDeleted))
    {
      localObject2 = ((Slot)localObject2).orderedNext;
      continue;
      localObject3 = localObject1;
      localObject1 = localObject2;
    }
    for (;;)
    {
      if (localObject3 != null)
      {
        if ((!paramBoolean) && ((((Slot)localObject3).getAttributes() & 0x2) != 0)) {
          break label310;
        }
        if (j != i) {
          break label303;
        }
        Object[] arrayOfObject = new Object[arrayOfSlot.length + i];
        localObject2 = arrayOfObject;
        if (localObject1 != null)
        {
          System.arraycopy(localObject1, 0, arrayOfObject, 0, i);
          localObject2 = arrayOfObject;
        }
        label175:
        if (((Slot)localObject3).name != null)
        {
          localObject1 = ((Slot)localObject3).name;
          label190:
          localObject2[j] = localObject1;
          j += 1;
        }
      }
      for (;;)
      {
        for (localObject1 = ((Slot)localObject3).orderedNext; (localObject1 != null) && (((Slot)localObject1).wasDeleted); localObject1 = ((Slot)localObject1).orderedNext) {}
        break label110;
        localObject1 = Integer.valueOf(((Slot)localObject3).indexOrHash);
        break label190;
        localObject2 = localObject1;
        if (j == localObject1.length + i) {
          break;
        }
        localObject2 = new Object[j];
        System.arraycopy(localObject1, 0, localObject2, 0, j);
        localObject1 = Context.getCurrentContext();
        if ((localObject1 != null) && (((Context)localObject1).hasFeature(16))) {
          Arrays.sort((Object[])localObject2, KEY_COMPARATOR);
        }
        return (Object[])localObject2;
        label303:
        localObject2 = localObject1;
        break label175;
        label310:
        localObject2 = localObject1;
      }
      j = i;
      localObject3 = localObject2;
    }
  }
  
  protected ScriptableObject getOwnPropertyDescriptor(Context paramContext, Object paramObject)
  {
    Slot localSlot = getSlot(paramContext, paramObject, 1);
    if (localSlot == null) {
      return null;
    }
    paramObject = getParentScope();
    if (paramObject == null) {
      paramObject = this;
    }
    for (;;)
    {
      return localSlot.getPropertyDescriptor(paramContext, (Scriptable)paramObject);
    }
  }
  
  public Scriptable getParentScope()
  {
    return this.parentScopeObject;
  }
  
  public Scriptable getPrototype()
  {
    return this.prototypeObject;
  }
  
  protected Slot getSlot(Context paramContext, Object paramObject, int paramInt)
  {
    paramObject = ScriptRuntime.toStringIdOrIndex(paramContext, paramObject);
    if (paramObject == null) {
      return getSlot(null, ScriptRuntime.lastIndexResult(paramContext), paramInt);
    }
    return getSlot((String)paramObject, 0, paramInt);
  }
  
  public String getTypeOf()
  {
    if (avoidObjectDetection()) {
      return "undefined";
    }
    return "object";
  }
  
  public boolean has(int paramInt, Scriptable paramScriptable)
  {
    if (this.externalData != null) {
      if (paramInt >= this.externalData.getArrayLength()) {}
    }
    while (getSlot(null, paramInt, 1) != null)
    {
      return true;
      return false;
    }
    return false;
  }
  
  public boolean has(String paramString, Scriptable paramScriptable)
  {
    return getSlot(paramString, 0, 1) != null;
  }
  
  public boolean hasInstance(Scriptable paramScriptable)
  {
    return ScriptRuntime.jsDelegatesTo(paramScriptable, this);
  }
  
  protected boolean isAccessorDescriptor(ScriptableObject paramScriptableObject)
  {
    return (hasProperty(paramScriptableObject, "get")) || (hasProperty(paramScriptableObject, "set"));
  }
  
  public boolean isConst(String paramString)
  {
    boolean bool = true;
    paramString = getSlot(paramString, 0, 1);
    if (paramString == null) {
      return false;
    }
    if ((paramString.getAttributes() & 0x5) == 5) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  protected boolean isDataDescriptor(ScriptableObject paramScriptableObject)
  {
    return (hasProperty(paramScriptableObject, "value")) || (hasProperty(paramScriptableObject, "writable"));
  }
  
  public boolean isEmpty()
  {
    return (this.count == 0) || (this.count == -1);
  }
  
  public boolean isExtensible()
  {
    return this.isExtensible;
  }
  
  protected boolean isGenericDescriptor(ScriptableObject paramScriptableObject)
  {
    return (!isDataDescriptor(paramScriptableObject)) && (!isAccessorDescriptor(paramScriptableObject));
  }
  
  protected boolean isGetterOrSetter(String paramString, int paramInt, boolean paramBoolean)
  {
    paramString = unwrapSlot(getSlot(paramString, paramInt, 1));
    if ((paramString instanceof GetterSlot))
    {
      if ((paramBoolean) && (((GetterSlot)paramString).setter != null)) {
        return true;
      }
      if ((!paramBoolean) && (((GetterSlot)paramString).getter != null)) {
        return true;
      }
    }
    return false;
  }
  
  public final boolean isSealed()
  {
    return this.count < 0;
  }
  
  public void preventExtensions()
  {
    this.isExtensible = false;
  }
  
  public void put(int paramInt, Scriptable paramScriptable, Object paramObject)
  {
    if (this.externalData != null) {
      if (paramInt < this.externalData.getArrayLength()) {
        this.externalData.setArrayElement(paramInt, paramObject);
      }
    }
    while (putImpl(null, paramInt, paramScriptable, paramObject))
    {
      return;
      throw new JavaScriptException(ScriptRuntime.newNativeError(Context.getCurrentContext(), this, TopLevel.NativeErrors.RangeError, new Object[] { "External array index out of bounds " }), null, 0);
    }
    if (paramScriptable == this) {
      throw Kit.codeBug();
    }
    paramScriptable.put(paramInt, paramScriptable, paramObject);
  }
  
  public void put(String paramString, Scriptable paramScriptable, Object paramObject)
  {
    if (putImpl(paramString, 0, paramScriptable, paramObject)) {
      return;
    }
    if (paramScriptable == this) {
      throw Kit.codeBug();
    }
    paramScriptable.put(paramString, paramScriptable, paramObject);
  }
  
  public void putConst(String paramString, Scriptable paramScriptable, Object paramObject)
  {
    if (putConstImpl(paramString, 0, paramScriptable, paramObject, 1)) {
      return;
    }
    if (paramScriptable == this) {
      throw Kit.codeBug();
    }
    if ((paramScriptable instanceof ConstProperties))
    {
      ((ConstProperties)paramScriptable).putConst(paramString, paramScriptable, paramObject);
      return;
    }
    paramScriptable.put(paramString, paramScriptable, paramObject);
  }
  
  protected boolean sameValue(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == NOT_FOUND) {
      return true;
    }
    if (paramObject2 == NOT_FOUND) {
      paramObject2 = Undefined.instance;
    }
    for (;;)
    {
      if (((paramObject2 instanceof Number)) && ((paramObject1 instanceof Number)))
      {
        double d1 = ((Number)paramObject2).doubleValue();
        double d2 = ((Number)paramObject1).doubleValue();
        if ((Double.isNaN(d1)) && (Double.isNaN(d2))) {
          return true;
        }
        if ((d1 == 0.0D) && (Double.doubleToLongBits(d1) != Double.doubleToLongBits(d2))) {
          return false;
        }
      }
      return ScriptRuntime.shallowEq(paramObject2, paramObject1);
    }
  }
  
  /* Error */
  public void sealObject()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 485	org/mozilla/javascript/ScriptableObject:count	I
    //   6: iflt +75 -> 81
    //   9: aload_0
    //   10: getfield 498	org/mozilla/javascript/ScriptableObject:firstAdded	Lorg/mozilla/javascript/ScriptableObject$Slot;
    //   13: astore_1
    //   14: aload_1
    //   15: ifnull +56 -> 71
    //   18: aload_1
    //   19: getfield 508	org/mozilla/javascript/ScriptableObject$Slot:value	Ljava/lang/Object;
    //   22: astore_2
    //   23: aload_2
    //   24: instanceof 1157
    //   27: ifeq +20 -> 47
    //   30: aload_2
    //   31: checkcast 1157	org/mozilla/javascript/LazilyLoadedCtor
    //   34: astore_2
    //   35: aload_2
    //   36: invokevirtual 1159	org/mozilla/javascript/LazilyLoadedCtor:init	()V
    //   39: aload_1
    //   40: aload_2
    //   41: invokevirtual 1161	org/mozilla/javascript/LazilyLoadedCtor:getValue	()Ljava/lang/Object;
    //   44: putfield 508	org/mozilla/javascript/ScriptableObject$Slot:value	Ljava/lang/Object;
    //   47: aload_1
    //   48: getfield 496	org/mozilla/javascript/ScriptableObject$Slot:orderedNext	Lorg/mozilla/javascript/ScriptableObject$Slot;
    //   51: astore_1
    //   52: goto -38 -> 14
    //   55: astore_3
    //   56: aload_1
    //   57: aload_2
    //   58: invokevirtual 1161	org/mozilla/javascript/LazilyLoadedCtor:getValue	()Ljava/lang/Object;
    //   61: putfield 508	org/mozilla/javascript/ScriptableObject$Slot:value	Ljava/lang/Object;
    //   64: aload_3
    //   65: athrow
    //   66: astore_1
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_1
    //   70: athrow
    //   71: aload_0
    //   72: aload_0
    //   73: getfield 485	org/mozilla/javascript/ScriptableObject:count	I
    //   76: iconst_m1
    //   77: ixor
    //   78: putfield 485	org/mozilla/javascript/ScriptableObject:count	I
    //   81: aload_0
    //   82: monitorexit
    //   83: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	ScriptableObject
    //   13	44	1	localSlot	Slot
    //   66	4	1	localObject1	Object
    //   22	36	2	localObject2	Object
    //   55	10	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   35	39	55	finally
    //   2	14	66	finally
    //   18	35	66	finally
    //   39	47	66	finally
    //   47	52	66	finally
    //   56	66	66	finally
    //   71	81	66	finally
  }
  
  public void setAttributes(int paramInt1, int paramInt2)
  {
    checkNotSealed(null, paramInt1);
    findAttributeSlot(null, paramInt1, 2).setAttributes(paramInt2);
  }
  
  @Deprecated
  public void setAttributes(int paramInt1, Scriptable paramScriptable, int paramInt2)
  {
    setAttributes(paramInt1, paramInt2);
  }
  
  public void setAttributes(String paramString, int paramInt)
  {
    checkNotSealed(paramString, 0);
    findAttributeSlot(paramString, 0, 2).setAttributes(paramInt);
  }
  
  @Deprecated
  public final void setAttributes(String paramString, Scriptable paramScriptable, int paramInt)
  {
    setAttributes(paramString, paramInt);
  }
  
  public void setExternalArrayData(ExternalArrayData paramExternalArrayData)
  {
    this.externalData = paramExternalArrayData;
    if (paramExternalArrayData == null)
    {
      delete("length");
      return;
    }
    defineProperty("length", null, GET_ARRAY_LENGTH, null, 3);
  }
  
  public void setGetterOrSetter(String paramString, int paramInt, Callable paramCallable, boolean paramBoolean)
  {
    setGetterOrSetter(paramString, paramInt, paramCallable, paramBoolean, false);
  }
  
  public void setParentScope(Scriptable paramScriptable)
  {
    this.parentScopeObject = paramScriptable;
  }
  
  public void setPrototype(Scriptable paramScriptable)
  {
    this.prototypeObject = paramScriptable;
  }
  
  public int size()
  {
    if (this.count < 0) {
      return this.count ^ 0xFFFFFFFF;
    }
    return this.count;
  }
  
  private static final class GetterSlot
    extends ScriptableObject.Slot
  {
    static final long serialVersionUID = -4900574849788797588L;
    Object getter;
    Object setter;
    
    GetterSlot(String paramString, int paramInt1, int paramInt2)
    {
      super(paramInt1, paramInt2);
    }
    
    ScriptableObject getPropertyDescriptor(Context paramContext, Scriptable paramScriptable)
    {
      boolean bool2 = true;
      int i = getAttributes();
      paramContext = new NativeObject();
      ScriptRuntime.setBuiltinProtoAndParent(paramContext, paramScriptable, TopLevel.Builtins.Object);
      if ((i & 0x2) == 0)
      {
        bool1 = true;
        paramContext.defineProperty("enumerable", Boolean.valueOf(bool1), 0);
        if ((i & 0x4) != 0) {
          break label146;
        }
        bool1 = true;
        label54:
        paramContext.defineProperty("configurable", Boolean.valueOf(bool1), 0);
        if ((this.getter == null) && (this.setter == null)) {
          if ((i & 0x1) != 0) {
            break label152;
          }
        }
      }
      label146:
      label152:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        paramContext.defineProperty("writable", Boolean.valueOf(bool1), 0);
        if (this.getter != null) {
          paramContext.defineProperty("get", this.getter, 0);
        }
        if (this.setter != null) {
          paramContext.defineProperty("set", this.setter, 0);
        }
        return paramContext;
        bool1 = false;
        break;
        bool1 = false;
        break label54;
      }
    }
    
    Object getValue(Scriptable paramScriptable)
    {
      MemberBox localMemberBox;
      Object[] arrayOfObject;
      Object localObject1;
      if (this.getter != null) {
        if ((this.getter instanceof MemberBox))
        {
          localMemberBox = (MemberBox)this.getter;
          if (localMemberBox.delegateTo == null)
          {
            arrayOfObject = ScriptRuntime.emptyArgs;
            localObject1 = paramScriptable;
            paramScriptable = arrayOfObject;
            paramScriptable = localMemberBox.invoke(localObject1, paramScriptable);
          }
        }
      }
      do
      {
        return paramScriptable;
        localObject1 = localMemberBox.delegateTo;
        arrayOfObject = new Object[1];
        arrayOfObject[0] = paramScriptable;
        paramScriptable = arrayOfObject;
        break;
        if ((this.getter instanceof Function))
        {
          localObject1 = (Function)this.getter;
          return ((Function)localObject1).call(Context.getContext(), ((Function)localObject1).getParentScope(), paramScriptable, ScriptRuntime.emptyArgs);
        }
        localObject1 = this.value;
        paramScriptable = (Scriptable)localObject1;
      } while (!(localObject1 instanceof LazilyLoadedCtor));
      paramScriptable = (LazilyLoadedCtor)localObject1;
      try
      {
        paramScriptable.init();
        paramScriptable = paramScriptable.getValue();
        this.value = paramScriptable;
        return paramScriptable;
      }
      finally
      {
        this.value = paramScriptable.getValue();
      }
    }
    
    void markDeleted()
    {
      super.markDeleted();
      this.getter = null;
      this.setter = null;
    }
    
    boolean setValue(Object paramObject, Scriptable paramScriptable1, Scriptable paramScriptable2)
    {
      if (this.setter == null)
      {
        if (this.getter != null)
        {
          paramObject = Context.getContext();
          if ((((Context)paramObject).isStrictMode()) || (((Context)paramObject).hasFeature(11))) {
            throw ScriptRuntime.typeError1("msg.set.prop.no.setter", this.name);
          }
          return true;
        }
      }
      else
      {
        paramScriptable1 = Context.getContext();
        Object localObject1;
        Object localObject2;
        if ((this.setter instanceof MemberBox))
        {
          localObject1 = (MemberBox)this.setter;
          localObject2 = ((MemberBox)localObject1).argTypes;
          localObject2 = FunctionObject.convertArg(paramScriptable1, paramScriptable2, paramObject, FunctionObject.getTypeTag(localObject2[(localObject2.length - 1)]));
          if (((MemberBox)localObject1).delegateTo == null)
          {
            paramObject = new Object[1];
            paramObject[0] = localObject2;
            ((MemberBox)localObject1).invoke(paramScriptable2, (Object[])paramObject);
          }
        }
        for (;;)
        {
          return true;
          paramScriptable1 = ((MemberBox)localObject1).delegateTo;
          paramObject = new Object[2];
          paramObject[0] = paramScriptable2;
          paramObject[1] = localObject2;
          paramScriptable2 = paramScriptable1;
          break;
          if ((this.setter instanceof Function))
          {
            localObject1 = (Function)this.setter;
            ((Function)localObject1).call(paramScriptable1, ((Function)localObject1).getParentScope(), paramScriptable2, new Object[] { paramObject });
          }
        }
      }
      return super.setValue(paramObject, paramScriptable1, paramScriptable2);
    }
  }
  
  private static final class KeyComparator
    implements Comparator<Object>
  {
    public int compare(Object paramObject1, Object paramObject2)
    {
      if ((paramObject1 instanceof Integer))
      {
        int i;
        int j;
        if ((paramObject2 instanceof Integer))
        {
          i = ((Integer)paramObject1).intValue();
          j = ((Integer)paramObject2).intValue();
          if (i >= j) {}
        }
        else
        {
          return -1;
        }
        if (i > j) {
          return 1;
        }
        return 0;
      }
      if ((paramObject2 instanceof Integer)) {
        return 1;
      }
      return 0;
    }
  }
  
  private static class RelinkedSlot
    extends ScriptableObject.Slot
  {
    final ScriptableObject.Slot slot;
    
    RelinkedSlot(ScriptableObject.Slot paramSlot)
    {
      super(paramSlot.indexOrHash, ScriptableObject.Slot.access$000(paramSlot));
      this.slot = ScriptableObject.unwrapSlot(paramSlot);
    }
    
    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      paramObjectOutputStream.writeObject(this.slot);
    }
    
    int getAttributes()
    {
      return this.slot.getAttributes();
    }
    
    ScriptableObject getPropertyDescriptor(Context paramContext, Scriptable paramScriptable)
    {
      return this.slot.getPropertyDescriptor(paramContext, paramScriptable);
    }
    
    Object getValue(Scriptable paramScriptable)
    {
      return this.slot.getValue(paramScriptable);
    }
    
    void markDeleted()
    {
      super.markDeleted();
      this.slot.markDeleted();
    }
    
    void setAttributes(int paramInt)
    {
      this.slot.setAttributes(paramInt);
    }
    
    boolean setValue(Object paramObject, Scriptable paramScriptable1, Scriptable paramScriptable2)
    {
      return this.slot.setValue(paramObject, paramScriptable1, paramScriptable2);
    }
  }
  
  private static class Slot
    implements Serializable
  {
    private static final long serialVersionUID = -6090581677123995491L;
    private volatile short attributes;
    int indexOrHash;
    String name;
    transient Slot next;
    volatile transient Slot orderedNext;
    volatile Object value;
    volatile transient boolean wasDeleted;
    
    Slot(String paramString, int paramInt1, int paramInt2)
    {
      this.name = paramString;
      this.indexOrHash = paramInt1;
      this.attributes = ((short)paramInt2);
    }
    
    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException, ClassNotFoundException
    {
      paramObjectInputStream.defaultReadObject();
      if (this.name != null) {
        this.indexOrHash = this.name.hashCode();
      }
    }
    
    int getAttributes()
    {
      return this.attributes;
    }
    
    ScriptableObject getPropertyDescriptor(Context paramContext, Scriptable paramScriptable)
    {
      return ScriptableObject.buildDataDescriptor(paramScriptable, this.value, this.attributes);
    }
    
    Object getValue(Scriptable paramScriptable)
    {
      return this.value;
    }
    
    void markDeleted()
    {
      this.wasDeleted = true;
      this.value = null;
      this.name = null;
    }
    
    void setAttributes(int paramInt)
    {
      try
      {
        ScriptableObject.checkValidAttributes(paramInt);
        this.attributes = ((short)paramInt);
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    boolean setValue(Object paramObject, Scriptable paramScriptable1, Scriptable paramScriptable2)
    {
      if ((this.attributes & 0x1) != 0)
      {
        if (Context.getContext().isStrictMode()) {
          throw ScriptRuntime.typeError1("msg.modify.readonly", this.name);
        }
      }
      else
      {
        if (paramScriptable1 != paramScriptable2) {
          break label40;
        }
        this.value = paramObject;
      }
      return true;
      label40:
      return false;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ScriptableObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */