package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class Traverser
{
  private final Context context;
  private final Style style;
  
  public Traverser(Context paramContext)
  {
    this.style = paramContext.getStyle();
    this.context = paramContext;
  }
  
  private Composite getComposite(Class paramClass)
    throws Exception
  {
    Type localType = getType(paramClass);
    if (paramClass == null) {
      throw new RootException("Can not instantiate null class", new Object[0]);
    }
    return new Composite(this.context, localType);
  }
  
  private Decorator getDecorator(Class paramClass)
    throws Exception
  {
    return this.context.getDecorator(paramClass);
  }
  
  private Type getType(Class paramClass)
  {
    return new ClassType(paramClass);
  }
  
  private Object read(InputNode paramInputNode, Class paramClass, Object paramObject)
    throws Exception
  {
    if (getName(paramClass) == null) {
      throw new RootException("Root annotation required for %s", new Object[] { paramClass });
    }
    return paramObject;
  }
  
  protected String getName(Class paramClass)
    throws Exception
  {
    paramClass = this.context.getName(paramClass);
    return this.style.getElement(paramClass);
  }
  
  public Object read(InputNode paramInputNode, Class paramClass)
    throws Exception
  {
    paramClass = getComposite(paramClass).read(paramInputNode);
    if (paramClass != null) {
      return read(paramInputNode, paramClass.getClass(), paramClass);
    }
    return null;
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    Class localClass = paramObject.getClass();
    return read(paramInputNode, localClass, getComposite(localClass).read(paramInputNode, paramObject));
  }
  
  public boolean validate(InputNode paramInputNode, Class paramClass)
    throws Exception
  {
    Composite localComposite = getComposite(paramClass);
    if (getName(paramClass) == null) {
      throw new RootException("Root annotation required for %s", new Object[] { paramClass });
    }
    return localComposite.validate(paramInputNode);
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    write(paramOutputNode, paramObject, paramObject.getClass());
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject, Class paramClass)
    throws Exception
  {
    Class localClass = paramObject.getClass();
    String str = getName(localClass);
    if (str == null) {
      throw new RootException("Root annotation required for %s", new Object[] { localClass });
    }
    write(paramOutputNode, paramObject, paramClass, str);
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject, Class paramClass, String paramString)
    throws Exception
  {
    paramOutputNode = paramOutputNode.getChild(paramString);
    paramClass = getType(paramClass);
    if (paramObject != null)
    {
      paramString = paramObject.getClass();
      Decorator localDecorator = getDecorator(paramString);
      if (localDecorator != null) {
        localDecorator.decorate(paramOutputNode);
      }
      if (!this.context.setOverride(paramClass, paramObject, paramOutputNode)) {
        getComposite(paramString).write(paramOutputNode, paramObject);
      }
    }
    paramOutputNode.commit();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Traverser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */