package com.microsoft.xbox.toolkit.ui;

import android.graphics.Typeface;
import com.microsoft.xbox.toolkit.system.SystemUtil;

public class XLETextArg
{
  private final Params params;
  private final String text;
  
  public XLETextArg(Params paramParams)
  {
    this(null, paramParams);
  }
  
  public XLETextArg(String paramString, Params paramParams)
  {
    this.text = paramString;
    this.params = paramParams;
  }
  
  public Params getParams()
  {
    return this.params;
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public boolean hasText()
  {
    return this.text != null;
  }
  
  public static class Params
  {
    private final boolean adjustForImageSize;
    private final int color;
    private final int eraseColor;
    private final Float textAspectRatio;
    private final float textSize;
    private final Typeface typeface;
    
    public Params()
    {
      this(SystemUtil.SPtoPixels(8.0F), -1, Typeface.DEFAULT, 0, false, null);
    }
    
    public Params(float paramFloat, int paramInt1, Typeface paramTypeface, int paramInt2, boolean paramBoolean, Float paramFloat1)
    {
      this.textSize = paramFloat;
      this.color = paramInt1;
      this.typeface = paramTypeface;
      this.eraseColor = paramInt2;
      this.adjustForImageSize = paramBoolean;
      this.textAspectRatio = paramFloat1;
    }
    
    public int getColor()
    {
      return this.color;
    }
    
    public int getEraseColor()
    {
      return this.eraseColor;
    }
    
    public Float getTextAspectRatio()
    {
      return this.textAspectRatio;
    }
    
    public float getTextSize()
    {
      return this.textSize;
    }
    
    public Typeface getTypeface()
    {
      return this.typeface;
    }
    
    public boolean hasEraseColor()
    {
      return this.eraseColor != 0;
    }
    
    public boolean hasTextAspectRatio()
    {
      return this.textAspectRatio != null;
    }
    
    public boolean isAdjustForImageSize()
    {
      return this.adjustForImageSize;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\XLETextArg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */