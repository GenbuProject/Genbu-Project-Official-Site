package org.mozilla.javascript;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;

public final class LazilyLoadedCtor
  implements Serializable
{
  private static final int STATE_BEFORE_INIT = 0;
  private static final int STATE_INITIALIZING = 1;
  private static final int STATE_WITH_VALUE = 2;
  private static final long serialVersionUID = 1L;
  private final String className;
  private Object initializedValue;
  private final boolean privileged;
  private final String propertyName;
  private final ScriptableObject scope;
  private final boolean sealed;
  private int state;
  
  public LazilyLoadedCtor(ScriptableObject paramScriptableObject, String paramString1, String paramString2, boolean paramBoolean)
  {
    this(paramScriptableObject, paramString1, paramString2, paramBoolean, false);
  }
  
  LazilyLoadedCtor(ScriptableObject paramScriptableObject, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.scope = paramScriptableObject;
    this.propertyName = paramString1;
    this.className = paramString2;
    this.sealed = paramBoolean1;
    this.privileged = paramBoolean2;
    this.state = 0;
    paramScriptableObject.addLazilyInitializedValue(paramString1, 0, this, 2);
  }
  
  private Object buildValue()
  {
    if (this.privileged) {
      AccessController.doPrivileged(new PrivilegedAction()
      {
        public Object run()
        {
          return LazilyLoadedCtor.this.buildValue0();
        }
      });
    }
    return buildValue0();
  }
  
  private Object buildValue0()
  {
    Object localObject1 = cast(Kit.classOrNull(this.className));
    if (localObject1 != null) {}
    try
    {
      localObject1 = ScriptableObject.buildClassCtor(this.scope, (Class)localObject1, this.sealed, false);
      if (localObject1 != null) {
        return localObject1;
      }
      localObject1 = this.scope.get(this.propertyName, this.scope);
      Object localObject2 = Scriptable.NOT_FOUND;
      if (localObject1 != localObject2) {
        return ???;
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Throwable localThrowable;
      do
      {
        localThrowable = localInvocationTargetException.getTargetException();
      } while (!(localThrowable instanceof RuntimeException));
      throw ((RuntimeException)localThrowable);
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;) {}
    }
    catch (InstantiationException localInstantiationException)
    {
      for (;;) {}
    }
    catch (RhinoException localRhinoException)
    {
      for (;;) {}
      return localRhinoException;
    }
    return Scriptable.NOT_FOUND;
  }
  
  private Class<? extends Scriptable> cast(Class<?> paramClass)
  {
    return paramClass;
  }
  
  Object getValue()
  {
    if (this.state != 2) {
      throw new IllegalStateException(this.propertyName);
    }
    return this.initializedValue;
  }
  
  void init()
  {
    try
    {
      if (this.state == 1) {
        throw new IllegalStateException("Recursive initialization for " + this.propertyName);
      }
    }
    finally {}
    Object localObject2;
    if (this.state == 0)
    {
      this.state = 1;
      localObject2 = Scriptable.NOT_FOUND;
    }
    try
    {
      Object localObject3 = buildValue();
      this.initializedValue = localObject3;
      this.state = 2;
      return;
    }
    finally
    {
      localObject4 = finally;
      this.initializedValue = localObject2;
      this.state = 2;
      throw ((Throwable)localObject4);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\LazilyLoadedCtor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */