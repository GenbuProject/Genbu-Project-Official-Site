package com.amazon.android.framework.task.command;

import android.app.Application;
import android.content.Intent;
import android.os.RemoteException;
import com.amazon.android.f.f;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.android.framework.prompt.Prompt;
import com.amazon.android.framework.prompt.PromptManager;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.q.d;
import com.amazon.venezia.command.FailureResult;
import com.amazon.venezia.command.SuccessResult;
import com.amazon.venezia.command.j;
import com.amazon.venezia.command.n;
import com.amazon.venezia.command.r;
import com.amazon.venezia.command.w;
import java.util.Map;

public abstract class AbstractCommandTask
  extends com.amazon.android.l.c
{
  private static final KiwiLogger LOGGER = new KiwiLogger("AbstractCommandTask");
  @Resource
  private Application application;
  @Resource
  private e authTokenVerifier;
  @Resource
  private b client;
  @Resource
  private d metricsManager;
  @Resource
  private PromptManager promptManager;
  @Resource
  protected com.amazon.android.f.b resultManager;
  
  private void expire(r paramr, a parama)
    throws RemoteException, KiwiException
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Expiring Decision: " + paramr + ", reason: " + parama);
    }
    handleCommandResult(this.client.a(paramr, parama));
  }
  
  private com.amazon.android.q.b extractMetric(KiwiException paramKiwiException)
  {
    com.amazon.android.q.b localb = new com.amazon.android.q.b(getFailureMetricName());
    localb.a("subType", paramKiwiException.getType()).a("reason", paramKiwiException.getReason()).a("context", paramKiwiException.getContext());
    return localb;
  }
  
  private w getCommand()
  {
    return new g(this);
  }
  
  private String getFailureMetricName()
  {
    return getCommandName() + "_failure";
  }
  
  private void handleChoice(r paramr, n paramn)
    throws RemoteException, KiwiException
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Handling customer choice: " + paramn);
    }
    Object localObject = paramn.b();
    if (localObject != null)
    {
      if (KiwiLogger.TRACE_ON) {
        LOGGER.trace("Choice has intent, scheduling it to be fired!!");
      }
      localObject = this.resultManager.a((Intent)localObject);
      if (localObject == null)
      {
        if (KiwiLogger.TRACE_ON) {
          LOGGER.trace("No result recived, expiring decision");
        }
        expire(paramr, a.a);
        return;
      }
      if (((f)localObject).b == 0)
      {
        if (KiwiLogger.TRACE_ON) {
          LOGGER.trace("Result canceled, expiring decision");
        }
        expire(paramr, a.c);
        return;
      }
      if (KiwiLogger.TRACE_ON) {
        LOGGER.trace("Result received!!!, notifying service");
      }
      handleCommandResult(this.client.a(paramn));
      return;
    }
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("No intent given, choosing now");
    }
    handleCommandResult(this.client.a(paramn));
  }
  
  private void handleCommandResult(l paraml)
    throws KiwiException, RemoteException
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Received result from CommandService: " + paraml);
    }
    if (paraml == null)
    {
      if (KiwiLogger.TRACE_ON) {
        LOGGER.trace("Received null result from command service, exiting task");
      }
      return;
    }
    if (paraml.f != null)
    {
      handleException(paraml.f);
      return;
    }
    if (paraml.b != null) {
      throw paraml.b;
    }
    String str = paraml.a;
    this.authTokenVerifier.a(str, this.client.a());
    if (paraml.c != null)
    {
      handleSuccess(paraml.c);
      return;
    }
    if (paraml.d != null)
    {
      handleFailure(paraml.d);
      return;
    }
    handleDecision(paraml.e);
  }
  
  private void handleDecision(r paramr)
    throws RemoteException, KiwiException
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Handling Decision");
    }
    try
    {
      Object localObject = new c(paramr);
      this.promptManager.present((Prompt)localObject);
      localObject = ((c)localObject).a();
      if (localObject == null)
      {
        if (KiwiLogger.TRACE_ON) {
          LOGGER.trace("DecisionChooser returned null!!, expiring");
        }
        expire(paramr, a.a);
        return;
      }
      handleChoice(paramr, (n)localObject);
      return;
    }
    catch (com.amazon.android.b.c localc)
    {
      expire(paramr, localc.a);
    }
  }
  
  private void handleException(j paramj)
    throws RemoteException, KiwiException
  {
    throw new com.amazon.android.b.e(paramj);
  }
  
  private void handleExecutionException(Throwable paramThrowable)
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Exception occurred while processing task: " + paramThrowable, paramThrowable);
    }
    paramThrowable = translate(paramThrowable);
    onException(paramThrowable);
    paramThrowable = extractMetric(paramThrowable);
    this.metricsManager.a(paramThrowable);
  }
  
  private void handleFailure(FailureResult paramFailureResult)
    throws RemoteException, KiwiException
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Command failed execution: " + paramFailureResult.getDisplayableName());
    }
    onFailure(paramFailureResult);
  }
  
  private void handleSuccess(SuccessResult paramSuccessResult)
    throws RemoteException, KiwiException
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Command executed successfully");
    }
    onSuccess(paramSuccessResult);
  }
  
  private void postExecution()
  {
    if (!isWorkflowChild()) {
      this.client.b();
    }
  }
  
  private KiwiException translate(Throwable paramThrowable)
  {
    if ((paramThrowable instanceof KiwiException)) {
      return (KiwiException)paramThrowable;
    }
    if ((paramThrowable instanceof RemoteException)) {
      return new com.amazon.android.b.a((RemoteException)paramThrowable);
    }
    return new com.amazon.android.b.b(paramThrowable);
  }
  
  public final void execute()
  {
    try
    {
      if (KiwiLogger.TRACE_ON)
      {
        LOGGER.trace("----------------------------------------------");
        LOGGER.trace("Executing: " + getCommandName());
        LOGGER.trace("----------------------------------------------");
      }
      preExecution();
      if (!isExecutionNeeded())
      {
        if (KiwiLogger.TRACE_ON) {
          LOGGER.trace("Execution not needed, quitting");
        }
        return;
      }
      if (KiwiLogger.TRACE_ON) {
        LOGGER.trace("Executing Command: " + getCommandName());
      }
      handleCommandResult(this.client.a(getCommand()));
      return;
    }
    catch (Throwable localThrowable)
    {
      handleExecutionException(localThrowable);
      return;
    }
    finally
    {
      if (KiwiLogger.TRACE_ON) {
        LOGGER.trace("Task finished");
      }
      postExecution();
    }
  }
  
  protected abstract Map getCommandData();
  
  protected abstract String getCommandName();
  
  protected abstract String getCommandVersion();
  
  protected abstract boolean isExecutionNeeded();
  
  protected void onException(KiwiException paramKiwiException)
  {
    LOGGER.error("On Exception!!!!: " + paramKiwiException);
  }
  
  protected abstract void onFailure(FailureResult paramFailureResult)
    throws RemoteException, KiwiException;
  
  protected abstract void onSuccess(SuccessResult paramSuccessResult)
    throws RemoteException, KiwiException;
  
  protected void preExecution()
    throws KiwiException
  {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\command\AbstractCommandTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */