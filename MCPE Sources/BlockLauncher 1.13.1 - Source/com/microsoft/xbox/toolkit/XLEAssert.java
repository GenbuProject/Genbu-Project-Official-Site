package com.microsoft.xbox.toolkit;

public class XLEAssert
{
  static
  {
    if (!XLEAssert.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public static void assertFalse(String paramString, boolean paramBoolean)
  {
    if (!paramBoolean) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      assertTrue(paramString, paramBoolean);
      return;
    }
  }
  
  public static void assertIsNotUIThread()
  {
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      assertTrue(bool);
      return;
    }
  }
  
  public static void assertIsUIThread()
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      assertTrue(bool);
      return;
    }
  }
  
  public static void assertNotNull(Object paramObject)
  {
    if (paramObject != null) {}
    for (boolean bool = true;; bool = false)
    {
      assertTrue(null, bool);
      return;
    }
  }
  
  public static void assertNotNull(String paramString, Object paramObject)
  {
    if (paramObject != null) {}
    for (boolean bool = true;; bool = false)
    {
      assertTrue(paramString, bool);
      return;
    }
  }
  
  public static void assertNull(Object paramObject)
  {
    if (paramObject == null) {}
    for (boolean bool = true;; bool = false)
    {
      assertTrue(null, bool);
      return;
    }
  }
  
  public static void assertTrue(String paramString, boolean paramBoolean)
  {
    assert (paramBoolean);
  }
  
  public static void assertTrue(boolean paramBoolean)
  {
    assertTrue(null, paramBoolean);
  }
  
  public static void fail(String paramString)
  {
    assertTrue(paramString, false);
  }
  
  private static String getCallerLocation()
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    int i = 0;
    int j = i;
    if (i < arrayOfStackTraceElement.length)
    {
      if ((!arrayOfStackTraceElement[i].getClassName().equals(XLEAssert.class.getName())) || (!arrayOfStackTraceElement[i].getMethodName().equals("getCallerLocation"))) {
        break label86;
      }
      j = i;
    }
    for (;;)
    {
      if ((j >= arrayOfStackTraceElement.length) || (!arrayOfStackTraceElement[j].getClassName().equals(XLEAssert.class.getName())))
      {
        if (j >= arrayOfStackTraceElement.length) {
          break label100;
        }
        return arrayOfStackTraceElement[j].toString();
        label86:
        i += 1;
        break;
      }
      j += 1;
    }
    label100:
    return "unknown";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\XLEAssert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */