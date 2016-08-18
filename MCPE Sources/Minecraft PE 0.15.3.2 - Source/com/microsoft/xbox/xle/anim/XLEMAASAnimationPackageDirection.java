package com.microsoft.xbox.xle.anim;

import android.view.View;
import com.microsoft.xbox.toolkit.anim.MAAS.MAASAnimationType;
import com.microsoft.xbox.toolkit.anim.MAASAnimation;
import com.microsoft.xbox.toolkit.anim.XLEAnimation;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class XLEMAASAnimationPackageDirection
  extends MAASAnimation
{
  @Element(required=false)
  public XLEMAASAnimation inAnimation;
  @Element(required=false)
  public XLEMAASAnimation outAnimation;
  
  public XLEAnimation compile(MAAS.MAASAnimationType paramMAASAnimationType, View paramView)
  {
    if (paramMAASAnimationType == MAAS.MAASAnimationType.ANIMATE_IN) {}
    for (paramMAASAnimationType = this.inAnimation; paramMAASAnimationType == null; paramMAASAnimationType = this.outAnimation) {
      return null;
    }
    return paramMAASAnimationType.compile(paramView);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\anim\XLEMAASAnimationPackageDirection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */