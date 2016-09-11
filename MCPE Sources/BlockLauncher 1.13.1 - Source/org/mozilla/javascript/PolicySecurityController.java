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
import org.mozilla.classfile.ClassFileWriter;

public class PolicySecurityController
  extends SecurityController
{
  private static final Map<CodeSource, Map<ClassLoader, SoftReference<SecureCaller>>> callers = new WeakHashMap();
  private static final byte[] secureCallerImplBytecode = ;
  
  private static byte[] loadBytecode()
  {
    int i = 1;
    String str = SecureCaller.class.getName();
    ClassFileWriter localClassFileWriter = new ClassFileWriter(str + "Impl", str, "<generated>");
    localClassFileWriter.startMethod("<init>", "()V", (short)1);
    localClassFileWriter.addALoad(0);
    localClassFileWriter.addInvoke(183, str, "<init>", "()V");
    localClassFileWriter.add(177);
    localClassFileWriter.stopMethod((short)1);
    localClassFileWriter.startMethod("call", "(Lorg/mozilla/javascript/Callable;" + "Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;", (short)17);
    while (i < 6)
    {
      localClassFileWriter.addALoad(i);
      i += 1;
    }
    localClassFileWriter.addInvoke(185, "org/mozilla/javascript/Callable", "call", "(" + "Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
    localClassFileWriter.add(176);
    localClassFileWriter.stopMethod((short)6);
    return localClassFileWriter.toByteArray();
  }
  
  public Object callWithDomain(Object arg1, final Context paramContext, Callable paramCallable, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    final ClassLoader localClassLoader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        return paramContext.getApplicationClassLoader();
      }
    });
    final CodeSource localCodeSource = (CodeSource)???;
    label187:
    label190:
    for (;;)
    {
      Object localObject;
      synchronized (callers)
      {
        localObject = (Map)callers.get(localCodeSource);
        if (localObject != null) {
          break label190;
        }
        localObject = new WeakHashMap();
        callers.put(localCodeSource, localObject);
      }
      try
      {
        ??? = (SoftReference)((Map)localObject).get(localClassLoader);
        if (??? != null)
        {
          ??? = (SecureCaller)((SoftReference)???).get();
          if (??? != null) {
            break label187;
          }
        }
        for (;;)
        {
          try
          {
            ??? = (SecureCaller)AccessController.doPrivileged(new PrivilegedExceptionAction()
            {
              public Object run()
                throws Exception
              {
                return new PolicySecurityController.Loader(localClassLoader, localCodeSource).defineClass(PolicySecurityController.SecureCaller.class.getName() + "Impl", PolicySecurityController.secureCallerImplBytecode).newInstance();
              }
            });
            ((Map)localObject).put(localClassLoader, new SoftReference(???));
            return ((SecureCaller)???).call(paramCallable, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
          }
          catch (PrivilegedActionException ???)
          {
            throw new UndeclaredThrowableException(((PrivilegedActionException)???).getCause());
          }
          paramContext = finally;
          throw paramContext;
          ??? = null;
          break;
        }
      }
      finally {}
    }
  }
  
  public GeneratedClassLoader createClassLoader(final ClassLoader paramClassLoader, final Object paramObject)
  {
    (Loader)AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        return new PolicySecurityController.Loader(paramClassLoader, (CodeSource)paramObject);
      }
    });
  }
  
  public Object getDynamicSecurityDomain(Object paramObject)
  {
    return paramObject;
  }
  
  public Class<?> getStaticSecurityDomainClassInternal()
  {
    return CodeSource.class;
  }
  
  private static class Loader
    extends SecureClassLoader
    implements GeneratedClassLoader
  {
    private final CodeSource codeSource;
    
    Loader(ClassLoader paramClassLoader, CodeSource paramCodeSource)
    {
      super();
      this.codeSource = paramCodeSource;
    }
    
    public Class<?> defineClass(String paramString, byte[] paramArrayOfByte)
    {
      return defineClass(paramString, paramArrayOfByte, 0, paramArrayOfByte.length, this.codeSource);
    }
    
    public void linkClass(Class<?> paramClass)
    {
      resolveClass(paramClass);
    }
  }
  
  public static abstract class SecureCaller
  {
    public abstract Object call(Callable paramCallable, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\PolicySecurityController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */