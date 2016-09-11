package com.microsoft.onlineid.internal.sso.service.operation;

import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.ApiRequest;
import com.microsoft.onlineid.internal.ApiResult;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.BlockingApiRequestResultReceiver;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.internal.exception.AccountNotFoundException;
import com.microsoft.onlineid.internal.exception.UserCancelledException;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.client.SsoResponse;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.sts.AuthenticatorAccountManager;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.sts.exception.InvalidResponseException;
import com.microsoft.onlineid.sts.exception.StsException;

public class GetTicketOperation
  extends ServiceOperation
{
  public GetTicketOperation(Context paramContext, Bundle paramBundle, AuthenticatorAccountManager paramAuthenticatorAccountManager, TicketManager paramTicketManager)
  {
    super(paramContext, paramBundle, paramAuthenticatorAccountManager, paramTicketManager);
  }
  
  public Bundle call()
    throws AccountNotFoundException, InvalidResponseException, NetworkException, StsException, InternalException
  {
    Object localObject1 = getParameters().getString("com.microsoft.onlineid.user_cid");
    Strings.verifyArgumentNotNullOrEmpty((String)localObject1, "com.microsoft.onlineid.user_cid");
    Object localObject2 = getAccountManager().getAccountByCid((String)localObject1);
    if (localObject2 == null) {
      throw new AccountNotFoundException();
    }
    ISecurityScope localISecurityScope = BundleMarshaller.scopeFromBundle(getParameters());
    String str = getParameters().getString("com.microsoft.onlineid.cobranding_id");
    boolean bool = getParameters().getBoolean("com.microsoft.onlineid.web_telemetry_requested");
    localObject1 = new BlockingApiRequestResultReceiver()
    {
      protected void onSuccess(ApiResult paramAnonymousApiResult)
      {
        setResult(paramAnonymousApiResult.getTicket());
      }
    };
    localObject2 = new TicketManager(getContext()).createTicketRequest(((AuthenticatorUserAccount)localObject2).getPuid(), localISecurityScope, getCallingPackage(), str, getCallerStateBundle()).setIsWebFlowTelemetryRequested(bool).setIsSdkRequest(true).setResultReceiver((ResultReceiver)localObject1);
    getContext().startService(((ApiRequest)localObject2).asIntent());
    try
    {
      localObject1 = ((BlockingApiRequestResultReceiver)localObject1).blockForResult();
      if (((SsoResponse)localObject1).hasData()) {
        return BundleMarshaller.ticketToBundle((Ticket)((SsoResponse)localObject1).getData());
      }
      if (((SsoResponse)localObject1).hasPendingIntent())
      {
        localObject1 = BundleMarshaller.pendingIntentToBundle(((SsoResponse)localObject1).getPendingIntent());
        return (Bundle)localObject1;
      }
    }
    catch (UserCancelledException localUserCancelledException)
    {
      Assertion.check(false, "Unexpected UserCancelledException caught in GetTicketOperation.");
      return BundleMarshaller.exceptionToBundle(localUserCancelledException);
    }
    catch (Exception localException)
    {
      return BundleMarshaller.exceptionToBundle(localException);
    }
    Assertion.check(false, "GetTicketOperation did not receive an expected result from MsaService.");
    throw new InternalException("GetTicketOperation did not receive an expected result from MsaService.");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\sso\service\operation\GetTicketOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */