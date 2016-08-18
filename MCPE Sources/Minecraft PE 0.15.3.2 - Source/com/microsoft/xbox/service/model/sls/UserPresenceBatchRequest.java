package com.microsoft.xbox.service.model.sls;

import com.microsoft.xbox.toolkit.GsonUtil;
import java.util.ArrayList;

public class UserPresenceBatchRequest
{
  public String level = "all";
  public ArrayList<String> users;
  
  public UserPresenceBatchRequest(ArrayList<String> paramArrayList)
  {
    this.users = paramArrayList;
  }
  
  public static String getUserPresenceBatchRequestBody(UserPresenceBatchRequest paramUserPresenceBatchRequest)
  {
    return GsonUtil.toJsonString(paramUserPresenceBatchRequest);
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\sls\UserPresenceBatchRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */