package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.Mode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class CompositeInlineMap
  implements Repeater
{
  private final Entry entry;
  private final MapFactory factory;
  private final Converter key;
  private final Style style;
  private final Converter value;
  
  public CompositeInlineMap(Context paramContext, Entry paramEntry, Type paramType)
    throws Exception
  {
    this.factory = new MapFactory(paramContext, paramType);
    this.value = paramEntry.getValue(paramContext);
    this.key = paramEntry.getKey(paramContext);
    this.style = paramContext.getStyle();
    this.entry = paramEntry;
  }
  
  private Object read(InputNode paramInputNode, Map paramMap)
    throws Exception
  {
    InputNode localInputNode = paramInputNode.getParent();
    String str = paramInputNode.getName();
    while (paramInputNode != null)
    {
      Object localObject = this.key.read(paramInputNode);
      paramInputNode = this.value.read(paramInputNode);
      if (paramMap != null) {
        paramMap.put(localObject, paramInputNode);
      }
      paramInputNode = localInputNode.getNext(str);
    }
    return paramMap;
  }
  
  private void write(OutputNode paramOutputNode, Map paramMap, Mode paramMode)
    throws Exception
  {
    String str = this.entry.getEntry();
    str = this.style.getElement(str);
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      OutputNode localOutputNode = paramOutputNode.getChild(str);
      Object localObject2 = paramMap.get(localObject1);
      localOutputNode.setMode(paramMode);
      this.key.write(localOutputNode, localObject1);
      this.value.write(localOutputNode, localObject2);
    }
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    Map localMap = (Map)this.factory.getInstance();
    if (localMap != null) {
      return read(paramInputNode, localMap);
    }
    return null;
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    paramObject = (Map)paramObject;
    if (paramObject != null) {
      return read(paramInputNode, (Map)paramObject);
    }
    return read(paramInputNode);
  }
  
  public boolean validate(InputNode paramInputNode)
    throws Exception
  {
    InputNode localInputNode = paramInputNode.getParent();
    String str = paramInputNode.getName();
    while (paramInputNode != null)
    {
      if (!this.key.validate(paramInputNode)) {}
      while (!this.value.validate(paramInputNode)) {
        return false;
      }
      paramInputNode = localInputNode.getNext(str);
    }
    return true;
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    OutputNode localOutputNode = paramOutputNode.getParent();
    Mode localMode = paramOutputNode.getMode();
    paramObject = (Map)paramObject;
    if (!paramOutputNode.isCommitted()) {
      paramOutputNode.remove();
    }
    write(localOutputNode, (Map)paramObject, localMode);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\CompositeInlineMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */