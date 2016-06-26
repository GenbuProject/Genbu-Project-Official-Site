package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XmlLiteral
  extends AstNode
{
  private List<XmlFragment> fragments = new ArrayList();
  
  public XmlLiteral()
  {
    this.type = 146;
  }
  
  public XmlLiteral(int paramInt)
  {
    super(paramInt);
    this.type = 146;
  }
  
  public XmlLiteral(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 146;
  }
  
  public void addFragment(XmlFragment paramXmlFragment)
  {
    assertNotNull(paramXmlFragment);
    this.fragments.add(paramXmlFragment);
    paramXmlFragment.setParent(this);
  }
  
  public List<XmlFragment> getFragments()
  {
    return this.fragments;
  }
  
  public void setFragments(List<XmlFragment> paramList)
  {
    assertNotNull(paramList);
    this.fragments.clear();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      addFragment((XmlFragment)paramList.next());
    }
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(250);
    Iterator localIterator = this.fragments.iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append(((XmlFragment)localIterator.next()).toSource(0));
    }
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      Iterator localIterator = this.fragments.iterator();
      while (localIterator.hasNext()) {
        ((XmlFragment)localIterator.next()).visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\XmlLiteral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */