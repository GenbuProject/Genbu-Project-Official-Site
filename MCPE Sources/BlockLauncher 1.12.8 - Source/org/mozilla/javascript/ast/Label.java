package org.mozilla.javascript.ast;

public class Label
  extends Jump
{
  private String name;
  
  public Label()
  {
    this.type = 131;
  }
  
  public Label(int paramInt)
  {
    this(paramInt, -1);
  }
  
  public Label(int paramInt1, int paramInt2)
  {
    this.type = 131;
    this.position = paramInt1;
    this.length = paramInt2;
  }
  
  public Label(int paramInt1, int paramInt2, String paramString)
  {
    this(paramInt1, paramInt2);
    setName(paramString);
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String paramString)
  {
    if (paramString == null) {}
    for (paramString = null; (paramString == null) || ("".equals(paramString)); paramString = paramString.trim()) {
      throw new IllegalArgumentException("invalid label name");
    }
    this.name = paramString;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append(this.name);
    localStringBuilder.append(":\n");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    paramNodeVisitor.visit(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\Label.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */