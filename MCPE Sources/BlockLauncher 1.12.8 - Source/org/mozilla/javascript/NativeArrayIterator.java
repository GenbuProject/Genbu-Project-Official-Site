package org.mozilla.javascript;

public final class NativeArrayIterator
  extends ES6Iterator
{
  private static final String ITERATOR_TAG = "ArrayIterator";
  private static final long serialVersionUID = 1L;
  private Scriptable arrayLike;
  private int index;
  
  private NativeArrayIterator() {}
  
  public NativeArrayIterator(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    super(paramScriptable1);
    this.index = 0;
    this.arrayLike = paramScriptable2;
  }
  
  static void init(ScriptableObject paramScriptableObject, boolean paramBoolean)
  {
    ES6Iterator.init(paramScriptableObject, paramBoolean, new NativeArrayIterator(), "ArrayIterator");
  }
  
  public String getClassName()
  {
    return "Array Iterator";
  }
  
  protected String getTag()
  {
    return "ArrayIterator";
  }
  
  protected boolean isDone(Context paramContext, Scriptable paramScriptable)
  {
    return this.index >= NativeArray.getLengthProperty(paramContext, this.arrayLike);
  }
  
  protected Object nextValue(Context paramContext, Scriptable paramScriptable)
  {
    paramContext = this.arrayLike;
    int i = this.index;
    this.index = (i + 1);
    paramScriptable = paramContext.get(i, this.arrayLike);
    paramContext = paramScriptable;
    if (paramScriptable == ScriptableObject.NOT_FOUND) {
      paramContext = Undefined.instance;
    }
    return paramContext;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeArrayIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */