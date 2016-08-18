package org.simpleframework.xml.stream;

abstract interface EventReader
{
  public abstract EventNode next()
    throws Exception;
  
  public abstract EventNode peek()
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\EventReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */