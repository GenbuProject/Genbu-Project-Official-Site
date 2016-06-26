package org.mozilla.javascript.ast;

public class ObjectProperty
  extends InfixExpression
{
  public ObjectProperty()
  {
    this.type = 104;
  }
  
  public ObjectProperty(int paramInt)
  {
    super(paramInt);
    this.type = 104;
  }
  
  public ObjectProperty(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 104;
  }
  
  public boolean isGetterMethod()
  {
    return this.type == 152;
  }
  
  public boolean isMethod()
  {
    return (isGetterMethod()) || (isSetterMethod()) || (isNormalMethod());
  }
  
  public boolean isNormalMethod()
  {
    return this.type == 164;
  }
  
  public boolean isSetterMethod()
  {
    return this.type == 153;
  }
  
  public void setIsGetterMethod()
  {
    this.type = 152;
  }
  
  public void setIsNormalMethod()
  {
    this.type = 164;
  }
  
  public void setIsSetterMethod()
  {
    this.type = 153;
  }
  
  public void setNodeType(int paramInt)
  {
    if ((paramInt != 104) && (paramInt != 152) && (paramInt != 153) && (paramInt != 164)) {
      throw new IllegalArgumentException("invalid node type: " + paramInt);
    }
    setType(paramInt);
  }
  
  public String toSource(int paramInt)
  {
    int j = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\n");
    localStringBuilder.append(makeIndent(paramInt + 1));
    AstNode localAstNode;
    int i;
    if (isGetterMethod())
    {
      localStringBuilder.append("get ");
      localAstNode = this.left;
      if (getType() != 104) {
        break label146;
      }
      i = 0;
      label64:
      localStringBuilder.append(localAstNode.toSource(i));
      if (this.type == 104) {
        localStringBuilder.append(": ");
      }
      localAstNode = this.right;
      if (getType() != 104) {
        break label151;
      }
      paramInt = j;
    }
    for (;;)
    {
      localStringBuilder.append(localAstNode.toSource(paramInt));
      return localStringBuilder.toString();
      if (!isSetterMethod()) {
        break;
      }
      localStringBuilder.append("set ");
      break;
      label146:
      i = paramInt;
      break label64;
      label151:
      paramInt += 1;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\ObjectProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */