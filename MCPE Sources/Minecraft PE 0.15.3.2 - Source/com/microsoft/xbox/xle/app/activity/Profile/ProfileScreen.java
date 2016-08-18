package com.microsoft.xbox.xle.app.activity.Profile;

import android.content.Context;
import android.util.AttributeSet;
import com.microsoft.xbox.xle.app.activity.ActivityBase;
import com.microsoft.xbox.xle.telemetry.helpers.UTCPeopleHub;
import com.microsoft.xboxtcui.R.layout;

public class ProfileScreen
  extends ActivityBase
{
  public ProfileScreen() {}
  
  public ProfileScreen(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected String getActivityName()
  {
    return "PeopleHub Info";
  }
  
  public void onCreate()
  {
    super.onCreate();
    onCreateContentView();
    ProfileScreenViewModel localProfileScreenViewModel = new ProfileScreenViewModel(this);
    this.viewModel = localProfileScreenViewModel;
    UTCPeopleHub.trackPeopleHubView(getActivityName(), localProfileScreenViewModel.getXuid(), localProfileScreenViewModel.isMeProfile());
  }
  
  public void onCreateContentView()
  {
    setContentView(R.layout.profile_screen);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\activity\Profile\ProfileScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */