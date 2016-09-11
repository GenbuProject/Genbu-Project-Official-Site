package com.microsoft.xbox.toolkit;

import android.app.ActivityManager;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import com.microsoft.xboxtcui.XboxTcuiSdk;

public class MemoryMonitor
{
  public static final int KB_TO_BYTES = 1024;
  public static final int MB_TO_BYTES = 1048576;
  public static final int MB_TO_KB = 1024;
  private static MemoryMonitor instance = new MemoryMonitor();
  private Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
  
  public static int getTotalPss()
  {
    try
    {
      Debug.getMemoryInfo(instance.memoryInfo);
      int i = instance.memoryInfo.getTotalPss();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static MemoryMonitor instance()
  {
    return instance;
  }
  
  public int getDalvikFreeKb()
  {
    try
    {
      Debug.getMemoryInfo(this.memoryInfo);
      int i = ((ActivityManager)XboxTcuiSdk.getSystemService("activity")).getMemoryClass();
      int j = getDalvikUsedKb();
      return i * 1024 - j;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getDalvikFreeMb()
  {
    try
    {
      int i = getDalvikFreeKb() / 1024;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getDalvikUsedKb()
  {
    try
    {
      Debug.getMemoryInfo(this.memoryInfo);
      int i = this.memoryInfo.dalvikPss;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getMemoryClass()
  {
    return ((ActivityManager)XboxTcuiSdk.getSystemService("activity")).getLargeMemoryClass();
  }
  
  public int getUsedKb()
  {
    try
    {
      Debug.getMemoryInfo(this.memoryInfo);
      int i = this.memoryInfo.dalvikPss;
      int j = this.memoryInfo.nativePss;
      return i + j;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\MemoryMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */