package org.mozilla.javascript.ast;

public class KeywordLiteral
  extends AstNode
{
  public KeywordLiteral() {}
  
  public KeywordLiteral(int paramInt)
  {
    super(paramInt);
  }
  
  public KeywordLiteral(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public KeywordLiteral(int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramInt1, paramInt2);
    setType(paramInt3);
  }
  
  public boolean isBooleanLiteral()
  {
    return (this.type == 45) || (this.type == 44);
  }
  
  public KeywordLiteral setType(int paramInt)
  {
    if ((paramInt != 43) && (paramInt != 42) && (paramInt != 45) && (paramInt != 44) && (paramInt != 161)) {
      throw new IllegalArgumentException("Invalid node type: " + paramInt);
    }
    this.type = paramInt;
    return this;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    switch (getType())
    {
    default: 
      throw new IllegalStateException("Invalid keyword literal type: " + getType());
    case 43: 
      localStringBuilder.append("this");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append("null");
      continue;
      localStringBuilder.append("true");
      continue;
      localStringBuilder.append("false");
      continue;
      localStringBuilder.append("debugger;\n");
    }
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    paramNodeVisitor.visit(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\KeywordLiteral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */