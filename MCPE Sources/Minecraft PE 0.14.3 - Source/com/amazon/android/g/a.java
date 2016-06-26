package com.amazon.android.g;

import android.app.Application;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.util.KiwiLogger;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.Comparator;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public final class a
  implements com.amazon.android.framework.resource.b, c
{
  private static final KiwiLogger a = new KiwiLogger("ObfuscationManagerImpl");
  private static final Comparator b = new b();
  @Resource
  private Application c;
  private SecretKeySpec d;
  
  private SecretKeySpec a()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(this.c.getPackageName());
    ((StringBuilder)localObject).append(Settings.Secure.getString(this.c.getContentResolver(), "android_id"));
    ((StringBuilder)localObject).append(Build.FINGERPRINT);
    ((StringBuilder)localObject).append(Build.BRAND);
    ((StringBuilder)localObject).append(Build.BOARD);
    ((StringBuilder)localObject).append(Build.MODEL);
    localObject = ((StringBuilder)localObject).toString();
    try
    {
      SecureRandom localSecureRandom = b();
      KeyGenerator localKeyGenerator = KeyGenerator.getInstance("AES");
      localSecureRandom.setSeed(a("SHA-256", a("MD5", ((String)localObject).getBytes())));
      localKeyGenerator.init(128, localSecureRandom);
      localObject = new SecretKeySpec(localKeyGenerator.generateKey().getEncoded(), "AES");
      return (SecretKeySpec)localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      if (KiwiLogger.ERROR_ON) {
        a.error("Unable to create KeySpec: " + localNoSuchAlgorithmException, localNoSuchAlgorithmException);
      }
      return null;
    }
    catch (NoSuchProviderException localNoSuchProviderException)
    {
      for (;;)
      {
        if (KiwiLogger.ERROR_ON) {
          a.error("Unable to find appropriate provider: ", localNoSuchProviderException);
        }
      }
    }
  }
  
  private static byte[] a(String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      paramString = MessageDigest.getInstance(paramString);
      paramString.update(paramArrayOfByte);
      return paramString.digest();
    }
    catch (NoSuchAlgorithmException paramString)
    {
      if (KiwiLogger.ERROR_ON) {
        a.error("Failed to create MessageDigest: " + paramString, paramString);
      }
    }
    return null;
  }
  
  private byte[] a(byte[] paramArrayOfByte)
  {
    try
    {
      Cipher localCipher = Cipher.getInstance("AES");
      localCipher.init(1, this.d);
      paramArrayOfByte = localCipher.doFinal(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      if (KiwiLogger.ERROR_ON) {
        a.error("Error obfuscating data: " + paramArrayOfByte, paramArrayOfByte);
      }
    }
    return null;
  }
  
  private static SecureRandom b()
    throws NoSuchAlgorithmException, NoSuchProviderException
  {
    if (Build.VERSION.SDK_INT >= 17) {
      try
      {
        SecureRandom localSecureRandom1 = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        return localSecureRandom1;
      }
      catch (NoSuchProviderException localNoSuchProviderException)
      {
        Provider[] arrayOfProvider = Security.getProviders("SecureRandom.SHA1PRNG");
        Arrays.sort(arrayOfProvider, b);
        byte[] arrayOfByte1 = new byte[8];
        byte[] tmp38_37 = arrayOfByte1;
        tmp38_37[0] = 0;
        byte[] tmp43_38 = tmp38_37;
        tmp43_38[1] = 1;
        byte[] tmp48_43 = tmp43_38;
        tmp48_43[2] = 2;
        byte[] tmp53_48 = tmp48_43;
        tmp53_48[3] = 3;
        byte[] tmp58_53 = tmp53_48;
        tmp58_53[4] = 4;
        byte[] tmp63_58 = tmp58_53;
        tmp63_58[5] = 5;
        byte[] tmp68_63 = tmp63_58;
        tmp68_63[6] = 6;
        byte[] tmp74_68 = tmp68_63;
        tmp74_68[7] = 7;
        tmp74_68;
        byte[] arrayOfByte2 = new byte['?'];
        byte[] arrayOfByte3 = new byte['?'];
        int j = arrayOfProvider.length;
        int i = 0;
        while (i < j)
        {
          Provider localProvider = arrayOfProvider[i];
          SecureRandom localSecureRandom2 = SecureRandom.getInstance("SHA1PRNG", localProvider);
          localSecureRandom2.setSeed(arrayOfByte1);
          localSecureRandom2.nextBytes(arrayOfByte2);
          localSecureRandom2 = SecureRandom.getInstance("SHA1PRNG", localProvider);
          localSecureRandom2.setSeed(arrayOfByte1);
          localSecureRandom2.nextBytes(arrayOfByte3);
          if (Arrays.equals(arrayOfByte2, arrayOfByte3)) {
            return SecureRandom.getInstance("SHA1PRNG", localProvider);
          }
          i += 1;
        }
        throw new NoSuchProviderException("No provider with predictable SecureRandom found.");
      }
    }
    return SecureRandom.getInstance("SHA1PRNG");
  }
  
  private byte[] b(byte[] paramArrayOfByte)
  {
    try
    {
      Cipher localCipher = Cipher.getInstance("AES");
      localCipher.init(2, this.d);
      paramArrayOfByte = localCipher.doFinal(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      if (KiwiLogger.ERROR_ON) {
        a.error("Error unobfuscating data: " + paramArrayOfByte, paramArrayOfByte);
      }
    }
    return null;
  }
  
  private static byte[] c(String paramString)
  {
    int j = paramString.length();
    byte[] arrayOfByte = new byte[j / 2];
    int i = 0;
    while (i < j)
    {
      arrayOfByte[(i / 2)] = ((byte)(Character.digit(paramString.charAt(i), 16) << 4 | Character.digit(paramString.charAt(i + 1), 16)));
      i += 2;
    }
    return arrayOfByte;
  }
  
  public final String a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    if (this.d == null) {
      return null;
    }
    try
    {
      paramString = a(paramString.getBytes("UTF-8"));
      StringBuffer localStringBuffer = new StringBuffer(paramString.length * 2);
      int i = 0;
      while (i < paramString.length)
      {
        int j = paramString[i];
        localStringBuffer.append("0123456789ABCDEF".charAt(j >> 4 & 0xF)).append("0123456789ABCDEF".charAt(j & 0xF));
        i += 1;
      }
      paramString = localStringBuffer.toString();
      return paramString;
    }
    catch (UnsupportedEncodingException paramString) {}
    return null;
  }
  
  public final String b(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    if (this.d == null) {
      return null;
    }
    try
    {
      paramString = new String(b(c(paramString)), "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString) {}
    return null;
  }
  
  public final void onResourcesPopulated()
  {
    this.d = a();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\g\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */