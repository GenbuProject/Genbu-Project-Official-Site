package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class TreeModel
  implements Model
{
  private LabelMap attributes;
  private Detail detail;
  private LabelMap elements;
  private Expression expression;
  private int index;
  private Label list;
  private ModelMap models;
  private String name;
  private OrderList order;
  private Policy policy;
  private String prefix;
  private Label text;
  
  public TreeModel(Policy paramPolicy, Detail paramDetail)
  {
    this(paramPolicy, paramDetail, null, null, 1);
  }
  
  public TreeModel(Policy paramPolicy, Detail paramDetail, String paramString1, String paramString2, int paramInt)
  {
    this.attributes = new LabelMap(paramPolicy);
    this.elements = new LabelMap(paramPolicy);
    this.models = new ModelMap(paramDetail);
    this.order = new OrderList();
    this.detail = paramDetail;
    this.policy = paramPolicy;
    this.prefix = paramString2;
    this.index = paramInt;
    this.name = paramString1;
  }
  
  private Model create(String paramString1, String paramString2, int paramInt)
    throws Exception
  {
    paramString2 = new TreeModel(this.policy, this.detail, paramString1, paramString2, paramInt);
    if (paramString1 != null)
    {
      this.models.register(paramString1, paramString2);
      this.order.add(paramString1);
    }
    return paramString2;
  }
  
  private void validateAttributes(Class paramClass)
    throws Exception
  {
    Iterator localIterator = this.attributes.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((Label)this.attributes.get(str) == null) {
        throw new AttributeException("Ordered attribute '%s' does not exist in %s", new Object[] { str, paramClass });
      }
      if (this.expression != null) {
        this.expression.getAttribute(str);
      }
    }
  }
  
  private void validateElements(Class paramClass)
    throws Exception
  {
    Iterator localIterator = this.elements.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      ModelList localModelList = (ModelList)this.models.get(str);
      Label localLabel = (Label)this.elements.get(str);
      if ((localModelList == null) && (localLabel == null)) {
        throw new ElementException("Ordered element '%s' does not exist in %s", new Object[] { str, paramClass });
      }
      if ((localModelList != null) && (localLabel != null) && (!localModelList.isEmpty())) {
        throw new ElementException("Element '%s' is also a path name in %s", new Object[] { str, paramClass });
      }
      if (this.expression != null) {
        this.expression.getElement(str);
      }
    }
  }
  
  private void validateExpression(Label paramLabel)
    throws Exception
  {
    Object localObject = paramLabel.getExpression();
    if (this.expression != null)
    {
      paramLabel = this.expression.getPath();
      localObject = ((Expression)localObject).getPath();
      if (!paramLabel.equals(localObject)) {
        throw new PathException("Path '%s' does not match '%s' in %s", new Object[] { paramLabel, localObject, this.detail });
      }
    }
    else
    {
      this.expression = ((Expression)localObject);
    }
  }
  
  private void validateExpressions(Class paramClass)
    throws Exception
  {
    paramClass = this.elements.iterator();
    Label localLabel;
    while (paramClass.hasNext())
    {
      localLabel = (Label)paramClass.next();
      if (localLabel != null) {
        validateExpression(localLabel);
      }
    }
    paramClass = this.attributes.iterator();
    while (paramClass.hasNext())
    {
      localLabel = (Label)paramClass.next();
      if (localLabel != null) {
        validateExpression(localLabel);
      }
    }
    if (this.text != null) {
      validateExpression(this.text);
    }
  }
  
  private void validateModels(Class paramClass)
    throws Exception
  {
    Iterator localIterator = this.models.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ModelList)localIterator.next();
      int i = 1;
      localObject = ((ModelList)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Model localModel = (Model)((Iterator)localObject).next();
        if (localModel != null)
        {
          String str = localModel.getName();
          int j = localModel.getIndex();
          if (j != i) {
            throw new ElementException("Path section '%s[%s]' is out of sequence in %s", new Object[] { str, Integer.valueOf(j), paramClass });
          }
          localModel.validate(paramClass);
          i += 1;
        }
      }
    }
  }
  
  private void validateText(Class paramClass)
    throws Exception
  {
    if (this.text != null)
    {
      if (!this.elements.isEmpty()) {
        throw new TextException("Text annotation %s used with elements in %s", new Object[] { this.text, paramClass });
      }
      if (isComposite()) {
        throw new TextException("Text annotation %s can not be used with paths in %s", new Object[] { this.text, paramClass });
      }
    }
  }
  
  public LabelMap getAttributes()
    throws Exception
  {
    return this.attributes.getLabels();
  }
  
  public LabelMap getElements()
    throws Exception
  {
    return this.elements.getLabels();
  }
  
  public Expression getExpression()
  {
    return this.expression;
  }
  
  public int getIndex()
  {
    return this.index;
  }
  
  public ModelMap getModels()
    throws Exception
  {
    return this.models.getModels();
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPrefix()
  {
    return this.prefix;
  }
  
  public Label getText()
  {
    if (this.list != null) {
      return this.list;
    }
    return this.text;
  }
  
  public boolean isAttribute(String paramString)
  {
    return this.attributes.containsKey(paramString);
  }
  
  public boolean isComposite()
  {
    do
    {
      Iterator localIterator1 = this.models.iterator();
      while (!localIterator2.hasNext())
      {
        if (!localIterator1.hasNext()) {
          break;
        }
        localIterator2 = ((ModelList)localIterator1.next()).iterator();
      }
      localModel = (Model)localIterator2.next();
    } while ((localModel == null) || (localModel.isEmpty()));
    while (!this.models.isEmpty())
    {
      Iterator localIterator2;
      Model localModel;
      return true;
    }
    return false;
  }
  
  public boolean isElement(String paramString)
  {
    return this.elements.containsKey(paramString);
  }
  
  public boolean isEmpty()
  {
    if (this.text != null) {}
    while ((!this.elements.isEmpty()) || (!this.attributes.isEmpty()) || (isComposite())) {
      return false;
    }
    return true;
  }
  
  public boolean isModel(String paramString)
  {
    return this.models.containsKey(paramString);
  }
  
  public Iterator<String> iterator()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.order.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add((String)localIterator.next());
    }
    return localArrayList.iterator();
  }
  
  public Model lookup(String paramString, int paramInt)
  {
    return this.models.lookup(paramString, paramInt);
  }
  
  public Model lookup(Expression paramExpression)
  {
    Model localModel2 = lookup(paramExpression.getFirst(), paramExpression.getIndex());
    Model localModel1 = localModel2;
    if (paramExpression.isPath())
    {
      paramExpression = paramExpression.getPath(1, 0);
      localModel1 = localModel2;
      if (localModel2 != null) {
        localModel1 = localModel2.lookup(paramExpression);
      }
    }
    return localModel1;
  }
  
  public Model register(String paramString1, String paramString2, int paramInt)
    throws Exception
  {
    Model localModel2 = this.models.lookup(paramString1, paramInt);
    Model localModel1 = localModel2;
    if (localModel2 == null) {
      localModel1 = create(paramString1, paramString2, paramInt);
    }
    return localModel1;
  }
  
  public void register(Label paramLabel)
    throws Exception
  {
    if (paramLabel.isAttribute())
    {
      registerAttribute(paramLabel);
      return;
    }
    if (paramLabel.isText())
    {
      registerText(paramLabel);
      return;
    }
    registerElement(paramLabel);
  }
  
  public void registerAttribute(String paramString)
    throws Exception
  {
    this.attributes.put(paramString, null);
  }
  
  public void registerAttribute(Label paramLabel)
    throws Exception
  {
    String str = paramLabel.getName();
    if (this.attributes.get(str) != null) {
      throw new AttributeException("Duplicate annotation of name '%s' on %s", new Object[] { str, paramLabel });
    }
    this.attributes.put(str, paramLabel);
  }
  
  public void registerElement(String paramString)
    throws Exception
  {
    if (!this.order.contains(paramString)) {
      this.order.add(paramString);
    }
    this.elements.put(paramString, null);
  }
  
  public void registerElement(Label paramLabel)
    throws Exception
  {
    String str = paramLabel.getName();
    if (this.elements.get(str) != null) {
      throw new ElementException("Duplicate annotation of name '%s' on %s", new Object[] { str, paramLabel });
    }
    if (!this.order.contains(str)) {
      this.order.add(str);
    }
    if (paramLabel.isTextList()) {
      this.list = paramLabel;
    }
    this.elements.put(str, paramLabel);
  }
  
  public void registerText(Label paramLabel)
    throws Exception
  {
    if (this.text != null) {
      throw new TextException("Duplicate text annotation on %s", new Object[] { paramLabel });
    }
    this.text = paramLabel;
  }
  
  public String toString()
  {
    return String.format("model '%s[%s]'", new Object[] { this.name, Integer.valueOf(this.index) });
  }
  
  public void validate(Class paramClass)
    throws Exception
  {
    validateExpressions(paramClass);
    validateAttributes(paramClass);
    validateElements(paramClass);
    validateModels(paramClass);
    validateText(paramClass);
  }
  
  private static class OrderList
    extends ArrayList<String>
  {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\TreeModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */