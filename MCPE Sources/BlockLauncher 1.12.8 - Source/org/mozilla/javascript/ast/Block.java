package org.mozilla.javascript.ast;

import java.util.Iterator;

public class Block
  extends AstNode
{
  public Block()
  {
    this.type = 130;
  }
  
  public Block(int paramInt)
  {
    super(paramInt);
    this.type = 130;
  }
  
  public Block(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 130;
  }
  
  public void addStatement(AstNode paramAstNode)
  {
    addChild(paramAstNode);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("{\n");
    Iterator localIterator = iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append(((AstNode)localIterator.next()).toSource(paramInt + 1));
    }
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      Iterator localIterator = iterator();
      while (localIterator.hasNext()) {
        ((AstNode)localIterator.next()).visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\Block.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */