package org.mozilla.javascript.json;

import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

public class JsonParser
{
  private Context cx;
  private int length;
  private int pos;
  private Scriptable scope;
  private String src;
  
  static
  {
    if (!JsonParser.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public JsonParser(Context paramContext, Scriptable paramScriptable)
  {
    this.cx = paramContext;
    this.scope = paramScriptable;
  }
  
  private void consume(char paramChar)
    throws JsonParser.ParseException
  {
    consumeWhitespace();
    if (this.pos >= this.length) {
      throw new ParseException("Expected " + paramChar + " but reached end of stream");
    }
    String str = this.src;
    int i = this.pos;
    this.pos = (i + 1);
    char c = str.charAt(i);
    if (c == paramChar) {
      return;
    }
    throw new ParseException("Expected " + paramChar + " found " + c);
  }
  
  private void consumeWhitespace()
  {
    for (;;)
    {
      if (this.pos < this.length) {}
      switch (this.src.charAt(this.pos))
      {
      default: 
        return;
      }
      this.pos += 1;
    }
  }
  
  private int fromHex(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9')) {
      return paramChar - '0';
    }
    if ((paramChar >= 'A') && (paramChar <= 'F')) {
      return paramChar - 'A' + 10;
    }
    if ((paramChar >= 'a') && (paramChar <= 'f')) {
      return paramChar - 'a' + 10;
    }
    return -1;
  }
  
  private char nextOrNumberError(int paramInt)
    throws JsonParser.ParseException
  {
    if (this.pos >= this.length) {
      throw numberError(paramInt, this.length);
    }
    String str = this.src;
    paramInt = this.pos;
    this.pos = (paramInt + 1);
    return str.charAt(paramInt);
  }
  
  private ParseException numberError(int paramInt1, int paramInt2)
  {
    return new ParseException("Unsupported number format: " + this.src.substring(paramInt1, paramInt2));
  }
  
  private Object readArray()
    throws JsonParser.ParseException
  {
    consumeWhitespace();
    if ((this.pos < this.length) && (this.src.charAt(this.pos) == ']'))
    {
      this.pos += 1;
      return this.cx.newArray(this.scope, 0);
    }
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (this.pos < this.length)
    {
      switch (this.src.charAt(this.pos))
      {
      default: 
        if (i != 0) {
          throw new ParseException("Missing comma in array literal");
        }
        break;
      case ']': 
        if (i == 0) {
          throw new ParseException("Unexpected comma in array literal");
        }
        this.pos += 1;
        return this.cx.newArray(this.scope, localArrayList.toArray());
      case ',': 
        if (i == 0) {
          throw new ParseException("Unexpected comma in array literal");
        }
        this.pos += 1;
      }
      for (i = 0;; i = 1)
      {
        consumeWhitespace();
        break;
        localArrayList.add(readValue());
      }
    }
    throw new ParseException("Unterminated array literal");
  }
  
  private void readDigits()
  {
    for (;;)
    {
      if (this.pos < this.length)
      {
        int i = this.src.charAt(this.pos);
        if ((i >= 48) && (i <= 57)) {}
      }
      else
      {
        return;
      }
      this.pos += 1;
    }
  }
  
  private Boolean readFalse()
    throws JsonParser.ParseException
  {
    if ((this.length - this.pos < 4) || (this.src.charAt(this.pos) != 'a') || (this.src.charAt(this.pos + 1) != 'l') || (this.src.charAt(this.pos + 2) != 's') || (this.src.charAt(this.pos + 3) != 'e')) {
      throw new ParseException("Unexpected token: f");
    }
    this.pos += 4;
    return Boolean.FALSE;
  }
  
  private Object readNull()
    throws JsonParser.ParseException
  {
    if ((this.length - this.pos < 3) || (this.src.charAt(this.pos) != 'u') || (this.src.charAt(this.pos + 1) != 'l') || (this.src.charAt(this.pos + 2) != 'l')) {
      throw new ParseException("Unexpected token: n");
    }
    this.pos += 3;
    return null;
  }
  
  private Number readNumber(char paramChar)
    throws JsonParser.ParseException
  {
    assert ((paramChar == '-') || ((paramChar >= '0') && (paramChar <= '9')));
    int i = this.pos - 1;
    char c = paramChar;
    if (paramChar == '-')
    {
      paramChar = nextOrNumberError(i);
      if (paramChar >= '0')
      {
        c = paramChar;
        if (paramChar <= '9') {}
      }
      else
      {
        throw numberError(i, this.pos);
      }
    }
    if (c != '0') {
      readDigits();
    }
    if ((this.pos < this.length) && (this.src.charAt(this.pos) == '.'))
    {
      this.pos += 1;
      paramChar = nextOrNumberError(i);
      if ((paramChar < '0') || (paramChar > '9')) {
        throw numberError(i, this.pos);
      }
      readDigits();
    }
    if (this.pos < this.length)
    {
      paramChar = this.src.charAt(this.pos);
      if ((paramChar == 'e') || (paramChar == 'E'))
      {
        this.pos += 1;
        c = nextOrNumberError(i);
        if (c != '-')
        {
          paramChar = c;
          if (c != '+') {}
        }
        else
        {
          paramChar = nextOrNumberError(i);
        }
        if ((paramChar < '0') || (paramChar > '9')) {
          throw numberError(i, this.pos);
        }
        readDigits();
      }
    }
    double d = Double.parseDouble(this.src.substring(i, this.pos));
    paramChar = (int)d;
    if (paramChar == d) {
      return Integer.valueOf(paramChar);
    }
    return Double.valueOf(d);
  }
  
  private Object readObject()
    throws JsonParser.ParseException
  {
    consumeWhitespace();
    Scriptable localScriptable = this.cx.newObject(this.scope);
    if ((this.pos < this.length) && (this.src.charAt(this.pos) == '}'))
    {
      this.pos += 1;
      return localScriptable;
    }
    int i = 0;
    while (this.pos < this.length)
    {
      String str = this.src;
      int j = this.pos;
      this.pos = (j + 1);
      switch (str.charAt(j))
      {
      default: 
        throw new ParseException("Unexpected token in object literal");
      case '}': 
        if (i == 0) {
          throw new ParseException("Unexpected comma in object literal");
        }
        return localScriptable;
      case ',': 
        if (i == 0) {
          throw new ParseException("Unexpected comma in object literal");
        }
        i = 0;
        consumeWhitespace();
        break;
      case '"': 
        if (i != 0) {
          throw new ParseException("Missing comma in object literal");
        }
        str = readString();
        consume(':');
        Object localObject = readValue();
        long l = ScriptRuntime.indexFromString(str);
        if (l < 0L) {
          localScriptable.put(str, localScriptable, localObject);
        }
        for (;;)
        {
          i = 1;
          break;
          localScriptable.put((int)l, localScriptable, localObject);
        }
      }
    }
    throw new ParseException("Unterminated object literal");
  }
  
  private String readString()
    throws JsonParser.ParseException
  {
    int i = this.pos;
    Object localObject;
    int j;
    do
    {
      if (this.pos < this.length)
      {
        localObject = this.src;
        j = this.pos;
        this.pos = (j + 1);
        j = ((String)localObject).charAt(j);
        if (j <= 31) {
          throw new ParseException("String contains control character");
        }
        if (j != 92) {}
      }
      else
      {
        localObject = new StringBuilder();
        if (this.pos >= this.length) {
          break label699;
        }
        if (($assertionsDisabled) || (this.src.charAt(this.pos - 1) == '\\')) {
          break;
        }
        throw new AssertionError();
      }
    } while (j != 34);
    return this.src.substring(i, this.pos - 1);
    ((StringBuilder)localObject).append(this.src, i, this.pos - 1);
    if (this.pos >= this.length) {
      throw new ParseException("Unterminated string");
    }
    String str = this.src;
    i = this.pos;
    this.pos = (i + 1);
    char c = str.charAt(i);
    switch (c)
    {
    default: 
      throw new ParseException("Unexpected character in string: '\\" + c + "'");
    case '"': 
      ((StringBuilder)localObject).append('"');
      label324:
      j = this.pos;
    }
    int k;
    do
    {
      i = j;
      if (this.pos >= this.length) {
        break;
      }
      str = this.src;
      i = this.pos;
      this.pos = (i + 1);
      k = str.charAt(i);
      if (k <= 31)
      {
        throw new ParseException("String contains control character");
        ((StringBuilder)localObject).append('\\');
        break label324;
        ((StringBuilder)localObject).append('/');
        break label324;
        ((StringBuilder)localObject).append('\b');
        break label324;
        ((StringBuilder)localObject).append('\f');
        break label324;
        ((StringBuilder)localObject).append('\n');
        break label324;
        ((StringBuilder)localObject).append('\r');
        break label324;
        ((StringBuilder)localObject).append('\t');
        break label324;
        if (this.length - this.pos < 5) {
          throw new ParseException("Invalid character code: \\u" + this.src.substring(this.pos));
        }
        i = fromHex(this.src.charAt(this.pos + 0)) << 12 | fromHex(this.src.charAt(this.pos + 1)) << 8 | fromHex(this.src.charAt(this.pos + 2)) << 4 | fromHex(this.src.charAt(this.pos + 3));
        if (i < 0) {
          throw new ParseException("Invalid character code: " + this.src.substring(this.pos, this.pos + 4));
        }
        this.pos += 4;
        ((StringBuilder)localObject).append((char)i);
        break label324;
      }
      i = j;
      if (k == 92) {
        break;
      }
    } while (k != 34);
    ((StringBuilder)localObject).append(this.src, j, this.pos - 1);
    return ((StringBuilder)localObject).toString();
    label699:
    throw new ParseException("Unterminated string literal");
  }
  
  private Boolean readTrue()
    throws JsonParser.ParseException
  {
    if ((this.length - this.pos < 3) || (this.src.charAt(this.pos) != 'r') || (this.src.charAt(this.pos + 1) != 'u') || (this.src.charAt(this.pos + 2) != 'e')) {
      throw new ParseException("Unexpected token: t");
    }
    this.pos += 3;
    return Boolean.TRUE;
  }
  
  private Object readValue()
    throws JsonParser.ParseException
  {
    consumeWhitespace();
    if (this.pos < this.length)
    {
      String str = this.src;
      int i = this.pos;
      this.pos = (i + 1);
      char c = str.charAt(i);
      switch (c)
      {
      default: 
        throw new ParseException("Unexpected token: " + c);
      case '{': 
        return readObject();
      case '[': 
        return readArray();
      case 't': 
        return readTrue();
      case 'f': 
        return readFalse();
      case '"': 
        return readString();
      case 'n': 
        return readNull();
      }
      return readNumber(c);
    }
    throw new ParseException("Empty JSON string");
  }
  
  public Object parseValue(String paramString)
    throws JsonParser.ParseException
  {
    if (paramString == null) {
      try
      {
        throw new ParseException("Input string may not be null");
      }
      finally {}
    }
    this.pos = 0;
    this.length = paramString.length();
    this.src = paramString;
    paramString = readValue();
    consumeWhitespace();
    if (this.pos < this.length) {
      throw new ParseException("Expected end of stream at char " + this.pos);
    }
    return paramString;
  }
  
  public static class ParseException
    extends Exception
  {
    static final long serialVersionUID = 4804542791749920772L;
    
    ParseException(Exception paramException)
    {
      super();
    }
    
    ParseException(String paramString)
    {
      super();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\json\JsonParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */