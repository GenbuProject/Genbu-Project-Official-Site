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
    if (0 + i > paramBitmap.getWidth()) {
      throw new IllegalArgumentException("x + width must be <= bitmap.width()");
    }
    if (0 + j > paramBitmap.getHeight()) {
      throw new IllegalArgumentException("y + height must be <= bitmap.height()");
    }
    if ((!paramBitmap.isMutable()) && (0 == 0) && (0 == 0) && (i == paramBitmap.getWidth()) && (j == paramBitmap.getHeight()) && ((localMatrix == null) || (localMatrix.isIdentity()))) {
      return paramBitmap;
    }
    Canvas localCanvas = new Canvas();
    Rect localRect = new Rect(0, 0, 0 + i, 0 + j);
    RectF localRectF = new RectF(0.0F, 0.0F, i, j);
    Object localObject2;
    Object localObject1;
    if ((localMatrix == null) || (localMatrix.isIdentity()))
    {
      localObject2 = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      localObject1 = null;
      ((Bitmap)localObject2).setDensity(paramBitmap.getDensity());
      localCanvas.setBitmap((Bitmap)localObject2);
      localCanvas.drawBitmap(paramBitmap, localRect, localRectF, (Paint)localObject1);
      return (Bitmap)localObject2;
    }
    if ((paramBitmap.hasAlpha()) || (!localMatrix.rectStaysRect())) {}
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      localObject1 = new RectF();
      localMatrix.mapRect((RectF)localObject1, localRectF);
      Bitmap localBitmap = Bitmap.createBitmap(Math.round(((RectF)localObject1).width()), Math.round(((RectF)localObject1).height()), Bitmap.Config.ARGB_8888);
      if (paramInt1 != 0) {
        localBitmap.eraseColor(0);
      }
      localCanvas.translate(-((RectF)localObject1).left, -((RectF)localObject1).top);
      localCanvas.concat(localMatrix);
      Paint localPaint = new Paint();
      localPaint.setFilterBitmap(paramBoolean);
      localObject2 = localBitmap;
      localObject1 = localPaint;
      if (localMatrix.rectStaysRect()) {
        break;
      }
      localPaint.setAntiAlias(true);
      localObject2 = localBitmap;
      localObject1 = localPaint;
      break;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\TextureResizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */