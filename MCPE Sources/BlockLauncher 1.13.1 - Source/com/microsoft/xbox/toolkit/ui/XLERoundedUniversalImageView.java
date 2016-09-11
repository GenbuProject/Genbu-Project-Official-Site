package com.microsoft.xbox.toolkit.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

public class XLERoundedUniversalImageView
  extends XLEUniversalImageView
{
  public XLERoundedUniversalImageView(Context paramContext)
  {
    super(paramContext, new XLEUniversalImageView.Params());
  }
  
  public XLERoundedUniversalImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public XLERoundedUniversalImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public static Bitmap getRoundedCroppedBitmap(Bitmap paramBitmap, int paramInt)
  {
    Bitmap localBitmap;
    if (paramBitmap.getWidth() == paramInt)
    {
      localBitmap = paramBitmap;
      if (paramBitmap.getHeight() == paramInt) {}
    }
    else
    {
      localBitmap = Bitmap.createScaledBitmap(paramBitmap, paramInt, paramInt, false);
    }
    paramBitmap = Bitmap.createBitmap(localBitmap.getWidth(), localBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(paramBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, localBitmap.getWidth(), localBitmap.getHeight());
    localPaint.setAntiAlias(true);
    localPaint.setFilterBitmap(true);
    localPaint.setDither(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localCanvas.drawCircle(localBitmap.getWidth() / 2 + 0.7F, localBitmap.getHeight() / 2 + 0.7F, 0.1F + localBitmap.getWidth() / 2, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(localBitmap, localRect, localRect, localPaint);
    return paramBitmap;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    Object localObject = getDrawable();
    if (localObject == null) {}
    while ((getWidth() == 0) || (getHeight() == 0)) {
      return;
    }
    localObject = ((BitmapDrawable)localObject).getBitmap().copy(Bitmap.Config.ARGB_8888, true);
    int i = getWidth();
    getHeight();
    paramCanvas.drawBitmap(getRoundedCroppedBitmap((Bitmap)localObject, i), 0.0F, 0.0F, null);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\XLERoundedUniversalImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */