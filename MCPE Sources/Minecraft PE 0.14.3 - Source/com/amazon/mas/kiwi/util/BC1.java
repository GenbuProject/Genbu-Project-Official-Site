package com.amazon.mas.kiwi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class BC1
{
  private static String CHECKSUM_ALGORITHM = "MD5";
  private static final int DIGEST_UPDATE_BUFFER_SIZE = 10240;
  private static final Set<String> DIRECTORIES_TO_IGNORE_FOR_PARTIAL_CHECKSUM = new HashSet() {};
  private static final int MAX_SIZE_FOR_FULL_CHECKSUM = 20971520;
  
  private static void calculateFullChecksum(File paramFile, MessageDigest paramMessageDigest)
    throws IOException
  {
    updateMessageDigestWithInputStream(new FileInputStream(paramFile), paramMessageDigest);
  }
  
  private static void calculatePartialChecksum(File paramFile, MessageDigest paramMessageDigest, byte[] paramArrayOfByte)
    throws IOException
  {
    JarFile localJarFile = new JarFile(paramFile);
    Enumeration localEnumeration = localJarFile.entries();
    while (localEnumeration.hasMoreElements())
    {
      Object localObject = (JarEntry)localEnumeration.nextElement();
      if (!isInDirectoryToIgnore((JarEntry)localObject))
      {
        paramFile = null;
        try
        {
          localObject = localJarFile.getInputStream((ZipEntry)localObject);
          paramFile = (File)localObject;
          updateMessageDigestWithInputStream((InputStream)localObject, paramMessageDigest, paramArrayOfByte);
          closeIgnoreException((InputStream)localObject);
        }
        finally
        {
          closeIgnoreException(paramFile);
        }
      }
    }
    closeIgnoreException(localJarFile);
  }
  
  private static void closeIgnoreException(InputStream paramInputStream)
  {
    if (paramInputStream != null) {}
    try
    {
      paramInputStream.close();
      return;
    }
    catch (IOException paramInputStream) {}
  }
  
  private static void closeIgnoreException(ZipFile paramZipFile)
  {
    if (paramZipFile != null) {}
    try
    {
      paramZipFile.close();
      return;
    }
    catch (IOException paramZipFile) {}
  }
  
  public static byte[] getBC1Checksum(File paramFile)
    throws IOException
  {
    MessageDigest localMessageDigest = getMessageDigest();
    if (isTooLargeForFullChecksum(paramFile)) {
      calculatePartialChecksum(paramFile, localMessageDigest, new byte['?']);
    }
    for (;;)
    {
      return localMessageDigest.digest();
      calculateFullChecksum(paramFile, localMessageDigest);
    }
  }
  
  public static byte[] getBC1Checksum(String paramString)
    throws IOException
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Given path is null");
    }
    File localFile = new File(paramString);
    if (!localFile.exists()) {
      throw new IOException("Cannot calculate checksum, file does not exist: " + paramString);
    }
    return getBC1Checksum(localFile);
  }
  
  public static String getBC1ChecksumBase64(String paramString)
    throws IOException
  {
    return toBase64(getBC1Checksum(paramString));
  }
  
  private static MessageDigest getMessageDigest()
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance(CHECKSUM_ALGORITHM);
      return localMessageDigest;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new IllegalStateException(localNoSuchAlgorithmException);
    }
  }
  
  private static boolean isInDirectoryToIgnore(JarEntry paramJarEntry)
  {
    Iterator localIterator = DIRECTORIES_TO_IGNORE_FOR_PARTIAL_CHECKSUM.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (paramJarEntry.getName().startsWith(str)) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean isTooLargeForFullChecksum(File paramFile)
  {
    return paramFile.length() >= 20971520L;
  }
  
  private static String toBase64(byte[] paramArrayOfByte)
  {
    return new String(Base64.encodeBytes(paramArrayOfByte));
  }
  
  private static void updateMessageDigestWithInputStream(InputStream paramInputStream, MessageDigest paramMessageDigest)
    throws FileNotFoundException, IOException
  {
    updateMessageDigestWithInputStream(paramInputStream, paramMessageDigest, new byte['?']);
  }
  
  private static void updateMessageDigestWithInputStream(InputStream paramInputStream, MessageDigest paramMessageDigest, byte[] paramArrayOfByte)
    throws FileNotFoundException, IOException
  {
    for (;;)
    {
      int i = paramInputStream.read(paramArrayOfByte);
      if (i == -1) {
        break;
      }
      paramMessageDigest.update(paramArrayOfByte, 0, i);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\mas\kiwi\util\BC1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */