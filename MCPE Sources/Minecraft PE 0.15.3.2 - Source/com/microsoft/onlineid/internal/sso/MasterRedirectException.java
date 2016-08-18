package com.microsoft.onlineid.internal.sso;

import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.internal.Strings;

public class MasterRedirectException
  extends InternalException
{
  private static final long serialVersionUID = 1L;
  private final String _redirectRequestTo;
  
  public MasterRedirectException(String paramString1, String paramString2)
  {
    super(paramString1 + ": " + paramString2);
    Strings.verifyArgumentNotNullOrEmpty(paramString2, "redirectRequestTo");
    this._redirectRequestTo = paramString2;
  }
  
  public String getRedirectRequestTo()
  {
    return this._redirectRequestTo;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\sso\MasterRedirectException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */