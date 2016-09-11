package org.mozilla.javascript;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Map;

public class Kit
{
  private static Method Throwable_initCause = null;
  
  static
  {
    try
    {
      Class localClass = classOrNull("java.lang.Throwable");
      Throwable_initCause = localClass.getMethod("initCause", new Class[] { localClass });
      return;
    }
    catch (Exception localException) {}
  }
  
  public static Object addListener(Object paramObject1, Object paramObject2)
  {
    if (paramObject2 == null) {
      throw new IllegalArgumentException();
    }
    if ((paramObject2 instanceof Object[])) {
      throw new IllegalArgumentException();
    }
    if (paramObject1 == null) {
      return paramObject2;
    }
    if (!(paramObject1 instanceof Object[])) {
      return new Object[] { paramObject1, paramObject2 };
    }
    paramObject1 = (Object[])paramObject1;
    int i = paramObject1.length;
    if (i < 2) {
      throw new IllegalArgumentException();
    }
    Object[] arrayOfObject = new Object[i + 1];
    System.arraycopy(paramObject1, 0, arrayOfObject, 0, i);
    arrayOfObject[i] = paramObject2;
    return arrayOfObject;
  }
  
  public static Class<?> classOrNull(ClassLoader paramClassLoader, String paramString)
  {
    try
    {
      paramClassLoader = paramClassLoader.loadClass(paramString);
      return paramClassLoader;
    }
    catch (ClassNotFoundException paramClassLoader)
    {
      return null;
    }
    catch (SecurityException paramClassLoader)
    {
      for (;;) {}
    }
    catch (LinkageError paramClassLoader)
    {
      for (;;) {}
    }
    catch (IllegalArgumentException paramClassLoader)
    {
      for (;;) {}
    }
  }
  
  public static Class<?> classOrNull(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
      return null;
    }
    catch (SecurityException paramString)
    {
      for (;;) {}
    }
    catch (LinkageError paramString)
    {
      for (;;) {}
    }
    catch (IllegalArgumentException paramString)
    {
      for (;;) {}
    }
  }
  
  public static RuntimeException codeBug()
    throws RuntimeException
  {
    IllegalStateException localIllegalStateException = new IllegalStateException("FAILED ASSERTION");
    localIllegalStateException.printStackTrace(System.err);
    throw localIllegalStateException;
  }
  
  public static RuntimeException codeBug(String paramString)
    throws RuntimeException
  {
    paramString = new IllegalStateException("FAILED ASSERTION: " + paramString);
    paramString.printStackTrace(System.err);
    throw paramString;
  }
  
  public static Object getListener(Object paramObject, int paramInt)
  {
    if (paramInt == 0)
    {
      Object localObject;
      if (paramObject == null) {
        localObject = null;
      }
      do
      {
        return localObject;
        localObject = paramObject;
      } while (!(paramObject instanceof Object[]));
      paramObject = (Object[])paramObject;
      if (paramObject.length < 2) {
        throw new IllegalArgumentException();
      }
      return paramObject[0];
    }
    if (paramInt == 1)
    {
      if (!(paramObject instanceof Object[]))
      {
        if (paramObject == null) {
          throw new IllegalArgumentException();
        }
        return null;
      }
      return ((Object[])(Object[])paramObject)[1];
    }
    paramObject = (Object[])paramObject;
    int i = paramObject.length;
    if (i < 2) {
      throw new IllegalArgumentException();
    }
    if (paramInt == i) {
      return null;
    }
    return paramObject[paramInt];
  }
  
  public static RuntimeException initCause(RuntimeException paramRuntimeException, Throwable paramThrowable)
  {
    if (Throwable_initCause != null) {}
    try
    {
      Throwable_initCause.invoke(paramRuntimeException, new Object[] { paramThrowable });
      return paramRuntimeException;
    }
    catch (Exception paramThrowable) {}
    return paramRuntimeException;
  }
  
  static Object initHash(Map<Object, Object> paramMap, Object paramObject1, Object paramObject2)
  {
    for (;;)
    {
      Object localObject;
      try
      {
        localObject = paramMap.get(paramObject1);
        if (localObject == null)
        {
          paramMap.put(paramObject1, paramObject2);
          return paramObject2;
        }
      }
      finally {}
      paramObject2 = localObject;
    }
  }
  
  public static Object makeHashKeyFromPair(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null) {
      throw new IllegalArgumentException();
    }
    if (paramObject2 == null) {
      throw new IllegalArgumentException();
    }
    return new ComplexKey(paramObject1, paramObject2);
  }
  
  static Object newInstanceOrNull(Class<?> paramClass)
  {
    try
    {
      paramClass = paramClass.newInstance();
      return paramClass;
    }
    catch (SecurityException paramClass)
    {
      return null;
    }
    catch (LinkageError paramClass)
    {
      for (;;) {}
    }
    catch (InstantiationException paramClass)
    {
      for (;;) {}
    }
    catch (IllegalAccessException paramClass)
    {
      for (;;) {}
    }
  }
  
  public static String readReader(Reader paramReader)
    throws IOException
  {
    Object localObject = new char['?'];
    int i = 0;
    for (;;)
    {
      int j = paramReader.read((char[])localObject, i, localObject.length - i);
      if (j < 0) {
        return new String((char[])localObject, 0, i);
      }
      j = i + j;
      i = j;
      if (j == localObject.length)
      {
        char[] arrayOfChar = new char[localObject.length * 2];
        System.arraycopy(localObject, 0, arrayOfChar, 0, j);
        localObject = arrayOfChar;
        i = j;
      }
    }
  }
  
  public static byte[] readStream(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("Bad initialBufferCapacity: " + paramInt);
    }
    Object localObject = new byte[paramInt];
    paramInt = 0;
    for (;;)
    {
      int i = paramInputStream.read((byte[])localObject, paramInt, localObject.length - paramInt);
      if (i < 0)
      {
        paramInputStream = (InputStream)localObject;
        if (paramInt != localObject.length)
        {
          paramInputStream = new byte[paramInt];
          System.arraycopy(localObject, 0, paramInputStream, 0, paramInt);
        }
        return paramInputStream;
      }
      i = paramInt + i;
      paramInt = i;
      if (i == localObject.length)
      {
        byte[] arrayOfByte = new byte[localObject.length * 2];
        System.arraycopy(localObject, 0, arrayOfByte, 0, i);
        localObject = arrayOfByte;
        paramInt = i;
      }
    }
  }
  
  public static Object removeListener(Object paramObject1, Object paramObject2)
  {
    if (paramObject2 == null) {
      throw new IllegalArgumentException();
    }
    if ((paramObject2 instanceof Object[])) {
      throw new IllegalArgumentException();
    }
    Object localObject;
    if (paramObject1 == paramObject2) {
      localObject = null;
    }
    Object[] arrayOfObject;
    int j;
    do
    {
      do
      {
        return localObject;
        localObject = paramObject1;
      } while (!(paramObject1 instanceof Object[]));
      arrayOfObject = (Object[])paramObject1;
      j = arrayOfObject.length;
      if (j < 2) {
        throw new IllegalArgumentException();
      }
      if (j != 2) {
        break;
      }
      if (arrayOfObject[1] == paramObject2) {
        return arrayOfObject[0];
      }
      localObject = paramObject1;
    } while (arrayOfObject[0] != paramObject2);
    return arrayOfObject[1];
    int i = j;
    int k;
    do
    {
      k = i - 1;
      if (arrayOfObject[k] == paramObject2)
      {
        paramObject1 = new Object[j - 1];
        System.arraycopy(arrayOfObject, 0, paramObject1, 0, k);
        System.arraycopy(arrayOfObject, k + 1, paramObject1, k, j - (k + 1));
        return paramObject1;
      }
      i = k;
    } while (k != 0);
    return paramObject1;
  }
  
  static boolean testIfCanLoadRhinoClasses(ClassLoader paramClassLoader)
  {
    Class localClass = ScriptRuntime.ContextFactoryClass;
    return classOrNull(paramClassLoader, localClass.getName()) == localClass;
  }
  
  public static int xDigitToInt(int paramInt1, int paramInt2)
  {
    if (paramInt1 <= 57)
    {
      paramInt1 -= 48;
      if (paramInt1 < 0) {}
    }
    else
    {
      for (;;)
      {
        return paramInt1 | paramInt2 << 4;
        if (paramInt1 <= 70)
        {
          if (65 > paramInt1) {
            break;
          }
          paramInt1 -= 55;
        }
        else
        {
          if ((paramInt1 > 102) || (97 > paramInt1)) {
            break;
          }
          paramInt1 -= 87;
        }
      }
    }
    return -1;
  }
  
  private static final class ComplexKey
  {
    private int hash;
    private Object key1;
    private Object key2;
    
    ComplexKey(Object paramObject1, Object paramObject2)
    {
      this.key1 = paramObject1;
      this.key2 = paramObject2;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof ComplexKey)) {}
      do
      {
        return false;
        paramObject = (ComplexKey)paramObject;
      } while ((!this.key1.equals(((ComplexKey)paramObject).key1)) || (!this.key2.equals(((ComplexKey)paramObject).key2)));
      return true;
    }
    
    public int hashCode()
    {
      if (this.hash == 0) {
        this.hash = (this.key1.hashCode() ^ this.key2.hashCode());
      }
      return this.hash;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\Kit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */