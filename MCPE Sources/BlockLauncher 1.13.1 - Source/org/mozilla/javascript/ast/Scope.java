package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.mozilla.javascript.Node;

public class Scope
  extends Jump
{
  private List<Scope> childScopes;
  protected Scope parentScope;
  protected Map<String, Symbol> symbolTable;
  protected ScriptNode top;
  
  public Scope()
  {
    this.type = 130;
  }
  
  public Scope(int paramInt)
  {
    this.type = 130;
    this.position = paramInt;
  }
  
  public Scope(int paramInt1, int paramInt2)
  {
    this(paramInt1);
    this.length = paramInt2;
  }
  
  private Map<String, Symbol> ensureSymbolTable()
  {
    if (this.symbolTable == null) {
      this.symbolTable = new LinkedHashMap(5);
    }
    return this.symbolTable;
  }
  
  public static void joinScopes(Scope paramScope1, Scope paramScope2)
  {
    Object localObject = paramScope1.ensureSymbolTable();
    paramScope1 = paramScope2.ensureSymbolTable();
    if (!Collections.disjoint(((Map)localObject).keySet(), paramScope1.keySet())) {
      codeBug();
    }
    localObject = ((Map)localObject).entrySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      Symbol localSymbol = (Symbol)localEntry.getValue();
      localSymbol.setContainingTable(paramScope2);
      paramScope1.put(localEntry.getKey(), localSymbol);
    }
  }
  
  public static Scope splitScope(Scope paramScope)
  {
    Scope localScope = new Scope(paramScope.getType());
    localScope.symbolTable = paramScope.symbolTable;
    paramScope.symbolTable = null;
    localScope.parent = paramScope.parent;
    localScope.setParentScope(paramScope.getParentScope());
    localScope.setParentScope(localScope);
    paramScope.parent = localScope;
    localScope.top = paramScope.top;
    return localScope;
  }
  
  public void addChildScope(Scope paramScope)
  {
    if (this.childScopes == null) {
      this.childScopes = new ArrayList();
    }
    this.childScopes.add(paramScope);
    paramScope.setParentScope(this);
  }
  
  public void clearParentScope()
  {
    this.parentScope = null;
  }
  
  public List<Scope> getChildScopes()
  {
    return this.childScopes;
  }
  
  public Scope getDefiningScope(String paramString)
  {
    for (Scope localScope = this; localScope != null; localScope = localScope.parentScope)
    {
      Map localMap = localScope.getSymbolTable();
      if ((localMap != null) && (localMap.containsKey(paramString))) {
        return localScope;
      }
    }
    return null;
  }
  
  public Scope getParentScope()
  {
    return this.parentScope;
  }
  
  public List<AstNode> getStatements()
  {
    ArrayList localArrayList = new ArrayList();
    for (Node localNode = getFirstChild(); localNode != null; localNode = localNode.getNext()) {
      localArrayList.add((AstNode)localNode);
    }
    return localArrayList;
  }
  
  public Symbol getSymbol(String paramString)
  {
    if (this.symbolTable == null) {
      return null;
    }
    return (Symbol)this.symbolTable.get(paramString);
  }
  
  public Map<String, Symbol> getSymbolTable()
  {
    return this.symbolTable;
  }
  
  public ScriptNode getTop()
  {
    return this.top;
  }
  
  public void putSymbol(Symbol paramSymbol)
  {
    if (paramSymbol.getName() == null) {
      throw new IllegalArgumentException("null symbol name");
    }
    ensureSymbolTable();
    this.symbolTable.put(paramSymbol.getName(), paramSymbol);
    paramSymbol.setContainingTable(this);
    this.top.addSymbol(paramSymbol);
  }
  
  public void replaceWith(Scope paramScope)
  {
    if (this.childScopes != null)
    {
      Iterator localIterator = this.childScopes.iterator();
      while (localIterator.hasNext()) {
        paramScope.addChildScope((Scope)localIterator.next());
      }
      this.childScopes.clear();
      this.childScopes = null;
    }
    if ((this.symbolTable != null) && (!this.symbolTable.isEmpty())) {
      joinScopes(this, paramScope);
    }
  }
  
  public void setParentScope(Scope paramScope)
  {
    this.parentScope = paramScope;
    if (paramScope == null) {}
    for (paramScope = (ScriptNode)this;; paramScope = paramScope.top)
    {
      this.top = paramScope;
      return;
    }
  }
  
  public void setSymbolTable(Map<String, Symbol> paramMap)
  {
    this.symbolTable = paramMap;
  }
  
  public void setTop(ScriptNode paramScriptNode)
  {
    this.top = paramScriptNode;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("{\n");
    Iterator localIterator = iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append(((AstNode)localIterator.next()).toSource(paramInt + 1));
    }
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      Iterator localIterator = iterator();
      while (localIterator.hasNext()) {
        ((AstNode)localIterator.next()).visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\Scope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */