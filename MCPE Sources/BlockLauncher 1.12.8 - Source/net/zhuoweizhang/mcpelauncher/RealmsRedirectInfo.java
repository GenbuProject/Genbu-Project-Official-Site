package net.zhuoweizhang.mcpelauncher;

import java.util.HashMap;
import java.util.Map;

public class RealmsRedirectInfo
{
  public static Map<String, RealmsRedirectInfo> targets = new HashMap();
  public String accountUrl;
  public String loginUrl = null;
  public String peoUrl = "NONE";
  
  static
  {
    add(new RealmsRedirectInfo("NONE", null, "https://account.mojang.com/m/login?app=mcpe"));
  }
  
  public RealmsRedirectInfo(String paramString1, String paramString2, String paramString3)
  {
    this.peoUrl = paramString1;
    this.accountUrl = paramString2;
    this.loginUrl = paramString3;
  }
  
  private static void add(RealmsRedirectInfo paramRealmsRedirectInfo)
  {
    targets.put(paramRealmsRedirectInfo.peoUrl, paramRealmsRedirectInfo);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\RealmsRedirectInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */