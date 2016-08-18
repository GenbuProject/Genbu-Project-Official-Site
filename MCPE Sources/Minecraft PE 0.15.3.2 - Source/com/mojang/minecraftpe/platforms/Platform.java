package com.mojang.minecraftpe.platforms;

import android.os.Build.VERSION;
import android.view.View;

public abstract class Platform
{
  public static Platform createPlatform(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return new Platform19(paramBoolean);
    }
    if (Build.VERSION.SDK_INT >= 21) {
      return new Platform21(paramBoolean);
    }
    return new Platform9();
  }
  
  public abstract String getABIS();
  
  public abstract void onAppStart(View paramView);
  
  public abstract void onViewFocusChanged(boolean paramBoolean);
  
  public abstract void onVolumePressed();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\mojang\minecraftpe\platforms\Platform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */