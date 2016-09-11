package org.mozilla.javascript.ast;

public class ForInLoop
  extends Loop
{
  protected int eachPosition = -1;
  protected int inPosition = -1;
  protected boolean isForEach;
  protected boolean isForOf;
  protected AstNode iteratedObject;
  protected AstNode iterator;
  
  public ForInLoop()
  {
    this.type = 120;
  }
  
  public ForInLoop(int paramInt)
  {
    super(paramInt);
    this.type = 120;
  }
  
  public ForInLoop(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 120;
  }
  
  public int getEachPosition()
  {
    return this.eachPosition;
  }
  
  public int getInPosition()
  {
    return this.inPosition;
  }
  
  public AstNode getIteratedObject()
  {
    return this.iteratedObject;
  }
  
  public AstNode getIterator()
  {
    return this.iterator;
  }
  
  public boolean isForEach()
  {
    return this.isForEach;
  }
  
  public boolean isForOf()
  {
    return this.isForOf;
  }
  
  public void setEachPosition(int paramInt)
  {
    this.eachPosition = paramInt;
  }
  
  public void setInPosition(int paramInt)
  {
    this.inPosition = paramInt;
  }
  
  public void setIsForEach(boolean paramBoolean)
  {
    this.isForEach = paramBoolean;
  }
  
  public void setIsForOf(boolean paramBoolean)
  {
    this.isForOf = paramBoolean;
  }
  
  public void setIteratedObject(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.iteratedObject = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setIterator(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.iterator = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("for ");
    if (isForEach()) {
      localStringBuilder.append("each ");
    }
    localStringBuilder.append("(");
    localStringBuilder.append(this.iterator.toSource(0));
    if (this.isForOf)
    {
      localStringBuilder.append(" of ");
      localStringBuilder.append(this.iteratedObject.toSource(0));
      localStringBuilder.append(") ");
      if (this.body.getType() != 130) {
        break label142;
      }
      localStringBuilder.append(this.body.toSource(paramInt).trim()).append("\n");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(" in ");
      break;
      label142:
      localStringBuilder.append("\n").append(this.body.toSource(paramInt + 1));
    }
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.iterator.visit(paramNodeVisitor);
      this.iteratedObject.visit(paramNodeVisitor);
      this.body.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\ForInLoop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */