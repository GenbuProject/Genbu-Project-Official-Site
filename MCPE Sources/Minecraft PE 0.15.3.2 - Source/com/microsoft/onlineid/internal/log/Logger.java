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
    //   4: invokestatic 42	com/microsoft/onlineid/internal/configuration/Settings:getInstance	(Landroid/content/Context;)Lcom/microsoft/onlineid/internal/configuration/Settings;
    //   7: astore_0
    //   8: getstatic 15	com/microsoft/onlineid/internal/log/Logger:logInstance	Lcom/microsoft/onlineid/internal/log/LogInstance;
    //   11: ifnonnull +36 -> 47
    //   14: new 10	com/microsoft/onlineid/internal/log/LogInstance
    //   17: dup
    //   18: aload_0
    //   19: ldc 44
    //   21: invokevirtual 48	com/microsoft/onlineid/internal/configuration/Settings:getSetting	(Ljava/lang/String;)Ljava/lang/String;
    //   24: invokestatic 54	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   27: aload_0
    //   28: ldc 56
    //   30: invokevirtual 48	com/microsoft/onlineid/internal/configuration/Settings:getSetting	(Ljava/lang/String;)Ljava/lang/String;
    //   33: invokestatic 54	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   36: iconst_1
    //   37: invokespecial 59	com/microsoft/onlineid/internal/log/LogInstance:<init>	(ZZZ)V
    //   40: putstatic 15	com/microsoft/onlineid/internal/log/Logger:logInstance	Lcom/microsoft/onlineid/internal/log/LogInstance;
    //   43: ldc 2
    //   45: monitorexit
    //   46: return
    //   47: getstatic 15	com/microsoft/onlineid/internal/log/Logger:logInstance	Lcom/microsoft/onlineid/internal/log/LogInstance;
    //   50: aload_0
    //   51: ldc 56
    //   53: invokevirtual 48	com/microsoft/onlineid/internal/configuration/Settings:getSetting	(Ljava/lang/String;)Ljava/lang/String;
    //   56: invokestatic 54	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   59: invokevirtual 63	com/microsoft/onlineid/internal/log/LogInstance:setIsLoggingEnabled	(Z)V
    //   62: getstatic 15	com/microsoft/onlineid/internal/log/Logger:logInstance	Lcom/microsoft/onlineid/internal/log/LogInstance;
    //   65: aload_0
    //   66: ldc 44
    //   68: invokevirtual 48	com/microsoft/onlineid/internal/configuration/Settings:getSetting	(Ljava/lang/String;)Ljava/lang/String;
    //   71: invokestatic 54	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   74: invokevirtual 66	com/microsoft/onlineid/internal/log/LogInstance:setIsRedactionEnable	(Z)V
    //   77: goto -34 -> 43
    //   80: astore_0
    //   81: ldc 2
    //   83: monitorexit
    //   84: aload_0
    //   85: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	paramContext	android.content.Context
    // Exception table:
    //   from	to	target	type
    //   3	43	80	finally
    //   47	77	80	finally
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\log\Logger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */