package org.mozilla.javascript.ast;

public class ContinueStatement
  extends Jump
{
  private Name label;
  private Loop target;
  
  public ContinueStatement()
  {
    this.type = 122;
  }
  
  public ContinueStatement(int paramInt)
  {
    this(paramInt, -1);
  }
  
  public ContinueStatement(int paramInt1, int paramInt2)
  {
    this.type = 122;
    this.position = paramInt1;
    this.length = paramInt2;
  }
  
  public ContinueStatement(int paramInt1, int paramInt2, Name paramName)
  {
    this(paramInt1, paramInt2);
    setLabel(paramName);
  }
  
  public ContinueStatement(int paramInt, Name paramName)
  {
    this(paramInt);
    setLabel(paramName);
  }
  
  public ContinueStatement(Name paramName)
  {
    this.type = 122;
    setLabel(paramName);
  }
  
  public Name getLabel()
  {
    return this.label;
  }
  
  public Loop getTarget()
  {
    return this.target;
  }
  
  public void setLabel(Name paramName)
  {
    this.label = paramName;
    if (paramName != null) {
      paramName.setParent(this);
    }
  }
  
  public void setTarget(Loop paramLoop)
  {
    assertNotNull(paramLoop);
    this.target = paramLoop;
    setJumpStatement(paramLoop);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("continue");
    if (this.label != null)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(this.label.toSource(0));
    }
    localStringBuilder.append(";\n");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if ((paramNodeVisitor.visit(this)) && (this.label != null)) {
      this.label.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\ContinueStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */