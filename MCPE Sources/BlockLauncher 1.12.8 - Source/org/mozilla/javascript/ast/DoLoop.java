package org.mozilla.javascript.ast;

public class DoLoop
  extends Loop
{
  private AstNode condition;
  private int whilePosition = -1;
  
  public DoLoop()
  {
    this.type = 119;
  }
  
  public DoLoop(int paramInt)
  {
    super(paramInt);
    this.type = 119;
  }
  
  public DoLoop(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 119;
  }
  
  public AstNode getCondition()
  {
    return this.condition;
  }
  
  public int getWhilePosition()
  {
    return this.whilePosition;
  }
  
  public void setCondition(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.condition = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setWhilePosition(int paramInt)
  {
    this.whilePosition = paramInt;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("do ");
    localStringBuilder.append(this.body.toSource(paramInt).trim());
    localStringBuilder.append(" while (");
    localStringBuilder.append(this.condition.toSource(0));
    localStringBuilder.append(");\n");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.body.visit(paramNodeVisitor);
      this.condition.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\DoLoop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */