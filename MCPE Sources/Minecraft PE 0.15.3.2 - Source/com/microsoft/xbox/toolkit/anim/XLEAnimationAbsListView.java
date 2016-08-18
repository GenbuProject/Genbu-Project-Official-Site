package com.microsoft.xbox.toolkit.anim;

import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import com.microsoft.xbox.toolkit.XLEAssert;

public class XLEAnimationAbsListView
  extends XLEAnimation
{
  private LayoutAnimationController layoutAnimationController = null;
  private AbsListView layoutView = null;
  
  public XLEAnimationAbsListView(LayoutAnimationController paramLayoutAnimationController)
  {
    this.layoutAnimationController = paramLayoutAnimationController;
    if (this.layoutAnimationController != null) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      return;
    }
  }
  
  public void clear()
  {
    this.layoutView.setLayoutAnimationListener(null);
    this.layoutView.clearAnimation();
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.layoutAnimationController.setInterpolator(paramInterpolator);
  }
  
  public void setTargetView(View paramView)
  {
    XLEAssert.assertNotNull(paramView);
    XLEAssert.assertTrue(paramView instanceof AbsListView);
    this.layoutView = ((AbsListView)paramView);
  }
  
  public void start()
  {
    this.layoutView.setLayoutAnimation(this.layoutAnimationController);
    if (this.endRunnable != null) {
      this.endRunnable.run();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\anim\XLEAnimationAbsListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */