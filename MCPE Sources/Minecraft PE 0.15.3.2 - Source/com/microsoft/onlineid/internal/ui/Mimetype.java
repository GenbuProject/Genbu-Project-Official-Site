package com.microsoft.onlineid.internal.ui;

import java.util.HashMap;

public enum Mimetype
{
  private static final HashMap<String, Mimetype> _map;
  private final String _mimetype;
  private final String _suffix;
  
  static
  {
    int i = 0;
    JAVASCRIPT = new Mimetype("JAVASCRIPT", 0, ".js", "application/javascript");
    PNG = new Mimetype("PNG", 1, ".png", "image/png");
    SVG = new Mimetype("SVG", 2, ".svg", "image/svg+xml");
    CSS = new Mimetype("CSS", 3, ".css", "text/css");
    FONT = new Mimetype("FONT", 4, ".woff", "application/x-font-woff");
    $VALUES = new Mimetype[] { JAVASCRIPT, PNG, SVG, CSS, FONT };
    _map = new HashMap();
    Mimetype[] arrayOfMimetype = values();
    int j = arrayOfMimetype.length;
    while (i < j)
    {
      Mimetype localMimetype = arrayOfMimetype[i];
      _map.put(localMimetype._suffix, localMimetype);
      i += 1;
    }
  }
  
  private Mimetype(String paramString1, String paramString2)
  {
    this._suffix = paramString1;
    this._mimetype = paramString2;
  }
  
  public static Mimetype findFromFilename(String paramString)
  {
    if (paramString != null)
    {
      int i = paramString.lastIndexOf('.');
      return (Mimetype)_map.get(paramString.substring(i));
    }
    return null;
  }
  
  public String toString()
  {
    return this._mimetype;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\ui\Mimetype.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */