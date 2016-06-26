package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

public class NativeJavaPackage
  extends ScriptableObject
{
  static final long serialVersionUID = 7445054382212031523L;
  private transient ClassLoader classLoader;
  private Set<String> negativeCache = null;
  private String packageName;
  
  @Deprecated
  public NativeJavaPackage(String paramString)
  {
    this(false, paramString, Context.getCurrentContext().getApplicationClassLoader());
  }
  
  @Deprecated
  public NativeJavaPackage(String paramString, ClassLoader paramClassLoader)
  {
    this(false, paramString, paramClassLoader);
  }
  
  NativeJavaPackage(boolean paramBoolean, String paramString, ClassLoader paramClassLoader)
  {
    this.packageName = paramString;
    this.classLoader = paramClassLoader;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    this.classLoader = Context.getCurrentContext().getApplicationClassLoader();
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof NativeJavaPackage))
    {
      paramObject = (NativeJavaPackage)paramObject;
      bool1 = bool2;
      if (this.packageName.equals(((NativeJavaPackage)paramObject).packageName))
      {
        bool1 = bool2;
        if (this.classLoader == ((NativeJavaPackage)paramObject).classLoader) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  NativeJavaPackage forcePackage(String paramString, Scriptable paramScriptable)
  {
    Object localObject = super.get(paramString, this);
    if ((localObject != null) && ((localObject instanceof NativeJavaPackage))) {
      return (NativeJavaPackage)localObject;
    }
    if (this.packageName.length() == 0) {}
    for (localObject = paramString;; localObject = this.packageName + "." + paramString)
    {
      localObject = new NativeJavaPackage(true, (String)localObject, this.classLoader);
      ScriptRuntime.setObjectProtoAndParent((ScriptableObject)localObject, paramScriptable);
      super.put(paramString, this, localObject);
      return (NativeJavaPackage)localObject;
    }
  }
  
  public Object get(int paramInt, Scriptable paramScriptable)
  {
    return NOT_FOUND;
  }
  
  public Object get(String paramString, Scriptable paramScriptable)
  {
    return getPkgProperty(paramString, paramScriptable, true);
  }
  
  public String getClassName()
  {
    return "JavaPackage";
  }
  
  public Object getDefaultValue(Class<?> paramClass)
  {
    return toString();
  }
  
  Object getPkgProperty(String paramString, Scriptable paramScriptable, boolean paramBoolean)
  {
    for (;;)
    {
      String str;
      try
      {
        localObject2 = super.get(paramString, paramScriptable);
        localObject1 = NOT_FOUND;
        if (localObject2 != localObject1) {
          return localObject2;
        }
        if ((this.negativeCache != null) && (this.negativeCache.contains(paramString)))
        {
          localObject2 = null;
          continue;
        }
        if (this.packageName.length() == 0)
        {
          str = paramString;
          localObject2 = Context.getContext();
          localObject1 = ((Context)localObject2).getClassShutter();
          if ((localObject1 != null) && (!((ClassShutter)localObject1).visibleToScripts(str))) {
            break label291;
          }
          if (this.classLoader == null) {
            break label245;
          }
          localObject1 = Kit.classOrNull(this.classLoader, str);
          if (localObject1 == null) {
            break label291;
          }
          localObject2 = ((Context)localObject2).getWrapFactory().wrapJavaClass((Context)localObject2, getTopLevelScope(this), (Class)localObject1);
          ((Scriptable)localObject2).setPrototype(getPrototype());
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            if (!paramBoolean) {
              break label255;
            }
            localObject1 = new NativeJavaPackage(true, str, this.classLoader);
            ScriptRuntime.setObjectProtoAndParent((ScriptableObject)localObject1, getParentScope());
          }
          localObject2 = localObject1;
          if (localObject1 == null) {
            continue;
          }
          super.put(paramString, paramScriptable, localObject1);
          localObject2 = localObject1;
          continue;
        }
        str = this.packageName + '.' + paramString;
      }
      finally {}
      continue;
      label245:
      Object localObject1 = Kit.classOrNull(str);
      continue;
      label255:
      if (this.negativeCache == null) {
        this.negativeCache = new HashSet();
      }
      this.negativeCache.add(paramString);
      localObject1 = localObject2;
      continue;
      label291:
      Object localObject2 = null;
    }
  }
  
  public boolean has(int paramInt, Scriptable paramScriptable)
  {
    return false;
  }
  
  public boolean has(String paramString, Scriptable paramScriptable)
  {
    return true;
  }
  
  public int hashCode()
  {
    int j = this.packageName.hashCode();
    if (this.classLoader == null) {}
    for (int i = 0;; i = this.classLoader.hashCode()) {
      return i ^ j;
    }
  }
  
  public void put(int paramInt, Scriptable paramScriptable, Object paramObject)
  {
    throw Context.reportRuntimeError0("msg.pkg.int");
  }
  
  public void put(String paramString, Scriptable paramScriptable, Object paramObject) {}
  
  public String toString()
  {
    return "[JavaPackage " + this.packageName + "]";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeJavaPackage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */