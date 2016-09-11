package com.microsoft.onlineid.internal.sso.service.operation;

import android.content.Context;
import android.os.Bundle;
import com.microsoft.onlineid.SignUpOptions;
import com.microsoft.onlineid.internal.PendingIntentBuilder;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.sts.AuthenticatorAccountManager;
import com.microsoft.onlineid.ui.AddAccountActivity;

public class GetSignUpIntentOperation
  extends ServiceOperation
{
  public GetSignUpIntentOperation(Context paramContext, Bundle paramBundle, AuthenticatorAccountManager paramAuthenticatorAccountManager, TicketManager paramTicketManager)
  {
    super(paramContext, paramBundle, paramAuthenticatorAccountManager, paramTicketManager);
  }
  
  public Bundle call()
  {
    SignUpOptions localSignUpOptions = new SignUpOptions(getParameters());
    String str1 = getParameters().getString("com.microsoft.onlineid.preferred_membername_type");
    String str2 = getParameters().getString("com.microsoft.onlineid.cobranding_id");
    boolean bool = getParameters().getBoolean("com.microsoft.onlineid.web_telemetry_requested");
    return BundleMarshaller.pendingIntentToBundle(getPendingIntentBuilder(AddAccountActivity.getSignUpIntent(getContext(), localSignUpOptions, str1, str2, bool, getCallingPackage(), getCallerStateBundle())).setContext(getContext()).buildActivity());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\sso\service\operation\GetSignUpIntentOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */