package com.google.android.gms.internal;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzhb
public class zzdu
  extends zzdr
{
  private static final Set<String> zzzX = Collections.synchronizedSet(new HashSet());
  private static final DecimalFormat zzzY = new DecimalFormat("#,###");
  private boolean zzAa;
  private File zzzZ;
  
  public zzdu(zzjp paramzzjp)
  {
    super(paramzzjp);
    paramzzjp = this.mContext.getCacheDir();
    if (paramzzjp == null) {
      zzin.zzaK("Context.getCacheDir() returned null");
    }
    do
    {
      return;
      this.zzzZ = new File(paramzzjp, "admobVideoStreams");
      if ((!this.zzzZ.isDirectory()) && (!this.zzzZ.mkdirs()))
      {
        zzin.zzaK("Could not create preload cache directory at " + this.zzzZ.getAbsolutePath());
        this.zzzZ = null;
        return;
      }
    } while ((this.zzzZ.setReadable(true, false)) && (this.zzzZ.setExecutable(true, false)));
    zzin.zzaK("Could not set cache file permissions at " + this.zzzZ.getAbsolutePath());
    this.zzzZ = null;
  }
  
  private File zza(File paramFile)
  {
    return new File(this.zzzZ, paramFile.getName() + ".done");
  }
  
  private static void zzb(File paramFile)
  {
    if (paramFile.isFile())
    {
      paramFile.setLastModified(System.currentTimeMillis());
      return;
    }
    try
    {
      paramFile.createNewFile();
      return;
    }
    catch (IOException paramFile) {}
  }
  
  public void abort()
  {
    this.zzAa = true;
  }
  
  /* Error */
  public boolean zzU(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 68	com/google/android/gms/internal/zzdu:zzzZ	Ljava/io/File;
    //   4: ifnonnull +14 -> 18
    //   7: aload_0
    //   8: aload_1
    //   9: aconst_null
    //   10: ldc -118
    //   12: aconst_null
    //   13: invokevirtual 141	com/google/android/gms/internal/zzdu:zza	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   16: iconst_0
    //   17: ireturn
    //   18: aload_0
    //   19: invokevirtual 145	com/google/android/gms/internal/zzdu:zzea	()I
    //   22: getstatic 151	com/google/android/gms/internal/zzbt:zzvO	Lcom/google/android/gms/internal/zzbp;
    //   25: invokevirtual 157	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   28: checkcast 159	java/lang/Integer
    //   31: invokevirtual 162	java/lang/Integer:intValue	()I
    //   34: if_icmple +26 -> 60
    //   37: aload_0
    //   38: invokevirtual 165	com/google/android/gms/internal/zzdu:zzeb	()Z
    //   41: ifne -23 -> 18
    //   44: ldc -89
    //   46: invokestatic 59	com/google/android/gms/internal/zzin:zzaK	(Ljava/lang/String;)V
    //   49: aload_0
    //   50: aload_1
    //   51: aconst_null
    //   52: ldc -87
    //   54: aconst_null
    //   55: invokevirtual 141	com/google/android/gms/internal/zzdu:zza	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   58: iconst_0
    //   59: ireturn
    //   60: aload_0
    //   61: aload_1
    //   62: invokevirtual 173	com/google/android/gms/internal/zzdu:zzV	(Ljava/lang/String;)Ljava/lang/String;
    //   65: astore 10
    //   67: new 61	java/io/File
    //   70: dup
    //   71: aload_0
    //   72: getfield 68	com/google/android/gms/internal/zzdu:zzzZ	Ljava/io/File;
    //   75: aload 10
    //   77: invokespecial 66	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   80: astore 16
    //   82: aload_0
    //   83: aload 16
    //   85: invokespecial 175	com/google/android/gms/internal/zzdu:zza	(Ljava/io/File;)Ljava/io/File;
    //   88: astore 11
    //   90: aload 16
    //   92: invokevirtual 114	java/io/File:isFile	()Z
    //   95: ifeq +53 -> 148
    //   98: aload 11
    //   100: invokevirtual 114	java/io/File:isFile	()Z
    //   103: ifeq +45 -> 148
    //   106: aload 16
    //   108: invokevirtual 178	java/io/File:length	()J
    //   111: l2i
    //   112: istore_2
    //   113: new 77	java/lang/StringBuilder
    //   116: dup
    //   117: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   120: ldc -76
    //   122: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: aload_1
    //   126: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: invokestatic 183	com/google/android/gms/internal/zzin:zzaI	(Ljava/lang/String;)V
    //   135: aload_0
    //   136: aload_1
    //   137: aload 16
    //   139: invokevirtual 88	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   142: iload_2
    //   143: invokevirtual 186	com/google/android/gms/internal/zzdu:zza	(Ljava/lang/String;Ljava/lang/String;I)V
    //   146: iconst_1
    //   147: ireturn
    //   148: new 77	java/lang/StringBuilder
    //   151: dup
    //   152: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   155: aload_0
    //   156: getfield 68	com/google/android/gms/internal/zzdu:zzzZ	Ljava/io/File;
    //   159: invokevirtual 88	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   162: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: aload_1
    //   166: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   172: astore 17
    //   174: getstatic 29	com/google/android/gms/internal/zzdu:zzzX	Ljava/util/Set;
    //   177: astore 10
    //   179: aload 10
    //   181: monitorenter
    //   182: getstatic 29	com/google/android/gms/internal/zzdu:zzzX	Ljava/util/Set;
    //   185: aload 17
    //   187: invokeinterface 192 2 0
    //   192: ifeq +49 -> 241
    //   195: new 77	java/lang/StringBuilder
    //   198: dup
    //   199: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   202: ldc -62
    //   204: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: aload_1
    //   208: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: invokestatic 59	com/google/android/gms/internal/zzin:zzaK	(Ljava/lang/String;)V
    //   217: aload_0
    //   218: aload_1
    //   219: aload 16
    //   221: invokevirtual 88	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   224: ldc -60
    //   226: aconst_null
    //   227: invokevirtual 141	com/google/android/gms/internal/zzdu:zza	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   230: aload 10
    //   232: monitorexit
    //   233: iconst_0
    //   234: ireturn
    //   235: astore_1
    //   236: aload 10
    //   238: monitorexit
    //   239: aload_1
    //   240: athrow
    //   241: getstatic 29	com/google/android/gms/internal/zzdu:zzzX	Ljava/util/Set;
    //   244: aload 17
    //   246: invokeinterface 199 2 0
    //   251: pop
    //   252: aload 10
    //   254: monitorexit
    //   255: aconst_null
    //   256: astore 13
    //   258: new 201	java/net/URL
    //   261: dup
    //   262: aload_1
    //   263: invokespecial 202	java/net/URL:<init>	(Ljava/lang/String;)V
    //   266: invokevirtual 206	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   269: astore 10
    //   271: getstatic 209	com/google/android/gms/internal/zzbt:zzvT	Lcom/google/android/gms/internal/zzbp;
    //   274: invokevirtual 157	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   277: checkcast 159	java/lang/Integer
    //   280: invokevirtual 162	java/lang/Integer:intValue	()I
    //   283: istore_2
    //   284: aload 10
    //   286: iload_2
    //   287: invokevirtual 215	java/net/URLConnection:setConnectTimeout	(I)V
    //   290: aload 10
    //   292: iload_2
    //   293: invokevirtual 218	java/net/URLConnection:setReadTimeout	(I)V
    //   296: aload 10
    //   298: instanceof 220
    //   301: ifeq +213 -> 514
    //   304: aload 10
    //   306: checkcast 220	java/net/HttpURLConnection
    //   309: invokevirtual 223	java/net/HttpURLConnection:getResponseCode	()I
    //   312: istore_2
    //   313: iload_2
    //   314: sipush 400
    //   317: if_icmplt +197 -> 514
    //   320: ldc -31
    //   322: astore 12
    //   324: new 77	java/lang/StringBuilder
    //   327: dup
    //   328: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   331: ldc -29
    //   333: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   336: iload_2
    //   337: invokestatic 230	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   340: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   346: astore 11
    //   348: new 111	java/io/IOException
    //   351: dup
    //   352: new 77	java/lang/StringBuilder
    //   355: dup
    //   356: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   359: ldc -24
    //   361: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   364: iload_2
    //   365: invokevirtual 235	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   368: ldc -19
    //   370: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   373: aload_1
    //   374: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   377: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   380: invokespecial 238	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   383: athrow
    //   384: astore 10
    //   386: aload 10
    //   388: instanceof 134
    //   391: ifeq +12 -> 403
    //   394: invokestatic 244	com/google/android/gms/ads/internal/zzr:zzbF	()Lcom/google/android/gms/internal/zzih;
    //   397: aload 10
    //   399: iconst_1
    //   400: invokevirtual 249	com/google/android/gms/internal/zzih:zzb	(Ljava/lang/Throwable;Z)V
    //   403: aload 13
    //   405: invokevirtual 254	java/io/FileOutputStream:close	()V
    //   408: aload_0
    //   409: getfield 130	com/google/android/gms/internal/zzdu:zzAa	Z
    //   412: ifeq +804 -> 1216
    //   415: new 77	java/lang/StringBuilder
    //   418: dup
    //   419: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   422: ldc_w 256
    //   425: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   428: aload_1
    //   429: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   432: ldc_w 258
    //   435: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   438: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   441: invokestatic 261	com/google/android/gms/internal/zzin:zzaJ	(Ljava/lang/String;)V
    //   444: aload 16
    //   446: invokevirtual 264	java/io/File:exists	()Z
    //   449: ifeq +38 -> 487
    //   452: aload 16
    //   454: invokevirtual 267	java/io/File:delete	()Z
    //   457: ifne +30 -> 487
    //   460: new 77	java/lang/StringBuilder
    //   463: dup
    //   464: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   467: ldc_w 269
    //   470: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: aload 16
    //   475: invokevirtual 88	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   478: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   481: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   484: invokestatic 59	com/google/android/gms/internal/zzin:zzaK	(Ljava/lang/String;)V
    //   487: aload_0
    //   488: aload_1
    //   489: aload 16
    //   491: invokevirtual 88	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   494: aload 12
    //   496: aload 11
    //   498: invokevirtual 141	com/google/android/gms/internal/zzdu:zza	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   501: getstatic 29	com/google/android/gms/internal/zzdu:zzzX	Ljava/util/Set;
    //   504: aload 17
    //   506: invokeinterface 272 2 0
    //   511: pop
    //   512: iconst_0
    //   513: ireturn
    //   514: aload 10
    //   516: invokevirtual 275	java/net/URLConnection:getContentLength	()I
    //   519: istore 4
    //   521: iload 4
    //   523: ifge +53 -> 576
    //   526: new 77	java/lang/StringBuilder
    //   529: dup
    //   530: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   533: ldc_w 277
    //   536: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   539: aload_1
    //   540: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   546: invokestatic 59	com/google/android/gms/internal/zzin:zzaK	(Ljava/lang/String;)V
    //   549: aload_0
    //   550: aload_1
    //   551: aload 16
    //   553: invokevirtual 88	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   556: ldc_w 279
    //   559: aconst_null
    //   560: invokevirtual 141	com/google/android/gms/internal/zzdu:zza	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   563: getstatic 29	com/google/android/gms/internal/zzdu:zzzX	Ljava/util/Set;
    //   566: aload 17
    //   568: invokeinterface 272 2 0
    //   573: pop
    //   574: iconst_0
    //   575: ireturn
    //   576: getstatic 38	com/google/android/gms/internal/zzdu:zzzY	Ljava/text/DecimalFormat;
    //   579: iload 4
    //   581: i2l
    //   582: invokevirtual 283	java/text/DecimalFormat:format	(J)Ljava/lang/String;
    //   585: astore 12
    //   587: getstatic 286	com/google/android/gms/internal/zzbt:zzvP	Lcom/google/android/gms/internal/zzbp;
    //   590: invokevirtual 157	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   593: checkcast 159	java/lang/Integer
    //   596: invokevirtual 162	java/lang/Integer:intValue	()I
    //   599: istore 5
    //   601: iload 4
    //   603: iload 5
    //   605: if_icmple +88 -> 693
    //   608: new 77	java/lang/StringBuilder
    //   611: dup
    //   612: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   615: ldc_w 288
    //   618: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: aload 12
    //   623: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   626: ldc_w 290
    //   629: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   632: aload_1
    //   633: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   636: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   639: invokestatic 59	com/google/android/gms/internal/zzin:zzaK	(Ljava/lang/String;)V
    //   642: new 77	java/lang/StringBuilder
    //   645: dup
    //   646: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   649: ldc_w 292
    //   652: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: aload 12
    //   657: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   660: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   663: astore 10
    //   665: aload_0
    //   666: aload_1
    //   667: aload 16
    //   669: invokevirtual 88	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   672: ldc_w 294
    //   675: aload 10
    //   677: invokevirtual 141	com/google/android/gms/internal/zzdu:zza	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   680: getstatic 29	com/google/android/gms/internal/zzdu:zzzX	Ljava/util/Set;
    //   683: aload 17
    //   685: invokeinterface 272 2 0
    //   690: pop
    //   691: iconst_0
    //   692: ireturn
    //   693: new 77	java/lang/StringBuilder
    //   696: dup
    //   697: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   700: ldc_w 296
    //   703: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   706: aload 12
    //   708: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   711: ldc_w 298
    //   714: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   717: aload_1
    //   718: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   721: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   724: invokestatic 183	com/google/android/gms/internal/zzin:zzaI	(Ljava/lang/String;)V
    //   727: aload 10
    //   729: invokevirtual 302	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   732: invokestatic 308	java/nio/channels/Channels:newChannel	(Ljava/io/InputStream;)Ljava/nio/channels/ReadableByteChannel;
    //   735: astore 12
    //   737: new 251	java/io/FileOutputStream
    //   740: dup
    //   741: aload 16
    //   743: invokespecial 310	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   746: astore 10
    //   748: aload 10
    //   750: invokevirtual 314	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   753: astore 13
    //   755: ldc_w 315
    //   758: invokestatic 321	java/nio/ByteBuffer:allocate	(I)Ljava/nio/ByteBuffer;
    //   761: astore 14
    //   763: invokestatic 325	com/google/android/gms/ads/internal/zzr:zzbG	()Lcom/google/android/gms/internal/zzmq;
    //   766: astore 15
    //   768: iconst_0
    //   769: istore_2
    //   770: aload 15
    //   772: invokeinterface 328 1 0
    //   777: lstore 6
    //   779: new 330	com/google/android/gms/internal/zziz
    //   782: dup
    //   783: getstatic 333	com/google/android/gms/internal/zzbt:zzvS	Lcom/google/android/gms/internal/zzbp;
    //   786: invokevirtual 157	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   789: checkcast 335	java/lang/Long
    //   792: invokevirtual 338	java/lang/Long:longValue	()J
    //   795: invokespecial 341	com/google/android/gms/internal/zziz:<init>	(J)V
    //   798: astore 18
    //   800: getstatic 344	com/google/android/gms/internal/zzbt:zzvR	Lcom/google/android/gms/internal/zzbp;
    //   803: invokevirtual 157	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   806: checkcast 335	java/lang/Long
    //   809: invokevirtual 338	java/lang/Long:longValue	()J
    //   812: lstore 8
    //   814: aload 12
    //   816: aload 14
    //   818: invokeinterface 350 2 0
    //   823: istore_3
    //   824: iload_3
    //   825: iflt +298 -> 1123
    //   828: iload_2
    //   829: iload_3
    //   830: iadd
    //   831: istore_3
    //   832: iload_3
    //   833: iload 5
    //   835: if_icmple +85 -> 920
    //   838: ldc_w 294
    //   841: astore 11
    //   843: aload 11
    //   845: astore 12
    //   847: aload 11
    //   849: astore 13
    //   851: new 77	java/lang/StringBuilder
    //   854: dup
    //   855: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   858: ldc_w 292
    //   861: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   864: iload_3
    //   865: invokestatic 230	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   868: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   871: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   874: astore 14
    //   876: aload 14
    //   878: astore 13
    //   880: aload 11
    //   882: astore 12
    //   884: aload 11
    //   886: astore 15
    //   888: new 111	java/io/IOException
    //   891: dup
    //   892: ldc_w 352
    //   895: invokespecial 238	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   898: athrow
    //   899: astore 11
    //   901: aload 10
    //   903: astore 14
    //   905: aload 11
    //   907: astore 10
    //   909: aload 13
    //   911: astore 11
    //   913: aload 14
    //   915: astore 13
    //   917: goto -531 -> 386
    //   920: aload 14
    //   922: invokevirtual 356	java/nio/ByteBuffer:flip	()Ljava/nio/Buffer;
    //   925: pop
    //   926: aload 13
    //   928: aload 14
    //   930: invokevirtual 361	java/nio/channels/FileChannel:write	(Ljava/nio/ByteBuffer;)I
    //   933: ifgt -7 -> 926
    //   936: aload 14
    //   938: invokevirtual 364	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   941: pop
    //   942: aload 15
    //   944: invokeinterface 328 1 0
    //   949: lload 6
    //   951: lsub
    //   952: ldc2_w 365
    //   955: lload 8
    //   957: lmul
    //   958: lcmp
    //   959: ifle +92 -> 1051
    //   962: ldc_w 368
    //   965: astore 11
    //   967: aload 11
    //   969: astore 12
    //   971: aload 11
    //   973: astore 13
    //   975: new 77	java/lang/StringBuilder
    //   978: dup
    //   979: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   982: ldc_w 370
    //   985: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   988: lload 8
    //   990: invokestatic 372	java/lang/Long:toString	(J)Ljava/lang/String;
    //   993: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   996: ldc_w 374
    //   999: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1002: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1005: astore 14
    //   1007: aload 14
    //   1009: astore 13
    //   1011: aload 11
    //   1013: astore 12
    //   1015: aload 11
    //   1017: astore 15
    //   1019: new 111	java/io/IOException
    //   1022: dup
    //   1023: ldc_w 376
    //   1026: invokespecial 238	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   1029: athrow
    //   1030: astore 11
    //   1032: aload 10
    //   1034: astore 13
    //   1036: aload 11
    //   1038: astore 10
    //   1040: aload 14
    //   1042: astore 11
    //   1044: aload 15
    //   1046: astore 12
    //   1048: goto -662 -> 386
    //   1051: aload_0
    //   1052: getfield 130	com/google/android/gms/internal/zzdu:zzAa	Z
    //   1055: ifeq +39 -> 1094
    //   1058: ldc_w 378
    //   1061: astore 13
    //   1063: aload 13
    //   1065: astore 12
    //   1067: new 111	java/io/IOException
    //   1070: dup
    //   1071: ldc_w 380
    //   1074: invokespecial 238	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   1077: athrow
    //   1078: astore 14
    //   1080: aconst_null
    //   1081: astore 11
    //   1083: aload 10
    //   1085: astore 13
    //   1087: aload 14
    //   1089: astore 10
    //   1091: goto -705 -> 386
    //   1094: iload_3
    //   1095: istore_2
    //   1096: aload 18
    //   1098: invokevirtual 383	com/google/android/gms/internal/zziz:tryAcquire	()Z
    //   1101: ifeq -287 -> 814
    //   1104: aload_0
    //   1105: aload_1
    //   1106: aload 16
    //   1108: invokevirtual 88	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1111: iload_3
    //   1112: iload 4
    //   1114: iconst_0
    //   1115: invokevirtual 386	com/google/android/gms/internal/zzdu:zza	(Ljava/lang/String;Ljava/lang/String;IIZ)V
    //   1118: iload_3
    //   1119: istore_2
    //   1120: goto -306 -> 814
    //   1123: aload 10
    //   1125: invokevirtual 254	java/io/FileOutputStream:close	()V
    //   1128: iconst_3
    //   1129: invokestatic 390	com/google/android/gms/internal/zzin:zzQ	(I)Z
    //   1132: ifeq +47 -> 1179
    //   1135: getstatic 38	com/google/android/gms/internal/zzdu:zzzY	Ljava/text/DecimalFormat;
    //   1138: iload_2
    //   1139: i2l
    //   1140: invokevirtual 283	java/text/DecimalFormat:format	(J)Ljava/lang/String;
    //   1143: astore 12
    //   1145: new 77	java/lang/StringBuilder
    //   1148: dup
    //   1149: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   1152: ldc_w 392
    //   1155: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1158: aload 12
    //   1160: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1163: ldc_w 298
    //   1166: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1169: aload_1
    //   1170: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1173: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1176: invokestatic 183	com/google/android/gms/internal/zzin:zzaI	(Ljava/lang/String;)V
    //   1179: aload 16
    //   1181: iconst_1
    //   1182: iconst_0
    //   1183: invokevirtual 95	java/io/File:setReadable	(ZZ)Z
    //   1186: pop
    //   1187: aload 11
    //   1189: invokestatic 394	com/google/android/gms/internal/zzdu:zzb	(Ljava/io/File;)V
    //   1192: aload_0
    //   1193: aload_1
    //   1194: aload 16
    //   1196: invokevirtual 88	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1199: iload_2
    //   1200: invokevirtual 186	com/google/android/gms/internal/zzdu:zza	(Ljava/lang/String;Ljava/lang/String;I)V
    //   1203: getstatic 29	com/google/android/gms/internal/zzdu:zzzX	Ljava/util/Set;
    //   1206: aload 17
    //   1208: invokeinterface 272 2 0
    //   1213: pop
    //   1214: iconst_1
    //   1215: ireturn
    //   1216: new 77	java/lang/StringBuilder
    //   1219: dup
    //   1220: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   1223: ldc_w 396
    //   1226: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1229: aload_1
    //   1230: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1233: ldc_w 258
    //   1236: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1239: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1242: aload 10
    //   1244: invokestatic 400	com/google/android/gms/internal/zzin:zzd	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1247: goto -803 -> 444
    //   1250: astore 13
    //   1252: goto -844 -> 408
    //   1255: astore 13
    //   1257: goto -849 -> 408
    //   1260: astore 10
    //   1262: aconst_null
    //   1263: astore 11
    //   1265: ldc_w 402
    //   1268: astore 12
    //   1270: goto -884 -> 386
    //   1273: astore 10
    //   1275: aconst_null
    //   1276: astore 11
    //   1278: goto -892 -> 386
    //   1281: astore 10
    //   1283: goto -897 -> 386
    //   1286: astore 14
    //   1288: aconst_null
    //   1289: astore 11
    //   1291: ldc_w 402
    //   1294: astore 12
    //   1296: aload 10
    //   1298: astore 13
    //   1300: aload 14
    //   1302: astore 10
    //   1304: goto -918 -> 386
    //   1307: astore 12
    //   1309: aconst_null
    //   1310: astore 11
    //   1312: aload 10
    //   1314: astore 14
    //   1316: aload 12
    //   1318: astore 10
    //   1320: aload 13
    //   1322: astore 12
    //   1324: aload 14
    //   1326: astore 13
    //   1328: goto -942 -> 386
    //   1331: astore 10
    //   1333: aconst_null
    //   1334: astore 11
    //   1336: ldc_w 402
    //   1339: astore 12
    //   1341: goto -955 -> 386
    //   1344: astore 10
    //   1346: aconst_null
    //   1347: astore 11
    //   1349: goto -963 -> 386
    //   1352: astore 14
    //   1354: aconst_null
    //   1355: astore 11
    //   1357: ldc_w 402
    //   1360: astore 12
    //   1362: aload 10
    //   1364: astore 13
    //   1366: aload 14
    //   1368: astore 10
    //   1370: goto -984 -> 386
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1373	0	this	zzdu
    //   0	1373	1	paramString	String
    //   112	1088	2	i	int
    //   823	296	3	j	int
    //   519	594	4	k	int
    //   599	237	5	m	int
    //   777	173	6	l1	long
    //   812	177	8	l2	long
    //   65	240	10	localObject1	Object
    //   384	131	10	localIOException1	IOException
    //   663	580	10	localObject2	Object
    //   1260	1	10	localRuntimeException1	RuntimeException
    //   1273	1	10	localRuntimeException2	RuntimeException
    //   1281	16	10	localRuntimeException3	RuntimeException
    //   1302	17	10	localObject3	Object
    //   1331	1	10	localIOException2	IOException
    //   1344	19	10	localIOException3	IOException
    //   1368	1	10	localObject4	Object
    //   88	797	11	localObject5	Object
    //   899	7	11	localIOException4	IOException
    //   911	105	11	localObject6	Object
    //   1030	7	11	localRuntimeException4	RuntimeException
    //   1042	314	11	localObject7	Object
    //   322	973	12	localObject8	Object
    //   1307	10	12	localRuntimeException5	RuntimeException
    //   1322	39	12	localObject9	Object
    //   256	830	13	localObject10	Object
    //   1250	1	13	localIOException5	IOException
    //   1255	1	13	localNullPointerException	NullPointerException
    //   1298	67	13	localObject11	Object
    //   761	280	14	localObject12	Object
    //   1078	10	14	localIOException6	IOException
    //   1286	15	14	localRuntimeException6	RuntimeException
    //   1314	11	14	localObject13	Object
    //   1352	15	14	localIOException7	IOException
    //   766	279	15	localObject14	Object
    //   80	1115	16	localFile	File
    //   172	1035	17	str	String
    //   798	299	18	localzziz	zziz
    // Exception table:
    //   from	to	target	type
    //   182	233	235	finally
    //   236	239	235	finally
    //   241	255	235	finally
    //   348	384	384	java/io/IOException
    //   888	899	899	java/io/IOException
    //   1019	1030	899	java/io/IOException
    //   888	899	1030	java/lang/RuntimeException
    //   1019	1030	1030	java/lang/RuntimeException
    //   851	876	1078	java/io/IOException
    //   975	1007	1078	java/io/IOException
    //   1067	1078	1078	java/io/IOException
    //   403	408	1250	java/io/IOException
    //   403	408	1255	java/lang/NullPointerException
    //   258	313	1260	java/lang/RuntimeException
    //   514	521	1260	java/lang/RuntimeException
    //   526	574	1260	java/lang/RuntimeException
    //   576	601	1260	java/lang/RuntimeException
    //   608	691	1260	java/lang/RuntimeException
    //   693	748	1260	java/lang/RuntimeException
    //   324	348	1273	java/lang/RuntimeException
    //   348	384	1281	java/lang/RuntimeException
    //   748	768	1286	java/lang/RuntimeException
    //   770	814	1286	java/lang/RuntimeException
    //   814	824	1286	java/lang/RuntimeException
    //   920	926	1286	java/lang/RuntimeException
    //   926	962	1286	java/lang/RuntimeException
    //   1051	1058	1286	java/lang/RuntimeException
    //   1096	1118	1286	java/lang/RuntimeException
    //   1123	1179	1286	java/lang/RuntimeException
    //   1179	1214	1286	java/lang/RuntimeException
    //   851	876	1307	java/lang/RuntimeException
    //   975	1007	1307	java/lang/RuntimeException
    //   1067	1078	1307	java/lang/RuntimeException
    //   258	313	1331	java/io/IOException
    //   514	521	1331	java/io/IOException
    //   526	574	1331	java/io/IOException
    //   576	601	1331	java/io/IOException
    //   608	691	1331	java/io/IOException
    //   693	748	1331	java/io/IOException
    //   324	348	1344	java/io/IOException
    //   748	768	1352	java/io/IOException
    //   770	814	1352	java/io/IOException
    //   814	824	1352	java/io/IOException
    //   920	926	1352	java/io/IOException
    //   926	962	1352	java/io/IOException
    //   1051	1058	1352	java/io/IOException
    //   1096	1118	1352	java/io/IOException
    //   1123	1179	1352	java/io/IOException
    //   1179	1214	1352	java/io/IOException
  }
  
  public int zzea()
  {
    int i = 0;
    int k = 0;
    if (this.zzzZ == null) {
      return k;
    }
    File[] arrayOfFile = this.zzzZ.listFiles();
    int m = arrayOfFile.length;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      k = i;
      if (!arrayOfFile[j].getName().endsWith(".done")) {
        k = i + 1;
      }
      j += 1;
      i = k;
    }
  }
  
  public boolean zzeb()
  {
    if (this.zzzZ == null) {
      return false;
    }
    Object localObject = null;
    long l1 = Long.MAX_VALUE;
    File[] arrayOfFile = this.zzzZ.listFiles();
    int j = arrayOfFile.length;
    int i = 0;
    if (i < j)
    {
      File localFile = arrayOfFile[i];
      if (localFile.getName().endsWith(".done")) {
        break label134;
      }
      long l2 = localFile.lastModified();
      if (l2 >= l1) {
        break label134;
      }
      localObject = localFile;
      l1 = l2;
    }
    label134:
    for (;;)
    {
      i += 1;
      break;
      boolean bool2;
      if (localObject != null)
      {
        bool2 = ((File)localObject).delete();
        localObject = zza((File)localObject);
        bool1 = bool2;
        if (!((File)localObject).isFile()) {}
      }
      for (boolean bool1 = bool2 & ((File)localObject).delete();; bool1 = false) {
        return bool1;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzdu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */