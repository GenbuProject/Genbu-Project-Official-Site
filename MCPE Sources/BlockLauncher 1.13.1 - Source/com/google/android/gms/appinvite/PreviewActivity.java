package com.google.android.gms.appinvite;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class PreviewActivity
  extends Activity
{
  public static final String ACTION_PREVIEW = "com.google.android.gms.appinvite.ACTION_PREVIEW";
  public static final String EXTRA_LAYOUT_RES_ID = "com.google.android.gms.appinvite.LAYOUT_RES_ID";
  public static final String EXTRA_TABS = "com.google.android.gms.appinvite.TABS";
  public static final String EXTRA_VIEWS = "com.google.android.gms.appinvite.VIEWS";
  public static final String KEY_TAB_CONTENT_ID = "tabContentId";
  public static final String KEY_TAB_TAG = "tabTag";
  public static final String KEY_TEXT_VIEW_IS_TITLE = "TextView_isTitle";
  public static final String KEY_TEXT_VIEW_TEXT = "TextView_text";
  public static final String KEY_TEXT_VIEW_TEXT_COLOR = "TextView_textColor";
  public static final String KEY_VIEW_BACKGROUND_COLOR = "View_backgroundColor";
  public static final String KEY_VIEW_ID = "View_id";
  public static final String KEY_VIEW_MIN_HEIGHT = "View_minHeight";
  public static final String KEY_VIEW_ON_CLICK_LISTENER = "View_onClickListener";
  public static final String KEY_WEB_VIEW_DATA = "WebView_data";
  public static final String ON_CLICK_LISTENER_CLOSE = "close";
  
  private View zza(Context paramContext, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    int i = paramBundle.getInt("com.google.android.gms.appinvite.LAYOUT_RES_ID");
    paramContext = LayoutInflater.from(paramContext).inflate(i, paramViewGroup, false);
    paramViewGroup = paramBundle.getParcelableArrayList("com.google.android.gms.appinvite.VIEWS");
    if (paramViewGroup != null)
    {
      paramViewGroup = paramViewGroup.iterator();
      while (paramViewGroup.hasNext()) {
        zza(paramContext, (Bundle)paramViewGroup.next());
      }
    }
    return paramContext;
  }
  
  private void zza(View paramView, Bundle paramBundle)
  {
    paramView = paramView.findViewById(paramBundle.getInt("View_id"));
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      switch (str.hashCode())
      {
      default: 
        label116:
        i = -1;
      }
      for (;;)
      {
        switch (i)
        {
        default: 
          break;
        case 0: 
          paramView.setBackgroundColor(paramBundle.getInt(str));
          break;
          if (!str.equals("View_backgroundColor")) {
            break label116;
          }
          i = 0;
          continue;
          if (!str.equals("View_minHeight")) {
            break label116;
          }
          i = 1;
          continue;
          if (!str.equals("View_onClickListener")) {
            break label116;
          }
          i = 2;
          continue;
          if (!str.equals("TextView_text")) {
            break label116;
          }
          i = 3;
          continue;
          if (!str.equals("TextView_textColor")) {
            break label116;
          }
          i = 4;
          continue;
          if (!str.equals("TextView_isTitle")) {
            break label116;
          }
          i = 5;
          continue;
          if (!str.equals("WebView_data")) {
            break label116;
          }
          i = 6;
        }
      }
      paramView.setMinimumHeight(paramBundle.getInt(str));
      continue;
      str = paramBundle.getString(str);
      switch (str.hashCode())
      {
      }
      label328:
      for (int i = -1;; i = 0) {
        switch (i)
        {
        default: 
          break;
        case 0: 
          paramView.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              PreviewActivity.this.finish();
            }
          });
          break;
          if (!str.equals("close")) {
            break label328;
          }
        }
      }
      if ((paramView instanceof TextView))
      {
        ((TextView)paramView).setText(paramBundle.getCharSequence(str));
        continue;
        if ((paramView instanceof TextView))
        {
          ((TextView)paramView).setTextColor(paramBundle.getInt(str));
          continue;
          if (((paramView instanceof TextView)) && (paramBundle.getBoolean(str)))
          {
            setTitle(((TextView)paramView).getText());
            continue;
            if ((paramView instanceof ViewGroup))
            {
              WebView localWebView = new WebView(this);
              localWebView.loadData(paramBundle.getString(str), "text/html; charset=utf-8", "UTF-8");
              ((ViewGroup)paramView).addView(localWebView, new ViewGroup.LayoutParams(-1, -1));
            }
          }
        }
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((getCallingActivity() == null) || (!"com.google.android.gms".equals(getCallingActivity().getPackageName())))
    {
      finish();
      return;
    }
    View localView;
    try
    {
      paramBundle = createPackageContext("com.google.android.gms", 0);
      localObject = getIntent().getExtras();
      localView = zza(paramBundle, null, (Bundle)localObject);
      if (localView == null)
      {
        finish();
        return;
      }
    }
    catch (PackageManager.NameNotFoundException paramBundle)
    {
      finish();
      return;
    }
    TabHost localTabHost = (TabHost)localView.findViewById(16908306);
    TabWidget localTabWidget = (TabWidget)localView.findViewById(16908307);
    Object localObject = ((Bundle)localObject).getParcelableArrayList("com.google.android.gms.appinvite.TABS");
    if ((localTabHost != null) && (localTabWidget != null) && (localObject != null))
    {
      localTabHost.setup();
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Bundle localBundle = (Bundle)((Iterator)localObject).next();
        TabHost.TabSpec localTabSpec = localTabHost.newTabSpec(localBundle.getString("tabTag"));
        localTabSpec.setContent(localBundle.getInt("tabContentId"));
        localTabSpec.setIndicator(zza(paramBundle, localTabWidget, localBundle));
        localTabHost.addTab(localTabSpec);
      }
    }
    setContentView(localView);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\appinvite\PreviewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */