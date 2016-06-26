package net.zhuoweizhang.mcpelauncher;

import android.opengl.GLES20;
import android.os.Environment;
import com.mojang.minecraftpe.MainActivity;
import java.io.File;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScreenshotHelper
{
  public static void takeScreenshot(String paramString)
  {
    int[] arrayOfInt = new int[4];
    GLES20.glGetIntegerv(2978, arrayOfInt, 0);
    ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(arrayOfInt[2] * arrayOfInt[3] * 4);
    GLES20.glReadPixels(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3], 6408, 5121, localByteBuffer);
    new Thread(new ScreenshotWriter(arrayOfInt, localByteBuffer, paramString)).start();
  }
  
  private static final class ScreenshotWriter
    implements Runnable
  {
    private ByteBuffer buf;
    private String fileName;
    private int[] screenDim;
    
    public ScreenshotWriter(int[] paramArrayOfInt, ByteBuffer paramByteBuffer, String paramString)
    {
      this.screenDim = paramArrayOfInt;
      this.buf = paramByteBuffer;
      this.fileName = paramString;
    }
    
    private File createOutputFile(String paramString)
    {
      File localFile2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "BlockLauncher");
      localFile2.mkdirs();
      String str = new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.US).format(new Date());
      File localFile1 = new File(localFile2, paramString + "-" + str + ".png");
      int i = 1;
      while (localFile1.exists())
      {
        i += 1;
        localFile1 = new File(localFile2, paramString + "-" + str + "_" + i + ".png");
      }
      return localFile1;
    }
    
    private void runCallBack(File paramFile)
    {
      if (MainActivity.currentMainActivity != null)
      {
        MainActivity localMainActivity = (MainActivity)MainActivity.currentMainActivity.get();
        if (localMainActivity != null) {
          localMainActivity.screenshotCallback(paramFile);
        }
      }
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 22	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:screenDim	[I
      //   4: iconst_2
      //   5: iaload
      //   6: istore_1
      //   7: aload_0
      //   8: getfield 22	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:screenDim	[I
      //   11: iconst_3
      //   12: iaload
      //   13: istore_2
      //   14: iload_1
      //   15: iload_2
      //   16: getstatic 117	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
      //   19: invokestatic 123	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
      //   22: astore 7
      //   24: aload_0
      //   25: getfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   28: invokevirtual 129	java/nio/ByteBuffer:rewind	()Ljava/nio/Buffer;
      //   31: pop
      //   32: iload_1
      //   33: iconst_4
      //   34: imul
      //   35: iconst_2
      //   36: imul
      //   37: newarray <illegal type>
      //   39: astore 4
      //   41: iload_1
      //   42: iconst_4
      //   43: imul
      //   44: istore_3
      //   45: iconst_0
      //   46: istore_1
      //   47: iload_1
      //   48: iload_2
      //   49: iconst_2
      //   50: idiv
      //   51: if_icmpge +110 -> 161
      //   54: aload_0
      //   55: getfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   58: iload_1
      //   59: iload_3
      //   60: imul
      //   61: invokevirtual 133	java/nio/ByteBuffer:position	(I)Ljava/nio/Buffer;
      //   64: pop
      //   65: aload_0
      //   66: getfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   69: aload 4
      //   71: iconst_0
      //   72: iload_3
      //   73: invokevirtual 136	java/nio/ByteBuffer:get	([BII)Ljava/nio/ByteBuffer;
      //   76: pop
      //   77: aload_0
      //   78: getfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   81: iload_2
      //   82: iload_1
      //   83: isub
      //   84: iconst_1
      //   85: isub
      //   86: iload_3
      //   87: imul
      //   88: invokevirtual 133	java/nio/ByteBuffer:position	(I)Ljava/nio/Buffer;
      //   91: pop
      //   92: aload_0
      //   93: getfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   96: aload 4
      //   98: iload_3
      //   99: iload_3
      //   100: invokevirtual 136	java/nio/ByteBuffer:get	([BII)Ljava/nio/ByteBuffer;
      //   103: pop
      //   104: aload_0
      //   105: getfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   108: iload_2
      //   109: iload_1
      //   110: isub
      //   111: iconst_1
      //   112: isub
      //   113: iload_3
      //   114: imul
      //   115: invokevirtual 133	java/nio/ByteBuffer:position	(I)Ljava/nio/Buffer;
      //   118: pop
      //   119: aload_0
      //   120: getfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   123: aload 4
      //   125: iconst_0
      //   126: iload_3
      //   127: invokevirtual 139	java/nio/ByteBuffer:put	([BII)Ljava/nio/ByteBuffer;
      //   130: pop
      //   131: aload_0
      //   132: getfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   135: iload_1
      //   136: iload_3
      //   137: imul
      //   138: invokevirtual 133	java/nio/ByteBuffer:position	(I)Ljava/nio/Buffer;
      //   141: pop
      //   142: aload_0
      //   143: getfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   146: aload 4
      //   148: iload_3
      //   149: iload_3
      //   150: invokevirtual 139	java/nio/ByteBuffer:put	([BII)Ljava/nio/ByteBuffer;
      //   153: pop
      //   154: iload_1
      //   155: iconst_1
      //   156: iadd
      //   157: istore_1
      //   158: goto -111 -> 47
      //   161: aload_0
      //   162: getfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   165: invokevirtual 129	java/nio/ByteBuffer:rewind	()Ljava/nio/Buffer;
      //   168: pop
      //   169: aload 7
      //   171: aload_0
      //   172: getfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   175: invokevirtual 143	android/graphics/Bitmap:copyPixelsFromBuffer	(Ljava/nio/Buffer;)V
      //   178: aload_0
      //   179: aconst_null
      //   180: putfield 24	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:buf	Ljava/nio/ByteBuffer;
      //   183: aload_0
      //   184: aload_0
      //   185: getfield 26	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:fileName	Ljava/lang/String;
      //   188: invokespecial 145	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:createOutputFile	(Ljava/lang/String;)Ljava/io/File;
      //   191: astore 8
      //   193: aconst_null
      //   194: astore 4
      //   196: aconst_null
      //   197: astore 6
      //   199: new 147	java/io/FileOutputStream
      //   202: dup
      //   203: aload 8
      //   205: invokespecial 149	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   208: astore 5
      //   210: aload 7
      //   212: getstatic 155	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
      //   215: bipush 100
      //   217: aload 5
      //   219: invokevirtual 159	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
      //   222: pop
      //   223: aload 5
      //   225: ifnull +103 -> 328
      //   228: aload 5
      //   230: invokevirtual 162	java/io/FileOutputStream:close	()V
      //   233: aload 7
      //   235: invokevirtual 165	android/graphics/Bitmap:recycle	()V
      //   238: invokestatic 170	java/lang/System:gc	()V
      //   241: aload_0
      //   242: aload 8
      //   244: invokespecial 172	net/zhuoweizhang/mcpelauncher/ScreenshotHelper$ScreenshotWriter:runCallBack	(Ljava/io/File;)V
      //   247: return
      //   248: astore 4
      //   250: goto -17 -> 233
      //   253: astore 4
      //   255: aload 6
      //   257: astore 5
      //   259: aload 4
      //   261: astore 6
      //   263: aload 5
      //   265: astore 4
      //   267: aload 6
      //   269: invokevirtual 175	java/io/IOException:printStackTrace	()V
      //   272: aload 5
      //   274: ifnull -41 -> 233
      //   277: aload 5
      //   279: invokevirtual 162	java/io/FileOutputStream:close	()V
      //   282: goto -49 -> 233
      //   285: astore 4
      //   287: goto -54 -> 233
      //   290: astore 5
      //   292: aload 4
      //   294: ifnull +8 -> 302
      //   297: aload 4
      //   299: invokevirtual 162	java/io/FileOutputStream:close	()V
      //   302: aload 5
      //   304: athrow
      //   305: astore 4
      //   307: goto -5 -> 302
      //   310: astore 6
      //   312: aload 5
      //   314: astore 4
      //   316: aload 6
      //   318: astore 5
      //   320: goto -28 -> 292
      //   323: astore 6
      //   325: goto -62 -> 263
      //   328: goto -95 -> 233
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	331	0	this	ScreenshotWriter
      //   6	152	1	i	int
      //   13	98	2	j	int
      //   44	106	3	k	int
      //   39	156	4	arrayOfByte	byte[]
      //   248	1	4	localIOException1	java.io.IOException
      //   253	7	4	localIOException2	java.io.IOException
      //   265	1	4	localObject1	Object
      //   285	13	4	localIOException3	java.io.IOException
      //   305	1	4	localIOException4	java.io.IOException
      //   314	1	4	localObject2	Object
      //   208	70	5	localObject3	Object
      //   290	23	5	localObject4	Object
      //   318	1	5	localObject5	Object
      //   197	71	6	localIOException5	java.io.IOException
      //   310	7	6	localObject6	Object
      //   323	1	6	localIOException6	java.io.IOException
      //   22	212	7	localBitmap	android.graphics.Bitmap
      //   191	52	8	localFile	File
      // Exception table:
      //   from	to	target	type
      //   228	233	248	java/io/IOException
      //   199	210	253	java/io/IOException
      //   277	282	285	java/io/IOException
      //   199	210	290	finally
      //   267	272	290	finally
      //   297	302	305	java/io/IOException
      //   210	223	310	finally
      //   210	223	323	java/io/IOException
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ScreenshotHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */