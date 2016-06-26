package com.amazon.android.m;

import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.mas.kiwi.util.Base64;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.Signature;

public final class a
{
  private static final KiwiLogger a = new KiwiLogger("SignatureVerifier");
  
  public static boolean a(String paramString1, String paramString2, PublicKey paramPublicKey)
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("Verifying signature of data: '" + paramString1 + "', signature: '" + paramString2 + "', with key: '" + paramPublicKey.toString());
    }
    try
    {
      paramString2 = Base64.decode(paramString2.getBytes());
      Signature localSignature = Signature.getInstance("SHA1withRSA");
      localSignature.initVerify(paramPublicKey);
      localSignature.update(paramString1.getBytes());
      boolean bool = localSignature.verify(paramString2);
      return bool;
    }
    catch (GeneralSecurityException paramString1)
    {
      return false;
    }
    catch (IOException paramString1) {}
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\m\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */