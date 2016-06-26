package org.mozilla.javascript;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.mozilla.javascript.regexp.NativeRegExp;

public class NativeArray
  extends IdScriptableObject
  implements List
{
  private static final Object ARRAY_TAG = "Array";
  private static final int ConstructorId_concat = -13;
  private static final int ConstructorId_every = -17;
  private static final int ConstructorId_filter = -18;
  private static final int ConstructorId_find = -22;
  private static final int ConstructorId_findIndex = -23;
  private static final int ConstructorId_forEach = -19;
  private static final int ConstructorId_indexOf = -15;
  private static final int ConstructorId_isArray = -26;
  private static final int ConstructorId_join = -5;
  private static final int ConstructorId_lastIndexOf = -16;
  private static final int ConstructorId_map = -20;
  private static final int ConstructorId_pop = -9;
  private static final int ConstructorId_push = -8;
  private static final int ConstructorId_reduce = -24;
  private static final int ConstructorId_reduceRight = -25;
  private static final int ConstructorId_reverse = -6;
  private static final int ConstructorId_shift = -10;
  private static final int ConstructorId_slice = -14;
  private static final int ConstructorId_some = -21;
  private static final int ConstructorId_sort = -7;
  private static final int ConstructorId_splice = -12;
  private static final int ConstructorId_unshift = -11;
  private static final int DEFAULT_INITIAL_CAPACITY = 10;
  private static final double GROW_FACTOR = 1.5D;
  private static final int Id_concat = 13;
  private static final int Id_constructor = 1;
  private static final int Id_every = 17;
  private static final int Id_filter = 18;
  private static final int Id_find = 22;
  private static final int Id_findIndex = 23;
  private static final int Id_forEach = 19;
  private static final int Id_indexOf = 15;
  private static final int Id_iterator = 26;
  private static final int Id_join = 5;
  private static final int Id_lastIndexOf = 16;
  private static final int Id_length = 1;
  private static final int Id_map = 20;
  private static final int Id_pop = 9;
  private static final int Id_push = 8;
  private static final int Id_reduce = 24;
  private static final int Id_reduceRight = 25;
  private static final int Id_reverse = 6;
  private static final int Id_shift = 10;
  private static final int Id_slice = 14;
  private static final int Id_some = 21;
  private static final int Id_sort = 7;
  private static final int Id_splice = 12;
  private static final int Id_toLocaleString = 3;
  private static final int Id_toSource = 4;
  private static final int Id_toString = 2;
  private static final int Id_unshift = 11;
  private static final int MAX_INSTANCE_ID = 1;
  private static final int MAX_PRE_GROW_SIZE = 1431655764;
  private static final int MAX_PROTOTYPE_ID = 26;
  private static final Integer NEGATIVE_ONE = Integer.valueOf(-1);
  private static int maximumInitialCapacity = 10000;
  static final long serialVersionUID = 7331366857676127338L;
  private Object[] dense;
  private boolean denseOnly;
  private long length;
  private int lengthAttr = 6;
  
  public NativeArray(long paramLong)
  {
    if (paramLong <= maximumInitialCapacity) {}
    for (boolean bool = true;; bool = false)
    {
      this.denseOnly = bool;
      if (this.denseOnly)
      {
        int j = (int)paramLong;
        int i = j;
        if (j < 10) {
          i = 10;
        }
        this.dense = new Object[i];
        Arrays.fill(this.dense, Scriptable.NOT_FOUND);
      }
      this.length = paramLong;
      return;
    }
  }
  
  public NativeArray(Object[] paramArrayOfObject)
  {
    this.denseOnly = true;
    this.dense = paramArrayOfObject;
    this.length = paramArrayOfObject.length;
  }
  
  private ScriptableObject defaultIndexPropertyDescriptor(Object paramObject)
  {
    Object localObject = getParentScope();
    if (localObject == null) {
      localObject = this;
    }
    for (;;)
    {
      NativeObject localNativeObject = new NativeObject();
      ScriptRuntime.setBuiltinProtoAndParent(localNativeObject, (Scriptable)localObject, TopLevel.Builtins.Object);
      localNativeObject.defineProperty("value", paramObject, 0);
      localNativeObject.defineProperty("writable", Boolean.valueOf(true), 0);
      localNativeObject.defineProperty("enumerable", Boolean.valueOf(true), 0);
      localNativeObject.defineProperty("configurable", Boolean.valueOf(true), 0);
      return localNativeObject;
    }
  }
  
  private static void defineElem(Context paramContext, Scriptable paramScriptable, long paramLong, Object paramObject)
  {
    if (paramLong > 2147483647L)
    {
      paramScriptable.put(Long.toString(paramLong), paramScriptable, paramObject);
      return;
    }
    paramScriptable.put((int)paramLong, paramScriptable, paramObject);
  }
  
  private static void deleteElem(Scriptable paramScriptable, long paramLong)
  {
    int i = (int)paramLong;
    if (i == paramLong)
    {
      paramScriptable.delete(i);
      return;
    }
    paramScriptable.delete(Long.toString(paramLong));
  }
  
  private boolean ensureCapacity(int paramInt)
  {
    if (paramInt > this.dense.length)
    {
      if (paramInt > 1431655764)
      {
        this.denseOnly = false;
        return false;
      }
      Object[] arrayOfObject = new Object[Math.max(paramInt, (int)(this.dense.length * 1.5D))];
      System.arraycopy(this.dense, 0, arrayOfObject, 0, this.dense.length);
      Arrays.fill(arrayOfObject, this.dense.length, arrayOfObject.length, Scriptable.NOT_FOUND);
      this.dense = arrayOfObject;
    }
    return true;
  }
  
  private static Object getElem(Context paramContext, Scriptable paramScriptable, long paramLong)
  {
    paramContext = getRawElem(paramScriptable, paramLong);
    if (paramContext != Scriptable.NOT_FOUND) {
      return paramContext;
    }
    return Undefined.instance;
  }
  
  static long getLengthProperty(Context paramContext, Scriptable paramScriptable)
  {
    if ((paramScriptable instanceof NativeString)) {
      return ((NativeString)paramScriptable).getLength();
    }
    if ((paramScriptable instanceof NativeArray)) {
      return ((NativeArray)paramScriptable).getLength();
    }
    paramContext = ScriptableObject.getProperty(paramScriptable, "length");
    if (paramContext == Scriptable.NOT_FOUND) {
      return 0L;
    }
    return ScriptRuntime.toUint32(paramContext);
  }
  
  static int getMaximumInitialCapacity()
  {
    return maximumInitialCapacity;
  }
  
  private static Object getRawElem(Scriptable paramScriptable, long paramLong)
  {
    if (paramLong > 2147483647L) {
      return ScriptableObject.getProperty(paramScriptable, Long.toString(paramLong));
    }
    return ScriptableObject.getProperty(paramScriptable, (int)paramLong);
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    new NativeArray(0L).exportAsJSClass(26, paramScriptable, paramBoolean);
  }
  
  private static Object iterativeMethod(Context paramContext, IdFunctionObject paramIdFunctionObject, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    int j = paramIdFunctionObject.methodId();
    Scriptable localScriptable1;
    if (22 != j)
    {
      localScriptable1 = paramScriptable2;
      if (23 != j) {}
    }
    else
    {
      localScriptable1 = ScriptRuntimeES6.requireObjectCoercible(paramContext, paramScriptable2, paramIdFunctionObject);
    }
    long l4 = getLengthProperty(paramContext, localScriptable1);
    if (paramArrayOfObject.length > 0) {}
    for (paramIdFunctionObject = paramArrayOfObject[0]; (paramIdFunctionObject == null) || (!(paramIdFunctionObject instanceof Function)); paramIdFunctionObject = Undefined.instance) {
      throw ScriptRuntime.notFunctionError(paramIdFunctionObject);
    }
    if ((paramContext.getLanguageVersion() >= 200) && ((paramIdFunctionObject instanceof NativeRegExp))) {
      throw ScriptRuntime.notFunctionError(paramIdFunctionObject);
    }
    Function localFunction = (Function)paramIdFunctionObject;
    Scriptable localScriptable2 = ScriptableObject.getTopLevelScope(localFunction);
    int i;
    label163:
    long l1;
    long l2;
    label177:
    Object[] arrayOfObject;
    if ((paramArrayOfObject.length < 2) || (paramArrayOfObject[1] == null) || (paramArrayOfObject[1] == Undefined.instance))
    {
      paramIdFunctionObject = localScriptable2;
      paramScriptable2 = null;
      if ((j == 18) || (j == 20))
      {
        if (j != 20) {
          break label330;
        }
        i = (int)l4;
        paramScriptable2 = paramContext.newArray(paramScriptable1, i);
      }
      l1 = 0L;
      l2 = 0L;
      if (l2 >= l4) {
        break label432;
      }
      arrayOfObject = new Object[3];
      paramArrayOfObject = getRawElem(localScriptable1, l2);
      paramScriptable1 = paramArrayOfObject;
      if (paramArrayOfObject == Scriptable.NOT_FOUND)
      {
        if ((j == 22) || (j == 23)) {
          paramScriptable1 = Undefined.instance;
        }
      }
      else
      {
        arrayOfObject[0] = paramScriptable1;
        arrayOfObject[1] = Long.valueOf(l2);
        arrayOfObject[2] = localScriptable1;
        paramArrayOfObject = localFunction.call(paramContext, localScriptable2, paramIdFunctionObject, arrayOfObject);
        switch (j)
        {
        }
      }
    }
    label330:
    do
    {
      do
      {
        do
        {
          for (;;)
          {
            l2 = 1L + l2;
            break label177;
            paramIdFunctionObject = ScriptRuntime.toObject(paramContext, paramScriptable1, paramArrayOfObject[1]);
            break;
            i = 0;
            break label163;
            if (!ScriptRuntime.toBoolean(paramArrayOfObject))
            {
              return Boolean.FALSE;
              if (ScriptRuntime.toBoolean(paramArrayOfObject))
              {
                long l3 = 1L + l1;
                defineElem(paramContext, paramScriptable2, l1, arrayOfObject[0]);
                l1 = l3;
                continue;
                continue;
                defineElem(paramContext, paramScriptable2, l2, paramArrayOfObject);
              }
            }
          }
        } while (!ScriptRuntime.toBoolean(paramArrayOfObject));
        return Boolean.TRUE;
      } while (!ScriptRuntime.toBoolean(paramArrayOfObject));
      return paramScriptable1;
    } while (!ScriptRuntime.toBoolean(paramArrayOfObject));
    return ScriptRuntime.wrapNumber(l2);
    switch (j)
    {
    case 19: 
    case 22: 
    default: 
      return Undefined.instance;
    case 17: 
      return Boolean.TRUE;
    case 18: 
    case 20: 
      return paramScriptable2;
    case 21: 
      label432:
      return Boolean.FALSE;
    }
    return ScriptRuntime.wrapNumber(-1.0D);
  }
  
  private static Object jsConstructor(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length == 0) {
      return new NativeArray(0L);
    }
    if (paramContext.getLanguageVersion() == 120) {
      return new NativeArray(paramArrayOfObject);
    }
    paramContext = paramArrayOfObject[0];
    if ((paramArrayOfObject.length > 1) || (!(paramContext instanceof Number))) {
      return new NativeArray(paramArrayOfObject);
    }
    long l = ScriptRuntime.toUint32(paramContext);
    if (l != ((Number)paramContext).doubleValue()) {
      throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage0("msg.arraylength.bad"));
    }
    return new NativeArray(l);
  }
  
  private static Scriptable js_concat(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    paramScriptable1 = paramContext.newArray(getTopLevelScope(paramScriptable1), 0);
    Object localObject;
    if (((paramScriptable2 instanceof NativeArray)) && ((paramScriptable1 instanceof NativeArray)))
    {
      NativeArray localNativeArray1 = (NativeArray)paramScriptable2;
      localObject = (NativeArray)paramScriptable1;
      if ((localNativeArray1.denseOnly) && (((NativeArray)localObject).denseOnly))
      {
        int j = (int)localNativeArray1.length;
        boolean bool = true;
        int k = 0;
        if ((k < paramArrayOfObject.length) && (bool))
        {
          NativeArray localNativeArray2;
          if ((paramArrayOfObject[k] instanceof NativeArray))
          {
            localNativeArray2 = (NativeArray)paramArrayOfObject[k];
            bool = localNativeArray2.denseOnly;
          }
          for (i = (int)(j + localNativeArray2.length);; i = j + 1)
          {
            k += 1;
            j = i;
            break;
          }
        }
        if ((bool) && (((NativeArray)localObject).ensureCapacity(j)))
        {
          System.arraycopy(localNativeArray1.dense, 0, ((NativeArray)localObject).dense, 0, (int)localNativeArray1.length);
          i = (int)localNativeArray1.length;
          k = 0;
          if ((k < paramArrayOfObject.length) && (bool))
          {
            if ((paramArrayOfObject[k] instanceof NativeArray))
            {
              paramContext = (NativeArray)paramArrayOfObject[k];
              System.arraycopy(paramContext.dense, 0, ((NativeArray)localObject).dense, i, (int)paramContext.length);
              i += (int)paramContext.length;
            }
            for (;;)
            {
              k += 1;
              break;
              ((NativeArray)localObject).dense[i] = paramArrayOfObject[k];
              i += 1;
            }
          }
          ((NativeArray)localObject).length = j;
          return paramScriptable1;
        }
      }
    }
    long l3;
    if (js_isArray(paramScriptable2))
    {
      l3 = getLengthProperty(paramContext, paramScriptable2);
      for (l1 = 0L;; l1 += 1L)
      {
        l2 = l1;
        if (l1 >= l3) {
          break;
        }
        localObject = getRawElem(paramScriptable2, l1);
        if (localObject != NOT_FOUND) {
          defineElem(paramContext, paramScriptable1, l1, localObject);
        }
      }
    }
    long l2 = 1L + 0L;
    defineElem(paramContext, paramScriptable1, 0L, paramScriptable2);
    long l1 = l2;
    int i = 0;
    while (i < paramArrayOfObject.length)
    {
      if (js_isArray(paramArrayOfObject[i]))
      {
        paramScriptable2 = (Scriptable)paramArrayOfObject[i];
        long l4 = getLengthProperty(paramContext, paramScriptable2);
        l3 = 0L;
        for (;;)
        {
          l2 = l1;
          if (l3 >= l4) {
            break;
          }
          localObject = getRawElem(paramScriptable2, l3);
          if (localObject != NOT_FOUND) {
            defineElem(paramContext, paramScriptable1, l1, localObject);
          }
          l3 += 1L;
          l1 += 1L;
        }
      }
      defineElem(paramContext, paramScriptable1, l1, paramArrayOfObject[i]);
      l2 = 1L + l1;
      i += 1;
      l1 = l2;
    }
    setLengthProperty(paramContext, paramScriptable1, l1);
    return paramScriptable1;
  }
  
  private static Object js_indexOf(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    long l2 = 0L;
    if (paramArrayOfObject.length > 0) {}
    long l4;
    int i;
    for (Object localObject = paramArrayOfObject[0];; localObject = Undefined.instance)
    {
      l4 = getLengthProperty(paramContext, paramScriptable);
      if (paramArrayOfObject.length >= 2) {
        break;
      }
      l1 = l2;
      l2 = l1;
      if (!(paramScriptable instanceof NativeArray)) {
        break label208;
      }
      paramArrayOfObject = (NativeArray)paramScriptable;
      l2 = l1;
      if (!paramArrayOfObject.denseOnly) {
        break label208;
      }
      Scriptable localScriptable = paramArrayOfObject.getPrototype();
      i = (int)l1;
      label67:
      if (i >= l4) {
        break label198;
      }
      paramScriptable = paramArrayOfObject.dense[i];
      paramContext = paramScriptable;
      if (paramScriptable == NOT_FOUND)
      {
        paramContext = paramScriptable;
        if (localScriptable != null) {
          paramContext = ScriptableObject.getProperty(localScriptable, i);
        }
      }
      if ((paramContext == NOT_FOUND) || (!ScriptRuntime.shallowEq(paramContext, localObject))) {
        break label191;
      }
      return Long.valueOf(i);
    }
    long l3 = ScriptRuntime.toInteger(paramArrayOfObject[1]);
    long l1 = l3;
    if (l3 < 0L)
    {
      l3 += l4;
      l1 = l3;
      if (l3 >= 0L) {}
    }
    for (;;)
    {
      l1 = l2;
      if (l2 <= l4 - 1L) {
        break;
      }
      return NEGATIVE_ONE;
      label191:
      i += 1;
      break label67;
      label198:
      return NEGATIVE_ONE;
      label208:
      do
      {
        l2 += 1L;
        if (l2 >= l4) {
          break;
        }
        paramContext = getRawElem(paramScriptable, l2);
      } while ((paramContext == NOT_FOUND) || (!ScriptRuntime.shallowEq(paramContext, localObject)));
      return Long.valueOf(l2);
      return NEGATIVE_ONE;
      l2 = l1;
    }
  }
  
  private static boolean js_isArray(Object paramObject)
  {
    if (!(paramObject instanceof Scriptable)) {
      return false;
    }
    return "Array".equals(((Scriptable)paramObject).getClassName());
  }
  
  private static String js_join(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    int i = 0;
    long l = getLengthProperty(paramContext, paramScriptable);
    int m = (int)l;
    if (l != m) {
      throw Context.reportRuntimeError1("msg.arraylength.too.big", String.valueOf(l));
    }
    if ((paramArrayOfObject.length < 1) || (paramArrayOfObject[0] == Undefined.instance)) {
      paramArrayOfObject = ",";
    }
    while ((paramScriptable instanceof NativeArray))
    {
      localObject1 = (NativeArray)paramScriptable;
      if (((NativeArray)localObject1).denseOnly)
      {
        paramContext = new StringBuilder();
        for (;;)
        {
          if (i < m)
          {
            if (i != 0) {
              paramContext.append(paramArrayOfObject);
            }
            if (i < ((NativeArray)localObject1).dense.length)
            {
              paramScriptable = localObject1.dense[i];
              if ((paramScriptable != null) && (paramScriptable != Undefined.instance) && (paramScriptable != Scriptable.NOT_FOUND)) {
                paramContext.append(ScriptRuntime.toString(paramScriptable));
              }
            }
            i += 1;
            continue;
            paramArrayOfObject = ScriptRuntime.toString(paramArrayOfObject[0]);
            break;
          }
        }
        return paramContext.toString();
      }
    }
    if (m == 0) {
      return "";
    }
    Object localObject1 = new String[m];
    i = 0;
    int k;
    for (int j = 0; i != m; j = k)
    {
      Object localObject2 = getElem(paramContext, paramScriptable, i);
      k = j;
      if (localObject2 != null)
      {
        k = j;
        if (localObject2 != Undefined.instance)
        {
          localObject2 = ScriptRuntime.toString(localObject2);
          k = j + ((String)localObject2).length();
          localObject1[i] = localObject2;
        }
      }
      i += 1;
    }
    paramContext = new StringBuilder(j + (m - 1) * paramArrayOfObject.length());
    i = 0;
    while (i != m)
    {
      if (i != 0) {
        paramContext.append(paramArrayOfObject);
      }
      paramScriptable = localObject1[i];
      if (paramScriptable != null) {
        paramContext.append(paramScriptable);
      }
      i += 1;
    }
    return paramContext.toString();
  }
  
  private static Object js_lastIndexOf(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    Object localObject;
    long l3;
    long l2;
    long l1;
    Scriptable localScriptable;
    int i;
    if (paramArrayOfObject.length > 0)
    {
      localObject = paramArrayOfObject[0];
      l3 = getLengthProperty(paramContext, paramScriptable);
      if (paramArrayOfObject.length >= 2) {
        break label130;
      }
      l2 = l3 - 1L;
      l1 = l2;
      if (!(paramScriptable instanceof NativeArray)) {
        break label206;
      }
      paramArrayOfObject = (NativeArray)paramScriptable;
      l1 = l2;
      if (!paramArrayOfObject.denseOnly) {
        break label206;
      }
      localScriptable = paramArrayOfObject.getPrototype();
      i = (int)l2;
    }
    for (;;)
    {
      if (i < 0) {
        break label196;
      }
      paramScriptable = paramArrayOfObject.dense[i];
      paramContext = paramScriptable;
      if (paramScriptable == NOT_FOUND)
      {
        paramContext = paramScriptable;
        if (localScriptable != null) {
          paramContext = ScriptableObject.getProperty(localScriptable, i);
        }
      }
      if ((paramContext != NOT_FOUND) && (ScriptRuntime.shallowEq(paramContext, localObject)))
      {
        return Long.valueOf(i);
        localObject = Undefined.instance;
        break;
        label130:
        l2 = ScriptRuntime.toInteger(paramArrayOfObject[1]);
        if (l2 >= l3) {
          l1 = l3 - 1L;
        }
        for (;;)
        {
          l2 = l1;
          if (l1 >= 0L) {
            break;
          }
          return NEGATIVE_ONE;
          l1 = l2;
          if (l2 < 0L) {
            l1 = l2 + l3;
          }
        }
      }
      i -= 1;
    }
    label196:
    return NEGATIVE_ONE;
    label206:
    do
    {
      l1 -= 1L;
      if (l1 < 0L) {
        break;
      }
      paramContext = getRawElem(paramScriptable, l1);
    } while ((paramContext == NOT_FOUND) || (!ScriptRuntime.shallowEq(paramContext, localObject)));
    return Long.valueOf(l1);
    return NEGATIVE_ONE;
  }
  
  private static Object js_pop(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    if ((paramScriptable instanceof NativeArray))
    {
      paramArrayOfObject = (NativeArray)paramScriptable;
      if ((paramArrayOfObject.denseOnly) && (paramArrayOfObject.length > 0L))
      {
        paramArrayOfObject.length -= 1L;
        paramContext = paramArrayOfObject.dense[((int)paramArrayOfObject.length)];
        paramArrayOfObject.dense[((int)paramArrayOfObject.length)] = NOT_FOUND;
        return paramContext;
      }
    }
    long l = getLengthProperty(paramContext, paramScriptable);
    if (l > 0L)
    {
      l -= 1L;
      paramArrayOfObject = getElem(paramContext, paramScriptable, l);
      deleteElem(paramScriptable, l);
    }
    for (;;)
    {
      setLengthProperty(paramContext, paramScriptable, l);
      return paramArrayOfObject;
      paramArrayOfObject = Undefined.instance;
    }
  }
  
  private static Object js_push(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    int j = 0;
    int i = 0;
    long l;
    if ((paramScriptable instanceof NativeArray))
    {
      NativeArray localNativeArray = (NativeArray)paramScriptable;
      if ((localNativeArray.denseOnly) && (localNativeArray.ensureCapacity((int)localNativeArray.length + paramArrayOfObject.length)))
      {
        while (i < paramArrayOfObject.length)
        {
          paramContext = localNativeArray.dense;
          l = localNativeArray.length;
          localNativeArray.length = (1L + l);
          paramContext[((int)l)] = paramArrayOfObject[i];
          i += 1;
        }
        paramScriptable = ScriptRuntime.wrapNumber(localNativeArray.length);
      }
    }
    do
    {
      return paramScriptable;
      l = getLengthProperty(paramContext, paramScriptable);
      i = j;
      while (i < paramArrayOfObject.length)
      {
        setElem(paramContext, paramScriptable, i + l, paramArrayOfObject[i]);
        i += 1;
      }
      paramScriptable = setLengthProperty(paramContext, paramScriptable, paramArrayOfObject.length + l);
    } while (paramContext.getLanguageVersion() != 120);
    if (paramArrayOfObject.length == 0) {
      return Undefined.instance;
    }
    return paramArrayOfObject[(paramArrayOfObject.length - 1)];
  }
  
  private static Scriptable js_reverse(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    if ((paramScriptable instanceof NativeArray))
    {
      paramArrayOfObject = (NativeArray)paramScriptable;
      if (paramArrayOfObject.denseOnly)
      {
        int j = 0;
        int i = (int)paramArrayOfObject.length - 1;
        while (j < i)
        {
          paramContext = paramArrayOfObject.dense[j];
          paramArrayOfObject.dense[j] = paramArrayOfObject.dense[i];
          paramArrayOfObject.dense[i] = paramContext;
          j += 1;
          i -= 1;
        }
      }
    }
    long l2 = getLengthProperty(paramContext, paramScriptable);
    long l3 = l2 / 2L;
    for (long l1 = 0L; l1 < l3; l1 += 1L)
    {
      long l4 = l2 - l1 - 1L;
      paramArrayOfObject = getRawElem(paramScriptable, l1);
      setRawElem(paramContext, paramScriptable, l1, getRawElem(paramScriptable, l4));
      setRawElem(paramContext, paramScriptable, l4, paramArrayOfObject);
    }
    return paramScriptable;
  }
  
  private static Object js_shift(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    if ((paramScriptable instanceof NativeArray))
    {
      paramArrayOfObject = (NativeArray)paramScriptable;
      if ((paramArrayOfObject.denseOnly) && (paramArrayOfObject.length > 0L))
      {
        paramArrayOfObject.length -= 1L;
        paramContext = paramArrayOfObject.dense[0];
        System.arraycopy(paramArrayOfObject.dense, 1, paramArrayOfObject.dense, 0, (int)paramArrayOfObject.length);
        paramArrayOfObject.dense[((int)paramArrayOfObject.length)] = NOT_FOUND;
        if (paramContext == NOT_FOUND) {
          return Undefined.instance;
        }
        return paramContext;
      }
    }
    long l1 = getLengthProperty(paramContext, paramScriptable);
    if (l1 > 0L)
    {
      long l2 = l1 - 1L;
      paramArrayOfObject = getElem(paramContext, paramScriptable, 0L);
      if (l2 > 0L) {
        for (l1 = 1L; l1 <= l2; l1 += 1L) {
          setRawElem(paramContext, paramScriptable, l1 - 1L, getRawElem(paramScriptable, l1));
        }
      }
      deleteElem(paramScriptable, l2);
      l1 = l2;
    }
    for (;;)
    {
      setLengthProperty(paramContext, paramScriptable, l1);
      return paramArrayOfObject;
      paramArrayOfObject = Undefined.instance;
    }
  }
  
  private Scriptable js_slice(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    Scriptable localScriptable = paramContext.newArray(getTopLevelScope(this), 0);
    long l3 = getLengthProperty(paramContext, paramScriptable);
    long l1;
    long l2;
    if (paramArrayOfObject.length == 0)
    {
      l1 = 0L;
      l2 = l3;
    }
    for (;;)
    {
      for (l3 = l1; l3 < l2; l3 += 1L)
      {
        paramArrayOfObject = getRawElem(paramScriptable, l3);
        if (paramArrayOfObject != NOT_FOUND) {
          defineElem(paramContext, localScriptable, l3 - l1, paramArrayOfObject);
        }
      }
      long l4 = toSliceIndex(ScriptRuntime.toInteger(paramArrayOfObject[0]), l3);
      l2 = l3;
      l1 = l4;
      if (paramArrayOfObject.length != 1)
      {
        l2 = l3;
        l1 = l4;
        if (paramArrayOfObject[1] != Undefined.instance)
        {
          l2 = toSliceIndex(ScriptRuntime.toInteger(paramArrayOfObject[1]), l3);
          l1 = l4;
        }
      }
    }
    setLengthProperty(paramContext, localScriptable, Math.max(0L, l2 - l1));
    return localScriptable;
  }
  
  private static Scriptable js_sort(final Context paramContext, final Scriptable paramScriptable1, Scriptable paramScriptable2, final Object[] paramArrayOfObject)
  {
    final Scriptable localScriptable;
    if ((paramArrayOfObject.length > 0) && (Undefined.instance != paramArrayOfObject[0]))
    {
      paramArrayOfObject = ScriptRuntime.getValueFunctionAndThis(paramArrayOfObject[0], paramContext);
      localScriptable = ScriptRuntime.lastStoredScriptable(paramContext);
    }
    int j;
    for (paramScriptable1 = new Comparator()
        {
          public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
          {
            if (paramAnonymousObject1 == Scriptable.NOT_FOUND) {
              if (paramAnonymousObject2 != Scriptable.NOT_FOUND) {}
            }
            double d;
            do
            {
              do
              {
                return 0;
                return 1;
                if (paramAnonymousObject2 == Scriptable.NOT_FOUND) {
                  return -1;
                }
                if (paramAnonymousObject1 != Undefined.instance) {
                  break;
                }
              } while (paramAnonymousObject2 == Undefined.instance);
              return 1;
              if (paramAnonymousObject2 == Undefined.instance) {
                return -1;
              }
              this.val$cmpBuf[0] = paramAnonymousObject1;
              this.val$cmpBuf[1] = paramAnonymousObject2;
              d = ScriptRuntime.toNumber(paramArrayOfObject.call(paramContext, paramScriptable1, localScriptable, this.val$cmpBuf));
              if (d < 0.0D) {
                return -1;
              }
            } while (d <= 0.0D);
            return 1;
          }
        };; paramScriptable1 = new Comparator()
        {
          public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
          {
            if (paramAnonymousObject1 == Scriptable.NOT_FOUND) {
              if (paramAnonymousObject2 != Scriptable.NOT_FOUND) {}
            }
            do
            {
              return 0;
              return 1;
              if (paramAnonymousObject2 == Scriptable.NOT_FOUND) {
                return -1;
              }
              if (paramAnonymousObject1 != Undefined.instance) {
                break;
              }
            } while (paramAnonymousObject2 == Undefined.instance);
            return 1;
            if (paramAnonymousObject2 == Undefined.instance) {
              return -1;
            }
            return ScriptRuntime.toString(paramAnonymousObject1).compareTo(ScriptRuntime.toString(paramAnonymousObject2));
          }
        })
    {
      long l = getLengthProperty(paramContext, paramScriptable2);
      j = (int)l;
      if (l == j) {
        break;
      }
      throw Context.reportRuntimeError1("msg.arraylength.too.big", String.valueOf(l));
    }
    paramArrayOfObject = new Object[j];
    int i = 0;
    while (i != j)
    {
      paramArrayOfObject[i] = getRawElem(paramScriptable2, i);
      i += 1;
    }
    Arrays.sort(paramArrayOfObject, paramScriptable1);
    i = 0;
    while (i < j)
    {
      setRawElem(paramContext, paramScriptable2, i, paramArrayOfObject[i]);
      i += 1;
    }
    return paramScriptable2;
  }
  
  private static Object js_splice(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    NativeArray localNativeArray = null;
    boolean bool = false;
    if ((paramScriptable2 instanceof NativeArray))
    {
      localNativeArray = (NativeArray)paramScriptable2;
      bool = localNativeArray.denseOnly;
    }
    paramScriptable1 = getTopLevelScope(paramScriptable1);
    int i = paramArrayOfObject.length;
    if (i == 0) {
      return paramContext.newArray(paramScriptable1, 0);
    }
    long l5 = getLengthProperty(paramContext, paramScriptable2);
    long l3 = toSliceIndex(ScriptRuntime.toInteger(paramArrayOfObject[0]), l5);
    i -= 1;
    long l1;
    long l4;
    if (paramArrayOfObject.length == 1)
    {
      l1 = l5 - l3;
      l4 = l3 + l1;
      if (l1 == 0L) {
        break label425;
      }
      if ((l1 != 1L) || (paramContext.getLanguageVersion() != 120)) {
        break label308;
      }
      paramScriptable1 = getElem(paramContext, paramScriptable2, l3);
    }
    long l2;
    for (;;)
    {
      l2 = i - l1;
      if ((!bool) || (l5 + l2 >= 2147483647L) || (!localNativeArray.ensureCapacity((int)(l5 + l2)))) {
        break label451;
      }
      System.arraycopy(localNativeArray.dense, (int)l4, localNativeArray.dense, (int)(i + l3), (int)(l5 - l4));
      if (i > 0) {
        System.arraycopy(paramArrayOfObject, 2, localNativeArray.dense, (int)l3, i);
      }
      if (l2 < 0L) {
        Arrays.fill(localNativeArray.dense, (int)(l5 + l2), (int)l5, NOT_FOUND);
      }
      localNativeArray.length = (l5 + l2);
      return paramScriptable1;
      double d = ScriptRuntime.toInteger(paramArrayOfObject[1]);
      if (d < 0.0D) {
        l1 = 0L;
      }
      for (;;)
      {
        i -= 1;
        break;
        if (d > l5 - l3) {
          l1 = l5 - l3;
        } else {
          l1 = d;
        }
      }
      label308:
      Object localObject;
      if (bool)
      {
        j = (int)(l4 - l3);
        localObject = new Object[j];
        System.arraycopy(localNativeArray.dense, (int)l3, localObject, 0, j);
        paramScriptable1 = paramContext.newArray(paramScriptable1, (Object[])localObject);
      }
      else
      {
        paramScriptable1 = paramContext.newArray(paramScriptable1, 0);
        for (l2 = l3; l2 != l4; l2 += 1L)
        {
          localObject = getRawElem(paramScriptable2, l2);
          if (localObject != NOT_FOUND) {
            setElem(paramContext, paramScriptable1, l2 - l3, localObject);
          }
        }
        setLengthProperty(paramContext, paramScriptable1, l4 - l3);
        continue;
        label425:
        if (paramContext.getLanguageVersion() == 120) {
          paramScriptable1 = Undefined.instance;
        } else {
          paramScriptable1 = paramContext.newArray(paramScriptable1, 0);
        }
      }
    }
    label451:
    if (l2 > 0L) {
      for (l1 = l5 - 1L; l1 >= l4; l1 -= 1L) {
        setRawElem(paramContext, paramScriptable2, l1 + l2, getRawElem(paramScriptable2, l1));
      }
    }
    if (l2 < 0L)
    {
      for (l1 = l4; l1 < l5; l1 += 1L) {
        setRawElem(paramContext, paramScriptable2, l1 + l2, getRawElem(paramScriptable2, l1));
      }
      for (l1 = l5 + l2; l1 < l5; l1 += 1L) {
        deleteElem(paramScriptable2, l1);
      }
    }
    int k = paramArrayOfObject.length;
    int j = 0;
    while (j < i)
    {
      setElem(paramContext, paramScriptable2, j + l3, paramArrayOfObject[(j + (k - i))]);
      j += 1;
    }
    setLengthProperty(paramContext, paramScriptable2, l5 + l2);
    return paramScriptable1;
  }
  
  private static Object js_unshift(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    Object localObject;
    if ((paramScriptable instanceof NativeArray))
    {
      localObject = (NativeArray)paramScriptable;
      if ((((NativeArray)localObject).denseOnly) && (((NativeArray)localObject).ensureCapacity((int)((NativeArray)localObject).length + paramArrayOfObject.length)))
      {
        System.arraycopy(((NativeArray)localObject).dense, 0, ((NativeArray)localObject).dense, paramArrayOfObject.length, (int)((NativeArray)localObject).length);
        i = 0;
        while (i < paramArrayOfObject.length)
        {
          ((NativeArray)localObject).dense[i] = paramArrayOfObject[i];
          i += 1;
        }
        ((NativeArray)localObject).length += paramArrayOfObject.length;
        return ScriptRuntime.wrapNumber(((NativeArray)localObject).length);
      }
    }
    long l2 = getLengthProperty(paramContext, paramScriptable);
    int i = paramArrayOfObject.length;
    if (paramArrayOfObject.length > 0)
    {
      if (l2 > 0L) {
        for (long l1 = l2 - 1L; l1 >= 0L; l1 -= 1L)
        {
          localObject = getRawElem(paramScriptable, l1);
          setRawElem(paramContext, paramScriptable, i + l1, localObject);
        }
      }
      i = 0;
      while (i < paramArrayOfObject.length)
      {
        setElem(paramContext, paramScriptable, i, paramArrayOfObject[i]);
        i += 1;
      }
    }
    return setLengthProperty(paramContext, paramScriptable, paramArrayOfObject.length + l2);
  }
  
  private static Object reduceMethod(Context paramContext, int paramInt, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    long l3 = getLengthProperty(paramContext, paramScriptable2);
    if (paramArrayOfObject.length > 0) {}
    for (paramScriptable1 = paramArrayOfObject[0]; (paramScriptable1 == null) || (!(paramScriptable1 instanceof Function)); paramScriptable1 = Undefined.instance) {
      throw ScriptRuntime.notFunctionError(paramScriptable1);
    }
    Function localFunction = (Function)paramScriptable1;
    Scriptable localScriptable = ScriptableObject.getTopLevelScope(localFunction);
    label74:
    long l1;
    label77:
    long l2;
    if (paramInt == 24)
    {
      paramInt = 1;
      if (paramArrayOfObject.length <= 1) {
        break label123;
      }
      paramScriptable1 = paramArrayOfObject[1];
      l1 = 0L;
      if (l1 >= l3) {
        break label196;
      }
      if (paramInt == 0) {
        break label130;
      }
      l2 = l1;
      label93:
      paramArrayOfObject = getRawElem(paramScriptable2, l2);
      if (paramArrayOfObject != Scriptable.NOT_FOUND) {
        break label142;
      }
    }
    for (;;)
    {
      l1 += 1L;
      break label77;
      paramInt = 0;
      break;
      label123:
      paramScriptable1 = Scriptable.NOT_FOUND;
      break label74;
      label130:
      l2 = l3 - 1L - l1;
      break label93;
      label142:
      if (paramScriptable1 == Scriptable.NOT_FOUND) {
        paramScriptable1 = paramArrayOfObject;
      } else {
        paramScriptable1 = localFunction.call(paramContext, localScriptable, localScriptable, new Object[] { paramScriptable1, paramArrayOfObject, Long.valueOf(l2), paramScriptable2 });
      }
    }
    label196:
    if (paramScriptable1 == Scriptable.NOT_FOUND) {
      throw ScriptRuntime.typeError0("msg.empty.array.reduce");
    }
    return paramScriptable1;
  }
  
  private static void setElem(Context paramContext, Scriptable paramScriptable, long paramLong, Object paramObject)
  {
    if (paramLong > 2147483647L)
    {
      ScriptableObject.putProperty(paramScriptable, Long.toString(paramLong), paramObject);
      return;
    }
    ScriptableObject.putProperty(paramScriptable, (int)paramLong, paramObject);
  }
  
  private void setLength(Object paramObject)
  {
    if ((this.lengthAttr & 0x1) != 0) {
      return;
    }
    double d = ScriptRuntime.toNumber(paramObject);
    long l2 = ScriptRuntime.toUint32(d);
    if (l2 != d) {
      throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage0("msg.arraylength.bad"));
    }
    if (this.denseOnly)
    {
      if (l2 < this.length)
      {
        Arrays.fill(this.dense, (int)l2, this.dense.length, NOT_FOUND);
        this.length = l2;
        return;
      }
      if ((l2 < 1431655764L) && (l2 < this.length * 1.5D) && (ensureCapacity((int)l2)))
      {
        this.length = l2;
        return;
      }
      this.denseOnly = false;
    }
    if (l2 < this.length) {
      if (this.length - l2 > 4096L)
      {
        paramObject = getIds();
        int i = 0;
        if (i < paramObject.length)
        {
          String str = paramObject[i];
          if ((str instanceof String))
          {
            str = (String)str;
            if (toArrayIndex(str) >= l2) {
              delete(str);
            }
          }
          for (;;)
          {
            i += 1;
            break;
            int j = ((Integer)str).intValue();
            if (j >= l2) {
              delete(j);
            }
          }
        }
      }
      else
      {
        for (long l1 = l2; l1 < this.length; l1 += 1L) {
          deleteElem(this, l1);
        }
      }
    }
    this.length = l2;
  }
  
  private static Object setLengthProperty(Context paramContext, Scriptable paramScriptable, long paramLong)
  {
    paramContext = ScriptRuntime.wrapNumber(paramLong);
    ScriptableObject.putProperty(paramScriptable, "length", paramContext);
    return paramContext;
  }
  
  static void setMaximumInitialCapacity(int paramInt)
  {
    maximumInitialCapacity = paramInt;
  }
  
  private static void setRawElem(Context paramContext, Scriptable paramScriptable, long paramLong, Object paramObject)
  {
    if (paramObject == NOT_FOUND)
    {
      deleteElem(paramScriptable, paramLong);
      return;
    }
    setElem(paramContext, paramScriptable, paramLong, paramObject);
  }
  
  private static long toArrayIndex(double paramDouble)
  {
    if (paramDouble == paramDouble)
    {
      long l = ScriptRuntime.toUint32(paramDouble);
      if ((l == paramDouble) && (l != 4294967295L)) {
        return l;
      }
    }
    return -1L;
  }
  
  private static long toArrayIndex(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      return toArrayIndex((String)paramObject);
    }
    if ((paramObject instanceof Number)) {
      return toArrayIndex(((Number)paramObject).doubleValue());
    }
    return -1L;
  }
  
  private static long toArrayIndex(String paramString)
  {
    long l = toArrayIndex(ScriptRuntime.toNumber(paramString));
    if (Long.toString(l).equals(paramString)) {
      return l;
    }
    return -1L;
  }
  
  private static int toDenseIndex(Object paramObject)
  {
    long l = toArrayIndex(paramObject);
    if ((0L <= l) && (l < 2147483647L)) {
      return (int)l;
    }
    return -1;
  }
  
  private static long toSliceIndex(double paramDouble, long paramLong)
  {
    long l;
    if (paramDouble < 0.0D) {
      if (paramLong + paramDouble < 0.0D) {
        l = 0L;
      }
    }
    do
    {
      return l;
      return (paramLong + paramDouble);
      l = paramLong;
    } while (paramDouble > paramLong);
    return paramDouble;
  }
  
  private static String toStringHelper(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, boolean paramBoolean1, boolean paramBoolean2)
  {
    long l2 = getLengthProperty(paramContext, paramScriptable2);
    StringBuilder localStringBuilder = new StringBuilder(256);
    String str;
    boolean bool;
    int j;
    if (paramBoolean1)
    {
      localStringBuilder.append('[');
      str = ", ";
      if (paramContext.iterating != null) {
        break label174;
      }
      bool = false;
      paramContext.iterating = new ObjToIntMap(31);
      j = 1;
      label62:
      if (bool) {
        break label396;
      }
    }
    for (;;)
    {
      Object localObject3;
      try
      {
        paramContext.iterating.put(paramScriptable2, 0);
        if (!paramBoolean1) {
          break label405;
        }
        if (paramContext.getLanguageVersion() < 150)
        {
          break label405;
          Object localObject1;
          if (localObject1 >= l2) {
            break label344;
          }
          if (localObject1 > 0L) {
            localStringBuilder.append(str);
          }
          localObject3 = getRawElem(paramScriptable2, localObject1);
          if (localObject3 != NOT_FOUND)
          {
            if (k == 0) {
              continue;
            }
            if (localObject3 != null)
            {
              localObject2 = Undefined.instance;
              if (localObject3 != localObject2) {
                continue;
              }
            }
          }
          i = 0;
          localObject1 += 1L;
          continue;
          str = ",";
          break;
          label174:
          bool = paramContext.iterating.has(paramScriptable2);
          j = 0;
          break label62;
        }
        k = 0;
        break label408;
        if (paramBoolean1)
        {
          localStringBuilder.append(ScriptRuntime.uneval(paramContext, paramScriptable1, localObject3));
          i = 1;
          continue;
        }
        if ((localObject3 instanceof String))
        {
          localObject2 = (String)localObject3;
          if (paramBoolean1)
          {
            localStringBuilder.append('"');
            localStringBuilder.append(ScriptRuntime.escapeString((String)localObject2));
            localStringBuilder.append('"');
          }
          else
          {
            localStringBuilder.append((String)localObject2);
          }
        }
      }
      finally
      {
        if (j != 0) {
          paramContext.iterating = null;
        }
      }
      Object localObject2 = localObject3;
      if (paramBoolean2) {
        localObject2 = ScriptRuntime.getPropFunctionAndThis(localObject3, "toLocaleString", paramContext, paramScriptable1).call(paramContext, paramScriptable1, ScriptRuntime.lastStoredScriptable(paramContext), ScriptRuntime.emptyArgs);
      }
      localStringBuilder.append(ScriptRuntime.toString(localObject2));
      int i = 1;
      continue;
      for (;;)
      {
        label344:
        if (j != 0) {
          paramContext.iterating = null;
        }
        if (paramBoolean1)
        {
          if ((i != 0) || (l1 <= 0L)) {
            break label385;
          }
          localStringBuilder.append(", ]");
        }
        for (;;)
        {
          return localStringBuilder.toString();
          label385:
          localStringBuilder.append(']');
        }
        label396:
        l1 = 0L;
        i = 0;
      }
      label405:
      int k = 1;
      label408:
      i = 0;
      long l1 = 0L;
      continue;
      i = 1;
    }
  }
  
  public void add(int paramInt, Object paramObject)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int paramInt, Collection paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(Collection paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean contains(Object paramObject)
  {
    return indexOf(paramObject) > -1;
  }
  
  public boolean containsAll(Collection paramCollection)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      if (!contains(paramCollection.next())) {
        return false;
      }
    }
    return true;
  }
  
  protected void defineOwnProperty(Context paramContext, Object paramObject, ScriptableObject paramScriptableObject, boolean paramBoolean)
  {
    int i = 0;
    if (this.dense != null)
    {
      Object[] arrayOfObject = this.dense;
      this.dense = null;
      this.denseOnly = false;
      while (i < arrayOfObject.length)
      {
        if (arrayOfObject[i] != NOT_FOUND) {
          put(i, this, arrayOfObject[i]);
        }
        i += 1;
      }
    }
    long l = toArrayIndex(paramObject);
    if (l >= this.length) {
      this.length = (l + 1L);
    }
    super.defineOwnProperty(paramContext, paramObject, paramScriptableObject, paramBoolean);
  }
  
  public void delete(int paramInt)
  {
    if ((this.dense != null) && (paramInt >= 0) && (paramInt < this.dense.length) && (!isSealed()) && ((this.denseOnly) || (!isGetterOrSetter(null, paramInt, true))))
    {
      this.dense[paramInt] = NOT_FOUND;
      return;
    }
    super.delete(paramInt);
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    int k = 1;
    boolean bool2 = false;
    if (!paramIdFunctionObject.hasTag(ARRAY_TAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i = paramIdFunctionObject.methodId();
    Object localObject = paramScriptable2;
    paramScriptable2 = paramArrayOfObject;
    paramArrayOfObject = (Object[])localObject;
    switch (i)
    {
    case -4: 
    case -3: 
    case -2: 
    case -1: 
    case 0: 
    default: 
      throw new IllegalArgumentException("Array.prototype has no method: " + paramIdFunctionObject.getFunctionName());
    case -25: 
    case -24: 
    case -23: 
    case -22: 
    case -21: 
    case -20: 
    case -19: 
    case -18: 
    case -17: 
    case -16: 
    case -15: 
    case -14: 
    case -13: 
    case -12: 
    case -11: 
    case -10: 
    case -9: 
    case -8: 
    case -7: 
    case -6: 
    case -5: 
      if (paramScriptable2.length > 0)
      {
        localObject = ScriptRuntime.toObject(paramContext, paramScriptable1, paramScriptable2[0]);
        paramArrayOfObject = new Object[paramScriptable2.length - 1];
        int j = 0;
        while (j < paramArrayOfObject.length)
        {
          paramArrayOfObject[j] = paramScriptable2[(j + 1)];
          j += 1;
        }
      }
      break;
    }
    for (paramScriptable2 = (Scriptable)localObject;; paramScriptable2 = (Scriptable)localObject)
    {
      i = -i;
      localObject = paramArrayOfObject;
      paramArrayOfObject = paramScriptable2;
      paramScriptable2 = (Scriptable)localObject;
      break;
      boolean bool1 = bool2;
      if (paramScriptable2.length > 0)
      {
        bool1 = bool2;
        if (js_isArray(paramScriptable2[0])) {
          bool1 = true;
        }
      }
      return Boolean.valueOf(bool1);
      if (paramArrayOfObject == null) {}
      for (i = k; i == 0; i = 0) {
        return paramIdFunctionObject.construct(paramContext, paramScriptable1, paramScriptable2);
      }
      return jsConstructor(paramContext, paramScriptable1, paramScriptable2);
      return toStringHelper(paramContext, paramScriptable1, paramArrayOfObject, paramContext.hasFeature(4), false);
      return toStringHelper(paramContext, paramScriptable1, paramArrayOfObject, false, true);
      return toStringHelper(paramContext, paramScriptable1, paramArrayOfObject, true, false);
      return js_join(paramContext, paramArrayOfObject, paramScriptable2);
      return js_reverse(paramContext, paramArrayOfObject, paramScriptable2);
      return js_sort(paramContext, paramScriptable1, paramArrayOfObject, paramScriptable2);
      return js_push(paramContext, paramArrayOfObject, paramScriptable2);
      return js_pop(paramContext, paramArrayOfObject, paramScriptable2);
      return js_shift(paramContext, paramArrayOfObject, paramScriptable2);
      return js_unshift(paramContext, paramArrayOfObject, paramScriptable2);
      return js_splice(paramContext, paramScriptable1, paramArrayOfObject, paramScriptable2);
      return js_concat(paramContext, paramScriptable1, paramArrayOfObject, paramScriptable2);
      return js_slice(paramContext, paramArrayOfObject, paramScriptable2);
      return js_indexOf(paramContext, paramArrayOfObject, paramScriptable2);
      return js_lastIndexOf(paramContext, paramArrayOfObject, paramScriptable2);
      return iterativeMethod(paramContext, paramIdFunctionObject, paramScriptable1, paramArrayOfObject, paramScriptable2);
      return reduceMethod(paramContext, i, paramScriptable1, paramArrayOfObject, paramScriptable2);
      return new NativeArrayIterator(paramScriptable1, paramArrayOfObject);
      localObject = paramArrayOfObject;
      paramArrayOfObject = paramScriptable2;
    }
  }
  
  protected void fillConstructorProperties(IdFunctionObject paramIdFunctionObject)
  {
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -5, "join", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -6, "reverse", 0);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -7, "sort", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -8, "push", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -9, "pop", 0);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -10, "shift", 0);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -11, "unshift", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -12, "splice", 2);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -13, "concat", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -14, "slice", 2);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -15, "indexOf", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -16, "lastIndexOf", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -17, "every", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -18, "filter", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -19, "forEach", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -20, "map", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -21, "some", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -22, "find", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -23, "findIndex", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -24, "reduce", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -25, "reduceRight", 1);
    addIdFunctionProperty(paramIdFunctionObject, ARRAY_TAG, -26, "isArray", 1);
    super.fillConstructorProperties(paramIdFunctionObject);
  }
  
  protected int findInstanceIdInfo(String paramString)
  {
    if (paramString.equals("length")) {
      return instanceIdInfo(this.lengthAttr, 1);
    }
    return super.findInstanceIdInfo(paramString);
  }
  
  protected int findPrototypeId(String paramString)
  {
    label68:
    String str;
    int i;
    switch (paramString.length())
    {
    case 12: 
    case 13: 
    default: 
      str = null;
      i = 0;
    }
    for (;;)
    {
      if ((str == null) || (str == paramString) || (str.equals(paramString))) {
        return i;
      }
      return 0;
      i = paramString.charAt(0);
      if (i == 109)
      {
        if ((paramString.charAt(2) != 'p') || (paramString.charAt(1) != 'a')) {
          break;
        }
        return 20;
      }
      if ((i != 112) || (paramString.charAt(2) != 'p') || (paramString.charAt(1) != 'o')) {
        break;
      }
      return 9;
      switch (paramString.charAt(2))
      {
      case 'j': 
      case 'k': 
      case 'l': 
      case 'o': 
      case 'p': 
      case 'q': 
      default: 
        str = null;
        i = 0;
        break;
      case 'i': 
        i = 5;
        str = "join";
        break;
      case 'm': 
        i = 21;
        str = "some";
        break;
      case 'n': 
        i = 22;
        str = "find";
        break;
      case 'r': 
        i = 7;
        str = "sort";
        break;
      case 's': 
        i = 8;
        str = "push";
        continue;
        i = paramString.charAt(1);
        if (i == 104)
        {
          i = 10;
          str = "shift";
        }
        else if (i == 108)
        {
          i = 14;
          str = "slice";
        }
        else
        {
          if (i != 118) {
            break label68;
          }
          i = 17;
          str = "every";
          continue;
          switch (paramString.charAt(0))
          {
          default: 
            str = null;
            i = 0;
            break;
          case 'c': 
            i = 13;
            str = "concat";
            break;
          case 'f': 
            i = 18;
            str = "filter";
            break;
          case 'r': 
            i = 24;
            str = "reduce";
            break;
          case 's': 
            i = 12;
            str = "splice";
            continue;
            switch (paramString.charAt(0))
            {
            default: 
              str = null;
              i = 0;
              break;
            case 'f': 
              i = 19;
              str = "forEach";
              break;
            case 'i': 
              i = 15;
              str = "indexOf";
              break;
            case 'r': 
              i = 6;
              str = "reverse";
              break;
            case 'u': 
              i = 11;
              str = "unshift";
              continue;
              i = paramString.charAt(3);
              if (i == 111)
              {
                i = 4;
                str = "toSource";
              }
              else
              {
                if (i != 116) {
                  break label68;
                }
                i = 2;
                str = "toString";
                continue;
                i = 23;
                str = "findIndex";
                continue;
                i = 26;
                str = "@@iterator";
                continue;
                i = paramString.charAt(0);
                if (i == 99)
                {
                  str = "constructor";
                  i = 1;
                }
                else if (i == 108)
                {
                  i = 16;
                  str = "lastIndexOf";
                }
                else
                {
                  if (i != 114) {
                    break label68;
                  }
                  i = 25;
                  str = "reduceRight";
                  continue;
                  str = "toLocaleString";
                  i = 3;
                }
              }
              break;
            }
            break;
          }
        }
        break;
      }
    }
    return i;
  }
  
  public Object get(int paramInt)
  {
    return get(paramInt);
  }
  
  public Object get(int paramInt, Scriptable paramScriptable)
  {
    if ((!this.denseOnly) && (isGetterOrSetter(null, paramInt, false))) {
      return super.get(paramInt, paramScriptable);
    }
    if ((this.dense != null) && (paramInt >= 0) && (paramInt < this.dense.length)) {
      return this.dense[paramInt];
    }
    return super.get(paramInt, paramScriptable);
  }
  
  public Object get(long paramLong)
  {
    if ((paramLong < 0L) || (paramLong >= this.length)) {
      throw new IndexOutOfBoundsException();
    }
    Object localObject2 = getRawElem(this, paramLong);
    Object localObject1;
    if ((localObject2 == Scriptable.NOT_FOUND) || (localObject2 == Undefined.instance)) {
      localObject1 = null;
    }
    do
    {
      return localObject1;
      localObject1 = localObject2;
    } while (!(localObject2 instanceof Wrapper));
    return ((Wrapper)localObject2).unwrap();
  }
  
  public Object[] getAllIds()
  {
    LinkedHashSet localLinkedHashSet = new LinkedHashSet(Arrays.asList(getIds()));
    localLinkedHashSet.addAll(Arrays.asList(super.getAllIds()));
    return localLinkedHashSet.toArray();
  }
  
  public int getAttributes(int paramInt)
  {
    if ((this.dense != null) && (paramInt >= 0) && (paramInt < this.dense.length) && (this.dense[paramInt] != NOT_FOUND)) {
      return 0;
    }
    return super.getAttributes(paramInt);
  }
  
  public String getClassName()
  {
    return "Array";
  }
  
  public Object getDefaultValue(Class<?> paramClass)
  {
    if ((paramClass == ScriptRuntime.NumberClass) && (Context.getContext().getLanguageVersion() == 120)) {
      return Long.valueOf(this.length);
    }
    return super.getDefaultValue(paramClass);
  }
  
  public Object[] getIds()
  {
    Object[] arrayOfObject2 = super.getIds();
    if (this.dense == null) {
      return arrayOfObject2;
    }
    int i = this.dense.length;
    long l = this.length;
    if (i > l) {
      i = (int)l;
    }
    for (;;)
    {
      if (i == 0) {
        return arrayOfObject2;
      }
      int n = arrayOfObject2.length;
      Object localObject = new Object[i + n];
      int j = 0;
      int m;
      for (int k = 0; j != i; k = m)
      {
        m = k;
        if (this.dense[j] != NOT_FOUND)
        {
          localObject[k] = Integer.valueOf(j);
          m = k + 1;
        }
        j += 1;
      }
      if (k != i)
      {
        Object[] arrayOfObject1 = new Object[k + n];
        System.arraycopy(localObject, 0, arrayOfObject1, 0, k);
        localObject = arrayOfObject1;
      }
      for (;;)
      {
        System.arraycopy(arrayOfObject2, 0, localObject, k, n);
        return (Object[])localObject;
      }
    }
  }
  
  public Integer[] getIndexIds()
  {
    Object[] arrayOfObject = getIds();
    ArrayList localArrayList = new ArrayList(arrayOfObject.length);
    int j = arrayOfObject.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = arrayOfObject[i];
      int k = ScriptRuntime.toInt32(localObject);
      if ((k >= 0) && (ScriptRuntime.toString(k).equals(ScriptRuntime.toString(localObject)))) {
        localArrayList.add(Integer.valueOf(k));
      }
      i += 1;
    }
    return (Integer[])localArrayList.toArray(new Integer[localArrayList.size()]);
  }
  
  protected String getInstanceIdName(int paramInt)
  {
    if (paramInt == 1) {
      return "length";
    }
    return super.getInstanceIdName(paramInt);
  }
  
  protected Object getInstanceIdValue(int paramInt)
  {
    if (paramInt == 1) {
      return ScriptRuntime.wrapNumber(this.length);
    }
    return super.getInstanceIdValue(paramInt);
  }
  
  public long getLength()
  {
    return this.length;
  }
  
  protected int getMaxInstanceId()
  {
    return 1;
  }
  
  protected ScriptableObject getOwnPropertyDescriptor(Context paramContext, Object paramObject)
  {
    if (this.dense != null)
    {
      int i = toDenseIndex(paramObject);
      if ((i >= 0) && (i < this.dense.length) && (this.dense[i] != NOT_FOUND)) {
        return defaultIndexPropertyDescriptor(this.dense[i]);
      }
    }
    return super.getOwnPropertyDescriptor(paramContext, paramObject);
  }
  
  public boolean has(int paramInt, Scriptable paramScriptable)
  {
    boolean bool = false;
    if ((!this.denseOnly) && (isGetterOrSetter(null, paramInt, false))) {
      bool = super.has(paramInt, paramScriptable);
    }
    do
    {
      return bool;
      if ((this.dense == null) || (paramInt < 0) || (paramInt >= this.dense.length)) {
        break;
      }
    } while (this.dense[paramInt] == NOT_FOUND);
    return true;
    return super.has(paramInt, paramScriptable);
  }
  
  public int indexOf(Object paramObject)
  {
    int i = 0;
    int j = 0;
    long l = this.length;
    if (l > 2147483647L) {
      throw new IllegalStateException();
    }
    int k = (int)l;
    if (paramObject == null)
    {
      i = j;
      while (i < k)
      {
        if (get(i) == null) {
          return i;
        }
        i += 1;
      }
    }
    do
    {
      i += 1;
      if (i >= k) {
        break;
      }
    } while (!paramObject.equals(get(i)));
    return i;
    return -1;
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i = 1;
    String str2 = null;
    String str1;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      str1 = "constructor";
    }
    for (;;)
    {
      initPrototypeMethod(ARRAY_TAG, paramInt, str1, str2, i);
      return;
      str1 = "toString";
      i = 0;
      continue;
      str1 = "toLocaleString";
      i = 0;
      continue;
      str1 = "toSource";
      i = 0;
      continue;
      str1 = "join";
      continue;
      str1 = "reverse";
      i = 0;
      continue;
      str1 = "sort";
      continue;
      str1 = "push";
      continue;
      str1 = "pop";
      i = 0;
      continue;
      str1 = "shift";
      i = 0;
      continue;
      str1 = "unshift";
      continue;
      str1 = "splice";
      i = 2;
      continue;
      str1 = "concat";
      continue;
      str1 = "slice";
      i = 2;
      continue;
      str1 = "indexOf";
      continue;
      str1 = "lastIndexOf";
      continue;
      str1 = "every";
      continue;
      str1 = "filter";
      continue;
      str1 = "forEach";
      continue;
      str1 = "map";
      continue;
      str1 = "some";
      continue;
      str1 = "find";
      continue;
      str1 = "findIndex";
      continue;
      str1 = "reduce";
      continue;
      str1 = "reduceRight";
      continue;
      str1 = "@@iterator";
      str2 = "[Symbol.iterator]";
      i = 0;
    }
  }
  
  public boolean isEmpty()
  {
    return this.length == 0L;
  }
  
  public Iterator iterator()
  {
    return listIterator(0);
  }
  
  @Deprecated
  public long jsGet_length()
  {
    return getLength();
  }
  
  public int lastIndexOf(Object paramObject)
  {
    long l = this.length;
    if (l > 2147483647L) {
      throw new IllegalStateException();
    }
    int i = (int)l;
    int j;
    if (paramObject == null)
    {
      i -= 1;
      while (i >= 0)
      {
        if (get(i) == null)
        {
          j = i;
          return j;
        }
        i -= 1;
      }
    }
    i -= 1;
    for (;;)
    {
      if (i < 0) {
        break label87;
      }
      j = i;
      if (paramObject.equals(get(i))) {
        break;
      }
      i -= 1;
    }
    label87:
    return -1;
  }
  
  public ListIterator listIterator()
  {
    return listIterator(0);
  }
  
  public ListIterator listIterator(final int paramInt)
  {
    long l = this.length;
    if (l > 2147483647L) {
      throw new IllegalStateException();
    }
    final int i = (int)l;
    if ((paramInt < 0) || (paramInt > i)) {
      throw new IndexOutOfBoundsException("Index: " + paramInt);
    }
    new ListIterator()
    {
      int cursor = paramInt;
      
      public void add(Object paramAnonymousObject)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean hasNext()
      {
        return this.cursor < i;
      }
      
      public boolean hasPrevious()
      {
        return this.cursor > 0;
      }
      
      public Object next()
      {
        if (this.cursor == i) {
          throw new NoSuchElementException();
        }
        NativeArray localNativeArray = NativeArray.this;
        int i = this.cursor;
        this.cursor = (i + 1);
        return localNativeArray.get(i);
      }
      
      public int nextIndex()
      {
        return this.cursor;
      }
      
      public Object previous()
      {
        if (this.cursor == 0) {
          throw new NoSuchElementException();
        }
        NativeArray localNativeArray = NativeArray.this;
        int i = this.cursor - 1;
        this.cursor = i;
        return localNativeArray.get(i);
      }
      
      public int previousIndex()
      {
        return this.cursor - 1;
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException();
      }
      
      public void set(Object paramAnonymousObject)
      {
        throw new UnsupportedOperationException();
      }
    };
  }
  
  public void put(int paramInt, Scriptable paramScriptable, Object paramObject)
  {
    if ((paramScriptable == this) && (!isSealed()) && (this.dense != null) && (paramInt >= 0) && ((this.denseOnly) || (!isGetterOrSetter(null, paramInt, true)))) {
      if ((isExtensible()) || (this.length > paramInt)) {}
    }
    do
    {
      do
      {
        return;
        if (paramInt >= this.dense.length) {
          break;
        }
        this.dense[paramInt] = paramObject;
      } while (this.length > paramInt);
      this.length = (paramInt + 1L);
      return;
      if ((this.denseOnly) && (paramInt < this.dense.length * 1.5D) && (ensureCapacity(paramInt + 1)))
      {
        this.dense[paramInt] = paramObject;
        this.length = (paramInt + 1L);
        return;
      }
      this.denseOnly = false;
      super.put(paramInt, paramScriptable, paramObject);
    } while ((paramScriptable != this) || ((this.lengthAttr & 0x1) != 0) || (this.length > paramInt));
    this.length = (paramInt + 1L);
  }
  
  public void put(String paramString, Scriptable paramScriptable, Object paramObject)
  {
    super.put(paramString, paramScriptable, paramObject);
    if (paramScriptable == this)
    {
      long l = toArrayIndex(paramString);
      if (l >= this.length)
      {
        this.length = (l + 1L);
        this.denseOnly = false;
      }
    }
  }
  
  public Object remove(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean remove(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean removeAll(Collection paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean retainAll(Collection paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public Object set(int paramInt, Object paramObject)
  {
    throw new UnsupportedOperationException();
  }
  
  void setDenseOnly(boolean paramBoolean)
  {
    if ((paramBoolean) && (!this.denseOnly)) {
      throw new IllegalArgumentException();
    }
    this.denseOnly = paramBoolean;
  }
  
  protected void setInstanceIdAttributes(int paramInt1, int paramInt2)
  {
    if (paramInt1 == 1) {
      this.lengthAttr = paramInt2;
    }
  }
  
  protected void setInstanceIdValue(int paramInt, Object paramObject)
  {
    if (paramInt == 1)
    {
      setLength(paramObject);
      return;
    }
    super.setInstanceIdValue(paramInt, paramObject);
  }
  
  public int size()
  {
    long l = this.length;
    if (l > 2147483647L) {
      throw new IllegalStateException();
    }
    return (int)l;
  }
  
  public List subList(int paramInt1, int paramInt2)
  {
    throw new UnsupportedOperationException();
  }
  
  public Object[] toArray()
  {
    return toArray(ScriptRuntime.emptyArgs);
  }
  
  public Object[] toArray(Object[] paramArrayOfObject)
  {
    long l = this.length;
    if (l > 2147483647L) {
      throw new IllegalStateException();
    }
    int j = (int)l;
    if (paramArrayOfObject.length >= j) {}
    for (;;)
    {
      int i = 0;
      while (i < j)
      {
        paramArrayOfObject[i] = get(i);
        i += 1;
      }
      paramArrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), j);
    }
    return paramArrayOfObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */