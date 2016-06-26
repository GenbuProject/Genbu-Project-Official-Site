package com.microsoft.onlineid.internal.log;

public class RedactableResponse
  extends RedactableXml
{
  private static final String[] TagsToKeep = { "ErrorSubcode", "ServerInfo", "S:Text", "S:Value", "ps:DisplaySessionID", "ps:ExpirationTime", "ps:RequestTime", "ps:SessionID", "ps:State", "psf:authstate", "psf:code", "psf:configVersion", "psf:reqstatus", "psf:serverInfo", "psf:text", "psf:value", "wsa:Address", "wst:TokenType", "wsu:Created", "wsu:Expires" };
  
  public RedactableResponse(String paramString)
  {
    super(paramString, TagsToKeep);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\log\RedactableResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */