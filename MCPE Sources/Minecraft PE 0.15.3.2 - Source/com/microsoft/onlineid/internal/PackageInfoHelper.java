package com.microsoft.onlineid.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;

public class PackageInfoHelper
{
  public static final String AuthenticatorPackageName = "com.microsoft.msa.authenticator";
  
  public static Signature[] getAppSignatures(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 64).signatures;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Assertion.check(false);
    }
    return new Signature[0];
  }
  
  public static String getAppVersionName(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static Signature[] getCurrentAppSignatures(Context paramContext)
  {
    return getAppSignatures(paramContext, paramContext.getPackageName());
  }
  
  public static int getCurrentAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Assertion.check(false);
    }
    return 0;
  }
  
  public static String getCurrentAppVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Assertion.check(false);
    }
    return "";
  }
  
  public static boolean isAuthenticatorApp(String paramString)
  {
    return "com.microsoft.msa.authenticator".equalsIgnoreCase(paramString);
  }
  
  public static boolean isAuthenticatorAppInstalled(Context paramContext)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo("com.microsoft.msa.authenticator", 128);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isCurrentApp(String paramString, Context paramContext)
  {
    return paramContext.getPackageName().equalsIgnoreCase(paramString);
  }
  
  public static boolean isRunningInAuthenticatorApp(Context paramContext)
  {
    return isAuthenticatorApp(paramContext.getPackageName());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\PackageInfoHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */