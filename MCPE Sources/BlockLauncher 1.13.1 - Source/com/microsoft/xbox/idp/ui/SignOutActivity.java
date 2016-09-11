package com.microsoft.xbox.idp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.microsoft.xbox.idp.R.layout;
import com.microsoft.xbox.idp.interop.Interop.AuthFlowScreenStatus;
import com.microsoft.xbox.idp.telemetry.helpers.UTCPageView;
import com.microsoft.xbox.idp.telemetry.helpers.UTCUser;

public class SignOutActivity
  extends AuthActivity
  implements HeaderFragment.Callbacks, SignOutFragment.Callbacks
{
  private static final String KEY_STATE = "KEY_STATE";
  private static final String TAG = SignOutActivity.class.getSimpleName();
  private State state;
  private Interop.AuthFlowScreenStatus status = Interop.AuthFlowScreenStatus.NO_ERROR;
  
  private void finishWithResult()
  {
    setResult(toActivityResult(this.status));
    finishCompat();
  }
  
  private void showBodyFragment(Task paramTask, Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    this.state.currentTask = paramTask;
    showBodyFragment(paramFragment, paramBundle, paramBoolean);
  }
  
  public void onBackPressed()
  {
    Log.d(TAG, "onBackPressed");
    this.status = Interop.AuthFlowScreenStatus.ERROR_USER_CANCEL;
    finishWithResult();
  }
  
  public void onClickCloseHeader()
  {
    Log.d(TAG, "onClickCloseHeader");
    this.status = Interop.AuthFlowScreenStatus.ERROR_USER_CANCEL;
    finishWithResult();
  }
  
  public void onComplete(SignOutFragment.Status paramStatus)
  {
    Log.d(TAG, "onComplete: StartSignInFragment.Status." + paramStatus);
    switch (paramStatus)
    {
    default: 
      return;
    case ???: 
      this.status = Interop.AuthFlowScreenStatus.NO_ERROR;
      UTCUser.trackSignout(getTitle());
      finishWithResult();
      return;
    case ???: 
      this.status = Interop.AuthFlowScreenStatus.ERROR_USER_CANCEL;
      finishWithResult();
      return;
    }
    this.status = Interop.AuthFlowScreenStatus.PROVIDER_ERROR;
    finishWithResult();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.xbid_activity_auth_flow);
    if (paramBundle == null)
    {
      this.state = new State();
      showBodyFragment(Task.SIGN_OUT, new SignOutFragment(), new Bundle(), true);
      return;
    }
    this.state = ((State)paramBundle.getParcelable("KEY_STATE"));
  }
  
  protected void onDestroy()
  {
    UTCPageView.removePage();
    super.onDestroy();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putParcelable("KEY_STATE", this.state);
  }
  
  private static class State
    implements Parcelable
  {
    public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator()
    {
      public SignOutActivity.State createFromParcel(Parcel paramAnonymousParcel)
      {
        return new SignOutActivity.State(paramAnonymousParcel);
      }
      
      public SignOutActivity.State[] newArray(int paramAnonymousInt)
      {
        return new SignOutActivity.State[paramAnonymousInt];
      }
    };
    public SignOutActivity.Task currentTask;
    
    public State() {}
    
    protected State(Parcel paramParcel)
    {
      int i = paramParcel.readInt();
      if (i != -1) {
        this.currentTask = SignOutActivity.Task.values()[i];
      }
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      if (this.currentTask == null) {}
      for (paramInt = -1;; paramInt = this.currentTask.ordinal())
      {
        paramParcel.writeInt(paramInt);
        return;
      }
    }
  }
  
  private static enum Task
  {
    SIGN_OUT;
    
    private Task() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\ui\SignOutActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */