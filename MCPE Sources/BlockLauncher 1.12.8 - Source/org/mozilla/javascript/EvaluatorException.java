package org.mozilla.javascript;

public class EvaluatorException
  extends RhinoException
{
  static final long serialVersionUID = -8743165779676009808L;
  
  public EvaluatorException(String paramString)
  {
    super(paramString);
  }
  
  public EvaluatorException(String paramString1, String paramString2, int paramInt)
  {
    this(paramString1, paramString2, paramInt, null, 0);
  }
  
  public EvaluatorException(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
  {
    super(paramString1);
    recordErrorOrigin(paramString2, paramInt1, paramString3, paramInt2);
  }
  
  @Deprecated
  public int getColumnNumber()
  {
    return columnNumber();
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
  
  @Deprecated
  public String getSourceName()
  {
    return sourceName();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\EvaluatorException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */