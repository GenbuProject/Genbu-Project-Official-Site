package com.amazon.android.l;

public abstract class c
  implements a
{
  private b workflow;
  
  protected final boolean isWorkflowChild()
  {
    return this.workflow != null;
  }
  
  protected final void quitParentWorkflow()
  {
    com.amazon.android.d.a.a(isWorkflowChild(), "task is no a workflow child");
    this.workflow.c();
  }
  
  public final void setWorkflow(b paramb)
  {
    com.amazon.android.d.a.a(paramb, "workflow");
    if (this.workflow == null) {}
    for (boolean bool = true;; bool = false)
    {
      com.amazon.android.d.a.a(bool, "workflow instance can only be set once");
      this.workflow = paramb;
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\l\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */