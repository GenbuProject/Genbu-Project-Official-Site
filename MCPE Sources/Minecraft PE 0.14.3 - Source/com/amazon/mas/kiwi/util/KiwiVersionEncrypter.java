package com.amazon.mas.kiwi.util;

import java.io.IOException;
import java.io.PrintStream;

public class KiwiVersionEncrypter
{
  private static final String SECRET_KEY = "Kiwi__Version__Obfuscator";
  
  private static void checkInput(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.isEmpty())) {
      throw new IllegalArgumentException("input '" + paramString2 + "' cannot be null or empty");
    }
  }
  
  public static String decrypt(String paramString)
    throws IOException
  {
    checkInput(paramString, "text");
    return new String(performXOR(Base64.decode(paramString.getBytes())), "UTF-8");
  }
  
  /* Error */
  public static String decryptFromFile(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 71
    //   3: invokestatic 49	com/amazon/mas/kiwi/util/KiwiVersionEncrypter:checkInput	(Ljava/lang/String;Ljava/lang/String;)V
    //   6: aconst_null
    //   7: astore_1
    //   8: new 73	java/io/BufferedReader
    //   11: dup
    //   12: new 75	java/io/FileReader
    //   15: dup
    //   16: aload_0
    //   17: invokespecial 76	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   20: invokespecial 79	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   23: astore_0
    //   24: aload_0
    //   25: invokevirtual 82	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore_1
    //   29: aload_0
    //   30: ifnull +7 -> 37
    //   33: aload_0
    //   34: invokevirtual 85	java/io/BufferedReader:close	()V
    //   37: new 17	java/lang/String
    //   40: dup
    //   41: aload_1
    //   42: invokevirtual 53	java/lang/String:getBytes	()[B
    //   45: invokestatic 59	com/amazon/mas/kiwi/util/Base64:decode	([B)[B
    //   48: invokestatic 62	com/amazon/mas/kiwi/util/KiwiVersionEncrypter:performXOR	([B)[B
    //   51: ldc 64
    //   53: invokespecial 67	java/lang/String:<init>	([BLjava/lang/String;)V
    //   56: areturn
    //   57: astore_0
    //   58: aload_1
    //   59: ifnull +7 -> 66
    //   62: aload_1
    //   63: invokevirtual 85	java/io/BufferedReader:close	()V
    //   66: aload_0
    //   67: athrow
    //   68: astore_0
    //   69: goto -32 -> 37
    //   72: astore_1
    //   73: goto -7 -> 66
    //   76: astore_2
    //   77: aload_0
    //   78: astore_1
    //   79: aload_2
    //   80: astore_0
    //   81: goto -23 -> 58
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	paramString	String
    //   7	56	1	str1	String
    //   72	1	1	localIOException	IOException
    //   78	1	1	str2	String
    //   76	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   8	24	57	finally
    //   33	37	68	java/io/IOException
    //   62	66	72	java/io/IOException
    //   24	29	76	finally
  }
  
  public static String encrypt(String paramString)
  {
    checkInput(paramString, "text");
    return Base64.encodeBytes(performXOR(paramString.getBytes()));
  }
  
  /* Error */
  public static void encryptToFile(String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 47
    //   3: invokestatic 49	com/amazon/mas/kiwi/util/KiwiVersionEncrypter:checkInput	(Ljava/lang/String;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 71
    //   9: invokestatic 49	com/amazon/mas/kiwi/util/KiwiVersionEncrypter:checkInput	(Ljava/lang/String;Ljava/lang/String;)V
    //   12: aload_0
    //   13: invokevirtual 53	java/lang/String:getBytes	()[B
    //   16: invokestatic 62	com/amazon/mas/kiwi/util/KiwiVersionEncrypter:performXOR	([B)[B
    //   19: invokestatic 90	com/amazon/mas/kiwi/util/Base64:encodeBytes	([B)Ljava/lang/String;
    //   22: astore_0
    //   23: aconst_null
    //   24: astore_2
    //   25: new 93	java/io/BufferedWriter
    //   28: dup
    //   29: new 95	java/io/FileWriter
    //   32: dup
    //   33: aload_1
    //   34: invokespecial 96	java/io/FileWriter:<init>	(Ljava/lang/String;)V
    //   37: invokespecial 99	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   40: astore_1
    //   41: aload_1
    //   42: aload_0
    //   43: invokevirtual 102	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   46: aload_1
    //   47: invokevirtual 105	java/io/BufferedWriter:flush	()V
    //   50: aload_1
    //   51: ifnull +7 -> 58
    //   54: aload_1
    //   55: invokevirtual 106	java/io/BufferedWriter:close	()V
    //   58: return
    //   59: astore_0
    //   60: aload_2
    //   61: astore_1
    //   62: aload_1
    //   63: ifnull +7 -> 70
    //   66: aload_1
    //   67: invokevirtual 106	java/io/BufferedWriter:close	()V
    //   70: aload_0
    //   71: athrow
    //   72: astore_0
    //   73: return
    //   74: astore_1
    //   75: goto -5 -> 70
    //   78: astore_0
    //   79: goto -17 -> 62
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	paramString1	String
    //   0	82	1	paramString2	String
    //   24	37	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   25	41	59	finally
    //   54	58	72	java/io/IOException
    //   66	70	74	java/io/IOException
    //   41	50	78	finally
  }
  
  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
    {
      System.out.println("Usage: com.amazon.mas.kiwi.util.KiwiVersionEncrypter <textToBeEncrypted> [<encryptToFileName>]");
      return;
    }
    if (paramArrayOfString.length > 1)
    {
      encryptToFile(paramArrayOfString[0], paramArrayOfString[1]);
      return;
    }
    System.out.println(encrypt(paramArrayOfString[0]));
  }
  
  private static byte[] performXOR(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte1 = new byte[paramArrayOfByte.length];
    byte[] arrayOfByte2 = "Kiwi__Version__Obfuscator".getBytes();
    int i = 0;
    int j = 0;
    while (j < paramArrayOfByte.length)
    {
      arrayOfByte1[j] = ((byte)(paramArrayOfByte[j] ^ arrayOfByte2[i]));
      int k = i + 1;
      i = k;
      if (k >= arrayOfByte2.length) {
        i = 0;
      }
      j += 1;
    }
    return arrayOfByte1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\mas\kiwi\util\KiwiVersionEncrypter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */