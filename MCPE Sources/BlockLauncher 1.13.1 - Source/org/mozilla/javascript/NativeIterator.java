package org.mozilla.javascript;

import java.util.Iterator;

public final class NativeIterator
  extends IdScriptableObject
{
  public static final String ITERATOR_PROPERTY_NAME = "__iterator__";
  private static final Object ITERATOR_TAG = "Iterator";
  private static final int Id___iterator__ = 3;
  private static final int Id_constructor = 1;
  private static final int Id_next = 2;
  private static final int MAX_PROTOTYPE_ID = 3;
  private static final String STOP_ITERATION = "StopIteration";
  private static final long serialVersionUID = -4136968203581667681L;
  private Object objectIterator;
  
  private NativeIterator() {}
  
  private NativeIterator(Object paramObject)
  {
    this.objectIterator = paramObject;
  }
  
  public static Object getStopIterationObject(Scriptable paramScriptable)
  {
    return ScriptableObject.getTopScopeValue(ScriptableObject.getTopLevelScope(paramScriptable), ITERATOR_TAG);
  }
  
  static void init(ScriptableObject paramScriptableObject, boolean paramBoolean)
  {
    new NativeIterator().exportAsJSClass(3, paramScriptableObject, paramBoolean);
    NativeGenerator.init(paramScriptableObject, paramBoolean);
    StopIteration localStopIteration = new StopIteration();
    localStopIteration.setPrototype(getObjectPrototype(paramScriptableObject));
    localStopIteration.setParentScope(paramScriptableObject);
    if (paramBoolean) {
      localStopIteration.sealObject();
    }
    ScriptableObject.defineProperty(paramScriptableObject, "StopIteration", localStopIteration, 2);
    paramScriptableObject.associateValue(ITERATOR_TAG, localStopIteration);
  }
  
  private static Object jsConstructor(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject.length == 0) || (paramArrayOfObject[0] == null) || (paramArrayOfObject[0] == Undefined.instance))
    {
      if (paramArrayOfObject.length == 0) {}
      for (paramContext = Undefined.instance;; paramContext = paramArrayOfObject[0]) {
        throw ScriptRuntime.typeError1("msg.no.properties", ScriptRuntime.toString(paramContext));
      }
    }
    Scriptable localScriptable = ScriptRuntime.toObject(paramContext, paramScriptable1, paramArrayOfObject[0]);
    boolean bool;
    if ((paramArrayOfObject.length > 1) && (ScriptRuntime.toBoolean(paramArrayOfObject[1])))
    {
      bool = true;
      if (paramScriptable2 == null) {
        break label143;
      }
      paramScriptable2 = VMBridge.instance.getJavaIterator(paramContext, paramScriptable1, localScriptable);
      if (paramScriptable2 == null) {
        break label127;
      }
      paramScriptable1 = ScriptableObject.getTopLevelScope(paramScriptable1);
      paramScriptable2 = paramContext.getWrapFactory().wrap(paramContext, paramScriptable1, new WrappedJavaIterator(paramScriptable2, paramScriptable1), WrappedJavaIterator.class);
    }
    label127:
    do
    {
      return paramScriptable2;
      bool = false;
      break;
      paramArrayOfObject = ScriptRuntime.toIterator(paramContext, paramScriptable1, localScriptable, bool);
      paramScriptable2 = paramArrayOfObject;
    } while (paramArrayOfObject != null);
    label143:
    if (bool) {}
    for (int i = 3;; i = 5)
    {
      paramContext = ScriptRuntime.enumInit(localScriptable, paramContext, paramScriptable1, i);
      ScriptRuntime.setEnumNumbers(paramContext, true);
      paramContext = new NativeIterator(paramContext);
      paramContext.setPrototype(ScriptableObject.getClassPrototype(paramScriptable1, paramContext.getClassName()));
      paramContext.setParentScope(paramScriptable1);
      return paramContext;
    }
  }
  
  private Object next(Context paramContext, Scriptable paramScriptable)
  {
    if (!ScriptRuntime.enumNext(this.objectIterator).booleanValue()) {
      throw new JavaScriptException(getStopIterationObject(paramScriptable), null, 0);
    }
    return ScriptRuntime.enumId(this.objectIterator, paramContext);
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(ITERATOR_TAG))
    {
      paramScriptable2 = super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
      return paramScriptable2;
    }
    int i = paramIdFunctionObject.methodId();
    if (i == 1) {
      return jsConstructor(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    if (!(paramScriptable2 instanceof NativeIterator)) {
      throw incompatibleCallError(paramIdFunctionObject);
    }
    paramIdFunctionObject = (NativeIterator)paramScriptable2;
    switch (i)
    {
    case 3: 
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    }
    return paramIdFunctionObject.next(paramContext, paramScriptable1);
  }
  
  protected int findPrototypeId(String paramString)
  {
    int i = paramString.length();
    String str;
    if (i == 4)
    {
      i = 2;
      str = "next";
    }
    for (;;)
    {
      if ((str != null) && (str != paramString) && (!str.equals(paramString)))
      {
        return 0;
        if (i == 11)
        {
          i = 1;
          str = "constructor";
          continue;
        }
        if (i == 12)
        {
          i = 3;
          str = "__iterator__";
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
    return "Iterator";
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i;
    String str;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      i = 2;
      str = "constructor";
    }
    for (;;)
    {
      initPrototypeMethod(ITERATOR_TAG, paramInt, str, i);
      return;
      i = 0;
      str = "next";
      continue;
      i = 1;
      str = "__iterator__";
    }
  }
  
  static class StopIteration
    extends NativeObject
  {
    private static final long serialVersionUID = 2485151085722377663L;
    
    public String getClassName()
    {
      return "StopIteration";
    }
    
    public boolean hasInstance(Scriptable paramScriptable)
    {
      return paramScriptable instanceof StopIteration;
    }
  }
  
  public static class WrappedJavaIterator
  {
    private Iterator<?> iterator;
    private Scriptable scope;
    
    WrappedJavaIterator(Iterator<?> paramIterator, Scriptable paramScriptable)
    {
      this.iterator = paramIterator;
      this.scope = paramScriptable;
    }
    
    public Object __iterator__(boolean paramBoolean)
    {
      return this;
    }
    
    public Object next()
    {
      if (!this.iterator.hasNext()) {
        throw new JavaScriptException(NativeIterator.getStopIterationObject(this.scope), null, 0);
      }
      return this.iterator.next();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */