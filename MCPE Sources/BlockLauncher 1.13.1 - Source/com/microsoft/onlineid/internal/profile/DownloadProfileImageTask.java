package com.microsoft.onlineid.internal.profile;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.transport.Transport;
import com.microsoft.onlineid.internal.transport.TransportFactory;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.sts.ServerConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DownloadProfileImageTask
  extends AsyncTask<Void, Void, Bitmap>
{
  public static final String UserTileExtension = ".png";
  protected static final String UserTileUrlFormat = "https://storage.%2$s/users/0x%1$s/myprofile/expressionprofile/profilephoto:UserTileStatic";
  private final AuthenticatorUserAccount _account;
  private final Context _applicationContext;
  private final ImageView _imageView;
  private boolean _newImageDownloaded;
  private final ServerConfig _serverConfig;
  private final Transport _transport;
  private final String _userTileFileName;
  
  public DownloadProfileImageTask(Context paramContext, AuthenticatorUserAccount paramAuthenticatorUserAccount, ImageView paramImageView)
  {
    this._applicationContext = paramContext;
    this._account = paramAuthenticatorUserAccount;
    this._transport = new TransportFactory(paramContext).createTransport();
    this._serverConfig = new ServerConfig(paramContext);
    this._imageView = paramImageView;
    this._userTileFileName = (this._account.getPuid() + ".png");
    this._newImageDownloaded = false;
  }
  
  DownloadProfileImageTask(AuthenticatorUserAccount paramAuthenticatorUserAccount, Transport paramTransport, ServerConfig paramServerConfig)
  {
    this._applicationContext = null;
    this._account = paramAuthenticatorUserAccount;
    this._transport = paramTransport;
    this._serverConfig = paramServerConfig;
    this._imageView = null;
    this._userTileFileName = null;
    this._newImageDownloaded = false;
  }
  
  /* Error */
  protected Bitmap doInBackground(Void... paramVarArgs)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 34	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_account	Lcom/microsoft/onlineid/sts/AuthenticatorUserAccount;
    //   6: invokevirtual 85	com/microsoft/onlineid/sts/AuthenticatorUserAccount:getCid	()Ljava/lang/String;
    //   9: astore_3
    //   10: aload_0
    //   11: getfield 50	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_serverConfig	Lcom/microsoft/onlineid/sts/ServerConfig;
    //   14: invokevirtual 89	com/microsoft/onlineid/sts/ServerConfig:getEnvironment	()Lcom/microsoft/onlineid/internal/configuration/Environment;
    //   17: getstatic 95	com/microsoft/onlineid/sts/ServerConfig$KnownEnvironment:Production	Lcom/microsoft/onlineid/sts/ServerConfig$KnownEnvironment;
    //   20: invokevirtual 96	com/microsoft/onlineid/sts/ServerConfig$KnownEnvironment:getEnvironment	()Lcom/microsoft/onlineid/internal/configuration/Environment;
    //   23: invokevirtual 102	com/microsoft/onlineid/internal/configuration/Environment:equals	(Ljava/lang/Object;)Z
    //   26: ifeq +95 -> 121
    //   29: ldc 104
    //   31: astore_1
    //   32: ldc 12
    //   34: iconst_2
    //   35: anewarray 106	java/lang/Object
    //   38: dup
    //   39: iconst_0
    //   40: aload_3
    //   41: aastore
    //   42: dup
    //   43: iconst_1
    //   44: aload_1
    //   45: aastore
    //   46: invokestatic 112	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   49: astore_1
    //   50: aload_0
    //   51: getfield 45	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_transport	Lcom/microsoft/onlineid/internal/transport/Transport;
    //   54: new 114	java/net/URL
    //   57: dup
    //   58: aload_1
    //   59: invokespecial 117	java/net/URL:<init>	(Ljava/lang/String;)V
    //   62: invokevirtual 123	com/microsoft/onlineid/internal/transport/Transport:openGetRequest	(Ljava/net/URL;)V
    //   65: aload_0
    //   66: getfield 45	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_transport	Lcom/microsoft/onlineid/internal/transport/Transport;
    //   69: iconst_1
    //   70: invokevirtual 127	com/microsoft/onlineid/internal/transport/Transport:setUseCaches	(Z)V
    //   73: aload_0
    //   74: getfield 45	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_transport	Lcom/microsoft/onlineid/internal/transport/Transport;
    //   77: invokevirtual 131	com/microsoft/onlineid/internal/transport/Transport:getResponseStream	()Ljava/io/InputStream;
    //   80: astore_1
    //   81: aload_1
    //   82: astore_2
    //   83: aload_2
    //   84: astore_1
    //   85: aload_2
    //   86: invokestatic 137	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   89: astore_3
    //   90: aload_2
    //   91: astore_1
    //   92: aload_0
    //   93: aload_3
    //   94: invokevirtual 141	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:saveUserTileImage	(Landroid/graphics/Bitmap;)V
    //   97: aload_2
    //   98: astore_1
    //   99: aload_0
    //   100: iconst_1
    //   101: putfield 72	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_newImageDownloaded	Z
    //   104: aload_0
    //   105: getfield 45	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_transport	Lcom/microsoft/onlineid/internal/transport/Transport;
    //   108: invokevirtual 144	com/microsoft/onlineid/internal/transport/Transport:closeConnection	()V
    //   111: aload_2
    //   112: ifnull +7 -> 119
    //   115: aload_2
    //   116: invokevirtual 149	java/io/InputStream:close	()V
    //   119: aload_3
    //   120: areturn
    //   121: ldc -105
    //   123: astore_1
    //   124: goto -92 -> 32
    //   127: astore_1
    //   128: ldc -103
    //   130: aload_1
    //   131: invokestatic 159	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   134: aload_3
    //   135: areturn
    //   136: astore_3
    //   137: aconst_null
    //   138: astore_2
    //   139: aload_2
    //   140: astore_1
    //   141: ldc -95
    //   143: aload_3
    //   144: invokestatic 159	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   147: aload_0
    //   148: getfield 45	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_transport	Lcom/microsoft/onlineid/internal/transport/Transport;
    //   151: invokevirtual 144	com/microsoft/onlineid/internal/transport/Transport:closeConnection	()V
    //   154: aload_2
    //   155: ifnull +7 -> 162
    //   158: aload_2
    //   159: invokevirtual 149	java/io/InputStream:close	()V
    //   162: aconst_null
    //   163: areturn
    //   164: astore_1
    //   165: ldc -103
    //   167: aload_1
    //   168: invokestatic 159	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   171: goto -9 -> 162
    //   174: astore_3
    //   175: aconst_null
    //   176: astore_2
    //   177: aload_2
    //   178: astore_1
    //   179: ldc -95
    //   181: aload_3
    //   182: invokestatic 159	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   185: aload_0
    //   186: getfield 45	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_transport	Lcom/microsoft/onlineid/internal/transport/Transport;
    //   189: invokevirtual 144	com/microsoft/onlineid/internal/transport/Transport:closeConnection	()V
    //   192: aload_2
    //   193: ifnull -31 -> 162
    //   196: aload_2
    //   197: invokevirtual 149	java/io/InputStream:close	()V
    //   200: goto -38 -> 162
    //   203: astore_1
    //   204: ldc -103
    //   206: aload_1
    //   207: invokestatic 159	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   210: goto -48 -> 162
    //   213: astore_1
    //   214: aload_0
    //   215: getfield 45	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_transport	Lcom/microsoft/onlineid/internal/transport/Transport;
    //   218: invokevirtual 144	com/microsoft/onlineid/internal/transport/Transport:closeConnection	()V
    //   221: aload_2
    //   222: ifnull +7 -> 229
    //   225: aload_2
    //   226: invokevirtual 149	java/io/InputStream:close	()V
    //   229: aload_1
    //   230: athrow
    //   231: astore_2
    //   232: ldc -103
    //   234: aload_2
    //   235: invokestatic 159	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   238: goto -9 -> 229
    //   241: astore_3
    //   242: aload_1
    //   243: astore_2
    //   244: aload_3
    //   245: astore_1
    //   246: goto -32 -> 214
    //   249: astore_3
    //   250: goto -73 -> 177
    //   253: astore_3
    //   254: goto -115 -> 139
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	257	0	this	DownloadProfileImageTask
    //   0	257	1	paramVarArgs	Void[]
    //   1	225	2	arrayOfVoid1	Void[]
    //   231	4	2	localIOException	IOException
    //   243	1	2	arrayOfVoid2	Void[]
    //   9	126	3	localObject1	Object
    //   136	8	3	localMalformedURLException1	java.net.MalformedURLException
    //   174	8	3	localNetworkException1	com.microsoft.onlineid.exception.NetworkException
    //   241	4	3	localObject2	Object
    //   249	1	3	localNetworkException2	com.microsoft.onlineid.exception.NetworkException
    //   253	1	3	localMalformedURLException2	java.net.MalformedURLException
    // Exception table:
    //   from	to	target	type
    //   115	119	127	java/io/IOException
    //   2	29	136	java/net/MalformedURLException
    //   32	81	136	java/net/MalformedURLException
    //   158	162	164	java/io/IOException
    //   2	29	174	com/microsoft/onlineid/exception/NetworkException
    //   32	81	174	com/microsoft/onlineid/exception/NetworkException
    //   196	200	203	java/io/IOException
    //   2	29	213	finally
    //   32	81	213	finally
    //   225	229	231	java/io/IOException
    //   85	90	241	finally
    //   92	97	241	finally
    //   99	104	241	finally
    //   141	147	241	finally
    //   179	185	241	finally
    //   85	90	249	com/microsoft/onlineid/exception/NetworkException
    //   92	97	249	com/microsoft/onlineid/exception/NetworkException
    //   99	104	249	com/microsoft/onlineid/exception/NetworkException
    //   85	90	253	java/net/MalformedURLException
    //   92	97	253	java/net/MalformedURLException
    //   99	104	253	java/net/MalformedURLException
  }
  
  /* Error */
  Bitmap getSavedUserTileImage()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: getfield 32	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_applicationContext	Landroid/content/Context;
    //   6: aload_0
    //   7: getfield 70	com/microsoft/onlineid/internal/profile/DownloadProfileImageTask:_userTileFileName	Ljava/lang/String;
    //   10: invokevirtual 176	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   13: astore_1
    //   14: aload_1
    //   15: invokestatic 137	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   18: astore_2
    //   19: aload_2
    //   20: astore_3
    //   21: aload_1
    //   22: ifnull +9 -> 31
    //   25: aload_1
    //   26: invokevirtual 179	java/io/FileInputStream:close	()V
    //   29: aload_2
    //   30: astore_3
    //   31: aload_3
    //   32: areturn
    //   33: astore_1
    //   34: ldc -75
    //   36: aload_1
    //   37: invokestatic 159	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   40: aload_2
    //   41: areturn
    //   42: astore_1
    //   43: aconst_null
    //   44: astore_1
    //   45: aload_1
    //   46: ifnull -15 -> 31
    //   49: aload_1
    //   50: invokevirtual 179	java/io/FileInputStream:close	()V
    //   53: aconst_null
    //   54: areturn
    //   55: astore_1
    //   56: ldc -75
    //   58: aload_1
    //   59: invokestatic 159	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   62: aconst_null
    //   63: areturn
    //   64: astore_2
    //   65: aconst_null
    //   66: astore_1
    //   67: aload_1
    //   68: ifnull +7 -> 75
    //   71: aload_1
    //   72: invokevirtual 179	java/io/FileInputStream:close	()V
    //   75: aload_2
    //   76: athrow
    //   77: astore_1
    //   78: ldc -75
    //   80: aload_1
    //   81: invokestatic 159	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   84: goto -9 -> 75
    //   87: astore_2
    //   88: goto -21 -> 67
    //   91: astore_2
    //   92: goto -47 -> 45
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	DownloadProfileImageTask
    //   13	13	1	localFileInputStream	java.io.FileInputStream
    //   33	4	1	localIOException1	IOException
    //   42	1	1	localFileNotFoundException1	FileNotFoundException
    //   44	6	1	localObject1	Object
    //   55	4	1	localIOException2	IOException
    //   66	6	1	localObject2	Object
    //   77	4	1	localIOException3	IOException
    //   18	23	2	localBitmap	Bitmap
    //   64	12	2	localObject3	Object
    //   87	1	2	localObject4	Object
    //   91	1	2	localFileNotFoundException2	FileNotFoundException
    //   1	31	3	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   25	29	33	java/io/IOException
    //   2	14	42	java/io/FileNotFoundException
    //   49	53	55	java/io/IOException
    //   2	14	64	finally
    //   71	75	77	java/io/IOException
    //   14	19	87	finally
    //   14	19	91	java/io/FileNotFoundException
  }
  
  protected void onPostExecute(Bitmap paramBitmap)
  {
    Object localObject;
    if (paramBitmap != null)
    {
      localObject = HttpResponseCache.getInstalled();
      if (localObject != null)
      {
        if (((HttpResponseCache)localObject).getHitCount() == 0)
        {
          localObject = "from network";
          ClientAnalytics.get().logEvent("Performance", "Load profile image", (String)localObject);
        }
      }
      else {
        this._imageView.setImageBitmap(paramBitmap);
      }
    }
    do
    {
      return;
      localObject = "from cache";
      break;
      paramBitmap = this._applicationContext.getFileStreamPath(this._userTileFileName);
    } while ((!paramBitmap.exists()) || (!this._newImageDownloaded));
    ClientAnalytics.get().logEvent("Performance", "Load profile image", "deleted");
    paramBitmap.delete();
    this._imageView.setImageDrawable(this._applicationContext.getResources().getDrawable(this._applicationContext.getResources().getIdentifier("msa_default_user_tile", "drawable", this._applicationContext.getPackageName())));
  }
  
  protected void onPreExecute()
  {
    Bitmap localBitmap = getSavedUserTileImage();
    if (localBitmap != null) {
      this._imageView.setImageBitmap(localBitmap);
    }
  }
  
  void saveUserTileImage(Bitmap paramBitmap)
  {
    Object localObject2;
    Object localObject1;
    if (paramBitmap != null)
    {
      localObject2 = null;
      localObject1 = null;
    }
    for (;;)
    {
      try
      {
        localFileOutputStream = this._applicationContext.openFileOutput(this._userTileFileName, 0);
        localObject1 = localFileOutputStream;
        localObject2 = localFileOutputStream;
        paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
      }
      catch (FileNotFoundException paramBitmap)
      {
        FileOutputStream localFileOutputStream;
        localObject2 = localObject1;
        Logger.error("Error saving user tile image.", paramBitmap);
        if (localObject1 == null) {
          continue;
        }
        try
        {
          ((FileOutputStream)localObject1).close();
          return;
        }
        catch (IOException paramBitmap)
        {
          Logger.error("Error closing file output stream.", paramBitmap);
          return;
        }
      }
      finally
      {
        if (localObject2 == null) {
          break label97;
        }
      }
      try
      {
        localFileOutputStream.close();
        return;
      }
      catch (IOException paramBitmap)
      {
        Logger.error("Error closing file output stream.", paramBitmap);
        return;
      }
    }
    try
    {
      ((FileOutputStream)localObject2).close();
      label97:
      throw paramBitmap;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Logger.error("Error closing file output stream.", localIOException);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\profile\DownloadProfileImageTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */