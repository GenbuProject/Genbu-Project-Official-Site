package com.microsoft.xbox.service.model.sls;

import com.microsoft.xbox.toolkit.GsonUtil;

public class NeverListRequest
{
  public long xuid;
  
  public NeverListRequest(long paramLong)
  {
    this.xuid = paramLong;
  }
  
  public static String getNeverListRequestBody(NeverListRequest paramNeverListRequest)
  {
    return GsonUtil.toJsonString(paramNeverListRequest);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\sls\NeverListRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */