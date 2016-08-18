package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

class OutputNodeMap
  extends LinkedHashMap<String, OutputNode>
  implements NodeMap<OutputNode>
{
  private final OutputNode source;
  
  public OutputNodeMap(OutputNode paramOutputNode)
  {
    this.source = paramOutputNode;
  }
  
  public OutputNode get(String paramString)
  {
    return (OutputNode)super.get(paramString);
  }
  
  public String getName()
  {
    return this.source.getName();
  }
  
  public OutputNode getNode()
  {
    return this.source;
  }
  
  public Iterator<String> iterator()
  {
    return keySet().iterator();
  }
  
  public OutputNode put(String paramString1, String paramString2)
  {
    paramString2 = new OutputAttribute(this.source, paramString1, paramString2);
    if (this.source != null) {
      put(paramString1, paramString2);
    }
    return paramString2;
  }
  
  public OutputNode remove(String paramString)
  {
    return (OutputNode)super.remove(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\OutputNodeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */