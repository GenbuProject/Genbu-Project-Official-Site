package com.microsoft.onlineid;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.SystemClock;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.microsoft.onlineid.internal.Uris;
import java.util.logging.Logger;

public class ResourcePreloader
{
  private static final String INT_PRELOAD_URI = "https://signup.live-int.com/SignupPreload";
  private static final String PROD_PRELOAD_URI = "https://signup.live.com/SignupPreload";
  private static final Logger logger = Logger.getLogger("ResourcePreloader");
  
  private static void addWebViewClient(WebView paramWebView)
  {
    paramWebView.setWebViewClient(new WebViewClient()
    {
      private long started;
      
      public void onLoadResource(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        ResourcePreloader.logger.info("Loading " + paramAnonymousString);
      }
      
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        long l1 = SystemClock.elapsedRealtime();
        long l2 = this.started;
        ResourcePreloader.logger.info("Page load for " + paramAnonymousString + " finished in " + (l1 - l2) + "ms");
      }
      
      public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        this.started = SystemClock.elapsedRealtime();
      }
    });
  }
  
  private static Uri buildUri(Context paramContext, String paramString)
  {
    paramString = Uri.parse("https://signup.live.com/SignupPreload").buildUpon().appendQueryParameter("cobrandid", paramString).build();
    return Uris.appendMarketQueryString(paramContext.getApplicationContext(), paramString);
  }
  
  public static void preloadSignup(Context paramContext, String paramString)
  {
    WebView localWebView = new WebView(paramContext);
    localWebView.getSettings().setJavaScriptEnabled(true);
    localWebView.loadUrl(buildUri(paramContext, paramString).toString());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\ResourcePreloader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */