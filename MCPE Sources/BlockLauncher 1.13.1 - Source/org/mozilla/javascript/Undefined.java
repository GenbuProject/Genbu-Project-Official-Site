package org.mozilla.javascript;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Undefined
  implements Serializable
{
  public static final Scriptable SCRIPTABLE_UNDEFINED;
  public static final Object instance = new Undefined();
  static final long serialVersionUID = 9195680630202616767L;
  
  static
  {
    ClassLoader localClassLoader = Undefined.class.getClassLoader();
    InvocationHandler local1 = new InvocationHandler()
    {
      public Object invoke(Object paramAnonymousObject, Method paramAnonymousMethod, Object[] paramAnonymousArrayOfObject)
        throws Throwable
      {
        boolean bool2 = false;
        if (paramAnonymousMethod.getName().equals("toString")) {
          return "undefined";
        }
        if (paramAnonymousMethod.getName().equals("equals"))
        {
          boolean bool1 = bool2;
          if (paramAnonymousArrayOfObject.length > 0)
          {
            bool1 = bool2;
            if (Undefined.isUndefined(paramAnonymousArrayOfObject[0])) {
              bool1 = true;
            }
          }
          return Boolean.valueOf(bool1);
        }
        throw new UnsupportedOperationException("undefined doesn't support " + paramAnonymousMethod.getName());
      }
    };
    SCRIPTABLE_UNDEFINED = (Scriptable)Proxy.newProxyInstance(localClassLoader, new Class[] { Scriptable.class }, local1);
  }
  
  public static boolean isUndefined(Object paramObject)
  {
    return (instance == paramObject) || (SCRIPTABLE_UNDEFINED == paramObject);
  }
  
  public boolean equals(Object paramObject)
  {
    return (isUndefined(paramObject)) || (super.equals(paramObject));
  }
  
  public Object readResolve()
  {
    return instance;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\Undefined.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */