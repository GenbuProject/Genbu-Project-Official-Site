package org.simpleframework.xml.stream;

class InputPosition
  implements Position
{
  private EventNode source;
  
  public InputPosition(EventNode paramEventNode)
  {
    this.source = paramEventNode;
  }
  
  public int getLine()
  {
    return this.source.getLine();
  }
  
  public String toString()
  {
    return String.format("line %s", new Object[] { Integer.valueOf(getLine()) });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\InputPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */