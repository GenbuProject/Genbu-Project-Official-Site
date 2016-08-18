package org.simpleframework.xml.stream;

import java.io.IOException;
import java.io.Writer;

class OutputBuffer
{
  private StringBuilder text = new StringBuilder();
  
  public void append(char paramChar)
  {
    this.text.append(paramChar);
  }
  
  public void append(String paramString)
  {
    this.text.append(paramString);
  }
  
  public void append(String paramString, int paramInt1, int paramInt2)
  {
    this.text.append(paramString, paramInt1, paramInt2);
  }
  
  public void append(char[] paramArrayOfChar)
  {
    this.text.append(paramArrayOfChar, 0, paramArrayOfChar.length);
  }
  
  public void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this.text.append(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void clear()
  {
    this.text.setLength(0);
  }
  
  public void write(Writer paramWriter)
    throws IOException
  {
    paramWriter.append(this.text);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\OutputBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */