package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

abstract interface Converter
{
  public abstract Object read(InputNode paramInputNode)
    throws Exception;
  
  public abstract Object read(InputNode paramInputNode, Object paramObject)
    throws Exception;
  
  public abstract boolean validate(InputNode paramInputNode)
    throws Exception;
  
  public abstract void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Converter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */