package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LabeledStatement
  extends AstNode
{
  private List<Label> labels = new ArrayList();
  private AstNode statement;
  
  public LabeledStatement()
  {
    this.type = 134;
  }
  
  public LabeledStatement(int paramInt)
  {
    super(paramInt);
    this.type = 134;
  }
  
  public LabeledStatement(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 134;
  }
  
  public void addLabel(Label paramLabel)
  {
    assertNotNull(paramLabel);
    this.labels.add(paramLabel);
    paramLabel.setParent(this);
  }
  
  public Label getFirstLabel()
  {
    return (Label)this.labels.get(0);
  }
  
  public Label getLabelByName(String paramString)
  {
    Iterator localIterator = this.labels.iterator();
    while (localIterator.hasNext())
    {
      Label localLabel = (Label)localIterator.next();
      if (paramString.equals(localLabel.getName())) {
        return localLabel;
      }
    }
    return null;
  }
  
  public List<Label> getLabels()
  {
    return this.labels;
  }
  
  public AstNode getStatement()
  {
    return this.statement;
  }
  
  public boolean hasSideEffects()
  {
    return true;
  }
  
  public void setLabels(List<Label> paramList)
  {
    assertNotNull(paramList);
    if (this.labels != null) {
      this.labels.clear();
    }
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      addLabel((Label)paramList.next());
    }
  }
  
  public void setStatement(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.statement = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.labels.iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append(((Label)localIterator.next()).toSource(paramInt));
    }
    localStringBuilder.append(this.statement.toSource(paramInt + 1));
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      Iterator localIterator = this.labels.iterator();
      while (localIterator.hasNext()) {
        ((AstNode)localIterator.next()).visit(paramNodeVisitor);
      }
      this.statement.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\LabeledStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */