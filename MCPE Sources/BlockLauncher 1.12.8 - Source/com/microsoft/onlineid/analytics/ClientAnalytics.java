package com.microsoft.onlineid.analytics;

import android.content.Context;
import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.AppViewBuilder;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.HitBuilders.ExceptionBuilder;
import com.google.android.gms.analytics.HitBuilders.TimingBuilder;
import com.google.android.gms.analytics.Tracker;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.NetworkConnectivity;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.sts.Cryptography;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ClientAnalytics
  implements IClientAnalytics
{
  public static final String AboutScreen = "About screen";
  public static final String AccountAddPendingScreen = "Account add pending screen";
  public static final String AccountAddedScreen = "Account add done screen";
  public static final String AccountPickerScreen = "Account picker";
  public static final String AccountsScreen = "Accounts screen";
  public static final String AddAccount = "Add account";
  public static final String AppAccountsCategory = "Authenticator accounts";
  public static final String AppCertificatesCategory = "Application certificates";
  public static final String ApproveSession = "Approve session";
  public static final String AtStartOfWebFlow = "At start of web flow";
  public static final String AvailableCertificates = "Available certificates";
  public static final String CloudRegistrationCategory = "Notification registration";
  public static final String CollectionConsistencyError = "Collection consistency error";
  public static final String CountryCode = "Country code";
  public static final String DenySession = "Deny session";
  public static final String DisableSessionApproval = "Disable session approval";
  public static final String DismissSession = "Dismiss session";
  private static final int DispatchPeriodSeconds = 10;
  public static final String DoesntExistInAccountManager = "Does not exist in Account Manager";
  public static final String DoesntExistInMeContact = "Does not exist in Me Contact";
  public static final String DoesntExistInTelephonyManager = "Does not exist in Telephony Manager";
  public static final String DuringWebFlow = "During web flow";
  public static final String EnableSessionApproval = "Enable session approval";
  public static final String EnableSessionApprovalWithoutRegistrationID = "Enable session approval without GCM registration ID";
  public static final String ExistsInAccountManager = "Exists in Account Manager";
  public static final String ExistsInMeContact = "Exists in Me Contact";
  public static final String ExistsInTelephonyManager = "Exists in Telephony Manager";
  public static final String FailedLabel = "Failed";
  public static final String FailedToCheckUnlockProcedure = "The check for an unlock procedure failed";
  public static final String FailedToGetMarketizedName = "Failed to get marketized name";
  public static final String FirstName = "First name";
  public static final String FirstRunScreen = "First run screen";
  public static final String GcmIDAddedToAccount = "GCM ID added to account on MSA server after registration";
  public static final String GcmIDInGoodState = "with GCM ID in good state";
  public static final String GcmIDMismatch = "with account GCM ID != app GCM ID";
  public static final String GcmRegistrationEvent = "GCM registration";
  private static final String GoogleAnalyticsPropertyID = "UA-50206275-2";
  public static final String GoogleEmail = "Google email";
  public static final String GoogleEmailCount = "Google email count";
  public static final String HasUnlockProcedure = "User has an unlock procedure";
  public static final String HomePhoneNumber = "Home phone number";
  public static final String ImplicitDisable = "implicit (removed account)";
  public static final String InitiateAccountAdd = "Initiate account add";
  private static IClientAnalytics Instance;
  public static final String LastName = "Last name";
  public static final String LoadProfileImage = "Load profile image";
  public static final String LoginCloudPinCollectionPendingScreen = "Login cloud pin pending screen";
  public static final String LoginCloudPinCollectionVerificationScreen = "Login cloud pin collection screen";
  public static final String MigrationAttempts = "Migration attempts";
  public static final String MigrationCategory = "Migration and Upgrade";
  public static final String MobilePhoneNumber = "Mobile phone number";
  public static final String NavigationCategory = "Navigation";
  public static final String NgcApproveSession = "Approve NGC session";
  public static final String NgcAttemptingToApproveSession = "Attempting to approve NGC session";
  public static final String NgcCategory = "NGC";
  public static final String NgcRegistrationCloudPinMismatch = "Entered cloud PINs did not match";
  public static final String NgcRegistrationFailed = "Device registration failed";
  public static final String NgcRegistrationSucceeded = "Device registration succeeded";
  public static final String NgcSessionApproved = "Session Approved";
  public static final String NoAccountGcmID = "with no GCM ID for account";
  public static final String NoAppGcmID = "with no GCM ID for current app version";
  public static final String NoNetworkConnectivity = "No network connectivity";
  public static final String NoUnlockProcedure = "User has no unlock procedure";
  public static final String NotificationErrorTap = "Tapped session error notification";
  public static final String NotificationExpired = "Session notification expired";
  public static final String NotificationScreen = "Notification drawer";
  public static final String NotificationTap = "Tapped session notification";
  public static final String PerformanceCategory = "Performance";
  public static final String PickAccount = "Pick an account";
  public static final String QRCodeAuthenticationCategory = "QR code authentication";
  public static final String QRCodeScannerScreen = "QR code scanner screen";
  public static final String RefreshSessionList = "Refresh sessions list";
  public static final String RegistrationCloudPinCollectionPendingScreen = "Registration cloud pin collection pending screen";
  public static final String RegistrationCloudPinCollectionSetupScreen = "Registration cloud pin collection setup screen";
  public static final String RegistrationCloudPinCollectionVerificationScreen = "Registration cloud pin collection verification screen";
  public static final String RemoveAccount = "Remove account";
  public static final String RenderingCategory = "Rendering";
  public static final String ScanMsaQRCode = "Scanned Msa QR code";
  public static final String ScanNonMsaQRCode = "Scanned non-Msa QR code";
  public static final String ScreenNameParam = "&cd";
  public static final String SdkCategory = "SDK";
  public static final String SessionApprovalCategory = "Session approval";
  public static final String SessionSeenWithoutNotification = "Session seen without notification";
  private static final int SessionTimeoutSeconds = 5;
  public static final String SessionsCategory = "Sessions";
  public static final String SessionsScreen = "Sessions screen";
  public static final String SignUp = "Sign up success";
  public static final String SmsVerificationCategory = "SMS verification";
  public static final String SsoError = "SSO error";
  public static final String SsoFallback = "SSO fallback";
  public static final String StorageCategory = "Storage";
  public static final String StsRequestCategory = "STS requests";
  public static final String SucceededLabel = "Succeeded";
  public static final String TapQRCodeImageButton = "Tap QR code image button to open scanner";
  public static final String TapQRCodeTextLink = "Tap QR code text link to open scanner";
  public static final String TotalAccounts = "Total accounts";
  public static final String TotalAppAccountsCategory = "Total authenticator accounts";
  public static final String TotalSAAccountsCategory = "Total session approval accounts";
  public static final String TotalTrustedSsoServices = "Total trusted SSO services";
  public static final String UniqueEmailCount = "Unique email count";
  public static final String UnlockProcedureCategory = "Device unlock procedure";
  public static final String UserDataCategory = "User data";
  public static final String Verified = "Successfully verified";
  public static final String ViaAccountPicker = "via account picker";
  public static final String ViaAccountsScreen = "via accounts screen";
  public static final String ViaAddAccountInApp = "via add account in app";
  public static final String ViaAddButton = "via add button";
  public static final String ViaFirstRun = "via first run";
  public static final String ViaFirstTimeUser = "via first time user";
  public static final String ViaMenu = "via menu";
  public static final String ViaNotification = "via notification drawer";
  public static final String ViaReturningUser = "via returning user";
  public static final String ViaSessionsScreen = "via sessions screen";
  public static final String WebWizardScreen = "Web wizard";
  private final Context _applicationContext;
  private boolean _clockSkewLogged = false;
  private final Tracker _tracker;
  
  protected ClientAnalytics()
  {
    this._applicationContext = null;
    this._tracker = null;
  }
  
  private ClientAnalytics(Context paramContext)
  {
    this._applicationContext = paramContext;
    Object localObject = GoogleAnalytics.getInstance(paramContext);
    ((GoogleAnalytics)localObject).setLocalDispatchPeriod(10);
    this._tracker = ((GoogleAnalytics)localObject).newTracker("UA-50206275-2");
    this._tracker.setAppName("Authenticator");
    this._tracker.setSessionTimeout(5L);
    localObject = new ExceptionReporter(this._tracker, Thread.getDefaultUncaughtExceptionHandler(), paramContext);
    ((ExceptionReporter)localObject).setExceptionParser(new MsaExceptionParser(paramContext, null));
    Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)localObject);
    this._tracker.set("&cd1", NetworkConnectivity.getNetworkTypeDescription(paramContext));
  }
  
  /* Error */
  public static IClientAnalytics get()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 433	com/microsoft/onlineid/analytics/ClientAnalytics:Instance	Lcom/microsoft/onlineid/analytics/IClientAnalytics;
    //   6: ifnonnull +16 -> 22
    //   9: new 435	com/microsoft/onlineid/analytics/NopClientAnalytics
    //   12: dup
    //   13: invokespecial 436	com/microsoft/onlineid/analytics/NopClientAnalytics:<init>	()V
    //   16: astore_0
    //   17: ldc 2
    //   19: monitorexit
    //   20: aload_0
    //   21: areturn
    //   22: getstatic 433	com/microsoft/onlineid/analytics/ClientAnalytics:Instance	Lcom/microsoft/onlineid/analytics/IClientAnalytics;
    //   25: astore_0
    //   26: goto -9 -> 17
    //   29: astore_0
    //   30: ldc 2
    //   32: monitorexit
    //   33: aload_0
    //   34: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   16	10	0	localObject1	Object
    //   29	5	0	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   3	17	29	finally
    //   22	26	29	finally
  }
  
  /* Error */
  public static void initialize(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 433	com/microsoft/onlineid/analytics/ClientAnalytics:Instance	Lcom/microsoft/onlineid/analytics/IClientAnalytics;
    //   6: ifnonnull +23 -> 29
    //   9: aload_0
    //   10: invokestatic 443	com/microsoft/onlineid/internal/PackageInfoHelper:isRunningInAuthenticatorApp	(Landroid/content/Context;)Z
    //   13: ifeq +20 -> 33
    //   16: new 2	com/microsoft/onlineid/analytics/ClientAnalytics
    //   19: dup
    //   20: aload_0
    //   21: invokespecial 445	com/microsoft/onlineid/analytics/ClientAnalytics:<init>	(Landroid/content/Context;)V
    //   24: astore_0
    //   25: aload_0
    //   26: putstatic 433	com/microsoft/onlineid/analytics/ClientAnalytics:Instance	Lcom/microsoft/onlineid/analytics/IClientAnalytics;
    //   29: ldc 2
    //   31: monitorexit
    //   32: return
    //   33: new 435	com/microsoft/onlineid/analytics/NopClientAnalytics
    //   36: dup
    //   37: invokespecial 436	com/microsoft/onlineid/analytics/NopClientAnalytics:<init>	()V
    //   40: astore_0
    //   41: goto -16 -> 25
    //   44: astore_0
    //   45: ldc 2
    //   47: monitorexit
    //   48: aload_0
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   3	25	44	finally
    //   25	29	44	finally
    //   33	41	44	finally
  }
  
  public TimedAnalyticsEvent createTimedEvent(String paramString1, String paramString2)
  {
    return createTimedEvent(paramString1, paramString2, null);
  }
  
  public TimedAnalyticsEvent createTimedEvent(String paramString1, String paramString2, String paramString3)
  {
    return new TimedAnalyticsEvent(this._tracker, paramString1, paramString2, paramString3);
  }
  
  public IClientAnalytics logCertificates(Map<String, byte[]> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      String str = Cryptography.encodeBase32((byte[])localEntry.getValue());
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append(str).append('(').append((String)localEntry.getKey()).append(')');
    }
    send(new HitBuilders.EventBuilder().setCategory("Application certificates").setAction("Available certificates").setLabel(localStringBuilder.toString()).build());
    return this;
  }
  
  public IClientAnalytics logClockSkew(long paramLong)
  {
    HitBuilders.TimingBuilder localTimingBuilder;
    if (!this._clockSkewLogged)
    {
      localTimingBuilder = new HitBuilders.TimingBuilder().setCategory("Clock skew").setVariable("Clock skew adjusted");
      if (paramLong >= 0L) {
        break label66;
      }
    }
    label66:
    for (String str = "Server ahead";; str = "Client ahead")
    {
      send(localTimingBuilder.setLabel(str).setValue(Math.abs(paramLong)).build());
      this._clockSkewLogged = true;
      return this;
    }
  }
  
  public IClientAnalytics logEvent(String paramString1, String paramString2)
  {
    return logEvent(paramString1, paramString2, null, null);
  }
  
  public IClientAnalytics logEvent(String paramString1, String paramString2, String paramString3)
  {
    return logEvent(paramString1, paramString2, paramString3, null);
  }
  
  public IClientAnalytics logEvent(String paramString1, String paramString2, String paramString3, Long paramLong)
  {
    if ((paramString1 == null) || (paramString2 == null))
    {
      Logger.error("At least category and action must be specified to log an event.");
      Assertion.check(false);
      return this;
    }
    HitBuilders.EventBuilder localEventBuilder = new HitBuilders.EventBuilder();
    localEventBuilder.setCategory(paramString1);
    localEventBuilder.setAction(paramString2);
    if (paramString3 != null) {
      localEventBuilder.setLabel(paramString3);
    }
    if (paramLong != null) {
      localEventBuilder.setValue(paramLong.longValue());
    }
    send(localEventBuilder.build());
    return this;
  }
  
  public IClientAnalytics logException(Throwable paramThrowable)
  {
    if (paramThrowable != null) {}
    for (boolean bool = true;; bool = false)
    {
      Assertion.check(bool);
      paramThrowable = new MsaExceptionParser(this._applicationContext, null).getDescription(Thread.currentThread().getName(), paramThrowable);
      send(new HitBuilders.ExceptionBuilder().setDescription(paramThrowable).setFatal(false).build());
      return this;
    }
  }
  
  public IClientAnalytics logScreenView(String paramString)
  {
    if (paramString != null) {}
    for (boolean bool = true;; bool = false)
    {
      Assertion.check(bool);
      setScreenName(paramString);
      send(new HitBuilders.AppViewBuilder().build());
      return this;
    }
  }
  
  public IClientAnalytics logTotalAccountsEvent(String paramString, int paramInt1, int paramInt2)
  {
    String str = Strings.pluralize(paramInt1, "account", "accounts") + " to " + Strings.pluralize(paramInt2, "account", "accounts");
    return get().logEvent(paramString, str);
  }
  
  public IClientAnalytics send(Map<String, String> paramMap)
  {
    this._tracker.send(paramMap);
    return this;
  }
  
  protected void setScreenName(String paramString)
  {
    this._tracker.setScreenName(paramString);
  }
  
  public void setTestMode()
  {
    GoogleAnalytics.getInstance(this._applicationContext).setDryRun(true);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\analytics\ClientAnalytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */