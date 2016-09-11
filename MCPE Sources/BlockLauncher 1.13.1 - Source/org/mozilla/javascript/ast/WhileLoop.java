package org.mozilla.javascript.ast;

public class WhileLoop
  extends Loop
{
  private AstNode condition;
  
  public WhileLoop()
  {
    this.type = 118;
  }
  
  public WhileLoop(int paramInt)
  {
    super(paramInt);
    this.type = 118;
  }
  
  public WhileLoop(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 118;
  }
  
  public AstNode getCondition()
  {
    return this.condition;
  }
  
  public void setCondition(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.condition = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("while (");
    localStringBuilder.append(this.condition.toSource(0));
    localStringBuilder.append(") ");
    if (this.body.getType() == 130)
    {
      localStringBuilder.append(this.body.toSource(paramInt).trim());
      localStringBuilder.append("\n");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append("\n").append(this.body.toSource(paramInt + 1));
    }
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.condition.visit(paramNodeVisitor);
      this.body.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\WhileLoop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */