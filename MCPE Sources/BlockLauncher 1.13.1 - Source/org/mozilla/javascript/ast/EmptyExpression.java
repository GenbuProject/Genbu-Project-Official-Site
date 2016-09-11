package org.mozilla.javascript.ast;

public class EmptyExpression
  extends AstNode
{
  public EmptyExpression()
  {
    this.type = 129;
  }
  
  public EmptyExpression(int paramInt)
  {
    super(paramInt);
    this.type = 129;
  }
  
  public EmptyExpression(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 129;
  }
  
  public String toSource(int paramInt)
  {
    return makeIndent(paramInt);
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    paramNodeVisitor.visit(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\EmptyExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */