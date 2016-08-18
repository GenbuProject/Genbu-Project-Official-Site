package com.microsoft.xbox.service.model.sls;

import com.microsoft.xbox.toolkit.GsonUtil;
import java.util.ArrayList;

public class FavoriteListRequest
{
  public ArrayList<String> xuids;
  
  public FavoriteListRequest(ArrayList<String> paramArrayList)
  {
    this.xuids = paramArrayList;
  }
  
  public static String getFavoriteListRequestBody(FavoriteListRequest paramFavoriteListRequest)
  {
    return GsonUtil.toJsonString(paramFavoriteListRequest);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\sls\FavoriteListRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */