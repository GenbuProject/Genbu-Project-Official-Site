package com.microsoft.onlineid.internal.profile;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.configuration.Environment;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.transport.Transport;
import com.microsoft.onlineid.internal.transport.TransportFactory;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.sts.ServerConfig;
import com.microsoft.onlineid.sts.ServerConfig.KnownEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadProfileImageTask
  extends AsyncTask<Void, Void, Bitmap>
{
  public static final String UserTileExtension = ".png";
  public static final String UserTileUrlFormat = "https://cid-%1$s.users.storage.%2$s/users/0x%1$s/myprofile/expressionprofile/profilephoto:UserTileStatic";
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
  
  protected Bitmap doInBackground(Void... paramVarArgs)
  {
    Object localObject5 = null;
    Object localObject6 = null;
    Bitmap localBitmap = null;
    Object localObject1 = localBitmap;
    Object localObject3 = localObject5;
    paramVarArgs = (Void[])localObject6;
    try
    {
      String str = this._account.getCid();
      localObject1 = localBitmap;
      localObject3 = localObject5;
      paramVarArgs = (Void[])localObject6;
      if (this._serverConfig.getEnvironment().equals(ServerConfig.KnownEnvironment.Production.getEnvironment())) {}
      for (Object localObject4 = "live.com";; localObject4 = "live-int.com")
      {
        localObject1 = localBitmap;
        localObject3 = localObject5;
        paramVarArgs = (Void[])localObject6;
        localObject4 = String.format("https://cid-%1$s.users.storage.%2$s/users/0x%1$s/myprofile/expressionprofile/profilephoto:UserTileStatic", new Object[] { str, localObject4 });
        localObject1 = localBitmap;
        localObject3 = localObject5;
        paramVarArgs = (Void[])localObject6;
        this._transport.openGetRequest(new URL((String)localObject4));
        localObject1 = localBitmap;
        localObject3 = localObject5;
        paramVarArgs = (Void[])localObject6;
        this._transport.setUseCaches(true);
        localObject1 = localBitmap;
        localObject3 = localObject5;
        paramVarArgs = (Void[])localObject6;
        localObject4 = this._transport.getResponseStream();
        localObject1 = localObject4;
        localObject3 = localObject4;
        paramVarArgs = (Void[])localObject4;
        localBitmap = BitmapFactory.decodeStream((InputStream)localObject4);
        localObject1 = localObject4;
        localObject3 = localObject4;
        paramVarArgs = (Void[])localObject4;
        saveUserTileImage(localBitmap);
        localObject1 = localObject4;
        localObject3 = localObject4;
        paramVarArgs = (Void[])localObject4;
        this._newImageDownloaded = true;
        this._transport.closeConnection();
        if (localObject4 != null) {}
        try
        {
          ((InputStream)localObject4).close();
          return localBitmap;
        }
        catch (IOException paramVarArgs)
        {
          Logger.error("Error closing response stream.", paramVarArgs);
          return localBitmap;
        }
      }
      try
      {
        paramVarArgs.close();
        throw ((Throwable)localObject2);
      }
      catch (IOException paramVarArgs)
      {
        for (;;)
        {
          Logger.error("Error closing response stream.", paramVarArgs);
        }
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      paramVarArgs = (Void[])localObject1;
      Logger.error("Error downloading image from url.", localMalformedURLException);
      this._transport.closeConnection();
      if (localObject1 != null) {}
      try
      {
        ((InputStream)localObject1).close();
        return null;
      }
      catch (IOException paramVarArgs)
      {
        for (;;)
        {
          Logger.error("Error closing response stream.", paramVarArgs);
        }
      }
    }
    catch (NetworkException localNetworkException)
    {
      for (;;)
      {
        paramVarArgs = localMalformedURLException;
        Logger.error("Error downloading image from url.", localNetworkException);
        this._transport.closeConnection();
        if (localMalformedURLException != null) {
          try
          {
            localMalformedURLException.close();
          }
          catch (IOException paramVarArgs)
          {
            Logger.error("Error closing response stream.", paramVarArgs);
          }
        }
      }
    }
    finally
    {
      this._transport.closeConnection();
      if (paramVarArgs == null) {}
    }
  }
  
  Bitmap getSavedUserTileImage()
  {
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        localFileInputStream = this._applicationContext.openFileInput(this._userTileFileName);
        localObject1 = localFileInputStream;
        localObject3 = localFileInputStream;
        Bitmap localBitmap = BitmapFactory.decodeStream(localFileInputStream);
        localObject1 = localBitmap;
        localObject3 = localObject1;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        FileInputStream localFileInputStream;
        localObject3 = localObject4;
        if (localObject1 == null) {
          continue;
        }
        try
        {
          ((FileInputStream)localObject1).close();
          return null;
        }
        catch (IOException localIOException1)
        {
          Logger.error("Error closing file input stream.", localIOException1);
          return null;
        }
      }
      finally
      {
        if (localObject3 == null) {
          break label87;
        }
      }
      try
      {
        localFileInputStream.close();
        localObject3 = localObject1;
        return (Bitmap)localObject3;
      }
      catch (IOException localIOException2)
      {
        Logger.error("Error closing file input stream.", localIOException2);
        return (Bitmap)localObject1;
      }
    }
    try
    {
      ((FileInputStream)localObject3).close();
      label87:
      throw ((Throwable)localObject2);
    }
    catch (IOException localIOException3)
    {
      for (;;)
      {
        Logger.error("Error closing file input stream.", localIOException3);
      }
    }
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\profile\DownloadProfileImageTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */