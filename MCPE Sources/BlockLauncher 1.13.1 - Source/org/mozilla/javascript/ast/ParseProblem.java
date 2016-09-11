package org.mozilla.javascript.ast;

public class ParseProblem
{
  private int length;
  private String message;
  private int offset;
  private String sourceName;
  private Type type;
  
  public ParseProblem(Type paramType, String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    setType(paramType);
    setMessage(paramString1);
    setSourceName(paramString2);
    setFileOffset(paramInt1);
    setLength(paramInt2);
  }
  
  public int getFileOffset()
  {
    return this.offset;
  }
  
  public int getLength()
  {
    return this.length;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public String getSourceName()
  {
    return this.sourceName;
  }
  
  public Type getType()
  {
    return this.type;
  }
  
  public void setFileOffset(int paramInt)
  {
    this.offset = paramInt;
  }
  
  public void setLength(int paramInt)
  {
    this.length = paramInt;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public void setSourceName(String paramString)
  {
    this.sourceName = paramString;
  }
  
  public void setType(Type paramType)
  {
    this.type = paramType;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(200);
    localStringBuilder.append(this.sourceName).append(":");
    localStringBuilder.append("offset=").append(this.offset).append(",");
    localStringBuilder.append("length=").append(this.length).append(",");
    if (this.type == Type.Error) {}
    for (String str = "error: ";; str = "warning: ")
    {
      localStringBuilder.append(str);
      localStringBuilder.append(this.message);
      return localStringBuilder.toString();
    }
  }
  
  public static enum Type
  {
    Error,  Warning;
    
    private Type() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\ParseProblem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */