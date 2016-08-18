package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.stream.Format;

class ParameterFactory
{
  private final Format format;
  
  public ParameterFactory(Support paramSupport)
  {
    this.format = paramSupport.getFormat();
  }
  
  private ParameterBuilder getBuilder(Annotation paramAnnotation)
    throws Exception
  {
    if ((paramAnnotation instanceof Element)) {
      return new ParameterBuilder(ElementParameter.class, Element.class);
    }
    if ((paramAnnotation instanceof ElementList)) {
      return new ParameterBuilder(ElementListParameter.class, ElementList.class);
    }
    if ((paramAnnotation instanceof ElementArray)) {
      return new ParameterBuilder(ElementArrayParameter.class, ElementArray.class);
    }
    if ((paramAnnotation instanceof ElementMapUnion)) {
      return new ParameterBuilder(ElementMapUnionParameter.class, ElementMapUnion.class, ElementMap.class);
    }
    if ((paramAnnotation instanceof ElementListUnion)) {
      return new ParameterBuilder(ElementListUnionParameter.class, ElementListUnion.class, ElementList.class);
    }
    if ((paramAnnotation instanceof ElementUnion)) {
      return new ParameterBuilder(ElementUnionParameter.class, ElementUnion.class, Element.class);
    }
    if ((paramAnnotation instanceof ElementMap)) {
      return new ParameterBuilder(ElementMapParameter.class, ElementMap.class);
    }
    if ((paramAnnotation instanceof Attribute)) {
      return new ParameterBuilder(AttributeParameter.class, Attribute.class);
    }
    if ((paramAnnotation instanceof Text)) {
      return new ParameterBuilder(TextParameter.class, Text.class);
    }
    throw new PersistenceException("Annotation %s not supported", new Object[] { paramAnnotation });
  }
  
  private Constructor getConstructor(Annotation paramAnnotation)
    throws Exception
  {
    paramAnnotation = getBuilder(paramAnnotation).getConstructor();
    if (!paramAnnotation.isAccessible()) {
      paramAnnotation.setAccessible(true);
    }
    return paramAnnotation;
  }
  
  public Parameter getInstance(Constructor paramConstructor, Annotation paramAnnotation, int paramInt)
    throws Exception
  {
    return getInstance(paramConstructor, paramAnnotation, null, paramInt);
  }
  
  public Parameter getInstance(Constructor paramConstructor, Annotation paramAnnotation1, Annotation paramAnnotation2, int paramInt)
    throws Exception
  {
    Constructor localConstructor = getConstructor(paramAnnotation1);
    if (paramAnnotation2 != null) {
      return (Parameter)localConstructor.newInstance(new Object[] { paramConstructor, paramAnnotation1, paramAnnotation2, this.format, Integer.valueOf(paramInt) });
    }
    return (Parameter)localConstructor.newInstance(new Object[] { paramConstructor, paramAnnotation1, this.format, Integer.valueOf(paramInt) });
  }
  
  private static class ParameterBuilder
  {
    private final Class entry;
    private final Class label;
    private final Class type;
    
    public ParameterBuilder(Class paramClass1, Class paramClass2)
    {
      this(paramClass1, paramClass2, null);
    }
    
    public ParameterBuilder(Class paramClass1, Class paramClass2, Class paramClass3)
    {
      this.label = paramClass2;
      this.entry = paramClass3;
      this.type = paramClass1;
    }
    
    private Constructor getConstructor(Class... paramVarArgs)
      throws Exception
    {
      return this.type.getConstructor(paramVarArgs);
    }
    
    public Constructor getConstructor()
      throws Exception
    {
      if (this.entry != null) {
        return getConstructor(this.label, this.entry);
      }
      return getConstructor(this.label);
    }
    
    public Constructor getConstructor(Class paramClass)
      throws Exception
    {
      return getConstructor(new Class[] { Constructor.class, paramClass, Format.class, Integer.TYPE });
    }
    
    public Constructor getConstructor(Class paramClass1, Class paramClass2)
      throws Exception
    {
      return getConstructor(new Class[] { Constructor.class, paramClass1, paramClass2, Format.class, Integer.TYPE });
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ParameterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */