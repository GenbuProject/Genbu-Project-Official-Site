package org.mozilla.javascript;

public final class NativeContinuation
  extends IdScriptableObject
  implements Function
{
  private static final Object FTAG = "Continuation";
  private static final int Id_constructor = 1;
  private static final int MAX_PROTOTYPE_ID = 1;
  static final long serialVersionUID = 1794167133757605367L;
  private Object implementation;
  
  public static void init(Context paramContext, Scriptable paramScriptable, boolean paramBoolean)
  {
    new NativeContinuation().exportAsJSClass(1, paramScriptable, paramBoolean);
  }
  
  public static boolean isContinuationConstructor(IdFunctionObject paramIdFunctionObject)
  {
    return (paramIdFunctionObject.hasTag(FTAG)) && (paramIdFunctionObject.methodId() == 1);
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    return Interpreter.restartContinuation(this, paramContext, paramScriptable1, paramArrayOfObject);
  }
  
  public Scriptable construct(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    throw Context.reportRuntimeError("Direct call is not supported");
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(FTAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i = paramIdFunctionObject.methodId();
    switch (i)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    }
    throw Context.reportRuntimeError("Direct call is not supported");
  }
  
  protected int findPrototypeId(String paramString)
  {
    int i;
    String str;
    if (paramString.length() == 11)
    {
      i = 1;
      str = "constructor";
    }
    for (;;)
    {
      if ((str != null) && (str != paramString) && (!str.equals(paramString))) {
        return 0;
      }
      return i;
      str = null;
      i = 0;
    }
  }
  
  public String getClassName()
  {
    return "Continuation";
  }
  
  public Object getImplementation()
  {
    return this.implementation;
  }
  
  public void initImplementation(Object paramObject)
  {
    this.implementation = paramObject;
  }
  
  protected void initPrototypeId(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    }
    initPrototypeMethod(FTAG, paramInt, "constructor", 0);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeContinuation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */