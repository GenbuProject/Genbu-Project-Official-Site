package com.microsoft.onlineid.sts;

import com.microsoft.onlineid.internal.Assertion;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography
{
  public static final String AesAlgorithm = "AES";
  public static final int AesCbcPkcs5PaddingInitializationVectorByteCount = 16;
  public static final String AesCbcPkcs5PaddingTransformation = "AES/CBC/PKCS5Padding";
  private static final String ByteToBase32Lookup = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
  public static final String HmacSha1Algorithm = "HmacSHA1";
  public static final String HmacSha256Algorithm = "HmacSHA256";
  public static final String Sha256Algorithm = "SHA256";
  
  public static byte[] decryptWithAesCbcPcs5PaddingCipher(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws IllegalBlockSizeException, BadPaddingException
  {
    return getInitializedDecryptionCipher("AES/CBC/PKCS5Padding", new SecretKeySpec(paramArrayOfByte2, "AES"), new IvParameterSpec(paramArrayOfByte1, 0, 16)).doFinal(paramArrayOfByte1, 16, paramArrayOfByte1.length - 16);
  }
  
  public static String encodeBase32(byte[] paramArrayOfByte)
  {
    boolean bool;
    StringBuilder localStringBuilder;
    int i;
    if (paramArrayOfByte != null)
    {
      bool = true;
      Assertion.check(bool);
      localStringBuilder = new StringBuilder(paramArrayOfByte.length * 8 / 5 + 1);
      i = 0;
    }
    for (;;)
    {
      if (i >= paramArrayOfByte.length) {
        break label129;
      }
      byte[] arrayOfByte = new byte[8];
      int j = Math.min(paramArrayOfByte.length - i, 5);
      System.arraycopy(paramArrayOfByte, i, arrayOfByte, arrayOfByte.length - j - 1, j);
      long l = ByteBuffer.wrap(arrayOfByte).getLong();
      j = (j + 1) * 8 - 5;
      for (;;)
      {
        if (j > 3)
        {
          localStringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".charAt((int)(l >>> j & 0x1F)));
          j -= 5;
          continue;
          bool = false;
          break;
        }
      }
      i += 5;
    }
    label129:
    return localStringBuilder.toString();
  }
  
  private static Cipher getInitializedDecryptionCipher(String paramString, Key paramKey, IvParameterSpec paramIvParameterSpec)
  {
    try
    {
      paramString = Cipher.getInstance(paramString);
      paramString.init(2, paramKey, paramIvParameterSpec);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new RuntimeException(paramString);
    }
    catch (NoSuchPaddingException paramString)
    {
      throw new RuntimeException(paramString);
    }
    catch (InvalidKeyException paramString)
    {
      throw new RuntimeException(paramString);
    }
    catch (InvalidAlgorithmParameterException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
  
  private static Mac getInitializedHmacDigester(Key paramKey, String paramString)
  {
    try
    {
      paramString = Mac.getInstance(paramString);
      paramString.init(paramKey);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramKey)
    {
      throw new RuntimeException(paramKey);
    }
    catch (InvalidKeyException paramKey)
    {
      throw new RuntimeException(paramKey);
    }
  }
  
  public static Mac getInitializedHmacSha1Digester(Key paramKey)
  {
    return getInitializedHmacDigester(paramKey, "HmacSHA1");
  }
  
  public static Mac getInitializedHmacSha256Digester(Key paramKey)
  {
    return getInitializedHmacDigester(paramKey, "HmacSHA256");
  }
  
  public static MessageDigest getSha256Digester()
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA256");
      return localMessageDigest;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new RuntimeException(localNoSuchAlgorithmException);
    }
  }
  
  public static MessageDigest getShaDigester()
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA");
      return localMessageDigest;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new RuntimeException(localNoSuchAlgorithmException);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\Cryptography.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */