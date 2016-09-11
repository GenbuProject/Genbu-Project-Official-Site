package org.mozilla.javascript;

import java.io.Serializable;
import java.lang.reflect.Method;

final class NativeError
  extends IdScriptableObject
{
  private static final int ConstructorId_captureStackTrace = -1;
  public static final int DEFAULT_STACK_LIMIT = -1;
  private static final Method ERROR_DELEGATE_GET_STACK;
  private static final Method ERROR_DELEGATE_SET_STACK;
  private static final Object ERROR_TAG = "Error";
  private static final int Id_constructor = 1;
  private static final int Id_toSource = 3;
  private static final int Id_toString = 2;
  private static final int MAX_PROTOTYPE_ID = 3;
  private static final String STACK_HIDE_KEY = "_stackHide";
  static final long serialVersionUID = -5338413581437645187L;
  private RhinoException stackProvider;
  
  static
  {
    try
    {
      ERROR_DELEGATE_GET_STACK = NativeError.class.getMethod("getStackDelegated", new Class[] { Scriptable.class });
      ERROR_DELEGATE_SET_STACK = NativeError.class.getMethod("setStackDelegated", new Class[] { Scriptable.class, Object.class });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new RuntimeException(localNoSuchMethodException);
    }
  }
  
  private Object callPrepareStack(Function paramFunction, ScriptStackElement[] paramArrayOfScriptStackElement)
  {
    Context localContext = Context.getCurrentContext();
    Object[] arrayOfObject = new Object[paramArrayOfScriptStackElement.length];
    int i = 0;
    while (i < paramArrayOfScriptStackElement.length)
    {
      NativeCallSite localNativeCallSite = (NativeCallSite)localContext.newObject(this, "CallSite");
      localNativeCallSite.setElement(paramArrayOfScriptStackElement[i]);
      arrayOfObject[i] = localNativeCallSite;
      i += 1;
    }
    return paramFunction.call(localContext, paramFunction, this, new Object[] { this, localContext.newArray(this, arrayOfObject) });
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    NativeError localNativeError = new NativeError();
    ScriptableObject.putProperty(localNativeError, "name", "Error");
    ScriptableObject.putProperty(localNativeError, "message", "");
    ScriptableObject.putProperty(localNativeError, "fileName", "");
    ScriptableObject.putProperty(localNativeError, "lineNumber", Integer.valueOf(0));
    localNativeError.setAttributes("name", 2);
    localNativeError.setAttributes("message", 2);
    localNativeError.exportAsJSClass(3, paramScriptable, paramBoolean);
    NativeCallSite.init(localNativeError, paramBoolean);
  }
  
  private static void js_captureStackTrace(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    ScriptableObject localScriptableObject = (ScriptableObject)ScriptRuntime.toObjectOrNull(paramContext, paramArrayOfObject[0], paramScriptable);
    Function localFunction = null;
    if (paramArrayOfObject.length > 1) {
      localFunction = (Function)ScriptRuntime.toObjectOrNull(paramContext, paramArrayOfObject[1], paramScriptable);
    }
    paramContext = (NativeError)paramContext.newObject(paramScriptable, "Error");
    paramContext.setStackProvider(new EvaluatorException("[object Object]"));
    if (localFunction != null)
    {
      paramScriptable = localFunction.get("name", localFunction);
      if ((paramScriptable != null) && (!Undefined.instance.equals(paramScriptable))) {
        paramContext.associateValue("_stackHide", Context.toString(paramScriptable));
      }
    }
    localScriptableObject.defineProperty("stack", paramContext, ERROR_DELEGATE_GET_STACK, ERROR_DELEGATE_SET_STACK, 0);
  }
  
  private static String js_toSource(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    Object localObject3 = ScriptableObject.getProperty(paramScriptable2, "name");
    Object localObject2 = ScriptableObject.getProperty(paramScriptable2, "message");
    Object localObject1 = ScriptableObject.getProperty(paramScriptable2, "fileName");
    Object localObject4 = ScriptableObject.getProperty(paramScriptable2, "lineNumber");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("(new ");
    paramScriptable2 = (Scriptable)localObject3;
    if (localObject3 == NOT_FOUND) {
      paramScriptable2 = Undefined.instance;
    }
    localStringBuilder.append(ScriptRuntime.toString(paramScriptable2));
    localStringBuilder.append("(");
    if ((localObject2 != NOT_FOUND) || (localObject1 != NOT_FOUND) || (localObject4 != NOT_FOUND)) {
      if (localObject2 != NOT_FOUND) {
        break label233;
      }
    }
    label227:
    label233:
    for (paramScriptable2 = "";; paramScriptable2 = (Scriptable)localObject2)
    {
      localStringBuilder.append(ScriptRuntime.uneval(paramContext, paramScriptable1, paramScriptable2));
      if ((localObject1 != NOT_FOUND) || (localObject4 != NOT_FOUND))
      {
        localStringBuilder.append(", ");
        if (localObject1 != NOT_FOUND) {
          break label227;
        }
      }
      for (paramScriptable2 = "";; paramScriptable2 = (Scriptable)localObject1)
      {
        localStringBuilder.append(ScriptRuntime.uneval(paramContext, paramScriptable1, paramScriptable2));
        if (localObject4 != NOT_FOUND)
        {
          int i = ScriptRuntime.toInt32(localObject4);
          if (i != 0)
          {
            localStringBuilder.append(", ");
            localStringBuilder.append(ScriptRuntime.toString(i));
          }
        }
        localStringBuilder.append("))");
        return localStringBuilder.toString();
      }
    }
  }
  
  private static Object js_toString(Scriptable paramScriptable)
  {
    Object localObject = ScriptableObject.getProperty(paramScriptable, "name");
    if ((localObject == NOT_FOUND) || (localObject == Undefined.instance))
    {
      localObject = "Error";
      paramScriptable = ScriptableObject.getProperty(paramScriptable, "message");
      if ((paramScriptable != NOT_FOUND) && (paramScriptable != Undefined.instance)) {
        break label68;
      }
    }
    label68:
    for (paramScriptable = "";; paramScriptable = ScriptRuntime.toString(paramScriptable))
    {
      if (localObject.toString().length() != 0) {
        break label76;
      }
      return paramScriptable;
      localObject = ScriptRuntime.toString(localObject);
      break;
    }
    label76:
    if (paramScriptable.toString().length() == 0) {
      return localObject;
    }
    return (String)localObject + ": " + (String)paramScriptable;
  }
  
  static NativeError make(Context paramContext, Scriptable paramScriptable, IdFunctionObject paramIdFunctionObject, Object[] paramArrayOfObject)
  {
    paramContext = (Scriptable)paramIdFunctionObject.get("prototype", paramIdFunctionObject);
    paramIdFunctionObject = new NativeError();
    paramIdFunctionObject.setPrototype(paramContext);
    paramIdFunctionObject.setParentScope(paramScriptable);
    int i = paramArrayOfObject.length;
    if (i >= 1)
    {
      if (paramArrayOfObject[0] != Undefined.instance) {
        ScriptableObject.putProperty(paramIdFunctionObject, "message", ScriptRuntime.toString(paramArrayOfObject[0]));
      }
      if (i >= 2)
      {
        ScriptableObject.putProperty(paramIdFunctionObject, "fileName", paramArrayOfObject[1]);
        if (i >= 3) {
          ScriptableObject.putProperty(paramIdFunctionObject, "lineNumber", Integer.valueOf(ScriptRuntime.toInt32(paramArrayOfObject[2])));
        }
      }
    }
    return paramIdFunctionObject;
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(ERROR_TAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i = paramIdFunctionObject.methodId();
    switch (i)
    {
    case 0: 
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    case 1: 
      return make(paramContext, paramScriptable1, paramIdFunctionObject, paramArrayOfObject);
    case 2: 
      return js_toString(paramScriptable2);
    case 3: 
      return js_toSource(paramContext, paramScriptable1, paramScriptable2);
    }
    js_captureStackTrace(paramContext, paramScriptable2, paramArrayOfObject);
    return Undefined.instance;
  }
  
  protected void fillConstructorProperties(IdFunctionObject paramIdFunctionObject)
  {
    addIdFunctionProperty(paramIdFunctionObject, ERROR_TAG, -1, "captureStackTrace", 2);
    ProtoProps localProtoProps = new ProtoProps(null);
    associateValue("_ErrorPrototypeProps", localProtoProps);
    paramIdFunctionObject.defineProperty("stackTraceLimit", localProtoProps, ProtoProps.GET_STACK_LIMIT, ProtoProps.SET_STACK_LIMIT, 0);
    paramIdFunctionObject.defineProperty("prepareStackTrace", localProtoProps, ProtoProps.GET_PREPARE_STACK, ProtoProps.SET_PREPARE_STACK, 0);
    super.fillConstructorProperties(paramIdFunctionObject);
  }
  
  protected int findPrototypeId(String paramString)
  {
    int i = paramString.length();
    String str;
    if (i == 8)
    {
      i = paramString.charAt(3);
      if (i == 111)
      {
        i = 3;
        str = "toSource";
      }
    }
    for (;;)
    {
      if ((str != null) && (str != paramString) && (!str.equals(paramString)))
      {
        return 0;
        if (i == 116)
        {
          i = 2;
          str = "toString";
          continue;
          if (i == 11)
          {
            i = 1;
            str = "constructor";
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
    return "Error";
  }
  
  public Object getStackDelegated(Scriptable paramScriptable)
  {
    if (this.stackProvider == null) {
      return NOT_FOUND;
    }
    int i = -1;
    Object localObject1 = null;
    Object localObject2 = (ProtoProps)((NativeError)getPrototype()).getAssociatedValue("_ErrorPrototypeProps");
    if (localObject2 != null)
    {
      i = ((ProtoProps)localObject2).getStackTraceLimit();
      localObject1 = ((ProtoProps)localObject2).getPrepareStackTrace();
    }
    localObject2 = (String)getAssociatedValue("_stackHide");
    localObject2 = this.stackProvider.getScriptStack(i, (String)localObject2);
    if (localObject1 == null) {}
    for (localObject1 = RhinoException.formatStackTrace((ScriptStackElement[])localObject2, this.stackProvider.details());; localObject1 = callPrepareStack((Function)localObject1, (ScriptStackElement[])localObject2))
    {
      setStackDelegated(paramScriptable, localObject1);
      return localObject1;
    }
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i = 0;
    String str;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      i = 1;
      str = "constructor";
    }
    for (;;)
    {
      initPrototypeMethod(ERROR_TAG, paramInt, str, i);
      return;
      str = "toString";
      continue;
      str = "toSource";
    }
  }
  
  public void setStackDelegated(Scriptable paramScriptable, Object paramObject)
  {
    paramScriptable.delete("stack");
    this.stackProvider = null;
    paramScriptable.put("stack", paramScriptable, paramObject);
  }
  
  public void setStackProvider(RhinoException paramRhinoException)
  {
    if (this.stackProvider == null)
    {
      this.stackProvider = paramRhinoException;
      defineProperty("stack", this, ERROR_DELEGATE_GET_STACK, ERROR_DELEGATE_SET_STACK, 2);
    }
  }
  
  public String toString()
  {
    Object localObject = js_toString(this);
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    return super.toString();
  }
  
  private static final class ProtoProps
    implements Serializable
  {
    static final Method GET_PREPARE_STACK;
    static final Method GET_STACK_LIMIT;
    static final String KEY = "_ErrorPrototypeProps";
    static final Method SET_PREPARE_STACK;
    static final Method SET_STACK_LIMIT;
    private static final long serialVersionUID = 1907180507775337939L;
    private Function prepareStackTrace;
    private int stackTraceLimit = -1;
    
    static
    {
      try
      {
        GET_STACK_LIMIT = ProtoProps.class.getMethod("getStackTraceLimit", new Class[] { Scriptable.class });
        SET_STACK_LIMIT = ProtoProps.class.getMethod("setStackTraceLimit", new Class[] { Scriptable.class, Object.class });
        GET_PREPARE_STACK = ProtoProps.class.getMethod("getPrepareStackTrace", new Class[] { Scriptable.class });
        SET_PREPARE_STACK = ProtoProps.class.getMethod("setPrepareStackTrace", new Class[] { Scriptable.class, Object.class });
        return;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        throw new RuntimeException(localNoSuchMethodException);
      }
    }
    
    public Object getPrepareStackTrace(Scriptable paramScriptable)
    {
      Function localFunction = getPrepareStackTrace();
      paramScriptable = localFunction;
      if (localFunction == null) {
        paramScriptable = Undefined.instance;
      }
      return paramScriptable;
    }
    
    public Function getPrepareStackTrace()
    {
      return this.prepareStackTrace;
    }
    
    public int getStackTraceLimit()
    {
      return this.stackTraceLimit;
    }
    
    public Object getStackTraceLimit(Scriptable paramScriptable)
    {
      if (this.stackTraceLimit >= 0) {
        return Integer.valueOf(this.stackTraceLimit);
      }
      return Double.valueOf(Double.POSITIVE_INFINITY);
    }
    
    public void setPrepareStackTrace(Scriptable paramScriptable, Object paramObject)
    {
      if ((paramObject == null) || (Undefined.instance.equals(paramObject))) {
        this.prepareStackTrace = null;
      }
      while (!(paramObject instanceof Function)) {
        return;
      }
      this.prepareStackTrace = ((Function)paramObject);
    }
    
    public void setStackTraceLimit(Scriptable paramScriptable, Object paramObject)
    {
      double d = Context.toNumber(paramObject);
      if ((Double.isNaN(d)) || (Double.isInfinite(d)))
      {
        this.stackTraceLimit = -1;
        return;
      }
      this.stackTraceLimit = ((int)d);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */