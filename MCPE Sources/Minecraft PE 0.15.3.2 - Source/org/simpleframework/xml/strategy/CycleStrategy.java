package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.NodeMap;

public class CycleStrategy
  implements Strategy
{
  private final Contract contract;
  private final ReadState read;
  private final WriteState write;
  
  public CycleStrategy()
  {
    this("id", "reference");
  }
  
  public CycleStrategy(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, "class");
  }
  
  public CycleStrategy(String paramString1, String paramString2, String paramString3)
  {
    this(paramString1, paramString2, paramString3, "length");
  }
  
  public CycleStrategy(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.contract = new Contract(paramString1, paramString2, paramString3, paramString4);
    this.write = new WriteState(this.contract);
    this.read = new ReadState(this.contract);
  }
  
  public Value read(Type paramType, NodeMap paramNodeMap, Map paramMap)
    throws Exception
  {
    paramMap = this.read.find(paramMap);
    if (paramMap != null) {
      return paramMap.read(paramType, paramNodeMap);
    }
    return null;
  }
  
  public boolean write(Type paramType, Object paramObject, NodeMap paramNodeMap, Map paramMap)
  {
    paramMap = this.write.find(paramMap);
    if (paramMap != null) {
      return paramMap.write(paramType, paramObject, paramNodeMap);
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\CycleStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */