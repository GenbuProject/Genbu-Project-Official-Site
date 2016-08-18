package com.microsoft.xbox.idp.ui;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.GsonBuilder;
import com.microsoft.xbox.idp.R.id;
import com.microsoft.xbox.idp.R.layout;
import com.microsoft.xbox.idp.R.string;
import com.microsoft.xbox.idp.compat.BaseFragment;
import com.microsoft.xbox.idp.model.UserAccount;
import com.microsoft.xbox.idp.services.Endpoints;
import com.microsoft.xbox.idp.services.EndpointsFactory;
import com.microsoft.xbox.idp.toolkit.BitmapLoader;
import com.microsoft.xbox.idp.toolkit.BitmapLoader.Result;
import com.microsoft.xbox.idp.toolkit.ObjectLoader;
import com.microsoft.xbox.idp.toolkit.ObjectLoader.Result;
import com.microsoft.xbox.idp.util.CacheUtil;
import com.microsoft.xbox.idp.util.FragmentLoaderKey;
import com.microsoft.xbox.idp.util.HttpCall;
import com.microsoft.xbox.idp.util.HttpUtil;

public class HeaderFragment
  extends BaseFragment
  implements View.OnClickListener
{
  private static final int LOADER_GET_PROFILE = 1;
  private static final int LOADER_USER_IMAGE_URL = 2;
  private static final Callbacks NO_OP_CALLBACKS;
  private static final String TAG;
  private Callbacks callbacks = NO_OP_CALLBACKS;
  private final LoaderManager.LoaderCallbacks<BitmapLoader.Result> imageCallbacks = new LoaderManager.LoaderCallbacks()
  {
    public Loader<BitmapLoader.Result> onCreateLoader(int paramAnonymousInt, Bundle paramAnonymousBundle)
    {
      Log.d(HeaderFragment.TAG, "Creating LOADER_USER_IMAGE_URL");
      paramAnonymousBundle = Uri.parse(HeaderFragment.this.userAccount.imageUrl);
      Log.d(HeaderFragment.TAG, "uri: " + paramAnonymousBundle);
      return new BitmapLoader(HeaderFragment.this.getActivity(), CacheUtil.getBitmapCache(), HeaderFragment.this.userAccount.imageUrl, new HttpCall("GET", HttpUtil.getEndpoint(paramAnonymousBundle), HttpUtil.getPathAndQuery(paramAnonymousBundle)));
    }
    
    public void onLoadFinished(Loader<BitmapLoader.Result> paramAnonymousLoader, BitmapLoader.Result paramAnonymousResult)
    {
      Log.d(HeaderFragment.TAG, "LOADER_USER_IMAGE_URL finished");
      if (paramAnonymousResult.hasData())
      {
        HeaderFragment.this.userImageView.setVisibility(0);
        HeaderFragment.this.userImageView.setImageBitmap((Bitmap)paramAnonymousResult.getData());
        return;
      }
      HeaderFragment.this.userImageView.setVisibility(8);
      Log.w(HeaderFragment.TAG, "LOADER_USER_IMAGE_URL: " + paramAnonymousResult.getError());
    }
    
    public void onLoaderReset(Loader<BitmapLoader.Result> paramAnonymousLoader)
    {
      HeaderFragment.this.userImageView.setImageBitmap(null);
    }
  };
  private UserAccount userAccount;
  LoaderManager.LoaderCallbacks<ObjectLoader.Result<UserAccount>> userAccountCallbacks = new LoaderManager.LoaderCallbacks()
  {
    public Loader<ObjectLoader.Result<UserAccount>> onCreateLoader(int paramAnonymousInt, Bundle paramAnonymousBundle)
    {
      Log.d(HeaderFragment.TAG, "Creating LOADER_GET_PROFILE");
      paramAnonymousBundle = HttpUtil.appendCommonParameters(new HttpCall("GET", EndpointsFactory.get().accounts(), "/users/current/profile"), "4");
      return new ObjectLoader(HeaderFragment.this.getActivity(), CacheUtil.getObjectLoaderCache(), new FragmentLoaderKey(HeaderFragment.class, 1), UserAccount.class, UserAccount.registerAdapters(new GsonBuilder()).create(), paramAnonymousBundle);
    }
    
    public void onLoadFinished(Loader<ObjectLoader.Result<UserAccount>> paramAnonymousLoader, ObjectLoader.Result<UserAccount> paramAnonymousResult)
    {
      Log.d(HeaderFragment.TAG, "LOADER_GET_PROFILE finished");
      if (paramAnonymousResult.hasData())
      {
        HeaderFragment.access$102(HeaderFragment.this, (UserAccount)paramAnonymousResult.getData());
        HeaderFragment.this.userEmail.setText(HeaderFragment.this.userAccount.email);
        if ((!TextUtils.isEmpty(HeaderFragment.this.userAccount.firstName)) || (!TextUtils.isEmpty(HeaderFragment.this.userAccount.lastName)))
        {
          HeaderFragment.this.userName.setVisibility(0);
          HeaderFragment.this.userName.setText(HeaderFragment.this.getString(R.string.xbid_first_and_last_name_android, new Object[] { HeaderFragment.this.userAccount.firstName, HeaderFragment.this.userAccount.lastName }));
        }
        for (;;)
        {
          HeaderFragment.this.getLoaderManager().initLoader(2, null, HeaderFragment.this.imageCallbacks);
          return;
          HeaderFragment.this.userName.setVisibility(8);
        }
      }
      Log.e(HeaderFragment.TAG, "Error getting UserAccount");
    }
    
    public void onLoaderReset(Loader<ObjectLoader.Result<UserAccount>> paramAnonymousLoader) {}
  };
  private TextView userEmail;
  private ImageView userImageView;
  private TextView userName;
  
  static
  {
    if (!HeaderFragment.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      TAG = HeaderFragment.class.getSimpleName();
      NO_OP_CALLBACKS = new Callbacks()
      {
        public void onClickCloseHeader() {}
      };
      return;
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    assert ((paramActivity instanceof Callbacks));
    this.callbacks = ((Callbacks)paramActivity);
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == R.id.xbid_close) {
      this.callbacks.onClickCloseHeader();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(R.layout.xbid_fragment_header, paramViewGroup, false);
  }
  
  public void onDetach()
  {
    super.onDetach();
    this.callbacks = NO_OP_CALLBACKS;
  }
  
  public void onResume()
  {
    super.onResume();
    Bundle localBundle = getArguments();
    if (localBundle != null)
    {
      getLoaderManager().initLoader(1, localBundle, this.userAccountCallbacks);
      return;
    }
    Log.e(TAG, "No arguments provided");
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    paramView.findViewById(R.id.xbid_close).setOnClickListener(this);
    this.userImageView = ((ImageView)paramView.findViewById(R.id.xbid_user_image));
    this.userName = ((TextView)paramView.findViewById(R.id.xbid_user_name));
    this.userEmail = ((TextView)paramView.findViewById(R.id.xbid_user_email));
  }
  
  public static abstract interface Callbacks
  {
    public abstract void onClickCloseHeader();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\ui\HeaderFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */