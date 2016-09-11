package org.mozilla.javascript.ast;

import org.mozilla.javascript.ScriptRuntime;

public class StringLiteral
  extends AstNode
{
  private char quoteChar;
  private String value;
  
  public StringLiteral()
  {
    this.type = 41;
  }
  
  public StringLiteral(int paramInt)
  {
    super(paramInt);
    this.type = 41;
  }
  
  public StringLiteral(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 41;
  }
  
  public char getQuoteCharacter()
  {
    return this.quoteChar;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public String getValue(boolean paramBoolean)
  {
    if (!paramBoolean) {
      return this.value;
    }
    return this.quoteChar + this.value + this.quoteChar;
  }
  
  public void setQuoteCharacter(char paramChar)
  {
    this.quoteChar = paramChar;
  }
  
  public void setValue(String paramString)
  {
    assertNotNull(paramString);
    this.value = paramString;
  }
  
  public String toSource(int paramInt)
  {
    return makeIndent(paramInt) + this.quoteChar + ScriptRuntime.escapeString(this.value, this.quoteChar) + this.quoteChar;
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    paramNodeVisitor.visit(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\StringLiteral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */