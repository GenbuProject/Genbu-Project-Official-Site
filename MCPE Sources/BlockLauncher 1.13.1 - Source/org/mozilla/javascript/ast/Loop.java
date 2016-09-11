package org.mozilla.javascript.ast;

public abstract class Loop
  extends Scope
{
  protected AstNode body;
  protected int lp = -1;
  protected int rp = -1;
  
  public Loop() {}
  
  public Loop(int paramInt)
  {
    super(paramInt);
  }
  
  public Loop(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public AstNode getBody()
  {
    return this.body;
  }
  
  public int getLp()
  {
    return this.lp;
  }
  
  public int getRp()
  {
    return this.rp;
  }
  
  public void setBody(AstNode paramAstNode)
  {
    this.body = paramAstNode;
    setLength(paramAstNode.getPosition() + paramAstNode.getLength() - getPosition());
    paramAstNode.setParent(this);
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
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\Loop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */