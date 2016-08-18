package org.simpleframework.xml.strategy;

import org.simpleframework.xml.util.WeakCache;

class ReadState
  extends WeakCache<ReadGraph>
{
  private final Contract contract;
  private final Loader loader = new Loader();
  
  public ReadState(Contract paramContract)
  {
    this.contract = paramContract;
  }
  
  private ReadGraph create(Object paramObject)
    throws Exception
  {
    ReadGraph localReadGraph2 = (ReadGraph)fetch(paramObject);
    ReadGraph localReadGraph1 = localReadGraph2;
    if (localReadGraph2 == null)
    {
      localReadGraph1 = new ReadGraph(this.contract, this.loader);
      cache(paramObject, localReadGraph1);
    }
    return localReadGraph1;
  }
  
  public ReadGraph find(Object paramObject)
    throws Exception
  {
    ReadGraph localReadGraph = (ReadGraph)fetch(paramObject);
    if (localReadGraph != null) {
      return localReadGraph;
    }
    return create(paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\ReadState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */