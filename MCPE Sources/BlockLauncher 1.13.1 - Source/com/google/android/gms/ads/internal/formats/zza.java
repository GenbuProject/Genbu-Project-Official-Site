package com.google.android.gms.ads.internal.formats;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.google.android.gms.internal.zzhb;
import java.util.List;

@zzhb
public class zza
{
  private static final int zzxI = Color.rgb(12, 174, 206);
  private static final int zzxJ = Color.rgb(204, 204, 204);
  static final int zzxK = zzxJ;
  static final int zzxL = zzxI;
  private final int mTextColor;
  private final String zzxM;
  private final List<Drawable> zzxN;
  private final int zzxO;
  private final int zzxP;
  private final int zzxQ;
  
  public zza(String paramString, List<Drawable> paramList, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, int paramInt)
  {
    this.zzxM = paramString;
    this.zzxN = paramList;
    if (paramInteger1 != null)
    {
      i = paramInteger1.intValue();
      this.zzxO = i;
      if (paramInteger2 == null) {
        break label81;
      }
      i = paramInteger2.intValue();
      label42:
      this.mTextColor = i;
      if (paramInteger3 == null) {
        break label89;
      }
    }
    label81:
    label89:
    for (int i = paramInteger3.intValue();; i = 12)
    {
      this.zzxP = i;
      this.zzxQ = paramInt;
      return;
      i = zzxK;
      break;
      i = zzxL;
      break label42;
    }
  }
  
  public int getBackgroundColor()
  {
    return this.zzxO;
  }
  
  public String getText()
  {
    return this.zzxM;
  }
  
  public int getTextColor()
  {
    return this.mTextColor;
  }
  
  public int getTextSize()
  {
    return this.zzxP;
  }
  
  public List<Drawable> zzdG()
  {
    return this.zzxN;
  }
  
  public int zzdH()
  {
    return this.zzxQ;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\formats\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */