package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ObjectLiteral
  extends AstNode
  implements DestructuringForm
{
  private static final List<ObjectProperty> NO_ELEMS = Collections.unmodifiableList(new ArrayList());
  private List<ObjectProperty> elements;
  boolean isDestructuring;
  
  public ObjectLiteral()
  {
    this.type = 67;
  }
  
  public ObjectLiteral(int paramInt)
  {
    super(paramInt);
    this.type = 67;
  }
  
  public ObjectLiteral(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 67;
  }
  
  public void addElement(ObjectProperty paramObjectProperty)
  {
    assertNotNull(paramObjectProperty);
    if (this.elements == null) {
      this.elements = new ArrayList();
    }
    this.elements.add(paramObjectProperty);
    paramObjectProperty.setParent(this);
  }
  
  public List<ObjectProperty> getElements()
  {
    if (this.elements != null) {
      return this.elements;
    }
    return NO_ELEMS;
  }
  
  public boolean isDestructuring()
  {
    return this.isDestructuring;
  }
  
  public void setElements(List<ObjectProperty> paramList)
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
        addElement((ObjectProperty)paramList.next());
      }
    }
  }
  
  public void setIsDestructuring(boolean paramBoolean)
  {
    this.isDestructuring = paramBoolean;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("{");
    if (this.elements != null) {
      printList(this.elements, localStringBuilder);
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      Iterator localIterator = getElements().iterator();
      while (localIterator.hasNext()) {
        ((ObjectProperty)localIterator.next()).visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\ObjectLiteral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */