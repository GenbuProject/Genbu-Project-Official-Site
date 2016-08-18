package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ModelSection
  implements Section
{
  private LabelMap attributes;
  private LabelMap elements;
  private Model model;
  private ModelMap models;
  
  public ModelSection(Model paramModel)
  {
    this.model = paramModel;
  }
  
  public String getAttribute(String paramString)
    throws Exception
  {
    Expression localExpression = this.model.getExpression();
    if (localExpression == null) {
      return paramString;
    }
    return localExpression.getAttribute(paramString);
  }
  
  public LabelMap getAttributes()
    throws Exception
  {
    if (this.attributes == null) {
      this.attributes = this.model.getAttributes();
    }
    return this.attributes;
  }
  
  public Label getElement(String paramString)
    throws Exception
  {
    return getElements().getLabel(paramString);
  }
  
  public LabelMap getElements()
    throws Exception
  {
    if (this.elements == null) {
      this.elements = this.model.getElements();
    }
    return this.elements;
  }
  
  public ModelMap getModels()
    throws Exception
  {
    if (this.models == null) {
      this.models = this.model.getModels();
    }
    return this.models;
  }
  
  public String getName()
  {
    return this.model.getName();
  }
  
  public String getPath(String paramString)
    throws Exception
  {
    Expression localExpression = this.model.getExpression();
    if (localExpression == null) {
      return paramString;
    }
    return localExpression.getElement(paramString);
  }
  
  public String getPrefix()
  {
    return this.model.getPrefix();
  }
  
  public Section getSection(String paramString)
    throws Exception
  {
    paramString = (ModelList)getModels().get(paramString);
    if (paramString != null)
    {
      paramString = paramString.take();
      if (paramString != null) {
        return new ModelSection(paramString);
      }
    }
    return null;
  }
  
  public Label getText()
    throws Exception
  {
    return this.model.getText();
  }
  
  public boolean isSection(String paramString)
    throws Exception
  {
    return getModels().get(paramString) != null;
  }
  
  public Iterator<String> iterator()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.model.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add((String)localIterator.next());
    }
    return localArrayList.iterator();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ModelSection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */