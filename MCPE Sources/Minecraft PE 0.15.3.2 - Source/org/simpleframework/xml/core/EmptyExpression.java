package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;

class EmptyExpression
  implements Expression
{
  private final List<String> list = new LinkedList();
  private final Style style;
  
  public EmptyExpression(Format paramFormat)
  {
    this.style = paramFormat.getStyle();
  }
  
  public String getAttribute(String paramString)
  {
    return this.style.getAttribute(paramString);
  }
  
  public String getElement(String paramString)
  {
    return this.style.getElement(paramString);
  }
  
  public String getFirst()
  {
    return null;
  }
  
  public int getIndex()
  {
    return 0;
  }
  
  public String getLast()
  {
    return null;
  }
  
  public String getPath()
  {
    return "";
  }
  
  public Expression getPath(int paramInt)
  {
    return null;
  }
  
  public Expression getPath(int paramInt1, int paramInt2)
  {
    return null;
  }
  
  public String getPrefix()
  {
    return null;
  }
  
  public boolean isAttribute()
  {
    return false;
  }
  
  public boolean isEmpty()
  {
    return true;
  }
  
  public boolean isPath()
  {
    return false;
  }
  
  public Iterator<String> iterator()
  {
    return this.list.iterator();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\EmptyExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */