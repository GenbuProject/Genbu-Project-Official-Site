package com.microsoft.xbox.service.model.friendfinder;

import com.microsoft.xbox.toolkit.GsonUtil;

public class UpdateThirdPartyTokenRequest
{
  public String accessToken;
  
  public UpdateThirdPartyTokenRequest(String paramString)
  {
    this.accessToken = paramString;
  }
  
  public static String getUpdateThirdPartyTokenRequestBody(UpdateThirdPartyTokenRequest paramUpdateThirdPartyTokenRequest)
  {
    try
    {
      paramUpdateThirdPartyTokenRequest = GsonUtil.toJsonString(paramUpdateThirdPartyTokenRequest);
      return paramUpdateThirdPartyTokenRequest;
    }
    catch (Exception paramUpdateThirdPartyTokenRequest) {}
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\friendfinder\UpdateThirdPartyTokenRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */