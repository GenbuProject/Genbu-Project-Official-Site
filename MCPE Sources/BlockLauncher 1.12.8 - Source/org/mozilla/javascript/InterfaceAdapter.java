package org.mozilla.javascript;

import java.lang.reflect.Method;

public class InterfaceAdapter
{
  private final Object proxyHelper;
  
  private InterfaceAdapter(ContextFactory paramContextFactory, Class<?> paramClass)
  {
    this.proxyHelper = VMBridge.instance.getInterfaceProxyHelper(paramContextFactory, new Class[] { paramClass });
  }
  
  static Object create(Context paramContext, Class<?> paramClass, ScriptableObject paramScriptableObject)
  {
    if (!paramClass.isInterface()) {
      throw new IllegalArgumentException();
    }
    Scriptable localScriptable = ScriptRuntime.getTopCallScope(paramContext);
    ClassCache localClassCache = ClassCache.get(localScriptable);
    Object localObject = (InterfaceAdapter)localClassCache.getInterfaceAdapter(paramClass);
    ContextFactory localContextFactory = paramContext.getFactory();
    if (localObject == null)
    {
      paramContext = paramClass.getMethods();
      if ((paramScriptableObject instanceof Callable))
      {
        int j = paramContext.length;
        if (j == 0) {
          throw Context.reportRuntimeError1("msg.no.empty.interface.conversion", paramClass.getName());
        }
        if (j > 1)
        {
          localObject = paramContext[0].getName();
          int i = 1;
          while (i < j)
          {
            if (!((String)localObject).equals(paramContext[i].getName())) {
              throw Context.reportRuntimeError1("msg.no.function.interface.conversion", paramClass.getName());
            }
            i += 1;
          }
        }
      }
      paramContext = new InterfaceAdapter(localContextFactory, paramClass);
      localClassCache.cacheInterfaceAdapter(paramClass, paramContext);
    }
    for (;;)
    {
      return VMBridge.instance.newInterfaceProxy(paramContext.proxyHelper, localContextFactory, paramContext, paramScriptableObject, localScriptable);
      paramContext = (Context)localObject;
    }
  }
  
  public Object invoke(ContextFactory paramContextFactory, final Object paramObject1, final Scriptable paramScriptable, final Object paramObject2, final Method paramMethod, final Object[] paramArrayOfObject)
  {
    paramContextFactory.call(new ContextAction()
    {
      public Object run(Context paramAnonymousContext)
      {
        return InterfaceAdapter.this.invokeImpl(paramAnonymousContext, paramObject1, paramScriptable, paramObject2, paramMethod, paramArrayOfObject);
      }
    });
  }
  
  Object invokeImpl(Context paramContext, Object paramObject1, Scriptable paramScriptable, Object paramObject2, Method paramMethod, Object[] paramArrayOfObject)
  {
    Object localObject1 = null;
    Object localObject2;
    if ((paramObject1 instanceof Callable))
    {
      paramObject1 = (Callable)paramObject1;
      localObject2 = paramContext.getWrapFactory();
      if (paramArrayOfObject != null) {
        break label152;
      }
      localObject1 = ScriptRuntime.emptyArgs;
      paramContext = ((Callable)paramObject1).call(paramContext, paramScriptable, ((WrapFactory)localObject2).wrapAsJavaObject(paramContext, paramScriptable, paramObject2, null), (Object[])localObject1);
      paramObject1 = paramMethod.getReturnType();
      if (paramObject1 != Void.TYPE) {
        break label226;
      }
      paramContext = null;
    }
    for (;;)
    {
      return paramContext;
      localObject2 = (Scriptable)paramObject1;
      paramObject1 = paramMethod.getName();
      localObject2 = ScriptableObject.getProperty((Scriptable)localObject2, (String)paramObject1);
      if (localObject2 == ScriptableObject.NOT_FOUND)
      {
        Context.reportWarning(ScriptRuntime.getMessage1("msg.undefined.function.interface", paramObject1));
        paramObject1 = paramMethod.getReturnType();
        paramContext = (Context)localObject1;
        if (paramObject1 != Void.TYPE) {
          return Context.jsToJava(null, (Class)paramObject1);
        }
      }
      else
      {
        if (!(localObject2 instanceof Callable)) {
          throw Context.reportRuntimeError1("msg.not.function.interface", paramObject1);
        }
        paramObject1 = (Callable)localObject2;
        break;
        label152:
        int i = 0;
        int j = paramArrayOfObject.length;
        for (;;)
        {
          localObject1 = paramArrayOfObject;
          if (i == j) {
            break;
          }
          localObject1 = paramArrayOfObject[i];
          if ((!(localObject1 instanceof String)) && (!(localObject1 instanceof Number)) && (!(localObject1 instanceof Boolean))) {
            paramArrayOfObject[i] = ((WrapFactory)localObject2).wrap(paramContext, paramScriptable, localObject1, null);
          }
          i += 1;
        }
        label226:
        paramContext = Context.jsToJava(paramContext, (Class)paramObject1);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\InterfaceAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */