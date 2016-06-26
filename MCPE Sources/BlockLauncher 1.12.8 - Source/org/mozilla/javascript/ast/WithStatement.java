package org.mozilla.javascript.ast;

public class WithStatement
  extends AstNode
{
  private AstNode expression;
  private int lp = -1;
  private int rp = -1;
  private AstNode statement;
  
  public WithStatement()
  {
    this.type = 124;
  }
  
  public WithStatement(int paramInt)
  {
    super(paramInt);
    this.type = 124;
  }
  
  public WithStatement(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 124;
  }
  
  public AstNode getExpression()
  {
    return this.expression;
  }
  
  public int getLp()
  {
    return this.lp;
  }
  
  public int getRp()
  {
    return this.rp;
  }
  
  public AstNode getStatement()
  {
    return this.statement;
  }
  
  public void setExpression(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.expression = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setLp(int paramInt)
  {
    this.lp = paramInt;
  }
  
  public void setParens(int paramInt1, int paramInt2)
  {
    this.lp = paramInt1;
    this.rp = paramInt2;
  }
  
  public void setRp(int paramInt)
  {
    this.rp = paramInt;
  }
  
  public void setStatement(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.statement = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("with (");
    localStringBuilder.append(this.expression.toSource(0));
    localStringBuilder.append(") ");
    if (this.statement.getType() == 130)
    {
      localStringBuilder.append(this.statement.toSource(paramInt).trim());
      localStringBuilder.append("\n");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append("\n").append(this.statement.toSource(paramInt + 1));
    }
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.expression.visit(paramNodeVisitor);
      this.statement.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\WithStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */