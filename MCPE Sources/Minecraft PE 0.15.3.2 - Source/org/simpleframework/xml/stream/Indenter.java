package org.simpleframework.xml.stream;

class Indenter
{
  private Cache cache;
  private int count;
  private int indent;
  private int index;
  
  public Indenter()
  {
    this(new Format());
  }
  
  public Indenter(Format paramFormat)
  {
    this(paramFormat, 16);
  }
  
  private Indenter(Format paramFormat, int paramInt)
  {
    this.indent = paramFormat.getIndent();
    this.cache = new Cache(paramInt);
  }
  
  private String create()
  {
    char[] arrayOfChar = new char[this.count + 1];
    if (this.count > 0)
    {
      arrayOfChar[0] = '\n';
      int i = 1;
      while (i <= this.count)
      {
        arrayOfChar[i] = ' ';
        i += 1;
      }
      return new String(arrayOfChar);
    }
    return "\n";
  }
  
  private String indent(int paramInt)
  {
    if (this.indent > 0)
    {
      String str2 = this.cache.get(paramInt);
      String str1 = str2;
      if (str2 == null)
      {
        str1 = create();
        this.cache.set(paramInt, str1);
      }
      if (this.cache.size() > 0) {
        return str1;
      }
    }
    return "";
  }
  
  public String pop()
  {
    int i = this.index - 1;
    this.index = i;
    String str = indent(i);
    if (this.indent > 0) {
      this.count -= this.indent;
    }
    return str;
  }
  
  public String push()
  {
    int i = this.index;
    this.index = (i + 1);
    String str = indent(i);
    if (this.indent > 0) {
      this.count += this.indent;
    }
    return str;
  }
  
  public String top()
  {
    return indent(this.index);
  }
  
  private static class Cache
  {
    private int count;
    private String[] list;
    
    public Cache(int paramInt)
    {
      this.list = new String[paramInt];
    }
    
    private void resize(int paramInt)
    {
      String[] arrayOfString = new String[paramInt];
      paramInt = 0;
      while (paramInt < this.list.length)
      {
        arrayOfString[paramInt] = this.list[paramInt];
        paramInt += 1;
      }
      this.list = arrayOfString;
    }
    
    public String get(int paramInt)
    {
      if (paramInt < this.list.length) {
        return this.list[paramInt];
      }
      return null;
    }
    
    public void set(int paramInt, String paramString)
    {
      if (paramInt >= this.list.length) {
        resize(paramInt * 2);
      }
      if (paramInt > this.count) {
        this.count = paramInt;
      }
      this.list[paramInt] = paramString;
    }
    
    public int size()
    {
      return this.count;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\Indenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */