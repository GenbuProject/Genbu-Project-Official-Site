package com.amazon.android.framework.task.command;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import com.amazon.android.b.d;
import com.amazon.android.b.g;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.m.c;
import com.amazon.mas.kiwi.util.Base64;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;

public final class e
{
  private static final KiwiLogger a = new KiwiLogger("CommandResultVerifier");
  @Resource
  private Application b;
  @Resource
  private c c;
  
  private PackageInfo a(String paramString)
    throws g
  {
    PackageManager localPackageManager = this.b.getPackageManager();
    try
    {
      paramString = localPackageManager.getPackageInfo(paramString, 64);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      a.trace("getPackageInfo() caught exception" + paramString);
      throw new g();
    }
  }
  
  private static String a(Signature paramSignature)
    throws CertificateException
  {
    paramSignature = new ByteArrayInputStream(paramSignature.toByteArray());
    return Base64.encodeBytes(((X509Certificate)CertificateFactory.getInstance("X509").generateCertificate(paramSignature)).getSignature());
  }
  
  private boolean a(String paramString, Signature paramSignature)
    throws com.amazon.android.h.a
  {
    try
    {
      String str = a(paramSignature);
      return com.amazon.android.m.a.a(str, paramString, this.c.a());
    }
    catch (CertificateException paramString)
    {
      if (KiwiLogger.ERROR_ON) {
        a.error("Failed to extract fingerprint from signature: " + paramSignature);
      }
    }
    return false;
  }
  
  private static boolean b(String paramString, Signature paramSignature)
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = paramString.equals(a(paramSignature));
      bool1 = bool2;
      a.trace("Signature valid: " + bool2);
      return bool2;
    }
    catch (CertificateException paramString)
    {
      a.error("Failed to extract fingerprint from signature");
    }
    return bool1;
  }
  
  public final void a(String paramString1, String paramString2)
    throws KiwiException
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("Verifying auth token: " + paramString1);
    }
    paramString2 = a(paramString2).signatures;
    int j = paramString2.length;
    int i = 0;
    while (i < j)
    {
      if (a(paramString1, paramString2[i])) {
        return;
      }
      i += 1;
    }
    throw new d();
  }
  
  public final boolean a(String paramString, List paramList)
  {
    a.trace("checkSignatures(" + paramString + ", " + paramList);
    try
    {
      paramString = a(paramString).signatures;
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        Signature localSignature = paramString[i];
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          boolean bool = b((String)localIterator.next(), localSignature);
          if (bool) {
            return true;
          }
        }
        i += 1;
      }
      return false;
    }
    catch (g paramString)
    {
      a.error("isPackageSignatureValid: caught exception while checking", paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\command\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */