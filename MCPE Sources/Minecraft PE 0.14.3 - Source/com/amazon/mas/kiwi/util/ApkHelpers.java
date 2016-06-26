package com.amazon.mas.kiwi.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSigner;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class ApkHelpers
{
  private static final String CONTENT_PREFIX = "com.amazon.content.id.";
  
  public static byte[] getApkSignature(String paramString)
    throws IOException, ApkInvalidException
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      throw new IllegalArgumentException("apkFileName cannot be null or empty!");
    }
    paramString = new JarFile(paramString);
    Certificate localCertificate;
    try
    {
      scanJar(paramString);
      localCertificate = getFirstSigningCert(paramString);
      if (localCertificate == null) {
        return null;
      }
    }
    catch (SecurityException paramString)
    {
      return null;
    }
    paramString = null;
    if ((localCertificate instanceof X509Certificate)) {
      paramString = ((X509Certificate)localCertificate).getSignature();
    }
    return paramString;
  }
  
  private static CodeSigner[] getCodeSigners(JarFile paramJarFile)
  {
    CodeSigner[] arrayOfCodeSigner = null;
    Enumeration localEnumeration = paramJarFile.entries();
    paramJarFile = arrayOfCodeSigner;
    while (localEnumeration.hasMoreElements())
    {
      arrayOfCodeSigner = ((JarEntry)localEnumeration.nextElement()).getCodeSigners();
      paramJarFile = arrayOfCodeSigner;
      if (arrayOfCodeSigner != null) {
        paramJarFile = arrayOfCodeSigner;
      }
    }
    return paramJarFile;
  }
  
  public static String getContentID(JarFile paramJarFile)
  {
    if (paramJarFile == null) {
      throw new IllegalArgumentException("apkSrc must not be null!");
    }
    paramJarFile = paramJarFile.entries();
    while (paramJarFile.hasMoreElements())
    {
      Object localObject = (JarEntry)paramJarFile.nextElement();
      if (!((JarEntry)localObject).isDirectory())
      {
        localObject = getContentIDFromName(((JarEntry)localObject).getName());
        if ((localObject != null) && (((String)localObject).length() != 0)) {
          return (String)localObject;
        }
      }
    }
    return null;
  }
  
  public static String getContentIDFromName(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      throw new IllegalArgumentException("name cannot be null or empty!");
    }
    int i = paramString.indexOf("com.amazon.content.id.");
    if (i < 0) {
      return null;
    }
    if (paramString.length() <= "com.amazon.content.id.".length()) {
      return null;
    }
    return paramString.substring("com.amazon.content.id.".length() + i);
  }
  
  private static Certificate getFirstSigningCert(JarFile paramJarFile)
  {
    Object localObject2 = getCodeSigners(paramJarFile);
    Object localObject1 = null;
    paramJarFile = (JarFile)localObject1;
    if (localObject2 != null)
    {
      paramJarFile = (JarFile)localObject1;
      if (localObject2.length > 0)
      {
        localObject2 = localObject2[0].getSignerCertPath().getCertificates();
        paramJarFile = (JarFile)localObject1;
        if (!((List)localObject2).isEmpty()) {
          paramJarFile = (Certificate)((List)localObject2).get(0);
        }
      }
    }
    return paramJarFile;
  }
  
  public static boolean isSigned(File paramFile)
    throws IOException
  {
    Object localObject3 = null;
    try
    {
      paramFile = new JarFile(paramFile);
      boolean bool;
      paramFile.close();
    }
    finally
    {
      try
      {
        bool = isSigned(paramFile);
        paramFile.close();
        return bool;
      }
      finally {}
      localObject1 = finally;
      paramFile = (File)localObject3;
    }
    throw ((Throwable)localObject1);
  }
  
  public static boolean isSigned(JarFile paramJarFile)
    throws IOException
  {
    if (paramJarFile == null) {
      throw new IllegalArgumentException("apkSrc must not be null!");
    }
    try
    {
      scanJar(paramJarFile);
      if (getCodeSigners(paramJarFile) != null) {
        return true;
      }
    }
    catch (SecurityException paramJarFile)
    {
      return true;
    }
    return false;
  }
  
  private static void scanJar(JarFile paramJarFile)
    throws IOException
  {
    Enumeration localEnumeration = paramJarFile.entries();
    byte[] arrayOfByte = new byte['?'];
    while (localEnumeration.hasMoreElements())
    {
      Object localObject3 = (JarEntry)localEnumeration.nextElement();
      Object localObject2 = null;
      Object localObject1 = null;
      try
      {
        localObject3 = paramJarFile.getInputStream((ZipEntry)localObject3);
        int i;
        do
        {
          localObject1 = localObject3;
          localObject2 = localObject3;
          i = ((InputStream)localObject3).read(arrayOfByte, 0, arrayOfByte.length);
        } while (i != -1);
        if (localObject3 != null) {
          ((InputStream)localObject3).close();
        }
      }
      catch (Exception paramJarFile)
      {
        localObject2 = localObject1;
        throw new ApkInvalidException(paramJarFile);
      }
      finally
      {
        if (localObject2 != null) {
          ((InputStream)localObject2).close();
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\mas\kiwi\util\ApkHelpers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */