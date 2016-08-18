package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import org.simpleframework.xml.strategy.Value;

class ArrayInstance
  implements Instance
{
  private final int length;
  private final Class type;
  private final Value value;
  
  public ArrayInstance(Value paramValue)
  {
    this.length = paramValue.getLength();
    this.type = paramValue.getType();
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
      localObject2 = Array.newInstance(this.type, this.length);
      localObject1 = localObject2;
    } while (this.value == null);
    this.value.setValue(localObject2);
    return localObject2;
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public boolean isReference()
  {
    return this.value.isReference();
  }
  
  public Object setInstance(Object paramObject)
  {
    if (this.value != null) {
      this.value.setValue(paramObject);
    }
    return paramObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ArrayInstance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */