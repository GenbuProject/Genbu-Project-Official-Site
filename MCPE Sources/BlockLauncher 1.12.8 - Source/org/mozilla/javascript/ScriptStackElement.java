package org.mozilla.javascript;

import java.io.Serializable;

public final class ScriptStackElement
  implements Serializable
{
  static final long serialVersionUID = -6416688260860477449L;
  public final String fileName;
  public final String functionName;
  public final int lineNumber;
  
  public ScriptStackElement(String paramString1, String paramString2, int paramInt)
  {
    this.fileName = paramString1;
    this.functionName = paramString2;
    this.lineNumber = paramInt;
  }
  
  private void appendV8Location(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append(this.fileName);
    if (this.lineNumber > -1) {
      paramStringBuilder.append(':').append(this.lineNumber);
    }
  }
  
  public void renderJavaStyle(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("\tat ").append(this.fileName);
    if (this.lineNumber > -1) {
      paramStringBuilder.append(':').append(this.lineNumber);
    }
    if (this.functionName != null) {
      paramStringBuilder.append(" (").append(this.functionName).append(')');
    }
  }
  
  public void renderMozillaStyle(StringBuilder paramStringBuilder)
  {
    if (this.functionName != null) {
      paramStringBuilder.append(this.functionName).append("()");
    }
    paramStringBuilder.append('@').append(this.fileName);
    if (this.lineNumber > -1) {
      paramStringBuilder.append(':').append(this.lineNumber);
    }
  }
  
  public void renderV8Style(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("    at ");
    if ((this.functionName == null) || ("anonymous".equals(this.functionName)) || ("undefined".equals(this.functionName)))
    {
      appendV8Location(paramStringBuilder);
      return;
    }
    paramStringBuilder.append(this.functionName).append(" (");
    appendV8Location(paramStringBuilder);
    paramStringBuilder.append(')');
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    renderMozillaStyle(localStringBuilder);
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ScriptStackElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */