package org.mozilla.javascript.ast;

public class CatchClause
  extends AstNode
{
  private Block body;
  private AstNode catchCondition;
  private int ifPosition = -1;
  private int lp = -1;
  private int rp = -1;
  private Name varName;
  
  public CatchClause()
  {
    this.type = 125;
  }
  
  public CatchClause(int paramInt)
  {
    super(paramInt);
    this.type = 125;
  }
  
  public CatchClause(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 125;
  }
  
  public Block getBody()
  {
    return this.body;
  }
  
  public AstNode getCatchCondition()
  {
    return this.catchCondition;
  }
  
  public int getIfPosition()
  {
    return this.ifPosition;
  }
  
  public int getLp()
  {
    return this.lp;
  }
  
  public int getRp()
  {
    return this.rp;
  }
  
  public Name getVarName()
  {
    return this.varName;
  }
  
  public void setBody(Block paramBlock)
  {
    assertNotNull(paramBlock);
    this.body = paramBlock;
    paramBlock.setParent(this);
  }
  
  public void setCatchCondition(AstNode paramAstNode)
  {
    this.catchCondition = paramAstNode;
    if (paramAstNode != null) {
      paramAstNode.setParent(this);
    }
  }
  
  public void setIfPosition(int paramInt)
  {
    this.ifPosition = paramInt;
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
  
  public void setVarName(Name paramName)
  {
    assertNotNull(paramName);
    this.varName = paramName;
    paramName.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("catch (");
    localStringBuilder.append(this.varName.toSource(0));
    if (this.catchCondition != null)
    {
      localStringBuilder.append(" if ");
      localStringBuilder.append(this.catchCondition.toSource(0));
    }
    localStringBuilder.append(") ");
    localStringBuilder.append(this.body.toSource(0));
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.varName.visit(paramNodeVisitor);
      if (this.catchCondition != null) {
        this.catchCondition.visit(paramNodeVisitor);
      }
      this.body.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\CatchClause.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */