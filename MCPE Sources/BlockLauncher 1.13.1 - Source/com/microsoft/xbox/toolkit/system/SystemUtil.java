package com.microsoft.xbox.toolkit.system;

import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SystemUtil
{
  private static final int MAX_SD_SCREEN_PIXELS = 384000;
  
  public static int DIPtoPixels(float paramFloat)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, XboxTcuiSdk.getResources().getDisplayMetrics());
  }
  
  public static int SPtoPixels(float paramFloat)
  {
    return (int)TypedValue.applyDimension(2, paramFloat, XboxTcuiSdk.getResources().getDisplayMetrics());
  }
  
  public static boolean TEST_randomFalseOutOf(int paramInt)
  {
    XLEAssert.assertTrue(false);
    return true;
  }
  
  public static void TEST_randomSleep(int paramInt)
  {
    XLEAssert.assertTrue(false);
  }
  
  public static int getColorDepth()
  {
    PixelFormat.getPixelFormatInfo(1, null);
    throw new NullPointerException();
  }
  
  public static String getDeviceId()
  {
    return Settings.Secure.getString(XboxTcuiSdk.getContentResolver(), "android_id");
  }
  
  public static String getDeviceModelName()
  {
    return Build.MODEL;
  }
  
  public static String getDeviceType()
  {
    XLEAssert.assertTrue(false);
    return "";
  }
  
  private static Display getDisplay()
  {
    return ((WindowManager)XboxTcuiSdk.getSystemService("window")).getDefaultDisplay();
  }
  
  public static String getMACAddress(String paramString)
  {
    try
    {
      Object localObject = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
      while (((Iterator)localObject).hasNext())
      {
        NetworkInterface localNetworkInterface = (NetworkInterface)((Iterator)localObject).next();
        if ((paramString == null) || (localNetworkInterface.getName().equalsIgnoreCase(paramString)))
        {
          paramString = localNetworkInterface.getHardwareAddress();
          if (paramString == null) {
            return "";
          }
          localObject = new StringBuilder();
          int i = 0;
          while (i < paramString.length)
          {
            ((StringBuilder)localObject).append(String.format("%02X:", new Object[] { Byte.valueOf(paramString[i]) }));
            i += 1;
          }
          if (((StringBuilder)localObject).length() > 0) {
            ((StringBuilder)localObject).deleteCharAt(((StringBuilder)localObject).length() - 1);
          }
          paramString = ((StringBuilder)localObject).toString();
          return paramString;
        }
      }
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static int getOrientation()
  {
    int i = 2;
    int j = getRotation();
    if ((j == 0) || (j == 2)) {
      i = 1;
    }
    return i;
  }
  
  public static int getRotation()
  {
    return getDisplay().getRotation();
  }
  
  public static int getScreenHeight()
  {
    return XboxTcuiSdk.getResources().getDisplayMetrics().heightPixels;
  }
  
  public static float getScreenHeightInches()
  {
    DisplayMetrics localDisplayMetrics = XboxTcuiSdk.getResources().getDisplayMetrics();
    return getScreenHeight() / localDisplayMetrics.ydpi;
  }
  
  public static int getScreenWidth()
  {
    return XboxTcuiSdk.getResources().getDisplayMetrics().widthPixels;
  }
  
  public static float getScreenWidthHeightAspectRatio()
  {
    int i = getScreenWidth();
    int j = getScreenHeight();
    if ((i <= 0) || (j <= 0)) {
      return 0.0F;
    }
    if (i > j) {
      return i / j;
    }
    return j / i;
  }
  
  public static float getScreenWidthInches()
  {
    DisplayMetrics localDisplayMetrics = XboxTcuiSdk.getResources().getDisplayMetrics();
    return getScreenWidth() / localDisplayMetrics.xdpi;
  }
  
  public static int getSdkInt()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static float getYDPI()
  {
    return XboxTcuiSdk.getResources().getDisplayMetrics().ydpi;
  }
  
  public static boolean isHDScreen()
  {
    return getScreenHeight() * getScreenWidth() > 384000;
  }
  
  public static boolean isKindle()
  {
    String str = Build.MANUFACTURER;
    return (str != null) && ("AMAZON".compareToIgnoreCase(str) == 0);
  }
  
  public static boolean isSDCardAvailable()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean isSlate()
  {
    float f1 = getScreenWidthInches();
    float f2 = getScreenHeightInches();
    double d = Math.pow(f1, 2.0D);
    return Math.sqrt(Math.pow(f2, 2.0D) + d) > 6.0D;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\system\SystemUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */