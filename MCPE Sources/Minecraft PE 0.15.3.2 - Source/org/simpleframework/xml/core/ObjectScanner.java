package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

class ObjectScanner
  implements Scanner
{
  private StructureBuilder builder;
  private Detail detail;
  private ClassScanner scanner;
  private Structure structure;
  private Support support;
  
  public ObjectScanner(Detail paramDetail, Support paramSupport)
    throws Exception
  {
    this.scanner = new ClassScanner(paramDetail, paramSupport);
    this.builder = new StructureBuilder(this, paramDetail, paramSupport);
    this.support = paramSupport;
    this.detail = paramDetail;
    scan(paramDetail);
  }
  
  private void commit(Detail paramDetail)
    throws Exception
  {
    paramDetail = paramDetail.getType();
    if (this.structure == null) {
      this.structure = this.builder.build(paramDetail);
    }
    this.builder = null;
  }
  
  private void field(Detail paramDetail)
    throws Exception
  {
    Object localObject = paramDetail.getType();
    paramDetail = paramDetail.getOverride();
    paramDetail = this.support.getFields((Class)localObject, paramDetail).iterator();
    while (paramDetail.hasNext())
    {
      localObject = (Contact)paramDetail.next();
      Annotation localAnnotation = ((Contact)localObject).getAnnotation();
      if (localAnnotation != null) {
        this.builder.process((Contact)localObject, localAnnotation);
      }
    }
  }
  
  private void method(Detail paramDetail)
    throws Exception
  {
    Object localObject = paramDetail.getType();
    paramDetail = paramDetail.getOverride();
    paramDetail = this.support.getMethods((Class)localObject, paramDetail).iterator();
    while (paramDetail.hasNext())
    {
      localObject = (Contact)paramDetail.next();
      Annotation localAnnotation = ((Contact)localObject).getAnnotation();
      if (localAnnotation != null) {
        this.builder.process((Contact)localObject, localAnnotation);
      }
    }
  }
  
  private void order(Detail paramDetail)
    throws Exception
  {
    paramDetail = paramDetail.getType();
    this.builder.assemble(paramDetail);
  }
  
  private void scan(Detail paramDetail)
    throws Exception
  {
    order(paramDetail);
    field(paramDetail);
    method(paramDetail);
    validate(paramDetail);
    commit(paramDetail);
  }
  
  private void validate(Detail paramDetail)
    throws Exception
  {
    paramDetail = paramDetail.getType();
    this.builder.commit(paramDetail);
    this.builder.validate(paramDetail);
  }
  
  public Caller getCaller(Context paramContext)
  {
    return new Caller(this, paramContext);
  }
  
  public Function getCommit()
  {
    return this.scanner.getCommit();
  }
  
  public Function getComplete()
  {
    return this.scanner.getComplete();
  }
  
  public Decorator getDecorator()
  {
    return this.scanner.getDecorator();
  }
  
  public Instantiator getInstantiator()
  {
    return this.structure.getInstantiator();
  }
  
  public String getName()
  {
    return this.detail.getName();
  }
  
  public Order getOrder()
  {
    return this.scanner.getOrder();
  }
  
  public ParameterMap getParameters()
  {
    return this.scanner.getParameters();
  }
  
  public Function getPersist()
  {
    return this.scanner.getPersist();
  }
  
  public Function getReplace()
  {
    return this.scanner.getReplace();
  }
  
  public Function getResolve()
  {
    return this.scanner.getResolve();
  }
  
  public Version getRevision()
  {
    return this.structure.getRevision();
  }
  
  public Section getSection()
  {
    return this.structure.getSection();
  }
  
  public Signature getSignature()
  {
    return this.scanner.getSignature();
  }
  
  public List<Signature> getSignatures()
  {
    return this.scanner.getSignatures();
  }
  
  public Label getText()
  {
    return this.structure.getText();
  }
  
  public Class getType()
  {
    return this.detail.getType();
  }
  
  public Function getValidate()
  {
    return this.scanner.getValidate();
  }
  
  public Label getVersion()
  {
    return this.structure.getVersion();
  }
  
  public boolean isEmpty()
  {
    return this.scanner.getRoot() == null;
  }
  
  public boolean isPrimitive()
  {
    return this.structure.isPrimitive();
  }
  
  public boolean isStrict()
  {
    return this.detail.isStrict();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ObjectScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */