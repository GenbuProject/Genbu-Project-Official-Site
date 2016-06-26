package com.amazon.android.licensing;

import android.app.Application;
import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.android.framework.prompt.PromptContent;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.TaskManager;
import com.amazon.android.framework.task.command.AbstractCommandTask;
import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.k.b;
import com.amazon.mas.kiwi.util.BC1;
import com.amazon.venezia.command.FailureResult;
import com.amazon.venezia.command.SuccessResult;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public final class l
  extends AbstractCommandTask
{
  private static final KiwiLogger a = new KiwiLogger("VerifyApplicationEntitlmentTask");
  private LicenseFailurePromptContentMapper b = new LicenseFailurePromptContentMapper();
  @Resource
  private com.amazon.android.q.d c;
  @Resource
  private Application d;
  @Resource
  private TaskManager e;
  @Resource
  private com.amazon.android.n.a f;
  @Resource
  private com.amazon.android.m.c g;
  
  protected final Map getCommandData()
  {
    return null;
  }
  
  protected final String getCommandName()
  {
    return "get_license";
  }
  
  protected final String getCommandVersion()
  {
    return "1.0";
  }
  
  protected final boolean isExecutionNeeded()
  {
    return this.f.a("APPLICATION_LICENSE") == null;
  }
  
  protected final void onException(KiwiException paramKiwiException)
  {
    PromptContent localPromptContent = this.b.map(paramKiwiException);
    if ((localPromptContent == null) && (KiwiLogger.ERROR_ON)) {
      a.error("No mapping specified for exception: " + paramKiwiException, paramKiwiException);
    }
    this.f.a("LICENSE_FAILURE_CONTENT", localPromptContent);
  }
  
  protected final void onFailure(FailureResult paramFailureResult)
    throws RemoteException, KiwiException
  {
    paramFailureResult = new PromptContent(paramFailureResult.getDisplayableName(), paramFailureResult.getDisplayableMessage(), paramFailureResult.getButtonLabel(), paramFailureResult.show());
    a.trace("onFailure: " + paramFailureResult);
    this.f.a("LICENSE_FAILURE_CONTENT", paramFailureResult);
  }
  
  protected final void onSuccess(SuccessResult paramSuccessResult)
    throws RemoteException, KiwiException
  {
    Object localObject1 = new c(paramSuccessResult.getData());
    paramSuccessResult = this.g.a();
    paramSuccessResult = new f(new com.amazon.android.m.d(((c)localObject1).a, paramSuccessResult));
    com.amazon.android.k.a locala = new com.amazon.android.k.a();
    locala.a(((c)localObject1).b, paramSuccessResult.b, k.b);
    locala.a(((c)localObject1).c, paramSuccessResult.c, k.c);
    locala.a(paramSuccessResult.e, this.d.getPackageName(), k.d);
    Object localObject2 = paramSuccessResult.d;
    Date localDate = new Date();
    localObject1 = k.a;
    if (((Comparable)localObject2).compareTo(localDate) <= 0)
    {
      localObject2 = new b((com.amazon.android.k.c)localObject1, "'" + localObject2 + "' <= '" + localDate + "'");
      locala.a.put(localObject1, localObject2);
    }
    try
    {
      localObject1 = BC1.getBC1ChecksumBase64(this.d.getPackageCodePath());
      locala.a(paramSuccessResult.a, localObject1, k.e);
      if (locala.a()) {
        throw new com.amazon.android.s.a(locala);
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localObject1 = k.e;
        b localb = new b((com.amazon.android.k.c)localObject1, "Exception: " + localIOException);
        locala.a.put(localObject1, localb);
      }
      a.trace("License Verification succeeded!");
      paramSuccessResult = new d(this, paramSuccessResult, paramSuccessResult.d);
      this.f.a.a("APPLICATION_LICENSE", paramSuccessResult);
      this.c.a(new com.amazon.android.p.a());
      this.e.enqueue(TaskPipelineId.BACKGROUND, new n());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\licensing\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */