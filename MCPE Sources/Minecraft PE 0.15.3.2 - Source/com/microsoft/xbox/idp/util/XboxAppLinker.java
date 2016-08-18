package com.microsoft.xbox.idp.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public class XboxAppLinker
{
  private static final String AMAZON_STORE_URI = "amzn://apps/android?p=";
  private static final String AMAZON_TABLET_STORE_PACKAGE = "com.amazon.venezia";
  private static final String AMAZON_UNDERGROUND_PACKAGE = "com.amazon.mShop.android";
  private static final String OCULUS_STORE_WEB_URI = "oculus.store://link/products?referrer=manual&item_id=";
  private static final String OCULUS_XBOXAPP_APP_ID = "1193603937358048";
  private static final String PLAY_STORE_PACKAGE = "com.android.vending";
  private static final String PLAY_STORE_URI = "market://details?id=";
  private static final String PLAY_STORE_WEB_URI = "https://play.google.com/store/apps/details?id=";
  private static final String TAG = XboxAppLinker.class.getSimpleName();
  public static final String XBOXAPP_BETA_PACKAGE = "com.microsoft.xboxone.smartglass.beta";
  public static final String XBOXAPP_PACKAGE = "com.microsoft.xboxone.smartglass";
  public static boolean betaAppInstalled;
  public static boolean mainAppInstalled;
  
  public static Intent getAppIntent(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
  }
  
  public static Intent getXboxAppInAnyMarketIntent(Context paramContext)
  {
    Object localObject2 = getXboxAppInMarketIntent(paramContext, "market://details?id=", "com.android.vending");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = getXboxAppInMarketIntent(paramContext, "amzn://apps/android?p=", "com.amazon.mShop.android");
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = getXboxAppInMarketIntent(paramContext, "amzn://apps/android?p=", "com.amazon.venezia");
    }
    paramContext = (Context)localObject2;
    if (localObject2 == null) {
      paramContext = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.microsoft.xboxone.smartglass"));
    }
    paramContext.setFlags(270565376);
    return paramContext;
  }
  
  public static Intent getXboxAppInMarketIntent(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1 + "com.microsoft.xboxone.smartglass"));
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramString1, 0).iterator();
    while (paramContext.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.next();
      if (localResolveInfo.activityInfo.applicationInfo.packageName.equals(paramString2))
      {
        paramContext = localResolveInfo.activityInfo;
        paramContext = new ComponentName(paramContext.applicationInfo.packageName, paramContext.name);
        paramString1.setFlags(270532608);
        paramString1.setComponent(paramContext);
        return paramString1;
      }
    }
    return null;
  }
  
  public static Intent getXboxAppInOculusMarketIntent(Context paramContext)
  {
    paramContext = new Intent("android.intent.action.VIEW", Uri.parse("oculus.store://link/products?referrer=manual&item_id=1193603937358048"));
    paramContext.setFlags(270565376);
    return paramContext;
  }
  
  public static Intent getXboxAppLaunchIntent(Context paramContext)
  {
    if (betaAppInstalled) {
      return paramContext.getPackageManager().getLaunchIntentForPackage("com.microsoft.xboxone.smartglass.beta");
    }
    return paramContext.getPackageManager().getLaunchIntentForPackage("com.microsoft.xboxone.smartglass");
  }
  
  public static boolean isInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isServiceInstalled(String paramString1, Context paramContext, String paramString2)
  {
    try
    {
      paramString1 = new ComponentName(paramString1, paramString2);
      paramContext.getPackageManager().getServiceInfo(paramString1, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString1)
    {
      Log.i(TAG, paramString1.getClass().toString());
      Log.i(TAG, paramString1.getMessage());
    }
    return false;
  }
  
  public static void launchXboxAppStorePage(Context paramContext)
  {
    paramContext.startActivity(getXboxAppInAnyMarketIntent(paramContext));
  }
  
  public static boolean xboxAppIsInstalled(Context paramContext)
  {
    boolean bool = false;
    if (isInstalled(paramContext, "com.microsoft.xboxone.smartglass"))
    {
      mainAppInstalled = true;
      if (!isInstalled(paramContext, "com.microsoft.xboxone.smartglass.beta")) {
        break label51;
      }
    }
    label51:
    for (betaAppInstalled = true;; betaAppInstalled = false)
    {
      if ((mainAppInstalled) || (betaAppInstalled)) {
        bool = true;
      }
      return bool;
      mainAppInstalled = false;
      break;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\util\XboxAppLinker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */