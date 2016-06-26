package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

public class Symbol
{
  private Scope containingTable;
  private int declType;
  private int index = -1;
  private String name;
  private Node node;
  
  public Symbol() {}
  
  public Symbol(int paramInt, String paramString)
  {
    setName(paramString);
    setDeclType(paramInt);
  }
  
  public Scope getContainingTable()
  {
    return this.containingTable;
  }
  
  public int getDeclType()
  {
    return this.declType;
  }
  
  public String getDeclTypeName()
  {
    return Token.typeToName(this.declType);
  }
  
  public int getIndex()
  {
    return this.index;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Node getNode()
  {
    return this.node;
  }
  
  public void setContainingTable(Scope paramScope)
  {
    this.containingTable = paramScope;
  }
  
  public void setDeclType(int paramInt)
  {
    if ((paramInt != 110) && (paramInt != 88) && (paramInt != 123) && (paramInt != 154) && (paramInt != 155)) {
      throw new IllegalArgumentException("Invalid declType: " + paramInt);
    }
    this.declType = paramInt;
  }
  
  public void setIndex(int paramInt)
  {
    this.index = paramInt;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setNode(Node paramNode)
  {
    this.node = paramNode;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Symbol (");
    localStringBuilder.append(getDeclTypeName());
    localStringBuilder.append(") name=");
    localStringBuilder.append(this.name);
    if (this.node != null)
    {
      localStringBuilder.append(" line=");
      localStringBuilder.append(this.node.getLineno());
    }
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\Symbol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */