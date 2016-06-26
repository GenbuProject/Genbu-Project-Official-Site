package com.microsoft.onlineid.interop;

import android.app.Activity;
import android.util.Log;
import com.microsoft.cll.android.ITicketCallback;
import com.microsoft.cll.android.TicketObject;
import com.microsoft.onlineid.interop.xbox.jobs.JobSilentSignIn;

public class CLLCallback
  implements ITicketCallback
{
  private static final String POLICY = "mbi_ssl";
  private static final String VORTEX_SCOPE = "vortex.data.microsoft.com";
  private Activity m_activity = null;
  private String m_vortexTicket = new String("");
  
  public CLLCallback(Activity paramActivity)
  {
    this.m_activity = paramActivity;
  }
  
  public String getAuthXToken(boolean paramBoolean)
  {
    return Interop.GetLiveXTokenCallback(paramBoolean);
  }
  
  public String getMsaDeviceTicket(boolean paramBoolean)
  {
    if ((this.m_vortexTicket.length() > 0) && (!paramBoolean)) {
      return this.m_vortexTicket;
    }
    MSATicketCallbacks localMSATicketCallbacks = new MSATicketCallbacks();
    synchronized (new JobSilentSignIn(this.m_activity, localMSATicketCallbacks, "vortex.data.microsoft.com", "mbi_ssl"))
    {
      try
      {
        ???.start();
        ???.wait();
        this.m_vortexTicket = localMSATicketCallbacks.getTicket();
        return this.m_vortexTicket;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.i("XSAPI.Android", "exception on votex MSA Ticket");
        }
      }
    }
  }
  
  public TicketObject getXTicketForXuid(String paramString)
  {
    return new TicketObject("x:" + Interop.GetXTokenCallback(paramString), false);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\CLLCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */