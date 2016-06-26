package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

public class UnaryExpression
  extends AstNode
{
  private boolean isPostfix;
  private AstNode operand;
  
  public UnaryExpression() {}
  
  public UnaryExpression(int paramInt)
  {
    super(paramInt);
  }
  
  public UnaryExpression(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public UnaryExpression(int paramInt1, int paramInt2, AstNode paramAstNode)
  {
    this(paramInt1, paramInt2, paramAstNode, false);
  }
  
  public UnaryExpression(int paramInt1, int paramInt2, AstNode paramAstNode, boolean paramBoolean)
  {
    assertNotNull(paramAstNode);
    int i;
    if (paramBoolean)
    {
      i = paramAstNode.getPosition();
      if (!paramBoolean) {
        break label59;
      }
      paramInt2 += 2;
    }
    for (;;)
    {
      setBounds(i, paramInt2);
      setOperator(paramInt1);
      setOperand(paramAstNode);
      this.isPostfix = paramBoolean;
      return;
      i = paramInt2;
      break;
      label59:
      paramInt2 = paramAstNode.getPosition() + paramAstNode.getLength();
    }
  }
  
  public AstNode getOperand()
  {
    return this.operand;
  }
  
  public int getOperator()
  {
    return this.type;
  }
  
  public boolean isPostfix()
  {
    return this.isPostfix;
  }
  
  public boolean isPrefix()
  {
    return !this.isPostfix;
  }
  
  public void setIsPostfix(boolean paramBoolean)
  {
    this.isPostfix = paramBoolean;
  }
  
  public void setOperand(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.operand = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setOperator(int paramInt)
  {
    if (!Token.isValidToken(paramInt)) {
      throw new IllegalArgumentException("Invalid token: " + paramInt);
    }
    setType(paramInt);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    paramInt = getType();
    if (!this.isPostfix)
    {
      localStringBuilder.append(operatorToString(paramInt));
      if ((paramInt == 32) || (paramInt == 31) || (paramInt == 127)) {
        localStringBuilder.append(" ");
      }
    }
    localStringBuilder.append(this.operand.toSource());
    if (this.isPostfix) {
      localStringBuilder.append(operatorToString(paramInt));
    }
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this)) {
      this.operand.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\UnaryExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */