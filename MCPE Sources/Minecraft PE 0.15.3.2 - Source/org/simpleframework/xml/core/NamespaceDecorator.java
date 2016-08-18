package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.stream.NamespaceMap;
import org.simpleframework.xml.stream.OutputNode;

class NamespaceDecorator
  implements Decorator
{
  private Namespace primary;
  private List<Namespace> scope = new ArrayList();
  
  private void namespace(OutputNode paramOutputNode)
  {
    if (this.primary != null) {
      paramOutputNode.setReference(this.primary.reference());
    }
  }
  
  private void scope(OutputNode paramOutputNode)
  {
    paramOutputNode = paramOutputNode.getNamespaces();
    Iterator localIterator = this.scope.iterator();
    while (localIterator.hasNext())
    {
      Namespace localNamespace = (Namespace)localIterator.next();
      paramOutputNode.setReference(localNamespace.reference(), localNamespace.prefix());
    }
  }
  
  public void add(Namespace paramNamespace)
  {
    this.scope.add(paramNamespace);
  }
  
  public void decorate(OutputNode paramOutputNode)
  {
    decorate(paramOutputNode, null);
  }
  
  public void decorate(OutputNode paramOutputNode, Decorator paramDecorator)
  {
    if (paramDecorator != null) {
      paramDecorator.decorate(paramOutputNode);
    }
    scope(paramOutputNode);
    namespace(paramOutputNode);
  }
  
  public void set(Namespace paramNamespace)
  {
    if (paramNamespace != null) {
      add(paramNamespace);
    }
    this.primary = paramNamespace;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\NamespaceDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */