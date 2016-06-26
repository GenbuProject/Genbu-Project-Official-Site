package org.mozilla.javascript;

public class NativeSymbol
  extends IdScriptableObject
{
  public static final String CLASS_NAME = "Symbol";
  public static final String ITERATOR_PROPERTY = "@@iterator";
  private static final int Id_constructor = 1;
  private static final int MAX_PROTOTYPE_ID = 1;
  public static final String SPECIES_PROPERTY = "@@species";
  public static final String TO_STRING_TAG_PROPERTY = "@@toStringTag";
  
  public static void init(Context paramContext, Scriptable paramScriptable, boolean paramBoolean)
  {
    new NativeSymbol().exportAsJSClass(1, paramScriptable, paramBoolean);
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag("Symbol")) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    switch (paramIdFunctionObject.methodId())
    {
    default: 
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    return new NativeSymbol();
  }
  
  protected void fillConstructorProperties(IdFunctionObject paramIdFunctionObject)
  {
    super.fillConstructorProperties(paramIdFunctionObject);
    paramIdFunctionObject.defineProperty("iterator", "@@iterator", 7);
    paramIdFunctionObject.defineProperty("species", "@@species", 7);
    paramIdFunctionObject.defineProperty("toStringTag", "@@toStringTag", 7);
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
    return "Symbol";
  }
  
  public String getTypeOf()
  {
    return "symbol";
  }
  
  protected void initPrototypeId(int paramInt)
  {
    String str = null;
    int i = -1;
    switch (paramInt)
    {
    default: 
      super.initPrototypeId(paramInt);
    }
    for (;;)
    {
      initPrototypeMethod("Symbol", paramInt, str, i);
      return;
      i = 0;
      str = "constructor";
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeSymbol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */