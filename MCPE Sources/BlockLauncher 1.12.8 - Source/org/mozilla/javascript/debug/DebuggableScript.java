package org.mozilla.javascript.debug;

public abstract interface DebuggableScript
{
  public abstract DebuggableScript getFunction(int paramInt);
  
  public abstract int getFunctionCount();
  
  public abstract String getFunctionName();
  
  public abstract int[] getLineNumbers();
  
  public abstract int getParamAndVarCount();
  
  public abstract int getParamCount();
  
  public abstract String getParamOrVarName(int paramInt);
  
  public abstract DebuggableScript getParent();
  
  public abstract String getSourceName();
  
  public abstract boolean isFunction();
  
  public abstract boolean isGeneratedScript();
  
  public abstract boolean isTopLevel();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\debug\DebuggableScript.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */