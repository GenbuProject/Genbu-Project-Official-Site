package com.microsoft.xbox.service.network.managers.friendfinder;

import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.ShortCircuitProfileResponse;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.UploadPhoneContactsRequest;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.UploadPhoneContactsResponse;
import com.microsoft.xbox.service.network.managers.ServiceManagerFactory;
import com.microsoft.xbox.service.network.managers.xblshared.ISLSServiceManager;
import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.NetworkAsyncTask;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import java.util.ArrayList;

public class UploadContactsAsyncTask
  extends NetworkAsyncTask<AsyncActionStatus>
{
  private static final int MAX_UPLOAD_NUM_PER_REQUEST = 100;
  private UploadContactsCompleted callback;
  private String phoneNumber;
  
  public UploadContactsAsyncTask(UploadContactsCompleted paramUploadContactsCompleted)
  {
    this.callback = paramUploadContactsCompleted;
    if (!JavaUtil.isNullOrEmpty(PhoneContactInfo.getInstance().getProfileNumber())) {
      this.phoneNumber = PhoneContactInfo.getInstance().getProfileNumber();
    }
    do
    {
      return;
      if (!JavaUtil.isNullOrEmpty(PhoneContactInfo.getInstance().getUserEnteredNumber()))
      {
        this.phoneNumber = PhoneContactInfo.getInstance().getUserEnteredNumber();
        return;
      }
    } while (JavaUtil.isNullOrEmpty(PhoneContactInfo.getInstance().getPhoneNumberFromSim()));
    this.phoneNumber = PhoneContactInfo.getInstance().getPhoneNumberFromSim();
  }
  
  private boolean batchUploadContacts(ArrayList<PhoneContactInfo.Contact> paramArrayList)
    throws XLEException
  {
    XLEAssert.assertNotNull(paramArrayList);
    boolean bool;
    int k;
    if (paramArrayList.size() > 100)
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      k = 1;
    }
    int i;
    for (int j = 0;; j = i)
    {
      if (k == 0) {
        break label90;
      }
      int m = j + 100;
      i = m;
      if (m >= paramArrayList.size())
      {
        i = paramArrayList.size();
        k = 0;
      }
      if (!uploadContacts(new ArrayList(paramArrayList.subList(j, i))))
      {
        return false;
        bool = false;
        break;
      }
    }
    label90:
    return true;
  }
  
  private boolean uploadContacts(ArrayList<PhoneContactInfo.Contact> paramArrayList)
    throws XLEException
  {
    XLEAssert.assertNotNull(paramArrayList);
    if ((paramArrayList.size() > 0) && (paramArrayList.size() <= 100)) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramArrayList = new ShortCircuitProfileMessage.UploadPhoneContactsRequest(paramArrayList, this.phoneNumber);
      paramArrayList = ServiceManagerFactory.getInstance().getSLSServiceManager().updatePhoneContacts(paramArrayList);
      if ((paramArrayList == null) || (paramArrayList.isErrorResponse)) {
        break;
      }
      paramArrayList = paramArrayList.getXboxPhoneContacts();
      PhoneContactInfo.getInstance().updateXboxContacts(paramArrayList);
      return true;
    }
    return false;
  }
  
  private boolean uploadContactsSucceeded()
    throws XLEException
  {
    ArrayList localArrayList = PhoneContactInfo.getInstance().getContacts();
    if (localArrayList != null)
    {
      if (localArrayList.size() == 0) {
        return true;
      }
      if (localArrayList.size() > 100) {
        return batchUploadContacts(localArrayList);
      }
      return uploadContacts(localArrayList);
    }
    return false;
  }
  
  protected boolean checkShouldExecute()
  {
    return true;
  }
  
  protected AsyncActionStatus loadDataInBackground()
  {
    try
    {
      if (JavaUtil.isNullOrEmpty(this.phoneNumber))
      {
        localObject = ServiceManagerFactory.getInstance().getSLSServiceManager().getMyShortCircuitProfile();
        if (localObject == null) {
          return AsyncActionStatus.FAIL;
        }
        localObject = ((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject).getXboxNumber();
        if (!JavaUtil.isNullOrEmpty((String)localObject))
        {
          PhoneContactInfo.getInstance().setProfileNumber((String)localObject);
          this.phoneNumber = ((String)localObject);
        }
      }
      else
      {
        if (!uploadContactsSucceeded()) {
          break label72;
        }
        return AsyncActionStatus.SUCCESS;
      }
      Object localObject = AsyncActionStatus.FAIL;
      return (AsyncActionStatus)localObject;
    }
    catch (XLEException localXLEException) {}
    label72:
    return AsyncActionStatus.FAIL;
  }
  
  protected AsyncActionStatus onError()
  {
    return AsyncActionStatus.FAIL;
  }
  
  protected void onNoAction() {}
  
  protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
  {
    if (this.callback != null) {
      this.callback.onResult(paramAsyncActionStatus);
    }
  }
  
  protected void onPreExecute() {}
  
  public static abstract interface UploadContactsCompleted
  {
    public abstract void onResult(AsyncActionStatus paramAsyncActionStatus);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\network\managers\friendfinder\UploadContactsAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */