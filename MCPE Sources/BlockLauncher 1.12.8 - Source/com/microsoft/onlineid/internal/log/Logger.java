package com.microsoft.onlineid.internal.log;

public class Logger
{
  private static LogInstance logInstance = new LogInstance();
  
  public static void error(IRedactable paramIRedactable)
  {
    logInstance.logRedactedMessage(paramIRedactable, 6);
  }
  
  public static void error(String paramString)
  {
    logInstance.logMessage(paramString, 6, null);
  }
  
  public static void error(String paramString, Throwable paramThrowable)
  {
    logInstance.logMessage(paramString, 6, paramThrowable);
  }
  
  public static String getLogTag()
  {
    return "MSA";
  }
  
  public static void info(IRedactable paramIRedactable)
  {
    logInstance.logRedactedMessage(paramIRedactable, 4);
  }
  
  public static void info(String paramString)
  {
    logInstance.logMessage(paramString, 4, null);
  }
  
  public static void info(String paramString, Throwable paramThrowable)
  {
    logInstance.logMessage(paramString, 4, paramThrowable);
  }
  
  /* Error */
  public static void initialize(android.content.Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 40	com/microsoft/onlineid/internal/configuration/Settings:initialize	(Landroid/content/Context;)V
    //   7: getstatic 15	com/microsoft/onlineid/internal/log/Logger:logInstance	Lcom/microsoft/onlineid/internal/log/LogInstance;
    //   10: ifnonnull +34 -> 44
    //   13: new 10	com/microsoft/onlineid/internal/log/LogInstance
    //   16: dup
    //   17: ldc 42
    //   19: invokestatic 46	com/microsoft/onlineid/internal/configuration/Settings:getSetting	(Ljava/lang/String;)Ljava/lang/String;
    //   22: invokestatic 52	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   25: ldc 54
    //   27: invokestatic 46	com/microsoft/onlineid/internal/configuration/Settings:getSetting	(Ljava/lang/String;)Ljava/lang/String;
    //   30: invokestatic 52	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   33: iconst_1
    //   34: invokespecial 57	com/microsoft/onlineid/internal/log/LogInstance:<init>	(ZZZ)V
    //   37: putstatic 15	com/microsoft/onlineid/internal/log/Logger:logInstance	Lcom/microsoft/onlineid/internal/log/LogInstance;
    //   40: ldc 2
    //   42: monitorexit
    //   43: return
    //   44: getstatic 15	com/microsoft/onlineid/internal/log/Logger:logInstance	Lcom/microsoft/onlineid/internal/log/LogInstance;
    //   47: ldc 54
    //   49: invokestatic 46	com/microsoft/onlineid/internal/configuration/Settings:getSetting	(Ljava/lang/String;)Ljava/lang/String;
    //   52: invokestatic 52	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   55: invokevirtual 61	com/microsoft/onlineid/internal/log/LogInstance:setIsLoggingEnabled	(Z)V
    //   58: getstatic 15	com/microsoft/onlineid/internal/log/Logger:logInstance	Lcom/microsoft/onlineid/internal/log/LogInstance;
    //   61: ldc 42
    //   63: invokestatic 46	com/microsoft/onlineid/internal/configuration/Settings:getSetting	(Ljava/lang/String;)Ljava/lang/String;
    //   66: invokestatic 52	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   69: invokevirtual 64	com/microsoft/onlineid/internal/log/LogInstance:setIsRedactionEnable	(Z)V
    //   72: goto -32 -> 40
    //   75: astore_0
    //   76: ldc 2
    //   78: monitorexit
    //   79: aload_0
    //   80: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	81	0	paramContext	android.content.Context
    // Exception table:
    //   from	to	target	type
    //   3	40	75	finally
    //   44	72	75	finally
  }
  
  static void setLogInstance(LogInstance paramLogInstance)
  {
    logInstance = paramLogInstance;
  }
  
  public static void setStackTraceLoggingOption(boolean paramBoolean)
  {
    logInstance.setIsStackTraceLoggingEnabled(paramBoolean);
  }
  
  public static boolean shouldRedact()
  {
    return logInstance.shouldRedact();
  }
  
  public static void warning(IRedactable paramIRedactable)
  {
    logInstance.logRedactedMessage(paramIRedactable, 5);
  }
  
  public static void warning(String paramString)
  {
    logInstance.logMessage(paramString, 5, null);
  }
  
  public static void warning(String paramString, Throwable paramThrowable)
  {
    logInstance.logMessage(paramString, 5, paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\log\Logger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */