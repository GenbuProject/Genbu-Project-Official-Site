package de.ankri.text.method;

import android.graphics.Rect;
import android.view.View;

public abstract interface TransformationMethodCompat
{
  public abstract CharSequence getTransformation(CharSequence paramCharSequence, View paramView);
  
  public abstract void onFocusChanged(View paramView, CharSequence paramCharSequence, boolean paramBoolean, int paramInt, Rect paramRect);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\de\ankri\text\method\TransformationMethodCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */