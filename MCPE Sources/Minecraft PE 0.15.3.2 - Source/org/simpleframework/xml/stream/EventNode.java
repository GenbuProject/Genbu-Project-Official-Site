package org.simpleframework.xml.stream;

abstract interface EventNode
  extends Iterable<Attribute>
{
  public abstract int getLine();
  
  public abstract String getName();
  
  public abstract String getPrefix();
  
  public abstract String getReference();
  
  public abstract Object getSource();
  
  public abstract String getValue();
  
  public abstract boolean isEnd();
  
  public abstract boolean isStart();
  
  public abstract boolean isText();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\EventNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */