package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.List;

class SignatureCreator
  implements Creator
{
  private final List<Parameter> list;
  private final Signature signature;
  private final Class type;
  
  public SignatureCreator(Signature paramSignature)
  {
    this.type = paramSignature.getType();
    this.list = paramSignature.getAll();
    this.signature = paramSignature;
  }
  
  private double getAdjustment(double paramDouble)
  {
    double d = this.list.size() / 1000.0D;
    if (paramDouble > 0.0D) {
      return paramDouble / this.list.size() + d;
    }
    return paramDouble / this.list.size();
  }
  
  private double getPercentage(Criteria paramCriteria)
    throws Exception
  {
    double d = 0.0D;
    Iterator localIterator = this.list.iterator();
    while (localIterator.hasNext())
    {
      Parameter localParameter = (Parameter)localIterator.next();
      if (paramCriteria.get(localParameter.getKey()) == null)
      {
        if (localParameter.isRequired()) {
          return -1.0D;
        }
        if (localParameter.isPrimitive()) {
          return -1.0D;
        }
      }
      else
      {
        d += 1.0D;
      }
    }
    return getAdjustment(d);
  }
  
  private Object getVariable(Criteria paramCriteria, int paramInt)
    throws Exception
  {
    paramCriteria = paramCriteria.remove(((Parameter)this.list.get(paramInt)).getKey());
    if (paramCriteria != null) {
      return paramCriteria.getValue();
    }
    return null;
  }
  
  public Object getInstance()
    throws Exception
  {
    return this.signature.create();
  }
  
  public Object getInstance(Criteria paramCriteria)
    throws Exception
  {
    Object[] arrayOfObject = this.list.toArray();
    int i = 0;
    while (i < this.list.size())
    {
      arrayOfObject[i] = getVariable(paramCriteria, i);
      i += 1;
    }
    return this.signature.create(arrayOfObject);
  }
  
  public double getScore(Criteria paramCriteria)
    throws Exception
  {
    Signature localSignature = this.signature.copy();
    Iterator localIterator = paramCriteria.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      Parameter localParameter = localSignature.get(localObject);
      localObject = paramCriteria.get(localObject);
      Contact localContact = ((Variable)localObject).getContact();
      if ((localParameter != null) && (!Support.isAssignable(((Variable)localObject).getValue().getClass(), localParameter.getType()))) {
        return -1.0D;
      }
      if ((localContact.isReadOnly()) && (localParameter == null)) {
        return -1.0D;
      }
    }
    return getPercentage(paramCriteria);
  }
  
  public Signature getSignature()
  {
    return this.signature;
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public String toString()
  {
    return this.signature.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\SignatureCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */