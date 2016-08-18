package com.microsoft.xboxtcui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.microsoft.xbox.service.model.ProfileModel;
import com.microsoft.xbox.service.network.managers.friendfinder.FacebookManager;
import com.microsoft.xbox.toolkit.Ready;
import com.microsoft.xbox.toolkit.XLEAssert;

public class FbShimActivity
  extends Activity
{
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    FacebookManager.getInstance().onShimActivityResult(paramInt1, paramInt2, paramIntent);
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    XLEAssert.assertTrue(FacebookManager.getFacebookManagerReady().getIsReady());
    XLEAssert.assertTrue(FacebookSdk.isInitialized());
    XLEAssert.assertNotNull(ProfileModel.getMeProfileModel());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xboxtcui\FbShimActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */