package org.mozilla.javascript.ast;

public class ExpressionStatement
  extends AstNode
{
  private AstNode expr;
  
  public ExpressionStatement()
  {
    this.type = 134;
  }
  
  public ExpressionStatement(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 134;
  }
  
  public ExpressionStatement(int paramInt1, int paramInt2, AstNode paramAstNode)
  {
    super(paramInt1, paramInt2);
    this.type = 134;
    setExpression(paramAstNode);
  }
  
  public ExpressionStatement(AstNode paramAstNode)
  {
    this(paramAstNode.getPosition(), paramAstNode.getLength(), paramAstNode);
  }
  
  public ExpressionStatement(AstNode paramAstNode, boolean paramBoolean)
  {
    this(paramAstNode);
    if (paramBoolean) {
      setHasResult();
    }
  }
  
  public AstNode getExpression()
  {
    return this.expr;
  }
  
  public boolean hasSideEffects()
  {
    return (this.type == 135) || (this.expr.hasSideEffects());
  }
  
  public void setExpression(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.expr = paramAstNode;
    paramAstNode.setParent(this);
    setLineno(paramAstNode.getLineno());
  }
  
  public void setHasResult()
  {
    this.type = 135;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.expr.toSource(paramInt));
    localStringBuilder.append(";\n");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this)) {
      this.expr.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\ExpressionStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */