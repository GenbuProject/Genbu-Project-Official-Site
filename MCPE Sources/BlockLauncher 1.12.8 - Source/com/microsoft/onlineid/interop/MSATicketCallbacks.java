package com.microsoft.onlineid.interop;

import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.interop.xbox.jobs.MSAJob;
import com.microsoft.onlineid.interop.xbox.jobs.MSAJob.Callbacks;

public class MSATicketCallbacks
  implements MSAJob.Callbacks
{
  private String m_ticket = new String("");
  
  public String getTicket()
  {
    return this.m_ticket;
  }
  
  public void onFailure(MSAJob paramMSAJob, Exception paramException)
  {
    try
    {
      paramMSAJob.notifyAll();
      return;
    }
    finally {}
  }
  
  public void onSignedOut(MSAJob paramMSAJob)
  {
    try
    {
      paramMSAJob.notifyAll();
      return;
    }
    finally {}
  }
  
  public void onTicketAcquired(MSAJob paramMSAJob, Ticket paramTicket)
  {
    try
    {
      this.m_ticket = paramTicket.getValue();
      paramMSAJob.notifyAll();
      return;
    }
    finally {}
  }
  
  public void onUiNeeded(MSAJob paramMSAJob)
  {
    try
    {
      paramMSAJob.notifyAll();
      return;
    }
    finally {}
  }
  
  public void onUserCancel(MSAJob paramMSAJob)
  {
    try
    {
      paramMSAJob.notifyAll();
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\MSATicketCallbacks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */