package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.accessibility.CaptioningManager;
import android.view.accessibility.CaptioningManager.CaptionStyle;
import com.google.android.gms.cast.internal.zzf;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zzne;
import org.json.JSONException;
import org.json.JSONObject;

public final class TextTrackStyle
{
  public static final int COLOR_UNSPECIFIED = 0;
  public static final float DEFAULT_FONT_SCALE = 1.0F;
  public static final int EDGE_TYPE_DEPRESSED = 4;
  public static final int EDGE_TYPE_DROP_SHADOW = 2;
  public static final int EDGE_TYPE_NONE = 0;
  public static final int EDGE_TYPE_OUTLINE = 1;
  public static final int EDGE_TYPE_RAISED = 3;
  public static final int EDGE_TYPE_UNSPECIFIED = -1;
  public static final int FONT_FAMILY_CASUAL = 4;
  public static final int FONT_FAMILY_CURSIVE = 5;
  public static final int FONT_FAMILY_MONOSPACED_SANS_SERIF = 1;
  public static final int FONT_FAMILY_MONOSPACED_SERIF = 3;
  public static final int FONT_FAMILY_SANS_SERIF = 0;
  public static final int FONT_FAMILY_SERIF = 2;
  public static final int FONT_FAMILY_SMALL_CAPITALS = 6;
  public static final int FONT_FAMILY_UNSPECIFIED = -1;
  public static final int FONT_STYLE_BOLD = 1;
  public static final int FONT_STYLE_BOLD_ITALIC = 3;
  public static final int FONT_STYLE_ITALIC = 2;
  public static final int FONT_STYLE_NORMAL = 0;
  public static final int FONT_STYLE_UNSPECIFIED = -1;
  public static final int WINDOW_TYPE_NONE = 0;
  public static final int WINDOW_TYPE_NORMAL = 1;
  public static final int WINDOW_TYPE_ROUNDED = 2;
  public static final int WINDOW_TYPE_UNSPECIFIED = -1;
  private JSONObject zzaaU;
  private float zzaco;
  private int zzacp;
  private int zzacq;
  private int zzacr;
  private int zzacs;
  private int zzact;
  private int zzacu;
  private String zzacv;
  private int zzacw;
  private int zzacx;
  private int zzxO;
  
  public TextTrackStyle()
  {
    clear();
  }
  
  private void clear()
  {
    this.zzaco = 1.0F;
    this.zzacp = 0;
    this.zzxO = 0;
    this.zzacq = -1;
    this.zzacr = 0;
    this.zzacs = -1;
    this.zzact = 0;
    this.zzacu = 0;
    this.zzacv = null;
    this.zzacw = -1;
    this.zzacx = -1;
    this.zzaaU = null;
  }
  
  @TargetApi(19)
  public static TextTrackStyle fromSystemSettings(Context paramContext)
  {
    TextTrackStyle localTextTrackStyle = new TextTrackStyle();
    if (!zzne.zzsk()) {
      return localTextTrackStyle;
    }
    paramContext = (CaptioningManager)paramContext.getSystemService("captioning");
    localTextTrackStyle.setFontScale(paramContext.getFontScale());
    paramContext = paramContext.getUserStyle();
    localTextTrackStyle.setBackgroundColor(paramContext.backgroundColor);
    localTextTrackStyle.setForegroundColor(paramContext.foregroundColor);
    label117:
    boolean bool1;
    boolean bool2;
    switch (paramContext.edgeType)
    {
    default: 
      localTextTrackStyle.setEdgeType(0);
      localTextTrackStyle.setEdgeColor(paramContext.edgeColor);
      paramContext = paramContext.getTypeface();
      if (paramContext != null)
      {
        if (!Typeface.MONOSPACE.equals(paramContext)) {
          break label158;
        }
        localTextTrackStyle.setFontGenericFamily(1);
        bool1 = paramContext.isBold();
        bool2 = paramContext.isItalic();
        if ((!bool1) || (!bool2)) {
          break label202;
        }
        localTextTrackStyle.setFontStyle(3);
      }
      break;
    }
    for (;;)
    {
      return localTextTrackStyle;
      localTextTrackStyle.setEdgeType(1);
      break;
      localTextTrackStyle.setEdgeType(2);
      break;
      label158:
      if (Typeface.SANS_SERIF.equals(paramContext))
      {
        localTextTrackStyle.setFontGenericFamily(0);
        break label117;
      }
      if (Typeface.SERIF.equals(paramContext))
      {
        localTextTrackStyle.setFontGenericFamily(2);
        break label117;
      }
      localTextTrackStyle.setFontGenericFamily(0);
      break label117;
      label202:
      if (bool1) {
        localTextTrackStyle.setFontStyle(1);
      } else if (bool2) {
        localTextTrackStyle.setFontStyle(2);
      } else {
        localTextTrackStyle.setFontStyle(0);
      }
    }
  }
  
  private String zzL(int paramInt)
  {
    return String.format("#%02X%02X%02X%02X", new Object[] { Integer.valueOf(Color.red(paramInt)), Integer.valueOf(Color.green(paramInt)), Integer.valueOf(Color.blue(paramInt)), Integer.valueOf(Color.alpha(paramInt)) });
  }
  
  private int zzcd(String paramString)
  {
    int j = 0;
    int i = j;
    if (paramString != null)
    {
      i = j;
      if (paramString.length() == 9)
      {
        i = j;
        if (paramString.charAt(0) != '#') {}
      }
    }
    try
    {
      i = Integer.parseInt(paramString.substring(1, 3), 16);
      j = Integer.parseInt(paramString.substring(3, 5), 16);
      int k = Integer.parseInt(paramString.substring(5, 7), 16);
      i = Color.argb(Integer.parseInt(paramString.substring(7, 9), 16), i, j, k);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool3 = false;
    if (this == paramObject) {
      bool1 = true;
    }
    int i;
    int j;
    label51:
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool3;
        } while (!(paramObject instanceof TextTrackStyle));
        paramObject = (TextTrackStyle)paramObject;
        if (this.zzaaU != null) {
          break;
        }
        i = 1;
        if (((TextTrackStyle)paramObject).zzaaU != null) {
          break label218;
        }
        j = 1;
        bool1 = bool3;
      } while (i != j);
      if ((this.zzaaU == null) || (((TextTrackStyle)paramObject).zzaaU == null)) {
        break;
      }
      bool1 = bool3;
    } while (!zznb.zze(this.zzaaU, ((TextTrackStyle)paramObject).zzaaU));
    if ((this.zzaco == ((TextTrackStyle)paramObject).zzaco) && (this.zzacp == ((TextTrackStyle)paramObject).zzacp) && (this.zzxO == ((TextTrackStyle)paramObject).zzxO) && (this.zzacq == ((TextTrackStyle)paramObject).zzacq) && (this.zzacr == ((TextTrackStyle)paramObject).zzacr) && (this.zzacs == ((TextTrackStyle)paramObject).zzacs) && (this.zzacu == ((TextTrackStyle)paramObject).zzacu) && (zzf.zza(this.zzacv, ((TextTrackStyle)paramObject).zzacv)) && (this.zzacw == ((TextTrackStyle)paramObject).zzacw) && (this.zzacx == ((TextTrackStyle)paramObject).zzacx)) {}
    for (boolean bool1 = bool2;; bool1 = false)
    {
      return bool1;
      i = 0;
      break;
      label218:
      j = 0;
      break label51;
    }
  }
  
  public int getBackgroundColor()
  {
    return this.zzxO;
  }
  
  public JSONObject getCustomData()
  {
    return this.zzaaU;
  }
  
  public int getEdgeColor()
  {
    return this.zzacr;
  }
  
  public int getEdgeType()
  {
    return this.zzacq;
  }
  
  public String getFontFamily()
  {
    return this.zzacv;
  }
  
  public int getFontGenericFamily()
  {
    return this.zzacw;
  }
  
  public float getFontScale()
  {
    return this.zzaco;
  }
  
  public int getFontStyle()
  {
    return this.zzacx;
  }
  
  public int getForegroundColor()
  {
    return this.zzacp;
  }
  
  public int getWindowColor()
  {
    return this.zzact;
  }
  
  public int getWindowCornerRadius()
  {
    return this.zzacu;
  }
  
  public int getWindowType()
  {
    return this.zzacs;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Float.valueOf(this.zzaco), Integer.valueOf(this.zzacp), Integer.valueOf(this.zzxO), Integer.valueOf(this.zzacq), Integer.valueOf(this.zzacr), Integer.valueOf(this.zzacs), Integer.valueOf(this.zzact), Integer.valueOf(this.zzacu), this.zzacv, Integer.valueOf(this.zzacw), Integer.valueOf(this.zzacx), this.zzaaU });
  }
  
  public void setBackgroundColor(int paramInt)
  {
    this.zzxO = paramInt;
  }
  
  public void setCustomData(JSONObject paramJSONObject)
  {
    this.zzaaU = paramJSONObject;
  }
  
  public void setEdgeColor(int paramInt)
  {
    this.zzacr = paramInt;
  }
  
  public void setEdgeType(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 4)) {
      throw new IllegalArgumentException("invalid edgeType");
    }
    this.zzacq = paramInt;
  }
  
  public void setFontFamily(String paramString)
  {
    this.zzacv = paramString;
  }
  
  public void setFontGenericFamily(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 6)) {
      throw new IllegalArgumentException("invalid fontGenericFamily");
    }
    this.zzacw = paramInt;
  }
  
  public void setFontScale(float paramFloat)
  {
    this.zzaco = paramFloat;
  }
  
  public void setFontStyle(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 3)) {
      throw new IllegalArgumentException("invalid fontStyle");
    }
    this.zzacx = paramInt;
  }
  
  public void setForegroundColor(int paramInt)
  {
    this.zzacp = paramInt;
  }
  
  public void setWindowColor(int paramInt)
  {
    this.zzact = paramInt;
  }
  
  public void setWindowCornerRadius(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("invalid windowCornerRadius");
    }
    this.zzacu = paramInt;
  }
  
  public void setWindowType(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 2)) {
      throw new IllegalArgumentException("invalid windowType");
    }
    this.zzacs = paramInt;
  }
  
  public JSONObject toJson()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("fontScale", this.zzaco);
      if (this.zzacp != 0) {
        localJSONObject.put("foregroundColor", zzL(this.zzacp));
      }
      if (this.zzxO != 0) {
        localJSONObject.put("backgroundColor", zzL(this.zzxO));
      }
      switch (this.zzacq)
      {
      case 0: 
        if (this.zzacr != 0) {
          localJSONObject.put("edgeColor", zzL(this.zzacr));
        }
        switch (this.zzacs)
        {
        case 0: 
          label156:
          if (this.zzact != 0) {
            localJSONObject.put("windowColor", zzL(this.zzact));
          }
          if (this.zzacs == 2) {
            localJSONObject.put("windowRoundedCornerRadius", this.zzacu);
          }
          if (this.zzacv != null) {
            localJSONObject.put("fontFamily", this.zzacv);
          }
          switch (this.zzacw)
          {
          case 0: 
            label264:
            switch (this.zzacx)
            {
            }
            break;
          }
          break;
        }
        break;
      }
      for (;;)
      {
        if (this.zzaaU == null) {
          break label599;
        }
        localJSONObject.put("customData", this.zzaaU);
        return localJSONObject;
        localJSONObject.put("edgeType", "NONE");
        break;
        localJSONObject.put("edgeType", "OUTLINE");
        break;
        localJSONObject.put("edgeType", "DROP_SHADOW");
        break;
        localJSONObject.put("edgeType", "RAISED");
        break;
        localJSONObject.put("edgeType", "DEPRESSED");
        break;
        localJSONObject.put("windowType", "NONE");
        break label156;
        localJSONObject.put("windowType", "NORMAL");
        break label156;
        localJSONObject.put("windowType", "ROUNDED_CORNERS");
        break label156;
        localJSONObject.put("fontGenericFamily", "SANS_SERIF");
        break label264;
        localJSONObject.put("fontGenericFamily", "MONOSPACED_SANS_SERIF");
        break label264;
        localJSONObject.put("fontGenericFamily", "SERIF");
        break label264;
        localJSONObject.put("fontGenericFamily", "MONOSPACED_SERIF");
        break label264;
        localJSONObject.put("fontGenericFamily", "CASUAL");
        break label264;
        localJSONObject.put("fontGenericFamily", "CURSIVE");
        break label264;
        localJSONObject.put("fontGenericFamily", "SMALL_CAPITALS");
        break label264;
        localJSONObject.put("fontStyle", "NORMAL");
        continue;
        localJSONObject.put("fontStyle", "BOLD");
        continue;
        localJSONObject.put("fontStyle", "ITALIC");
        continue;
        localJSONObject.put("fontStyle", "BOLD_ITALIC");
        continue;
        break;
        break label156;
        break label264;
      }
      label599:
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
  
  public void zzg(JSONObject paramJSONObject)
    throws JSONException
  {
    clear();
    this.zzaco = ((float)paramJSONObject.optDouble("fontScale", 1.0D));
    this.zzacp = zzcd(paramJSONObject.optString("foregroundColor"));
    this.zzxO = zzcd(paramJSONObject.optString("backgroundColor"));
    String str;
    if (paramJSONObject.has("edgeType"))
    {
      str = paramJSONObject.getString("edgeType");
      if ("NONE".equals(str)) {
        this.zzacq = 0;
      }
    }
    else
    {
      this.zzacr = zzcd(paramJSONObject.optString("edgeColor"));
      if (paramJSONObject.has("windowType"))
      {
        str = paramJSONObject.getString("windowType");
        if (!"NONE".equals(str)) {
          break label325;
        }
        this.zzacs = 0;
      }
      label128:
      this.zzact = zzcd(paramJSONObject.optString("windowColor"));
      if (this.zzacs == 2) {
        this.zzacu = paramJSONObject.optInt("windowRoundedCornerRadius", 0);
      }
      this.zzacv = paramJSONObject.optString("fontFamily", null);
      if (paramJSONObject.has("fontGenericFamily"))
      {
        str = paramJSONObject.getString("fontGenericFamily");
        if (!"SANS_SERIF".equals(str)) {
          break label361;
        }
        this.zzacw = 0;
      }
      label208:
      if (paramJSONObject.has("fontStyle"))
      {
        str = paramJSONObject.getString("fontStyle");
        if (!"NORMAL".equals(str)) {
          break label470;
        }
        this.zzacx = 0;
      }
    }
    for (;;)
    {
      this.zzaaU = paramJSONObject.optJSONObject("customData");
      return;
      if ("OUTLINE".equals(str))
      {
        this.zzacq = 1;
        break;
      }
      if ("DROP_SHADOW".equals(str))
      {
        this.zzacq = 2;
        break;
      }
      if ("RAISED".equals(str))
      {
        this.zzacq = 3;
        break;
      }
      if (!"DEPRESSED".equals(str)) {
        break;
      }
      this.zzacq = 4;
      break;
      label325:
      if ("NORMAL".equals(str))
      {
        this.zzacs = 1;
        break label128;
      }
      if (!"ROUNDED_CORNERS".equals(str)) {
        break label128;
      }
      this.zzacs = 2;
      break label128;
      label361:
      if ("MONOSPACED_SANS_SERIF".equals(str))
      {
        this.zzacw = 1;
        break label208;
      }
      if ("SERIF".equals(str))
      {
        this.zzacw = 2;
        break label208;
      }
      if ("MONOSPACED_SERIF".equals(str))
      {
        this.zzacw = 3;
        break label208;
      }
      if ("CASUAL".equals(str))
      {
        this.zzacw = 4;
        break label208;
      }
      if ("CURSIVE".equals(str))
      {
        this.zzacw = 5;
        break label208;
      }
      if (!"SMALL_CAPITALS".equals(str)) {
        break label208;
      }
      this.zzacw = 6;
      break label208;
      label470:
      if ("BOLD".equals(str)) {
        this.zzacx = 1;
      } else if ("ITALIC".equals(str)) {
        this.zzacx = 2;
      } else if ("BOLD_ITALIC".equals(str)) {
        this.zzacx = 3;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\TextTrackStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */