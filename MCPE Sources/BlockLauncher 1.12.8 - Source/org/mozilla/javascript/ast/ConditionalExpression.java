package org.mozilla.javascript.ast;

public class ConditionalExpression
  extends AstNode
{
  private int colonPosition = -1;
  private AstNode falseExpression;
  private int questionMarkPosition = -1;
  private AstNode testExpression;
  private AstNode trueExpression;
  
  public ConditionalExpression()
  {
    this.type = 103;
  }
  
  public ConditionalExpression(int paramInt)
  {
    super(paramInt);
    this.type = 103;
  }
  
  public ConditionalExpression(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 103;
  }
  
  public int getColonPosition()
  {
    return this.colonPosition;
  }
  
  public AstNode getFalseExpression()
  {
    return this.falseExpression;
  }
  
  public int getQuestionMarkPosition()
  {
    return this.questionMarkPosition;
  }
  
  public AstNode getTestExpression()
  {
    return this.testExpression;
  }
  
  public AstNode getTrueExpression()
  {
    return this.trueExpression;
  }
  
  public boolean hasSideEffects()
  {
    if ((this.testExpression == null) || (this.trueExpression == null) || (this.falseExpression == null)) {
      codeBug();
    }
    return (this.trueExpression.hasSideEffects()) && (this.falseExpression.hasSideEffects());
  }
  
  public void setColonPosition(int paramInt)
  {
    this.colonPosition = paramInt;
  }
  
  public void setFalseExpression(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.falseExpression = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setQuestionMarkPosition(int paramInt)
  {
    this.questionMarkPosition = paramInt;
  }
  
  public void setTestExpression(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.testExpression = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setTrueExpression(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.trueExpression = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append(this.testExpression.toSource(paramInt));
    localStringBuilder.append(" ? ");
    localStringBuilder.append(this.trueExpression.toSource(0));
    localStringBuilder.append(" : ");
    localStringBuilder.append(this.falseExpression.toSource(0));
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.testExpression.visit(paramNodeVisitor);
      this.trueExpression.visit(paramNodeVisitor);
      this.falseExpression.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\ConditionalExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */