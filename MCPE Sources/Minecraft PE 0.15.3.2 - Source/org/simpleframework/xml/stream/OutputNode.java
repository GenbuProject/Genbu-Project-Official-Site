package org.simpleframework.xml.stream;

public abstract interface OutputNode
  extends Node
{
  public abstract void commit()
    throws Exception;
  
  public abstract NodeMap<OutputNode> getAttributes();
  
  public abstract OutputNode getChild(String paramString)
    throws Exception;
  
  public abstract String getComment();
  
  public abstract Mode getMode();
  
  public abstract NamespaceMap getNamespaces();
  
  public abstract OutputNode getParent();
  
  public abstract String getPrefix();
  
  public abstract String getPrefix(boolean paramBoolean);
  
  public abstract String getReference();
  
  public abstract boolean isCommitted();
  
  public abstract boolean isRoot();
  
  public abstract void remove()
    throws Exception;
  
  public abstract OutputNode setAttribute(String paramString1, String paramString2);
  
  public abstract void setComment(String paramString);
  
  public abstract void setData(boolean paramBoolean);
  
  public abstract void setMode(Mode paramMode);
  
  public abstract void setName(String paramString);
  
  public abstract void setReference(String paramString);
  
  public abstract void setValue(String paramString);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\OutputNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */