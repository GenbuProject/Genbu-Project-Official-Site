package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.stream.Format;

class GroupExtractor
  implements Group
{
  private final LabelMap elements;
  private final ExtractorFactory factory;
  private final Annotation label;
  private final Registry registry;
  
  public GroupExtractor(Contact paramContact, Annotation paramAnnotation, Format paramFormat)
    throws Exception
  {
    this.factory = new ExtractorFactory(paramContact, paramAnnotation, paramFormat);
    this.elements = new LabelMap();
    this.registry = new Registry(this.elements);
    this.label = paramAnnotation;
    extract();
  }
  
  private void extract()
    throws Exception
  {
    Extractor localExtractor = this.factory.getInstance();
    if (localExtractor != null) {
      extract(localExtractor);
    }
  }
  
  private void extract(Extractor paramExtractor)
    throws Exception
  {
    Annotation[] arrayOfAnnotation = paramExtractor.getAnnotations();
    int j = arrayOfAnnotation.length;
    int i = 0;
    while (i < j)
    {
      extract(paramExtractor, arrayOfAnnotation[i]);
      i += 1;
    }
  }
  
  private void extract(Extractor paramExtractor, Annotation paramAnnotation)
    throws Exception
  {
    Label localLabel = paramExtractor.getLabel(paramAnnotation);
    paramExtractor = paramExtractor.getType(paramAnnotation);
    if (this.registry != null) {
      this.registry.register(paramExtractor, localLabel);
    }
  }
  
  public LabelMap getElements()
    throws Exception
  {
    return this.elements.getLabels();
  }
  
  public Label getLabel(Class paramClass)
  {
    return this.registry.resolve(paramClass);
  }
  
  public String[] getNames()
    throws Exception
  {
    return this.elements.getKeys();
  }
  
  public String[] getPaths()
    throws Exception
  {
    return this.elements.getPaths();
  }
  
  public Label getText()
  {
    return this.registry.resolveText();
  }
  
  public boolean isDeclared(Class paramClass)
  {
    return this.registry.containsKey(paramClass);
  }
  
  public boolean isInline()
  {
    Iterator localIterator = this.registry.iterator();
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
    } while (((Label)localIterator.next()).isInline());
    while (this.registry.isEmpty()) {
      return false;
    }
    return true;
  }
  
  public boolean isTextList()
  {
    return this.registry.isText();
  }
  
  public boolean isValid(Class paramClass)
  {
    return this.registry.resolve(paramClass) != null;
  }
  
  public String toString()
  {
    return this.label.toString();
  }
  
  private static class Registry
    extends LinkedHashMap<Class, Label>
    implements Iterable<Label>
  {
    private LabelMap elements;
    private Label text;
    
    public Registry(LabelMap paramLabelMap)
    {
      this.elements = paramLabelMap;
    }
    
    private void registerElement(Class paramClass, Label paramLabel)
      throws Exception
    {
      String str = paramLabel.getName();
      if (!this.elements.containsKey(str)) {
        this.elements.put(str, paramLabel);
      }
      if (!containsKey(paramClass)) {
        put(paramClass, paramLabel);
      }
    }
    
    private void registerText(Label paramLabel)
      throws Exception
    {
      Text localText = (Text)paramLabel.getContact().getAnnotation(Text.class);
      if (localText != null) {
        this.text = new TextListLabel(paramLabel, localText);
      }
    }
    
    private Label resolveElement(Class paramClass)
    {
      while (paramClass != null)
      {
        Label localLabel = (Label)get(paramClass);
        if (localLabel != null) {
          return localLabel;
        }
        paramClass = paramClass.getSuperclass();
      }
      return null;
    }
    
    private Label resolveText(Class paramClass)
    {
      if ((this.text != null) && (paramClass == String.class)) {
        return this.text;
      }
      return null;
    }
    
    public boolean isText()
    {
      return this.text != null;
    }
    
    public Iterator<Label> iterator()
    {
      return values().iterator();
    }
    
    public void register(Class paramClass, Label paramLabel)
      throws Exception
    {
      paramLabel = new CacheLabel(paramLabel);
      registerElement(paramClass, paramLabel);
      registerText(paramLabel);
    }
    
    public Label resolve(Class paramClass)
    {
      Label localLabel2 = resolveText(paramClass);
      Label localLabel1 = localLabel2;
      if (localLabel2 == null) {
        localLabel1 = resolveElement(paramClass);
      }
      return localLabel1;
    }
    
    public Label resolveText()
    {
      return resolveText(String.class);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\GroupExtractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */