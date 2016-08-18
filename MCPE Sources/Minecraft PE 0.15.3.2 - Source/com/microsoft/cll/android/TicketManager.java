package com.microsoft.cll.android;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TicketManager
{
  private final String TAG = "AndroidCll-TicketManager";
  private final ITicketCallback callback;
  private final ILogger logger;
  private boolean needDeviceTicket = true;
  private final Map<String, String> tickets;
  
  public TicketManager(ITicketCallback paramITicketCallback, ILogger paramILogger)
  {
    this.callback = paramITicketCallback;
    this.logger = paramILogger;
    this.tickets = new HashMap();
  }
  
  public void addTickets(List<String> paramList)
  {
    if ((paramList == null) || (this.callback == null)) {}
    for (;;)
    {
      return;
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        if (!this.tickets.containsKey(str2))
        {
          this.logger.info("AndroidCll-TicketManager", "Getting ticket for " + str2);
          TicketObject localTicketObject = this.callback.getXTicketForXuid(str2);
          String str1 = localTicketObject.ticket;
          paramList = str1;
          if (localTicketObject.hasDeviceClaims)
          {
            this.needDeviceTicket = false;
            paramList = "rp:" + str1;
          }
          this.tickets.put(str2, paramList);
        }
        else
        {
          this.logger.info("AndroidCll-TicketManager", "We already have a ticket for this id, skipping.");
        }
      }
    }
  }
  
  public void clean()
  {
    this.tickets.clear();
    this.needDeviceTicket = true;
  }
  
  public TicketHeaders getHeaders(boolean paramBoolean)
  {
    Object localObject;
    if ((this.callback == null) || (this.tickets.isEmpty())) {
      localObject = null;
    }
    TicketHeaders localTicketHeaders;
    do
    {
      return (TicketHeaders)localObject;
      localTicketHeaders = new TicketHeaders();
      localTicketHeaders.authXToken = this.callback.getAuthXToken(paramBoolean);
      localTicketHeaders.xtokens = this.tickets;
      localObject = localTicketHeaders;
    } while (!this.needDeviceTicket);
    localTicketHeaders.msaDeviceTicket = this.callback.getMsaDeviceTicket(paramBoolean);
    return localTicketHeaders;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\TicketManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */