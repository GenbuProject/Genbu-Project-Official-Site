package com.microsoft.onlineid.sts;

import com.microsoft.onlineid.internal.Strings;
import java.nio.ByteBuffer;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SharedKeyGenerator
{
  static final int NonceLengthBytes = 32;
  private final byte[] _sessionKey;
  
  public SharedKeyGenerator(byte[] paramArrayOfByte)
  {
    this._sessionKey = paramArrayOfByte;
  }
  
  static byte[] deriveSP800108HmacSHA256Key(int paramInt, byte[] paramArrayOfByte1, String paramString, byte[] paramArrayOfByte2)
  {
    ByteBuffer localByteBuffer1 = ByteBuffer.allocate(paramInt);
    ByteBuffer localByteBuffer2 = ByteBuffer.allocate(4);
    paramArrayOfByte1 = Cryptography.getInitializedHmacSha256Digester(new SecretKeySpec(paramArrayOfByte1, "HmacSHA256"));
    int i = 1;
    while (localByteBuffer1.position() < paramInt)
    {
      paramArrayOfByte1.reset();
      localByteBuffer2.clear();
      localByteBuffer2.putInt(i);
      localByteBuffer2.rewind();
      paramArrayOfByte1.update(localByteBuffer2);
      paramArrayOfByte1.update(paramString.getBytes(Strings.Utf8Charset));
      paramArrayOfByte1.update((byte)0);
      paramArrayOfByte1.update(paramArrayOfByte2);
      localByteBuffer2.clear();
      localByteBuffer2.putInt(paramInt * 8);
      localByteBuffer2.rewind();
      paramArrayOfByte1.update(localByteBuffer2);
      byte[] arrayOfByte = paramArrayOfByte1.doFinal();
      int k = arrayOfByte.length;
      int j = k;
      if (k > localByteBuffer1.remaining()) {
        j = localByteBuffer1.remaining();
      }
      localByteBuffer1.put(arrayOfByte, 0, j);
      i += 1;
    }
    return localByteBuffer1.array();
  }
  
  public byte[] generateKey(KeyPurpose paramKeyPurpose, byte[] paramArrayOfByte)
  {
    return deriveSP800108HmacSHA256Key(paramKeyPurpose.getKeyLengthBytes(), this._sessionKey, paramKeyPurpose.getLabel(), paramArrayOfByte);
  }
  
  public static enum KeyPurpose
  {
    CredentialSignature(32, "WS-SecureConversation"),  STSDigest(32, "WS-SecureConversationWS-SecureConversation");
    
    private final int _keyLengthBytes;
    private final String _label;
    
    private KeyPurpose(int paramInt, String paramString)
    {
      this._keyLengthBytes = paramInt;
      this._label = paramString;
    }
    
    int getKeyLengthBytes()
    {
      return this._keyLengthBytes;
    }
    
    String getLabel()
    {
      return this._label;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\SharedKeyGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */