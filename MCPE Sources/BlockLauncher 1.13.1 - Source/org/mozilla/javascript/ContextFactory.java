package org.mozilla.javascript;

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.mozilla.javascript.xml.XMLLib.Factory;

public class ContextFactory
{
  private static ContextFactory global = new ContextFactory();
  private static volatile boolean hasCustomGlobal;
  private ClassLoader applicationClassLoader;
  private boolean disabledListening;
  private volatile Object listeners;
  private final Object listenersLock = new Object();
  private volatile boolean sealed;
  
  public static ContextFactory getGlobal()
  {
    return global;
  }
  
  public static GlobalSetter getGlobalSetter()
  {
    try
    {
      if (hasCustomGlobal) {
        throw new IllegalStateException();
      }
    }
    finally {}
    hasCustomGlobal = true;
    GlobalSetter local1GlobalSetterImpl = new GlobalSetter()
    {
      public ContextFactory getContextFactoryGlobal()
      {
        return ContextFactory.global;
      }
      
      public void setContextFactoryGlobal(ContextFactory paramAnonymousContextFactory)
      {
        ContextFactory localContextFactory = paramAnonymousContextFactory;
        if (paramAnonymousContextFactory == null) {
          localContextFactory = new ContextFactory();
        }
        ContextFactory.access$002(localContextFactory);
      }
    };
    return local1GlobalSetterImpl;
  }
  
  public static boolean hasExplicitGlobal()
  {
    return hasCustomGlobal;
  }
  
  public static void initGlobal(ContextFactory paramContextFactory)
  {
    if (paramContextFactory == null) {
      try
      {
        throw new IllegalArgumentException();
      }
      finally {}
    }
    if (hasCustomGlobal) {
      throw new IllegalStateException();
    }
    hasCustomGlobal = true;
    global = paramContextFactory;
  }
  
  private boolean isDom3Present()
  {
    Class localClass = Kit.classOrNull("org.w3c.dom.Node");
    if (localClass == null) {
      return false;
    }
    try
    {
      localClass.getMethod("getUserData", new Class[] { String.class });
      return true;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}
    return false;
  }
  
  public final void addListener(Listener paramListener)
  {
    checkNotSealed();
    synchronized (this.listenersLock)
    {
      if (this.disabledListening) {
        throw new IllegalStateException();
      }
    }
    this.listeners = Kit.addListener(this.listeners, paramListener);
  }
  
  public final Object call(ContextAction paramContextAction)
  {
    return Context.call(this, paramContextAction);
  }
  
  protected final void checkNotSealed()
  {
    if (this.sealed) {
      throw new IllegalStateException();
    }
  }
  
  protected GeneratedClassLoader createClassLoader(final ClassLoader paramClassLoader)
  {
    (GeneratedClassLoader)AccessController.doPrivileged(new PrivilegedAction()
    {
      public DefiningClassLoader run()
      {
        return new DefiningClassLoader(paramClassLoader);
      }
    });
  }
  
  final void disableContextListening()
  {
    checkNotSealed();
    synchronized (this.listenersLock)
    {
      this.disabledListening = true;
      this.listeners = null;
      return;
    }
  }
  
  protected Object doTopCall(Callable paramCallable, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    paramContext = paramCallable.call(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    paramCallable = paramContext;
    if ((paramContext instanceof ConsString)) {
      paramCallable = paramContext.toString();
    }
    return paramCallable;
  }
  
  @Deprecated
  public final Context enter()
  {
    return enterContext(null);
  }
  
  public Context enterContext()
  {
    return enterContext(null);
  }
  
  public final Context enterContext(Context paramContext)
  {
    return Context.enter(paramContext, this);
  }
  
  @Deprecated
  public final void exit() {}
  
  public final ClassLoader getApplicationClassLoader()
  {
    return this.applicationClassLoader;
  }
  
  protected XMLLib.Factory getE4xImplementationFactory()
  {
    if (isDom3Present()) {
      return XMLLib.Factory.create("org.mozilla.javascript.xmlimpl.XMLLibImpl");
    }
    return null;
  }
  
  protected boolean hasFeature(Context paramContext, int paramInt)
  {
    boolean bool4 = true;
    boolean bool5 = true;
    boolean bool2 = true;
    boolean bool3 = false;
    boolean bool1 = bool3;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      paramInt = paramContext.getLanguageVersion();
      if ((paramInt != 100) && (paramInt != 110))
      {
        bool1 = bool3;
        if (paramInt != 120) {
          break;
        }
      }
      else
      {
        bool1 = true;
      }
    case 2: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
      do
      {
        return bool1;
        return true;
        if (paramContext.getLanguageVersion() == 120) {}
        for (bool1 = bool2;; bool1 = false) {
          return bool1;
        }
        return true;
        paramInt = paramContext.getLanguageVersion();
        if (paramInt == 0) {
          break;
        }
        bool1 = bool3;
      } while (paramInt < 160);
      return true;
    case 14: 
      return true;
    case 15: 
      if (paramContext.getLanguageVersion() <= 170) {}
      for (bool1 = bool4;; bool1 = false) {
        return bool1;
      }
    }
    if (paramContext.getLanguageVersion() >= 200) {}
    for (bool1 = bool5;; bool1 = false) {
      return bool1;
    }
  }
  
  public final void initApplicationClassLoader(ClassLoader paramClassLoader)
  {
    if (paramClassLoader == null) {
      throw new IllegalArgumentException("loader is null");
    }
    if (!Kit.testIfCanLoadRhinoClasses(paramClassLoader)) {
      throw new IllegalArgumentException("Loader can not resolve Rhino classes");
    }
    if (this.applicationClassLoader != null) {
      throw new IllegalStateException("applicationClassLoader can only be set once");
    }
    checkNotSealed();
    this.applicationClassLoader = paramClassLoader;
  }
  
  public final boolean isSealed()
  {
    return this.sealed;
  }
  
  protected Context makeContext()
  {
    return new Context(this);
  }
  
  protected void observeInstructionCount(Context paramContext, int paramInt) {}
  
  protected void onContextCreated(Context paramContext)
  {
    Object localObject = this.listeners;
    int i = 0;
    for (;;)
    {
      Listener localListener = (Listener)Kit.getListener(localObject, i);
      if (localListener == null) {
        return;
      }
      localListener.contextCreated(paramContext);
      i += 1;
    }
  }
  
  protected void onContextReleased(Context paramContext)
  {
    Object localObject = this.listeners;
    int i = 0;
    for (;;)
    {
      Listener localListener = (Listener)Kit.getListener(localObject, i);
      if (localListener == null) {
        return;
      }
      localListener.contextReleased(paramContext);
      i += 1;
    }
  }
  
  public final void removeListener(Listener paramListener)
  {
    checkNotSealed();
    synchronized (this.listenersLock)
    {
      if (this.disabledListening) {
        throw new IllegalStateException();
      }
    }
    this.listeners = Kit.removeListener(this.listeners, paramListener);
  }
  
  public final void seal()
  {
    checkNotSealed();
    this.sealed = true;
  }
  
  public static abstract interface GlobalSetter
  {
    public abstract ContextFactory getContextFactoryGlobal();
    
    public abstract void setContextFactoryGlobal(ContextFactory paramContextFactory);
  }
  
  public static abstract interface Listener
  {
    public abstract void contextCreated(Context paramContext);
    
    public abstract void contextReleased(Context paramContext);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ContextFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */