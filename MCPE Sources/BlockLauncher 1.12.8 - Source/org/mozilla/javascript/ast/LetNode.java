package org.mozilla.javascript.ast;

public class LetNode
  extends Scope
{
  private AstNode body;
  private int lp = -1;
  private int rp = -1;
  private VariableDeclaration variables;
  
  public LetNode()
  {
    this.type = 159;
  }
  
  public LetNode(int paramInt)
  {
    super(paramInt);
    this.type = 159;
  }
  
  public LetNode(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 159;
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
  
  public VariableDeclaration getVariables()
  {
    return this.variables;
  }
  
  public void setBody(AstNode paramAstNode)
  {
    this.body = paramAstNode;
    if (paramAstNode != null) {
      paramAstNode.setParent(this);
    }
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
  
  public void setVariables(VariableDeclaration paramVariableDeclaration)
  {
    assertNotNull(paramVariableDeclaration);
    this.variables = paramVariableDeclaration;
    paramVariableDeclaration.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    String str = makeIndent(paramInt);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append("let (");
    printList(this.variables.getVariables(), localStringBuilder);
    localStringBuilder.append(") ");
    if (this.body != null) {
      localStringBuilder.append(this.body.toSource(paramInt));
    }
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.variables.visit(paramNodeVisitor);
      if (this.body != null) {
        this.body.visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\LetNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */