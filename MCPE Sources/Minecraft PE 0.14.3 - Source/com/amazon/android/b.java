package com.amazon.android;

import com.amazon.android.framework.resource.Resource;
import com.amazon.android.licensing.e;
import com.amazon.android.r.a;

public final class b
  extends com.amazon.android.l.b
{
  @Resource
  private com.amazon.android.framework.task.command.b a;
  
  public b()
  {
    a(new i());
    a(new a());
    a(new e());
  }
  
  protected final void a()
  {
    if (!isWorkflowChild()) {
      this.a.b();
    }
  }
  
  protected final String b()
  {
    return "DrmFullApplicationLaunchTaskWorkflow";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */