package org.mozilla.javascript.jdk13;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.InterfaceAdapter;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.VMBridge;

public class VMBridge_jdk13
  extends VMBridge
{
  private ThreadLocal<Object[]> contextLocal = new ThreadLocal();
  
  protected Context getContext(Object paramObject)
  {
    return (Context)((Object[])(Object[])paramObject)[0];
  }
  
  protected ClassLoader getCurrentThreadClassLoader()
  {
    return Thread.currentThread().getContextClassLoader();
  }
  
  protected Object getInterfaceProxyHelper(ContextFactory paramContextFactory, Class<?>[] paramArrayOfClass)
  {
    paramContextFactory = Proxy.getProxyClass(paramArrayOfClass[0].getClassLoader(), paramArrayOfClass);
    try
    {
      paramContextFactory = paramContextFactory.getConstructor(new Class[] { InvocationHandler.class });
      return paramContextFactory;
    }
    catch (NoSuchMethodException paramContextFactory)
    {
      throw Kit.initCause(new IllegalStateException(), paramContextFactory);
    }
  }
  
  protected Object getThreadContextHelper()
  {
    Object[] arrayOfObject2 = (Object[])this.contextLocal.get();
    Object[] arrayOfObject1 = arrayOfObject2;
    if (arrayOfObject2 == null)
    {
      arrayOfObject1 = new Object[1];
      this.contextLocal.set(arrayOfObject1);
    }
    return arrayOfObject1;
  }
  
  protected boolean isVarArgs(Member paramMember)
  {
    return false;
  }
  
  protected Object newInterfaceProxy(Object paramObject1, final ContextFactory paramContextFactory, final InterfaceAdapter paramInterfaceAdapter, final Object paramObject2, final Scriptable paramScriptable)
  {
    paramObject1 = (Constructor)paramObject1;
    paramContextFactory = new InvocationHandler()
    {
      public Object invoke(Object paramAnonymousObject, Method paramAnonymousMethod, Object[] paramAnonymousArrayOfObject)
      {
        boolean bool = false;
        if (paramAnonymousMethod.getDeclaringClass() == Object.class)
        {
          String str = paramAnonymousMethod.getName();
          if (str.equals("equals"))
          {
            if (paramAnonymousObject == paramAnonymousArrayOfObject[0]) {
              bool = true;
            }
            return Boolean.valueOf(bool);
          }
          if (str.equals("hashCode")) {
            return Integer.valueOf(paramObject2.hashCode());
          }
          if (str.equals("toString")) {
            return "Proxy[" + paramObject2.toString() + "]";
          }
        }
        return paramInterfaceAdapter.invoke(paramContextFactory, paramObject2, paramScriptable, paramAnonymousObject, paramAnonymousMethod, paramAnonymousArrayOfObject);
      }
    };
    try
    {
      paramObject1 = ((Constructor)paramObject1).newInstance(new Object[] { paramContextFactory });
      return paramObject1;
    }
    catch (InvocationTargetException paramObject1)
    {
      throw Context.throwAsScriptRuntimeEx((Throwable)paramObject1);
    }
    catch (IllegalAccessException paramObject1)
    {
      throw Kit.initCause(new IllegalStateException(), (Throwable)paramObject1);
    }
    catch (InstantiationException paramObject1)
    {
      throw Kit.initCause(new IllegalStateException(), (Throwable)paramObject1);
    }
  }
  
  protected void setContext(Object paramObject, Context paramContext)
  {
    ((Object[])paramObject)[0] = paramContext;
  }
  
  protected boolean tryToMakeAccessible(Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof AccessibleObject)) {
      bool = false;
    }
    do
    {
      return bool;
      paramObject = (AccessibleObject)paramObject;
    } while (((AccessibleObject)paramObject).isAccessible());
    try
    {
      ((AccessibleObject)paramObject).setAccessible(true);
      return ((AccessibleObject)paramObject).isAccessible();
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\jdk13\VMBridge_jdk13.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */