package com.microsoft.xboxtcui;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareLinkContent.Builder;
import com.facebook.share.widget.ShareDialog;
import com.microsoft.xbox.service.model.ProfileModel;
import com.microsoft.xbox.service.network.managers.friendfinder.FacebookManager;
import com.microsoft.xbox.xle.app.ImageUtil;
import java.net.URI;

public class FbShareShimActivity
  extends FbShimActivity
{
  private final String SHARE_TO_FACEBOOK_LINK = "http://go.microsoft.com/fwlink/?LinkId=698852";
  private ShareDialog shareDialog;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle == null)
    {
      this.shareDialog = new ShareDialog(this);
      FacebookManager.getInstance().registerShareCallback(this.shareDialog);
      paramBundle = ImageUtil.getMedium(ProfileModel.getMeProfileModel().getGamerPicImageUrl());
      if (ShareDialog.canShow(ShareLinkContent.class))
      {
        paramBundle = ((ShareLinkContent.Builder)new ShareLinkContent.Builder().setImageUrl(Uri.parse(paramBundle.toString())).setContentTitle(XboxTcuiSdk.getResources().getString(R.string.FriendFinder_Facebook_Share_Title)).setContentDescription(XboxTcuiSdk.getResources().getString(R.string.FriendFinder_Facebook_Share_Description)).setContentUrl(Uri.parse("http://go.microsoft.com/fwlink/?LinkId=698852"))).build();
        this.shareDialog.show(paramBundle);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xboxtcui\FbShareShimActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */