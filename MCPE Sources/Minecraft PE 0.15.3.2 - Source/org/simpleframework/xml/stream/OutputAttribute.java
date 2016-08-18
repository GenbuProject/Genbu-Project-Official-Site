package org.simpleframework.xml.stream;

class OutputAttribute
  implements OutputNode
{
  private String name;
  private String reference;
  private NamespaceMap scope;
  private OutputNode source;
  private String value;
  
  public OutputAttribute(OutputNode paramOutputNode, String paramString1, String paramString2)
  {
    this.scope = paramOutputNode.getNamespaces();
    this.source = paramOutputNode;
    this.value = paramString2;
    this.name = paramString1;
  }
  
  public void commit() {}
  
  public NodeMap<OutputNode> getAttributes()
  {
    return new OutputNodeMap(this);
  }
  
  public OutputNode getChild(String paramString)
  {
    return null;
  }
  
  public String getComment()
  {
    return null;
  }
  
  public Mode getMode()
  {
    return Mode.INHERIT;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public NamespaceMap getNamespaces()
  {
    return this.scope;
  }
  
  public OutputNode getParent()
  {
    return this.source;
  }
  
  public String getPrefix()
  {
    return this.scope.getPrefix(this.reference);
  }
  
  public String getPrefix(boolean paramBoolean)
  {
    return this.scope.getPrefix(this.reference);
  }
  
  public String getReference()
  {
    return this.reference;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public boolean isCommitted()
  {
    return true;
  }
  
  public boolean isRoot()
  {
    return false;
  }
  
  public void remove() {}
  
  public OutputNode setAttribute(String paramString1, String paramString2)
  {
    return null;
  }
  
  public void setComment(String paramString) {}
  
  public void setData(boolean paramBoolean) {}
  
  public void setMode(Mode paramMode) {}
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setReference(String paramString)
  {
    this.reference = paramString;
  }
  
  public void setValue(String paramString)
  {
    this.value = paramString;
  }
  
  public String toString()
  {
    return String.format("attribute %s='%s'", new Object[] { this.name, this.value });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\OutputAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */