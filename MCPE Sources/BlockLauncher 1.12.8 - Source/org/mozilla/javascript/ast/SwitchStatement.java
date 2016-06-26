package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SwitchStatement
  extends Jump
{
  private static final List<SwitchCase> NO_CASES = Collections.unmodifiableList(new ArrayList());
  private List<SwitchCase> cases;
  private AstNode expression;
  private int lp = -1;
  private int rp = -1;
  
  public SwitchStatement()
  {
    this.type = 115;
  }
  
  public SwitchStatement(int paramInt)
  {
    this.type = 115;
    this.position = paramInt;
  }
  
  public SwitchStatement(int paramInt1, int paramInt2)
  {
    this.type = 115;
    this.position = paramInt1;
    this.length = paramInt2;
  }
  
  public void addCase(SwitchCase paramSwitchCase)
  {
    assertNotNull(paramSwitchCase);
    if (this.cases == null) {
      this.cases = new ArrayList();
    }
    this.cases.add(paramSwitchCase);
    paramSwitchCase.setParent(this);
  }
  
  public List<SwitchCase> getCases()
  {
    if (this.cases != null) {
      return this.cases;
    }
    return NO_CASES;
  }
  
  public AstNode getExpression()
  {
    return this.expression;
  }
  
  public int getLp()
  {
    return this.lp;
  }
  
  public int getRp()
  {
    return this.rp;
  }
  
  public void setCases(List<SwitchCase> paramList)
  {
    if (paramList == null) {
      this.cases = null;
    }
    for (;;)
    {
      return;
      if (this.cases != null) {
        this.cases.clear();
      }
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        addCase((SwitchCase)paramList.next());
      }
    }
  }
  
  public void setExpression(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.expression = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setLp(int paramInt)
  {
    this.lp = paramInt;
  }
  
  public void setParens(int paramInt1, int paramInt2)
  {
    this.lp = paramInt1;
    this.rp = paramInt2;
  }
  
  public void setRp(int paramInt)
  {
    this.rp = paramInt;
  }
  
  public String toSource(int paramInt)
  {
    String str = makeIndent(paramInt);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append("switch (");
    localStringBuilder.append(this.expression.toSource(0));
    localStringBuilder.append(") {\n");
    if (this.cases != null)
    {
      Iterator localIterator = this.cases.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append(((SwitchCase)localIterator.next()).toSource(paramInt + 1));
      }
    }
    localStringBuilder.append(str);
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.expression.visit(paramNodeVisitor);
      Iterator localIterator = getCases().iterator();
      while (localIterator.hasNext()) {
        ((SwitchCase)localIterator.next()).visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\SwitchStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */