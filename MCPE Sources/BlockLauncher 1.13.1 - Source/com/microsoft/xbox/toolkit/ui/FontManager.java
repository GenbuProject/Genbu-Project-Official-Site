package com.microsoft.xbox.toolkit.ui;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;

public class FontManager
{
  private static FontManager instance = new FontManager();
  private HashMap<String, Typeface> fonts;
  
  public static FontManager Instance()
  {
    return instance;
  }
  
  public Typeface getTypeface(Context paramContext, String paramString)
  {
    if (this.fonts == null) {
      this.fonts = new HashMap();
    }
    if (!this.fonts.containsKey(paramString))
    {
      paramContext = Typeface.createFromAsset(paramContext.getAssets(), paramString);
      this.fonts.put(paramString, paramContext);
    }
    return (Typeface)this.fonts.get(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\FontManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */