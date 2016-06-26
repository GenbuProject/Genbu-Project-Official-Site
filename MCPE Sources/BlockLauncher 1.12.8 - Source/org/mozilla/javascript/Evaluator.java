package org.mozilla.javascript;

import java.util.List;
import org.mozilla.javascript.ast.ScriptNode;

public abstract interface Evaluator
{
  public abstract void captureStackInfo(RhinoException paramRhinoException);
  
  public abstract Object compile(CompilerEnvirons paramCompilerEnvirons, ScriptNode paramScriptNode, String paramString, boolean paramBoolean);
  
  public abstract Function createFunctionObject(Context paramContext, Scriptable paramScriptable, Object paramObject1, Object paramObject2);
  
  public abstract Script createScriptObject(Object paramObject1, Object paramObject2);
  
  public abstract String getPatchedStack(RhinoException paramRhinoException, String paramString);
  
  public abstract List<String> getScriptStack(RhinoException paramRhinoException);
  
  public abstract String getSourcePositionFromStack(Context paramContext, int[] paramArrayOfInt);
  
  public abstract void setEvalScriptFlag(Script paramScript);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\Evaluator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */