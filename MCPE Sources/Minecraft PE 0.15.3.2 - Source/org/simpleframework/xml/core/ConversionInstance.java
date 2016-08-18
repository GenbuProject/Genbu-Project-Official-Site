package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;

class ConversionInstance
  implements Instance
{
  private final Context context;
  private final Class convert;
  private final Value value;
  
  public ConversionInstance(Context paramContext, Value paramValue, Class paramClass)
    throws Exception
  {
    this.context = paramContext;
    this.convert = paramClass;
    this.value = paramValue;
  }
  
  public Object getInstance()
    throws Exception
  {
    Object localObject1;
    if (this.value.isReference()) {
      localObject1 = this.value.getValue();
    }
    Object localObject2;
    do
    {
      return localObject1;
      localObject2 = getInstance(this.convert);
      localObject1 = localObject2;
    } while (localObject2 == null);
    setInstance(localObject2);
    return localObject2;
  }
  
  public Object getInstance(Class paramClass)
    throws Exception
  {
    return this.context.getInstance(paramClass).getInstance();
  }
  
  public Class getType()
  {
    return this.convert;
  }
  
  public boolean isReference()
  {
    return this.value.isReference();
  }
  
  public Object setInstance(Object paramObject)
    throws Exception
  {
    if (this.value != null) {
      this.value.setValue(paramObject);
    }
    return paramObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ConversionInstance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */