package org.simpleframework.xml.stream;

import java.util.Iterator;

public abstract interface NamespaceMap
  extends Iterable<String>
{
  public abstract String getPrefix();
  
  public abstract String getPrefix(String paramString);
  
  public abstract String getReference(String paramString);
  
  public abstract Iterator<String> iterator();
  
  public abstract String setReference(String paramString);
  
  public abstract String setReference(String paramString1, String paramString2);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\NamespaceMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */