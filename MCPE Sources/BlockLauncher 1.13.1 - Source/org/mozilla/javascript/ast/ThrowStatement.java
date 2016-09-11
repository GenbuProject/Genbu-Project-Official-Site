package org.mozilla.javascript.ast;

public class ThrowStatement
  extends AstNode
{
  private AstNode expression;
  
  public ThrowStatement()
  {
    this.type = 50;
  }
  
  public ThrowStatement(int paramInt)
  {
    super(paramInt);
    this.type = 50;
  }
  
  public ThrowStatement(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 50;
  }
  
  public ThrowStatement(int paramInt1, int paramInt2, AstNode paramAstNode)
  {
    super(paramInt1, paramInt2);
    this.type = 50;
    setExpression(paramAstNode);
  }
  
  public ThrowStatement(int paramInt, AstNode paramAstNode)
  {
    super(paramInt, paramAstNode.getLength());
    this.type = 50;
    setExpression(paramAstNode);
  }
  
  public ThrowStatement(AstNode paramAstNode)
  {
    this.type = 50;
    setExpression(paramAstNode);
  }
  
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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("throw");
    localStringBuilder.append(" ");
    localStringBuilder.append(this.expression.toSource(0));
    localStringBuilder.append(";\n");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this)) {
      this.expression.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\ThrowStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */