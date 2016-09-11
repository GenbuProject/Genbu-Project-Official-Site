package com.microsoft.xbox.toolkit.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import com.microsoft.xbox.toolkit.XLERValueHelper;

public class XLEButton
  extends Button
{
  private boolean alwaysClickable;
  protected boolean disableSound = false;
  private int disabledTextColor;
  private int enabledTextColor;
  protected ButtonStateHandler stateHandler = new ButtonStateHandler();
  
  public XLEButton(Context paramContext)
  {
    super(paramContext);
    setSoundEffectsEnabled(false);
  }
  
  public XLEButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public XLEButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (isInEditMode()) {
      return;
    }
    setSoundEffectsEnabled(false);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, XLERValueHelper.getStyleableRValueArray("XLEButton"));
    try
    {
      this.stateHandler.setDisabled(localTypedArray.getBoolean(XLERValueHelper.getStyleableRValue("XLEButton_disabled"), false));
      this.stateHandler.setDisabledImageHandle(localTypedArray.getResourceId(XLERValueHelper.getStyleableRValue("XLEButton_disabledImage"), -1));
      this.stateHandler.setEnabledImageHandle(localTypedArray.getResourceId(XLERValueHelper.getStyleableRValue("XLEButton_enabledImage"), -1));
      this.stateHandler.setPressedImageHandle(localTypedArray.getResourceId(XLERValueHelper.getStyleableRValue("XLEButton_pressedImage"), -1));
      this.disableSound = localTypedArray.getBoolean(XLERValueHelper.getStyleableRValue("XLEButton_disableSound"), false);
      setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, XLERValueHelper.getStyleableRValueArray("CustomTypeface"));
      String str = paramAttributeSet.getString(XLERValueHelper.getStyleableRValue("CustomTypeface_typefaceSource"));
      paramAttributeSet.recycle();
      if ((str != null) && (str.length() > 0)) {
        applyCustomTypeface(paramContext, str);
      }
      this.enabledTextColor = getCurrentTextColor();
      this.disabledTextColor = localTypedArray.getColor(XLERValueHelper.getStyleableRValue("XLEButton_disabledTextColor"), this.enabledTextColor);
      this.alwaysClickable = localTypedArray.getBoolean(XLERValueHelper.getStyleableRValue("XLEButton_alwaysClickable"), false);
      if (this.alwaysClickable)
      {
        super.setEnabled(true);
        super.setClickable(true);
      }
      return;
    }
    finally
    {
      localTypedArray.recycle();
    }
  }
  
  private void applyCustomTypeface(Context paramContext, String paramString)
  {
    if (paramString != null) {
      setTypeface(FontManager.Instance().getTypeface(getContext(), paramString));
    }
  }
  
  private boolean hasSize()
  {
    return (getWidth() > 0) && (getHeight() > 0);
  }
  
  protected void onFinishInflate()
  {
    updateImage();
    setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        boolean bool = XLEButton.this.stateHandler.onTouch(paramAnonymousMotionEvent);
        XLEButton.this.updateImage();
        return bool;
      }
    });
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    boolean bool = false;
    if (hasSize()) {
      bool = this.stateHandler.onSizeChanged(getWidth(), getHeight());
    }
    if (bool) {
      updateImage();
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    if (!this.alwaysClickable) {
      super.setEnabled(paramBoolean);
    }
    if (this.stateHandler == null) {
      this.stateHandler = new ButtonStateHandler();
    }
    this.stateHandler.setEnabled(paramBoolean);
    updateImage();
    updateTextColor();
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    if (this.disableSound)
    {
      super.setOnClickListener(paramOnClickListener);
      return;
    }
    super.setOnClickListener(TouchUtil.createOnClickListener(paramOnClickListener));
  }
  
  public void setOnLongClickListener(View.OnLongClickListener paramOnLongClickListener)
  {
    if (this.disableSound)
    {
      super.setOnLongClickListener(paramOnLongClickListener);
      return;
    }
    super.setOnLongClickListener(TouchUtil.createOnLongClickListener(paramOnLongClickListener));
  }
  
  public void setPressedStateRunnable(ButtonStateHandler.ButtonStateHandlerRunnable paramButtonStateHandlerRunnable)
  {
    this.stateHandler.setPressedStateRunnable(paramButtonStateHandlerRunnable);
  }
  
  public void setTypeFace(String paramString)
  {
    applyCustomTypeface(getContext(), paramString);
  }
  
  protected void updateImage()
  {
    if (this.stateHandler.getImageDrawable() != null) {
      setBackgroundDrawable(this.stateHandler.getImageDrawable());
    }
  }
  
  protected void updateTextColor()
  {
    if (this.enabledTextColor != this.disabledTextColor) {
      if (!this.stateHandler.getDisabled()) {
        break label32;
      }
    }
    label32:
    for (int i = this.disabledTextColor;; i = this.enabledTextColor)
    {
      setTextColor(i);
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\XLEButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */