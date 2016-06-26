package net.zhuoweizhang.mcpelauncher;

import android.content.Context;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;
import org.mozilla.javascript.Script;

public class ScriptTranslationCache
{
  public static final String CLASS_PACKAGE = "modpe.";
  public static final String SCRIPT_DEX_DIR = "dex";
  public static final String SCRIPT_ODEX_DIR = "odex";
  
  private static void dexScript(Context paramContext, File paramFile)
  {
    paramContext = new TranslateThread(paramContext, paramFile);
    paramFile = new Thread(Thread.currentThread().getThreadGroup(), paramContext, "BlockLauncher parse thread", 262144L);
    paramFile.start();
    try
    {
      paramFile.join();
      if (paramContext.error != null)
      {
        if ((paramContext.error instanceof RuntimeException)) {}
        for (paramContext = (RuntimeException)paramContext.error;; paramContext = new RuntimeException(paramContext.error)) {
          throw paramContext;
        }
      }
    }
    catch (InterruptedException paramFile)
    {
      for (;;) {}
    }
  }
  
  private static void dexScriptImpl(Context paramContext, File paramFile)
    throws IOException
  {}
  
  public static Script get(Context paramContext, File paramFile)
    throws IOException
  {
    File localFile = getDexFile(paramContext, paramFile);
    if ((!localFile.exists()) || (localFile.lastModified() < paramFile.lastModified())) {
      dexScript(paramContext, paramFile);
    }
    paramContext = paramContext.getDir("odex", 0);
    paramContext = new DexClassLoader(localFile.getAbsolutePath(), paramContext.getAbsolutePath(), null, ScriptTranslationCache.class.getClassLoader());
    try
    {
      paramContext = (Script)paramContext.loadClass("modpe." + getClassName(paramFile)).newInstance();
      return paramContext;
    }
    catch (ClassNotFoundException paramContext)
    {
      throw new RuntimeException(paramContext);
    }
    catch (InstantiationException paramContext)
    {
      throw new RuntimeException(paramContext);
    }
    catch (IllegalAccessException paramContext)
    {
      throw new RuntimeException(paramContext);
    }
  }
  
  private static String getClassName(File paramFile)
  {
    paramFile = paramFile.getName();
    return getClassName(paramFile.substring(0, paramFile.length() - 3));
  }
  
  private static String getClassName(String paramString)
  {
    char[] arrayOfChar = new char[paramString.length() + 1];
    int i = 0;
    if (!Character.isJavaIdentifierStart(paramString.charAt(0)))
    {
      arrayOfChar[0] = '_';
      i = 0 + 1;
    }
    int j = 0;
    if (j < paramString.length())
    {
      char c = paramString.charAt(j);
      if (Character.isJavaIdentifierPart(c)) {
        arrayOfChar[i] = c;
      }
      for (;;)
      {
        j += 1;
        i += 1;
        break;
        arrayOfChar[i] = '_';
      }
    }
    return new String(arrayOfChar).trim();
  }
  
  private static File getDexFile(Context paramContext, File paramFile)
  {
    return new File(paramContext.getDir("dex", 0), paramFile.getName() + ".dex");
  }
  
  private static class TranslateThread
    implements Runnable
  {
    private Context context;
    public Exception error;
    private File file;
    
    public TranslateThread(Context paramContext, File paramFile)
    {
      this.context = paramContext;
      this.file = paramFile;
    }
    
    public void run()
    {
      try
      {
        ScriptTranslationCache.dexScriptImpl(this.context, this.file);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        this.error = localException;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ScriptTranslationCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */