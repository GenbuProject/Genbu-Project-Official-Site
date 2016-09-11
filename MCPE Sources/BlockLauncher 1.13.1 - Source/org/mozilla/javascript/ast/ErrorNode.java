package org.mozilla.javascript.ast;

public class ErrorNode
  extends AstNode
{
  private String message;
  
  public ErrorNode()
  {
    this.type = -1;
  }
  
  public ErrorNode(int paramInt)
  {
    super(paramInt);
    this.type = -1;
  }
  
  public ErrorNode(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = -1;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public String toSource(int paramInt)
  {
    return "";
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    paramNodeVisitor.visit(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\ErrorNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */