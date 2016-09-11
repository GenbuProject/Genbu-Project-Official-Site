package org.mozilla.javascript.debug;

import org.mozilla.javascript.Context;

public abstract interface Debugger
{
  public abstract DebugFrame getFrame(Context paramContext, DebuggableScript paramDebuggableScript);
  
  public abstract void handleCompilationDone(Context paramContext, DebuggableScript paramDebuggableScript, String paramString);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\debug\Debugger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */