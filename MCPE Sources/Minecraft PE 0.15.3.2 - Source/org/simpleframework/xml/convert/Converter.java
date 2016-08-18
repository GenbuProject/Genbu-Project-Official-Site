package org.simpleframework.xml.convert;

import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public abstract interface Converter<T>
{
  public abstract T read(InputNode paramInputNode)
    throws Exception;
  
  public abstract void write(OutputNode paramOutputNode, T paramT)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\convert\Converter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */