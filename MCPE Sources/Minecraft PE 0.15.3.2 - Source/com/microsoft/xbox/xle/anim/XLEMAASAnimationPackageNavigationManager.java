package com.microsoft.xbox.xle.anim;

import android.view.View;
import com.microsoft.xbox.toolkit.anim.MAAS.MAASAnimationType;
import com.microsoft.xbox.toolkit.anim.MAASAnimation;
import com.microsoft.xbox.toolkit.anim.XLEAnimation;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class XLEMAASAnimationPackageNavigationManager
  extends MAASAnimation
{
  @Element(required=false)
  public XLEMAASAnimationPackageDirection backward;
  @Element(required=false)
  public XLEMAASAnimationPackageDirection forward;
  
  public XLEAnimation compile(MAAS.MAASAnimationType paramMAASAnimationType, boolean paramBoolean, View paramView)
  {
    if (paramBoolean) {}
    for (XLEMAASAnimationPackageDirection localXLEMAASAnimationPackageDirection = this.backward; localXLEMAASAnimationPackageDirection == null; localXLEMAASAnimationPackageDirection = this.forward) {
      return null;
    }
    return localXLEMAASAnimationPackageDirection.compile(paramMAASAnimationType, paramView);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\anim\XLEMAASAnimationPackageNavigationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */