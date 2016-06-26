package com.microsoft.cll.android;

public abstract interface ITicketCallback
{
  public abstract String getAuthXToken(boolean paramBoolean);
  
  public abstract String getMsaDeviceTicket(boolean paramBoolean);
  
  public abstract TicketObject getXTicketForXuid(String paramString);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\cll\android\ITicketCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */