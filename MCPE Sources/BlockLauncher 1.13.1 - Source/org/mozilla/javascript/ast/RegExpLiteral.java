package org.mozilla.javascript.ast;

public class RegExpLiteral
  extends AstNode
{
  private String flags;
  private String value;
  
  public RegExpLiteral()
  {
    this.type = 48;
  }
  
  public RegExpLiteral(int paramInt)
  {
    super(paramInt);
    this.type = 48;
  }
  
  public RegExpLiteral(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 48;
  }
  
  public String getFlags()
  {
    return this.flags;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public void setFlags(String paramString)
  {
    this.flags = paramString;
  }
  
  public void setValue(String paramString)
  {
    assertNotNull(paramString);
    this.value = paramString;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder().append(makeIndent(paramInt)).append("/").append(this.value).append("/");
    if (this.flags == null) {}
    for (String str = "";; str = this.flags) {
      return str;
    }
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    paramNodeVisitor.visit(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\RegExpLiteral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */