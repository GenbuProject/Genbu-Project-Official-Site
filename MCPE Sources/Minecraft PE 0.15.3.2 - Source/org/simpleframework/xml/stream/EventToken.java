package org.simpleframework.xml.stream;

import java.util.Iterator;

abstract class EventToken
  implements EventNode
{
  public int getLine()
  {
    return -1;
  }
  
  public String getName()
  {
    return null;
  }
  
  public String getPrefix()
  {
    return null;
  }
  
  public String getReference()
  {
    return null;
  }
  
  public Object getSource()
  {
    return null;
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
    return false;
  }
  
  public boolean isText()
  {
    return false;
  }
  
  public Iterator<Attribute> iterator()
  {
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\EventToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */