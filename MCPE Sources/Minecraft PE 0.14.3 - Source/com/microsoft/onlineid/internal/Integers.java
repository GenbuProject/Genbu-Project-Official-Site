package com.microsoft.onlineid.internal;

import java.util.Locale;

public class Integers
{
  public static int parseIntHex(String paramString)
  {
    Strings.verifyArgumentNotNullOrEmpty(paramString, "hexHr");
    long l = Long.decode(paramString).longValue();
    if ((l < 0L) || (l > 4294967295L)) {
      throw new IllegalArgumentException(String.format(Locale.US, "Hex string does not fit in integer: %s", new Object[] { paramString }));
    }
    return (int)l;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\Integers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */