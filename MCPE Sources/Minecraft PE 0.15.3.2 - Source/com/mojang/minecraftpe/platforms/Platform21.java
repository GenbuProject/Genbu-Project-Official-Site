package com.mojang.minecraftpe.platforms;

import android.annotation.TargetApi;
import android.os.Build;

@TargetApi(21)
public class Platform21
  extends Platform19
{
  public Platform21(boolean paramBoolean)
  {
    super(paramBoolean);
  }
  
  public String getABIS()
  {
    return Build.SUPPORTED_ABIS.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\mojang\minecraftpe\platforms\Platform21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */