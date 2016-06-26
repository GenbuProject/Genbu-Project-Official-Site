package org.mozilla.javascript.ast;

public class Assignment
  extends InfixExpression
{
  public Assignment() {}
  
  public Assignment(int paramInt)
  {
    super(paramInt);
  }
  
  public Assignment(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public Assignment(int paramInt1, int paramInt2, AstNode paramAstNode1, AstNode paramAstNode2)
  {
    super(paramInt1, paramInt2, paramAstNode1, paramAstNode2);
  }
  
  public Assignment(int paramInt1, AstNode paramAstNode1, AstNode paramAstNode2, int paramInt2)
  {
    super(paramInt1, paramAstNode1, paramAstNode2, paramInt2);
  }
  
  public Assignment(AstNode paramAstNode1, AstNode paramAstNode2)
  {
    super(paramAstNode1, paramAstNode2);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\Assignment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */