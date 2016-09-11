package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class IdScriptableObject
  extends ScriptableObject
  implements IdFunctionCall
{
  private transient PrototypeValues prototypeValues;
  
  public IdScriptableObject() {}
  
  public IdScriptableObject(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    super(paramScriptable1, paramScriptable2);
  }
  
  private ScriptableObject getBuiltInDescriptor(String paramString)
  {
    Scriptable localScriptable = getParentScope();
    Object localObject = localScriptable;
    if (localScriptable == null) {
      localObject = this;
    }
    int i = findInstanceIdInfo(paramString);
    if (i != 0) {
      return buildDataDescriptor((Scriptable)localObject, getInstanceIdValue(0xFFFF & i), i >>> 16);
    }
    if (this.prototypeValues != null)
    {
      i = this.prototypeValues.findId(paramString);
      if (i != 0) {
        return buildDataDescriptor((Scriptable)localObject, this.prototypeValues.get(i), this.prototypeValues.getAttributes(i));
      }
    }
    return null;
  }
  
  protected static EcmaError incompatibleCallError(IdFunctionObject paramIdFunctionObject)
  {
    throw ScriptRuntime.typeError1("msg.incompat.call", paramIdFunctionObject.getFunctionName());
  }
  
  protected static int instanceIdInfo(int paramInt1, int paramInt2)
  {
    return paramInt1 << 16 | paramInt2;
  }
  
  private IdFunctionObject newIdFunction(Object paramObject, int paramInt1, String paramString, int paramInt2, Scriptable paramScriptable)
  {
    if (Context.getContext().getLanguageVersion() < 200) {}
    for (paramObject = new IdFunctionObject(this, paramObject, paramInt1, paramString, paramInt2, paramScriptable);; paramObject = new IdFunctionObjectES6(this, paramObject, paramInt1, paramString, paramInt2, paramScriptable))
    {
      if (isSealed()) {
        ((IdFunctionObject)paramObject).sealObject();
      }
      return (IdFunctionObject)paramObject;
    }
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    int i = paramObjectInputStream.readInt();
    if (i != 0) {
      activatePrototypeMap(i);
    }
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    int i = 0;
    if (this.prototypeValues != null) {
      i = this.prototypeValues.getMaxId();
    }
    paramObjectOutputStream.writeInt(i);
  }
  
  public final void activatePrototypeMap(int paramInt)
  {
    PrototypeValues localPrototypeValues1 = new PrototypeValues(this, paramInt);
    try
    {
      if (this.prototypeValues != null) {
        throw new IllegalStateException();
      }
    }
    finally {}
    this.prototypeValues = localPrototypeValues2;
  }
  
  protected void addIdFunctionProperty(Scriptable paramScriptable, Object paramObject, int paramInt1, String paramString, int paramInt2)
  {
    newIdFunction(paramObject, paramInt1, paramString, paramInt2, ScriptableObject.getTopLevelScope(paramScriptable)).addAsProperty(paramScriptable);
  }
  
  protected final Object defaultGet(String paramString)
  {
    return super.get(paramString, this);
  }
  
  protected final boolean defaultHas(String paramString)
  {
    return super.has(paramString, this);
  }
  
  protected final void defaultPut(String paramString, Object paramObject)
  {
    super.put(paramString, this, paramObject);
  }
  
  public void defineOwnProperty(Context paramContext, Object paramObject, ScriptableObject paramScriptableObject)
  {
    String str;
    int i;
    if ((paramObject instanceof String))
    {
      str = (String)paramObject;
      j = findInstanceIdInfo(str);
      if (j != 0)
      {
        i = 0xFFFF & j;
        if (!isAccessorDescriptor(paramScriptableObject)) {
          break label95;
        }
        delete(i);
      }
      if (this.prototypeValues != null)
      {
        i = this.prototypeValues.findId(str);
        if (i != 0)
        {
          if (!isAccessorDescriptor(paramScriptableObject)) {
            break label176;
          }
          this.prototypeValues.delete(i);
        }
      }
    }
    super.defineOwnProperty(paramContext, paramObject, paramScriptableObject);
    return;
    label95:
    checkPropertyDefinition(paramScriptableObject);
    checkPropertyChange(str, getOwnPropertyDescriptor(paramContext, paramObject), paramScriptableObject);
    j >>>= 16;
    paramContext = getProperty(paramScriptableObject, "value");
    if ((paramContext != NOT_FOUND) && ((j & 0x1) == 0) && (!sameValue(paramContext, getInstanceIdValue(i)))) {
      setInstanceIdValue(i, paramContext);
    }
    setAttributes(str, applyDescriptorToAttributeBitset(j, paramScriptableObject));
    return;
    label176:
    checkPropertyDefinition(paramScriptableObject);
    checkPropertyChange(str, getOwnPropertyDescriptor(paramContext, paramObject), paramScriptableObject);
    int j = this.prototypeValues.getAttributes(i);
    paramContext = getProperty(paramScriptableObject, "value");
    if ((paramContext != NOT_FOUND) && ((j & 0x1) == 0) && (!sameValue(paramContext, this.prototypeValues.get(i)))) {
      this.prototypeValues.set(i, this, paramContext);
    }
    this.prototypeValues.setAttributes(i, applyDescriptorToAttributeBitset(j, paramScriptableObject));
  }
  
  public void delete(String paramString)
  {
    int i = findInstanceIdInfo(paramString);
    if ((i != 0) && (!isSealed())) {
      if ((i >>> 16 & 0x4) != 0)
      {
        if (Context.getContext().isStrictMode()) {
          throw ScriptRuntime.typeError1("msg.delete.property.with.configurable.false", paramString);
        }
      }
      else {
        setInstanceIdValue(i & 0xFFFF, NOT_FOUND);
      }
    }
    do
    {
      return;
      if (this.prototypeValues == null) {
        break;
      }
      i = this.prototypeValues.findId(paramString);
      if (i == 0) {
        break;
      }
    } while (isSealed());
    this.prototypeValues.delete(i);
    return;
    super.delete(paramString);
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    throw paramIdFunctionObject.unknown();
  }
  
  public final IdFunctionObject exportAsJSClass(int paramInt, Scriptable paramScriptable, boolean paramBoolean)
  {
    if ((paramScriptable != this) && (paramScriptable != null))
    {
      setParentScope(paramScriptable);
      setPrototype(getObjectPrototype(paramScriptable));
    }
    activatePrototypeMap(paramInt);
    paramScriptable = this.prototypeValues.createPrecachedConstructor();
    if (paramBoolean) {
      sealObject();
    }
    fillConstructorProperties(paramScriptable);
    if (paramBoolean) {
      paramScriptable.sealObject();
    }
    paramScriptable.exportAsScopeProperty();
    return paramScriptable;
  }
  
  protected void fillConstructorProperties(IdFunctionObject paramIdFunctionObject) {}
  
  protected int findInstanceIdInfo(String paramString)
  {
    return 0;
  }
  
  protected int findPrototypeId(String paramString)
  {
    throw new IllegalStateException(paramString);
  }
  
  public Object get(String paramString, Scriptable paramScriptable)
  {
    paramScriptable = super.get(paramString, paramScriptable);
    if (paramScriptable != NOT_FOUND) {}
    do
    {
      Object localObject;
      do
      {
        return paramScriptable;
        i = findInstanceIdInfo(paramString);
        if (i == 0) {
          break;
        }
        localObject = getInstanceIdValue(i & 0xFFFF);
        paramScriptable = (Scriptable)localObject;
      } while (localObject != NOT_FOUND);
      if (this.prototypeValues == null) {
        break;
      }
      int i = this.prototypeValues.findId(paramString);
      if (i == 0) {
        break;
      }
      paramString = this.prototypeValues.get(i);
      paramScriptable = paramString;
    } while (paramString != NOT_FOUND);
    return NOT_FOUND;
  }
  
  public int getAttributes(String paramString)
  {
    int i = findInstanceIdInfo(paramString);
    if (i != 0) {
      return i >>> 16;
    }
    if (this.prototypeValues != null)
    {
      i = this.prototypeValues.findId(paramString);
      if (i != 0) {
        return this.prototypeValues.getAttributes(i);
      }
    }
    return super.getAttributes(paramString);
  }
  
  Object[] getIds(boolean paramBoolean)
  {
    Object[] arrayOfObject1 = super.getIds(paramBoolean);
    Object[] arrayOfObject2 = arrayOfObject1;
    if (this.prototypeValues != null) {
      arrayOfObject2 = this.prototypeValues.getNames(paramBoolean, arrayOfObject1);
    }
    int j = getMaxInstanceId();
    Object localObject = arrayOfObject2;
    int i;
    int k;
    if (j != 0)
    {
      arrayOfObject1 = null;
      i = 0;
      if (j != 0)
      {
        localObject = getInstanceIdName(j);
        k = findInstanceIdInfo((String)localObject);
        if (k == 0) {
          break label203;
        }
        k >>>= 16;
        if (((k & 0x4) != 0) || (NOT_FOUND != getInstanceIdValue(j))) {}
      }
    }
    label203:
    for (;;)
    {
      j -= 1;
      break;
      if ((paramBoolean) || ((k & 0x2) == 0))
      {
        if (i == 0) {
          arrayOfObject1 = new Object[j];
        }
        arrayOfObject1[i] = localObject;
        i += 1;
        continue;
        localObject = arrayOfObject2;
        if (i != 0)
        {
          if ((arrayOfObject2.length == 0) && (arrayOfObject1.length == i)) {
            localObject = arrayOfObject1;
          }
        }
        else {
          return (Object[])localObject;
        }
        localObject = new Object[arrayOfObject2.length + i];
        System.arraycopy(arrayOfObject2, 0, localObject, 0, arrayOfObject2.length);
        System.arraycopy(arrayOfObject1, 0, localObject, arrayOfObject2.length, i);
        return (Object[])localObject;
      }
    }
  }
  
  protected String getInstanceIdName(int paramInt)
  {
    throw new IllegalArgumentException(String.valueOf(paramInt));
  }
  
  protected Object getInstanceIdValue(int paramInt)
  {
    throw new IllegalStateException(String.valueOf(paramInt));
  }
  
  protected int getMaxInstanceId()
  {
    return 0;
  }
  
  protected ScriptableObject getOwnPropertyDescriptor(Context paramContext, Object paramObject)
  {
    ScriptableObject localScriptableObject = super.getOwnPropertyDescriptor(paramContext, paramObject);
    paramContext = localScriptableObject;
    if (localScriptableObject == null)
    {
      paramContext = localScriptableObject;
      if ((paramObject instanceof String)) {
        paramContext = getBuiltInDescriptor((String)paramObject);
      }
    }
    return paramContext;
  }
  
  public boolean has(String paramString, Scriptable paramScriptable)
  {
    int i = findInstanceIdInfo(paramString);
    if (i != 0)
    {
      if ((i >>> 16 & 0x4) != 0) {}
      while (NOT_FOUND != getInstanceIdValue(i & 0xFFFF)) {
        return true;
      }
      return false;
    }
    if (this.prototypeValues != null)
    {
      i = this.prototypeValues.findId(paramString);
      if (i != 0) {
        return this.prototypeValues.has(i);
      }
    }
    return super.has(paramString, paramScriptable);
  }
  
  public final boolean hasPrototypeMap()
  {
    return this.prototypeValues != null;
  }
  
  public final void initPrototypeConstructor(IdFunctionObject paramIdFunctionObject)
  {
    int i = this.prototypeValues.constructorId;
    if (i == 0) {
      throw new IllegalStateException();
    }
    if (paramIdFunctionObject.methodId() != i) {
      throw new IllegalArgumentException();
    }
    if (isSealed()) {
      paramIdFunctionObject.sealObject();
    }
    this.prototypeValues.initValue(i, "constructor", paramIdFunctionObject, 2);
  }
  
  protected void initPrototypeId(int paramInt)
  {
    throw new IllegalStateException(String.valueOf(paramInt));
  }
  
  public final IdFunctionObject initPrototypeMethod(Object paramObject, int paramInt1, String paramString, int paramInt2)
  {
    return initPrototypeMethod(paramObject, paramInt1, paramString, paramString, paramInt2);
  }
  
  public final IdFunctionObject initPrototypeMethod(Object paramObject, int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    Scriptable localScriptable = ScriptableObject.getTopLevelScope(this);
    if (paramString2 != null) {}
    for (;;)
    {
      paramObject = newIdFunction(paramObject, paramInt1, paramString2, paramInt2, localScriptable);
      this.prototypeValues.initValue(paramInt1, paramString1, paramObject, 2);
      return (IdFunctionObject)paramObject;
      paramString2 = paramString1;
    }
  }
  
  public final void initPrototypeValue(int paramInt1, String paramString, Object paramObject, int paramInt2)
  {
    this.prototypeValues.initValue(paramInt1, paramString, paramObject, paramInt2);
  }
  
  public void put(String paramString, Scriptable paramScriptable, Object paramObject)
  {
    int i = findInstanceIdInfo(paramString);
    if (i != 0)
    {
      if ((paramScriptable == this) && (isSealed())) {
        throw Context.reportRuntimeError1("msg.modify.sealed", paramString);
      }
      if ((i >>> 16 & 0x1) == 0)
      {
        if (paramScriptable == this) {
          setInstanceIdValue(i & 0xFFFF, paramObject);
        }
      }
      else {
        return;
      }
      paramScriptable.put(paramString, paramScriptable, paramObject);
      return;
    }
    if (this.prototypeValues != null)
    {
      i = this.prototypeValues.findId(paramString);
      if (i != 0)
      {
        if ((paramScriptable == this) && (isSealed())) {
          throw Context.reportRuntimeError1("msg.modify.sealed", paramString);
        }
        this.prototypeValues.set(i, paramScriptable, paramObject);
        return;
      }
    }
    super.put(paramString, paramScriptable, paramObject);
  }
  
  public void setAttributes(String paramString, int paramInt)
  {
    ScriptableObject.checkValidAttributes(paramInt);
    int i = findInstanceIdInfo(paramString);
    if (i != 0)
    {
      if (paramInt != i >>> 16) {
        setInstanceIdAttributes(0xFFFF & i, paramInt);
      }
      return;
    }
    if (this.prototypeValues != null)
    {
      i = this.prototypeValues.findId(paramString);
      if (i != 0)
      {
        this.prototypeValues.setAttributes(i, paramInt);
        return;
      }
    }
    super.setAttributes(paramString, paramInt);
  }
  
  protected void setInstanceIdAttributes(int paramInt1, int paramInt2)
  {
    throw ScriptRuntime.constructError("InternalError", "Changing attributes not supported for " + getClassName() + " " + getInstanceIdName(paramInt1) + " property");
  }
  
  protected void setInstanceIdValue(int paramInt, Object paramObject)
  {
    throw new IllegalStateException(String.valueOf(paramInt));
  }
  
  private static final class PrototypeValues
    implements Serializable
  {
    private static final int NAME_SLOT = 1;
    private static final int SLOT_SPAN = 2;
    static final long serialVersionUID = 3038645279153854371L;
    private short[] attributeArray;
    private IdFunctionObject constructor;
    private short constructorAttrs;
    int constructorId;
    private int maxId;
    private IdScriptableObject obj;
    private Object[] valueArray;
    
    PrototypeValues(IdScriptableObject paramIdScriptableObject, int paramInt)
    {
      if (paramIdScriptableObject == null) {
        throw new IllegalArgumentException();
      }
      if (paramInt < 1) {
        throw new IllegalArgumentException();
      }
      this.obj = paramIdScriptableObject;
      this.maxId = paramInt;
    }
    
    private Object ensureId(int paramInt)
    {
      Object[] arrayOfObject3 = this.valueArray;
      Object[] arrayOfObject1 = arrayOfObject3;
      if (arrayOfObject3 == null) {}
      for (;;)
      {
        try
        {
          arrayOfObject3 = this.valueArray;
          arrayOfObject1 = arrayOfObject3;
          if (arrayOfObject3 == null)
          {
            arrayOfObject1 = new Object[this.maxId * 2];
            this.valueArray = arrayOfObject1;
            this.attributeArray = new short[this.maxId];
          }
          int i = (paramInt - 1) * 2;
          arrayOfObject3 = arrayOfObject1[i];
          if (arrayOfObject3 != null) {
            break;
          }
          if (paramInt == this.constructorId)
          {
            initSlot(this.constructorId, "constructor", this.constructor, this.constructorAttrs);
            this.constructor = null;
            arrayOfObject3 = arrayOfObject1[i];
            arrayOfObject1 = arrayOfObject3;
            if (arrayOfObject3 != null) {
              return ???;
            }
            throw new IllegalStateException(this.obj.getClass().getName() + ".initPrototypeId(int id) " + "did not initialize id=" + paramInt);
          }
        }
        finally {}
        this.obj.initPrototypeId(paramInt);
      }
      Object[] arrayOfObject2 = arrayOfObject3;
      return arrayOfObject2;
    }
    
    private void initSlot(int paramInt1, String paramString, Object paramObject, int paramInt2)
    {
      Object[] arrayOfObject = this.valueArray;
      if (arrayOfObject == null) {
        throw new IllegalStateException();
      }
      Object localObject = paramObject;
      if (paramObject == null) {
        localObject = UniqueTag.NULL_VALUE;
      }
      int i = (paramInt1 - 1) * 2;
      if (arrayOfObject[i] == null)
      {
        arrayOfObject[i] = localObject;
        arrayOfObject[(i + 1)] = paramString;
      }
      do
      {
        try
        {
          this.attributeArray[(paramInt1 - 1)] = ((short)paramInt2);
          return;
        }
        finally {}
      } while (paramString.equals(arrayOfObject[(i + 1)]));
      throw new IllegalStateException();
    }
    
    final IdFunctionObject createPrecachedConstructor()
    {
      if (this.constructorId != 0) {
        throw new IllegalStateException();
      }
      this.constructorId = this.obj.findPrototypeId("constructor");
      if (this.constructorId == 0) {
        throw new IllegalStateException("No id for constructor property");
      }
      this.obj.initPrototypeId(this.constructorId);
      if (this.constructor == null) {
        throw new IllegalStateException(this.obj.getClass().getName() + ".initPrototypeId() did not " + "initialize id=" + this.constructorId);
      }
      this.constructor.initFunction(this.obj.getClassName(), ScriptableObject.getTopLevelScope(this.obj));
      this.constructor.markAsConstructor(this.obj);
      return this.constructor;
    }
    
    final void delete(int paramInt)
    {
      ensureId(paramInt);
      if ((this.attributeArray[(paramInt - 1)] & 0x4) != 0)
      {
        if (Context.getContext().isStrictMode()) {
          throw ScriptRuntime.typeError1("msg.delete.property.with.configurable.false", (String)this.valueArray[((paramInt - 1) * 2 + 1)]);
        }
      }
      else {
        try
        {
          this.valueArray[((paramInt - 1) * 2)] = Scriptable.NOT_FOUND;
          this.attributeArray[(paramInt - 1)] = 0;
          return;
        }
        finally {}
      }
    }
    
    final int findId(String paramString)
    {
      return this.obj.findPrototypeId(paramString);
    }
    
    final Object get(int paramInt)
    {
      Object localObject2 = ensureId(paramInt);
      Object localObject1 = localObject2;
      if (localObject2 == UniqueTag.NULL_VALUE) {
        localObject1 = null;
      }
      return localObject1;
    }
    
    final int getAttributes(int paramInt)
    {
      ensureId(paramInt);
      return this.attributeArray[(paramInt - 1)];
    }
    
    final int getMaxId()
    {
      return this.maxId;
    }
    
    final Object[] getNames(boolean paramBoolean, Object[] paramArrayOfObject)
    {
      Object localObject1 = null;
      int j = 1;
      int i = 0;
      Object localObject2;
      if (j <= this.maxId)
      {
        localObject2 = ensureId(j);
        if (((!paramBoolean) && ((this.attributeArray[(j - 1)] & 0x2) != 0)) || (localObject2 == Scriptable.NOT_FOUND)) {
          break label188;
        }
        String str = (String)this.valueArray[((j - 1) * 2 + 1)];
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new Object[this.maxId];
        }
        localObject2[i] = str;
        i += 1;
        localObject1 = localObject2;
      }
      label188:
      for (;;)
      {
        j += 1;
        break;
        if (i == 0) {
          return paramArrayOfObject;
        }
        if ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0))
        {
          paramArrayOfObject = (Object[])localObject1;
          if (i != localObject1.length)
          {
            paramArrayOfObject = new Object[i];
            System.arraycopy(localObject1, 0, paramArrayOfObject, 0, i);
          }
          return paramArrayOfObject;
        }
        j = paramArrayOfObject.length;
        localObject2 = new Object[j + i];
        System.arraycopy(paramArrayOfObject, 0, localObject2, 0, j);
        System.arraycopy(localObject1, 0, localObject2, j, i);
        return (Object[])localObject2;
      }
    }
    
    final boolean has(int paramInt)
    {
      Object localObject = this.valueArray;
      if (localObject == null) {}
      do
      {
        return true;
        localObject = localObject[((paramInt - 1) * 2)];
      } while ((localObject == null) || (localObject != Scriptable.NOT_FOUND));
      return false;
    }
    
    final void initValue(int paramInt1, String paramString, Object paramObject, int paramInt2)
    {
      if ((1 > paramInt1) || (paramInt1 > this.maxId)) {
        throw new IllegalArgumentException();
      }
      if (paramString == null) {
        throw new IllegalArgumentException();
      }
      if (paramObject == Scriptable.NOT_FOUND) {
        throw new IllegalArgumentException();
      }
      ScriptableObject.checkValidAttributes(paramInt2);
      if (this.obj.findPrototypeId(paramString) != paramInt1) {
        throw new IllegalArgumentException(paramString);
      }
      if (paramInt1 == this.constructorId)
      {
        if (!(paramObject instanceof IdFunctionObject)) {
          throw new IllegalArgumentException("consructor should be initialized with IdFunctionObject");
        }
        this.constructor = ((IdFunctionObject)paramObject);
        this.constructorAttrs = ((short)paramInt2);
        return;
      }
      initSlot(paramInt1, paramString, paramObject, paramInt2);
    }
    
    final void set(int paramInt, Scriptable paramScriptable, Object paramObject)
    {
      if (paramObject == Scriptable.NOT_FOUND) {
        throw new IllegalArgumentException();
      }
      ensureId(paramInt);
      if ((this.attributeArray[(paramInt - 1)] & 0x1) == 0)
      {
        if (paramScriptable == this.obj)
        {
          paramScriptable = (Scriptable)paramObject;
          if (paramObject == null) {
            paramScriptable = UniqueTag.NULL_VALUE;
          }
          try
          {
            this.valueArray[((paramInt - 1) * 2)] = paramScriptable;
            return;
          }
          finally {}
        }
        paramScriptable.put((String)this.valueArray[((paramInt - 1) * 2 + 1)], paramScriptable, paramObject);
      }
    }
    
    final void setAttributes(int paramInt1, int paramInt2)
    {
      ScriptableObject.checkValidAttributes(paramInt2);
      ensureId(paramInt1);
      try
      {
        this.attributeArray[(paramInt1 - 1)] = ((short)paramInt2);
        return;
      }
      finally {}
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\IdScriptableObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */