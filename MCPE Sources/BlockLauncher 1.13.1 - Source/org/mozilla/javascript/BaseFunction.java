package org.mozilla.javascript;

public class BaseFunction
  extends IdScriptableObject
  implements Function
{
  private static final Object FUNCTION_TAG = "Function";
  private static final int Id_apply = 4;
  private static final int Id_arguments = 5;
  private static final int Id_arity = 2;
  private static final int Id_bind = 6;
  private static final int Id_call = 5;
  private static final int Id_constructor = 1;
  private static final int Id_length = 1;
  private static final int Id_name = 3;
  private static final int Id_prototype = 4;
  private static final int Id_toSource = 3;
  private static final int Id_toString = 2;
  private static final int MAX_INSTANCE_ID = 5;
  private static final int MAX_PROTOTYPE_ID = 6;
  static final long serialVersionUID = 5311394446546053859L;
  private int argumentsAttributes = 6;
  private Object argumentsObj = NOT_FOUND;
  private Object prototypeProperty;
  private int prototypePropertyAttributes = 6;
  
  public BaseFunction() {}
  
  public BaseFunction(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    super(paramScriptable1, paramScriptable2);
  }
  
  private Object getArguments()
  {
    if (defaultHas("arguments")) {}
    for (Object localObject = defaultGet("arguments"); localObject != NOT_FOUND; localObject = this.argumentsObj) {
      return localObject;
    }
    localObject = ScriptRuntime.findFunctionActivation(Context.getContext(), this);
    if (localObject == null) {
      return null;
    }
    return ((NativeCall)localObject).get("arguments", (Scriptable)localObject);
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    BaseFunction localBaseFunction = new BaseFunction();
    localBaseFunction.prototypePropertyAttributes = 7;
    localBaseFunction.exportAsJSClass(6, paramScriptable, paramBoolean);
  }
  
  static boolean isApply(IdFunctionObject paramIdFunctionObject)
  {
    return (paramIdFunctionObject.hasTag(FUNCTION_TAG)) && (paramIdFunctionObject.methodId() == 4);
  }
  
  static boolean isApplyOrCall(IdFunctionObject paramIdFunctionObject)
  {
    if (paramIdFunctionObject.hasTag(FUNCTION_TAG)) {}
    switch (paramIdFunctionObject.methodId())
    {
    default: 
      return false;
    }
    return true;
  }
  
  private static Object jsConstructor(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    int j = paramArrayOfObject.length;
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("function ");
    if (paramContext.getLanguageVersion() != 120) {
      ((StringBuilder)localObject).append("anonymous");
    }
    ((StringBuilder)localObject).append('(');
    int i = 0;
    while (i < j - 1)
    {
      if (i > 0) {
        ((StringBuilder)localObject).append(',');
      }
      ((StringBuilder)localObject).append(ScriptRuntime.toString(paramArrayOfObject[i]));
      i += 1;
    }
    ((StringBuilder)localObject).append(") {");
    if (j != 0) {
      ((StringBuilder)localObject).append(ScriptRuntime.toString(paramArrayOfObject[(j - 1)]));
    }
    ((StringBuilder)localObject).append("\n}");
    String str = ((StringBuilder)localObject).toString();
    int[] arrayOfInt = new int[1];
    localObject = Context.getSourcePositionFromStack(arrayOfInt);
    paramArrayOfObject = (Object[])localObject;
    if (localObject == null)
    {
      paramArrayOfObject = "<eval'ed string>";
      arrayOfInt[0] = 1;
    }
    localObject = ScriptRuntime.makeUrlForGeneratedScript(false, paramArrayOfObject, arrayOfInt[0]);
    paramScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    ErrorReporter localErrorReporter = DefaultErrorReporter.forEval(paramContext.getErrorReporter());
    Evaluator localEvaluator = Context.createInterpreter();
    if (localEvaluator == null) {
      throw new JavaScriptException("Interpreter not present", paramArrayOfObject, arrayOfInt[0]);
    }
    return paramContext.compileFunction(paramScriptable, str, localEvaluator, localErrorReporter, (String)localObject, 1, null);
  }
  
  private BaseFunction realFunction(Scriptable paramScriptable, IdFunctionObject paramIdFunctionObject)
  {
    Object localObject = paramScriptable.getDefaultValue(ScriptRuntime.FunctionClass);
    paramScriptable = (Scriptable)localObject;
    if ((localObject instanceof Delegator)) {
      paramScriptable = ((Delegator)localObject).getDelegee();
    }
    if ((paramScriptable instanceof BaseFunction)) {
      return (BaseFunction)paramScriptable;
    }
    throw ScriptRuntime.typeError1("msg.incompat.call", paramIdFunctionObject.getFunctionName());
  }
  
  /* Error */
  private Object setupDefaultPrototype()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 209	org/mozilla/javascript/BaseFunction:prototypeProperty	Ljava/lang/Object;
    //   6: ifnull +12 -> 18
    //   9: aload_0
    //   10: getfield 209	org/mozilla/javascript/BaseFunction:prototypeProperty	Ljava/lang/Object;
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: new 211	org/mozilla/javascript/NativeObject
    //   21: dup
    //   22: invokespecial 212	org/mozilla/javascript/NativeObject:<init>	()V
    //   25: astore_2
    //   26: aload_2
    //   27: ldc -42
    //   29: aload_0
    //   30: iconst_2
    //   31: invokevirtual 218	org/mozilla/javascript/NativeObject:defineProperty	(Ljava/lang/String;Ljava/lang/Object;I)V
    //   34: aload_0
    //   35: aload_2
    //   36: putfield 209	org/mozilla/javascript/BaseFunction:prototypeProperty	Ljava/lang/Object;
    //   39: aload_0
    //   40: invokestatic 221	org/mozilla/javascript/BaseFunction:getObjectPrototype	(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;
    //   43: astore_3
    //   44: aload_2
    //   45: astore_1
    //   46: aload_3
    //   47: aload_2
    //   48: if_acmpeq -34 -> 14
    //   51: aload_2
    //   52: aload_3
    //   53: invokevirtual 225	org/mozilla/javascript/NativeObject:setPrototype	(Lorg/mozilla/javascript/Scriptable;)V
    //   56: aload_2
    //   57: astore_1
    //   58: goto -44 -> 14
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	BaseFunction
    //   13	45	1	localObject1	Object
    //   61	4	1	localObject2	Object
    //   25	32	2	localNativeObject	NativeObject
    //   43	10	3	localScriptable	Scriptable
    // Exception table:
    //   from	to	target	type
    //   2	14	61	finally
    //   18	44	61	finally
    //   51	56	61	finally
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    return Undefined.instance;
  }
  
  public Scriptable construct(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    Scriptable localScriptable = createObject(paramContext, paramScriptable);
    if (localScriptable != null)
    {
      paramContext = call(paramContext, paramScriptable, localScriptable, paramArrayOfObject);
      if ((paramContext instanceof Scriptable)) {
        paramContext = (Scriptable)paramContext;
      }
    }
    else
    {
      do
      {
        do
        {
          return paramContext;
          paramContext = call(paramContext, paramScriptable, null, paramArrayOfObject);
          if (!(paramContext instanceof Scriptable)) {
            throw new IllegalStateException("Bad implementaion of call as constructor, name=" + getFunctionName() + " in " + getClass().getName());
          }
          paramScriptable = (Scriptable)paramContext;
          if (paramScriptable.getPrototype() == null)
          {
            paramContext = getClassPrototype();
            if (paramScriptable != paramContext) {
              paramScriptable.setPrototype(paramContext);
            }
          }
          paramContext = paramScriptable;
        } while (paramScriptable.getParentScope() != null);
        paramArrayOfObject = getParentScope();
        paramContext = paramScriptable;
      } while (paramScriptable == paramArrayOfObject);
      paramScriptable.setParentScope(paramArrayOfObject);
      return paramScriptable;
    }
    return localScriptable;
  }
  
  public Scriptable createObject(Context paramContext, Scriptable paramScriptable)
  {
    paramContext = new NativeObject();
    paramContext.setPrototype(getClassPrototype());
    paramContext.setParentScope(getParentScope());
    return paramContext;
  }
  
  String decompile(int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramInt2 & 0x1) != 0) {}
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      if (paramInt1 == 0)
      {
        localStringBuilder.append("function ");
        localStringBuilder.append(getFunctionName());
        localStringBuilder.append("() {\n\t");
      }
      localStringBuilder.append("[native code, arity=");
      localStringBuilder.append(getArity());
      localStringBuilder.append("]\n");
      if (paramInt1 == 0) {
        localStringBuilder.append("}\n");
      }
      return localStringBuilder.toString();
    }
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    boolean bool = false;
    int i = 0;
    if (!paramIdFunctionObject.hasTag(FUNCTION_TAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int j = paramIdFunctionObject.methodId();
    switch (j)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(j));
    case 1: 
      return jsConstructor(paramContext, paramScriptable1, paramArrayOfObject);
    case 2: 
      return realFunction(paramScriptable2, paramIdFunctionObject).decompile(ScriptRuntime.toInt32(paramArrayOfObject, 0), 0);
    case 3: 
      paramIdFunctionObject = realFunction(paramScriptable2, paramIdFunctionObject);
      if (paramArrayOfObject.length != 0)
      {
        j = ScriptRuntime.toInt32(paramArrayOfObject[0]);
        if (j < 0) {
          break;
        }
      }
      break;
    }
    for (;;)
    {
      return paramIdFunctionObject.decompile(j, i);
      j = 0;
      i = 2;
      continue;
      if (j == 4) {
        bool = true;
      }
      return ScriptRuntime.applyOrCall(bool, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
      if (!(paramScriptable2 instanceof Callable)) {
        throw ScriptRuntime.notFunctionError(paramScriptable2);
      }
      Callable localCallable = (Callable)paramScriptable2;
      i = paramArrayOfObject.length;
      if (i > 0)
      {
        paramIdFunctionObject = ScriptRuntime.toObjectOrNull(paramContext, paramArrayOfObject[0], paramScriptable1);
        paramScriptable2 = new Object[i - 1];
        System.arraycopy(paramArrayOfObject, 1, paramScriptable2, 0, i - 1);
      }
      for (;;)
      {
        return new BoundFunction(paramContext, paramScriptable1, localCallable, paramIdFunctionObject, paramScriptable2);
        paramIdFunctionObject = null;
        paramScriptable2 = ScriptRuntime.emptyArgs;
      }
      j = 0;
      i = 2;
    }
  }
  
  protected void fillConstructorProperties(IdFunctionObject paramIdFunctionObject)
  {
    paramIdFunctionObject.setPrototype(this);
    super.fillConstructorProperties(paramIdFunctionObject);
  }
  
  protected int findInstanceIdInfo(String paramString)
  {
    int k = 0;
    String str;
    int i;
    switch (paramString.length())
    {
    case 7: 
    case 8: 
    default: 
      str = null;
      i = 0;
    }
    int j;
    for (;;)
    {
      j = i;
      if (str != null)
      {
        j = i;
        if (str != paramString)
        {
          j = i;
          if (!str.equals(paramString)) {
            j = 0;
          }
        }
      }
      if (j != 0) {
        break label156;
      }
      i = super.findInstanceIdInfo(paramString);
      return i;
      i = 3;
      str = "name";
      continue;
      i = 2;
      str = "arity";
      continue;
      i = 1;
      str = "length";
      continue;
      i = paramString.charAt(0);
      if (i == 97)
      {
        i = 5;
        str = "arguments";
      }
      else
      {
        if (i != 112) {
          break;
        }
        i = 4;
        str = "prototype";
      }
    }
    switch (j)
    {
    default: 
      throw new IllegalStateException();
    case 1: 
    case 2: 
    case 3: 
      label156:
      i = 7;
    }
    for (;;)
    {
      return instanceIdInfo(i, j);
      i = k;
      if (!hasPrototypeProperty()) {
        break;
      }
      i = this.prototypePropertyAttributes;
      continue;
      i = this.argumentsAttributes;
    }
  }
  
  protected int findPrototypeId(String paramString)
  {
    String str;
    int i;
    switch (paramString.length())
    {
    case 6: 
    case 7: 
    case 9: 
    case 10: 
    default: 
      str = null;
      i = 0;
    }
    for (;;)
    {
      if ((str == null) || (str == paramString) || (str.equals(paramString))) {
        return i;
      }
      return 0;
      i = paramString.charAt(0);
      if (i == 98)
      {
        i = 6;
        str = "bind";
      }
      else
      {
        if (i != 99) {
          break;
        }
        i = 5;
        str = "call";
        continue;
        i = 4;
        str = "apply";
        continue;
        i = paramString.charAt(3);
        if (i == 111)
        {
          i = 3;
          str = "toSource";
        }
        else
        {
          if (i != 116) {
            break;
          }
          i = 2;
          str = "toString";
          continue;
          i = 1;
          str = "constructor";
        }
      }
    }
    return i;
  }
  
  public int getArity()
  {
    return 0;
  }
  
  public String getClassName()
  {
    return "Function";
  }
  
  protected Scriptable getClassPrototype()
  {
    Object localObject = getPrototypeProperty();
    if ((localObject instanceof Scriptable)) {
      return (Scriptable)localObject;
    }
    return ScriptableObject.getObjectPrototype(this);
  }
  
  public String getFunctionName()
  {
    return "";
  }
  
  protected String getInstanceIdName(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return super.getInstanceIdName(paramInt);
    case 1: 
      return "length";
    case 2: 
      return "arity";
    case 3: 
      return "name";
    case 4: 
      return "prototype";
    }
    return "arguments";
  }
  
  protected Object getInstanceIdValue(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return super.getInstanceIdValue(paramInt);
    case 1: 
      return ScriptRuntime.wrapInt(getLength());
    case 2: 
      return ScriptRuntime.wrapInt(getArity());
    case 3: 
      return getFunctionName();
    case 4: 
      return getPrototypeProperty();
    }
    return getArguments();
  }
  
  public int getLength()
  {
    return 0;
  }
  
  protected int getMaxInstanceId()
  {
    return 5;
  }
  
  protected Object getPrototypeProperty()
  {
    Object localObject2 = this.prototypeProperty;
    Object localObject1;
    if (localObject2 == null) {
      if ((this instanceof NativeFunction)) {
        localObject1 = setupDefaultPrototype();
      }
    }
    do
    {
      return localObject1;
      return Undefined.instance;
      localObject1 = localObject2;
    } while (localObject2 != UniqueTag.NULL_VALUE);
    return null;
  }
  
  public String getTypeOf()
  {
    if (avoidObjectDetection()) {
      return "undefined";
    }
    return "function";
  }
  
  public boolean hasInstance(Scriptable paramScriptable)
  {
    Object localObject = ScriptableObject.getProperty(this, "prototype");
    if ((localObject instanceof Scriptable)) {
      return ScriptRuntime.jsDelegatesTo(paramScriptable, (Scriptable)localObject);
    }
    throw ScriptRuntime.typeError1("msg.instanceof.bad.prototype", getFunctionName());
  }
  
  protected boolean hasPrototypeProperty()
  {
    return (this.prototypeProperty != null) || ((this instanceof NativeFunction));
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
      str = "constructor";
    }
    for (;;)
    {
      initPrototypeMethod(FUNCTION_TAG, paramInt, str, i);
      return;
      i = 0;
      str = "toString";
      continue;
      str = "toSource";
      continue;
      i = 2;
      str = "apply";
      continue;
      str = "call";
      continue;
      str = "bind";
    }
  }
  
  public void setImmunePrototypeProperty(Object paramObject)
  {
    if ((this.prototypePropertyAttributes & 0x1) != 0) {
      throw new IllegalStateException();
    }
    if (paramObject != null) {}
    for (;;)
    {
      this.prototypeProperty = paramObject;
      this.prototypePropertyAttributes = 7;
      return;
      paramObject = UniqueTag.NULL_VALUE;
    }
  }
  
  protected void setInstanceIdAttributes(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      super.setInstanceIdAttributes(paramInt1, paramInt2);
      return;
    case 4: 
      this.prototypePropertyAttributes = paramInt2;
      return;
    }
    this.argumentsAttributes = paramInt2;
  }
  
  protected void setInstanceIdValue(int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default: 
      super.setInstanceIdValue(paramInt, paramObject);
    }
    do
    {
      do
      {
        return;
      } while ((this.prototypePropertyAttributes & 0x1) != 0);
      if (paramObject != null) {}
      for (;;)
      {
        this.prototypeProperty = paramObject;
        return;
        paramObject = UniqueTag.NULL_VALUE;
      }
      if (paramObject == NOT_FOUND) {
        Kit.codeBug();
      }
      if (defaultHas("arguments"))
      {
        defaultPut("arguments", paramObject);
        return;
      }
    } while ((this.argumentsAttributes & 0x1) != 0);
    this.argumentsObj = paramObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\BaseFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */