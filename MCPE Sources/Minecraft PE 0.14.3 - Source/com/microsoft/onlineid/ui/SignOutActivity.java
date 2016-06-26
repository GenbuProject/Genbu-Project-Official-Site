package com.microsoft.onlineid.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import com.microsoft.onlineid.internal.ActivityResultSender;
import com.microsoft.onlineid.internal.ActivityResultSender.ResultType;
import com.microsoft.onlineid.internal.ApiRequest;
import com.microsoft.onlineid.internal.ApiRequest.Extras;
import com.microsoft.onlineid.internal.ApiRequestResultReceiver;
import com.microsoft.onlineid.internal.ApiResult;
import com.microsoft.onlineid.internal.Intents.DataBuilder;
import com.microsoft.onlineid.internal.MsaService;
import com.microsoft.onlineid.internal.Resources;

public class SignOutActivity
  extends Activity
{
  private boolean _isSignedOutOfThisAppOnly;
  private ActivityResultSender _resultSender;
  private String _userCid;
  
  private AlertDialog buildDialog()
  {
    final Object localObject1 = getApplicationContext();
    final Object localObject2 = new ApiRequest((Context)localObject1, getIntent());
    Resources localResources = new Resources((Context)localObject1);
    RelativeLayout localRelativeLayout = (RelativeLayout)getLayoutInflater().inflate(localResources.getLayout("sign_out_custom_content_view"), null);
    final Object localObject3 = (CheckBox)localRelativeLayout.findViewById(localResources.getId("signOutCheckBox"));
    ((CheckBox)localObject3).setText(String.format(localResources.getString("sign_out_dialog_checkbox"), new Object[] { ((ApiRequest)localObject2).getAccountName() }));
    localObject1 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        String str2 = localObject2.getAccountPuid();
        String str1;
        SignOutActivity localSignOutActivity;
        if (localObject3.isChecked())
        {
          str1 = "com.microsoft.onlineid.internal.SIGN_OUT_ALL_APPS";
          localSignOutActivity = SignOutActivity.this;
          if (!localObject3.isChecked()) {
            break label113;
          }
        }
        label113:
        for (boolean bool = false;; bool = true)
        {
          SignOutActivity.access$002(localSignOutActivity, bool);
          new ApiRequest(localObject1, new Intent(localObject1, MsaService.class).setAction(str1)).setAccountPuid(str2).setResultReceiver(new SignOutActivity.SignOutResultReceiver(SignOutActivity.this)).executeAsync();
          paramAnonymousDialogInterface.dismiss();
          return;
          str1 = "com.microsoft.onlineid.internal.SIGN_OUT";
          break;
        }
      }
    };
    localObject2 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    };
    localObject3 = new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        SignOutActivity.this.finish();
      }
    };
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setView(localRelativeLayout).setTitle(localResources.getString("sign_out_dialog_title")).setPositiveButton(localResources.getString("sign_out_dialog_button_sign_out"), (DialogInterface.OnClickListener)localObject1).setNegativeButton(localResources.getString("sign_out_dialog_button_cancel"), (DialogInterface.OnClickListener)localObject2).setOnCancelListener((DialogInterface.OnCancelListener)localObject3);
    return localBuilder.create();
  }
  
  public static Intent getSignOutIntent(Context paramContext, String paramString1, String paramString2, String paramString3, Bundle paramBundle)
  {
    return new Intent(paramContext, SignOutActivity.class).putExtra(ApiRequest.Extras.AccountPuid.getKey(), paramString1).putExtra(ApiRequest.Extras.AccountName.getKey(), paramString3).putExtra("com.microsoft.onlineid.user_cid", paramString2).putExtra("com.microsoft.onlineid.client_state", paramBundle).setData(new Intents.DataBuilder().add(paramString1).add(paramString3).build());
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    this._resultSender = new ActivityResultSender(this, ActivityResultSender.ResultType.Account);
    this._userCid = getIntent().getStringExtra("com.microsoft.onlineid.user_cid");
    buildDialog().show();
  }
  
  private class SignOutResultReceiver
    extends ApiRequestResultReceiver
  {
    public SignOutResultReceiver()
    {
      super();
    }
    
    protected void onFailure(Exception paramException)
    {
      SignOutActivity.this._resultSender.putException(paramException).set();
      SignOutActivity.this.finish();
    }
    
    protected void onSuccess(ApiResult paramApiResult)
    {
      SignOutActivity.this._resultSender.putSignedOutCid(SignOutActivity.this._userCid, SignOutActivity.this._isSignedOutOfThisAppOnly).set();
      SignOutActivity.this.finish();
    }
    
    protected void onUINeeded(PendingIntent paramPendingIntent)
    {
      onFailure(new UnsupportedOperationException("onUINeeded not expected for sign out request."));
    }
    
    protected void onUserCancel()
    {
      SignOutActivity.this.finish();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\ui\SignOutActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */