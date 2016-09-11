package com.integralblue.httpresponsecache.compat;

import java.util.logging.Logger;

public final class System
{
  private static final Logger LOGGER = Logger.getLogger(System.class.getName());
  
  public static void logI(String paramString, Throwable paramThrowable)
  {
    LOGGER.info(paramString + ": " + paramThrowable);
  }
  
  public static final void logW(String paramString)
  {
    LOGGER.warning(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\System.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */