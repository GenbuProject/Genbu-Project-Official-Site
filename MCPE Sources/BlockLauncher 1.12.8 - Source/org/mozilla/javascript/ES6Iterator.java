package org.mozilla.javascript;

public abstract class ES6Iterator
  extends IdScriptableObject
{
  public static final String DONE_PROPERTY = "done";
  private static final int Id_iterator = 2;
  private static final int Id_next = 1;
  private static final int Id_toStringTag = 3;
  private static final int MAX_PROTOTYPE_ID = 3;
  public static final String NEXT_METHOD = "next";
  public static final String VALUE_PROPERTY = "value";
  protected boolean exhausted = false;
  
  ES6Iterator() {}
  
  ES6Iterator(Scriptable paramScriptable)
  {
    paramScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    setParentScope(paramScriptable);
    setPrototype((IdScriptableObject)ScriptableObject.getTopScopeValue(paramScriptable, getTag()));
  }
  
  static void init(ScriptableObject paramScriptableObject, boolean paramBoolean, IdScriptableObject paramIdScriptableObject, String paramString)
  {
    if (paramScriptableObject != null)
    {
      paramIdScriptableObject.setParentScope(paramScriptableObject);
      paramIdScriptableObject.setPrototype(getObjectPrototype(paramScriptableObject));
    }
    paramIdScriptableObject.activatePrototypeMap(3);
    if (paramBoolean) {
      paramIdScriptableObject.sealObject();
    }
    if (paramScriptableObject != null) {
      paramScriptableObject.associateValue(paramString, paramIdScriptableObject);
    }
  }
  
  private Scriptable makeIteratorResult(Context paramContext, Scriptable paramScriptable, boolean paramBoolean, Object paramObject)
  {
    paramContext = paramContext.newObject(paramScriptable);
    ScriptableObject.putProperty(paramContext, "value", paramObject);
    ScriptableObject.putProperty(paramContext, "done", Boolean.valueOf(paramBoolean));
    return paramContext;
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(getTag()))
    {
      paramIdFunctionObject = super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
      return paramIdFunctionObject;
    }
    int i = paramIdFunctionObject.methodId();
    if (!(paramScriptable2 instanceof ES6Iterator)) {
      throw incompatibleCallError(paramIdFunctionObject);
    }
    paramScriptable2 = (ES6Iterator)paramScriptable2;
    paramIdFunctionObject = paramScriptable2;
    switch (i)
    {
    case 2: 
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    }
    return paramScriptable2.next(paramContext, paramScriptable1);
  }
  
  protected int findPrototypeId(String paramString)
  {
    int i = 0;
    if (paramString.charAt(0) == 'n') {
      i = 1;
    }
    do
    {
      return i;
      if ("@@iterator".equals(paramString)) {
        return 2;
      }
    } while (!"@@toStringTag".equals(paramString));
    return 3;
  }
  
  protected abstract String getTag();
  
  protected void initPrototypeId(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      initPrototypeMethod(getTag(), paramInt, "next", 0);
      return;
    case 2: 
      initPrototypeMethod(getTag(), paramInt, "@@iterator", "[Symbol.iterator]", 0);
      return;
    }
    initPrototypeValue(3, "@@toStringTag", getClassName(), 3);
  }
  
  protected abstract boolean isDone(Context paramContext, Scriptable paramScriptable);
  
  protected Object next(Context paramContext, Scriptable paramScriptable)
  {
    Object localObject = Undefined.instance;
    boolean bool;
    if ((isDone(paramContext, paramScriptable)) || (this.exhausted))
    {
      bool = true;
      if (bool) {
        break label50;
      }
      localObject = nextValue(paramContext, paramScriptable);
    }
    for (;;)
    {
      return makeIteratorResult(paramContext, paramScriptable, bool, localObject);
      bool = false;
      break;
      label50:
      this.exhausted = true;
    }
  }
  
  protected abstract Object nextValue(Context paramContext, Scriptable paramScriptable);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ES6Iterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */