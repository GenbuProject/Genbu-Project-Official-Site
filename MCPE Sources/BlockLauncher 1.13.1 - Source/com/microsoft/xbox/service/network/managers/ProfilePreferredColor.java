package com.microsoft.xbox.service.network.managers;

import com.google.gson.annotations.SerializedName;

public class ProfilePreferredColor
{
  private int primary = -1;
  @SerializedName("primaryColor")
  private String primaryColorString;
  private int secondary = -1;
  @SerializedName("secondaryColor")
  private String secondaryColorString;
  private int tertiary = -1;
  @SerializedName("tertiaryColor")
  private String tertiaryColorString;
  
  public static int convertColorFromString(String paramString)
  {
    int i;
    if (paramString == null) {
      i = 0;
    }
    int j;
    do
    {
      return i;
      String str = paramString;
      if (paramString.startsWith("#")) {
        str = paramString.substring(1);
      }
      j = Integer.parseInt(str, 16);
      i = j;
    } while (j >> 24 != 0);
    return j | 0xFF000000;
  }
  
  public int getPrimaryColor()
  {
    if (this.primary < 0) {
      this.primary = convertColorFromString(this.primaryColorString);
    }
    return this.primary;
  }
  
  public int getSecondaryColor()
  {
    if (this.secondary < 0) {
      this.secondary = convertColorFromString(this.secondaryColorString);
    }
    return this.secondary;
  }
  
  public int getTertiaryColor()
  {
    if (this.tertiary < 0) {
      this.tertiary = convertColorFromString(this.tertiaryColorString);
    }
    return this.tertiary;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\network\managers\ProfilePreferredColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */