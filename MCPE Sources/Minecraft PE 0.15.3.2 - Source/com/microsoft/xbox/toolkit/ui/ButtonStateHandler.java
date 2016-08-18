package com.microsoft.xbox.toolkit.ui;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

public class ButtonStateHandler
{
  protected boolean disabled = false;
  private XLEBitmap.XLEBitmapDrawable disabledImage = null;
  private int disabledImageHandle = -1;
  private XLEBitmap.XLEBitmapDrawable enabledImage = null;
  private int enabledImageHandle = -1;
  protected boolean pressed = false;
  private XLEBitmap.XLEBitmapDrawable pressedImage = null;
  private int pressedImageHandle = -1;
  private ButtonStateHandlerRunnable pressedStateRunnable = null;
  
  public boolean getDisabled()
  {
    return this.disabled;
  }
  
  public Drawable getImageDrawable()
  {
    if ((this.pressed) && (this.pressedImageHandle != -1)) {
      if (this.pressedImage != null) {}
    }
    do
    {
      do
      {
        return null;
        return this.pressedImage.getDrawable();
        if ((!this.disabled) || (this.disabledImageHandle == -1)) {
          break;
        }
      } while (this.disabledImage == null);
      return this.disabledImage.getDrawable();
    } while ((this.enabledImageHandle == -1) || (this.enabledImage == null));
    return this.enabledImage.getDrawable();
  }
  
  public boolean onSizeChanged(int paramInt1, int paramInt2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.disabledImage == null)
    {
      bool1 = bool2;
      if (this.disabledImageHandle != -1)
      {
        bool1 = true;
        this.disabledImage = TextureManager.Instance().loadScaledResourceDrawable(this.disabledImageHandle);
      }
    }
    bool2 = bool1;
    if (this.enabledImage == null)
    {
      bool2 = bool1;
      if (this.enabledImageHandle != -1)
      {
        bool2 = true;
        this.enabledImage = TextureManager.Instance().loadScaledResourceDrawable(this.enabledImageHandle);
      }
    }
    bool1 = bool2;
    if (this.pressedImage == null)
    {
      bool1 = bool2;
      if (this.pressedImageHandle != -1)
      {
        bool1 = true;
        this.pressedImage = TextureManager.Instance().loadScaledResourceDrawable(this.pressedImageHandle);
      }
    }
    return bool1;
  }
  
  public boolean onTouch(MotionEvent paramMotionEvent)
  {
    boolean bool = this.pressed;
    if (paramMotionEvent.getAction() == 0) {
      this.pressed = true;
    }
    for (;;)
    {
      if ((this.pressedStateRunnable != null) && (bool != this.pressed)) {
        this.pressedStateRunnable.onPressStateChanged(this.pressed);
      }
      return false;
      if (paramMotionEvent.getAction() == 1) {
        this.pressed = false;
      } else if (paramMotionEvent.getAction() == 3) {
        this.pressed = false;
      }
    }
  }
  
  public void setDisabled(boolean paramBoolean)
  {
    this.disabled = paramBoolean;
  }
  
  public void setDisabledImageHandle(int paramInt)
  {
    this.disabledImageHandle = paramInt;
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    if (!paramBoolean) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      this.disabled = paramBoolean;
      return;
    }
  }
  
  public void setEnabledImageHandle(int paramInt)
  {
    this.enabledImageHandle = paramInt;
  }
  
  public void setPressedImageHandle(int paramInt)
  {
    this.pressedImageHandle = paramInt;
  }
  
  public void setPressedStateRunnable(ButtonStateHandlerRunnable paramButtonStateHandlerRunnable)
  {
    this.pressedStateRunnable = paramButtonStateHandlerRunnable;
  }
  
  public static abstract interface ButtonStateHandlerRunnable
  {
    public abstract void onPressStateChanged(boolean paramBoolean);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\ButtonStateHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */