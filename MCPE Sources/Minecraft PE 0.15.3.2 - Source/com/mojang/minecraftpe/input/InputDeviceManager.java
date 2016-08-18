package com.mojang.minecraftpe.input;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;

public abstract class InputDeviceManager
{
  public static InputDeviceManager create(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return new JellyBeanDeviceManager(paramContext);
    }
    return new DefaultDeviceManager(null);
  }
  
  public abstract void register();
  
  public abstract void unregister();
  
  public static class DefaultDeviceManager
    extends InputDeviceManager
  {
    public void register()
    {
      Log.w("MCPE", "INPUT Noop register device manager");
    }
    
    public void unregister()
    {
      Log.w("MCPE", "INPUT Noop unregister device manager");
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\mojang\minecraftpe\input\InputDeviceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */