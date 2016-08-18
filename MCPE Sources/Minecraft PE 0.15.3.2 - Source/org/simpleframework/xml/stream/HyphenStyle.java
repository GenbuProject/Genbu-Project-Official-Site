package org.simpleframework.xml.stream;

public class HyphenStyle
  implements Style
{
  private final Builder builder = new Builder(this.style);
  private final Style style = new HyphenBuilder();
  
  public String getAttribute(String paramString)
  {
    return this.builder.getAttribute(paramString);
  }
  
  public String getElement(String paramString)
  {
    return this.builder.getElement(paramString);
  }
  
  public void setAttribute(String paramString1, String paramString2)
  {
    this.builder.setAttribute(paramString1, paramString2);
  }
  
  public void setElement(String paramString1, String paramString2)
  {
    this.builder.setElement(paramString1, paramString2);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\HyphenStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */