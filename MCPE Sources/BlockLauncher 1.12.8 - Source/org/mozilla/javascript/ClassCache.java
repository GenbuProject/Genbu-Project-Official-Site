package org.mozilla.javascript;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClassCache
  implements Serializable
{
  private static final Object AKEY = "ClassCache";
  private static final long serialVersionUID = -8866246036237312215L;
  private Scriptable associatedScope;
  private volatile boolean cachingIsEnabled = true;
  private transient Map<JavaAdapter.JavaAdapterSignature, Class<?>> classAdapterCache;
  private transient Map<Class<?>, JavaMembers> classTable;
  private int generatedClassSerial;
  private transient Map<Class<?>, Object> interfaceAdapterCache;
  
  public static ClassCache get(Scriptable paramScriptable)
  {
    paramScriptable = (ClassCache)ScriptableObject.getTopScopeValue(paramScriptable, AKEY);
    if (paramScriptable == null) {
      throw new RuntimeException("Can't find top level scope for ClassCache.get");
    }
    return paramScriptable;
  }
  
  public boolean associate(ScriptableObject paramScriptableObject)
  {
    if (paramScriptableObject.getParentScope() != null) {
      throw new IllegalArgumentException();
    }
    if (this == paramScriptableObject.associateValue(AKEY, this))
    {
      this.associatedScope = paramScriptableObject;
      return true;
    }
    return false;
  }
  
  void cacheInterfaceAdapter(Class<?> paramClass, Object paramObject)
  {
    try
    {
      if (this.cachingIsEnabled)
      {
        if (this.interfaceAdapterCache == null) {
          this.interfaceAdapterCache = new ConcurrentHashMap(16, 0.75F, 1);
        }
        this.interfaceAdapterCache.put(paramClass, paramObject);
      }
      return;
    }
    finally {}
  }
  
  public void clearCaches()
  {
    try
    {
      this.classTable = null;
      this.classAdapterCache = null;
      this.interfaceAdapterCache = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  Scriptable getAssociatedScope()
  {
    return this.associatedScope;
  }
  
  Map<Class<?>, JavaMembers> getClassCacheMap()
  {
    if (this.classTable == null) {
      this.classTable = new ConcurrentHashMap(16, 0.75F, 1);
    }
    return this.classTable;
  }
  
  Object getInterfaceAdapter(Class<?> paramClass)
  {
    if (this.interfaceAdapterCache == null) {
      return null;
    }
    return this.interfaceAdapterCache.get(paramClass);
  }
  
  Map<JavaAdapter.JavaAdapterSignature, Class<?>> getInterfaceAdapterCacheMap()
  {
    if (this.classAdapterCache == null) {
      this.classAdapterCache = new ConcurrentHashMap(16, 0.75F, 1);
    }
    return this.classAdapterCache;
  }
  
  public final boolean isCachingEnabled()
  {
    return this.cachingIsEnabled;
  }
  
  @Deprecated
  public boolean isInvokerOptimizationEnabled()
  {
    return false;
  }
  
  public final int newClassSerialNumber()
  {
    try
    {
      int i = this.generatedClassSerial + 1;
      this.generatedClassSerial = i;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public void setCachingEnabled(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 37	org/mozilla/javascript/ClassCache:cachingIsEnabled	Z
    //   6: istore_2
    //   7: iload_1
    //   8: iload_2
    //   9: if_icmpne +6 -> 15
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: iload_1
    //   16: ifne +7 -> 23
    //   19: aload_0
    //   20: invokevirtual 114	org/mozilla/javascript/ClassCache:clearCaches	()V
    //   23: aload_0
    //   24: iload_1
    //   25: putfield 37	org/mozilla/javascript/ClassCache:cachingIsEnabled	Z
    //   28: goto -16 -> 12
    //   31: astore_3
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_3
    //   35: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	36	0	this	ClassCache
    //   0	36	1	paramBoolean	boolean
    //   6	4	2	bool	boolean
    //   31	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	31	finally
    //   19	23	31	finally
    //   23	28	31	finally
  }
  
  @Deprecated
  public void setInvokerOptimizationEnabled(boolean paramBoolean) {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ClassCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */