package com.microsoft.onlineid.sts;

import android.net.Uri;
import android.net.Uri.Builder;
import android.util.Base64;
import com.microsoft.onlineid.internal.Strings;
import java.security.SecureRandom;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class OneTimeCredentialSigner
{
  private static final String ApplicationIDLabel = "appid";
  private static final String BinaryVersionLabel = "bver";
  private static final String CurrentTimeLabel = "ct";
  private static final String DATokenLabel = "da";
  private static final String HashAlgorithmLabel = "hashalg";
  private static final String HashAlgorithmValue = "SHA256";
  private static final String HashLabel = "hash";
  private static final String HmacSha256Algorithm = "HmacSHA256";
  private static final String NonceLabel = "nonce";
  private final Date _currentServerTime;
  private final DAToken _daToken;
  private final SecureRandom _secureRandom;
  private final SharedKeyGenerator _sharedKeyGenerator;
  
  OneTimeCredentialSigner(DAToken paramDAToken, Date paramDate, SecureRandom paramSecureRandom, SharedKeyGenerator paramSharedKeyGenerator)
  {
    this._daToken = paramDAToken;
    this._currentServerTime = paramDate;
    this._secureRandom = paramSecureRandom;
    this._sharedKeyGenerator = paramSharedKeyGenerator;
  }
  
  public OneTimeCredentialSigner(Date paramDate, DAToken paramDAToken)
  {
    this._daToken = paramDAToken;
    this._currentServerTime = paramDate;
    this._secureRandom = new SecureRandom();
    this._sharedKeyGenerator = new SharedKeyGenerator(paramDAToken.getSessionKey());
  }
  
  public String generateOneTimeSignedCredential(String paramString)
  {
    byte[] arrayOfByte = new byte[32];
    this._secureRandom.nextBytes(arrayOfByte);
    paramString = new Uri.Builder().appendQueryParameter("ct", Long.toString(this._currentServerTime.getTime() / 1000L)).appendQueryParameter("hashalg", "SHA256").appendQueryParameter("bver", "11").appendQueryParameter("appid", paramString).appendQueryParameter("da", this._daToken.getToken()).appendQueryParameter("nonce", Base64.encodeToString(arrayOfByte, 2));
    return paramString.appendQueryParameter("hash", Base64.encodeToString(Cryptography.getInitializedHmacSha256Digester(new SecretKeySpec(this._sharedKeyGenerator.generateKey(SharedKeyGenerator.KeyPurpose.CredentialSignature, arrayOfByte), "HmacSHA256")).doFinal(paramString.build().getEncodedQuery().getBytes(Strings.Utf8Charset)), 2)).build().getEncodedQuery();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\sts\OneTimeCredentialSigner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */