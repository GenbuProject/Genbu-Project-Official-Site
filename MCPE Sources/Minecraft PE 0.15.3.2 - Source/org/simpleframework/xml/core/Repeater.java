package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.InputNode;

abstract interface Repeater
  extends Converter
{
  public abstract Object read(InputNode paramInputNode, Object paramObject)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Repeater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */