package com.amazon.android.m;

import com.amazon.android.framework.util.KiwiLogger;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

public final class b
{
  private static final KiwiLogger a = new KiwiLogger("CertVerifier");
  private static final Set e;
  private final PKIXParameters b;
  private final CertPathValidator c;
  private final Set d;
  
  static
  {
    HashSet localHashSet = new HashSet();
    e = localHashSet;
    localHashSet.add("verisign");
    e.add("thawte");
  }
  
  public b()
    throws GeneralSecurityException
  {
    Object localObject1 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    ((TrustManagerFactory)localObject1).init(null);
    this.d = new HashSet();
    localObject1 = ((TrustManagerFactory)localObject1).getTrustManagers();
    int n = localObject1.length;
    int i = 0;
    while (i < n)
    {
      X509Certificate[] arrayOfX509Certificate = localObject1[i];
      if ((arrayOfX509Certificate instanceof X509TrustManager))
      {
        arrayOfX509Certificate = ((X509TrustManager)arrayOfX509Certificate).getAcceptedIssuers();
        if (arrayOfX509Certificate != null)
        {
          int i1 = arrayOfX509Certificate.length;
          int j = 0;
          int m;
          for (int k = 0; j < i1; k = m)
          {
            Object localObject2 = arrayOfX509Certificate[j];
            m = k;
            if (a((X509Certificate)localObject2))
            {
              if (KiwiLogger.TRACE_ON) {
                a.trace("Trusted Cert: " + ((X509Certificate)localObject2).getSubjectX500Principal().getName());
              }
              localObject2 = new TrustAnchor((X509Certificate)localObject2, null);
              this.d.add(localObject2);
              m = k + 1;
            }
            j += 1;
          }
          if (KiwiLogger.TRACE_ON) {
            a.trace(String.format("loaded %d certs\n", new Object[] { Integer.valueOf(k) }));
          }
        }
      }
      i += 1;
    }
    if (this.d.isEmpty()) {
      a.error("TrustManager did not return valid accepted issuers, likely 3P custom TrustManager implementation issue.");
    }
    this.b = new PKIXParameters(this.d);
    this.b.setRevocationEnabled(false);
    this.c = CertPathValidator.getInstance("PKIX");
  }
  
  private static boolean a(X509Certificate paramX509Certificate)
  {
    paramX509Certificate = paramX509Certificate.getSubjectDN().getName().toLowerCase();
    Iterator localIterator = e.iterator();
    while (localIterator.hasNext()) {
      if (paramX509Certificate.contains((String)localIterator.next())) {
        return true;
      }
    }
    return false;
  }
  
  public final boolean a(CertPath paramCertPath)
  {
    boolean bool = false;
    try
    {
      this.c.validate(paramCertPath, this.b);
      bool = true;
    }
    catch (CertPathValidatorException paramCertPath)
    {
      while (!(paramCertPath.getCause() instanceof CertificateExpiredException)) {}
      if (!KiwiLogger.TRACE_ON) {
        break label44;
      }
      a.trace("CertVerifier doesn't care about an out of date certificate.");
      return true;
    }
    catch (Exception paramCertPath)
    {
      label44:
      while (!KiwiLogger.TRACE_ON) {}
      a.error("Failed to verify cert path: " + paramCertPath, paramCertPath);
    }
    return bool;
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\m\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */