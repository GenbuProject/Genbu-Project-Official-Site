package com.facebook.internal;

public class InternalSettings
{
  private static volatile String mCustomUserAgent;
  
  public static String getCustomUserAgent()
  {
    return mCustomUserAgent;
  }
  
  public static void setCustomUserAgent(String paramString)
  {
    mCustomUserAgent = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\internal\InternalSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */