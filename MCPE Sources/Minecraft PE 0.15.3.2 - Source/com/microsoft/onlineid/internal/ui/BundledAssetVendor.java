package com.microsoft.onlineid.internal.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.configuration.Settings;
import com.microsoft.onlineid.internal.log.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

public class BundledAssetVendor
  implements IWebPropertyProvider
{
  private static final String AccessControlAllowOriginAllValue = "*";
  private static final String AccessControlAllowOriginKey = "Access-Control-Allow-Origin";
  private static final Map<String, String> AccessControlAllowOriginMap = Collections.singletonMap("Access-Control-Allow-Origin", "*");
  private static final String HttpsScheme = "https://";
  private static BundledAssetVendor Instance;
  public static final String ManifestAssetPath = "com.microsoft.onlineid.serverAssetBundle.path";
  public static final String ManifestAssetVersion = "com.microsoft.onlineid.serverAssetBundle.version";
  private final Context _applicationContext;
  private AssetManager _assetManager;
  private Object _countLock = new Object();
  private volatile int _hitCount;
  private volatile int _missCount;
  private String _pathToAssetBundle = null;
  private String _version;
  
  private BundledAssetVendor(Context paramContext)
  {
    this._applicationContext = getApplicationContextFromContext(paramContext);
  }
  
  private Context getApplicationContext()
  {
    return this._applicationContext;
  }
  
  private static Context getApplicationContextFromContext(Context paramContext)
  {
    Context localContext = paramContext;
    if (paramContext.getApplicationContext() != null) {
      localContext = paramContext.getApplicationContext();
    }
    return localContext;
  }
  
  public static BundledAssetVendor getInstance(Context paramContext)
    throws IllegalArgumentException
  {
    if (Instance == null) {}
    for (;;)
    {
      try
      {
        if (Instance == null)
        {
          Instance = new BundledAssetVendor(paramContext);
          Instance.initialize();
        }
        return Instance;
      }
      finally {}
      if (Instance.getApplicationContext() == getApplicationContextFromContext(paramContext)) {
        continue;
      }
      Assertion.check(false, "Replacing previous instance with new instance for provided different context.");
      try
      {
        Instance = new BundledAssetVendor(paramContext);
        Instance.initialize();
      }
      finally {}
    }
  }
  
  private void initialize()
  {
    this._assetManager = this._applicationContext.getAssets();
    Object localObject = this._applicationContext.getPackageManager();
    this._missCount = 0;
    this._hitCount = 0;
    try
    {
      localObject = ((PackageManager)localObject).getApplicationInfo(this._applicationContext.getPackageName(), 128).metaData;
      if (localObject == null)
      {
        this._pathToAssetBundle = null;
        this._version = null;
        return;
      }
      this._pathToAssetBundle = ((Bundle)localObject).getString("com.microsoft.onlineid.serverAssetBundle.path");
      this._version = ((Bundle)localObject).getString("com.microsoft.onlineid.serverAssetBundle.version");
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Logger.error("Package name not found", localNameNotFoundException);
    }
  }
  
  protected String buildLocalAssetPath(String paramString)
  {
    return this._pathToAssetBundle + '/' + paramString.substring("https://".length());
  }
  
  @TargetApi(17)
  public WebResourceResponse getAsset(String paramString)
  {
    if (TextUtils.isEmpty(this._pathToAssetBundle)) {
      return null;
    }
    String str = buildLocalAssetPath(paramString);
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    Mimetype localMimetype = Mimetype.findFromFilename(str);
    if (localMimetype == null) {
      return null;
    }
    try
    {
      Object localObject = this._assetManager.open(str);
      localObject = new WebResourceResponse(localMimetype.toString(), "UTF-8", (InputStream)localObject);
      if ((Build.VERSION.SDK_INT >= 21) && (localMimetype == Mimetype.FONT)) {
        ((WebResourceResponse)localObject).setResponseHeaders(AccessControlAllowOriginMap);
      }
      if (Settings.isDebugBuild()) {
        Logger.info("BundledAssetVendor: Proxied " + paramString + " with " + str);
      }
      incrementHitCount();
      return (WebResourceResponse)localObject;
    }
    catch (IOException localIOException)
    {
      if (Settings.isDebugBuild()) {
        Logger.info("BundledAssetVendor: MISS: No proxied asset found for " + paramString);
      }
      incrementMissCount();
    }
    return null;
  }
  
  public String getProperty(PropertyBag.Key paramKey)
  {
    switch (paramKey)
    {
    default: 
      return null;
    case ???: 
      return this._version;
    case ???: 
      return Integer.toString(this._hitCount);
    }
    return Integer.toString(this._missCount);
  }
  
  public boolean handlesProperty(PropertyBag.Key paramKey)
  {
    switch (paramKey)
    {
    default: 
      return false;
    }
    return true;
  }
  
  protected void incrementHitCount()
  {
    synchronized (this._countLock)
    {
      this._hitCount += 1;
      return;
    }
  }
  
  protected void incrementMissCount()
  {
    synchronized (this._countLock)
    {
      this._missCount += 1;
      return;
    }
  }
  
  protected void setHitCount(int paramInt)
  {
    synchronized (this._countLock)
    {
      this._hitCount = paramInt;
      return;
    }
  }
  
  protected void setMissCount(int paramInt)
  {
    synchronized (this._countLock)
    {
      this._missCount = paramInt;
      return;
    }
  }
  
  public void setProperty(PropertyBag.Key paramKey, String paramString)
  {
    try
    {
      switch (paramKey)
      {
      case ???: 
        setHitCount(Integer.parseInt(paramString));
        return;
      }
    }
    catch (NumberFormatException paramKey)
    {
      Logger.error("Could not convert string to integer: '" + paramString + "'");
      return;
    }
    setMissCount(Integer.parseInt(paramString));
    return;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\ui\BundledAssetVendor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */