package org.mozilla.javascript.ast;

public class Yield
  extends AstNode
{
  private AstNode value;
  
  public Yield()
  {
    this.type = 73;
  }
  
  public Yield(int paramInt)
  {
    super(paramInt);
    this.type = 73;
  }
  
  public Yield(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 73;
  }
  
  public Yield(int paramInt1, int paramInt2, AstNode paramAstNode)
  {
    super(paramInt1, paramInt2);
    this.type = 73;
    setValue(paramAstNode);
  }
  
  public AstNode getValue()
  {
    return this.value;
  }
  
  public void setValue(AstNode paramAstNode)
  {
    this.value = paramAstNode;
    if (paramAstNode != null) {
      paramAstNode.setParent(this);
    }
  }
  
  public String toSource(int paramInt)
  {
    if (this.value == null) {
      return "yield";
    }
    return "yield " + this.value.toSource(0);
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if ((paramNodeVisitor.visit(this)) && (this.value != null)) {
      this.value.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\Yield.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */