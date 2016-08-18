package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.LimitedCache;

class ExpressionBuilder
{
  private final Cache<Expression> cache = new LimitedCache();
  private final Format format;
  private final Class type;
  
  public ExpressionBuilder(Detail paramDetail, Support paramSupport)
  {
    this.format = paramSupport.getFormat();
    this.type = paramDetail.getType();
  }
  
  private Expression create(String paramString)
    throws Exception
  {
    PathParser localPathParser = new PathParser(paramString, new ClassType(this.type), this.format);
    if (this.cache != null) {
      this.cache.cache(paramString, localPathParser);
    }
    return localPathParser;
  }
  
  public Expression build(String paramString)
    throws Exception
  {
    Expression localExpression2 = (Expression)this.cache.fetch(paramString);
    Expression localExpression1 = localExpression2;
    if (localExpression2 == null) {
      localExpression1 = create(paramString);
    }
    return localExpression1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ExpressionBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */