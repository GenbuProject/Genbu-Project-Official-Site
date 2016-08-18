package com.microsoft.xbox.toolkit.anim;

import android.content.res.AssetManager;
import com.microsoft.xbox.toolkit.XMLHelper;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;

public class MAAS
{
  private static MAAS instance = new MAAS();
  private final String ASSET_FILENAME = "animation/%sAnimation.xml";
  private final String SDCARD_FILENAME = "/sdcard/bishop/maas/%sAnimation.xml";
  private Hashtable<String, MAASAnimation> maasFileCache = new Hashtable();
  private boolean usingSdcard = false;
  
  public static MAAS getInstance()
  {
    return instance;
  }
  
  private MAASAnimation getMAASFile(String paramString)
  {
    if (!this.maasFileCache.containsKey(paramString))
    {
      MAASAnimation localMAASAnimation = loadMAASFile(paramString);
      if (localMAASAnimation != null) {
        this.maasFileCache.put(paramString, localMAASAnimation);
      }
    }
    return (MAASAnimation)this.maasFileCache.get(paramString);
  }
  
  private MAASAnimation loadMAASFile(String paramString)
  {
    try
    {
      if (this.usingSdcard) {}
      for (paramString = new FileInputStream(new File(String.format("/sdcard/bishop/maas/%sAnimation.xml", new Object[] { paramString })));; paramString = XboxTcuiSdk.getAssetManager().open(paramString))
      {
        return (MAASAnimation)XMLHelper.instance().load(paramString, MAASAnimation.class);
        paramString = String.format("animation/%sAnimation.xml", new Object[] { paramString });
      }
      return null;
    }
    catch (Exception paramString) {}
  }
  
  public MAASAnimation getAnimation(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    return getMAASFile(paramString);
  }
  
  public static enum MAASAnimationType
  {
    ANIMATE_IN,  ANIMATE_OUT;
    
    private MAASAnimationType() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\anim\MAAS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */