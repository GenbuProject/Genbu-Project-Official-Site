package net.zhuoweizhang.mcpelauncher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class CoffeeScriptCompiler
{
  public static String compile(String paramString, boolean paramBoolean)
  {
    System.gc();
    paramString = new TranslateThread(paramString, paramBoolean);
    Thread localThread = new Thread(Thread.currentThread().getThreadGroup(), paramString, "BlockLauncher parse thread", 262144L);
    localThread.start();
    try
    {
      localThread.join();
      System.gc();
      if (paramString.error != null)
      {
        if ((paramString.error instanceof RuntimeException)) {}
        for (paramString = (RuntimeException)paramString.error;; paramString = new RuntimeException(paramString.error)) {
          throw paramString;
        }
      }
      return paramString.output;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  public static void compileFile(File paramFile1, File paramFile2)
    throws IOException
  {
    Object localObject = new FileInputStream(paramFile1);
    byte[] arrayOfByte = new byte[(int)paramFile1.length()];
    ((InputStream)localObject).read(arrayOfByte);
    ((InputStream)localObject).close();
    localObject = Charset.forName("UTF-8");
    paramFile1 = compile(new String(arrayOfByte, (Charset)localObject), isLiterateCoffeeScript(paramFile1));
    paramFile2 = new FileOutputStream(paramFile2);
    paramFile2.write(paramFile1.getBytes((Charset)localObject));
    paramFile2.close();
  }
  
  private static String compileForReal(String paramString, boolean paramBoolean)
  {
    throw new RuntimeException("CoffeeScript compiler has been removed");
  }
  
  public static boolean isCoffeeScript(File paramFile)
  {
    return (paramFile.getName().toLowerCase().endsWith(".coffee")) || (isLiterateCoffeeScript(paramFile));
  }
  
  public static boolean isLiterateCoffeeScript(File paramFile)
  {
    return paramFile.getName().toLowerCase().endsWith(".litcoffee");
  }
  
  public static String outputName(String paramString)
  {
    return paramString.substring(0, paramString.lastIndexOf(".")) + ".js";
  }
  
  private static class TranslateThread
    implements Runnable
  {
    public Throwable error;
    public String input;
    public boolean literate;
    public String output;
    
    public TranslateThread(String paramString, boolean paramBoolean)
    {
      this.input = paramString;
      this.literate = paramBoolean;
    }
    
    public void run()
    {
      try
      {
        this.output = CoffeeScriptCompiler.compileForReal(this.input, this.literate);
        return;
      }
      catch (Exception localException)
      {
        this.error = localException;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\CoffeeScriptCompiler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */