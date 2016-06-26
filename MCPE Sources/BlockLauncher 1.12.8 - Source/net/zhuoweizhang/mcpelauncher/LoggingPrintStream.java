package net.zhuoweizhang.mcpelauncher;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Formatter;
import java.util.Locale;

abstract class LoggingPrintStream
  extends PrintStream
{
  private final StringBuilder builder = new StringBuilder();
  private CharBuffer decodedChars;
  private CharsetDecoder decoder;
  private ByteBuffer encodedBytes;
  private final Formatter formatter = new Formatter(this.builder, null);
  
  protected LoggingPrintStream()
  {
    super(new OutputStream()
    {
      public void write(int paramAnonymousInt)
        throws IOException
      {
        throw new AssertionError();
      }
    });
  }
  
  private void flush(boolean paramBoolean)
  {
    int j = this.builder.length();
    int k;
    for (int i = 0; i < j; i = k + 1)
    {
      k = this.builder.indexOf("\n", i);
      if (k == -1) {
        break;
      }
      log(this.builder.substring(i, k));
    }
    if (paramBoolean)
    {
      if (i < j) {
        log(this.builder.substring(i));
      }
      this.builder.setLength(0);
      return;
    }
    this.builder.delete(0, i);
  }
  
  public PrintStream append(char paramChar)
  {
    try
    {
      print(paramChar);
      return this;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public PrintStream append(CharSequence paramCharSequence)
  {
    try
    {
      this.builder.append(paramCharSequence);
      flush(false);
      return this;
    }
    finally
    {
      paramCharSequence = finally;
      throw paramCharSequence;
    }
  }
  
  public PrintStream append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    try
    {
      this.builder.append(paramCharSequence, paramInt1, paramInt2);
      flush(false);
      return this;
    }
    finally
    {
      paramCharSequence = finally;
      throw paramCharSequence;
    }
  }
  
  public boolean checkError()
  {
    return false;
  }
  
  public void close() {}
  
  public void flush()
  {
    try
    {
      flush(true);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public PrintStream format(String paramString, Object... paramVarArgs)
  {
    return format(Locale.getDefault(), paramString, paramVarArgs);
  }
  
  public PrintStream format(Locale paramLocale, String paramString, Object... paramVarArgs)
  {
    if (paramString == null) {
      try
      {
        throw new NullPointerException("format");
      }
      finally {}
    }
    this.formatter.format(paramLocale, paramString, paramVarArgs);
    flush(false);
    return this;
  }
  
  protected abstract void log(String paramString);
  
  public void print(char paramChar)
  {
    try
    {
      this.builder.append(paramChar);
      if (paramChar == '\n') {
        flush(false);
      }
      return;
    }
    finally {}
  }
  
  public void print(double paramDouble)
  {
    try
    {
      this.builder.append(paramDouble);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void print(float paramFloat)
  {
    try
    {
      this.builder.append(paramFloat);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void print(int paramInt)
  {
    try
    {
      this.builder.append(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void print(long paramLong)
  {
    try
    {
      this.builder.append(paramLong);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void print(Object paramObject)
  {
    try
    {
      this.builder.append(paramObject);
      flush(false);
      return;
    }
    finally
    {
      paramObject = finally;
      throw ((Throwable)paramObject);
    }
  }
  
  public void print(String paramString)
  {
    try
    {
      this.builder.append(paramString);
      flush(false);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void print(boolean paramBoolean)
  {
    try
    {
      this.builder.append(paramBoolean);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void print(char[] paramArrayOfChar)
  {
    try
    {
      this.builder.append(paramArrayOfChar);
      flush(false);
      return;
    }
    finally
    {
      paramArrayOfChar = finally;
      throw paramArrayOfChar;
    }
  }
  
  public PrintStream printf(String paramString, Object... paramVarArgs)
  {
    return format(paramString, paramVarArgs);
  }
  
  public PrintStream printf(Locale paramLocale, String paramString, Object... paramVarArgs)
  {
    return format(paramLocale, paramString, paramVarArgs);
  }
  
  public void println()
  {
    try
    {
      flush(true);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void println(char paramChar)
  {
    try
    {
      this.builder.append(paramChar);
      flush(true);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void println(double paramDouble)
  {
    try
    {
      this.builder.append(paramDouble);
      flush(true);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void println(float paramFloat)
  {
    try
    {
      this.builder.append(paramFloat);
      flush(true);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void println(int paramInt)
  {
    try
    {
      this.builder.append(paramInt);
      flush(true);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void println(long paramLong)
  {
    try
    {
      this.builder.append(paramLong);
      flush(true);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void println(Object paramObject)
  {
    try
    {
      this.builder.append(paramObject);
      flush(true);
      return;
    }
    finally
    {
      paramObject = finally;
      throw ((Throwable)paramObject);
    }
  }
  
  /* Error */
  public void println(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 28	net/zhuoweizhang/mcpelauncher/LoggingPrintStream:builder	Ljava/lang/StringBuilder;
    //   6: invokevirtual 42	java/lang/StringBuilder:length	()I
    //   9: ifne +70 -> 79
    //   12: aload_1
    //   13: ifnull +66 -> 79
    //   16: aload_1
    //   17: invokevirtual 156	java/lang/String:length	()I
    //   20: istore_3
    //   21: iconst_0
    //   22: istore_2
    //   23: iload_2
    //   24: iload_3
    //   25: if_icmpge +37 -> 62
    //   28: aload_1
    //   29: bipush 10
    //   31: iload_2
    //   32: invokevirtual 159	java/lang/String:indexOf	(II)I
    //   35: istore 4
    //   37: iload 4
    //   39: iconst_m1
    //   40: if_icmpeq +22 -> 62
    //   43: aload_0
    //   44: aload_1
    //   45: iload_2
    //   46: iload 4
    //   48: invokevirtual 160	java/lang/String:substring	(II)Ljava/lang/String;
    //   51: invokevirtual 56	net/zhuoweizhang/mcpelauncher/LoggingPrintStream:log	(Ljava/lang/String;)V
    //   54: iload 4
    //   56: iconst_1
    //   57: iadd
    //   58: istore_2
    //   59: goto -36 -> 23
    //   62: iload_2
    //   63: iload_3
    //   64: if_icmpge +12 -> 76
    //   67: aload_0
    //   68: aload_1
    //   69: iload_2
    //   70: invokevirtual 161	java/lang/String:substring	(I)Ljava/lang/String;
    //   73: invokevirtual 56	net/zhuoweizhang/mcpelauncher/LoggingPrintStream:log	(Ljava/lang/String;)V
    //   76: aload_0
    //   77: monitorexit
    //   78: return
    //   79: aload_0
    //   80: getfield 28	net/zhuoweizhang/mcpelauncher/LoggingPrintStream:builder	Ljava/lang/StringBuilder;
    //   83: aload_1
    //   84: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload_0
    //   89: iconst_1
    //   90: invokespecial 79	net/zhuoweizhang/mcpelauncher/LoggingPrintStream:flush	(Z)V
    //   93: goto -17 -> 76
    //   96: astore_1
    //   97: aload_0
    //   98: monitorexit
    //   99: aload_1
    //   100: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	101	0	this	LoggingPrintStream
    //   0	101	1	paramString	String
    //   22	48	2	i	int
    //   20	45	3	j	int
    //   35	23	4	k	int
    // Exception table:
    //   from	to	target	type
    //   2	12	96	finally
    //   16	21	96	finally
    //   28	37	96	finally
    //   43	54	96	finally
    //   67	76	96	finally
    //   79	93	96	finally
  }
  
  public void println(boolean paramBoolean)
  {
    try
    {
      this.builder.append(paramBoolean);
      flush(true);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void println(char[] paramArrayOfChar)
  {
    try
    {
      this.builder.append(paramArrayOfChar);
      flush(true);
      return;
    }
    finally
    {
      paramArrayOfChar = finally;
      throw paramArrayOfChar;
    }
  }
  
  protected void setError() {}
  
  public void write(int paramInt)
  {
    write(new byte[] { (byte)paramInt }, 0, 1);
  }
  
  public void write(byte[] paramArrayOfByte)
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    for (;;)
    {
      try
      {
        if (this.decoder != null) {
          break label175;
        }
        this.encodedBytes = ByteBuffer.allocate(80);
        this.decodedChars = CharBuffer.allocate(80);
        this.decoder = Charset.defaultCharset().newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
      }
      finally {}
      if (paramInt1 < paramInt2)
      {
        int i = Math.min(this.encodedBytes.remaining(), paramInt2 - paramInt1);
        this.encodedBytes.put(paramArrayOfByte, paramInt1, i);
        paramInt1 += i;
        this.encodedBytes.flip();
        CoderResult localCoderResult = this.decoder.decode(this.encodedBytes, this.decodedChars, false);
        this.decodedChars.flip();
        this.builder.append(this.decodedChars);
        this.decodedChars.clear();
        if (!localCoderResult.isOverflow()) {
          this.encodedBytes.compact();
        }
      }
      else
      {
        flush(false);
        return;
        label175:
        paramInt2 = paramInt1 + paramInt2;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\LoggingPrintStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */