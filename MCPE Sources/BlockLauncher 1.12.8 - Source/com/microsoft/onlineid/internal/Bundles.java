package com.microsoft.onlineid.internal;

import android.os.Bundle;
import com.microsoft.onlineid.internal.log.Logger;
import java.util.Iterator;
import java.util.Set;

public class Bundles
{
  public static void log(String paramString, Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      Logger.info(paramString);
      paramString = paramBundle.keySet().iterator();
      while (paramString.hasNext())
      {
        String str = (String)paramString.next();
        Object localObject = paramBundle.get(str);
        if (localObject != null) {
          Logger.info(String.format("%s: %s (%s)", new Object[] { str, localObject.toString(), localObject.getClass().getName() }));
        } else {
          Logger.info(String.format("%s: null", new Object[] { str }));
        }
      }
    }
    Logger.info(paramString + " (bundle was null)");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\Bundles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */