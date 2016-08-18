package org.simpleframework.xml.stream;

public abstract interface InputNode
  extends Node
{
  public abstract InputNode getAttribute(String paramString);
  
  public abstract NodeMap<InputNode> getAttributes();
  
  public abstract InputNode getNext()
    throws Exception;
  
  public abstract InputNode getNext(String paramString)
    throws Exception;
  
  public abstract InputNode getParent();
  
  public abstract Position getPosition();
  
  public abstract String getPrefix();
  
  public abstract String getReference();
  
  public abstract Object getSource();
  
  public abstract boolean isElement();
  
  public abstract boolean isEmpty()
    throws Exception;
  
  public abstract boolean isRoot();
  
  public abstract void skip()
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\InputNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */