package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.OutputNode;

abstract interface Decorator
{
  public abstract void decorate(OutputNode paramOutputNode);
  
  public abstract void decorate(OutputNode paramOutputNode, Decorator paramDecorator);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Decorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */