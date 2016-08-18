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


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\anim\ExponentialInterpolator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */