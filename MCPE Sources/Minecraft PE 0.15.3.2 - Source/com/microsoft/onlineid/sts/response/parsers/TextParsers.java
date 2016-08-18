package com.microsoft.onlineid.sts.response.parsers;

import android.util.Base64;
import com.microsoft.onlineid.internal.Integers;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.net.MalformedURLException;
import java.net.URL;

class TextParsers
{
  static byte[] parseBase64(String paramString)
    throws StsParseException
  {
    try
    {
      paramString = Base64.decode(paramString, 2);
      return paramString;
    }
    catch (NullPointerException paramString)
    {
      throw new StsParseException(paramString);
    }
    catch (IllegalArgumentException paramString)
    {
      throw new StsParseException(paramString);
    }
  }
  
  static int parseInt(String paramString1, String paramString2)
    throws StsParseException
  {
    try
    {
      int i = Integer.parseInt(paramString1);
      return i;
    }
    catch (NumberFormatException paramString1)
    {
      throw new StsParseException(paramString2, paramString1, new Object[0]);
    }
  }
  
  static int parseIntHex(String paramString)
    throws StsParseException
  {
    try
    {
      int i = Integers.parseIntHex(paramString);
      return i;
    }
    catch (IllegalArgumentException paramString)
    {
      throw new StsParseException(paramString);
    }
  }
  
  static URL parseUrl(String paramString1, String paramString2)
    throws StsParseException
  {
    try
    {
      paramString1 = new URL(paramString1);
      return paramString1;
    }
    catch (MalformedURLException paramString1)
    {
      throw new StsParseException(paramString2, paramString1, new Object[0]);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\response\parsers\TextParsers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */