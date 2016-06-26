package com.amazon.android.licensing;

import com.amazon.android.d.a;
import com.amazon.android.framework.task.Task;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.framework.util.d;
import java.lang.reflect.Method;

public final class n
  implements Task
{
  private static final KiwiLogger a = new KiwiLogger("DRMSuccessTask");
  
  private static Method a(Class paramClass, String paramString)
  {
    a.a(paramClass, "Class<?> target");
    a.a(paramString, "String methodName");
    do
    {
      try
      {
        paramClass = paramClass.getDeclaredMethod(paramString, new Class[0]);
        if (paramClass == null)
        {
          if (KiwiLogger.TRACE_ON) {
            a.trace("No exception thrown, but method '" + paramString + "' was not found, this should not happen. ");
          }
          localObject = null;
          return (Method)localObject;
        }
      }
      catch (NoSuchMethodException paramClass)
      {
        if (KiwiLogger.TRACE_ON) {
          a.trace("Did not find method " + paramString);
        }
        return null;
      }
      paramClass.setAccessible(true);
      if (!d.c(paramClass))
      {
        if (KiwiLogger.TRACE_ON) {
          a.trace("Callback " + paramString + " isn't static, ignoring...");
        }
        return null;
      }
      if (!d.b(paramClass))
      {
        if (KiwiLogger.TRACE_ON) {
          a.trace("Callback " + paramString + " returns a value, ignoring...");
        }
        return null;
      }
      Object localObject = paramClass;
    } while (!d.a(paramClass));
    if (KiwiLogger.TRACE_ON) {
      a.trace("Callback " + paramString + " takes parameters, ignoring...");
    }
    return null;
  }
  
  public final void execute()
  {
    Object localObject = d.a("com.amazon.drm.AmazonLicenseVerificationCallback");
    if (localObject == null) {}
    for (;;)
    {
      return;
      localObject = a((Class)localObject, "onDRMSuccess");
      if (localObject != null)
      {
        if (KiwiLogger.TRACE_ON) {
          a.trace("Invoking callback: " + ((Method)localObject).getName());
        }
        try
        {
          ((Method)localObject).invoke(null, new Object[0]);
          if (KiwiLogger.TRACE_ON)
          {
            a.trace("Callback invoked.");
            return;
          }
        }
        catch (Exception localException) {}
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\licensing\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */