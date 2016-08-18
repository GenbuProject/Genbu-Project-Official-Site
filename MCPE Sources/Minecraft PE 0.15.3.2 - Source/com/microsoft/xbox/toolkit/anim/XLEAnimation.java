package com.microsoft.xbox.toolkit.anim;

import android.view.View;
import android.view.animation.Interpolator;
import com.microsoft.xbox.toolkit.ThreadManager;

public abstract class XLEAnimation
{
  protected Runnable endRunnable;
  
  public abstract void clear();
  
  public abstract void setInterpolator(Interpolator paramInterpolator);
  
  public void setOnAnimationEnd(final Runnable paramRunnable)
  {
    if (paramRunnable != null)
    {
      this.endRunnable = new Runnable()
      {
        public void run()
        {
          ThreadManager.UIThreadPost(paramRunnable);
        }
      };
      return;
    }
    this.endRunnable = null;
  }
  
  public abstract void setTargetView(View paramView);
  
  public abstract void start();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\anim\XLEAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */