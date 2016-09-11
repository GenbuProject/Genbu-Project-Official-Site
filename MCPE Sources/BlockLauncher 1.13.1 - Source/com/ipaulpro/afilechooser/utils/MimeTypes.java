package com.ipaulpro.afilechooser.utils;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MimeTypes
{
  private Map<String, String> mMimeTypes = new HashMap();
  
  public String getMimeType(Uri paramUri)
  {
    return getMimeType(FileUtils.getFile(paramUri).getName());
  }
  
  public String getMimeType(String paramString)
  {
    paramString = FileUtils.getExtension(paramString);
    if (paramString.length() > 0)
    {
      str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString.substring(1));
      if (str != null) {
        return str;
      }
    }
    paramString = paramString.toLowerCase();
    String str = (String)this.mMimeTypes.get(paramString);
    paramString = str;
    if (str == null) {
      paramString = "*/*";
    }
    return paramString;
  }
  
  public void put(String paramString1, String paramString2)
  {
    paramString2 = paramString2.toLowerCase();
    this.mMimeTypes.put(paramString1, paramString2);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\ipaulpro\afilechooser\utils\MimeTypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */