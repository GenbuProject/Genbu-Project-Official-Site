package org.mozilla.javascript.ast;

public class ArrayComprehensionLoop
  extends ForInLoop
{
  public ArrayComprehensionLoop() {}
  
  public ArrayComprehensionLoop(int paramInt)
  {
    super(paramInt);
  }
  
  public ArrayComprehensionLoop(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public AstNode getBody()
  {
    return null;
  }
  
  public void setBody(AstNode paramAstNode)
  {
    throw new UnsupportedOperationException("this node type has no body");
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder().append(makeIndent(paramInt)).append(" for ");
    if (isForEach())
    {
      str = "each ";
      localStringBuilder = localStringBuilder.append(str).append("(").append(this.iterator.toSource(0));
      if (!isForOf()) {
        break label94;
      }
    }
    label94:
    for (String str = " of ";; str = " in ")
    {
      return str + this.iteratedObject.toSource(0) + ")";
      str = "";
      break;
    }
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.iterator.visit(paramNodeVisitor);
      this.iteratedObject.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\ArrayComprehensionLoop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */