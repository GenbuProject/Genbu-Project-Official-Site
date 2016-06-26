package org.mozilla.javascript.v8dtoa;

import java.util.Arrays;

public class FastDtoaBuilder
{
  static final char[] digits = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 };
  final char[] chars = new char[25];
  int end = 0;
  boolean formatted = false;
  int point;
  
  private void toExponentialFormat(int paramInt1, int paramInt2)
  {
    if (this.end - paramInt1 > 1)
    {
      paramInt1 += 1;
      System.arraycopy(this.chars, paramInt1, this.chars, paramInt1 + 1, this.end - paramInt1);
      this.chars[paramInt1] = '.';
      this.end += 1;
    }
    char[] arrayOfChar = this.chars;
    paramInt1 = this.end;
    this.end = (paramInt1 + 1);
    arrayOfChar[paramInt1] = 'e';
    int i = 43;
    paramInt1 = paramInt2 - 1;
    paramInt2 = paramInt1;
    if (paramInt1 < 0)
    {
      i = 45;
      paramInt2 = -paramInt1;
    }
    arrayOfChar = this.chars;
    paramInt1 = this.end;
    this.end = (paramInt1 + 1);
    arrayOfChar[paramInt1] = i;
    if (paramInt2 > 99)
    {
      paramInt1 = this.end + 2;
      this.end = (paramInt1 + 1);
    }
    for (;;)
    {
      this.chars[paramInt1] = digits[(paramInt2 % 10)];
      paramInt2 /= 10;
      if (paramInt2 == 0)
      {
        return;
        if (paramInt2 > 9)
        {
          paramInt1 = this.end + 1;
          break;
        }
        paramInt1 = this.end;
        break;
      }
      paramInt1 -= 1;
    }
  }
  
  private void toFixedFormat(int paramInt1, int paramInt2)
  {
    if (this.point < this.end) {
      if (paramInt2 > 0)
      {
        System.arraycopy(this.chars, this.point, this.chars, this.point + 1, this.end - this.point);
        this.chars[this.point] = '.';
        this.end += 1;
      }
    }
    while (this.point <= this.end)
    {
      return;
      int i = paramInt1 + 2 - paramInt2;
      System.arraycopy(this.chars, paramInt1, this.chars, i, this.end - paramInt1);
      this.chars[paramInt1] = '0';
      this.chars[(paramInt1 + 1)] = '.';
      if (paramInt2 < 0) {
        Arrays.fill(this.chars, paramInt1 + 2, i, '0');
      }
      this.end += 2 - paramInt2;
      return;
    }
    Arrays.fill(this.chars, this.end, this.point, '0');
    this.end += this.point - this.end;
  }
  
  void append(char paramChar)
  {
    char[] arrayOfChar = this.chars;
    int i = this.end;
    this.end = (i + 1);
    arrayOfChar[i] = paramChar;
  }
  
  void decreaseLast()
  {
    char[] arrayOfChar = this.chars;
    int i = this.end - 1;
    arrayOfChar[i] = ((char)(arrayOfChar[i] - '\001'));
  }
  
  public String format()
  {
    int i;
    int j;
    if (!this.formatted)
    {
      if (this.chars[0] != '-') {
        break label67;
      }
      i = 1;
      j = this.point - i;
      if ((j >= -5) && (j <= 21)) {
        break label72;
      }
      toExponentialFormat(i, j);
    }
    for (;;)
    {
      this.formatted = true;
      return new String(this.chars, 0, this.end);
      label67:
      i = 0;
      break;
      label72:
      toFixedFormat(i, j);
    }
  }
  
  public void reset()
  {
    this.end = 0;
    this.formatted = false;
  }
  
  public String toString()
  {
    return "[chars:" + new String(this.chars, 0, this.end) + ", point:" + this.point + "]";
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\v8dtoa\FastDtoaBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */