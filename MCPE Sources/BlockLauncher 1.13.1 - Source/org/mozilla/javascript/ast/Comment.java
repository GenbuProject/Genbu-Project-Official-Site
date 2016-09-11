package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token.CommentType;

public class Comment
  extends AstNode
{
  private Token.CommentType commentType;
  private String value;
  
  public Comment(int paramInt1, int paramInt2, Token.CommentType paramCommentType, String paramString)
  {
    super(paramInt1, paramInt2);
    this.type = 162;
    this.commentType = paramCommentType;
    this.value = paramString;
  }
  
  public Token.CommentType getCommentType()
  {
    return this.commentType;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public void setCommentType(Token.CommentType paramCommentType)
  {
    this.commentType = paramCommentType;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(getLength() + 10);
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append(this.value);
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    paramNodeVisitor.visit(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\Comment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */