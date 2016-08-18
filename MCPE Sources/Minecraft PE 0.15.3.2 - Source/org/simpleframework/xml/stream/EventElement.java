package org.simpleframework.xml.stream;

import java.util.ArrayList;

abstract class EventElement
  extends ArrayList<Attribute>
  implements EventNode
{
  public int getLine()
  {
    return -1;
  }
  
  public String getValue()
  {
    return null;
  }
  
  public boolean isEnd()
  {
    return false;
  }
  
  public boolean isStart()
  {
    return true;
  }
  
  public boolean isText()
  {
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\EventElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */