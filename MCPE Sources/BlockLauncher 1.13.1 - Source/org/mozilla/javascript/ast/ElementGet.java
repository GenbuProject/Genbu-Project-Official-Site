package org.mozilla.javascript.ast;

public class ElementGet
  extends AstNode
{
  private AstNode element;
  private int lb = -1;
  private int rb = -1;
  private AstNode target;
  
  public ElementGet()
  {
    this.type = 36;
  }
  
  public ElementGet(int paramInt)
  {
    super(paramInt);
    this.type = 36;
  }
  
  public ElementGet(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 36;
  }
  
  public ElementGet(AstNode paramAstNode1, AstNode paramAstNode2)
  {
    this.type = 36;
    setTarget(paramAstNode1);
    setElement(paramAstNode2);
  }
  
  public AstNode getElement()
  {
    return this.element;
  }
  
  public int getLb()
  {
    return this.lb;
  }
  
  public int getRb()
  {
    return this.rb;
  }
  
  public AstNode getTarget()
  {
    return this.target;
  }
  
  public void setElement(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.element = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setLb(int paramInt)
  {
    this.lb = paramInt;
  }
  
  public void setParens(int paramInt1, int paramInt2)
  {
    this.lb = paramInt1;
    this.rb = paramInt2;
  }
  
  public void setRb(int paramInt)
  {
    this.rb = paramInt;
  }
  
  public void setTarget(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.target = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append(this.target.toSource(0));
    localStringBuilder.append("[");
    localStringBuilder.append(this.element.toSource(0));
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.target.visit(paramNodeVisitor);
      this.element.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\ElementGet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */