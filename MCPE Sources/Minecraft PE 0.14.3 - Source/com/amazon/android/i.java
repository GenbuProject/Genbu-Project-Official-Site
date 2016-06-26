package com.amazon.android;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.android.framework.prompt.PromptContent;
import com.amazon.android.framework.prompt.PromptManager;
import com.amazon.android.framework.prompt.c;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.command.AbstractCommandTask;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.venezia.command.FailureResult;
import com.amazon.venezia.command.SuccessResult;
import java.util.Map;

public final class i
  extends AbstractCommandTask
{
  private static final KiwiLogger a = new KiwiLogger("CheckIfAppisBlockedTask");
  @Resource
  private PromptManager b;
  
  protected final Map getCommandData()
  {
    return null;
  }
  
  protected final String getCommandName()
  {
    return "check_blocked_status";
  }
  
  protected final String getCommandVersion()
  {
    return "1.0";
  }
  
  protected final boolean isExecutionNeeded()
  {
    return true;
  }
  
  protected final void onFailure(FailureResult paramFailureResult)
    throws RemoteException, KiwiException
  {
    if (isWorkflowChild()) {
      quitParentWorkflow();
    }
    a.test("app is blocked, killing");
    paramFailureResult = new c(new PromptContent(paramFailureResult.getDisplayableName(), paramFailureResult.getDisplayableMessage(), paramFailureResult.getButtonLabel(), paramFailureResult.show()));
    this.b.present(paramFailureResult);
  }
  
  protected final void onSuccess(SuccessResult paramSuccessResult)
    throws RemoteException, KiwiException
  {
    if ((paramSuccessResult.getData() != null) && (paramSuccessResult.getData().containsKey("verbose")))
    {
      boolean bool = ((Boolean)paramSuccessResult.getData().get("verbose")).booleanValue();
      KiwiLogger.ERROR_ON = bool;
      KiwiLogger.TRACE_ON = bool;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */