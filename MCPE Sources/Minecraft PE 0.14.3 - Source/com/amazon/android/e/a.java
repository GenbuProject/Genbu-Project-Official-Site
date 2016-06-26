package com.amazon.android.e;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.command.AbstractCommandTask;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.q.b;
import com.amazon.android.q.d;
import com.amazon.venezia.command.FailureResult;
import com.amazon.venezia.command.SuccessResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class a
  extends AbstractCommandTask
{
  private static final KiwiLogger a = new KiwiLogger("SubmitMetricsTask");
  @Resource
  private d b;
  private com.amazon.android.q.a c;
  
  protected final Map getCommandData()
  {
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList = new ArrayList(this.c.b());
    Iterator localIterator = this.c.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((b)localIterator.next()).a);
    }
    localHashMap.put("metrics", localArrayList);
    return localHashMap;
  }
  
  protected final String getCommandName()
  {
    return "submit_metrics";
  }
  
  protected final String getCommandVersion()
  {
    return "1.0";
  }
  
  protected final boolean isExecutionNeeded()
  {
    return !this.c.a();
  }
  
  protected final void onFailure(FailureResult paramFailureResult)
    throws RemoteException, KiwiException
  {}
  
  protected final void onSuccess(SuccessResult paramSuccessResult)
    throws RemoteException, KiwiException
  {}
  
  protected final void preExecution()
    throws KiwiException
  {
    this.c = this.b.a();
    if (KiwiLogger.TRACE_ON)
    {
      a.trace("--------------- SUBMIT METRICS -------------------");
      a.trace("Size: " + this.c.b());
      a.trace("--------------------------------------------------");
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\e\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */