package org.simpleframework.xml.core;

import java.util.Arrays;
import java.util.List;

class LabelGroup
{
  private final List<Label> list;
  private final int size;
  
  public LabelGroup(List<Label> paramList)
  {
    this.size = paramList.size();
    this.list = paramList;
  }
  
  public LabelGroup(Label paramLabel)
  {
    this(Arrays.asList(new Label[] { paramLabel }));
  }
  
  public List<Label> getList()
  {
    return this.list;
  }
  
  public Label getPrimary()
  {
    if (this.size > 0) {
      return (Label)this.list.get(0);
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\LabelGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */