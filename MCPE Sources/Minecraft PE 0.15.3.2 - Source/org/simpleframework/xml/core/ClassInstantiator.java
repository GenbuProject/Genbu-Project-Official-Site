package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ClassInstantiator
  implements Instantiator
{
  private final List<Creator> creators;
  private final Detail detail;
  private final Creator primary;
  private final ParameterMap registry;
  
  public ClassInstantiator(List<Creator> paramList, Creator paramCreator, ParameterMap paramParameterMap, Detail paramDetail)
  {
    this.creators = paramList;
    this.registry = paramParameterMap;
    this.primary = paramCreator;
    this.detail = paramDetail;
  }
  
  private Creator getCreator(Criteria paramCriteria)
    throws Exception
  {
    Object localObject = this.primary;
    double d1 = 0.0D;
    Iterator localIterator = this.creators.iterator();
    while (localIterator.hasNext())
    {
      Creator localCreator = (Creator)localIterator.next();
      double d2 = localCreator.getScore(paramCriteria);
      if (d2 > d1)
      {
        localObject = localCreator;
        d1 = d2;
      }
    }
    return (Creator)localObject;
  }
  
  public List<Creator> getCreators()
  {
    return new ArrayList(this.creators);
  }
  
  public Object getInstance()
    throws Exception
  {
    return this.primary.getInstance();
  }
  
  public Object getInstance(Criteria paramCriteria)
    throws Exception
  {
    Creator localCreator = getCreator(paramCriteria);
    if (localCreator == null) {
      throw new PersistenceException("Constructor not matched for %s", new Object[] { this.detail });
    }
    return localCreator.getInstance(paramCriteria);
  }
  
  public Parameter getParameter(String paramString)
  {
    return (Parameter)this.registry.get(paramString);
  }
  
  public List<Parameter> getParameters()
  {
    return this.registry.getAll();
  }
  
  public boolean isDefault()
  {
    if (this.creators.size() <= 1) {
      return this.primary != null;
    }
    return false;
  }
  
  public String toString()
  {
    return String.format("creator for %s", new Object[] { this.detail });
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ClassInstantiator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */