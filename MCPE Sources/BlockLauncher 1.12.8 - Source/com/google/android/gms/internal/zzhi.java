package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@zzhb
class zzhi
{
  private final String zzEY;
  private String zzF;
  private int zzGu;
  private final List<String> zzJI;
  private final List<String> zzJJ;
  private final String zzJK;
  private final String zzJL;
  private final String zzJM;
  private final String zzJN;
  private final boolean zzJO;
  private final boolean zzJP;
  
  public zzhi(int paramInt, Map<String, String> paramMap)
  {
    this.zzF = ((String)paramMap.get("url"));
    this.zzJL = ((String)paramMap.get("base_uri"));
    this.zzJM = ((String)paramMap.get("post_parameters"));
    this.zzJO = parseBoolean((String)paramMap.get("drt_include"));
    this.zzJP = parseBoolean((String)paramMap.get("pan_include"));
    this.zzJK = ((String)paramMap.get("activation_overlay_url"));
    this.zzJJ = zzav((String)paramMap.get("check_packages"));
    this.zzEY = ((String)paramMap.get("request_id"));
    this.zzJN = ((String)paramMap.get("type"));
    this.zzJI = zzav((String)paramMap.get("errors"));
    this.zzGu = paramInt;
  }
  
  private static boolean parseBoolean(String paramString)
  {
    return (paramString != null) && ((paramString.equals("1")) || (paramString.equals("true")));
  }
  
  private List<String> zzav(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Arrays.asList(paramString.split(","));
  }
  
  public int getErrorCode()
  {
    return this.zzGu;
  }
  
  public String getRequestId()
  {
    return this.zzEY;
  }
  
  public String getType()
  {
    return this.zzJN;
  }
  
  public String getUrl()
  {
    return this.zzF;
  }
  
  public void setUrl(String paramString)
  {
    this.zzF = paramString;
  }
  
  public List<String> zzgE()
  {
    return this.zzJI;
  }
  
  public String zzgF()
  {
    return this.zzJM;
  }
  
  public boolean zzgG()
  {
    return this.zzJO;
  }
  
  public boolean zzgH()
  {
    return this.zzJP;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzhi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */