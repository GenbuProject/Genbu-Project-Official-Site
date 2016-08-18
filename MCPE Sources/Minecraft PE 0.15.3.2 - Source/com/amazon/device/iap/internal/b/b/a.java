package com.amazon.device.iap.internal.b.b;

import android.app.Activity;
import android.content.Intent;
import android.os.RemoteException;
import com.amazon.android.framework.context.ContextManager;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.Task;
import com.amazon.android.framework.task.TaskManager;
import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import com.amazon.device.iap.internal.b.i;
import com.amazon.device.iap.internal.util.MetricsHelper;
import com.amazon.venezia.command.SuccessResult;
import java.util.Map;

abstract class a
  extends i
{
  private static final String d = a.class.getSimpleName();
  @Resource
  protected TaskManager a;
  @Resource
  protected ContextManager b;
  protected final String c;
  
  a(com.amazon.device.iap.internal.b.e parame, String paramString1, String paramString2)
  {
    super(parame, "purchase_item", paramString1);
    this.c = paramString2;
    a("sku", this.c);
  }
  
  protected boolean a(final SuccessResult paramSuccessResult)
    throws RemoteException, KiwiException
  {
    paramSuccessResult = paramSuccessResult.getData();
    com.amazon.device.iap.internal.util.e.a(d, "data: " + paramSuccessResult);
    if (!paramSuccessResult.containsKey("purchaseItemIntent"))
    {
      com.amazon.device.iap.internal.util.e.b(d, "did not find intent");
      return false;
    }
    com.amazon.device.iap.internal.util.e.a(d, "found intent");
    paramSuccessResult = (Intent)paramSuccessResult.remove("purchaseItemIntent");
    this.a.enqueueAtFront(TaskPipelineId.FOREGROUND, new Task()
    {
      public void execute()
      {
        try
        {
          Activity localActivity2 = a.this.b.getVisible();
          Activity localActivity1 = localActivity2;
          if (localActivity2 == null) {
            localActivity1 = a.this.b.getRoot();
          }
          com.amazon.device.iap.internal.util.e.a(a.a(), "About to fire intent with activity " + localActivity1);
          localActivity1.startActivity(paramSuccessResult);
          return;
        }
        catch (Exception localException)
        {
          MetricsHelper.submitExceptionMetrics(a.a(a.this), a.a() + ".onResult().execute()", localException);
          com.amazon.device.iap.internal.util.e.b(a.a(), "Exception when attempting to fire intent: " + localException);
        }
      }
    });
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\internal\b\b\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */