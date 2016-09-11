package org.mozilla.javascript;

final class Arguments
  extends IdScriptableObject
{
  private static final String FTAG = "Arguments";
  private static final int Id_callee = 1;
  private static final int Id_caller = 3;
  private static final int Id_length = 2;
  private static final int MAX_INSTANCE_ID = 3;
  private static BaseFunction iteratorMethod = new BaseFunction()
  {
    public Object call(Context paramAnonymousContext, Scriptable paramAnonymousScriptable1, Scriptable paramAnonymousScriptable2, Object[] paramAnonymousArrayOfObject)
    {
      return new NativeArrayIterator(paramAnonymousScriptable1, paramAnonymousScriptable2);
    }
  };
  static final long serialVersionUID = 4275508002492040609L;
  private NativeCall activation;
  private Object[] args;
  private int calleeAttr = 2;
  private Object calleeObj;
  private int callerAttr = 2;
  private Object callerObj;
  private int lengthAttr = 2;
  private Object lengthObj;
  
  public Arguments(NativeCall paramNativeCall)
  {
    this.activation = paramNativeCall;
    Scriptable localScriptable = paramNativeCall.getParentScope();
    setParentScope(localScriptable);
    setPrototype(ScriptableObject.getObjectPrototype(localScriptable));
    this.args = paramNativeCall.originalArgs;
    this.lengthObj = Integer.valueOf(this.args.length);
    paramNativeCall = paramNativeCall.function;
    this.calleeObj = paramNativeCall;
    int i = paramNativeCall.getLanguageVersion();
    if ((i <= 130) && (i != 0)) {}
    for (this.callerObj = null;; this.callerObj = NOT_FOUND)
    {
      defineProperty("@@iterator", iteratorMethod, 2);
      return;
    }
  }
  
  private Object arg(int paramInt)
  {
    if ((paramInt < 0) || (this.args.length <= paramInt)) {
      return NOT_FOUND;
    }
    return this.args[paramInt];
  }
  
  private Object getFromActivation(int paramInt)
  {
    String str = this.activation.function.getParamOrVarName(paramInt);
    return this.activation.get(str, this.activation);
  }
  
  private void putIntoActivation(int paramInt, Object paramObject)
  {
    String str = this.activation.function.getParamOrVarName(paramInt);
    this.activation.put(str, this.activation, paramObject);
  }
  
  private void removeArg(int paramInt)
  {
    try
    {
      if (this.args[paramInt] != NOT_FOUND)
      {
        if (this.args == this.activation.originalArgs) {
          this.args = ((Object[])this.args.clone());
        }
        this.args[paramInt] = NOT_FOUND;
      }
      return;
    }
    finally {}
  }
  
  private void replaceArg(int paramInt, Object paramObject)
  {
    if (sharedWithActivation(paramInt)) {
      putIntoActivation(paramInt, paramObject);
    }
    try
    {
      if (this.args == this.activation.originalArgs) {
        this.args = ((Object[])this.args.clone());
      }
      this.args[paramInt] = paramObject;
      return;
    }
    finally {}
  }
  
  private boolean sharedWithActivation(int paramInt)
  {
    if (Context.getContext().isStrictMode()) {}
    NativeFunction localNativeFunction;
    int i;
    do
    {
      return false;
      localNativeFunction = this.activation.function;
      i = localNativeFunction.getParamCount();
    } while (paramInt >= i);
    if (paramInt < i - 1)
    {
      String str = localNativeFunction.getParamOrVarName(paramInt);
      paramInt += 1;
      for (;;)
      {
        if (paramInt >= i) {
          break label72;
        }
        if (str.equals(localNativeFunction.getParamOrVarName(paramInt))) {
          break;
        }
        paramInt += 1;
      }
    }
    label72:
    return true;
  }
  
  void defineAttributesForStrictMode()
  {
    if (!Context.getContext().isStrictMode()) {
      return;
    }
    setGetterOrSetter("caller", 0, new ThrowTypeError("caller"), true);
    setGetterOrSetter("caller", 0, new ThrowTypeError("caller"), false);
    setGetterOrSetter("callee", 0, new ThrowTypeError("callee"), true);
    setGetterOrSetter("callee", 0, new ThrowTypeError("callee"), false);
    setAttributes("caller", 6);
    setAttributes("callee", 6);
    this.callerObj = null;
    this.calleeObj = null;
  }
  
  protected void defineOwnProperty(Context paramContext, Object paramObject, ScriptableObject paramScriptableObject, boolean paramBoolean)
  {
    super.defineOwnProperty(paramContext, paramObject, paramScriptableObject, paramBoolean);
    double d = ScriptRuntime.toNumber(paramObject);
    int i = (int)d;
    if (d != i) {}
    do
    {
      do
      {
        do
        {
          return;
        } while (arg(i) == NOT_FOUND);
        if (isAccessorDescriptor(paramScriptableObject))
        {
          removeArg(i);
          return;
        }
        paramContext = getProperty(paramScriptableObject, "value");
      } while (paramContext == NOT_FOUND);
      replaceArg(i, paramContext);
    } while (!isFalse(getProperty(paramScriptableObject, "writable")));
    removeArg(i);
  }
  
  public void delete(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.args.length)) {
      removeArg(paramInt);
    }
    super.delete(paramInt);
  }
  
  protected int findInstanceIdInfo(String paramString)
  {
    int j = 0;
    int i;
    String str;
    if (paramString.length() == 6)
    {
      i = paramString.charAt(5);
      if (i == 101)
      {
        str = "callee";
        i = 1;
      }
    }
    for (;;)
    {
      if ((str != null) && (str != paramString) && (!str.equals(paramString))) {}
      for (;;)
      {
        if ((Context.getContext().isStrictMode()) && ((j == 1) || (j == 3)))
        {
          return super.findInstanceIdInfo(paramString);
          if (i == 104)
          {
            i = 2;
            str = "length";
            break;
          }
          if (i != 114) {
            break label180;
          }
          str = "caller";
          i = 3;
          break;
        }
        if (j == 0) {
          return super.findInstanceIdInfo(paramString);
        }
        switch (j)
        {
        default: 
          throw new IllegalStateException();
        case 1: 
          i = this.calleeAttr;
        }
        for (;;)
        {
          return instanceIdInfo(i, j);
          i = this.callerAttr;
          continue;
          i = this.lengthAttr;
        }
        j = i;
      }
      label180:
      str = null;
      i = 0;
    }
  }
  
  public Object get(int paramInt, Scriptable paramScriptable)
  {
    Object localObject = arg(paramInt);
    if (localObject == NOT_FOUND) {
      paramScriptable = super.get(paramInt, paramScriptable);
    }
    do
    {
      return paramScriptable;
      paramScriptable = (Scriptable)localObject;
    } while (!sharedWithActivation(paramInt));
    return getFromActivation(paramInt);
  }
  
  public String getClassName()
  {
    return "Arguments";
  }
  
  Object[] getIds(boolean paramBoolean)
  {
    int m = 0;
    Object[] arrayOfObject = super.getIds(paramBoolean);
    if (this.args.length != 0)
    {
      boolean[] arrayOfBoolean = new boolean[this.args.length];
      int i = this.args.length;
      int j = 0;
      Object localObject;
      int k;
      while (j != arrayOfObject.length)
      {
        localObject = arrayOfObject[j];
        k = i;
        if ((localObject instanceof Integer))
        {
          int n = ((Integer)localObject).intValue();
          k = i;
          if (n >= 0)
          {
            k = i;
            if (n < this.args.length)
            {
              k = i;
              if (arrayOfBoolean[n] == 0)
              {
                arrayOfBoolean[n] = true;
                k = i - 1;
              }
            }
          }
        }
        j += 1;
        i = k;
      }
      j = i;
      if (!paramBoolean)
      {
        k = 0;
        for (;;)
        {
          j = i;
          if (k >= arrayOfBoolean.length) {
            break;
          }
          j = i;
          if (arrayOfBoolean[k] == 0)
          {
            j = i;
            if (super.has(k, this))
            {
              arrayOfBoolean[k] = true;
              j = i - 1;
            }
          }
          k += 1;
          i = j;
        }
      }
      if (j != 0)
      {
        localObject = new Object[arrayOfObject.length + j];
        System.arraycopy(arrayOfObject, 0, localObject, j, arrayOfObject.length);
        k = 0;
        i = m;
        while (i != this.args.length)
        {
          if (arrayOfBoolean != null)
          {
            m = k;
            if (arrayOfBoolean[i] != 0) {}
          }
          else
          {
            localObject[k] = Integer.valueOf(i);
            m = k + 1;
          }
          i += 1;
          k = m;
        }
        if (k != j) {
          Kit.codeBug();
        }
        return (Object[])localObject;
      }
    }
    return arrayOfObject;
  }
  
  protected String getInstanceIdName(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 1: 
      return "callee";
    case 2: 
      return "length";
    }
    return "caller";
  }
  
  protected Object getInstanceIdValue(int paramInt)
  {
    Object localObject1;
    switch (paramInt)
    {
    default: 
      localObject1 = super.getInstanceIdValue(paramInt);
    }
    NativeCall localNativeCall;
    do
    {
      Object localObject2;
      do
      {
        return localObject1;
        return this.calleeObj;
        return this.lengthObj;
        localObject2 = this.callerObj;
        if (localObject2 == UniqueTag.NULL_VALUE) {
          return null;
        }
        localObject1 = localObject2;
      } while (localObject2 != null);
      localNativeCall = this.activation.parentActivationCall;
      localObject1 = localObject2;
    } while (localNativeCall == null);
    return localNativeCall.get("arguments", localNativeCall);
  }
  
  protected int getMaxInstanceId()
  {
    return 3;
  }
  
  protected ScriptableObject getOwnPropertyDescriptor(Context paramContext, Object paramObject)
  {
    double d = ScriptRuntime.toNumber(paramObject);
    int i = (int)d;
    if (d != i) {
      return super.getOwnPropertyDescriptor(paramContext, paramObject);
    }
    Object localObject = arg(i);
    if (localObject == NOT_FOUND) {
      return super.getOwnPropertyDescriptor(paramContext, paramObject);
    }
    if (sharedWithActivation(i)) {
      localObject = getFromActivation(i);
    }
    if (super.has(i, this))
    {
      paramContext = super.getOwnPropertyDescriptor(paramContext, paramObject);
      paramContext.put("value", paramContext, localObject);
      return paramContext;
    }
    paramContext = getParentScope();
    if (paramContext == null) {
      paramContext = this;
    }
    for (;;)
    {
      return buildDataDescriptor(paramContext, localObject, 0);
    }
  }
  
  public boolean has(int paramInt, Scriptable paramScriptable)
  {
    if (arg(paramInt) != NOT_FOUND) {
      return true;
    }
    return super.has(paramInt, paramScriptable);
  }
  
  public void put(int paramInt, Scriptable paramScriptable, Object paramObject)
  {
    if (arg(paramInt) == NOT_FOUND)
    {
      super.put(paramInt, paramScriptable, paramObject);
      return;
    }
    replaceArg(paramInt, paramObject);
  }
  
  public void put(String paramString, Scriptable paramScriptable, Object paramObject)
  {
    super.put(paramString, paramScriptable, paramObject);
  }
  
  protected void setInstanceIdAttributes(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      super.setInstanceIdAttributes(paramInt1, paramInt2);
      return;
    case 1: 
      this.calleeAttr = paramInt2;
      return;
    case 2: 
      this.lengthAttr = paramInt2;
      return;
    }
    this.callerAttr = paramInt2;
  }
  
  protected void setInstanceIdValue(int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default: 
      super.setInstanceIdValue(paramInt, paramObject);
      return;
    case 1: 
      this.calleeObj = paramObject;
      return;
    case 2: 
      this.lengthObj = paramObject;
      return;
    }
    if (paramObject != null) {}
    for (;;)
    {
      this.callerObj = paramObject;
      return;
      paramObject = UniqueTag.NULL_VALUE;
    }
  }
  
  private static class ThrowTypeError
    extends BaseFunction
  {
    private String propertyName;
    
    ThrowTypeError(String paramString)
    {
      this.propertyName = paramString;
    }
    
    public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
    {
      throw ScriptRuntime.typeError1("msg.arguments.not.access.strict", this.propertyName);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\Arguments.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */