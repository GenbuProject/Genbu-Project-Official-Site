package org.simpleframework.xml.stream;

public abstract interface Node
{
  public abstract String getName();
  
  public abstract Node getParent();
  
  public abstract String getValue()
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\Node.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */