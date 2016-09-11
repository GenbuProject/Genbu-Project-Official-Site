package com.microsoft.xbox.toolkit.anim;

public class ExponentialInterpolator
  extends XLEInterpolator
{
  private float exponent;
  
  public ExponentialInterpolator(float paramFloat, EasingMode paramEasingMode)
  {
    super(paramEasingMode);
    this.exponent = paramFloat;
  }
  
  protected float getInterpolationCore(float paramFloat)
  {
    return (float)((Math.pow(2.718281828459045D, this.exponent * paramFloat) - 1.0D) / (Math.pow(2.718281828459045D, this.exponent) - 1.0D));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\anim\ExponentialInterpolator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */