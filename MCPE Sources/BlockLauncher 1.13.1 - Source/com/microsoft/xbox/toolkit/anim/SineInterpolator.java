package com.microsoft.xbox.toolkit.anim;

public class SineInterpolator
  extends XLEInterpolator
{
  public SineInterpolator(EasingMode paramEasingMode)
  {
    super(paramEasingMode);
  }
  
  protected float getInterpolationCore(float paramFloat)
  {
    return (float)(1.0D - Math.sin(1.5707963267948966D * (1.0D - paramFloat)));
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\anim\SineInterpolator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */