package android.support.customtabs;

import android.os.Bundle;

public class CustomTabsCallback
{
  public static final int NAVIGATION_ABORTED = 4;
  public static final int NAVIGATION_FAILED = 3;
  public static final int NAVIGATION_FINISHED = 2;
  public static final int NAVIGATION_STARTED = 1;
  public static final int TAB_HIDDEN = 6;
  public static final int TAB_SHOWN = 5;
  
  public void extraCallback(String paramString, Bundle paramBundle) {}
  
  public void onNavigationEvent(int paramInt, Bundle paramBundle) {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\android\support\customtabs\CustomTabsCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */