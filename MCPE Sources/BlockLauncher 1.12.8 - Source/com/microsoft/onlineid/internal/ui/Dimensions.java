package com.microsoft.onlineid.internal.ui;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.TouchDelegate;
import android.view.View;

public final class Dimensions
{
  private static final float MinimumTouchableTargetDp = 48.0F;
  
  public static int convertDipToPixels(float paramFloat, DisplayMetrics paramDisplayMetrics)
  {
    return Math.round(TypedValue.applyDimension(1, paramFloat, paramDisplayMetrics));
  }
  
  public static void ensureMinimumTouchTarget(View paramView1, final View paramView2, final DisplayMetrics paramDisplayMetrics)
  {
    paramView2.post(new Runnable()
    {
      public void run()
      {
        Rect localRect = new Rect();
        this.val$view.getHitRect(localRect);
        int k = localRect.width();
        int i = localRect.height();
        int j = Dimensions.convertDipToPixels(48.0F, paramDisplayMetrics);
        if (k < j)
        {
          k = (j - k + 1) / 2;
          localRect.left -= k;
          localRect.right = (k + localRect.right);
        }
        if (i < j)
        {
          i = (j - i + 1) / 2;
          localRect.top -= i;
          localRect.bottom = (i + localRect.bottom);
        }
        paramView2.setTouchDelegate(new TouchDelegate(localRect, this.val$view));
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\ui\Dimensions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */