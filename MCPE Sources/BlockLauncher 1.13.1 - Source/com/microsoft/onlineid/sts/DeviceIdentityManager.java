package com.microsoft.onlineid.sts;

import android.content.Context;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.sts.exception.InvalidResponseException;
import com.microsoft.onlineid.sts.exception.RequestThrottledException;
import com.microsoft.onlineid.sts.exception.StsException;
import com.microsoft.onlineid.sts.request.DeviceAuthRequest;
import com.microsoft.onlineid.sts.request.DeviceProvisionRequest;
import com.microsoft.onlineid.sts.request.StsRequestFactory;
import com.microsoft.onlineid.sts.response.DeviceAuthResponse;
import com.microsoft.onlineid.sts.response.DeviceProvisionResponse;

public class DeviceIdentityManager
{
  static final int MaxProvisionAttemptsPerCall = 3;
  private final Context _applicationContext;
  private DeviceCredentialGenerator _credentialGenerator;
  private StsRequestFactory _requestFactory;
  private final TypedStorage _storage;
  
  public DeviceIdentityManager(Context paramContext)
  {
    this._applicationContext = paramContext;
    this._storage = new TypedStorage(paramContext);
    this._requestFactory = null;
    this._credentialGenerator = null;
  }
  
  DeviceIdentityManager(TypedStorage paramTypedStorage, DeviceCredentialGenerator paramDeviceCredentialGenerator, StsRequestFactory paramStsRequestFactory)
  {
    this._applicationContext = null;
    this._storage = paramTypedStorage;
    this._credentialGenerator = paramDeviceCredentialGenerator;
    this._requestFactory = paramStsRequestFactory;
  }
  
  private boolean checkProvisionResponse(int paramInt, DeviceProvisionResponse paramDeviceProvisionResponse)
    throws RequestThrottledException, StsException
  {
    if (paramDeviceProvisionResponse.succeeded()) {
      return true;
    }
    paramDeviceProvisionResponse = paramDeviceProvisionResponse.getError();
    switch (paramDeviceProvisionResponse.getCode())
    {
    default: 
      throw new StsException("Unable to provision device", paramDeviceProvisionResponse);
    }
    if (paramInt == 3)
    {
      Logger.error("provisionNewDevice() exceeded allowable number of retry attempts.");
      throw new RequestThrottledException("provisionNewDevice() exceeded allowable number of retry attempts.");
    }
    Logger.warning("Device provision request failed due to invalid credentials. Trying again.");
    return false;
  }
  
  private DeviceCredentialGenerator getCredentialGenerator()
  {
    if (this._credentialGenerator == null) {
      this._credentialGenerator = new DeviceCredentialGenerator();
    }
    return this._credentialGenerator;
  }
  
  private StsRequestFactory getRequestFactory()
  {
    if (this._requestFactory == null) {
      this._requestFactory = new StsRequestFactory(this._applicationContext);
    }
    return this._requestFactory;
  }
  
  private DeviceIdentity provisionNewDevice()
    throws NetworkException, InvalidResponseException, StsException
  {
    this._storage.deleteDeviceIdentity();
    int i = 1;
    Object localObject = null;
    while (i <= 3)
    {
      DeviceCredentials localDeviceCredentials = getCredentialGenerator().generate();
      if (localObject == null) {
        localObject = getRequestFactory().createDeviceProvisionRequest(localDeviceCredentials);
      }
      for (;;)
      {
        DeviceProvisionResponse localDeviceProvisionResponse = (DeviceProvisionResponse)((DeviceProvisionRequest)localObject).send();
        if (!checkProvisionResponse(i, localDeviceProvisionResponse)) {
          break;
        }
        localObject = new DeviceIdentity(localDeviceCredentials, localDeviceProvisionResponse.getPuid(), null);
        this._storage.writeDeviceIdentity((DeviceIdentity)localObject);
        return (DeviceIdentity)localObject;
        ((DeviceProvisionRequest)localObject).setDeviceCredentials(localDeviceCredentials);
      }
      i += 1;
    }
    return null;
  }
  
  public DeviceIdentity getDeviceIdentity(boolean paramBoolean)
    throws NetworkException, InvalidResponseException, StsException
  {
    Object localObject = this._storage.readDeviceIdentity();
    if ((localObject != null) && (((DeviceIdentity)localObject).getDAToken() != null) && (!paramBoolean)) {
      return (DeviceIdentity)localObject;
    }
    if (localObject != null)
    {
      localDeviceAuthResponse = (DeviceAuthResponse)getRequestFactory().createDeviceAuthRequest((DeviceIdentity)localObject).send();
      if (localDeviceAuthResponse.succeeded())
      {
        ((DeviceIdentity)localObject).setDAToken(localDeviceAuthResponse.getDAToken());
        this._storage.writeDeviceIdentity((DeviceIdentity)localObject);
        return (DeviceIdentity)localObject;
      }
      localObject = localDeviceAuthResponse.getError();
      switch (localObject.getCode())
      {
      default: 
        throw new StsException("Failed to authenticate device", (StsError)localObject);
      }
    }
    localObject = provisionNewDevice();
    DeviceAuthResponse localDeviceAuthResponse = (DeviceAuthResponse)getRequestFactory().createDeviceAuthRequest((DeviceIdentity)localObject).send();
    if (localDeviceAuthResponse.succeeded())
    {
      ((DeviceIdentity)localObject).setDAToken(localDeviceAuthResponse.getDAToken());
      this._storage.writeDeviceIdentity((DeviceIdentity)localObject);
      return (DeviceIdentity)localObject;
    }
    throw new StsException("Failed to authenticate device", localDeviceAuthResponse.getError());
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\sts\DeviceIdentityManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */