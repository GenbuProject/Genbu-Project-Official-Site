package org.mozilla.javascript;

import java.lang.ref.SoftReference;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.util.Map;
import java.util.WeakHashMap;

public abstract class SecureCaller
{
  private static final Map<CodeSource, Map<ClassLoader, SoftReference<SecureCaller>>> callers = new WeakHashMap();
  private static final byte[] secureCallerImplBytecode = ;
  
  static Object callSecurely(final CodeSource paramCodeSource, Callable paramCallable, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    ClassLoader localClassLoader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        return this.val$thread.getContextClassLoader();
      }
    });
    label193:
    for (;;)
    {
      Object localObject2;
      synchronized (callers)
      {
        localObject2 = (Map)callers.get(paramCodeSource);
        if (localObject2 != null) {
          break label193;
        }
        localObject2 = new WeakHashMap();
        callers.put(paramCodeSource, localObject2);
      }
      for (;;)
      {
        try
        {
          ??? = (SoftReference)((Map)localObject2).get(localClassLoader);
          if (??? != null)
          {
            ??? = (SecureCaller)((SoftReference)???).get();
            if (??? != null) {}
          }
          else
          {
            try
            {
              paramCodeSource = (SecureCaller)AccessController.doPrivileged(new PrivilegedExceptionAction()
              {
                public Object run()
                  throws Exception
                {
                  Object localObject = getClass();
                  if (this.val$classLoader.loadClass(((Class)localObject).getName()) != localObject) {}
                  for (localObject = ((Class)localObject).getClassLoader();; localObject = this.val$classLoader) {
                    return new SecureCaller.SecureClassLoaderImpl((ClassLoader)localObject).defineAndLinkClass(SecureCaller.class.getName() + "Impl", SecureCaller.secureCallerImplBytecode, paramCodeSource).newInstance();
                  }
                }
              });
              ((Map)localObject2).put(localClassLoader, new SoftReference(paramCodeSource));
              return paramCodeSource.call(paramCallable, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
            }
            catch (PrivilegedActionException paramCodeSource)
            {
              throw new UndeclaredThrowableException(paramCodeSource.getCause());
            }
            paramCodeSource = finally;
            throw paramCodeSource;
            ??? = null;
            continue;
          }
          paramCodeSource = (CodeSource)???;
        }
        finally {}
      }
    }
  }
  
  private static byte[] loadBytecode()
  {
    (byte[])AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        return SecureCaller.access$100();
      }
    });
  }
  
  /* Error */
  private static byte[] loadBytecodePrivileged()
  {
    // Byte code:
    //   0: ldc 2
    //   2: ldc 104
    //   4: invokevirtual 110	java/lang/Class:getResource	(Ljava/lang/String;)Ljava/net/URL;
    //   7: astore_1
    //   8: aload_1
    //   9: invokevirtual 116	java/net/URL:openStream	()Ljava/io/InputStream;
    //   12: astore_1
    //   13: new 118	java/io/ByteArrayOutputStream
    //   16: dup
    //   17: invokespecial 119	java/io/ByteArrayOutputStream:<init>	()V
    //   20: astore_2
    //   21: aload_1
    //   22: invokevirtual 125	java/io/InputStream:read	()I
    //   25: istore_0
    //   26: iload_0
    //   27: iconst_m1
    //   28: if_icmpne +14 -> 42
    //   31: aload_2
    //   32: invokevirtual 128	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   35: astore_2
    //   36: aload_1
    //   37: invokevirtual 131	java/io/InputStream:close	()V
    //   40: aload_2
    //   41: areturn
    //   42: aload_2
    //   43: iload_0
    //   44: invokevirtual 135	java/io/ByteArrayOutputStream:write	(I)V
    //   47: goto -26 -> 21
    //   50: astore_2
    //   51: aload_1
    //   52: invokevirtual 131	java/io/InputStream:close	()V
    //   55: aload_2
    //   56: athrow
    //   57: astore_1
    //   58: new 91	java/lang/reflect/UndeclaredThrowableException
    //   61: dup
    //   62: aload_1
    //   63: invokespecial 98	java/lang/reflect/UndeclaredThrowableException:<init>	(Ljava/lang/Throwable;)V
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   25	19	0	i	int
    //   7	45	1	localObject1	Object
    //   57	6	1	localIOException	java.io.IOException
    //   20	23	2	localObject2	Object
    //   50	6	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   13	21	50	finally
    //   21	26	50	finally
    //   31	36	50	finally
    //   42	47	50	finally
    //   8	13	57	java/io/IOException
    //   36	40	57	java/io/IOException
    //   51	57	57	java/io/IOException
  }
  
  public abstract Object call(Callable paramCallable, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject);
  
  private static class SecureClassLoaderImpl
    extends SecureClassLoader
  {
    SecureClassLoaderImpl(ClassLoader paramClassLoader)
    {
      super();
    }
    
    Class<?> defineAndLinkClass(String paramString, byte[] paramArrayOfByte, CodeSource paramCodeSource)
    {
      paramString = defineClass(paramString, paramArrayOfByte, 0, paramArrayOfByte.length, paramCodeSource);
      resolveClass(paramString);
      return paramString;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\SecureCaller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */