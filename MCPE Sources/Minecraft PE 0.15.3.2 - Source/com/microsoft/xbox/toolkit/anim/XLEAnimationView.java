package com.microsoft.xbox.toolkit.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import com.microsoft.xbox.toolkit.ThreadManager;
import com.microsoft.xbox.toolkit.XLEAssert;
import java.util.Iterator;
import java.util.List;

public class XLEAnimationView
  extends XLEAnimation
{
  private Animation anim;
  private View animtarget;
  
  public XLEAnimationView(Animation paramAnimation)
  {
    this.anim = paramAnimation;
    this.anim.setFillAfter(true);
    this.anim.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        XLEAnimationView.this.onViewAnimationEnd();
        if (XLEAnimationView.this.endRunnable != null) {
          XLEAnimationView.this.endRunnable.run();
        }
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
        XLEAnimationView.this.onViewAnimationStart();
      }
    });
  }
  
  private void onViewAnimationEnd()
  {
    ThreadManager.UIThreadPost(new Runnable()
    {
      public void run()
      {
        XLEAnimationView.this.animtarget.setLayerType(0, null);
      }
    });
  }
  
  private void onViewAnimationStart()
  {
    this.animtarget.setLayerType(2, null);
  }
  
  public void clear()
  {
    this.anim.setAnimationListener(null);
    this.animtarget.clearAnimation();
  }
  
  public void setFillAfter(boolean paramBoolean)
  {
    this.anim.setFillAfter(paramBoolean);
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.anim.setInterpolator(paramInterpolator);
  }
  
  public void setTargetView(View paramView)
  {
    XLEAssert.assertNotNull(paramView);
    this.animtarget = paramView;
    if ((this.anim instanceof AnimationSet))
    {
      Iterator localIterator = ((AnimationSet)this.anim).getAnimations().iterator();
      while (localIterator.hasNext())
      {
        Animation localAnimation = (Animation)localIterator.next();
        if ((localAnimation instanceof HeightAnimation)) {
          ((HeightAnimation)localAnimation).setTargetView(paramView);
        }
      }
    }
  }
  
  public void start()
  {
    this.animtarget.startAnimation(this.anim);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\anim\XLEAnimationView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */