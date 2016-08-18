package com.facebook.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.app.FragmentActivity;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookSdk;
import com.facebook.internal.CustomTab;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.FetchedAppSettings;
import com.facebook.internal.Validate;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomTabLoginMethodHandler
  extends WebLoginMethodHandler
{
  private static final String CHROME_PACKAGE = "com.android.chrome";
  public static final Parcelable.Creator<CustomTabLoginMethodHandler> CREATOR = new Parcelable.Creator()
  {
    public CustomTabLoginMethodHandler createFromParcel(Parcel paramAnonymousParcel)
    {
      return new CustomTabLoginMethodHandler(paramAnonymousParcel);
    }
    
    public CustomTabLoginMethodHandler[] newArray(int paramAnonymousInt)
    {
      return new CustomTabLoginMethodHandler[paramAnonymousInt];
    }
  };
  private static final String CUSTOM_TABS_SERVICE_ACTION = "android.support.customtabs.action.CustomTabsService";
  private static final String OAUTH_DIALOG = "oauth";
  private CustomTab customTab;
  
  CustomTabLoginMethodHandler(Parcel paramParcel)
  {
    super(paramParcel);
  }
  
  CustomTabLoginMethodHandler(LoginClient paramLoginClient)
  {
    super(paramLoginClient);
  }
  
  private boolean isChromeCustomTabsSupported(Context paramContext)
  {
    boolean bool2 = false;
    Intent localIntent = new Intent("android.support.customtabs.action.CustomTabsService");
    localIntent.setPackage("com.android.chrome");
    paramContext = paramContext.getPackageManager().queryIntentServices(localIntent, 0);
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (!paramContext.isEmpty()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private boolean isCustomTabsAllowed()
  {
    return (isCustomTabsEnabled()) && (isChromeCustomTabsSupported(this.loginClient.getActivity())) && (Validate.hasCustomTabRedirectActivity(FacebookSdk.getApplicationContext()));
  }
  
  private boolean isCustomTabsEnabled()
  {
    Utility.FetchedAppSettings localFetchedAppSettings = Utility.getAppSettingsWithoutQuery(Utility.getMetadataApplicationId(this.loginClient.getActivity()));
    return (localFetchedAppSettings != null) && (localFetchedAppSettings.getCustomTabsEnabled());
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  String getNameForLogging()
  {
    return "custom_tab";
  }
  
  AccessTokenSource getTokenSource()
  {
    return AccessTokenSource.CUSTOM_TAB;
  }
  
  protected void putChallengeParam(JSONObject paramJSONObject)
    throws JSONException
  {
    if ((this.loginClient.getFragment() instanceof LoginFragment)) {
      paramJSONObject.put("7_challenge", ((LoginFragment)this.loginClient.getFragment()).getChallengeParam());
    }
  }
  
  boolean tryAuthorize(LoginClient.Request paramRequest)
  {
    if (!isCustomTabsAllowed()) {
      return false;
    }
    paramRequest = addExtraParameters(getParameters(paramRequest), paramRequest);
    FragmentActivity localFragmentActivity = this.loginClient.getActivity();
    this.customTab = new CustomTab("oauth", paramRequest);
    this.customTab.openCustomTab(localFragmentActivity);
    return true;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\login\CustomTabLoginMethodHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */