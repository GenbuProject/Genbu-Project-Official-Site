package org.simpleframework.xml.core;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.stream.OutputNode;

class Qualifier
  implements Decorator
{
  private NamespaceDecorator decorator = new NamespaceDecorator();
  
  public Qualifier(Contact paramContact)
  {
    scan(paramContact);
  }
  
  private void namespace(Contact paramContact)
  {
    paramContact = (Namespace)paramContact.getAnnotation(Namespace.class);
    if (paramContact != null)
    {
      this.decorator.set(paramContact);
      this.decorator.add(paramContact);
    }
  }
  
  private void scan(Contact paramContact)
  {
    namespace(paramContact);
    scope(paramContact);
  }
  
  private void scope(Contact paramContact)
  {
    paramContact = (NamespaceList)paramContact.getAnnotation(NamespaceList.class);
    if (paramContact != null)
    {
      paramContact = paramContact.value();
      int j = paramContact.length;
      int i = 0;
      while (i < j)
      {
        Namespace localNamespace = paramContact[i];
        this.decorator.add(localNamespace);
        i += 1;
      }
    }
  }
  
  public void decorate(OutputNode paramOutputNode)
  {
    this.decorator.decorate(paramOutputNode);
  }
  
  public void decorate(OutputNode paramOutputNode, Decorator paramDecorator)
  {
    this.decorator.decorate(paramOutputNode, paramDecorator);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Qualifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */