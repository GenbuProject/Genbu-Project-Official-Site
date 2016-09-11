package com.microsoft.xbox.xle.anim;

import android.content.res.Resources;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.microsoft.xbox.toolkit.XLERValueHelper;
import com.microsoft.xbox.toolkit.anim.AnimationFunctionType;
import com.microsoft.xbox.toolkit.anim.AnimationProperty;
import com.microsoft.xbox.toolkit.anim.BackEaseInterpolator;
import com.microsoft.xbox.toolkit.anim.EasingMode;
import com.microsoft.xbox.toolkit.anim.ExponentialInterpolator;
import com.microsoft.xbox.toolkit.anim.HeightAnimation;
import com.microsoft.xbox.toolkit.anim.SineInterpolator;
import com.microsoft.xbox.toolkit.anim.XLEInterpolator;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import org.simpleframework.xml.Attribute;

public class XLEAnimationDefinition
{
  @Attribute(required=false)
  public int delayMs;
  @Attribute(required=false)
  public String dimen;
  @Attribute(required=false)
  public int durationMs;
  @Attribute(required=false)
  public EasingMode easing = EasingMode.EaseIn;
  @Attribute(required=false)
  public float from;
  @Attribute(required=false)
  public int fromXType = 1;
  @Attribute(required=false)
  public int fromYType = 1;
  @Attribute(required=false)
  public float parameter;
  @Attribute(required=false)
  public float pivotX = 0.5F;
  @Attribute(required=false)
  public float pivotY = 0.5F;
  @Attribute(required=false)
  public AnimationProperty property;
  @Attribute(required=false)
  public boolean scaleRelativeToSelf = true;
  @Attribute(required=false)
  public float to;
  @Attribute(required=false)
  public int toXType = 1;
  @Attribute(required=false)
  public int toYType = 1;
  @Attribute(required=false)
  public AnimationFunctionType type;
  
  private Interpolator getInterpolator()
  {
    switch (this.type)
    {
    default: 
      return new XLEInterpolator(this.easing);
    case ???: 
      return new SineInterpolator(this.easing);
    case ???: 
      return new ExponentialInterpolator(this.parameter, this.easing);
    }
    return new BackEaseInterpolator(this.parameter, this.easing);
  }
  
  public Animation getAnimation()
  {
    Interpolator localInterpolator = getInterpolator();
    Object localObject;
    switch (1.$SwitchMap$com$microsoft$xbox$toolkit$anim$AnimationProperty[this.property.ordinal()])
    {
    default: 
      localObject = null;
    }
    while (localObject != null)
    {
      ((Animation)localObject).setDuration(this.durationMs);
      ((Animation)localObject).setInterpolator(localInterpolator);
      ((Animation)localObject).setStartOffset(this.delayMs);
      return (Animation)localObject;
      localObject = new AlphaAnimation(this.from, this.to);
      continue;
      float f1 = this.from;
      float f2 = this.to;
      float f3 = this.from;
      float f4 = this.to;
      label141:
      float f5;
      if (this.scaleRelativeToSelf)
      {
        i = 1;
        f5 = this.pivotX;
        if (!this.scaleRelativeToSelf) {
          break label190;
        }
      }
      label190:
      for (int j = 1;; j = 2)
      {
        localObject = new ScaleAnimation(f1, f2, f3, f4, i, f5, j, this.pivotY);
        break;
        i = 2;
        break label141;
      }
      localObject = new TranslateAnimation(this.fromXType, this.from, this.toXType, this.to, 1, 0.0F, 1, 0.0F);
      continue;
      localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, this.fromYType, this.from, this.toYType, this.to);
      continue;
      i = XLERValueHelper.findDimensionIdByName(this.dimen);
      if (i < 0) {
        break label301;
      }
    }
    label301:
    for (int i = XboxTcuiSdk.getResources().getDimensionPixelSize(i);; i = 0)
    {
      localObject = new HeightAnimation(0, i);
      break;
      return null;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\anim\XLEAnimationDefinition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */