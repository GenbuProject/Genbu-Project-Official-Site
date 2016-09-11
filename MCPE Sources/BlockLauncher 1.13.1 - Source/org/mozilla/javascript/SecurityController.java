package org.mozilla.javascript;

public abstract class SecurityController
{
  private static SecurityController global;
  
  public static GeneratedClassLoader createLoader(ClassLoader paramClassLoader, Object paramObject)
  {
    Context localContext = Context.getContext();
    ClassLoader localClassLoader = paramClassLoader;
    if (paramClassLoader == null) {
      localClassLoader = localContext.getApplicationClassLoader();
    }
    paramClassLoader = localContext.getSecurityController();
    if (paramClassLoader == null) {
      return localContext.createClassLoader(localClassLoader);
    }
    return paramClassLoader.createClassLoader(localClassLoader, paramClassLoader.getDynamicSecurityDomain(paramObject));
  }
  
  public static Class<?> getStaticSecurityDomainClass()
  {
    SecurityController localSecurityController = Context.getContext().getSecurityController();
    if (localSecurityController == null) {
      return null;
    }
    return localSecurityController.getStaticSecurityDomainClassInternal();
  }
  
  static SecurityController global()
  {
    return global;
  }
  
  public static boolean hasGlobal()
  {
    return global != null;
  }
  
  public static void initGlobal(SecurityController paramSecurityController)
  {
    if (paramSecurityController == null) {
      throw new IllegalArgumentException();
    }
    if (global != null) {
      throw new SecurityException("Cannot overwrite already installed global SecurityController");
    }
    global = paramSecurityController;
  }
  
  public Object callWithDomain(Object paramObject, Context paramContext, final Callable paramCallable, Scriptable paramScriptable1, final Scriptable paramScriptable2, final Object[] paramArrayOfObject)
  {
    execWithDomain(paramContext, paramScriptable1, new Script()
    {
      public Object exec(Context paramAnonymousContext, Scriptable paramAnonymousScriptable)
      {
        return paramCallable.call(paramAnonymousContext, paramAnonymousScriptable, paramScriptable2, paramArrayOfObject);
      }
    }, paramObject);
  }
  
  public abstract GeneratedClassLoader createClassLoader(ClassLoader paramClassLoader, Object paramObject);
  
  @Deprecated
  public Object execWithDomain(Context paramContext, Scriptable paramScriptable, Script paramScript, Object paramObject)
  {
    throw new IllegalStateException("callWithDomain should be overridden");
  }
  
  public abstract Object getDynamicSecurityDomain(Object paramObject);
  
  public Class<?> getStaticSecurityDomainClassInternal()
  {
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\SecurityController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */