package com.microsoft.xbox.xle.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.microsoft.xbox.toolkit.XLERValueHelper;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.xle.app.XLEUtil;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;

public class IconFontSubTextButton
  extends LinearLayout
{
  private FrameLayout iconFrameLayout;
  private CustomTypefaceTextView iconTextView;
  private CustomTypefaceTextView subtitleTextView;
  private CustomTypefaceTextView titleTextView;
  
  public IconFontSubTextButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public IconFontSubTextButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public IconFontSubTextButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    LayoutInflater.from(paramContext).inflate(R.layout.icon_font_subtext_button, this, true);
    this.iconTextView = ((CustomTypefaceTextView)findViewById(R.id.icon_font_subtext_icon));
    this.iconFrameLayout = ((FrameLayout)findViewById(R.id.icon_font_subtext_btn_icon_bg));
    this.titleTextView = ((CustomTypefaceTextView)findViewById(R.id.icon_font_subtext_btn_title));
    this.subtitleTextView = ((CustomTypefaceTextView)findViewById(R.id.icon_font_subtext_btn_subtitle));
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, XLERValueHelper.getStyleableRValueArray("IconFontSubTextButton"));
    paramAttributeSet = paramContext.getString(XLERValueHelper.getStyleableRValue("IconFontSubTextButton_icon_uri"));
    String str1 = paramContext.getString(XLERValueHelper.getStyleableRValue("IconFontSubTextButton_text_title"));
    String str2 = paramContext.getString(XLERValueHelper.getStyleableRValue("IconFontSubTextButton_text_subtitle"));
    this.iconFrameLayout.setBackgroundColor(paramContext.getColor(XLERValueHelper.getStyleableRValue("IconFontSubTextButton_icon_bg"), 0));
    paramContext.recycle();
    XLEUtil.updateTextAndVisibilityIfNotNull(this.iconTextView, paramAttributeSet, 0);
    XLEUtil.updateTextAndVisibilityIfNotNull(this.titleTextView, str1, 0);
    XLEUtil.updateTextAndVisibilityIfNotNull(this.subtitleTextView, str2, 0);
    setFocusable(true);
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClickable(true);
    paramAccessibilityNodeInfo.setClassName(Button.class.getName());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\ui\IconFontSubTextButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */