package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArrayLiteral
  extends AstNode
  implements DestructuringForm
{
  private static final List<AstNode> NO_ELEMS = Collections.unmodifiableList(new ArrayList());
  private int destructuringLength;
  private List<AstNode> elements;
  private boolean isDestructuring;
  private int skipCount;
  
  public ArrayLiteral()
  {
    this.type = 66;
  }
  
  public ArrayLiteral(int paramInt)
  {
    super(paramInt);
    this.type = 66;
  }
  
  public ArrayLiteral(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 66;
  }
  
  public void addElement(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    if (this.elements == null) {
      this.elements = new ArrayList();
    }
    this.elements.add(paramAstNode);
    paramAstNode.setParent(this);
  }
  
  public int getDestructuringLength()
  {
    return this.destructuringLength;
  }
  
  public AstNode getElement(int paramInt)
  {
    if (this.elements == null) {
      throw new IndexOutOfBoundsException("no elements");
    }
    return (AstNode)this.elements.get(paramInt);
  }
  
  public List<AstNode> getElements()
  {
    if (this.elements != null) {
      return this.elements;
    }
    return NO_ELEMS;
  }
  
  public int getSize()
  {
    if (this.elements == null) {
      return 0;
    }
    return this.elements.size();
  }
  
  public int getSkipCount()
  {
    return this.skipCount;
  }
  
  public boolean isDestructuring()
  {
    return this.isDestructuring;
  }
  
  public void setDestructuringLength(int paramInt)
  {
    this.destructuringLength = paramInt;
  }
  
  public void setElements(List<AstNode> paramList)
  {
    if (paramList == null) {
      this.elements = null;
    }
    for (;;)
    {
      return;
      if (this.elements != null) {
        this.elements.clear();
      }
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        addElement((AstNode)paramList.next());
      }
    }
  }
  
  public void setIsDestructuring(boolean paramBoolean)
  {
    this.isDestructuring = paramBoolean;
  }
  
  public void setSkipCount(int paramInt)
  {
    this.skipCount = paramInt;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("[");
    if (this.elements != null) {
      printList(this.elements, localStringBuilder);
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      Iterator localIterator = getElements().iterator();
      while (localIterator.hasNext()) {
        ((AstNode)localIterator.next()).visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\ArrayLiteral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */