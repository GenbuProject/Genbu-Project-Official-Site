package org.simpleframework.xml.core;

class Template
{
  protected char[] buf;
  protected String cache;
  protected int count;
  
  public Template()
  {
    this(16);
  }
  
  public Template(int paramInt)
  {
    this.buf = new char[paramInt];
  }
  
  public void append(char paramChar)
  {
    ensureCapacity(this.count + 1);
    char[] arrayOfChar = this.buf;
    int i = this.count;
    this.count = (i + 1);
    arrayOfChar[i] = paramChar;
  }
  
  public void append(String paramString)
  {
    ensureCapacity(this.count + paramString.length());
    paramString.getChars(0, paramString.length(), this.buf, this.count);
    this.count += paramString.length();
  }
  
  public void append(String paramString, int paramInt1, int paramInt2)
  {
    ensureCapacity(this.count + paramInt2);
    paramString.getChars(paramInt1, paramInt2, this.buf, this.count);
    this.count += paramInt2;
  }
  
  public void append(Template paramTemplate)
  {
    append(paramTemplate.buf, 0, paramTemplate.count);
  }
  
  public void append(Template paramTemplate, int paramInt1, int paramInt2)
  {
    append(paramTemplate.buf, paramInt1, paramInt2);
  }
  
  public void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    ensureCapacity(this.count + paramInt2);
    System.arraycopy(paramArrayOfChar, paramInt1, this.buf, this.count, paramInt2);
    this.count += paramInt2;
  }
  
  public void clear()
  {
    this.cache = null;
    this.count = 0;
  }
  
  protected void ensureCapacity(int paramInt)
  {
    if (this.buf.length < paramInt)
    {
      char[] arrayOfChar = new char[Math.max(paramInt, this.buf.length * 2)];
      System.arraycopy(this.buf, 0, arrayOfChar, 0, this.count);
      this.buf = arrayOfChar;
    }
  }
  
  public int length()
  {
    return this.count;
  }
  
  public String toString()
  {
    return new String(this.buf, 0, this.count);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Template.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */