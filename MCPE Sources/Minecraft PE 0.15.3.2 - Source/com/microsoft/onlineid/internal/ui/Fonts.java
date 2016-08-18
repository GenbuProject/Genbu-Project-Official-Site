package com.microsoft.onlineid.internal.ui;

import android.content.Context;
import android.graphics.Typeface;

public enum Fonts
{
  SegoeUI("segoeui", null, 0),  SegoeUILight("segoeuil", "sans-serif-thin", 0),  SegoeUISemiBold("seguisb", null, 1);
  
  private String _fallbackFamilyName;
  private int _fallbackStyle;
  private String _filename;
  private boolean _loadFailed = false;
  private Typeface _typeface = null;
  
  private Fonts(String paramString1, String paramString2, int paramInt)
  {
    this._filename = paramString1;
    this._fallbackFamilyName = paramString2;
    this._fallbackStyle = paramInt;
  }
  
  public Typeface getTypeface(Context paramContext)
  {
    boolean bool = true;
    for (;;)
    {
      try
      {
        if ((this._typeface == null) && (!this._loadFailed)) {
          paramContext = paramContext.getAssets();
        }
        try
        {
          this._typeface = Typeface.createFromAsset(paramContext, String.format("fonts/%s.ttf", new Object[] { this._filename }));
          if (this._typeface == null)
          {
            this._loadFailed = bool;
            paramContext = this._typeface;
            return paramContext;
          }
        }
        catch (RuntimeException paramContext)
        {
          this._typeface = Typeface.create(this._fallbackFamilyName, this._fallbackStyle);
          continue;
        }
        bool = false;
      }
      finally {}
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\ui\Fonts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */