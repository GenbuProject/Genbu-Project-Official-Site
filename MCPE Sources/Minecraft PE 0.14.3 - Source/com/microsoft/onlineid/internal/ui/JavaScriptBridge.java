package com.microsoft.onlineid.internal.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.SecurityScope;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.ApiRequest;
import com.microsoft.onlineid.internal.ApiRequest.Extras;
import com.microsoft.onlineid.internal.ApiResult;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.configuration.Environment;
import com.microsoft.onlineid.internal.exception.AccountNotFoundException;
import com.microsoft.onlineid.internal.exception.PromptNeededException;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.ServerConfig;
import com.microsoft.onlineid.sts.ServerConfig.KnownEnvironment;
import com.microsoft.onlineid.sts.exception.InlineFlowException;
import com.microsoft.onlineid.sts.exception.InvalidResponseException;
import com.microsoft.onlineid.sts.exception.StsException;
import com.microsoft.onlineid.userdata.AccountManagerReader;
import com.microsoft.onlineid.userdata.SignUpData;

public class JavaScriptBridge
{
  private static final String PPCRL_REQUEST_E_USER_CANCELED = "80048842";
  private boolean _isOutOfBandInterrupt;
  private final PropertyBag _propertyBag;
  private final ServerConfig _serverConfig;
  private final TicketManager _ticketManager;
  private final TypedStorage _typedStorage;
  private final WebFlowActivity _webFlowActivity;
  
  @Deprecated
  public JavaScriptBridge()
  {
    this._webFlowActivity = null;
    this._propertyBag = null;
    this._serverConfig = null;
    this._typedStorage = null;
    this._ticketManager = null;
  }
  
  public JavaScriptBridge(WebFlowActivity paramWebFlowActivity)
  {
    this._webFlowActivity = paramWebFlowActivity;
    this._propertyBag = new PropertyBag();
    paramWebFlowActivity = this._webFlowActivity.getApplicationContext();
    this._serverConfig = new ServerConfig(paramWebFlowActivity);
    this._typedStorage = new TypedStorage(paramWebFlowActivity);
    this._ticketManager = new TicketManager(paramWebFlowActivity);
    populatePropertyBag();
  }
  
  private static PropertyBag.Key getKeyForName(String paramString)
  {
    if (paramString == null)
    {
      Assertion.check(false);
      return null;
    }
    try
    {
      paramString = PropertyBag.Key.valueOf(paramString);
      return paramString;
    }
    catch (IllegalArgumentException paramString) {}
    return null;
  }
  
  @JavascriptInterface
  public void FinalBack()
  {
    this._webFlowActivity.cancel();
  }
  
  @JavascriptInterface
  public void FinalNext()
  {
    String str1 = this._webFlowActivity.getIntent().getAction();
    String str2 = this._propertyBag.get(PropertyBag.Key.ErrorCode);
    try
    {
      if (!TextUtils.isEmpty(str2)) {
        break label155;
      }
      if (("com.microsoft.onlineid.internal.SIGN_IN".equals(str1)) || ("com.microsoft.onlineid.internal.SIGN_UP".equals(str1)))
      {
        handleSignInResult();
        return;
      }
      if ("com.microsoft.onlineid.internal.RESOLVE_INTERRUPT".equals(str1))
      {
        handleInterruptResult();
        return;
      }
    }
    catch (Exception localException)
    {
      ClientAnalytics.get().logException(localException);
      Logger.error("Web flow with action " + str1 + " failed.", localException);
      this._webFlowActivity.sendResult(1, new ApiResult().setException(localException).asBundle());
      return;
    }
    throw new InternalException("Unknown Action: " + str1);
    label155:
    if (this._isOutOfBandInterrupt)
    {
      this._webFlowActivity.cancel();
      return;
    }
    String str3 = this._propertyBag.get(PropertyBag.Key.ExtendedErrorString);
    if ((str3 != null) && (str3.contains("80048842")))
    {
      FinalBack();
      return;
    }
    throw new InlineFlowException(this._propertyBag.get(PropertyBag.Key.ErrorString), this._propertyBag.get(PropertyBag.Key.ErrorURL), localException, str3);
  }
  
  @JavascriptInterface
  public String Property(String paramString)
  {
    paramString = getKeyForName(paramString);
    if (paramString != null) {
      return this._propertyBag.get(paramString);
    }
    return null;
  }
  
  @JavascriptInterface
  public void Property(String paramString1, String paramString2)
  {
    paramString1 = getKeyForName(paramString1);
    if (paramString1 != null)
    {
      this._propertyBag.set(paramString1, paramString2);
      if (paramString1.equals(PropertyBag.Key.IsSignUp))
      {
        Logger.info(PropertyBag.Key.IsSignUp + "=" + paramString2);
        ClientAnalytics.get().logEvent("Authenticator accounts", "Sign up success");
      }
    }
  }
  
  protected AuthenticatorUserAccount createAccountFromProperties(PropertyBag paramPropertyBag)
    throws InternalException
  {
    String str1 = paramPropertyBag.get(PropertyBag.Key.DAToken);
    String str2 = paramPropertyBag.get(PropertyBag.Key.DASessionKey);
    String str3 = paramPropertyBag.get(PropertyBag.Key.SigninName);
    String str4 = paramPropertyBag.get(PropertyBag.Key.CID);
    paramPropertyBag = paramPropertyBag.get(PropertyBag.Key.PUID);
    validateProperty(PropertyBag.Key.DAToken, str1);
    validateProperty(PropertyBag.Key.DASessionKey, str2);
    validateProperty(PropertyBag.Key.SigninName, str3);
    return new AuthenticatorUserAccount(paramPropertyBag, str4, str3, new DAToken(str1, Base64.decode(str2, 2)));
  }
  
  protected void handleInterruptResult()
    throws AccountNotFoundException, InternalException
  {
    String str1 = new ApiRequest(null, this._webFlowActivity.getIntent()).getAccountPuid();
    AuthenticatorUserAccount localAuthenticatorUserAccount = this._typedStorage.readAccount(str1);
    if (localAuthenticatorUserAccount == null) {
      throw new AccountNotFoundException("Account was deleted before interrupt could be resolved.");
    }
    String str2 = this._propertyBag.get(PropertyBag.Key.DAToken);
    str1 = this._propertyBag.get(PropertyBag.Key.DASessionKey);
    if ((!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty(str1))) {}
    for (;;)
    {
      try
      {
        localAuthenticatorUserAccount.setDAToken(new DAToken(str2, Base64.decode(str1, 2)));
        this._typedStorage.writeAccount(localAuthenticatorUserAccount);
        str1 = this._propertyBag.get(PropertyBag.Key.STSInlineFlowToken);
        if (TextUtils.isEmpty(str1))
        {
          Logger.error("Interrupt resolution did not return a flow token.");
          Assertion.check(false, "Interrupt resolution did not return a flow token.");
        }
        this._webFlowActivity.sendResult(-1, new ApiResult().setFlowToken(str1).asBundle());
        return;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        Logger.error("Could not decode Base64: " + str1);
        throw new InternalException("Session Key from interrupt resolution was invalid.");
      }
      Logger.warning("WebWizard property bag did not have DAToken/SessionKey");
    }
  }
  
  protected void handleSignInResult()
    throws InternalException, NetworkException, InvalidResponseException, StsException
  {
    AuthenticatorUserAccount localAuthenticatorUserAccount = createAccountFromProperties(this._propertyBag);
    if (localAuthenticatorUserAccount.isNewAndInOutOfBandInterrupt())
    {
      if (ServerConfig.KnownEnvironment.Production.getEnvironment().equals(this._serverConfig.getEnvironment())) {}
      for (Object localObject = "ssl.live.com";; localObject = "ssl.live-int.com")
      {
        localObject = new SecurityScope((String)localObject, "mbi_ssl");
        try
        {
          this._ticketManager.getTicketNoCache(localAuthenticatorUserAccount, (ISecurityScope)localObject, null);
          return;
        }
        catch (PromptNeededException localPromptNeededException)
        {
          final Intent localIntent = localPromptNeededException.getRequest().asIntent();
          localIntent.removeExtra(ApiRequest.Extras.Continuation.getKey());
          localIntent.fillIn(this._webFlowActivity.getIntent(), 0);
          localIntent.setAction("com.microsoft.onlineid.internal.RESOLVE_INTERRUPT");
          this._webFlowActivity.runOnUiThread(new Runnable()
          {
            public void run()
            {
              JavaScriptBridge.this._webFlowActivity.setIntent(localIntent);
              JavaScriptBridge.this._webFlowActivity.recreate();
            }
          });
          return;
        }
      }
    }
    this._typedStorage.writeAccount(localAuthenticatorUserAccount);
    this._webFlowActivity.sendResult(-1, new ApiResult().setAccountPuid(localAuthenticatorUserAccount.getPuid()).asBundle());
  }
  
  protected void populatePropertyBag()
  {
    Context localContext = this._webFlowActivity.getApplicationContext();
    SignUpData localSignUpData = new SignUpData(localContext);
    this._propertyBag.set(PropertyBag.Key.PfUsernames, new AccountManagerReader(localContext).getEmailsAsJsonArray());
    this._propertyBag.set(PropertyBag.Key.PfFirstName, localSignUpData.getFirstName());
    this._propertyBag.set(PropertyBag.Key.PfLastName, localSignUpData.getLastName());
    this._propertyBag.set(PropertyBag.Key.PfDeviceEmail, localSignUpData.getDeviceEmail());
    this._propertyBag.set(PropertyBag.Key.PfPhone, localSignUpData.getPhone());
    this._propertyBag.set(PropertyBag.Key.PfCountryCode, localSignUpData.getCountryCode());
  }
  
  void setIsOutOfBandInterrupt()
  {
    this._isOutOfBandInterrupt = true;
  }
  
  protected void validateProperty(PropertyBag.Key paramKey, String paramString)
    throws InternalException
  {
    if (TextUtils.isEmpty(paramString))
    {
      paramKey = "PropertyBag was missing required property: " + paramKey.name();
      Logger.error(paramKey);
      throw new InternalException(paramKey);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\ui\JavaScriptBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */