package org.simpleframework.xml.stream;

abstract class Splitter
{
  protected StringBuilder builder = new StringBuilder();
  protected int count;
  protected int off;
  protected char[] text;
  
  public Splitter(String paramString)
  {
    this.text = paramString.toCharArray();
    this.count = this.text.length;
  }
  
  private boolean acronym()
  {
    int i = this.off;
    int j = 0;
    while ((i < this.count) && (isUpper(this.text[i])))
    {
      j += 1;
      i += 1;
    }
    if (j > 1)
    {
      int k = i;
      if (i < this.count)
      {
        k = i;
        if (isUpper(this.text[(i - 1)])) {
          k = i - 1;
        }
      }
      commit(this.text, this.off, k - this.off);
      this.off = k;
    }
    return j > 1;
  }
  
  private boolean isDigit(char paramChar)
  {
    return Character.isDigit(paramChar);
  }
  
  private boolean isLetter(char paramChar)
  {
    return Character.isLetter(paramChar);
  }
  
  private boolean isSpecial(char paramChar)
  {
    return !Character.isLetterOrDigit(paramChar);
  }
  
  private boolean isUpper(char paramChar)
  {
    return Character.isUpperCase(paramChar);
  }
  
  private boolean number()
  {
    int i = this.off;
    int j = 0;
    while ((i < this.count) && (isDigit(this.text[i])))
    {
      j += 1;
      i += 1;
    }
    if (j > 0) {
      commit(this.text, this.off, i - this.off);
    }
    this.off = i;
    return j > 0;
  }
  
  private void token()
  {
    int i = this.off;
    for (;;)
    {
      char c;
      if (i < this.count)
      {
        c = this.text[i];
        if (isLetter(c)) {
          break label78;
        }
      }
      label78:
      while ((i > this.off) && (isUpper(c)))
      {
        if (i > this.off)
        {
          parse(this.text, this.off, i - this.off);
          commit(this.text, this.off, i - this.off);
        }
        this.off = i;
        return;
      }
      i += 1;
    }
  }
  
  protected abstract void commit(char[] paramArrayOfChar, int paramInt1, int paramInt2);
  
  protected abstract void parse(char[] paramArrayOfChar, int paramInt1, int paramInt2);
  
  public String process()
  {
    if (this.off < this.count) {
      for (;;)
      {
        if ((this.off >= this.count) || (!isSpecial(this.text[this.off])))
        {
          if (acronym()) {
            break;
          }
          token();
          number();
          break;
        }
        this.off += 1;
      }
    }
    return this.builder.toString();
  }
  
  protected char toLower(char paramChar)
  {
    return Character.toLowerCase(paramChar);
  }
  
  protected char toUpper(char paramChar)
  {
    return Character.toUpperCase(paramChar);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\Splitter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */