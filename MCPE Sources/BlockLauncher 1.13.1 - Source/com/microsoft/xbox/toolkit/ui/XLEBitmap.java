package com.microsoft.xbox.toolkit.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import java.io.InputStream;

public class XLEBitmap
{
  public static String ALLOCATION_TAG = "XLEBITMAP";
  private Bitmap bitmapSrc = null;
  
  private XLEBitmap(Bitmap paramBitmap)
  {
    this.bitmapSrc = paramBitmap;
  }
  
  public static XLEBitmap createBitmap(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    return createBitmap(Bitmap.createBitmap(paramInt1, paramInt2, paramConfig));
  }
  
  public static XLEBitmap createBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      return null;
    }
    return new XLEBitmap(paramBitmap);
  }
  
  public static XLEBitmap createScaledBitmap(XLEBitmap paramXLEBitmap, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    return createBitmap(Bitmap.createScaledBitmap(paramXLEBitmap.bitmapSrc, paramInt1, paramInt2, paramBoolean));
  }
  
  public static XLEBitmap createScaledBitmap8888(XLEBitmap paramXLEBitmap, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    return createBitmap(TextureResizer.createScaledBitmap8888(paramXLEBitmap.bitmapSrc, paramInt1, paramInt2, paramBoolean));
  }
  
  public static XLEBitmap decodeResource(Resources paramResources, int paramInt)
  {
    return createBitmap(BitmapFactory.decodeResource(paramResources, paramInt));
  }
  
  public static XLEBitmap decodeResource(Resources paramResources, int paramInt, BitmapFactory.Options paramOptions)
  {
    return createBitmap(BitmapFactory.decodeResource(paramResources, paramInt, paramOptions));
  }
  
  public static XLEBitmap decodeStream(InputStream paramInputStream)
  {
    return createBitmap(BitmapFactory.decodeStream(paramInputStream));
  }
  
  public static XLEBitmap decodeStream(InputStream paramInputStream, BitmapFactory.Options paramOptions)
  {
    return createBitmap(BitmapFactory.decodeStream(paramInputStream, null, paramOptions));
  }
  
  public void finalize() {}
  
  public Bitmap getBitmap()
  {
    return this.bitmapSrc;
  }
  
  public int getByteCount()
  {
    return this.bitmapSrc.getRowBytes() * this.bitmapSrc.getHeight();
  }
  
  public XLEBitmapDrawable getDrawable()
  {
    return new XLEBitmapDrawable(new BitmapDrawable(this.bitmapSrc));
  }
  
  public static class XLEBitmapDrawable
  {
    private BitmapDrawable drawable;
    
    public XLEBitmapDrawable(BitmapDrawable paramBitmapDrawable)
    {
      this.drawable = paramBitmapDrawable;
    }
    
    public BitmapDrawable getDrawable()
    {
      return this.drawable;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\XLEBitmap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */