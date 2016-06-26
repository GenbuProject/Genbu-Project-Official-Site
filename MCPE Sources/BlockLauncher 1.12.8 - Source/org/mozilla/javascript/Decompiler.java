package org.mozilla.javascript;

public class Decompiler
{
  public static final int CASE_GAP_PROP = 3;
  private static final int FUNCTION_END = 167;
  public static final int INDENT_GAP_PROP = 2;
  public static final int INITIAL_INDENT_PROP = 1;
  public static final int ONLY_BODY_FLAG = 1;
  public static final int TO_SOURCE_FLAG = 2;
  private static final boolean printSource = false;
  private char[] sourceBuffer = new char['?'];
  private int sourceTop;
  
  private void append(char paramChar)
  {
    if (this.sourceTop == this.sourceBuffer.length) {
      increaseSourceCapacity(this.sourceTop + 1);
    }
    this.sourceBuffer[this.sourceTop] = paramChar;
    this.sourceTop += 1;
  }
  
  private void appendString(String paramString)
  {
    int j = paramString.length();
    int i = 1;
    if (j >= 32768) {
      i = 2;
    }
    i = i + this.sourceTop + j;
    if (i > this.sourceBuffer.length) {
      increaseSourceCapacity(i);
    }
    if (j >= 32768)
    {
      this.sourceBuffer[this.sourceTop] = ((char)(j >>> 16 | 0x8000));
      this.sourceTop += 1;
    }
    this.sourceBuffer[this.sourceTop] = ((char)j);
    this.sourceTop += 1;
    paramString.getChars(0, j, this.sourceBuffer, this.sourceTop);
    this.sourceTop = i;
  }
  
  public static String decompile(String paramString, int paramInt, UintMap paramUintMap)
  {
    int i8 = paramString.length();
    if (i8 == 0) {
      return "";
    }
    int i5 = paramUintMap.getInt(1, 0);
    if (i5 < 0) {
      throw new IllegalArgumentException();
    }
    int n = paramUintMap.getInt(2, 4);
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    int i9 = paramUintMap.getInt(3, 2);
    if (i9 < 0) {
      throw new IllegalArgumentException();
    }
    paramUintMap = new StringBuilder();
    int i1;
    int i2;
    label103:
    int i6;
    int i7;
    int i3;
    if ((paramInt & 0x1) != 0)
    {
      i1 = 1;
      if ((paramInt & 0x2) == 0) {
        break label184;
      }
      i2 = 1;
      i6 = 0;
      i7 = 0;
      if (paramString.charAt(0) != '?') {
        break label190;
      }
      i3 = -1;
    }
    int i4;
    for (int m = 1;; m = 0)
    {
      if (i2 != 0) {
        break label203;
      }
      paramUintMap.append('\n');
      i4 = 0;
      for (;;)
      {
        i = i5;
        paramInt = m;
        j = i7;
        k = i6;
        if (i4 >= i5) {
          break;
        }
        paramUintMap.append(' ');
        i4 += 1;
      }
      i1 = 0;
      break;
      label184:
      i2 = 0;
      break label103;
      label190:
      i3 = paramString.charAt(1);
    }
    label203:
    int i = i5;
    paramInt = m;
    int j = i7;
    int k = i6;
    if (i3 == 2)
    {
      paramUintMap.append('(');
      k = i6;
      j = i7;
      paramInt = m;
      i = i5;
    }
    if (paramInt < i8) {
      switch (paramString.charAt(paramInt))
      {
      case '\002': 
      case '\003': 
      case '\005': 
      case '\006': 
      case '\007': 
      case '\b': 
      case '!': 
      case '"': 
      case '#': 
      case '$': 
      case '%': 
      case '&': 
      case '1': 
      case '3': 
      case '6': 
      case '7': 
      case '8': 
      case '9': 
      case ':': 
      case ';': 
      case '<': 
      case '=': 
      case '>': 
      case '?': 
      case '@': 
      case 'A': 
      case 'B': 
      case 'D': 
      case 'E': 
      case 'F': 
      case 'G': 
      case 'H': 
      case 'J': 
      case 'K': 
      case 'L': 
      case 'M': 
      case 'N': 
      case 'O': 
      case 'P': 
      case 'Q': 
      case 'o': 
      case 'p': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case '?': 
      case 'Åë': 
      case 'Åí': 
      case '?': 
      default: 
        throw new RuntimeException("Token: " + Token.name(paramString.charAt(paramInt)));
      case '?': 
      case '?': 
      case '?': 
        if (paramString.charAt(paramInt) == '?')
        {
          paramUintMap.append("get ");
          m = printSourceString(paramString, paramInt + 1 + 1, false, paramUintMap);
          paramInt = j;
          m += 1;
          j = i;
          i = k;
          k = m;
        }
      case '\'': 
      case '0': 
      case ')': 
      case '(': 
      case '-': 
      case ',': 
      case '*': 
      case '+': 
      case 'n': 
      case 'Åò': 
      case 'Z': 
      case 'V': 
      case 'W': 
      case 'X': 
      case 'Y': 
      case 'T': 
      case 'U': 
      case '\001': 
        for (;;)
        {
          label988:
          m = i;
          i4 = paramInt;
          paramInt = k + 1;
          i = j;
          j = i4;
          k = m;
          break;
          if (paramString.charAt(paramInt) != '?') {
            break label988;
          }
          paramUintMap.append("set ");
          break label988;
          paramInt = printSourceString(paramString, paramInt + 1, false, paramUintMap);
          break;
          paramInt = printSourceString(paramString, paramInt + 1, true, paramUintMap);
          break;
          paramInt = printSourceNumber(paramString, paramInt + 1, paramUintMap);
          break;
          paramUintMap.append("true");
          m = k;
          i4 = i;
          k = paramInt;
          paramInt = j;
          i = m;
          j = i4;
          continue;
          paramUintMap.append("false");
          m = k;
          i4 = i;
          k = paramInt;
          paramInt = j;
          i = m;
          j = i4;
          continue;
          paramUintMap.append("null");
          m = k;
          i4 = i;
          k = paramInt;
          paramInt = j;
          i = m;
          j = i4;
          continue;
          paramUintMap.append("this");
          m = k;
          i4 = i;
          k = paramInt;
          paramInt = j;
          i = m;
          j = i4;
          continue;
          paramUintMap.append("function ");
          m = k;
          i4 = i;
          k = paramInt + 1;
          paramInt = j;
          i = m;
          j = i4;
          continue;
          m = k;
          i4 = i;
          k = paramInt;
          paramInt = j;
          i = m;
          j = i4;
          continue;
          paramUintMap.append(", ");
          m = k;
          i4 = i;
          k = paramInt;
          paramInt = j;
          i = m;
          j = i4;
          continue;
          m = i;
          if (1 == getNext(paramString, i8, paramInt)) {
            m = i + n;
          }
          paramUintMap.append('{');
          i = k + 1;
          k = paramInt;
          paramInt = j;
          j = m;
          continue;
          k -= 1;
          if ((i1 != 0) && (k == 0))
          {
            m = k;
            i4 = i;
            k = paramInt;
            paramInt = j;
            i = m;
            j = i4;
          }
          else
          {
            paramUintMap.append('}');
            switch (getNext(paramString, i8, paramInt))
            {
            }
            for (;;)
            {
              m = k;
              i4 = i;
              k = paramInt;
              paramInt = j;
              i = m;
              j = i4;
              break;
              i -= n;
              continue;
              i -= n;
              paramUintMap.append(' ');
            }
            paramUintMap.append('(');
            m = k;
            i4 = i;
            k = paramInt;
            paramInt = j;
            i = m;
            j = i4;
            continue;
            paramUintMap.append(')');
            if (86 != getNext(paramString, i8, paramInt)) {
              break label4267;
            }
            paramUintMap.append(' ');
            m = k;
            i4 = i;
            k = paramInt;
            paramInt = j;
            i = m;
            j = i4;
            continue;
            paramUintMap.append('[');
            m = k;
            i4 = i;
            k = paramInt;
            paramInt = j;
            i = m;
            j = i4;
            continue;
            paramUintMap.append(']');
            m = k;
            i4 = i;
            k = paramInt;
            paramInt = j;
            i = m;
            j = i4;
            continue;
            if (i2 == 0) {
              break label1661;
            }
            m = k;
            i4 = i;
            k = paramInt;
            paramInt = j;
            i = m;
            j = i4;
          }
        }
        label1661:
        i4 = 1;
        m = j;
        if (j == 0)
        {
          m = 1;
          if (i1 != 0)
          {
            paramUintMap.setLength(0);
            m = 1;
            j = i - n;
          }
        }
        break;
      }
    }
    for (i = 0;; i = i4)
    {
      if (i != 0) {
        paramUintMap.append('\n');
      }
      if (paramInt + 1 < i8)
      {
        i4 = 0;
        i5 = paramString.charAt(paramInt + 1);
        if ((i5 == 116) || (i5 == 117)) {
          i = n - i9;
        }
        while (i < j)
        {
          paramUintMap.append(' ');
          i += 1;
          continue;
          if (i5 == 87)
          {
            i = n;
          }
          else
          {
            i = i4;
            if (i5 == 39)
            {
              i = i4;
              if (paramString.charAt(getSourceStringEnd(paramString, paramInt + 2)) == 'h') {
                i = n;
              }
            }
          }
        }
        i = k;
        k = paramInt;
        paramInt = m;
        break;
        paramUintMap.append('.');
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("new ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("delete ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("if ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("else ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("for ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append(" in ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("with ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("while ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("do ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("try ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("catch ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("finally ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("throw ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("switch ");
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
        paramUintMap.append("break");
        if (39 == getNext(paramString, i8, paramInt))
        {
          paramUintMap.append(' ');
          m = k;
          i4 = i;
          k = paramInt;
          paramInt = j;
          i = m;
          j = i4;
          break;
          paramUintMap.append("continue");
          if (39 == getNext(paramString, i8, paramInt))
          {
            paramUintMap.append(' ');
            m = k;
            i4 = i;
            k = paramInt;
            paramInt = j;
            i = m;
            j = i4;
            break;
            paramUintMap.append("case ");
            m = k;
            i4 = i;
            k = paramInt;
            paramInt = j;
            i = m;
            j = i4;
            break;
            paramUintMap.append("default");
            m = k;
            i4 = i;
            k = paramInt;
            paramInt = j;
            i = m;
            j = i4;
            break;
            paramUintMap.append("return");
            if (83 != getNext(paramString, i8, paramInt))
            {
              paramUintMap.append(' ');
              m = k;
              i4 = i;
              k = paramInt;
              paramInt = j;
              i = m;
              j = i4;
              break;
              paramUintMap.append("var ");
              m = k;
              i4 = i;
              k = paramInt;
              paramInt = j;
              i = m;
              j = i4;
              break;
              paramUintMap.append("let ");
              m = k;
              i4 = i;
              k = paramInt;
              paramInt = j;
              i = m;
              j = i4;
              break;
              paramUintMap.append(';');
              if (1 != getNext(paramString, i8, paramInt))
              {
                paramUintMap.append(' ');
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" = ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" += ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" -= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" *= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" /= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" %= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" |= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" ^= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" &= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" <<= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" >>= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" >>>= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" ? ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(": ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                if (1 == getNext(paramString, i8, paramInt))
                {
                  paramUintMap.append(':');
                  m = k;
                  i4 = i;
                  k = paramInt;
                  paramInt = j;
                  i = m;
                  j = i4;
                  break;
                }
                paramUintMap.append(" : ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" || ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" && ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" | ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" ^ ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" & ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" === ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" !== ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" == ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" != ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" <= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" < ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" >= ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" > ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" instanceof ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" << ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" >> ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" >>> ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append("typeof ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append("void ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append("const ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append("yield ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append('!');
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append('~');
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append('+');
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append('-');
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append("++");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append("--");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" + ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" - ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" * ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" / ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" % ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append("::");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append("..");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(".(");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append('@');
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append("debugger;\n");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                paramUintMap.append(" => ");
                m = k;
                i4 = i;
                k = paramInt;
                paramInt = j;
                i = m;
                j = i4;
                break;
                if (i2 == 0) {
                  if (i1 == 0) {
                    paramUintMap.append('\n');
                  }
                }
                for (;;)
                {
                  return paramUintMap.toString();
                  if (i3 == 2) {
                    paramUintMap.append(')');
                  }
                }
              }
            }
          }
        }
        label4267:
        m = k;
        i4 = i;
        k = paramInt;
        paramInt = j;
        i = m;
        j = i4;
        break;
      }
      i = k;
      k = paramInt;
      paramInt = m;
      break;
      j = i;
    }
  }
  
  private static int getNext(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 + 1 < paramInt1) {
      return paramString.charAt(paramInt2 + 1);
    }
    return 0;
  }
  
  private static int getSourceStringEnd(String paramString, int paramInt)
  {
    return printSourceString(paramString, paramInt, false, null);
  }
  
  private void increaseSourceCapacity(int paramInt)
  {
    if (paramInt <= this.sourceBuffer.length) {
      Kit.codeBug();
    }
    int i = this.sourceBuffer.length * 2;
    if (i < paramInt) {}
    for (;;)
    {
      char[] arrayOfChar = new char[paramInt];
      System.arraycopy(this.sourceBuffer, 0, arrayOfChar, 0, this.sourceTop);
      this.sourceBuffer = arrayOfChar;
      return;
      paramInt = i;
    }
  }
  
  private static int printSourceNumber(String paramString, int paramInt, StringBuilder paramStringBuilder)
  {
    double d = 0.0D;
    int i = paramString.charAt(paramInt);
    paramInt += 1;
    if (i == 83)
    {
      if (paramStringBuilder != null) {
        d = paramString.charAt(paramInt);
      }
      paramInt += 1;
      if (paramStringBuilder != null) {
        paramStringBuilder.append(ScriptRuntime.numberToString(d, 10));
      }
      return paramInt;
    }
    if ((i == 74) || (i == 68))
    {
      long l;
      if (paramStringBuilder != null)
      {
        l = paramString.charAt(paramInt) << 48 | paramString.charAt(paramInt + 1) << 32 | paramString.charAt(paramInt + 2) << 16 | paramString.charAt(paramInt + 3);
        if (i != 74) {
          break label132;
        }
      }
      label132:
      for (d = l;; d = Double.longBitsToDouble(l))
      {
        paramInt += 4;
        break;
      }
    }
    throw new RuntimeException();
  }
  
  private static int printSourceString(String paramString, int paramInt, boolean paramBoolean, StringBuilder paramStringBuilder)
  {
    int j = paramString.charAt(paramInt);
    int k = paramInt + 1;
    int i = j;
    paramInt = k;
    if ((0x8000 & j) != 0)
    {
      i = (j & 0x7FFF) << 16 | paramString.charAt(k);
      paramInt = k + 1;
    }
    if (paramStringBuilder != null)
    {
      paramString = paramString.substring(paramInt, paramInt + i);
      if (paramBoolean) {
        break label79;
      }
      paramStringBuilder.append(paramString);
    }
    for (;;)
    {
      return i + paramInt;
      label79:
      paramStringBuilder.append('"');
      paramStringBuilder.append(ScriptRuntime.escapeString(paramString));
      paramStringBuilder.append('"');
    }
  }
  
  private String sourceToString(int paramInt)
  {
    if ((paramInt < 0) || (this.sourceTop < paramInt)) {
      Kit.codeBug();
    }
    return new String(this.sourceBuffer, paramInt, this.sourceTop - paramInt);
  }
  
  void addEOL(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 166)) {
      throw new IllegalArgumentException();
    }
    append((char)paramInt);
    append('\001');
  }
  
  void addName(String paramString)
  {
    addToken(39);
    appendString(paramString);
  }
  
  void addNumber(double paramDouble)
  {
    addToken(40);
    long l = paramDouble;
    if (l != paramDouble)
    {
      l = Double.doubleToLongBits(paramDouble);
      append('D');
      append((char)(int)(l >> 48));
      append((char)(int)(l >> 32));
      append((char)(int)(l >> 16));
      append((char)(int)l);
      return;
    }
    if (l < 0L) {
      Kit.codeBug();
    }
    if (l <= 65535L)
    {
      append('S');
      append((char)(int)l);
      return;
    }
    append('J');
    append((char)(int)(l >> 48));
    append((char)(int)(l >> 32));
    append((char)(int)(l >> 16));
    append((char)(int)l);
  }
  
  void addRegexp(String paramString1, String paramString2)
  {
    addToken(48);
    appendString('/' + paramString1 + '/' + paramString2);
  }
  
  void addString(String paramString)
  {
    addToken(41);
    appendString(paramString);
  }
  
  void addToken(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 166)) {
      throw new IllegalArgumentException();
    }
    append((char)paramInt);
  }
  
  int getCurrentOffset()
  {
    return this.sourceTop;
  }
  
  String getEncodedSource()
  {
    return sourceToString(0);
  }
  
  int markFunctionEnd(int paramInt)
  {
    paramInt = getCurrentOffset();
    append('Åò');
    return paramInt;
  }
  
  int markFunctionStart(int paramInt)
  {
    int i = getCurrentOffset();
    if (paramInt != 4)
    {
      addToken(110);
      append((char)paramInt);
    }
    return i;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\Decompiler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */