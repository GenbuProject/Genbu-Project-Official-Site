package org.fmod;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.os.Build.VERSION;

public class FMOD
{
  private static Context gContext = null;
  
  public static boolean checkInit()
  {
    return gContext != null;
  }
  
  public static void close()
  {
    gContext = null;
  }
  
  public static AssetManager getAssetManager()
  {
    if (gContext != null) {
      return gContext.getAssets();
    }
    return null;
  }
  
  public static int getOutputBlockSize()
  {
    if ((gContext != null) && (Build.VERSION.SDK_INT >= 17))
    {
      String str = ((AudioManager)gContext.getSystemService("audio")).getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
      if (str != null) {
        return Integer.parseInt(str);
      }
    }
    return 0;
  }
  
  public static int getOutputSampleRate()
  {
    if ((gContext != null) && (Build.VERSION.SDK_INT >= 17))
    {
      String str = ((AudioManager)gContext.getSystemService("audio")).getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
      if (str != null) {
        return Integer.parseInt(str);
      }
    }
    return 0;
  }
  
  public static void init(Context paramContext)
  {
    gContext = paramContext;
  }
  
  public static boolean supportsLowLatency()
  {
    if ((gContext != null) && (Build.VERSION.SDK_INT >= 5)) {
      return gContext.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\org\fmod\FMOD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */