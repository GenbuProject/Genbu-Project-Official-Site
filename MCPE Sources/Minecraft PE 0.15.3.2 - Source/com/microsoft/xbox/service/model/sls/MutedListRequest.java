package com.microsoft.xbox.service.model.sls;

import com.microsoft.xbox.toolkit.GsonUtil;

public class MutedListRequest
{
  public long xuid;
  
  public MutedListRequest(long paramLong)
  {
    this.xuid = paramLong;
  }
  
  public static String getNeverListRequestBody(MutedListRequest paramMutedListRequest)
  {
    return GsonUtil.toJsonString(paramMutedListRequest);
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\sls\MutedListRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */