package com.amazon.android.framework.util;

import android.util.Log;

public class KiwiLogger
{
  public static boolean ERROR_ON = true;
  private static final String TAG = "Kiwi";
  private static boolean TEST_ON = false;
  public static boolean TRACE_ON = true;
  private String componentName;
  
  public KiwiLogger(String paramString)
  {
    this.componentName = paramString;
  }
  
  public static void enableTest()
  {
    TEST_ON = true;
  }
  
  private String getComponentMessage(String paramString)
  {
    return this.componentName + ": " + paramString;
  }
  
  public static boolean isTestEnabled()
  {
    return TEST_ON;
  }
  
  public void error(String paramString)
  {
    if (ERROR_ON) {
      Log.e("Kiwi", getComponentMessage(paramString));
    }
  }
  
  public void error(String paramString, Throwable paramThrowable)
  {
    if (ERROR_ON) {
      Log.e("Kiwi", getComponentMessage(paramString), paramThrowable);
    }
  }
  
  public void test(String paramString)
  {
    if (TEST_ON) {
      Log.e("Kiwi", "TEST-" + getComponentMessage(paramString));
    }
  }
  
  public void trace(String paramString)
  {
    if (TRACE_ON) {
      Log.d("Kiwi", getComponentMessage(paramString));
    }
  }
  
  public void trace(String paramString, Throwable paramThrowable)
  {
    if (TRACE_ON) {
      Log.d("Kiwi", getComponentMessage(paramString), paramThrowable);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\util\KiwiLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */