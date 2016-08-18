package com.microsoft.xbox.toolkit.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class HeightAnimation
  extends Animation
{
  private int fromValue;
  private int toValue;
  private View view;
  
  public HeightAnimation(int paramInt1, int paramInt2)
  {
    this.fromValue = paramInt1;
    this.toValue = paramInt2;
  }
  
  protected void applyTransformation(float paramFloat, Transformation paramTransformation)
  {
    int i = (int)((this.toValue - this.fromValue) * paramFloat);
    this.view.getLayoutParams().height = (this.fromValue + i);
    this.view.requestLayout();
  }
  
  public void setTargetView(View paramView)
  {
    this.view = paramView;
    this.fromValue = paramView.getHeight();
  }
  
  public boolean willChangeBounds()
  {
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\anim\HeightAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */