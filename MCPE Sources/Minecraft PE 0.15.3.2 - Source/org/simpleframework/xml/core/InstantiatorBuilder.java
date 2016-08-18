package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class InstantiatorBuilder
{
  private LabelMap attributes = new LabelMap();
  private Comparer comparer = new Comparer();
  private Detail detail;
  private LabelMap elements = new LabelMap();
  private Instantiator factory;
  private List<Creator> options = new ArrayList();
  private Scanner scanner;
  private LabelMap texts = new LabelMap();
  
  public InstantiatorBuilder(Scanner paramScanner, Detail paramDetail)
  {
    this.scanner = paramScanner;
    this.detail = paramDetail;
  }
  
  private Instantiator build(Detail paramDetail)
    throws Exception
  {
    if (this.factory == null) {
      this.factory = create(paramDetail);
    }
    return this.factory;
  }
  
  private boolean contains(String[] paramArrayOfString, String paramString)
    throws Exception
  {
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      if (str == paramString) {}
      while (str.equals(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private Creator create(Signature paramSignature)
  {
    SignatureCreator localSignatureCreator = new SignatureCreator(paramSignature);
    if (paramSignature != null) {
      this.options.add(localSignatureCreator);
    }
    return localSignatureCreator;
  }
  
  private Instantiator create(Detail paramDetail)
    throws Exception
  {
    Signature localSignature = this.scanner.getSignature();
    ParameterMap localParameterMap = this.scanner.getParameters();
    SignatureCreator localSignatureCreator = null;
    if (localSignature != null) {
      localSignatureCreator = new SignatureCreator(localSignature);
    }
    return new ClassInstantiator(this.options, localSignatureCreator, localParameterMap, paramDetail);
  }
  
  private Parameter create(Parameter paramParameter)
    throws Exception
  {
    Label localLabel = resolve(paramParameter);
    if (localLabel != null) {
      return new CacheParameter(paramParameter, localLabel);
    }
    return null;
  }
  
  private void populate(Detail paramDetail)
    throws Exception
  {
    paramDetail = this.scanner.getSignatures().iterator();
    while (paramDetail.hasNext()) {
      populate((Signature)paramDetail.next());
    }
  }
  
  private void populate(Signature paramSignature)
    throws Exception
  {
    Signature localSignature = new Signature(paramSignature);
    paramSignature = paramSignature.iterator();
    while (paramSignature.hasNext())
    {
      Parameter localParameter = create((Parameter)paramSignature.next());
      if (localParameter != null) {
        localSignature.add(localParameter);
      }
    }
    create(localSignature);
  }
  
  private void register(Label paramLabel, LabelMap paramLabelMap)
    throws Exception
  {
    String str1 = paramLabel.getName();
    String str2 = paramLabel.getPath();
    if (paramLabelMap.containsKey(str1)) {
      if (!((Label)paramLabelMap.get(str1)).getPath().equals(str1)) {
        paramLabelMap.remove(str1);
      }
    }
    for (;;)
    {
      paramLabelMap.put(str2, paramLabel);
      return;
      paramLabelMap.put(str1, paramLabel);
    }
  }
  
  private Label resolve(Parameter paramParameter)
    throws Exception
  {
    if (paramParameter.isAttribute()) {
      return resolve(paramParameter, this.attributes);
    }
    if (paramParameter.isText()) {
      return resolve(paramParameter, this.texts);
    }
    return resolve(paramParameter, this.elements);
  }
  
  private Label resolve(Parameter paramParameter, LabelMap paramLabelMap)
    throws Exception
  {
    String str = paramParameter.getName();
    paramParameter = (Label)paramLabelMap.get(paramParameter.getPath());
    if (paramParameter == null) {
      return (Label)paramLabelMap.get(str);
    }
    return paramParameter;
  }
  
  private void validate(Detail paramDetail)
    throws Exception
  {
    Iterator localIterator = this.scanner.getParameters().getAll().iterator();
    while (localIterator.hasNext())
    {
      Parameter localParameter = (Parameter)localIterator.next();
      Label localLabel = resolve(localParameter);
      String str = localParameter.getPath();
      if (localLabel == null) {
        throw new ConstructorException("Parameter '%s' does not have a match in %s", new Object[] { str, paramDetail });
      }
      validateParameter(localLabel, localParameter);
    }
    validateConstructors();
  }
  
  private void validateAnnotations(Label paramLabel, Parameter paramParameter)
    throws Exception
  {
    Object localObject2 = paramLabel.getAnnotation();
    Object localObject1 = paramParameter.getAnnotation();
    paramLabel = paramParameter.getName();
    if (!this.comparer.equals((Annotation)localObject2, (Annotation)localObject1))
    {
      localObject2 = ((Annotation)localObject2).annotationType();
      localObject1 = ((Annotation)localObject1).annotationType();
      if (!localObject2.equals(localObject1)) {
        throw new ConstructorException("Annotation %s does not match %s for '%s' in %s", new Object[] { localObject1, localObject2, paramLabel, paramParameter });
      }
    }
  }
  
  private void validateConstructor(Label paramLabel, List<Creator> paramList)
    throws Exception
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Signature localSignature = ((Creator)paramList.next()).getSignature();
      Contact localContact = paramLabel.getContact();
      Object localObject = paramLabel.getKey();
      if ((localContact.isReadOnly()) && (localSignature.get(localObject) == null)) {
        paramList.remove();
      }
    }
  }
  
  private void validateConstructors()
    throws Exception
  {
    List localList = this.factory.getCreators();
    if (this.factory.isDefault())
    {
      validateConstructors(this.elements);
      validateConstructors(this.attributes);
    }
    if (!localList.isEmpty())
    {
      validateConstructors(this.elements, localList);
      validateConstructors(this.attributes, localList);
    }
  }
  
  private void validateConstructors(LabelMap paramLabelMap)
    throws Exception
  {
    paramLabelMap = paramLabelMap.iterator();
    while (paramLabelMap.hasNext())
    {
      Label localLabel = (Label)paramLabelMap.next();
      if ((localLabel != null) && (localLabel.getContact().isReadOnly())) {
        throw new ConstructorException("Default constructor can not accept read only %s in %s", new Object[] { localLabel, this.detail });
      }
    }
  }
  
  private void validateConstructors(LabelMap paramLabelMap, List<Creator> paramList)
    throws Exception
  {
    paramLabelMap = paramLabelMap.iterator();
    while (paramLabelMap.hasNext())
    {
      Label localLabel = (Label)paramLabelMap.next();
      if (localLabel != null) {
        validateConstructor(localLabel, paramList);
      }
    }
    if (paramList.isEmpty()) {
      throw new ConstructorException("No constructor accepts all read only values in %s", new Object[] { this.detail });
    }
  }
  
  private void validateNames(Label paramLabel, Parameter paramParameter)
    throws Exception
  {
    Object localObject = paramLabel.getNames();
    String str = paramParameter.getName();
    if (!contains((String[])localObject, str))
    {
      localObject = paramLabel.getName();
      if (str != localObject)
      {
        if ((str == null) || (localObject == null)) {
          throw new ConstructorException("Annotation does not match %s for '%s' in %s", new Object[] { paramLabel, str, paramParameter });
        }
        if (!str.equals(localObject)) {
          throw new ConstructorException("Annotation does not match %s for '%s' in %s", new Object[] { paramLabel, str, paramParameter });
        }
      }
    }
  }
  
  private void validateParameter(Label paramLabel, Parameter paramParameter)
    throws Exception
  {
    Contact localContact = paramLabel.getContact();
    String str = paramParameter.getName();
    if (!Support.isAssignable(paramParameter.getType(), localContact.getType())) {
      throw new ConstructorException("Type is not compatible with %s for '%s' in %s", new Object[] { paramLabel, str, paramParameter });
    }
    validateNames(paramLabel, paramParameter);
    validateAnnotations(paramLabel, paramParameter);
  }
  
  public Instantiator build()
    throws Exception
  {
    if (this.factory == null)
    {
      populate(this.detail);
      build(this.detail);
      validate(this.detail);
    }
    return this.factory;
  }
  
  public void register(Label paramLabel)
    throws Exception
  {
    if (paramLabel.isAttribute())
    {
      register(paramLabel, this.attributes);
      return;
    }
    if (paramLabel.isText())
    {
      register(paramLabel, this.texts);
      return;
    }
    register(paramLabel, this.elements);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\InstantiatorBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */