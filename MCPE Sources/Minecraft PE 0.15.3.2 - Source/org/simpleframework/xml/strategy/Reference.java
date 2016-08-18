package org.simpleframework.xml.strategy;

class Reference
  implements Value
{
  private Class type;
  private Object value;
  
  public Reference(Object paramObject, Class paramClass)
  {
    this.value = paramObject;
    this.type = paramClass;
  }
  
  public int getLength()
  {
    return 0;
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public Object getValue()
  {
    return this.value;
  }
  
  public boolean isReference()
  {
    return true;
  }
  
  public void setValue(Object paramObject)
  {
    this.value = paramObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\Reference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */