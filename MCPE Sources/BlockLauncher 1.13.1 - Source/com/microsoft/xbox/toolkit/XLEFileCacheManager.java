package com.microsoft.xbox.toolkit;

import android.app.Activity;
import com.microsoft.xbox.toolkit.system.SystemUtil;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.io.File;
import java.util.HashMap;

public class XLEFileCacheManager
{
  public static XLEFileCache emptyFileCache = new XLEFileCache();
  private static HashMap<String, XLEFileCache> sAllCaches = new HashMap();
  private static HashMap<XLEFileCache, File> sCacheRootDirMap = new HashMap();
  
  public static XLEFileCache createCache(String paramString, int paramInt)
  {
    try
    {
      paramString = createCache(paramString, paramInt, true);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static XLEFileCache createCache(String paramString, int paramInt, boolean paramBoolean)
  {
    if (paramInt <= 0) {
      try
      {
        throw new IllegalArgumentException("maxFileNumber must be > 0");
      }
      finally {}
    }
    if ((paramString == null) || (paramString.length() <= 0)) {
      throw new IllegalArgumentException("subDirectory must be not null and at least one character length");
    }
    XLEFileCache localXLEFileCache = (XLEFileCache)sAllCaches.get(paramString);
    if (localXLEFileCache == null) {
      if (!paramBoolean) {
        paramString = emptyFileCache;
      }
    }
    do
    {
      for (;;)
      {
        return paramString;
        if (!SystemUtil.isSDCardAvailable())
        {
          paramString = emptyFileCache;
        }
        else
        {
          localXLEFileCache = new XLEFileCache(paramString, paramInt);
          File localFile = new File(XboxTcuiSdk.getActivity().getCacheDir(), paramString);
          if (!localFile.exists()) {
            localFile.mkdirs();
          }
          localXLEFileCache.size = localFile.list().length;
          sAllCaches.put(paramString, localXLEFileCache);
          sCacheRootDirMap.put(localXLEFileCache, localFile);
          paramString = localXLEFileCache;
        }
      }
      paramString = localXLEFileCache;
    } while (localXLEFileCache.maxFileNumber == paramInt);
    throw new IllegalArgumentException("The same subDirectory with different maxFileNumber already exist.");
  }
  
  static File getCacheRootDir(XLEFileCache paramXLEFileCache)
  {
    return (File)sCacheRootDirMap.get(paramXLEFileCache);
  }
  
  public static String getCacheStatus()
  {
    return sAllCaches.values().toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\XLEFileCacheManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */