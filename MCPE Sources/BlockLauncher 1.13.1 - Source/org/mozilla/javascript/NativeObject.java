package org.mozilla.javascript;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public class NativeObject
  extends IdScriptableObject
  implements Map
{
  private static final int ConstructorId_create = -9;
  private static final int ConstructorId_defineProperties = -8;
  private static final int ConstructorId_defineProperty = -5;
  private static final int ConstructorId_freeze = -13;
  private static final int ConstructorId_getOwnPropertyDescriptor = -4;
  private static final int ConstructorId_getOwnPropertyNames = -3;
  private static final int ConstructorId_getPrototypeOf = -1;
  private static final int ConstructorId_isExtensible = -6;
  private static final int ConstructorId_isFrozen = -11;
  private static final int ConstructorId_isSealed = -10;
  private static final int ConstructorId_keys = -2;
  private static final int ConstructorId_preventExtensions = -7;
  private static final int ConstructorId_seal = -12;
  private static final int Id___defineGetter__ = 9;
  private static final int Id___defineSetter__ = 10;
  private static final int Id___lookupGetter__ = 11;
  private static final int Id___lookupSetter__ = 12;
  private static final int Id_constructor = 1;
  private static final int Id_hasOwnProperty = 5;
  private static final int Id_isPrototypeOf = 7;
  private static final int Id_propertyIsEnumerable = 6;
  private static final int Id_toLocaleString = 3;
  private static final int Id_toSource = 8;
  private static final int Id_toString = 2;
  private static final int Id_valueOf = 4;
  private static final int MAX_PROTOTYPE_ID = 12;
  private static final Object OBJECT_TAG = "Object";
  static final long serialVersionUID = -6345305608474346996L;
  
  private Scriptable getCompatibleObject(Context paramContext, Scriptable paramScriptable, Object paramObject)
  {
    if (paramContext.getLanguageVersion() >= 200) {
      return ensureScriptable(ScriptRuntime.toObject(paramContext, paramScriptable, paramObject));
    }
    return ensureScriptable(paramObject);
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    new NativeObject().exportAsJSClass(12, paramScriptable, paramBoolean);
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean containsKey(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      return has((String)paramObject, this);
    }
    if ((paramObject instanceof Number)) {
      return has(((Number)paramObject).intValue(), this);
    }
    return false;
  }
  
  public boolean containsValue(Object paramObject)
  {
    Iterator localIterator = values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if ((paramObject == localObject) || ((paramObject != null) && (paramObject.equals(localObject)))) {
        return true;
      }
    }
    return false;
  }
  
  public Set<Map.Entry<Object, Object>> entrySet()
  {
    return new EntrySet();
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    boolean bool1 = true;
    boolean bool4 = true;
    boolean bool2 = true;
    int k = 0;
    int m = 0;
    int i = 0;
    int j = 0;
    boolean bool3 = false;
    if (!paramIdFunctionObject.hasTag(OBJECT_TAG)) {
      paramIdFunctionObject = super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int n;
    do
    {
      do
      {
        do
        {
          return paramIdFunctionObject;
          n = paramIdFunctionObject.methodId();
          switch (n)
          {
          case 0: 
          default: 
            throw new IllegalArgumentException(String.valueOf(n));
          case 1: 
            if (paramScriptable2 != null) {
              return paramIdFunctionObject.construct(paramContext, paramScriptable1, paramArrayOfObject);
            }
            if ((paramArrayOfObject.length == 0) || (paramArrayOfObject[0] == null) || (paramArrayOfObject[0] == Undefined.instance)) {
              return new NativeObject();
            }
            return ScriptRuntime.toObject(paramContext, paramScriptable1, paramArrayOfObject[0]);
          case 3: 
            paramIdFunctionObject = ScriptableObject.getProperty(paramScriptable2, "toString");
            if (!(paramIdFunctionObject instanceof Callable)) {
              throw ScriptRuntime.notFunctionError(paramIdFunctionObject);
            }
            return ((Callable)paramIdFunctionObject).call(paramContext, paramScriptable1, paramScriptable2, ScriptRuntime.emptyArgs);
          case 2: 
            if (!paramContext.hasFeature(4)) {
              break label349;
            }
            paramContext = ScriptRuntime.defaultObjectToSource(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
            i = paramContext.length();
            paramIdFunctionObject = paramContext;
          }
        } while (i == 0);
        paramIdFunctionObject = paramContext;
      } while (paramContext.charAt(0) != '(');
      paramIdFunctionObject = paramContext;
    } while (paramContext.charAt(i - 1) != ')');
    return paramContext.substring(1, i - 1);
    label349:
    return ScriptRuntime.defaultObjectToString(paramScriptable2);
    return paramScriptable2;
    if (paramArrayOfObject.length < 1)
    {
      paramIdFunctionObject = Undefined.instance;
      paramIdFunctionObject = ScriptRuntime.toStringIdOrIndex(paramContext, paramIdFunctionObject);
      if (paramIdFunctionObject != null) {
        break label408;
      }
    }
    label408:
    for (bool1 = paramScriptable2.has(ScriptRuntime.lastIndexResult(paramContext), paramScriptable2);; bool1 = paramScriptable2.has(paramIdFunctionObject, paramScriptable2))
    {
      return ScriptRuntime.wrapBoolean(bool1);
      paramIdFunctionObject = paramArrayOfObject[0];
      break;
    }
    if (paramArrayOfObject.length < 1)
    {
      paramIdFunctionObject = Undefined.instance;
      paramIdFunctionObject = ScriptRuntime.toStringIdOrIndex(paramContext, paramIdFunctionObject);
      if (paramIdFunctionObject != null) {
        break label522;
      }
      i = ScriptRuntime.lastIndexResult(paramContext);
      bool2 = paramScriptable2.has(i, paramScriptable2);
      bool1 = bool2;
      if (bool2)
      {
        bool1 = bool2;
        if ((paramScriptable2 instanceof ScriptableObject))
        {
          if ((((ScriptableObject)paramScriptable2).getAttributes(i) & 0x2) != 0) {
            break label516;
          }
          bool1 = true;
        }
      }
    }
    label516:
    label522:
    do
    {
      do
      {
        for (;;)
        {
          return ScriptRuntime.wrapBoolean(bool1);
          paramIdFunctionObject = paramArrayOfObject[0];
          break;
          bool1 = false;
        }
        bool3 = paramScriptable2.has(paramIdFunctionObject, paramScriptable2);
        bool1 = bool3;
      } while (!bool3);
      bool1 = bool3;
    } while (!(paramScriptable2 instanceof ScriptableObject));
    if ((((ScriptableObject)paramScriptable2).getAttributes(paramIdFunctionObject) & 0x2) == 0) {}
    for (bool1 = bool2;; bool1 = false) {
      break;
    }
    bool1 = bool3;
    if (paramArrayOfObject.length != 0)
    {
      bool1 = bool3;
      if ((paramArrayOfObject[0] instanceof Scriptable))
      {
        paramIdFunctionObject = (Scriptable)paramArrayOfObject[0];
        paramContext = paramIdFunctionObject.getPrototype();
        if (paramContext != paramScriptable2) {
          break label636;
        }
      }
    }
    for (bool1 = true;; bool1 = bool3)
    {
      return ScriptRuntime.wrapBoolean(bool1);
      label636:
      paramIdFunctionObject = paramContext;
      if (paramContext != null) {
        break;
      }
    }
    return ScriptRuntime.defaultObjectToSource(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    if ((paramArrayOfObject.length < 2) || (!(paramArrayOfObject[1] instanceof Callable)))
    {
      if (paramArrayOfObject.length >= 2) {}
      for (paramIdFunctionObject = paramArrayOfObject[1];; paramIdFunctionObject = Undefined.instance) {
        throw ScriptRuntime.notFunctionError(paramIdFunctionObject);
      }
    }
    if (!(paramScriptable2 instanceof ScriptableObject)) {
      throw Context.reportRuntimeError2("msg.extend.scriptable", paramScriptable2.getClass().getName(), String.valueOf(paramArrayOfObject[0]));
    }
    paramIdFunctionObject = (ScriptableObject)paramScriptable2;
    paramScriptable1 = ScriptRuntime.toStringIdOrIndex(paramContext, paramArrayOfObject[0]);
    if (paramScriptable1 != null)
    {
      i = 0;
      paramContext = (Callable)paramArrayOfObject[1];
      if (n != 10) {
        break label805;
      }
    }
    for (;;)
    {
      paramIdFunctionObject.setGetterOrSetter(paramScriptable1, i, paramContext, bool1);
      if ((paramIdFunctionObject instanceof NativeArray)) {
        ((NativeArray)paramIdFunctionObject).setDenseOnly(false);
      }
      return Undefined.instance;
      i = ScriptRuntime.lastIndexResult(paramContext);
      break;
      label805:
      bool1 = false;
    }
    if ((paramArrayOfObject.length < 1) || (!(paramScriptable2 instanceof ScriptableObject))) {
      return Undefined.instance;
    }
    paramIdFunctionObject = (ScriptableObject)paramScriptable2;
    paramScriptable1 = ScriptRuntime.toStringIdOrIndex(paramContext, paramArrayOfObject[0]);
    if (paramScriptable1 != null)
    {
      i = 0;
      if (n != 12) {
        break label892;
      }
      bool1 = bool4;
    }
    for (;;)
    {
      label863:
      paramContext = paramIdFunctionObject.getGetterOrSetter(paramScriptable1, i, bool1);
      if (paramContext != null) {}
      label892:
      do
      {
        if (paramContext == null) {
          break label922;
        }
        return paramContext;
        i = ScriptRuntime.lastIndexResult(paramContext);
        break;
        bool1 = false;
        break label863;
        paramIdFunctionObject = paramIdFunctionObject.getPrototype();
      } while ((paramIdFunctionObject == null) || (!(paramIdFunctionObject instanceof ScriptableObject)));
      paramIdFunctionObject = (ScriptableObject)paramIdFunctionObject;
    }
    label922:
    return Undefined.instance;
    if (paramArrayOfObject.length < 1) {}
    for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[0]) {
      return getCompatibleObject(paramContext, paramScriptable1, paramIdFunctionObject).getPrototype();
    }
    if (paramArrayOfObject.length < 1) {}
    for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[0])
    {
      paramIdFunctionObject = getCompatibleObject(paramContext, paramScriptable1, paramIdFunctionObject).getIds();
      i = k;
      while (i < paramIdFunctionObject.length)
      {
        paramIdFunctionObject[i] = ScriptRuntime.toString(paramIdFunctionObject[i]);
        i += 1;
      }
    }
    return paramContext.newArray(paramScriptable1, paramIdFunctionObject);
    if (paramArrayOfObject.length < 1) {}
    for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[0])
    {
      paramIdFunctionObject = ensureScriptableObject(getCompatibleObject(paramContext, paramScriptable1, paramIdFunctionObject)).getAllIds();
      i = m;
      while (i < paramIdFunctionObject.length)
      {
        paramIdFunctionObject[i] = ScriptRuntime.toString(paramIdFunctionObject[i]);
        i += 1;
      }
    }
    return paramContext.newArray(paramScriptable1, paramIdFunctionObject);
    if (paramArrayOfObject.length < 1)
    {
      paramIdFunctionObject = Undefined.instance;
      label1110:
      paramScriptable1 = ensureScriptableObject(getCompatibleObject(paramContext, paramScriptable1, paramIdFunctionObject));
      if (paramArrayOfObject.length >= 2) {
        break label1160;
      }
    }
    label1160:
    for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[1])
    {
      paramContext = paramScriptable1.getOwnPropertyDescriptor(paramContext, ScriptRuntime.toString(paramIdFunctionObject));
      paramIdFunctionObject = paramContext;
      if (paramContext != null) {
        break;
      }
      return Undefined.instance;
      paramIdFunctionObject = paramArrayOfObject[0];
      break label1110;
    }
    if (paramArrayOfObject.length < 1)
    {
      paramIdFunctionObject = Undefined.instance;
      paramScriptable2 = ensureScriptableObject(paramIdFunctionObject);
      if (paramArrayOfObject.length >= 2) {
        break label1229;
      }
      paramIdFunctionObject = Undefined.instance;
      label1196:
      if (paramArrayOfObject.length >= 3) {
        break label1237;
      }
    }
    label1229:
    label1237:
    for (paramScriptable1 = Undefined.instance;; paramScriptable1 = paramArrayOfObject[2])
    {
      paramScriptable2.defineOwnProperty(paramContext, paramIdFunctionObject, ensureScriptableObject(paramScriptable1));
      return paramScriptable2;
      paramIdFunctionObject = paramArrayOfObject[0];
      break;
      paramIdFunctionObject = paramArrayOfObject[1];
      break label1196;
    }
    if (paramArrayOfObject.length < 1) {}
    for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[0]) {
      return Boolean.valueOf(ensureScriptableObject(paramIdFunctionObject).isExtensible());
    }
    if (paramArrayOfObject.length < 1) {}
    for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[0])
    {
      paramIdFunctionObject = ensureScriptableObject(paramIdFunctionObject);
      paramIdFunctionObject.preventExtensions();
      return paramIdFunctionObject;
    }
    if (paramArrayOfObject.length < 1)
    {
      paramIdFunctionObject = Undefined.instance;
      paramScriptable1 = ensureScriptableObject(paramIdFunctionObject);
      if (paramArrayOfObject.length >= 2) {
        break label1358;
      }
    }
    label1358:
    for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[1])
    {
      paramScriptable1.defineOwnProperties(paramContext, ensureScriptableObject(Context.toObject(paramIdFunctionObject, getParentScope())));
      return paramScriptable1;
      paramIdFunctionObject = paramArrayOfObject[0];
      break;
    }
    if (paramArrayOfObject.length < 1)
    {
      paramIdFunctionObject = Undefined.instance;
      if (paramIdFunctionObject != null) {
        break label1450;
      }
    }
    label1450:
    for (paramIdFunctionObject = null;; paramIdFunctionObject = ensureScriptable(paramIdFunctionObject))
    {
      paramScriptable1 = new NativeObject();
      paramScriptable1.setParentScope(getParentScope());
      paramScriptable1.setPrototype(paramIdFunctionObject);
      if ((paramArrayOfObject.length > 1) && (paramArrayOfObject[1] != Undefined.instance)) {
        paramScriptable1.defineOwnProperties(paramContext, ensureScriptableObject(Context.toObject(paramArrayOfObject[1], getParentScope())));
      }
      return paramScriptable1;
      paramIdFunctionObject = paramArrayOfObject[0];
      break;
    }
    if (paramArrayOfObject.length < 1) {}
    for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[0])
    {
      paramIdFunctionObject = ensureScriptableObject(paramIdFunctionObject);
      if (!paramIdFunctionObject.isExtensible()) {
        break;
      }
      return Boolean.FALSE;
    }
    paramScriptable1 = paramIdFunctionObject.getAllIds();
    j = paramScriptable1.length;
    while (i < j)
    {
      paramScriptable2 = paramIdFunctionObject.getOwnPropertyDescriptor(paramContext, paramScriptable1[i]).get("configurable");
      if (Boolean.TRUE.equals(paramScriptable2)) {
        return Boolean.FALSE;
      }
      i += 1;
    }
    return Boolean.TRUE;
    if (paramArrayOfObject.length < 1) {}
    for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[0])
    {
      paramIdFunctionObject = ensureScriptableObject(paramIdFunctionObject);
      if (!paramIdFunctionObject.isExtensible()) {
        break;
      }
      return Boolean.FALSE;
    }
    paramScriptable1 = paramIdFunctionObject.getAllIds();
    k = paramScriptable1.length;
    i = j;
    while (i < k)
    {
      paramScriptable2 = paramIdFunctionObject.getOwnPropertyDescriptor(paramContext, paramScriptable1[i]);
      if (Boolean.TRUE.equals(paramScriptable2.get("configurable"))) {
        return Boolean.FALSE;
      }
      if ((isDataDescriptor(paramScriptable2)) && (Boolean.TRUE.equals(paramScriptable2.get("writable")))) {
        return Boolean.FALSE;
      }
      i += 1;
    }
    return Boolean.TRUE;
    if (paramArrayOfObject.length < 1) {}
    for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[0])
    {
      paramIdFunctionObject = ensureScriptableObject(paramIdFunctionObject);
      paramScriptable1 = paramIdFunctionObject.getAllIds();
      j = paramScriptable1.length;
      i = 0;
      while (i < j)
      {
        paramScriptable2 = paramScriptable1[i];
        paramArrayOfObject = paramIdFunctionObject.getOwnPropertyDescriptor(paramContext, paramScriptable2);
        if (Boolean.TRUE.equals(paramArrayOfObject.get("configurable")))
        {
          paramArrayOfObject.put("configurable", paramArrayOfObject, Boolean.FALSE);
          paramIdFunctionObject.defineOwnProperty(paramContext, paramScriptable2, paramArrayOfObject, false);
        }
        i += 1;
      }
    }
    paramIdFunctionObject.preventExtensions();
    return paramIdFunctionObject;
    if (paramArrayOfObject.length < 1) {}
    for (paramIdFunctionObject = Undefined.instance;; paramIdFunctionObject = paramArrayOfObject[0])
    {
      paramIdFunctionObject = ensureScriptableObject(paramIdFunctionObject);
      paramScriptable1 = paramIdFunctionObject.getAllIds();
      j = paramScriptable1.length;
      i = 0;
      while (i < j)
      {
        paramScriptable2 = paramScriptable1[i];
        paramArrayOfObject = paramIdFunctionObject.getOwnPropertyDescriptor(paramContext, paramScriptable2);
        if ((isDataDescriptor(paramArrayOfObject)) && (Boolean.TRUE.equals(paramArrayOfObject.get("writable")))) {
          paramArrayOfObject.put("writable", paramArrayOfObject, Boolean.FALSE);
        }
        if (Boolean.TRUE.equals(paramArrayOfObject.get("configurable"))) {
          paramArrayOfObject.put("configurable", paramArrayOfObject, Boolean.FALSE);
        }
        paramIdFunctionObject.defineOwnProperty(paramContext, paramScriptable2, paramArrayOfObject, false);
        i += 1;
      }
    }
    paramIdFunctionObject.preventExtensions();
    return paramIdFunctionObject;
  }
  
  protected void fillConstructorProperties(IdFunctionObject paramIdFunctionObject)
  {
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -1, "getPrototypeOf", 1);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -2, "keys", 1);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -3, "getOwnPropertyNames", 1);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -4, "getOwnPropertyDescriptor", 2);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -5, "defineProperty", 3);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -6, "isExtensible", 1);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -7, "preventExtensions", 1);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -8, "defineProperties", 2);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -9, "create", 2);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -10, "isSealed", 1);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -11, "isFrozen", 1);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -12, "seal", 1);
    addIdFunctionProperty(paramIdFunctionObject, OBJECT_TAG, -13, "freeze", 1);
    super.fillConstructorProperties(paramIdFunctionObject);
  }
  
  protected int findPrototypeId(String paramString)
  {
    String str;
    int i;
    switch (paramString.length())
    {
    case 9: 
    case 10: 
    case 12: 
    case 15: 
    case 17: 
    case 18: 
    case 19: 
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
      i = 4;
      str = "valueOf";
      continue;
      i = paramString.charAt(3);
      if (i == 111)
      {
        i = 8;
        str = "toSource";
      }
      else
      {
        if (i != 116) {
          break;
        }
        str = "toString";
        i = 2;
        continue;
        i = 1;
        str = "constructor";
        continue;
        i = 7;
        str = "isPrototypeOf";
        continue;
        i = paramString.charAt(0);
        if (i == 104)
        {
          i = 5;
          str = "hasOwnProperty";
        }
        else
        {
          if (i != 116) {
            break;
          }
          str = "toLocaleString";
          i = 3;
          continue;
          i = paramString.charAt(2);
          if (i == 100)
          {
            i = paramString.charAt(8);
            if (i == 71)
            {
              i = 9;
              str = "__defineGetter__";
              continue;
            }
            if (i != 83) {
              break;
            }
            i = 10;
            str = "__defineSetter__";
            continue;
          }
          if (i != 108) {
            break;
          }
          i = paramString.charAt(8);
          if (i == 71)
          {
            i = 11;
            str = "__lookupGetter__";
          }
          else
          {
            if (i != 83) {
              break;
            }
            i = 12;
            str = "__lookupSetter__";
            continue;
            i = 6;
            str = "propertyIsEnumerable";
          }
        }
      }
    }
    return i;
  }
  
  public String getClassName()
  {
    return "Object";
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i = 1;
    String str;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      str = "constructor";
    }
    for (;;)
    {
      initPrototypeMethod(OBJECT_TAG, paramInt, str, i);
      return;
      str = "toString";
      i = 0;
      continue;
      str = "toLocaleString";
      i = 0;
      continue;
      str = "valueOf";
      i = 0;
      continue;
      str = "hasOwnProperty";
      continue;
      str = "propertyIsEnumerable";
      continue;
      str = "isPrototypeOf";
      continue;
      str = "toSource";
      i = 0;
      continue;
      str = "__defineGetter__";
      i = 2;
      continue;
      str = "__defineSetter__";
      i = 2;
      continue;
      str = "__lookupGetter__";
      continue;
      str = "__lookupSetter__";
    }
  }
  
  public Set<Object> keySet()
  {
    return new KeySet();
  }
  
  public Object put(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }
  
  public void putAll(Map paramMap)
  {
    throw new UnsupportedOperationException();
  }
  
  public Object remove(Object paramObject)
  {
    Object localObject = get(paramObject);
    if ((paramObject instanceof String)) {
      delete((String)paramObject);
    }
    while (!(paramObject instanceof Number)) {
      return localObject;
    }
    delete(((Number)paramObject).intValue());
    return localObject;
  }
  
  public String toString()
  {
    return ScriptRuntime.defaultObjectToString(this);
  }
  
  public Collection<Object> values()
  {
    return new ValueCollection();
  }
  
  class EntrySet
    extends AbstractSet<Map.Entry<Object, Object>>
  {
    EntrySet() {}
    
    public Iterator<Map.Entry<Object, Object>> iterator()
    {
      new Iterator()
      {
        Object[] ids = NativeObject.this.getIds();
        int index = 0;
        Object key = null;
        
        public boolean hasNext()
        {
          return this.index < this.ids.length;
        }
        
        public Map.Entry<Object, Object> next()
        {
          final Object localObject = this.ids;
          int i = this.index;
          this.index = (i + 1);
          localObject = localObject[i];
          this.key = localObject;
          new Map.Entry()
          {
            public boolean equals(Object paramAnonymous2Object)
            {
              if (!(paramAnonymous2Object instanceof Map.Entry)) {}
              label30:
              do
              {
                do
                {
                  return false;
                  paramAnonymous2Object = (Map.Entry)paramAnonymous2Object;
                  if (localObject != null) {
                    break;
                  }
                } while (((Map.Entry)paramAnonymous2Object).getKey() != null);
                if (this.val$value != null) {
                  break label67;
                }
              } while (((Map.Entry)paramAnonymous2Object).getValue() != null);
              for (;;)
              {
                return true;
                if (!localObject.equals(((Map.Entry)paramAnonymous2Object).getKey())) {
                  break;
                }
                break label30;
                label67:
                if (!this.val$value.equals(((Map.Entry)paramAnonymous2Object).getValue())) {
                  break;
                }
              }
            }
            
            public Object getKey()
            {
              return localObject;
            }
            
            public Object getValue()
            {
              return this.val$value;
            }
            
            public int hashCode()
            {
              int j = 0;
              int i;
              if (localObject == null)
              {
                i = 0;
                if (this.val$value != null) {
                  break label33;
                }
              }
              for (;;)
              {
                return i ^ j;
                i = localObject.hashCode();
                break;
                label33:
                j = this.val$value.hashCode();
              }
            }
            
            public Object setValue(Object paramAnonymous2Object)
            {
              throw new UnsupportedOperationException();
            }
            
            public String toString()
            {
              return localObject + "=" + this.val$value;
            }
          };
        }
        
        public void remove()
        {
          if (this.key == null) {
            throw new IllegalStateException();
          }
          NativeObject.this.remove(this.key);
          this.key = null;
        }
      };
    }
    
    public int size()
    {
      return NativeObject.this.size();
    }
  }
  
  class KeySet
    extends AbstractSet<Object>
  {
    KeySet() {}
    
    public boolean contains(Object paramObject)
    {
      return NativeObject.this.containsKey(paramObject);
    }
    
    public Iterator<Object> iterator()
    {
      new Iterator()
      {
        Object[] ids = NativeObject.this.getIds();
        int index = 0;
        Object key;
        
        public boolean hasNext()
        {
          return this.index < this.ids.length;
        }
        
        public Object next()
        {
          try
          {
            Object localObject = this.ids;
            int i = this.index;
            this.index = (i + 1);
            localObject = localObject[i];
            this.key = localObject;
            return localObject;
          }
          catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
          {
            this.key = null;
            throw new NoSuchElementException();
          }
        }
        
        public void remove()
        {
          if (this.key == null) {
            throw new IllegalStateException();
          }
          NativeObject.this.remove(this.key);
          this.key = null;
        }
      };
    }
    
    public int size()
    {
      return NativeObject.this.size();
    }
  }
  
  class ValueCollection
    extends AbstractCollection<Object>
  {
    ValueCollection() {}
    
    public Iterator<Object> iterator()
    {
      new Iterator()
      {
        Object[] ids = NativeObject.this.getIds();
        int index = 0;
        Object key;
        
        public boolean hasNext()
        {
          return this.index < this.ids.length;
        }
        
        public Object next()
        {
          NativeObject localNativeObject = NativeObject.this;
          Object localObject = this.ids;
          int i = this.index;
          this.index = (i + 1);
          localObject = localObject[i];
          this.key = localObject;
          return localNativeObject.get(localObject);
        }
        
        public void remove()
        {
          if (this.key == null) {
            throw new IllegalStateException();
          }
          NativeObject.this.remove(this.key);
          this.key = null;
        }
      };
    }
    
    public int size()
    {
      return NativeObject.this.size();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */