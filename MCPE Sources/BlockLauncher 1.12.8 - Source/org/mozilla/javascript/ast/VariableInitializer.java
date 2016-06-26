package org.mozilla.javascript.ast;

public class VariableInitializer
  extends AstNode
{
  private AstNode initializer;
  private AstNode target;
  
  public VariableInitializer()
  {
    this.type = 123;
  }
  
  public VariableInitializer(int paramInt)
  {
    super(paramInt);
    this.type = 123;
  }
  
  public VariableInitializer(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 123;
  }
  
  public AstNode getInitializer()
  {
    return this.initializer;
  }
  
  public AstNode getTarget()
  {
    return this.target;
  }
  
  public boolean isDestructuring()
  {
    return !(this.target instanceof Name);
  }
  
  public void setInitializer(AstNode paramAstNode)
  {
    this.initializer = paramAstNode;
    if (paramAstNode != null) {
      paramAstNode.setParent(this);
    }
  }
  
  public void setNodeType(int paramInt)
  {
    if ((paramInt != 123) && (paramInt != 155) && (paramInt != 154)) {
      throw new IllegalArgumentException("invalid node type");
    }
    setType(paramInt);
  }
  
  public void setTarget(AstNode paramAstNode)
  {
    if (paramAstNode == null) {
      throw new IllegalArgumentException("invalid target arg");
    }
    this.target = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append(this.target.toSource(0));
    if (this.initializer != null)
    {
      localStringBuilder.append(" = ");
      localStringBuilder.append(this.initializer.toSource(0));
    }
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.target.visit(paramNodeVisitor);
      if (this.initializer != null) {
        this.initializer.visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\VariableInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */