package com.microsoft.onlineid.internal.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.analytics.ITimedAnalyticsEvent;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.ApiRequest;
import com.microsoft.onlineid.internal.ApiResult;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.NetworkConnectivity;
import com.microsoft.onlineid.internal.Uris;
import com.microsoft.onlineid.internal.configuration.Settings;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.log.SendLogsHandler;
import com.microsoft.onlineid.internal.transport.Transport;
import com.microsoft.onlineid.sms.SmsReceiver;
import com.microsoft.onlineid.userdata.AccountManagerReader;
import com.microsoft.onlineid.userdata.TelephonyManagerReader;
import java.util.Locale;

public class WebFlowActivity
  extends Activity
{
  public static final String ActionResolveInterrupt = "com.microsoft.onlineid.internal.RESOLVE_INTERRUPT";
  public static final String ActionSignIn = "com.microsoft.onlineid.internal.SIGN_IN";
  public static final String ActionSignUp = "com.microsoft.onlineid.internal.SIGN_UP";
  public static final String FullScreenTag = "com.microsoft.onlineid.internal.ui.FullScreen";
  private static final String JavaScriptOnBack = "javascript:OnBack()";
  private static final String ScenarioAuthUrl = "auth url";
  private static final String ScenarioSignIn = "sign in";
  private static final String ScenarioSignUp = "sign up";
  private JavaScriptBridge _javaScriptBridge;
  private SendLogsHandler _logHandler;
  private ITimedAnalyticsEvent _pageLoadTimingEvent;
  protected ProgressView _progressView;
  private String _scenario;
  private SmsReceiver _smsReceiver;
  private String _startUrl;
  private WebTelemetryRecorder _webTelemetryRecorder;
  private WebView _webView;
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void configureWebView(Bundle paramBundle, WebFlowTelemetryData paramWebFlowTelemetryData)
  {
    this._webTelemetryRecorder = new WebTelemetryRecorder(paramWebFlowTelemetryData.getIsWebTelemetryRequested(), paramBundle);
    this._javaScriptBridge = new JavaScriptBridge(this, this._webTelemetryRecorder, paramWebFlowTelemetryData);
    this._webView.addJavascriptInterface(this._javaScriptBridge, "external");
    paramBundle = this._webView.getSettings();
    paramBundle.setUserAgentString(Transport.mergeUserAgentStrings(paramBundle.getUserAgentString(), Transport.buildUserAgentString(getApplicationContext())));
    paramBundle.setJavaScriptEnabled(true);
    paramBundle.setJavaScriptCanOpenWindowsAutomatically(true);
    paramBundle.setSupportMultipleWindows(true);
    paramBundle = new HostedWebViewClient();
    this._javaScriptBridge.setAssetBundlePropertyProvider(paramBundle.getAssetVendor());
    this._webView.setWebViewClient(paramBundle);
    this._webView.setWebChromeClient(new HostedWebChromeClient(null));
  }
  
  private RelativeLayout createInitialUI()
  {
    RelativeLayout localRelativeLayout = new RelativeLayout(this);
    localRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
    localRelativeLayout.setBackgroundColor(-1);
    this._webView = new WebView(this);
    Object localObject = getIntent().getAction();
    if ("com.microsoft.onlineid.internal.SIGN_IN".equals(localObject)) {
      localObject = "msa_sdk_webflow_webview_sign_in";
    }
    for (;;)
    {
      int i = getApplicationContext().getResources().getIdentifier((String)localObject, "id", getApplicationContext().getPackageName());
      this._webView.setId(i);
      localObject = new RelativeLayout.LayoutParams(-2, -1);
      ((RelativeLayout.LayoutParams)localObject).addRule(10);
      localRelativeLayout.addView(this._webView, (ViewGroup.LayoutParams)localObject);
      this._progressView = new ProgressView(this);
      localObject = new RelativeLayout.LayoutParams(-1, -2);
      ((RelativeLayout.LayoutParams)localObject).addRule(10);
      localRelativeLayout.addView(this._progressView, (ViewGroup.LayoutParams)localObject);
      return localRelativeLayout;
      if ("com.microsoft.onlineid.internal.SIGN_UP".equals(localObject)) {
        localObject = "msa_sdk_webflow_webview_sign_up";
      } else {
        localObject = "msa_sdk_webflow_webview_resolve_interrupt";
      }
    }
  }
  
  private void disableSavePasswordInWebView()
  {
    if (Build.VERSION.SDK_INT < 18) {
      this._webView.getSettings().setSavePassword(false);
    }
  }
  
  public static ApiRequest getFlowRequest(Context paramContext, Uri paramUri, String paramString, boolean paramBoolean, WebFlowTelemetryData paramWebFlowTelemetryData)
  {
    new ApiRequest(paramContext, new Intent().setClass(paramContext, WebFlowActivity.class).setAction(paramString).setData(paramUri).putExtra("com.microsoft.onlineid.internal.ui.FullScreen", paramBoolean).putExtras(paramWebFlowTelemetryData.asBundle()))
    {
      public void executeAsync()
      {
        getContext().startActivity(asIntent());
      }
    };
  }
  
  private void initializeSendLogsHandler()
  {
    if (Settings.isDebugBuild())
    {
      this._logHandler = new SendLogsHandler(this);
      this._logHandler.setSendScreenshot(true);
    }
  }
  
  private void onReceivedWebError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    paramWebView.stopLoading();
    paramWebView.loadUrl("about:blank");
    ClientAnalytics.get().logEvent("Performance", "No network connectivity", "During web flow");
    sendResult(1, new ApiResult().setException(new NetworkException(String.format(Locale.US, "Error code: %d, Error description: %s, Failing url: %s", new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 }))).asBundle());
    finish();
  }
  
  private boolean overrideUrlLoading(WebView paramWebView, String paramString)
  {
    return false;
  }
  
  private void showLoadingFinished(WebView paramWebView, String paramString)
  {
    this._progressView.stopAnimation();
    if (this._pageLoadTimingEvent != null) {
      this._pageLoadTimingEvent.end();
    }
  }
  
  private void showLoadingStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    this._progressView.startAnimation();
    this._pageLoadTimingEvent = ClientAnalytics.get().createTimedEvent("Rendering", "WebWizard page load", this._scenario).start();
  }
  
  public void cancel()
  {
    sendResult(0, null);
  }
  
  public void onBackPressed()
  {
    if ((this._webView.canGoBack()) && (!this._webView.getUrl().startsWith(this._startUrl)))
    {
      this._webView.loadUrl("javascript:OnBack()");
      return;
    }
    cancel();
  }
  
  protected final void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(createInitialUI());
    CookieSyncManager.createInstance(this);
    CookieManager.getInstance().setAcceptCookie(true);
    CookieManager.getInstance().removeAllCookie();
    configureWebView(paramBundle, new WebFlowTelemetryData(getIntent().getExtras()));
    disableSavePasswordInWebView();
    initializeSendLogsHandler();
    paramBundle = getIntent().getData();
    Uri localUri = Uris.appendMarketQueryString(getApplicationContext(), paramBundle);
    paramBundle = localUri;
    if (!NetworkConnectivity.isAirplaneModeOn(getApplicationContext())) {
      paramBundle = Uris.appendPhoneDigits(new TelephonyManagerReader(getApplicationContext()), localUri);
    }
    this._startUrl = Uris.appendEmails(new AccountManagerReader(getApplicationContext()), paramBundle).toString();
    if (Settings.isDebugBuild()) {
      Logger.info("Web flow starting URL: " + this._startUrl);
    }
    this._webView.loadUrl(this._startUrl);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (this._logHandler != null) {
      this._logHandler.trySendLogsOnKeyEvent(paramInt);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public void onPause()
  {
    super.onPause();
    unregisterReceiver(this._smsReceiver);
  }
  
  protected void onResume()
  {
    super.onResume();
    this._smsReceiver = new SmsReceiver(this._javaScriptBridge);
    IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
    localIntentFilter.setPriority(Integer.MAX_VALUE);
    registerReceiver(this._smsReceiver, localIntentFilter);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this._webTelemetryRecorder.saveInstanceState(paramBundle);
  }
  
  public final void onStart()
  {
    super.onStart();
    String str = getIntent().getAction();
    if ("com.microsoft.onlineid.internal.SIGN_IN".equals(str)) {
      this._scenario = "sign in";
    }
    for (;;)
    {
      ClientAnalytics.get().logScreenView("Web wizard (" + this._scenario + ")");
      return;
      if ("com.microsoft.onlineid.internal.SIGN_UP".equals(str)) {
        this._scenario = "sign up";
      } else if ("com.microsoft.onlineid.internal.RESOLVE_INTERRUPT".equals(str)) {
        this._scenario = "auth url";
      } else if (TextUtils.isEmpty(str)) {
        this._scenario = "not specified";
      } else {
        this._scenario = str;
      }
    }
  }
  
  public void sendResult(int paramInt, Bundle paramBundle)
  {
    ApiRequest localApiRequest = new ApiRequest(getApplicationContext(), getIntent());
    Intent localIntent = localApiRequest.getContinuation();
    ResultReceiver localResultReceiver = localApiRequest.getResultReceiver();
    Bundle localBundle = paramBundle;
    if (this._webTelemetryRecorder.hasEvents()) {
      localBundle = new ApiResult(paramBundle).setWebFlowTelemetryFields(this._webTelemetryRecorder).asBundle();
    }
    if ((localIntent != null) && (paramInt == -1)) {
      localApiRequest.sendSuccess(new ApiResult(localBundle));
    }
    for (;;)
    {
      finish();
      if ((paramInt == -1) && ((getIntent().getFlags() & 0x10000) == 65536)) {
        overridePendingTransition(0, 0);
      }
      return;
      if (localResultReceiver == null) {
        break;
      }
      localResultReceiver.send(paramInt, localBundle);
    }
    if (localBundle != null) {}
    for (paramBundle = new Intent().putExtras(localBundle);; paramBundle = null)
    {
      setResult(paramInt, paramBundle);
      break;
    }
  }
  
  private class HostedWebChromeClient
    extends WebChromeClient
  {
    private HostedWebChromeClient() {}
    
    public boolean onCreateWindow(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
    {
      try
      {
        ((WebView.WebViewTransport)paramMessage.obj).setWebView(new WebView(WebFlowActivity.this));
        paramMessage.sendToTarget();
        WebFlowActivity.this._javaScriptBridge.setIsOutOfBandInterrupt();
        return true;
      }
      catch (ClassCastException paramWebView)
      {
        Assertion.check(false, "resultMsg is not a WebViewTransport");
      }
      return false;
    }
  }
  
  private class HostedWebViewClient
    extends WebViewClient
  {
    private long _finished;
    private final BundledAssetVendor _precachedAssetVendor = BundledAssetVendor.getInstance(WebFlowActivity.this.getApplicationContext());
    private long _started;
    
    public HostedWebViewClient() {}
    
    public BundledAssetVendor getAssetVendor()
    {
      return this._precachedAssetVendor;
    }
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      this._finished = SystemClock.elapsedRealtime();
      super.onPageFinished(paramWebView, paramString);
      WebFlowActivity.this.showLoadingFinished(paramWebView, paramString);
      if (Settings.isDebugBuild()) {
        Logger.info("Page load time = " + Long.toString(this._finished - this._started));
      }
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      WebFlowActivity.this.showLoadingStarted(paramWebView, paramString, paramBitmap);
      this._started = SystemClock.elapsedRealtime();
      Logger.info("New page loaded: " + paramString);
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      WebFlowActivity.this.onReceivedWebError(paramWebView, paramInt, paramString1, paramString2);
    }
    
    @TargetApi(21)
    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, WebResourceRequest paramWebResourceRequest)
    {
      return shouldInterceptRequest(paramWebView, paramWebResourceRequest.getUrl().toString());
    }
    
    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
    {
      return this._precachedAssetVendor.getAsset(paramString);
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      if (!WebFlowActivity.this.overrideUrlLoading(paramWebView, paramString)) {
        return super.shouldOverrideUrlLoading(paramWebView, paramString);
      }
      return true;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\ui\WebFlowActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */