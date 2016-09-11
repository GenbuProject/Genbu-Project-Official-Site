package org.mozilla.javascript.ast;

public class NumberLiteral
  extends AstNode
{
  private double number;
  private String value;
  
  public NumberLiteral()
  {
    this.type = 40;
  }
  
  public NumberLiteral(double paramDouble)
  {
    this.type = 40;
    setDouble(paramDouble);
    setValue(Double.toString(paramDouble));
  }
  
  public NumberLiteral(int paramInt)
  {
    super(paramInt);
    this.type = 40;
  }
  
  public NumberLiteral(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 40;
  }
  
  public NumberLiteral(int paramInt, String paramString)
  {
    super(paramInt);
    this.type = 40;
    setValue(paramString);
    setLength(paramString.length());
  }
  
  public NumberLiteral(int paramInt, String paramString, double paramDouble)
  {
    this(paramInt, paramString);
    setDouble(paramDouble);
  }
  
  public double getNumber()
  {
    return this.number;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public void setNumber(double paramDouble)
  {
    this.number = paramDouble;
  }
  
  public void setValue(String paramString)
  {
    assertNotNull(paramString);
    this.value = paramString;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder().append(makeIndent(paramInt));
    if (this.value == null) {}
    for (String str = "<null>";; str = this.value) {
      return str;
    }
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    paramNodeVisitor.visit(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\NumberLiteral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */