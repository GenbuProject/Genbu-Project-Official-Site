package com.microsoft.xbox.idp.interop;

import android.content.Context;
import android.util.Log;
import com.microsoft.cll.android.ITicketCallback;
import com.microsoft.cll.android.TicketObject;
import com.microsoft.xbox.idp.jobs.JobSilentSignIn;

public class CLLCallback
  implements ITicketCallback
{
  private static final String POLICY = "mbi_ssl";
  private static final String VORTEX_SCOPE = "vortex.data.microsoft.com";
  private String m_activityTitle;
  private Context m_context = null;
  private String m_vortexTicket = new String("");
  
  public CLLCallback(Context paramContext, String paramString)
  {
    this.m_context = paramContext;
    this.m_activityTitle = paramString;
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
    ??? = new LocalConfig();
    synchronized (new JobSilentSignIn(this.m_context, this.m_activityTitle, localMSATicketCallbacks, "vortex.data.microsoft.com", "mbi_ssl", ((LocalConfig)???).getCid()))
    {
      try
      {
        ((JobSilentSignIn)???).start();
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\interop\CLLCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */