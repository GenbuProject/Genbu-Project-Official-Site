package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

class InputNodeMap
  extends LinkedHashMap<String, InputNode>
  implements NodeMap<InputNode>
{
  private final InputNode source;
  
  protected InputNodeMap(InputNode paramInputNode)
  {
    this.source = paramInputNode;
  }
  
  public InputNodeMap(InputNode paramInputNode, EventNode paramEventNode)
  {
    this.source = paramInputNode;
    build(paramEventNode);
  }
  
  private void build(EventNode paramEventNode)
  {
    paramEventNode = paramEventNode.iterator();
    while (paramEventNode.hasNext())
    {
      Attribute localAttribute = (Attribute)paramEventNode.next();
      InputAttribute localInputAttribute = new InputAttribute(this.source, localAttribute);
      if (!localAttribute.isReserved()) {
        put(localInputAttribute.getName(), localInputAttribute);
      }
    }
  }
  
  public InputNode get(String paramString)
  {
    return (InputNode)super.get(paramString);
  }
  
  public String getName()
  {
    return this.source.getName();
  }
  
  public InputNode getNode()
  {
    return this.source;
  }
  
  public Iterator<String> iterator()
  {
    return keySet().iterator();
  }
  
  public InputNode put(String paramString1, String paramString2)
  {
    paramString2 = new InputAttribute(this.source, paramString1, paramString2);
    if (paramString1 != null) {
      put(paramString1, paramString2);
    }
    return paramString2;
  }
  
  public InputNode remove(String paramString)
  {
    return (InputNode)super.remove(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\InputNodeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */