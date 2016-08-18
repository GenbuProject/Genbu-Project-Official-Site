package com.facebook.internal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;

public class CustomTab
{
  private static final String CHROME_PACKAGE = "com.android.chrome";
  private Uri uri;
  
  public CustomTab(String paramString, Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    this.uri = Utility.buildUri(ServerProtocol.getDialogAuthority(), ServerProtocol.getAPIVersion() + "/" + "dialog/" + paramString, localBundle);
  }
  
  public void openCustomTab(Activity paramActivity)
  {
    CustomTabsIntent localCustomTabsIntent = new CustomTabsIntent.Builder().build();
    localCustomTabsIntent.intent.setPackage("com.android.chrome");
    localCustomTabsIntent.launchUrl(paramActivity, this.uri);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\internal\CustomTab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */