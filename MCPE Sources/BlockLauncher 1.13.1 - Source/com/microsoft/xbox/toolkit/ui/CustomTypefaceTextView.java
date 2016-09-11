package com.microsoft.xbox.toolkit.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.microsoft.xboxtcui.R.styleable;

public class CustomTypefaceTextView
  extends TextView
{
  public CustomTypefaceTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (!isInEditMode())
    {
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CustomTypeface);
      String str1 = paramAttributeSet.getString(R.styleable.CustomTypeface_typefaceSource);
      String str2 = paramAttributeSet.getString(R.styleable.CustomTypeface_uppercaseText);
      if (str2 != null) {
        setText(str2.toUpperCase());
      }
      applyCustomTypeface(paramContext, str1);
      paramAttributeSet.recycle();
    }
  }
  
  public CustomTypefaceTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (!isInEditMode())
    {
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CustomTypeface);
      applyCustomTypeface(paramContext, paramAttributeSet.getString(R.styleable.CustomTypeface_typefaceSource));
      paramAttributeSet.recycle();
    }
  }
  
  public CustomTypefaceTextView(Context paramContext, String paramString)
  {
    super(paramContext);
    applyCustomTypeface(paramContext, paramString);
  }
  
  private void applyCustomTypeface(Context paramContext, String paramString)
  {
    if (paramString != null) {
      setTypeface(FontManager.Instance().getTypeface(getContext(), paramString));
    }
    setCursorVisible(false);
  }
  
  public void setClickable(boolean paramBoolean)
  {
    if (paramBoolean) {
      throw new UnsupportedOperationException("If you want CustomTypefaceTextView to be clickable, use XLEButton instead.");
    }
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    throw new UnsupportedOperationException("If you want CustomTypefaceTextView to be clickable, use XLEButton instead.");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\CustomTypefaceTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */