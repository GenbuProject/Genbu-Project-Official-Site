package com.amazon.android.u;

import com.amazon.android.framework.util.KiwiLogger;

public final class a
{
  private static final KiwiLogger a = new KiwiLogger("Serializer");
  
  /* Error */
  public static Object a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +10 -> 11
    //   4: aload_0
    //   5: invokevirtual 32	java/lang/String:length	()I
    //   8: ifne +5 -> 13
    //   11: aconst_null
    //   12: areturn
    //   13: aload_0
    //   14: invokevirtual 36	java/lang/String:getBytes	()[B
    //   17: invokestatic 42	com/amazon/mas/kiwi/util/Base64:decode	([B)[B
    //   20: astore_1
    //   21: new 44	java/io/ObjectInputStream
    //   24: dup
    //   25: new 46	java/io/ByteArrayInputStream
    //   28: dup
    //   29: aload_1
    //   30: invokespecial 49	java/io/ByteArrayInputStream:<init>	([B)V
    //   33: invokespecial 52	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   36: astore_2
    //   37: aload_2
    //   38: astore_1
    //   39: aload_2
    //   40: invokevirtual 56	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   43: astore_3
    //   44: aload_2
    //   45: invokestatic 60	com/amazon/android/framework/util/a:a	(Ljava/io/InputStream;)V
    //   48: aload_3
    //   49: areturn
    //   50: astore_0
    //   51: getstatic 64	com/amazon/android/framework/util/KiwiLogger:ERROR_ON	Z
    //   54: ifeq +12 -> 66
    //   57: getstatic 18	com/amazon/android/u/a:a	Lcom/amazon/android/framework/util/KiwiLogger;
    //   60: ldc 66
    //   62: aload_0
    //   63: invokevirtual 70	com/amazon/android/framework/util/KiwiLogger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   66: aconst_null
    //   67: areturn
    //   68: astore_3
    //   69: aconst_null
    //   70: astore_2
    //   71: aload_2
    //   72: astore_1
    //   73: getstatic 64	com/amazon/android/framework/util/KiwiLogger:ERROR_ON	Z
    //   76: ifeq +31 -> 107
    //   79: aload_2
    //   80: astore_1
    //   81: getstatic 18	com/amazon/android/u/a:a	Lcom/amazon/android/framework/util/KiwiLogger;
    //   84: new 72	java/lang/StringBuilder
    //   87: dup
    //   88: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   91: ldc 75
    //   93: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: aload_0
    //   97: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: aload_3
    //   104: invokevirtual 70	com/amazon/android/framework/util/KiwiLogger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   107: aload_2
    //   108: invokestatic 60	com/amazon/android/framework/util/a:a	(Ljava/io/InputStream;)V
    //   111: aconst_null
    //   112: areturn
    //   113: astore_0
    //   114: aconst_null
    //   115: astore_1
    //   116: aload_1
    //   117: invokestatic 60	com/amazon/android/framework/util/a:a	(Ljava/io/InputStream;)V
    //   120: aload_0
    //   121: athrow
    //   122: astore_0
    //   123: goto -7 -> 116
    //   126: astore_3
    //   127: goto -56 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	130	0	paramString	String
    //   20	97	1	localObject1	Object
    //   36	72	2	localObjectInputStream	java.io.ObjectInputStream
    //   43	6	3	localObject2	Object
    //   68	36	3	localException1	Exception
    //   126	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   13	21	50	java/io/IOException
    //   21	37	68	java/lang/Exception
    //   21	37	113	finally
    //   39	44	122	finally
    //   73	79	122	finally
    //   81	107	122	finally
    //   39	44	126	java/lang/Exception
  }
  
  /* Error */
  public static String a(java.io.Serializable paramSerializable)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: new 86	java/io/ByteArrayOutputStream
    //   9: dup
    //   10: invokespecial 87	java/io/ByteArrayOutputStream:<init>	()V
    //   13: astore_3
    //   14: new 89	java/io/ObjectOutputStream
    //   17: dup
    //   18: aload_3
    //   19: invokespecial 92	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   22: astore_2
    //   23: aload_2
    //   24: astore_1
    //   25: aload_2
    //   26: aload_0
    //   27: invokevirtual 96	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   30: aload_2
    //   31: astore_1
    //   32: aload_3
    //   33: invokevirtual 99	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   36: invokestatic 103	com/amazon/mas/kiwi/util/Base64:encodeBytes	([B)Ljava/lang/String;
    //   39: astore_3
    //   40: aload_2
    //   41: invokestatic 105	com/amazon/android/framework/util/a:a	(Ljava/io/OutputStream;)V
    //   44: aload_3
    //   45: areturn
    //   46: astore_3
    //   47: aconst_null
    //   48: astore_2
    //   49: aload_2
    //   50: astore_1
    //   51: getstatic 64	com/amazon/android/framework/util/KiwiLogger:ERROR_ON	Z
    //   54: ifeq +31 -> 85
    //   57: aload_2
    //   58: astore_1
    //   59: getstatic 18	com/amazon/android/u/a:a	Lcom/amazon/android/framework/util/KiwiLogger;
    //   62: new 72	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   69: ldc 107
    //   71: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: aload_0
    //   75: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   78: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: aload_3
    //   82: invokevirtual 70	com/amazon/android/framework/util/KiwiLogger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   85: aload_2
    //   86: invokestatic 105	com/amazon/android/framework/util/a:a	(Ljava/io/OutputStream;)V
    //   89: aconst_null
    //   90: areturn
    //   91: astore_0
    //   92: aconst_null
    //   93: astore_1
    //   94: aload_1
    //   95: invokestatic 105	com/amazon/android/framework/util/a:a	(Ljava/io/OutputStream;)V
    //   98: aload_0
    //   99: athrow
    //   100: astore_0
    //   101: goto -7 -> 94
    //   104: astore_3
    //   105: goto -56 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	paramSerializable	java.io.Serializable
    //   24	71	1	localObjectOutputStream1	java.io.ObjectOutputStream
    //   22	64	2	localObjectOutputStream2	java.io.ObjectOutputStream
    //   13	32	3	localObject	Object
    //   46	36	3	localIOException1	java.io.IOException
    //   104	1	3	localIOException2	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   14	23	46	java/io/IOException
    //   14	23	91	finally
    //   25	30	100	finally
    //   32	40	100	finally
    //   51	57	100	finally
    //   59	85	100	finally
    //   25	30	104	java/io/IOException
    //   32	40	104	java/io/IOException
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\u\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */