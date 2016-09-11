package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SwitchCase
  extends AstNode
{
  private AstNode expression;
  private List<AstNode> statements;
  
  public SwitchCase()
  {
    this.type = 116;
  }
  
  public SwitchCase(int paramInt)
  {
    super(paramInt);
    this.type = 116;
  }
  
  public SwitchCase(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 116;
  }
  
  public void addStatement(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    if (this.statements == null) {
      this.statements = new ArrayList();
    }
    setLength(paramAstNode.getPosition() + paramAstNode.getLength() - getPosition());
    this.statements.add(paramAstNode);
    paramAstNode.setParent(this);
  }
  
  public AstNode getExpression()
  {
    return this.expression;
  }
  
  public List<AstNode> getStatements()
  {
    return this.statements;
  }
  
  public boolean isDefault()
  {
    return this.expression == null;
  }
  
  public void setExpression(AstNode paramAstNode)
  {
    this.expression = paramAstNode;
    if (paramAstNode != null) {
      paramAstNode.setParent(this);
    }
  }
  
  public void setStatements(List<AstNode> paramList)
  {
    if (this.statements != null) {
      this.statements.clear();
    }
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      addStatement((AstNode)paramList.next());
    }
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    if (this.expression == null) {
      localStringBuilder.append("default:\n");
    }
    while (this.statements != null)
    {
      Iterator localIterator = this.statements.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append(((AstNode)localIterator.next()).toSource(paramInt + 1));
      }
      localStringBuilder.append("case ");
      localStringBuilder.append(this.expression.toSource(0));
      localStringBuilder.append(":\n");
    }
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      if (this.expression != null) {
        this.expression.visit(paramNodeVisitor);
      }
      if (this.statements != null)
      {
        Iterator localIterator = this.statements.iterator();
        while (localIterator.hasNext()) {
          ((AstNode)localIterator.next()).visit(paramNodeVisitor);
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\SwitchCase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */