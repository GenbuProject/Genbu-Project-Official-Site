package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

class PrimitiveScanner
  implements Scanner
{
  private final Detail detail;
  private final Section section = new EmptySection(this);
  
  public PrimitiveScanner(Detail paramDetail)
  {
    this.detail = paramDetail;
  }
  
  public Caller getCaller(Context paramContext)
  {
    return new Caller(this, paramContext);
  }
  
  public Function getCommit()
  {
    return null;
  }
  
  public Function getComplete()
  {
    return null;
  }
  
  public Decorator getDecorator()
  {
    return null;
  }
  
  public Instantiator getInstantiator()
  {
    return null;
  }
  
  public String getName()
  {
    return null;
  }
  
  public Order getOrder()
  {
    return null;
  }
  
  public ParameterMap getParameters()
  {
    return new ParameterMap();
  }
  
  public Function getPersist()
  {
    return null;
  }
  
  public Function getReplace()
  {
    return null;
  }
  
  public Function getResolve()
  {
    return null;
  }
  
  public Version getRevision()
  {
    return null;
  }
  
  public Section getSection()
  {
    return this.section;
  }
  
  public Signature getSignature()
  {
    return null;
  }
  
  public List<Signature> getSignatures()
  {
    return new LinkedList();
  }
  
  public Label getText()
  {
    return null;
  }
  
  public Class getType()
  {
    return this.detail.getType();
  }
  
  public Function getValidate()
  {
    return null;
  }
  
  public Label getVersion()
  {
    return null;
  }
  
  public boolean isEmpty()
  {
    return true;
  }
  
  public boolean isPrimitive()
  {
    return true;
  }
  
  public boolean isStrict()
  {
    return true;
  }
  
  private static class EmptySection
    implements Section
  {
    private final List<String> list = new LinkedList();
    private final Scanner scanner;
    
    public EmptySection(Scanner paramScanner)
    {
      this.scanner = paramScanner;
    }
    
    public String getAttribute(String paramString)
    {
      return null;
    }
    
    public LabelMap getAttributes()
    {
      return new LabelMap(this.scanner);
    }
    
    public Label getElement(String paramString)
    {
      return null;
    }
    
    public LabelMap getElements()
    {
      return new LabelMap(this.scanner);
    }
    
    public String getName()
    {
      return null;
    }
    
    public String getPath(String paramString)
    {
      return null;
    }
    
    public String getPrefix()
    {
      return null;
    }
    
    public Section getSection(String paramString)
    {
      return null;
    }
    
    public Label getText()
    {
      return null;
    }
    
    public boolean isSection(String paramString)
    {
      return false;
    }
    
    public Iterator<String> iterator()
    {
      return this.list.iterator();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\PrimitiveScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */