package org.simpleframework.xml.stream;

import java.util.Iterator;

public abstract interface NodeMap<T extends Node>
  extends Iterable<String>
{
  public abstract T get(String paramString);
  
  public abstract String getName();
  
  public abstract T getNode();
  
  public abstract Iterator<String> iterator();
  
  public abstract T put(String paramString1, String paramString2);
  
  public abstract T remove(String paramString);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\NodeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */