package com.microsoft.xbox.toolkit.ui;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.text.TextPaint;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

public class XLETextTask
  extends AsyncTask<XLETextArg, Void, Bitmap>
{
  private static final String TAG = XLETextTask.class.getSimpleName();
  private final WeakReference<ImageView> img;
  private final int imgHeight;
  private final int imgWidth;
  
  public XLETextTask(ImageView paramImageView)
  {
    this.img = new WeakReference(paramImageView);
    this.imgWidth = paramImageView.getWidth();
    this.imgHeight = paramImageView.getHeight();
  }
  
  protected Bitmap doInBackground(XLETextArg... paramVarArgs)
  {
    Object localObject = null;
    String str;
    TextPaint localTextPaint;
    int n;
    int i1;
    int j;
    int i;
    if (paramVarArgs.length > 0)
    {
      localObject = paramVarArgs[0];
      paramVarArgs = ((XLETextArg)localObject).getParams();
      str = ((XLETextArg)localObject).getText();
      localTextPaint = new TextPaint();
      localTextPaint.setTextSize(paramVarArgs.getTextSize());
      localTextPaint.setAntiAlias(true);
      localTextPaint.setColor(paramVarArgs.getColor());
      localTextPaint.setTypeface(paramVarArgs.getTypeface());
      n = Math.round(localTextPaint.measureText(str));
      i1 = Math.round(localTextPaint.descent() - localTextPaint.ascent());
      if (!paramVarArgs.isAdjustForImageSize()) {
        break label288;
      }
      j = Math.max(n, this.imgWidth);
      i = Math.max(i1, this.imgHeight);
    }
    for (;;)
    {
      int k = i;
      int m = j;
      float f1;
      if (paramVarArgs.hasTextAspectRatio())
      {
        f1 = paramVarArgs.getTextAspectRatio().floatValue();
        k = i;
        m = j;
        if (f1 > 0.0F)
        {
          if (i <= j * f1) {
            break label273;
          }
          m = (int)(i / f1);
          k = i;
        }
      }
      for (;;)
      {
        localObject = Bitmap.createBitmap(m, k, Bitmap.Config.ARGB_8888);
        if (paramVarArgs.hasEraseColor()) {
          ((Bitmap)localObject).eraseColor(paramVarArgs.getEraseColor());
        }
        paramVarArgs = new Canvas((Bitmap)localObject);
        f1 = Math.max(0, m - n) / 2 + 0;
        float f2 = -localTextPaint.ascent();
        paramVarArgs.drawText(str, f1, Math.max(0, k - i1) / 2 + f2, localTextPaint);
        return (Bitmap)localObject;
        label273:
        k = (int)(j * f1);
        m = j;
      }
      label288:
      i = i1;
      j = n;
    }
  }
  
  protected void onPostExecute(Bitmap paramBitmap)
  {
    ImageView localImageView = (ImageView)this.img.get();
    if (localImageView != null) {
      localImageView.setImageBitmap(paramBitmap);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\XLETextTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */