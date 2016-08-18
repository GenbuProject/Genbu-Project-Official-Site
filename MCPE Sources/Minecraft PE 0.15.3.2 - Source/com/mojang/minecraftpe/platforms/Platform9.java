package com.mojang.minecraftpe.platforms;

import android.os.Build;
import android.view.View;

public class Platform9
  extends Platform
{
  public String getABIS()
  {
    return Build.CPU_ABI;
  }
  
  public void onAppStart(View paramView) {}
  
  public void onViewFocusChanged(boolean paramBoolean) {}
  
  public void onVolumePressed() {}
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\mojang\minecraftpe\platforms\Platform9.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */