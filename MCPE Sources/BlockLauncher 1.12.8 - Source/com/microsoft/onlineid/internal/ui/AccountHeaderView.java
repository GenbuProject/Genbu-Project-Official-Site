package com.microsoft.onlineid.internal.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.view.View;

public class AccountHeaderView
{
  public static final float MarginLargeDip = 16.0F;
  public static final float MarginLogoDip = 9.3F;
  public static final float MarginMediumDip = 8.0F;
  public static final float SizeLogoDip = 32.0F;
  public static final int TextColorTitle = Color.rgb(88, 88, 88);
  public static final float TextSizeLargeSP = 16.0F;
  
  public static void applyStyle(Activity paramActivity, CharSequence paramCharSequence)
  {
    ActionBar localActionBar = paramActivity.getActionBar();
    if (localActionBar == null) {
      return;
    }
    paramCharSequence = paramCharSequence.toString();
    SpannableString localSpannableString = new SpannableString(paramCharSequence);
    localSpannableString.setSpan(new CustomTypefaceSpan(Fonts.SegoeUISemiBold.getTypeface(paramActivity.getApplicationContext())), 0, paramCharSequence.length(), 18);
    localActionBar.setTitle(localSpannableString);
    paramCharSequence = paramActivity.findViewById(16908332);
    paramCharSequence.setPadding(0, 0, 0, 0);
    int i = Dimensions.convertDipToPixels(32.0F, paramActivity.getResources().getDisplayMetrics());
    paramActivity = paramCharSequence.getLayoutParams();
    paramActivity.height = i;
    paramActivity.width = i;
  }
  
  private static class CustomTypefaceSpan
    extends MetricAffectingSpan
  {
    private Typeface _typeface;
    
    public CustomTypefaceSpan(Typeface paramTypeface)
    {
      this._typeface = paramTypeface;
    }
    
    public void updateDrawState(TextPaint paramTextPaint)
    {
      paramTextPaint.setTypeface(this._typeface);
      paramTextPaint.setFlags(paramTextPaint.getFlags() | 0x80);
    }
    
    public void updateMeasureState(TextPaint paramTextPaint)
    {
      paramTextPaint.setTypeface(this._typeface);
      paramTextPaint.setFlags(paramTextPaint.getFlags() | 0x80);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\ui\AccountHeaderView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */