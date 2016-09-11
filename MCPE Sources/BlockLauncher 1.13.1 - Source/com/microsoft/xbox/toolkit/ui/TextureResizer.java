package com.microsoft.xbox.toolkit.ui;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class TextureResizer
{
  public static Bitmap createScaledBitmap8888(Bitmap paramBitmap, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f1 = paramInt1 / i;
    float f2 = paramInt2 / j;
    Matrix localMatrix = new Matrix();
    localMatrix.setScale(f1, f2);
    if (i + 0 > paramBitmap.getWidth()) {
      throw new IllegalArgumentException("x + width must be <= bitmap.width()");
    }
    if (j + 0 > paramBitmap.getHeight()) {
      throw new IllegalArgumentException("y + height must be <= bitmap.height()");
    }
    if ((!paramBitmap.isMutable()) && (i == paramBitmap.getWidth()) && (j == paramBitmap.getHeight()) && ((localMatrix == null) || (localMatrix.isIdentity()))) {
      return paramBitmap;
    }
    Canvas localCanvas = new Canvas();
    Rect localRect = new Rect(0, 0, i + 0, j + 0);
    RectF localRectF = new RectF(0.0F, 0.0F, i, j);
    Bitmap localBitmap;
    Object localObject;
    if ((localMatrix == null) || (localMatrix.isIdentity()))
    {
      localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      localObject = null;
    }
    label363:
    for (;;)
    {
      localBitmap.setDensity(paramBitmap.getDensity());
      localCanvas.setBitmap(localBitmap);
      localCanvas.drawBitmap(paramBitmap, localRect, localRectF, (Paint)localObject);
      return localBitmap;
      if ((paramBitmap.hasAlpha()) || (!localMatrix.rectStaysRect())) {}
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        localObject = new RectF();
        localMatrix.mapRect((RectF)localObject, localRectF);
        localBitmap = Bitmap.createBitmap(Math.round(((RectF)localObject).width()), Math.round(((RectF)localObject).height()), Bitmap.Config.ARGB_8888);
        if (paramInt1 != 0) {
          localBitmap.eraseColor(0);
        }
        localCanvas.translate(-((RectF)localObject).left, -((RectF)localObject).top);
        localCanvas.concat(localMatrix);
        localObject = new Paint();
        ((Paint)localObject).setFilterBitmap(paramBoolean);
        if (localMatrix.rectStaysRect()) {
          break label363;
        }
        ((Paint)localObject).setAntiAlias(true);
        break;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\TextureResizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */