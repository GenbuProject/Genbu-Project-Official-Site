package com.amazon.android.framework.prompt;

import com.amazon.android.framework.context.ContextManager;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.resource.b;
import com.amazon.android.framework.util.KiwiLogger;

public final class c
  extends SimplePrompt
  implements b
{
  private static final KiwiLogger a = new KiwiLogger("ShutdownPrompt");
  @Resource
  private ContextManager b;
  
  public c(PromptContent paramPromptContent)
  {
    super(paramPromptContent);
  }
  
  protected final void doAction()
  {
    this.b.finishActivities();
    a.test("license verification failed");
    a.test("Killing application");
  }
  
  protected final long getExpirationDurationInSeconds()
  {
    return 31536000L;
  }
  
  public final void onResourcesPopulatedImpl()
  {
    this.b.stopServices();
  }
  
  public final String toString()
  {
    return "ShutdownPrompt";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\prompt\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */