package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.Node;

public class ScriptNode
  extends Scope
{
  private List<FunctionNode> EMPTY_LIST = Collections.emptyList();
  private Object compilerData;
  private String encodedSource;
  private int encodedSourceEnd = -1;
  private int encodedSourceStart = -1;
  private int endLineno = -1;
  private List<FunctionNode> functions;
  private boolean inStrictMode;
  private boolean[] isConsts;
  private int paramCount = 0;
  private List<RegExpLiteral> regexps;
  private String sourceName;
  private List<Symbol> symbols = new ArrayList(4);
  private int tempNumber = 0;
  private String[] variableNames;
  
  public ScriptNode()
  {
    this.top = this;
    this.type = 137;
  }
  
  public ScriptNode(int paramInt)
  {
    super(paramInt);
    this.top = this;
    this.type = 137;
  }
  
  public int addFunction(FunctionNode paramFunctionNode)
  {
    if (paramFunctionNode == null) {
      codeBug();
    }
    if (this.functions == null) {
      this.functions = new ArrayList();
    }
    this.functions.add(paramFunctionNode);
    return this.functions.size() - 1;
  }
  
  public void addRegExp(RegExpLiteral paramRegExpLiteral)
  {
    if (paramRegExpLiteral == null) {
      codeBug();
    }
    if (this.regexps == null) {
      this.regexps = new ArrayList();
    }
    this.regexps.add(paramRegExpLiteral);
    paramRegExpLiteral.putIntProp(4, this.regexps.size() - 1);
  }
  
  void addSymbol(Symbol paramSymbol)
  {
    if (this.variableNames != null) {
      codeBug();
    }
    if (paramSymbol.getDeclType() == 88) {
      this.paramCount += 1;
    }
    this.symbols.add(paramSymbol);
  }
  
  public void flattenSymbolTable(boolean paramBoolean)
  {
    Object localObject1;
    Object localObject2;
    if (!paramBoolean)
    {
      localObject1 = new ArrayList();
      if (this.symbolTable != null)
      {
        i = 0;
        while (i < this.symbols.size())
        {
          localObject2 = (Symbol)this.symbols.get(i);
          if (((Symbol)localObject2).getContainingTable() == this) {
            ((List)localObject1).add(localObject2);
          }
          i += 1;
        }
      }
      this.symbols = ((List)localObject1);
    }
    this.variableNames = new String[this.symbols.size()];
    this.isConsts = new boolean[this.symbols.size()];
    int i = 0;
    if (i < this.symbols.size())
    {
      localObject1 = (Symbol)this.symbols.get(i);
      this.variableNames[i] = ((Symbol)localObject1).getName();
      localObject2 = this.isConsts;
      if (((Symbol)localObject1).getDeclType() == 155) {}
      for (paramBoolean = true;; paramBoolean = false)
      {
        localObject2[i] = paramBoolean;
        ((Symbol)localObject1).setIndex(i);
        i += 1;
        break;
      }
    }
  }
  
  public int getBaseLineno()
  {
    return this.lineno;
  }
  
  public Object getCompilerData()
  {
    return this.compilerData;
  }
  
  public String getEncodedSource()
  {
    return this.encodedSource;
  }
  
  public int getEncodedSourceEnd()
  {
    return this.encodedSourceEnd;
  }
  
  public int getEncodedSourceStart()
  {
    return this.encodedSourceStart;
  }
  
  public int getEndLineno()
  {
    return this.endLineno;
  }
  
  public int getFunctionCount()
  {
    if (this.functions == null) {
      return 0;
    }
    return this.functions.size();
  }
  
  public FunctionNode getFunctionNode(int paramInt)
  {
    return (FunctionNode)this.functions.get(paramInt);
  }
  
  public List<FunctionNode> getFunctions()
  {
    if (this.functions == null) {
      return this.EMPTY_LIST;
    }
    return this.functions;
  }
  
  public int getIndexForNameNode(Node paramNode)
  {
    if (this.variableNames == null) {
      codeBug();
    }
    Scope localScope = paramNode.getScope();
    if (localScope == null) {}
    for (paramNode = null; paramNode == null; paramNode = localScope.getSymbol(((Name)paramNode).getIdentifier())) {
      return -1;
    }
    return paramNode.getIndex();
  }
  
  public String getNextTempName()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("$");
    int i = this.tempNumber;
    this.tempNumber = (i + 1);
    return i;
  }
  
  public boolean[] getParamAndVarConst()
  {
    if (this.variableNames == null) {
      codeBug();
    }
    return this.isConsts;
  }
  
  public int getParamAndVarCount()
  {
    if (this.variableNames == null) {
      codeBug();
    }
    return this.symbols.size();
  }
  
  public String[] getParamAndVarNames()
  {
    if (this.variableNames == null) {
      codeBug();
    }
    return this.variableNames;
  }
  
  public int getParamCount()
  {
    return this.paramCount;
  }
  
  public String getParamOrVarName(int paramInt)
  {
    if (this.variableNames == null) {
      codeBug();
    }
    return this.variableNames[paramInt];
  }
  
  public int getRegexpCount()
  {
    if (this.regexps == null) {
      return 0;
    }
    return this.regexps.size();
  }
  
  public String getRegexpFlags(int paramInt)
  {
    return ((RegExpLiteral)this.regexps.get(paramInt)).getFlags();
  }
  
  public String getRegexpString(int paramInt)
  {
    return ((RegExpLiteral)this.regexps.get(paramInt)).getValue();
  }
  
  public String getSourceName()
  {
    return this.sourceName;
  }
  
  public List<Symbol> getSymbols()
  {
    return this.symbols;
  }
  
  public boolean isInStrictMode()
  {
    return this.inStrictMode;
  }
  
  public void setBaseLineno(int paramInt)
  {
    if ((paramInt < 0) || (this.lineno >= 0)) {
      codeBug();
    }
    this.lineno = paramInt;
  }
  
  public void setCompilerData(Object paramObject)
  {
    assertNotNull(paramObject);
    if (this.compilerData != null) {
      throw new IllegalStateException();
    }
    this.compilerData = paramObject;
  }
  
  public void setEncodedSource(String paramString)
  {
    this.encodedSource = paramString;
  }
  
  public void setEncodedSourceBounds(int paramInt1, int paramInt2)
  {
    this.encodedSourceStart = paramInt1;
    this.encodedSourceEnd = paramInt2;
  }
  
  public void setEncodedSourceEnd(int paramInt)
  {
    this.encodedSourceEnd = paramInt;
  }
  
  public void setEncodedSourceStart(int paramInt)
  {
    this.encodedSourceStart = paramInt;
  }
  
  public void setEndLineno(int paramInt)
  {
    if ((paramInt < 0) || (this.endLineno >= 0)) {
      codeBug();
    }
    this.endLineno = paramInt;
  }
  
  public void setInStrictMode(boolean paramBoolean)
  {
    this.inStrictMode = paramBoolean;
  }
  
  public void setSourceName(String paramString)
  {
    this.sourceName = paramString;
  }
  
  public void setSymbols(List<Symbol> paramList)
  {
    this.symbols = paramList;
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\ScriptNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */