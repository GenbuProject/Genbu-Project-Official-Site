package com.microsoft.onlineid.internal.sso;

import android.app.PendingIntent;
import android.os.Bundle;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.OnlineIdConfiguration;
import com.microsoft.onlineid.OnlineIdConfiguration.PreferredSignUpMemberNameType;
import com.microsoft.onlineid.SecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.exception.AccountNotFoundException;
import com.microsoft.onlineid.internal.sso.client.ClientConfigUpdateNeededException;
import com.microsoft.onlineid.internal.sso.exception.ClientNotAuthorizedException;
import com.microsoft.onlineid.internal.sso.exception.UnsupportedClientVersionException;
import com.microsoft.onlineid.internal.storage.StorageException;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.DeviceCredentials;
import com.microsoft.onlineid.sts.DeviceIdentity;
import com.microsoft.onlineid.sts.IntegerCodeServerError;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.exception.InvalidResponseException;
import com.microsoft.onlineid.sts.exception.StsException;
import java.util.Date;

public class BundleMarshaller
{
  public static final String AccountPickerBodyKey = "com.microsoft.onlineid.account_picker_body";
  public static final String ActivityResultTypeKey = "com.microsoft.onlineid.result_type";
  public static final String AllUsersKey = "com.microsoft.onlineid.all_users";
  public static final String BackupDeviceKey = "com.microsoft.onlineid.backup_device";
  public static final String BackupUsersKey = "com.microsoft.onlineid.backup_users";
  public static final String CidExclusionListKey = "com.microsoft.onlineid.cid_exclusion_list";
  public static final String ClientConfigLastDownloadedTimeKey = "com.microsoft.onlineid.client_config_last_downloaded_time";
  public static final String ClientConfigVersionKey = "com.microsoft.onlineid.client_config_version";
  public static final String ClientPackageNameKey = "com.microsoft.onlineid.client_package_name";
  public static final String ClientSdkVersionKey = "com.microsoft.onlineid.client_sdk_version";
  public static final String ClientSsoVersionKey = "com.microsoft.onlineid.client_sso_version";
  public static final String ClientStateBundleKey = "com.microsoft.onlineid.client_state";
  public static final String CobrandingIdKey = "com.microsoft.onlineid.cobranding_id";
  public static final String DeviceDATokenKey = "com.microsoft.onlineid.device_datoken";
  public static final String DeviceDATokenObtainedTime = "com.microsoft.onlineid.device_datoken_obtained_time";
  public static final String DevicePasswordKey = "com.microsoft.onlineid.device_password";
  public static final String DeviceProvisionTimeKey = "com.microsoft.onlineid.device_provision_time";
  public static final String DevicePuidKey = "com.microsoft.onlineid.device_puid";
  public static final String DeviceSessionKeyBase64Key = "com.microsoft.onlineid.device_session_key_base64";
  public static final String DeviceUsernameKey = "com.microsoft.onlineid.device_username";
  public static final String ErrorCodeKey = "com.microsoft.onlineid.error_code";
  public static final String ErrorMessageKey = "com.microsoft.onlineid.error_message";
  public static final String IsRegisteredForNgc = "com.microsoft.onlineid.registered_for_ngc";
  public static final String IsSignedOutOfThisAppOnlyKey = "com.microsoft.onlineid.signed_out_this_app_only";
  public static final String KeyPrefix = "com.microsoft.onlineid.";
  public static final String PreferredMembernameTypeKey = "com.microsoft.onlineid.preferred_membername_type";
  public static final String RedirectRequestToKey = "com.microsoft.onlineid.redirect_request_to";
  public static final String RemoteConnectCodeKey = "com.microsoft.onlineid.remote_connect_code";
  public static final String SignInPrefillUsernameKey = "com.microsoft.onlineid.prefill_username";
  public static final String TicketExpirationTimeKey = "com.microsoft.onlineid.ticket_expiration_time";
  public static final String TicketPolicyKey = "com.microsoft.onlineid.ticket_scope_policy";
  public static final String TicketTargetKey = "com.microsoft.onlineid.ticket_scope_target";
  public static final String TicketValueKey = "com.microsoft.onlineid.ticket_value";
  public static final String UiResolutionIntentKey = "com.microsoft.onlineid.ui_resolution_intent";
  public static final String UserCidKey = "com.microsoft.onlineid.user_cid";
  public static final String UserDATokenKey = "com.microsoft.onlineid.user_datoken";
  public static final String UserDATokenObtainedTimeKey = "com.microsoft.onlineid.user_datoken_obtained_time";
  public static final String UserDisplayNameKey = "com.microsoft.onlineid.user_display_name";
  public static final String UserJustAddedKey = "com.microsoft.onlineid.user_just_added";
  public static final String UserPuidKey = "com.microsoft.onlineid.user_puid";
  public static final String UserSessionKeyBase64Key = "com.microsoft.onlineid.user_session_key_base64";
  public static final String UserUsernameKey = "com.microsoft.onlineid.user_username";
  
  public static DeviceIdentity deviceAccountFromBundle(Bundle paramBundle)
    throws BundleMarshallerException
  {
    try
    {
      DeviceCredentials localDeviceCredentials = new DeviceCredentials(paramBundle.getString("com.microsoft.onlineid.device_username"), paramBundle.getString("com.microsoft.onlineid.device_password"));
      DAToken localDAToken = new DAToken(paramBundle.getString("com.microsoft.onlineid.device_datoken"), paramBundle.getByteArray("com.microsoft.onlineid.device_session_key_base64"));
      paramBundle = new DeviceIdentity(localDeviceCredentials, paramBundle.getString("com.microsoft.onlineid.device_puid"), localDAToken);
      return paramBundle;
    }
    catch (IllegalArgumentException paramBundle)
    {
      throw new BundleMarshallerException("Could not create DeviceIdentity from Bundle.", paramBundle);
    }
  }
  
  public static Bundle deviceAccountToBundle(DeviceIdentity paramDeviceIdentity)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("com.microsoft.onlineid.device_puid", paramDeviceIdentity.getPuid());
    DeviceCredentials localDeviceCredentials = paramDeviceIdentity.getCredentials();
    localBundle.putString("com.microsoft.onlineid.device_username", localDeviceCredentials.getUsername());
    localBundle.putString("com.microsoft.onlineid.device_password", localDeviceCredentials.getPassword());
    paramDeviceIdentity = paramDeviceIdentity.getDAToken();
    localBundle.putString("com.microsoft.onlineid.device_datoken", paramDeviceIdentity.getToken());
    localBundle.putByteArray("com.microsoft.onlineid.device_session_key_base64", paramDeviceIdentity.getSessionKey());
    return localBundle;
  }
  
  public static Bundle errorToBundle(SsoServiceError paramSsoServiceError, String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("com.microsoft.onlineid.error_code", paramSsoServiceError.getCode());
    localBundle.putString("com.microsoft.onlineid.error_message", paramString);
    return localBundle;
  }
  
  public static AuthenticationException exceptionFromBundle(Bundle paramBundle)
    throws BundleMarshallerException
  {
    int i = paramBundle.getInt("com.microsoft.onlineid.error_code");
    String str = paramBundle.getString("com.microsoft.onlineid.error_message");
    if (i != 0)
    {
      SsoServiceError localSsoServiceError = SsoServiceError.get(i);
      switch (localSsoServiceError)
      {
      default: 
        return new InternalException(str);
      case ???: 
        return new ClientNotAuthorizedException(str);
      case ???: 
        return new UnsupportedClientVersionException(str);
      case ???: 
        return new InternalException(new StorageException(str));
      case ???: 
        return new InternalException(new IllegalArgumentException(str));
      case ???: 
        return new AccountNotFoundException(str);
      case ???: 
        return new NetworkException(str);
      case ???: 
        return new StsException(str, new StsError(new IntegerCodeServerError(0)));
      case ???: 
        return new InvalidResponseException(str);
      case ???: 
        return new MasterRedirectException(str, paramBundle.getString("com.microsoft.onlineid.redirect_request_to"));
      }
      return new ClientConfigUpdateNeededException(str);
    }
    if (str != null) {
      return new InternalException(str);
    }
    throw new BundleMarshallerException("Neither error_code nor error_message was found in the given Bundle.");
  }
  
  public static Bundle exceptionToBundle(Exception paramException)
  {
    try
    {
      throw paramException;
    }
    catch (ClientNotAuthorizedException paramException)
    {
      return errorToBundle(SsoServiceError.ClientNotAuthorized, paramException.getMessage());
    }
    catch (UnsupportedClientVersionException paramException)
    {
      return errorToBundle(SsoServiceError.UnsupportedClientVersion, paramException.getMessage());
    }
    catch (StorageException paramException)
    {
      return errorToBundle(SsoServiceError.StorageException, paramException.getMessage());
    }
    catch (IllegalArgumentException paramException)
    {
      return errorToBundle(SsoServiceError.IllegalArgumentException, paramException.getMessage());
    }
    catch (AccountNotFoundException paramException)
    {
      return errorToBundle(SsoServiceError.AccountNotFound, paramException.getMessage());
    }
    catch (NetworkException paramException)
    {
      return errorToBundle(SsoServiceError.NetworkException, paramException.getMessage());
    }
    catch (StsException paramException)
    {
      return errorToBundle(SsoServiceError.StsException, paramException.getMessage());
    }
    catch (InvalidResponseException paramException)
    {
      return errorToBundle(SsoServiceError.InvalidResponseException, paramException.getMessage());
    }
    catch (ClientConfigUpdateNeededException paramException)
    {
      return errorToBundle(SsoServiceError.ClientConfigUpdateNeededException, paramException.getMessage());
    }
    catch (MasterRedirectException paramException)
    {
      Bundle localBundle = errorToBundle(SsoServiceError.MasterRedirectException, paramException.getMessage());
      localBundle.putString("com.microsoft.onlineid.redirect_request_to", paramException.getRedirectRequestTo());
      return localBundle;
    }
    catch (Exception paramException) {}
    return errorToBundle(SsoServiceError.Unknown, paramException.getClass().getName() + ": " + paramException.getMessage());
  }
  
  public static boolean hasError(Bundle paramBundle)
  {
    int i = paramBundle.getInt("com.microsoft.onlineid.error_code");
    paramBundle = paramBundle.getString("com.microsoft.onlineid.error_message");
    return (i != 0) || (paramBundle != null);
  }
  
  public static boolean hasLimitedUserAccount(Bundle paramBundle)
  {
    return (paramBundle.getString("com.microsoft.onlineid.user_puid") != null) && (paramBundle.getString("com.microsoft.onlineid.user_cid") != null) && (paramBundle.getString("com.microsoft.onlineid.user_username") != null);
  }
  
  public static boolean hasPendingIntent(Bundle paramBundle)
  {
    return paramBundle.containsKey("com.microsoft.onlineid.ui_resolution_intent");
  }
  
  public static boolean hasScope(Bundle paramBundle)
  {
    String str = paramBundle.getString("com.microsoft.onlineid.ticket_scope_target", null);
    paramBundle = paramBundle.getString("com.microsoft.onlineid.ticket_scope_policy", null);
    return (str != null) && (paramBundle != null);
  }
  
  public static boolean hasTicket(Bundle paramBundle)
  {
    return (paramBundle.getString("com.microsoft.onlineid.ticket_scope_target") != null) && (paramBundle.getString("com.microsoft.onlineid.ticket_scope_policy") != null) && (paramBundle.getLong("com.microsoft.onlineid.ticket_expiration_time") != 0L) && (paramBundle.getString("com.microsoft.onlineid.ticket_value") != null);
  }
  
  public static AuthenticatorUserAccount limitedUserAccountFromBundle(Bundle paramBundle)
    throws BundleMarshallerException
  {
    try
    {
      AuthenticatorUserAccount localAuthenticatorUserAccount = new AuthenticatorUserAccount(paramBundle.getString("com.microsoft.onlineid.user_puid"), paramBundle.getString("com.microsoft.onlineid.user_cid"), paramBundle.getString("com.microsoft.onlineid.user_username"), null);
      localAuthenticatorUserAccount.setDisplayName(paramBundle.getString("com.microsoft.onlineid.user_display_name"));
      return localAuthenticatorUserAccount;
    }
    catch (IllegalArgumentException paramBundle)
    {
      throw new BundleMarshallerException("Could not create limited AuthenticatorUserAccount from Bundle.", paramBundle);
    }
  }
  
  public static Bundle limitedUserAccountToBundle(AuthenticatorUserAccount paramAuthenticatorUserAccount)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("com.microsoft.onlineid.user_cid", paramAuthenticatorUserAccount.getCid());
    localBundle.putString("com.microsoft.onlineid.user_puid", paramAuthenticatorUserAccount.getPuid());
    localBundle.putString("com.microsoft.onlineid.user_username", paramAuthenticatorUserAccount.getUsername());
    localBundle.putString("com.microsoft.onlineid.user_display_name", paramAuthenticatorUserAccount.getDisplayName());
    return localBundle;
  }
  
  public static Bundle onlineIdConfigurationToBundle(OnlineIdConfiguration paramOnlineIdConfiguration)
  {
    Bundle localBundle = new Bundle();
    OnlineIdConfiguration.PreferredSignUpMemberNameType localPreferredSignUpMemberNameType = paramOnlineIdConfiguration.getPreferredSignUpMemberNameType();
    if (localPreferredSignUpMemberNameType != OnlineIdConfiguration.PreferredSignUpMemberNameType.None) {
      localBundle.putString("com.microsoft.onlineid.preferred_membername_type", localPreferredSignUpMemberNameType.toString());
    }
    paramOnlineIdConfiguration = paramOnlineIdConfiguration.getCobrandingId();
    if ((paramOnlineIdConfiguration != null) && (!paramOnlineIdConfiguration.isEmpty())) {
      localBundle.putString("com.microsoft.onlineid.cobranding_id", paramOnlineIdConfiguration);
    }
    return localBundle;
  }
  
  public static PendingIntent pendingIntentFromBundle(Bundle paramBundle)
    throws BundleMarshallerException
  {
    paramBundle = (PendingIntent)paramBundle.getParcelable("com.microsoft.onlineid.ui_resolution_intent");
    if (paramBundle == null) {
      throw new BundleMarshallerException("PendingIntent not found in Bundle.");
    }
    return paramBundle;
  }
  
  public static Bundle pendingIntentToBundle(PendingIntent paramPendingIntent)
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("com.microsoft.onlineid.ui_resolution_intent", paramPendingIntent);
    return localBundle;
  }
  
  public static ISecurityScope scopeFromBundle(Bundle paramBundle)
    throws BundleMarshallerException
  {
    try
    {
      paramBundle = new SecurityScope(paramBundle.getString("com.microsoft.onlineid.ticket_scope_target"), paramBundle.getString("com.microsoft.onlineid.ticket_scope_policy"));
      return paramBundle;
    }
    catch (IllegalArgumentException paramBundle)
    {
      throw new BundleMarshallerException("Could not create SecurityScope from Bundle.", paramBundle);
    }
  }
  
  public static Bundle scopeToBundle(ISecurityScope paramISecurityScope)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("com.microsoft.onlineid.ticket_scope_target", paramISecurityScope.getTarget());
    localBundle.putString("com.microsoft.onlineid.ticket_scope_policy", paramISecurityScope.getPolicy());
    return localBundle;
  }
  
  public static Ticket ticketFromBundle(Bundle paramBundle)
    throws BundleMarshallerException
  {
    try
    {
      paramBundle = new Ticket(scopeFromBundle(paramBundle), new Date(paramBundle.getLong("com.microsoft.onlineid.ticket_expiration_time")), paramBundle.getString("com.microsoft.onlineid.ticket_value"));
      return paramBundle;
    }
    catch (IllegalArgumentException paramBundle)
    {
      throw new BundleMarshallerException("Could not create Ticket from Bundle.", paramBundle);
    }
  }
  
  public static Bundle ticketToBundle(Ticket paramTicket)
  {
    Bundle localBundle = scopeToBundle(paramTicket.getScope());
    localBundle.putString("com.microsoft.onlineid.ticket_value", paramTicket.getValue());
    localBundle.putLong("com.microsoft.onlineid.ticket_expiration_time", paramTicket.getExpiry().getTime());
    return localBundle;
  }
  
  public static AuthenticatorUserAccount userAccountFromBundle(Bundle paramBundle)
    throws BundleMarshallerException
  {
    try
    {
      DAToken localDAToken = userDATokenFromBundle(paramBundle);
      paramBundle = limitedUserAccountFromBundle(paramBundle);
      paramBundle.setDAToken(localDAToken);
      return paramBundle;
    }
    catch (IllegalArgumentException paramBundle)
    {
      throw new BundleMarshallerException("Could not create AuthenticatorUserAccount from Bundle.", paramBundle);
    }
  }
  
  public static Bundle userAccountToBundle(AuthenticatorUserAccount paramAuthenticatorUserAccount)
  {
    Bundle localBundle = limitedUserAccountToBundle(paramAuthenticatorUserAccount);
    localBundle.putAll(userDATokenToBundle(paramAuthenticatorUserAccount.getDAToken()));
    return localBundle;
  }
  
  public static DAToken userDATokenFromBundle(Bundle paramBundle)
    throws BundleMarshallerException
  {
    try
    {
      paramBundle = new DAToken(paramBundle.getString("com.microsoft.onlineid.user_datoken"), paramBundle.getByteArray("com.microsoft.onlineid.user_session_key_base64"));
      return paramBundle;
    }
    catch (IllegalArgumentException paramBundle)
    {
      throw new BundleMarshallerException("Could not create DAToken from Bundle.", paramBundle);
    }
  }
  
  public static Bundle userDATokenToBundle(DAToken paramDAToken)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("com.microsoft.onlineid.user_datoken", paramDAToken.getToken());
    localBundle.putByteArray("com.microsoft.onlineid.user_session_key_base64", paramDAToken.getSessionKey());
    return localBundle;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\sso\BundleMarshaller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */