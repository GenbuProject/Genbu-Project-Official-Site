package bolts;

import android.content.Context;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewAppLinkResolver
  implements AppLinkResolver
{
  private static final String KEY_AL_VALUE = "value";
  private static final String KEY_ANDROID = "android";
  private static final String KEY_APP_NAME = "app_name";
  private static final String KEY_CLASS = "class";
  private static final String KEY_PACKAGE = "package";
  private static final String KEY_SHOULD_FALLBACK = "should_fallback";
  private static final String KEY_URL = "url";
  private static final String KEY_WEB = "web";
  private static final String KEY_WEB_URL = "url";
  private static final String META_TAG_PREFIX = "al";
  private static final String PREFER_HEADER = "Prefer-Html-Meta-Tags";
  private static final String TAG_EXTRACTION_JAVASCRIPT = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
  private final Context context;
  
  public WebViewAppLinkResolver(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private static List<Map<String, Object>> getAlList(Map<String, Object> paramMap, String paramString)
  {
    paramString = (List)paramMap.get(paramString);
    paramMap = paramString;
    if (paramString == null) {
      paramMap = Collections.emptyList();
    }
    return paramMap;
  }
  
  private static AppLink makeAppLinkFromAlData(Map<String, Object> paramMap, Uri paramUri)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = (List)paramMap.get("android");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = Collections.emptyList();
    }
    localObject2 = ((List)localObject1).iterator();
    if (((Iterator)localObject2).hasNext())
    {
      localObject1 = (Map)((Iterator)localObject2).next();
      List localList1 = getAlList((Map)localObject1, "url");
      List localList2 = getAlList((Map)localObject1, "package");
      List localList3 = getAlList((Map)localObject1, "class");
      List localList4 = getAlList((Map)localObject1, "app_name");
      int j = Math.max(localList1.size(), Math.max(localList2.size(), Math.max(localList3.size(), localList4.size())));
      int i = 0;
      label143:
      label179:
      Uri localUri;
      label223:
      String str1;
      label264:
      String str2;
      if (i < j)
      {
        if (localList1.size() <= i) {
          break label341;
        }
        localObject1 = ((Map)localList1.get(i)).get("value");
        localUri = tryCreateUrl((String)localObject1);
        if (localList2.size() <= i) {
          break label347;
        }
        localObject1 = ((Map)localList2.get(i)).get("value");
        str1 = (String)localObject1;
        if (localList3.size() <= i) {
          break label353;
        }
        localObject1 = ((Map)localList3.get(i)).get("value");
        str2 = (String)localObject1;
        if (localList4.size() <= i) {
          break label359;
        }
      }
      label341:
      label347:
      label353:
      label359:
      for (localObject1 = ((Map)localList4.get(i)).get("value");; localObject1 = null)
      {
        localArrayList.add(new AppLink.Target(str1, str2, localUri, (String)localObject1));
        i += 1;
        break label143;
        break;
        localObject1 = null;
        break label179;
        localObject1 = null;
        break label223;
        localObject1 = null;
        break label264;
      }
    }
    paramMap = (List)paramMap.get("web");
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      paramMap = (Map)paramMap.get(0);
      localObject2 = (List)paramMap.get("url");
      paramMap = (List)paramMap.get("should_fallback");
      if ((paramMap != null) && (paramMap.size() > 0))
      {
        paramMap = (String)((Map)paramMap.get(0)).get("value");
        if (Arrays.asList(new String[] { "no", "false", "0" }).contains(paramMap.toLowerCase()))
        {
          paramMap = null;
          localObject1 = paramMap;
          if (paramMap != null)
          {
            localObject1 = paramMap;
            if (localObject2 != null)
            {
              localObject1 = paramMap;
              if (((List)localObject2).size() <= 0) {}
            }
          }
        }
      }
    }
    for (localObject1 = tryCreateUrl((String)((Map)((List)localObject2).get(0)).get("value"));; localObject1 = paramUri)
    {
      return new AppLink(paramUri, localArrayList, (Uri)localObject1);
      paramMap = paramUri;
      break;
    }
  }
  
  private static Map<String, Object> parseAlData(JSONArray paramJSONArray)
    throws JSONException
  {
    HashMap localHashMap = new HashMap();
    int i = 0;
    JSONObject localJSONObject;
    String[] arrayOfString;
    int j;
    Object localObject1;
    label64:
    Object localObject2;
    for (;;)
    {
      if (i < paramJSONArray.length())
      {
        localJSONObject = paramJSONArray.getJSONObject(i);
        arrayOfString = localJSONObject.getString("property").split(":");
        if (!arrayOfString[0].equals("al"))
        {
          i += 1;
        }
        else
        {
          j = 1;
          localObject1 = localHashMap;
          if (j < arrayOfString.length)
          {
            localObject2 = (List)((Map)localObject1).get(arrayOfString[j]);
            if (localObject2 != null) {
              break label243;
            }
            localObject2 = new ArrayList();
            ((Map)localObject1).put(arrayOfString[j], localObject2);
          }
        }
      }
    }
    label143:
    label240:
    label243:
    for (;;)
    {
      if (((List)localObject2).size() > 0)
      {
        localObject1 = (Map)((List)localObject2).get(((List)localObject2).size() - 1);
        if ((localObject1 != null) && (j != arrayOfString.length - 1)) {
          break label240;
        }
        localObject1 = new HashMap();
        ((List)localObject2).add(localObject1);
      }
      for (;;)
      {
        j += 1;
        break label64;
        localObject1 = null;
        break label143;
        if (!localJSONObject.has("content")) {
          break;
        }
        if (localJSONObject.isNull("content"))
        {
          ((Map)localObject1).put("value", null);
          break;
        }
        ((Map)localObject1).put("value", localJSONObject.getString("content"));
        break;
        return localHashMap;
      }
    }
  }
  
  /* Error */
  private static String readFromConnection(URLConnection paramURLConnection)
    throws java.io.IOException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: instanceof 220
    //   6: ifeq +71 -> 77
    //   9: aload_0
    //   10: checkcast 220	java/net/HttpURLConnection
    //   13: astore 4
    //   15: aload_0
    //   16: invokevirtual 226	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   19: astore_3
    //   20: new 228	java/io/ByteArrayOutputStream
    //   23: dup
    //   24: invokespecial 229	java/io/ByteArrayOutputStream:<init>	()V
    //   27: astore 6
    //   29: sipush 1024
    //   32: newarray <illegal type>
    //   34: astore 4
    //   36: aload_3
    //   37: aload 4
    //   39: invokevirtual 235	java/io/InputStream:read	([B)I
    //   42: istore_2
    //   43: iload_2
    //   44: iconst_m1
    //   45: if_icmpeq +40 -> 85
    //   48: aload 6
    //   50: aload 4
    //   52: iconst_0
    //   53: iload_2
    //   54: invokevirtual 239	java/io/ByteArrayOutputStream:write	([BII)V
    //   57: goto -21 -> 36
    //   60: astore_0
    //   61: aload_3
    //   62: invokevirtual 242	java/io/InputStream:close	()V
    //   65: aload_0
    //   66: athrow
    //   67: astore_3
    //   68: aload 4
    //   70: invokevirtual 245	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   73: astore_3
    //   74: goto -54 -> 20
    //   77: aload_0
    //   78: invokevirtual 226	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   81: astore_3
    //   82: goto -62 -> 20
    //   85: aload_0
    //   86: invokevirtual 248	java/net/URLConnection:getContentEncoding	()Ljava/lang/String;
    //   89: astore 5
    //   91: aload 5
    //   93: astore 4
    //   95: aload 5
    //   97: ifnonnull +67 -> 164
    //   100: aload_0
    //   101: invokevirtual 251	java/net/URLConnection:getContentType	()Ljava/lang/String;
    //   104: ldc -3
    //   106: invokevirtual 199	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   109: astore 4
    //   111: aload 4
    //   113: arraylength
    //   114: istore_2
    //   115: aload 5
    //   117: astore_0
    //   118: iload_1
    //   119: iload_2
    //   120: if_icmpge +32 -> 152
    //   123: aload 4
    //   125: iload_1
    //   126: aaload
    //   127: invokevirtual 256	java/lang/String:trim	()Ljava/lang/String;
    //   130: astore_0
    //   131: aload_0
    //   132: ldc_w 258
    //   135: invokevirtual 261	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   138: ifeq +47 -> 185
    //   141: aload_0
    //   142: ldc_w 258
    //   145: invokevirtual 262	java/lang/String:length	()I
    //   148: invokevirtual 266	java/lang/String:substring	(I)Ljava/lang/String;
    //   151: astore_0
    //   152: aload_0
    //   153: astore 4
    //   155: aload_0
    //   156: ifnonnull +8 -> 164
    //   159: ldc_w 268
    //   162: astore 4
    //   164: new 135	java/lang/String
    //   167: dup
    //   168: aload 6
    //   170: invokevirtual 272	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   173: aload 4
    //   175: invokespecial 275	java/lang/String:<init>	([BLjava/lang/String;)V
    //   178: astore_0
    //   179: aload_3
    //   180: invokevirtual 242	java/io/InputStream:close	()V
    //   183: aload_0
    //   184: areturn
    //   185: iload_1
    //   186: iconst_1
    //   187: iadd
    //   188: istore_1
    //   189: goto -74 -> 115
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	192	0	paramURLConnection	URLConnection
    //   1	188	1	i	int
    //   42	79	2	j	int
    //   19	43	3	localInputStream1	java.io.InputStream
    //   67	1	3	localException	Exception
    //   73	107	3	localInputStream2	java.io.InputStream
    //   13	161	4	localObject	Object
    //   89	27	5	str	String
    //   27	142	6	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   20	36	60	finally
    //   36	43	60	finally
    //   48	57	60	finally
    //   85	91	60	finally
    //   100	115	60	finally
    //   123	152	60	finally
    //   164	179	60	finally
    //   15	20	67	java/lang/Exception
  }
  
  private static Uri tryCreateUrl(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse(paramString);
  }
  
  public Task<AppLink> getAppLinkFromUrlInBackground(final Uri paramUri)
  {
    final Capture localCapture1 = new Capture();
    final Capture localCapture2 = new Capture();
    Task.callInBackground(new Callable()
    {
      public Void call()
        throws Exception
      {
        URL localURL = new URL(paramUri.toString());
        URLConnection localURLConnection = null;
        while (localURL != null)
        {
          localURLConnection = localURL.openConnection();
          if ((localURLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection)localURLConnection).setInstanceFollowRedirects(true);
          }
          localURLConnection.setRequestProperty("Prefer-Html-Meta-Tags", "al");
          localURLConnection.connect();
          if ((localURLConnection instanceof HttpURLConnection))
          {
            HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURLConnection;
            if ((localHttpURLConnection.getResponseCode() >= 300) && (localHttpURLConnection.getResponseCode() < 400))
            {
              localURL = new URL(localHttpURLConnection.getHeaderField("Location"));
              localHttpURLConnection.disconnect();
            }
            else
            {
              localURL = null;
            }
          }
          else
          {
            localURL = null;
          }
        }
        try
        {
          localCapture1.set(WebViewAppLinkResolver.readFromConnection(localURLConnection));
          localCapture2.set(localURLConnection.getContentType());
          return null;
        }
        finally
        {
          if ((localURLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection)localURLConnection).disconnect();
          }
        }
      }
    }).onSuccessTask(new Continuation()
    {
      public Task<JSONArray> then(Task<Void> paramAnonymousTask)
        throws Exception
      {
        final Task.TaskCompletionSource localTaskCompletionSource = Task.create();
        WebView localWebView = new WebView(WebViewAppLinkResolver.this.context);
        localWebView.getSettings().setJavaScriptEnabled(true);
        localWebView.setNetworkAvailable(false);
        localWebView.setWebViewClient(new WebViewClient()
        {
          private boolean loaded = false;
          
          private void runJavaScript(WebView paramAnonymous2WebView)
          {
            if (!this.loaded)
            {
              this.loaded = true;
              paramAnonymous2WebView.loadUrl("javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())");
            }
          }
          
          public void onLoadResource(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            super.onLoadResource(paramAnonymous2WebView, paramAnonymous2String);
            runJavaScript(paramAnonymous2WebView);
          }
          
          public void onPageFinished(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            super.onPageFinished(paramAnonymous2WebView, paramAnonymous2String);
            runJavaScript(paramAnonymous2WebView);
          }
        });
        localWebView.addJavascriptInterface(new Object()
        {
          @JavascriptInterface
          public void setValue(String paramAnonymous2String)
          {
            try
            {
              localTaskCompletionSource.trySetResult(new JSONArray(paramAnonymous2String));
              return;
            }
            catch (JSONException paramAnonymous2String)
            {
              localTaskCompletionSource.trySetError(paramAnonymous2String);
            }
          }
        }, "boltsWebViewAppLinkResolverResult");
        if (localCapture2.get() != null) {}
        for (paramAnonymousTask = ((String)localCapture2.get()).split(";")[0];; paramAnonymousTask = null)
        {
          localWebView.loadDataWithBaseURL(paramUri.toString(), (String)localCapture1.get(), paramAnonymousTask, null, null);
          return localTaskCompletionSource.getTask();
        }
      }
    }, Task.UI_THREAD_EXECUTOR).onSuccess(new Continuation()
    {
      public AppLink then(Task<JSONArray> paramAnonymousTask)
        throws Exception
      {
        return WebViewAppLinkResolver.makeAppLinkFromAlData(WebViewAppLinkResolver.access$000((JSONArray)paramAnonymousTask.getResult()), paramUri);
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\bolts\WebViewAppLinkResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */