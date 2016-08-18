package com.microsoft.onlineid.ui;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import com.microsoft.onlineid.internal.ui.Fonts;
import com.microsoft.onlineid.sdk.R.styleable;

public class StyledTextView
  extends TextView
{
  public StyledTextView(Context paramContext)
  {
    super(paramContext);
  }
  
  public StyledTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    applyAttributes(paramContext, paramAttributeSet);
  }
  
  public StyledTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    applyAttributes(paramContext, paramAttributeSet);
  }
  
  private void applyAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramAttributeSet = paramContext.getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.StyledTextView, 0, 0);
    int i = 0;
    if (i < paramAttributeSet.getIndexCount())
    {
      int j = paramAttributeSet.getIndex(i);
      if (j == R.styleable.StyledTextView_font) {
        if (!isInEditMode())
        {
          String str = paramAttributeSet.getString(j);
          if (str != null) {
            setTypeface(Fonts.valueOf(str).getTypeface(paramContext));
          }
        }
      }
      for (;;)
      {
        i += 1;
        break;
        if (j == R.styleable.StyledTextView_isUnderlined) {
          if (paramAttributeSet.getBoolean(j, false)) {
            setPaintFlags(getPaintFlags() | 0x8);
          } else {
            setPaintFlags(getPaintFlags() & 0xFFFFFFF7);
          }
        }
      }
    }
    paramAttributeSet.recycle();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\ui\StyledTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */