package com.amazon.android.h;

import com.amazon.android.framework.exception.KiwiException;

public class b
  extends KiwiException
{
  private static final long serialVersionUID = 1L;
  
  public b(String paramString1, String paramString2)
  {
    super("SIGNED_TOKEN_PARSE_FAILURE", paramString1, paramString2);
  }
  
  public static b a()
  {
    return new b("INVALID_FORMAT", null);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\h\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */