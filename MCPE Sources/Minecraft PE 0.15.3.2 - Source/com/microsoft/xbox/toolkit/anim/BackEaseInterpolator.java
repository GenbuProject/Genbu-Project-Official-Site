package com.microsoft.xbox.toolkit.anim;

public class BackEaseInterpolator
  extends XLEInterpolator
{
  private float amplitude;
  
  public BackEaseInterpolator(float paramFloat, EasingMode paramEasingMode)
  {
    super(paramEasingMode);
    this.amplitude = paramFloat;
  }
  
  protected float getInterpolationCore(float paramFloat)
  {
    paramFloat = (float)Math.max(paramFloat, 0.0D);
    return (float)(paramFloat * paramFloat * paramFloat - this.amplitude * paramFloat * Math.sin(paramFloat * 3.141592653589793D));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\anim\BackEaseInterpolator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */