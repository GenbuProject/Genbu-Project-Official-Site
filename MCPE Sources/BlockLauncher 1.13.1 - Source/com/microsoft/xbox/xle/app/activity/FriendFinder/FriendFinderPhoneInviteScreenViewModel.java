package com.microsoft.xbox.xle.app.activity.FriendFinder;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.service.network.managers.friendfinder.PhoneContactInfo;
import com.microsoft.xbox.service.network.managers.friendfinder.PhoneContactInfo.Contact;
import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.NetworkAsyncTask;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.xle.app.adapter.FriendFinderPhoneInviteScreenAdapater;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xbox.xle.viewmodel.ViewModelBase;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FriendFinderPhoneInviteScreenViewModel
  extends ViewModelBase
{
  private Comparator<PhoneContactInfo.Contact> contactComparator = new Comparator()
  {
    public int compare(PhoneContactInfo.Contact paramAnonymousContact1, PhoneContactInfo.Contact paramAnonymousContact2)
    {
      return paramAnonymousContact1.displayName.compareTo(paramAnonymousContact2.displayName);
    }
  };
  private ArrayList<PhoneContactInfo.Contact> contactsList = new ArrayList();
  private boolean isUploadingContacts;
  private UploadContactsAsyncTask uploadContactsAsyncTask;
  
  public FriendFinderPhoneInviteScreenViewModel(ScreenLayout paramScreenLayout)
  {
    super(paramScreenLayout);
    XLEAssert.fail("This isn't supported yet.");
    this.adapter = new FriendFinderPhoneInviteScreenAdapater(this);
  }
  
  private void cancelActiveTasks()
  {
    if (this.uploadContactsAsyncTask != null)
    {
      this.uploadContactsAsyncTask.cancel();
      this.uploadContactsAsyncTask = null;
    }
  }
  
  private void onUploadContactsTaskCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    this.isUploadingContacts = false;
    this.contactsList = PhoneContactInfo.getInstance().getContacts();
    Collections.sort(this.contactsList, this.contactComparator);
    updateAdapter();
  }
  
  public void addContacts(ArrayList<Integer> paramArrayList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Integer localInteger = (Integer)paramArrayList.next();
      if (localStringBuffer.length() > 0) {
        localStringBuffer.append(',');
      }
      localStringBuffer.append((String)((PhoneContactInfo.Contact)this.contactsList.get(localInteger.intValue())).phoneNumbers.get(0));
    }
    paramArrayList = new Intent("android.intent.action.SENDTO");
    paramArrayList.setData(Uri.parse("smsto:" + Uri.encode(localStringBuffer.toString())));
    paramArrayList.putExtra("sms_body", XboxTcuiSdk.getResources().getString(R.string.FriendFinder_PhoneInviteFriends_Message));
    paramArrayList.putExtra("address", localStringBuffer.toString());
    if (!XboxTcuiSdk.getActivity().getPackageManager().queryIntentActivities(paramArrayList, 0).isEmpty()) {
      XboxTcuiSdk.getActivity().startActivity(paramArrayList);
    }
    paramArrayList = new ActivityParameters();
    paramArrayList.putFriendFinderDone(true);
    try
    {
      NavigationManager.getInstance().PushScreen(FriendFinderHomeScreen.class, paramArrayList);
      return;
    }
    catch (XLEException paramArrayList) {}
  }
  
  public ArrayList<PhoneContactInfo.Contact> getContacts()
  {
    return this.contactsList;
  }
  
  public boolean isBusy()
  {
    return this.isUploadingContacts;
  }
  
  public void load(boolean paramBoolean)
  {
    cancelActiveTasks();
    if (PhoneContactInfo.getInstance().isXboxContactsUpdated())
    {
      this.contactsList = PhoneContactInfo.getInstance().getContacts();
      Collections.sort(this.contactsList, this.contactComparator);
      return;
    }
    this.uploadContactsAsyncTask = new UploadContactsAsyncTask(null);
    this.uploadContactsAsyncTask.load(true);
  }
  
  public boolean onBackButtonPressed()
  {
    UTCFriendFinder.trackBackButtonPressed(getScreen().getName(), FriendFinderType.PHONE);
    return super.onBackButtonPressed();
  }
  
  public void onRehydrate()
  {
    this.adapter = new FriendFinderPhoneInviteScreenAdapater(this);
  }
  
  protected void onStartOverride() {}
  
  protected void onStopOverride()
  {
    cancelActiveTasks();
  }
  
  private class UploadContactsAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private UploadContactsAsyncTask() {}
    
    protected boolean checkShouldExecute()
    {
      return false;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      return AsyncActionStatus.SUCCESS;
    }
    
    protected AsyncActionStatus onError()
    {
      return null;
    }
    
    protected void onNoAction()
    {
      FriendFinderPhoneInviteScreenViewModel.this.onUploadContactsTaskCompleted(AsyncActionStatus.NO_CHANGE);
    }
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      FriendFinderPhoneInviteScreenViewModel.this.onUploadContactsTaskCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      FriendFinderPhoneInviteScreenViewModel.access$202(FriendFinderPhoneInviteScreenViewModel.this, true);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderPhoneInviteScreenViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */