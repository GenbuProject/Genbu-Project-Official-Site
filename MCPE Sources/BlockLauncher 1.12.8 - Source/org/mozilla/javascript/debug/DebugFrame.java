package org.mozilla.javascript.debug;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public abstract interface DebugFrame
{
  public abstract void onDebuggerStatement(Context paramContext);
  
  public abstract void onEnter(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject);
  
  public abstract void onExceptionThrown(Context paramContext, Throwable paramThrowable);
  
  public abstract void onExit(Context paramContext, boolean paramBoolean, Object paramObject);
  
  public abstract void onLineChange(Context paramContext, int paramInt);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\debug\DebugFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */