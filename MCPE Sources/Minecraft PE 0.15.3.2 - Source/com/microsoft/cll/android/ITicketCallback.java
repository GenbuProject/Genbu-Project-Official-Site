package com.microsoft.cll.android;

public abstract interface ITicketCallback
{
  public abstract String getAuthXToken(boolean paramBoolean);
  
  public abstract String getMsaDeviceTicket(boolean paramBoolean);
  
  public abstract TicketObject getXTicketForXuid(String paramString);
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\ITicketCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */