package org.mozilla.javascript;

public final class NativeGenerator
  extends IdScriptableObject
{
  public static final int GENERATOR_CLOSE = 2;
  public static final int GENERATOR_SEND = 0;
  private static final Object GENERATOR_TAG = "Generator";
  public static final int GENERATOR_THROW = 1;
  private static final int Id___iterator__ = 5;
  private static final int Id_close = 1;
  private static final int Id_next = 2;
  private static final int Id_send = 3;
  private static final int Id_throw = 4;
  private static final int MAX_PROTOTYPE_ID = 5;
  private static final long serialVersionUID = 1645892441041347273L;
  private boolean firstTime = true;
  private NativeFunction function;
  private int lineNumber;
  private String lineSource;
  private boolean locked;
  private Object savedState;
  
  private NativeGenerator() {}
  
  public NativeGenerator(Scriptable paramScriptable, NativeFunction paramNativeFunction, Object paramObject)
  {
    this.function = paramNativeFunction;
    this.savedState = paramObject;
    paramScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    setParentScope(paramScriptable);
    setPrototype((NativeGenerator)ScriptableObject.getTopScopeValue(paramScriptable, GENERATOR_TAG));
  }
  
  static NativeGenerator init(ScriptableObject paramScriptableObject, boolean paramBoolean)
  {
    NativeGenerator localNativeGenerator = new NativeGenerator();
    if (paramScriptableObject != null)
    {
      localNativeGenerator.setParentScope(paramScriptableObject);
      localNativeGenerator.setPrototype(getObjectPrototype(paramScriptableObject));
    }
    localNativeGenerator.activatePrototypeMap(5);
    if (paramBoolean) {
      localNativeGenerator.sealObject();
    }
    if (paramScriptableObject != null) {
      paramScriptableObject.associateValue(GENERATOR_TAG, localNativeGenerator);
    }
    return localNativeGenerator;
  }
  
  private Object resume(Context paramContext, Scriptable paramScriptable, int paramInt, Object paramObject)
  {
    if (this.savedState == null) {
      if (paramInt == 2) {
        paramContext = Undefined.instance;
      }
    }
    for (;;)
    {
      return paramContext;
      if (paramInt == 1) {}
      for (;;)
      {
        throw new JavaScriptException(paramObject, this.lineSource, this.lineNumber);
        paramObject = NativeIterator.getStopIterationObject(paramScriptable);
      }
      try
      {
        try
        {
          if (this.locked) {
            throw ScriptRuntime.typeError0("msg.already.exec.gen");
          }
        }
        finally {}
      }
      catch (GeneratorClosedException paramContext)
      {
        paramScriptable = Undefined.instance;
        try
        {
          this.locked = false;
          paramContext = paramScriptable;
          if (paramInt != 2) {
            continue;
          }
          this.savedState = null;
          return paramScriptable;
        }
        finally {}
        this.locked = true;
        paramScriptable = this.function.resumeGenerator(paramContext, paramScriptable, paramInt, this.savedState, paramObject);
        try
        {
          this.locked = false;
          paramContext = paramScriptable;
          if (paramInt != 2) {
            continue;
          }
          this.savedState = null;
          return paramScriptable;
        }
        finally {}
      }
      catch (RhinoException paramContext)
      {
        this.lineNumber = paramContext.lineNumber();
        this.lineSource = paramContext.lineSource();
        this.savedState = null;
        throw paramContext;
      }
      finally
      {
        try
        {
          this.locked = false;
          if (paramInt == 2) {
            this.savedState = null;
          }
          throw paramContext;
        }
        finally {}
      }
    }
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(GENERATOR_TAG))
    {
      paramScriptable2 = super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
      return paramScriptable2;
    }
    int i = paramIdFunctionObject.methodId();
    if (!(paramScriptable2 instanceof NativeGenerator)) {
      throw incompatibleCallError(paramIdFunctionObject);
    }
    NativeGenerator localNativeGenerator = (NativeGenerator)paramScriptable2;
    switch (i)
    {
    case 5: 
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    case 1: 
      return localNativeGenerator.resume(paramContext, paramScriptable1, 2, new GeneratorClosedException());
    case 2: 
      localNativeGenerator.firstTime = false;
      return localNativeGenerator.resume(paramContext, paramScriptable1, 0, Undefined.instance);
    case 3: 
      if (paramArrayOfObject.length > 0) {}
      for (paramIdFunctionObject = paramArrayOfObject[0]; (localNativeGenerator.firstTime) && (!paramIdFunctionObject.equals(Undefined.instance)); paramIdFunctionObject = Undefined.instance) {
        throw ScriptRuntime.typeError0("msg.send.newborn");
      }
      return localNativeGenerator.resume(paramContext, paramScriptable1, 0, paramIdFunctionObject);
    }
    if (paramArrayOfObject.length > 0) {}
    for (paramIdFunctionObject = paramArrayOfObject[0];; paramIdFunctionObject = Undefined.instance) {
      return localNativeGenerator.resume(paramContext, paramScriptable1, 1, paramIdFunctionObject);
    }
  }
  
  protected int findPrototypeId(String paramString)
  {
    int i = paramString.length();
    String str;
    if (i == 4)
    {
      i = paramString.charAt(0);
      if (i == 110)
      {
        i = 2;
        str = "next";
      }
    }
    for (;;)
    {
      if ((str != null) && (str != paramString) && (!str.equals(paramString)))
      {
        return 0;
        if (i == 115)
        {
          i = 3;
          str = "send";
          continue;
          if (i == 5)
          {
            i = paramString.charAt(0);
            if (i == 99)
            {
              i = 1;
              str = "close";
              continue;
            }
            if (i == 116)
            {
              i = 4;
              str = "throw";
            }
          }
          else if (i == 12)
          {
            str = "__iterator__";
            i = 5;
          }
        }
      }
      else
      {
        return i;
      }
      str = null;
      i = 0;
    }
  }
  
  public String getClassName()
  {
    return "Generator";
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i = 1;
    String str;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      str = "close";
    }
    for (;;)
    {
      initPrototypeMethod(GENERATOR_TAG, paramInt, str, i);
      return;
      str = "next";
      continue;
      str = "send";
      i = 0;
      continue;
      str = "throw";
      i = 0;
      continue;
      str = "__iterator__";
    }
  }
  
  public static class GeneratorClosedException
    extends RuntimeException
  {
    private static final long serialVersionUID = 2561315658662379681L;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */