package org.mozilla.javascript.ast;

public class ForLoop
  extends Loop
{
  private AstNode condition;
  private AstNode increment;
  private AstNode initializer;
  
  public ForLoop()
  {
    this.type = 120;
  }
  
  public ForLoop(int paramInt)
  {
    super(paramInt);
    this.type = 120;
  }
  
  public ForLoop(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 120;
  }
  
  public AstNode getCondition()
  {
    return this.condition;
  }
  
  public AstNode getIncrement()
  {
    return this.increment;
  }
  
  public AstNode getInitializer()
  {
    return this.initializer;
  }
  
  public void setCondition(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.condition = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setIncrement(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.increment = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setInitializer(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.initializer = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("for (");
    localStringBuilder.append(this.initializer.toSource(0));
    localStringBuilder.append("; ");
    localStringBuilder.append(this.condition.toSource(0));
    localStringBuilder.append("; ");
    localStringBuilder.append(this.increment.toSource(0));
    localStringBuilder.append(") ");
    if (this.body.getType() == 130) {
      localStringBuilder.append(this.body.toSource(paramInt).trim()).append("\n");
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
      this.initializer.visit(paramNodeVisitor);
      this.condition.visit(paramNodeVisitor);
      this.increment.visit(paramNodeVisitor);
      this.body.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\ForLoop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */