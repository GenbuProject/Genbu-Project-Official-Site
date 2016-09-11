package com.microsoft.xbox.toolkit.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class XLEImageView
  extends ImageView
{
  public static final int IMAGE_ERROR = 2;
  public static final int IMAGE_FINAL = 0;
  public static final int IMAGE_LOADING = 1;
  public String TEST_loadingOrLoadedImageUrl;
  protected boolean isFinal = false;
  protected boolean shouldAnimate = true;
  
  public XLEImageView(Context paramContext)
  {
    this(paramContext, null, 0);
  }
  
  public XLEImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public XLEImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setSoundEffectsEnabled(false);
  }
  
  public boolean getShouldAnimate()
  {
    return (this.shouldAnimate) && (!this.isFinal);
  }
  
  public void setFinal(boolean paramBoolean)
  {
    this.isFinal = paramBoolean;
  }
  
  public void setImageBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      return;
    }
    super.setImageBitmap(paramBitmap);
  }
  
  public void setImageSource(Bitmap paramBitmap, int paramInt)
  {
    if (paramBitmap == null) {
      return;
    }
    super.setImageBitmap(paramBitmap);
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    super.setOnClickListener(TouchUtil.createOnClickListener(paramOnClickListener));
  }
  
  public void setShouldAnimate(boolean paramBoolean)
  {
    this.shouldAnimate = paramBoolean;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\XLEImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */