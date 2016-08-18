package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;

class ClassSchema
  implements Schema
{
  private final Caller caller;
  private final Decorator decorator;
  private final Instantiator factory;
  private final boolean primitive;
  private final Version revision;
  private final Section section;
  private final Label text;
  private final Class type;
  private final Label version;
  
  public ClassSchema(Scanner paramScanner, Context paramContext)
    throws Exception
  {
    this.caller = paramScanner.getCaller(paramContext);
    this.factory = paramScanner.getInstantiator();
    this.revision = paramScanner.getRevision();
    this.decorator = paramScanner.getDecorator();
    this.primitive = paramScanner.isPrimitive();
    this.version = paramScanner.getVersion();
    this.section = paramScanner.getSection();
    this.text = paramScanner.getText();
    this.type = paramScanner.getType();
  }
  
  public Caller getCaller()
  {
    return this.caller;
  }
  
  public Decorator getDecorator()
  {
    return this.decorator;
  }
  
  public Instantiator getInstantiator()
  {
    return this.factory;
  }
  
  public Version getRevision()
  {
    return this.revision;
  }
  
  public Section getSection()
  {
    return this.section;
  }
  
  public Label getText()
  {
    return this.text;
  }
  
  public Label getVersion()
  {
    return this.version;
  }
  
  public boolean isPrimitive()
  {
    return this.primitive;
  }
  
  public String toString()
  {
    return String.format("schema for %s", new Object[] { this.type });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ClassSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */