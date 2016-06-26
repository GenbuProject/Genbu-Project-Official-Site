package org.mozilla.javascript;

import java.io.IOException;
import java.io.Reader;

class TokenStream
{
  private static final char BYTE_ORDER_MARK = '?';
  private static final int EOF_CHAR = -1;
  private ObjToIntMap allStrings = new ObjToIntMap(50);
  private int commentCursor = -1;
  private String commentPrefix = "";
  Token.CommentType commentType;
  int cursor;
  private boolean dirtyLine;
  private boolean hitEOF = false;
  private boolean isBinary;
  private boolean isHex;
  private boolean isOctal;
  private boolean isOldOctal;
  private int lineEndChar = -1;
  private int lineStart = 0;
  int lineno;
  private double number;
  private Parser parser;
  private int quoteChar;
  String regExpFlags;
  private char[] sourceBuffer;
  int sourceCursor;
  private int sourceEnd;
  private Reader sourceReader;
  private String sourceString;
  private String string = "";
  private char[] stringBuffer = new char['?'];
  private int stringBufferTop;
  int tokenBeg;
  int tokenEnd;
  private final int[] ungetBuffer = new int[3];
  private int ungetCursor;
  private boolean xmlIsAttribute;
  private boolean xmlIsTagContent;
  private int xmlOpenTagsCount;
  
  static
  {
    if (!TokenStream.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  TokenStream(Parser paramParser, Reader paramReader, String paramString, int paramInt)
  {
    this.parser = paramParser;
    this.lineno = paramInt;
    if (paramReader != null)
    {
      if (paramString != null) {
        Kit.codeBug();
      }
      this.sourceReader = paramReader;
      this.sourceBuffer = new char['?'];
    }
    for (this.sourceEnd = 0;; this.sourceEnd = paramString.length())
    {
      this.cursor = 0;
      this.sourceCursor = 0;
      return;
      if (paramString == null) {
        Kit.codeBug();
      }
      this.sourceString = paramString;
    }
  }
  
  private void addToString(int paramInt)
  {
    int i = this.stringBufferTop;
    if (i == this.stringBuffer.length)
    {
      char[] arrayOfChar = new char[this.stringBuffer.length * 2];
      System.arraycopy(this.stringBuffer, 0, arrayOfChar, 0, i);
      this.stringBuffer = arrayOfChar;
    }
    this.stringBuffer[i] = ((char)paramInt);
    this.stringBufferTop = (i + 1);
  }
  
  private boolean canUngetChar()
  {
    return (this.ungetCursor == 0) || (this.ungetBuffer[(this.ungetCursor - 1)] != 10);
  }
  
  private final int charAt(int paramInt)
  {
    if (paramInt < 0) {}
    for (;;)
    {
      return -1;
      if (this.sourceString != null)
      {
        if (paramInt < this.sourceEnd) {
          return this.sourceString.charAt(paramInt);
        }
      }
      else
      {
        int i = paramInt;
        if (paramInt >= this.sourceEnd) {
          i = this.sourceCursor;
        }
        try
        {
          boolean bool = fillSourceBuffer();
          if (bool)
          {
            i = paramInt - (i - this.sourceCursor);
            return this.sourceBuffer[i];
          }
        }
        catch (IOException localIOException) {}
      }
    }
    return -1;
  }
  
  private String convertLastCharToHex(String paramString)
  {
    int i = 0;
    int j = paramString.length() - 1;
    StringBuffer localStringBuffer = new StringBuffer(paramString.substring(0, j));
    localStringBuffer.append("\\u");
    paramString = Integer.toHexString(paramString.charAt(j));
    while (i < 4 - paramString.length())
    {
      localStringBuffer.append('0');
      i += 1;
    }
    localStringBuffer.append(paramString);
    return localStringBuffer.toString();
  }
  
  private boolean fillSourceBuffer()
    throws IOException
  {
    if (this.sourceString != null) {
      Kit.codeBug();
    }
    if (this.sourceEnd == this.sourceBuffer.length)
    {
      if ((this.lineStart == 0) || (isMarkingComment())) {
        break label125;
      }
      System.arraycopy(this.sourceBuffer, this.lineStart, this.sourceBuffer, 0, this.sourceEnd - this.lineStart);
      this.sourceEnd -= this.lineStart;
      this.sourceCursor -= this.lineStart;
      this.lineStart = 0;
    }
    int i;
    for (;;)
    {
      i = this.sourceReader.read(this.sourceBuffer, this.sourceEnd, this.sourceBuffer.length - this.sourceEnd);
      if (i >= 0) {
        break;
      }
      return false;
      label125:
      char[] arrayOfChar = new char[this.sourceBuffer.length * 2];
      System.arraycopy(this.sourceBuffer, 0, arrayOfChar, 0, this.sourceEnd);
      this.sourceBuffer = arrayOfChar;
    }
    this.sourceEnd += i;
    return true;
  }
  
  private int getChar()
    throws IOException
  {
    return getChar(true);
  }
  
  private int getChar(boolean paramBoolean)
    throws IOException
  {
    Object localObject;
    int i;
    int j;
    if (this.ungetCursor != 0)
    {
      this.cursor += 1;
      localObject = this.ungetBuffer;
      i = this.ungetCursor - 1;
      this.ungetCursor = i;
      j = localObject[i];
    }
    label42:
    label77:
    do
    {
      return j;
      this.cursor += 1;
      localObject = this.sourceString;
      i = this.sourceCursor;
      this.sourceCursor = (i + 1);
      i = ((String)localObject).charAt(i);
      if (this.lineEndChar >= 0) {
        if ((this.lineEndChar == 13) && (i == 10)) {
          this.lineEndChar = 10;
        }
      }
      do
      {
        if (this.sourceString != null)
        {
          if (this.sourceCursor != this.sourceEnd) {
            break label42;
          }
          this.hitEOF = true;
          return -1;
        }
        if ((this.sourceCursor == this.sourceEnd) && (!fillSourceBuffer()))
        {
          this.hitEOF = true;
          return -1;
        }
        this.cursor += 1;
        localObject = this.sourceBuffer;
        i = this.sourceCursor;
        this.sourceCursor = (i + 1);
        i = localObject[i];
        break label77;
        this.lineEndChar = -1;
        this.lineStart = (this.sourceCursor - 1);
        this.lineno += 1;
        if (i <= 127)
        {
          if (i != 10)
          {
            j = i;
            if (i != 13) {
              break;
            }
          }
          this.lineEndChar = i;
          return 10;
        }
        j = i;
        if (i == 65279) {
          break;
        }
      } while ((paramBoolean) && (isJSFormatChar(i)));
      j = i;
    } while (!ScriptRuntime.isJSLineTerminator(i));
    this.lineEndChar = i;
    return 10;
  }
  
  private int getCharIgnoreLineEnd()
    throws IOException
  {
    Object localObject;
    int i;
    int j;
    if (this.ungetCursor != 0)
    {
      this.cursor += 1;
      localObject = this.ungetBuffer;
      i = this.ungetCursor - 1;
      this.ungetCursor = i;
      j = localObject[i];
    }
    label192:
    do
    {
      return j;
      for (;;)
      {
        if (isJSFormatChar(i))
        {
          if (this.sourceString != null)
          {
            if (this.sourceCursor == this.sourceEnd)
            {
              this.hitEOF = true;
              return -1;
            }
            this.cursor += 1;
            localObject = this.sourceString;
            i = this.sourceCursor;
            this.sourceCursor = (i + 1);
          }
          for (i = ((String)localObject).charAt(i);; i = localObject[i])
          {
            if (i > 127) {
              break label192;
            }
            if (i != 10)
            {
              j = i;
              if (i != 13) {
                break;
              }
            }
            this.lineEndChar = i;
            return 10;
            if ((this.sourceCursor == this.sourceEnd) && (!fillSourceBuffer()))
            {
              this.hitEOF = true;
              return -1;
            }
            this.cursor += 1;
            localObject = this.sourceBuffer;
            i = this.sourceCursor;
            this.sourceCursor = (i + 1);
          }
          if (i == 65279) {
            return i;
          }
        }
      }
      j = i;
    } while (!ScriptRuntime.isJSLineTerminator(i));
    this.lineEndChar = i;
    return 10;
  }
  
  private String getStringFromBuffer()
  {
    this.tokenEnd = this.cursor;
    return new String(this.stringBuffer, 0, this.stringBufferTop);
  }
  
  private static boolean isAlpha(int paramInt)
  {
    if (paramInt <= 90) {
      if (65 > paramInt) {}
    }
    while ((97 <= paramInt) && (paramInt <= 122))
    {
      return true;
      return false;
    }
    return false;
  }
  
  static boolean isDigit(int paramInt)
  {
    return (48 <= paramInt) && (paramInt <= 57);
  }
  
  private static boolean isJSFormatChar(int paramInt)
  {
    return (paramInt > 127) && (Character.getType((char)paramInt) == 16);
  }
  
  static boolean isJSSpace(int paramInt)
  {
    boolean bool = false;
    if (paramInt <= 127) {
      if ((paramInt == 32) || (paramInt == 9) || (paramInt == 12) || (paramInt == 11)) {
        bool = true;
      }
    }
    while ((paramInt != 160) && (paramInt != 65279) && (Character.getType((char)paramInt) != 12)) {
      return bool;
    }
    return true;
  }
  
  static boolean isKeyword(String paramString, int paramInt, boolean paramBoolean)
  {
    return stringToKeyword(paramString, paramInt, paramBoolean) != 0;
  }
  
  private boolean isMarkingComment()
  {
    return this.commentCursor != -1;
  }
  
  private void markCommentStart()
  {
    markCommentStart("");
  }
  
  private void markCommentStart(String paramString)
  {
    if ((this.parser.compilerEnv.isRecordingComments()) && (this.sourceReader != null))
    {
      this.commentPrefix = paramString;
      this.commentCursor = (this.sourceCursor - 1);
    }
  }
  
  private boolean matchChar(int paramInt)
    throws IOException
  {
    int i = getCharIgnoreLineEnd();
    if (i == paramInt)
    {
      this.tokenEnd = this.cursor;
      return true;
    }
    ungetCharIgnoreLineEnd(i);
    return false;
  }
  
  private int peekChar()
    throws IOException
  {
    int i = getChar();
    ungetChar(i);
    return i;
  }
  
  private boolean readCDATA()
    throws IOException
  {
    int i = getChar();
    while (i != -1)
    {
      addToString(i);
      if ((i == 93) && (peekChar() == 93))
      {
        i = getChar();
        addToString(i);
        if (peekChar() == 62)
        {
          addToString(getChar());
          return true;
        }
      }
      else
      {
        i = getChar();
      }
    }
    this.stringBufferTop = 0;
    this.string = null;
    this.parser.addError("msg.XML.bad.form");
    return false;
  }
  
  private boolean readEntity()
    throws IOException
  {
    int k = getChar();
    int j = 1;
    if (k != -1)
    {
      addToString(k);
      int i = j;
      switch (k)
      {
      default: 
        i = j;
      }
      do
      {
        for (;;)
        {
          k = getChar();
          j = i;
          break;
          i = j + 1;
        }
        j -= 1;
        i = j;
      } while (j != 0);
      return true;
    }
    this.stringBufferTop = 0;
    this.string = null;
    this.parser.addError("msg.XML.bad.form");
    return false;
  }
  
  private boolean readPI()
    throws IOException
  {
    for (int i = getChar(); i != -1; i = getChar())
    {
      addToString(i);
      if ((i == 63) && (peekChar() == 62))
      {
        addToString(getChar());
        return true;
      }
    }
    this.stringBufferTop = 0;
    this.string = null;
    this.parser.addError("msg.XML.bad.form");
    return false;
  }
  
  private boolean readQuotedString(int paramInt)
    throws IOException
  {
    for (int i = getChar(); i != -1; i = getChar())
    {
      addToString(i);
      if (i == paramInt) {
        return true;
      }
    }
    this.stringBufferTop = 0;
    this.string = null;
    this.parser.addError("msg.XML.bad.form");
    return false;
  }
  
  private boolean readXmlComment()
    throws IOException
  {
    int i = getChar();
    while (i != -1)
    {
      addToString(i);
      if ((i == 45) && (peekChar() == 45))
      {
        i = getChar();
        addToString(i);
        if (peekChar() == 62)
        {
          addToString(getChar());
          return true;
        }
      }
      else
      {
        i = getChar();
      }
    }
    this.stringBufferTop = 0;
    this.string = null;
    this.parser.addError("msg.XML.bad.form");
    return false;
  }
  
  private void skipLine()
    throws IOException
  {
    int i;
    do
    {
      i = getChar();
    } while ((i != -1) && (i != 10));
    ungetChar(i);
    this.tokenEnd = this.cursor;
  }
  
  private static int stringToKeyword(String paramString, int paramInt, boolean paramBoolean)
  {
    if (paramInt < 200) {
      return stringToKeywordForJS(paramString);
    }
    return stringToKeywordForES(paramString, paramBoolean);
  }
  
  private static int stringToKeywordForES(String paramString, boolean paramBoolean)
  {
    int j = 114;
    label56:
    String str;
    int i;
    switch (paramString.length())
    {
    default: 
      str = null;
      i = 0;
    }
    for (;;)
    {
      j = i;
      if (str != null)
      {
        j = i;
        if (str != paramString)
        {
          j = i;
          if (!str.equals(paramString)) {
            j = 0;
          }
        }
      }
      for (;;)
      {
        if (j != 0) {
          break label1377;
        }
        return 0;
        i = paramString.charAt(1);
        if (i == 102)
        {
          if (paramString.charAt(0) != 'i') {
            break;
          }
          j = 113;
          continue;
        }
        if (i == 110)
        {
          if (paramString.charAt(0) != 'i') {
            break;
          }
          j = 52;
          continue;
        }
        if ((i != 111) || (paramString.charAt(0) != 'd')) {
          break;
        }
        j = 119;
        continue;
        switch (paramString.charAt(0))
        {
        default: 
          str = null;
          i = 0;
          break;
        case 'f': 
          if ((paramString.charAt(2) != 'r') || (paramString.charAt(1) != 'o')) {
            break;
          }
          j = 120;
          break;
        case 'l': 
          if ((paramString.charAt(2) != 't') || (paramString.charAt(1) != 'e')) {
            break;
          }
          j = 154;
          break;
        case 'n': 
          if ((paramString.charAt(2) != 'w') || (paramString.charAt(1) != 'e')) {
            break;
          }
          j = 30;
          break;
        case 't': 
          if ((paramString.charAt(2) != 'y') || (paramString.charAt(1) != 'r')) {
            break;
          }
          j = 82;
          break;
        case 'v': 
          if ((paramString.charAt(2) != 'r') || (paramString.charAt(1) != 'a')) {
            break;
          }
          j = 123;
          continue;
          switch (paramString.charAt(0))
          {
          default: 
            str = null;
            i = 0;
            break;
          case 'c': 
            if ((paramString.charAt(3) != 'e') || (paramString.charAt(2) != 's') || (paramString.charAt(1) != 'a')) {
              break;
            }
            j = 116;
            break;
          case 'e': 
            i = paramString.charAt(3);
            if (i == 101)
            {
              if ((paramString.charAt(2) != 's') || (paramString.charAt(1) != 'l')) {
                break;
              }
              continue;
            }
            if ((i != 109) || (paramString.charAt(2) != 'u') || (paramString.charAt(1) != 'n')) {
              break;
            }
            j = 128;
            break;
          case 'n': 
            i = 42;
            str = "null";
            break;
          case 't': 
            i = paramString.charAt(3);
            if (i == 101)
            {
              if ((paramString.charAt(2) != 'u') || (paramString.charAt(1) != 'r')) {
                break;
              }
              j = 45;
              continue;
            }
            if ((i != 115) || (paramString.charAt(2) != 'i') || (paramString.charAt(1) != 'h')) {
              break;
            }
            j = 43;
          }
          break;
        }
      }
      i = 127;
      str = "void";
      continue;
      i = 124;
      str = "with";
      continue;
      switch (paramString.charAt(2))
      {
      default: 
        str = null;
        i = 0;
        break;
      case 'a': 
        i = paramString.charAt(0);
        if (i == 99)
        {
          i = 128;
          str = "class";
        }
        else
        {
          if (i != 97) {
            break;
          }
          i = 128;
          str = "await";
        }
        break;
      case 'e': 
        i = paramString.charAt(0);
        if (i == 98)
        {
          i = 121;
          str = "break";
        }
        else
        {
          if (i != 121) {
            break;
          }
          i = 73;
          str = "yield";
        }
        break;
      case 'i': 
        i = 118;
        str = "while";
        break;
      case 'l': 
        i = 44;
        str = "false";
        break;
      case 'n': 
        i = 155;
        str = "const";
        break;
      case 'p': 
        i = 128;
        str = "super";
        break;
      case 'r': 
        i = 50;
        str = "throw";
        break;
      case 't': 
        i = 125;
        str = "catch";
        continue;
        switch (paramString.charAt(1))
        {
        default: 
          str = null;
          i = 0;
          break;
        case 'e': 
          i = paramString.charAt(0);
          if (i == 100)
          {
            i = 31;
            str = "delete";
          }
          else
          {
            if (i != 114) {
              break;
            }
            i = 4;
            str = "return";
          }
          break;
        case 'm': 
          i = 128;
          str = "import";
          break;
        case 't': 
          if (paramBoolean)
          {
            i = 128;
            str = "static";
          }
          break;
        case 'u': 
          if (paramBoolean)
          {
            i = 128;
            str = "public";
          }
          break;
        case 'w': 
          i = 115;
          str = "switch";
          break;
        case 'x': 
          i = 128;
          str = "export";
          break;
        case 'y': 
          i = 32;
          str = "typeof";
          continue;
          switch (paramString.charAt(1))
          {
          default: 
            str = null;
            i = 0;
            break;
          case 'a': 
            if (paramBoolean)
            {
              i = 128;
              str = "package";
            }
            break;
          case 'e': 
            i = 117;
            str = "default";
            break;
          case 'i': 
            i = 126;
            str = "finally";
            break;
          case 'r': 
            if (paramBoolean)
            {
              i = 128;
              str = "private";
            }
            break;
          case 'x': 
            i = 128;
            str = "extends";
            continue;
            switch (paramString.charAt(0))
            {
            case 'e': 
            default: 
              str = null;
              i = 0;
              break;
            case 'c': 
              i = 122;
              str = "continue";
              break;
            case 'd': 
              i = 161;
              str = "debugger";
              break;
            case 'f': 
              i = 110;
              str = "function";
              continue;
              i = paramString.charAt(0);
              if ((i == 105) && (paramBoolean))
              {
                i = 128;
                str = "interface";
              }
              else
              {
                if ((i != 112) || (!paramBoolean)) {
                  break label56;
                }
                i = 128;
                str = "protected";
                continue;
                i = paramString.charAt(1);
                if ((i == 109) && (paramBoolean))
                {
                  i = 128;
                  str = "implements";
                }
                else
                {
                  if (i != 110) {
                    break label56;
                  }
                  i = 53;
                  str = "instanceof";
                }
              }
              break;
            }
            break;
          }
          break;
        }
        break;
      }
    }
    label1377:
    return j & 0xFF;
  }
  
  private static int stringToKeywordForJS(String paramString)
  {
    int i = 128;
    label68:
    String str;
    switch (paramString.length())
    {
    case 11: 
    default: 
      str = null;
      i = 0;
    }
    int j;
    for (;;)
    {
      j = i;
      if (str != null)
      {
        j = i;
        if (str != paramString)
        {
          j = i;
          if (!str.equals(paramString)) {
            j = 0;
          }
        }
      }
      for (;;)
      {
        if (j != 0) {
          break label1547;
        }
        return 0;
        i = paramString.charAt(1);
        if (i == 102)
        {
          if (paramString.charAt(0) != 'i') {
            break;
          }
          j = 113;
          continue;
        }
        if (i == 110)
        {
          if (paramString.charAt(0) != 'i') {
            break;
          }
          j = 52;
          continue;
        }
        if ((i != 111) || (paramString.charAt(0) != 'd')) {
          break;
        }
        j = 119;
        continue;
        switch (paramString.charAt(0))
        {
        default: 
          str = null;
          i = 0;
          break;
        case 'f': 
          if ((paramString.charAt(2) != 'r') || (paramString.charAt(1) != 'o')) {
            break;
          }
          j = 120;
          break;
        case 'i': 
          if ((paramString.charAt(2) != 't') || (paramString.charAt(1) != 'n')) {
            break;
          }
          j = i;
          break;
        case 'l': 
          if ((paramString.charAt(2) != 't') || (paramString.charAt(1) != 'e')) {
            break;
          }
          j = 154;
          break;
        case 'n': 
          if ((paramString.charAt(2) != 'w') || (paramString.charAt(1) != 'e')) {
            break;
          }
          j = 30;
          break;
        case 't': 
          if ((paramString.charAt(2) != 'y') || (paramString.charAt(1) != 'r')) {
            break;
          }
          j = 82;
          break;
        case 'v': 
          if ((paramString.charAt(2) != 'r') || (paramString.charAt(1) != 'a')) {
            break;
          }
          j = 123;
          continue;
          switch (paramString.charAt(0))
          {
          default: 
            str = null;
            i = 0;
            break;
          case 'b': 
            str = "byte";
            break;
          case 'c': 
            j = paramString.charAt(3);
            if (j == 101)
            {
              if ((paramString.charAt(2) != 's') || (paramString.charAt(1) != 'a')) {
                break;
              }
              j = 116;
              continue;
            }
            if ((j != 114) || (paramString.charAt(2) != 'a') || (paramString.charAt(1) != 'h')) {
              break;
            }
            j = i;
            break;
          case 'e': 
            j = paramString.charAt(3);
            if (j == 101)
            {
              if ((paramString.charAt(2) != 's') || (paramString.charAt(1) != 'l')) {
                break;
              }
              j = 114;
              continue;
            }
            if ((j != 109) || (paramString.charAt(2) != 'u') || (paramString.charAt(1) != 'n')) {
              break;
            }
            j = i;
            break;
          case 'g': 
            str = "goto";
            break;
          case 'l': 
            str = "long";
            break;
          case 'n': 
            i = 42;
            str = "null";
            break;
          case 't': 
            i = paramString.charAt(3);
            if (i == 101)
            {
              if ((paramString.charAt(2) != 'u') || (paramString.charAt(1) != 'r')) {
                break;
              }
              j = 45;
              continue;
            }
            if ((i != 115) || (paramString.charAt(2) != 'i') || (paramString.charAt(1) != 'h')) {
              break;
            }
            j = 43;
          }
          break;
        }
      }
      i = 127;
      str = "void";
      continue;
      i = 124;
      str = "with";
      continue;
      switch (paramString.charAt(2))
      {
      case 'b': 
      case 'c': 
      case 'd': 
      case 'f': 
      case 'g': 
      case 'h': 
      case 'j': 
      case 'k': 
      case 'm': 
      case 'q': 
      case 's': 
      default: 
        str = null;
        i = 0;
        break;
      case 'a': 
        str = "class";
        break;
      case 'e': 
        i = paramString.charAt(0);
        if (i == 98)
        {
          i = 121;
          str = "break";
        }
        else
        {
          if (i != 121) {
            break;
          }
          i = 73;
          str = "yield";
        }
        break;
      case 'i': 
        i = 118;
        str = "while";
        break;
      case 'l': 
        i = 44;
        str = "false";
        break;
      case 'n': 
        j = paramString.charAt(0);
        if (j == 99)
        {
          i = 155;
          str = "const";
        }
        else
        {
          if (j != 102) {
            break;
          }
          str = "final";
        }
        break;
      case 'o': 
        j = paramString.charAt(0);
        if (j == 102)
        {
          str = "float";
        }
        else
        {
          if (j != 115) {
            break;
          }
          str = "short";
        }
        break;
      case 'p': 
        str = "super";
        break;
      case 'r': 
        i = 50;
        str = "throw";
        break;
      case 't': 
        i = 125;
        str = "catch";
        continue;
        switch (paramString.charAt(1))
        {
        default: 
          str = null;
          i = 0;
          break;
        case 'a': 
          str = "native";
          break;
        case 'e': 
          i = paramString.charAt(0);
          if (i == 100)
          {
            i = 31;
            str = "delete";
          }
          else
          {
            if (i != 114) {
              break;
            }
            i = 4;
            str = "return";
          }
          break;
        case 'h': 
          str = "throws";
          break;
        case 'm': 
          str = "import";
          break;
        case 'o': 
          str = "double";
          break;
        case 't': 
          str = "static";
          break;
        case 'u': 
          str = "public";
          break;
        case 'w': 
          i = 115;
          str = "switch";
          break;
        case 'x': 
          str = "export";
          break;
        case 'y': 
          i = 32;
          str = "typeof";
          continue;
          switch (paramString.charAt(1))
          {
          default: 
            str = null;
            i = 0;
            break;
          case 'a': 
            str = "package";
            break;
          case 'e': 
            i = 117;
            str = "default";
            break;
          case 'i': 
            i = 126;
            str = "finally";
            break;
          case 'o': 
            str = "boolean";
            break;
          case 'r': 
            str = "private";
            break;
          case 'x': 
            str = "extends";
            continue;
            switch (paramString.charAt(0))
            {
            default: 
              str = null;
              i = 0;
              break;
            case 'a': 
              str = "abstract";
              break;
            case 'c': 
              i = 122;
              str = "continue";
              break;
            case 'd': 
              i = 161;
              str = "debugger";
              break;
            case 'f': 
              i = 110;
              str = "function";
              break;
            case 'v': 
              str = "volatile";
              continue;
              j = paramString.charAt(0);
              if (j == 105)
              {
                str = "interface";
              }
              else if (j == 112)
              {
                str = "protected";
              }
              else
              {
                if (j != 116) {
                  break label68;
                }
                str = "transient";
                continue;
                j = paramString.charAt(1);
                if (j == 109)
                {
                  str = "implements";
                }
                else
                {
                  if (j != 110) {
                    break label68;
                  }
                  i = 53;
                  str = "instanceof";
                  continue;
                  str = "synchronized";
                }
              }
              break;
            }
            break;
          }
          break;
        }
        break;
      }
    }
    label1547:
    return j & 0xFF;
  }
  
  private final String substring(int paramInt1, int paramInt2)
  {
    if (this.sourceString != null) {
      return this.sourceString.substring(paramInt1, paramInt2);
    }
    return new String(this.sourceBuffer, paramInt1, paramInt2 - paramInt1);
  }
  
  private void ungetChar(int paramInt)
  {
    if ((this.ungetCursor != 0) && (this.ungetBuffer[(this.ungetCursor - 1)] == 10)) {
      Kit.codeBug();
    }
    int[] arrayOfInt = this.ungetBuffer;
    int i = this.ungetCursor;
    this.ungetCursor = (i + 1);
    arrayOfInt[i] = paramInt;
    this.cursor -= 1;
  }
  
  private void ungetCharIgnoreLineEnd(int paramInt)
  {
    int[] arrayOfInt = this.ungetBuffer;
    int i = this.ungetCursor;
    this.ungetCursor = (i + 1);
    arrayOfInt[i] = paramInt;
    this.cursor -= 1;
  }
  
  final boolean eof()
  {
    return this.hitEOF;
  }
  
  final String getAndResetCurrentComment()
  {
    if (this.sourceString != null)
    {
      if (isMarkingComment()) {
        Kit.codeBug();
      }
      return this.sourceString.substring(this.tokenBeg, this.tokenEnd);
    }
    if (!isMarkingComment()) {
      Kit.codeBug();
    }
    StringBuilder localStringBuilder = new StringBuilder(this.commentPrefix);
    localStringBuilder.append(this.sourceBuffer, this.commentCursor, getTokenLength() - this.commentPrefix.length());
    this.commentCursor = -1;
    return localStringBuilder.toString();
  }
  
  public Token.CommentType getCommentType()
  {
    return this.commentType;
  }
  
  public int getCursor()
  {
    return this.cursor;
  }
  
  int getFirstXMLToken()
    throws IOException
  {
    this.xmlOpenTagsCount = 0;
    this.xmlIsAttribute = false;
    this.xmlIsTagContent = false;
    if (!canUngetChar()) {
      return -1;
    }
    ungetChar(60);
    return getNextXMLToken();
  }
  
  final String getLine()
  {
    int i = this.sourceCursor;
    int j;
    if (this.lineEndChar >= 0)
    {
      j = i - 1;
      i = j;
      if (this.lineEndChar == 10)
      {
        i = j;
        if (charAt(j - 1) == 13) {
          i = j - 1;
        }
      }
      return substring(this.lineStart, i);
    }
    i -= this.lineStart;
    for (;;)
    {
      j = charAt(this.lineStart + i);
      if ((j == -1) || (ScriptRuntime.isJSLineTerminator(j)))
      {
        i += this.lineStart;
        break;
      }
      i += 1;
    }
  }
  
  final String getLine(int paramInt, int[] paramArrayOfInt)
  {
    assert ((paramInt >= 0) && (paramInt <= this.cursor));
    assert (paramArrayOfInt.length == 2);
    int i = this.cursor + this.ungetCursor - paramInt;
    paramInt = this.sourceCursor;
    if (i > paramInt) {
      return null;
    }
    int j = 0;
    int k = 0;
    int n;
    int m;
    if (i > 0)
    {
      assert (paramInt > 0);
      n = charAt(paramInt - 1);
      if (!ScriptRuntime.isJSLineTerminator(n)) {
        break label301;
      }
      k = paramInt;
      m = i;
      if (n == 10)
      {
        k = paramInt;
        m = i;
        if (charAt(paramInt - 2) == 13)
        {
          m = i - 1;
          k = paramInt - 1;
        }
      }
      paramInt = k;
      i = j + 1;
      j = m;
      k -= 1;
      m = paramInt;
    }
    for (paramInt = k;; paramInt = k)
    {
      n = m - 1;
      k = i;
      i = j - 1;
      m = paramInt;
      paramInt = n;
      j = k;
      k = m;
      break;
      i = 0;
      if (paramInt > 0) {
        if (!ScriptRuntime.isJSLineTerminator(charAt(paramInt - 1))) {}
      }
      for (;;)
      {
        n = this.lineno;
        if (this.lineEndChar >= 0) {}
        for (m = 1;; m = 0)
        {
          paramArrayOfInt[0] = (m + (n - j));
          paramArrayOfInt[1] = i;
          if (j != 0) {
            break label288;
          }
          return getLine();
          i += 1;
          paramInt -= 1;
          break;
        }
        label288:
        return substring(paramInt, k);
        paramInt = 0;
      }
      label301:
      m = paramInt;
      paramInt = j;
      j = i;
      i = paramInt;
    }
  }
  
  final int getLineno()
  {
    return this.lineno;
  }
  
  int getNextXMLToken()
    throws IOException
  {
    this.tokenBeg = this.cursor;
    this.stringBufferTop = 0;
    int i = getChar();
    if (i != -1)
    {
      if (this.xmlIsTagContent)
      {
        switch (i)
        {
        default: 
          addToString(i);
          this.xmlIsAttribute = false;
        }
        while ((!this.xmlIsTagContent) && (this.xmlOpenTagsCount == 0))
        {
          this.string = getStringFromBuffer();
          return 149;
          addToString(i);
          this.xmlIsTagContent = false;
          this.xmlIsAttribute = false;
          continue;
          addToString(i);
          if (peekChar() == 62)
          {
            addToString(getChar());
            this.xmlIsTagContent = false;
            this.xmlOpenTagsCount -= 1;
            continue;
            ungetChar(i);
            this.string = getStringFromBuffer();
            return 146;
            addToString(i);
            if (!readQuotedString(i))
            {
              return -1;
              addToString(i);
              this.xmlIsAttribute = true;
              continue;
              addToString(i);
            }
          }
        }
      }
      switch (i)
      {
      default: 
        addToString(i);
      case 60: 
        for (;;)
        {
          i = getChar();
          break;
          addToString(i);
          switch (peekChar())
          {
          default: 
            this.xmlIsTagContent = true;
            this.xmlOpenTagsCount += 1;
            break;
          case 33: 
            addToString(getChar());
            switch (peekChar())
            {
            default: 
              if (!readEntity()) {
                return -1;
              }
              break;
            case 45: 
              addToString(getChar());
              i = getChar();
              if (i == 45)
              {
                addToString(i);
                if (!readXmlComment()) {
                  return -1;
                }
              }
              else
              {
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return -1;
              }
              break;
            case 91: 
              addToString(getChar());
              if ((getChar() == 67) && (getChar() == 68) && (getChar() == 65) && (getChar() == 84) && (getChar() == 65) && (getChar() == 91))
              {
                addToString(67);
                addToString(68);
                addToString(65);
                addToString(84);
                addToString(65);
                addToString(91);
                if (!readCDATA()) {
                  return -1;
                }
              }
              else
              {
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return -1;
              }
              break;
            }
            break;
          case 63: 
            addToString(getChar());
            if (!readPI()) {
              return -1;
            }
            break;
          case 47: 
            addToString(getChar());
            if (this.xmlOpenTagsCount == 0)
            {
              this.stringBufferTop = 0;
              this.string = null;
              this.parser.addError("msg.XML.bad.form");
              return -1;
            }
            this.xmlIsTagContent = true;
            this.xmlOpenTagsCount -= 1;
          }
        }
      }
      ungetChar(i);
      this.string = getStringFromBuffer();
      return 146;
    }
    this.tokenEnd = this.cursor;
    this.stringBufferTop = 0;
    this.string = null;
    this.parser.addError("msg.XML.bad.form");
    return -1;
  }
  
  final double getNumber()
  {
    return this.number;
  }
  
  final int getOffset()
  {
    int j = this.sourceCursor - this.lineStart;
    int i = j;
    if (this.lineEndChar >= 0) {
      i = j - 1;
    }
    return i;
  }
  
  final char getQuoteChar()
  {
    return (char)this.quoteChar;
  }
  
  final String getSourceString()
  {
    return this.sourceString;
  }
  
  final String getString()
  {
    return this.string;
  }
  
  final int getToken()
    throws IOException
  {
    int i1 = 1;
    int j;
    do
    {
      j = getChar();
      if (j == -1)
      {
        this.tokenBeg = (this.cursor - 1);
        this.tokenEnd = this.cursor;
        j = 0;
        return j;
      }
      if (j == 10)
      {
        this.dirtyLine = false;
        this.tokenBeg = (this.cursor - 1);
        this.tokenEnd = this.cursor;
        return 1;
      }
    } while (isJSSpace(j));
    if (j != 45) {
      this.dirtyLine = true;
    }
    this.tokenBeg = (this.cursor - 1);
    this.tokenEnd = this.cursor;
    if (j == 64) {
      return 148;
    }
    int i;
    boolean bool;
    if (j == 92)
    {
      j = getChar();
      if (j == 117)
      {
        this.stringBufferTop = 0;
        i = 1;
        bool = true;
        if (!bool) {
          break label567;
        }
        j = i;
      }
    }
    int k;
    int m;
    for (;;)
    {
      if (i != 0)
      {
        k = 0;
        i = 0;
        for (;;)
        {
          m = i;
          if (k != 4)
          {
            i = Kit.xDigitToInt(getChar(), i);
            if (i < 0) {
              m = i;
            }
          }
          else
          {
            if (m >= 0) {
              break label265;
            }
            this.parser.addError("msg.invalid.escape");
            return -1;
            ungetChar(j);
            j = 92;
            i = 0;
            bool = false;
            break;
            bool = Character.isJavaIdentifierStart((char)j);
            if (bool)
            {
              this.stringBufferTop = 0;
              addToString(j);
            }
            i = 0;
            break;
          }
          k += 1;
        }
        label265:
        addToString(m);
        i = 0;
      }
      else
      {
        k = getChar();
        if (k != 92) {
          break label318;
        }
        if (getChar() != 117) {
          break label306;
        }
        j = 1;
        i = 1;
      }
    }
    label306:
    this.parser.addError("msg.illegal.character");
    return -1;
    label318:
    String str1;
    Object localObject;
    if ((k == -1) || (k == 65279) || (!Character.isJavaIdentifierPart((char)k)))
    {
      ungetChar(k);
      str1 = getStringFromBuffer();
      if (j != 0) {
        break label531;
      }
      i = stringToKeyword(str1, this.parser.compilerEnv.getLanguageVersion(), this.parser.inUseStrictDirective());
      if (i != 0)
      {
        if (((i != 154) && (i != 73)) || (this.parser.compilerEnv.getLanguageVersion() >= 170)) {
          break label2769;
        }
        if (i != 154) {
          break label523;
        }
        localObject = "let";
        label425:
        this.string = ((String)localObject);
        i = 39;
      }
    }
    label523:
    label531:
    label567:
    label727:
    label825:
    label890:
    label1712:
    label2752:
    label2769:
    for (;;)
    {
      this.string = ((String)this.allStrings.intern(str1));
      j = i;
      if (i != 128) {
        break;
      }
      j = i;
      if (this.parser.compilerEnv.getLanguageVersion() >= 200) {
        break;
      }
      j = i;
      if (!this.parser.compilerEnv.isReservedKeywordAsIdentifier()) {
        break;
      }
      for (;;)
      {
        this.string = ((String)this.allStrings.intern(str1));
        return 39;
        addToString(k);
        break;
        localObject = "yield";
        break label425;
        if (isKeyword(str1, this.parser.compilerEnv.getLanguageVersion(), this.parser.inUseStrictDirective()))
        {
          str1 = convertLastCharToHex(str1);
          continue;
          if ((isDigit(j)) || ((j == 46) && (isDigit(peekChar()))))
          {
            this.stringBufferTop = 0;
            this.isBinary = false;
            this.isOctal = false;
            this.isOldOctal = false;
            this.isHex = false;
            if (this.parser.compilerEnv.getLanguageVersion() >= 200)
            {
              k = 1;
              i = j;
              if (j != 48) {
                break label825;
              }
              i = getChar();
              if ((i != 120) && (i != 88)) {
                break label727;
              }
              i = 16;
              this.isHex = true;
              j = getChar();
            }
            for (;;)
            {
              if (i != 16) {
                break label2752;
              }
              m = 1;
              k = j;
              for (j = m; Kit.xDigitToInt(k, 0) >= 0; j = 0)
              {
                addToString(k);
                k = getChar();
              }
              k = 0;
              break;
              if ((k != 0) && ((i == 111) || (i == 79)))
              {
                i = 8;
                this.isOctal = true;
                j = getChar();
              }
              else if ((k != 0) && ((i == 98) || (i == 66)))
              {
                i = 2;
                this.isBinary = true;
                j = getChar();
              }
              else if (isDigit(i))
              {
                k = 8;
                this.isOldOctal = true;
                j = i;
                i = k;
              }
              else
              {
                addToString(48);
                k = 10;
                j = i;
                i = k;
              }
            }
          }
          for (;;)
          {
            if ((48 <= k) && (k <= 57))
            {
              if ((i == 8) && (k >= 56)) {
                if (this.isOldOctal)
                {
                  localObject = this.parser;
                  if (k == 56)
                  {
                    str1 = "8";
                    ((Parser)localObject).addWarning("msg.bad.octal.literal", str1);
                    j = 10;
                  }
                }
              }
              do
              {
                do
                {
                  addToString(k);
                  k = getChar();
                  m = 0;
                  i = j;
                  j = m;
                  break;
                  str1 = "9";
                  break label890;
                  this.parser.addError("msg.caught.nfe");
                  return -1;
                  j = i;
                } while (i != 2);
                j = i;
              } while (k < 50);
              this.parser.addError("msg.caught.nfe");
              return -1;
            }
            m = i;
            int n = j;
            j = k;
            for (;;)
            {
              if ((n != 0) && ((this.isBinary) || (this.isOctal) || (this.isHex)))
              {
                this.parser.addError("msg.caught.nfe");
                return -1;
              }
              i = j;
              k = i1;
              if (m == 10) {
                if ((j != 46) && (j != 101))
                {
                  i = j;
                  k = i1;
                  if (j != 69) {}
                }
                else
                {
                  k = j;
                  if (j == 46) {
                    do
                    {
                      addToString(j);
                      k = getChar();
                      j = k;
                    } while (isDigit(k));
                  }
                  if (k != 101)
                  {
                    i = k;
                    if (k != 69) {}
                  }
                  else
                  {
                    addToString(k);
                    j = getChar();
                    if (j != 43)
                    {
                      i = j;
                      if (j != 45) {}
                    }
                    else
                    {
                      addToString(j);
                      i = getChar();
                    }
                    j = i;
                    if (!isDigit(i))
                    {
                      this.parser.addError("msg.missing.exponent");
                      return -1;
                    }
                    do
                    {
                      addToString(j);
                      i = getChar();
                      j = i;
                    } while (isDigit(i));
                  }
                  k = 0;
                }
              }
              ungetChar(i);
              str1 = getStringFromBuffer();
              this.string = str1;
              if ((m == 10) && (k == 0)) {}
              for (;;)
              {
                try
                {
                  d = Double.parseDouble(str1);
                  this.number = d;
                  return 40;
                }
                catch (NumberFormatException localNumberFormatException)
                {
                  this.parser.addError("msg.caught.nfe");
                  return -1;
                }
                double d = ScriptRuntime.stringToNumber(localNumberFormatException, 0, m);
              }
              if ((j == 34) || (j == 39))
              {
                this.quoteChar = j;
                this.stringBufferTop = 0;
                j = getChar(false);
                while (j != this.quoteChar)
                {
                  if ((j == 10) || (j == -1))
                  {
                    ungetChar(j);
                    this.tokenEnd = this.cursor;
                    this.parser.addError("msg.unterminated.string.lit");
                    return -1;
                  }
                  i = j;
                  if (j == 92) {
                    j = getChar();
                  }
                  switch (j)
                  {
                  default: 
                    i = j;
                    if (48 <= j)
                    {
                      i = j;
                      if (j < 56)
                      {
                        k = j - 48;
                        m = getChar();
                        i = k;
                        j = m;
                        if (48 <= m)
                        {
                          i = k;
                          j = m;
                          if (m < 56)
                          {
                            k = k * 8 + m - 48;
                            m = getChar();
                            i = k;
                            j = m;
                            if (48 <= m)
                            {
                              i = k;
                              j = m;
                              if (m < 56)
                              {
                                i = k;
                                j = m;
                                if (k <= 31)
                                {
                                  i = k * 8 + m - 48;
                                  j = getChar();
                                }
                              }
                            }
                          }
                        }
                        ungetChar(j);
                      }
                    }
                  case 98: 
                  case 102: 
                  case 110: 
                  case 114: 
                  case 116: 
                  case 118: 
                  case 117: 
                  case 120: 
                    for (;;)
                    {
                      addToString(i);
                      j = getChar(false);
                      break;
                      i = 8;
                      continue;
                      i = 12;
                      continue;
                      i = 10;
                      continue;
                      i = 13;
                      continue;
                      i = 9;
                      continue;
                      i = 11;
                      continue;
                      n = this.stringBufferTop;
                      addToString(117);
                      k = 0;
                      i = 0;
                      for (;;)
                      {
                        if (k == 4) {
                          break label1712;
                        }
                        m = getChar();
                        i = Kit.xDigitToInt(m, i);
                        j = m;
                        if (i < 0) {
                          break;
                        }
                        addToString(m);
                        k += 1;
                      }
                      this.stringBufferTop = n;
                      continue;
                      j = getChar();
                      k = Kit.xDigitToInt(j, 0);
                      if (k < 0)
                      {
                        addToString(120);
                        break;
                      }
                      i = getChar();
                      k = Kit.xDigitToInt(i, k);
                      if (k < 0)
                      {
                        addToString(120);
                        addToString(j);
                        j = i;
                        break;
                      }
                      i = k;
                    }
                  }
                  j = getChar();
                }
                String str2 = getStringFromBuffer();
                this.string = ((String)this.allStrings.intern(str2));
                return 41;
              }
              switch (j)
              {
              default: 
                this.parser.addError("msg.illegal.character");
                return -1;
              case 59: 
                return 83;
              case 91: 
                return 84;
              case 93: 
                return 85;
              case 123: 
                return 86;
              case 125: 
                return 87;
              case 40: 
                return 88;
              case 41: 
                return 89;
              case 44: 
                return 90;
              case 63: 
                return 103;
              case 58: 
                if (matchChar(58)) {
                  return 145;
                }
                return 104;
              case 46: 
                if (matchChar(46)) {
                  return 144;
                }
                if (matchChar(40)) {
                  return 147;
                }
                return 109;
              case 124: 
                if (matchChar(124)) {
                  return 105;
                }
                if (matchChar(61)) {
                  return 92;
                }
                return 9;
              case 94: 
                if (matchChar(61)) {
                  return 93;
                }
                return 10;
              case 38: 
                if (matchChar(38)) {
                  return 106;
                }
                if (matchChar(61)) {
                  return 94;
                }
                return 11;
              case 61: 
                if (matchChar(61))
                {
                  if (matchChar(61)) {
                    return 46;
                  }
                  return 12;
                }
                if (matchChar(62)) {
                  return 165;
                }
                return 91;
              case 33: 
                if (matchChar(61))
                {
                  if (matchChar(61)) {
                    return 47;
                  }
                  return 13;
                }
                return 26;
              case 60: 
                if (matchChar(33))
                {
                  if (matchChar(45))
                  {
                    if (matchChar(45))
                    {
                      this.tokenBeg = (this.cursor - 4);
                      skipLine();
                      this.commentType = Token.CommentType.HTML;
                      return 162;
                    }
                    ungetCharIgnoreLineEnd(45);
                  }
                  ungetCharIgnoreLineEnd(33);
                }
                if (matchChar(60))
                {
                  if (matchChar(61)) {
                    return 95;
                  }
                  return 18;
                }
                if (matchChar(61)) {
                  return 15;
                }
                return 14;
              case 62: 
                if (matchChar(62))
                {
                  if (matchChar(62))
                  {
                    if (matchChar(61)) {
                      return 97;
                    }
                    return 20;
                  }
                  if (matchChar(61)) {
                    return 96;
                  }
                  return 19;
                }
                if (matchChar(61)) {
                  return 17;
                }
                return 16;
              case 42: 
                if (matchChar(61)) {
                  return 100;
                }
                return 23;
              case 47: 
                markCommentStart();
                if (matchChar(47))
                {
                  this.tokenBeg = (this.cursor - 2);
                  skipLine();
                  this.commentType = Token.CommentType.LINE;
                  return 162;
                }
                if (matchChar(42))
                {
                  this.tokenBeg = (this.cursor - 2);
                  if (matchChar(42))
                  {
                    this.commentType = Token.CommentType.JSDOC;
                    i = 1;
                  }
                  for (;;)
                  {
                    j = getChar();
                    if (j == -1)
                    {
                      this.tokenEnd = (this.cursor - 1);
                      this.parser.addError("msg.unterminated.comment");
                      return 162;
                      this.commentType = Token.CommentType.BLOCK_COMMENT;
                      i = 0;
                    }
                    else if (j == 42)
                    {
                      i = 1;
                    }
                    else if (j == 47)
                    {
                      if (i != 0)
                      {
                        this.tokenEnd = this.cursor;
                        return 162;
                      }
                    }
                    else
                    {
                      this.tokenEnd = this.cursor;
                      i = 0;
                    }
                  }
                }
                if (matchChar(61)) {
                  return 101;
                }
                return 24;
              case 37: 
                if (matchChar(61)) {
                  return 102;
                }
                return 25;
              case 126: 
                return 27;
              case 43: 
                if (matchChar(61)) {
                  return 98;
                }
                if (matchChar(43)) {
                  return 107;
                }
                return 21;
              }
              if (matchChar(61)) {
                i = 99;
              }
              for (;;)
              {
                this.dirtyLine = true;
                return i;
                if (matchChar(45))
                {
                  if ((!this.dirtyLine) && (matchChar(62)))
                  {
                    markCommentStart("--");
                    skipLine();
                    this.commentType = Token.CommentType.HTML;
                    return 162;
                  }
                  i = 108;
                }
                else
                {
                  i = 22;
                }
              }
              n = j;
              j = k;
              m = i;
            }
            m = 1;
            k = j;
            j = m;
          }
        }
      }
    }
  }
  
  public int getTokenBeg()
  {
    return this.tokenBeg;
  }
  
  public int getTokenEnd()
  {
    return this.tokenEnd;
  }
  
  public int getTokenLength()
  {
    return this.tokenEnd - this.tokenBeg;
  }
  
  final boolean isNumberBinary()
  {
    return this.isBinary;
  }
  
  final boolean isNumberHex()
  {
    return this.isHex;
  }
  
  final boolean isNumberOctal()
  {
    return this.isOctal;
  }
  
  final boolean isNumberOldOctal()
  {
    return this.isOldOctal;
  }
  
  boolean isXMLAttribute()
  {
    return this.xmlIsAttribute;
  }
  
  String readAndClearRegExpFlags()
  {
    String str = this.regExpFlags;
    this.regExpFlags = null;
    return str;
  }
  
  void readRegExp(int paramInt)
    throws IOException
  {
    int k = this.tokenBeg;
    this.stringBufferTop = 0;
    if (paramInt == 101) {
      addToString(61);
    }
    int j;
    for (;;)
    {
      paramInt = 0;
      j = getChar();
      if ((j == 47) && (paramInt == 0)) {
        break label162;
      }
      if ((j != 10) && (j != -1)) {
        break;
      }
      ungetChar(j);
      this.tokenEnd = (this.cursor - 1);
      this.string = new String(this.stringBuffer, 0, this.stringBufferTop);
      this.parser.reportError("msg.unterminated.re.lit");
      return;
      if (paramInt != 24) {
        Kit.codeBug();
      }
    }
    int i;
    if (j == 92)
    {
      addToString(j);
      i = getChar();
    }
    for (;;)
    {
      addToString(i);
      break;
      if (j == 91)
      {
        paramInt = 1;
        i = j;
      }
      else
      {
        i = j;
        if (j == 93)
        {
          paramInt = 0;
          i = j;
        }
      }
    }
    label162:
    paramInt = this.stringBufferTop;
    for (;;)
    {
      if (matchChar(103))
      {
        addToString(103);
      }
      else if (matchChar(105))
      {
        addToString(105);
      }
      else if (matchChar(109))
      {
        addToString(109);
      }
      else
      {
        if (!matchChar(121)) {
          break;
        }
        addToString(121);
      }
    }
    this.tokenEnd = (this.stringBufferTop + k + 2);
    if (isAlpha(peekChar())) {
      this.parser.reportError("msg.invalid.re.flag");
    }
    this.string = new String(this.stringBuffer, 0, paramInt);
    this.regExpFlags = new String(this.stringBuffer, paramInt, this.stringBufferTop - paramInt);
  }
  
  String tokenToString(int paramInt)
  {
    return "";
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\TokenStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */