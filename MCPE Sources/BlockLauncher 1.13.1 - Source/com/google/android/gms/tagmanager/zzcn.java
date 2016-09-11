package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf.zzf;
import com.google.android.gms.internal.zzrq.zza;
import com.google.android.gms.internal.zzrs;
import com.google.android.gms.internal.zzrs.zzc;
import com.google.android.gms.internal.zzrs.zzg;
import com.google.android.gms.internal.zzst;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class zzcn
  implements zzp.zzf
{
  private final Context mContext;
  private final String zzbhM;
  private zzbf<zzrq.zza> zzbkg;
  private final ExecutorService zzbkn;
  
  zzcn(Context paramContext, String paramString)
  {
    this.mContext = paramContext;
    this.zzbhM = paramString;
    this.zzbkn = Executors.newSingleThreadExecutor();
  }
  
  private zzrs.zzc zza(ByteArrayOutputStream paramByteArrayOutputStream)
  {
    try
    {
      paramByteArrayOutputStream = zzaz.zzgi(paramByteArrayOutputStream.toString("UTF-8"));
      return paramByteArrayOutputStream;
    }
    catch (UnsupportedEncodingException paramByteArrayOutputStream)
    {
      zzbg.zzaI("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
      return null;
    }
    catch (JSONException paramByteArrayOutputStream)
    {
      zzbg.zzaK("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
    }
    return null;
  }
  
  private void zzd(zzrq.zza paramzza)
    throws IllegalArgumentException
  {
    if ((paramzza.zzju == null) && (paramzza.zzbme == null)) {
      throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
    }
  }
  
  private zzrs.zzc zzx(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = zzrs.zzb(zzaf.zzf.zzc(paramArrayOfByte));
      if (paramArrayOfByte != null) {
        zzbg.v("The container was successfully loaded from the resource (using binary file)");
      }
      return paramArrayOfByte;
    }
    catch (zzst paramArrayOfByte)
    {
      zzbg.e("The resource file is corrupted. The container cannot be extracted from the binary file");
      return null;
    }
    catch (zzrs.zzg paramArrayOfByte)
    {
      zzbg.zzaK("The resource file is invalid. The container from the binary file is invalid");
    }
    return null;
  }
  
  public void release()
  {
    try
    {
      this.zzbkn.shutdown();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void zzGl()
  {
    this.zzbkn.execute(new Runnable()
    {
      public void run()
      {
        zzcn.this.zzHc();
      }
    });
  }
  
  /* Error */
  void zzHc()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 140	com/google/android/gms/tagmanager/zzcn:zzbkg	Lcom/google/android/gms/tagmanager/zzbf;
    //   4: ifnonnull +13 -> 17
    //   7: new 142	java/lang/IllegalStateException
    //   10: dup
    //   11: ldc -112
    //   13: invokespecial 145	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_0
    //   18: getfield 140	com/google/android/gms/tagmanager/zzcn:zzbkg	Lcom/google/android/gms/tagmanager/zzbf;
    //   21: invokeinterface 150 1 0
    //   26: ldc -104
    //   28: invokestatic 112	com/google/android/gms/tagmanager/zzbg:v	(Ljava/lang/String;)V
    //   31: invokestatic 158	com/google/android/gms/tagmanager/zzcb:zzGU	()Lcom/google/android/gms/tagmanager/zzcb;
    //   34: invokevirtual 162	com/google/android/gms/tagmanager/zzcb:zzGV	()Lcom/google/android/gms/tagmanager/zzcb$zza;
    //   37: getstatic 168	com/google/android/gms/tagmanager/zzcb$zza:zzbjV	Lcom/google/android/gms/tagmanager/zzcb$zza;
    //   40: if_acmpeq +15 -> 55
    //   43: invokestatic 158	com/google/android/gms/tagmanager/zzcb:zzGU	()Lcom/google/android/gms/tagmanager/zzcb;
    //   46: invokevirtual 162	com/google/android/gms/tagmanager/zzcb:zzGV	()Lcom/google/android/gms/tagmanager/zzcb$zza;
    //   49: getstatic 171	com/google/android/gms/tagmanager/zzcb$zza:zzbjW	Lcom/google/android/gms/tagmanager/zzcb$zza;
    //   52: if_acmpne +32 -> 84
    //   55: aload_0
    //   56: getfield 28	com/google/android/gms/tagmanager/zzcn:zzbhM	Ljava/lang/String;
    //   59: invokestatic 158	com/google/android/gms/tagmanager/zzcb:zzGU	()Lcom/google/android/gms/tagmanager/zzcb;
    //   62: invokevirtual 175	com/google/android/gms/tagmanager/zzcb:getContainerId	()Ljava/lang/String;
    //   65: invokevirtual 181	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   68: ifeq +16 -> 84
    //   71: aload_0
    //   72: getfield 140	com/google/android/gms/tagmanager/zzcn:zzbkg	Lcom/google/android/gms/tagmanager/zzbf;
    //   75: getstatic 187	com/google/android/gms/tagmanager/zzbf$zza:zzbju	Lcom/google/android/gms/tagmanager/zzbf$zza;
    //   78: invokeinterface 190 2 0
    //   83: return
    //   84: new 192	java/io/FileInputStream
    //   87: dup
    //   88: aload_0
    //   89: invokevirtual 196	com/google/android/gms/tagmanager/zzcn:zzHd	()Ljava/io/File;
    //   92: invokespecial 199	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   95: astore_1
    //   96: new 47	java/io/ByteArrayOutputStream
    //   99: dup
    //   100: invokespecial 200	java/io/ByteArrayOutputStream:<init>	()V
    //   103: astore_2
    //   104: aload_1
    //   105: aload_2
    //   106: invokestatic 203	com/google/android/gms/internal/zzrs:zzb	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   109: aload_2
    //   110: invokevirtual 207	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   113: invokestatic 211	com/google/android/gms/internal/zzrq$zza:zzy	([B)Lcom/google/android/gms/internal/zzrq$zza;
    //   116: astore_2
    //   117: aload_0
    //   118: aload_2
    //   119: invokespecial 213	com/google/android/gms/tagmanager/zzcn:zzd	(Lcom/google/android/gms/internal/zzrq$zza;)V
    //   122: aload_0
    //   123: getfield 140	com/google/android/gms/tagmanager/zzcn:zzbkg	Lcom/google/android/gms/tagmanager/zzbf;
    //   126: aload_2
    //   127: invokeinterface 217 2 0
    //   132: aload_1
    //   133: invokevirtual 220	java/io/FileInputStream:close	()V
    //   136: ldc -34
    //   138: invokestatic 112	com/google/android/gms/tagmanager/zzbg:v	(Ljava/lang/String;)V
    //   141: return
    //   142: astore_1
    //   143: ldc -32
    //   145: invokestatic 65	com/google/android/gms/tagmanager/zzbg:zzaI	(Ljava/lang/String;)V
    //   148: aload_0
    //   149: getfield 140	com/google/android/gms/tagmanager/zzcn:zzbkg	Lcom/google/android/gms/tagmanager/zzbf;
    //   152: getstatic 187	com/google/android/gms/tagmanager/zzbf$zza:zzbju	Lcom/google/android/gms/tagmanager/zzbf$zza;
    //   155: invokeinterface 190 2 0
    //   160: return
    //   161: astore_1
    //   162: ldc -30
    //   164: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   167: goto -31 -> 136
    //   170: astore_2
    //   171: aload_0
    //   172: getfield 140	com/google/android/gms/tagmanager/zzcn:zzbkg	Lcom/google/android/gms/tagmanager/zzbf;
    //   175: getstatic 229	com/google/android/gms/tagmanager/zzbf$zza:zzbjv	Lcom/google/android/gms/tagmanager/zzbf$zza;
    //   178: invokeinterface 190 2 0
    //   183: ldc -25
    //   185: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   188: aload_1
    //   189: invokevirtual 220	java/io/FileInputStream:close	()V
    //   192: goto -56 -> 136
    //   195: astore_1
    //   196: ldc -30
    //   198: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   201: goto -65 -> 136
    //   204: astore_2
    //   205: aload_0
    //   206: getfield 140	com/google/android/gms/tagmanager/zzcn:zzbkg	Lcom/google/android/gms/tagmanager/zzbf;
    //   209: getstatic 229	com/google/android/gms/tagmanager/zzbf$zza:zzbjv	Lcom/google/android/gms/tagmanager/zzbf$zza;
    //   212: invokeinterface 190 2 0
    //   217: ldc -23
    //   219: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   222: aload_1
    //   223: invokevirtual 220	java/io/FileInputStream:close	()V
    //   226: goto -90 -> 136
    //   229: astore_1
    //   230: ldc -30
    //   232: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   235: goto -99 -> 136
    //   238: astore_2
    //   239: aload_1
    //   240: invokevirtual 220	java/io/FileInputStream:close	()V
    //   243: aload_2
    //   244: athrow
    //   245: astore_1
    //   246: ldc -30
    //   248: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   251: goto -8 -> 243
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	254	0	this	zzcn
    //   95	38	1	localFileInputStream	java.io.FileInputStream
    //   142	1	1	localFileNotFoundException	java.io.FileNotFoundException
    //   161	28	1	localIOException1	java.io.IOException
    //   195	28	1	localIOException2	java.io.IOException
    //   229	11	1	localIOException3	java.io.IOException
    //   245	1	1	localIOException4	java.io.IOException
    //   103	24	2	localObject1	Object
    //   170	1	2	localIOException5	java.io.IOException
    //   204	1	2	localIllegalArgumentException	IllegalArgumentException
    //   238	6	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   84	96	142	java/io/FileNotFoundException
    //   132	136	161	java/io/IOException
    //   96	132	170	java/io/IOException
    //   188	192	195	java/io/IOException
    //   96	132	204	java/lang/IllegalArgumentException
    //   222	226	229	java/io/IOException
    //   96	132	238	finally
    //   171	188	238	finally
    //   205	222	238	finally
    //   239	243	245	java/io/IOException
  }
  
  File zzHd()
  {
    String str = "resource_" + this.zzbhM;
    return new File(this.mContext.getDir("google_tagmanager", 0), str);
  }
  
  public void zza(zzbf<zzrq.zza> paramzzbf)
  {
    this.zzbkg = paramzzbf;
  }
  
  public void zzb(final zzrq.zza paramzza)
  {
    this.zzbkn.execute(new Runnable()
    {
      public void run()
      {
        zzcn.this.zzc(paramzza);
      }
    });
  }
  
  /* Error */
  boolean zzc(zzrq.zza paramzza)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 196	com/google/android/gms/tagmanager/zzcn:zzHd	()Ljava/io/File;
    //   4: astore_3
    //   5: new 266	java/io/FileOutputStream
    //   8: dup
    //   9: aload_3
    //   10: invokespecial 267	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   13: astore_2
    //   14: aload_2
    //   15: aload_1
    //   16: invokestatic 272	com/google/android/gms/internal/zzsu:toByteArray	(Lcom/google/android/gms/internal/zzsu;)[B
    //   19: invokevirtual 276	java/io/FileOutputStream:write	([B)V
    //   22: aload_2
    //   23: invokevirtual 277	java/io/FileOutputStream:close	()V
    //   26: iconst_1
    //   27: ireturn
    //   28: astore_1
    //   29: ldc_w 279
    //   32: invokestatic 117	com/google/android/gms/tagmanager/zzbg:e	(Ljava/lang/String;)V
    //   35: iconst_0
    //   36: ireturn
    //   37: astore_1
    //   38: ldc_w 281
    //   41: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   44: iconst_1
    //   45: ireturn
    //   46: astore_1
    //   47: ldc_w 283
    //   50: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   53: aload_3
    //   54: invokevirtual 287	java/io/File:delete	()Z
    //   57: pop
    //   58: aload_2
    //   59: invokevirtual 277	java/io/FileOutputStream:close	()V
    //   62: iconst_0
    //   63: ireturn
    //   64: astore_1
    //   65: ldc_w 281
    //   68: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   71: iconst_0
    //   72: ireturn
    //   73: astore_1
    //   74: aload_2
    //   75: invokevirtual 277	java/io/FileOutputStream:close	()V
    //   78: aload_1
    //   79: athrow
    //   80: astore_2
    //   81: ldc_w 281
    //   84: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   87: goto -9 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	zzcn
    //   0	90	1	paramzza	zzrq.zza
    //   13	62	2	localFileOutputStream	java.io.FileOutputStream
    //   80	1	2	localIOException	java.io.IOException
    //   4	50	3	localFile	File
    // Exception table:
    //   from	to	target	type
    //   5	14	28	java/io/FileNotFoundException
    //   22	26	37	java/io/IOException
    //   14	22	46	java/io/IOException
    //   58	62	64	java/io/IOException
    //   14	22	73	finally
    //   47	58	73	finally
    //   74	78	80	java/io/IOException
  }
  
  /* Error */
  public zzrs.zzc zzke(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 26	com/google/android/gms/tagmanager/zzcn:mContext	Landroid/content/Context;
    //   4: invokevirtual 295	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   7: iload_1
    //   8: invokevirtual 301	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   11: astore_3
    //   12: new 235	java/lang/StringBuilder
    //   15: dup
    //   16: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   19: ldc_w 303
    //   22: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: iload_1
    //   26: invokevirtual 306	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   29: ldc_w 308
    //   32: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: aload_0
    //   36: getfield 26	com/google/android/gms/tagmanager/zzcn:mContext	Landroid/content/Context;
    //   39: invokevirtual 295	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   42: iload_1
    //   43: invokevirtual 312	android/content/res/Resources:getResourceName	(I)Ljava/lang/String;
    //   46: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: ldc_w 314
    //   52: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: invokevirtual 244	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: invokestatic 112	com/google/android/gms/tagmanager/zzbg:v	(Ljava/lang/String;)V
    //   61: new 47	java/io/ByteArrayOutputStream
    //   64: dup
    //   65: invokespecial 200	java/io/ByteArrayOutputStream:<init>	()V
    //   68: astore_2
    //   69: aload_3
    //   70: aload_2
    //   71: invokestatic 203	com/google/android/gms/internal/zzrs:zzb	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   74: aload_0
    //   75: aload_2
    //   76: invokespecial 316	com/google/android/gms/tagmanager/zzcn:zza	(Ljava/io/ByteArrayOutputStream;)Lcom/google/android/gms/internal/zzrs$zzc;
    //   79: astore_3
    //   80: aload_3
    //   81: ifnull +37 -> 118
    //   84: ldc_w 318
    //   87: invokestatic 112	com/google/android/gms/tagmanager/zzbg:v	(Ljava/lang/String;)V
    //   90: aload_3
    //   91: areturn
    //   92: astore_2
    //   93: new 235	java/lang/StringBuilder
    //   96: dup
    //   97: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   100: ldc_w 320
    //   103: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: iload_1
    //   107: invokevirtual 306	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   110: invokevirtual 244	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   116: aconst_null
    //   117: areturn
    //   118: aload_0
    //   119: aload_2
    //   120: invokevirtual 207	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   123: invokespecial 322	com/google/android/gms/tagmanager/zzcn:zzx	([B)Lcom/google/android/gms/internal/zzrs$zzc;
    //   126: astore_2
    //   127: aload_2
    //   128: areturn
    //   129: astore_2
    //   130: new 235	java/lang/StringBuilder
    //   133: dup
    //   134: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   137: ldc_w 324
    //   140: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: iload_1
    //   144: invokevirtual 306	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   147: ldc_w 308
    //   150: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: aload_0
    //   154: getfield 26	com/google/android/gms/tagmanager/zzcn:mContext	Landroid/content/Context;
    //   157: invokevirtual 295	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   160: iload_1
    //   161: invokevirtual 312	android/content/res/Resources:getResourceName	(I)Ljava/lang/String;
    //   164: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: ldc_w 314
    //   170: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: invokevirtual 244	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   176: invokestatic 70	com/google/android/gms/tagmanager/zzbg:zzaK	(Ljava/lang/String;)V
    //   179: aconst_null
    //   180: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	181	0	this	zzcn
    //   0	181	1	paramInt	int
    //   68	8	2	localByteArrayOutputStream	ByteArrayOutputStream
    //   92	28	2	localNotFoundException	android.content.res.Resources.NotFoundException
    //   126	2	2	localzzc	zzrs.zzc
    //   129	1	2	localIOException	java.io.IOException
    //   11	80	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	12	92	android/content/res/Resources$NotFoundException
    //   61	80	129	java/io/IOException
    //   84	90	129	java/io/IOException
    //   118	127	129	java/io/IOException
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzcn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */