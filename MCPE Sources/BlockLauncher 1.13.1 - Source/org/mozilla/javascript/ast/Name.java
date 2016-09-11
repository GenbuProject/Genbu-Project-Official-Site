package org.mozilla.javascript.ast;

public class Name
  extends AstNode
{
  private String identifier;
  private Scope scope;
  
  public Name()
  {
    this.type = 39;
  }
  
  public Name(int paramInt)
  {
    super(paramInt);
    this.type = 39;
  }
  
  public Name(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 39;
  }
  
  public Name(int paramInt1, int paramInt2, String paramString)
  {
    super(paramInt1, paramInt2);
    this.type = 39;
    setIdentifier(paramString);
  }
  
  public Name(int paramInt, String paramString)
  {
    super(paramInt);
    this.type = 39;
    setIdentifier(paramString);
    setLength(paramString.length());
  }
  
  public Scope getDefiningScope()
  {
    Scope localScope = getEnclosingScope();
    String str = getIdentifier();
    if (localScope == null) {
      return null;
    }
    return localScope.getDefiningScope(str);
  }
  
  public String getIdentifier()
  {
    return this.identifier;
  }
  
  public Scope getScope()
  {
    return this.scope;
  }
  
  public boolean isLocalName()
  {
    Scope localScope = getDefiningScope();
    return (localScope != null) && (localScope.getParentScope() != null);
  }
  
  public int length()
  {
    if (this.identifier == null) {
      return 0;
    }
    return this.identifier.length();
  }
  
  public void setIdentifier(String paramString)
  {
    assertNotNull(paramString);
    this.identifier = paramString;
    setLength(paramString.length());
  }
  
  public void setScope(Scope paramScope)
  {
    this.scope = paramScope;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder().append(makeIndent(paramInt));
    if (this.identifier == null) {}
    for (String str = "<null>";; str = this.identifier) {
      return str;
    }
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    paramNodeVisitor.visit(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\Name.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */