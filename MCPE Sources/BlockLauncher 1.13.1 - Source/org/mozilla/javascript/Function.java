package org.mozilla.javascript;

public abstract interface Function
  extends Scriptable, Callable
{
  public abstract Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject);
  
  public abstract Scriptable construct(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\Function.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */