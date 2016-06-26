package com.amazon.android.h;

import com.amazon.android.framework.exception.KiwiException;

public class a
  extends KiwiException
{
  private static final long serialVersionUID = 1L;
  
  public a(String paramString, Throwable paramThrowable)
  {
    super("DATA_AUTH_KEY_LOAD_FAILURE", paramString, paramThrowable);
  }
  
  public static a a(Throwable paramThrowable)
  {
    return new a("CERT_FAILED_TO_LOAD", paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\h\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */