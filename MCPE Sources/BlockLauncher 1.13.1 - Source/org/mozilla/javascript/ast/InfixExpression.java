package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

public class InfixExpression
  extends AstNode
{
  protected AstNode left;
  protected int operatorPosition = -1;
  protected AstNode right;
  
  public InfixExpression() {}
  
  public InfixExpression(int paramInt)
  {
    super(paramInt);
  }
  
  public InfixExpression(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public InfixExpression(int paramInt1, int paramInt2, AstNode paramAstNode1, AstNode paramAstNode2)
  {
    super(paramInt1, paramInt2);
    setLeft(paramAstNode1);
    setRight(paramAstNode2);
  }
  
  public InfixExpression(int paramInt1, AstNode paramAstNode1, AstNode paramAstNode2, int paramInt2)
  {
    setType(paramInt1);
    setOperatorPosition(paramInt2 - paramAstNode1.getPosition());
    setLeftAndRight(paramAstNode1, paramAstNode2);
  }
  
  public InfixExpression(AstNode paramAstNode1, AstNode paramAstNode2)
  {
    setLeftAndRight(paramAstNode1, paramAstNode2);
  }
  
  public AstNode getLeft()
  {
    return this.left;
  }
  
  public int getOperator()
  {
    return getType();
  }
  
  public int getOperatorPosition()
  {
    return this.operatorPosition;
  }
  
  public AstNode getRight()
  {
    return this.right;
  }
  
  public boolean hasSideEffects()
  {
    boolean bool1 = true;
    boolean bool2 = false;
    switch (getType())
    {
    default: 
      bool1 = super.hasSideEffects();
    case 90: 
      do
      {
        return bool1;
      } while ((this.right != null) && (this.right.hasSideEffects()));
      return false;
    }
    if ((this.left == null) || (!this.left.hasSideEffects()))
    {
      bool1 = bool2;
      if (this.right != null)
      {
        bool1 = bool2;
        if (!this.right.hasSideEffects()) {}
      }
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }
  
  public void setLeft(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.left = paramAstNode;
    setLineno(paramAstNode.getLineno());
    paramAstNode.setParent(this);
  }
  
  public void setLeftAndRight(AstNode paramAstNode1, AstNode paramAstNode2)
  {
    assertNotNull(paramAstNode1);
    assertNotNull(paramAstNode2);
    setBounds(paramAstNode1.getPosition(), paramAstNode2.getPosition() + paramAstNode2.getLength());
    setLeft(paramAstNode1);
    setRight(paramAstNode2);
  }
  
  public void setOperator(int paramInt)
  {
    if (!Token.isValidToken(paramInt)) {
      throw new IllegalArgumentException("Invalid token: " + paramInt);
    }
    setType(paramInt);
  }
  
  public void setOperatorPosition(int paramInt)
  {
    this.operatorPosition = paramInt;
  }
  
  public void setRight(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.right = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append(this.left.toSource());
    localStringBuilder.append(" ");
    localStringBuilder.append(operatorToString(getType()));
    localStringBuilder.append(" ");
    localStringBuilder.append(this.right.toSource());
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.left.visit(paramNodeVisitor);
      this.right.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\InfixExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */