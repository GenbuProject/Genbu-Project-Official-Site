package com.microsoft.xbox.service.model.serialization;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="version")
public class Version
{
  @Element
  public int latest;
  @Element
  public int min;
  @Element
  public String url;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\model\serialization\Version.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */