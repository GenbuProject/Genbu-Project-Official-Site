package org.mozilla.javascript.ast;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class AstRoot
  extends ScriptNode
{
  private SortedSet<Comment> comments;
  
  public AstRoot()
  {
    this.type = 137;
  }
  
  public AstRoot(int paramInt)
  {
    super(paramInt);
    this.type = 137;
  }
  
  public void addComment(Comment paramComment)
  {
    assertNotNull(paramComment);
    if (this.comments == null) {
      this.comments = new TreeSet(new AstNode.PositionComparator());
    }
    this.comments.add(paramComment);
    paramComment.setParent(this);
  }
  
  public void checkParentLinks()
  {
    visit(new NodeVisitor()
    {
      public boolean visit(AstNode paramAnonymousAstNode)
      {
        if (paramAnonymousAstNode.getType() == 137) {}
        while (paramAnonymousAstNode.getParent() != null) {
          return true;
        }
        throw new IllegalStateException("No parent for node: " + paramAnonymousAstNode + "\n" + paramAnonymousAstNode.toSource(0));
      }
    });
  }
  
  public String debugPrint()
  {
    AstNode.DebugPrintVisitor localDebugPrintVisitor = new AstNode.DebugPrintVisitor(new StringBuilder(1000));
    visitAll(localDebugPrintVisitor);
    return localDebugPrintVisitor.toString();
  }
  
  public SortedSet<Comment> getComments()
  {
    return this.comments;
  }
  
  public void setComments(SortedSet<Comment> paramSortedSet)
  {
    if (paramSortedSet == null) {
      this.comments = null;
    }
    for (;;)
    {
      return;
      if (this.comments != null) {
        this.comments.clear();
      }
      paramSortedSet = paramSortedSet.iterator();
      while (paramSortedSet.hasNext()) {
        addComment((Comment)paramSortedSet.next());
      }
    }
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append(((AstNode)localIterator.next()).toSource(paramInt));
    }
    return localStringBuilder.toString();
  }
  
  public void visitAll(NodeVisitor paramNodeVisitor)
  {
    visit(paramNodeVisitor);
    visitComments(paramNodeVisitor);
  }
  
  public void visitComments(NodeVisitor paramNodeVisitor)
  {
    if (this.comments != null)
    {
      Iterator localIterator = this.comments.iterator();
      while (localIterator.hasNext()) {
        paramNodeVisitor.visit((Comment)localIterator.next());
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\AstRoot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */