package org.mozilla.javascript;

public abstract interface ErrorReporter
{
  public abstract void error(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2);
  
  public abstract EvaluatorException runtimeError(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2);
  
  public abstract void warning(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ErrorReporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */