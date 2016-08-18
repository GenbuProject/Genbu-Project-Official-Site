package org.simpleframework.xml.stream;

class OutputDocument
  implements OutputNode
{
  private String comment;
  private Mode mode = Mode.INHERIT;
  private String name;
  private String reference;
  private OutputStack stack;
  private OutputNodeMap table = new OutputNodeMap(this);
  private String value;
  private NodeWriter writer;
  
  public OutputDocument(NodeWriter paramNodeWriter, OutputStack paramOutputStack)
  {
    this.writer = paramNodeWriter;
    this.stack = paramOutputStack;
  }
  
  public void commit()
    throws Exception
  {
    if (this.stack.isEmpty()) {
      throw new NodeException("No root node");
    }
    this.stack.bottom().commit();
  }
  
  public NodeMap<OutputNode> getAttributes()
  {
    return this.table;
  }
  
  public OutputNode getChild(String paramString)
    throws Exception
  {
    return this.writer.writeElement(this, paramString);
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public Mode getMode()
  {
    return this.mode;
  }
  
  public String getName()
  {
    return null;
  }
  
  public NamespaceMap getNamespaces()
  {
    return null;
  }
  
  public OutputNode getParent()
  {
    return null;
  }
  
  public String getPrefix()
  {
    return null;
  }
  
  public String getPrefix(boolean paramBoolean)
  {
    return null;
  }
  
  public String getReference()
  {
    return this.reference;
  }
  
  public String getValue()
    throws Exception
  {
    return this.value;
  }
  
  public boolean isCommitted()
  {
    return this.stack.isEmpty();
  }
  
  public boolean isRoot()
  {
    return true;
  }
  
  public void remove()
    throws Exception
  {
    if (this.stack.isEmpty()) {
      throw new NodeException("No root node");
    }
    this.stack.bottom().remove();
  }
  
  public OutputNode setAttribute(String paramString1, String paramString2)
  {
    return this.table.put(paramString1, paramString2);
  }
  
  public void setComment(String paramString)
  {
    this.comment = paramString;
  }
  
  public void setData(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mode = Mode.DATA;
      return;
    }
    this.mode = Mode.ESCAPE;
  }
  
  public void setMode(Mode paramMode)
  {
    this.mode = paramMode;
  }
  
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
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\OutputDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */