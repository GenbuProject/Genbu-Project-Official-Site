package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.google.android.gms.R.string;
import com.google.android.gms.ads.internal.zzr;
import java.util.Map;

@zzhb
public class zzfm
  extends zzfs
{
  private final Context mContext;
  private String zzCU;
  private long zzCV;
  private long zzCW;
  private String zzCX;
  private String zzCY;
  private final Map<String, String> zzxA;
  
  public zzfm(zzjp paramzzjp, Map<String, String> paramMap)
  {
    super(paramzzjp, "createCalendarEvent");
    this.zzxA = paramMap;
    this.mContext = paramzzjp.zzhP();
    zzeK();
  }
  
  private String zzaj(String paramString)
  {
    if (TextUtils.isEmpty((CharSequence)this.zzxA.get(paramString))) {
      return "";
    }
    return (String)this.zzxA.get(paramString);
  }
  
  private long zzak(String paramString)
  {
    paramString = (String)this.zzxA.get(paramString);
    if (paramString == null) {
      return -1L;
    }
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (NumberFormatException paramString) {}
    return -1L;
  }
  
  private void zzeK()
  {
    this.zzCU = zzaj("description");
    this.zzCX = zzaj("summary");
    this.zzCV = zzak("start_ticks");
    this.zzCW = zzak("end_ticks");
    this.zzCY = zzaj("location");
  }
  
  @TargetApi(14)
  Intent createIntent()
  {
    Intent localIntent = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
    localIntent.putExtra("title", this.zzCU);
    localIntent.putExtra("eventLocation", this.zzCY);
    localIntent.putExtra("description", this.zzCX);
    if (this.zzCV > -1L) {
      localIntent.putExtra("beginTime", this.zzCV);
    }
    if (this.zzCW > -1L) {
      localIntent.putExtra("endTime", this.zzCW);
    }
    localIntent.setFlags(268435456);
    return localIntent;
  }
  
  public void execute()
  {
    if (this.mContext == null)
    {
      zzam("Activity context is not available.");
      return;
    }
    if (!zzr.zzbC().zzM(this.mContext).zzdo())
    {
      zzam("This feature is not available on the device.");
      return;
    }
    AlertDialog.Builder localBuilder = zzr.zzbC().zzL(this.mContext);
    localBuilder.setTitle(zzr.zzbF().zzd(R.string.create_calendar_title, "Create calendar event"));
    localBuilder.setMessage(zzr.zzbF().zzd(R.string.create_calendar_message, "Allow Ad to create a calendar event?"));
    localBuilder.setPositiveButton(zzr.zzbF().zzd(R.string.accept, "Accept"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = zzfm.this.createIntent();
        zzr.zzbC().zzb(zzfm.zza(zzfm.this), paramAnonymousDialogInterface);
      }
    });
    localBuilder.setNegativeButton(zzr.zzbF().zzd(R.string.decline, "Decline"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        zzfm.this.zzam("Operation denied by user.");
      }
    });
    localBuilder.create().show();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzfm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */