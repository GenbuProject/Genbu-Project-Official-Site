package com.microsoft.xbox.toolkit;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlUtil
{
  public static boolean UrisEqualCaseInsensitive(URI paramURI1, URI paramURI2)
  {
    if (paramURI1 == paramURI2) {
      return true;
    }
    if ((paramURI1 == null) || (paramURI2 == null)) {
      return false;
    }
    return JavaUtil.stringsEqualCaseInsensitive(paramURI1.toString(), paramURI2.toString());
  }
  
  public static String encodeUrl(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    do
    {
      return null;
      paramString = getEncodedUri(paramString);
    } while (paramString == null);
    return paramString.toString();
  }
  
  public static URI getEncodedUri(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return null;
    }
    return getEncodedUriNonNull(paramString);
  }
  
  public static URI getEncodedUriNonNull(String paramString)
  {
    try
    {
      paramString = new URL(paramString);
      paramString = new URI(paramString.getProtocol(), paramString.getUserInfo(), paramString.getHost(), paramString.getPort(), paramString.getPath(), paramString.getQuery(), paramString.getRef());
      return paramString;
    }
    catch (URISyntaxException paramString)
    {
      return null;
    }
    catch (MalformedURLException paramString) {}
    return null;
  }
  
  public static URI getUri(String paramString)
  {
    if (JavaUtil.isNullOrEmpty(paramString)) {
      return null;
    }
    try
    {
      paramString = new URI(paramString);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\UrlUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */