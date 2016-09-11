package org.mozilla.javascript;

class DefaultErrorReporter
  implements ErrorReporter
{
  static final DefaultErrorReporter instance = new DefaultErrorReporter();
  private ErrorReporter chainedReporter;
  private boolean forEval;
  
  static ErrorReporter forEval(ErrorReporter paramErrorReporter)
  {
    DefaultErrorReporter localDefaultErrorReporter = new DefaultErrorReporter();
    localDefaultErrorReporter.forEval = true;
    localDefaultErrorReporter.chainedReporter = paramErrorReporter;
    return localDefaultErrorReporter;
  }
  
  public void error(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
  {
    String str2;
    String str1;
    if (this.forEval)
    {
      str2 = "SyntaxError";
      if (!paramString1.startsWith("TypeError: ")) {
        break label87;
      }
      str2 = "TypeError";
      str1 = paramString1.substring("TypeError: ".length());
    }
    for (paramString1 = str2;; paramString1 = str2)
    {
      throw ScriptRuntime.constructError(paramString1, str1, paramString2, paramInt1, paramString3, paramInt2);
      if (this.chainedReporter != null)
      {
        this.chainedReporter.error(paramString1, paramString2, paramInt1, paramString3, paramInt2);
        return;
      }
      throw runtimeError(paramString1, paramString2, paramInt1, paramString3, paramInt2);
      label87:
      str1 = paramString1;
    }
  }
  
  public EvaluatorException runtimeError(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
  {
    if (this.chainedReporter != null) {
      return this.chainedReporter.runtimeError(paramString1, paramString2, paramInt1, paramString3, paramInt2);
    }
    return new EvaluatorException(paramString1, paramString2, paramInt1, paramString3, paramInt2);
  }
  
  public void warning(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
  {
    if (this.chainedReporter != null) {
      this.chainedReporter.warning(paramString1, paramString2, paramInt1, paramString3, paramInt2);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\DefaultErrorReporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */