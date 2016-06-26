package com.microsoft.onlineid.internal;

import com.microsoft.onlineid.internal.configuration.Settings;

public class Assertion
{
  public static void check(boolean paramBoolean)
    throws AssertionError
  {
    check(paramBoolean, "");
  }
  
  public static void check(boolean paramBoolean, Object paramObject)
    throws AssertionError
  {
    if ((!paramBoolean) && (Settings.isDebugBuild())) {
      throw new AssertionError(paramObject);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\Assertion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */