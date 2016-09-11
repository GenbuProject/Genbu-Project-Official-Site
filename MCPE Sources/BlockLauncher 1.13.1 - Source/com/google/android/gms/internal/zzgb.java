package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzhb
public class zzgb
  implements zzfz
{
  private final Context mContext;
  final Set<WebView> zzFr = Collections.synchronizedSet(new HashSet());
  
  public zzgb(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public void zza(String paramString1, final String paramString2, final String paramString3)
  {
    zzin.zzaI("Fetching assets for the given html");
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        final WebView localWebView = zzgb.this.zzfR();
        localWebView.setWebViewClient(new WebViewClient()
        {
          public void onPageFinished(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            zzin.zzaI("Loading assets have finished");
            zzgb.this.zzFr.remove(localWebView);
          }
          
          public void onReceivedError(WebView paramAnonymous2WebView, int paramAnonymous2Int, String paramAnonymous2String1, String paramAnonymous2String2)
          {
            zzin.zzaK("Loading assets have failed.");
            zzgb.this.zzFr.remove(localWebView);
          }
        });
        zzgb.this.zzFr.add(localWebView);
        localWebView.loadDataWithBaseURL(paramString2, paramString3, "text/html", "UTF-8", null);
        zzin.zzaI("Fetching assets finished.");
      }
    });
  }
  
  public WebView zzfR()
  {
    WebView localWebView = new WebView(this.mContext);
    localWebView.getSettings().setJavaScriptEnabled(true);
    return localWebView;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzgb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */