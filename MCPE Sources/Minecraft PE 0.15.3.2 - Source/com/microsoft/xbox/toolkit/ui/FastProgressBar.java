package com.microsoft.xbox.toolkit.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class FastProgressBar
  extends ProgressBar
{
  private boolean isEnabled;
  private int visibility;
  
  public FastProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setEnabled(true);
    setVisibility(0);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    try
    {
      super.onDraw(paramCanvas);
      postInvalidateDelayed(33L);
      return;
    }
    finally
    {
      paramCanvas = finally;
      throw paramCanvas;
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    if (this.isEnabled != paramBoolean)
    {
      this.isEnabled = paramBoolean;
      if (!this.isEnabled)
      {
        this.visibility = getVisibility();
        super.setVisibility(8);
      }
    }
    else
    {
      return;
    }
    super.setVisibility(this.visibility);
  }
  
  public void setVisibility(int paramInt)
  {
    if (this.isEnabled)
    {
      super.setVisibility(paramInt);
      return;
    }
    this.visibility = paramInt;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\FastProgressBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */