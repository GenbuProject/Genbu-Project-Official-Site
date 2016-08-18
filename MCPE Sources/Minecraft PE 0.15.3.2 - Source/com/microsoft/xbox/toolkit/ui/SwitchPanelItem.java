package com.microsoft.xbox.toolkit.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.microsoft.xbox.toolkit.XLERValueHelper;

public class SwitchPanelItem
  extends FrameLayout
  implements SwitchPanel.SwitchPanelChild
{
  private final int INVALID_STATE_ID = -1;
  private int state;
  
  public SwitchPanelItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, XLERValueHelper.getStyleableRValueArray("SwitchPanelItem"));
    this.state = paramContext.getInteger(XLERValueHelper.getStyleableRValue("SwitchPanelItem_state"), -1);
    paramContext.recycle();
    if (this.state < 0) {
      throw new IllegalArgumentException("You must specify the state attribute in the xml, and the value must be positive.");
    }
    setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
  }
  
  public int getState()
  {
    return this.state;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\SwitchPanelItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */