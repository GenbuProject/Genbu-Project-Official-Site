package com.microsoft.cll.android;

import java.util.Map;

public class PreSerializedEvent
{
  public Map<String, String> attributes;
  public String data;
  public String partBName;
  public String partCName;
  
  public PreSerializedEvent(String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    this.data = paramString1;
    this.partBName = paramString3;
    this.partCName = paramString2;
    this.attributes = paramMap;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\PreSerializedEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */