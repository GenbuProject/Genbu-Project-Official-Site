package com.microsoft.onlineid.internal.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.microsoft.onlineid.SignInOptions;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.internal.ActivityResultSender;
import com.microsoft.onlineid.internal.ActivityResultSender.ResultType;
import com.microsoft.onlineid.internal.ApiRequest;
import com.microsoft.onlineid.internal.ApiRequestResultReceiver;
import com.microsoft.onlineid.internal.ApiResult;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.Intents.DataBuilder;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.sts.AuthenticatorAccountManager;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.ui.AddAccountActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AccountPickerActivity
  extends Activity
{
  public static final String ActionPickAccount = "com.microsoft.onlineid.internal.PICK_ACCOUNT";
  public static final int AddAccountRequest = 1;
  private static final float BackgroundDimValue = 0.5F;
  private AccountListAdapter _accountList;
  private AuthenticatorAccountManager _accountManager;
  private Set<String> _cidExclusionList;
  private com.microsoft.onlineid.internal.Resources _resources;
  private ActivityResultSender _resultSender;
  
  public static Intent getAccountPickerIntent(Context paramContext, ArrayList<String> paramArrayList, String paramString1, String paramString2, String paramString3, Bundle paramBundle)
  {
    return new Intent().setClass(paramContext, AccountPickerActivity.class).setAction("com.microsoft.onlineid.internal.PICK_ACCOUNT").putStringArrayListExtra(Extras.CidsToExclude.getKey(), paramArrayList).putExtra("com.microsoft.onlineid.preferred_membername_type", paramString1).putExtra("com.microsoft.onlineid.cobranding_id", paramString2).putExtra("com.microsoft.onlineid.client_package_name", paramString3).putExtra("com.microsoft.onlineid.client_state", paramBundle).setData(new Intents.DataBuilder().add(paramArrayList).add(paramString1).add(paramString2).add(paramString3).build());
  }
  
  private int getStatusBarHeight()
  {
    int j = getResources().getIdentifier("status_bar_height", "dimen", "android");
    int i = 0;
    if (j != 0) {
      i = getResources().getDimensionPixelSize(j);
    }
    return i;
  }
  
  private void launchAddAccountFlow()
  {
    ClientAnalytics.get().logEvent("SDK", "Initiate account add", "via account picker");
    Intent localIntent = AddAccountActivity.getSignInIntent(getApplicationContext(), new SignInOptions(getIntent().getExtras()), getIntent().getStringExtra("com.microsoft.onlineid.preferred_membername_type"), getIntent().getStringExtra("com.microsoft.onlineid.cobranding_id"), false, getIntent().getStringExtra("com.microsoft.onlineid.client_package_name"), getIntent().getBundleExtra("com.microsoft.onlineid.client_state"));
    startActivityForResult(new ApiRequest(getApplicationContext(), localIntent).setResultReceiver(new AddAccountFlowReceiver(new Handler())).asIntent(), 1);
  }
  
  private void onAccountPicked(AuthenticatorUserAccount paramAuthenticatorUserAccount)
  {
    this._resultSender.putLimitedUserAccount(paramAuthenticatorUserAccount).set();
    finish();
  }
  
  private void onException(Exception paramException)
  {
    this._resultSender.putException(paramException).set();
    finish();
  }
  
  private void setupWindow()
  {
    requestWindowFeature(8);
    Window localWindow = getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    localWindow.addFlags(2);
    int j = localDisplayMetrics.heightPixels - getStatusBarHeight();
    int i = this._resources.getDimensionPixelSize("accountPickerMargin");
    int m = localDisplayMetrics.widthPixels - i;
    int k = this._resources.getDimensionPixelSize("maxAccountPickerHeight");
    int n = this._resources.getDimensionPixelSize("maxAccountPickerWidth");
    if (j > k)
    {
      i = k;
      localLayoutParams.height = i;
      if (m <= n) {
        break label152;
      }
      i = n;
      label112:
      localLayoutParams.width = i;
      if (j <= k) {
        break label158;
      }
    }
    label152:
    label158:
    for (i = 17;; i = 80)
    {
      localLayoutParams.gravity = i;
      localLayoutParams.dimAmount = 0.5F;
      localWindow.setAttributes(localLayoutParams);
      return;
      i = j;
      break;
      i = m;
      break label112;
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    this._resources = new com.microsoft.onlineid.internal.Resources(getApplicationContext());
    setupWindow();
    AccountHeaderView.applyStyle(this, this._resources.getString("webflow_header"));
    super.onCreate(paramBundle);
    this._resultSender = new ActivityResultSender(this, ActivityResultSender.ResultType.Account);
    setContentView(this._resources.getLayout("account_picker"));
    Bundle localBundle = getIntent().getBundleExtra("com.microsoft.onlineid.client_state");
    Object localObject = null;
    String str = getIntent().getStringExtra("com.microsoft.onlineid.client_package_name");
    paramBundle = (Bundle)localObject;
    if (str != null)
    {
      paramBundle = (Bundle)localObject;
      if (str.equals("com.microsoft.msa.authenticator")) {
        paramBundle = localBundle.getString("com.microsoft.onlineid.account_picker_body");
      }
    }
    localObject = paramBundle;
    if (paramBundle == null) {
      localObject = this._resources.getString("account_picker_list_body");
    }
    paramBundle = BaseScreenFragment.buildWithBaseScreen(this._resources.getString("account_picker_list_header"), (String)localObject, BaseScreenFragment.class);
    getFragmentManager().beginTransaction().add(this._resources.getId("accountPickerBase"), paramBundle).commit();
    this._accountList = new AccountListAdapter(this);
    this._accountManager = new AuthenticatorAccountManager(getApplicationContext());
    paramBundle = getIntent().getStringArrayListExtra(Extras.CidsToExclude.getKey());
    this._cidExclusionList = new HashSet();
    if (paramBundle != null) {
      this._cidExclusionList.addAll(paramBundle);
    }
    paramBundle = (ListView)findViewById(this._resources.getId("listAccounts"));
    paramBundle.addFooterView(getLayoutInflater().inflate(this._resources.getLayout("add_account_tile"), paramBundle, false));
    paramBundle.setAdapter(this._accountList);
    paramBundle.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        ClientAnalytics.get().logEvent("SDK", "Pick an account", "via account picker");
        if (paramAnonymousInt == AccountPickerActivity.this._accountList.getCount())
        {
          AccountPickerActivity.this.launchAddAccountFlow();
          return;
        }
        paramAnonymousAdapterView = (AuthenticatorUserAccount)AccountPickerActivity.this._accountList.getItem(paramAnonymousInt);
        AccountPickerActivity.this.onAccountPicked(paramAnonymousAdapterView);
        AccountPickerActivity.this.finish();
      }
    });
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(this._resources.getMenu("action_dismiss_account_picker"), paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == this._resources.getId("action_dismiss"))
    {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onResume()
  {
    super.onResume();
    Set localSet = this._accountManager.getFilteredAccounts(this._cidExclusionList);
    Logger.info(String.format(Locale.US, "%d active account(s)", new Object[] { Integer.valueOf(localSet.size()) }));
    if (localSet.isEmpty())
    {
      launchAddAccountFlow();
      return;
    }
    this._accountList.setContent(localSet);
  }
  
  protected void onStart()
  {
    super.onStart();
    ClientAnalytics.get().logScreenView("Account picker");
  }
  
  private class AddAccountFlowReceiver
    extends ApiRequestResultReceiver
  {
    public AddAccountFlowReceiver(Handler paramHandler)
    {
      super();
    }
    
    protected void onFailure(Exception paramException)
    {
      if (paramException != null) {}
      for (boolean bool = true;; bool = false)
      {
        Assertion.check(bool, "Request failed without Exception.");
        AccountPickerActivity.this.onException(paramException);
        return;
      }
    }
    
    protected void onSuccess(ApiResult paramApiResult)
    {
      ClientAnalytics.get().logEvent("SDK", "Add account", "via account picker");
      paramApiResult = AccountPickerActivity.this._accountManager.getAccountByPuid(paramApiResult.getAccountPuid());
      if (paramApiResult == null)
      {
        AccountPickerActivity.this.onException(new InternalException("Picker could not find newly added account."));
        return;
      }
      AccountPickerActivity.this.onAccountPicked(paramApiResult);
    }
    
    protected void onUINeeded(PendingIntent paramPendingIntent)
    {
      try
      {
        AccountPickerActivity.this.startIntentSenderForResult(paramPendingIntent.getIntentSender(), 0, null, 0, 0, 0);
        return;
      }
      catch (IntentSender.SendIntentException paramPendingIntent)
      {
        AccountPickerActivity.this.onException(paramPendingIntent);
      }
    }
    
    protected void onUserCancel()
    {
      if (AccountPickerActivity.this._accountManager.getFilteredAccounts(AccountPickerActivity.this._cidExclusionList).isEmpty()) {
        AccountPickerActivity.this.finish();
      }
    }
  }
  
  private static enum Extras
  {
    CidsToExclude;
    
    private Extras() {}
    
    public String getKey()
    {
      return "com.microsoft.msa.authenticator." + name();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\ui\AccountPickerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */