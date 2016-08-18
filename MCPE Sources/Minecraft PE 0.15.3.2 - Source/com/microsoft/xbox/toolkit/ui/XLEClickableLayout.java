package com.microsoft.xbox.toolkit.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.RelativeLayout;

public class XLEClickableLayout
  extends RelativeLayout
{
  public XLEClickableLayout(Context paramContext)
  {
    super(paramContext);
    setSoundEffectsEnabled(false);
  }
  
  public XLEClickableLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setSoundEffectsEnabled(false);
  }
  
  public XLEClickableLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setSoundEffectsEnabled(false);
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClickable(true);
    paramAccessibilityNodeInfo.setClassName(Button.class.getName());
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    super.setOnClickListener(TouchUtil.createOnClickListener(paramOnClickListener));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\XLEClickableLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */