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
    return (float)(1.0D - Math.sin((1.0D - paramFloat) * 1.5707963267948966D));
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\anim\SineInterpolator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */