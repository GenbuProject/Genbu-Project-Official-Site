package org.mozilla.javascript.ast;

public class BreakStatement
  extends Jump
{
  private Name breakLabel;
  private AstNode target;
  
  public BreakStatement()
  {
    this.type = 121;
  }
  
  public BreakStatement(int paramInt)
  {
    this.type = 121;
    this.position = paramInt;
  }
  
  public BreakStatement(int paramInt1, int paramInt2)
  {
    this.type = 121;
    this.position = paramInt1;
    this.length = paramInt2;
  }
  
  public Name getBreakLabel()
  {
    return this.breakLabel;
  }
  
  public AstNode getBreakTarget()
  {
    return this.target;
  }
  
  public void setBreakLabel(Name paramName)
  {
    this.breakLabel = paramName;
    if (paramName != null) {
      paramName.setParent(this);
    }
  }
  
  public void setBreakTarget(Jump paramJump)
  {
    assertNotNull(paramJump);
    this.target = paramJump;
    setJumpStatement(paramJump);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("break");
    if (this.breakLabel != null)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(this.breakLabel.toSource(0));
    }
    localStringBuilder.append(";\n");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if ((paramNodeVisitor.visit(this)) && (this.breakLabel != null)) {
      this.breakLabel.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\BreakStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */