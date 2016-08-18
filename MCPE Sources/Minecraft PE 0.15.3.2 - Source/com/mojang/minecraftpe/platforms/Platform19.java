package com.mojang.minecraftpe.platforms;

import android.annotation.TargetApi;
import android.os.Handler;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;

@TargetApi(19)
public class Platform19
  extends Platform9
{
  private Runnable decorViewSettings;
  private View decoreView;
  private Handler eventHandler;
  
  public Platform19(boolean paramBoolean)
  {
    if (paramBoolean) {
      this.eventHandler = new Handler();
    }
  }
  
  public void onAppStart(View paramView)
  {
    if (this.eventHandler == null) {
      return;
    }
    this.decoreView = paramView;
    this.decoreView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
    {
      public void onSystemUiVisibilityChange(int paramAnonymousInt)
      {
        Platform19.this.eventHandler.postDelayed(Platform19.this.decorViewSettings, 500L);
      }
    });
    this.decorViewSettings = new Runnable()
    {
      public void run()
      {
        Platform19.this.decoreView.setSystemUiVisibility(5894);
      }
    };
    this.eventHandler.post(this.decorViewSettings);
  }
  
  public void onViewFocusChanged(boolean paramBoolean)
  {
    if ((this.eventHandler != null) && (paramBoolean)) {
      this.eventHandler.postDelayed(this.decorViewSettings, 500L);
    }
  }
  
  public void onVolumePressed() {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\mojang\minecraftpe\platforms\Platform19.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */