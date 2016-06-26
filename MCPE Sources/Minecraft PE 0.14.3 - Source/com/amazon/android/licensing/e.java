package com.amazon.android.licensing;

import com.amazon.android.e.a;
import com.amazon.android.framework.resource.Resource;

public final class e
  extends com.amazon.android.l.b
{
  @Resource
  private com.amazon.android.framework.task.command.b a;
  
  public e()
  {
    a(new l());
    a(new a());
    a(new b());
  }
  
  protected final void a()
  {
    if (!isWorkflowChild()) {
      this.a.b();
    }
  }
  
  protected final String b()
  {
    return "LicenseEnforcementTaskWorkflow";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\licensing\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */