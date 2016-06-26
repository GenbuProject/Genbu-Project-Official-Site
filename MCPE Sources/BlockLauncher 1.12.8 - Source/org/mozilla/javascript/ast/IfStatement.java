package org.mozilla.javascript.ast;

public class IfStatement
  extends AstNode
{
  private AstNode condition;
  private AstNode elsePart;
  private int elsePosition = -1;
  private int lp = -1;
  private int rp = -1;
  private AstNode thenPart;
  
  public IfStatement()
  {
    this.type = 113;
  }
  
  public IfStatement(int paramInt)
  {
    super(paramInt);
    this.type = 113;
  }
  
  public IfStatement(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 113;
  }
  
  public AstNode getCondition()
  {
    return this.condition;
  }
  
  public AstNode getElsePart()
  {
    return this.elsePart;
  }
  
  public int getElsePosition()
  {
    return this.elsePosition;
  }
  
  public int getLp()
  {
    return this.lp;
  }
  
  public int getRp()
  {
    return this.rp;
  }
  
  public AstNode getThenPart()
  {
    return this.thenPart;
  }
  
  public void setCondition(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.condition = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setElsePart(AstNode paramAstNode)
  {
    this.elsePart = paramAstNode;
    if (paramAstNode != null) {
      paramAstNode.setParent(this);
    }
  }
  
  public void setElsePosition(int paramInt)
  {
    this.elsePosition = paramInt;
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
  
  public void setThenPart(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.thenPart = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    String str = makeIndent(paramInt);
    StringBuilder localStringBuilder = new StringBuilder(32);
    localStringBuilder.append(str);
    localStringBuilder.append("if (");
    localStringBuilder.append(this.condition.toSource(0));
    localStringBuilder.append(") ");
    if (this.thenPart.getType() != 130) {
      localStringBuilder.append("\n").append(makeIndent(paramInt + 1));
    }
    localStringBuilder.append(this.thenPart.toSource(paramInt).trim());
    if (this.elsePart != null)
    {
      if (this.thenPart.getType() == 130) {
        break label201;
      }
      localStringBuilder.append("\n").append(str).append("else ");
    }
    for (;;)
    {
      if ((this.elsePart.getType() != 130) && (this.elsePart.getType() != 113)) {
        localStringBuilder.append("\n").append(makeIndent(paramInt + 1));
      }
      localStringBuilder.append(this.elsePart.toSource(paramInt).trim());
      localStringBuilder.append("\n");
      return localStringBuilder.toString();
      label201:
      localStringBuilder.append(" else ");
    }
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.condition.visit(paramNodeVisitor);
      this.thenPart.visit(paramNodeVisitor);
      if (this.elsePart != null) {
        this.elsePart.visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\IfStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */