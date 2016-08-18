package org.simpleframework.xml.strategy;

import org.simpleframework.xml.util.WeakCache;

class WriteState
  extends WeakCache<WriteGraph>
{
  private Contract contract;
  
  public WriteState(Contract paramContract)
  {
    this.contract = paramContract;
  }
  
  public WriteGraph find(Object paramObject)
  {
    WriteGraph localWriteGraph2 = (WriteGraph)fetch(paramObject);
    WriteGraph localWriteGraph1 = localWriteGraph2;
    if (localWriteGraph2 == null)
    {
      localWriteGraph1 = new WriteGraph(this.contract);
      cache(paramObject, localWriteGraph1);
    }
    return localWriteGraph1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\WriteState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */