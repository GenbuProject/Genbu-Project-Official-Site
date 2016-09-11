package com.integralblue.httpresponsecache.compat.libcore.net.http;

import java.util.ArrayList;
import java.util.List;

public final class HeaderParser
{
  public static void parseCacheControl(String paramString, CacheControlHandler paramCacheControlHandler)
  {
    int i = 0;
    while (i < paramString.length())
    {
      int j = skipUntil(paramString, i, "=,");
      String str2 = paramString.substring(i, j).trim();
      if ((j == paramString.length()) || (paramString.charAt(j) == ','))
      {
        i = j + 1;
        paramCacheControlHandler.handle(str2, null);
      }
      else
      {
        j = skipWhitespace(paramString, j + 1);
        String str1;
        if ((j < paramString.length()) && (paramString.charAt(j) == '"'))
        {
          i = j + 1;
          j = skipUntil(paramString, i, "\"");
          str1 = paramString.substring(i, j);
          i = j + 1;
        }
        for (;;)
        {
          paramCacheControlHandler.handle(str2, str1);
          break;
          i = skipUntil(paramString, j, ",");
          str1 = paramString.substring(j, i).trim();
        }
      }
    }
  }
  
  public static List<Challenge> parseChallenges(RawHeaders paramRawHeaders, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    if (j < paramRawHeaders.length())
    {
      if (!paramString.equalsIgnoreCase(paramRawHeaders.getFieldName(j))) {}
      for (;;)
      {
        j += 1;
        break;
        String str1 = paramRawHeaders.getValue(j);
        int i = 0;
        while (i < str1.length())
        {
          int k = skipUntil(str1, i, " ");
          String str2 = str1.substring(i, k).trim();
          k = skipWhitespace(str1, k);
          i = k;
          if (str1.regionMatches(k, "realm=\"", 0, "realm=\"".length()))
          {
            i = k + "realm=\"".length();
            k = skipUntil(str1, i, "\"");
            String str3 = str1.substring(i, k);
            i = skipWhitespace(str1, skipUntil(str1, k + 1, ",") + 1);
            localArrayList.add(new Challenge(str2, str3));
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static int parseSeconds(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      if (l > 2147483647L) {
        return Integer.MAX_VALUE;
      }
      if (l < 0L) {
        return 0;
      }
      return (int)l;
    }
    catch (NumberFormatException paramString) {}
    return -1;
  }
  
  private static int skipUntil(String paramString1, int paramInt, String paramString2)
  {
    for (;;)
    {
      if ((paramInt >= paramString1.length()) || (paramString2.indexOf(paramString1.charAt(paramInt)) != -1)) {
        return paramInt;
      }
      paramInt += 1;
    }
  }
  
  private static int skipWhitespace(String paramString, int paramInt)
  {
    for (;;)
    {
      if (paramInt < paramString.length())
      {
        int i = paramString.charAt(paramInt);
        if ((i == 32) || (i == 9)) {}
      }
      else
      {
        return paramInt;
      }
      paramInt += 1;
    }
  }
  
  public static abstract interface CacheControlHandler
  {
    public abstract void handle(String paramString1, String paramString2);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\HeaderParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */