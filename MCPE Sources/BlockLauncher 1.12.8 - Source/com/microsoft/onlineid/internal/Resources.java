package com.microsoft.onlineid.internal;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import java.util.Locale;

public class Resources
{
  private final Context _appContext;
  
  public Resources(Context paramContext)
  {
    this._appContext = paramContext;
  }
  
  private int getIdentifierByType(String paramString1, String paramString2)
  {
    try
    {
      int i = this._appContext.getResources().getIdentifier(paramString1, paramString2, this._appContext.getPackageName());
      return i;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      Assertion.check(false, String.format(Locale.US, "%s resource with name %s not found", new Object[] { paramString2, paramString1 }));
    }
    return 0;
  }
  
  public static String getSdkVersion(Context paramContext)
  {
    return new Resources(paramContext).getSdkVersion();
  }
  
  public static String getString(Context paramContext, String paramString)
  {
    return new Resources(paramContext).getString(paramString);
  }
  
  public int getDimensionPixelSize(String paramString)
  {
    try
    {
      int i = this._appContext.getResources().getDimensionPixelSize(getIdentifierByType(paramString, "dimen"));
      return i;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      Assertion.check(false, String.format(Locale.US, "Dimen resource with name %s not found", new Object[] { paramString }));
    }
    return 0;
  }
  
  public int getId(String paramString)
  {
    try
    {
      int i = getIdentifierByType(paramString, "id");
      return i;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      Assertion.check(false, String.format(Locale.US, "Id resource with name %s not found", new Object[] { paramString }));
    }
    return 0;
  }
  
  public int getLayout(String paramString)
  {
    try
    {
      int i = getIdentifierByType(paramString, "layout");
      return i;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      Assertion.check(false, String.format(Locale.US, "Layout resource with name %s not found", new Object[] { paramString }));
    }
    return 0;
  }
  
  public int getMenu(String paramString)
  {
    try
    {
      int i = getIdentifierByType(paramString, "menu");
      return i;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      Assertion.check(false, String.format(Locale.US, "Menu resource with name %s not found", new Object[] { paramString }));
    }
    return 0;
  }
  
  public String getSdkVersion()
  {
    return getString("sdk_version_name");
  }
  
  public String getString(String paramString)
  {
    try
    {
      String str = this._appContext.getString(getIdentifierByType(paramString, "string"));
      return str;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      Assertion.check(false, String.format(Locale.US, "String resource with name %s not found", new Object[] { paramString }));
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\Resources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */