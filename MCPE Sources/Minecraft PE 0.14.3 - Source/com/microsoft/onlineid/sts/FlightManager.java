package com.microsoft.onlineid.sts;

import android.content.Context;
import android.text.TextUtils;
import com.microsoft.onlineid.internal.configuration.Flight;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class FlightManager
{
  private static final Comparator<AuthenticatorUserAccount> PuidComparator = new Comparator()
  {
    public int compare(AuthenticatorUserAccount paramAnonymousAuthenticatorUserAccount1, AuthenticatorUserAccount paramAnonymousAuthenticatorUserAccount2)
    {
      return paramAnonymousAuthenticatorUserAccount1.getPuid().compareTo(paramAnonymousAuthenticatorUserAccount2.getPuid());
    }
  };
  private static int ResultTooManyFlights = -1;
  private AuthenticatorAccountManager _accountManager;
  private final Context _applicationContext;
  private final TypedStorage _typedStorage;
  
  protected FlightManager()
  {
    this._applicationContext = null;
    this._typedStorage = null;
  }
  
  public FlightManager(Context paramContext)
  {
    this._applicationContext = paramContext;
    this._accountManager = new AuthenticatorAccountManager(this._applicationContext);
    this._typedStorage = new TypedStorage(this._applicationContext);
  }
  
  private AuthenticatorUserAccount getPrimeAccount()
  {
    if (this._typedStorage.hasAccounts()) {
      return (AuthenticatorUserAccount)Collections.min(this._accountManager.getAccounts(), PuidComparator);
    }
    return null;
  }
  
  private void unenrollAllFlights() {}
  
  public boolean canShowNgc()
  {
    return (isInNgcFlight()) || (new AuthenticatorAccountManager(this._applicationContext).hasNgcSessionApprovalAccounts());
  }
  
  public void enrollInFlights()
  {
    Set localSet = getFlights();
    Logger.info("Enrolling in Flights" + TextUtils.join(", ", localSet));
    unenrollAllFlights();
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      int i = ((Integer)localIterator.next()).intValue();
      if (i != Flight.QRCode.getFlightID()) {
        if (i == ResultTooManyFlights) {
          Logger.error("This client is in too many flights!  They are currently enrolled in " + TextUtils.join(", ", localSet));
        } else {
          Logger.warning("Unrecognized flight number " + i + " returned");
        }
      }
    }
  }
  
  public Set<Integer> getFlights()
  {
    if (isDeviceFlightOverrideEnabled())
    {
      localObject = this._typedStorage.readDeviceBasedFlights();
      if (localObject != null) {
        return (Set<Integer>)localObject;
      }
      return Collections.emptySet();
    }
    Object localObject = getPrimeAccount();
    if (localObject != null) {}
    for (localObject = ((AuthenticatorUserAccount)localObject).getFlights();; localObject = Collections.emptySet()) {
      return (Set<Integer>)localObject;
    }
  }
  
  public boolean isDeviceFlightOverrideEnabled()
  {
    return this._typedStorage.readDeviceFlightOverrideEnabled();
  }
  
  public boolean isInNgcFlight()
  {
    return getFlights().contains(Integer.valueOf(Flight.QRCode.getFlightID()));
  }
  
  public void setDeviceFlightOverrideEnabled(boolean paramBoolean)
  {
    this._typedStorage.writeDeviceFlightOverrideEnabled(paramBoolean);
  }
  
  public void setFlights(Set<Integer> paramSet)
  {
    AuthenticatorUserAccount localAuthenticatorUserAccount = getPrimeAccount();
    if (isDeviceFlightOverrideEnabled()) {
      this._typedStorage.writeDeviceBasedFlights(paramSet);
    }
    while (localAuthenticatorUserAccount == null) {
      return;
    }
    localAuthenticatorUserAccount.setFlights(paramSet);
    this._typedStorage.writeAccount(localAuthenticatorUserAccount);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\FlightManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */