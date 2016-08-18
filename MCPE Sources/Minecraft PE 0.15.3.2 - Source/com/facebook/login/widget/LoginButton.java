package com.facebook.login.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.R.color;
import com.facebook.R.string;
import com.facebook.R.style;
import com.facebook.R.styleable;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.LoginAuthorizationType;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.FetchedAppSettings;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public class LoginButton
  extends FacebookButtonBase
{
  private static final String TAG = LoginButton.class.getName();
  private AccessTokenTracker accessTokenTracker;
  private boolean confirmLogout;
  private String loginLogoutEventName = "fb_login_view_usage";
  private LoginManager loginManager;
  private String loginText;
  private String logoutText;
  private LoginButtonProperties properties = new LoginButtonProperties();
  private boolean toolTipChecked;
  private long toolTipDisplayTime = 6000L;
  private ToolTipMode toolTipMode;
  private ToolTipPopup toolTipPopup;
  private ToolTipPopup.Style toolTipStyle = ToolTipPopup.Style.BLUE;
  
  public LoginButton(Context paramContext)
  {
    super(paramContext, null, 0, 0, "fb_login_button_create", "fb_login_button_did_tap");
  }
  
  public LoginButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet, 0, 0, "fb_login_button_create", "fb_login_button_did_tap");
  }
  
  public LoginButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt, 0, "fb_login_button_create", "fb_login_button_did_tap");
  }
  
  private void checkToolTipSettings()
  {
    switch (this.toolTipMode)
    {
    default: 
      return;
    case ???: 
      final String str = Utility.getMetadataApplicationId(getContext());
      FacebookSdk.getExecutor().execute(new Runnable()
      {
        public void run()
        {
          final Utility.FetchedAppSettings localFetchedAppSettings = Utility.queryAppSettings(str, false);
          LoginButton.this.getActivity().runOnUiThread(new Runnable()
          {
            public void run()
            {
              LoginButton.this.showToolTipPerSettings(localFetchedAppSettings);
            }
          });
        }
      });
      return;
    }
    displayToolTip(getResources().getString(R.string.com_facebook_tooltip_default));
  }
  
  private void displayToolTip(String paramString)
  {
    this.toolTipPopup = new ToolTipPopup(paramString, this);
    this.toolTipPopup.setStyle(this.toolTipStyle);
    this.toolTipPopup.setNuxDisplayTime(this.toolTipDisplayTime);
    this.toolTipPopup.show();
  }
  
  private int measureButtonWidth(String paramString)
  {
    int i = measureTextWidth(paramString);
    return getCompoundPaddingLeft() + getCompoundDrawablePadding() + i + getCompoundPaddingRight();
  }
  
  private void parseLoginButtonAttributes(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    this.toolTipMode = ToolTipMode.DEFAULT;
    paramContext = paramContext.getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.com_facebook_login_view, paramInt1, paramInt2);
    try
    {
      this.confirmLogout = paramContext.getBoolean(R.styleable.com_facebook_login_view_com_facebook_confirm_logout, true);
      this.loginText = paramContext.getString(R.styleable.com_facebook_login_view_com_facebook_login_text);
      this.logoutText = paramContext.getString(R.styleable.com_facebook_login_view_com_facebook_logout_text);
      this.toolTipMode = ToolTipMode.fromInt(paramContext.getInt(R.styleable.com_facebook_login_view_com_facebook_tooltip_mode, ToolTipMode.DEFAULT.getValue()));
      return;
    }
    finally
    {
      paramContext.recycle();
    }
  }
  
  private void setButtonText()
  {
    Resources localResources = getResources();
    if ((!isInEditMode()) && (AccessToken.getCurrentAccessToken() != null))
    {
      if (this.logoutText != null) {}
      for (localObject = this.logoutText;; localObject = localResources.getString(R.string.com_facebook_loginview_log_out_button))
      {
        setText((CharSequence)localObject);
        return;
      }
    }
    if (this.loginText != null)
    {
      setText(this.loginText);
      return;
    }
    String str = localResources.getString(R.string.com_facebook_loginview_log_in_button_long);
    int i = getWidth();
    Object localObject = str;
    if (i != 0)
    {
      localObject = str;
      if (measureButtonWidth(str) > i) {
        localObject = localResources.getString(R.string.com_facebook_loginview_log_in_button);
      }
    }
    setText((CharSequence)localObject);
  }
  
  private void showToolTipPerSettings(Utility.FetchedAppSettings paramFetchedAppSettings)
  {
    if ((paramFetchedAppSettings != null) && (paramFetchedAppSettings.getNuxEnabled()) && (getVisibility() == 0)) {
      displayToolTip(paramFetchedAppSettings.getNuxContent());
    }
  }
  
  public void clearPermissions()
  {
    this.properties.clearPermissions();
  }
  
  protected void configureButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super.configureButton(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setInternalOnClickListener(getNewLoginClickListener());
    parseLoginButtonAttributes(paramContext, paramAttributeSet, paramInt1, paramInt2);
    if (isInEditMode())
    {
      setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));
      this.loginText = "Log in with Facebook";
    }
    for (;;)
    {
      setButtonText();
      return;
      this.accessTokenTracker = new AccessTokenTracker()
      {
        protected void onCurrentAccessTokenChanged(AccessToken paramAnonymousAccessToken1, AccessToken paramAnonymousAccessToken2)
        {
          LoginButton.this.setButtonText();
        }
      };
    }
  }
  
  public void dismissToolTip()
  {
    if (this.toolTipPopup != null)
    {
      this.toolTipPopup.dismiss();
      this.toolTipPopup = null;
    }
  }
  
  public DefaultAudience getDefaultAudience()
  {
    return this.properties.getDefaultAudience();
  }
  
  protected int getDefaultRequestCode()
  {
    return CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode();
  }
  
  protected int getDefaultStyleResource()
  {
    return R.style.com_facebook_loginview_default_style;
  }
  
  public LoginBehavior getLoginBehavior()
  {
    return this.properties.getLoginBehavior();
  }
  
  LoginManager getLoginManager()
  {
    if (this.loginManager == null) {
      this.loginManager = LoginManager.getInstance();
    }
    return this.loginManager;
  }
  
  protected LoginClickListener getNewLoginClickListener()
  {
    return new LoginClickListener();
  }
  
  List<String> getPermissions()
  {
    return this.properties.getPermissions();
  }
  
  public long getToolTipDisplayTime()
  {
    return this.toolTipDisplayTime;
  }
  
  public ToolTipMode getToolTipMode()
  {
    return this.toolTipMode;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if ((this.accessTokenTracker != null) && (!this.accessTokenTracker.isTracking()))
    {
      this.accessTokenTracker.startTracking();
      setButtonText();
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.accessTokenTracker != null) {
      this.accessTokenTracker.stopTracking();
    }
    dismissToolTip();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((!this.toolTipChecked) && (!isInEditMode()))
    {
      this.toolTipChecked = true;
      checkToolTipSettings();
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    setButtonText();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    Object localObject = getPaint().getFontMetrics();
    paramInt2 = getCompoundPaddingTop();
    int i = (int)Math.ceil(Math.abs(((Paint.FontMetrics)localObject).top) + Math.abs(((Paint.FontMetrics)localObject).bottom));
    int j = getCompoundPaddingBottom();
    Resources localResources = getResources();
    String str = this.loginText;
    localObject = str;
    if (str == null)
    {
      localObject = localResources.getString(R.string.com_facebook_loginview_log_in_button_long);
      k = measureButtonWidth((String)localObject);
      if (resolveSize(k, paramInt1) < k) {
        localObject = localResources.getString(R.string.com_facebook_loginview_log_in_button);
      }
    }
    int k = measureButtonWidth((String)localObject);
    str = this.logoutText;
    localObject = str;
    if (str == null) {
      localObject = localResources.getString(R.string.com_facebook_loginview_log_out_button);
    }
    setMeasuredDimension(resolveSize(Math.max(k, measureButtonWidth((String)localObject)), paramInt1), paramInt2 + i + j);
  }
  
  protected void onVisibilityChanged(View paramView, int paramInt)
  {
    super.onVisibilityChanged(paramView, paramInt);
    if (paramInt != 0) {
      dismissToolTip();
    }
  }
  
  public void registerCallback(CallbackManager paramCallbackManager, FacebookCallback<LoginResult> paramFacebookCallback)
  {
    getLoginManager().registerCallback(paramCallbackManager, paramFacebookCallback);
  }
  
  public void setDefaultAudience(DefaultAudience paramDefaultAudience)
  {
    this.properties.setDefaultAudience(paramDefaultAudience);
  }
  
  public void setLoginBehavior(LoginBehavior paramLoginBehavior)
  {
    this.properties.setLoginBehavior(paramLoginBehavior);
  }
  
  void setLoginManager(LoginManager paramLoginManager)
  {
    this.loginManager = paramLoginManager;
  }
  
  void setProperties(LoginButtonProperties paramLoginButtonProperties)
  {
    this.properties = paramLoginButtonProperties;
  }
  
  public void setPublishPermissions(List<String> paramList)
  {
    this.properties.setPublishPermissions(paramList);
  }
  
  public void setPublishPermissions(String... paramVarArgs)
  {
    this.properties.setPublishPermissions(Arrays.asList(paramVarArgs));
  }
  
  public void setReadPermissions(List<String> paramList)
  {
    this.properties.setReadPermissions(paramList);
  }
  
  public void setReadPermissions(String... paramVarArgs)
  {
    this.properties.setReadPermissions(Arrays.asList(paramVarArgs));
  }
  
  public void setToolTipDisplayTime(long paramLong)
  {
    this.toolTipDisplayTime = paramLong;
  }
  
  public void setToolTipMode(ToolTipMode paramToolTipMode)
  {
    this.toolTipMode = paramToolTipMode;
  }
  
  public void setToolTipStyle(ToolTipPopup.Style paramStyle)
  {
    this.toolTipStyle = paramStyle;
  }
  
  static class LoginButtonProperties
  {
    private LoginAuthorizationType authorizationType = null;
    private DefaultAudience defaultAudience = DefaultAudience.FRIENDS;
    private LoginBehavior loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK;
    private List<String> permissions = Collections.emptyList();
    
    public void clearPermissions()
    {
      this.permissions = null;
      this.authorizationType = null;
    }
    
    public DefaultAudience getDefaultAudience()
    {
      return this.defaultAudience;
    }
    
    public LoginBehavior getLoginBehavior()
    {
      return this.loginBehavior;
    }
    
    List<String> getPermissions()
    {
      return this.permissions;
    }
    
    public void setDefaultAudience(DefaultAudience paramDefaultAudience)
    {
      this.defaultAudience = paramDefaultAudience;
    }
    
    public void setLoginBehavior(LoginBehavior paramLoginBehavior)
    {
      this.loginBehavior = paramLoginBehavior;
    }
    
    public void setPublishPermissions(List<String> paramList)
    {
      if (LoginAuthorizationType.READ.equals(this.authorizationType)) {
        throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
      }
      if (Utility.isNullOrEmpty(paramList)) {
        throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
      }
      this.permissions = paramList;
      this.authorizationType = LoginAuthorizationType.PUBLISH;
    }
    
    public void setReadPermissions(List<String> paramList)
    {
      if (LoginAuthorizationType.PUBLISH.equals(this.authorizationType)) {
        throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
      }
      this.permissions = paramList;
      this.authorizationType = LoginAuthorizationType.READ;
    }
  }
  
  protected class LoginClickListener
    implements View.OnClickListener
  {
    protected LoginClickListener() {}
    
    protected LoginManager getLoginManager()
    {
      LoginManager localLoginManager = LoginManager.getInstance();
      localLoginManager.setDefaultAudience(LoginButton.this.getDefaultAudience());
      localLoginManager.setLoginBehavior(LoginButton.this.getLoginBehavior());
      return localLoginManager;
    }
    
    public void onClick(View paramView)
    {
      LoginButton.this.callExternalOnClickListener(paramView);
      paramView = AccessToken.getCurrentAccessToken();
      AppEventsLogger localAppEventsLogger;
      Bundle localBundle;
      if (paramView != null)
      {
        performLogout(LoginButton.this.getContext());
        localAppEventsLogger = AppEventsLogger.newLogger(LoginButton.this.getContext());
        localBundle = new Bundle();
        if (paramView == null) {
          break label83;
        }
      }
      label83:
      for (int i = 0;; i = 1)
      {
        localBundle.putInt("logging_in", i);
        localAppEventsLogger.logSdkEvent(LoginButton.this.loginLogoutEventName, null, localBundle);
        return;
        performLogin();
        break;
      }
    }
    
    protected void performLogin()
    {
      LoginManager localLoginManager = getLoginManager();
      if (LoginAuthorizationType.PUBLISH.equals(LoginButton.access$500(LoginButton.this).authorizationType))
      {
        if (LoginButton.this.getFragment() != null)
        {
          localLoginManager.logInWithPublishPermissions(LoginButton.this.getFragment(), LoginButton.access$500(LoginButton.this).permissions);
          return;
        }
        if (LoginButton.this.getNativeFragment() != null)
        {
          localLoginManager.logInWithPublishPermissions(LoginButton.this.getNativeFragment(), LoginButton.access$500(LoginButton.this).permissions);
          return;
        }
        localLoginManager.logInWithPublishPermissions(LoginButton.this.getActivity(), LoginButton.access$500(LoginButton.this).permissions);
        return;
      }
      if (LoginButton.this.getFragment() != null)
      {
        localLoginManager.logInWithReadPermissions(LoginButton.this.getFragment(), LoginButton.access$500(LoginButton.this).permissions);
        return;
      }
      if (LoginButton.this.getNativeFragment() != null)
      {
        localLoginManager.logInWithReadPermissions(LoginButton.this.getNativeFragment(), LoginButton.access$500(LoginButton.this).permissions);
        return;
      }
      localLoginManager.logInWithReadPermissions(LoginButton.this.getActivity(), LoginButton.access$500(LoginButton.this).permissions);
    }
    
    protected void performLogout(Context paramContext)
    {
      final LoginManager localLoginManager = getLoginManager();
      if (LoginButton.this.confirmLogout)
      {
        String str1 = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_log_out_action);
        String str2 = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_cancel_action);
        Object localObject = Profile.getCurrentProfile();
        if ((localObject != null) && (((Profile)localObject).getName() != null)) {}
        for (localObject = String.format(LoginButton.this.getResources().getString(R.string.com_facebook_loginview_logged_in_as), new Object[] { ((Profile)localObject).getName() });; localObject = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_logged_in_using_facebook))
        {
          paramContext = new AlertDialog.Builder(paramContext);
          paramContext.setMessage((CharSequence)localObject).setCancelable(true).setPositiveButton(str1, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              localLoginManager.logOut();
            }
          }).setNegativeButton(str2, null);
          paramContext.create().show();
          return;
        }
      }
      localLoginManager.logOut();
    }
  }
  
  public static enum ToolTipMode
  {
    AUTOMATIC("automatic", 0),  DISPLAY_ALWAYS("display_always", 1),  NEVER_DISPLAY("never_display", 2);
    
    public static ToolTipMode DEFAULT = AUTOMATIC;
    private int intValue;
    private String stringValue;
    
    private ToolTipMode(String paramString, int paramInt)
    {
      this.stringValue = paramString;
      this.intValue = paramInt;
    }
    
    public static ToolTipMode fromInt(int paramInt)
    {
      ToolTipMode[] arrayOfToolTipMode = values();
      int j = arrayOfToolTipMode.length;
      int i = 0;
      while (i < j)
      {
        ToolTipMode localToolTipMode = arrayOfToolTipMode[i];
        if (localToolTipMode.getValue() == paramInt) {
          return localToolTipMode;
        }
        i += 1;
      }
      return null;
    }
    
    public int getValue()
    {
      return this.intValue;
    }
    
    public String toString()
    {
      return this.stringValue;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\login\widget\LoginButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */