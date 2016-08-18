package com.microsoft.onlineid.internal.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.microsoft.onlineid.internal.Resources;
import com.microsoft.onlineid.internal.profile.DownloadProfileImageTask;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.ui.AbstractListAdapter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountListAdapter
  extends AbstractListAdapter<AuthenticatorUserAccount>
{
  protected final Context _applicationContext;
  private final int _imageUserTileResId;
  protected final Resources _resources;
  private final int _textEmailResId;
  private final int _textFirstLastResId;
  protected final Set<TextView> _visibleAccounts = new HashSet();
  
  public AccountListAdapter()
  {
    this._applicationContext = null;
    this._resources = null;
    this._imageUserTileResId = 0;
    this._textFirstLastResId = 0;
    this._textEmailResId = 0;
  }
  
  public AccountListAdapter(Activity paramActivity)
  {
    this._applicationContext = paramActivity.getApplicationContext();
    this._resources = new Resources(this._applicationContext);
    this._imageUserTileResId = this._resources.getId("imageUserTile");
    this._textFirstLastResId = this._resources.getId("textFirstLast");
    this._textEmailResId = this._resources.getId("textEmail");
  }
  
  public long getItemId(int paramInt)
  {
    return ((AuthenticatorUserAccount)this._items.get(paramInt)).hashCode();
  }
  
  public int getItemViewType(int paramInt)
  {
    return 0;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    AuthenticatorUserAccount localAuthenticatorUserAccount = (AuthenticatorUserAccount)this._items.get(paramInt);
    View localView = paramView;
    if (paramView == null)
    {
      paramInt = this._resources.getLayout("account_picker_tile");
      localView = ((LayoutInflater)paramViewGroup.getContext().getSystemService("layout_inflater")).inflate(paramInt, null);
    }
    setupBasicAccountViews(localView, localAuthenticatorUserAccount);
    return localView;
  }
  
  public int getViewTypeCount()
  {
    return 1;
  }
  
  protected void onChanged()
  {
    super.onChanged();
    this._visibleAccounts.clear();
  }
  
  protected void setupBasicAccountViews(View paramView, AuthenticatorUserAccount paramAuthenticatorUserAccount)
  {
    ImageView localImageView = (ImageView)paramView.findViewById(this._imageUserTileResId);
    TextView localTextView = (TextView)paramView.findViewById(this._textFirstLastResId);
    paramView = (TextView)paramView.findViewById(this._textEmailResId);
    paramAuthenticatorUserAccount.getDisplayName();
    paramView.setText(paramAuthenticatorUserAccount.getUsername());
    if (localTextView != null) {
      localTextView.setText(paramAuthenticatorUserAccount.getDisplayName());
    }
    new DownloadProfileImageTask(this._applicationContext, paramAuthenticatorUserAccount, localImageView).execute(new Void[0]);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\ui\AccountListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */