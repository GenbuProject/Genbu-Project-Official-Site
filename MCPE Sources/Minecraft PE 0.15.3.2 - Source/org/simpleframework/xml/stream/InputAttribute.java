package org.simpleframework.xml.stream;

class InputAttribute
  implements InputNode
{
  private String name;
  private InputNode parent;
  private String prefix;
  private String reference;
  private Object source;
  private String value;
  
  public InputAttribute(InputNode paramInputNode, String paramString1, String paramString2)
  {
    this.parent = paramInputNode;
    this.value = paramString2;
    this.name = paramString1;
  }
  
  public InputAttribute(InputNode paramInputNode, Attribute paramAttribute)
  {
    this.reference = paramAttribute.getReference();
    this.prefix = paramAttribute.getPrefix();
    this.source = paramAttribute.getSource();
    this.value = paramAttribute.getValue();
    this.name = paramAttribute.getName();
    this.parent = paramInputNode;
  }
  
  public InputNode getAttribute(String paramString)
  {
    return null;
  }
  
  public NodeMap<InputNode> getAttributes()
  {
    return new InputNodeMap(this);
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public InputNode getNext()
  {
    return null;
  }
  
  public InputNode getNext(String paramString)
  {
    return null;
  }
  
  public InputNode getParent()
  {
    return this.parent;
  }
  
  public Position getPosition()
  {
    return this.parent.getPosition();
  }
  
  public String getPrefix()
  {
    return this.prefix;
  }
  
  public String getReference()
  {
    return this.reference;
  }
  
  public Object getSource()
  {
    return this.source;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public boolean isElement()
  {
    return false;
  }
  
  public boolean isEmpty()
  {
    return false;
  }
  
  public boolean isRoot()
  {
    return false;
  }
  
  public void skip() {}
  
  public String toString()
  {
    return String.format("attribute %s='%s'", new Object[] { this.name, this.value });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\InputAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */