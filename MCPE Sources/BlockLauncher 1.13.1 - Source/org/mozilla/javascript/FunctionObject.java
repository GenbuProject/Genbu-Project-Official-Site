package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class FunctionObject
  extends BaseFunction
{
  public static final int JAVA_BOOLEAN_TYPE = 3;
  public static final int JAVA_DOUBLE_TYPE = 4;
  public static final int JAVA_INT_TYPE = 2;
  public static final int JAVA_OBJECT_TYPE = 6;
  public static final int JAVA_SCRIPTABLE_TYPE = 5;
  public static final int JAVA_STRING_TYPE = 1;
  public static final int JAVA_UNSUPPORTED_TYPE = 0;
  private static final short VARARGS_CTOR = -2;
  private static final short VARARGS_METHOD = -1;
  private static boolean sawSecurityException = false;
  static final long serialVersionUID = -5332312783643935019L;
  private String functionName;
  private transient boolean hasVoidReturn;
  private boolean isStatic;
  MemberBox member;
  private int parmsLength;
  private transient int returnTypeTag;
  private transient byte[] typeTags;
  
  public FunctionObject(String paramString, Member paramMember, Scriptable paramScriptable)
  {
    if ((paramMember instanceof Constructor)) {
      this.member = new MemberBox((Constructor)paramMember);
    }
    int j;
    for (this.isStatic = true;; this.isStatic = this.member.isStatic())
    {
      paramMember = this.member.getName();
      this.functionName = paramString;
      paramString = this.member.argTypes;
      j = paramString.length;
      if ((j != 4) || ((!paramString[1].isArray()) && (!paramString[2].isArray()))) {
        break label280;
      }
      if (!paramString[1].isArray()) {
        break label219;
      }
      if ((this.isStatic) && (paramString[0] == ScriptRuntime.ContextClass) && (paramString[1].getComponentType() == ScriptRuntime.ObjectClass) && (paramString[2] == ScriptRuntime.FunctionClass) && (paramString[3] == Boolean.TYPE)) {
        break;
      }
      throw Context.reportRuntimeError1("msg.varargs.ctor", paramMember);
      this.member = new MemberBox((Method)paramMember);
    }
    this.parmsLength = -2;
    if (this.member.isMethod())
    {
      paramString = this.member.method().getReturnType();
      if (paramString == Void.TYPE) {
        this.hasVoidReturn = true;
      }
    }
    label219:
    label280:
    do
    {
      for (;;)
      {
        ScriptRuntime.setFunctionProtoAndParent(this, paramScriptable);
        return;
        if ((!this.isStatic) || (paramString[0] != ScriptRuntime.ContextClass) || (paramString[1] != ScriptRuntime.ScriptableClass) || (paramString[2].getComponentType() != ScriptRuntime.ObjectClass) || (paramString[3] != ScriptRuntime.FunctionClass)) {
          throw Context.reportRuntimeError1("msg.varargs.fun", paramMember);
        }
        this.parmsLength = -1;
        break;
        this.parmsLength = j;
        if (j <= 0) {
          break;
        }
        this.typeTags = new byte[j];
        while (i != j)
        {
          int k = getTypeTag(paramString[i]);
          if (k == 0) {
            throw Context.reportRuntimeError2("msg.bad.parms", paramString[i].getName(), paramMember);
          }
          this.typeTags[i] = ((byte)k);
          i += 1;
        }
        break;
        this.returnTypeTag = getTypeTag(paramString);
      }
      paramString = this.member.getDeclaringClass();
    } while (ScriptRuntime.ScriptableClass.isAssignableFrom(paramString));
    throw Context.reportRuntimeError1("msg.bad.ctor.return", paramString.getName());
  }
  
  public static Object convertArg(Context paramContext, Scriptable paramScriptable, Object paramObject, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException();
    case 1: 
      if (!(paramObject instanceof String)) {
        break;
      }
    case 6: 
    case 2: 
    case 3: 
    case 4: 
      do
      {
        do
        {
          do
          {
            return paramObject;
            return ScriptRuntime.toString(paramObject);
          } while ((paramObject instanceof Integer));
          return Integer.valueOf(ScriptRuntime.toInt32(paramObject));
        } while ((paramObject instanceof Boolean));
        if (ScriptRuntime.toBoolean(paramObject)) {}
        for (paramContext = Boolean.TRUE;; paramContext = Boolean.FALSE) {
          return paramContext;
        }
      } while ((paramObject instanceof Double));
      return new Double(ScriptRuntime.toNumber(paramObject));
    }
    return ScriptRuntime.toObjectOrNull(paramContext, paramObject, paramScriptable);
  }
  
  @Deprecated
  public static Object convertArg(Context paramContext, Scriptable paramScriptable, Object paramObject, Class<?> paramClass)
  {
    int i = getTypeTag(paramClass);
    if (i == 0) {
      throw Context.reportRuntimeError1("msg.cant.convert", paramClass.getName());
    }
    return convertArg(paramContext, paramScriptable, paramObject, i);
  }
  
  static Method findSingleMethod(Method[] paramArrayOfMethod, String paramString)
  {
    Object localObject1 = null;
    int j = paramArrayOfMethod.length;
    int i = 0;
    while (i != j)
    {
      Method localMethod = paramArrayOfMethod[i];
      Object localObject2;
      if ((localMethod != null) && (paramString.equals(localMethod.getName())))
      {
        localObject2 = localMethod;
        if (localObject1 != null) {
          throw Context.reportRuntimeError2("msg.no.overload", paramString, localMethod.getDeclaringClass().getName());
        }
      }
      else
      {
        localObject2 = localObject1;
      }
      i += 1;
      localObject1 = localObject2;
    }
    return (Method)localObject1;
  }
  
  static Method[] getMethodList(Class<?> paramClass)
  {
    int k = 0;
    try
    {
      if (!sawSecurityException)
      {
        Method[] arrayOfMethod1 = paramClass.getDeclaredMethods();
        arrayOfMethod2 = arrayOfMethod1;
        if (arrayOfMethod1 == null) {
          arrayOfMethod2 = paramClass.getMethods();
        }
        i = 0;
        j = 0;
        for (;;)
        {
          if (i >= arrayOfMethod2.length) {
            break label101;
          }
          if (!sawSecurityException) {
            break;
          }
          if (arrayOfMethod2[i].getDeclaringClass() == paramClass) {
            break label94;
          }
          arrayOfMethod2[i] = null;
          i += 1;
        }
      }
    }
    catch (SecurityException localSecurityException)
    {
      Method[] arrayOfMethod2;
      for (;;)
      {
        sawSecurityException = true;
        Object localObject = null;
        continue;
        if (Modifier.isPublic(arrayOfMethod2[i].getModifiers())) {
          label94:
          j += 1;
        }
      }
      label101:
      paramClass = new Method[j];
      int j = 0;
      int i = k;
      while (i < arrayOfMethod2.length)
      {
        k = j;
        if (arrayOfMethod2[i] != null)
        {
          paramClass[j] = arrayOfMethod2[i];
          k = j + 1;
        }
        i += 1;
        j = k;
      }
    }
    return paramClass;
  }
  
  public static int getTypeTag(Class<?> paramClass)
  {
    if (paramClass == ScriptRuntime.StringClass) {
      return 1;
    }
    if ((paramClass == ScriptRuntime.IntegerClass) || (paramClass == Integer.TYPE)) {
      return 2;
    }
    if ((paramClass == ScriptRuntime.BooleanClass) || (paramClass == Boolean.TYPE)) {
      return 3;
    }
    if ((paramClass == ScriptRuntime.DoubleClass) || (paramClass == Double.TYPE)) {
      return 4;
    }
    if (ScriptRuntime.ScriptableClass.isAssignableFrom(paramClass)) {
      return 5;
    }
    if (paramClass == ScriptRuntime.ObjectClass) {
      return 6;
    }
    return 0;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    if (this.parmsLength > 0)
    {
      paramObjectInputStream = this.member.argTypes;
      this.typeTags = new byte[this.parmsLength];
      int i = 0;
      while (i != this.parmsLength)
      {
        this.typeTags[i] = ((byte)getTypeTag(paramObjectInputStream[i]));
        i += 1;
      }
    }
    if (this.member.isMethod())
    {
      paramObjectInputStream = this.member.method().getReturnType();
      if (paramObjectInputStream == Void.TYPE) {
        this.hasVoidReturn = true;
      }
    }
    else
    {
      return;
    }
    this.returnTypeTag = getTypeTag(paramObjectInputStream);
  }
  
  public void addAsConstructor(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    initAsConstructor(paramScriptable1, paramScriptable2);
    defineProperty(paramScriptable1, paramScriptable2.getClassName(), this, 2);
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    int j = 0;
    int k = paramArrayOfObject.length;
    int i = 0;
    while (i < k)
    {
      if ((paramArrayOfObject[i] instanceof ConsString)) {
        paramArrayOfObject[i] = paramArrayOfObject[i].toString();
      }
      i += 1;
    }
    label119:
    label129:
    Object localObject1;
    if (this.parmsLength < 0)
    {
      if (this.parmsLength == -1)
      {
        paramScriptable2 = this.member.invoke(null, new Object[] { paramContext, paramScriptable2, paramArrayOfObject, this });
        i = 1;
      }
      for (;;)
      {
        paramArrayOfObject = paramScriptable2;
        if (i != 0)
        {
          if (!this.hasVoidReturn) {
            break;
          }
          paramArrayOfObject = Undefined.instance;
        }
        return paramArrayOfObject;
        if (paramScriptable2 == null)
        {
          i = 1;
          if (i == 0) {
            break label198;
          }
        }
        label198:
        for (paramScriptable2 = Boolean.TRUE;; paramScriptable2 = Boolean.FALSE)
        {
          localObject1 = new Object[4];
          localObject1[0] = paramContext;
          localObject1[1] = paramArrayOfObject;
          localObject1[2] = this;
          localObject1[3] = paramScriptable2;
          if (!this.member.isCtor()) {
            break label205;
          }
          paramScriptable2 = this.member.newInstance((Object[])localObject1);
          i = j;
          break;
          i = 0;
          break label129;
        }
        label205:
        paramScriptable2 = this.member.invoke(null, (Object[])localObject1);
        i = j;
      }
    }
    Scriptable localScriptable = paramScriptable2;
    Object localObject2;
    boolean bool2;
    if (!this.isStatic)
    {
      localObject2 = this.member.getDeclaringClass();
      localScriptable = paramScriptable2;
      if (!((Class)localObject2).isInstance(paramScriptable2))
      {
        if (paramScriptable2 != paramScriptable1) {
          break label563;
        }
        localObject1 = getParentScope();
        if (paramScriptable1 == localObject1) {
          break label563;
        }
        bool2 = ((Class)localObject2).isInstance(localObject1);
        bool1 = bool2;
        if (bool2) {
          paramScriptable2 = (Scriptable)localObject1;
        }
      }
    }
    label560:
    label563:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      localScriptable = paramScriptable2;
      if (!bool1) {
        throw ScriptRuntime.typeError1("msg.incompat.call", this.functionName);
      }
      if (this.parmsLength == k)
      {
        i = 0;
        for (paramScriptable2 = paramArrayOfObject;; paramScriptable2 = (Scriptable)localObject1)
        {
          localObject1 = paramScriptable2;
          if (i == this.parmsLength) {
            break;
          }
          Object localObject3 = paramArrayOfObject[i];
          localObject2 = convertArg(paramContext, paramScriptable1, localObject3, this.typeTags[i]);
          localObject1 = paramScriptable2;
          if (localObject3 != localObject2)
          {
            localObject1 = paramScriptable2;
            if (paramScriptable2 == paramArrayOfObject) {
              localObject1 = (Object[])paramArrayOfObject.clone();
            }
            localObject1[i] = localObject2;
          }
          i += 1;
        }
      }
      if (this.parmsLength == 0) {
        localObject1 = ScriptRuntime.emptyArgs;
      }
      for (;;)
      {
        if (this.member.isMethod())
        {
          paramScriptable2 = this.member.invoke(localScriptable, (Object[])localObject1);
          i = 1;
          break;
          localObject1 = new Object[this.parmsLength];
          i = 0;
          if (i == this.parmsLength) {
            break label560;
          }
          if (i < k) {}
          for (paramScriptable2 = paramArrayOfObject[i];; paramScriptable2 = Undefined.instance)
          {
            localObject1[i] = convertArg(paramContext, paramScriptable1, paramScriptable2, this.typeTags[i]);
            i += 1;
            break;
          }
        }
        paramScriptable2 = this.member.newInstance((Object[])localObject1);
        i = j;
        break;
        paramArrayOfObject = paramScriptable2;
        if (this.returnTypeTag != 0) {
          break label119;
        }
        return paramContext.getWrapFactory().wrap(paramContext, paramScriptable1, paramScriptable2, null);
      }
    }
  }
  
  public Scriptable createObject(Context paramContext, Scriptable paramScriptable)
  {
    if ((this.member.isCtor()) || (this.parmsLength == -2)) {
      return null;
    }
    try
    {
      paramContext = (Scriptable)this.member.getDeclaringClass().newInstance();
      paramContext.setPrototype(getClassPrototype());
      paramContext.setParentScope(getParentScope());
      return paramContext;
    }
    catch (Exception paramContext)
    {
      throw Context.throwAsScriptRuntimeEx(paramContext);
    }
  }
  
  public int getArity()
  {
    if (this.parmsLength < 0) {
      return 1;
    }
    return this.parmsLength;
  }
  
  public String getFunctionName()
  {
    if (this.functionName == null) {
      return "";
    }
    return this.functionName;
  }
  
  public int getLength()
  {
    return getArity();
  }
  
  public Member getMethodOrConstructor()
  {
    if (this.member.isMethod()) {
      return this.member.method();
    }
    return this.member.ctor();
  }
  
  void initAsConstructor(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    ScriptRuntime.setFunctionProtoAndParent(this, paramScriptable1);
    setImmunePrototypeProperty(paramScriptable2);
    paramScriptable2.setParentScope(this);
    defineProperty(paramScriptable2, "constructor", this, 7);
    setParentScope(paramScriptable1);
  }
  
  boolean isVarArgsConstructor()
  {
    return this.parmsLength == -2;
  }
  
  boolean isVarArgsMethod()
  {
    return this.parmsLength == -1;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\FunctionObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */