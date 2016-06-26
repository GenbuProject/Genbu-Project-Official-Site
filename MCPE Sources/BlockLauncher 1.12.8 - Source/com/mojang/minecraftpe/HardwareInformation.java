package com.mojang.minecraftpe;

import android.os.Build;
import android.os.Build.VERSION;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class HardwareInformation
{
  private static String cpuFeatures;
  private static String cpuName = "unknown";
  private static int numCores;
  
  static
  {
    cpuFeatures = "unknown";
    numCores = 1;
    initHardwareInformation();
  }
  
  public static String getAndroidVersion()
  {
    return "Android " + Build.VERSION.RELEASE;
  }
  
  public static String getCPUFeatures()
  {
    return cpuFeatures;
  }
  
  public static String getCPUName()
  {
    return cpuName;
  }
  
  public static String getCPUType()
  {
    return Build.CPU_ABI;
  }
  
  public static String getDeviceModelName()
  {
    return Build.MANUFACTURER.toUpperCase() + " " + Build.MODEL;
  }
  
  public static int getNumCores()
  {
    return numCores;
  }
  
  private static int getNumCoresReal()
  {
    FileFilter local1 = new FileFilter()
    {
      public boolean accept(File paramAnonymousFile)
      {
        return Pattern.matches("cpu[0-9]+", paramAnonymousFile.getName());
      }
    };
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(local1).length;
      return i;
    }
    catch (Exception localException) {}
    return Math.max(1, Runtime.getRuntime().availableProcessors());
  }
  
  public static void initHardwareInformation()
  {
    try
    {
      numCores = getNumCoresReal();
      parseCpuInfo();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  /* Error */
  private static void parseCpuInfo()
    throws java.io.IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: iconst_0
    //   4: istore_0
    //   5: iconst_0
    //   6: istore_1
    //   7: new 113	java/io/BufferedReader
    //   10: dup
    //   11: new 115	java/io/FileReader
    //   14: dup
    //   15: ldc 117
    //   17: invokespecial 118	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   20: invokespecial 121	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   23: astore 5
    //   25: aload 5
    //   27: invokevirtual 124	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   30: astore 4
    //   32: aload 4
    //   34: ifnull +85 -> 119
    //   37: aload 4
    //   39: ldc 126
    //   41: invokevirtual 130	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   44: ifeq -19 -> 25
    //   47: aload 4
    //   49: ldc 126
    //   51: invokevirtual 134	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   54: astore 6
    //   56: aload 6
    //   58: iconst_0
    //   59: aaload
    //   60: invokevirtual 137	java/lang/String:trim	()Ljava/lang/String;
    //   63: astore 4
    //   65: aload 6
    //   67: iconst_1
    //   68: aaload
    //   69: invokevirtual 137	java/lang/String:trim	()Ljava/lang/String;
    //   72: astore 6
    //   74: aload 4
    //   76: ldc -117
    //   78: invokevirtual 143	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   81: ifne +13 -> 94
    //   84: aload 4
    //   86: ldc -111
    //   88: invokevirtual 143	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   91: ifeq +39 -> 130
    //   94: aload 6
    //   96: putstatic 17	com/mojang/minecraftpe/HardwareInformation:cpuName	Ljava/lang/String;
    //   99: iconst_1
    //   100: istore_3
    //   101: iload_1
    //   102: istore_2
    //   103: iload_2
    //   104: istore_1
    //   105: iload_3
    //   106: istore_0
    //   107: iload_3
    //   108: ifeq -83 -> 25
    //   111: iload_2
    //   112: istore_1
    //   113: iload_3
    //   114: istore_0
    //   115: iload_2
    //   116: ifeq -91 -> 25
    //   119: aload 5
    //   121: ifnull +8 -> 129
    //   124: aload 5
    //   126: invokevirtual 148	java/io/BufferedReader:close	()V
    //   129: return
    //   130: aload 4
    //   132: ldc -106
    //   134: invokevirtual 143	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: ifne +17 -> 154
    //   140: iload_1
    //   141: istore_2
    //   142: iload_0
    //   143: istore_3
    //   144: aload 4
    //   146: ldc -104
    //   148: invokevirtual 143	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   151: ifeq -48 -> 103
    //   154: aload 6
    //   156: putstatic 19	com/mojang/minecraftpe/HardwareInformation:cpuFeatures	Ljava/lang/String;
    //   159: iconst_1
    //   160: istore_2
    //   161: iload_0
    //   162: istore_3
    //   163: goto -60 -> 103
    //   166: astore 4
    //   168: aload 6
    //   170: astore 5
    //   172: aload 5
    //   174: ifnull +8 -> 182
    //   177: aload 5
    //   179: invokevirtual 148	java/io/BufferedReader:close	()V
    //   182: aload 4
    //   184: athrow
    //   185: astore 4
    //   187: return
    //   188: astore 5
    //   190: goto -8 -> 182
    //   193: astore 4
    //   195: goto -23 -> 172
    // Local variable table:
    //   start	length	slot	name	signature
    //   4	158	0	i	int
    //   6	135	1	j	int
    //   102	59	2	k	int
    //   100	63	3	m	int
    //   30	115	4	str	String
    //   166	17	4	localObject1	Object
    //   185	1	4	localIOException1	java.io.IOException
    //   193	1	4	localObject2	Object
    //   23	155	5	localObject3	Object
    //   188	1	5	localIOException2	java.io.IOException
    //   1	168	6	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   7	25	166	finally
    //   124	129	185	java/io/IOException
    //   177	182	188	java/io/IOException
    //   25	32	193	finally
    //   37	94	193	finally
    //   94	99	193	finally
    //   130	140	193	finally
    //   144	154	193	finally
    //   154	159	193	finally
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\mojang\minecraftpe\HardwareInformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */