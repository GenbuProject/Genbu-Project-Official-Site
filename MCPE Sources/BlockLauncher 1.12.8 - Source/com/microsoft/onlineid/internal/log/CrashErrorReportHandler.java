package com.microsoft.onlineid.internal.log;

import android.content.Context;

public class CrashErrorReportHandler
  implements Thread.UncaughtExceptionHandler
{
  private static Thread.UncaughtExceptionHandler defaultExceptionHandler = null;
  private static ErrorReportManager reportManager = new ErrorReportManager();
  
  public static CrashErrorReportHandler getInstance()
  {
    return InstanceHolder.instance;
  }
  
  public void init(Context paramContext)
  {
    try
    {
      if (defaultExceptionHandler == null)
      {
        defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
      }
      reportManager.init(paramContext);
      return;
    }
    finally {}
  }
  
  public void sendPriorCrashReport(Context paramContext)
  {
    reportManager.checkAndSendCrashReportWithUserPermission(paramContext);
  }
  
  public void setSendScreenshot(boolean paramBoolean)
  {
    reportManager.setSendScreenshot(paramBoolean);
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    try
    {
      Logger.info("Handling of the uncaughtException");
      reportManager.generateAndSaveCrashReport(paramThrowable);
      defaultExceptionHandler.uncaughtException(paramThread, paramThrowable);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Logger.warning("Error CollectPackageInformation", localException);
      }
    }
  }
  
  private static class InstanceHolder
  {
    private static CrashErrorReportHandler instance = new CrashErrorReportHandler(null);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\log\CrashErrorReportHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */