package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

class LabelMap
  extends LinkedHashMap<String, Label>
  implements Iterable<Label>
{
  private final Policy policy;
  
  public LabelMap()
  {
    this(null);
  }
  
  public LabelMap(Policy paramPolicy)
  {
    this.policy = paramPolicy;
  }
  
  private String[] getArray(Set<String> paramSet)
  {
    return (String[])paramSet.toArray(new String[0]);
  }
  
  public String[] getKeys()
    throws Exception
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Label)localIterator.next();
      if (localObject != null)
      {
        String str = ((Label)localObject).getPath();
        localObject = ((Label)localObject).getName();
        localHashSet.add(str);
        localHashSet.add(localObject);
      }
    }
    return getArray(localHashSet);
  }
  
  public Label getLabel(String paramString)
  {
    return (Label)remove(paramString);
  }
  
  public LabelMap getLabels()
    throws Exception
  {
    LabelMap localLabelMap = new LabelMap(this.policy);
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Label localLabel = (Label)localIterator.next();
      if (localLabel != null) {
        localLabelMap.put(localLabel.getPath(), localLabel);
      }
    }
    return localLabelMap;
  }
  
  public String[] getPaths()
    throws Exception
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Label localLabel = (Label)localIterator.next();
      if (localLabel != null) {
        localHashSet.add(localLabel.getPath());
      }
    }
    return getArray(localHashSet);
  }
  
  public boolean isStrict(Context paramContext)
  {
    if (this.policy == null) {
      return paramContext.isStrict();
    }
    return (paramContext.isStrict()) && (this.policy.isStrict());
  }
  
  public Iterator<Label> iterator()
  {
    return values().iterator();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\LabelMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */