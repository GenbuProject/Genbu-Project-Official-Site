package com.microsoft.onlineid.interop.xbox.toolkit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView
  extends ImageView
{
  static
  {
    if (!CircleImageView.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public CircleImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public CircleImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private Bitmap createBitmap(Drawable paramDrawable)
  {
    assert ((getWidth() > 0) && (getHeight() > 0));
    Bitmap localBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    paramDrawable.draw(localCanvas);
    return localBitmap;
  }
  
  private Bitmap createRoundBitmap(Bitmap paramBitmap, int paramInt)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramInt, paramInt, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    localPaint.setAntiAlias(true);
    localPaint.setFilterBitmap(true);
    localPaint.setDither(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localCanvas.drawCircle(paramBitmap.getWidth() / 2 + 0.7F, paramBitmap.getHeight() / 2 + 0.7F, 0.1F + paramBitmap.getWidth() / 2, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    return localBitmap;
  }
  
  private void drawBitmap(Canvas paramCanvas, Bitmap paramBitmap)
  {
    int i;
    if (paramBitmap != null)
    {
      i = Math.min(getWidth(), getHeight());
      if ((paramBitmap.getWidth() == i) && (paramBitmap.getHeight() == i)) {
        drawRoundBitmap(paramCanvas, paramBitmap, i);
      }
    }
    else
    {
      return;
    }
    paramBitmap = Bitmap.createScaledBitmap(paramBitmap, i, i, false);
    try
    {
      drawRoundBitmap(paramCanvas, paramBitmap, i);
      return;
    }
    finally
    {
      paramBitmap.recycle();
    }
  }
  
  private void drawRoundBitmap(Canvas paramCanvas, Bitmap paramBitmap, int paramInt)
  {
    paramBitmap = createRoundBitmap(paramBitmap, paramInt);
    try
    {
      paramCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, null);
      return;
    }
    finally
    {
      paramBitmap.recycle();
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if ((getWidth() == 0) || (getHeight() == 0)) {}
    do
    {
      return;
      localObject = getDrawable();
    } while (localObject == null);
    if ((localObject instanceof BitmapDrawable))
    {
      drawBitmap(paramCanvas, ((BitmapDrawable)localObject).getBitmap());
      return;
    }
    Object localObject = createBitmap((Drawable)localObject);
    try
    {
      drawBitmap(paramCanvas, (Bitmap)localObject);
      return;
    }
    finally
    {
      ((Bitmap)localObject).recycle();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\xbox\toolkit\CircleImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */