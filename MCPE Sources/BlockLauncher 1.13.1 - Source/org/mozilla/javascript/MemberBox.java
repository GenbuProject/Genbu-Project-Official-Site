package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

final class MemberBox
  implements Serializable
{
  private static final Class<?>[] primitives = { Boolean.TYPE, Byte.TYPE, Character.TYPE, Double.TYPE, Float.TYPE, Integer.TYPE, Long.TYPE, Short.TYPE, Void.TYPE };
  static final long serialVersionUID = 6358550398665688245L;
  transient Class<?>[] argTypes;
  transient Object delegateTo;
  private transient Member memberObject;
  transient boolean vararg;
  
  MemberBox(Constructor<?> paramConstructor)
  {
    init(paramConstructor);
  }
  
  MemberBox(Method paramMethod)
  {
    init(paramMethod);
  }
  
  private void init(Constructor<?> paramConstructor)
  {
    this.memberObject = paramConstructor;
    this.argTypes = paramConstructor.getParameterTypes();
    this.vararg = VMBridge.instance.isVarArgs(paramConstructor);
  }
  
  private void init(Method paramMethod)
  {
    this.memberObject = paramMethod;
    this.argTypes = paramMethod.getParameterTypes();
    this.vararg = VMBridge.instance.isVarArgs(paramMethod);
  }
  
  private static Member readMember(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    if (!paramObjectInputStream.readBoolean()) {
      return null;
    }
    boolean bool = paramObjectInputStream.readBoolean();
    String str = (String)paramObjectInputStream.readObject();
    Class localClass = (Class)paramObjectInputStream.readObject();
    paramObjectInputStream = readParameters(paramObjectInputStream);
    if (bool) {}
    try
    {
      return localClass.getMethod(str, paramObjectInputStream);
    }
    catch (NoSuchMethodException paramObjectInputStream)
    {
      throw new IOException("Cannot find member: " + paramObjectInputStream);
    }
    paramObjectInputStream = localClass.getConstructor(paramObjectInputStream);
    return paramObjectInputStream;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    paramObjectInputStream = readMember(paramObjectInputStream);
    if ((paramObjectInputStream instanceof Method))
    {
      init((Method)paramObjectInputStream);
      return;
    }
    init((Constructor)paramObjectInputStream);
  }
  
  private static Class<?>[] readParameters(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    Class[] arrayOfClass = new Class[paramObjectInputStream.readShort()];
    int i = 0;
    if (i < arrayOfClass.length)
    {
      if (!paramObjectInputStream.readBoolean()) {
        arrayOfClass[i] = ((Class)paramObjectInputStream.readObject());
      }
      for (;;)
      {
        i += 1;
        break;
        arrayOfClass[i] = primitives[paramObjectInputStream.readByte()];
      }
    }
    return arrayOfClass;
  }
  
  /* Error */
  private static Method searchAccessibleMethod(Method paramMethod, Class<?>[] paramArrayOfClass)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 169	java/lang/reflect/Method:getModifiers	()I
    //   4: istore_2
    //   5: iload_2
    //   6: invokestatic 175	java/lang/reflect/Modifier:isPublic	(I)Z
    //   9: ifeq +102 -> 111
    //   12: iload_2
    //   13: invokestatic 178	java/lang/reflect/Modifier:isStatic	(I)Z
    //   16: ifne +95 -> 111
    //   19: aload_0
    //   20: invokevirtual 182	java/lang/reflect/Method:getDeclaringClass	()Ljava/lang/Class;
    //   23: astore 5
    //   25: aload 5
    //   27: invokevirtual 183	java/lang/Class:getModifiers	()I
    //   30: invokestatic 175	java/lang/reflect/Modifier:isPublic	(I)Z
    //   33: ifne +78 -> 111
    //   36: aload_0
    //   37: invokevirtual 186	java/lang/reflect/Method:getName	()Ljava/lang/String;
    //   40: astore 6
    //   42: aload 5
    //   44: invokevirtual 189	java/lang/Class:getInterfaces	()[Ljava/lang/Class;
    //   47: astore 7
    //   49: iconst_0
    //   50: istore_2
    //   51: aload 7
    //   53: arraylength
    //   54: istore_3
    //   55: aload 5
    //   57: astore_0
    //   58: iload_2
    //   59: iload_3
    //   60: if_icmpeq +40 -> 100
    //   63: aload 7
    //   65: iload_2
    //   66: aaload
    //   67: astore_0
    //   68: aload_0
    //   69: invokevirtual 183	java/lang/Class:getModifiers	()I
    //   72: invokestatic 175	java/lang/reflect/Modifier:isPublic	(I)Z
    //   75: ifeq +14 -> 89
    //   78: aload_0
    //   79: aload 6
    //   81: aload_1
    //   82: invokevirtual 122	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   85: astore_0
    //   86: aload_0
    //   87: areturn
    //   88: astore_0
    //   89: iload_2
    //   90: iconst_1
    //   91: iadd
    //   92: istore_2
    //   93: goto -38 -> 55
    //   96: astore_0
    //   97: aload 5
    //   99: astore_0
    //   100: aload_0
    //   101: invokevirtual 192	java/lang/Class:getSuperclass	()Ljava/lang/Class;
    //   104: astore 5
    //   106: aload 5
    //   108: ifnonnull +5 -> 113
    //   111: aconst_null
    //   112: areturn
    //   113: aload 5
    //   115: astore_0
    //   116: aload 5
    //   118: invokevirtual 183	java/lang/Class:getModifiers	()I
    //   121: invokestatic 175	java/lang/reflect/Modifier:isPublic	(I)Z
    //   124: ifeq -24 -> 100
    //   127: aload 5
    //   129: aload 6
    //   131: aload_1
    //   132: invokevirtual 122	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   135: astore 7
    //   137: aload 7
    //   139: invokevirtual 169	java/lang/reflect/Method:getModifiers	()I
    //   142: istore_2
    //   143: aload 5
    //   145: astore_0
    //   146: iload_2
    //   147: invokestatic 175	java/lang/reflect/Modifier:isPublic	(I)Z
    //   150: ifeq -50 -> 100
    //   153: iload_2
    //   154: invokestatic 178	java/lang/reflect/Modifier:isStatic	(I)Z
    //   157: istore 4
    //   159: aload 5
    //   161: astore_0
    //   162: iload 4
    //   164: ifne -64 -> 100
    //   167: aload 7
    //   169: areturn
    //   170: astore_0
    //   171: goto -82 -> 89
    //   174: astore_0
    //   175: aload 5
    //   177: astore_0
    //   178: goto -78 -> 100
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	181	0	paramMethod	Method
    //   0	181	1	paramArrayOfClass	Class<?>[]
    //   4	150	2	i	int
    //   54	7	3	j	int
    //   157	6	4	bool	boolean
    //   23	153	5	localClass	Class
    //   40	90	6	str	String
    //   47	121	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   78	86	88	java/lang/NoSuchMethodException
    //   127	143	96	java/lang/SecurityException
    //   146	159	96	java/lang/SecurityException
    //   78	86	170	java/lang/SecurityException
    //   127	143	174	java/lang/NoSuchMethodException
    //   146	159	174	java/lang/NoSuchMethodException
  }
  
  private static void writeMember(ObjectOutputStream paramObjectOutputStream, Member paramMember)
    throws IOException
  {
    if (paramMember == null)
    {
      paramObjectOutputStream.writeBoolean(false);
      return;
    }
    paramObjectOutputStream.writeBoolean(true);
    if ((!(paramMember instanceof Method)) && (!(paramMember instanceof Constructor))) {
      throw new IllegalArgumentException("not Method or Constructor");
    }
    paramObjectOutputStream.writeBoolean(paramMember instanceof Method);
    paramObjectOutputStream.writeObject(paramMember.getName());
    paramObjectOutputStream.writeObject(paramMember.getDeclaringClass());
    if ((paramMember instanceof Method))
    {
      writeParameters(paramObjectOutputStream, ((Method)paramMember).getParameterTypes());
      return;
    }
    writeParameters(paramObjectOutputStream, ((Constructor)paramMember).getParameterTypes());
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    writeMember(paramObjectOutputStream, this.memberObject);
  }
  
  private static void writeParameters(ObjectOutputStream paramObjectOutputStream, Class<?>[] paramArrayOfClass)
    throws IOException
  {
    paramObjectOutputStream.writeShort(paramArrayOfClass.length);
    int i = 0;
    while (i < paramArrayOfClass.length)
    {
      Class<?> localClass = paramArrayOfClass[i];
      boolean bool = localClass.isPrimitive();
      paramObjectOutputStream.writeBoolean(bool);
      if (!bool)
      {
        paramObjectOutputStream.writeObject(localClass);
        i += 1;
      }
      else
      {
        int j = 0;
        for (;;)
        {
          if (j >= primitives.length) {
            break label88;
          }
          if (localClass.equals(primitives[j]))
          {
            paramObjectOutputStream.writeByte(j);
            break;
          }
          j += 1;
        }
        label88:
        throw new IllegalArgumentException("Primitive " + localClass + " not found");
      }
    }
  }
  
  Constructor<?> ctor()
  {
    return (Constructor)this.memberObject;
  }
  
  Class<?> getDeclaringClass()
  {
    return this.memberObject.getDeclaringClass();
  }
  
  String getName()
  {
    return this.memberObject.getName();
  }
  
  Object invoke(Object paramObject, Object[] paramArrayOfObject)
  {
    Object localObject1 = method();
    for (;;)
    {
      try
      {
        localObject2 = ((Method)localObject1).invoke(paramObject, paramArrayOfObject);
        return localObject2;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Object localObject2 = searchAccessibleMethod((Method)localObject1, this.argTypes);
        if (localObject2 != null)
        {
          this.memberObject = ((Member)localObject2);
          localObject1 = localObject2;
          return ((Method)localObject1).invoke(paramObject, paramArrayOfObject);
        }
        if (!VMBridge.instance.tryToMakeAccessible(localObject1)) {
          throw Context.throwAsScriptRuntimeEx(localIllegalAccessException);
        }
      }
      catch (InvocationTargetException paramObject)
      {
        paramArrayOfObject = ((InvocationTargetException)paramObject).getTargetException();
        paramObject = paramArrayOfObject;
        if (!(paramArrayOfObject instanceof InvocationTargetException))
        {
          if ((paramArrayOfObject instanceof ContinuationPending)) {
            throw ((ContinuationPending)paramArrayOfObject);
          }
          throw Context.throwAsScriptRuntimeEx(paramArrayOfObject);
        }
      }
      catch (Exception paramObject)
      {
        throw Context.throwAsScriptRuntimeEx((Throwable)paramObject);
      }
    }
  }
  
  boolean isCtor()
  {
    return this.memberObject instanceof Constructor;
  }
  
  boolean isMethod()
  {
    return this.memberObject instanceof Method;
  }
  
  boolean isStatic()
  {
    return Modifier.isStatic(this.memberObject.getModifiers());
  }
  
  Member member()
  {
    return this.memberObject;
  }
  
  Method method()
  {
    return (Method)this.memberObject;
  }
  
  Object newInstance(Object[] paramArrayOfObject)
  {
    Constructor localConstructor = ctor();
    try
    {
      Object localObject = localConstructor.newInstance(paramArrayOfObject);
      return localObject;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      if (!VMBridge.instance.tryToMakeAccessible(localConstructor)) {
        throw Context.throwAsScriptRuntimeEx(localIllegalAccessException);
      }
    }
    catch (Exception paramArrayOfObject)
    {
      throw Context.throwAsScriptRuntimeEx(paramArrayOfObject);
    }
    paramArrayOfObject = localConstructor.newInstance(paramArrayOfObject);
    return paramArrayOfObject;
  }
  
  String toJavaDeclaration()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject;
    if (isMethod())
    {
      localObject = method();
      localStringBuilder.append(((Method)localObject).getReturnType());
      localStringBuilder.append(' ');
      localStringBuilder.append(((Method)localObject).getName());
    }
    for (;;)
    {
      localStringBuilder.append(JavaMembers.liveConnectSignature(this.argTypes));
      return localStringBuilder.toString();
      String str = ctor().getDeclaringClass().getName();
      int i = str.lastIndexOf('.');
      localObject = str;
      if (i >= 0) {
        localObject = str.substring(i + 1);
      }
      localStringBuilder.append((String)localObject);
    }
  }
  
  public String toString()
  {
    return this.memberObject.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\MemberBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */