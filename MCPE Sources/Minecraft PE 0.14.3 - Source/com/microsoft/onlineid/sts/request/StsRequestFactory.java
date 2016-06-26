package com.microsoft.onlineid.sts.request;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.internal.Applications;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.internal.PackageInfoHelper;
import com.microsoft.onlineid.internal.transport.TransportFactory;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.sts.ClockSkewManager;
import com.microsoft.onlineid.sts.DeviceCredentials;
import com.microsoft.onlineid.sts.DeviceIdentity;
import com.microsoft.onlineid.sts.ServerConfig;
import com.microsoft.onlineid.sts.ServerConfig.Endpoint;
import com.microsoft.onlineid.sts.XmlSigner;

public class StsRequestFactory
{
  protected final Context _applicationContext;
  private final ClockSkewManager _clockSkewManager;
  
  public StsRequestFactory(Context paramContext)
  {
    this._applicationContext = paramContext;
    this._clockSkewManager = new ClockSkewManager(paramContext);
  }
  
  public StsRequestFactory(Context paramContext, ClockSkewManager paramClockSkewManager)
  {
    this._applicationContext = paramContext;
    this._clockSkewManager = paramClockSkewManager;
  }
  
  private String buildTelemetry()
  {
    String str = this._applicationContext.getPackageManager().getInstallerPackageName(this._applicationContext.getPackageName());
    if (TextUtils.isEmpty(str)) {
      return "";
    }
    return "PackageMarket=" + str;
  }
  
  public DeviceAuthRequest createDeviceAuthRequest(DeviceIdentity paramDeviceIdentity)
  {
    DeviceAuthRequest localDeviceAuthRequest = new DeviceAuthRequest();
    initializeRequest(localDeviceAuthRequest);
    localDeviceAuthRequest.setDeviceCredentials(paramDeviceIdentity.getCredentials());
    return localDeviceAuthRequest;
  }
  
  public DeviceProvisionRequest createDeviceProvisionRequest(DeviceCredentials paramDeviceCredentials)
  {
    DeviceProvisionRequest localDeviceProvisionRequest = new DeviceProvisionRequest();
    initializeRequest(localDeviceProvisionRequest);
    localDeviceProvisionRequest.setDeviceCredentials(paramDeviceCredentials);
    return localDeviceProvisionRequest;
  }
  
  public ServiceRequest createServiceRequest(AuthenticatorUserAccount paramAuthenticatorUserAccount, DeviceIdentity paramDeviceIdentity, ISecurityScope paramISecurityScope, String paramString1, String paramString2)
  {
    return createServiceRequest(paramAuthenticatorUserAccount, paramDeviceIdentity, paramISecurityScope, paramString1, paramString2, false);
  }
  
  public ServiceRequest createServiceRequest(AuthenticatorUserAccount paramAuthenticatorUserAccount, DeviceIdentity paramDeviceIdentity, ISecurityScope paramISecurityScope, String paramString1, String paramString2, boolean paramBoolean)
  {
    Objects.verifyArgumentNotNull(paramAuthenticatorUserAccount, "userAccount");
    Objects.verifyArgumentNotNull(paramDeviceIdentity, "deviceIdentity");
    Objects.verifyArgumentNotNull(paramISecurityScope, "scope");
    ServiceRequest localServiceRequest = new ServiceRequest();
    initializeRequest(localServiceRequest);
    localServiceRequest.setRequestFlights(paramBoolean);
    localServiceRequest.setUserDA(paramAuthenticatorUserAccount.getDAToken());
    localServiceRequest.setDeviceDA(paramDeviceIdentity.getDAToken());
    localServiceRequest.addRequest(paramISecurityScope);
    localServiceRequest.setFlowToken(paramString2);
    localServiceRequest.setClientAppUri(Applications.buildClientAppUri(this._applicationContext, paramString1));
    localServiceRequest.setTelemetry(buildTelemetry());
    return localServiceRequest;
  }
  
  public ServiceRequest createServiceRequest(AuthenticatorUserAccount paramAuthenticatorUserAccount, DeviceIdentity paramDeviceIdentity, String paramString, ISecurityScope paramISecurityScope)
  {
    return createServiceRequest(paramAuthenticatorUserAccount, paramDeviceIdentity, paramISecurityScope, paramString, null);
  }
  
  protected ServerConfig getConfig()
  {
    return new ServerConfig(this._applicationContext);
  }
  
  protected void initializeRequest(AbstractStsRequest<?> paramAbstractStsRequest)
  {
    ServerConfig localServerConfig = getConfig();
    ServerConfig.Endpoint localEndpoint = paramAbstractStsRequest.getEndpoint();
    if (localEndpoint != null) {}
    for (boolean bool = true;; bool = false)
    {
      Assertion.check(bool);
      paramAbstractStsRequest.setDestination(localServerConfig.getUrl(localEndpoint));
      paramAbstractStsRequest.setTransportFactory(new TransportFactory(this._applicationContext));
      paramAbstractStsRequest.setClockSkewManager(this._clockSkewManager);
      paramAbstractStsRequest.setMsaAppVersionCode(PackageInfoHelper.getCurrentAppVersionCode(this._applicationContext));
      if ((paramAbstractStsRequest instanceof ISignableRequest)) {
        ((ISignableRequest)paramAbstractStsRequest).setXmlSigner(new XmlSigner());
      }
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\request\StsRequestFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */