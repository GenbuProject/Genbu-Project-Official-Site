package org.mozilla.javascript.ast;

public class ParenthesizedExpression
  extends AstNode
{
  private AstNode expression;
  
  public ParenthesizedExpression()
  {
    this.type = 88;
  }
  
  public ParenthesizedExpression(int paramInt)
  {
    super(paramInt);
    this.type = 88;
  }
  
  public ParenthesizedExpression(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 88;
  }
  
  public ParenthesizedExpression(int paramInt1, int paramInt2, AstNode paramAstNode)
  {
    super(paramInt1, paramInt2);
    this.type = 88;
    setExpression(paramAstNode);
  }
  
  public ParenthesizedExpression(AstNode paramAstNode) {}
  
  public AstNode getExpression()
  {
    return this.expression;
  }
  
  public void setExpression(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.expression = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    return makeIndent(paramInt) + "(" + this.expression.toSource(0) + ")";
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this)) {
      this.expression.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\ParenthesizedExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */