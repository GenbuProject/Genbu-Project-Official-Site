package com.microsoft.xbox.toolkit;

import java.util.Date;

public class XLEUnhandledExceptionHandler
  implements Thread.UncaughtExceptionHandler
{
  public static XLEUnhandledExceptionHandler Instance = new XLEUnhandledExceptionHandler();
  private Thread.UncaughtExceptionHandler oldExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
  
  private void printStackTrace(String paramString, Throwable paramThrowable)
  {
    new Date();
    paramThrowable = paramThrowable.getStackTrace();
    int j = paramThrowable.length;
    paramString = "";
    int i = 0;
    while (i < j)
    {
      Object localObject = paramThrowable[i];
      paramString = paramString + String.format("\t%s\n", new Object[] { ((StackTraceElement)localObject).toString() });
      i += 1;
    }
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    paramThrowable.toString();
    if (paramThrowable.getCause() != null) {
      printStackTrace("CAUSE STACK TRACE", paramThrowable.getCause());
    }
    printStackTrace("MAIN THREAD STACK TRACE", paramThrowable);
    this.oldExceptionHandler.uncaughtException(paramThread, paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\XLEUnhandledExceptionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */