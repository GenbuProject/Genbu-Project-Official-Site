package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

public class VariableDeclaration
  extends AstNode
{
  private boolean isStatement;
  private List<VariableInitializer> variables = new ArrayList();
  
  public VariableDeclaration()
  {
    this.type = 123;
  }
  
  public VariableDeclaration(int paramInt)
  {
    super(paramInt);
    this.type = 123;
  }
  
  public VariableDeclaration(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 123;
  }
  
  private String declTypeName()
  {
    return Token.typeToName(this.type).toLowerCase();
  }
  
  public void addVariable(VariableInitializer paramVariableInitializer)
  {
    assertNotNull(paramVariableInitializer);
    this.variables.add(paramVariableInitializer);
    paramVariableInitializer.setParent(this);
  }
  
  public List<VariableInitializer> getVariables()
  {
    return this.variables;
  }
  
  public boolean isConst()
  {
    return this.type == 155;
  }
  
  public boolean isLet()
  {
    return this.type == 154;
  }
  
  public boolean isStatement()
  {
    return this.isStatement;
  }
  
  public boolean isVar()
  {
    return this.type == 123;
  }
  
  public void setIsStatement(boolean paramBoolean)
  {
    this.isStatement = paramBoolean;
  }
  
  public Node setType(int paramInt)
  {
    if ((paramInt != 123) && (paramInt != 155) && (paramInt != 154)) {
      throw new IllegalArgumentException("invalid decl type: " + paramInt);
    }
    return super.setType(paramInt);
  }
  
  public void setVariables(List<VariableInitializer> paramList)
  {
    assertNotNull(paramList);
    this.variables.clear();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      addVariable((VariableInitializer)paramList.next());
    }
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append(declTypeName());
    localStringBuilder.append(" ");
    printList(this.variables, localStringBuilder);
    if (isStatement()) {
      localStringBuilder.append(";\n");
    }
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      Iterator localIterator = this.variables.iterator();
      while (localIterator.hasNext()) {
        ((AstNode)localIterator.next()).visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\VariableDeclaration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */