package org.mozilla.javascript;

public class EcmaError
  extends RhinoException
{
  static final long serialVersionUID = -6261226256957286699L;
  private String errorMessage;
  private String errorName;
  
  EcmaError(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2)
  {
    recordErrorOrigin(paramString3, paramInt1, paramString4, paramInt2);
    this.errorName = paramString1;
    this.errorMessage = paramString2;
  }
  
  @Deprecated
  public EcmaError(Scriptable paramScriptable, String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    this("InternalError", ScriptRuntime.toString(paramScriptable), paramString1, paramInt1, paramString2, paramInt2);
  }
  
  public String details()
  {
    return this.errorName + ": " + this.errorMessage;
  }
  
  @Deprecated
  public int getColumnNumber()
  {
    return columnNumber();
  }
  
  public String getErrorMessage()
  {
    return this.errorMessage;
  }
  
  @Deprecated
  public Scriptable getErrorObject()
  {
    return null;
  }
  
  @Deprecated
  public int getLineNumber()
  {
    return lineNumber();
  }
  
  @Deprecated
  public String getLineSource()
  {
    return lineSource();
  }
  
  public String getName()
  {
    return this.errorName;
  }
  
  @Deprecated
  public String getSourceName()
  {
    return sourceName();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\EcmaError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */