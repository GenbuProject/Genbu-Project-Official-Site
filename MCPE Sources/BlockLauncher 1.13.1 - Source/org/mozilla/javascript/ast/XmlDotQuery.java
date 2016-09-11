package org.mozilla.javascript.ast;

public class XmlDotQuery
  extends InfixExpression
{
  private int rp = -1;
  
  public XmlDotQuery()
  {
    this.type = 147;
  }
  
  public XmlDotQuery(int paramInt)
  {
    super(paramInt);
    this.type = 147;
  }
  
  public XmlDotQuery(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 147;
  }
  
  public int getRp()
  {
    return this.rp;
  }
  
  public void setRp(int paramInt)
  {
    this.rp = paramInt;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append(getLeft().toSource(0));
    localStringBuilder.append(".(");
    localStringBuilder.append(getRight().toSource(0));
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\XmlDotQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */