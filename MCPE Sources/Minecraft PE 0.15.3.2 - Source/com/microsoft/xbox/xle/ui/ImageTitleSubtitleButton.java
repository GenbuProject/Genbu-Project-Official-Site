package com.microsoft.xbox.xle.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.XLERValueHelper;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.toolkit.ui.XLEUniversalImageView;
import com.microsoft.xbox.xle.app.XLEUtil;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;
import java.net.URI;

public class ImageTitleSubtitleButton
  extends LinearLayout
{
  private XLEUniversalImageView iconImageView;
  private CustomTypefaceTextView subtitleTextView;
  private CustomTypefaceTextView titleTextView;
  
  public ImageTitleSubtitleButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ImageTitleSubtitleButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ImageTitleSubtitleButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    LayoutInflater.from(paramContext).inflate(R.layout.image_title_subtitle_button, this, true);
    this.iconImageView = ((XLEUniversalImageView)findViewById(R.id.image_title_subtitle_button_image));
    this.titleTextView = ((CustomTypefaceTextView)findViewById(R.id.image_title_subtitle_button_title));
    this.subtitleTextView = ((CustomTypefaceTextView)findViewById(R.id.image_title_subtitle_button_subtitle));
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, XLERValueHelper.getStyleableRValueArray("ImageTitleSubtitleButton"));
    paramAttributeSet = paramContext.getString(XLERValueHelper.getStyleableRValue("ImageTitleSubtitleButton_image_uri"));
    String str1 = paramContext.getString(XLERValueHelper.getStyleableRValue("ImageTitleSubtitleButton_text_title"));
    String str2 = paramContext.getString(XLERValueHelper.getStyleableRValue("ImageTitleSubtitleButton_text_subtitle"));
    paramContext.recycle();
    setImageUri(paramAttributeSet);
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
  
  public void setImageUri(String paramString)
  {
    if (!JavaUtil.isNullOrEmpty(paramString)) {
      this.iconImageView.setImageURI2(URI.create(paramString));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\ui\ImageTitleSubtitleButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */