package org.simpleframework.xml.core;

import org.simpleframework.xml.Order;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;

class ModelAssembler
{
  private final ExpressionBuilder builder;
  private final Detail detail;
  private final Format format;
  
  public ModelAssembler(ExpressionBuilder paramExpressionBuilder, Detail paramDetail, Support paramSupport)
    throws Exception
  {
    this.format = paramSupport.getFormat();
    this.builder = paramExpressionBuilder;
    this.detail = paramDetail;
  }
  
  private void assembleAttributes(Model paramModel, Order paramOrder)
    throws Exception
  {
    paramOrder = paramOrder.attributes();
    int j = paramOrder.length;
    int i = 0;
    if (i < j)
    {
      String str = paramOrder[i];
      Expression localExpression = this.builder.build(str);
      if ((!localExpression.isAttribute()) && (localExpression.isPath())) {
        throw new PathException("Ordered attribute '%s' references an element in %s", new Object[] { localExpression, this.detail });
      }
      if (!localExpression.isPath()) {
        paramModel.registerAttribute(this.format.getStyle().getAttribute(str));
      }
      for (;;)
      {
        i += 1;
        break;
        registerAttributes(paramModel, localExpression);
      }
    }
  }
  
  private void assembleElements(Model paramModel, Order paramOrder)
    throws Exception
  {
    paramOrder = paramOrder.elements();
    int j = paramOrder.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramOrder[i];
      localObject = this.builder.build((String)localObject);
      if (((Expression)localObject).isAttribute()) {
        throw new PathException("Ordered element '%s' references an attribute in %s", new Object[] { localObject, this.detail });
      }
      registerElements(paramModel, (Expression)localObject);
      i += 1;
    }
  }
  
  private void registerAttribute(Model paramModel, Expression paramExpression)
    throws Exception
  {
    paramExpression = paramExpression.getFirst();
    if (paramExpression != null) {
      paramModel.registerAttribute(paramExpression);
    }
  }
  
  private void registerAttributes(Model paramModel, Expression paramExpression)
    throws Exception
  {
    String str2 = paramExpression.getPrefix();
    String str1 = paramExpression.getFirst();
    int i = paramExpression.getIndex();
    if (paramExpression.isPath())
    {
      paramModel = paramModel.register(str1, str2, i);
      paramExpression = paramExpression.getPath(1);
      if (paramModel == null) {
        throw new PathException("Element '%s' does not exist in %s", new Object[] { str1, this.detail });
      }
      registerAttributes(paramModel, paramExpression);
      return;
    }
    registerAttribute(paramModel, paramExpression);
  }
  
  private void registerElement(Model paramModel, Expression paramExpression)
    throws Exception
  {
    String str1 = paramExpression.getPrefix();
    String str2 = paramExpression.getFirst();
    int i = paramExpression.getIndex();
    if ((i > 1) && (paramModel.lookup(str2, i - 1) == null)) {
      throw new PathException("Ordered element '%s' in path '%s' is out of sequence for %s", new Object[] { str2, paramExpression, this.detail });
    }
    paramModel.register(str2, str1, i);
  }
  
  private void registerElements(Model paramModel, Expression paramExpression)
    throws Exception
  {
    Object localObject1 = paramExpression.getPrefix();
    Object localObject2 = paramExpression.getFirst();
    int i = paramExpression.getIndex();
    if (localObject2 != null)
    {
      localObject1 = paramModel.register((String)localObject2, (String)localObject1, i);
      localObject2 = paramExpression.getPath(1);
      if (paramExpression.isPath()) {
        registerElements((Model)localObject1, (Expression)localObject2);
      }
    }
    registerElement(paramModel, paramExpression);
  }
  
  public void assemble(Model paramModel, Order paramOrder)
    throws Exception
  {
    assembleElements(paramModel, paramOrder);
    assembleAttributes(paramModel, paramOrder);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ModelAssembler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */