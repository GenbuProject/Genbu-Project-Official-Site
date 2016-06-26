package org.mozilla.javascript;

import java.lang.reflect.Member;
import java.util.Iterator;

public abstract class VMBridge
{
  static final VMBridge instance = ;
  
  private static VMBridge makeInstance()
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "org.mozilla.javascript.VMBridge_custom";
    arrayOfString[1] = "org.mozilla.javascript.jdk15.VMBridge_jdk15";
    arrayOfString[2] = "org.mozilla.javascript.jdk13.VMBridge_jdk13";
    arrayOfString[3] = "org.mozilla.javascript.jdk11.VMBridge_jdk11";
    int i = 0;
    while (i != arrayOfString.length)
    {
      Object localObject = Kit.classOrNull(arrayOfString[i]);
      if (localObject != null)
      {
        localObject = (VMBridge)Kit.newInstanceOrNull((Class)localObject);
        if (localObject != null) {
          return (VMBridge)localObject;
        }
      }
      i += 1;
    }
    throw new IllegalStateException("Failed to create VMBridge instance");
  }
  
  protected abstract Context getContext(Object paramObject);
  
  protected abstract ClassLoader getCurrentThreadClassLoader();
  
  protected Object getInterfaceProxyHelper(ContextFactory paramContextFactory, Class<?>[] paramArrayOfClass)
  {
    throw Context.reportRuntimeError("VMBridge.getInterfaceProxyHelper is not supported");
  }
  
  public Iterator<?> getJavaIterator(Context paramContext, Scriptable paramScriptable, Object paramObject)
  {
    if ((paramObject instanceof Wrapper))
    {
      paramContext = ((Wrapper)paramObject).unwrap();
      if ((paramContext instanceof Iterator)) {
        return (Iterator)paramContext;
      }
    }
    else
    {
      return null;
    }
    return null;
  }
  
  protected abstract Object getThreadContextHelper();
  
  protected abstract boolean isVarArgs(Member paramMember);
  
  protected Object newInterfaceProxy(Object paramObject1, ContextFactory paramContextFactory, InterfaceAdapter paramInterfaceAdapter, Object paramObject2, Scriptable paramScriptable)
  {
    throw Context.reportRuntimeError("VMBridge.newInterfaceProxy is not supported");
  }
  
  protected abstract void setContext(Object paramObject, Context paramContext);
  
  protected abstract boolean tryToMakeAccessible(Object paramObject);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\VMBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */