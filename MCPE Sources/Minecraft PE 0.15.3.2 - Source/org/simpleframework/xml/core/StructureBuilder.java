package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.Version;
import org.simpleframework.xml.strategy.Type;

class StructureBuilder
{
  private ModelAssembler assembler;
  private LabelMap attributes;
  private ExpressionBuilder builder;
  private LabelMap elements;
  private Instantiator factory;
  private boolean primitive;
  private InstantiatorBuilder resolver;
  private Model root;
  private Scanner scanner;
  private Support support;
  private Label text;
  private LabelMap texts;
  private Label version;
  
  public StructureBuilder(Scanner paramScanner, Detail paramDetail, Support paramSupport)
    throws Exception
  {
    this.builder = new ExpressionBuilder(paramDetail, paramSupport);
    this.assembler = new ModelAssembler(this.builder, paramDetail, paramSupport);
    this.resolver = new InstantiatorBuilder(paramScanner, paramDetail);
    this.root = new TreeModel(paramScanner, paramDetail);
    this.attributes = new LabelMap(paramScanner);
    this.elements = new LabelMap(paramScanner);
    this.texts = new LabelMap(paramScanner);
    this.scanner = paramScanner;
    this.support = paramSupport;
  }
  
  private Model create(Expression paramExpression)
    throws Exception
  {
    Object localObject = this.root;
    Expression localExpression = paramExpression;
    for (paramExpression = (Expression)localObject;; paramExpression = (Expression)localObject)
    {
      localObject = paramExpression;
      if (paramExpression != null)
      {
        String str1 = localExpression.getPrefix();
        String str2 = localExpression.getFirst();
        int i = localExpression.getIndex();
        localObject = paramExpression;
        if (str2 != null) {
          localObject = paramExpression.register(str2, str1, i);
        }
        if (localExpression.isPath()) {}
      }
      else
      {
        return (Model)localObject;
      }
      localExpression = localExpression.getPath(1);
    }
  }
  
  private boolean isAttribute(String paramString)
    throws Exception
  {
    Expression localExpression = this.builder.build(paramString);
    Model localModel = lookup(localExpression);
    if (localModel != null)
    {
      String str = localExpression.getLast();
      if (!localExpression.isPath()) {
        return localModel.isAttribute(paramString);
      }
      return localModel.isAttribute(str);
    }
    return false;
  }
  
  private boolean isElement(String paramString)
    throws Exception
  {
    paramString = this.builder.build(paramString);
    Model localModel = lookup(paramString);
    if (localModel != null)
    {
      String str = paramString.getLast();
      int i = paramString.getIndex();
      if (localModel.isElement(str)) {}
      do
      {
        return true;
        if (!localModel.isModel(str)) {
          break;
        }
      } while (!localModel.lookup(str, i).isEmpty());
      return false;
    }
    return false;
  }
  
  private boolean isEmpty()
  {
    if (this.text != null) {
      return false;
    }
    return this.root.isEmpty();
  }
  
  private Model lookup(Expression paramExpression)
    throws Exception
  {
    Expression localExpression = paramExpression.getPath(0, 1);
    if (paramExpression.isPath()) {
      return this.root.lookup(localExpression);
    }
    return this.root;
  }
  
  private void process(Contact paramContact, Annotation paramAnnotation, LabelMap paramLabelMap)
    throws Exception
  {
    paramAnnotation = this.support.getLabel(paramContact, paramAnnotation);
    String str1 = paramAnnotation.getPath();
    String str2 = paramAnnotation.getName();
    if (paramLabelMap.get(str1) != null) {
      throw new PersistenceException("Duplicate annotation of name '%s' on %s", new Object[] { str2, paramContact });
    }
    process(paramContact, paramAnnotation, paramLabelMap);
  }
  
  private void process(Contact paramContact, Label paramLabel, LabelMap paramLabelMap)
    throws Exception
  {
    Expression localExpression = paramLabel.getExpression();
    String str = paramLabel.getPath();
    paramContact = this.root;
    if (!localExpression.isEmpty()) {
      paramContact = register(localExpression);
    }
    this.resolver.register(paramLabel);
    paramContact.register(paramLabel);
    paramLabelMap.put(str, paramLabel);
  }
  
  private Model register(Expression paramExpression)
    throws Exception
  {
    Model localModel = this.root.lookup(paramExpression);
    if (localModel != null) {
      return localModel;
    }
    return create(paramExpression);
  }
  
  private void text(Contact paramContact, Annotation paramAnnotation)
    throws Exception
  {
    Label localLabel = this.support.getLabel(paramContact, paramAnnotation);
    Expression localExpression = localLabel.getExpression();
    String str = localLabel.getPath();
    paramContact = this.root;
    if (!localExpression.isEmpty()) {
      paramContact = register(localExpression);
    }
    if (this.texts.get(str) != null) {
      throw new TextException("Multiple text annotations in %s", new Object[] { paramAnnotation });
    }
    this.resolver.register(localLabel);
    paramContact.register(localLabel);
    this.texts.put(str, localLabel);
  }
  
  private void union(Contact paramContact, Annotation paramAnnotation, LabelMap paramLabelMap)
    throws Exception
  {
    paramAnnotation = this.support.getLabels(paramContact, paramAnnotation).iterator();
    while (paramAnnotation.hasNext())
    {
      Label localLabel = (Label)paramAnnotation.next();
      String str1 = localLabel.getPath();
      String str2 = localLabel.getName();
      if (paramLabelMap.get(str1) != null) {
        throw new PersistenceException("Duplicate annotation of name '%s' on %s", new Object[] { str2, localLabel });
      }
      process(paramContact, localLabel, paramLabelMap);
    }
  }
  
  private void validateAttributes(Class paramClass, Order paramOrder)
    throws Exception
  {
    if (paramOrder != null)
    {
      paramOrder = paramOrder.attributes();
      int j = paramOrder.length;
      int i = 0;
      while (i < j)
      {
        String str = paramOrder[i];
        if (!isAttribute(str)) {
          throw new AttributeException("Ordered attribute '%s' missing in %s", new Object[] { str, paramClass });
        }
        i += 1;
      }
    }
  }
  
  private void validateElements(Class paramClass, Order paramOrder)
    throws Exception
  {
    if (paramOrder != null)
    {
      paramOrder = paramOrder.elements();
      int j = paramOrder.length;
      int i = 0;
      while (i < j)
      {
        String str = paramOrder[i];
        if (!isElement(str)) {
          throw new ElementException("Ordered element '%s' missing for %s", new Object[] { str, paramClass });
        }
        i += 1;
      }
    }
  }
  
  private void validateModel(Class paramClass)
    throws Exception
  {
    if (!this.root.isEmpty()) {
      this.root.validate(paramClass);
    }
  }
  
  private void validateText(Class paramClass)
    throws Exception
  {
    Label localLabel = this.root.getText();
    if (localLabel != null)
    {
      if (!localLabel.isTextList())
      {
        if (!this.elements.isEmpty()) {
          throw new TextException("Elements used with %s in %s", new Object[] { localLabel, paramClass });
        }
        if (this.root.isComposite()) {
          throw new TextException("Paths used with %s in %s", new Object[] { localLabel, paramClass });
        }
      }
    }
    else if (this.scanner.isEmpty()) {
      this.primitive = isEmpty();
    }
  }
  
  private void validateTextList(Class paramClass)
    throws Exception
  {
    Label localLabel = this.root.getText();
    if ((localLabel != null) && (localLabel.isTextList()))
    {
      Object localObject1 = localLabel.getKey();
      Iterator localIterator = this.elements.iterator();
      while (localIterator.hasNext())
      {
        Object localObject2 = (Label)localIterator.next();
        if (!((Label)localObject2).getKey().equals(localObject1)) {
          throw new TextException("Elements used with %s in %s", new Object[] { localLabel, paramClass });
        }
        localObject2 = ((Label)localObject2).getDependent().getType();
        if (localObject2 == String.class) {
          throw new TextException("Illegal entry of %s with text annotations on %s in %s", new Object[] { localObject2, localLabel, paramClass });
        }
      }
      if (this.root.isComposite()) {
        throw new TextException("Paths used with %s in %s", new Object[] { localLabel, paramClass });
      }
    }
  }
  
  private void validateUnions(Class paramClass)
    throws Exception
  {
    paramClass = this.elements.iterator();
    while (paramClass.hasNext())
    {
      Label localLabel = (Label)paramClass.next();
      String[] arrayOfString = localLabel.getPaths();
      Contact localContact = localLabel.getContact();
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = arrayOfString[i];
        Annotation localAnnotation = localContact.getAnnotation();
        localObject = (Label)this.elements.get(localObject);
        if (localLabel.isInline() != ((Label)localObject).isInline()) {
          throw new UnionException("Inline must be consistent in %s for %s", new Object[] { localAnnotation, localContact });
        }
        if (localLabel.isRequired() != ((Label)localObject).isRequired()) {
          throw new UnionException("Required must be consistent in %s for %s", new Object[] { localAnnotation, localContact });
        }
        i += 1;
      }
    }
  }
  
  private void version(Contact paramContact, Annotation paramAnnotation)
    throws Exception
  {
    paramContact = this.support.getLabel(paramContact, paramAnnotation);
    if (this.version != null) {
      throw new AttributeException("Multiple version annotations in %s", new Object[] { paramAnnotation });
    }
    this.version = paramContact;
  }
  
  public void assemble(Class paramClass)
    throws Exception
  {
    paramClass = this.scanner.getOrder();
    if (paramClass != null) {
      this.assembler.assemble(this.root, paramClass);
    }
  }
  
  public Structure build(Class paramClass)
    throws Exception
  {
    return new Structure(this.factory, this.root, this.version, this.text, this.primitive);
  }
  
  public void commit(Class paramClass)
    throws Exception
  {
    if (this.factory == null) {
      this.factory = this.resolver.build();
    }
  }
  
  public void process(Contact paramContact, Annotation paramAnnotation)
    throws Exception
  {
    if ((paramAnnotation instanceof Attribute)) {
      process(paramContact, paramAnnotation, this.attributes);
    }
    if ((paramAnnotation instanceof ElementUnion)) {
      union(paramContact, paramAnnotation, this.elements);
    }
    if ((paramAnnotation instanceof ElementListUnion)) {
      union(paramContact, paramAnnotation, this.elements);
    }
    if ((paramAnnotation instanceof ElementMapUnion)) {
      union(paramContact, paramAnnotation, this.elements);
    }
    if ((paramAnnotation instanceof ElementList)) {
      process(paramContact, paramAnnotation, this.elements);
    }
    if ((paramAnnotation instanceof ElementArray)) {
      process(paramContact, paramAnnotation, this.elements);
    }
    if ((paramAnnotation instanceof ElementMap)) {
      process(paramContact, paramAnnotation, this.elements);
    }
    if ((paramAnnotation instanceof Element)) {
      process(paramContact, paramAnnotation, this.elements);
    }
    if ((paramAnnotation instanceof Version)) {
      version(paramContact, paramAnnotation);
    }
    if ((paramAnnotation instanceof Text)) {
      text(paramContact, paramAnnotation);
    }
  }
  
  public void validate(Class paramClass)
    throws Exception
  {
    Order localOrder = this.scanner.getOrder();
    validateUnions(paramClass);
    validateElements(paramClass, localOrder);
    validateAttributes(paramClass, localOrder);
    validateModel(paramClass);
    validateText(paramClass);
    validateTextList(paramClass);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\StructureBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */