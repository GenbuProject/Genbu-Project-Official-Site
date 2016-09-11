package org.mozilla.javascript.regexp;

public class SubString
{
  public static final SubString emptySubString = new SubString();
  int index;
  int length;
  String str;
  
  public SubString() {}
  
  public SubString(String paramString)
  {
    this.str = paramString;
    this.index = 0;
    this.length = paramString.length();
  }
  
  public SubString(String paramString, int paramInt1, int paramInt2)
  {
    this.str = paramString;
    this.index = paramInt1;
    this.length = paramInt2;
  }
  
  public String toString()
  {
    if (this.str == null) {
      return "";
    }
    return this.str.substring(this.index, this.index + this.length);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\regexp\SubString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */