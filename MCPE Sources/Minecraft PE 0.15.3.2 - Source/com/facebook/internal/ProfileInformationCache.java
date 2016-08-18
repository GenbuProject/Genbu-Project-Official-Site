package com.facebook.internal;

import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

class ProfileInformationCache
{
  private static final ConcurrentHashMap<String, JSONObject> infoCache = new ConcurrentHashMap();
  
  public static JSONObject getProfileInformation(String paramString)
  {
    return (JSONObject)infoCache.get(paramString);
  }
  
  public static void putProfileInformation(String paramString, JSONObject paramJSONObject)
  {
    infoCache.put(paramString, paramJSONObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\internal\ProfileInformationCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */