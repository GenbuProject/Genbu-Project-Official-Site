package org.simpleframework.xml.core;

import java.util.Iterator;
import org.simpleframework.xml.Version;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NamespaceMap;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

class Composite
  implements Converter
{
  private final Context context;
  private final Criteria criteria;
  private final ObjectFactory factory;
  private final Primitive primitive;
  private final Revision revision;
  private final Type type;
  
  public Composite(Context paramContext, Type paramType)
  {
    this(paramContext, paramType, null);
  }
  
  public Composite(Context paramContext, Type paramType, Class paramClass)
  {
    this.factory = new ObjectFactory(paramContext, paramType, paramClass);
    this.primitive = new Primitive(paramContext, paramType);
    this.criteria = new Collector();
    this.revision = new Revision();
    this.context = paramContext;
    this.type = paramType;
  }
  
  private boolean isOverridden(OutputNode paramOutputNode, Object paramObject, Type paramType)
    throws Exception
  {
    return this.factory.setOverride(paramType, paramObject, paramOutputNode);
  }
  
  private Object read(InputNode paramInputNode, Instance paramInstance, Class paramClass)
    throws Exception
  {
    Object localObject = this.context.getSchema(paramClass);
    paramClass = ((Schema)localObject).getCaller();
    localObject = read((Schema)localObject, paramInstance).read(paramInputNode);
    paramClass.validate(localObject);
    paramClass.commit(localObject);
    paramInstance.setInstance(localObject);
    return readResolve(paramInputNode, localObject, paramClass);
  }
  
  private Builder read(Schema paramSchema, Instance paramInstance)
    throws Exception
  {
    if (paramSchema.getInstantiator().isDefault()) {
      return new Builder(this, this.criteria, paramSchema, paramInstance);
    }
    return new Injector(this, this.criteria, paramSchema, paramInstance, null);
  }
  
  private void read(InputNode paramInputNode, Object paramObject, Schema paramSchema)
    throws Exception
  {
    Section localSection = paramSchema.getSection();
    readVersion(paramInputNode, paramObject, paramSchema);
    readSection(paramInputNode, paramObject, localSection);
  }
  
  private void readAttribute(InputNode paramInputNode, Object paramObject, Section paramSection, LabelMap paramLabelMap)
    throws Exception
  {
    paramSection = paramSection.getAttribute(paramInputNode.getName());
    Label localLabel = paramLabelMap.getLabel(paramSection);
    if (localLabel == null)
    {
      paramInputNode = paramInputNode.getPosition();
      paramObject = this.context.getType(this.type, paramObject);
      if ((paramLabelMap.isStrict(this.context)) && (this.revision.isEqual())) {
        throw new AttributeException("Attribute '%s' does not have a match in %s at %s", new Object[] { paramSection, paramObject, paramInputNode });
      }
    }
    else
    {
      readInstance(paramInputNode, paramObject, localLabel);
    }
  }
  
  private void readAttributes(InputNode paramInputNode, Object paramObject, Section paramSection)
    throws Exception
  {
    Object localObject = paramInputNode.getAttributes();
    LabelMap localLabelMap = paramSection.getAttributes();
    localObject = ((NodeMap)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      InputNode localInputNode = paramInputNode.getAttribute((String)((Iterator)localObject).next());
      if (localInputNode != null) {
        readAttribute(localInputNode, paramObject, paramSection, localLabelMap);
      }
    }
    validate(paramInputNode, localLabelMap, paramObject);
  }
  
  private void readElement(InputNode paramInputNode, Object paramObject, Section paramSection, LabelMap paramLabelMap)
    throws Exception
  {
    String str = paramSection.getPath(paramInputNode.getName());
    Label localLabel = paramLabelMap.getLabel(str);
    paramSection = localLabel;
    if (localLabel == null) {
      paramSection = this.criteria.resolve(str);
    }
    if (paramSection == null)
    {
      paramSection = paramInputNode.getPosition();
      paramObject = this.context.getType(this.type, paramObject);
      if ((paramLabelMap.isStrict(this.context)) && (this.revision.isEqual())) {
        throw new ElementException("Element '%s' does not have a match in %s at %s", new Object[] { str, paramObject, paramSection });
      }
      paramInputNode.skip();
      return;
    }
    readUnion(paramInputNode, paramObject, paramLabelMap, paramSection);
  }
  
  private void readElements(InputNode paramInputNode, Object paramObject, Section paramSection)
    throws Exception
  {
    LabelMap localLabelMap = paramSection.getElements();
    InputNode localInputNode = paramInputNode.getNext();
    if (localInputNode != null)
    {
      Section localSection = paramSection.getSection(localInputNode.getName());
      if (localSection != null) {
        readSection(localInputNode, paramObject, localSection);
      }
      for (;;)
      {
        localInputNode = paramInputNode.getNext();
        break;
        readElement(localInputNode, paramObject, paramSection, localLabelMap);
      }
    }
    validate(paramInputNode, localLabelMap, paramObject);
  }
  
  private Object readInstance(InputNode paramInputNode, Object paramObject, Label paramLabel)
    throws Exception
  {
    Object localObject = readVariable(paramInputNode, paramObject, paramLabel);
    if (localObject == null)
    {
      paramInputNode = paramInputNode.getPosition();
      paramObject = this.context.getType(this.type, paramObject);
      if ((paramLabel.isRequired()) && (this.revision.isEqual())) {
        throw new ValueRequiredException("Empty value for %s in %s at %s", new Object[] { paramLabel, paramObject, paramInputNode });
      }
    }
    else if (localObject != paramLabel.getEmpty(this.context))
    {
      this.criteria.set(paramLabel, localObject);
    }
    return localObject;
  }
  
  private Object readPrimitive(InputNode paramInputNode, Instance paramInstance)
    throws Exception
  {
    Class localClass = paramInstance.getType();
    paramInputNode = this.primitive.read(paramInputNode, localClass);
    if (localClass != null) {
      paramInstance.setInstance(paramInputNode);
    }
    return paramInputNode;
  }
  
  private Object readResolve(InputNode paramInputNode, Object paramObject, Caller paramCaller)
    throws Exception
  {
    if (paramObject != null)
    {
      Position localPosition = paramInputNode.getPosition();
      paramInputNode = paramCaller.resolve(paramObject);
      paramObject = this.type.getType();
      paramCaller = paramInputNode.getClass();
      if (!((Class)paramObject).isAssignableFrom(paramCaller)) {
        throw new ElementException("Type %s does not match %s at %s", new Object[] { paramCaller, paramObject, localPosition });
      }
    }
    else
    {
      paramInputNode = (InputNode)paramObject;
    }
    return paramInputNode;
  }
  
  private void readSection(InputNode paramInputNode, Object paramObject, Section paramSection)
    throws Exception
  {
    readText(paramInputNode, paramObject, paramSection);
    readAttributes(paramInputNode, paramObject, paramSection);
    readElements(paramInputNode, paramObject, paramSection);
  }
  
  private void readText(InputNode paramInputNode, Object paramObject, Section paramSection)
    throws Exception
  {
    paramSection = paramSection.getText();
    if (paramSection != null) {
      readInstance(paramInputNode, paramObject, paramSection);
    }
  }
  
  private void readUnion(InputNode paramInputNode, Object paramObject, LabelMap paramLabelMap, Label paramLabel)
    throws Exception
  {
    paramInputNode = readInstance(paramInputNode, paramObject, paramLabel);
    paramObject = paramLabel.getPaths();
    int j = paramObject.length;
    int i = 0;
    while (i < j)
    {
      paramLabelMap.getLabel(paramObject[i]);
      i += 1;
    }
    if (paramLabel.isInline()) {
      this.criteria.set(paramLabel, paramInputNode);
    }
  }
  
  private Object readVariable(InputNode paramInputNode, Object paramObject, Label paramLabel)
    throws Exception
  {
    Converter localConverter = paramLabel.getConverter(this.context);
    if (paramLabel.isCollection())
    {
      Variable localVariable = this.criteria.get(paramLabel);
      paramLabel = paramLabel.getContact();
      if (localVariable != null) {
        return localConverter.read(paramInputNode, localVariable.getValue());
      }
      if (paramObject != null)
      {
        paramObject = paramLabel.get(paramObject);
        if (paramObject != null) {
          return localConverter.read(paramInputNode, paramObject);
        }
      }
    }
    return localConverter.read(paramInputNode);
  }
  
  private void readVersion(InputNode paramInputNode, Object paramObject, Label paramLabel)
    throws Exception
  {
    paramInputNode = readInstance(paramInputNode, paramObject, paramLabel);
    paramObject = this.type.getType();
    if (paramInputNode != null)
    {
      double d = this.context.getVersion((Class)paramObject).revision();
      if (!paramInputNode.equals(this.revision)) {
        this.revision.compare(Double.valueOf(d), paramInputNode);
      }
    }
  }
  
  private void readVersion(InputNode paramInputNode, Object paramObject, Schema paramSchema)
    throws Exception
  {
    paramSchema = paramSchema.getVersion();
    Class localClass = this.type.getType();
    if (paramSchema != null)
    {
      String str = paramSchema.getName();
      paramInputNode = (InputNode)paramInputNode.getAttributes().remove(str);
      if (paramInputNode != null) {
        readVersion(paramInputNode, paramObject, paramSchema);
      }
    }
    else
    {
      return;
    }
    paramInputNode = this.context.getVersion(localClass);
    paramObject = Double.valueOf(this.revision.getDefault());
    double d = paramInputNode.revision();
    this.criteria.set(paramSchema, paramObject);
    this.revision.compare(Double.valueOf(d), paramObject);
  }
  
  private void validate(InputNode paramInputNode, Label paramLabel)
    throws Exception
  {
    Converter localConverter = paramLabel.getConverter(this.context);
    Position localPosition = paramInputNode.getPosition();
    Class localClass = this.type.getType();
    if (!localConverter.validate(paramInputNode)) {
      throw new PersistenceException("Invalid value for %s in %s at %s", new Object[] { paramLabel, localClass, localPosition });
    }
    this.criteria.set(paramLabel, null);
  }
  
  private void validate(InputNode paramInputNode, LabelMap paramLabelMap)
    throws Exception
  {
    paramInputNode = paramInputNode.getPosition();
    paramLabelMap = paramLabelMap.iterator();
    while (paramLabelMap.hasNext())
    {
      Label localLabel = (Label)paramLabelMap.next();
      Class localClass = this.type.getType();
      if ((localLabel.isRequired()) && (this.revision.isEqual())) {
        throw new ValueRequiredException("Unable to satisfy %s for %s at %s", new Object[] { localLabel, localClass, paramInputNode });
      }
    }
  }
  
  private void validate(InputNode paramInputNode, LabelMap paramLabelMap, Object paramObject)
    throws Exception
  {
    paramObject = this.context.getType(this.type, paramObject);
    paramInputNode = paramInputNode.getPosition();
    paramLabelMap = paramLabelMap.iterator();
    while (paramLabelMap.hasNext())
    {
      Label localLabel = (Label)paramLabelMap.next();
      if ((localLabel.isRequired()) && (this.revision.isEqual())) {
        throw new ValueRequiredException("Unable to satisfy %s for %s at %s", new Object[] { localLabel, paramObject, paramInputNode });
      }
      Object localObject = localLabel.getEmpty(this.context);
      if (localObject != null) {
        this.criteria.set(localLabel, localObject);
      }
    }
  }
  
  private boolean validate(InputNode paramInputNode, Class paramClass)
    throws Exception
  {
    paramClass = this.context.getSchema(paramClass);
    Section localSection = paramClass.getSection();
    validateText(paramInputNode, paramClass);
    validateSection(paramInputNode, localSection);
    return paramInputNode.isElement();
  }
  
  private void validateAttribute(InputNode paramInputNode, Section paramSection, LabelMap paramLabelMap)
    throws Exception
  {
    Position localPosition = paramInputNode.getPosition();
    paramSection = paramSection.getAttribute(paramInputNode.getName());
    Label localLabel = paramLabelMap.getLabel(paramSection);
    if (localLabel == null)
    {
      paramInputNode = this.type.getType();
      if ((paramLabelMap.isStrict(this.context)) && (this.revision.isEqual())) {
        throw new AttributeException("Attribute '%s' does not exist for %s at %s", new Object[] { paramSection, paramInputNode, localPosition });
      }
    }
    else
    {
      validate(paramInputNode, localLabel);
    }
  }
  
  private void validateAttributes(InputNode paramInputNode, Section paramSection)
    throws Exception
  {
    Object localObject = paramInputNode.getAttributes();
    LabelMap localLabelMap = paramSection.getAttributes();
    localObject = ((NodeMap)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      InputNode localInputNode = paramInputNode.getAttribute((String)((Iterator)localObject).next());
      if (localInputNode != null) {
        validateAttribute(localInputNode, paramSection, localLabelMap);
      }
    }
    validate(paramInputNode, localLabelMap);
  }
  
  private void validateElement(InputNode paramInputNode, Section paramSection, LabelMap paramLabelMap)
    throws Exception
  {
    String str = paramSection.getPath(paramInputNode.getName());
    Object localObject = paramLabelMap.getLabel(str);
    paramSection = (Section)localObject;
    if (localObject == null) {
      paramSection = this.criteria.resolve(str);
    }
    if (paramSection == null)
    {
      paramSection = paramInputNode.getPosition();
      localObject = this.type.getType();
      if ((paramLabelMap.isStrict(this.context)) && (this.revision.isEqual())) {
        throw new ElementException("Element '%s' does not exist for %s at %s", new Object[] { str, localObject, paramSection });
      }
      paramInputNode.skip();
      return;
    }
    validateUnion(paramInputNode, paramLabelMap, paramSection);
  }
  
  private void validateElements(InputNode paramInputNode, Section paramSection)
    throws Exception
  {
    LabelMap localLabelMap = paramSection.getElements();
    InputNode localInputNode = paramInputNode.getNext();
    if (localInputNode != null)
    {
      Section localSection = paramSection.getSection(localInputNode.getName());
      if (localSection != null) {
        validateSection(localInputNode, localSection);
      }
      for (;;)
      {
        localInputNode = paramInputNode.getNext();
        break;
        validateElement(localInputNode, paramSection, localLabelMap);
      }
    }
    validate(paramInputNode, localLabelMap);
  }
  
  private void validateSection(InputNode paramInputNode, Section paramSection)
    throws Exception
  {
    validateAttributes(paramInputNode, paramSection);
    validateElements(paramInputNode, paramSection);
  }
  
  private void validateText(InputNode paramInputNode, Schema paramSchema)
    throws Exception
  {
    paramSchema = paramSchema.getText();
    if (paramSchema != null) {
      validate(paramInputNode, paramSchema);
    }
  }
  
  private void validateUnion(InputNode paramInputNode, LabelMap paramLabelMap, Label paramLabel)
    throws Exception
  {
    String[] arrayOfString = paramLabel.getPaths();
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      paramLabelMap.getLabel(arrayOfString[i]);
      i += 1;
    }
    if (paramLabel.isInline()) {
      this.criteria.set(paramLabel, null);
    }
    validate(paramInputNode, paramLabel);
  }
  
  private void write(OutputNode paramOutputNode, Object paramObject, Schema paramSchema)
    throws Exception
  {
    Section localSection = paramSchema.getSection();
    writeVersion(paramOutputNode, paramObject, paramSchema);
    writeSection(paramOutputNode, paramObject, localSection);
  }
  
  private void writeAttribute(OutputNode paramOutputNode, Object paramObject, Label paramLabel)
    throws Exception
  {
    if (paramObject != null) {
      paramLabel.getDecorator().decorate(paramOutputNode.setAttribute(paramLabel.getName(), this.factory.getText(paramObject)));
    }
  }
  
  private void writeAttributes(OutputNode paramOutputNode, Object paramObject, Section paramSection)
    throws Exception
  {
    Iterator localIterator = paramSection.getAttributes().iterator();
    while (localIterator.hasNext())
    {
      Label localLabel = (Label)localIterator.next();
      Object localObject = localLabel.getContact().get(paramObject);
      Class localClass = this.context.getType(this.type, paramObject);
      paramSection = (Section)localObject;
      if (localObject == null) {
        paramSection = localLabel.getEmpty(this.context);
      }
      if ((paramSection == null) && (localLabel.isRequired())) {
        throw new AttributeException("Value for %s is null in %s", new Object[] { localLabel, localClass });
      }
      writeAttribute(paramOutputNode, paramSection, localLabel);
    }
  }
  
  private void writeElement(OutputNode paramOutputNode, Object paramObject, Converter paramConverter)
    throws Exception
  {
    paramConverter.write(paramOutputNode, paramObject);
  }
  
  private void writeElement(OutputNode paramOutputNode, Object paramObject, Label paramLabel)
    throws Exception
  {
    if (paramObject != null)
    {
      Class localClass = paramObject.getClass();
      Label localLabel = paramLabel.getLabel(localClass);
      String str = localLabel.getName();
      paramLabel = paramLabel.getType(localClass);
      paramOutputNode = paramOutputNode.getChild(str);
      if (!localLabel.isInline()) {
        writeNamespaces(paramOutputNode, paramLabel, localLabel);
      }
      if ((localLabel.isInline()) || (!isOverridden(paramOutputNode, paramObject, paramLabel)))
      {
        paramLabel = localLabel.getConverter(this.context);
        paramOutputNode.setData(localLabel.isData());
        writeElement(paramOutputNode, paramObject, paramLabel);
      }
    }
  }
  
  private void writeElements(OutputNode paramOutputNode, Object paramObject, Section paramSection)
    throws Exception
  {
    Iterator localIterator = paramSection.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = paramSection.getSection(str);
      if (localObject != null)
      {
        writeSection(paramOutputNode.getChild(str), paramObject, (Section)localObject);
      }
      else
      {
        localObject = paramSection.getElement(paramSection.getPath(str));
        Class localClass = this.context.getType(this.type, paramObject);
        if (this.criteria.get((Label)localObject) == null)
        {
          if (localObject == null) {
            throw new ElementException("Element '%s' not defined in %s", new Object[] { str, localClass });
          }
          writeUnion(paramOutputNode, paramObject, paramSection, (Label)localObject);
        }
      }
    }
  }
  
  private void writeNamespaces(OutputNode paramOutputNode, Type paramType, Label paramLabel)
    throws Exception
  {
    paramType = paramType.getType();
    paramType = this.context.getDecorator(paramType);
    paramLabel.getDecorator().decorate(paramOutputNode, paramType);
  }
  
  private Object writeReplace(Object paramObject)
    throws Exception
  {
    Object localObject = paramObject;
    if (paramObject != null)
    {
      localObject = paramObject.getClass();
      localObject = this.context.getCaller((Class)localObject).replace(paramObject);
    }
    return localObject;
  }
  
  private void writeSection(OutputNode paramOutputNode, Object paramObject, Section paramSection)
    throws Exception
  {
    Object localObject = paramOutputNode.getNamespaces();
    String str = paramSection.getPrefix();
    if (str != null)
    {
      localObject = ((NamespaceMap)localObject).getReference(str);
      if (localObject == null) {
        throw new ElementException("Namespace prefix '%s' in %s is not in scope", new Object[] { str, this.type });
      }
      paramOutputNode.setReference((String)localObject);
    }
    writeAttributes(paramOutputNode, paramObject, paramSection);
    writeElements(paramOutputNode, paramObject, paramSection);
    writeText(paramOutputNode, paramObject, paramSection);
  }
  
  private void writeText(OutputNode paramOutputNode, Object paramObject, Label paramLabel)
    throws Exception
  {
    if ((paramObject != null) && (!paramLabel.isTextList()))
    {
      paramObject = this.factory.getText(paramObject);
      paramOutputNode.setData(paramLabel.isData());
      paramOutputNode.setValue((String)paramObject);
    }
  }
  
  private void writeText(OutputNode paramOutputNode, Object paramObject, Section paramSection)
    throws Exception
  {
    Label localLabel = paramSection.getText();
    if (localLabel != null)
    {
      paramSection = localLabel.getContact().get(paramObject);
      Class localClass = this.context.getType(this.type, paramObject);
      paramObject = paramSection;
      if (paramSection == null) {
        paramObject = localLabel.getEmpty(this.context);
      }
      if ((paramObject == null) && (localLabel.isRequired())) {
        throw new TextException("Value for %s is null in %s", new Object[] { localLabel, localClass });
      }
      writeText(paramOutputNode, paramObject, localLabel);
    }
  }
  
  private void writeUnion(OutputNode paramOutputNode, Object paramObject, Section paramSection, Label paramLabel)
    throws Exception
  {
    paramSection = paramLabel.getContact().get(paramObject);
    paramObject = this.context.getType(this.type, paramObject);
    if ((paramSection == null) && (paramLabel.isRequired())) {
      throw new ElementException("Value for %s is null in %s", new Object[] { paramLabel, paramObject });
    }
    paramObject = writeReplace(paramSection);
    if (paramObject != null) {
      writeElement(paramOutputNode, paramObject, paramLabel);
    }
    this.criteria.set(paramLabel, paramObject);
  }
  
  private void writeVersion(OutputNode paramOutputNode, Object paramObject, Schema paramSchema)
    throws Exception
  {
    paramObject = paramSchema.getRevision();
    paramSchema = paramSchema.getVersion();
    if (paramObject != null)
    {
      double d = this.revision.getDefault();
      paramObject = Double.valueOf(((Version)paramObject).revision());
      if (!this.revision.compare(paramObject, Double.valueOf(d))) {
        break label70;
      }
      if (paramSchema.isRequired()) {
        writeAttribute(paramOutputNode, paramObject, paramSchema);
      }
    }
    return;
    label70:
    writeAttribute(paramOutputNode, paramObject, paramSchema);
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    Instance localInstance = this.factory.getInstance(paramInputNode);
    Class localClass = localInstance.getType();
    if (localInstance.isReference()) {
      return localInstance.getInstance();
    }
    if (this.context.isPrimitive(localClass)) {
      return readPrimitive(paramInputNode, localInstance);
    }
    return read(paramInputNode, localInstance, localClass);
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    Object localObject = paramObject.getClass();
    localObject = this.context.getSchema((Class)localObject);
    Caller localCaller = ((Schema)localObject).getCaller();
    read(paramInputNode, paramObject, (Schema)localObject);
    this.criteria.commit(paramObject);
    localCaller.validate(paramObject);
    localCaller.commit(paramObject);
    return readResolve(paramInputNode, paramObject, localCaller);
  }
  
  public boolean validate(InputNode paramInputNode)
    throws Exception
  {
    Instance localInstance = this.factory.getInstance(paramInputNode);
    if (!localInstance.isReference())
    {
      localInstance.setInstance(null);
      return validate(paramInputNode, localInstance.getType());
    }
    return true;
  }
  
  /* Error */
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual 300	java/lang/Object:getClass	()Ljava/lang/Class;
    //   4: astore_3
    //   5: aload_0
    //   6: getfield 58	org/simpleframework/xml/core/Composite:context	Lorg/simpleframework/xml/core/Context;
    //   9: aload_3
    //   10: invokeinterface 97 2 0
    //   15: astore 4
    //   17: aload 4
    //   19: invokeinterface 103 1 0
    //   24: astore_3
    //   25: aload 4
    //   27: invokeinterface 590 1 0
    //   32: ifeq +18 -> 50
    //   35: aload_0
    //   36: getfield 46	org/simpleframework/xml/core/Composite:primitive	Lorg/simpleframework/xml/core/Primitive;
    //   39: aload_1
    //   40: aload_2
    //   41: invokevirtual 591	org/simpleframework/xml/core/Primitive:write	(Lorg/simpleframework/xml/stream/OutputNode;Ljava/lang/Object;)V
    //   44: aload_3
    //   45: aload_2
    //   46: invokevirtual 594	org/simpleframework/xml/core/Caller:complete	(Ljava/lang/Object;)V
    //   49: return
    //   50: aload_3
    //   51: aload_2
    //   52: invokevirtual 597	org/simpleframework/xml/core/Caller:persist	(Ljava/lang/Object;)V
    //   55: aload_0
    //   56: aload_1
    //   57: aload_2
    //   58: aload 4
    //   60: invokespecial 599	org/simpleframework/xml/core/Composite:write	(Lorg/simpleframework/xml/stream/OutputNode;Ljava/lang/Object;Lorg/simpleframework/xml/core/Schema;)V
    //   63: goto -19 -> 44
    //   66: astore_1
    //   67: aload_3
    //   68: aload_2
    //   69: invokevirtual 594	org/simpleframework/xml/core/Caller:complete	(Ljava/lang/Object;)V
    //   72: aload_1
    //   73: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	74	0	this	Composite
    //   0	74	1	paramOutputNode	OutputNode
    //   0	74	2	paramObject	Object
    //   4	64	3	localObject	Object
    //   15	44	4	localSchema	Schema
    // Exception table:
    //   from	to	target	type
    //   25	44	66	finally
    //   50	63	66	finally
  }
  
  private static class Builder
  {
    protected final Composite composite;
    protected final Criteria criteria;
    protected final Schema schema;
    protected final Instance value;
    
    public Builder(Composite paramComposite, Criteria paramCriteria, Schema paramSchema, Instance paramInstance)
    {
      this.composite = paramComposite;
      this.criteria = paramCriteria;
      this.schema = paramSchema;
      this.value = paramInstance;
    }
    
    public Object read(InputNode paramInputNode)
      throws Exception
    {
      Object localObject = this.value.getInstance();
      Section localSection = this.schema.getSection();
      this.value.setInstance(localObject);
      this.composite.readVersion(paramInputNode, localObject, this.schema);
      this.composite.readText(paramInputNode, localObject, localSection);
      this.composite.readAttributes(paramInputNode, localObject, localSection);
      this.composite.readElements(paramInputNode, localObject, localSection);
      this.criteria.commit(localObject);
      return localObject;
    }
  }
  
  private class Injector
    extends Composite.Builder
  {
    private Injector(Composite paramComposite, Criteria paramCriteria, Schema paramSchema, Instance paramInstance)
    {
      super(paramCriteria, paramSchema, paramInstance);
    }
    
    private Object readInject(InputNode paramInputNode)
      throws Exception
    {
      paramInputNode = this.schema.getInstantiator().getInstance(this.criteria);
      this.value.setInstance(paramInputNode);
      this.criteria.commit(paramInputNode);
      return paramInputNode;
    }
    
    public Object read(InputNode paramInputNode)
      throws Exception
    {
      Section localSection = this.schema.getSection();
      this.composite.readVersion(paramInputNode, null, this.schema);
      this.composite.readText(paramInputNode, null, localSection);
      this.composite.readAttributes(paramInputNode, null, localSection);
      this.composite.readElements(paramInputNode, null, localSection);
      return readInject(paramInputNode);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Composite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */