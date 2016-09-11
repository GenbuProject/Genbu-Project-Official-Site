package com.microsoft.xbox.xle.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.microsoft.xbox.toolkit.XLERValueHelper;
import com.microsoft.xbox.toolkit.ui.FontManager;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;

public class IconFontToggleButton
  extends LinearLayout
  implements Checkable
{
  private boolean checked;
  private String checkedIcon;
  private String checkedText;
  private TextView iconTextView;
  private TextView labelTextView;
  private String uncheckedIcon;
  private String uncheckedText;
  
  public IconFontToggleButton(Context paramContext)
  {
    super(paramContext);
  }
  
  public IconFontToggleButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initViews(paramContext, paramAttributeSet);
  }
  
  public IconFontToggleButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initViews(paramContext, paramAttributeSet);
  }
  
  private void applyCustomTypeface(Context paramContext, String paramString)
  {
    if ((paramString != null) && (this.labelTextView != null))
    {
      paramContext = FontManager.Instance().getTypeface(getContext(), paramString);
      this.labelTextView.setTypeface(paramContext);
    }
  }
  
  private void initViews(Context paramContext, AttributeSet paramAttributeSet)
  {
    ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.iconfont_toggle_btn_view, this, true);
    this.iconTextView = ((TextView)findViewById(R.id.iconfont_toggle_btn_icon));
    this.labelTextView = ((TextView)findViewById(R.id.iconfont_toggle_btn_text));
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, XLERValueHelper.getStyleableRValueArray("CustomTypeface"));
    String str = localTypedArray.getString(XLERValueHelper.getStyleableRValue("CustomTypeface_typefaceSource"));
    localTypedArray.recycle();
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, XLERValueHelper.getStyleableRValueArray("IconFontToggleButton"));
    this.checkedText = paramAttributeSet.getString(XLERValueHelper.getStyleableRValue("IconFontToggleButton_text_checked"));
    this.uncheckedText = paramAttributeSet.getString(XLERValueHelper.getStyleableRValue("IconFontToggleButton_text_unchecked"));
    this.checkedIcon = paramAttributeSet.getString(XLERValueHelper.getStyleableRValue("IconFontToggleButton_icon_checked"));
    this.uncheckedIcon = paramAttributeSet.getString(XLERValueHelper.getStyleableRValue("IconFontToggleButton_icon_unchecked"));
    float f = paramAttributeSet.getDimensionPixelSize(XLERValueHelper.getStyleableRValue("IconFontToggleButton_icon_size"), -1);
    if (f != -1.0F) {
      this.iconTextView.setTextSize(0, f);
    }
    paramAttributeSet.recycle();
    applyCustomTypeface(paramContext, str);
    setFocusable(true);
  }
  
  public boolean isChecked()
  {
    return this.checked;
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClickable(true);
    paramAccessibilityNodeInfo.setClassName(Button.class.getName());
  }
  
  public void setChecked(boolean paramBoolean)
  {
    this.checked = paramBoolean;
    sendAccessibilityEvent(1);
    TextView localTextView;
    if (this.labelTextView != null)
    {
      localTextView = this.labelTextView;
      if (this.checked)
      {
        str = this.checkedText;
        localTextView.setText(str);
        this.labelTextView.setVisibility(0);
      }
    }
    else if (this.iconTextView != null)
    {
      localTextView = this.iconTextView;
      if (!this.checked) {
        break label97;
      }
    }
    label97:
    for (String str = this.checkedIcon;; str = this.uncheckedIcon)
    {
      localTextView.setText(str);
      this.iconTextView.setVisibility(0);
      invalidate();
      return;
      str = this.uncheckedText;
      break;
    }
  }
  
  public void setCheckedText(String paramString)
  {
    this.checkedText = paramString;
  }
  
  public void setUncheckedText(String paramString)
  {
    this.uncheckedText = paramString;
  }
  
  public void toggle()
  {
    if (!this.checked) {}
    for (boolean bool = true;; bool = false)
    {
      setChecked(bool);
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\ui\IconFontToggleButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */