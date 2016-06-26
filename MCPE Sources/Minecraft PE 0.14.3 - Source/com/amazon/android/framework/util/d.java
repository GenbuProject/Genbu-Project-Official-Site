package com.amazon.android.framework.util;

import com.amazon.android.d.a;
import java.lang.reflect.Method;

public final class d
{
  private static final KiwiLogger a = new KiwiLogger("Reflection");
  
  public static Class a(String paramString)
  {
    a.a(paramString, "String className");
    Object localObject = Thread.currentThread().getContextClassLoader();
    try
    {
      localObject = ((ClassLoader)localObject).loadClass(paramString);
      return (Class)localObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("ClassNoFound: " + paramString);
      }
    }
    return null;
  }
  
  public static boolean a(Method paramMethod)
  {
    a.a(paramMethod, "Method m");
    return paramMethod.getParameterTypes().length > 0;
  }
  
  public static boolean b(Method paramMethod)
  {
    a.a(paramMethod, "Method m");
    return paramMethod.getReturnType().equals(Void.TYPE);
  }
  
  public static boolean c(Method paramMethod)
  {
    a.a(paramMethod, "Method m");
    return (paramMethod.getModifiers() & 0x8) != 0;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\util\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */