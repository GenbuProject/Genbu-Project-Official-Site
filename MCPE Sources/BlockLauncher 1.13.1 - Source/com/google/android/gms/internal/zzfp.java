package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.R.string;
import com.google.android.gms.ads.internal.zzr;
import java.util.Map;

@zzhb
public class zzfp
  extends zzfs
{
  private final Context mContext;
  private final Map<String, String> zzxA;
  
  public zzfp(zzjp paramzzjp, Map<String, String> paramMap)
  {
    super(paramzzjp, "storePicture");
    this.zzxA = paramMap;
    this.mContext = paramzzjp.zzhP();
  }
  
  public void execute()
  {
    if (this.mContext == null)
    {
      zzam("Activity context is not available");
      return;
    }
    if (!zzr.zzbC().zzM(this.mContext).zzdl())
    {
      zzam("Feature is not supported by the device.");
      return;
    }
    final String str1 = (String)this.zzxA.get("iurl");
    if (TextUtils.isEmpty(str1))
    {
      zzam("Image url cannot be empty.");
      return;
    }
    if (!URLUtil.isValidUrl(str1))
    {
      zzam("Invalid image url: " + str1);
      return;
    }
    final String str2 = zzal(str1);
    if (!zzr.zzbC().zzaE(str2))
    {
      zzam("Image type not recognized: " + str2);
      return;
    }
    AlertDialog.Builder localBuilder = zzr.zzbC().zzL(this.mContext);
    localBuilder.setTitle(zzr.zzbF().zzd(R.string.store_picture_title, "Save image"));
    localBuilder.setMessage(zzr.zzbF().zzd(R.string.store_picture_message, "Allow Ad to store image in Picture gallery?"));
    localBuilder.setPositiveButton(zzr.zzbF().zzd(R.string.accept, "Accept"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = (DownloadManager)zzfp.zza(zzfp.this).getSystemService("download");
        try
        {
          paramAnonymousDialogInterface.enqueue(zzfp.this.zzf(str1, str2));
          return;
        }
        catch (IllegalStateException paramAnonymousDialogInterface)
        {
          zzfp.this.zzam("Could not store picture.");
        }
      }
    });
    localBuilder.setNegativeButton(zzr.zzbF().zzd(R.string.decline, "Decline"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        zzfp.this.zzam("User canceled the download.");
      }
    });
    localBuilder.create().show();
  }
  
  String zzal(String paramString)
  {
    return Uri.parse(paramString).getLastPathSegment();
  }
  
  DownloadManager.Request zzf(String paramString1, String paramString2)
  {
    paramString1 = new DownloadManager.Request(Uri.parse(paramString1));
    paramString1.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, paramString2);
    zzr.zzbE().zza(paramString1);
    return paramString1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzfp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */