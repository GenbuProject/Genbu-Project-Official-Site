package com.amazon.android.licensing;

import com.amazon.android.framework.prompt.Prompt;
import com.amazon.android.framework.prompt.PromptContent;
import com.amazon.android.framework.prompt.PromptManager;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.n.a;
import com.amazon.android.n.d;

public final class b
  extends com.amazon.android.l.c
{
  private static final KiwiLogger a = new KiwiLogger("LicenseKillTask");
  @Resource
  private a b;
  @Resource
  private PromptManager c;
  
  public final void execute()
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("License Kill Task Executing!!!");
    }
    if (this.b.b("APPLICATION_LICENSE"))
    {
      a.test("license verification succeeded");
      return;
    }
    if (KiwiLogger.TRACE_ON) {
      a.trace("License Kill Task determined app is not licensed, killing app");
    }
    if (isWorkflowChild()) {
      quitParentWorkflow();
    }
    Object localObject = (PromptContent)this.b.a("LICENSE_FAILURE_CONTENT");
    if (localObject != null)
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("Fetched failure content from store: " + localObject);
      }
      this.b.a.c("LICENSE_FAILURE_CONTENT");
    }
    for (;;)
    {
      localObject = new com.amazon.android.framework.prompt.c((PromptContent)localObject);
      this.c.present((Prompt)localObject);
      return;
      localObject = i.e;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\licensing\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */