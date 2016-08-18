package org.simpleframework.xml.stream;

class HyphenBuilder
  implements Style
{
  public String getAttribute(String paramString)
  {
    String str = null;
    if (paramString != null) {
      str = new Parser(paramString, null).process();
    }
    return str;
  }
  
  public String getElement(String paramString)
  {
    String str = null;
    if (paramString != null) {
      str = new Parser(paramString, null).process();
    }
    return str;
  }
  
  private class Parser
    extends Splitter
  {
    private Parser(String paramString)
    {
      super();
    }
    
    protected void commit(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      this.builder.append(paramArrayOfChar, paramInt1, paramInt2);
      if (paramInt1 + paramInt2 < this.count) {
        this.builder.append('-');
      }
    }
    
    protected void parse(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      paramArrayOfChar[paramInt1] = toLower(paramArrayOfChar[paramInt1]);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\HyphenBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */