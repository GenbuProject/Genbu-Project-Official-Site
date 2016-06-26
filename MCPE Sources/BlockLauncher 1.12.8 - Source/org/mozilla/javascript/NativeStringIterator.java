package org.mozilla.javascript;

public final class NativeStringIterator
  extends ES6Iterator
{
  private static final String ITERATOR_TAG = "StringIterator";
  private static final long serialVersionUID = 1L;
  private int index;
  private String string;
  
  private NativeStringIterator() {}
  
  NativeStringIterator(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    super(paramScriptable1);
    this.index = 0;
    this.string = ScriptRuntime.toString(paramScriptable2);
  }
  
  static void init(ScriptableObject paramScriptableObject, boolean paramBoolean)
  {
    ES6Iterator.init(paramScriptableObject, paramBoolean, new NativeStringIterator(), "StringIterator");
  }
  
  public String getClassName()
  {
    return "String Iterator";
  }
  
  protected String getTag()
  {
    return "StringIterator";
  }
  
  protected boolean isDone(Context paramContext, Scriptable paramScriptable)
  {
    return this.index >= this.string.length();
  }
  
  protected Object nextValue(Context paramContext, Scriptable paramScriptable)
  {
    int i = this.string.offsetByCodePoints(this.index, 1);
    paramContext = this.string.substring(this.index, i);
    this.index = i;
    return paramContext;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeStringIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */