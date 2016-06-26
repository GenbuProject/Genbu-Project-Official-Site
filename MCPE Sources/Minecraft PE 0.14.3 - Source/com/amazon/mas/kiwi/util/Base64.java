package com.amazon.mas.kiwi.util;

import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class Base64
{
  public static final int DECODE = 0;
  public static final int DONT_GUNZIP = 4;
  public static final int DO_BREAK_LINES = 8;
  public static final int ENCODE = 1;
  private static final byte EQUALS_SIGN = 61;
  private static final byte EQUALS_SIGN_ENC = -1;
  public static final int GZIP = 2;
  private static final int MAX_LINE_LENGTH = 76;
  private static final byte NEW_LINE = 10;
  public static final int NO_OPTIONS = 0;
  public static final int ORDERED = 32;
  private static final String PREFERRED_ENCODING = "US-ASCII";
  public static final int URL_SAFE = 16;
  private static final byte WHITE_SPACE_ENC = -5;
  private static final byte[] _ORDERED_ALPHABET;
  private static final byte[] _ORDERED_DECODABET;
  private static final byte[] _STANDARD_ALPHABET;
  private static final byte[] _STANDARD_DECODABET;
  private static final byte[] _URL_SAFE_ALPHABET;
  private static final byte[] _URL_SAFE_DECODABET;
  
  static
  {
    if (!Base64.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      _STANDARD_ALPHABET = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
      _STANDARD_DECODABET = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
      _URL_SAFE_ALPHABET = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
      _URL_SAFE_DECODABET = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
      _ORDERED_ALPHABET = new byte[] { 45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
      _ORDERED_DECODABET = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
      return;
    }
  }
  
  public static byte[] decode(String paramString)
    throws IOException
  {
    return decode(paramString, 0);
  }
  
  /* Error */
  public static byte[] decode(String paramString, int paramInt)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 217	java/lang/NullPointerException
    //   7: dup
    //   8: ldc -37
    //   10: invokespecial 222	java/lang/NullPointerException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_0
    //   15: ldc 39
    //   17: invokevirtual 227	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   20: astore 4
    //   22: aload 4
    //   24: astore_0
    //   25: aload_0
    //   26: iconst_0
    //   27: aload_0
    //   28: arraylength
    //   29: iload_1
    //   30: invokestatic 230	com/amazon/mas/kiwi/util/Base64:decode	([BIII)[B
    //   33: astore 9
    //   35: iload_1
    //   36: iconst_4
    //   37: iand
    //   38: ifeq +181 -> 219
    //   41: iconst_1
    //   42: istore_1
    //   43: aload 9
    //   45: ifnull +390 -> 435
    //   48: aload 9
    //   50: arraylength
    //   51: iconst_4
    //   52: if_icmplt +383 -> 435
    //   55: iload_1
    //   56: ifne +379 -> 435
    //   59: ldc -25
    //   61: aload 9
    //   63: iconst_0
    //   64: baload
    //   65: sipush 255
    //   68: iand
    //   69: aload 9
    //   71: iconst_1
    //   72: baload
    //   73: bipush 8
    //   75: ishl
    //   76: ldc -24
    //   78: iand
    //   79: ior
    //   80: if_icmpne +355 -> 435
    //   83: aconst_null
    //   84: astore 6
    //   86: aconst_null
    //   87: astore 7
    //   89: aconst_null
    //   90: astore 8
    //   92: aconst_null
    //   93: astore 5
    //   95: sipush 2048
    //   98: newarray <illegal type>
    //   100: astore 10
    //   102: new 234	java/io/ByteArrayOutputStream
    //   105: dup
    //   106: invokespecial 235	java/io/ByteArrayOutputStream:<init>	()V
    //   109: astore_0
    //   110: new 237	java/io/ByteArrayInputStream
    //   113: dup
    //   114: aload 9
    //   116: invokespecial 240	java/io/ByteArrayInputStream:<init>	([B)V
    //   119: astore 4
    //   121: new 242	java/util/zip/GZIPInputStream
    //   124: dup
    //   125: aload 4
    //   127: invokespecial 245	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   130: astore 5
    //   132: iconst_0
    //   133: istore_1
    //   134: iload_1
    //   135: istore_2
    //   136: iload_1
    //   137: istore_3
    //   138: aload 5
    //   140: aload 10
    //   142: invokevirtual 249	java/util/zip/GZIPInputStream:read	([B)I
    //   145: istore_1
    //   146: iload_1
    //   147: iflt +77 -> 224
    //   150: iload_1
    //   151: istore_2
    //   152: iload_1
    //   153: istore_3
    //   154: aload_0
    //   155: aload 10
    //   157: iconst_0
    //   158: iload_1
    //   159: invokevirtual 253	java/io/ByteArrayOutputStream:write	([BII)V
    //   162: goto -28 -> 134
    //   165: astore 7
    //   167: iload_2
    //   168: istore_1
    //   169: aload 5
    //   171: astore 8
    //   173: aload_0
    //   174: astore 5
    //   176: aload 4
    //   178: astore 6
    //   180: aload 8
    //   182: astore_0
    //   183: aload 7
    //   185: astore 4
    //   187: aload 4
    //   189: invokevirtual 256	java/io/IOException:printStackTrace	()V
    //   192: aload 5
    //   194: invokevirtual 259	java/io/ByteArrayOutputStream:close	()V
    //   197: aload_0
    //   198: invokevirtual 260	java/util/zip/GZIPInputStream:close	()V
    //   201: aload 6
    //   203: invokevirtual 261	java/io/ByteArrayInputStream:close	()V
    //   206: aload 9
    //   208: areturn
    //   209: astore 4
    //   211: aload_0
    //   212: invokevirtual 264	java/lang/String:getBytes	()[B
    //   215: astore_0
    //   216: goto -191 -> 25
    //   219: iconst_0
    //   220: istore_1
    //   221: goto -178 -> 43
    //   224: iload_1
    //   225: istore_2
    //   226: iload_1
    //   227: istore_3
    //   228: aload_0
    //   229: invokevirtual 267	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   232: astore 6
    //   234: aload_0
    //   235: invokevirtual 259	java/io/ByteArrayOutputStream:close	()V
    //   238: aload 5
    //   240: invokevirtual 260	java/util/zip/GZIPInputStream:close	()V
    //   243: aload 4
    //   245: invokevirtual 261	java/io/ByteArrayInputStream:close	()V
    //   248: aload 6
    //   250: areturn
    //   251: astore_0
    //   252: aload 6
    //   254: areturn
    //   255: astore_0
    //   256: aload 9
    //   258: areturn
    //   259: astore 4
    //   261: aconst_null
    //   262: astore_0
    //   263: aload 8
    //   265: astore 5
    //   267: aload 5
    //   269: invokevirtual 259	java/io/ByteArrayOutputStream:close	()V
    //   272: aload_0
    //   273: invokevirtual 260	java/util/zip/GZIPInputStream:close	()V
    //   276: aload 6
    //   278: invokevirtual 261	java/io/ByteArrayInputStream:close	()V
    //   281: aload 4
    //   283: athrow
    //   284: astore_0
    //   285: goto -47 -> 238
    //   288: astore_0
    //   289: goto -46 -> 243
    //   292: astore 4
    //   294: goto -97 -> 197
    //   297: astore_0
    //   298: goto -97 -> 201
    //   301: astore 5
    //   303: goto -31 -> 272
    //   306: astore_0
    //   307: goto -31 -> 276
    //   310: astore_0
    //   311: goto -30 -> 281
    //   314: astore 4
    //   316: aload_0
    //   317: astore 5
    //   319: aconst_null
    //   320: astore_0
    //   321: goto -54 -> 267
    //   324: astore 5
    //   326: aload 4
    //   328: astore 6
    //   330: aload_0
    //   331: astore 7
    //   333: aconst_null
    //   334: astore_0
    //   335: aload 5
    //   337: astore 4
    //   339: aload 7
    //   341: astore 5
    //   343: goto -76 -> 267
    //   346: astore 6
    //   348: aload 4
    //   350: astore 7
    //   352: aload_0
    //   353: astore 8
    //   355: aload 5
    //   357: astore_0
    //   358: aload 6
    //   360: astore 4
    //   362: aload 7
    //   364: astore 6
    //   366: aload 8
    //   368: astore 5
    //   370: goto -103 -> 267
    //   373: astore 4
    //   375: goto -108 -> 267
    //   378: astore 4
    //   380: aconst_null
    //   381: astore_0
    //   382: iconst_0
    //   383: istore_1
    //   384: aload 7
    //   386: astore 6
    //   388: goto -201 -> 187
    //   391: astore 4
    //   393: aconst_null
    //   394: astore 6
    //   396: iconst_0
    //   397: istore_1
    //   398: aload_0
    //   399: astore 5
    //   401: aload 6
    //   403: astore_0
    //   404: aload 7
    //   406: astore 6
    //   408: goto -221 -> 187
    //   411: astore 7
    //   413: aconst_null
    //   414: astore 8
    //   416: iconst_0
    //   417: istore_1
    //   418: aload 4
    //   420: astore 6
    //   422: aload_0
    //   423: astore 5
    //   425: aload 7
    //   427: astore 4
    //   429: aload 8
    //   431: astore_0
    //   432: goto -245 -> 187
    //   435: aload 9
    //   437: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	438	0	paramString	String
    //   0	438	1	paramInt	int
    //   135	91	2	i	int
    //   137	91	3	j	int
    //   20	168	4	localObject1	Object
    //   209	35	4	localUnsupportedEncodingException	UnsupportedEncodingException
    //   259	23	4	localObject2	Object
    //   292	1	4	localException1	Exception
    //   314	13	4	localObject3	Object
    //   337	24	4	localObject4	Object
    //   373	1	4	localObject5	Object
    //   378	1	4	localIOException1	IOException
    //   391	28	4	localIOException2	IOException
    //   427	1	4	localObject6	Object
    //   93	175	5	localObject7	Object
    //   301	1	5	localException2	Exception
    //   317	1	5	str	String
    //   324	12	5	localObject8	Object
    //   341	83	5	localObject9	Object
    //   84	245	6	localObject10	Object
    //   346	13	6	localObject11	Object
    //   364	57	6	localObject12	Object
    //   87	1	7	localObject13	Object
    //   165	19	7	localIOException3	IOException
    //   331	74	7	localObject14	Object
    //   411	15	7	localIOException4	IOException
    //   90	340	8	localObject15	Object
    //   33	403	9	arrayOfByte1	byte[]
    //   100	56	10	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   138	146	165	java/io/IOException
    //   154	162	165	java/io/IOException
    //   228	234	165	java/io/IOException
    //   14	22	209	java/io/UnsupportedEncodingException
    //   243	248	251	java/lang/Exception
    //   201	206	255	java/lang/Exception
    //   102	110	259	finally
    //   234	238	284	java/lang/Exception
    //   238	243	288	java/lang/Exception
    //   192	197	292	java/lang/Exception
    //   197	201	297	java/lang/Exception
    //   267	272	301	java/lang/Exception
    //   272	276	306	java/lang/Exception
    //   276	281	310	java/lang/Exception
    //   110	121	314	finally
    //   121	132	324	finally
    //   138	146	346	finally
    //   154	162	346	finally
    //   228	234	346	finally
    //   187	192	373	finally
    //   102	110	378	java/io/IOException
    //   110	121	391	java/io/IOException
    //   121	132	411	java/io/IOException
  }
  
  public static byte[] decode(byte[] paramArrayOfByte)
    throws IOException
  {
    return decode(paramArrayOfByte, 0, paramArrayOfByte.length, 0);
  }
  
  public static byte[] decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("Cannot decode null source array.");
    }
    if ((paramInt1 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length)) {
      throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", new Object[] { Integer.valueOf(paramArrayOfByte.length), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
    }
    if (paramInt2 == 0) {
      return new byte[0];
    }
    if (paramInt2 < 4) {
      throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + paramInt2);
    }
    byte[] arrayOfByte2 = getDecodabet(paramInt3);
    byte[] arrayOfByte1 = new byte[paramInt2 * 3 / 4];
    byte[] arrayOfByte3 = new byte[4];
    int j = 0;
    int k = paramInt1;
    int i = 0;
    int n = 0;
    int m;
    if (k < paramInt1 + paramInt2)
    {
      n = arrayOfByte2[(paramArrayOfByte[k] & 0xFF)];
      if (n >= -5)
      {
        if (n < -1) {
          break label309;
        }
        int i1 = i + 1;
        arrayOfByte3[i] = paramArrayOfByte[k];
        i = i1;
        m = j;
        if (i1 <= 3) {
          break label296;
        }
        i = j + decode4to3(arrayOfByte3, 0, arrayOfByte1, j, paramInt3);
        j = 0;
        if (paramArrayOfByte[k] != 61) {
          break label288;
        }
      }
    }
    for (;;)
    {
      paramArrayOfByte = new byte[i];
      System.arraycopy(arrayOfByte1, 0, paramArrayOfByte, 0, i);
      return paramArrayOfByte;
      throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", new Object[] { Integer.valueOf(paramArrayOfByte[k] & 0xFF), Integer.valueOf(k) }));
      label288:
      m = i;
      i = j;
      for (;;)
      {
        label296:
        k += 1;
        j = m;
        break;
        label309:
        m = j;
      }
      i = j;
    }
  }
  
  private static int decode4to3(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3)
  {
    if (paramArrayOfByte1 == null) {
      throw new NullPointerException("Source array was null.");
    }
    if (paramArrayOfByte2 == null) {
      throw new NullPointerException("Destination array was null.");
    }
    if ((paramInt1 < 0) || (paramInt1 + 3 >= paramArrayOfByte1.length)) {
      throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", new Object[] { Integer.valueOf(paramArrayOfByte1.length), Integer.valueOf(paramInt1) }));
    }
    if ((paramInt2 < 0) || (paramInt2 + 2 >= paramArrayOfByte2.length)) {
      throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", new Object[] { Integer.valueOf(paramArrayOfByte2.length), Integer.valueOf(paramInt2) }));
    }
    byte[] arrayOfByte = getDecodabet(paramInt3);
    if (paramArrayOfByte1[(paramInt1 + 2)] == 61)
    {
      paramArrayOfByte2[paramInt2] = ((byte)(((arrayOfByte[paramArrayOfByte1[paramInt1]] & 0xFF) << 18 | (arrayOfByte[paramArrayOfByte1[(paramInt1 + 1)]] & 0xFF) << 12) >>> 16));
      return 1;
    }
    if (paramArrayOfByte1[(paramInt1 + 3)] == 61)
    {
      paramInt1 = (arrayOfByte[paramArrayOfByte1[paramInt1]] & 0xFF) << 18 | (arrayOfByte[paramArrayOfByte1[(paramInt1 + 1)]] & 0xFF) << 12 | (arrayOfByte[paramArrayOfByte1[(paramInt1 + 2)]] & 0xFF) << 6;
      paramArrayOfByte2[paramInt2] = ((byte)(paramInt1 >>> 16));
      paramArrayOfByte2[(paramInt2 + 1)] = ((byte)(paramInt1 >>> 8));
      return 2;
    }
    paramInt1 = (arrayOfByte[paramArrayOfByte1[paramInt1]] & 0xFF) << 18 | (arrayOfByte[paramArrayOfByte1[(paramInt1 + 1)]] & 0xFF) << 12 | (arrayOfByte[paramArrayOfByte1[(paramInt1 + 2)]] & 0xFF) << 6 | arrayOfByte[paramArrayOfByte1[(paramInt1 + 3)]] & 0xFF;
    paramArrayOfByte2[paramInt2] = ((byte)(paramInt1 >> 16));
    paramArrayOfByte2[(paramInt2 + 1)] = ((byte)(paramInt1 >> 8));
    paramArrayOfByte2[(paramInt2 + 2)] = ((byte)paramInt1);
    return 3;
  }
  
  /* Error */
  public static void decodeFileToFile(String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 323	com/amazon/mas/kiwi/util/Base64:decodeFromFile	(Ljava/lang/String;)[B
    //   4: astore_3
    //   5: aconst_null
    //   6: astore_0
    //   7: aconst_null
    //   8: astore_2
    //   9: new 325	java/io/BufferedOutputStream
    //   12: dup
    //   13: new 327	java/io/FileOutputStream
    //   16: dup
    //   17: aload_1
    //   18: invokespecial 328	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   21: invokespecial 331	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   24: astore_1
    //   25: aload_1
    //   26: aload_3
    //   27: invokevirtual 335	java/io/OutputStream:write	([B)V
    //   30: aload_1
    //   31: invokevirtual 336	java/io/OutputStream:close	()V
    //   34: return
    //   35: astore_1
    //   36: aload_2
    //   37: astore_0
    //   38: aload_1
    //   39: athrow
    //   40: astore_1
    //   41: aload_0
    //   42: invokevirtual 336	java/io/OutputStream:close	()V
    //   45: aload_1
    //   46: athrow
    //   47: astore_0
    //   48: return
    //   49: astore_0
    //   50: goto -5 -> 45
    //   53: astore_2
    //   54: aload_1
    //   55: astore_0
    //   56: aload_2
    //   57: astore_1
    //   58: goto -17 -> 41
    //   61: astore_0
    //   62: aload_1
    //   63: astore_2
    //   64: aload_0
    //   65: astore_1
    //   66: aload_2
    //   67: astore_0
    //   68: goto -30 -> 38
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	71	0	paramString1	String
    //   0	71	1	paramString2	String
    //   8	29	2	localObject1	Object
    //   53	4	2	localObject2	Object
    //   63	4	2	str	String
    //   4	23	3	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   9	25	35	java/io/IOException
    //   9	25	40	finally
    //   38	40	40	finally
    //   30	34	47	java/lang/Exception
    //   41	45	49	java/lang/Exception
    //   25	30	53	finally
    //   25	30	61	java/io/IOException
  }
  
  /* Error */
  public static byte[] decodeFromFile(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: aload 5
    //   8: astore_3
    //   9: new 338	java/io/File
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 339	java/io/File:<init>	(Ljava/lang/String;)V
    //   17: astore_0
    //   18: iconst_0
    //   19: istore_1
    //   20: aload 5
    //   22: astore_3
    //   23: aload_0
    //   24: invokevirtual 343	java/io/File:length	()J
    //   27: ldc2_w 344
    //   30: lcmp
    //   31: ifle +56 -> 87
    //   34: aload 5
    //   36: astore_3
    //   37: new 207	java/io/IOException
    //   40: dup
    //   41: new 287	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 288	java/lang/StringBuilder:<init>	()V
    //   48: ldc_w 347
    //   51: invokevirtual 294	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: aload_0
    //   55: invokevirtual 343	java/io/File:length	()J
    //   58: invokevirtual 350	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   61: ldc_w 352
    //   64: invokevirtual 294	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: invokevirtual 301	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokespecial 310	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   73: athrow
    //   74: astore_0
    //   75: aload 4
    //   77: astore_3
    //   78: aload_0
    //   79: athrow
    //   80: astore_0
    //   81: aload_3
    //   82: invokevirtual 353	com/amazon/mas/kiwi/util/Base64$InputStream:close	()V
    //   85: aload_0
    //   86: athrow
    //   87: aload 5
    //   89: astore_3
    //   90: aload_0
    //   91: invokevirtual 343	java/io/File:length	()J
    //   94: l2i
    //   95: newarray <illegal type>
    //   97: astore 6
    //   99: aload 5
    //   101: astore_3
    //   102: new 6	com/amazon/mas/kiwi/util/Base64$InputStream
    //   105: dup
    //   106: new 355	java/io/BufferedInputStream
    //   109: dup
    //   110: new 357	java/io/FileInputStream
    //   113: dup
    //   114: aload_0
    //   115: invokespecial 360	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   118: invokespecial 361	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   121: iconst_0
    //   122: invokespecial 364	com/amazon/mas/kiwi/util/Base64$InputStream:<init>	(Ljava/io/InputStream;I)V
    //   125: astore_0
    //   126: aload_0
    //   127: aload 6
    //   129: iload_1
    //   130: sipush 4096
    //   133: invokevirtual 367	com/amazon/mas/kiwi/util/Base64$InputStream:read	([BII)I
    //   136: istore_2
    //   137: iload_2
    //   138: iflt +10 -> 148
    //   141: iload_1
    //   142: iload_2
    //   143: iadd
    //   144: istore_1
    //   145: goto -19 -> 126
    //   148: iload_1
    //   149: newarray <illegal type>
    //   151: astore_3
    //   152: aload 6
    //   154: iconst_0
    //   155: aload_3
    //   156: iconst_0
    //   157: iload_1
    //   158: invokestatic 307	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   161: aload_0
    //   162: invokevirtual 353	com/amazon/mas/kiwi/util/Base64$InputStream:close	()V
    //   165: aload_3
    //   166: areturn
    //   167: astore_0
    //   168: aload_3
    //   169: areturn
    //   170: astore_3
    //   171: goto -86 -> 85
    //   174: astore 4
    //   176: aload_0
    //   177: astore_3
    //   178: aload 4
    //   180: astore_0
    //   181: goto -100 -> 81
    //   184: astore 4
    //   186: aload_0
    //   187: astore_3
    //   188: aload 4
    //   190: astore_0
    //   191: goto -113 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	194	0	paramString	String
    //   19	139	1	i	int
    //   136	8	2	j	int
    //   8	161	3	localObject1	Object
    //   170	1	3	localException	Exception
    //   177	11	3	str	String
    //   4	72	4	localObject2	Object
    //   174	5	4	localObject3	Object
    //   184	5	4	localIOException	IOException
    //   1	99	5	localObject4	Object
    //   97	56	6	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   9	18	74	java/io/IOException
    //   23	34	74	java/io/IOException
    //   37	74	74	java/io/IOException
    //   90	99	74	java/io/IOException
    //   102	126	74	java/io/IOException
    //   9	18	80	finally
    //   23	34	80	finally
    //   37	74	80	finally
    //   78	80	80	finally
    //   90	99	80	finally
    //   102	126	80	finally
    //   161	165	167	java/lang/Exception
    //   81	85	170	java/lang/Exception
    //   126	137	174	finally
    //   148	161	174	finally
    //   126	137	184	java/io/IOException
    //   148	161	184	java/io/IOException
  }
  
  /* Error */
  public static void decodeToFile(String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: new 9	com/amazon/mas/kiwi/util/Base64$OutputStream
    //   7: dup
    //   8: new 327	java/io/FileOutputStream
    //   11: dup
    //   12: aload_1
    //   13: invokespecial 328	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   16: iconst_0
    //   17: invokespecial 371	com/amazon/mas/kiwi/util/Base64$OutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   20: astore_1
    //   21: aload_1
    //   22: aload_0
    //   23: ldc 39
    //   25: invokevirtual 227	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   28: invokevirtual 372	com/amazon/mas/kiwi/util/Base64$OutputStream:write	([B)V
    //   31: aload_1
    //   32: invokevirtual 373	com/amazon/mas/kiwi/util/Base64$OutputStream:close	()V
    //   35: return
    //   36: astore_0
    //   37: aload_3
    //   38: astore_2
    //   39: aload_0
    //   40: athrow
    //   41: astore_0
    //   42: aload_2
    //   43: invokevirtual 373	com/amazon/mas/kiwi/util/Base64$OutputStream:close	()V
    //   46: aload_0
    //   47: athrow
    //   48: astore_0
    //   49: return
    //   50: astore_1
    //   51: goto -5 -> 46
    //   54: astore_0
    //   55: aload_1
    //   56: astore_2
    //   57: goto -15 -> 42
    //   60: astore_0
    //   61: aload_1
    //   62: astore_2
    //   63: goto -24 -> 39
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	paramString1	String
    //   0	66	1	paramString2	String
    //   1	62	2	localObject1	Object
    //   3	35	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   4	21	36	java/io/IOException
    //   4	21	41	finally
    //   39	41	41	finally
    //   31	35	48	java/lang/Exception
    //   42	46	50	java/lang/Exception
    //   21	31	54	finally
    //   21	31	60	java/io/IOException
  }
  
  public static void encode(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2)
  {
    byte[] arrayOfByte1 = new byte[3];
    byte[] arrayOfByte2 = new byte[4];
    while (paramByteBuffer1.hasRemaining())
    {
      int i = Math.min(3, paramByteBuffer1.remaining());
      paramByteBuffer1.get(arrayOfByte1, 0, i);
      encode3to4(arrayOfByte2, arrayOfByte1, i, 0);
      paramByteBuffer2.put(arrayOfByte2);
    }
  }
  
  public static void encode(ByteBuffer paramByteBuffer, CharBuffer paramCharBuffer)
  {
    byte[] arrayOfByte1 = new byte[3];
    byte[] arrayOfByte2 = new byte[4];
    while (paramByteBuffer.hasRemaining())
    {
      int i = Math.min(3, paramByteBuffer.remaining());
      paramByteBuffer.get(arrayOfByte1, 0, i);
      encode3to4(arrayOfByte2, arrayOfByte1, i, 0);
      i = 0;
      while (i < 4)
      {
        paramCharBuffer.put((char)(arrayOfByte2[i] & 0xFF));
        i += 1;
      }
    }
  }
  
  private static byte[] encode3to4(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3, int paramInt4)
  {
    byte[] arrayOfByte = getAlphabet(paramInt4);
    label22:
    int i;
    if (paramInt2 > 0)
    {
      paramInt4 = paramArrayOfByte1[paramInt1] << 24 >>> 8;
      if (paramInt2 <= 1) {
        break label100;
      }
      i = paramArrayOfByte1[(paramInt1 + 1)] << 24 >>> 16;
      label40:
      if (paramInt2 <= 2) {
        break label106;
      }
    }
    label100:
    label106:
    for (paramInt1 = paramArrayOfByte1[(paramInt1 + 2)] << 24 >>> 24;; paramInt1 = 0)
    {
      paramInt1 = paramInt4 | i | paramInt1;
      switch (paramInt2)
      {
      default: 
        return paramArrayOfByte2;
        paramInt4 = 0;
        break label22;
        i = 0;
        break label40;
      }
    }
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = arrayOfByte[(paramInt1 >>> 6 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 3)] = arrayOfByte[(paramInt1 & 0x3F)];
    return paramArrayOfByte2;
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = arrayOfByte[(paramInt1 >>> 6 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 3)] = 61;
    return paramArrayOfByte2;
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = 61;
    paramArrayOfByte2[(paramInt3 + 3)] = 61;
    return paramArrayOfByte2;
  }
  
  private static byte[] encode3to4(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2)
  {
    encode3to4(paramArrayOfByte2, 0, paramInt1, paramArrayOfByte1, 0, paramInt2);
    return paramArrayOfByte1;
  }
  
  public static String encodeBytes(byte[] paramArrayOfByte)
  {
    Object localObject = null;
    try
    {
      paramArrayOfByte = encodeBytes(paramArrayOfByte, 0, paramArrayOfByte.length, 0);
      if ((!$assertionsDisabled) && (paramArrayOfByte == null)) {
        throw new AssertionError();
      }
    }
    catch (IOException localIOException)
    {
      do
      {
        paramArrayOfByte = (byte[])localObject;
      } while ($assertionsDisabled);
      throw new AssertionError(localIOException.getMessage());
    }
    return paramArrayOfByte;
  }
  
  public static String encodeBytes(byte[] paramArrayOfByte, int paramInt)
    throws IOException
  {
    return encodeBytes(paramArrayOfByte, 0, paramArrayOfByte.length, paramInt);
  }
  
  public static String encodeBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Object localObject = null;
    try
    {
      paramArrayOfByte = encodeBytes(paramArrayOfByte, paramInt1, paramInt2, 0);
      if ((!$assertionsDisabled) && (paramArrayOfByte == null)) {
        throw new AssertionError();
      }
    }
    catch (IOException localIOException)
    {
      do
      {
        paramArrayOfByte = (byte[])localObject;
      } while ($assertionsDisabled);
      throw new AssertionError(localIOException.getMessage());
    }
    return paramArrayOfByte;
  }
  
  public static String encodeBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    paramArrayOfByte = encodeBytesToBytes(paramArrayOfByte, paramInt1, paramInt2, paramInt3);
    try
    {
      String str = new String(paramArrayOfByte, "US-ASCII");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return new String(paramArrayOfByte);
  }
  
  public static byte[] encodeBytesToBytes(byte[] paramArrayOfByte)
  {
    Object localObject = null;
    try
    {
      paramArrayOfByte = encodeBytesToBytes(paramArrayOfByte, 0, paramArrayOfByte.length, 0);
      return paramArrayOfByte;
    }
    catch (IOException localIOException)
    {
      do
      {
        paramArrayOfByte = (byte[])localObject;
      } while ($assertionsDisabled);
      throw new AssertionError("IOExceptions only come from GZipping, which is turned off: " + localIOException.getMessage());
    }
  }
  
  /* Error */
  public static byte[] encodeBytesToBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +14 -> 15
    //   4: new 217	java/lang/NullPointerException
    //   7: dup
    //   8: ldc_w 434
    //   11: invokespecial 222	java/lang/NullPointerException:<init>	(Ljava/lang/String;)V
    //   14: athrow
    //   15: iload_1
    //   16: ifge +31 -> 47
    //   19: new 272	java/lang/IllegalArgumentException
    //   22: dup
    //   23: new 287	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 288	java/lang/StringBuilder:<init>	()V
    //   30: ldc_w 436
    //   33: invokevirtual 294	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: iload_1
    //   37: invokevirtual 297	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   40: invokevirtual 301	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   43: invokespecial 285	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   46: athrow
    //   47: iload_2
    //   48: ifge +31 -> 79
    //   51: new 272	java/lang/IllegalArgumentException
    //   54: dup
    //   55: new 287	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 288	java/lang/StringBuilder:<init>	()V
    //   62: ldc_w 438
    //   65: invokevirtual 294	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: iload_2
    //   69: invokevirtual 297	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   72: invokevirtual 301	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokespecial 285	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   78: athrow
    //   79: iload_1
    //   80: iload_2
    //   81: iadd
    //   82: aload_0
    //   83: arraylength
    //   84: if_icmple +43 -> 127
    //   87: new 272	java/lang/IllegalArgumentException
    //   90: dup
    //   91: ldc_w 440
    //   94: iconst_3
    //   95: anewarray 4	java/lang/Object
    //   98: dup
    //   99: iconst_0
    //   100: iload_1
    //   101: invokestatic 280	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   104: aastore
    //   105: dup
    //   106: iconst_1
    //   107: iload_2
    //   108: invokestatic 280	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   111: aastore
    //   112: dup
    //   113: iconst_2
    //   114: aload_0
    //   115: arraylength
    //   116: invokestatic 280	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   119: aastore
    //   120: invokestatic 284	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   123: invokespecial 285	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   126: athrow
    //   127: iload_3
    //   128: iconst_2
    //   129: iand
    //   130: ifeq +103 -> 233
    //   133: new 234	java/io/ByteArrayOutputStream
    //   136: dup
    //   137: invokespecial 235	java/io/ByteArrayOutputStream:<init>	()V
    //   140: astore 8
    //   142: new 9	com/amazon/mas/kiwi/util/Base64$OutputStream
    //   145: dup
    //   146: aload 8
    //   148: iload_3
    //   149: iconst_1
    //   150: ior
    //   151: invokespecial 371	com/amazon/mas/kiwi/util/Base64$OutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   154: astore 9
    //   156: new 442	java/util/zip/GZIPOutputStream
    //   159: dup
    //   160: aload 9
    //   162: invokespecial 443	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   165: astore 10
    //   167: aload 10
    //   169: aload_0
    //   170: iload_1
    //   171: iload_2
    //   172: invokevirtual 444	java/util/zip/GZIPOutputStream:write	([BII)V
    //   175: aload 10
    //   177: invokevirtual 445	java/util/zip/GZIPOutputStream:close	()V
    //   180: aload 10
    //   182: invokevirtual 445	java/util/zip/GZIPOutputStream:close	()V
    //   185: aload 9
    //   187: invokevirtual 373	com/amazon/mas/kiwi/util/Base64$OutputStream:close	()V
    //   190: aload 8
    //   192: invokevirtual 259	java/io/ByteArrayOutputStream:close	()V
    //   195: aload 8
    //   197: invokevirtual 267	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   200: areturn
    //   201: astore 10
    //   203: aconst_null
    //   204: astore 9
    //   206: aconst_null
    //   207: astore 8
    //   209: aconst_null
    //   210: astore_0
    //   211: aload 10
    //   213: athrow
    //   214: astore 10
    //   216: aload 9
    //   218: invokevirtual 445	java/util/zip/GZIPOutputStream:close	()V
    //   221: aload_0
    //   222: invokevirtual 373	com/amazon/mas/kiwi/util/Base64$OutputStream:close	()V
    //   225: aload 8
    //   227: invokevirtual 259	java/io/ByteArrayOutputStream:close	()V
    //   230: aload 10
    //   232: athrow
    //   233: iload_3
    //   234: bipush 8
    //   236: iand
    //   237: ifeq +137 -> 374
    //   240: iconst_1
    //   241: istore 6
    //   243: iload_2
    //   244: iconst_3
    //   245: idiv
    //   246: istore 5
    //   248: iload_2
    //   249: iconst_3
    //   250: irem
    //   251: ifle +129 -> 380
    //   254: iconst_4
    //   255: istore 4
    //   257: iload 5
    //   259: iconst_4
    //   260: imul
    //   261: iload 4
    //   263: iadd
    //   264: istore 5
    //   266: iload 5
    //   268: istore 4
    //   270: iload 6
    //   272: ifeq +13 -> 285
    //   275: iload 5
    //   277: iload 5
    //   279: bipush 76
    //   281: idiv
    //   282: iadd
    //   283: istore 4
    //   285: iload 4
    //   287: newarray <illegal type>
    //   289: astore 8
    //   291: iconst_0
    //   292: istore 4
    //   294: iconst_0
    //   295: istore 5
    //   297: iconst_0
    //   298: istore 7
    //   300: iload 7
    //   302: iload_2
    //   303: iconst_2
    //   304: isub
    //   305: if_icmpge +81 -> 386
    //   308: aload_0
    //   309: iload 7
    //   311: iload_1
    //   312: iadd
    //   313: iconst_3
    //   314: aload 8
    //   316: iload 4
    //   318: iload_3
    //   319: invokestatic 194	com/amazon/mas/kiwi/util/Base64:encode3to4	([BII[BII)[B
    //   322: pop
    //   323: iload 5
    //   325: iconst_4
    //   326: iadd
    //   327: istore 5
    //   329: iload 6
    //   331: ifeq +241 -> 572
    //   334: iload 5
    //   336: bipush 76
    //   338: if_icmplt +234 -> 572
    //   341: aload 8
    //   343: iload 4
    //   345: iconst_4
    //   346: iadd
    //   347: bipush 10
    //   349: bastore
    //   350: iload 4
    //   352: iconst_1
    //   353: iadd
    //   354: istore 4
    //   356: iconst_0
    //   357: istore 5
    //   359: iload 4
    //   361: iconst_4
    //   362: iadd
    //   363: istore 4
    //   365: iload 7
    //   367: iconst_3
    //   368: iadd
    //   369: istore 7
    //   371: goto -71 -> 300
    //   374: iconst_0
    //   375: istore 6
    //   377: goto -134 -> 243
    //   380: iconst_0
    //   381: istore 4
    //   383: goto -126 -> 257
    //   386: iload 4
    //   388: istore 5
    //   390: iload 7
    //   392: iload_2
    //   393: if_icmpge +27 -> 420
    //   396: aload_0
    //   397: iload 7
    //   399: iload_1
    //   400: iadd
    //   401: iload_2
    //   402: iload 7
    //   404: isub
    //   405: aload 8
    //   407: iload 4
    //   409: iload_3
    //   410: invokestatic 194	com/amazon/mas/kiwi/util/Base64:encode3to4	([BII[BII)[B
    //   413: pop
    //   414: iload 4
    //   416: iconst_4
    //   417: iadd
    //   418: istore 5
    //   420: iload 5
    //   422: aload 8
    //   424: arraylength
    //   425: iconst_1
    //   426: isub
    //   427: if_icmpgt +20 -> 447
    //   430: iload 5
    //   432: newarray <illegal type>
    //   434: astore_0
    //   435: aload 8
    //   437: iconst_0
    //   438: aload_0
    //   439: iconst_0
    //   440: iload 5
    //   442: invokestatic 307	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   445: aload_0
    //   446: areturn
    //   447: aload 8
    //   449: areturn
    //   450: astore_0
    //   451: goto -266 -> 185
    //   454: astore_0
    //   455: goto -265 -> 190
    //   458: astore_0
    //   459: goto -264 -> 195
    //   462: astore 9
    //   464: goto -243 -> 221
    //   467: astore_0
    //   468: goto -243 -> 225
    //   471: astore_0
    //   472: goto -242 -> 230
    //   475: astore 10
    //   477: aconst_null
    //   478: astore 9
    //   480: aconst_null
    //   481: astore 8
    //   483: aconst_null
    //   484: astore_0
    //   485: goto -269 -> 216
    //   488: astore 10
    //   490: aconst_null
    //   491: astore 9
    //   493: aconst_null
    //   494: astore_0
    //   495: goto -279 -> 216
    //   498: astore 10
    //   500: aconst_null
    //   501: astore 11
    //   503: aload 9
    //   505: astore_0
    //   506: aload 11
    //   508: astore 9
    //   510: goto -294 -> 216
    //   513: astore 11
    //   515: aload 9
    //   517: astore_0
    //   518: aload 10
    //   520: astore 9
    //   522: aload 11
    //   524: astore 10
    //   526: goto -310 -> 216
    //   529: astore 10
    //   531: aconst_null
    //   532: astore 9
    //   534: aconst_null
    //   535: astore_0
    //   536: goto -325 -> 211
    //   539: astore 10
    //   541: aconst_null
    //   542: astore 11
    //   544: aload 9
    //   546: astore_0
    //   547: aload 11
    //   549: astore 9
    //   551: goto -340 -> 211
    //   554: astore_0
    //   555: aload 10
    //   557: astore 11
    //   559: aload_0
    //   560: astore 10
    //   562: aload 9
    //   564: astore_0
    //   565: aload 11
    //   567: astore 9
    //   569: goto -358 -> 211
    //   572: goto -213 -> 359
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	575	0	paramArrayOfByte	byte[]
    //   0	575	1	paramInt1	int
    //   0	575	2	paramInt2	int
    //   0	575	3	paramInt3	int
    //   255	163	4	i	int
    //   246	195	5	j	int
    //   241	135	6	k	int
    //   298	107	7	m	int
    //   140	342	8	localObject1	Object
    //   154	63	9	localOutputStream	OutputStream
    //   462	1	9	localException	Exception
    //   478	90	9	localObject2	Object
    //   165	16	10	localGZIPOutputStream	java.util.zip.GZIPOutputStream
    //   201	11	10	localIOException1	IOException
    //   214	17	10	localObject3	Object
    //   475	1	10	localObject4	Object
    //   488	1	10	localObject5	Object
    //   498	21	10	localObject6	Object
    //   524	1	10	localObject7	Object
    //   529	1	10	localIOException2	IOException
    //   539	17	10	localIOException3	IOException
    //   560	1	10	arrayOfByte	byte[]
    //   501	6	11	localObject8	Object
    //   513	10	11	localObject9	Object
    //   542	24	11	localIOException4	IOException
    // Exception table:
    //   from	to	target	type
    //   133	142	201	java/io/IOException
    //   211	214	214	finally
    //   180	185	450	java/lang/Exception
    //   185	190	454	java/lang/Exception
    //   190	195	458	java/lang/Exception
    //   216	221	462	java/lang/Exception
    //   221	225	467	java/lang/Exception
    //   225	230	471	java/lang/Exception
    //   133	142	475	finally
    //   142	156	488	finally
    //   156	167	498	finally
    //   167	180	513	finally
    //   142	156	529	java/io/IOException
    //   156	167	539	java/io/IOException
    //   167	180	554	java/io/IOException
  }
  
  /* Error */
  public static void encodeFileToFile(String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 450	com/amazon/mas/kiwi/util/Base64:encodeFromFile	(Ljava/lang/String;)Ljava/lang/String;
    //   4: astore_3
    //   5: aconst_null
    //   6: astore_0
    //   7: aconst_null
    //   8: astore_2
    //   9: new 325	java/io/BufferedOutputStream
    //   12: dup
    //   13: new 327	java/io/FileOutputStream
    //   16: dup
    //   17: aload_1
    //   18: invokespecial 328	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   21: invokespecial 331	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   24: astore_1
    //   25: aload_1
    //   26: aload_3
    //   27: ldc 39
    //   29: invokevirtual 227	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   32: invokevirtual 335	java/io/OutputStream:write	([B)V
    //   35: aload_1
    //   36: invokevirtual 336	java/io/OutputStream:close	()V
    //   39: return
    //   40: astore_1
    //   41: aload_2
    //   42: astore_0
    //   43: aload_1
    //   44: athrow
    //   45: astore_1
    //   46: aload_0
    //   47: invokevirtual 336	java/io/OutputStream:close	()V
    //   50: aload_1
    //   51: athrow
    //   52: astore_0
    //   53: return
    //   54: astore_0
    //   55: goto -5 -> 50
    //   58: astore_2
    //   59: aload_1
    //   60: astore_0
    //   61: aload_2
    //   62: astore_1
    //   63: goto -17 -> 46
    //   66: astore_0
    //   67: aload_1
    //   68: astore_2
    //   69: aload_0
    //   70: astore_1
    //   71: aload_2
    //   72: astore_0
    //   73: goto -30 -> 43
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	paramString1	String
    //   0	76	1	paramString2	String
    //   8	34	2	localObject1	Object
    //   58	4	2	localObject2	Object
    //   68	4	2	str1	String
    //   4	23	3	str2	String
    // Exception table:
    //   from	to	target	type
    //   9	25	40	java/io/IOException
    //   9	25	45	finally
    //   43	45	45	finally
    //   35	39	52	java/lang/Exception
    //   46	50	54	java/lang/Exception
    //   25	35	58	finally
    //   25	35	66	java/io/IOException
  }
  
  /* Error */
  public static String encodeFromFile(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: aload 5
    //   8: astore_3
    //   9: new 338	java/io/File
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 339	java/io/File:<init>	(Ljava/lang/String;)V
    //   17: astore_0
    //   18: aload 5
    //   20: astore_3
    //   21: aload_0
    //   22: invokevirtual 343	java/io/File:length	()J
    //   25: l2d
    //   26: ldc2_w 451
    //   29: dmul
    //   30: dconst_1
    //   31: dadd
    //   32: d2i
    //   33: bipush 40
    //   35: invokestatic 455	java/lang/Math:max	(II)I
    //   38: newarray <illegal type>
    //   40: astore 6
    //   42: iconst_0
    //   43: istore_1
    //   44: aload 5
    //   46: astore_3
    //   47: new 6	com/amazon/mas/kiwi/util/Base64$InputStream
    //   50: dup
    //   51: new 355	java/io/BufferedInputStream
    //   54: dup
    //   55: new 357	java/io/FileInputStream
    //   58: dup
    //   59: aload_0
    //   60: invokespecial 360	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   63: invokespecial 361	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   66: iconst_1
    //   67: invokespecial 364	com/amazon/mas/kiwi/util/Base64$InputStream:<init>	(Ljava/io/InputStream;I)V
    //   70: astore_0
    //   71: aload_0
    //   72: aload 6
    //   74: iload_1
    //   75: sipush 4096
    //   78: invokevirtual 367	com/amazon/mas/kiwi/util/Base64$InputStream:read	([BII)I
    //   81: istore_2
    //   82: iload_2
    //   83: iflt +10 -> 93
    //   86: iload_1
    //   87: iload_2
    //   88: iadd
    //   89: istore_1
    //   90: goto -19 -> 71
    //   93: new 224	java/lang/String
    //   96: dup
    //   97: aload 6
    //   99: iconst_0
    //   100: iload_1
    //   101: ldc 39
    //   103: invokespecial 458	java/lang/String:<init>	([BIILjava/lang/String;)V
    //   106: astore_3
    //   107: aload_0
    //   108: invokevirtual 353	com/amazon/mas/kiwi/util/Base64$InputStream:close	()V
    //   111: aload_3
    //   112: areturn
    //   113: astore_0
    //   114: aload 4
    //   116: astore_3
    //   117: aload_0
    //   118: athrow
    //   119: astore_0
    //   120: aload_3
    //   121: invokevirtual 353	com/amazon/mas/kiwi/util/Base64$InputStream:close	()V
    //   124: aload_0
    //   125: athrow
    //   126: astore_0
    //   127: aload_3
    //   128: areturn
    //   129: astore_3
    //   130: goto -6 -> 124
    //   133: astore 4
    //   135: aload_0
    //   136: astore_3
    //   137: aload 4
    //   139: astore_0
    //   140: goto -20 -> 120
    //   143: astore 4
    //   145: aload_0
    //   146: astore_3
    //   147: aload 4
    //   149: astore_0
    //   150: goto -33 -> 117
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	153	0	paramString	String
    //   43	58	1	i	int
    //   81	8	2	j	int
    //   8	120	3	localObject1	Object
    //   129	1	3	localException	Exception
    //   136	11	3	str	String
    //   4	111	4	localObject2	Object
    //   133	5	4	localObject3	Object
    //   143	5	4	localIOException	IOException
    //   1	44	5	localObject4	Object
    //   40	58	6	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   9	18	113	java/io/IOException
    //   21	42	113	java/io/IOException
    //   47	71	113	java/io/IOException
    //   9	18	119	finally
    //   21	42	119	finally
    //   47	71	119	finally
    //   117	119	119	finally
    //   107	111	126	java/lang/Exception
    //   120	124	129	java/lang/Exception
    //   71	82	133	finally
    //   93	107	133	finally
    //   71	82	143	java/io/IOException
    //   93	107	143	java/io/IOException
  }
  
  public static String encodeObject(Serializable paramSerializable)
    throws IOException
  {
    return encodeObject(paramSerializable, 0);
  }
  
  /* Error */
  public static String encodeObject(Serializable paramSerializable, int paramInt)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +14 -> 15
    //   4: new 217	java/lang/NullPointerException
    //   7: dup
    //   8: ldc_w 465
    //   11: invokespecial 222	java/lang/NullPointerException:<init>	(Ljava/lang/String;)V
    //   14: athrow
    //   15: aconst_null
    //   16: astore 6
    //   18: aconst_null
    //   19: astore 13
    //   21: aconst_null
    //   22: astore 5
    //   24: aconst_null
    //   25: astore 16
    //   27: aconst_null
    //   28: astore 9
    //   30: aconst_null
    //   31: astore 4
    //   33: aconst_null
    //   34: astore 7
    //   36: aconst_null
    //   37: astore 14
    //   39: aconst_null
    //   40: astore 19
    //   42: aconst_null
    //   43: astore 17
    //   45: aconst_null
    //   46: astore 12
    //   48: aconst_null
    //   49: astore 8
    //   51: aconst_null
    //   52: astore 11
    //   54: aconst_null
    //   55: astore 18
    //   57: aconst_null
    //   58: astore 15
    //   60: aconst_null
    //   61: astore 10
    //   63: new 234	java/io/ByteArrayOutputStream
    //   66: dup
    //   67: invokespecial 235	java/io/ByteArrayOutputStream:<init>	()V
    //   70: astore_2
    //   71: new 9	com/amazon/mas/kiwi/util/Base64$OutputStream
    //   74: dup
    //   75: aload_2
    //   76: iload_1
    //   77: iconst_1
    //   78: ior
    //   79: invokespecial 371	com/amazon/mas/kiwi/util/Base64$OutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   82: astore_3
    //   83: iload_1
    //   84: iconst_2
    //   85: iand
    //   86: ifeq +96 -> 182
    //   89: aload 19
    //   91: astore 6
    //   93: aload 18
    //   95: astore 7
    //   97: aload 17
    //   99: astore 8
    //   101: aload 15
    //   103: astore 9
    //   105: new 442	java/util/zip/GZIPOutputStream
    //   108: dup
    //   109: aload_3
    //   110: invokespecial 443	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   113: astore 4
    //   115: new 467	java/io/ObjectOutputStream
    //   118: dup
    //   119: aload 4
    //   121: invokespecial 468	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   124: astore 5
    //   126: aload 4
    //   128: astore 6
    //   130: aload 5
    //   132: astore 7
    //   134: aload 4
    //   136: astore 8
    //   138: aload 5
    //   140: astore 9
    //   142: aload 5
    //   144: aload_0
    //   145: invokevirtual 471	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   148: aload 5
    //   150: invokevirtual 472	java/io/ObjectOutputStream:close	()V
    //   153: aload 4
    //   155: invokevirtual 445	java/util/zip/GZIPOutputStream:close	()V
    //   158: aload_3
    //   159: invokevirtual 336	java/io/OutputStream:close	()V
    //   162: aload_2
    //   163: invokevirtual 259	java/io/ByteArrayOutputStream:close	()V
    //   166: new 224	java/lang/String
    //   169: dup
    //   170: aload_2
    //   171: invokevirtual 267	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   174: ldc 39
    //   176: invokespecial 429	java/lang/String:<init>	([BLjava/lang/String;)V
    //   179: astore_0
    //   180: aload_0
    //   181: areturn
    //   182: aload 19
    //   184: astore 6
    //   186: aload 18
    //   188: astore 7
    //   190: aload 17
    //   192: astore 8
    //   194: aload 15
    //   196: astore 9
    //   198: new 467	java/io/ObjectOutputStream
    //   201: dup
    //   202: aload_3
    //   203: invokespecial 468	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   206: astore 5
    //   208: aload 12
    //   210: astore 4
    //   212: goto -86 -> 126
    //   215: astore_3
    //   216: aload 10
    //   218: astore 8
    //   220: aload 13
    //   222: astore_0
    //   223: aload 9
    //   225: astore_2
    //   226: aload_2
    //   227: astore 5
    //   229: aload_0
    //   230: astore 6
    //   232: aload 4
    //   234: astore 7
    //   236: aload_3
    //   237: athrow
    //   238: astore 4
    //   240: aload 7
    //   242: astore_3
    //   243: aload 6
    //   245: astore_2
    //   246: aload 5
    //   248: astore_0
    //   249: aload 8
    //   251: invokevirtual 472	java/io/ObjectOutputStream:close	()V
    //   254: aload_3
    //   255: invokevirtual 445	java/util/zip/GZIPOutputStream:close	()V
    //   258: aload_0
    //   259: invokevirtual 336	java/io/OutputStream:close	()V
    //   262: aload_2
    //   263: invokevirtual 259	java/io/ByteArrayOutputStream:close	()V
    //   266: aload 4
    //   268: athrow
    //   269: astore_0
    //   270: new 224	java/lang/String
    //   273: dup
    //   274: aload_2
    //   275: invokevirtual 267	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   278: invokespecial 430	java/lang/String:<init>	([B)V
    //   281: areturn
    //   282: astore_0
    //   283: goto -130 -> 153
    //   286: astore_0
    //   287: goto -129 -> 158
    //   290: astore_0
    //   291: goto -129 -> 162
    //   294: astore_0
    //   295: goto -129 -> 166
    //   298: astore 5
    //   300: goto -46 -> 254
    //   303: astore_3
    //   304: goto -46 -> 258
    //   307: astore_0
    //   308: goto -46 -> 262
    //   311: astore_0
    //   312: goto -46 -> 266
    //   315: astore 4
    //   317: aload 16
    //   319: astore_0
    //   320: aload 14
    //   322: astore_3
    //   323: aload 11
    //   325: astore 8
    //   327: goto -78 -> 249
    //   330: astore 4
    //   332: aload_3
    //   333: astore_0
    //   334: aload 6
    //   336: astore_3
    //   337: aload 7
    //   339: astore 8
    //   341: goto -92 -> 249
    //   344: astore 5
    //   346: aload_3
    //   347: astore_0
    //   348: aload 4
    //   350: astore_3
    //   351: aload 11
    //   353: astore 8
    //   355: aload 5
    //   357: astore 4
    //   359: goto -110 -> 249
    //   362: astore_3
    //   363: aload_2
    //   364: astore_0
    //   365: aload 9
    //   367: astore_2
    //   368: aload 10
    //   370: astore 8
    //   372: goto -146 -> 226
    //   375: astore 4
    //   377: aload_2
    //   378: astore_0
    //   379: aload_3
    //   380: astore_2
    //   381: aload 4
    //   383: astore_3
    //   384: aload 8
    //   386: astore 4
    //   388: aload 9
    //   390: astore 8
    //   392: goto -166 -> 226
    //   395: astore 5
    //   397: aload_2
    //   398: astore_0
    //   399: aload_3
    //   400: astore_2
    //   401: aload 5
    //   403: astore_3
    //   404: aload 10
    //   406: astore 8
    //   408: goto -182 -> 226
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	411	0	paramSerializable	Serializable
    //   0	411	1	paramInt	int
    //   70	331	2	localObject1	Object
    //   82	121	3	localOutputStream	OutputStream
    //   215	22	3	localIOException1	IOException
    //   242	13	3	localObject2	Object
    //   303	1	3	localException1	Exception
    //   322	29	3	localObject3	Object
    //   362	18	3	localIOException2	IOException
    //   383	21	3	localIOException3	IOException
    //   31	202	4	localObject4	Object
    //   238	29	4	localObject5	Object
    //   315	1	4	localObject6	Object
    //   330	19	4	localObject7	Object
    //   357	1	4	localObject8	Object
    //   375	7	4	localIOException4	IOException
    //   386	1	4	localObject9	Object
    //   22	225	5	localObject10	Object
    //   298	1	5	localException2	Exception
    //   344	12	5	localObject11	Object
    //   395	7	5	localIOException5	IOException
    //   16	319	6	localObject12	Object
    //   34	304	7	localObject13	Object
    //   49	358	8	localObject14	Object
    //   28	361	9	localObject15	Object
    //   61	344	10	localObject16	Object
    //   52	300	11	localObject17	Object
    //   46	163	12	localObject18	Object
    //   19	202	13	localObject19	Object
    //   37	284	14	localObject20	Object
    //   58	137	15	localObject21	Object
    //   25	293	16	localObject22	Object
    //   43	148	17	localObject23	Object
    //   55	132	18	localObject24	Object
    //   40	143	19	localObject25	Object
    // Exception table:
    //   from	to	target	type
    //   63	71	215	java/io/IOException
    //   63	71	238	finally
    //   236	238	238	finally
    //   166	180	269	java/io/UnsupportedEncodingException
    //   148	153	282	java/lang/Exception
    //   153	158	286	java/lang/Exception
    //   158	162	290	java/lang/Exception
    //   162	166	294	java/lang/Exception
    //   249	254	298	java/lang/Exception
    //   254	258	303	java/lang/Exception
    //   258	262	307	java/lang/Exception
    //   262	266	311	java/lang/Exception
    //   71	83	315	finally
    //   105	115	330	finally
    //   142	148	330	finally
    //   198	208	330	finally
    //   115	126	344	finally
    //   71	83	362	java/io/IOException
    //   105	115	375	java/io/IOException
    //   142	148	375	java/io/IOException
    //   198	208	375	java/io/IOException
    //   115	126	395	java/io/IOException
  }
  
  /* Error */
  public static void encodeToFile(byte[] paramArrayOfByte, String paramString)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +14 -> 15
    //   4: new 217	java/lang/NullPointerException
    //   7: dup
    //   8: ldc_w 475
    //   11: invokespecial 222	java/lang/NullPointerException:<init>	(Ljava/lang/String;)V
    //   14: athrow
    //   15: aconst_null
    //   16: astore_2
    //   17: aconst_null
    //   18: astore_3
    //   19: new 9	com/amazon/mas/kiwi/util/Base64$OutputStream
    //   22: dup
    //   23: new 327	java/io/FileOutputStream
    //   26: dup
    //   27: aload_1
    //   28: invokespecial 328	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   31: iconst_1
    //   32: invokespecial 371	com/amazon/mas/kiwi/util/Base64$OutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   35: astore_1
    //   36: aload_1
    //   37: aload_0
    //   38: invokevirtual 372	com/amazon/mas/kiwi/util/Base64$OutputStream:write	([B)V
    //   41: aload_1
    //   42: invokevirtual 373	com/amazon/mas/kiwi/util/Base64$OutputStream:close	()V
    //   45: return
    //   46: astore_0
    //   47: aload_3
    //   48: astore_2
    //   49: aload_0
    //   50: athrow
    //   51: astore_0
    //   52: aload_2
    //   53: invokevirtual 373	com/amazon/mas/kiwi/util/Base64$OutputStream:close	()V
    //   56: aload_0
    //   57: athrow
    //   58: astore_0
    //   59: return
    //   60: astore_1
    //   61: goto -5 -> 56
    //   64: astore_0
    //   65: aload_1
    //   66: astore_2
    //   67: goto -15 -> 52
    //   70: astore_0
    //   71: aload_1
    //   72: astore_2
    //   73: goto -24 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	paramArrayOfByte	byte[]
    //   0	76	1	paramString	String
    //   16	57	2	localObject1	Object
    //   18	30	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   19	36	46	java/io/IOException
    //   19	36	51	finally
    //   49	51	51	finally
    //   41	45	58	java/lang/Exception
    //   52	56	60	java/lang/Exception
    //   36	41	64	finally
    //   36	41	70	java/io/IOException
  }
  
  private static final byte[] getAlphabet(int paramInt)
  {
    if ((paramInt & 0x10) == 16) {
      return _URL_SAFE_ALPHABET;
    }
    if ((paramInt & 0x20) == 32) {
      return _ORDERED_ALPHABET;
    }
    return _STANDARD_ALPHABET;
  }
  
  private static final byte[] getDecodabet(int paramInt)
  {
    if ((paramInt & 0x10) == 16) {
      return _URL_SAFE_DECODABET;
    }
    if ((paramInt & 0x20) == 32) {
      return _ORDERED_DECODABET;
    }
    return _STANDARD_DECODABET;
  }
  
  public static class InputStream
    extends FilterInputStream
  {
    private boolean breakLines;
    private byte[] buffer;
    private int bufferLength;
    private byte[] decodabet;
    private boolean encode;
    private int lineLength;
    private int numSigBytes;
    private int options;
    private int position;
    
    public InputStream(InputStream paramInputStream)
    {
      this(paramInputStream, 0);
    }
    
    public InputStream(InputStream paramInputStream, int paramInt)
    {
      super();
      this.options = paramInt;
      boolean bool;
      if ((paramInt & 0x8) > 0)
      {
        bool = true;
        this.breakLines = bool;
        if ((paramInt & 0x1) <= 0) {
          break label90;
        }
        bool = true;
        label35:
        this.encode = bool;
        if (!this.encode) {
          break label96;
        }
      }
      label90:
      label96:
      for (int i = 4;; i = 3)
      {
        this.bufferLength = i;
        this.buffer = new byte[this.bufferLength];
        this.position = -1;
        this.lineLength = 0;
        this.decodabet = Base64.getDecodabet(paramInt);
        return;
        bool = false;
        break;
        bool = false;
        break label35;
      }
    }
    
    public int read()
      throws IOException
    {
      int j;
      if (this.position < 0)
      {
        if (!this.encode) {
          break label110;
        }
        arrayOfByte = new byte[3];
        j = 0;
        i = 0;
        while (i < 3)
        {
          int k = this.in.read();
          if (k < 0) {
            break;
          }
          arrayOfByte[i] = ((byte)k);
          j += 1;
          i += 1;
        }
        if (j > 0)
        {
          Base64.encode3to4(arrayOfByte, 0, j, this.buffer, 0, this.options);
          this.position = 0;
          this.numSigBytes = 4;
        }
      }
      else
      {
        if (this.position < 0) {
          break label299;
        }
        if (this.position < this.numSigBytes) {
          break label213;
        }
        return -1;
      }
      return -1;
      label110:
      byte[] arrayOfByte = new byte[4];
      int i = 0;
      for (;;)
      {
        if (i < 4)
        {
          do
          {
            j = this.in.read();
          } while ((j >= 0) && (this.decodabet[(j & 0x7F)] <= -5));
          if (j >= 0) {}
        }
        else
        {
          if (i != 4) {
            break label197;
          }
          this.numSigBytes = Base64.decode4to3(arrayOfByte, 0, this.buffer, 0, this.options);
          this.position = 0;
          break;
        }
        arrayOfByte[i] = ((byte)j);
        i += 1;
      }
      label197:
      if (i == 0) {
        return -1;
      }
      throw new IOException("Improperly padded Base64 input.");
      label213:
      if ((this.encode) && (this.breakLines) && (this.lineLength >= 76))
      {
        this.lineLength = 0;
        return 10;
      }
      this.lineLength += 1;
      arrayOfByte = this.buffer;
      i = this.position;
      this.position = (i + 1);
      i = arrayOfByte[i];
      if (this.position >= this.bufferLength) {
        this.position = -1;
      }
      return i & 0xFF;
      label299:
      throw new IOException("Error in Base64 code reading stream.");
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      int i = 0;
      while (i < paramInt2)
      {
        int j = read();
        if (j >= 0)
        {
          paramArrayOfByte[(paramInt1 + i)] = ((byte)j);
          i += 1;
        }
        else if (i == 0)
        {
          return -1;
        }
      }
      return i;
    }
  }
  
  public static class OutputStream
    extends FilterOutputStream
  {
    private byte[] b4;
    private boolean breakLines;
    private byte[] buffer;
    private int bufferLength;
    private byte[] decodabet;
    private boolean encode;
    private int lineLength;
    private int options;
    private int position;
    private boolean suspendEncoding;
    
    public OutputStream(OutputStream paramOutputStream)
    {
      this(paramOutputStream, 1);
    }
    
    public OutputStream(OutputStream paramOutputStream, int paramInt)
    {
      super();
      boolean bool;
      if ((paramInt & 0x8) != 0)
      {
        bool = true;
        this.breakLines = bool;
        if ((paramInt & 0x1) == 0) {
          break label102;
        }
        bool = true;
        label30:
        this.encode = bool;
        if (!this.encode) {
          break label108;
        }
      }
      label102:
      label108:
      for (int i = 3;; i = 4)
      {
        this.bufferLength = i;
        this.buffer = new byte[this.bufferLength];
        this.position = 0;
        this.lineLength = 0;
        this.suspendEncoding = false;
        this.b4 = new byte[4];
        this.options = paramInt;
        this.decodabet = Base64.getDecodabet(paramInt);
        return;
        bool = false;
        break;
        bool = false;
        break label30;
      }
    }
    
    public void close()
      throws IOException
    {
      flushBase64();
      super.close();
      this.buffer = null;
      this.out = null;
    }
    
    public void flushBase64()
      throws IOException
    {
      if (this.position > 0)
      {
        if (this.encode)
        {
          this.out.write(Base64.encode3to4(this.b4, this.buffer, this.position, this.options));
          this.position = 0;
        }
      }
      else {
        return;
      }
      throw new IOException("Base64 input not properly padded.");
    }
    
    public void resumeEncoding()
    {
      this.suspendEncoding = false;
    }
    
    public void suspendEncoding()
      throws IOException
    {
      flushBase64();
      this.suspendEncoding = true;
    }
    
    public void write(int paramInt)
      throws IOException
    {
      if (this.suspendEncoding) {
        this.out.write(paramInt);
      }
      do
      {
        do
        {
          do
          {
            return;
            if (!this.encode) {
              break;
            }
            arrayOfByte = this.buffer;
            i = this.position;
            this.position = (i + 1);
            arrayOfByte[i] = ((byte)paramInt);
          } while (this.position < this.bufferLength);
          this.out.write(Base64.encode3to4(this.b4, this.buffer, this.bufferLength, this.options));
          this.lineLength += 4;
          if ((this.breakLines) && (this.lineLength >= 76))
          {
            this.out.write(10);
            this.lineLength = 0;
          }
          this.position = 0;
          return;
          if (this.decodabet[(paramInt & 0x7F)] <= -5) {
            break;
          }
          byte[] arrayOfByte = this.buffer;
          int i = this.position;
          this.position = (i + 1);
          arrayOfByte[i] = ((byte)paramInt);
        } while (this.position < this.bufferLength);
        paramInt = Base64.decode4to3(this.buffer, 0, this.b4, 0, this.options);
        this.out.write(this.b4, 0, paramInt);
        this.position = 0;
        return;
      } while (this.decodabet[(paramInt & 0x7F)] == -5);
      throw new IOException("Invalid character in Base64 data.");
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      if (this.suspendEncoding) {
        this.out.write(paramArrayOfByte, paramInt1, paramInt2);
      }
      for (;;)
      {
        return;
        int i = 0;
        while (i < paramInt2)
        {
          write(paramArrayOfByte[(paramInt1 + i)]);
          i += 1;
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\mas\kiwi\util\Base64.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */