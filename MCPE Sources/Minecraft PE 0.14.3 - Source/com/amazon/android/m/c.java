package com.amazon.android.m;

import android.app.Application;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.util.KiwiLogger;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.security.auth.x500.X500Principal;

public final class c
{
  private static final KiwiLogger a = new KiwiLogger("DataAuthenticationKeyLoader");
  @Resource
  private Application b;
  @Resource
  private com.amazon.android.n.a c;
  
  private static CertPath a(JarFile paramJarFile, JarEntry paramJarEntry)
    throws com.amazon.android.h.a
  {
    try
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("Extracting cert from entry: " + paramJarEntry.getName());
      }
      CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
      if (KiwiLogger.TRACE_ON) {
        a.trace("Generating certificates from entry input stream");
      }
      paramJarFile = localCertificateFactory.generateCertPath(new ArrayList(localCertificateFactory.generateCertificates(paramJarFile.getInputStream(paramJarEntry))));
      return paramJarFile;
    }
    catch (Exception paramJarFile)
    {
      throw com.amazon.android.h.a.a(paramJarFile);
    }
  }
  
  private static JarEntry a(JarFile paramJarFile)
    throws com.amazon.android.h.a
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("Searching for cert in apk");
    }
    paramJarFile = paramJarFile.entries();
    while (paramJarFile.hasMoreElements())
    {
      JarEntry localJarEntry = (JarEntry)paramJarFile.nextElement();
      if ((!localJarEntry.isDirectory()) && (localJarEntry.getName().equals("kiwi"))) {
        return localJarEntry;
      }
    }
    throw new com.amazon.android.h.a("CERT_NOT_FOUND", null);
  }
  
  private JarFile b()
    throws com.amazon.android.h.a
  {
    Object localObject = this.b.getPackageCodePath();
    if (KiwiLogger.TRACE_ON) {
      a.trace("Opening apk: " + (String)localObject);
    }
    try
    {
      localObject = new JarFile((String)localObject, false);
      return (JarFile)localObject;
    }
    catch (IOException localIOException)
    {
      throw com.amazon.android.h.a.a(localIOException);
    }
  }
  
  private static b c()
    throws com.amazon.android.h.a
  {
    try
    {
      b localb = new b();
      return localb;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      throw new com.amazon.android.h.a("FAILED_TO_ESTABLISH_TRUST", localGeneralSecurityException);
    }
  }
  
  public final PublicKey a()
    throws com.amazon.android.h.a
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("Loading data authentication key...");
    }
    if (KiwiLogger.TRACE_ON) {
      a.trace("Checking KiwiDataStore for key...");
    }
    Object localObject1 = (PublicKey)this.c.a("DATA_AUTHENTICATION_KEY");
    Object localObject2;
    StringBuilder localStringBuilder;
    if (KiwiLogger.TRACE_ON)
    {
      localObject2 = a;
      localStringBuilder = new StringBuilder().append("Key was cached: ");
      if (localObject1 == null) {
        break label92;
      }
    }
    label92:
    for (boolean bool = true;; bool = false)
    {
      ((KiwiLogger)localObject2).trace(bool);
      if (localObject1 == null) {
        break;
      }
      return (PublicKey)localObject1;
    }
    if (KiwiLogger.TRACE_ON) {
      a.trace("Loading authentication key from apk...");
    }
    localObject1 = b();
    localObject1 = a((JarFile)localObject1, a((JarFile)localObject1));
    int i;
    if ((localObject1 != null) && (((CertPath)localObject1).getCertificates().size() > 0))
    {
      localObject2 = (Certificate)((CertPath)localObject1).getCertificates().get(0);
      if ((localObject2 instanceof X509Certificate))
      {
        localObject2 = ((X509Certificate)localObject2).getSubjectX500Principal().getName();
        if (KiwiLogger.TRACE_ON) {
          a.trace("Kiwi Cert Details: " + (String)localObject2);
        }
        if ((((String)localObject2).contains("O=Amazon.com\\, Inc.")) && (((String)localObject2).contains("C=US")) && (((String)localObject2).contains("ST=Washington")) && (((String)localObject2).contains("L=Seattle"))) {
          i = 1;
        }
      }
    }
    while (i == 0)
    {
      throw new com.amazon.android.h.a("CERT_INVALID", null);
      i = 0;
      continue;
      i = 0;
    }
    if (!c().a((CertPath)localObject1)) {
      throw new com.amazon.android.h.a("VERIFICATION_FAILED", null);
    }
    localObject1 = ((X509Certificate)((CertPath)localObject1).getCertificates().get(0)).getPublicKey();
    if (KiwiLogger.TRACE_ON) {
      a.trace("Placing auth key into storage");
    }
    this.c.a("DATA_AUTHENTICATION_KEY", localObject1);
    return (PublicKey)localObject1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\m\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */