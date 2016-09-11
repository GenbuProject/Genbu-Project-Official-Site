package com.microsoft.xbox.service.model.sls;

import com.microsoft.xbox.toolkit.GsonUtil;
import java.util.ArrayList;

public class AddShareIdentityRequest
{
  public ArrayList<String> xuids;
  
  public AddShareIdentityRequest(ArrayList<String> paramArrayList)
  {
    this.xuids = paramArrayList;
  }
  
  public static String getAddShareIdentityRequestBody(AddShareIdentityRequest paramAddShareIdentityRequest)
  {
    return GsonUtil.toJsonString(paramAddShareIdentityRequest);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\model\sls\AddShareIdentityRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */