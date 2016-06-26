package com.integralblue.httpresponsecache.compat.java.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InetAddress
{
  private static final Pattern IPV4_PATTERN = Pattern.compile("\\A(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}\\z");
  private static final Pattern IPV6_6HEX4DEC_PATTERN = Pattern.compile("\\A((?:[0-9A-Fa-f]{1,4}:){6,6})(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}\\z");
  private static final Pattern IPV6_HEX4DECCOMPRESSED_PATTERN = Pattern.compile("\\A((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?) ::((?:[0-9A-Fa-f]{1,4}:)*)(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}\\z");
  private static final Pattern IPV6_HEXCOMPRESSED_PATTERN = Pattern.compile("\\A((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)\\z");
  private static final Pattern IPV6_PATTERN = Pattern.compile("\\A(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}\\z");
  
  public static boolean isNumeric(String paramString)
  {
    return (IPV4_PATTERN.matcher(paramString).matches()) || (IPV6_HEX4DECCOMPRESSED_PATTERN.matcher(paramString).matches()) || (IPV6_6HEX4DEC_PATTERN.matcher(paramString).matches()) || (IPV6_HEXCOMPRESSED_PATTERN.matcher(paramString).matches()) || (IPV6_PATTERN.matcher(paramString).matches());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\java\net\InetAddress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */